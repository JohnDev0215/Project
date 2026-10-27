package N1;

import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J extends s7.i implements InterfaceC2782k {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4472m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Q f4473n;
    public final /* synthetic */ InterfaceC2194h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s7.i f4474p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public J(Q q4, InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4473n = q4;
        this.o = interfaceC2194h;
        this.f4474p = (s7.i) interfaceC2785n;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((J) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new J(this.f4473n, this.o, this.f4474p, interfaceC2189c);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0073  */
    /* JADX WARN: Type inference failed for: r6v0, types: [s7.i, z7.n] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f4472m
            N1.Q r2 = r8.f4473n
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L2b
            if (r1 == r5) goto L27
            if (r1 == r4) goto L1f
            if (r1 != r3) goto L17
            java.lang.Object r8 = r8.l
            h8.AbstractC1386c.u(r9)
            goto L71
        L17:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L1f:
            java.lang.Object r1 = r8.l
            N1.d r1 = (N1.C0423d) r1
            h8.AbstractC1386c.u(r9)
            goto L4f
        L27:
            h8.AbstractC1386c.u(r9)
            goto L37
        L2b:
            h8.AbstractC1386c.u(r9)
            r8.f4472m = r5
            java.lang.Object r9 = N1.Q.e(r2, r5, r8)
            if (r9 != r0) goto L37
            return r0
        L37:
            r1 = r9
            N1.d r1 = (N1.C0423d) r1
            N1.I r9 = new N1.I
            s7.i r6 = r8.f4474p
            r7 = 0
            r9.<init>(r6, r1, r7)
            r8.l = r1
            r8.f4472m = r4
            q7.h r4 = r8.o
            java.lang.Object r9 = S8.AbstractC0727y.F(r4, r9, r8)
            if (r9 != r0) goto L4f
            return r0
        L4f:
            java.lang.Object r4 = r1.f4516b
            if (r4 == 0) goto L58
            int r4 = r4.hashCode()
            goto L59
        L58:
            r4 = 0
        L59:
            int r6 = r1.c
            if (r4 != r6) goto L73
            java.lang.Object r1 = r1.f4516b
            boolean r1 = A7.m.a(r1, r9)
            if (r1 != 0) goto L72
            r8.l = r9
            r8.f4472m = r3
            java.lang.Object r8 = r2.i(r9, r5, r8)
            if (r8 != r0) goto L70
            return r0
        L70:
            r8 = r9
        L71:
            r9 = r8
        L72:
            return r9
        L73:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Data in DataStore was mutated but DataStore is only compatible with Immutable types."
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.J.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
