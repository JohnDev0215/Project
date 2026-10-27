package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final o f4002a = new o(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Long f4003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TimeZone f4004c;

    private o(Long l, TimeZone timeZone) {
        this.f4003b = l;
        this.f4004c = timeZone;
    }

    static o c() {
        return f4002a;
    }

    Calendar a() {
        return b(this.f4004c);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f4003b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
