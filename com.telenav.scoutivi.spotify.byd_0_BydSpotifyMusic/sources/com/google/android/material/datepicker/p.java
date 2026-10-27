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
    static AtomicReference<o> f4498a = new AtomicReference<>();

    static long a(long j7) {
        Calendar calendarL = l();
        calendarL.setTimeInMillis(j7);
        return e(calendarL).getTimeInMillis();
    }

    @TargetApi(24)
    static DateFormat b(Locale locale) {
        return d("MMMd", locale);
    }

    @TargetApi(24)
    static DateFormat c(Locale locale) {
        return d("MMMEd", locale);
    }

    @TargetApi(24)
    private static DateFormat d(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(k());
        return instanceForSkeleton;
    }

    static Calendar e(Calendar calendar) {
        Calendar calendarM = m(calendar);
        Calendar calendarL = l();
        calendarL.set(calendarM.get(1), calendarM.get(2), calendarM.get(5));
        return calendarL;
    }

    static SimpleDateFormat f() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toLocalizedPattern().replaceAll("\\s+", ""), Locale.getDefault());
        simpleDateFormat.setTimeZone(i());
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    static String g(Resources resources, SimpleDateFormat simpleDateFormat) {
        String localizedPattern = simpleDateFormat.toLocalizedPattern();
        return localizedPattern.replaceAll("d", resources.getString(o2.j.mtrl_picker_text_input_day_abbr)).replaceAll("M", resources.getString(o2.j.mtrl_picker_text_input_month_abbr)).replaceAll("y", resources.getString(o2.j.mtrl_picker_text_input_year_abbr));
    }

    static o h() {
        o oVar = f4498a.get();
        return oVar == null ? o.c() : oVar;
    }

    private static TimeZone i() {
        return TimeZone.getTimeZone("UTC");
    }

    static Calendar j() {
        Calendar calendarA = h().a();
        calendarA.set(11, 0);
        calendarA.set(12, 0);
        calendarA.set(13, 0);
        calendarA.set(14, 0);
        calendarA.setTimeZone(i());
        return calendarA;
    }

    @TargetApi(24)
    private static android.icu.util.TimeZone k() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    static Calendar l() {
        return m(null);
    }

    static Calendar m(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(i());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    @TargetApi(24)
    static DateFormat n(Locale locale) {
        return d("yMMMd", locale);
    }

    @TargetApi(24)
    static DateFormat o(Locale locale) {
        return d("yMMMEd", locale);
    }
}
