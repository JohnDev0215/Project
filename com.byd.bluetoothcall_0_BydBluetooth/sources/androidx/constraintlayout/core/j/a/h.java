package androidx.constraintlayout.core.j.a;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    double[] f779c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f780d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    g f781e;
    int f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f777a = new float[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    double[] f778b = new double[0];
    double g = 6.283185307179586d;
    private boolean h = false;

    public void a(double d2, float f) {
        int length = this.f777a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.f778b, d2);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.f778b = Arrays.copyOf(this.f778b, length);
        this.f777a = Arrays.copyOf(this.f777a, length);
        this.f779c = new double[length];
        double[] dArr = this.f778b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.f778b[iBinarySearch] = d2;
        this.f777a[iBinarySearch] = f;
        this.h = false;
    }

    double b(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f778b, d2);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.f777a;
        int i2 = i - 1;
        double d3 = fArr[i] - fArr[i2];
        double[] dArr = this.f778b;
        double d4 = d3 / (dArr[i] - dArr[i2]);
        return (((double) fArr[i2]) - (d4 * dArr[i2])) + (d2 * d4);
    }

    double c(double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f778b, d2);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.f777a;
        int i2 = i - 1;
        double d3 = fArr[i] - fArr[i2];
        double[] dArr = this.f778b;
        double d4 = d3 / (dArr[i] - dArr[i2]);
        return this.f779c[i2] + ((((double) fArr[i2]) - (dArr[i2] * d4)) * (d2 - dArr[i2])) + ((d4 * ((d2 * d2) - (dArr[i2] * dArr[i2]))) / 2.0d);
    }

    public double d(double d2, double d3, double d4) {
        double dC = d3 + c(d2);
        double dB = b(d2) + d4;
        switch (this.f) {
            case 1:
                return 0.0d;
            case 2:
                return dB * 4.0d * Math.signum((((dC * 4.0d) + 3.0d) % 4.0d) - 2.0d);
            case 3:
                return dB * 2.0d;
            case 4:
                return (-dB) * 2.0d;
            case 5:
                double d5 = this.g;
                return (-d5) * dB * Math.sin(d5 * dC);
            case 6:
                return dB * 4.0d * ((((dC * 4.0d) + 2.0d) % 4.0d) - 2.0d);
            case 7:
                return this.f781e.f(dC % 1.0d, 0);
            default:
                double d6 = this.g;
                return dB * d6 * Math.cos(d6 * dC);
        }
    }

    public double e(double d2, double d3) {
        double dAbs;
        double dC = c(d2) + d3;
        switch (this.f) {
            case 1:
                return Math.signum(0.5d - (dC % 1.0d));
            case 2:
                dAbs = Math.abs((((dC * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((dC * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((dC * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.g * (d3 + dC));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((dC * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            case 7:
                return this.f781e.c(dC % 1.0d, 0);
            default:
                return Math.sin(this.g * dC);
        }
        return 1.0d - dAbs;
    }

    public void f() {
        int i = 0;
        double d2 = 0.0d;
        while (true) {
            float[] fArr = this.f777a;
            if (i >= fArr.length) {
                break;
            }
            d2 += (double) fArr[i];
            i++;
        }
        double d3 = 0.0d;
        int i2 = 1;
        while (true) {
            float[] fArr2 = this.f777a;
            if (i2 >= fArr2.length) {
                break;
            }
            int i3 = i2 - 1;
            float f = (fArr2[i3] + fArr2[i2]) / 2.0f;
            double[] dArr = this.f778b;
            d3 += (dArr[i2] - dArr[i3]) * ((double) f);
            i2++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr3 = this.f777a;
            if (i4 >= fArr3.length) {
                break;
            }
            fArr3[i4] = (float) (((double) fArr3[i4]) * (d2 / d3));
            i4++;
        }
        this.f779c[0] = 0.0d;
        int i5 = 1;
        while (true) {
            float[] fArr4 = this.f777a;
            if (i5 >= fArr4.length) {
                this.h = true;
                return;
            }
            int i6 = i5 - 1;
            float f2 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr2 = this.f778b;
            double d4 = dArr2[i5] - dArr2[i6];
            double[] dArr3 = this.f779c;
            dArr3[i5] = dArr3[i6] + (d4 * ((double) f2));
            i5++;
        }
    }

    public void g(int i, String str) {
        this.f = i;
        this.f780d = str;
        if (str != null) {
            this.f781e = g.i(str);
        }
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f778b) + " period=" + Arrays.toString(this.f777a);
    }
}
