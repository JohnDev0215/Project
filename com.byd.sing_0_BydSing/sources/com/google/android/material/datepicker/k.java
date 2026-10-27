package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f3536a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Long f3537b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TimeZone f3538c;

    private k(Long l, TimeZone timeZone) {
        this.f3537b = l;
        this.f3538c = timeZone;
    }

    static k c() {
        return f3536a;
    }

    Calendar a() {
        return b(this.f3538c);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f3537b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
