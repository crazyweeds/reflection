package org.example.trim.old;

import cn.hutool.json.JSONUtil;
import org.example.trim.entity.Order;
import org.example.trim.entity.User;

import java.util.Objects;

/**
 * @author RippleChan
 * @date 2023/11/4
 */
public class Old {
    
    public static void main(String[] args) {
        User user = User.builder().username("test ").build();
        if (!Objects.isNull(user) && !Objects.isNull(user.getUsername())) {
            user.setUsername(user.getUsername().trim());
        }
        System.out.println(JSONUtil.toJsonPrettyStr(user));
        
        Order order = Order.builder().no("X888888888 ").build();
        if (!Objects.isNull(order) && !Objects.isNull(order.getNo())) {
            order.setNo(order.getNo().trim());
        }
        System.out.println(JSONUtil.toJsonPrettyStr(order));
    }

}
