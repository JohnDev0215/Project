package p;

/* JADX INFO: loaded from: classes.dex */
public final class ss1 implements de2 {
    public final ko c;
    public final fo e;
    public bb2 k;
    public int l;
    public boolean m;
    public long n;

    public ss1(ko koVar) {
        v03.j(koVar, "upstream");
        this.c = koVar;
        fo foVarD = koVar.d();
        this.e = foVarD;
        bb2 bb2Var = foVarD.c;
        this.k = bb2Var;
        this.l = bb2Var != null ? bb2Var.b : -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.m = true;
    }

    @Override // p.de2
    public final tl2 e() {
        return this.c.e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
    
        if (r3 == r5.b) goto L15;
     */
    @Override // p.de2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long t(p.fo r9, long r10) {
        /*
            r8 = this;
            java.lang.String r0 = "sink"
            p.v03.j(r9, r0)
            r0 = 0
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 < 0) goto L76
            boolean r3 = r8.m
            r3 = r3 ^ 1
            if (r3 == 0) goto L6a
            p.bb2 r3 = r8.k
            p.fo r4 = r8.e
            if (r3 == 0) goto L31
            p.bb2 r5 = r4.c
            if (r3 != r5) goto L25
            int r3 = r8.l
            p.v03.h(r5)
            int r5 = r5.b
            if (r3 != r5) goto L25
            goto L31
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Peek source is invalid because upstream source was used"
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L31:
            if (r2 != 0) goto L34
            return r0
        L34:
            long r0 = r8.n
            r2 = 1
            long r0 = r0 + r2
            p.ko r2 = r8.c
            boolean r0 = r2.a(r0)
            if (r0 != 0) goto L44
            r9 = -1
            return r9
        L44:
            p.bb2 r0 = r8.k
            if (r0 != 0) goto L52
            p.bb2 r0 = r4.c
            if (r0 == 0) goto L52
            r8.k = r0
            int r0 = r0.b
            r8.l = r0
        L52:
            long r0 = r4.e
            long r2 = r8.n
            long r0 = r0 - r2
            long r10 = java.lang.Math.min(r10, r0)
            p.fo r2 = r8.e
            long r3 = r8.n
            r5 = r10
            r7 = r9
            r2.E0(r3, r5, r7)
            long r0 = r8.n
            long r0 = r0 + r10
            r8.n = r0
            return r10
        L6a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "closed"
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L76:
            java.lang.String r9 = "byteCount < 0: "
            java.lang.String r9 = p.u4.g(r9, r10)
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r9 = r9.toString()
            r10.<init>(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ss1.t(p.fo, long):long");
    }
}
