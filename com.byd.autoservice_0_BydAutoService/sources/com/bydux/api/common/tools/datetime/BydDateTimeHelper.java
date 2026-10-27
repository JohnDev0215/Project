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

    public static int getSystemTime(Context context, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        int i7 = calendar.get(11);
        int i8 = calendar.get(12);
        int i9 = calendar.get(13);
        int i10 = calendar.get(7);
        switch (i3) {
            case 0:
                return i4;
            case 1:
                return 1 + i5;
            case 2:
                return i6;
            case 3:
                if (!is24HourFormat(context)) {
                    if (i7 > 12) {
                        i7 -= 12;
                    } else if (i7 == 0) {
                        return 12;
                    }
                }
                return i7;
            case 4:
                return i8;
            case 5:
                return i9;
            case 6:
                return i10 - 1;
            case 7:
                if (!is24HourFormat(context)) {
                    return i7 >= 12 ? 2 : 1;
                }
                break;
        }
        return 0;
    }

    public static boolean is24HourFormat(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        return string != null && string.equals(TimeManagerImpl.TIME_FORMAT_TWENTY_FOUR);
    }

    public static String timeFormat(int i3) {
        return timeFormat(i3, false);
    }

    public static String timeFormat(int i3, boolean z3) {
        int i4 = i3 / 1000;
        int i5 = (i4 / 60) % 60;
        int i6 = i4 % 60;
        int i7 = i4 / 3600;
        StringBuilder sb = new StringBuilder();
        if (i7 < 10) {
            sb.append("0");
            sb.append(i7);
        } else {
            sb.append(i7);
        }
        sb.append(":");
        if (i5 < 10) {
            sb.append("0");
            sb.append(i5);
        } else {
            sb.append(i5);
        }
        if (!z3) {
            sb.append(":");
            if (i6 < 10) {
                sb.append("0");
                sb.append(i6);
            } else {
                sb.append(i6);
            }
        }
        return sb.toString();
    }
}
