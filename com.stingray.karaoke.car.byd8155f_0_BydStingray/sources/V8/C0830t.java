package V8;

import z7.InterfaceC2786o;

/* JADX INFO: renamed from: V8.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0830t implements InterfaceC0817f {
    public final /* synthetic */ InterfaceC0817f l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s7.i f9652m;

    /* JADX WARN: Multi-variable type inference failed */
    public C0830t(InterfaceC0817f interfaceC0817f, InterfaceC2786o interfaceC2786o) {
        this.l = interfaceC0817f;
        this.f9652m = (s7.i) interfaceC2786o;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r8v5, types: [s7.i, z7.o] */
    /* JADX WARN: Type inference failed for: r9v3, types: [s7.i, z7.o] */
    @Override // V8.InterfaceC0817f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(V8.InterfaceC0818g r9, q7.InterfaceC2189c r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof V8.C0829s
            if (r0 == 0) goto L13
            r0 = r10
            V8.s r0 = (V8.C0829s) r0
            int r1 = r0.f9649m
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9649m = r1
            goto L18
        L13:
            V8.s r0 = new V8.s
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f9649m
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L53
            if (r2 == r5) goto L44
            if (r2 == r4) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r8 = r0.o
            W8.z r8 = (W8.z) r8
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L32
            goto L7e
        L32:
            r9 = move-exception
            goto L86
        L34:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3c:
            java.lang.Object r8 = r0.o
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            h8.AbstractC1386c.u(r10)
            goto L9e
        L44:
            V8.g r9 = r0.f9651p
            java.lang.Object r8 = r0.o
            V8.t r8 = (V8.C0830t) r8
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L4e
            goto L65
        L4e:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L8a
        L53:
            h8.AbstractC1386c.u(r10)
            V8.f r10 = r8.l     // Catch: java.lang.Throwable -> L4e
            r0.o = r8     // Catch: java.lang.Throwable -> L4e
            r0.f9651p = r9     // Catch: java.lang.Throwable -> L4e
            r0.f9649m = r5     // Catch: java.lang.Throwable -> L4e
            java.lang.Object r10 = r10.b(r9, r0)     // Catch: java.lang.Throwable -> L4e
            if (r10 != r1) goto L65
            return r1
        L65:
            W8.z r10 = new W8.z
            q7.h r2 = r0.getContext()
            r10.<init>(r9, r2)
            s7.i r8 = r8.f9652m     // Catch: java.lang.Throwable -> L84
            r0.o = r10     // Catch: java.lang.Throwable -> L84
            r0.f9651p = r6     // Catch: java.lang.Throwable -> L84
            r0.f9649m = r3     // Catch: java.lang.Throwable -> L84
            java.lang.Object r8 = r8.g(r10, r6, r0)     // Catch: java.lang.Throwable -> L84
            if (r8 != r1) goto L7d
            return r1
        L7d:
            r8 = r10
        L7e:
            r8.releaseIntercepted()
            m7.q r8 = m7.q.f19267a
            return r8
        L84:
            r9 = move-exception
            r8 = r10
        L86:
            r8.releaseIntercepted()
            throw r9
        L8a:
            V8.h0 r10 = new V8.h0
            r10.<init>(r8)
            s7.i r9 = r9.f9652m
            r0.o = r8
            r0.f9651p = r6
            r0.f9649m = r4
            java.lang.Object r9 = V8.AbstractC0828q.d(r10, r9, r8, r0)
            if (r9 != r1) goto L9e
            return r1
        L9e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.C0830t.b(V8.g, q7.c):java.lang.Object");
    }
}
