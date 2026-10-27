package defpackage;

import java.text.DecimalFormat;
import java.util.Arrays;

/* JADX INFO: compiled from: SplineSet.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class wq2 {
    public l20 a;
    public int[] b = new int[10];
    public float[] c = new float[10];
    public int d;
    public String e;

    public final float a(float f) {
        return (float) this.a.b(f);
    }

    public void b(float f, int i) {
        int[] iArr = this.b;
        if (iArr.length < this.d + 1) {
            this.b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.c;
            this.c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.b;
        int i2 = this.d;
        iArr2[i2] = i;
        this.c[i2] = f;
        this.d = i2 + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(int r15) {
        /*
            r14 = this;
            int r0 = r14.d
            if (r0 != 0) goto L5
            return
        L5:
            int[] r1 = r14.b
            float[] r2 = r14.c
            r3 = 1
            int r0 = r0 - r3
            int r4 = r1.length
            int r4 = r4 + 10
            int[] r4 = new int[r4]
            r5 = 0
            r4[r5] = r0
            r4[r3] = r5
            r0 = 2
            r6 = r0
        L17:
            if (r6 <= 0) goto L65
            int r6 = r6 + (-1)
            r7 = r4[r6]
            int r6 = r6 + (-1)
            r8 = r4[r6]
            if (r7 >= r8) goto L17
            r9 = r1[r8]
            r10 = r7
            r11 = r10
        L27:
            if (r10 >= r8) goto L40
            r12 = r1[r10]
            if (r12 > r9) goto L3d
            r13 = r1[r11]
            r1[r11] = r12
            r1[r10] = r13
            r12 = r2[r11]
            r13 = r2[r10]
            r2[r11] = r13
            r2[r10] = r12
            int r11 = r11 + 1
        L3d:
            int r10 = r10 + 1
            goto L27
        L40:
            r9 = r1[r11]
            r10 = r1[r8]
            r1[r11] = r10
            r1[r8] = r9
            r9 = r2[r11]
            r10 = r2[r8]
            r2[r11] = r10
            r2[r8] = r9
            int r9 = r6 + 1
            int r10 = r11 + (-1)
            r4[r6] = r10
            int r6 = r9 + 1
            r4[r9] = r7
            int r7 = r6 + 1
            r4[r6] = r8
            int r6 = r7 + 1
            int r11 = r11 + 1
            r4[r7] = r11
            goto L17
        L65:
            r1 = r3
            r2 = r1
        L67:
            int r4 = r14.d
            if (r1 >= r4) goto L7a
            int[] r4 = r14.b
            int r6 = r1 + (-1)
            r6 = r4[r6]
            r4 = r4[r1]
            if (r6 == r4) goto L77
            int r2 = r2 + 1
        L77:
            int r1 = r1 + 1
            goto L67
        L7a:
            double[] r1 = new double[r2]
            int[] r0 = new int[r0]
            r0[r3] = r3
            r0[r5] = r2
            java.lang.Class r2 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r2, r0)
            double[][] r0 = (double[][]) r0
            r2 = r5
            r3 = r2
        L8c:
            int r4 = r14.d
            if (r2 >= r4) goto Lb8
            if (r2 <= 0) goto L9d
            int[] r4 = r14.b
            r6 = r4[r2]
            int r7 = r2 + (-1)
            r4 = r4[r7]
            if (r6 != r4) goto L9d
            goto Lb5
        L9d:
            int[] r4 = r14.b
            r4 = r4[r2]
            double r6 = (double) r4
            r8 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r6 = r6 * r8
            r1[r3] = r6
            r4 = r0[r3]
            float[] r6 = r14.c
            r6 = r6[r2]
            double r6 = (double) r6
            r4[r5] = r6
            int r3 = r3 + 1
        Lb5:
            int r2 = r2 + 1
            goto L8c
        Lb8:
            l20 r15 = defpackage.l20.a(r15, r1, r0)
            r14.a = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.wq2.c(int):void");
    }

    public final String toString() {
        String string = this.e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i = 0; i < this.d; i++) {
            StringBuilder sbK = C0173a.k(string, "[");
            sbK.append(this.b[i]);
            sbK.append(" , ");
            sbK.append(decimalFormat.format(this.c[i]));
            sbK.append("] ");
            string = sbK.toString();
        }
        return string;
    }
}
