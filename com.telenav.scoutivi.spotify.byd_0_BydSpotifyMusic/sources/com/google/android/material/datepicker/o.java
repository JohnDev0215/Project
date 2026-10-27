package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final o f4495c = new o(null, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Long f4496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TimeZone f4497b;

    private o(Long l7, TimeZone timeZone) {
        this.f4496a = l7;
        this.f4497b = timeZone;
    }

    static o c() {
        return f4495c;
    }

    Calendar a() {
        return b(this.f4497b);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l7 = this.f4496a;
        if (l7 != null) {
            calendar.setTimeInMillis(l7.longValue());
        }
        return calendar;
    }
}
