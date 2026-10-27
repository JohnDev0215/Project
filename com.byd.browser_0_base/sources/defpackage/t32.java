package defpackage;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: PriceDropMetricsLogger.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t32 {
    public static final long b;
    public static final long c;
    public xl2 a;

    /* JADX INFO: compiled from: PriceDropMetricsLogger.java */
    public enum a {
        f("AbandonedTab"),
        g("StaleTab"),
        h("ActiveTab");

        public final String e;

        a(String str) {
            this.e = str;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.e;
        }
    }

    static {
        TimeUnit timeUnit = TimeUnit.DAYS;
        b = timeUnit.toMillis(90L);
        c = timeUnit.toMillis(1L);
    }

    public t32(xl2 xl2Var) {
        this.a = xl2Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(long r8, java.lang.String r10) {
        /*
            r7 = this;
            long r0 = defpackage.t32.b
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            t32$a r1 = t32.a.f
            if (r0 < 0) goto La
            r8 = r1
            goto L15
        La:
            long r2 = defpackage.t32.c
            int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r8 >= 0) goto L13
            t32$a r8 = t32.a.h
            goto L15
        L13:
            t32$a r8 = t32.a.g
        L15:
            if (r8 != r1) goto L18
            return
        L18:
            xl2 r9 = r7.a
            xl2$d r9 = r9.o
            java.lang.String r9 = r9.d
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            r0 = 1
            r9 = r9 ^ r0
            xl2 r7 = r7.a
            xl2$d r7 = r7.o
            long r1 = r7.a
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r6 = 0
            if (r5 == 0) goto L33
            r5 = r0
            goto L34
        L33:
            r5 = r6
        L34:
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L3a
            r1 = r0
            goto L3b
        L3a:
            r1 = r6
        L3b:
            if (r1 == 0) goto L4a
            long r1 = r7.b
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 == 0) goto L45
            r7 = r0
            goto L46
        L45:
            r7 = r6
        L46:
            if (r7 == 0) goto L4a
            r7 = r0
            goto L4b
        L4a:
            r7 = r6
        L4b:
            java.util.Locale r1 = java.util.Locale.US
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r3[r6] = r8
            r3[r0] = r10
            java.lang.String r4 = "Commerce.PriceDrops.%s%s.IsProductDetailPage"
            java.lang.String r3 = java.lang.String.format(r1, r4, r3)
            defpackage.oj2.O(r3, r9)
            java.lang.Object[] r9 = new java.lang.Object[r2]
            r9[r6] = r8
            r9[r0] = r10
            java.lang.String r3 = "Commerce.PriceDrops.%s%s.ContainsPrice"
            java.lang.String r9 = java.lang.String.format(r1, r3, r9)
            defpackage.oj2.O(r9, r5)
            java.lang.Object[] r9 = new java.lang.Object[r2]
            r9[r6] = r8
            r9[r0] = r10
            java.lang.String r8 = "Commerce.PriceDrops.%s%s.ContainsPriceDrop"
            java.lang.String r8 = java.lang.String.format(r1, r8, r9)
            defpackage.oj2.O(r8, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t32.a(long, java.lang.String):void");
    }
}
