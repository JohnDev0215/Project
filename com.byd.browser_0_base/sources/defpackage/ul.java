package defpackage;

import android.graphics.Color;

/* JADX INFO: compiled from: CamColor.java */
/* JADX INFO: loaded from: classes.dex */
public final class ul {
    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;

    public ul(float f, float f2, float f3, float f4, float f5, float f6) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
    }

    public static ul a(int i) {
        io3 io3Var = io3.k;
        float fT = a8.T(Color.red(i));
        float fT2 = a8.T(Color.green(i));
        float fT3 = a8.T(Color.blue(i));
        float[][] fArr = a8.f;
        float[] fArr2 = fArr[0];
        float f = (fArr2[2] * fT3) + (fArr2[1] * fT2) + (fArr2[0] * fT);
        float[] fArr3 = fArr[1];
        float f2 = (fArr3[2] * fT3) + (fArr3[1] * fT2) + (fArr3[0] * fT);
        float[] fArr4 = fArr[2];
        float f3 = (fT3 * fArr4[2]) + (fT2 * fArr4[1]) + (fT * fArr4[0]);
        float[][] fArr5 = a8.c;
        float[] fArr6 = fArr5[0];
        float f4 = (fArr6[2] * f3) + (fArr6[1] * f2) + (fArr6[0] * f);
        float[] fArr7 = fArr5[1];
        float f5 = (fArr7[2] * f3) + (fArr7[1] * f2) + (fArr7[0] * f);
        float[] fArr8 = fArr5[2];
        float f6 = (f3 * fArr8[2]) + (f2 * fArr8[1]) + (f * fArr8[0]);
        float[] fArr9 = io3Var.g;
        float f7 = fArr9[0] * f4;
        float f8 = fArr9[1] * f5;
        float f9 = fArr9[2] * f6;
        float fAbs = Math.abs(f7);
        float f10 = io3Var.h;
        float fPow = (float) Math.pow(((double) (fAbs * f10)) / 100.0d, 0.42d);
        float fPow2 = (float) Math.pow(((double) (Math.abs(f8) * f10)) / 100.0d, 0.42d);
        float fPow3 = (float) Math.pow(((double) (Math.abs(f9) * f10)) / 100.0d, 0.42d);
        float fSignum = ((Math.signum(f7) * 400.0f) * fPow) / (fPow + 27.13f);
        float fSignum2 = ((Math.signum(f8) * 400.0f) * fPow2) / (fPow2 + 27.13f);
        float fSignum3 = ((Math.signum(f9) * 400.0f) * fPow3) / (fPow3 + 27.13f);
        double d = fSignum3;
        float f11 = ((float) (((((double) fSignum2) * (-12.0d)) + (((double) fSignum) * 11.0d)) + d)) / 11.0f;
        float f12 = ((float) (((double) (fSignum + fSignum2)) - (d * 2.0d))) / 9.0f;
        float f13 = fSignum2 * 20.0f;
        float f14 = ((21.0f * fSignum3) + ((fSignum * 20.0f) + f13)) / 20.0f;
        float f15 = (((fSignum * 40.0f) + f13) + fSignum3) / 20.0f;
        float fAtan2 = (((float) Math.atan2(f12, f11)) * 180.0f) / 3.1415927f;
        if (fAtan2 < 0.0f) {
            fAtan2 += 360.0f;
        } else if (fAtan2 >= 360.0f) {
            fAtan2 -= 360.0f;
        }
        float f16 = fAtan2;
        float f17 = (3.1415927f * f16) / 180.0f;
        float f18 = f15 * io3Var.b;
        float f19 = io3Var.a;
        double d2 = f18 / f19;
        float f20 = io3Var.j;
        float f21 = io3Var.d;
        float fPow4 = ((float) Math.pow(d2, f20 * f21)) * 100.0f;
        Math.sqrt(fPow4 / 100.0f);
        float f22 = f19 + 4.0f;
        float fPow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, io3Var.f), 0.73d)) * ((float) Math.pow((((((((float) (Math.cos(((((double) (((double) f16) < 20.14d ? 360.0f + f16 : f16)) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * io3Var.e) * io3Var.c) * ((float) Math.sqrt((f12 * f12) + (f11 * f11)))) / (f14 + 0.305f), 0.9d)) * ((float) Math.sqrt(((double) fPow4) / 100.0d));
        float f23 = io3Var.i * fPow5;
        Math.sqrt((r3 * f21) / f22);
        float f24 = (1.7f * fPow4) / ((0.007f * fPow4) + 1.0f);
        float fLog = ((float) Math.log((f23 * 0.0228f) + 1.0f)) * 43.85965f;
        double d3 = f17;
        return new ul(f16, fPow5, fPow4, f24, fLog * ((float) Math.cos(d3)), fLog * ((float) Math.sin(d3)));
    }

    public static ul b(float f, float f2, float f3) {
        io3 io3Var = io3.k;
        float f4 = io3Var.d;
        Math.sqrt(((double) f) / 100.0d);
        float f5 = io3Var.a + 4.0f;
        float f6 = io3Var.i * f2;
        Math.sqrt(((f2 / ((float) Math.sqrt(r1))) * io3Var.d) / f5);
        float f7 = (1.7f * f) / ((0.007f * f) + 1.0f);
        float fLog = ((float) Math.log((((double) f6) * 0.0228d) + 1.0d)) * 43.85965f;
        double d = (3.1415927f * f3) / 180.0f;
        return new ul(f3, f2, f, f7, fLog * ((float) Math.cos(d)), fLog * ((float) Math.sin(d)));
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(defpackage.io3 r16) {
        /*
            Method dump skipped, instruction units count: 381
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ul.c(io3):int");
    }
}
