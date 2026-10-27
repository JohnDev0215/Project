package j9;

import r9.C2319i;
import r9.J;
import r9.L;

/* JADX INFO: loaded from: classes.dex */
public final class s implements J {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f17575m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2319i f17576n = new C2319i();
    public final C2319i o = new C2319i();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f17577p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ u f17578q;

    public s(u uVar, long j3, boolean z6) {
        this.f17578q = uVar;
        this.l = j3;
        this.f17575m = z6;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j3;
        u uVar = this.f17578q;
        synchronized (uVar) {
            this.f17577p = true;
            C2319i c2319i = this.o;
            j3 = c2319i.f21932m;
            c2319i.b();
            uVar.notifyAll();
        }
        if (j3 > 0) {
            byte[] bArr = d9.b.f13645a;
            this.f17578q.f17581b.k(j3);
        }
        this.f17578q.a();
    }

    @Override // r9.J
    public final L e() {
        return this.f17578q.f17588k;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0089 A[LOOP:0: B:4:0x0010->B:45:0x0089, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x008c A[SYNTHETIC] */
    @Override // r9.J
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long p(r9.C2319i r16, long r17) throws java.lang.Throwable {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            java.lang.String r4 = "sink"
            A7.m.f(r1, r4)
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 < 0) goto Laa
        L10:
            j9.u r6 = r0.f17578q
            monitor-enter(r6)
            j9.t r7 = r6.f17588k     // Catch: java.lang.Throwable -> L95
            r7.i()     // Catch: java.lang.Throwable -> L95
            monitor-enter(r6)     // Catch: java.lang.Throwable -> L33
            int r7 = r6.f17589m     // Catch: java.lang.Throwable -> L9f
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L33
            if (r7 == 0) goto L39
            boolean r7 = r0.f17575m     // Catch: java.lang.Throwable -> L33
            if (r7 != 0) goto L39
            java.io.IOException r7 = r6.f17590n     // Catch: java.lang.Throwable -> L33
            if (r7 != 0) goto L3a
            j9.z r7 = new j9.z     // Catch: java.lang.Throwable -> L33
            monitor-enter(r6)     // Catch: java.lang.Throwable -> L33
            int r8 = r6.f17589m     // Catch: java.lang.Throwable -> L36
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L33
            A7.k.n(r8)     // Catch: java.lang.Throwable -> L33
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L33
            goto L3a
        L33:
            r0 = move-exception
            goto La2
        L36:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L36
            throw r0     // Catch: java.lang.Throwable -> L33
        L39:
            r7 = 0
        L3a:
            boolean r8 = r0.f17577p     // Catch: java.lang.Throwable -> L33
            if (r8 != 0) goto L97
            r9.i r8 = r0.o     // Catch: java.lang.Throwable -> L33
            long r9 = r8.f21932m     // Catch: java.lang.Throwable -> L33
            int r11 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            r12 = -1
            r14 = 0
            if (r11 <= 0) goto L76
            long r9 = java.lang.Math.min(r2, r9)     // Catch: java.lang.Throwable -> L33
            long r8 = r8.p(r1, r9)     // Catch: java.lang.Throwable -> L33
            long r10 = r6.c     // Catch: java.lang.Throwable -> L33
            long r10 = r10 + r8
            r6.c = r10     // Catch: java.lang.Throwable -> L33
            long r4 = r6.d     // Catch: java.lang.Throwable -> L33
            long r10 = r10 - r4
            if (r7 != 0) goto L81
            j9.m r4 = r6.f17581b     // Catch: java.lang.Throwable -> L33
            j9.y r4 = r4.f17539A     // Catch: java.lang.Throwable -> L33
            int r4 = r4.a()     // Catch: java.lang.Throwable -> L33
            int r4 = r4 / 2
            long r4 = (long) r4     // Catch: java.lang.Throwable -> L33
            int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r4 < 0) goto L81
            j9.m r4 = r6.f17581b     // Catch: java.lang.Throwable -> L33
            int r5 = r6.f17580a     // Catch: java.lang.Throwable -> L33
            r4.q(r10, r5)     // Catch: java.lang.Throwable -> L33
            long r4 = r6.c     // Catch: java.lang.Throwable -> L33
            r6.d = r4     // Catch: java.lang.Throwable -> L33
            goto L81
        L76:
            boolean r4 = r0.f17575m     // Catch: java.lang.Throwable -> L33
            if (r4 != 0) goto L80
            if (r7 != 0) goto L80
            r6.k()     // Catch: java.lang.Throwable -> L33
            r14 = 1
        L80:
            r8 = r12
        L81:
            j9.t r4 = r6.f17588k     // Catch: java.lang.Throwable -> L95
            r4.l()     // Catch: java.lang.Throwable -> L95
            monitor-exit(r6)
            if (r14 == 0) goto L8c
            r4 = 0
            goto L10
        L8c:
            int r0 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r0 == 0) goto L91
            return r8
        L91:
            if (r7 != 0) goto L94
            return r12
        L94:
            throw r7
        L95:
            r0 = move-exception
            goto La8
        L97:
            java.io.IOException r0 = new java.io.IOException     // Catch: java.lang.Throwable -> L33
            java.lang.String r1 = "stream closed"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L33
            throw r0     // Catch: java.lang.Throwable -> L33
        L9f:
            r0 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L9f
            throw r0     // Catch: java.lang.Throwable -> L33
        La2:
            j9.t r1 = r6.f17588k     // Catch: java.lang.Throwable -> L95
            r1.l()     // Catch: java.lang.Throwable -> L95
            throw r0     // Catch: java.lang.Throwable -> L95
        La8:
            monitor-exit(r6)
            throw r0
        Laa:
            java.lang.String r0 = "byteCount < 0: "
            java.lang.String r0 = M5.b.f(r0, r2)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.s.p(r9.i, long):long");
    }
}
