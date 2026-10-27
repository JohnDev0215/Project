package P5;

import q7.InterfaceC2189c;
import y0.C2722B;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5343m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p f5344n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5344n = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        b bVar = new b(this.f5344n, interfaceC2189c);
        bVar.f5343m = obj;
        return bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((b) create((C2722B) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0050  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r5.l
            r2 = 1
            r3 = 2
            if (r1 == 0) goto L24
            if (r1 == r2) goto L1c
            if (r1 != r3) goto L14
            java.lang.Object r0 = r5.f5343m
            y0.q r0 = (y0.q) r0
            h8.AbstractC1386c.u(r6)
            goto L4c
        L14:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1c:
            java.lang.Object r1 = r5.f5343m
            y0.B r1 = (y0.C2722B) r1
            h8.AbstractC1386c.u(r6)
            goto L3d
        L24:
            h8.AbstractC1386c.u(r6)
            java.lang.Object r6 = r5.f5343m
            r1 = r6
            y0.B r1 = (y0.C2722B) r1
            P5.a r6 = new P5.a
            r4 = 0
            r6.<init>(r4)
            r5.f5343m = r1
            r5.l = r2
            java.lang.Object r6 = r1.H0(r6, r5)
            if (r6 != r0) goto L3d
            return r0
        L3d:
            y0.q r6 = (y0.q) r6
            r5.f5343m = r6
            r5.l = r3
            java.lang.Object r1 = C7.a.x(r1, r6, r5)
            if (r1 != r0) goto L4a
            return r0
        L4a:
            r0 = r6
            r6 = r1
        L4c:
            y0.q r6 = (y0.q) r6
            if (r6 == 0) goto L5e
            P5.p r5 = r5.f5344n
            P5.z r6 = new P5.z
            long r0 = r0.f24077a
            r6.<init>(r0)
            U8.l r5 = r5.f5385h
            r5.p(r6)
        L5e:
            m7.q r5 = m7.q.f19267a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: P5.b.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
