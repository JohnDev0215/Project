package p;

import java.text.DecimalFormat;
import java.util.Arrays;

/* JADX INFO: compiled from: SplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected b f8764a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected int[] f8765b = new int[10];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected float[] f8766c = new float[10];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f8767d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f8768e;

    /* JADX INFO: compiled from: SplineSet.java */
    private static class a {
        static void a(int[] iArr, float[] fArr, int i7, int i8) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i8;
            iArr2[1] = i7;
            int i9 = 2;
            while (i9 > 0) {
                int i10 = i9 - 1;
                int i11 = iArr2[i10];
                i9 = i10 - 1;
                int i12 = iArr2[i9];
                if (i11 < i12) {
                    int iB = b(iArr, fArr, i11, i12);
                    int i13 = i9 + 1;
                    iArr2[i9] = iB - 1;
                    int i14 = i13 + 1;
                    iArr2[i13] = i11;
                    int i15 = i14 + 1;
                    iArr2[i14] = i12;
                    i9 = i15 + 1;
                    iArr2[i15] = iB + 1;
                }
            }
        }

        private static int b(int[] iArr, float[] fArr, int i7, int i8) {
            int i9 = iArr[i8];
            int i10 = i7;
            while (i7 < i8) {
                if (iArr[i7] <= i9) {
                    c(iArr, fArr, i10, i7);
                    i10++;
                }
                i7++;
            }
            c(iArr, fArr, i10, i8);
            return i10;
        }

        private static void c(int[] iArr, float[] fArr, int i7, int i8) {
            int i9 = iArr[i7];
            iArr[i7] = iArr[i8];
            iArr[i8] = i9;
            float f7 = fArr[i7];
            fArr[i7] = fArr[i8];
            fArr[i8] = f7;
        }
    }

    public float a(float f7) {
        return (float) this.f8764a.c(f7, 0);
    }

    public void b(int i7, float f7) {
        int[] iArr = this.f8765b;
        if (iArr.length < this.f8767d + 1) {
            this.f8765b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f8766c;
            this.f8766c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f8765b;
        int i8 = this.f8767d;
        iArr2[i8] = i7;
        this.f8766c[i8] = f7;
        this.f8767d = i8 + 1;
    }

    public void c(String str) {
        this.f8768e = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(int r10) {
        /*
            r9 = this;
            int r0 = r9.f8767d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r9.f8765b
            float[] r2 = r9.f8766c
            r3 = 1
            int r0 = r0 - r3
            r4 = 0
            p.j.a.a(r1, r2, r4, r0)
            r0 = r3
            r1 = r0
        L11:
            int r2 = r9.f8767d
            if (r0 >= r2) goto L24
            int[] r2 = r9.f8765b
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
            int r5 = r9.f8767d
            if (r2 >= r5) goto L63
            if (r2 <= 0) goto L48
            int[] r5 = r9.f8765b
            r6 = r5[r2]
            int r7 = r2 + (-1)
            r5 = r5[r7]
            if (r6 != r5) goto L48
            goto L60
        L48:
            int[] r5 = r9.f8765b
            r5 = r5[r2]
            double r5 = (double) r5
            r7 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r5 = r5 * r7
            r0[r3] = r5
            r5 = r1[r3]
            float[] r6 = r9.f8766c
            r6 = r6[r2]
            double r6 = (double) r6
            r5[r4] = r6
            int r3 = r3 + 1
        L60:
            int r2 = r2 + 1
            goto L37
        L63:
            p.b r10 = p.b.a(r10, r0, r1)
            r9.f8764a = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p.j.d(int):void");
    }

    public String toString() {
        String str = this.f8768e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i7 = 0; i7 < this.f8767d; i7++) {
            str = str + "[" + this.f8765b[i7] + " , " + decimalFormat.format(this.f8766c[i7]) + "] ";
        }
        return str;
    }
}
