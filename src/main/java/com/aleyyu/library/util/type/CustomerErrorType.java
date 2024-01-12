package com.aleyyu.library.util.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerErrorType implements BaseType{

    CUSTOMER_NOT_EXIST("E01", "This customer does not exist."),
    CUSTOMER_NAME_MISSING("E02", "Customer name is missing. Please check the given information."),
    LIBRARY_NO_MISSING("E03", "Library number is missing. Please check the given information."),
    ADDRESS_MISSING("E04", "Address is missing. Please check the given information."),
    PHONE_NUMBER_MISSING("E05", "Phone number is missing. Please check the given information.");

    private String code;
    private String message;
}
