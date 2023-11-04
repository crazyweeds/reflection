package org.example.trim.core;

/**
 * @author RippleChan
 * @date 2023/11/4
 */
public class ServiceException extends RuntimeException {
    
    private ServiceCodeEnum serviceCodeEnum;
    
    private String message;
    
    private ServiceException() {
        throw new RuntimeException("禁止");
    }
    
    
    public ServiceException(ServiceCodeEnum serviceCodeEnum, String message) {
        this.serviceCodeEnum = serviceCodeEnum;
        this.message = message;
    }
    
    public ServiceException(ServiceCodeEnum serviceCodeEnum) {
        this.serviceCodeEnum = serviceCodeEnum;
        this.message = serviceCodeEnum.getMessage();
    }
    
}
