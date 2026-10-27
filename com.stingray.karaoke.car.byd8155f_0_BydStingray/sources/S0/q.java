package S0;

import io.sentry.C1489n1;

/* JADX INFO: loaded from: classes.dex */
public final class q implements InterfaceC0702n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P6.a f7850a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final F f7851b;
    public final C1489n1 c;
    public final v d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final io.sentry.hints.i f7852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p f7853f;

    public q(P6.a aVar, C0691c c0691c) {
        C1489n1 c1489n1 = r.f7854a;
        v vVar = new v(r.f7855b);
        io.sentry.hints.i iVar = new io.sentry.hints.i(8);
        this.f7850a = aVar;
        this.f7851b = c0691c;
        this.c = c1489n1;
        this.d = vVar;
        this.f7852e = iVar;
        this.f7853f = new p(this, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final S0.O a(S0.L r5) {
        /*
            r4 = this;
            io.sentry.n1 r0 = r4.c
            A.b r1 = new A.b
            r2 = 28
            r1.<init>(r4, r2, r5)
            java.lang.Object r4 = r0.f16624m
            C8.d r4 = (C8.d) r4
            monitor-enter(r4)
            java.lang.Object r2 = r0.f16625n     // Catch: java.lang.Throwable -> L2d
            R0.b r2 = (R0.b) r2     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r2 = r2.b(r5)     // Catch: java.lang.Throwable -> L2d
            S0.O r2 = (S0.O) r2     // Catch: java.lang.Throwable -> L2d
            if (r2 == 0) goto L2f
            boolean r3 = r2.a()     // Catch: java.lang.Throwable -> L2d
            if (r3 == 0) goto L22
            monitor-exit(r4)
            goto L5e
        L22:
            java.lang.Object r2 = r0.f16625n     // Catch: java.lang.Throwable -> L2d
            R0.b r2 = (R0.b) r2     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r2 = r2.h(r5)     // Catch: java.lang.Throwable -> L2d
            S0.O r2 = (S0.O) r2     // Catch: java.lang.Throwable -> L2d
            goto L2f
        L2d:
            r5 = move-exception
            goto L6a
        L2f:
            monitor-exit(r4)
            A.b r4 = new A.b     // Catch: java.lang.Exception -> L61
            r2 = 29
            r4.<init>(r0, r2, r5)     // Catch: java.lang.Exception -> L61
            java.lang.Object r4 = r1.b(r4)     // Catch: java.lang.Exception -> L61
            r2 = r4
            S0.O r2 = (S0.O) r2     // Catch: java.lang.Exception -> L61
            java.lang.Object r4 = r0.f16624m
            C8.d r4 = (C8.d) r4
            monitor-enter(r4)
            java.lang.Object r1 = r0.f16625n     // Catch: java.lang.Throwable -> L5b
            R0.b r1 = (R0.b) r1     // Catch: java.lang.Throwable -> L5b
            java.lang.Object r1 = r1.b(r5)     // Catch: java.lang.Throwable -> L5b
            if (r1 != 0) goto L5d
            boolean r1 = r2.a()     // Catch: java.lang.Throwable -> L5b
            if (r1 == 0) goto L5d
            java.lang.Object r0 = r0.f16625n     // Catch: java.lang.Throwable -> L5b
            R0.b r0 = (R0.b) r0     // Catch: java.lang.Throwable -> L5b
            r0.e(r5, r2)     // Catch: java.lang.Throwable -> L5b
            goto L5d
        L5b:
            r5 = move-exception
            goto L5f
        L5d:
            monitor-exit(r4)
        L5e:
            return r2
        L5f:
            monitor-exit(r4)
            throw r5
        L61:
            r4 = move-exception
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Could not load font"
            r5.<init>(r0, r4)
            throw r5
        L6a:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: S0.q.a(S0.L):S0.O");
    }

    public final O b(AbstractC0703o abstractC0703o, A a10, int i7, int i10) {
        F f10 = this.f7851b;
        f10.getClass();
        A a11 = f10.a(a10);
        this.f7850a.getClass();
        return a(new L(abstractC0703o, a11, i7, i10, null));
    }
}
