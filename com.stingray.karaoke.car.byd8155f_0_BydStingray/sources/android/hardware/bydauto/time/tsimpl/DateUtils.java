package android.hardware.bydauto.time.tsimpl;

import M5.b;
import android.content.Context;
import android.hardware.bydauto.properties.CarSettings;
import android.provider.Settings;
import h6.n;
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

    public static long formatDateToMillis(int[] iArr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(iArr[0] + "-" + iArr[1] + "-" + iArr[2] + " " + iArr[3] + ":" + iArr[4] + ":" + iArr[5]).getTime();
        } catch (ParseException e10) {
            n.u(TAG, "formatDateToMillis error, date is illegal, data:" + formatIntArray(iArr), e10);
            return -1L;
        } catch (Throwable th) {
            n.u(TAG, "formatDateToMillis error, unknown exception, data:" + formatIntArray(iArr), th);
            return -1L;
        }
    }

    public static long formatHourMinuteSecondToMillis(int i7, int i10, int i11) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, i7);
        calendar.set(12, i10);
        calendar.set(13, i11);
        return calendar.getTimeInMillis();
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
            if (length < 0) {
                return sb.toString();
            }
            StringBuilder sbJ = b.j(", index-", length, ":");
            sbJ.append(iArr[length]);
            sb.append(sbJ.toString());
        }
    }

    public static long formatYearMonthDayToMillis(int i7, int i10, int i11) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i7);
        calendar.set(2, i10 - 1);
        calendar.set(5, i11);
        return calendar.getTimeInMillis();
    }

    public static boolean isAutoTimeSwitchOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), CarSettings.UserTableData.AUTO_TIME, 0) > 0;
    }

    public static boolean isAutoTimeZoneSwitchOpen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "auto_time_zone", 0) > 0;
    }

    public static int[] millisToFormatDate(long j3) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j3);
        return new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13)};
    }
}
