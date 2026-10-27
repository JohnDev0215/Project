package K;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class w0 extends s7.i implements InterfaceC2785n {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3448m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ T.X f3449n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C.k f3450p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(T.X x8, long j3, C.k kVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3449n = x8;
        this.o = j3;
        this.f3450p = kVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new w0(this.f3449n, this.o, this.f3450p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((w0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f3448m
            C.k r2 = r8.f3450p
            r3 = 2
            r4 = 1
            T.X r5 = r8.f3449n
            if (r1 == 0) goto L28
            if (r1 == r4) goto L20
            if (r1 != r3) goto L18
            java.lang.Object r8 = r8.l
            C.m r8 = (C.m) r8
            h8.AbstractC1386c.u(r9)
            goto L5f
        L18:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L20:
            java.lang.Object r1 = r8.l
            T.X r1 = (T.X) r1
            h8.AbstractC1386c.u(r9)
            goto L46
        L28:
            h8.AbstractC1386c.u(r9)
            java.lang.Object r9 = r5.getValue()
            C.m r9 = (C.m) r9
            if (r9 == 0) goto L4a
            C.l r1 = new C.l
            r1.<init>(r9)
            if (r2 == 0) goto L45
            r8.l = r5
            r8.f3448m = r4
            java.lang.Object r9 = r2.a(r1, r8)
            if (r9 != r0) goto L45
            return r0
        L45:
            r1 = r5
        L46:
            r9 = 0
            r1.setValue(r9)
        L4a:
            C.m r9 = new C.m
            long r6 = r8.o
            r9.<init>(r6)
            if (r2 == 0) goto L60
            r8.l = r9
            r8.f3448m = r3
            java.lang.Object r8 = r2.a(r9, r8)
            if (r8 != r0) goto L5e
            return r0
        L5e:
            r8 = r9
        L5f:
            r9 = r8
        L60:
            r5.setValue(r9)
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: K.w0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
