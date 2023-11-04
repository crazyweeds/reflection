package org.example.trim.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author RippleChan
 * @date 2023/11/4 23:01
 */
@Getter
@AllArgsConstructor
public enum ServiceCodeEnum {
    
    SYS_ERROR(-1, "系统繁忙"),
    OK(0, "OK"),;
    
    /**
     * 码值
     */
    private Integer code;
    
    /**
     * 提示信息
     */
    private String message;
    
}
