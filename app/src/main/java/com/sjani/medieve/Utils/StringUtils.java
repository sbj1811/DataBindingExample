package com.sjani.medieve.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to format Strings
 */
public class StringUtils {

    /**
     * Converts ISO 8601 to custom format
     *
     * @param dateTime ISO 8601 format dtring
     * @return custom date-time format string
     */
    public static String formatDateTime(String dateTime) {
        Date date = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            date = simpleDateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("h:mm a MMM dd, yyyy").format(date);
    }

    public static String ConvertToISO8601(String dateString, String timeString) {
        Date date = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy-HH:mm");
            date = simpleDateFormat.parse(dateString + "-" + timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
    }


}
