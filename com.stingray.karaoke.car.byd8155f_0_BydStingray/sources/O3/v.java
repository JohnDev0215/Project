package o3;

import S8.InterfaceC0725w;
import n3.C1945j;
import q7.InterfaceC2189c;
import x.C2634f0;
import x.x0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class v extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f20502m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f20503n;
    public final /* synthetic */ C1945j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ x0 f20504p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(C2634f0 c2634f0, C1945j c1945j, x0 x0Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20503n = c2634f0;
        this.o = c1945j;
        this.f20504p = x0Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        v vVar = new v(this.f20503n, this.o, this.f20504p, interfaceC2189c);
        vVar.f20502m = obj;
        return vVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((v) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d A[RETURN] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
        /*
            r14 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r14.l
            m7.q r2 = m7.q.f19267a
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L1b
            if (r1 == r4) goto L17
            if (r1 != r3) goto Lf
            goto L17
        Lf:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L17:
            h8.AbstractC1386c.u(r15)
            goto L87
        L1b:
            h8.AbstractC1386c.u(r15)
            java.lang.Object r15 = r14.f20502m
            S8.w r15 = (S8.InterfaceC0725w) r15
            x.f0 r1 = r14.f20503n
            T.e0 r5 = r1.o
            java.lang.Object r5 = r5.getValue()
            n3.j r6 = r14.o
            boolean r5 = A7.m.a(r5, r6)
            if (r5 != 0) goto L4e
            r14.l = r4
            x.x0 r10 = r1.f23521q
            if (r10 != 0) goto L3a
        L38:
            r14 = r2
            goto L4b
        L3a:
            x.X r15 = new x.X
            r8 = 0
            r7 = 0
            r5 = r15
            r9 = r1
            r5.<init>(r6, r7, r8, r9, r10)
            x.T r1 = r1.f23527w
            java.lang.Object r14 = x.T.a(r1, r15, r14)
            if (r14 != r0) goto L38
        L4b:
            if (r14 != r0) goto L87
            return r0
        L4e:
            x.x0 r4 = r14.f20504p
            T.D r4 = r4.l
            java.lang.Object r4 = r4.getValue()
            java.lang.Number r4 = (java.lang.Number) r4
            long r4 = r4.longValue()
            r7 = 1000000(0xf4240, float:1.401298E-39)
            long r7 = (long) r7
            long r4 = r4 / r7
            T.a0 r7 = r1.f23524t
            float r8 = r7.i()
            float r7 = r7.i()
            float r4 = (float) r4
            float r7 = r7 * r4
            int r4 = (int) r7
            r5 = 6
            r7 = 0
            r9 = 0
            x.C0 r10 = x.AbstractC2631e.q(r4, r9, r7, r5)
            A.v0 r11 = new A.v0
            r4 = 6
            r11.<init>(r15, r1, r6, r4)
            r14.l = r3
            r9 = 0
            r13 = 4
            r12 = r14
            java.lang.Object r14 = x.AbstractC2631e.e(r8, r9, r10, r11, r12, r13)
            if (r14 != r0) goto L87
            return r0
        L87:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: o3.v.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
