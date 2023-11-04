package org.example.trim.re;

import cn.hutool.core.util.ClassUtil;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author RippleChan
 * @date 2023/11/4
 */
@SuppressWarnings("ALL")
@UtilityClass
public class TrimUtils {
    
    /**
     * @see org.example.trim.TrimUtilsTest#trimTest
     * 不支持基本类型
     * @param o
     */
    public static void trim(Object o) {
        if (Objects.isNull(o)) {
            return;
        }
        boolean jdkClass = ClassUtil.isJdkClass(o.getClass());
        if (jdkClass) {
            return;
        }
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(Boolean.TRUE);
            Class<?> type = field.getType();
            if (Objects.equals(type, String.class)) {
                /**
                 * 获取原始值
                 */
                try {
                    Object original = field.get(o);
                    if (Objects.isNull(original)) {
                        continue;
                    }
                    String originalStr = (String) original;
                    field.set(o, originalStr.trim());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
