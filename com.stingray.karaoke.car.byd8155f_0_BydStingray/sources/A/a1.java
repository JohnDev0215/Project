package A;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class a1 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f141m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f142n;
    public final /* synthetic */ InterfaceC0725w o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s7.i f143p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ A7.o f144q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0023k0 f145r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public a1(InterfaceC0725w interfaceC0725w, InterfaceC2786o interfaceC2786o, InterfaceC2782k interfaceC2782k, C0023k0 c0023k0, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = interfaceC0725w;
        this.f143p = (s7.i) interfaceC2786o;
        this.f144q = (A7.o) interfaceC2782k;
        this.f145r = c0023k0;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [s7.i, z7.o] */
    /* JADX WARN: Type inference failed for: r3v0, types: [A7.o, z7.k] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        ?? r32 = this.f144q;
        C0023k0 c0023k0 = this.f145r;
        a1 a1Var = new a1(this.o, this.f143p, r32, c0023k0, interfaceC2189c);
        a1Var.f142n = obj;
        return a1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((a1) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Type inference failed for: r8v0, types: [s7.i, z7.o] */
    /* JADX WARN: Type inference failed for: r9v1, types: [A7.o, z7.k] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            r9 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r9.f141m
            A.k0 r2 = r9.f145r
            S8.w r3 = r9.o
            r4 = 3
            r5 = 0
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L26
            if (r1 == r7) goto L1e
            if (r1 != r6) goto L16
            h8.AbstractC1386c.u(r10)
            goto L61
        L16:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L1e:
            java.lang.Object r1 = r9.f142n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r10)
            goto L41
        L26:
            h8.AbstractC1386c.u(r10)
            java.lang.Object r10 = r9.f142n
            r1 = r10
            y0.z r1 = (y0.z) r1
            A.W0 r10 = new A.W0
            r10.<init>(r2, r5)
            S8.AbstractC0727y.w(r3, r5, r5, r10, r4)
            r9.f142n = r1
            r9.f141m = r7
            java.lang.Object r10 = A.r1.c(r1, r9, r4)
            if (r10 != r0) goto L41
            return r0
        L41:
            y0.q r10 = (y0.q) r10
            r10.a()
            A.W r7 = A.r1.f263a
            s7.i r8 = r9.f143p
            if (r8 == r7) goto L54
            A.X0 r7 = new A.X0
            r7.<init>(r8, r2, r10, r5)
            S8.AbstractC0727y.w(r3, r5, r5, r7, r4)
        L54:
            r9.f142n = r5
            r9.f141m = r6
            y0.i r10 = y0.EnumC2731i.f24067m
            java.lang.Object r10 = A.r1.e(r1, r10, r9)
            if (r10 != r0) goto L61
            return r0
        L61:
            y0.q r10 = (y0.q) r10
            if (r10 != 0) goto L6e
            A.Y0 r9 = new A.Y0
            r9.<init>(r2, r5)
            S8.AbstractC0727y.w(r3, r5, r5, r9, r4)
            goto L85
        L6e:
            r10.a()
            A.Z0 r0 = new A.Z0
            r0.<init>(r2, r5)
            S8.AbstractC0727y.w(r3, r5, r5, r0, r4)
            A7.o r9 = r9.f144q
            m0.c r0 = new m0.c
            long r1 = r10.c
            r0.<init>(r1)
            r9.b(r0)
        L85:
            m7.q r9 = m7.q.f19267a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: A.a1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
