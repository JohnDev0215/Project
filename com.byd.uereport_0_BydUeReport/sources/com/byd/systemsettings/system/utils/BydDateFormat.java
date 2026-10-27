package com.byd.systemsettings.system.utils;

import android.content.Context;
import android.icu.util.Calendar;
import com.byd.systemsettings.system.BydSystemSettings;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public final class BydDateFormat {
    public static final String DEF_FORMAT = "yyyy/MM/dd EEEE";
    public static final String FORMAT_DE = "EEEE d MMMM yyyy";
    public static final String FORMAT_DE_FULL = "EEEE, d MMMM, yyyy";
    public static final String FORMAT_EN = "EEE., MMM.d, yyyy";
    public static final String FORMAT_EN_FULL = "EEEE, MMMM d, yyyy";
    public static final String FORMAT_EN_MONTH_5 = "EEE., MMM d, yyyy";
    public static final String FORMAT_ES = "EEEE d MMMM yyyy";
    public static final String FORMAT_MULT = "yyyy-MM-dd EEEE";
    public static final String FORMAT_PT = "EEEE d MMMM yyyy";
    private static final String TAG = "BydDateFormat";
    private static final String TIME_FORMAT_DE_12 = "EEE, dd MMM, hh:mm:ss a yyyy";
    private static final String TIME_FORMAT_DE_24 = "EEE, dd MMM, HH:mm:ss yyyy";
    private static final String TIME_FORMAT_EN_12 = "EEE., MMM. dd, hh:mm:ss a yyyy";
    private static final String TIME_FORMAT_EN_12_MONTH_5 = "EEE., MMM dd, hh:mm:ss a yyyy";
    private static final String TIME_FORMAT_EN_24 = "EEE., MMM. dd, HH:mm:ss yyyy";
    private static final String TIME_FORMAT_EN_24_MONTH_5 = "EEE., MMM dd, HH:mm:ss yyyy";
    private static final String TIME_FORMAT_ZH_12 = "yyyy年MM月dd日 a hh:mm:ss";
    private static final String TIME_FORMAT_ZH_24 = "yyyy年MM月dd日 HH:mm:ss";
    public static volatile Locale curLocale;

    private static String getDateFormat(Locale locale) {
        return FORMAT_MULT;
    }

    private static String getDateFormatFull(Locale locale) {
        return locale.equals(Locale.CHINA) ? DEF_FORMAT : (!isEnglish(locale) && locale.equals(Locale.GERMANY)) ? FORMAT_DE_FULL : FORMAT_EN_FULL;
    }

    public static String getFormatDate() {
        Locale locale = curLocale;
        String dateFormat = getDateFormat(locale);
        if (isEnglish(locale) && Calendar.getInstance().get(2) + 1 == 5) {
            a.a(TAG, "---getFormatDate---EN_MONTH_5.");
            dateFormat = FORMAT_EN_MONTH_5;
        }
        return new SimpleDateFormat(dateFormat, locale).format(new Date(System.currentTimeMillis()));
    }

    public static String getFormatTime(Context context) {
        Locale locale = curLocale;
        String timeFormat = getTimeFormat(context, locale);
        if (isEnglish(locale) && Calendar.getInstance().get(2) + 1 == 5) {
            String str = BydSystemSettings.Singleton.get().getTimeSystem(context) == 0 ? TIME_FORMAT_EN_24_MONTH_5 : TIME_FORMAT_EN_12_MONTH_5;
            a.a(TAG, "---getFormatTime--MONTH_5--format: " + str);
            timeFormat = str;
        }
        return new SimpleDateFormat(timeFormat, locale).format(new Date(System.currentTimeMillis()));
    }

    private static String getTimeFormat(Context context, Locale locale) {
        boolean z7 = BydSystemSettings.Singleton.get().getTimeSystem(context) == 0;
        return locale.equals(Locale.CHINA) ? z7 ? TIME_FORMAT_ZH_24 : TIME_FORMAT_ZH_12 : isEnglish(locale) ? z7 ? TIME_FORMAT_EN_24 : TIME_FORMAT_EN_12 : locale.equals(Locale.GERMANY) ? z7 ? TIME_FORMAT_DE_24 : TIME_FORMAT_DE_12 : z7 ? TIME_FORMAT_EN_24 : TIME_FORMAT_EN_12;
    }

    public static void init(Context context) {
        a.a(TAG, "---init---");
        onLanguageChanged(context);
    }

    public static boolean isEnglish(Locale locale) {
        if (locale == null) {
            return false;
        }
        return locale.equals(Locale.US) || locale.equals(Locale.ENGLISH) || locale.equals(Locale.UK);
    }

    public static void onLanguageChanged(Context context) {
        if (context == null) {
            return;
        }
        curLocale = context.getResources().getConfiguration().getLocales().get(0);
        a.a(TAG, "---onLanguageChanged---curLocale: " + curLocale.toString());
    }
}
