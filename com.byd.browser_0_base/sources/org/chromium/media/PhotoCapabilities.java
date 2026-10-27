package org.chromium.media;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class PhotoCapabilities {
    public final boolean[] a;
    public final double[] b;
    public final int[] c;
    public final int[] d;
    public final int[] e;
    public final int[][] f;

    /* JADX WARN: Code restructure failed: missing block: B:73:0x005e, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PhotoCapabilities(boolean[] r8, double[] r9, int[] r10, int[] r11, int[] r12, int[][] r13) {
        /*
            r7 = this;
            r7.<init>()
            int r0 = r8.length
            r1 = 3
            if (r0 != r1) goto La9
            int r0 = r9.length
            r2 = 16
            if (r0 != r2) goto La9
            int r0 = r10.length
            if (r0 != r2) goto La9
            int r0 = r12.length
            if (r0 != r1) goto La9
            int r0 = r13.length
            if (r0 != r1) goto La9
            r0 = 0
            if (r11 == 0) goto L2c
            r2 = r0
        L19:
            int r3 = r11.length
            if (r2 >= r3) goto L2c
            r3 = r11[r2]
            if (r3 < 0) goto L26
            r4 = 4
            if (r3 >= r4) goto L26
            int r2 = r2 + 1
            goto L19
        L26:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r7.<init>()
            throw r7
        L2c:
            r2 = r0
        L2d:
            int r3 = r12.length
            r4 = 5
            if (r2 >= r3) goto L40
            r3 = r12[r2]
            if (r3 < 0) goto L3a
            if (r3 >= r4) goto L3a
            int r2 = r2 + 1
            goto L2d
        L3a:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r7.<init>()
            throw r7
        L40:
            r2 = r0
        L41:
            int r3 = r13.length
            if (r2 >= r3) goto L61
            r3 = r13[r2]
            if (r3 != 0) goto L49
            goto L5e
        L49:
            r3 = r0
        L4a:
            r5 = r13[r2]
            int r6 = r5.length
            if (r3 >= r6) goto L5e
            r5 = r5[r3]
            if (r5 < 0) goto L58
            if (r5 >= r4) goto L58
            int r3 = r3 + 1
            goto L4a
        L58:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r7.<init>()
            throw r7
        L5e:
            int r2 = r2 + 1
            goto L41
        L61:
            java.lang.Object r8 = r8.clone()
            boolean[] r8 = (boolean[]) r8
            r7.a = r8
            java.lang.Object r8 = r9.clone()
            double[] r8 = (double[]) r8
            r7.b = r8
            java.lang.Object r8 = r10.clone()
            int[] r8 = (int[]) r8
            r7.c = r8
            r8 = 0
            if (r11 != 0) goto L7e
            r9 = r8
            goto L84
        L7e:
            java.lang.Object r9 = r11.clone()
            int[] r9 = (int[]) r9
        L84:
            r7.d = r9
            java.lang.Object r9 = r12.clone()
            int[] r9 = (int[]) r9
            r7.e = r9
            int[][] r9 = new int[r1][]
            r7.f = r9
        L92:
            int r9 = r13.length
            if (r0 >= r9) goto La8
            int[][] r9 = r7.f
            r10 = r13[r0]
            if (r10 != 0) goto L9d
            r10 = r8
            goto La3
        L9d:
            java.lang.Object r10 = r10.clone()
            int[] r10 = (int[]) r10
        La3:
            r9[r0] = r10
            int r0 = r0 + 1
            goto L92
        La8:
            return
        La9:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.PhotoCapabilities.<init>(boolean[], double[], int[], int[], int[], int[][]):void");
    }

    @CalledByNative
    public boolean getBool(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        }
        return this.a[i];
    }

    @CalledByNative
    public double getDouble(int i) {
        if (i < 0 || i >= 16) {
            throw new IllegalArgumentException();
        }
        return this.b[i];
    }

    @CalledByNative
    public int[] getFillLightModeArray() {
        int[] iArr = this.d;
        return iArr != null ? (int[]) iArr.clone() : new int[0];
    }

    @CalledByNative
    public int getInt(int i) {
        if (i < 0 || i >= 16) {
            throw new IllegalArgumentException();
        }
        return this.c[i];
    }

    @CalledByNative
    public int getMeteringMode(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        }
        return this.e[i];
    }

    @CalledByNative
    public int[] getMeteringModeArray(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException();
        }
        int[] iArr = this.f[i];
        return iArr != null ? (int[]) iArr.clone() : new int[0];
    }
}
