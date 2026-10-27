package com.amazon.client.metrics.thirdparty;

/* JADX INFO: loaded from: classes2.dex */
public enum Channel {
    LOCATION,
    ANONYMOUS,
    NON_ANONYMOUS;

    public static Channel fromInt(int i) {
        if (i < 0 || i > Priority.values().length) {
            throw new IllegalArgumentException("invalid priority " + i);
        }
        return values()[i];
    }
}
