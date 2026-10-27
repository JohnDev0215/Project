package com.google.zxing.common;

/* JADX INFO: compiled from: BitSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f4199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4201c;

    public c(byte[] bArr) {
        this.f4199a = bArr;
    }

    public int a() {
        return ((this.f4199a.length - this.f4200b) * 8) - this.f4201c;
    }

    public int b() {
        return this.f4201c;
    }

    public int c() {
        return this.f4200b;
    }

    public int d(int i) {
        if (i <= 0 || i > 32 || i > a()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i2 = this.f4201c;
        int i3 = 0;
        if (i2 > 0) {
            int i4 = 8 - i2;
            int i5 = i < i4 ? i : i4;
            int i6 = i4 - i5;
            byte[] bArr = this.f4199a;
            int i7 = this.f4200b;
            int i8 = (((255 >> (8 - i5)) << i6) & bArr[i7]) >> i6;
            i -= i5;
            int i9 = i2 + i5;
            this.f4201c = i9;
            if (i9 == 8) {
                this.f4201c = 0;
                this.f4200b = i7 + 1;
            }
            i3 = i8;
        }
        if (i <= 0) {
            return i3;
        }
        while (i >= 8) {
            int i10 = i3 << 8;
            byte[] bArr2 = this.f4199a;
            int i11 = this.f4200b;
            i3 = (bArr2[i11] & 255) | i10;
            this.f4200b = i11 + 1;
            i -= 8;
        }
        if (i <= 0) {
            return i3;
        }
        int i12 = 8 - i;
        int i13 = (i3 << i) | ((((255 >> i12) << i12) & this.f4199a[this.f4200b]) >> i12);
        this.f4201c += i;
        return i13;
    }
}
