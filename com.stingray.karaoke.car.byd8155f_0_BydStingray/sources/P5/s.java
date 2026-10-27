package P5;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class s extends s7.i implements InterfaceC2785n {
    public A7.w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.y f5395m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5396n;
    public final /* synthetic */ float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ p f5397p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(float f10, p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = f10;
        this.f5397p = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new s(this.o, this.f5397p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((s) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005f A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005d -> B:19:0x0060). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f5396n
            r2 = 1
            P5.p r3 = r8.f5397p
            if (r1 == 0) goto L1b
            if (r1 != r2) goto L13
            A7.y r1 = r8.f5395m
            A7.w r4 = r8.l
            h8.AbstractC1386c.u(r9)
            goto L60
        L13:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L1b:
            h8.AbstractC1386c.u(r9)
            A7.w r9 = new A7.w
            r9.<init>()
            float r1 = r8.o
            r9.l = r1
            A7.y r1 = new A7.y
            r1.<init>()
            r4 = r9
        L2d:
            float r9 = r4.l
            r5 = 0
            int r9 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r9 != 0) goto L35
            goto L71
        L35:
            S8.k0 r9 = r3.l
            if (r9 == 0) goto L71
            boolean r9 = r9.b()
            if (r9 != r2) goto L71
            A3.y r9 = new A3.y
            r5 = 1
            r9.<init>(r1, r4, r3, r5)
            r8.l = r4
            r8.f5395m = r1
            r8.f5396n = r2
            q7.h r5 = r8.getContext()
            T.T r5 = T.C0735d.I(r5)
            F8.u r6 = new F8.u
            r7 = 1
            r6.<init>(r7, r9)
            java.lang.Object r9 = r5.o(r6, r8)
            if (r9 != r0) goto L60
            return r0
        L60:
            U8.l r9 = r3.f5386i
            float r5 = r4.l
            java.lang.Float r6 = new java.lang.Float
            r6.<init>(r5)
            r9.p(r6)
            r9 = 0
            r3.r(r9, r9, r9)
            goto L2d
        L71:
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: P5.s.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
