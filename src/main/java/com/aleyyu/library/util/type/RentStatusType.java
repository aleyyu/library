package com.aleyyu.library.util.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RentStatusType implements BaseType {

    RETURNED("R", "Returned"),
    NOT_RETURNED("N", "Not returned");

    private String code;
    private String message;
}
