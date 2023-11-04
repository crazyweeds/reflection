package org.example.trim;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.trim.entity.Order;
import org.example.trim.entity.User;
import org.example.trim.utils.TrimUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @see TrimUtils
 * @author RippleChan
 * @date 2023/11/4
 */
@Slf4j
public class TrimUtilsTest {
    
    @Test
    public void trimLong() {
        Long trim = TrimUtils.trim(1L);
        System.out.println(trim);
    }
    
    /**
     * trim基本类型
     */
    @Test
    public void trimBasic() {
        String abc = TrimUtils.trim("abc ");
        System.out.println(abc);
    }
    
    @Test
    public void trimNull() {
        TrimUtils.trim(null);
    }
    
    /**
     * @see TrimUtils#trim
     */
    @Test
    public void trimUserTest() {
        User build = User.builder()
                .id(1L)
                .username(" test ")
                .build();
        System.out.println("处理前：" + JSONUtil.toJsonPrettyStr(build));
        User trim = TrimUtils.trim(build);
        System.out.println("处理后：" + JSONUtil.toJsonPrettyStr(trim));
    }
    
    @Test
    public void trimOrderTest() {
        Order build = Order.builder().build();
        System.out.println("处理前：" + JSONUtil.toJsonPrettyStr(build));
        TrimUtils.trim(build);
        System.out.println("处理后：" + JSONUtil.toJsonPrettyStr(build));
    }
    
    @Test
    public void trimList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a   ");
        strings.add("b");
        strings.add(null);
        TrimUtils.trim(strings);
        strings.forEach(System.out::println);
    }
    
    @Test
    public void trimUsers() {
        ArrayList<Object> users = new ArrayList<>();
        users.add(User.builder().build());
        users.add(User.builder().username("aabc ").build());
        users.add(null);
        users.add(1);
        TrimUtils.trim(users);
        users.forEach(u -> System.out.println(JSONUtil.toJsonPrettyStr(u)));
    }
    
    @Test
    public void trimMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("abc", "abc");
        map.put("abc ", "abc");
        TrimUtils.trim(map, Boolean.TRUE);
        System.out.println(JSONUtil.toJsonPrettyStr(map));
    }
    
    @Test
    public void trimMapValue() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("abc", "abc ");
        map.put("abc ", "abc");
        TrimUtils.trim(map, Boolean.FALSE);
        System.out.println(JSONUtil.toJsonPrettyStr(map));
    }

}
