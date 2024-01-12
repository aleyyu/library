package com.aleyyu.library.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final String errorCode;
    private final String errorMessage;

    public BaseException(Throwable cause){
        super(cause);
        this.errorCode = "";
        this.errorMessage = "";
    }

    public BaseException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
