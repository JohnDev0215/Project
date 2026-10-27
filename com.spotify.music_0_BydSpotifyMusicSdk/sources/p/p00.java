package p;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class p00 implements Comparator {
    public final /* synthetic */ int c;

    public /* synthetic */ p00(int i) {
        this.c = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x003f  */
    @Override // java.util.Comparator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int compare(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            int r0 = r6.c
            r1 = -1
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L5d;
                case 1: goto L29;
                case 2: goto L20;
                default: goto L8;
            }
        L8:
            p.mz2 r7 = (p.mz2) r7
            p.os1 r7 = r7.a
            p.mz2 r8 = (p.mz2) r8
            p.os1 r8 = r8.a
            if (r7 != r8) goto L14
            r1 = r3
            goto L1f
        L14:
            if (r7 != 0) goto L17
            goto L1f
        L17:
            if (r8 != 0) goto L1b
            r1 = r2
            goto L1f
        L1b:
            int r1 = r7.compareTo(r8)
        L1f:
            return r1
        L20:
            java.lang.Comparable r7 = (java.lang.Comparable) r7
            java.lang.Comparable r8 = (java.lang.Comparable) r8
            int r7 = r7.compareTo(r8)
            return r7
        L29:
            p.c01 r7 = (p.c01) r7
            p.c01 r8 = (p.c01) r8
            androidx.recyclerview.widget.RecyclerView r0 = r7.d
            if (r0 != 0) goto L33
            r4 = r2
            goto L34
        L33:
            r4 = r3
        L34:
            androidx.recyclerview.widget.RecyclerView r5 = r8.d
            if (r5 != 0) goto L3a
            r5 = r2
            goto L3b
        L3a:
            r5 = r3
        L3b:
            if (r4 == r5) goto L42
            if (r0 != 0) goto L40
        L3f:
            r1 = r2
        L40:
            r3 = r1
            goto L5c
        L42:
            boolean r0 = r7.a
            boolean r4 = r8.a
            if (r0 == r4) goto L4b
            if (r0 == 0) goto L3f
            goto L40
        L4b:
            int r0 = r8.b
            int r1 = r7.b
            int r0 = r0 - r1
            if (r0 == 0) goto L54
            r3 = r0
            goto L5c
        L54:
            int r7 = r7.c
            int r8 = r8.c
            int r7 = r7 - r8
            if (r7 == 0) goto L5c
            r3 = r7
        L5c:
            return r3
        L5d:
            android.view.View r7 = (android.view.View) r7
            android.view.View r8 = (android.view.View) r8
            java.util.WeakHashMap r0 = p.zt2.a
            float r7 = p.ot2.m(r7)
            float r8 = p.ot2.m(r8)
            int r0 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r0 <= 0) goto L70
            goto L77
        L70:
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 >= 0) goto L76
            r1 = r2
            goto L77
        L76:
            r1 = r3
        L77:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.p00.compare(java.lang.Object, java.lang.Object):int");
    }
}
