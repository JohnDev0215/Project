package defpackage;

/* JADX INFO: compiled from: LinearCurveFit.java */
/* JADX INFO: loaded from: classes.dex */
public final class h71 extends l20 {
    public final double[] a;
    public final double[][] b;
    public final double[] c;

    public h71(double[] dArr, double[][] dArr2) {
        int length = dArr2[0].length;
        this.c = new double[length];
        this.a = dArr;
        this.b = dArr2;
        if (length > 2) {
            double d = 0.0d;
            int i = 0;
            while (i < dArr.length) {
                double d2 = dArr2[i][0];
                if (i > 0) {
                    double d3 = d2 - d;
                    Math.hypot(d3, d3);
                }
                i++;
                d = d2;
            }
        }
    }

    @Override // defpackage.l20
    public final double b(double d) {
        double[] dArr = this.a;
        int length = dArr.length;
        double d2 = dArr[0];
        double[][] dArr2 = this.b;
        if (d <= d2) {
            return (e(d2) * (d - d2)) + dArr2[0][0];
        }
        int i = length - 1;
        double d3 = dArr[i];
        if (d >= d3) {
            return (e(d3) * (d - d3)) + dArr2[i][0];
        }
        int i2 = 0;
        while (i2 < i) {
            double d4 = dArr[i2];
            if (d == d4) {
                return dArr2[i2][0];
            }
            int i3 = i2 + 1;
            double d5 = dArr[i3];
            if (d < d5) {
                double d6 = (d - d4) / (d5 - d4);
                return (dArr2[i3][0] * d6) + ((1.0d - d6) * dArr2[i2][0]);
            }
            i2 = i3;
        }
        return 0.0d;
    }

    @Override // defpackage.l20
    public final void c(double d, double[] dArr) {
        double[] dArr2 = this.a;
        int length = dArr2.length;
        double[][] dArr3 = this.b;
        int i = 0;
        int length2 = dArr3[0].length;
        double d2 = dArr2[0];
        double[] dArr4 = this.c;
        if (d <= d2) {
            f(d2, dArr4);
            for (int i2 = 0; i2 < length2; i2++) {
                dArr[i2] = ((d - dArr2[0]) * dArr4[i2]) + dArr3[0][i2];
            }
            return;
        }
        int i3 = length - 1;
        double d3 = dArr2[i3];
        if (d >= d3) {
            f(d3, dArr4);
            while (i < length2) {
                dArr[i] = ((d - dArr2[i3]) * dArr4[i]) + dArr3[i3][i];
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < length - 1) {
            if (d == dArr2[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    dArr[i5] = dArr3[i4][i5];
                }
            }
            int i6 = i4 + 1;
            double d4 = dArr2[i6];
            if (d < d4) {
                double d5 = dArr2[i4];
                double d6 = (d - d5) / (d4 - d5);
                while (i < length2) {
                    dArr[i] = (dArr3[i6][i] * d6) + ((1.0d - d6) * dArr3[i4][i]);
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    @Override // defpackage.l20
    public final void d(double d, float[] fArr) {
        double[] dArr = this.a;
        int length = dArr.length;
        double[][] dArr2 = this.b;
        int i = 0;
        int length2 = dArr2[0].length;
        double d2 = dArr[0];
        double[] dArr3 = this.c;
        if (d <= d2) {
            f(d2, dArr3);
            for (int i2 = 0; i2 < length2; i2++) {
                fArr[i2] = (float) (((d - dArr[0]) * dArr3[i2]) + dArr2[0][i2]);
            }
            return;
        }
        int i3 = length - 1;
        double d3 = dArr[i3];
        if (d >= d3) {
            f(d3, dArr3);
            while (i < length2) {
                fArr[i] = (float) (((d - dArr[i3]) * dArr3[i]) + dArr2[i3][i]);
                i++;
            }
            return;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (d == dArr[i4]) {
                for (int i5 = 0; i5 < length2; i5++) {
                    fArr[i5] = (float) dArr2[i4][i5];
                }
            }
            int i6 = i4 + 1;
            double d4 = dArr[i6];
            if (d < d4) {
                double d5 = dArr[i4];
                double d6 = (d - d5) / (d4 - d5);
                while (i < length2) {
                    fArr[i] = (float) ((dArr2[i6][i] * d6) + ((1.0d - d6) * dArr2[i4][i]));
                    i++;
                }
                return;
            }
            i4 = i6;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000a A[PHI: r3
  0x000a: PHI (r3v6 double) = (r3v0 double), (r3v2 double) binds: [B:3:0x0008, B:6:0x0012] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.l20
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double e(double r9) {
        /*
            r8 = this;
            double[] r0 = r8.a
            int r1 = r0.length
            r2 = 0
            r3 = r0[r2]
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 >= 0) goto Lc
        La:
            r9 = r3
            goto L15
        Lc:
            int r3 = r1 + (-1)
            r3 = r0[r3]
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 < 0) goto L15
            goto La
        L15:
            r3 = r2
        L16:
            int r4 = r1 + (-1)
            if (r3 >= r4) goto L34
            int r4 = r3 + 1
            r5 = r0[r4]
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 > 0) goto L32
            r9 = r0[r3]
            double r5 = r5 - r9
            double[][] r8 = r8.b
            r9 = r8[r3]
            r9 = r9[r2]
            r8 = r8[r4]
            r0 = r8[r2]
            double r0 = r0 - r9
            double r0 = r0 / r5
            return r0
        L32:
            r3 = r4
            goto L16
        L34:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.h71.e(double):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x000f A[PHI: r4
  0x000f: PHI (r4v6 double) = (r4v0 double), (r4v2 double) binds: [B:3:0x000d, B:6:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // defpackage.l20
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(double r10, double[] r12) {
        /*
            r9 = this;
            double[] r0 = r9.a
            int r1 = r0.length
            double[][] r9 = r9.b
            r2 = 0
            r3 = r9[r2]
            int r3 = r3.length
            r4 = r0[r2]
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 > 0) goto L11
        Lf:
            r10 = r4
            goto L1a
        L11:
            int r4 = r1 + (-1)
            r4 = r0[r4]
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 < 0) goto L1a
            goto Lf
        L1a:
            r4 = r2
        L1b:
            int r5 = r1 + (-1)
            if (r4 >= r5) goto L3d
            int r5 = r4 + 1
            r6 = r0[r5]
            int r8 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r8 > 0) goto L3b
            r10 = r0[r4]
            double r6 = r6 - r10
        L2a:
            if (r2 >= r3) goto L3d
            r10 = r9[r4]
            r10 = r10[r2]
            r0 = r9[r5]
            r0 = r0[r2]
            double r0 = r0 - r10
            double r0 = r0 / r6
            r12[r2] = r0
            int r2 = r2 + 1
            goto L2a
        L3b:
            r4 = r5
            goto L1b
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.h71.f(double, double[]):void");
    }

    @Override // defpackage.l20
    public final double[] g() {
        return this.a;
    }
}
