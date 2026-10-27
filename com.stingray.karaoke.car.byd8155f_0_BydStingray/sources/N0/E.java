package N0;

import a.AbstractC0904a;
import n0.C1891t;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public abstract class E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f4336a = AbstractC0904a.I(14);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f4337b = AbstractC0904a.I(0);
    public static final long c = C1891t.f19451j;
    public static final Y0.m d;

    static {
        long j3 = C1891t.f19445b;
        d = j3 != 16 ? new Y0.c(j3) : Y0.l.f10622a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final N0.D a(N0.D r19, long r20, n0.AbstractC1887p r22, float r23, long r24, S0.A r26, S0.w r27, S0.x r28, S0.AbstractC0703o r29, java.lang.String r30, long r31, Y0.a r33, Y0.n r34, U0.b r35, long r36, Y0.j r38, n0.C1865O r39, N0.x r40, p0.e r41) {
        /*
            Method dump skipped, instruction units count: 518
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N0.E.a(N0.D, long, n0.p, float, long, S0.A, S0.w, S0.x, S0.o, java.lang.String, long, Y0.a, Y0.n, U0.b, long, Y0.j, n0.O, N0.x, p0.e):N0.D");
    }

    public static final Object b(Object obj, Object obj2, float f10) {
        return ((double) f10) < 0.5d ? obj : obj2;
    }

    public static final long c(long j3, long j10, float f10) {
        if (AbstractC0904a.L(j3) || AbstractC0904a.L(j10)) {
            return ((a1.m) b(new a1.m(j3), new a1.m(j10), f10)).f11042a;
        }
        if (AbstractC0904a.L(j3) || AbstractC0904a.L(j10)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.");
        }
        if (a1.n.a(a1.m.b(j3), a1.m.b(j10))) {
            return AbstractC0904a.O(AbstractC2673a.O(a1.m.c(j3), a1.m.c(j10), f10), 1095216660480L & j3);
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) a1.n.b(a1.m.b(j3))) + " and " + ((Object) a1.n.b(a1.m.b(j10)))).toString());
    }
}
