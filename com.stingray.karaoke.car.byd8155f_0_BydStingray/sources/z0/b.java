package z0;

import A9.k;
import h6.q;
import l9.d;
import x.AbstractC2641j;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f24256a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f24257b = 1;
    public final int c;
    public final C2752a[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f24258e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float[] f24259f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float[] f24260g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f24261h;

    public b() {
        int i7;
        int iC = AbstractC2641j.c(1);
        if (iC == 0) {
            i7 = 3;
        } else {
            if (iC != 1) {
                throw new k();
            }
            i7 = 2;
        }
        this.c = i7;
        this.d = new C2752a[20];
        this.f24259f = new float[20];
        this.f24260g = new float[20];
        this.f24261h = new float[3];
    }

    public final void a(float f10, long j3) {
        int i7 = (this.f24258e + 1) % 20;
        this.f24258e = i7;
        C2752a[] c2752aArr = this.d;
        C2752a c2752a = c2752aArr[i7];
        if (c2752a != null) {
            c2752a.f24254a = j3;
            c2752a.f24255b = f10;
        } else {
            C2752a c2752a2 = new C2752a();
            c2752a2.f24254a = j3;
            c2752a2.f24255b = f10;
            c2752aArr[i7] = c2752a2;
        }
    }

    public final float b(float f10) {
        int i7;
        float[] fArr;
        float[] fArr2;
        boolean z6;
        float f11;
        float fSignum;
        float f12 = 0.0f;
        if (f10 <= 0.0f) {
            d.h0("maximumVelocity should be a positive value. You specified=" + f10);
            throw null;
        }
        int i10 = this.f24258e;
        C2752a[] c2752aArr = this.d;
        C2752a c2752a = c2752aArr[i10];
        if (c2752a == null) {
            f11 = 0.0f;
        } else {
            int i11 = 0;
            C2752a c2752a2 = c2752a;
            while (true) {
                C2752a c2752a3 = c2752aArr[i10];
                boolean z10 = this.f24256a;
                i7 = this.f24257b;
                fArr = this.f24259f;
                fArr2 = this.f24260g;
                if (c2752a3 == null) {
                    z6 = z10;
                    break;
                }
                long j3 = c2752a.f24254a;
                int i12 = i10;
                long j10 = c2752a3.f24254a;
                float f13 = j3 - j10;
                z6 = z10;
                float fAbs = Math.abs(j10 - c2752a2.f24254a);
                c2752a2 = (i7 == 1 || z6) ? c2752a3 : c2752a;
                if (f13 > 100.0f || fAbs > 40.0f) {
                    break;
                }
                fArr[i11] = c2752a3.f24255b;
                fArr2[i11] = -f13;
                i10 = (i12 == 0 ? 20 : i12) - 1;
                i11++;
                if (i11 >= 20) {
                    break;
                }
            }
            if (i11 >= this.c) {
                int iC = AbstractC2641j.c(i7);
                if (iC == 0) {
                    try {
                        float[] fArr3 = this.f24261h;
                        q.y(fArr2, fArr, i11, fArr3);
                        fSignum = fArr3[1];
                    } catch (IllegalArgumentException unused) {
                        fSignum = 0.0f;
                    }
                } else {
                    if (iC != 1) {
                        throw new k();
                    }
                    int i13 = i11 - 1;
                    float f14 = fArr2[i13];
                    int i14 = i13;
                    float fAbs2 = 0.0f;
                    while (i14 > 0) {
                        int i15 = i14 - 1;
                        float f15 = fArr2[i15];
                        if (f14 != f15) {
                            float f16 = (z6 ? -fArr[i15] : fArr[i14] - fArr[i15]) / (f14 - f15);
                            fAbs2 += Math.abs(f16) * (f16 - (Math.signum(fAbs2) * ((float) Math.sqrt(Math.abs(fAbs2) * 2))));
                            if (i14 == i13) {
                                fAbs2 *= 0.5f;
                            }
                        }
                        i14--;
                        f14 = f15;
                    }
                    fSignum = Math.signum(fAbs2) * ((float) Math.sqrt(Math.abs(fAbs2) * 2));
                }
                f11 = fSignum * 1000;
            } else {
                f11 = 0.0f;
            }
            f12 = 0.0f;
        }
        return (f11 == f12 || Float.isNaN(f11)) ? f12 : f11 > f12 ? AbstractC2673a.s(f11, f10) : AbstractC2673a.r(f11, -f10);
    }
}
