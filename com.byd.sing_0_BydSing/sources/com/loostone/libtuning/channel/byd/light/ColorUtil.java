package com.loostone.libtuning.channel.byd.light;

/* JADX INFO: loaded from: classes.dex */
public class ColorUtil {
    public static int getColor(int[] iArr) {
        return iArr[2] | (iArr[0] << 16) | (-16777216) | (iArr[1] << 8);
    }

    public static int getColor(byte[] bArr) {
        return bArr[2] | (bArr[0] << 16) | (-16777216) | (bArr[1] << 8);
    }
}
