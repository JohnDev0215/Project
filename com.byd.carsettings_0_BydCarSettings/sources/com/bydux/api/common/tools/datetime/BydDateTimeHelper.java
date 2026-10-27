package com.bydux.api.common.tools.datetime;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.provider.Settings;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
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

    public static int getSystemTime(Context context, int i) {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        int i4 = calendar.get(5);
        int i5 = calendar.get(11);
        int i6 = calendar.get(12);
        int i7 = calendar.get(13);
        int i8 = calendar.get(7);
        switch (i) {
            case 0:
                return i2;
            case 1:
                return 1 + i3;
            case 2:
                return i4;
            case 3:
                if (!is24HourFormat(context)) {
                    if (i5 > 12) {
                        i5 -= 12;
                    } else if (i5 == 0) {
                        return 12;
                    }
                }
                return i5;
            case 4:
                return i6;
            case 5:
                return i7;
            case 6:
                return i8 - 1;
            case 7:
                if (!is24HourFormat(context)) {
                    return i5 >= 12 ? 2 : 1;
                }
                break;
        }
        return 0;
    }

    public static boolean is24HourFormat(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        return string != null && string.equals(TimeManagerImpl.TIME_FORMAT_TWENTY_FOUR);
    }

    public static String timeFormat(int i) {
        return timeFormat(i, false);
    }

    public static String timeFormat(int i, boolean z) {
        int i2 = i / 1000;
        int i3 = (i2 / 60) % 60;
        int i4 = i2 % 60;
        int i5 = i2 / 3600;
        StringBuilder sb = new StringBuilder();
        if (i5 < 10) {
            sb.append("0");
            sb.append(i5);
        } else {
            sb.append(i5);
        }
        sb.append(":");
        if (i3 < 10) {
            sb.append("0");
            sb.append(i3);
        } else {
            sb.append(i3);
        }
        if (!z) {
            sb.append(":");
            if (i4 < 10) {
                sb.append("0");
                sb.append(i4);
            } else {
                sb.append(i4);
            }
        }
        return sb.toString();
    }
}
