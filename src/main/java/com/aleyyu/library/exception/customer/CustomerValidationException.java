package com.aleyyu.library.exception.customer;

import com.aleyyu.library.exception.BaseException;

public class CustomerValidationException extends BaseException {

    public CustomerValidationException(String errorCode, String errorMessage){
        super(errorCode, errorMessage);
    }
}
