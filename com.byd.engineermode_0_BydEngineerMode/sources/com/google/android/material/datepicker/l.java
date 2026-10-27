package com.google.android.material.datepicker;

import android.annotation.TargetApi;
import android.icu.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: UtcDates.java */
/* JADX INFO: loaded from: classes.dex */
class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static AtomicReference<k> f2730a = new AtomicReference<>();

    static long a(long j) {
        Calendar calendarL = l();
        calendarL.setTimeInMillis(j);
        return d(calendarL).getTimeInMillis();
    }

    @TargetApi(24)
    static DateFormat b(Locale locale) {
        return c("MMMEd", locale);
    }

    @TargetApi(24)
    private static DateFormat c(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(k());
        return instanceForSkeleton;
    }

    static Calendar d(Calendar calendar) {
        Calendar calendarM = m(calendar);
        Calendar calendarL = l();
        calendarL.set(calendarM.get(1), calendarM.get(2), calendarM.get(5));
        return calendarL;
    }

    private static java.text.DateFormat e(int i, Locale locale) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i, locale);
        dateInstance.setTimeZone(i());
        return dateInstance;
    }

    static java.text.DateFormat f(Locale locale) {
        return e(0, locale);
    }

    private static SimpleDateFormat g(String str, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(i());
        return simpleDateFormat;
    }

    static k h() {
        k kVar = f2730a.get();
        return kVar == null ? k.c() : kVar;
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
        return c("yMMMEd", locale);
    }

    static SimpleDateFormat o() {
        return p(Locale.getDefault());
    }

    private static SimpleDateFormat p(Locale locale) {
        return g("LLLL, yyyy", locale);
    }
}
