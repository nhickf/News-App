package com.phnews.nick.phnews.utils;
import com.crashlytics.android.Crashlytics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatNewsApiDate(String inputDate){
        if (inputDate == null){
         return null;
        }
        try {
            String inputDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
            String outDateFormat = "EEE,d MMM yyyy KK:mm";

            SimpleDateFormat inputFormat = new SimpleDateFormat(inputDateFormat);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outDateFormat);

            Date date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        }
        catch (ParseException e){
            e.printStackTrace();
            Crashlytics.logException(e);
        }
        return inputDate;
    }
}
