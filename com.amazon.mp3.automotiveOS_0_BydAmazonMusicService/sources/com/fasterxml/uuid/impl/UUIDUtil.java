package com.fasterxml.uuid.impl;

/* JADX INFO: loaded from: classes6.dex */
public class UUIDUtil {
    public static long initUUIDSecondLong(long j) {
        return ((j << 2) >>> 2) | Long.MIN_VALUE;
    }

    protected static final long gatherLong(byte[] bArr, int i) {
        return ((((long) _gatherInt(bArr, i + 4)) << 32) >>> 32) | (((long) _gatherInt(bArr, i)) << 32);
    }

    private static final int _gatherInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | (bArr[i] << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }
}
