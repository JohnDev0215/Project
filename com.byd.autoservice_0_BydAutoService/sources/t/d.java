package t;

/* JADX INFO: compiled from: LinearCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double[] f4958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[][] f4959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f4960c;

    public d(double[] dArr, double[][] dArr2) {
        this.f4960c = Double.NaN;
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.f4958a = dArr;
        this.f4959b = dArr2;
        if (length2 > 2) {
            int i3 = 0;
            double d3 = 0.0d;
            double d4 = 0.0d;
            while (i3 < dArr.length) {
                double d5 = dArr2[i3][0];
                double d6 = dArr2[i3][0];
                if (i3 > 0) {
                    Math.hypot(d5 - d3, d6 - d4);
                }
                i3++;
                d3 = d5;
                d4 = d6;
            }
            this.f4960c = 0.0d;
        }
    }

    @Override // t.b
    public double c(double d3, int i3) {
        double[] dArr = this.f4958a;
        int length = dArr.length;
        int i4 = 0;
        if (d3 <= dArr[0]) {
            return this.f4959b[0][i3];
        }
        int i5 = length - 1;
        if (d3 >= dArr[i5]) {
            return this.f4959b[i5][i3];
        }
        while (i4 < i5) {
            double[] dArr2 = this.f4958a;
            if (d3 == dArr2[i4]) {
                return this.f4959b[i4][i3];
            }
            int i6 = i4 + 1;
            if (d3 < dArr2[i6]) {
                double d4 = (d3 - dArr2[i4]) / (dArr2[i6] - dArr2[i4]);
                double[][] dArr3 = this.f4959b;
                return (dArr3[i4][i3] * (1.0d - d4)) + (dArr3[i6][i3] * d4);
            }
            i4 = i6;
        }
        return 0.0d;
    }

    @Override // t.b
    public void d(double d3, double[] dArr) {
        double[] dArr2 = this.f4958a;
        int length = dArr2.length;
        int i3 = 0;
        int length2 = this.f4959b[0].length;
        if (d3 <= dArr2[0]) {
            for (int i4 = 0; i4 < length2; i4++) {
                dArr[i4] = this.f4959b[0][i4];
            }
            return;
        }
        int i5 = length - 1;
        if (d3 >= dArr2[i5]) {
            while (i3 < length2) {
                dArr[i3] = this.f4959b[i5][i3];
                i3++;
            }
            return;
        }
        int i6 = 0;
        while (i6 < i5) {
            if (d3 == this.f4958a[i6]) {
                for (int i7 = 0; i7 < length2; i7++) {
                    dArr[i7] = this.f4959b[i6][i7];
                }
            }
            double[] dArr3 = this.f4958a;
            int i8 = i6 + 1;
            if (d3 < dArr3[i8]) {
                double d4 = (d3 - dArr3[i6]) / (dArr3[i8] - dArr3[i6]);
                while (i3 < length2) {
                    double[][] dArr4 = this.f4959b;
                    dArr[i3] = (dArr4[i6][i3] * (1.0d - d4)) + (dArr4[i8][i3] * d4);
                    i3++;
                }
                return;
            }
            i6 = i8;
        }
    }

    @Override // t.b
    public void e(double d3, float[] fArr) {
        double[] dArr = this.f4958a;
        int length = dArr.length;
        int i3 = 0;
        int length2 = this.f4959b[0].length;
        if (d3 <= dArr[0]) {
            for (int i4 = 0; i4 < length2; i4++) {
                fArr[i4] = (float) this.f4959b[0][i4];
            }
            return;
        }
        int i5 = length - 1;
        if (d3 >= dArr[i5]) {
            while (i3 < length2) {
                fArr[i3] = (float) this.f4959b[i5][i3];
                i3++;
            }
            return;
        }
        int i6 = 0;
        while (i6 < i5) {
            if (d3 == this.f4958a[i6]) {
                for (int i7 = 0; i7 < length2; i7++) {
                    fArr[i7] = (float) this.f4959b[i6][i7];
                }
            }
            double[] dArr2 = this.f4958a;
            int i8 = i6 + 1;
            if (d3 < dArr2[i8]) {
                double d4 = (d3 - dArr2[i6]) / (dArr2[i8] - dArr2[i6]);
                while (i3 < length2) {
                    double[][] dArr3 = this.f4959b;
                    fArr[i3] = (float) ((dArr3[i6][i3] * (1.0d - d4)) + (dArr3[i8][i3] * d4));
                    i3++;
                }
                return;
            }
            i6 = i8;
        }
    }

    @Override // t.b
    public double f(double d3, int i3) {
        double[] dArr = this.f4958a;
        int length = dArr.length;
        int i4 = 0;
        if (d3 < dArr[0]) {
            d3 = dArr[0];
        } else {
            int i5 = length - 1;
            if (d3 >= dArr[i5]) {
                d3 = dArr[i5];
            }
        }
        while (i4 < length - 1) {
            double[] dArr2 = this.f4958a;
            int i6 = i4 + 1;
            if (d3 <= dArr2[i6]) {
                double d4 = dArr2[i6] - dArr2[i4];
                double d5 = dArr2[i4];
                double[][] dArr3 = this.f4959b;
                return (dArr3[i6][i3] - dArr3[i4][i3]) / d4;
            }
            i4 = i6;
        }
        return 0.0d;
    }

    @Override // t.b
    public void g(double d3, double[] dArr) {
        double[] dArr2 = this.f4958a;
        int length = dArr2.length;
        int length2 = this.f4959b[0].length;
        if (d3 <= dArr2[0]) {
            d3 = dArr2[0];
        } else {
            int i3 = length - 1;
            if (d3 >= dArr2[i3]) {
                d3 = dArr2[i3];
            }
        }
        int i4 = 0;
        while (i4 < length - 1) {
            double[] dArr3 = this.f4958a;
            int i5 = i4 + 1;
            if (d3 <= dArr3[i5]) {
                double d4 = dArr3[i5] - dArr3[i4];
                double d5 = dArr3[i4];
                for (int i6 = 0; i6 < length2; i6++) {
                    double[][] dArr4 = this.f4959b;
                    dArr[i6] = (dArr4[i5][i6] - dArr4[i4][i6]) / d4;
                }
                return;
            }
            i4 = i5;
        }
    }

    @Override // t.b
    public double[] h() {
        return this.f4958a;
    }
}
