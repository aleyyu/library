package com.aleyyu.library.util.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookErrorType implements BaseType{

    BOOK_DOES_NOT_EXIST("E001", "This book does not exist."),
    BOOK_BY_ISBN_ALREADY_EXISTS("E002", "This ISBN already exists"),
    BOOK_UNAVAILABLE("E003", "This book is unavailable");

    private String code;
    private String message;
}
