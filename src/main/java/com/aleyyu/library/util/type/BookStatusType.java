package com.aleyyu.library.util.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookStatusType implements BaseType{

    AVAILABLE("Y", "Available"),

    UNAVAILABLE("N", "Unavailable");

    private String code;
    private String message;

}
