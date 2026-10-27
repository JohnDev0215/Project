package com.bydux.api.common.tools.datetime;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.provider.Settings;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
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

    public static int getSystemTime(Context context, int i8) {
        Calendar calendar = Calendar.getInstance();
        int i9 = calendar.get(1);
        int i10 = calendar.get(2);
        int i11 = calendar.get(5);
        int i12 = calendar.get(11);
        int i13 = calendar.get(12);
        int i14 = calendar.get(13);
        int i15 = calendar.get(7);
        switch (i8) {
            case 0:
                return i9;
            case 1:
                return 1 + i10;
            case 2:
                return i11;
            case 3:
                if (!is24HourFormat(context)) {
                    if (i12 > 12) {
                        i12 -= 12;
                    } else if (i12 == 0) {
                        return 12;
                    }
                }
                return i12;
            case 4:
                return i13;
            case 5:
                return i14;
            case 6:
                return i15 - 1;
            case 7:
                if (!is24HourFormat(context)) {
                    return i12 >= 12 ? 2 : 1;
                }
                break;
        }
        return 0;
    }

    public static boolean is24HourFormat(Context context) {
        String string = Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24);
        return string != null && string.equals("24");
    }

    public static String timeFormat(int i8) {
        return timeFormat(i8, false);
    }

    public static String timeFormat(int i8, boolean z7) {
        int i9 = i8 / 1000;
        int i10 = (i9 / 60) % 60;
        int i11 = i9 % 60;
        int i12 = i9 / 3600;
        StringBuilder sb = new StringBuilder();
        if (i12 < 10) {
            sb.append("0");
            sb.append(i12);
        } else {
            sb.append(i12);
        }
        sb.append(":");
        if (i10 < 10) {
            sb.append("0");
            sb.append(i10);
        } else {
            sb.append(i10);
        }
        if (!z7) {
            sb.append(":");
            if (i11 < 10) {
                sb.append("0");
                sb.append(i11);
            } else {
                sb.append(i11);
            }
        }
        return sb.toString();
    }
}
