package Q5;

import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0602o0 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7004m;

    public /* synthetic */ C0602o0(Object obj, int i7) {
        this.l = i7;
        this.f7004m = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // z7.InterfaceC2785n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invoke(java.lang.Object r3, java.lang.Object r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.f7004m
            int r2 = r2.l
            switch(r2) {
                case 0: goto L8c;
                case 1: goto L78;
                default: goto L7;
            }
        L7:
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r2 = r3.intValue()
            q7.f r4 = (q7.InterfaceC2192f) r4
            q7.g r3 = r4.getKey()
            W8.z r0 = (W8.z) r0
            q7.h r0 = r0.f9944m
            q7.f r0 = r0.F(r3)
            S8.t r1 = S8.C0722t.f8137m
            if (r3 == r1) goto L27
            if (r4 == r0) goto L24
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L3c
        L24:
            int r2 = r2 + 1
            goto L3c
        L27:
            r1 = r0
            S8.W r1 = (S8.W) r1
            S8.W r4 = (S8.W) r4
        L2c:
            r3 = 0
            if (r4 != 0) goto L31
            r4 = r3
            goto L38
        L31:
            if (r4 != r1) goto L34
            goto L38
        L34:
            boolean r0 = r4 instanceof X8.q
            if (r0 != 0) goto L66
        L38:
            if (r4 != r1) goto L41
            if (r1 != 0) goto L24
        L3c:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            return r2
        L41:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of "
            r3.<init>(r0)
            r3.append(r4)
            java.lang.String r4 = ", expected child of "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L66:
            X8.q r4 = (X8.q) r4
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = S8.d0.f8111m
            java.lang.Object r4 = r0.get(r4)
            S8.l r4 = (S8.InterfaceC0715l) r4
            if (r4 == 0) goto L76
            S8.W r3 = r4.getParent()
        L76:
            r4 = r3
            goto L2c
        L78:
            java.lang.Float r3 = (java.lang.Float) r3
            r3.floatValue()
            java.lang.Float r4 = (java.lang.Float) r4
            r4.getClass()
            V5.h r2 = V5.g.f9506a
            T.X r0 = (T.X) r0
            r0.setValue(r3)
            m7.q r2 = m7.q.f19267a
            return r2
        L8c:
            F.t r3 = (F.t) r3
            Q5.T2 r4 = (Q5.T2) r4
            java.lang.String r2 = "$this$items"
            A7.m.f(r3, r2)
            java.lang.String r2 = "item"
            A7.m.f(r4, r2)
            A7.x r0 = (A7.x) r0
            int r2 = r0.l
            int r3 = r4.f6573b
            if (r3 < r2) goto La9
            int r2 = F.t.f1957b
            long r2 = P8.n.d(r2)
            goto Lad
        La9:
            long r2 = P8.n.d(r3)
        Lad:
            F.b r4 = new F.b
            r4.<init>(r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: Q5.C0602o0.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
