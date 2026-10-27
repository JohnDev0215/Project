package A;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Q0 extends s7.i implements InterfaceC2785n {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f93m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ long f94n;
    public final /* synthetic */ S0 o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q0(S0 s02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = s02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        Q0 q02 = new Q0(this.o, interfaceC2189c);
        q02.f94n = ((a1.o) obj).f11044a;
        return q02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        long j3 = ((a1.o) obj).f11044a;
        Q0 q02 = new Q0(this.o, (InterfaceC2189c) obj2);
        q02.f94n = j3;
        return q02.invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            r7.a r6 = r7.EnumC2303a.l
            int r0 = r11.f93m
            r1 = 3
            r2 = 2
            r3 = 1
            A.S0 r4 = r11.o
            if (r0 == 0) goto L33
            if (r0 == r3) goto L2c
            if (r0 == r2) goto L23
            if (r0 != r1) goto L1b
            long r0 = r11.l
            long r2 = r11.f94n
            h8.AbstractC1386c.u(r12)
            r9 = r0
            r0 = r12
            goto L76
        L1b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L23:
            long r2 = r11.l
            long r7 = r11.f94n
            h8.AbstractC1386c.u(r12)
            r0 = r12
            goto L5b
        L2c:
            long r7 = r11.f94n
            h8.AbstractC1386c.u(r12)
            r0 = r12
            goto L45
        L33:
            h8.AbstractC1386c.u(r12)
            long r7 = r11.f94n
            x0.d r0 = r4.f103f
            r11.f94n = r7
            r11.f93m = r3
            java.lang.Object r0 = r0.b(r7, r11)
            if (r0 != r6) goto L45
            return r6
        L45:
            a1.o r0 = (a1.o) r0
            long r9 = r0.f11044a
            long r9 = a1.o.d(r7, r9)
            r11.f94n = r7
            r11.l = r9
            r11.f93m = r2
            java.lang.Object r0 = r4.b(r9, r11)
            if (r0 != r6) goto L5a
            return r6
        L5a:
            r2 = r9
        L5b:
            a1.o r0 = (a1.o) r0
            long r9 = r0.f11044a
            x0.d r0 = r4.f103f
            long r2 = a1.o.d(r2, r9)
            r11.f94n = r7
            r11.l = r9
            r11.f93m = r1
            r1 = r2
            r3 = r9
            r5 = r11
            java.lang.Object r0 = r0.a(r1, r3, r5)
            if (r0 != r6) goto L75
            return r6
        L75:
            r2 = r7
        L76:
            a1.o r0 = (a1.o) r0
            long r0 = r0.f11044a
            long r0 = a1.o.d(r9, r0)
            long r0 = a1.o.d(r2, r0)
            a1.o r2 = new a1.o
            r2.<init>(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: A.Q0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
