package com.aleyyu.library.util.helper;

import com.aleyyu.library.util.constants.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {


    public static LocalDate convert(String dateStr){
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_INPUT));
    }
}
