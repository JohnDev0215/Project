package K;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0347j0 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public y0.q f3300m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3301n;
    public /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3302p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0347j0(InterfaceC0355n0 interfaceC0355n0, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f3302p = interfaceC0355n0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0347j0 c0347j0 = new C0347j0(this.f3302p, interfaceC2189c);
        c0347j0.o = obj;
        return c0347j0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0347j0) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0052 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARN: Type inference failed for: r13v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0050 -> B:17:0x0053). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
        /*
            r12 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r12.f3301n
            r2 = 1
            r3 = 2
            K.n0 r4 = r12.f3302p
            if (r1 == 0) goto L28
            if (r1 == r2) goto L20
            if (r1 != r3) goto L18
            y0.q r1 = r12.f3300m
            java.lang.Object r2 = r12.o
            y0.z r2 = (y0.z) r2
            h8.AbstractC1386c.u(r13)
            goto L53
        L18:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L20:
            java.lang.Object r1 = r12.o
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r13)
            goto L3b
        L28:
            h8.AbstractC1386c.u(r13)
            java.lang.Object r13 = r12.o
            r1 = r13
            y0.z r1 = (y0.z) r1
            r12.o = r1
            r12.f3301n = r2
            java.lang.Object r13 = A.r1.c(r1, r12, r3)
            if (r13 != r0) goto L3b
            return r0
        L3b:
            y0.q r13 = (y0.q) r13
            long r5 = r13.c
            r4.d()
            r2 = r1
            r1 = r13
        L44:
            r12.o = r2
            r12.f3300m = r1
            r12.f3301n = r3
            y0.i r13 = y0.EnumC2731i.f24067m
            java.lang.Object r13 = r2.a(r13, r12)
            if (r13 != r0) goto L53
            return r0
        L53:
            y0.h r13 = (y0.C2730h) r13
            java.lang.Object r13 = r13.f24065a
            int r5 = r13.size()
            r6 = 0
        L5c:
            if (r6 >= r5) goto L76
            java.lang.Object r7 = r13.get(r6)
            y0.q r7 = (y0.q) r7
            long r8 = r7.f24077a
            long r10 = r1.f24077a
            boolean r8 = y0.C2738p.a(r8, r10)
            if (r8 == 0) goto L73
            boolean r7 = r7.d
            if (r7 == 0) goto L73
            goto L44
        L73:
            int r6 = r6 + 1
            goto L5c
        L76:
            r4.b()
            m7.q r12 = m7.q.f19267a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: K.C0347j0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
