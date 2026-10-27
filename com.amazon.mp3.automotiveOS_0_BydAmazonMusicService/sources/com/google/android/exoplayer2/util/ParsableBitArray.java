package com.google.android.exoplayer2.util;

/* JADX INFO: loaded from: classes6.dex */
public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.byteLimit = i;
    }

    public void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i;
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public int getBytePosition() {
        Assertions.checkState(this.bitOffset == 0);
        return this.byteOffset;
    }

    public void setPosition(int i) {
        int i2 = i / 8;
        this.byteOffset = i2;
        this.bitOffset = i - (i2 * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int i = this.bitOffset + 1;
        this.bitOffset = i;
        if (i == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
    }

    public void skipBits(int i) {
        int i2 = i / 8;
        int i3 = this.byteOffset + i2;
        this.byteOffset = i3;
        int i4 = this.bitOffset + (i - (i2 * 8));
        this.bitOffset = i4;
        if (i4 > 7) {
            this.byteOffset = i3 + 1;
            this.bitOffset = i4 - 8;
        }
        assertValidOffset();
    }

    public boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public int readBits(int i) {
        int i2;
        if (i == 0) {
            return 0;
        }
        this.bitOffset += i;
        int i3 = 0;
        while (true) {
            i2 = this.bitOffset;
            if (i2 <= 8) {
                break;
            }
            int i4 = i2 - 8;
            this.bitOffset = i4;
            byte[] bArr = this.data;
            int i5 = this.byteOffset;
            this.byteOffset = i5 + 1;
            i3 |= (bArr[i5] & 255) << i4;
        }
        byte[] bArr2 = this.data;
        int i6 = this.byteOffset;
        int i7 = ((-1) >>> (32 - i)) & (i3 | ((bArr2[i6] & 255) >> (8 - i2)));
        if (i2 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i6 + 1;
        }
        assertValidOffset();
        return i7;
    }

    public void byteAlign() {
        if (this.bitOffset == 0) {
            return;
        }
        this.bitOffset = 0;
        this.byteOffset++;
        assertValidOffset();
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        Assertions.checkState(this.bitOffset == 0);
        System.arraycopy(this.data, this.byteOffset, bArr, i, i2);
        this.byteOffset += i2;
        assertValidOffset();
    }

    public void skipBytes(int i) {
        Assertions.checkState(this.bitOffset == 0);
        this.byteOffset += i;
        assertValidOffset();
    }

    private void assertValidOffset() {
        int i;
        int i2 = this.byteOffset;
        Assertions.checkState(i2 >= 0 && (i2 < (i = this.byteLimit) || (i2 == i && this.bitOffset == 0)));
    }
}
