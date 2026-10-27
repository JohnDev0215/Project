package com.amazon.alexa.voice.core.audio;

/* JADX INFO: loaded from: classes2.dex */
public final class PCML16AudioTransformer {
    public int transform(short[] sArr, int i, int i2, byte[] bArr, int i3) {
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + 1;
            short s = sArr[i + i4];
            bArr[i3] = (byte) (s & 255);
            i3 = i5 + 1;
            bArr[i5] = (byte) ((s >> 8) & 255);
        }
        return i2 * 2;
    }
}
