package com.byd.vrassistant.conversation;

import android.util.Log;
import com.byd.vrassistant.utils.RLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DateHelper {
    private static final String TAG = "DateHelper";

    public String getTime(String str, boolean z) {
        try {
            String str2 = LocalDateTime.parse(str, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(DateTimeFormatter.ofPattern(z ? "HH:mm" : "hh:mm"));
            RLog.d(TAG, "normalDate==" + str2);
            return str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public int getWeekOneDayNumHour(String str) {
        return OffsetDateTime.parse(str, DateTimeFormatter.ISO_OFFSET_DATE_TIME).getDayOfWeek().getValue();
    }

    public int getWeekNum(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd")).getDayOfWeek().getValue();
    }

    public String format(String str, String str2) {
        return format(str, "yyyy-MM-dd", str2);
    }

    public boolean isToday(String str) {
        try {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(LocalDate.now());
        } catch (DateTimeParseException unused) {
            Log.e(TAG, "isToday: 日期格式不正确: " + str);
            return false;
        }
    }

    public String format(String str, String str2, String str3) {
        RLog.d(TAG, "todayTimeFormat origin== " + str);
        try {
            return new SimpleDateFormat(str3, Locale.ENGLISH).format(new SimpleDateFormat(str2, Locale.ENGLISH).parse(str));
        } catch (ParseException e) {
            RLog.e(TAG, "todayTimeFormat: ", e);
            return str;
        }
    }
}
