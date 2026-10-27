package x;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2635g extends s7.i implements InterfaceC2785n {
    public U8.d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f23531m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f23532n;
    public final /* synthetic */ U8.p o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2629d f23533p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T.X f23534q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T.X f23535r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2635g(U8.p pVar, C2629d c2629d, T.X x8, T.X x9, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = pVar;
        this.f23533p = c2629d;
        this.f23534q = x8;
        this.f23535r = x9;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2635g c2635g = new C2635g(this.o, this.f23533p, this.f23534q, this.f23535r, interfaceC2189c);
        c2635g.f23532n = obj;
        return c2635g;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2635g) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0033 -> B:12:0x0036). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
        /*
            r12 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r12.f23531m
            U8.p r2 = r12.o
            r3 = 1
            if (r1 == 0) goto L1d
            if (r1 != r3) goto L15
            U8.d r1 = r12.l
            java.lang.Object r4 = r12.f23532n
            S8.w r4 = (S8.InterfaceC0725w) r4
            h8.AbstractC1386c.u(r13)
            goto L36
        L15:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L1d:
            h8.AbstractC1386c.u(r13)
            java.lang.Object r13 = r12.f23532n
            S8.w r13 = (S8.InterfaceC0725w) r13
            U8.d r1 = r2.iterator()
            r4 = r13
        L29:
            r12.f23532n = r4
            r12.l = r1
            r12.f23531m = r3
            java.lang.Object r13 = r1.a(r12)
            if (r13 != r0) goto L36
            return r0
        L36:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L62
            java.lang.Object r13 = r1.c()
            java.lang.Object r5 = r2.h()
            java.lang.Object r5 = U8.s.a(r5)
            if (r5 != 0) goto L4e
            r7 = r13
            goto L4f
        L4e:
            r7 = r5
        L4f:
            x.f r13 = new x.f
            T.X r10 = r12.f23535r
            T.X r9 = r12.f23534q
            x.d r8 = r12.f23533p
            r11 = 0
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            r5 = 3
            r6 = 0
            S8.AbstractC0727y.w(r4, r6, r6, r13, r5)
            goto L29
        L62:
            m7.q r12 = m7.q.f19267a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2635g.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
