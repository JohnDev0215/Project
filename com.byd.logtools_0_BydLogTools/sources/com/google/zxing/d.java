package com.google.zxing;

/* JADX INFO: compiled from: LuminanceSource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4248b;

    protected d(int i, int i2) {
        this.f4247a = i;
        this.f4248b = i2;
    }

    public final int a() {
        return this.f4248b;
    }

    public abstract byte[] b();

    public abstract byte[] c(int i, byte[] bArr);

    public final int d() {
        return this.f4247a;
    }

    public d e() {
        return new c(this);
    }

    public boolean f() {
        return false;
    }

    public d g() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public final String toString() {
        int i = this.f4247a;
        byte[] bArrC = new byte[i];
        StringBuilder sb = new StringBuilder(this.f4248b * (i + 1));
        for (int i2 = 0; i2 < this.f4248b; i2++) {
            bArrC = c(i2, bArrC);
            for (int i3 = 0; i3 < this.f4247a; i3++) {
                int i4 = bArrC[i3] & 255;
                sb.append(i4 < 64 ? '#' : i4 < 128 ? '+' : i4 < 192 ? '.' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
