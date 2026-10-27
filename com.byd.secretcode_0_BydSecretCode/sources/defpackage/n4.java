package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class n4 {
    public double[] c;
    public int d;
    public float[] a = new float[0];
    public double[] b = new double[0];
    public double e = 6.283185307179586d;
    public boolean f = false;

    public void a(double d, float f) {
        int length = this.a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.b, d);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.b = Arrays.copyOf(this.b, length);
        this.a = Arrays.copyOf(this.a, length);
        this.c = new double[length];
        double[] dArr = this.b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.b[iBinarySearch] = d;
        this.a[iBinarySearch] = f;
        this.f = false;
    }

    public double b(double d) {
        if (d <= 0.0d) {
            d = 1.0E-5d;
        } else if (d >= 1.0d) {
            d = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.b, d);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.a;
        int i2 = i - 1;
        double d2 = fArr[i] - fArr[i2];
        double[] dArr = this.b;
        double d3 = d2 / (dArr[i] - dArr[i2]);
        return (((double) fArr[i2]) - (d3 * dArr[i2])) + (d * d3);
    }

    public double c(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        } else if (d > 1.0d) {
            d = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.b, d);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.a;
        int i2 = i - 1;
        double d2 = fArr[i] - fArr[i2];
        double[] dArr = this.b;
        double d3 = d2 / (dArr[i] - dArr[i2]);
        return this.c[i2] + ((((double) fArr[i2]) - (dArr[i2] * d3)) * (d - dArr[i2])) + ((d3 * ((d * d) - (dArr[i2] * dArr[i2]))) / 2.0d);
    }

    public double d(double d) {
        double dB;
        double dSignum;
        double dB2;
        double dB3;
        double dSin;
        switch (this.d) {
            case 1:
                return 0.0d;
            case 2:
                dB = b(d) * 4.0d;
                dSignum = Math.signum((((c(d) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dB * dSignum;
            case 3:
                dB2 = b(d);
                return dB2 * 2.0d;
            case 4:
                dB2 = -b(d);
                return dB2 * 2.0d;
            case 5:
                dB3 = (-this.e) * b(d);
                dSin = Math.sin(this.e * c(d));
                return dB3 * dSin;
            case 6:
                dB = b(d) * 4.0d;
                dSignum = (((c(d) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dB * dSignum;
            default:
                dB3 = this.e * b(d);
                dSin = Math.cos(this.e * c(d));
                return dB3 * dSin;
        }
    }

    public double e(double d) {
        double dAbs;
        switch (this.d) {
            case 1:
                return Math.signum(0.5d - (c(d) % 1.0d));
            case 2:
                dAbs = Math.abs((((c(d) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((c(d) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((c(d) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.e * c(d));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((c(d) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.e * c(d));
        }
        return 1.0d - dAbs;
    }

    public void f() {
        int i = 0;
        double d = 0.0d;
        while (true) {
            float[] fArr = this.a;
            if (i >= fArr.length) {
                break;
            }
            d += (double) fArr[i];
            i++;
        }
        double d2 = 0.0d;
        int i2 = 1;
        while (true) {
            float[] fArr2 = this.a;
            if (i2 >= fArr2.length) {
                break;
            }
            int i3 = i2 - 1;
            float f = (fArr2[i3] + fArr2[i2]) / 2.0f;
            double[] dArr = this.b;
            d2 += (dArr[i2] - dArr[i3]) * ((double) f);
            i2++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr3 = this.a;
            if (i4 >= fArr3.length) {
                break;
            }
            fArr3[i4] = (float) (((double) fArr3[i4]) * (d / d2));
            i4++;
        }
        this.c[0] = 0.0d;
        int i5 = 1;
        while (true) {
            float[] fArr4 = this.a;
            if (i5 >= fArr4.length) {
                this.f = true;
                return;
            }
            int i6 = i5 - 1;
            float f2 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr2 = this.b;
            double d3 = dArr2[i5] - dArr2[i6];
            double[] dArr3 = this.c;
            dArr3[i5] = dArr3[i6] + (d3 * ((double) f2));
            i5++;
        }
    }

    public void g(int i) {
        this.d = i;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.b) + " period=" + Arrays.toString(this.a);
    }
}
