package com.appdynamics.extensions.service.appd;


public class ServiceException extends RuntimeException{
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
