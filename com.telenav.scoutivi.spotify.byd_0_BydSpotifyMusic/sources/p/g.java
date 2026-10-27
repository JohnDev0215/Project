package p;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: MonotonicCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double[] f8749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double[][] f8750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double[][] f8751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f8752d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    double[] f8753e;

    public g(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.f8753e = new double[length2];
        int i7 = length - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, i7, length2);
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        for (int i8 = 0; i8 < length2; i8++) {
            int i9 = 0;
            while (i9 < i7) {
                int i10 = i9 + 1;
                dArr3[i9][i8] = (dArr2[i10][i8] - dArr2[i9][i8]) / (dArr[i10] - dArr[i9]);
                if (i9 == 0) {
                    dArr4[i9][i8] = dArr3[i9][i8];
                } else {
                    dArr4[i9][i8] = (dArr3[i9 - 1][i8] + dArr3[i9][i8]) * 0.5d;
                }
                i9 = i10;
            }
            dArr4[i7][i8] = dArr3[length - 2][i8];
        }
        for (int i11 = 0; i11 < i7; i11++) {
            for (int i12 = 0; i12 < length2; i12++) {
                if (dArr3[i11][i12] == 0.0d) {
                    dArr4[i11][i12] = 0.0d;
                    dArr4[i11 + 1][i12] = 0.0d;
                } else {
                    double d7 = dArr4[i11][i12] / dArr3[i11][i12];
                    int i13 = i11 + 1;
                    double d8 = dArr4[i13][i12] / dArr3[i11][i12];
                    double dHypot = Math.hypot(d7, d8);
                    if (dHypot > 9.0d) {
                        double d9 = 3.0d / dHypot;
                        dArr4[i11][i12] = d7 * d9 * dArr3[i11][i12];
                        dArr4[i13][i12] = d9 * d8 * dArr3[i11][i12];
                    }
                }
            }
        }
        this.f8749a = dArr;
        this.f8750b = dArr2;
        this.f8751c = dArr4;
    }

    public static g h(String str) {
        double[] dArr = new double[str.length() / 2];
        int iIndexOf = str.indexOf(40) + 1;
        int iIndexOf2 = str.indexOf(44, iIndexOf);
        int i7 = 0;
        while (iIndexOf2 != -1) {
            dArr[i7] = Double.parseDouble(str.substring(iIndexOf, iIndexOf2).trim());
            iIndexOf = iIndexOf2 + 1;
            iIndexOf2 = str.indexOf(44, iIndexOf);
            i7++;
        }
        dArr[i7] = Double.parseDouble(str.substring(iIndexOf, str.indexOf(41, iIndexOf)).trim());
        return i(Arrays.copyOf(dArr, i7 + 1));
    }

    private static g i(double[] dArr) {
        int length = (dArr.length * 3) - 2;
        int length2 = dArr.length - 1;
        double d7 = 1.0d / ((double) length2);
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, length, 1);
        double[] dArr3 = new double[length];
        for (int i7 = 0; i7 < dArr.length; i7++) {
            double d8 = dArr[i7];
            int i8 = i7 + length2;
            dArr2[i8][0] = d8;
            double d9 = ((double) i7) * d7;
            dArr3[i8] = d9;
            if (i7 > 0) {
                int i9 = (length2 * 2) + i7;
                dArr2[i9][0] = d8 + 1.0d;
                dArr3[i9] = d9 + 1.0d;
                int i10 = i7 - 1;
                dArr2[i10][0] = (d8 - 1.0d) - d7;
                dArr3[i10] = (d9 - 1.0d) - d7;
            }
        }
        return new g(dArr3, dArr2);
    }

    private static double j(double d7, double d8, double d9, double d10, double d11, double d12) {
        double d13 = d8 * d8;
        double d14 = d8 * 6.0d;
        double d15 = 3.0d * d7;
        return ((((((((((-6.0d) * d13) * d10) + (d14 * d10)) + ((6.0d * d13) * d9)) - (d14 * d9)) + ((d15 * d12) * d13)) + ((d15 * d11) * d13)) - (((2.0d * d7) * d12) * d8)) - (((4.0d * d7) * d11) * d8)) + (d7 * d11);
    }

    private static double l(double d7, double d8, double d9, double d10, double d11, double d12) {
        double d13 = d8 * d8;
        double d14 = d13 * d8;
        double d15 = 3.0d * d13;
        double d16 = ((((((-2.0d) * d14) * d10) + (d15 * d10)) + ((d14 * 2.0d) * d9)) - (d15 * d9)) + d9;
        double d17 = d7 * d12;
        double d18 = d7 * d11;
        return ((((d16 + (d17 * d14)) + (d14 * d18)) - (d17 * d13)) - (((d7 * 2.0d) * d11) * d13)) + (d18 * d8);
    }

    @Override // p.b
    public double c(double d7, int i7) {
        double[] dArr = this.f8749a;
        int length = dArr.length;
        int i8 = 0;
        if (this.f8752d) {
            if (d7 <= dArr[0]) {
                return this.f8750b[0][i7] + ((d7 - dArr[0]) * k(dArr[0], i7));
            }
            int i9 = length - 1;
            if (d7 >= dArr[i9]) {
                return this.f8750b[i9][i7] + ((d7 - dArr[i9]) * k(dArr[i9], i7));
            }
        } else {
            if (d7 <= dArr[0]) {
                return this.f8750b[0][i7];
            }
            int i10 = length - 1;
            if (d7 >= dArr[i10]) {
                return this.f8750b[i10][i7];
            }
        }
        while (i8 < length - 1) {
            double[] dArr2 = this.f8749a;
            if (d7 == dArr2[i8]) {
                return this.f8750b[i8][i7];
            }
            int i11 = i8 + 1;
            if (d7 < dArr2[i11]) {
                double d8 = dArr2[i11] - dArr2[i8];
                double d9 = (d7 - dArr2[i8]) / d8;
                double[][] dArr3 = this.f8750b;
                double d10 = dArr3[i8][i7];
                double d11 = dArr3[i11][i7];
                double[][] dArr4 = this.f8751c;
                return l(d8, d9, d10, d11, dArr4[i8][i7], dArr4[i11][i7]);
            }
            i8 = i11;
        }
        return 0.0d;
    }

    @Override // p.b
    public void d(double d7, double[] dArr) {
        double[] dArr2 = this.f8749a;
        int length = dArr2.length;
        int i7 = 0;
        int length2 = this.f8750b[0].length;
        if (this.f8752d) {
            if (d7 <= dArr2[0]) {
                f(dArr2[0], this.f8753e);
                for (int i8 = 0; i8 < length2; i8++) {
                    dArr[i8] = this.f8750b[0][i8] + ((d7 - this.f8749a[0]) * this.f8753e[i8]);
                }
                return;
            }
            int i9 = length - 1;
            if (d7 >= dArr2[i9]) {
                f(dArr2[i9], this.f8753e);
                while (i7 < length2) {
                    dArr[i7] = this.f8750b[i9][i7] + ((d7 - this.f8749a[i9]) * this.f8753e[i7]);
                    i7++;
                }
                return;
            }
        } else {
            if (d7 <= dArr2[0]) {
                for (int i10 = 0; i10 < length2; i10++) {
                    dArr[i10] = this.f8750b[0][i10];
                }
                return;
            }
            int i11 = length - 1;
            if (d7 >= dArr2[i11]) {
                while (i7 < length2) {
                    dArr[i7] = this.f8750b[i11][i7];
                    i7++;
                }
                return;
            }
        }
        int i12 = 0;
        while (i12 < length - 1) {
            if (d7 == this.f8749a[i12]) {
                for (int i13 = 0; i13 < length2; i13++) {
                    dArr[i13] = this.f8750b[i12][i13];
                }
            }
            double[] dArr3 = this.f8749a;
            int i14 = i12 + 1;
            if (d7 < dArr3[i14]) {
                double d8 = dArr3[i14] - dArr3[i12];
                double d9 = (d7 - dArr3[i12]) / d8;
                while (i7 < length2) {
                    double[][] dArr4 = this.f8750b;
                    double d10 = dArr4[i12][i7];
                    double d11 = dArr4[i14][i7];
                    double[][] dArr5 = this.f8751c;
                    dArr[i7] = l(d8, d9, d10, d11, dArr5[i12][i7], dArr5[i14][i7]);
                    i7++;
                }
                return;
            }
            i12 = i14;
        }
    }

    @Override // p.b
    public void e(double d7, float[] fArr) {
        double[] dArr = this.f8749a;
        int length = dArr.length;
        int i7 = 0;
        int length2 = this.f8750b[0].length;
        if (this.f8752d) {
            if (d7 <= dArr[0]) {
                f(dArr[0], this.f8753e);
                for (int i8 = 0; i8 < length2; i8++) {
                    fArr[i8] = (float) (this.f8750b[0][i8] + ((d7 - this.f8749a[0]) * this.f8753e[i8]));
                }
                return;
            }
            int i9 = length - 1;
            if (d7 >= dArr[i9]) {
                f(dArr[i9], this.f8753e);
                while (i7 < length2) {
                    fArr[i7] = (float) (this.f8750b[i9][i7] + ((d7 - this.f8749a[i9]) * this.f8753e[i7]));
                    i7++;
                }
                return;
            }
        } else {
            if (d7 <= dArr[0]) {
                for (int i10 = 0; i10 < length2; i10++) {
                    fArr[i10] = (float) this.f8750b[0][i10];
                }
                return;
            }
            int i11 = length - 1;
            if (d7 >= dArr[i11]) {
                while (i7 < length2) {
                    fArr[i7] = (float) this.f8750b[i11][i7];
                    i7++;
                }
                return;
            }
        }
        int i12 = 0;
        while (i12 < length - 1) {
            if (d7 == this.f8749a[i12]) {
                for (int i13 = 0; i13 < length2; i13++) {
                    fArr[i13] = (float) this.f8750b[i12][i13];
                }
            }
            double[] dArr2 = this.f8749a;
            int i14 = i12 + 1;
            if (d7 < dArr2[i14]) {
                double d8 = dArr2[i14] - dArr2[i12];
                double d9 = (d7 - dArr2[i12]) / d8;
                while (i7 < length2) {
                    double[][] dArr3 = this.f8750b;
                    double d10 = dArr3[i12][i7];
                    double d11 = dArr3[i14][i7];
                    double[][] dArr4 = this.f8751c;
                    fArr[i7] = (float) l(d8, d9, d10, d11, dArr4[i12][i7], dArr4[i14][i7]);
                    i7++;
                }
                return;
            }
            i12 = i14;
        }
    }

    @Override // p.b
    public void f(double d7, double[] dArr) {
        double d8;
        double[] dArr2 = this.f8749a;
        int length = dArr2.length;
        int length2 = this.f8750b[0].length;
        if (d7 <= dArr2[0]) {
            d8 = dArr2[0];
        } else {
            int i7 = length - 1;
            d8 = d7 >= dArr2[i7] ? dArr2[i7] : d7;
        }
        int i8 = 0;
        while (i8 < length - 1) {
            double[] dArr3 = this.f8749a;
            int i9 = i8 + 1;
            if (d8 <= dArr3[i9]) {
                double d9 = dArr3[i9] - dArr3[i8];
                double d10 = (d8 - dArr3[i8]) / d9;
                for (int i10 = 0; i10 < length2; i10++) {
                    double[][] dArr4 = this.f8750b;
                    double d11 = dArr4[i8][i10];
                    double d12 = dArr4[i9][i10];
                    double[][] dArr5 = this.f8751c;
                    dArr[i10] = j(d9, d10, d11, d12, dArr5[i8][i10], dArr5[i9][i10]) / d9;
                }
                return;
            }
            i8 = i9;
        }
    }

    @Override // p.b
    public double[] g() {
        return this.f8749a;
    }

    public double k(double d7, int i7) {
        double d8;
        double[] dArr = this.f8749a;
        int length = dArr.length;
        int i8 = 0;
        if (d7 < dArr[0]) {
            d8 = dArr[0];
        } else {
            int i9 = length - 1;
            d8 = d7 >= dArr[i9] ? dArr[i9] : d7;
        }
        while (i8 < length - 1) {
            double[] dArr2 = this.f8749a;
            int i10 = i8 + 1;
            if (d8 <= dArr2[i10]) {
                double d9 = dArr2[i10] - dArr2[i8];
                double d10 = (d8 - dArr2[i8]) / d9;
                double[][] dArr3 = this.f8750b;
                double d11 = dArr3[i8][i7];
                double d12 = dArr3[i10][i7];
                double[][] dArr4 = this.f8751c;
                return j(d9, d10, d11, d12, dArr4[i8][i7], dArr4[i10][i7]) / d9;
            }
            i8 = i10;
        }
        return 0.0d;
    }
}
