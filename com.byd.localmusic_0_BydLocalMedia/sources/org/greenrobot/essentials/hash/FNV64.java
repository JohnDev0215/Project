package org.greenrobot.essentials.hash;

import java.util.zip.Checksum;

/* JADX INFO: loaded from: classes5.dex */
public class FNV64 implements Checksum {
    private static final long INITIAL_VALUE = -3750763034362895579L;
    private static final long MULTIPLIER = 1099511628211L;
    private long hash = INITIAL_VALUE;

    @Override // java.util.zip.Checksum
    public void update(int i) {
        long j = this.hash ^ (((long) i) & 255);
        this.hash = j;
        this.hash = j * MULTIPLIER;
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            long j = this.hash ^ (255 & ((long) bArr[i]));
            this.hash = j;
            this.hash = j * MULTIPLIER;
            i++;
        }
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        return this.hash;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.hash = INITIAL_VALUE;
    }
}
