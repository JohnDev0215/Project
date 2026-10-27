package androidx.lifecycle;

import S8.InterfaceC0725w;
import a2.C0908b;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class S extends s7.i implements InterfaceC2785n {
    public A7.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A7.z f11950m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11951n;
    public final /* synthetic */ A9.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ EnumC0981t f11952p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0725w f11953q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0908b f11954r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S(A9.b bVar, EnumC0981t enumC0981t, InterfaceC0725w interfaceC0725w, C0908b c0908b, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = bVar;
        this.f11952p = enumC0981t;
        this.f11953q = interfaceC0725w;
        this.f11954r = c0908b;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new S(this.o, this.f11952p, this.f11953q, this.f11954r, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((S) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b9  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) throws java.lang.Throwable {
        /*
            r17 = this;
            r0 = r17
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f11951n
            m7.q r3 = m7.q.f19267a
            r4 = 0
            A9.b r5 = r0.o
            r6 = 1
            if (r2 == 0) goto L24
            if (r2 != r6) goto L1c
            A7.z r1 = r0.f11950m
            A7.z r2 = r0.l
            h8.AbstractC1386c.u(r18)     // Catch: java.lang.Throwable -> L19
            goto L95
        L19:
            r0 = move-exception
            goto Laa
        L1c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L24:
            h8.AbstractC1386c.u(r18)
            androidx.lifecycle.t r2 = r5.O0()
            androidx.lifecycle.t r7 = androidx.lifecycle.EnumC0981t.l
            if (r2 != r7) goto L30
            return r3
        L30:
            A7.z r2 = new A7.z
            r2.<init>()
            A7.z r7 = new A7.z
            r7.<init>()
            androidx.lifecycle.t r8 = r0.f11952p     // Catch: java.lang.Throwable -> La8
            S8.w r11 = r0.f11953q     // Catch: java.lang.Throwable -> La8
            a2.b r15 = r0.f11954r     // Catch: java.lang.Throwable -> La8
            r0.l = r2     // Catch: java.lang.Throwable -> La8
            r0.f11950m = r7     // Catch: java.lang.Throwable -> La8
            r0.f11951n = r6     // Catch: java.lang.Throwable -> La8
            S8.i r14 = new S8.i     // Catch: java.lang.Throwable -> La8
            q7.c r0 = h6.e.k(r17)     // Catch: java.lang.Throwable -> La8
            r14.<init>(r6, r0)     // Catch: java.lang.Throwable -> La8
            r14.s()     // Catch: java.lang.Throwable -> La8
            androidx.lifecycle.q r0 = androidx.lifecycle.EnumC0980s.Companion     // Catch: java.lang.Throwable -> La8
            r0.getClass()     // Catch: java.lang.Throwable -> La8
            java.lang.String r0 = "state"
            A7.m.f(r8, r0)     // Catch: java.lang.Throwable -> La8
            int r0 = r8.ordinal()     // Catch: java.lang.Throwable -> La8
            r6 = 2
            if (r0 == r6) goto L72
            r6 = 3
            if (r0 == r6) goto L6f
            r6 = 4
            if (r0 == r6) goto L6b
            r9 = r4
            goto L75
        L6b:
            androidx.lifecycle.s r0 = androidx.lifecycle.EnumC0980s.ON_RESUME     // Catch: java.lang.Throwable -> La8
        L6d:
            r9 = r0
            goto L75
        L6f:
            androidx.lifecycle.s r0 = androidx.lifecycle.EnumC0980s.ON_START     // Catch: java.lang.Throwable -> La8
            goto L6d
        L72:
            androidx.lifecycle.s r0 = androidx.lifecycle.EnumC0980s.ON_CREATE     // Catch: java.lang.Throwable -> La8
            goto L6d
        L75:
            androidx.lifecycle.s r12 = androidx.lifecycle.C0979q.a(r8)     // Catch: java.lang.Throwable -> La8
            b9.c r0 = b9.d.a()     // Catch: java.lang.Throwable -> La8
            androidx.lifecycle.Q r6 = new androidx.lifecycle.Q     // Catch: java.lang.Throwable -> La8
            r8 = r6
            r10 = r2
            r13 = r14
            r16 = r14
            r14 = r0
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> La8
            r7.l = r6     // Catch: java.lang.Throwable -> La8
            r5.M0(r6)     // Catch: java.lang.Throwable -> La8
            java.lang.Object r0 = r16.q()     // Catch: java.lang.Throwable -> La8
            if (r0 != r1) goto L94
            return r1
        L94:
            r1 = r7
        L95:
            java.lang.Object r0 = r2.l
            S8.W r0 = (S8.W) r0
            if (r0 == 0) goto L9e
            r0.g(r4)
        L9e:
            java.lang.Object r0 = r1.l
            androidx.lifecycle.y r0 = (androidx.lifecycle.InterfaceC0986y) r0
            if (r0 == 0) goto La7
            r5.V0(r0)
        La7:
            return r3
        La8:
            r0 = move-exception
            r1 = r7
        Laa:
            java.lang.Object r2 = r2.l
            S8.W r2 = (S8.W) r2
            if (r2 == 0) goto Lb3
            r2.g(r4)
        Lb3:
            java.lang.Object r1 = r1.l
            androidx.lifecycle.y r1 = (androidx.lifecycle.InterfaceC0986y) r1
            if (r1 == 0) goto Lbc
            r5.V0(r1)
        Lbc:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.S.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
