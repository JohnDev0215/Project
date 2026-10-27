package b.e.a.a;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    double[] f1793c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1794d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f1791a = new float[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    double[] f1792b = new double[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    double f1795e = 6.283185307179586d;
    private boolean f = false;

    public void a(double d2, float f) {
        int length = this.f1791a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.f1792b, d2);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.f1792b = Arrays.copyOf(this.f1792b, length);
        this.f1791a = Arrays.copyOf(this.f1791a, length);
        this.f1793c = new double[length];
        double[] dArr = this.f1792b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.f1792b[iBinarySearch] = d2;
        this.f1791a[iBinarySearch] = f;
        this.f = false;
    }

    double b(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f1792b, d2);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.f1791a;
        int i2 = i - 1;
        double d3 = fArr[i] - fArr[i2];
        double[] dArr = this.f1792b;
        double d4 = d3 / (dArr[i] - dArr[i2]);
        return (((double) fArr[i2]) - (d4 * dArr[i2])) + (d2 * d4);
    }

    double c(double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f1792b, d2);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.f1791a;
        int i2 = i - 1;
        double d3 = fArr[i] - fArr[i2];
        double[] dArr = this.f1792b;
        double d4 = d3 / (dArr[i] - dArr[i2]);
        return this.f1793c[i2] + ((((double) fArr[i2]) - (dArr[i2] * d4)) * (d2 - dArr[i2])) + ((d4 * ((d2 * d2) - (dArr[i2] * dArr[i2]))) / 2.0d);
    }

    public double d(double d2) {
        double dB;
        double dSignum;
        double dB2;
        double dB3;
        double dSin;
        switch (this.f1794d) {
            case 1:
                return 0.0d;
            case 2:
                dB = b(d2) * 4.0d;
                dSignum = Math.signum((((c(d2) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dB * dSignum;
            case 3:
                dB2 = b(d2);
                return dB2 * 2.0d;
            case 4:
                dB2 = -b(d2);
                return dB2 * 2.0d;
            case 5:
                dB3 = (-this.f1795e) * b(d2);
                dSin = Math.sin(this.f1795e * c(d2));
                return dB3 * dSin;
            case 6:
                dB = b(d2) * 4.0d;
                dSignum = (((c(d2) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dB * dSignum;
            default:
                dB3 = this.f1795e * b(d2);
                dSin = Math.cos(this.f1795e * c(d2));
                return dB3 * dSin;
        }
    }

    public double e(double d2) {
        double dAbs;
        switch (this.f1794d) {
            case 1:
                return Math.signum(0.5d - (c(d2) % 1.0d));
            case 2:
                dAbs = Math.abs((((c(d2) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((c(d2) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((c(d2) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.f1795e * c(d2));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((c(d2) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.f1795e * c(d2));
        }
        return 1.0d - dAbs;
    }

    public void f() {
        int i = 0;
        double d2 = 0.0d;
        while (true) {
            float[] fArr = this.f1791a;
            if (i >= fArr.length) {
                break;
            }
            d2 += (double) fArr[i];
            i++;
        }
        double d3 = 0.0d;
        int i2 = 1;
        while (true) {
            float[] fArr2 = this.f1791a;
            if (i2 >= fArr2.length) {
                break;
            }
            int i3 = i2 - 1;
            float f = (fArr2[i3] + fArr2[i2]) / 2.0f;
            double[] dArr = this.f1792b;
            d3 += (dArr[i2] - dArr[i3]) * ((double) f);
            i2++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr3 = this.f1791a;
            if (i4 >= fArr3.length) {
                break;
            }
            fArr3[i4] = (float) (((double) fArr3[i4]) * (d2 / d3));
            i4++;
        }
        this.f1793c[0] = 0.0d;
        int i5 = 1;
        while (true) {
            float[] fArr4 = this.f1791a;
            if (i5 >= fArr4.length) {
                this.f = true;
                return;
            }
            int i6 = i5 - 1;
            float f2 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr2 = this.f1792b;
            double d4 = dArr2[i5] - dArr2[i6];
            double[] dArr3 = this.f1793c;
            dArr3[i5] = dArr3[i6] + (d4 * ((double) f2));
            i5++;
        }
    }

    public void g(int i) {
        this.f1794d = i;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f1792b) + " period=" + Arrays.toString(this.f1791a);
    }
}
