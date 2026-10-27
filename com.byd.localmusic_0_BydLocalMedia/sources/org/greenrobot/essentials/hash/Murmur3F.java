package org.greenrobot.essentials.hash;

import java.math.BigInteger;
import org.greenrobot.essentials.PrimitiveArrayUtils;

/* JADX INFO: loaded from: classes5.dex */
public class Murmur3F implements Checksum128 {
    private static final long C1 = -8663945395140668459L;
    private static final long C2 = 5545529020109919103L;
    private static PrimitiveArrayUtils primitiveArrayUtils = PrimitiveArrayUtils.getInstance();
    private boolean finished;
    private long finishedH1;
    private long finishedH2;
    private long h1;
    private long h2;
    private int length;
    private long partialK1;
    private long partialK2;
    private int partialPos;
    private final long seed;

    private long fmix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }

    public Murmur3F() {
        this.seed = 0L;
    }

    public Murmur3F(int i) {
        long j = ((long) i) & 4294967295L;
        this.seed = j;
        this.h2 = j;
        this.h1 = j;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        this.finished = false;
        switch (this.partialPos) {
            case 0:
                this.partialK1 = i & 255;
                break;
            case 1:
                this.partialK1 |= (long) ((i & 255) << 8);
                break;
            case 2:
                this.partialK1 |= (long) ((i & 255) << 16);
                break;
            case 3:
                this.partialK1 |= (((long) i) & 255) << 24;
                break;
            case 4:
                this.partialK1 |= (((long) i) & 255) << 32;
                break;
            case 5:
                this.partialK1 |= (((long) i) & 255) << 40;
                break;
            case 6:
                this.partialK1 |= (((long) i) & 255) << 48;
                break;
            case 7:
                this.partialK1 = ((((long) i) & 255) << 56) | this.partialK1;
                break;
            case 8:
                this.partialK2 = i & 255;
                break;
            case 9:
                this.partialK2 |= (long) ((i & 255) << 8);
                break;
            case 10:
                this.partialK2 |= (long) ((i & 255) << 16);
                break;
            case 11:
                this.partialK2 |= (((long) i) & 255) << 24;
                break;
            case 12:
                this.partialK2 |= (((long) i) & 255) << 32;
                break;
            case 13:
                this.partialK2 |= (((long) i) & 255) << 40;
                break;
            case 14:
                this.partialK2 |= (((long) i) & 255) << 48;
                break;
            case 15:
                this.partialK2 = ((((long) i) & 255) << 56) | this.partialK2;
                break;
        }
        int i2 = this.partialPos + 1;
        this.partialPos = i2;
        if (i2 == 16) {
            applyKs(this.partialK1, this.partialK2);
            this.partialPos = 0;
        }
        this.length++;
    }

    public void updateLongLE(long j) {
        this.finished = false;
        int i = this.partialPos;
        if (i == 0) {
            this.partialK1 = j;
        } else if (i == 8) {
            this.partialK2 = j;
        } else {
            throw new IllegalStateException("Cannot mix long with other alignments than 8: " + this.partialPos);
        }
        int i2 = this.partialPos + 8;
        this.partialPos = i2;
        if (i2 == 16) {
            applyKs(this.partialK1, this.partialK2);
            this.partialPos = 0;
        }
        this.length += 8;
    }

    public void updateLongBE(long j) {
        updateLongLE(Long.reverseBytes(j));
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        this.finished = false;
        while (this.partialPos != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        int i3 = i2 & 15;
        int i4 = (i2 + i) - i3;
        for (int i5 = i; i5 < i4; i5 += 16) {
            applyKs(primitiveArrayUtils.getLongLE(bArr, i5), primitiveArrayUtils.getLongLE(bArr, i5 + 8));
        }
        this.length += i4 - i;
        for (int i6 = 0; i6 < i3; i6++) {
            update(bArr[i4 + i6]);
        }
    }

    private void applyKs(long j, long j2) {
        long jRotateLeft = (Long.rotateLeft(j * C1, 31) * C2) ^ this.h1;
        this.h1 = jRotateLeft;
        long jRotateLeft2 = Long.rotateLeft(jRotateLeft, 27);
        this.h1 = jRotateLeft2;
        long j3 = jRotateLeft2 + this.h2;
        this.h1 = j3;
        this.h1 = (j3 * 5) + 1390208809;
        long jRotateLeft3 = (Long.rotateLeft(j2 * C2, 33) * C1) ^ this.h2;
        this.h2 = jRotateLeft3;
        long jRotateLeft4 = Long.rotateLeft(jRotateLeft3, 31);
        this.h2 = jRotateLeft4;
        long j4 = jRotateLeft4 + this.h1;
        this.h2 = j4;
        this.h2 = (j4 * 5) + 944331445;
    }

    private void checkFinished() {
        if (this.finished) {
            return;
        }
        this.finished = true;
        this.finishedH1 = this.h1;
        this.finishedH2 = this.h2;
        int i = this.partialPos;
        if (i > 0) {
            if (i > 8) {
                this.finishedH2 = (Long.rotateLeft(this.partialK2 * C2, 33) * C1) ^ this.finishedH2;
            }
            this.finishedH1 = (Long.rotateLeft(this.partialK1 * C1, 31) * C2) ^ this.finishedH1;
        }
        long j = this.finishedH1;
        int i2 = this.length;
        long j2 = j ^ ((long) i2);
        this.finishedH1 = j2;
        long j3 = this.finishedH2 ^ ((long) i2);
        this.finishedH2 = j3;
        long j4 = j2 + j3;
        this.finishedH1 = j4;
        this.finishedH2 = j3 + j4;
        this.finishedH1 = fmix64(j4);
        long jFmix64 = fmix64(this.finishedH2);
        this.finishedH2 = jFmix64;
        long j5 = this.finishedH1 + jFmix64;
        this.finishedH1 = j5;
        this.finishedH2 = jFmix64 + j5;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        checkFinished();
        return this.finishedH1;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public long getValueHigh() {
        checkFinished();
        return this.finishedH2;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public BigInteger getValueBigInteger() {
        return new BigInteger(1, getValueBytesBigEndian());
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public String getValueHexString() {
        checkFinished();
        return getPaddedHexString(this.finishedH2) + getPaddedHexString(this.finishedH1);
    }

    private String getPaddedHexString(long j) {
        String hexString = Long.toHexString(j);
        while (hexString.length() < 16) {
            hexString = '0' + hexString;
        }
        return hexString;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public byte[] getValueBytesBigEndian() {
        checkFinished();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (255 & (this.finishedH2 >>> (56 - (i * 8))));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + 8] = (byte) ((this.finishedH1 >>> (56 - (i2 * 8))) & 255);
        }
        return bArr;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public byte[] getValueBytesLittleEndian() {
        checkFinished();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (255 & (this.finishedH1 >>> (i * 8)));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + 8] = (byte) ((this.finishedH2 >>> (i2 * 8)) & 255);
        }
        return bArr;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        long j = this.seed;
        this.h2 = j;
        this.h1 = j;
        this.length = 0;
        this.partialPos = 0;
        this.finished = false;
        this.partialK2 = 0L;
        this.partialK1 = 0L;
        this.finishedH2 = 0L;
        this.finishedH1 = 0L;
    }
}
