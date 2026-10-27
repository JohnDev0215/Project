package com.amazon.client.metrics.common;

/* JADX INFO: loaded from: classes2.dex */
public enum Channel {
    LOCATION,
    ANONYMOUS,
    NON_ANONYMOUS;

    public static Channel fromInt(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException("Invalid Channel " + i);
        }
        return values()[i];
    }
}
