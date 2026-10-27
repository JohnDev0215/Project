package x1;

import android.graphics.Color;

/* JADX INFO: renamed from: x1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2662a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23690a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f23691b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f23692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f23693f;

    public C2662a(float f10, float f11, float f12, float f13, float f14, float f15) {
        this.f23690a = f10;
        this.f23691b = f11;
        this.c = f12;
        this.d = f13;
        this.f23692e = f14;
        this.f23693f = f15;
    }

    public static C2662a a(int i7) {
        C2672k c2672k = C2672k.f23710k;
        float fE = AbstractC2663b.e(Color.red(i7));
        float fE2 = AbstractC2663b.e(Color.green(i7));
        float fE3 = AbstractC2663b.e(Color.blue(i7));
        float[][] fArr = AbstractC2663b.d;
        float[] fArr2 = fArr[0];
        float f10 = (fArr2[2] * fE3) + (fArr2[1] * fE2) + (fArr2[0] * fE);
        float[] fArr3 = fArr[1];
        float f11 = (fArr3[2] * fE3) + (fArr3[1] * fE2) + (fArr3[0] * fE);
        float[] fArr4 = fArr[2];
        float f12 = (fE3 * fArr4[2]) + (fE2 * fArr4[1]) + (fE * fArr4[0]);
        float[][] fArr5 = AbstractC2663b.f23694a;
        float[] fArr6 = fArr5[0];
        float f13 = (fArr6[2] * f12) + (fArr6[1] * f11) + (fArr6[0] * f10);
        float[] fArr7 = fArr5[1];
        float f14 = (fArr7[2] * f12) + (fArr7[1] * f11) + (fArr7[0] * f10);
        float[] fArr8 = fArr5[2];
        float f15 = (f12 * fArr8[2]) + (f11 * fArr8[1]) + (f10 * fArr8[0]);
        float[] fArr9 = c2672k.f23715g;
        float f16 = fArr9[0] * f13;
        float f17 = fArr9[1] * f14;
        float f18 = fArr9[2] * f15;
        float fAbs = Math.abs(f16);
        float f19 = c2672k.f23716h;
        float fPow = (float) Math.pow(((double) (fAbs * f19)) / 100.0d, 0.42d);
        float fPow2 = (float) Math.pow(((double) (Math.abs(f17) * f19)) / 100.0d, 0.42d);
        float fPow3 = (float) Math.pow(((double) (Math.abs(f18) * f19)) / 100.0d, 0.42d);
        float fSignum = ((Math.signum(f16) * 400.0f) * fPow) / (fPow + 27.13f);
        float fSignum2 = ((Math.signum(f17) * 400.0f) * fPow2) / (fPow2 + 27.13f);
        float fSignum3 = ((Math.signum(f18) * 400.0f) * fPow3) / (fPow3 + 27.13f);
        double d = fSignum3;
        float f20 = ((float) (((((double) fSignum2) * (-12.0d)) + (((double) fSignum) * 11.0d)) + d)) / 11.0f;
        float f21 = ((float) (((double) (fSignum + fSignum2)) - (d * 2.0d))) / 9.0f;
        float f22 = fSignum2 * 20.0f;
        float f23 = ((21.0f * fSignum3) + ((fSignum * 20.0f) + f22)) / 20.0f;
        float f24 = (((fSignum * 40.0f) + f22) + fSignum3) / 20.0f;
        float fAtan2 = (((float) Math.atan2(f21, f20)) * 180.0f) / 3.1415927f;
        if (fAtan2 < 0.0f) {
            fAtan2 += 360.0f;
        } else if (fAtan2 >= 360.0f) {
            fAtan2 -= 360.0f;
        }
        float f25 = fAtan2;
        float f26 = (3.1415927f * f25) / 180.0f;
        float f27 = f24 * c2672k.f23712b;
        float f28 = c2672k.f23711a;
        float f29 = c2672k.d;
        float fPow4 = ((float) Math.pow(f27 / f28, c2672k.f23718j * f29)) * 100.0f;
        Math.sqrt(fPow4 / 100.0f);
        float f30 = f28 + 4.0f;
        float fPow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, c2672k.f23714f), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos(((((double) (((double) f25) < 20.14d ? 360.0f + f25 : f25)) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * c2672k.f23713e) * c2672k.c) * ((float) Math.sqrt((f21 * f21) + (f20 * f20)))) / (f23 + 0.305f), 0.9d)) * ((float) Math.sqrt(((double) fPow4) / 100.0d));
        float f31 = c2672k.f23717i * fPow5;
        Math.sqrt((r3 * f29) / f30);
        float f32 = (1.7f * fPow4) / ((0.007f * fPow4) + 1.0f);
        float fLog = ((float) Math.log((f31 * 0.0228f) + 1.0f)) * 43.85965f;
        double d10 = f26;
        return new C2662a(f25, fPow5, fPow4, f32, fLog * ((float) Math.cos(d10)), fLog * ((float) Math.sin(d10)));
    }

    public static C2662a b(float f10, float f11, float f12) {
        C2672k c2672k = C2672k.f23710k;
        float f13 = c2672k.d;
        Math.sqrt(((double) f10) / 100.0d);
        float f14 = c2672k.f23711a + 4.0f;
        float f15 = c2672k.f23717i * f11;
        Math.sqrt(((f11 / ((float) Math.sqrt(r1))) * c2672k.d) / f14);
        float f16 = (1.7f * f10) / ((0.007f * f10) + 1.0f);
        float fLog = ((float) Math.log((((double) f15) * 0.0228d) + 1.0d)) * 43.85965f;
        double d = (3.1415927f * f12) / 180.0f;
        return new C2662a(f12, f11, f10, f16, fLog * ((float) Math.cos(d)), fLog * ((float) Math.sin(d)));
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(x1.C2672k r16) {
        /*
            Method dump skipped, instruction units count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x1.C2662a.c(x1.k):int");
    }
}
