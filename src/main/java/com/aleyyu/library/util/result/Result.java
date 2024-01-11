package com.aleyyu.library.util.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result {

    boolean isSuccess;

    String message;

    public Result(boolean isSuccess){
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

}
