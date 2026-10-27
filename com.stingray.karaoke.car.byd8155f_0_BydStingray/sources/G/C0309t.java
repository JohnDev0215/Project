package G;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import x.InterfaceC2615D;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: G.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0309t extends s7.i implements InterfaceC2785n {
    public InterfaceC2615D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2610m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0314y f2611n;
    public final /* synthetic */ InterfaceC2615D o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f2612p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0309t(C0314y c0314y, InterfaceC2615D interfaceC2615D, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2611n = c0314y;
        this.o = interfaceC2615D;
        this.f2612p = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0309t(this.f2611n, this.o, this.f2612p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0309t) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x008b A[RETURN] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
        /*
            r14 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r14.f2610m
            G.y r2 = r14.f2611n
            long r3 = r14.f2612p
            r5 = 2
            r6 = 1
            x.d r7 = r2.f2628m
            if (r1 == 0) goto L25
            if (r1 == r6) goto L1f
            if (r1 != r5) goto L17
            h8.AbstractC1386c.u(r15)     // Catch: java.util.concurrent.CancellationException -> L94
            goto L8c
        L17:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L1f:
            x.D r1 = r14.l
            h8.AbstractC1386c.u(r15)     // Catch: java.util.concurrent.CancellationException -> L94
            goto L5f
        L25:
            h8.AbstractC1386c.u(r15)
            T.e0 r15 = r7.d     // Catch: java.util.concurrent.CancellationException -> L94
            java.lang.Object r15 = r15.getValue()     // Catch: java.util.concurrent.CancellationException -> L94
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch: java.util.concurrent.CancellationException -> L94
            boolean r15 = r15.booleanValue()     // Catch: java.util.concurrent.CancellationException -> L94
            x.D r1 = r14.o
            if (r15 == 0) goto L41
            boolean r15 = r1 instanceof x.C2640i0     // Catch: java.util.concurrent.CancellationException -> L94
            if (r15 == 0) goto L3f
            x.i0 r1 = (x.C2640i0) r1     // Catch: java.util.concurrent.CancellationException -> L94
            goto L41
        L3f:
            x.i0 r1 = G.AbstractC0315z.f2631a     // Catch: java.util.concurrent.CancellationException -> L94
        L41:
            T.e0 r15 = r7.d     // Catch: java.util.concurrent.CancellationException -> L94
            java.lang.Object r15 = r15.getValue()     // Catch: java.util.concurrent.CancellationException -> L94
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch: java.util.concurrent.CancellationException -> L94
            boolean r15 = r15.booleanValue()     // Catch: java.util.concurrent.CancellationException -> L94
            if (r15 != 0) goto L64
            a1.h r15 = new a1.h     // Catch: java.util.concurrent.CancellationException -> L94
            r15.<init>(r3)     // Catch: java.util.concurrent.CancellationException -> L94
            r14.l = r1     // Catch: java.util.concurrent.CancellationException -> L94
            r14.f2610m = r6     // Catch: java.util.concurrent.CancellationException -> L94
            java.lang.Object r15 = r7.e(r15, r14)     // Catch: java.util.concurrent.CancellationException -> L94
            if (r15 != r0) goto L5f
            return r0
        L5f:
            A.I0 r15 = r2.c     // Catch: java.util.concurrent.CancellationException -> L94
            r15.a()     // Catch: java.util.concurrent.CancellationException -> L94
        L64:
            r10 = r1
            java.lang.Object r15 = r7.d()     // Catch: java.util.concurrent.CancellationException -> L94
            a1.h r15 = (a1.h) r15     // Catch: java.util.concurrent.CancellationException -> L94
            long r6 = r15.f11033a     // Catch: java.util.concurrent.CancellationException -> L94
            long r3 = a1.h.c(r6, r3)     // Catch: java.util.concurrent.CancellationException -> L94
            x.d r8 = r2.f2628m     // Catch: java.util.concurrent.CancellationException -> L94
            a1.h r9 = new a1.h     // Catch: java.util.concurrent.CancellationException -> L94
            r9.<init>(r3)     // Catch: java.util.concurrent.CancellationException -> L94
            w.C r11 = new w.C     // Catch: java.util.concurrent.CancellationException -> L94
            r15 = 3
            r11.<init>(r15, r3, r2)     // Catch: java.util.concurrent.CancellationException -> L94
            r15 = 0
            r14.l = r15     // Catch: java.util.concurrent.CancellationException -> L94
            r14.f2610m = r5     // Catch: java.util.concurrent.CancellationException -> L94
            r13 = 4
            r12 = r14
            java.lang.Object r14 = x.C2629d.c(r8, r9, r10, r11, r12, r13)     // Catch: java.util.concurrent.CancellationException -> L94
            if (r14 != r0) goto L8c
            return r0
        L8c:
            int r14 = G.C0314y.f2618r     // Catch: java.util.concurrent.CancellationException -> L94
            r14 = 0
            r2.d(r14)     // Catch: java.util.concurrent.CancellationException -> L94
            r2.f2621e = r14     // Catch: java.util.concurrent.CancellationException -> L94
        L94:
            m7.q r14 = m7.q.f19267a
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: G.C0309t.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
