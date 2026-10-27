package P5;

import E.D;
import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5392m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ D f5393n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ p f5394p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(D d, boolean z6, p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f5393n = d;
        this.o = z6;
        this.f5394p = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r(this.f5393n, this.o, this.f5394p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0068 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0066 -> B:7:0x0013). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f5392m
            r2 = 1
            r3 = 2
            E.D r4 = r8.f5393n
            if (r1 == 0) goto L23
            if (r1 == r2) goto L1d
            if (r1 != r3) goto L15
            float r1 = r8.l
            h8.AbstractC1386c.u(r9)
        L13:
            r9 = r1
            goto L45
        L15:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L1d:
            float r1 = r8.l
            h8.AbstractC1386c.u(r9)
            goto L57
        L23:
            h8.AbstractC1386c.u(r9)
            E.t r9 = r4.h()
            boolean r9 = r9.f1418n
            r1 = r9 ^ 1
            boolean r5 = r8.o
            if (r5 == 0) goto L3d
            E.t r5 = r4.h()
            A.h0 r5 = r5.o
            A.h0 r6 = A.EnumC0017h0.l
            if (r5 == r6) goto L3d
            goto L3e
        L3d:
            r9 = r1
        L3e:
            if (r9 == 0) goto L43
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L45
        L43:
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
        L45:
            P5.p r1 = r8.f5394p
            U8.l r1 = r1.f5386i
            r8.l = r9
            r8.f5392m = r2
            java.lang.Object r1 = r1.c(r8)
            if (r1 != r0) goto L54
            return r0
        L54:
            r7 = r1
            r1 = r9
            r9 = r7
        L57:
            java.lang.Number r9 = (java.lang.Number) r9
            float r9 = r9.floatValue()
            float r9 = r9 * r1
            r8.l = r1
            r8.f5392m = r3
            java.lang.Object r9 = H7.H.O(r4, r9, r8)
            if (r9 != r0) goto L13
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: P5.r.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
