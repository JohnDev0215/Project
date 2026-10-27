package C;

import A7.z;
import V8.InterfaceC0818g;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC0818g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f827m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f828n;

    public /* synthetic */ f(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f827m = obj;
        this.f828n = obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object a(int r6, q7.InterfaceC2189c r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof V8.Y
            if (r0 == 0) goto L13
            r0 = r7
            V8.Y r0 = (V8.Y) r0
            int r1 = r0.f9601n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9601n = r1
            goto L18
        L13:
            V8.Y r0 = new V8.Y
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f9601n
            m7.q r3 = m7.q.f19267a
            r4 = 1
            if (r2 == 0) goto L31
            if (r2 != r4) goto L29
            h8.AbstractC1386c.u(r7)
            goto L4f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            h8.AbstractC1386c.u(r7)
            if (r6 <= 0) goto L4f
            java.lang.Object r6 = r5.f827m
            A7.v r6 = (A7.v) r6
            boolean r7 = r6.l
            if (r7 != 0) goto L4f
            r6.l = r4
            V8.U r6 = V8.U.l
            r0.f9601n = r4
            java.lang.Object r5 = r5.f828n
            V8.g r5 = (V8.InterfaceC0818g) r5
            java.lang.Object r5 = r5.f(r6, r0)
            if (r5 != r1) goto L4f
            return r1
        L4f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: C.f.a(int, q7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object b(N5.f0 r8, q7.InterfaceC2189c r9) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r9 instanceof X5.C0850b0
            if (r0 == 0) goto L13
            r0 = r9
            X5.b0 r0 = (X5.C0850b0) r0
            int r1 = r0.f10228q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f10228q = r1
            goto L18
        L13:
            X5.b0 r0 = new X5.b0
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f10228q
            r3 = 1
            if (r2 == 0) goto L46
            if (r2 == r3) goto L3c
            r7 = 2
            if (r2 != r7) goto L34
            C.f r7 = r0.l
            V8.L r7 = (V8.L) r7
            h8.AbstractC1386c.u(r9)
            V8.f0 r7 = (V8.f0) r7
            r7.l(r9)
            goto Laa
        L34:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3c:
            X5.D0 r7 = r0.f10226n
            N5.f0 r8 = r0.f10225m
            C.f r0 = r0.l
            h8.AbstractC1386c.u(r9)
            goto L7f
        L46:
            h8.AbstractC1386c.u(r9)
            if (r8 == 0) goto Laa
            com.stingray.kca.model.KaraokeSongResponseV2 r9 = r8.f4691a
            java.lang.String r2 = r9.f13136a
            if (r2 == 0) goto Laa
            java.lang.Object r2 = r7.f827m
            X5.D0 r2 = (X5.D0) r2
            V8.f0 r4 = r2.f10119O
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            r4.getClass()
            r6 = 0
            r4.m(r6, r5)
            n6.u r4 = r2.f10101A
            r4.c()
            java.lang.String r9 = r9.f13136a
            A7.m.c(r9)
            r0.l = r7
            r0.f10225m = r8
            r0.f10226n = r2
            r0.f10228q = r3
            java.lang.Object r3 = r7.f828n
            j6.n r3 = (j6.n) r3
            java.lang.Object r9 = j6.n.d(r3, r9, r0)
            if (r9 != r1) goto L7d
            return r1
        L7d:
            r0 = r7
            r7 = r2
        L7f:
            j6.a r9 = (j6.a) r9
            boolean r9 = r9.f17032a
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            T.e0 r7 = r7.f10109F
            r7.setValue(r9)
            java.lang.Object r7 = r0.f827m
            X5.D0 r7 = (X5.D0) r7
            N5.Z r9 = r7.f10158x
            T.e0 r7 = r7.f10109F
            java.lang.Object r7 = r7.getValue()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r9.F(r7)
            com.stingray.kca.model.KaraokeSongResponseV2 r7 = r8.f4691a
            java.lang.Boolean r7 = r7.f13146n
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            A7.m.a(r7, r8)
        Laa:
            m7.q r7 = m7.q.f19267a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: C.f.b(N5.f0, q7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0149  */
    /* JADX WARN: Type inference failed for: r13v32, types: [s7.i, z7.n] */
    /* JADX WARN: Type inference failed for: r1v7, types: [A7.o, z7.a] */
    @Override // V8.InterfaceC0818g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(java.lang.Object r12, q7.InterfaceC2189c r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 856
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C.f.f(java.lang.Object, q7.c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public f(InterfaceC2785n interfaceC2785n, z zVar) {
        this.l = 7;
        this.f827m = (s7.i) interfaceC2785n;
        this.f828n = zVar;
    }
}
