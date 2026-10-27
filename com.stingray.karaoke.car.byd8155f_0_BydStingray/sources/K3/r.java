package k3;

import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f18093g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f18094h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f18095i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f18096j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f18097k;
    public static final String l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f18099b;
    public final Object c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1660o0 f18100e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final A1 f18101f;

    static {
        int i7 = AbstractC1343A.f15195a;
        f18093g = Integer.toString(0, 36);
        f18094h = Integer.toString(1, 36);
        f18095i = Integer.toString(2, 36);
        f18096j = Integer.toString(3, 36);
        f18097k = Integer.toString(4, 36);
        l = Integer.toString(5, 36);
    }

    public r(int i7, long j3, C1660o0 c1660o0, A1 a12, Object obj, int i10) {
        this.f18098a = i7;
        this.f18099b = j3;
        this.f18100e = c1660o0;
        this.f18101f = a12;
        this.c = obj;
        this.d = i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static k3.r a(android.os.Bundle r10) {
        /*
            java.lang.String r0 = k3.r.f18093g
            r1 = 0
            int r3 = r10.getInt(r0, r1)
            java.lang.String r0 = k3.r.f18094h
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r10.getLong(r0, r4)
            java.lang.String r0 = k3.r.f18095i
            android.os.Bundle r0 = r10.getBundle(r0)
            r2 = 0
            if (r0 != 0) goto L1c
            r6 = r2
            goto L21
        L1c:
            k3.o0 r0 = k3.C1660o0.a(r0)
            r6 = r0
        L21:
            java.lang.String r0 = k3.r.l
            android.os.Bundle r0 = r10.getBundle(r0)
            if (r0 == 0) goto L2f
            k3.A1 r0 = k3.A1.a(r0)
        L2d:
            r7 = r0
            goto L38
        L2f:
            if (r3 == 0) goto L37
            k3.A1 r0 = new k3.A1
            r0.<init>(r3)
            goto L2d
        L37:
            r7 = r2
        L38:
            java.lang.String r0 = k3.r.f18097k
            int r9 = r10.getInt(r0)
            r0 = 1
            if (r9 == r0) goto L7f
            r0 = 2
            java.lang.String r8 = k3.r.f18096j
            if (r9 == r0) goto L81
            r0 = 3
            if (r9 == r0) goto L53
            r10 = 4
            if (r9 != r10) goto L4d
            goto L7f
        L4d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L53:
            android.os.IBinder r10 = r10.getBinder(r8)
            if (r10 != 0) goto L5a
            goto L7f
        L5a:
            d5.I r10 = e2.BinderC1198f.a(r10)
            d5.F r0 = d5.I.u()
        L62:
            int r2 = r10.size()
            if (r1 >= r2) goto L7b
            java.lang.Object r2 = r10.get(r1)
            android.os.Bundle r2 = (android.os.Bundle) r2
            r2.getClass()
            e2.F r2 = e2.F.a(r2)
            r0.a(r2)
            int r1 = r1 + 1
            goto L62
        L7b:
            d5.d0 r2 = r0.h()
        L7f:
            r8 = r2
            goto L8d
        L81:
            android.os.Bundle r10 = r10.getBundle(r8)
            if (r10 != 0) goto L88
            goto L7f
        L88:
            e2.F r2 = e2.F.a(r10)
            goto L7f
        L8d:
            k3.r r10 = new k3.r
            r2 = r10
            r2.<init>(r3, r4, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.r.a(android.os.Bundle):k3.r");
    }
}
