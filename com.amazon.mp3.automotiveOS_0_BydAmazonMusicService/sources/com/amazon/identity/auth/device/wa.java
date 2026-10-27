package com.amazon.identity.auth.device;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class wa {
    public static long a(long j, TimeUnit timeUnit) {
        return b(j * 24, timeUnit);
    }

    public static long b(long j, TimeUnit timeUnit) {
        return c(j * 60, timeUnit);
    }

    public static long c(long j, TimeUnit timeUnit) {
        return timeUnit.convert(j * 60, TimeUnit.SECONDS);
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        return Long.toString(date.getTime());
    }

    public static Date a(String str) {
        Long lK = e.k(str);
        if (lK == null) {
            return null;
        }
        return new Date(lK.longValue());
    }
}
