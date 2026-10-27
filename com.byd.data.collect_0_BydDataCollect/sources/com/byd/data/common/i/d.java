package com.byd.data.common.i;

/* JADX INFO: compiled from: HexUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static byte a(byte[] bArr, int i2) {
        byte b2 = bArr[2];
        for (int i3 = 3; i3 < i2; i3++) {
            b2 = (byte) (b2 ^ bArr[i3]);
        }
        return b2;
    }
}
