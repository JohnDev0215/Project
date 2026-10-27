package t;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[] f4966c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4967d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f4964a = new float[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[] f4965b = new double[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f4968e = 6.283185307179586d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4969f = false;

    public void a(double d3, float f3) {
        int length = this.f4964a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.f4965b, d3);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.f4965b = Arrays.copyOf(this.f4965b, length);
        this.f4964a = Arrays.copyOf(this.f4964a, length);
        this.f4966c = new double[length];
        double[] dArr = this.f4965b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.f4965b[iBinarySearch] = d3;
        this.f4964a[iBinarySearch] = f3;
        this.f4969f = false;
    }

    public double b(double d3) {
        if (d3 <= 0.0d) {
            d3 = 1.0E-5d;
        } else if (d3 >= 1.0d) {
            d3 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f4965b, d3);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i3 = (-iBinarySearch) - 1;
        float[] fArr = this.f4964a;
        int i4 = i3 - 1;
        double d4 = fArr[i3] - fArr[i4];
        double[] dArr = this.f4965b;
        double d5 = d4 / (dArr[i3] - dArr[i4]);
        return (((double) fArr[i4]) - (d5 * dArr[i4])) + (d3 * d5);
    }

    public double c(double d3) {
        if (d3 < 0.0d) {
            d3 = 0.0d;
        } else if (d3 > 1.0d) {
            d3 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f4965b, d3);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i3 = (-iBinarySearch) - 1;
        float[] fArr = this.f4964a;
        int i4 = i3 - 1;
        double d4 = fArr[i3] - fArr[i4];
        double[] dArr = this.f4965b;
        double d5 = d4 / (dArr[i3] - dArr[i4]);
        return this.f4966c[i4] + ((((double) fArr[i4]) - (dArr[i4] * d5)) * (d3 - dArr[i4])) + ((d5 * ((d3 * d3) - (dArr[i4] * dArr[i4]))) / 2.0d);
    }

    public double d(double d3) {
        double dB;
        double dSignum;
        double dB2;
        double dB3;
        double dSin;
        switch (this.f4967d) {
            case 1:
                return 0.0d;
            case 2:
                dB = b(d3) * 4.0d;
                dSignum = Math.signum((((c(d3) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dB * dSignum;
            case 3:
                dB2 = b(d3);
                return dB2 * 2.0d;
            case 4:
                dB2 = -b(d3);
                return dB2 * 2.0d;
            case 5:
                dB3 = (-this.f4968e) * b(d3);
                dSin = Math.sin(this.f4968e * c(d3));
                return dB3 * dSin;
            case 6:
                dB = b(d3) * 4.0d;
                dSignum = (((c(d3) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dB * dSignum;
            default:
                dB3 = this.f4968e * b(d3);
                dSin = Math.cos(this.f4968e * c(d3));
                return dB3 * dSin;
        }
    }

    public double e(double d3) {
        double dAbs;
        switch (this.f4967d) {
            case 1:
                return Math.signum(0.5d - (c(d3) % 1.0d));
            case 2:
                dAbs = Math.abs((((c(d3) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((c(d3) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((c(d3) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.f4968e * c(d3));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((c(d3) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.f4968e * c(d3));
        }
        return 1.0d - dAbs;
    }

    public void f() {
        int i3 = 0;
        double d3 = 0.0d;
        while (true) {
            float[] fArr = this.f4964a;
            if (i3 >= fArr.length) {
                break;
            }
            d3 += (double) fArr[i3];
            i3++;
        }
        double d4 = 0.0d;
        int i4 = 1;
        while (true) {
            float[] fArr2 = this.f4964a;
            if (i4 >= fArr2.length) {
                break;
            }
            int i5 = i4 - 1;
            float f3 = (fArr2[i5] + fArr2[i4]) / 2.0f;
            double[] dArr = this.f4965b;
            d4 += (dArr[i4] - dArr[i5]) * ((double) f3);
            i4++;
        }
        int i6 = 0;
        while (true) {
            float[] fArr3 = this.f4964a;
            if (i6 >= fArr3.length) {
                break;
            }
            fArr3[i6] = (float) (((double) fArr3[i6]) * (d3 / d4));
            i6++;
        }
        this.f4966c[0] = 0.0d;
        int i7 = 1;
        while (true) {
            float[] fArr4 = this.f4964a;
            if (i7 >= fArr4.length) {
                this.f4969f = true;
                return;
            }
            int i8 = i7 - 1;
            float f4 = (fArr4[i8] + fArr4[i7]) / 2.0f;
            double[] dArr2 = this.f4965b;
            double d5 = dArr2[i7] - dArr2[i8];
            double[] dArr3 = this.f4966c;
            dArr3[i7] = dArr3[i8] + (d5 * ((double) f4));
            i7++;
        }
    }

    public void g(int i3) {
        this.f4967d = i3;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f4965b) + " period=" + Arrays.toString(this.f4964a);
    }
}
