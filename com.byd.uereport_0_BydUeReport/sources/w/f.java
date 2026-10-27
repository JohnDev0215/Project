package w;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double[] f8641c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8642d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f8639a = new float[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double[] f8640b = new double[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f8643e = 6.283185307179586d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8644f = false;

    public void a(double d8, float f8) {
        int length = this.f8639a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.f8640b, d8);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.f8640b = Arrays.copyOf(this.f8640b, length);
        this.f8639a = Arrays.copyOf(this.f8639a, length);
        this.f8641c = new double[length];
        double[] dArr = this.f8640b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.f8640b[iBinarySearch] = d8;
        this.f8639a[iBinarySearch] = f8;
        this.f8644f = false;
    }

    public double b(double d8) {
        if (d8 <= 0.0d) {
            d8 = 1.0E-5d;
        } else if (d8 >= 1.0d) {
            d8 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f8640b, d8);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i8 = (-iBinarySearch) - 1;
        float[] fArr = this.f8639a;
        int i9 = i8 - 1;
        double d9 = fArr[i8] - fArr[i9];
        double[] dArr = this.f8640b;
        double d10 = d9 / (dArr[i8] - dArr[i9]);
        return (((double) fArr[i9]) - (d10 * dArr[i9])) + (d8 * d10);
    }

    public double c(double d8) {
        if (d8 < 0.0d) {
            d8 = 0.0d;
        } else if (d8 > 1.0d) {
            d8 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f8640b, d8);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i8 = (-iBinarySearch) - 1;
        float[] fArr = this.f8639a;
        int i9 = i8 - 1;
        double d9 = fArr[i8] - fArr[i9];
        double[] dArr = this.f8640b;
        double d10 = d9 / (dArr[i8] - dArr[i9]);
        return this.f8641c[i9] + ((((double) fArr[i9]) - (dArr[i9] * d10)) * (d8 - dArr[i9])) + ((d10 * ((d8 * d8) - (dArr[i9] * dArr[i9]))) / 2.0d);
    }

    public double d(double d8) {
        double dB;
        double dSignum;
        double dB2;
        double dB3;
        double dSin;
        switch (this.f8642d) {
            case 1:
                return 0.0d;
            case 2:
                dB = b(d8) * 4.0d;
                dSignum = Math.signum((((c(d8) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dB * dSignum;
            case 3:
                dB2 = b(d8);
                return dB2 * 2.0d;
            case 4:
                dB2 = -b(d8);
                return dB2 * 2.0d;
            case 5:
                dB3 = (-this.f8643e) * b(d8);
                dSin = Math.sin(this.f8643e * c(d8));
                return dB3 * dSin;
            case 6:
                dB = b(d8) * 4.0d;
                dSignum = (((c(d8) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dB * dSignum;
            default:
                dB3 = this.f8643e * b(d8);
                dSin = Math.cos(this.f8643e * c(d8));
                return dB3 * dSin;
        }
    }

    public double e(double d8) {
        double dAbs;
        switch (this.f8642d) {
            case 1:
                return Math.signum(0.5d - (c(d8) % 1.0d));
            case 2:
                dAbs = Math.abs((((c(d8) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((c(d8) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((c(d8) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.f8643e * c(d8));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((c(d8) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.f8643e * c(d8));
        }
        return 1.0d - dAbs;
    }

    public void f() {
        int i8 = 0;
        double d8 = 0.0d;
        while (true) {
            float[] fArr = this.f8639a;
            if (i8 >= fArr.length) {
                break;
            }
            d8 += (double) fArr[i8];
            i8++;
        }
        double d9 = 0.0d;
        int i9 = 1;
        while (true) {
            float[] fArr2 = this.f8639a;
            if (i9 >= fArr2.length) {
                break;
            }
            int i10 = i9 - 1;
            float f8 = (fArr2[i10] + fArr2[i9]) / 2.0f;
            double[] dArr = this.f8640b;
            d9 += (dArr[i9] - dArr[i10]) * ((double) f8);
            i9++;
        }
        int i11 = 0;
        while (true) {
            float[] fArr3 = this.f8639a;
            if (i11 >= fArr3.length) {
                break;
            }
            fArr3[i11] = (float) (((double) fArr3[i11]) * (d8 / d9));
            i11++;
        }
        this.f8641c[0] = 0.0d;
        int i12 = 1;
        while (true) {
            float[] fArr4 = this.f8639a;
            if (i12 >= fArr4.length) {
                this.f8644f = true;
                return;
            }
            int i13 = i12 - 1;
            float f9 = (fArr4[i13] + fArr4[i12]) / 2.0f;
            double[] dArr2 = this.f8640b;
            double d10 = dArr2[i12] - dArr2[i13];
            double[] dArr3 = this.f8641c;
            dArr3[i12] = dArr3[i13] + (d10 * ((double) f9));
            i12++;
        }
    }

    public void g(int i8) {
        this.f8642d = i8;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f8640b) + " period=" + Arrays.toString(this.f8639a);
    }
}
