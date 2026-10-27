package org.greenrobot.essentials.hash;

import java.util.zip.Checksum;

/* JADX INFO: loaded from: classes5.dex */
public class FNV32 implements Checksum {
    private static final int INITIAL_VALUE = -2128831035;
    private static final int MULTIPLIER = 16777619;
    private int hash = INITIAL_VALUE;

    @Override // java.util.zip.Checksum
    public void update(int i) {
        int i2 = (i & 255) ^ this.hash;
        this.hash = i2;
        this.hash = i2 * MULTIPLIER;
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            int i4 = this.hash ^ (bArr[i] & 255);
            this.hash = i4;
            this.hash = i4 * MULTIPLIER;
            i++;
        }
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return ((long) this.hash) & 4294967295L;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.hash = INITIAL_VALUE;
    }
}
