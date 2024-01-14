package com.aleyyu.library.util.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentErrorType implements BaseType {

    RENT_NOT_EXIST("E01", "This rent does not exist.");

    private String code;
    private String message;
}
