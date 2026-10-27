package k6;

import S8.InterfaceC0725w;
import T.X;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: k6.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1706n extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ v f18282m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f18283n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1706n(v vVar, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f18282m = vVar;
        this.f18283n = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1706n(this.f18282m, this.f18283n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1706n) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.l
            T.X r2 = r6.f18283n
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L1e
            if (r1 == r4) goto L1a
            if (r1 != r3) goto L12
            h8.AbstractC1386c.u(r7)
            goto L43
        L12:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1a:
            h8.AbstractC1386c.u(r7)
            goto L33
        L1e:
            h8.AbstractC1386c.u(r7)
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            r2.setValue(r7)
            k6.v r7 = r6.f18282m
            r6.l = r4
            long r4 = r7.f18300a
            java.lang.Object r7 = S8.AbstractC0727y.i(r4, r6)
            if (r7 != r0) goto L33
            return r0
        L33:
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            r2.setValue(r7)
            r6.l = r3
            r1 = 800(0x320, double:3.953E-321)
            java.lang.Object r6 = S8.AbstractC0727y.i(r1, r6)
            if (r6 != r0) goto L43
            return r0
        L43:
            io.sentry.hints.i r6 = k6.o.f18284a
            e0.r r6 = k6.o.d
            java.lang.Object r7 = n7.AbstractC1998m.a0(r6)
            k6.v r7 = (k6.v) r7
            if (r7 == 0) goto L58
            r0 = 0
            k6.o.f18286e = r0
            r6.remove(r7)
            io.sentry.hints.i.o()
        L58:
            m7.q r6 = m7.q.f19267a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: k6.C1706n.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
