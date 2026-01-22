package com.example.eventsnap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
    public static String getDateDDMMMYYYYFormat(Date date) {
        try {

            SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            return displayDateFormat.format(date);
        } catch (Exception e) {

        }
        return "";
    }
}
