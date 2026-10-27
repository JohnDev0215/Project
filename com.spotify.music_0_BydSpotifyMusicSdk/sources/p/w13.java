package p;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class w13 {
    public byte[] a = new byte[4096];
    public int b = 0;
    public int e = -1;
    public long c = -1;
    public boolean h = false;
    public int f = 30;
    public long d = -1;
    public int g = -1;
    public String i = null;

    public final int a(byte[] bArr, int i, int i2) {
        int iC = c(30, i, i2, bArr);
        if (iC == -1) {
            return -1;
        }
        if (this.c == -1) {
            byte[] bArr2 = this.a;
            long jA0 = ((long) ((he1.a0(bArr2, 2) << 16) | he1.a0(bArr2, 0))) & 4294967295L;
            this.c = jA0;
            if (jA0 == 67324752) {
                this.h = false;
                byte[] bArr3 = this.a;
                this.d = ((long) ((he1.a0(bArr3, 20) << 16) | he1.a0(bArr3, 18))) & 4294967295L;
                this.g = he1.a0(this.a, 8);
                this.e = he1.a0(this.a, 26);
                int iA0 = this.e + 30 + he1.a0(this.a, 28);
                this.f = iA0;
                int length = this.a.length;
                if (length < iA0) {
                    do {
                        length += length;
                    } while (length < iA0);
                    this.a = Arrays.copyOf(this.a, length);
                }
            } else {
                this.h = true;
            }
        }
        int iC2 = c(this.f, i + iC, i2 - iC, bArr);
        if (iC2 == -1) {
            return -1;
        }
        int i3 = iC + iC2;
        if (!this.h && this.i == null) {
            this.i = new String(this.a, 30, this.e);
        }
        return i3;
    }

    public final q03 b() {
        int i = this.b;
        int i2 = this.f;
        if (i < i2) {
            return new q03(this.i, this.d, this.g, true, this.h, Arrays.copyOf(this.a, i));
        }
        q03 q03Var = new q03(this.i, this.d, this.g, false, this.h, Arrays.copyOf(this.a, i2));
        this.b = 0;
        this.e = -1;
        this.c = -1L;
        this.h = false;
        this.f = 30;
        this.d = -1L;
        this.g = -1;
        this.i = null;
        return q03Var;
    }

    public final int c(int i, int i2, int i3, byte[] bArr) {
        int i4 = this.b;
        if (i4 >= i) {
            return 0;
        }
        int iMin = Math.min(i3, i - i4);
        System.arraycopy(bArr, i2, this.a, this.b, iMin);
        int i5 = this.b + iMin;
        this.b = i5;
        if (i5 < i) {
            return -1;
        }
        return iMin;
    }
}
