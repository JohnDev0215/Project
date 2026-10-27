package p;

import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes.dex */
public final class xl2 {
    public boolean a;
    public boolean b;
    public Object c;
    public Object d;

    public xl2(int i) {
        if (i != 1) {
            return;
        }
        this.c = new SparseIntArray();
        this.d = new SparseIntArray();
        this.a = false;
        this.b = false;
    }

    public final int a(int i, int i2) {
        if (!this.b) {
            return c(i, i2);
        }
        int i3 = ((SparseIntArray) this.d).get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int iC = c(i, i2);
        ((SparseIntArray) this.d).put(i, iC);
        return iC;
    }

    public final int b(int i, int i2) {
        if (!this.a) {
            return i % i2;
        }
        int i3 = ((SparseIntArray) this.c).get(i, -1);
        if (i3 != -1) {
            return i3;
        }
        int i4 = i % i2;
        ((SparseIntArray) this.c).put(i, i4);
        return i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(int r8, int r9) {
        /*
            r7 = this;
            boolean r0 = r7.b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L49
            java.lang.Object r0 = r7.d
            android.util.SparseIntArray r0 = (android.util.SparseIntArray) r0
            int r3 = r0.size()
            int r3 = r3 - r1
            r4 = r2
        L10:
            if (r4 > r3) goto L22
            int r5 = r4 + r3
            int r5 = r5 >>> r1
            int r6 = r0.keyAt(r5)
            if (r6 >= r8) goto L1e
            int r4 = r5 + 1
            goto L10
        L1e:
            int r5 = r5 + (-1)
            r3 = r5
            goto L10
        L22:
            int r4 = r4 - r1
            r3 = -1
            if (r4 < 0) goto L31
            int r5 = r0.size()
            if (r4 >= r5) goto L31
            int r0 = r0.keyAt(r4)
            goto L32
        L31:
            r0 = r3
        L32:
            if (r0 == r3) goto L49
            java.lang.Object r3 = r7.d
            android.util.SparseIntArray r3 = (android.util.SparseIntArray) r3
            int r3 = r3.get(r0)
            int r4 = r0 + 1
            int r0 = r7.b(r0, r9)
            int r0 = r0 + r1
            if (r0 != r9) goto L4c
            int r3 = r3 + 1
            r0 = r2
            goto L4c
        L49:
            r0 = r2
            r3 = r0
            r4 = r3
        L4c:
            if (r4 >= r8) goto L5e
            int r0 = r0 + 1
            if (r0 != r9) goto L56
            int r3 = r3 + 1
            r0 = r2
            goto L5b
        L56:
            if (r0 <= r9) goto L5b
            int r3 = r3 + 1
            r0 = r1
        L5b:
            int r4 = r4 + 1
            goto L4c
        L5e:
            int r0 = r0 + r1
            if (r0 <= r9) goto L63
            int r3 = r3 + 1
        L63:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p.xl2.c(int, int):int");
    }

    public final void d() {
        ((SparseIntArray) this.c).clear();
    }
}
