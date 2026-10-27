package com.bumptech.glide.load.data;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class j extends FilterInputStream {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final byte[] f12726n = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
    public static final int o = 31;
    public final byte l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f12727m;

    public j(InputStream inputStream, int i7) {
        super(inputStream);
        if (i7 < -1 || i7 > 8) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Cannot add invalid orientation: "));
        }
        this.l = (byte) i7;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void mark(int i7) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() {
        int i7;
        int i10 = this.f12727m;
        int i11 = (i10 < 2 || i10 > (i7 = o)) ? super.read() : i10 == i7 ? this.l : f12726n[i10 - 2] & 255;
        if (i11 != -1) {
            this.f12727m++;
        }
        return i11;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j3) throws IOException {
        long jSkip = super.skip(j3);
        if (jSkip > 0) {
            this.f12727m = (int) (((long) this.f12727m) + jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) throws IOException {
        int i11;
        int i12 = this.f12727m;
        int i13 = o;
        if (i12 > i13) {
            i11 = super.read(bArr, i7, i10);
        } else if (i12 == i13) {
            bArr[i7] = this.l;
            i11 = 1;
        } else if (i12 < 2) {
            i11 = super.read(bArr, i7, 2 - i12);
        } else {
            int iMin = Math.min(i13 - i12, i10);
            System.arraycopy(f12726n, this.f12727m - 2, bArr, i7, iMin);
            i11 = iMin;
        }
        if (i11 > 0) {
            this.f12727m += i11;
        }
        return i11;
    }
}
