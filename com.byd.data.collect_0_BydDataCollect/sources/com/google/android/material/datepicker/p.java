package com.google.android.material.datepicker;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.icu.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: UtcDates.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static AtomicReference<o> f4005a = new AtomicReference<>();

    static long a(long j) {
        Calendar calendarR = r();
        calendarR.setTimeInMillis(j);
        return f(calendarR).getTimeInMillis();
    }

    private static int b(String str, String str2, int i2, int i3) {
        while (i3 >= 0 && i3 < str.length() && str2.indexOf(str.charAt(i3)) == -1) {
            if (str.charAt(i3) == '\'') {
                do {
                    i3 += i2;
                    if (i3 < 0 || i3 >= str.length()) {
                        break;
                    }
                } while (str.charAt(i3) != '\'');
            }
            i3 += i2;
        }
        return i3;
    }

    @TargetApi(24)
    static DateFormat c(Locale locale) {
        return e("MMMd", locale);
    }

    @TargetApi(24)
    static DateFormat d(Locale locale) {
        return e("MMMEd", locale);
    }

    @TargetApi(24)
    private static DateFormat e(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(q());
        return instanceForSkeleton;
    }

    static Calendar f(Calendar calendar) {
        Calendar calendarS = s(calendar);
        Calendar calendarR = r();
        calendarR.set(calendarS.get(1), calendarS.get(2), calendarS.get(5));
        return calendarR;
    }

    private static java.text.DateFormat g(int i2, Locale locale) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i2, locale);
        dateInstance.setTimeZone(o());
        return dateInstance;
    }

    static java.text.DateFormat h(Locale locale) {
        return g(0, locale);
    }

    static java.text.DateFormat i(Locale locale) {
        return g(2, locale);
    }

    static java.text.DateFormat j(Locale locale) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) i(locale);
        simpleDateFormat.applyPattern(x(simpleDateFormat.toPattern()));
        return simpleDateFormat;
    }

    private static SimpleDateFormat k(String str, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(o());
        return simpleDateFormat;
    }

    static SimpleDateFormat l() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toLocalizedPattern().replaceAll("\\s+", ""), Locale.getDefault());
        simpleDateFormat.setTimeZone(o());
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    static String m(Resources resources, SimpleDateFormat simpleDateFormat) {
        String localizedPattern = simpleDateFormat.toLocalizedPattern();
        return localizedPattern.replaceAll("d", resources.getString(d.b.a.a.j.mtrl_picker_text_input_day_abbr)).replaceAll("M", resources.getString(d.b.a.a.j.mtrl_picker_text_input_month_abbr)).replaceAll("y", resources.getString(d.b.a.a.j.mtrl_picker_text_input_year_abbr));
    }

    static o n() {
        o oVar = f4005a.get();
        return oVar == null ? o.c() : oVar;
    }

    private static TimeZone o() {
        return TimeZone.getTimeZone("UTC");
    }

    static Calendar p() {
        Calendar calendarA = n().a();
        calendarA.set(11, 0);
        calendarA.set(12, 0);
        calendarA.set(13, 0);
        calendarA.set(14, 0);
        calendarA.setTimeZone(o());
        return calendarA;
    }

    @TargetApi(24)
    private static android.icu.util.TimeZone q() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    static Calendar r() {
        return s(null);
    }

    static Calendar s(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(o());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    @TargetApi(24)
    static DateFormat t(Locale locale) {
        return e("yMMMd", locale);
    }

    @TargetApi(24)
    static DateFormat u(Locale locale) {
        return e("yMMMEd", locale);
    }

    static SimpleDateFormat v() {
        return w(Locale.getDefault());
    }

    private static SimpleDateFormat w(Locale locale) {
        return k("LLLL, yyyy", locale);
    }

    private static String x(String str) {
        int iB = b(str, "yY", 1, 0);
        if (iB >= str.length()) {
            return str;
        }
        String str2 = "EMd";
        int iB2 = b(str, "EMd", 1, iB);
        if (iB2 < str.length()) {
            str2 = "EMd,";
        }
        return str.replace(str.substring(b(str, str2, -1, iB) + 1, iB2), " ").trim();
    }
}
