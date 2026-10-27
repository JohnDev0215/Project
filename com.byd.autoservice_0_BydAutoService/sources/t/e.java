package t;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: MonotonicCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double[] f4961a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[][] f4962b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[][] f4963c;

    public e(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        int i3 = length - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, i3, length2);
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        for (int i4 = 0; i4 < length2; i4++) {
            int i5 = 0;
            while (i5 < i3) {
                int i6 = i5 + 1;
                dArr3[i5][i4] = (dArr2[i6][i4] - dArr2[i5][i4]) / (dArr[i6] - dArr[i5]);
                if (i5 == 0) {
                    dArr4[i5][i4] = dArr3[i5][i4];
                } else {
                    dArr4[i5][i4] = (dArr3[i5 - 1][i4] + dArr3[i5][i4]) * 0.5d;
                }
                i5 = i6;
            }
            dArr4[i3][i4] = dArr3[length - 2][i4];
        }
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < length2; i8++) {
                if (dArr3[i7][i8] == 0.0d) {
                    dArr4[i7][i8] = 0.0d;
                    dArr4[i7 + 1][i8] = 0.0d;
                } else {
                    double d3 = dArr4[i7][i8] / dArr3[i7][i8];
                    int i9 = i7 + 1;
                    double d4 = dArr4[i9][i8] / dArr3[i7][i8];
                    double dHypot = Math.hypot(d3, d4);
                    if (dHypot > 9.0d) {
                        double d5 = 3.0d / dHypot;
                        dArr4[i7][i8] = d3 * d5 * dArr3[i7][i8];
                        dArr4[i9][i8] = d5 * d4 * dArr3[i7][i8];
                    }
                }
            }
        }
        this.f4961a = dArr;
        this.f4962b = dArr2;
        this.f4963c = dArr4;
    }

    public static double i(double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = d4 * d4;
        double d10 = d4 * 6.0d;
        double d11 = 3.0d * d3;
        return ((((((((((-6.0d) * d9) * d6) + (d10 * d6)) + ((6.0d * d9) * d5)) - (d10 * d5)) + ((d11 * d8) * d9)) + ((d11 * d7) * d9)) - (((2.0d * d3) * d8) * d4)) - (((4.0d * d3) * d7) * d4)) + (d3 * d7);
    }

    public static double j(double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = d4 * d4;
        double d10 = d9 * d4;
        double d11 = 3.0d * d9;
        double d12 = ((((((-2.0d) * d10) * d6) + (d11 * d6)) + ((d10 * 2.0d) * d5)) - (d11 * d5)) + d5;
        double d13 = d3 * d8;
        double d14 = d3 * d7;
        return ((((d12 + (d13 * d10)) + (d10 * d14)) - (d13 * d9)) - (((d3 * 2.0d) * d7) * d9)) + (d14 * d4);
    }

    @Override // t.b
    public double c(double d3, int i3) {
        double[] dArr = this.f4961a;
        int length = dArr.length;
        int i4 = 0;
        if (d3 <= dArr[0]) {
            return this.f4962b[0][i3];
        }
        int i5 = length - 1;
        if (d3 >= dArr[i5]) {
            return this.f4962b[i5][i3];
        }
        while (i4 < i5) {
            double[] dArr2 = this.f4961a;
            if (d3 == dArr2[i4]) {
                return this.f4962b[i4][i3];
            }
            int i6 = i4 + 1;
            if (d3 < dArr2[i6]) {
                double d4 = dArr2[i6] - dArr2[i4];
                double d5 = (d3 - dArr2[i4]) / d4;
                double[][] dArr3 = this.f4962b;
                double d6 = dArr3[i4][i3];
                double d7 = dArr3[i6][i3];
                double[][] dArr4 = this.f4963c;
                return j(d4, d5, d6, d7, dArr4[i4][i3], dArr4[i6][i3]);
            }
            i4 = i6;
        }
        return 0.0d;
    }

    @Override // t.b
    public void d(double d3, double[] dArr) {
        double[] dArr2 = this.f4961a;
        int length = dArr2.length;
        int i3 = 0;
        int length2 = this.f4962b[0].length;
        if (d3 <= dArr2[0]) {
            for (int i4 = 0; i4 < length2; i4++) {
                dArr[i4] = this.f4962b[0][i4];
            }
            return;
        }
        int i5 = length - 1;
        if (d3 >= dArr2[i5]) {
            while (i3 < length2) {
                dArr[i3] = this.f4962b[i5][i3];
                i3++;
            }
            return;
        }
        int i6 = 0;
        while (i6 < i5) {
            if (d3 == this.f4961a[i6]) {
                for (int i7 = 0; i7 < length2; i7++) {
                    dArr[i7] = this.f4962b[i6][i7];
                }
            }
            double[] dArr3 = this.f4961a;
            int i8 = i6 + 1;
            if (d3 < dArr3[i8]) {
                double d4 = dArr3[i8] - dArr3[i6];
                double d5 = (d3 - dArr3[i6]) / d4;
                while (i3 < length2) {
                    double[][] dArr4 = this.f4962b;
                    double d6 = dArr4[i6][i3];
                    double d7 = dArr4[i8][i3];
                    double[][] dArr5 = this.f4963c;
                    dArr[i3] = j(d4, d5, d6, d7, dArr5[i6][i3], dArr5[i8][i3]);
                    i3++;
                }
                return;
            }
            i6 = i8;
        }
    }

    @Override // t.b
    public void e(double d3, float[] fArr) {
        double[] dArr = this.f4961a;
        int length = dArr.length;
        int i3 = 0;
        int length2 = this.f4962b[0].length;
        if (d3 <= dArr[0]) {
            for (int i4 = 0; i4 < length2; i4++) {
                fArr[i4] = (float) this.f4962b[0][i4];
            }
            return;
        }
        int i5 = length - 1;
        if (d3 >= dArr[i5]) {
            while (i3 < length2) {
                fArr[i3] = (float) this.f4962b[i5][i3];
                i3++;
            }
            return;
        }
        int i6 = 0;
        while (i6 < i5) {
            if (d3 == this.f4961a[i6]) {
                for (int i7 = 0; i7 < length2; i7++) {
                    fArr[i7] = (float) this.f4962b[i6][i7];
                }
            }
            double[] dArr2 = this.f4961a;
            int i8 = i6 + 1;
            if (d3 < dArr2[i8]) {
                double d4 = dArr2[i8] - dArr2[i6];
                double d5 = (d3 - dArr2[i6]) / d4;
                while (i3 < length2) {
                    double[][] dArr3 = this.f4962b;
                    double d6 = dArr3[i6][i3];
                    double d7 = dArr3[i8][i3];
                    double[][] dArr4 = this.f4963c;
                    fArr[i3] = (float) j(d4, d5, d6, d7, dArr4[i6][i3], dArr4[i8][i3]);
                    i3++;
                }
                return;
            }
            i6 = i8;
        }
    }

    @Override // t.b
    public double f(double d3, int i3) {
        double d4;
        double[] dArr = this.f4961a;
        int length = dArr.length;
        int i4 = 0;
        if (d3 < dArr[0]) {
            d4 = dArr[0];
        } else {
            int i5 = length - 1;
            d4 = d3 >= dArr[i5] ? dArr[i5] : d3;
        }
        while (i4 < length - 1) {
            double[] dArr2 = this.f4961a;
            int i6 = i4 + 1;
            if (d4 <= dArr2[i6]) {
                double d5 = dArr2[i6] - dArr2[i4];
                double d6 = (d4 - dArr2[i4]) / d5;
                double[][] dArr3 = this.f4962b;
                double d7 = dArr3[i4][i3];
                double d8 = dArr3[i6][i3];
                double[][] dArr4 = this.f4963c;
                return i(d5, d6, d7, d8, dArr4[i4][i3], dArr4[i6][i3]) / d5;
            }
            i4 = i6;
        }
        return 0.0d;
    }

    @Override // t.b
    public void g(double d3, double[] dArr) {
        double d4;
        double[] dArr2 = this.f4961a;
        int length = dArr2.length;
        int length2 = this.f4962b[0].length;
        if (d3 <= dArr2[0]) {
            d4 = dArr2[0];
        } else {
            int i3 = length - 1;
            d4 = d3 >= dArr2[i3] ? dArr2[i3] : d3;
        }
        int i4 = 0;
        while (i4 < length - 1) {
            double[] dArr3 = this.f4961a;
            int i5 = i4 + 1;
            if (d4 <= dArr3[i5]) {
                double d5 = dArr3[i5] - dArr3[i4];
                double d6 = (d4 - dArr3[i4]) / d5;
                for (int i6 = 0; i6 < length2; i6++) {
                    double[][] dArr4 = this.f4962b;
                    double d7 = dArr4[i4][i6];
                    double d8 = dArr4[i5][i6];
                    double[][] dArr5 = this.f4963c;
                    dArr[i6] = i(d5, d6, d7, d8, dArr5[i4][i6], dArr5[i5][i6]) / d5;
                }
                return;
            }
            i4 = i5;
        }
    }

    @Override // t.b
    public double[] h() {
        return this.f4961a;
    }
}
