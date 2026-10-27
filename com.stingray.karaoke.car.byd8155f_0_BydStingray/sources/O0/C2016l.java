package o0;

import n0.AbstractC1861K;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: o0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2016l extends AbstractC2007c {
    public static final float[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float[] f20206e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final float[] f20207f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final float[] f20208g;

    static {
        float[] fArrH = AbstractC2014j.h(new float[]{0.818933f, 0.032984544f, 0.0482003f, 0.36186674f, 0.9293119f, 0.26436627f, -0.12885971f, 0.03614564f, 0.6338517f}, AbstractC2014j.c(C2005a.f20173b.f20174a, new float[]{0.964212f, 1.0f, 0.8251883f}, new float[]{0.95042855f, 1.0f, 1.0889004f}));
        d = fArrH;
        float[] fArr = {0.21045426f, 1.9779985f, 0.025904037f, 0.7936178f, -2.4285922f, 0.78277177f, -0.004072047f, 0.4505937f, -0.80867577f};
        f20206e = fArr;
        f20207f = AbstractC2014j.g(fArrH);
        f20208g = AbstractC2014j.g(fArr);
    }

    @Override // o0.AbstractC2007c
    public final float a(int i7) {
        return i7 == 0 ? 1.0f : 0.5f;
    }

    @Override // o0.AbstractC2007c
    public final float b(int i7) {
        return i7 == 0 ? 0.0f : -0.5f;
    }

    @Override // o0.AbstractC2007c
    public final long d(float f10, float f11, float f12) {
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        if (f10 > 1.0f) {
            f10 = 1.0f;
        }
        if (f11 < -0.5f) {
            f11 = -0.5f;
        }
        if (f11 > 0.5f) {
            f11 = 0.5f;
        }
        if (f12 < -0.5f) {
            f12 = -0.5f;
        }
        float f13 = f12 <= 0.5f ? f12 : 0.5f;
        float[] fArr = f20208g;
        float f14 = (fArr[6] * f13) + (fArr[3] * f11) + (fArr[0] * f10);
        float f15 = (fArr[7] * f13) + (fArr[4] * f11) + (fArr[1] * f10);
        float f16 = (fArr[8] * f13) + (fArr[5] * f11) + (fArr[2] * f10);
        float f17 = f14 * f14 * f14;
        float f18 = f15 * f15 * f15;
        float f19 = f16 * f16 * f16;
        float[] fArr2 = f20207f;
        return (((long) Float.floatToRawIntBits((fArr2[6] * f19) + ((fArr2[3] * f18) + (fArr2[0] * f17)))) << 32) | (4294967295L & ((long) Float.floatToRawIntBits((fArr2[7] * f19) + (fArr2[4] * f18) + (fArr2[1] * f17))));
    }

    @Override // o0.AbstractC2007c
    public final float e(float f10, float f11, float f12) {
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        if (f10 > 1.0f) {
            f10 = 1.0f;
        }
        if (f11 < -0.5f) {
            f11 = -0.5f;
        }
        if (f11 > 0.5f) {
            f11 = 0.5f;
        }
        if (f12 < -0.5f) {
            f12 = -0.5f;
        }
        float f13 = f12 <= 0.5f ? f12 : 0.5f;
        float[] fArr = f20208g;
        float f14 = (fArr[6] * f13) + (fArr[3] * f11) + (fArr[0] * f10);
        float f15 = (fArr[7] * f13) + (fArr[4] * f11) + (fArr[1] * f10);
        float f16 = (fArr[8] * f13) + (fArr[5] * f11) + (fArr[2] * f10);
        float f17 = f14 * f14 * f14;
        float f18 = f15 * f15 * f15;
        float f19 = f16 * f16 * f16;
        float[] fArr2 = f20207f;
        return (fArr2[8] * f19) + (fArr2[5] * f18) + (fArr2[2] * f17);
    }

    @Override // o0.AbstractC2007c
    public final long f(float f10, float f11, float f12, float f13, AbstractC2007c abstractC2007c) {
        float[] fArr = d;
        float f14 = (fArr[6] * f12) + (fArr[3] * f11) + (fArr[0] * f10);
        float f15 = (fArr[7] * f12) + (fArr[4] * f11) + (fArr[1] * f10);
        float f16 = (fArr[8] * f12) + (fArr[5] * f11) + (fArr[2] * f10);
        float fB = AbstractC2673a.B(f14);
        float fB2 = AbstractC2673a.B(f15);
        float fB3 = AbstractC2673a.B(f16);
        float[] fArr2 = f20206e;
        return AbstractC1861K.b((fArr2[6] * fB3) + (fArr2[3] * fB2) + (fArr2[0] * fB), (fArr2[7] * fB3) + (fArr2[4] * fB2) + (fArr2[1] * fB), (fArr2[8] * fB3) + (fArr2[5] * fB2) + (fArr2[2] * fB), f13, abstractC2007c);
    }
}
