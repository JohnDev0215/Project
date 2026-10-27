package V8;

import q7.InterfaceC2189c;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends s7.i implements InterfaceC2786o {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ InterfaceC0818g f9606m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ int f9607n;
    public final /* synthetic */ c0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(c0 c0Var, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.o = c0Var;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj2).intValue();
        a0 a0Var = new a0(this.o, (InterfaceC2189c) obj3);
        a0Var.f9606m = (InterfaceC0818g) obj;
        a0Var.f9607n = iIntValue;
        return a0Var.invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0086 A[RETURN] */
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
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            V8.c0 r7 = r10.o
            if (r1 == 0) goto L36
            if (r1 == r6) goto L32
            if (r1 == r5) goto L2c
            if (r1 == r4) goto L26
            if (r1 == r3) goto L20
            if (r1 != r2) goto L18
            goto L32
        L18:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L20:
            V8.g r1 = r10.f9606m
            h8.AbstractC1386c.u(r11)
            goto L79
        L26:
            V8.g r1 = r10.f9606m
            h8.AbstractC1386c.u(r11)
            goto L6c
        L2c:
            V8.g r1 = r10.f9606m
            h8.AbstractC1386c.u(r11)
            goto L57
        L32:
            h8.AbstractC1386c.u(r11)
            goto L87
        L36:
            h8.AbstractC1386c.u(r11)
            V8.g r1 = r10.f9606m
            int r11 = r10.f9607n
            if (r11 <= 0) goto L4a
            V8.U r11 = V8.U.l
            r10.l = r6
            java.lang.Object r10 = r1.f(r11, r10)
            if (r10 != r0) goto L87
            return r0
        L4a:
            long r8 = r7.f9610a
            r10.f9606m = r1
            r10.l = r5
            java.lang.Object r11 = S8.AbstractC0727y.i(r8, r10)
            if (r11 != r0) goto L57
            return r0
        L57:
            long r5 = r7.f9611b
            r8 = 0
            int r11 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r11 <= 0) goto L79
            V8.U r11 = V8.U.f9595m
            r10.f9606m = r1
            r10.l = r4
            java.lang.Object r11 = r1.f(r11, r10)
            if (r11 != r0) goto L6c
            return r0
        L6c:
            long r4 = r7.f9611b
            r10.f9606m = r1
            r10.l = r3
            java.lang.Object r11 = S8.AbstractC0727y.i(r4, r10)
            if (r11 != r0) goto L79
            return r0
        L79:
            V8.U r11 = V8.U.f9596n
            r3 = 0
            r10.f9606m = r3
            r10.l = r2
            java.lang.Object r10 = r1.f(r11, r10)
            if (r10 != r0) goto L87
            return r0
        L87:
            m7.q r10 = m7.q.f19267a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.a0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
