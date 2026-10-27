package f5;

import g1.AbstractC1274a;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f14598a = 0;

    static {
        Math.log(2.0d);
    }

    public static boolean a(double d) {
        return AbstractC1274a.R(d) && (d == 0.0d || 52 - Long.numberOfTrailingZeros(AbstractC1274a.N(d)) <= Math.getExponent(d));
    }

    public static boolean b(double d) {
        if (d <= 0.0d || !AbstractC1274a.R(d)) {
            return false;
        }
        long jN = AbstractC1274a.N(d);
        return (jN & (jN - 1)) == 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int c(double r5, java.math.RoundingMode r7) {
        /*
            r0 = 0
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L10
            boolean r0 = g1.AbstractC1274a.R(r5)
            if (r0 == 0) goto L10
            r0 = r2
            goto L11
        L10:
            r0 = r1
        L11:
            if (r0 == 0) goto L80
            int r0 = java.lang.Math.getExponent(r5)
            int r3 = java.lang.Math.getExponent(r5)
            r4 = -1022(0xfffffffffffffc02, float:NaN)
            if (r3 < r4) goto L76
            int[] r3 = f5.AbstractC1260a.f14597a
            int r7 = r7.ordinal()
            r7 = r3[r7]
            switch(r7) {
                case 1: goto L63;
                case 2: goto L69;
                case 3: goto L5c;
                case 4: goto L54;
                case 5: goto L4a;
                case 6: goto L30;
                case 7: goto L30;
                case 8: goto L30;
                default: goto L2a;
            }
        L2a:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L30:
            long r5 = java.lang.Double.doubleToRawLongBits(r5)
            r3 = 4503599627370495(0xfffffffffffff, double:2.225073858507201E-308)
            long r5 = r5 & r3
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            long r5 = r5 | r3
            double r5 = java.lang.Double.longBitsToDouble(r5)
            double r5 = r5 * r5
            r3 = 4611686018427387904(0x4000000000000000, double:2.0)
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 <= 0) goto L69
            r1 = r2
            goto L69
        L4a:
            if (r0 < 0) goto L4d
            r1 = r2
        L4d:
            boolean r5 = b(r5)
        L51:
            r5 = r5 ^ r2
            r1 = r1 & r5
            goto L69
        L54:
            if (r0 >= 0) goto L57
            r1 = r2
        L57:
            boolean r5 = b(r5)
            goto L51
        L5c:
            boolean r5 = b(r5)
            r1 = r5 ^ 1
            goto L69
        L63:
            boolean r5 = b(r5)
            if (r5 == 0) goto L6e
        L69:
            if (r1 == 0) goto L6d
            int r0 = r0 + 1
        L6d:
            return r0
        L6e:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "mode was UNNECESSARY, but rounding was necessary"
            r5.<init>(r6)
            throw r5
        L76:
            r0 = 4841369599423283200(0x4330000000000000, double:4.503599627370496E15)
            double r5 = r5 * r0
            int r5 = c(r5, r7)
            int r5 = r5 + (-52)
            return r5
        L80:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "x must be positive and finite"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: f5.b.c(double, java.math.RoundingMode):int");
    }
}
