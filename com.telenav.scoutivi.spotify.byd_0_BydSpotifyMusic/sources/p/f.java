package p;

/* JADX INFO: compiled from: LinearCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double[] f8744a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double[][] f8745b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f8746c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f8747d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    double[] f8748e;

    public f(double[] dArr, double[][] dArr2) {
        this.f8746c = Double.NaN;
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.f8748e = new double[length2];
        this.f8744a = dArr;
        this.f8745b = dArr2;
        if (length2 > 2) {
            int i7 = 0;
            double d7 = 0.0d;
            double d8 = 0.0d;
            while (i7 < dArr.length) {
                double d9 = dArr2[i7][0];
                double d10 = dArr2[i7][0];
                if (i7 > 0) {
                    Math.hypot(d9 - d7, d10 - d8);
                }
                i7++;
                d7 = d9;
                d8 = d10;
            }
            this.f8746c = 0.0d;
        }
    }

    @Override // p.b
    public double c(double d7, int i7) {
        double[] dArr = this.f8744a;
        int length = dArr.length;
        int i8 = 0;
        if (this.f8747d) {
            if (d7 <= dArr[0]) {
                return this.f8745b[0][i7] + ((d7 - dArr[0]) * h(dArr[0], i7));
            }
            int i9 = length - 1;
            if (d7 >= dArr[i9]) {
                return this.f8745b[i9][i7] + ((d7 - dArr[i9]) * h(dArr[i9], i7));
            }
        } else {
            if (d7 <= dArr[0]) {
                return this.f8745b[0][i7];
            }
            int i10 = length - 1;
            if (d7 >= dArr[i10]) {
                return this.f8745b[i10][i7];
            }
        }
        while (i8 < length - 1) {
            double[] dArr2 = this.f8744a;
            if (d7 == dArr2[i8]) {
                return this.f8745b[i8][i7];
            }
            int i11 = i8 + 1;
            if (d7 < dArr2[i11]) {
                double d8 = (d7 - dArr2[i8]) / (dArr2[i11] - dArr2[i8]);
                double[][] dArr3 = this.f8745b;
                return (dArr3[i8][i7] * (1.0d - d8)) + (dArr3[i11][i7] * d8);
            }
            i8 = i11;
        }
        return 0.0d;
    }

    @Override // p.b
    public void d(double d7, double[] dArr) {
        double[] dArr2 = this.f8744a;
        int length = dArr2.length;
        int i7 = 0;
        int length2 = this.f8745b[0].length;
        if (this.f8747d) {
            if (d7 <= dArr2[0]) {
                f(dArr2[0], this.f8748e);
                for (int i8 = 0; i8 < length2; i8++) {
                    dArr[i8] = this.f8745b[0][i8] + ((d7 - this.f8744a[0]) * this.f8748e[i8]);
                }
                return;
            }
            int i9 = length - 1;
            if (d7 >= dArr2[i9]) {
                f(dArr2[i9], this.f8748e);
                while (i7 < length2) {
                    dArr[i7] = this.f8745b[i9][i7] + ((d7 - this.f8744a[i9]) * this.f8748e[i7]);
                    i7++;
                }
                return;
            }
        } else {
            if (d7 <= dArr2[0]) {
                for (int i10 = 0; i10 < length2; i10++) {
                    dArr[i10] = this.f8745b[0][i10];
                }
                return;
            }
            int i11 = length - 1;
            if (d7 >= dArr2[i11]) {
                while (i7 < length2) {
                    dArr[i7] = this.f8745b[i11][i7];
                    i7++;
                }
                return;
            }
        }
        int i12 = 0;
        while (i12 < length - 1) {
            if (d7 == this.f8744a[i12]) {
                for (int i13 = 0; i13 < length2; i13++) {
                    dArr[i13] = this.f8745b[i12][i13];
                }
            }
            double[] dArr3 = this.f8744a;
            int i14 = i12 + 1;
            if (d7 < dArr3[i14]) {
                double d8 = (d7 - dArr3[i12]) / (dArr3[i14] - dArr3[i12]);
                while (i7 < length2) {
                    double[][] dArr4 = this.f8745b;
                    dArr[i7] = (dArr4[i12][i7] * (1.0d - d8)) + (dArr4[i14][i7] * d8);
                    i7++;
                }
                return;
            }
            i12 = i14;
        }
    }

    @Override // p.b
    public void e(double d7, float[] fArr) {
        double[] dArr = this.f8744a;
        int length = dArr.length;
        int i7 = 0;
        int length2 = this.f8745b[0].length;
        if (this.f8747d) {
            if (d7 <= dArr[0]) {
                f(dArr[0], this.f8748e);
                for (int i8 = 0; i8 < length2; i8++) {
                    fArr[i8] = (float) (this.f8745b[0][i8] + ((d7 - this.f8744a[0]) * this.f8748e[i8]));
                }
                return;
            }
            int i9 = length - 1;
            if (d7 >= dArr[i9]) {
                f(dArr[i9], this.f8748e);
                while (i7 < length2) {
                    fArr[i7] = (float) (this.f8745b[i9][i7] + ((d7 - this.f8744a[i9]) * this.f8748e[i7]));
                    i7++;
                }
                return;
            }
        } else {
            if (d7 <= dArr[0]) {
                for (int i10 = 0; i10 < length2; i10++) {
                    fArr[i10] = (float) this.f8745b[0][i10];
                }
                return;
            }
            int i11 = length - 1;
            if (d7 >= dArr[i11]) {
                while (i7 < length2) {
                    fArr[i7] = (float) this.f8745b[i11][i7];
                    i7++;
                }
                return;
            }
        }
        int i12 = 0;
        while (i12 < length - 1) {
            if (d7 == this.f8744a[i12]) {
                for (int i13 = 0; i13 < length2; i13++) {
                    fArr[i13] = (float) this.f8745b[i12][i13];
                }
            }
            double[] dArr2 = this.f8744a;
            int i14 = i12 + 1;
            if (d7 < dArr2[i14]) {
                double d8 = (d7 - dArr2[i12]) / (dArr2[i14] - dArr2[i12]);
                while (i7 < length2) {
                    double[][] dArr3 = this.f8745b;
                    fArr[i7] = (float) ((dArr3[i12][i7] * (1.0d - d8)) + (dArr3[i14][i7] * d8));
                    i7++;
                }
                return;
            }
            i12 = i14;
        }
    }

    @Override // p.b
    public void f(double d7, double[] dArr) {
        double[] dArr2 = this.f8744a;
        int length = dArr2.length;
        int length2 = this.f8745b[0].length;
        if (d7 <= dArr2[0]) {
            d7 = dArr2[0];
        } else {
            int i7 = length - 1;
            if (d7 >= dArr2[i7]) {
                d7 = dArr2[i7];
            }
        }
        int i8 = 0;
        while (i8 < length - 1) {
            double[] dArr3 = this.f8744a;
            int i9 = i8 + 1;
            if (d7 <= dArr3[i9]) {
                double d8 = dArr3[i9] - dArr3[i8];
                double d9 = dArr3[i8];
                for (int i10 = 0; i10 < length2; i10++) {
                    double[][] dArr4 = this.f8745b;
                    dArr[i10] = (dArr4[i9][i10] - dArr4[i8][i10]) / d8;
                }
                return;
            }
            i8 = i9;
        }
    }

    @Override // p.b
    public double[] g() {
        return this.f8744a;
    }

    public double h(double d7, int i7) {
        double[] dArr = this.f8744a;
        int length = dArr.length;
        int i8 = 0;
        if (d7 < dArr[0]) {
            d7 = dArr[0];
        } else {
            int i9 = length - 1;
            if (d7 >= dArr[i9]) {
                d7 = dArr[i9];
            }
        }
        while (i8 < length - 1) {
            double[] dArr2 = this.f8744a;
            int i10 = i8 + 1;
            if (d7 <= dArr2[i10]) {
                double d8 = dArr2[i10] - dArr2[i8];
                double d9 = dArr2[i8];
                double[][] dArr3 = this.f8745b;
                return (dArr3[i10][i7] - dArr3[i8][i7]) / d8;
            }
            i8 = i10;
        }
        return 0.0d;
    }
}
