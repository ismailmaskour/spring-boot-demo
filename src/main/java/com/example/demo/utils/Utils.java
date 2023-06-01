package com.example.demo.utils;

import java.util.Date;

public class Utils {
    

    @Deprecated //'2011/02/25'
    public String dateToString(Date date) {
        return date.getYear()+"/";
    }

}
