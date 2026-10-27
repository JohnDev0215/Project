package org.chromium.url;

import J.N;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class Parsed {
    public final int a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public final int n;
    public final int o;
    public final int p;
    public final Parsed q;
    public final boolean r;

    @CalledByNative
    public Parsed(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, boolean z, Parsed parsed) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
        this.i = i9;
        this.j = i10;
        this.k = i11;
        this.l = i12;
        this.m = i13;
        this.n = i14;
        this.o = i15;
        this.p = i16;
        this.r = z;
        this.q = parsed;
    }

    public static Parsed a(String[] strArr, int i) {
        int i2 = i + 1;
        int i3 = Integer.parseInt(strArr[i]);
        int i4 = i2 + 1;
        int i5 = Integer.parseInt(strArr[i2]);
        int i6 = i4 + 1;
        int i7 = Integer.parseInt(strArr[i4]);
        int i8 = i6 + 1;
        int i9 = Integer.parseInt(strArr[i6]);
        int i10 = i8 + 1;
        int i11 = Integer.parseInt(strArr[i8]);
        int i12 = i10 + 1;
        int i13 = Integer.parseInt(strArr[i10]);
        int i14 = i12 + 1;
        int i15 = Integer.parseInt(strArr[i12]);
        int i16 = i14 + 1;
        int i17 = Integer.parseInt(strArr[i14]);
        int i18 = i16 + 1;
        int i19 = Integer.parseInt(strArr[i16]);
        int i20 = i18 + 1;
        int i21 = Integer.parseInt(strArr[i18]);
        int i22 = i20 + 1;
        int i23 = Integer.parseInt(strArr[i20]);
        int i24 = i22 + 1;
        int i25 = Integer.parseInt(strArr[i22]);
        int i26 = i24 + 1;
        int i27 = Integer.parseInt(strArr[i24]);
        int i28 = i26 + 1;
        int i29 = Integer.parseInt(strArr[i26]);
        int i30 = i28 + 1;
        int i31 = Integer.parseInt(strArr[i28]);
        int i32 = i30 + 1;
        int i33 = Integer.parseInt(strArr[i30]);
        int i34 = i32 + 1;
        return new Parsed(i3, i5, i7, i9, i11, i13, i15, i17, i19, i21, i23, i25, i27, i29, i31, i33, Boolean.parseBoolean(strArr[i32]), Boolean.parseBoolean(strArr[i34]) ? a(strArr, i34 + 1) : null);
    }

    public final String b() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append((char) 0);
        sb.append(this.b);
        sb.append((char) 0);
        sb.append(this.c);
        sb.append((char) 0);
        sb.append(this.d);
        sb.append((char) 0);
        sb.append(this.e);
        sb.append((char) 0);
        sb.append(this.f);
        sb.append((char) 0);
        sb.append(this.g);
        sb.append((char) 0);
        sb.append(this.h);
        sb.append((char) 0);
        sb.append(this.i);
        sb.append((char) 0);
        sb.append(this.j);
        sb.append((char) 0);
        sb.append(this.k);
        sb.append((char) 0);
        sb.append(this.l);
        sb.append((char) 0);
        sb.append(this.m);
        sb.append((char) 0);
        sb.append(this.n);
        sb.append((char) 0);
        sb.append(this.o);
        sb.append((char) 0);
        sb.append(this.p);
        sb.append((char) 0);
        sb.append(this.r);
        sb.append((char) 0);
        Parsed parsed = this.q;
        sb.append(parsed != null);
        if (parsed != null) {
            sb.append((char) 0);
            sb.append(parsed.b());
        }
        return sb.toString();
    }

    public final long c() {
        Parsed parsed = this.q;
        return N.MsTyiXfW(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.r, parsed != null ? parsed.c() : 0L);
    }
}
