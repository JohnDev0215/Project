package p;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    double[] f8756c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    String f8757d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    g f8758e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f8759f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    float[] f8754a = new float[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    double[] f8755b = new double[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    double f8760g = 6.283185307179586d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f8761h = false;

    public void a(double d7, float f7) {
        int length = this.f8754a.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.f8755b, d7);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.f8755b = Arrays.copyOf(this.f8755b, length);
        this.f8754a = Arrays.copyOf(this.f8754a, length);
        this.f8756c = new double[length];
        double[] dArr = this.f8755b;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.f8755b[iBinarySearch] = d7;
        this.f8754a[iBinarySearch] = f7;
        this.f8761h = false;
    }

    double b(double d7) {
        if (d7 < 0.0d) {
            d7 = 0.0d;
        } else if (d7 > 1.0d) {
            d7 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.f8755b, d7);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i7 = (-iBinarySearch) - 1;
        float[] fArr = this.f8754a;
        int i8 = i7 - 1;
        double d8 = fArr[i7] - fArr[i8];
        double[] dArr = this.f8755b;
        double d9 = d8 / (dArr[i7] - dArr[i8]);
        return this.f8756c[i8] + ((((double) fArr[i8]) - (dArr[i8] * d9)) * (d7 - dArr[i8])) + ((d9 * ((d7 * d7) - (dArr[i8] * dArr[i8]))) / 2.0d);
    }

    public double c(double d7, double d8) {
        double dAbs;
        double dB = b(d7) + d8;
        switch (this.f8759f) {
            case 1:
                return Math.signum(0.5d - (dB % 1.0d));
            case 2:
                dAbs = Math.abs((((dB * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((dB * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((dB * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.f8760g * (d8 + dB));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((dB * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            case 7:
                return this.f8758e.c(dB % 1.0d, 0);
            default:
                return Math.sin(this.f8760g * dB);
        }
        return 1.0d - dAbs;
    }

    public void d() {
        int i7 = 0;
        double d7 = 0.0d;
        while (true) {
            float[] fArr = this.f8754a;
            if (i7 >= fArr.length) {
                break;
            }
            d7 += (double) fArr[i7];
            i7++;
        }
        double d8 = 0.0d;
        int i8 = 1;
        while (true) {
            float[] fArr2 = this.f8754a;
            if (i8 >= fArr2.length) {
                break;
            }
            int i9 = i8 - 1;
            float f7 = (fArr2[i9] + fArr2[i8]) / 2.0f;
            double[] dArr = this.f8755b;
            d8 += (dArr[i8] - dArr[i9]) * ((double) f7);
            i8++;
        }
        int i10 = 0;
        while (true) {
            float[] fArr3 = this.f8754a;
            if (i10 >= fArr3.length) {
                break;
            }
            fArr3[i10] = (float) (((double) fArr3[i10]) * (d7 / d8));
            i10++;
        }
        this.f8756c[0] = 0.0d;
        int i11 = 1;
        while (true) {
            float[] fArr4 = this.f8754a;
            if (i11 >= fArr4.length) {
                this.f8761h = true;
                return;
            }
            int i12 = i11 - 1;
            float f8 = (fArr4[i12] + fArr4[i11]) / 2.0f;
            double[] dArr2 = this.f8755b;
            double d9 = dArr2[i11] - dArr2[i12];
            double[] dArr3 = this.f8756c;
            dArr3[i11] = dArr3[i12] + (d9 * ((double) f8));
            i11++;
        }
    }

    public void e(int i7, String str) {
        this.f8759f = i7;
        this.f8757d = str;
        if (str != null) {
            this.f8758e = g.h(str);
        }
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.f8755b) + " period=" + Arrays.toString(this.f8754a);
    }
}
