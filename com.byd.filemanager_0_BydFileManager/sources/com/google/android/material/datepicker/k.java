package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f4208a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Long f4209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TimeZone f4210c;

    private k(Long l, TimeZone timeZone) {
        this.f4209b = l;
        this.f4210c = timeZone;
    }

    static k c() {
        return f4208a;
    }

    Calendar a() {
        return b(this.f4210c);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f4209b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
