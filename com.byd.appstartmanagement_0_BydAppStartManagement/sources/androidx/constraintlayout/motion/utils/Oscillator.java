package androidx.constraintlayout.motion.utils;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Oscillator {
    public static final int BOUNCE = 6;
    public static final int COS_WAVE = 5;
    public static final int REVERSE_SAW_WAVE = 4;
    public static final int SAW_WAVE = 3;
    public static final int SIN_WAVE = 0;
    public static final int SQUARE_WAVE = 1;
    public static String TAG = "Oscillator";
    public static final int TRIANGLE_WAVE = 2;
    public double[] mArea;
    public int mType;
    public float[] mPeriod = new float[0];
    public double[] mPosition = new double[0];
    public double PI2 = 6.283185307179586d;
    private boolean mNormalized = false;

    public void addPoint(double d2, float f) {
        int length = this.mPeriod.length + 1;
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        this.mPosition = Arrays.copyOf(this.mPosition, length);
        this.mPeriod = Arrays.copyOf(this.mPeriod, length);
        this.mArea = new double[length];
        double[] dArr = this.mPosition;
        System.arraycopy(dArr, iBinarySearch, dArr, iBinarySearch + 1, (length - iBinarySearch) - 1);
        this.mPosition[iBinarySearch] = d2;
        this.mPeriod[iBinarySearch] = f;
        this.mNormalized = false;
    }

    public double getDP(double d2) {
        if (d2 <= 0.0d) {
            d2 = 1.0E-5d;
        } else if (d2 >= 1.0d) {
            d2 = 0.999999d;
        }
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (iBinarySearch > 0 || iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.mPeriod;
        int i2 = i - 1;
        double d3 = fArr[i] - fArr[i2];
        double[] dArr = this.mPosition;
        double d4 = d3 / (dArr[i] - dArr[i2]);
        return (((double) fArr[i2]) - (d4 * dArr[i2])) + (d2 * d4);
    }

    public double getP(double d2) {
        if (d2 < 0.0d) {
            d2 = 0.0d;
        } else if (d2 > 1.0d) {
            d2 = 1.0d;
        }
        int iBinarySearch = Arrays.binarySearch(this.mPosition, d2);
        if (iBinarySearch > 0) {
            return 1.0d;
        }
        if (iBinarySearch == 0) {
            return 0.0d;
        }
        int i = (-iBinarySearch) - 1;
        float[] fArr = this.mPeriod;
        int i2 = i - 1;
        double d3 = fArr[i] - fArr[i2];
        double[] dArr = this.mPosition;
        double d4 = d3 / (dArr[i] - dArr[i2]);
        return this.mArea[i2] + ((((double) fArr[i2]) - (dArr[i2] * d4)) * (d2 - dArr[i2])) + ((d4 * ((d2 * d2) - (dArr[i2] * dArr[i2]))) / 2.0d);
    }

    public double getSlope(double d2) {
        double dp;
        double dSignum;
        double dp2;
        double dp3;
        double dSin;
        switch (this.mType) {
            case 1:
                return 0.0d;
            case 2:
                dp = getDP(d2) * 4.0d;
                dSignum = Math.signum((((getP(d2) * 4.0d) + 3.0d) % 4.0d) - 2.0d);
                return dp * dSignum;
            case 3:
                dp2 = getDP(d2);
                return dp2 * 2.0d;
            case 4:
                dp2 = -getDP(d2);
                return dp2 * 2.0d;
            case 5:
                dp3 = (-this.PI2) * getDP(d2);
                dSin = Math.sin(this.PI2 * getP(d2));
                return dp3 * dSin;
            case 6:
                dp = getDP(d2) * 4.0d;
                dSignum = (((getP(d2) * 4.0d) + 2.0d) % 4.0d) - 2.0d;
                return dp * dSignum;
            default:
                dp3 = this.PI2 * getDP(d2);
                dSin = Math.cos(this.PI2 * getP(d2));
                return dp3 * dSin;
        }
    }

    public double getValue(double d2) {
        double dAbs;
        switch (this.mType) {
            case 1:
                return Math.signum(0.5d - (getP(d2) % 1.0d));
            case 2:
                dAbs = Math.abs((((getP(d2) * 4.0d) + 1.0d) % 4.0d) - 2.0d);
                break;
            case 3:
                return (((getP(d2) * 2.0d) + 1.0d) % 2.0d) - 1.0d;
            case 4:
                dAbs = ((getP(d2) * 2.0d) + 1.0d) % 2.0d;
                break;
            case 5:
                return Math.cos(this.PI2 * getP(d2));
            case 6:
                double dAbs2 = 1.0d - Math.abs(((getP(d2) * 4.0d) % 4.0d) - 2.0d);
                dAbs = dAbs2 * dAbs2;
                break;
            default:
                return Math.sin(this.PI2 * getP(d2));
        }
        return 1.0d - dAbs;
    }

    public void normalize() {
        int i = 0;
        double d2 = 0.0d;
        while (true) {
            float[] fArr = this.mPeriod;
            if (i >= fArr.length) {
                break;
            }
            d2 += (double) fArr[i];
            i++;
        }
        double d3 = 0.0d;
        int i2 = 1;
        while (true) {
            float[] fArr2 = this.mPeriod;
            if (i2 >= fArr2.length) {
                break;
            }
            int i3 = i2 - 1;
            float f = (fArr2[i3] + fArr2[i2]) / 2.0f;
            double[] dArr = this.mPosition;
            d3 += (dArr[i2] - dArr[i3]) * ((double) f);
            i2++;
        }
        int i4 = 0;
        while (true) {
            float[] fArr3 = this.mPeriod;
            if (i4 >= fArr3.length) {
                break;
            }
            fArr3[i4] = (float) (((double) fArr3[i4]) * (d2 / d3));
            i4++;
        }
        this.mArea[0] = 0.0d;
        int i5 = 1;
        while (true) {
            float[] fArr4 = this.mPeriod;
            if (i5 >= fArr4.length) {
                this.mNormalized = true;
                return;
            }
            int i6 = i5 - 1;
            float f2 = (fArr4[i6] + fArr4[i5]) / 2.0f;
            double[] dArr2 = this.mPosition;
            double d4 = dArr2[i5] - dArr2[i6];
            double[] dArr3 = this.mArea;
            dArr3[i5] = dArr3[i6] + (d4 * ((double) f2));
            i5++;
        }
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String toString() {
        return "pos =" + Arrays.toString(this.mPosition) + " period=" + Arrays.toString(this.mPeriod);
    }
}
