package A;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class A0 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ J0 f0m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f1n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A0(J0 j02, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f0m = j02;
        this.f1n = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new A0(this.f0m, this.f1n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((A0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065 A[RETURN] */
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
            r3 = 1
            if (r1 == 0) goto L17
            if (r1 != r3) goto Lf
            h8.AbstractC1386c.u(r9)
            goto L66
        Lf:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L17:
            h8.AbstractC1386c.u(r9)
            A.J0 r9 = r8.f0m
            A.S0 r9 = r9.f53N
            r8.l = r3
            A.h0 r1 = r9.d
            A.h0 r4 = A.EnumC0017h0.f181m
            r5 = 0
            long r6 = r8.f1n
            if (r1 != r4) goto L2e
            long r3 = a1.o.a(r6, r5, r5, r3)
            goto L33
        L2e:
            r1 = 2
            long r3 = a1.o.a(r6, r5, r5, r1)
        L33:
            A.Q0 r1 = new A.Q0
            r5 = 0
            r1.<init>(r9, r5)
            y.r0 r5 = r9.f101b
            if (r5 == 0) goto L56
            A.K0 r6 = r9.f100a
            boolean r6 = r6.c()
            if (r6 != 0) goto L4d
            A.K0 r6 = r9.f100a
            boolean r6 = r6.a()
            if (r6 == 0) goto L56
        L4d:
            java.lang.Object r8 = r5.b(r3, r1, r8)
            if (r8 != r0) goto L54
            goto L63
        L54:
            r8 = r2
            goto L63
        L56:
            A.Q0 r1 = new A.Q0
            r1.<init>(r9, r8)
            r1.f94n = r3
            java.lang.Object r8 = r1.invokeSuspend(r2)
            if (r8 != r0) goto L54
        L63:
            if (r8 != r0) goto L66
            return r0
        L66:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: A.A0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
