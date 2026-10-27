package o3;

import S8.InterfaceC0725w;
import n3.C1945j;
import q7.InterfaceC2189c;
import x.C2634f0;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class u extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f20500m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2634f0 f20501n;
    public final /* synthetic */ C1945j o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(float f10, C2634f0 c2634f0, C1945j c1945j, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20500m = f10;
        this.f20501n = c2634f0;
        this.o = c1945j;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new u(this.f20500m, this.f20501n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((u) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070 A[RETURN] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.l
            m7.q r2 = m7.q.f19267a
            x.f0 r3 = r8.f20501n
            r4 = 0
            float r5 = r8.f20500m
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L23
            if (r1 == r7) goto L1f
            if (r1 != r6) goto L17
            h8.AbstractC1386c.u(r9)
            goto L71
        L17:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L1f:
            h8.AbstractC1386c.u(r9)
            goto L39
        L23:
            h8.AbstractC1386c.u(r9)
            int r9 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r9 <= 0) goto L39
            r8.l = r7
            T.e0 r9 = r3.f23519n
            java.lang.Object r9 = r9.getValue()
            java.lang.Object r9 = r3.h1(r5, r9, r8)
            if (r9 != r0) goto L39
            return r0
        L39:
            int r9 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r9 != 0) goto L71
            r8.l = r6
            x.x0 r9 = r3.f23521q
            if (r9 != 0) goto L45
        L43:
            r8 = r2
            goto L6e
        L45:
            T.e0 r1 = r3.o
            java.lang.Object r1 = r1.getValue()
            n3.j r4 = r8.o
            boolean r1 = A7.m.a(r1, r4)
            if (r1 == 0) goto L60
            T.e0 r1 = r3.f23519n
            java.lang.Object r1 = r1.getValue()
            boolean r1 = A7.m.a(r1, r4)
            if (r1 == 0) goto L60
            goto L43
        L60:
            x.c0 r1 = new x.c0
            r5 = 0
            r1.<init>(r3, r4, r9, r5)
            x.T r9 = r3.f23527w
            java.lang.Object r8 = x.T.a(r9, r1, r8)
            if (r8 != r0) goto L43
        L6e:
            if (r8 != r0) goto L71
            return r0
        L71:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: o3.u.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
