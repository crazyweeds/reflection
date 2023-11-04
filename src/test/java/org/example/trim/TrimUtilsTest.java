package org.example.trim;

import cn.hutool.json.JSONUtil;
import org.example.trim.entity.Order;
import org.example.trim.entity.User;
import org.example.trim.re.TrimUtils;
import org.junit.Test;

/**
 * @see org.example.trim.re.TrimUtils
 * @author RippleChan
 * @date 2023/11/4
 */
public class TrimUtilsTest {
    
    /**
     * trim基本类型
     */
    @Test
    public void trimBasic() {
        TrimUtils.trim("abc ");
    }
    
    @Test
    public void trimNull() {
        TrimUtils.trim(null);
    }
    
    /**
     * @see org.example.trim.re.TrimUtils#trim
     */
    @Test
    public void trimUserTest() {
        User build = User.builder()
                .id(1L)
                .username(" test ")
                .build();
        System.out.println("处理前：" + JSONUtil.toJsonPrettyStr(build));
        TrimUtils.trim(build);
        System.out.println("处理后：" + JSONUtil.toJsonPrettyStr(build));
    }
    
    @Test
    public void trimOrderTest() {
        Order build = Order.builder().build();
        System.out.println("处理前：" + JSONUtil.toJsonPrettyStr(build));
        TrimUtils.trim(build);
        System.out.println("处理后：" + JSONUtil.toJsonPrettyStr(build));
    }

}
