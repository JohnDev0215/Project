package androidx.constraintlayout.motion.utils;

/* JADX INFO: loaded from: classes.dex */
public class LinearCurveFit extends CurveFit {
    private static final String TAG = "LinearCurveFit";
    private double[] mT;
    private double mTotalLength;
    private double[][] mY;

    public LinearCurveFit(double[] dArr, double[][] dArr2) {
        this.mTotalLength = Double.NaN;
        int length = dArr.length;
        int length2 = dArr2[0].length;
        this.mT = dArr;
        this.mY = dArr2;
        if (length2 > 2) {
            int i = 0;
            double d2 = 0.0d;
            double d3 = 0.0d;
            while (i < dArr.length) {
                double d4 = dArr2[i][0];
                double d5 = dArr2[i][0];
                if (i > 0) {
                    Math.hypot(d4 - d2, d5 - d3);
                }
                i++;
                d2 = d4;
                d3 = d5;
            }
            this.mTotalLength = 0.0d;
        }
    }

    private double getLength2D(double d2) {
        if (Double.isNaN(this.mTotalLength)) {
            return 0.0d;
        }
        double[] dArr = this.mT;
        int length = dArr.length;
        if (d2 <= dArr[0]) {
            return 0.0d;
        }
        int i = length - 1;
        if (d2 >= dArr[i]) {
            return this.mTotalLength;
        }
        double dHypot = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i2 = 0;
        while (i2 < i) {
            double[][] dArr2 = this.mY;
            double d5 = dArr2[i2][0];
            double d6 = dArr2[i2][1];
            if (i2 > 0) {
                dHypot += Math.hypot(d5 - d3, d6 - d4);
            }
            double[] dArr3 = this.mT;
            if (d2 == dArr3[i2]) {
                return dHypot;
            }
            int i3 = i2 + 1;
            if (d2 < dArr3[i3]) {
                double d7 = (d2 - dArr3[i2]) / (dArr3[i3] - dArr3[i2]);
                double[][] dArr4 = this.mY;
                double d8 = 1.0d - d7;
                return dHypot + Math.hypot(d6 - ((dArr4[i2][1] * d8) + (dArr4[i3][1] * d7)), d5 - ((dArr4[i2][0] * d8) + (dArr4[i3][0] * d7)));
            }
            i2 = i3;
            d3 = d5;
            d4 = d6;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double d2, double[] dArr) {
        double[] dArr2 = this.mT;
        int length = dArr2.length;
        int i = 0;
        int length2 = this.mY[0].length;
        if (d2 <= dArr2[0]) {
            for (int i2 = 0; i2 < length2; i2++) {
                dArr[i2] = this.mY[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        if (d2 >= dArr2[i3]) {
            while (i < length2) {
                dArr[i] = this.mY[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d2 == this.mT[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    dArr[i5] = this.mY[i4][i5];
                }
            }
            double[] dArr3 = this.mT;
            int i6 = i4 + 1;
            if (d2 < dArr3[i6]) {
                double d3 = (d2 - dArr3[i4]) / (dArr3[i6] - dArr3[i4]);
                while (i < length2) {
                    double[][] dArr4 = this.mY;
                    dArr[i] = (dArr4[i4][i] * (1.0d - d3)) + (dArr4[i6][i] * d3);
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getSlope(double d2, double[] dArr) {
        double[] dArr2 = this.mT;
        int length = dArr2.length;
        int length2 = this.mY[0].length;
        if (d2 <= dArr2[0]) {
            d2 = dArr2[0];
        } else {
            int i = length - 1;
            if (d2 >= dArr2[i]) {
                d2 = dArr2[i];
            }
        }
        int i2 = 0;
        while (i2 < length - 1) {
            double[] dArr3 = this.mT;
            int i3 = i2 + 1;
            if (d2 <= dArr3[i3]) {
                double d3 = dArr3[i3] - dArr3[i2];
                double d4 = dArr3[i2];
                for (int i4 = 0; i4 < length2; i4++) {
                    double[][] dArr4 = this.mY;
                    dArr[i4] = (dArr4[i3][i4] - dArr4[i2][i4]) / d3;
                }
                return;
            }
            i2 = i3;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double[] getTimePoints() {
        return this.mT;
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getSlope(double d2, int i) {
        double[] dArr = this.mT;
        int length = dArr.length;
        int i2 = 0;
        if (d2 < dArr[0]) {
            d2 = dArr[0];
        } else {
            int i3 = length - 1;
            if (d2 >= dArr[i3]) {
                d2 = dArr[i3];
            }
        }
        while (i2 < length - 1) {
            double[] dArr2 = this.mT;
            int i4 = i2 + 1;
            if (d2 <= dArr2[i4]) {
                double d3 = dArr2[i4] - dArr2[i2];
                double d4 = dArr2[i2];
                double[][] dArr3 = this.mY;
                return (dArr3[i4][i] - dArr3[i2][i]) / d3;
            }
            i2 = i4;
        }
        return 0.0d;
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public void getPos(double d2, float[] fArr) {
        double[] dArr = this.mT;
        int length = dArr.length;
        int i = 0;
        int length2 = this.mY[0].length;
        if (d2 <= dArr[0]) {
            for (int i2 = 0; i2 < length2; i2++) {
                fArr[i2] = (float) this.mY[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        if (d2 >= dArr[i3]) {
            while (i < length2) {
                fArr[i] = (float) this.mY[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d2 == this.mT[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    fArr[i5] = (float) this.mY[i4][i5];
                }
            }
            double[] dArr2 = this.mT;
            int i6 = i4 + 1;
            if (d2 < dArr2[i6]) {
                double d3 = (d2 - dArr2[i4]) / (dArr2[i6] - dArr2[i4]);
                while (i < length2) {
                    double[][] dArr3 = this.mY;
                    fArr[i] = (float) ((dArr3[i4][i] * (1.0d - d3)) + (dArr3[i6][i] * d3));
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // androidx.constraintlayout.motion.utils.CurveFit
    public double getPos(double d2, int i) {
        double[] dArr = this.mT;
        int length = dArr.length;
        int i2 = 0;
        if (d2 <= dArr[0]) {
            return this.mY[0][i];
        }
        int i3 = length - 1;
        if (d2 >= dArr[i3]) {
            return this.mY[i3][i];
        }
        while (i2 < i3) {
            double[] dArr2 = this.mT;
            if (d2 == dArr2[i2]) {
                return this.mY[i2][i];
            }
            int i4 = i2 + 1;
            if (d2 < dArr2[i4]) {
                double d3 = (d2 - dArr2[i2]) / (dArr2[i4] - dArr2[i2]);
                double[][] dArr3 = this.mY;
                return (dArr3[i2][i] * (1.0d - d3)) + (dArr3[i4][i] * d3);
            }
            i2 = i4;
        }
        return 0.0d;
    }
}
