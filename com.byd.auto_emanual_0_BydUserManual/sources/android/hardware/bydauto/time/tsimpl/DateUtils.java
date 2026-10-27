package android.hardware.bydauto.time.tsimpl;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    public static int[] millisToFormatDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13)};
    }

    public static long formatDateToMillis(int[] iArr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(iArr[0]).append("-").append(iArr[1]).append("-").append(iArr[2]).append(" ").append(iArr[3]).append(":").append(iArr[4]).append(":").append(iArr[5]);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sb.toString()).getTime();
        } catch (ParseException e) {
            Log.w(TAG, "formatDateToMillis error, date is illegal, data:" + formatIntArray(iArr), e);
            return -1L;
        } catch (Throwable th) {
            Log.w(TAG, "formatDateToMillis error, unknown exception, data:" + formatIntArray(iArr), th);
            return -1L;
        }
    }

    public static long formatYearMonthDayToMillis(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        calendar.set(5, i3);
        return calendar.getTimeInMillis();
    }

    public static long formatHourMinuteSecondToMillis(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        return calendar.getTimeInMillis();
    }

    public static boolean isAutoTimeSwitchOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "auto_time", 0) > 0;
    }

    public static boolean isAutoTimeZoneSwitchOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "auto_time_zone", 0) > 0;
    }

    private static String formatIntArray(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr == null) {
            return "data is null!";
        }
        if (iArr.length == 0) {
            return "data length is 0!";
        }
        int length = iArr.length;
        sb.append("data length:" + length);
        while (true) {
            length--;
            if (length >= 0) {
                sb.append(", index-" + length + ":" + iArr[length]);
            } else {
                return sb.toString();
            }
        }
    }
}
