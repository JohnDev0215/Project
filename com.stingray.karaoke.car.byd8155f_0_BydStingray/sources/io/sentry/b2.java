package io.sentry;

import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class b2 extends T1 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final io.sentry.protocol.C f16475A = io.sentry.protocol.C.CUSTOM;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f16476v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public io.sentry.protocol.C f16477w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public B8.t f16478x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public A2.c f16479y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Y f16480z;

    public b2(String str, io.sentry.protocol.C c, String str2, B8.t tVar) {
        super(new io.sentry.protocol.t((UUID) null), new V1(), str2, null, null);
        this.f16480z = Y.SENTRY;
        h6.e.q(str, "name is required");
        this.f16476v = str;
        this.f16477w = c;
        this.o = tVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.sentry.b2 a(h3.y r9) {
        /*
            r9.getClass()
            java.lang.Object r0 = r9.o
            A2.c r0 = (A2.c) r0
            r1 = 0
            if (r0 == 0) goto L3b
            r2 = 0
            r0.f311b = r2
            java.lang.String r3 = "sentry-sample_rate"
            java.lang.String r3 = r0.k(r3)
            if (r3 == 0) goto L28
            double r3 = java.lang.Double.parseDouble(r3)     // Catch: java.lang.NumberFormatException -> L28
            java.lang.Double r5 = java.lang.Double.valueOf(r3)     // Catch: java.lang.NumberFormatException -> L28
            boolean r5 = h6.i.w(r5, r2)     // Catch: java.lang.NumberFormatException -> L28
            if (r5 == 0) goto L28
            java.lang.Double r3 = java.lang.Double.valueOf(r3)     // Catch: java.lang.NumberFormatException -> L28
            goto L29
        L28:
            r3 = r1
        L29:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            if (r3 == 0) goto L35
            B8.t r1 = new B8.t
            r1.<init>(r2, r3)
            goto L3b
        L35:
            B8.t r3 = new B8.t
            r3.<init>(r2, r1)
            r1 = r3
        L3b:
            io.sentry.b2 r8 = new io.sentry.b2
            java.lang.Object r2 = r9.f15499m
            r3 = r2
            io.sentry.protocol.t r3 = (io.sentry.protocol.t) r3
            java.lang.Object r9 = r9.f15500n
            r4 = r9
            io.sentry.V1 r4 = (io.sentry.V1) r4
            r6 = 0
            java.lang.String r5 = "default"
            r7 = 0
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            io.sentry.Y r9 = io.sentry.Y.SENTRY
            r8.f16480z = r9
            java.lang.String r9 = "<unlabeled transaction>"
            r8.f16476v = r9
            r8.f16478x = r1
            io.sentry.protocol.C r9 = io.sentry.b2.f16475A
            r8.f16477w = r9
            r8.f16479y = r0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.b2.a(h3.y):io.sentry.b2");
    }
}
