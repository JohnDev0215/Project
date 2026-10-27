package y1;

import android.graphics.Color;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f24111a = new ThreadLocal();

    public static void a(int i7, int i10, int i11, float[] fArr) {
        float f10;
        float fAbs;
        float f11 = i7 / 255.0f;
        float f12 = i10 / 255.0f;
        float f13 = i11 / 255.0f;
        float fMax = Math.max(f11, Math.max(f12, f13));
        float fMin = Math.min(f11, Math.min(f12, f13));
        float f14 = fMax - fMin;
        float f15 = (fMax + fMin) / 2.0f;
        if (fMax == fMin) {
            f10 = 0.0f;
            fAbs = 0.0f;
        } else {
            f10 = fMax == f11 ? ((f12 - f13) / f14) % 6.0f : fMax == f12 ? ((f13 - f11) / f14) + 2.0f : 4.0f + ((f11 - f12) / f14);
            fAbs = f14 / (1.0f - Math.abs((2.0f * f15) - 1.0f));
        }
        float f16 = (f10 * 60.0f) % 360.0f;
        if (f16 < 0.0f) {
            f16 += 360.0f;
        }
        fArr[0] = f16 < 0.0f ? 0.0f : Math.min(f16, 360.0f);
        fArr[1] = fAbs < 0.0f ? 0.0f : Math.min(fAbs, 1.0f);
        fArr[2] = f15 >= 0.0f ? Math.min(f15, 1.0f) : 0.0f;
    }

    public static int b(double d, double d10, double d11) {
        double d12 = (((-0.4986d) * d11) + (((-1.5372d) * d10) + (3.2406d * d))) / 100.0d;
        double d13 = ((0.0415d * d11) + ((1.8758d * d10) + ((-0.9689d) * d))) / 100.0d;
        double d14 = ((1.057d * d11) + (((-0.204d) * d10) + (0.0557d * d))) / 100.0d;
        double dPow = d12 > 0.0031308d ? (Math.pow(d12, 0.4166666666666667d) * 1.055d) - 0.055d : d12 * 12.92d;
        double dPow2 = d13 > 0.0031308d ? (Math.pow(d13, 0.4166666666666667d) * 1.055d) - 0.055d : d13 * 12.92d;
        double dPow3 = d14 > 0.0031308d ? (Math.pow(d14, 0.4166666666666667d) * 1.055d) - 0.055d : d14 * 12.92d;
        int iRound = (int) Math.round(dPow * 255.0d);
        int iMin = iRound < 0 ? 0 : Math.min(iRound, 255);
        int iRound2 = (int) Math.round(dPow2 * 255.0d);
        int iMin2 = iRound2 < 0 ? 0 : Math.min(iRound2, 255);
        int iRound3 = (int) Math.round(dPow3 * 255.0d);
        return Color.rgb(iMin, iMin2, iRound3 >= 0 ? Math.min(iRound3, 255) : 0);
    }

    public static double c(int i7, int i10) {
        if (Color.alpha(i10) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i10));
        }
        if (Color.alpha(i7) < 255) {
            i7 = f(i7, i10);
        }
        double d = d(i7) + 0.05d;
        double d10 = d(i10) + 0.05d;
        return Math.max(d, d10) / Math.min(d, d10);
    }

    public static double d(int i7) {
        ThreadLocal threadLocal = f24111a;
        double[] dArr = (double[]) threadLocal.get();
        if (dArr == null) {
            dArr = new double[3];
            threadLocal.set(dArr);
        }
        int iRed = Color.red(i7);
        int iGreen = Color.green(i7);
        int iBlue = Color.blue(i7);
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d = ((double) iRed) / 255.0d;
        double dPow = d < 0.04045d ? d / 12.92d : Math.pow((d + 0.055d) / 1.055d, 2.4d);
        double d10 = ((double) iGreen) / 255.0d;
        double dPow2 = d10 < 0.04045d ? d10 / 12.92d : Math.pow((d10 + 0.055d) / 1.055d, 2.4d);
        double d11 = ((double) iBlue) / 255.0d;
        double dPow3 = d11 < 0.04045d ? d11 / 12.92d : Math.pow((d11 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = ((0.1805d * dPow3) + (0.3576d * dPow2) + (0.4124d * dPow)) * 100.0d;
        double d12 = ((0.0722d * dPow3) + (0.7152d * dPow2) + (0.2126d * dPow)) * 100.0d;
        dArr[1] = d12;
        dArr[2] = ((dPow3 * 0.9505d) + (dPow2 * 0.1192d) + (dPow * 0.0193d)) * 100.0d;
        return d12 / 100.0d;
    }

    public static int e(int i7, int i10, float f10) {
        int i11 = 255;
        if (Color.alpha(i10) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i10));
        }
        double d = f10;
        if (c(h(i7, 255), i10) < d) {
            return -1;
        }
        int i12 = 0;
        for (int i13 = 0; i13 <= 10 && i11 - i12 > 1; i13++) {
            int i14 = (i12 + i11) / 2;
            if (c(h(i7, i14), i10) < d) {
                i12 = i14;
            } else {
                i11 = i14;
            }
        }
        return i11;
    }

    public static int f(int i7, int i10) {
        int iAlpha = Color.alpha(i10);
        int iAlpha2 = Color.alpha(i7);
        int i11 = 255 - (((255 - iAlpha2) * (255 - iAlpha)) / 255);
        return Color.argb(i11, g(Color.red(i7), iAlpha2, Color.red(i10), iAlpha, i11), g(Color.green(i7), iAlpha2, Color.green(i10), iAlpha, i11), g(Color.blue(i7), iAlpha2, Color.blue(i10), iAlpha, i11));
    }

    public static int g(int i7, int i10, int i11, int i12, int i13) {
        if (i13 == 0) {
            return 0;
        }
        return (((255 - i10) * (i11 * i12)) + ((i7 * 255) * i10)) / (i13 * 255);
    }

    public static int h(int i7, int i10) {
        if (i10 < 0 || i10 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i7 & 16777215) | (i10 << 24);
    }
}
