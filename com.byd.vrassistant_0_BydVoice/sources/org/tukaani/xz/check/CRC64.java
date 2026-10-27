package org.tukaani.xz.check;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes4.dex */
public class CRC64 extends Check {
    private static final long[][] TABLE = (long[][]) Array.newInstance((Class<?>) Long.TYPE, 4, 256);
    private long crc = -1;

    static {
        int i = 0;
        while (i < 4) {
            for (int i2 = 0; i2 < 256; i2++) {
                long j = i == 0 ? i2 : TABLE[i - 1][i2];
                for (int i3 = 0; i3 < 8; i3++) {
                    long j2 = j & 1;
                    j >>>= 1;
                    if (j2 == 1) {
                        j ^= -3932672073523589310L;
                    }
                }
                TABLE[i][i2] = j;
            }
            i++;
        }
    }

    public CRC64() {
        this.size = 8;
        this.name = "CRC64";
    }

    @Override // org.tukaani.xz.check.Check
    public byte[] finish() {
        long j = ~this.crc;
        this.crc = -1L;
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (j >> (i * 8));
        }
        return bArr;
    }

    @Override // org.tukaani.xz.check.Check
    public void update(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        int i4 = i3 - 3;
        while (i < i4) {
            long j = this.crc;
            int i5 = (int) j;
            long[][] jArr = TABLE;
            this.crc = (((j >>> 32) ^ (jArr[3][(i5 & 255) ^ (bArr[i] & 255)] ^ jArr[2][((i5 >>> 8) & 255) ^ (bArr[i + 1] & 255)])) ^ jArr[1][((i5 >>> 16) & 255) ^ (bArr[i + 2] & 255)]) ^ jArr[0][((i5 >>> 24) & 255) ^ (bArr[i + 3] & 255)];
            i += 4;
        }
        while (i < i3) {
            long[] jArr2 = TABLE[0];
            int i6 = i + 1;
            int i7 = bArr[i] & 255;
            long j2 = this.crc;
            this.crc = (j2 >>> 8) ^ jArr2[i7 ^ (((int) j2) & 255)];
            i = i6;
        }
    }
}
