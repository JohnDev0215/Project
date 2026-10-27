package V8;

import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0819h implements InterfaceC0817f {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f9624m;

    public /* synthetic */ C0819h(Object obj, int i7) {
        this.l = i7;
        this.f9624m = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Type inference failed for: r5v12, types: [s7.i, z7.n] */
    @Override // V8.InterfaceC0817f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(V8.InterfaceC0818g r6, q7.InterfaceC2189c r7) throws java.lang.Throwable {
        /*
            r5 = this;
            int r0 = r5.l
            switch(r0) {
                case 0: goto L9f;
                case 1: goto L88;
                case 2: goto L24;
                default: goto L5;
            }
        L5:
            W8.v r0 = new W8.v
            java.lang.Object r5 = r5.f9624m
            V8.p r5 = (V8.C0827p) r5
            r1 = 0
            r0.<init>(r5, r6, r1)
            W8.u r5 = new W8.u
            q7.h r6 = r7.getContext()
            r5.<init>(r7, r6)
            java.lang.Object r5 = io.sentry.config.a.m0(r5, r5, r0)
            r7.a r6 = r7.EnumC2303a.l
            if (r5 != r6) goto L21
            goto L23
        L21:
            m7.q r5 = m7.q.f19267a
        L23:
            return r5
        L24:
            boolean r0 = r7 instanceof V8.C0812a
            if (r0 == 0) goto L37
            r0 = r7
            V8.a r0 = (V8.C0812a) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L37
            int r1 = r1 - r2
            r0.o = r1
            goto L3c
        L37:
            V8.a r0 = new V8.a
            r0.<init>(r5, r7)
        L3c:
            java.lang.Object r7 = r0.f9604m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            m7.q r3 = m7.q.f19267a
            r4 = 1
            if (r2 == 0) goto L59
            if (r2 != r4) goto L51
            W8.z r5 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: java.lang.Throwable -> L4f
            goto L79
        L4f:
            r6 = move-exception
            goto L84
        L51:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L59:
            h8.AbstractC1386c.u(r7)
            W8.z r7 = new W8.z
            q7.h r2 = r0.getContext()
            r7.<init>(r6, r2)
            r0.l = r7     // Catch: java.lang.Throwable -> L82
            r0.o = r4     // Catch: java.lang.Throwable -> L82
            java.lang.Object r5 = r5.f9624m     // Catch: java.lang.Throwable -> L7e
            s7.i r5 = (s7.i) r5     // Catch: java.lang.Throwable -> L7e
            java.lang.Object r5 = r5.invoke(r7, r0)     // Catch: java.lang.Throwable -> L7e
            if (r5 != r1) goto L74
            goto L75
        L74:
            r5 = r3
        L75:
            if (r5 != r1) goto L78
            goto L7d
        L78:
            r5 = r7
        L79:
            r5.releaseIntercepted()
            r1 = r3
        L7d:
            return r1
        L7e:
            r5 = move-exception
            r6 = r5
        L80:
            r5 = r7
            goto L84
        L82:
            r6 = move-exception
            goto L80
        L84:
            r5.releaseIntercepted()
            throw r6
        L88:
            A3.m r0 = new A3.m
            r1 = 13
            r0.<init>(r6, r1)
            java.lang.Object r5 = r5.f9624m
            W8.n r5 = (W8.n) r5
            java.lang.Object r5 = r5.b(r0, r7)
            r7.a r6 = r7.EnumC2303a.l
            if (r5 != r6) goto L9c
            goto L9e
        L9c:
            m7.q r5 = m7.q.f19267a
        L9e:
            return r5
        L9f:
            java.lang.Object r5 = r5.f9624m
            V8.U r5 = (V8.U) r5
            java.lang.Object r5 = r6.f(r5, r7)
            r7.a r6 = r7.EnumC2303a.l
            if (r5 != r6) goto Lac
            goto Lae
        Lac:
            m7.q r5 = m7.q.f19267a
        Lae:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.C0819h.b(V8.g, q7.c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0819h(InterfaceC2785n interfaceC2785n) {
        this.l = 2;
        this.f9624m = (s7.i) interfaceC2785n;
    }
}
