package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f2343a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Long f2344b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TimeZone f2345c;

    private k(Long l, TimeZone timeZone) {
        this.f2344b = l;
        this.f2345c = timeZone;
    }

    static k c() {
        return f2343a;
    }

    Calendar a() {
        return b(this.f2345c);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f2344b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
