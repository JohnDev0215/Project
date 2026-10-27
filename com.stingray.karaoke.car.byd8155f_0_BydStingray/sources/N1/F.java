package N1;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class F extends s7.i implements InterfaceC2785n {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4463m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ boolean f4464n;
    public final /* synthetic */ Q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f4465p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(Q q4, int i7, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = q4;
        this.f4465p = i7;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        F f10 = new F(this.o, this.f4465p, interfaceC2189c);
        f10.f4464n = ((Boolean) obj).booleanValue();
        return f10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((F) create(bool, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.f4463m
            N1.Q r2 = r6.o
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L22
            if (r1 == r4) goto L1c
            if (r1 != r3) goto L14
            java.lang.Object r6 = r6.l
            h8.AbstractC1386c.u(r7)
            goto L46
        L14:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1c:
            boolean r1 = r6.f4464n
            h8.AbstractC1386c.u(r7)
            goto L32
        L22:
            h8.AbstractC1386c.u(r7)
            boolean r1 = r6.f4464n
            r6.f4464n = r1
            r6.f4463m = r4
            java.lang.Object r7 = r2.h(r6)
            if (r7 != r0) goto L32
            return r0
        L32:
            if (r1 == 0) goto L4d
            N1.b0 r1 = r2.f()
            r6.l = r7
            r6.f4463m = r3
            java.lang.Integer r6 = r1.a()
            if (r6 != r0) goto L43
            return r0
        L43:
            r5 = r7
            r7 = r6
            r6 = r5
        L46:
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            goto L52
        L4d:
            int r6 = r6.f4465p
            r5 = r7
            r7 = r6
            r6 = r5
        L52:
            N1.d r0 = new N1.d
            if (r6 == 0) goto L5b
            int r1 = r6.hashCode()
            goto L5c
        L5b:
            r1 = 0
        L5c:
            r0.<init>(r1, r7, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.F.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
