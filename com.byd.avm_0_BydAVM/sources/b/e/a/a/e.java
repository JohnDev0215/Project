package b.e.a.a;

import java.lang.reflect.Array;

/* JADX INFO: compiled from: MonotonicCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double[] f1575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double[][] f1576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double[][] f1577c;

    public e(double[] dArr, double[][] dArr2) {
        int length = dArr.length;
        int length2 = dArr2[0].length;
        int i = length - 1;
        double[][] dArr3 = (double[][]) Array.newInstance((Class<?>) double.class, i, length2);
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, length, length2);
        for (int i2 = 0; i2 < length2; i2++) {
            int i3 = 0;
            while (i3 < i) {
                int i4 = i3 + 1;
                dArr3[i3][i2] = (dArr2[i4][i2] - dArr2[i3][i2]) / (dArr[i4] - dArr[i3]);
                if (i3 == 0) {
                    dArr4[i3][i2] = dArr3[i3][i2];
                } else {
                    dArr4[i3][i2] = (dArr3[i3 - 1][i2] + dArr3[i3][i2]) * 0.5d;
                }
                i3 = i4;
            }
            dArr4[i][i2] = dArr3[length - 2][i2];
        }
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = 0; i6 < length2; i6++) {
                if (dArr3[i5][i6] == 0.0d) {
                    dArr4[i5][i6] = 0.0d;
                    dArr4[i5 + 1][i6] = 0.0d;
                } else {
                    double d2 = dArr4[i5][i6] / dArr3[i5][i6];
                    int i7 = i5 + 1;
                    double d3 = dArr4[i7][i6] / dArr3[i5][i6];
                    double dHypot = Math.hypot(d2, d3);
                    if (dHypot > 9.0d) {
                        double d4 = 3.0d / dHypot;
                        dArr4[i5][i6] = d2 * d4 * dArr3[i5][i6];
                        dArr4[i7][i6] = d4 * d3 * dArr3[i5][i6];
                    }
                }
            }
        }
        this.f1575a = dArr;
        this.f1576b = dArr2;
        this.f1577c = dArr4;
    }

    private static double i(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d3 * d3;
        double d9 = d3 * 6.0d;
        double d10 = 3.0d * d2;
        return ((((((((((-6.0d) * d8) * d5) + (d9 * d5)) + ((6.0d * d8) * d4)) - (d9 * d4)) + ((d10 * d7) * d8)) + ((d10 * d6) * d8)) - (((2.0d * d2) * d7) * d3)) - (((4.0d * d2) * d6) * d3)) + (d2 * d6);
    }

    private static double j(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d3 * d3;
        double d9 = d8 * d3;
        double d10 = 3.0d * d8;
        double d11 = ((((((-2.0d) * d9) * d5) + (d10 * d5)) + ((d9 * 2.0d) * d4)) - (d10 * d4)) + d4;
        double d12 = d2 * d7;
        double d13 = d2 * d6;
        return ((((d11 + (d12 * d9)) + (d9 * d13)) - (d12 * d8)) - (((d2 * 2.0d) * d6) * d8)) + (d13 * d3);
    }

    @Override // b.e.a.a.b
    public double c(double d2, int i) {
        double[] dArr = this.f1575a;
        int length = dArr.length;
        int i2 = 0;
        if (d2 <= dArr[0]) {
            return this.f1576b[0][i];
        }
        int i3 = length - 1;
        if (d2 >= dArr[i3]) {
            return this.f1576b[i3][i];
        }
        while (i2 < i3) {
            double[] dArr2 = this.f1575a;
            if (d2 == dArr2[i2]) {
                return this.f1576b[i2][i];
            }
            int i4 = i2 + 1;
            if (d2 < dArr2[i4]) {
                double d3 = dArr2[i4] - dArr2[i2];
                double d4 = (d2 - dArr2[i2]) / d3;
                double[][] dArr3 = this.f1576b;
                double d5 = dArr3[i2][i];
                double d6 = dArr3[i4][i];
                double[][] dArr4 = this.f1577c;
                return j(d3, d4, d5, d6, dArr4[i2][i], dArr4[i4][i]);
            }
            i2 = i4;
        }
        return 0.0d;
    }

    @Override // b.e.a.a.b
    public void d(double d2, double[] dArr) {
        double[] dArr2 = this.f1575a;
        int length = dArr2.length;
        int i = 0;
        int length2 = this.f1576b[0].length;
        if (d2 <= dArr2[0]) {
            for (int i2 = 0; i2 < length2; i2++) {
                dArr[i2] = this.f1576b[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        if (d2 >= dArr2[i3]) {
            while (i < length2) {
                dArr[i] = this.f1576b[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d2 == this.f1575a[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    dArr[i5] = this.f1576b[i4][i5];
                }
            }
            double[] dArr3 = this.f1575a;
            int i6 = i4 + 1;
            if (d2 < dArr3[i6]) {
                double d3 = dArr3[i6] - dArr3[i4];
                double d4 = (d2 - dArr3[i4]) / d3;
                while (i < length2) {
                    double[][] dArr4 = this.f1576b;
                    double d5 = dArr4[i4][i];
                    double d6 = dArr4[i6][i];
                    double[][] dArr5 = this.f1577c;
                    dArr[i] = j(d3, d4, d5, d6, dArr5[i4][i], dArr5[i6][i]);
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // b.e.a.a.b
    public void e(double d2, float[] fArr) {
        double[] dArr = this.f1575a;
        int length = dArr.length;
        int i = 0;
        int length2 = this.f1576b[0].length;
        if (d2 <= dArr[0]) {
            for (int i2 = 0; i2 < length2; i2++) {
                fArr[i2] = (float) this.f1576b[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        if (d2 >= dArr[i3]) {
            while (i < length2) {
                fArr[i] = (float) this.f1576b[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d2 == this.f1575a[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    fArr[i5] = (float) this.f1576b[i4][i5];
                }
            }
            double[] dArr2 = this.f1575a;
            int i6 = i4 + 1;
            if (d2 < dArr2[i6]) {
                double d3 = dArr2[i6] - dArr2[i4];
                double d4 = (d2 - dArr2[i4]) / d3;
                while (i < length2) {
                    double[][] dArr3 = this.f1576b;
                    double d5 = dArr3[i4][i];
                    double d6 = dArr3[i6][i];
                    double[][] dArr4 = this.f1577c;
                    fArr[i] = (float) j(d3, d4, d5, d6, dArr4[i4][i], dArr4[i6][i]);
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // b.e.a.a.b
    public double f(double d2, int i) {
        double d3;
        double[] dArr = this.f1575a;
        int length = dArr.length;
        int i2 = 0;
        if (d2 < dArr[0]) {
            d3 = dArr[0];
        } else {
            int i3 = length - 1;
            d3 = d2 >= dArr[i3] ? dArr[i3] : d2;
        }
        while (i2 < length - 1) {
            double[] dArr2 = this.f1575a;
            int i4 = i2 + 1;
            if (d3 <= dArr2[i4]) {
                double d4 = dArr2[i4] - dArr2[i2];
                double d5 = (d3 - dArr2[i2]) / d4;
                double[][] dArr3 = this.f1576b;
                double d6 = dArr3[i2][i];
                double d7 = dArr3[i4][i];
                double[][] dArr4 = this.f1577c;
                return i(d4, d5, d6, d7, dArr4[i2][i], dArr4[i4][i]) / d4;
            }
            i2 = i4;
        }
        return 0.0d;
    }

    @Override // b.e.a.a.b
    public void g(double d2, double[] dArr) {
        double d3;
        double[] dArr2 = this.f1575a;
        int length = dArr2.length;
        int length2 = this.f1576b[0].length;
        if (d2 <= dArr2[0]) {
            d3 = dArr2[0];
        } else {
            int i = length - 1;
            d3 = d2 >= dArr2[i] ? dArr2[i] : d2;
        }
        int i2 = 0;
        while (i2 < length - 1) {
            double[] dArr3 = this.f1575a;
            int i3 = i2 + 1;
            if (d3 <= dArr3[i3]) {
                double d4 = dArr3[i3] - dArr3[i2];
                double d5 = (d3 - dArr3[i2]) / d4;
                for (int i4 = 0; i4 < length2; i4++) {
                    double[][] dArr4 = this.f1576b;
                    double d6 = dArr4[i2][i4];
                    double d7 = dArr4[i3][i4];
                    double[][] dArr5 = this.f1577c;
                    dArr[i4] = i(d4, d5, d6, d7, dArr5[i2][i4], dArr5[i3][i4]) / d4;
                }
                return;
            }
            i2 = i3;
        }
    }

    @Override // b.e.a.a.b
    public double[] h() {
        return this.f1575a;
    }
}
