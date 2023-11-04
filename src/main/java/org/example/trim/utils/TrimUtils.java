package org.example.trim.utils;

import cn.hutool.core.util.ClassUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.example.trim.core.ServiceCodeEnum;
import org.example.trim.core.ServiceException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author RippleChan
 * @date 2023/11/4
 */
@Slf4j
@SuppressWarnings("ALL")
@UtilityClass
public class TrimUtils {
    
    public static <K, V> void trim(Map<K, V> map, boolean isKey) {
        if (Objects.isNull(map) || map.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        Map<K, V> tmp = new LinkedHashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<K, V> next = iterator.next();
            K key = next.getKey();
            V value = next.getValue();
            if (isKey) {
                K trim = trim(key);
                tmp.put(trim, value);
            } else {
                V trim = trim(value);
                tmp.put(key, trim);
            }
        }
        map.clear();
        map.putAll(tmp);
    }
    
    public static <T> void trim(Collection<T> collection) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            return;
        }
        ArrayList<T> tmp = new ArrayList<>();
        tmp.addAll(collection);
        collection.clear();
        for (int i = 0; i < tmp.size(); i++) {
            T t = tmp.get(i);
            T trim = trim(t);
            collection.add(t);
        }
    }
    
    /**
     * @see org.example.trim.TrimUtilsTest#trimTest
     * @param o
     */
    public static <T> T trim(T o) {
        if (Objects.isNull(o)) {
            return o;
        }
        boolean jdkClass = ClassUtil.isJdkClass(o.getClass());
        if (jdkClass) {
            return trimString(o);
        }
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Class<?> type = field.getType();
            if (Objects.equals(type, String.class)) {
                /**
                 * 获取原始值
                 */
                try {
                    field.setAccessible(Boolean.TRUE);
                    Object original = field.get(o);
                    if (Objects.isNull(original)) {
                        continue;
                    }
                    String originalStr = (String) original;
                    field.set(o, originalStr.trim());
                } catch (IllegalAccessException e) {
                    throw new ServiceException(ServiceCodeEnum.SYS_ERROR, "访问失败");
                }
            }
        }
        return o;
    }
    
    private static <T> T trimString(T o) {
        if (!Objects.equals(o.getClass(), String.class)) {
            log.error("不支持的类型，期望：String.class，实际：{}", o.getClass());
            throw new ServiceException(ServiceCodeEnum.SYS_ERROR, "不支持的类型");
        }
        String originalStr = (String) o;
        return (T) originalStr.trim();
    }
    
}
