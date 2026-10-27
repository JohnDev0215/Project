package x;

import h2.AbstractC1347d;
import java.util.Arrays;
import n0.C1886o;

/* JADX INFO: renamed from: x.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2654u {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static float[] f23617s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23618a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f23619b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f23620e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f23621f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f23622g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f23623h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f23624i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f23625j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f23626k;
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f23627m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f23628n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f23629p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f23630q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f23631r;

    public C2654u(int i7, float f10, float f11, float f12, float f13, float f14, float f15) {
        boolean z6;
        float f16;
        float f17;
        float f18 = f12;
        this.f23618a = f10;
        this.f23619b = f11;
        this.c = f18;
        this.d = f13;
        this.f23620e = f14;
        this.f23621f = f15;
        float f19 = f14 - f18;
        float f20 = f15 - f13;
        int i10 = 1;
        boolean z10 = i7 == 1 || (i7 == 4 ? f20 > 0.0f : !(i7 != 5 || f20 >= 0.0f));
        this.f23630q = z10;
        float f21 = f11 - f10;
        float f22 = 1 / f21;
        this.f23626k = f22;
        boolean z11 = 3 == i7;
        if (z11 || Math.abs(f19) < 0.001f || Math.abs(f20) < 0.001f) {
            float fHypot = (float) Math.hypot(f20, f19);
            this.f23622g = fHypot;
            this.f23629p = fHypot * f22;
            this.f23628n = f19 / f21;
            this.o = f20 / f21;
            this.f23625j = new float[101];
            this.l = Float.NaN;
            this.f23627m = Float.NaN;
            z6 = true;
        } else {
            this.f23625j = new float[101];
            this.l = (z10 ? -1 : 1) * f19;
            this.f23627m = f20 * (z10 ? 1 : -1);
            this.f23628n = z10 ? f14 : f18;
            this.o = z10 ? f13 : f15;
            float f23 = f13 - f15;
            int length = C1886o.c().length;
            float fHypot2 = 0.0f;
            float f24 = 0.0f;
            float f25 = 0.0f;
            int i11 = 0;
            while (i11 < length) {
                double radians = (float) Math.toRadians((((double) i11) * 90.0d) / ((double) (C1886o.c().length - i10)));
                float fSin = ((float) Math.sin(radians)) * f19;
                float fCos = ((float) Math.cos(radians)) * f23;
                if (i11 > 0) {
                    f16 = f23;
                    f17 = fCos;
                    fHypot2 += (float) Math.hypot(fSin - f24, fCos - f25);
                    C1886o.c()[i11] = fHypot2;
                } else {
                    f16 = f23;
                    f17 = fCos;
                }
                i11++;
                f23 = f16;
                f25 = f17;
                f24 = fSin;
                i10 = 1;
            }
            this.f23622g = fHypot2;
            int length2 = C1886o.c().length;
            for (int i12 = 0; i12 < length2; i12++) {
                float[] fArrC = C1886o.c();
                fArrC[i12] = fArrC[i12] / fHypot2;
            }
            float[] fArr = this.f23625j;
            int length3 = fArr.length;
            for (int i13 = 0; i13 < length3; i13++) {
                float length4 = i13 / (fArr.length - 1);
                float[] fArrC2 = C1886o.c();
                int iBinarySearch = Arrays.binarySearch(fArrC2, 0, fArrC2.length, length4);
                if (iBinarySearch >= 0) {
                    fArr[i13] = iBinarySearch / (C1886o.c().length - 1);
                } else if (iBinarySearch == -1) {
                    fArr[i13] = 0.0f;
                } else {
                    int i14 = -iBinarySearch;
                    int i15 = i14 - 2;
                    fArr[i13] = (((length4 - C1886o.c()[i15]) / (C1886o.c()[i14 - 1] - C1886o.c()[i15])) + i15) / (C1886o.c().length - 1);
                }
            }
            this.f23629p = this.f23622g * this.f23626k;
            z6 = z11;
        }
        this.f23631r = z6;
    }

    public final float a() {
        float f10 = this.l * this.f23624i;
        float fHypot = this.f23629p / ((float) Math.hypot(f10, (-this.f23627m) * this.f23623h));
        return this.f23630q ? (-f10) * fHypot : f10 * fHypot;
    }

    public final float b() {
        float f10 = this.l * this.f23624i;
        float f11 = (-this.f23627m) * this.f23623h;
        float fHypot = this.f23629p / ((float) Math.hypot(f10, f11));
        return this.f23630q ? (-f11) * fHypot : f11 * fHypot;
    }

    public final void c(float f10) {
        float f11 = (this.f23630q ? this.f23619b - f10 : f10 - this.f23618a) * this.f23626k;
        float fA = 0.0f;
        if (f11 > 0.0f) {
            fA = 1.0f;
            if (f11 < 1.0f) {
                float[] fArr = this.f23625j;
                float length = f11 * (fArr.length - 1);
                int i7 = (int) length;
                float f12 = fArr[i7];
                fA = AbstractC1347d.a(fArr[i7 + 1], f12, length - i7, f12);
            }
        }
        double d = fA * 1.5707964f;
        this.f23623h = (float) Math.sin(d);
        this.f23624i = (float) Math.cos(d);
    }
}
