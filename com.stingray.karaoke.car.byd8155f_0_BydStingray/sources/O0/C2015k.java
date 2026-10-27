package o0;

import n0.AbstractC1861K;

/* JADX INFO: renamed from: o0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2015k extends AbstractC2007c {
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2015k(int i7, int i10, long j3, String str) {
        super(str, j3, i7);
        this.d = i10;
    }

    @Override // o0.AbstractC2007c
    public final float a(int i7) {
        switch (this.d) {
            case 0:
                return i7 == 0 ? 100.0f : 128.0f;
            default:
                return 2.0f;
        }
    }

    @Override // o0.AbstractC2007c
    public final float b(int i7) {
        switch (this.d) {
            case 0:
                return i7 == 0 ? 0.0f : -128.0f;
            default:
                return -2.0f;
        }
    }

    @Override // o0.AbstractC2007c
    public final long d(float f10, float f11, float f12) {
        switch (this.d) {
            case 0:
                if (f10 < 0.0f) {
                    f10 = 0.0f;
                }
                if (f10 > 100.0f) {
                    f10 = 100.0f;
                }
                if (f11 < -128.0f) {
                    f11 = -128.0f;
                }
                if (f11 > 128.0f) {
                    f11 = 128.0f;
                }
                float f13 = (f10 + 16.0f) / 116.0f;
                float f14 = (f11 * 0.002f) + f13;
                float f15 = f14 > 0.20689656f ? f14 * f14 * f14 : (f14 - 0.13793103f) * 0.12841855f;
                float f16 = f13 > 0.20689656f ? f13 * f13 * f13 : (f13 - 0.13793103f) * 0.12841855f;
                float[] fArr = AbstractC2014j.f20205e;
                return (((long) Float.floatToRawIntBits(f15 * fArr[0])) << 32) | (4294967295L & ((long) Float.floatToRawIntBits(f16 * fArr[1])));
            default:
                if (f10 < -2.0f) {
                    f10 = -2.0f;
                }
                if (f10 > 2.0f) {
                    f10 = 2.0f;
                }
                if (f11 < -2.0f) {
                    f11 = -2.0f;
                }
                return (((long) Float.floatToRawIntBits(f10)) << 32) | (((long) Float.floatToRawIntBits(f11 <= 2.0f ? f11 : 2.0f)) & 4294967295L);
        }
    }

    @Override // o0.AbstractC2007c
    public final float e(float f10, float f11, float f12) {
        switch (this.d) {
            case 0:
                if (f10 < 0.0f) {
                    f10 = 0.0f;
                }
                if (f10 > 100.0f) {
                    f10 = 100.0f;
                }
                if (f12 < -128.0f) {
                    f12 = -128.0f;
                }
                if (f12 > 128.0f) {
                    f12 = 128.0f;
                }
                float f13 = ((f10 + 16.0f) / 116.0f) - (f12 * 0.005f);
                return (f13 > 0.20689656f ? f13 * f13 * f13 : 0.12841855f * (f13 - 0.13793103f)) * AbstractC2014j.f20205e[2];
            default:
                if (f12 < -2.0f) {
                    f12 = -2.0f;
                }
                if (f12 > 2.0f) {
                    return 2.0f;
                }
                return f12;
        }
    }

    @Override // o0.AbstractC2007c
    public final long f(float f10, float f11, float f12, float f13, AbstractC2007c abstractC2007c) {
        switch (this.d) {
            case 0:
                float[] fArr = AbstractC2014j.f20205e;
                float f14 = f10 / fArr[0];
                float f15 = f11 / fArr[1];
                float f16 = f12 / fArr[2];
                float fCbrt = f14 > 0.008856452f ? (float) Math.cbrt(f14) : (f14 * 7.787037f) + 0.13793103f;
                float fCbrt2 = f15 > 0.008856452f ? (float) Math.cbrt(f15) : (f15 * 7.787037f) + 0.13793103f;
                float fCbrt3 = f16 > 0.008856452f ? (float) Math.cbrt(f16) : (f16 * 7.787037f) + 0.13793103f;
                float f17 = (116.0f * fCbrt2) - 16.0f;
                float f18 = (fCbrt - fCbrt2) * 500.0f;
                float f19 = (fCbrt2 - fCbrt3) * 200.0f;
                if (f17 < 0.0f) {
                    f17 = 0.0f;
                }
                if (f17 > 100.0f) {
                    f17 = 100.0f;
                }
                if (f18 < -128.0f) {
                    f18 = -128.0f;
                }
                if (f18 > 128.0f) {
                    f18 = 128.0f;
                }
                if (f19 < -128.0f) {
                    f19 = -128.0f;
                }
                return AbstractC1861K.b(f17, f18, f19 <= 128.0f ? f19 : 128.0f, f13, abstractC2007c);
            default:
                if (f10 < -2.0f) {
                    f10 = -2.0f;
                }
                if (f10 > 2.0f) {
                    f10 = 2.0f;
                }
                if (f11 < -2.0f) {
                    f11 = -2.0f;
                }
                if (f11 > 2.0f) {
                    f11 = 2.0f;
                }
                if (f12 < -2.0f) {
                    f12 = -2.0f;
                }
                return AbstractC1861K.b(f10, f11, f12 <= 2.0f ? f12 : 2.0f, f13, abstractC2007c);
        }
    }
}
