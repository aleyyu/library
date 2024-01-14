package com.aleyyu.library.exception.book;

import com.aleyyu.library.exception.BaseException;
import com.aleyyu.library.model.Book;

import java.util.Objects;

public class BookValidationException extends BaseException {

    public BookValidationException(String errorCode, String errorMessage){
        super(errorCode, errorMessage);
    }
}
