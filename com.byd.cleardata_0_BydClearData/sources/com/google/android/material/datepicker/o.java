package com.google.android.material.datepicker;

import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final o f3078a = new o(null, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Long f3079b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TimeZone f3080c;

    private o(Long l, TimeZone timeZone) {
        this.f3079b = l;
        this.f3080c = timeZone;
    }

    static o c() {
        return f3078a;
    }

    Calendar a() {
        return b(this.f3080c);
    }

    Calendar b(TimeZone timeZone) {
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = this.f3079b;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return calendar;
    }
}
