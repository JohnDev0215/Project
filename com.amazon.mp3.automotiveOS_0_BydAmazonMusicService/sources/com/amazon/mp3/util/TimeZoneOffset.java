package com.amazon.mp3.util;

import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class TimeZoneOffset {
    private final int mOffsetInMillis;

    public TimeZoneOffset() {
        this(new GregorianCalendar().getTimeZone().getOffset(System.currentTimeMillis()));
    }

    TimeZoneOffset(final int offsetInMillis) {
        this.mOffsetInMillis = offsetInMillis;
    }

    public String toString() {
        TimeUnit timeUnit = TimeUnit.HOURS;
        long j = this.mOffsetInMillis;
        TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
        return String.format(Locale.US, "%+03d:%02d", Long.valueOf(timeUnit.convert(j, timeUnit2)), Long.valueOf(Math.abs(TimeUnit.MINUTES.convert(this.mOffsetInMillis, timeUnit2) % 60)));
    }
}
