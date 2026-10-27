package defpackage;

import java.util.Arrays;

/* JADX INFO: compiled from: Oscillator.java */
/* JADX INFO: loaded from: classes.dex */
public final class gt1 {
    public float[] a = new float[0];
    public double[] b = new double[0];
    public double[] c;
    public mi1 d;
    public int e;

    public final void a(double d, float f) {
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
    }

    public final double b(double d) {
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
        float f = fArr[i];
        int i2 = i - 1;
        float f2 = fArr[i2];
        double d2 = f - f2;
        double[] dArr = this.b;
        double d3 = dArr[i];
        double d4 = dArr[i2];
        double d5 = d2 / (d3 - d4);
        return ((((d * d) - (d4 * d4)) * d5) / 2.0d) + ((d - d4) * (((double) f2) - (d5 * d4))) + this.c[i2];
    }

    public final double c(double d, double d2) {
        double dB = b(d) + d2;
        switch (this.e) {
            case 1:
                return Math.signum(0.5d - (dB % 1.0d));
            case 2:
                return 1.0d - Math.abs((((dB * 4.0d) + 1.0d) % 4.0d) - 2.0d);
            case 3:
                return (((dB * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                return 1.0d - (((dB * 2.0d) + 1.0d) % 2.0d);
            case 5:
                return Math.cos((d2 + dB) * 6.283185307179586d);
            case 6:
                double dAbs = 1.0d - Math.abs(((dB * 4.0d) % 4.0d) - 2.0d);
                return 1.0d - (dAbs * dAbs);
            case 7:
                return this.d.b(dB % 1.0d);
            default:
                return Math.sin(dB * 6.283185307179586d);
        }
    }

    public final String toString() {
        return "pos =" + Arrays.toString(this.b) + " period=" + Arrays.toString(this.a);
    }
}
