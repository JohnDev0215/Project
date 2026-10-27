package com.google.android.play.core.assetpacks;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Arrays;
import p.q03;
import p.w13;

/* JADX INFO: loaded from: classes.dex */
public final class f extends FilterInputStream {
    public final w13 c;
    public byte[] e;
    public long k;
    public boolean l;
    public boolean m;

    public f(InputStream inputStream) {
        super(inputStream);
        this.c = new w13();
        this.e = new byte[4096];
        this.l = false;
        this.m = false;
    }

    public final q03 b() {
        byte[] bArr;
        if (this.k > 0) {
            do {
                bArr = this.e;
            } while (read(bArr, 0, bArr.length) != -1);
        }
        if (this.l || this.m) {
            return new q03(null, -1L, -1, false, false, null);
        }
        boolean zC = c(30);
        w13 w13Var = this.c;
        if (!zC) {
            this.l = true;
            return w13Var.b();
        }
        q03 q03VarB = w13Var.b();
        if (q03VarB.e) {
            this.m = true;
            return q03VarB;
        }
        if (q03VarB.b == 4294967295L) {
            throw new zzck("Files bigger than 4GiB are not supported.");
        }
        int i = w13Var.f - 30;
        long j = i;
        int length = this.e.length;
        if (j > length) {
            do {
                length += length;
            } while (length < j);
            this.e = Arrays.copyOf(this.e, length);
        }
        if (!c(i)) {
            this.l = true;
            return w13Var.b();
        }
        q03 q03VarB2 = w13Var.b();
        this.k = q03VarB2.b;
        return q03VarB2;
    }

    public final boolean c(int i) {
        int iMax = Math.max(0, super.read(this.e, 0, i));
        w13 w13Var = this.c;
        if (iMax != i) {
            int i2 = i - iMax;
            if (Math.max(0, super.read(this.e, iMax, i2)) != i2) {
                w13Var.a(this.e, 0, iMax);
                return false;
            }
        }
        w13Var.a(this.e, 0, i);
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        long j = this.k;
        if (j <= 0 || this.l) {
            return -1;
        }
        int iMax = Math.max(0, super.read(bArr, i, (int) Math.min(j, i2)));
        this.k -= (long) iMax;
        if (iMax != 0) {
            return iMax;
        }
        this.l = true;
        return 0;
    }
}
