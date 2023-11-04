package org.example.trim.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author RippleChan
 * @date 2023/11/4
 */
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    
    private Long id;

    private String no;
    
}
