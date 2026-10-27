package android.hardware.bydauto.time.tsimpl;

import android.content.Context;
import android.hardware.bydauto.properties.CarSettings;
import android.provider.Settings;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class DateUtils {
    private static final int COUNT = 6;
    private static final int INDEX_DAY = 2;
    private static final int INDEX_HOUR = 3;
    private static final int INDEX_MINUTE = 4;
    private static final int INDEX_MONTH = 1;
    private static final int INDEX_SECOND = 5;
    private static final int INDEX_YEAR = 0;
    private static final String TAG = "TimeSync_UtilClass";
    public static final int TIME_ERR = -1;

    public static int[] millisToFormatDate(long original) {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(original);
        int[] data = {date.get(1), date.get(2) + 1, date.get(5), date.get(11), date.get(12), date.get(13)};
        return data;
    }

    public static long formatDateToMillis(int[] data) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(data[0] + "-" + data[1] + "-" + data[2] + " " + data[3] + ":" + data[4] + ":" + data[5]);
            long timeInMillis = date.getTime();
            return timeInMillis;
        } catch (ParseException ex) {
            Log.w(TAG, "formatDateToMillis error, date is illegal, data:" + formatIntArray(data), ex);
            return -1L;
        } catch (Throwable ex2) {
            Log.w(TAG, "formatDateToMillis error, unknown exception, data:" + formatIntArray(data), ex2);
            return -1L;
        }
    }

    public static long formatYearMonthDayToMillis(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month - 1);
        calendar.set(5, day);
        return calendar.getTimeInMillis();
    }

    public static long formatHourMinuteSecondToMillis(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, hour);
        calendar.set(12, minute);
        calendar.set(13, second);
        return calendar.getTimeInMillis();
    }

    public static boolean isAutoTimeSwitchOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), CarSettings.UserTableData.AUTO_TIME, 0) > 0;
    }

    public static boolean isAutoTimeZoneSwitchOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "auto_time_zone", 0) > 0;
    }

    private static String formatIntArray(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        if (data == null) {
            return "data is null!";
        }
        if (data.length == 0) {
            return "data length is 0!";
        }
        int n = data.length;
        stringBuilder.append("data length:" + n);
        for (int n2 = n + (-1); n2 >= 0; n2 += -1) {
            stringBuilder.append(", index-" + n2 + ":" + data[n2]);
        }
        return stringBuilder.toString();
    }
}
