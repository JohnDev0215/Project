package M;

import S8.InterfaceC0725w;
import S8.W;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class l extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ W f4143m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f4144n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(W w10, n nVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4143m = w10;
        this.f4144n = nVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new l(this.f4143m, this.f4144n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((l) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0049 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0055 -> B:21:0x003a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            r9 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r9.l
            r2 = 0
            r3 = 500(0x1f4, double:2.47E-321)
            r5 = 3
            r6 = 2
            r7 = 1
            M.n r8 = r9.f4144n
            if (r1 == 0) goto L2a
            if (r1 == r7) goto L26
            if (r1 == r6) goto L22
            if (r1 != r5) goto L1a
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L18
            goto L3a
        L18:
            r9 = move-exception
            goto L58
        L1a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L22:
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L18
            goto L4a
        L26:
            h8.AbstractC1386c.u(r10)
            goto L3a
        L2a:
            h8.AbstractC1386c.u(r10)
            S8.W r10 = r9.f4143m
            if (r10 == 0) goto L3a
            r9.l = r7
            java.lang.Object r10 = S8.AbstractC0727y.g(r10, r9)
            if (r10 != r0) goto L3a
            return r0
        L3a:
            T.a0 r10 = r8.f4147b     // Catch: java.lang.Throwable -> L18
            r1 = 1065353216(0x3f800000, float:1.0)
            r10.j(r1)     // Catch: java.lang.Throwable -> L18
            r9.l = r6     // Catch: java.lang.Throwable -> L18
            java.lang.Object r10 = S8.AbstractC0727y.i(r3, r9)     // Catch: java.lang.Throwable -> L18
            if (r10 != r0) goto L4a
            return r0
        L4a:
            T.a0 r10 = r8.f4147b     // Catch: java.lang.Throwable -> L18
            r10.j(r2)     // Catch: java.lang.Throwable -> L18
            r9.l = r5     // Catch: java.lang.Throwable -> L18
            java.lang.Object r10 = S8.AbstractC0727y.i(r3, r9)     // Catch: java.lang.Throwable -> L18
            if (r10 != r0) goto L3a
            return r0
        L58:
            T.a0 r10 = r8.f4147b
            r10.j(r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: M.l.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
