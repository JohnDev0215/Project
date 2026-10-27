package w;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: MonotonicCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double[] f8636a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[][] f8637b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[][] f8638c;

    public e(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        int i8 = length - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, i8, length2);
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        for (int i9 = 0; i9 < length2; i9++) {
            int i10 = 0;
            while (i10 < i8) {
                int i11 = i10 + 1;
                dArr3[i10][i9] = (dArr2[i11][i9] - dArr2[i10][i9]) / (dArr[i11] - dArr[i10]);
                if (i10 == 0) {
                    dArr4[i10][i9] = dArr3[i10][i9];
                } else {
                    dArr4[i10][i9] = (dArr3[i10 - 1][i9] + dArr3[i10][i9]) * 0.5d;
                }
                i10 = i11;
            }
            dArr4[i8][i9] = dArr3[length - 2][i9];
        }
        for (int i12 = 0; i12 < i8; i12++) {
            for (int i13 = 0; i13 < length2; i13++) {
                if (dArr3[i12][i13] == 0.0d) {
                    dArr4[i12][i13] = 0.0d;
                    dArr4[i12 + 1][i13] = 0.0d;
                } else {
                    double d8 = dArr4[i12][i13] / dArr3[i12][i13];
                    int i14 = i12 + 1;
                    double d9 = dArr4[i14][i13] / dArr3[i12][i13];
                    double dHypot = Math.hypot(d8, d9);
                    if (dHypot > 9.0d) {
                        double d10 = 3.0d / dHypot;
                        dArr4[i12][i13] = d8 * d10 * dArr3[i12][i13];
                        dArr4[i14][i13] = d10 * d9 * dArr3[i12][i13];
                    }
                }
            }
        }
        this.f8636a = dArr;
        this.f8637b = dArr2;
        this.f8638c = dArr4;
    }

    public static double i(double d8, double d9, double d10, double d11, double d12, double d13) {
        double d14 = d9 * d9;
        double d15 = d9 * 6.0d;
        double d16 = 3.0d * d8;
        return ((((((((((-6.0d) * d14) * d11) + (d15 * d11)) + ((6.0d * d14) * d10)) - (d15 * d10)) + ((d16 * d13) * d14)) + ((d16 * d12) * d14)) - (((2.0d * d8) * d13) * d9)) - (((4.0d * d8) * d12) * d9)) + (d8 * d12);
    }

    public static double j(double d8, double d9, double d10, double d11, double d12, double d13) {
        double d14 = d9 * d9;
        double d15 = d14 * d9;
        double d16 = 3.0d * d14;
        double d17 = ((((((-2.0d) * d15) * d11) + (d16 * d11)) + ((d15 * 2.0d) * d10)) - (d16 * d10)) + d10;
        double d18 = d8 * d13;
        double d19 = d8 * d12;
        return ((((d17 + (d18 * d15)) + (d15 * d19)) - (d18 * d14)) - (((d8 * 2.0d) * d12) * d14)) + (d19 * d9);
    }

    @Override // w.b
    public double c(double d8, int i8) {
        double[] dArr = this.f8636a;
        int length = dArr.length;
        int i9 = 0;
        if (d8 <= dArr[0]) {
            return this.f8637b[0][i8];
        }
        int i10 = length - 1;
        if (d8 >= dArr[i10]) {
            return this.f8637b[i10][i8];
        }
        while (i9 < i10) {
            double[] dArr2 = this.f8636a;
            if (d8 == dArr2[i9]) {
                return this.f8637b[i9][i8];
            }
            int i11 = i9 + 1;
            if (d8 < dArr2[i11]) {
                double d9 = dArr2[i11] - dArr2[i9];
                double d10 = (d8 - dArr2[i9]) / d9;
                double[][] dArr3 = this.f8637b;
                double d11 = dArr3[i9][i8];
                double d12 = dArr3[i11][i8];
                double[][] dArr4 = this.f8638c;
                return j(d9, d10, d11, d12, dArr4[i9][i8], dArr4[i11][i8]);
            }
            i9 = i11;
        }
        return 0.0d;
    }

    @Override // w.b
    public void d(double d8, double[] dArr) {
        double[] dArr2 = this.f8636a;
        int length = dArr2.length;
        int i8 = 0;
        int length2 = this.f8637b[0].length;
        if (d8 <= dArr2[0]) {
            for (int i9 = 0; i9 < length2; i9++) {
                dArr[i9] = this.f8637b[0][i9];
            }
            return;
        }
        int i10 = length - 1;
        if (d8 >= dArr2[i10]) {
            while (i8 < length2) {
                dArr[i8] = this.f8637b[i10][i8];
                i8++;
            }
            return;
        }
        int i11 = 0;
        while (i11 < i10) {
            if (d8 == this.f8636a[i11]) {
                for (int i12 = 0; i12 < length2; i12++) {
                    dArr[i12] = this.f8637b[i11][i12];
                }
            }
            double[] dArr3 = this.f8636a;
            int i13 = i11 + 1;
            if (d8 < dArr3[i13]) {
                double d9 = dArr3[i13] - dArr3[i11];
                double d10 = (d8 - dArr3[i11]) / d9;
                while (i8 < length2) {
                    double[][] dArr4 = this.f8637b;
                    double d11 = dArr4[i11][i8];
                    double d12 = dArr4[i13][i8];
                    double[][] dArr5 = this.f8638c;
                    dArr[i8] = j(d9, d10, d11, d12, dArr5[i11][i8], dArr5[i13][i8]);
                    i8++;
                }
                return;
            }
            i11 = i13;
        }
    }

    @Override // w.b
    public void e(double d8, float[] fArr) {
        double[] dArr = this.f8636a;
        int length = dArr.length;
        int i8 = 0;
        int length2 = this.f8637b[0].length;
        if (d8 <= dArr[0]) {
            for (int i9 = 0; i9 < length2; i9++) {
                fArr[i9] = (float) this.f8637b[0][i9];
            }
            return;
        }
        int i10 = length - 1;
        if (d8 >= dArr[i10]) {
            while (i8 < length2) {
                fArr[i8] = (float) this.f8637b[i10][i8];
                i8++;
            }
            return;
        }
        int i11 = 0;
        while (i11 < i10) {
            if (d8 == this.f8636a[i11]) {
                for (int i12 = 0; i12 < length2; i12++) {
                    fArr[i12] = (float) this.f8637b[i11][i12];
                }
            }
            double[] dArr2 = this.f8636a;
            int i13 = i11 + 1;
            if (d8 < dArr2[i13]) {
                double d9 = dArr2[i13] - dArr2[i11];
                double d10 = (d8 - dArr2[i11]) / d9;
                while (i8 < length2) {
                    double[][] dArr3 = this.f8637b;
                    double d11 = dArr3[i11][i8];
                    double d12 = dArr3[i13][i8];
                    double[][] dArr4 = this.f8638c;
                    fArr[i8] = (float) j(d9, d10, d11, d12, dArr4[i11][i8], dArr4[i13][i8]);
                    i8++;
                }
                return;
            }
            i11 = i13;
        }
    }

    @Override // w.b
    public double f(double d8, int i8) {
        double d9;
        double[] dArr = this.f8636a;
        int length = dArr.length;
        int i9 = 0;
        if (d8 < dArr[0]) {
            d9 = dArr[0];
        } else {
            int i10 = length - 1;
            d9 = d8 >= dArr[i10] ? dArr[i10] : d8;
        }
        while (i9 < length - 1) {
            double[] dArr2 = this.f8636a;
            int i11 = i9 + 1;
            if (d9 <= dArr2[i11]) {
                double d10 = dArr2[i11] - dArr2[i9];
                double d11 = (d9 - dArr2[i9]) / d10;
                double[][] dArr3 = this.f8637b;
                double d12 = dArr3[i9][i8];
                double d13 = dArr3[i11][i8];
                double[][] dArr4 = this.f8638c;
                return i(d10, d11, d12, d13, dArr4[i9][i8], dArr4[i11][i8]) / d10;
            }
            i9 = i11;
        }
        return 0.0d;
    }

    @Override // w.b
    public void g(double d8, double[] dArr) {
        double d9;
        double[] dArr2 = this.f8636a;
        int length = dArr2.length;
        int length2 = this.f8637b[0].length;
        if (d8 <= dArr2[0]) {
            d9 = dArr2[0];
        } else {
            int i8 = length - 1;
            d9 = d8 >= dArr2[i8] ? dArr2[i8] : d8;
        }
        int i9 = 0;
        while (i9 < length - 1) {
            double[] dArr3 = this.f8636a;
            int i10 = i9 + 1;
            if (d9 <= dArr3[i10]) {
                double d10 = dArr3[i10] - dArr3[i9];
                double d11 = (d9 - dArr3[i9]) / d10;
                for (int i11 = 0; i11 < length2; i11++) {
                    double[][] dArr4 = this.f8637b;
                    double d12 = dArr4[i9][i11];
                    double d13 = dArr4[i10][i11];
                    double[][] dArr5 = this.f8638c;
                    dArr[i11] = i(d10, d11, d12, d13, dArr5[i9][i11], dArr5[i10][i11]) / d10;
                }
                return;
            }
            i9 = i10;
        }
    }

    @Override // w.b
    public double[] h() {
        return this.f8636a;
    }
}
