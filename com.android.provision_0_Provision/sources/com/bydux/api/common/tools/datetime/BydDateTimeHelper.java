package com.bydux.api.common.tools.datetime;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.provider.Settings;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class BydDateTimeHelper {
    public static final int FUNC_ID_DAY = 2;
    public static final int FUNC_ID_HOUR = 3;
    public static final int FUNC_ID_MINUTE = 4;
    public static final int FUNC_ID_MONTH = 1;
    public static final int FUNC_ID_SECOND = 5;
    public static final int FUNC_ID_TIME_MODE = 7;
    public static final int FUNC_ID_WEEK = 6;
    public static final int FUNC_ID_YEAR = 0;

    private BydDateTimeHelper() {
    }

    public static int getSystemTime(Context context, int id) {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        int i7 = calendar.get(7);
        switch (id) {
            case 0:
                return i;
            case 1:
                return 1 + i2;
            case 2:
                return i3;
            case 3:
                if (!is24HourFormat(context)) {
                    if (i4 > 12) {
                        i4 -= 12;
                    } else if (i4 == 0) {
                        return 12;
                    }
                }
                return i4;
            case 4:
                return i5;
            case 5:
                return i6;
            case 6:
                return i7 - 1;
            case 7:
                if (!is24HourFormat(context)) {
                    return i4 >= 12 ? 2 : 1;
                }
                break;
        }
        return 0;
    }

    public static boolean is24HourFormat(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        return string != null && string.equals(TimeManagerImpl.TIME_FORMAT_TWENTY_FOUR);
    }

    public static String timeFormat(int ms) {
        return timeFormat(ms, false);
    }

    public static String timeFormat(int ms, boolean ignoreSecond) {
        int i = ms / 1000;
        int i2 = (i / 60) % 60;
        int i3 = i % 60;
        int i4 = i / 3600;
        StringBuilder sb = new StringBuilder();
        if (i4 < 10) {
            sb.append("0").append(i4);
        } else {
            sb.append(i4);
        }
        sb.append(":");
        if (i2 < 10) {
            sb.append("0").append(i2);
        } else {
            sb.append(i2);
        }
        if (!ignoreSecond) {
            sb.append(":");
            if (i3 < 10) {
                sb.append("0").append(i3);
            } else {
                sb.append(i3);
            }
        }
        return sb.toString();
    }
}
