package b.e.a.a;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[] f1214c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1215d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f1212a = new float[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[] f1213b = new double[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f1216e = 6.283185307179586d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1217f = false;

    public void a(double d2, float f2) {
        int length = this.f1212a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.f1213b, d2);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.f1213b = Arrays.copyOf(this.f1213b, length);
        this.f1212a = Arrays.copyOf(this.f1212a, length);
        this.f1214c = new double[length];
        double[] dArr = this.f1213b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.f1213b[iBinarySearch] = d2;
        this.f1212a[iBinarySearch] = f2;
        this.f1217f = false;
    }

    public double b(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f1213b, d2);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i2 = (-iBinarySearch) - 1;
        float[] fArr = this.f1212a;
        int i3 = i2 - 1;
        double d3 = fArr[i2] - fArr[i3];
        double[] dArr = this.f1213b;
        double d4 = d3 / (dArr[i2] - dArr[i3]);
        return (((double) fArr[i3]) - (d4 * dArr[i3])) + (d2 * d4);
    }

    public double c(double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f1213b, d2);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i2 = (-iBinarySearch) - 1;
        float[] fArr = this.f1212a;
        int i3 = i2 - 1;
        double d3 = fArr[i2] - fArr[i3];
        double[] dArr = this.f1213b;
        double d4 = d3 / (dArr[i2] - dArr[i3]);
        return this.f1214c[i3] + ((((double) fArr[i3]) - (dArr[i3] * d4)) * (d2 - dArr[i3])) + ((d4 * ((d2 * d2) - (dArr[i3] * dArr[i3]))) / 2.0d);
    }

    public double d(double d2) {
        double dB;
        double dSignum;
        double dB2;
        double dB3;
        double dSin;
        switch (this.f1215d) {
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
                dB3 = (-this.f1216e) * b(d2);
                dSin = Math.sin(this.f1216e * c(d2));
                return dB3 * dSin;
            case 6:
                dB = b(d2) * 4.0d;
                dSignum = (((c(d2) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dB * dSignum;
            default:
                dB3 = this.f1216e * b(d2);
                dSin = Math.cos(this.f1216e * c(d2));
                return dB3 * dSin;
        }
    }

    public double e(double d2) {
        double dAbs;
        switch (this.f1215d) {
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
                return Math.cos(this.f1216e * c(d2));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((c(d2) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.f1216e * c(d2));
        }
        return 1.0d - dAbs;
    }

    public void f() {
        int i2 = 0;
        double d2 = 0.0d;
        while (true) {
            float[] fArr = this.f1212a;
            if (i2 >= fArr.length) {
                break;
            }
            d2 += (double) fArr[i2];
            i2++;
        }
        double d3 = 0.0d;
        int i3 = 1;
        while (true) {
            float[] fArr2 = this.f1212a;
            if (i3 >= fArr2.length) {
                break;
            }
            int i4 = i3 - 1;
            float f2 = (fArr2[i4] + fArr2[i3]) / 2.0f;
            double[] dArr = this.f1213b;
            d3 += (dArr[i3] - dArr[i4]) * ((double) f2);
            i3++;
        }
        int i5 = 0;
        while (true) {
            float[] fArr3 = this.f1212a;
            if (i5 >= fArr3.length) {
                break;
            }
            fArr3[i5] = (float) (((double) fArr3[i5]) * (d2 / d3));
            i5++;
        }
        this.f1214c[0] = 0.0d;
        int i6 = 1;
        while (true) {
            float[] fArr4 = this.f1212a;
            if (i6 >= fArr4.length) {
                this.f1217f = true;
                return;
            }
            int i7 = i6 - 1;
            float f3 = (fArr4[i7] + fArr4[i6]) / 2.0f;
            double[] dArr2 = this.f1213b;
            double d4 = dArr2[i6] - dArr2[i7];
            double[] dArr3 = this.f1214c;
            dArr3[i6] = dArr3[i7] + (d4 * ((double) f3));
            i6++;
        }
    }

    public void g(int i2) {
        this.f1215d = i2;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f1213b) + " period=" + Arrays.toString(this.f1212a);
    }
}
