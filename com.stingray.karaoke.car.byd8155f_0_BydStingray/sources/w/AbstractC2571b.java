package w;

import h2.AbstractC1347d;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: w.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2571b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f23088a;

    static {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float[] fArr = new float[101];
        f23088a = fArr;
        float[] fArr2 = new float[101];
        float f18 = 0.0f;
        float f19 = 0.0f;
        for (int i7 = 0; i7 < 100; i7++) {
            float f20 = i7 / 100;
            float f21 = 1.0f;
            while (true) {
                f10 = ((f21 - f18) / 2.0f) + f18;
                f11 = 1.0f - f10;
                f12 = f10 * 3.0f * f11;
                f13 = f10 * f10 * f10;
                float f22 = (((f10 * 0.35000002f) + (f11 * 0.175f)) * f12) + f13;
                if (Math.abs(f22 - f20) < 1.0E-5d) {
                    break;
                } else if (f22 > f20) {
                    f21 = f10;
                } else {
                    f18 = f10;
                }
            }
            float f23 = 0.5f;
            fArr[i7] = (((f11 * 0.5f) + f10) * f12) + f13;
            float f24 = 1.0f;
            while (true) {
                f14 = ((f24 - f19) / 2.0f) + f19;
                f15 = 1.0f - f14;
                f16 = f14 * 3.0f * f15;
                f17 = f14 * f14 * f14;
                float f25 = (((f15 * f23) + f14) * f16) + f17;
                if (Math.abs(f25 - f20) >= 1.0E-5d) {
                    if (f25 > f20) {
                        f24 = f14;
                    } else {
                        f19 = f14;
                    }
                    f23 = 0.5f;
                }
            }
            fArr2[i7] = (((f14 * 0.35000002f) + (f15 * 0.175f)) * f16) + f17;
        }
        fArr2[100] = 1.0f;
        fArr[100] = 1.0f;
    }

    public static C2570a a(float f10) {
        float f11 = 0.0f;
        float f12 = 1.0f;
        float fU = AbstractC2673a.u(f10, 0.0f, 1.0f);
        float f13 = 100;
        int i7 = (int) (f13 * fU);
        if (i7 < 100) {
            float f14 = i7 / f13;
            int i10 = i7 + 1;
            float f15 = i10 / f13;
            float[] fArr = f23088a;
            float f16 = fArr[i7];
            float f17 = (fArr[i10] - f16) / (f15 - f14);
            float fA = AbstractC1347d.a(fU, f14, f17, f16);
            f11 = f17;
            f12 = fA;
        }
        return new C2570a(f12, f11);
    }
}
