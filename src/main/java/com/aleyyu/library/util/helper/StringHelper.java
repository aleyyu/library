package com.aleyyu.library.util.helper;

import org.apache.commons.lang3.StringUtils;

public class StringHelper {

    public static boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }
}
