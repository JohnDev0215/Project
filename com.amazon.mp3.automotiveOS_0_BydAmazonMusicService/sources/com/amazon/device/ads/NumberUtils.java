package com.amazon.device.ads;

/* JADX INFO: loaded from: classes2.dex */
class NumberUtils {
    public static final long convertToMillisecondsFromSeconds(long j) {
        return j * 1000;
    }

    private NumberUtils() {
    }

    public static final long convertToMillisecondsFromNanoseconds(long j) {
        return j / 1000000;
    }
}
