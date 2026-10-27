package N1;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class P extends s7.i implements InterfaceC2785n {
    public A7.x l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4483m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4484n;
    public final /* synthetic */ A7.x o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Q f4485p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4486q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f4487r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P(A7.x xVar, Q q4, Object obj, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = xVar;
        this.f4485p = q4;
        this.f4486q = obj;
        this.f4487r = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        P p10 = new P(this.o, this.f4485p, this.f4486q, this.f4487r, interfaceC2189c);
        p10.f4484n = obj;
        return p10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((P) create((P1.k) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0069  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f4483m
            A7.x r2 = r8.o
            java.lang.Object r3 = r8.f4486q
            N1.Q r4 = r8.f4485p
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L28
            if (r1 == r6) goto L1e
            if (r1 != r5) goto L16
            h8.AbstractC1386c.u(r9)
            goto L65
        L16:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L1e:
            A7.x r1 = r8.l
            java.lang.Object r6 = r8.f4484n
            P1.k r6 = (P1.k) r6
            h8.AbstractC1386c.u(r9)
            goto L4f
        L28:
            h8.AbstractC1386c.u(r9)
            java.lang.Object r9 = r8.f4484n
            P1.k r9 = (P1.k) r9
            N1.b0 r1 = r4.f()
            r8.f4484n = r9
            r8.l = r2
            r8.f4483m = r6
            N1.a r1 = r1.f4514b
            java.lang.Object r1 = r1.l
            java.util.concurrent.atomic.AtomicInteger r1 = (java.util.concurrent.atomic.AtomicInteger) r1
            int r1 = r1.incrementAndGet()
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r1)
            if (r6 != r0) goto L4b
            return r0
        L4b:
            r1 = r2
            r7 = r6
            r6 = r9
            r9 = r7
        L4f:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r1.l = r9
            r9 = 0
            r8.f4484n = r9
            r8.l = r9
            r8.f4483m = r5
            java.lang.Object r9 = r6.b(r3, r8)
            if (r9 != r0) goto L65
            return r0
        L65:
            boolean r8 = r8.f4487r
            if (r8 == 0) goto L7d
            N1.S r8 = r4.f4492r
            N1.d r9 = new N1.d
            if (r3 == 0) goto L74
            int r0 = r3.hashCode()
            goto L75
        L74:
            r0 = 0
        L75:
            int r1 = r2.l
            r9.<init>(r0, r1, r3)
            r8.b(r9)
        L7d:
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.P.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
