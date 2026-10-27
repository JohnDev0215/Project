package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: TimeSource.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final k f2727a = new k(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Long f2728b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TimeZone f2729c;

    private k(Long l, TimeZone timeZone) {
        this.f2728b = l;
        this.f2729c = timeZone;
    }

    static k c() {
        return f2727a;
    }

    Calendar a() {
        return b(this.f2729c);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f2728b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
