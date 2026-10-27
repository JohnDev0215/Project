package com.amazon.client.metrics.common;

/* JADX INFO: loaded from: classes2.dex */
public enum Priority {
    NORMAL,
    HIGH,
    RESERVED_FOR_LOCATION_SERVICE,
    RESERVED_FOR_NON_ANONYMOUS_METRICS,
    CRITICAL;

    public static Priority fromInt(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException("invalid priority " + i);
        }
        return values()[i];
    }
}
