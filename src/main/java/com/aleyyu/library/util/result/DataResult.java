package com.aleyyu.library.util.result;

import lombok.Getter;

@Getter
public class DataResult<T> extends Result{

    private T data;

    public DataResult(T data, boolean isSuccess, String message) {
        super(isSuccess, message);
        this.data = data;
    }

    public DataResult(T data, boolean isSuccess) {
        super(isSuccess);
        this.data = data;
    }
}
