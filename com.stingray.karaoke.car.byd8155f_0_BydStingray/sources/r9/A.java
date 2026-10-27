package r9;

/* JADX INFO: loaded from: classes.dex */
public final class A implements J {
    public final InterfaceC2321k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2319i f21891m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public E f21892n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f21893p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f21894q;

    public A(InterfaceC2321k interfaceC2321k) {
        this.l = interfaceC2321k;
        C2319i c2319iC = interfaceC2321k.c();
        this.f21891m = c2319iC;
        E e10 = c2319iC.l;
        this.f21892n = e10;
        this.o = e10 != null ? e10.f21900b : -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f21893p = true;
    }

    @Override // r9.J
    public final L e() {
        return this.l.e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
    
        if (r3 == r5.f21900b) goto L15;
     */
    @Override // r9.J
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long p(r9.C2319i r9, long r10) {
        /*
            r8 = this;
            java.lang.String r0 = "sink"
            A7.m.f(r9, r0)
            r0 = 0
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 < 0) goto L6c
            boolean r3 = r8.f21893p
            if (r3 != 0) goto L64
            r9.E r3 = r8.f21892n
            r9.i r4 = r8.f21891m
            if (r3 == 0) goto L2b
            r9.E r5 = r4.l
            if (r3 != r5) goto L23
            int r3 = r8.o
            A7.m.c(r5)
            int r5 = r5.f21900b
            if (r3 != r5) goto L23
            goto L2b
        L23:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Peek source is invalid because upstream source was used"
            r8.<init>(r9)
            throw r8
        L2b:
            if (r2 != 0) goto L2e
            return r0
        L2e:
            long r0 = r8.f21894q
            r2 = 1
            long r0 = r0 + r2
            r9.k r2 = r8.l
            boolean r0 = r2.l(r0)
            if (r0 != 0) goto L3e
            r8 = -1
            return r8
        L3e:
            r9.E r0 = r8.f21892n
            if (r0 != 0) goto L4c
            r9.E r0 = r4.l
            if (r0 == 0) goto L4c
            r8.f21892n = r0
            int r0 = r0.f21900b
            r8.o = r0
        L4c:
            long r0 = r4.f21932m
            long r2 = r8.f21894q
            long r0 = r0 - r2
            long r10 = java.lang.Math.min(r10, r0)
            r9.i r2 = r8.f21891m
            long r4 = r8.f21894q
            r3 = r9
            r6 = r10
            r2.i(r3, r4, r6)
            long r0 = r8.f21894q
            long r0 = r0 + r10
            r8.f21894q = r0
            return r10
        L64:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "closed"
            r8.<init>(r9)
            throw r8
        L6c:
            java.lang.String r8 = "byteCount < 0: "
            java.lang.String r8 = M5.b.f(r8, r10)
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: r9.A.p(r9.i, long):long");
    }
}
