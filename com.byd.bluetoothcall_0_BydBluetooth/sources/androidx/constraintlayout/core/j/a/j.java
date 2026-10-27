package androidx.constraintlayout.core.j.a;

import java.text.DecimalFormat;
import java.util.Arrays;

/* JADX INFO: compiled from: SplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected b f784a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f785b = new int[10];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected float[] f786c = new float[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f787d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f788e;

    /* JADX INFO: compiled from: SplineSet.java */
    private static class a {
        static void a(int[] iArr, float[] fArr, int i, int i2) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i2;
            iArr2[1] = i;
            int i3 = 2;
            while (i3 > 0) {
                int i4 = i3 - 1;
                int i5 = iArr2[i4];
                i3 = i4 - 1;
                int i6 = iArr2[i3];
                if (i5 < i6) {
                    int iB = b(iArr, fArr, i5, i6);
                    int i7 = i3 + 1;
                    iArr2[i3] = iB - 1;
                    int i8 = i7 + 1;
                    iArr2[i7] = i5;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    i3 = i9 + 1;
                    iArr2[i9] = iB + 1;
                }
            }
        }

        private static int b(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i2];
            int i4 = i;
            while (i < i2) {
                if (iArr[i] <= i3) {
                    c(iArr, fArr, i4, i);
                    i4++;
                }
                i++;
            }
            c(iArr, fArr, i4, i2);
            return i4;
        }

        private static void c(int[] iArr, float[] fArr, int i, int i2) {
            int i3 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i3;
            float f = fArr[i];
            fArr[i] = fArr[i2];
            fArr[i2] = f;
        }
    }

    public float a(float f) {
        return (float) this.f784a.c(f, 0);
    }

    public float b(float f) {
        return (float) this.f784a.f(f, 0);
    }

    public void c(int i, float f) {
        int[] iArr = this.f785b;
        if (iArr.length < this.f787d + 1) {
            this.f785b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f786c;
            this.f786c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f785b;
        int i2 = this.f787d;
        iArr2[i2] = i;
        this.f786c[i2] = f;
        this.f787d = i2 + 1;
    }

    public void d(String str) {
        this.f788e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e(int r10) {
        /*
            r9 = this;
            int r0 = r9.f787d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.f785b
            float[] r2 = r9.f786c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            androidx.constraintlayout.core.j.a.j.a.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.f787d
            if (r0 >= r2) goto L24
            int[] r2 = r9.f785b
            int r5 = r0 + (-1)
            r5 = r2[r5]
            r2 = r2[r0]
            if (r5 == r2) goto L21
            int r1 = r1 + 1
        L21:
            int r0 = r0 + 1
            goto L11
        L24:
            double[] r0 = new double[r1]
            r2 = 2
            int[] r2 = new int[r2]
            r2[r3] = r3
            r2[r4] = r1
            java.lang.Class<double> r1 = double.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)
            double[][] r1 = (double[][]) r1
            r2 = r4
            r3 = r2
        L37:
            int r5 = r9.f787d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.f785b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.f785b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.f786c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            androidx.constraintlayout.core.j.a.b r10 = androidx.constraintlayout.core.j.a.b.a(r10, r0, r1)
            r9.f784a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.j.a.j.e(int):void");
    }

    public String toString() {
        String str = this.f788e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i = 0; i < this.f787d; i++) {
            str = str + "[" + this.f785b[i] + " , " + decimalFormat.format(this.f786c[i]) + "] ";
        }
        return str;
    }
}
