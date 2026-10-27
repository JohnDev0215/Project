package com.micphone.utils;

/* JADX INFO: loaded from: classes.dex */
public class utils {
    public static int byte2Int(byte[] bArr, int i, int i2) {
        return ((bArr[i] & 255) << 0) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }
}
