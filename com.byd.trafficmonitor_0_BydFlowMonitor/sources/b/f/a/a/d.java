package b.f.a.a;

/* JADX INFO: compiled from: LinearCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double[] f2004a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[][] f2005b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f2006c;

    public d(double[] dArr, double[][] dArr2) {
        this.f2006c = Double.NaN;
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.f2004a = dArr;
        this.f2005b = dArr2;
        if (length2 > 2) {
            int i2 = 0;
            double d2 = 0.0d;
            double d3 = 0.0d;
            while (i2 < dArr.length) {
                double d4 = dArr2[i2][0];
                double d5 = dArr2[i2][0];
                if (i2 > 0) {
                    Math.hypot(d4 - d2, d5 - d3);
                }
                i2++;
                d2 = d4;
                d3 = d5;
            }
            this.f2006c = 0.0d;
        }
    }

    @Override // b.f.a.a.b
    public double c(double d2, int i2) {
        double[] dArr = this.f2004a;
        int length = dArr.length;
        int i3 = 0;
        if (d2 <= dArr[0]) {
            return this.f2005b[0][i2];
        }
        int i4 = length - 1;
        if (d2 >= dArr[i4]) {
            return this.f2005b[i4][i2];
        }
        while (i3 < i4) {
            double[] dArr2 = this.f2004a;
            if (d2 == dArr2[i3]) {
                return this.f2005b[i3][i2];
            }
            int i5 = i3 + 1;
            if (d2 < dArr2[i5]) {
                double d3 = (d2 - dArr2[i3]) / (dArr2[i5] - dArr2[i3]);
                double[][] dArr3 = this.f2005b;
                return (dArr3[i3][i2] * (1.0d - d3)) + (dArr3[i5][i2] * d3);
            }
            i3 = i5;
        }
        return 0.0d;
    }

    @Override // b.f.a.a.b
    public void d(double d2, double[] dArr) {
        double[] dArr2 = this.f2004a;
        int length = dArr2.length;
        int i2 = 0;
        int length2 = this.f2005b[0].length;
        if (d2 <= dArr2[0]) {
            for (int i3 = 0; i3 < length2; i3++) {
                dArr[i3] = this.f2005b[0][i3];
            }
            return;
        }
        int i4 = length - 1;
        if (d2 >= dArr2[i4]) {
            while (i2 < length2) {
                dArr[i2] = this.f2005b[i4][i2];
                i2++;
            }
            return;
        }
        int i5 = 0;
        while (i5 < i4) {
            if (d2 == this.f2004a[i5]) {
                for (int i6 = 0; i6 < length2; i6++) {
                    dArr[i6] = this.f2005b[i5][i6];
                }
            }
            double[] dArr3 = this.f2004a;
            int i7 = i5 + 1;
            if (d2 < dArr3[i7]) {
                double d3 = (d2 - dArr3[i5]) / (dArr3[i7] - dArr3[i5]);
                while (i2 < length2) {
                    double[][] dArr4 = this.f2005b;
                    dArr[i2] = (dArr4[i5][i2] * (1.0d - d3)) + (dArr4[i7][i2] * d3);
                    i2++;
                }
                return;
            }
            i5 = i7;
        }
    }

    @Override // b.f.a.a.b
    public void e(double d2, float[] fArr) {
        double[] dArr = this.f2004a;
        int length = dArr.length;
        int i2 = 0;
        int length2 = this.f2005b[0].length;
        if (d2 <= dArr[0]) {
            for (int i3 = 0; i3 < length2; i3++) {
                fArr[i3] = (float) this.f2005b[0][i3];
            }
            return;
        }
        int i4 = length - 1;
        if (d2 >= dArr[i4]) {
            while (i2 < length2) {
                fArr[i2] = (float) this.f2005b[i4][i2];
                i2++;
            }
            return;
        }
        int i5 = 0;
        while (i5 < i4) {
            if (d2 == this.f2004a[i5]) {
                for (int i6 = 0; i6 < length2; i6++) {
                    fArr[i6] = (float) this.f2005b[i5][i6];
                }
            }
            double[] dArr2 = this.f2004a;
            int i7 = i5 + 1;
            if (d2 < dArr2[i7]) {
                double d3 = (d2 - dArr2[i5]) / (dArr2[i7] - dArr2[i5]);
                while (i2 < length2) {
                    double[][] dArr3 = this.f2005b;
                    fArr[i2] = (float) ((dArr3[i5][i2] * (1.0d - d3)) + (dArr3[i7][i2] * d3));
                    i2++;
                }
                return;
            }
            i5 = i7;
        }
    }

    @Override // b.f.a.a.b
    public double f(double d2, int i2) {
        double[] dArr = this.f2004a;
        int length = dArr.length;
        int i3 = 0;
        if (d2 < dArr[0]) {
            d2 = dArr[0];
        } else {
            int i4 = length - 1;
            if (d2 >= dArr[i4]) {
                d2 = dArr[i4];
            }
        }
        while (i3 < length - 1) {
            double[] dArr2 = this.f2004a;
            int i5 = i3 + 1;
            if (d2 <= dArr2[i5]) {
                double d3 = dArr2[i5] - dArr2[i3];
                double d4 = dArr2[i3];
                double[][] dArr3 = this.f2005b;
                return (dArr3[i5][i2] - dArr3[i3][i2]) / d3;
            }
            i3 = i5;
        }
        return 0.0d;
    }

    @Override // b.f.a.a.b
    public void g(double d2, double[] dArr) {
        double[] dArr2 = this.f2004a;
        int length = dArr2.length;
        int length2 = this.f2005b[0].length;
        if (d2 <= dArr2[0]) {
            d2 = dArr2[0];
        } else {
            int i2 = length - 1;
            if (d2 >= dArr2[i2]) {
                d2 = dArr2[i2];
            }
        }
        int i3 = 0;
        while (i3 < length - 1) {
            double[] dArr3 = this.f2004a;
            int i4 = i3 + 1;
            if (d2 <= dArr3[i4]) {
                double d3 = dArr3[i4] - dArr3[i3];
                double d4 = dArr3[i3];
                for (int i5 = 0; i5 < length2; i5++) {
                    double[][] dArr4 = this.f2005b;
                    dArr[i5] = (dArr4[i4][i5] - dArr4[i3][i5]) / d3;
                }
                return;
            }
            i3 = i4;
        }
    }

    @Override // b.f.a.a.b
    public double[] h() {
        return this.f2004a;
    }
}
