package com.ts.car.timesync.utils;

import com.ts.car.timesync.LogUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class DateUtils {
    private static final int COUNT = 7;
    private static final int INDEX_DAY = 3;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_MINUTE = 1;
    private static final int INDEX_MONTH = 4;
    private static final int INDEX_SECOND = 0;
    private static final int INDEX_WEEK = 6;
    private static final int INDEX_YEAR = 5;
    private static final String TAG = "TimeSync_UtilClass";
    public static final int TIME_ERR = -1;

    public static int[] millisToFormatDate(long original) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(original);
        int[] data = {date.get(13), date.get(12), date.get(11), date.get(5), date.get(2) + 1, date.get(1), date.get(7) - 1};
        LogUtils.warning(TAG, "getCalendarData, original:" + original + ", year:" + data[5] + ", month:" + data[4] + ", day:" + data[3] + ", hour:" + data[2] + ", minute:" + data[1] + ", second:" + data[0]);
        return data;
    }

    public static long formatDateToMillis(int[] data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(data[5] + "-" + data[4] + "-" + data[3] + " " + data[2] + ":" + data[1] + ":" + data[0]);
            long timeInMillis = date.getTime();
            return timeInMillis;
        } catch (ParseException ex) {
            LogUtils.warning(TAG, "date is null", ex);
            return -1L;
        }
    }
}
