package com.ruyue.note.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    public static long dateToStamp(String time) throws ParseException {
        SimpleDateFormat sdr = new SimpleDateFormat(FORMAT_YMDHM, Locale.CHINA);
        Date date = sdr.parse(time);
        return date.getTime();
    }

    public static String stampToDate(long lt){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_YMDHM, Locale.CHINA);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
