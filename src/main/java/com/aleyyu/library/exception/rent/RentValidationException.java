package com.aleyyu.library.exception.rent;

import com.aleyyu.library.exception.BaseException;

public class RentValidationException extends BaseException {

    public RentValidationException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
