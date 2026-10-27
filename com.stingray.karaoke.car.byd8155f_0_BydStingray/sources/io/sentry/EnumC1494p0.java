package io.sentry;

import java.util.Locale;

/* JADX INFO: renamed from: io.sentry.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1494p0 implements InterfaceC1504q0 {
    NANOSECOND,
    MICROSECOND,
    MILLISECOND,
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    WEEK;

    @Override // io.sentry.InterfaceC1504q0
    public String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
