package com.sponsors.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {
    public static String getDateString(Date date, String dateFormat) {
        DateTimeFormatter objDateFormat = null;
        DateTime objDateTime = null;
        String objFormateDate = null;
        try {
            objDateFormat = DateTimeFormat.forPattern(dateFormat);
            objDateTime = new DateTime(date);
            objFormateDate = objDateFormat.print(objDateTime);
        } catch (Exception exception) {
            objFormateDate = null;
        } finally {
            objDateFormat = null;
            objDateTime = null;
        }
        return objFormateDate;
    }
}
