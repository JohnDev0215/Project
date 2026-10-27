package y0;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class x extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f24104m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f24105n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(long j3, z zVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f24104m = j3;
        this.f24105n = zVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new x(this.f24104m, this.f24105n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((x) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
        /*
            r10 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r10.l
            r2 = 1
            long r4 = r10.f24104m
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L20
            if (r1 == r7) goto L1c
            if (r1 != r6) goto L14
            h8.AbstractC1386c.u(r11)
            goto L37
        L14:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L1c:
            h8.AbstractC1386c.u(r11)
            goto L2e
        L20:
            h8.AbstractC1386c.u(r11)
            long r8 = r4 - r2
            r10.l = r7
            java.lang.Object r11 = S8.AbstractC0727y.i(r8, r10)
            if (r11 != r0) goto L2e
            return r0
        L2e:
            r10.l = r6
            java.lang.Object r11 = S8.AbstractC0727y.i(r2, r10)
            if (r11 != r0) goto L37
            return r0
        L37:
            y0.z r10 = r10.f24105n
            S8.i r10 = r10.f24109n
            if (r10 == 0) goto L49
            y0.j r11 = new y0.j
            r11.<init>(r4)
            m7.k r11 = h8.AbstractC1386c.f(r11)
            r10.resumeWith(r11)
        L49:
            m7.q r10 = m7.q.f19267a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: y0.x.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
