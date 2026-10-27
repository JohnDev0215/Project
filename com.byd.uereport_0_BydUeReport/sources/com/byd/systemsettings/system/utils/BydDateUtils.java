package com.byd.systemsettings.system.utils;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.icu.util.Calendar;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.systemsettings.system.model.BydDate;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public final class BydDateUtils {
    public static final int DATE_INDEX_INVALID = -1;
    private static final long DAY_SECS = 86400;
    public static final String DEF_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_MM_EN = "HH:mm, MMM.d, yyyy";
    public static final String FORMAT_MM_EN_12 = "hh:mm a, MMM.d, yyyy";
    public static final String FORMAT_MM_ZH = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_MM_ZH_12 = "yyyy-MM-dd a hh:mm";
    public static final int HOUR_MAX = 23;
    public static final int MINUTE_MAX = 59;
    public static final int MONTH = 1;
    public static final int MONTH_3 = 2;
    public static final int MONTH_MAX = 12;
    public static final long SECS_WEEK = 518400;
    private static final String TAG = "BydDateUtils";
    public static final int UNDEFINED = -1;
    public static final int WEEK = 0;
    public static final int YEAR = 3;
    private static SimpleDateFormat sdfDate;

    public static BydDate getBydDate(String str) {
        int i8;
        int i9;
        if (TextUtils.isEmpty(str) || !str.contains("-")) {
            return null;
        }
        String[] strArrSplit = str.split("-");
        int i10 = 0;
        try {
            i8 = Integer.parseInt(strArrSplit[0]);
            try {
                i9 = Integer.parseInt(strArrSplit[1]);
                try {
                    i10 = Integer.parseInt(strArrSplit[2]);
                } catch (Exception e8) {
                    e = e8;
                    a.b(TAG, "--getBydDate---e = " + e.toString());
                }
            } catch (Exception e9) {
                e = e9;
                i9 = 0;
            }
        } catch (Exception e10) {
            e = e10;
            i8 = 0;
            i9 = 0;
        }
        return new BydDate(i8, i9, i10, 0, 0, 0);
    }

    public static BydDate getBydDateTime(String str) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        if (!TextUtils.isEmpty(str) && str.contains(" ")) {
            int i13 = 0;
            a.b(TAG, "--getBydDateTime---date = " + str);
            String[] strArrSplit = str.split(" ");
            if (strArrSplit != null && strArrSplit.length >= 2) {
                String str2 = strArrSplit[0];
                String str3 = strArrSplit[1];
                a.b(TAG, "--getBydDateTime---dateStr = " + str2);
                a.b(TAG, "--getBydDateTime---timeStr = " + str3);
                if (!str2.contains("-")) {
                    return null;
                }
                String[] strArrSplit2 = str2.split("-");
                String[] strArrSplit3 = str3.split(":");
                try {
                    i9 = Integer.parseInt(strArrSplit2[0]);
                    try {
                        i10 = Integer.parseInt(strArrSplit2[1]);
                        try {
                            i8 = Integer.parseInt(strArrSplit2[2]);
                            try {
                                i11 = Integer.parseInt(strArrSplit3[0]);
                                try {
                                    i12 = Integer.parseInt(strArrSplit3[1]);
                                    try {
                                        i13 = Integer.parseInt(strArrSplit3[2]);
                                    } catch (Exception e8) {
                                        e = e8;
                                        a.b(TAG, "--getBydDateTime---e = " + e.toString());
                                    }
                                } catch (Exception e9) {
                                    e = e9;
                                    i12 = 0;
                                }
                            } catch (Exception e10) {
                                e = e10;
                                i11 = 0;
                                i12 = i11;
                                a.b(TAG, "--getBydDateTime---e = " + e.toString());
                                return new BydDate(i9, i10, i8, i11, i12, i13);
                            }
                        } catch (Exception e11) {
                            e = e11;
                            i8 = 0;
                            i11 = 0;
                        }
                    } catch (Exception e12) {
                        e = e12;
                        i8 = 0;
                        i10 = 0;
                        i11 = i10;
                        i12 = i11;
                        a.b(TAG, "--getBydDateTime---e = " + e.toString());
                        return new BydDate(i9, i10, i8, i11, i12, i13);
                    }
                } catch (Exception e13) {
                    e = e13;
                    i8 = 0;
                    i9 = 0;
                    i10 = 0;
                }
                return new BydDate(i9, i10, i8, i11, i12, i13);
            }
        }
        return null;
    }

    public static BydDate getCurDate() {
        Calendar calendar = Calendar.getInstance();
        return new BydDate(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }

    public static BydDate getCurDateAmpm(Context context) {
        Calendar calendar = Calendar.getInstance();
        int i8 = calendar.get(1);
        int i9 = calendar.get(2) + 1;
        int i10 = calendar.get(5);
        int i11 = calendar.get(11);
        BydDate bydDate = new BydDate(i8, i9, i10, i11, calendar.get(12), calendar.get(13));
        bydDate.setH24(is24H(context));
        bydDate.setAm(!bydDate.isH24() && isAm());
        if (isAm() && i11 >= 12) {
            bydDate.setDay(i11 - 12);
        }
        return bydDate;
    }

    public static String getDate(long j8, String str) {
        if (TextUtils.isEmpty(str)) {
            str = FORMAT_DATE;
        }
        return new SimpleDateFormat(str).format(new Date(j8));
    }

    public static int getDayIndexFromNow(long j8) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        String timeFormat = getTimeFormat(System.currentTimeMillis(), FORMAT_DATE);
        String timeFormat2 = getTimeFormat(j8, FORMAT_DATE);
        if (!TextUtils.isEmpty(timeFormat) && timeFormat.contains("-") && !TextUtils.isEmpty(timeFormat2) && timeFormat2.contains("-")) {
            String[] strArrSplit = timeFormat.split("-");
            String[] strArrSplit2 = timeFormat2.split("-");
            try {
                i11 = Integer.parseInt(strArrSplit[0]);
                try {
                    i12 = Integer.parseInt(strArrSplit[1]);
                    try {
                        i10 = Integer.parseInt(strArrSplit[2]);
                        try {
                            i8 = Integer.parseInt(strArrSplit2[0]);
                            try {
                                i9 = Integer.parseInt(strArrSplit2[1]);
                                try {
                                    i13 = Integer.parseInt(strArrSplit2[2]);
                                } catch (Exception e8) {
                                    e = e8;
                                    a.b(TAG, "--getCurDay---e = " + e.toString());
                                    i13 = -1;
                                }
                            } catch (Exception e9) {
                                e = e9;
                                i9 = -1;
                            }
                        } catch (Exception e10) {
                            e = e10;
                            i8 = -1;
                            i9 = -1;
                        }
                    } catch (Exception e11) {
                        e = e11;
                        i8 = -1;
                        i9 = -1;
                        i10 = 0;
                    }
                } catch (Exception e12) {
                    e = e12;
                    i8 = -1;
                    i9 = -1;
                    i10 = 0;
                    i12 = 0;
                }
            } catch (Exception e13) {
                e = e13;
                i8 = -1;
                i9 = -1;
                i10 = 0;
                i11 = 0;
                i12 = 0;
            }
            if (i11 == i8 && i12 - i9 <= 1) {
                return i12 == i9 ? i10 - i13 : (getDayMaxInMonth(i11, i9) + i10) - i13;
            }
        }
        return -1;
    }

    public static int getDayMaxInMonth(int i8, int i9) {
        switch (i9) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return isLeapYear(i8) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 0;
        }
    }

    public static long getDaysTime(int i8) {
        String timeFormat = getTimeFormat(System.currentTimeMillis(), FORMAT_DATE);
        long j8 = parse(timeFormat, FORMAT_DATE) / 1000;
        a.a(TAG, "--getDaysTime---date = " + timeFormat);
        return new BigDecimal(j8).subtract(new BigDecimal(DAY_SECS).multiply(new BigDecimal(i8))).longValue() * 1000;
    }

    public static int getHourMaxInDay(int i8, int i9, int i10) {
        return 24;
    }

    public static int getMinuteMaxInHour(int i8, int i9, int i10, int i11, int i12) {
        BydDate curDate = getCurDate();
        if (i8 != curDate.getYear() && i9 == curDate.getMonth() && i10 == curDate.getDay() && i11 == curDate.getHour() && i12 == curDate.getMinute()) {
            return curDate.getDay();
        }
        return 1;
    }

    public static int getMonthMaxInYear(int i8) {
        return 12;
    }

    public static int getSecondMaxInMinute(int i8, int i9) {
        BydDate curDate = getCurDate();
        if (i8 == curDate.getYear() || i9 != curDate.getMonth()) {
            return 1;
        }
        return curDate.getDay();
    }

    public static long getTime(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Date date = new SimpleDateFormat(str2).parse(str);
                if (date != null) {
                    return date.getTime();
                }
            } catch (ParseException e8) {
                e8.printStackTrace();
            }
        }
        return 0L;
    }

    public static long getTimeByDate(long j8) {
        return getTime(getDate(j8), FORMAT_DATE);
    }

    public static String getTimeFormat(long j8, String str) {
        if (TextUtils.isEmpty(str)) {
            str = DEF_FORMAT;
        }
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j8));
    }

    public static String getTwoDigits(int i8) {
        if (i8 >= 10) {
            return String.valueOf(i8);
        }
        return "0" + i8;
    }

    public static boolean is24H(Context context) {
        return !"12".equals(Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24));
    }

    public static boolean isAm() {
        return Calendar.getInstance().get(9) == 0;
    }

    public static boolean isLeapYear(int i8) {
        return (i8 % 4 == 0 && i8 % 100 != 0) || i8 % 400 == 0;
    }

    private static boolean overDate(int i8) {
        return i8 > getCurDate().getYear();
    }

    public static long parse(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = DEF_FORMAT;
        }
        try {
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (ParseException e8) {
            e8.printStackTrace();
            return 0L;
        }
    }

    private static boolean overDate(int i8, int i9) {
        if (overDate(i8)) {
            return true;
        }
        BydDate curDate = getCurDate();
        return i8 == curDate.getYear() && i9 > curDate.getMonth();
    }

    public static String getDate(long j8) {
        if (sdfDate == null) {
            sdfDate = new SimpleDateFormat(FORMAT_DATE);
        }
        return sdfDate.format(new Date(j8));
    }

    public static long parse(BydDate bydDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bydDate.getYear());
        stringBuffer.append("-");
        stringBuffer.append(getTwoDigits(bydDate.getMonth()));
        stringBuffer.append("-");
        stringBuffer.append(getTwoDigits(bydDate.getDay()));
        try {
            return simpleDateFormat.parse(stringBuffer.toString()).getTime();
        } catch (ParseException e8) {
            e8.printStackTrace();
            return 0L;
        }
    }

    private static boolean overDate(int i8, int i9, int i10) {
        if (overDate(i8, i9)) {
            return true;
        }
        BydDate curDate = getCurDate();
        return i9 == curDate.getMonth() && i10 > curDate.getDay();
    }

    private static boolean overDate(int i8, int i9, int i10, int i11) {
        if (overDate(i8, i9, i10)) {
            return true;
        }
        BydDate curDate = getCurDate();
        return i10 == curDate.getDay() && i11 > curDate.getHour();
    }

    private static boolean overDate(int i8, int i9, int i10, int i11, int i12) {
        if (overDate(i8, i9, i10, i11)) {
            return true;
        }
        BydDate curDate = getCurDate();
        return i11 == curDate.getHour() && i12 > curDate.getMinute();
    }

    private static boolean overDate(int i8, int i9, int i10, int i11, int i12, int i13) {
        if (overDate(i8, i9, i10, i11, i12)) {
            return true;
        }
        BydDate curDate = getCurDate();
        return i12 == curDate.getMinute() && i13 > curDate.getSecond();
    }
}
