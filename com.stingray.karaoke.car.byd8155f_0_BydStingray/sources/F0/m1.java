package F0;

import S8.InterfaceC0725w;
import T.C0767t0;
import android.view.View;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f2224m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.z f2225n;
    public final /* synthetic */ C0767t0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ androidx.lifecycle.A f2226p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ n1 f2227q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ View f2228r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m1(A7.z zVar, C0767t0 c0767t0, androidx.lifecycle.A a10, n1 n1Var, View view, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2225n = zVar;
        this.o = c0767t0;
        this.f2226p = a10;
        this.f2227q = n1Var;
        this.f2228r = view;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m1 m1Var = new m1(this.f2225n, this.o, this.f2226p, this.f2227q, this.f2228r, interfaceC2189c);
        m1Var.f2224m = obj;
        return m1Var;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a3  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) throws java.lang.Throwable {
        /*
            r11 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r11.l
            androidx.lifecycle.A r2 = r11.f2226p
            m7.q r3 = m7.q.f19267a
            r4 = 0
            F0.n1 r5 = r11.f2227q
            r6 = 1
            if (r1 == 0) goto L24
            if (r1 != r6) goto L1c
            java.lang.Object r11 = r11.f2224m
            S8.W r11 = (S8.W) r11
            h8.AbstractC1386c.u(r12)     // Catch: java.lang.Throwable -> L19
            goto L8e
        L19:
            r12 = move-exception
            goto La1
        L1c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L24:
            h8.AbstractC1386c.u(r12)
            java.lang.Object r12 = r11.f2224m
            S8.w r12 = (S8.InterfaceC0725w) r12
            A7.z r1 = r11.f2225n     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r1 = r1.l     // Catch: java.lang.Throwable -> L5d
            F0.F0 r1 = (F0.F0) r1     // Catch: java.lang.Throwable -> L5d
            if (r1 == 0) goto L63
            android.view.View r7 = r11.f2228r     // Catch: java.lang.Throwable -> L5d
            android.content.Context r7 = r7.getContext()     // Catch: java.lang.Throwable -> L5d
            android.content.Context r7 = r7.getApplicationContext()     // Catch: java.lang.Throwable -> L5d
            V8.d0 r7 = F0.q1.a(r7)     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r8 = r7.getValue()     // Catch: java.lang.Throwable -> L5d
            java.lang.Number r8 = (java.lang.Number) r8     // Catch: java.lang.Throwable -> L5d
            float r8 = r8.floatValue()     // Catch: java.lang.Throwable -> L5d
            T.a0 r9 = r1.l     // Catch: java.lang.Throwable -> L61
            r9.j(r8)     // Catch: java.lang.Throwable -> L61
            F0.l1 r8 = new F0.l1     // Catch: java.lang.Throwable -> L5d
            r8.<init>(r7, r1, r4)     // Catch: java.lang.Throwable -> L5d
            r1 = 3
            S8.k0 r12 = S8.AbstractC0727y.w(r12, r4, r4, r8, r1)     // Catch: java.lang.Throwable -> L5d
            goto L64
        L5b:
            r11 = r4
            goto La1
        L5d:
            r12 = move-exception
            goto L5b
        L5f:
            r12 = r11
            goto L5b
        L61:
            r11 = move-exception
            goto L5f
        L63:
            r12 = r4
        L64:
            T.t0 r1 = r11.o     // Catch: java.lang.Throwable -> L9f
            r11.f2224m = r12     // Catch: java.lang.Throwable -> L9f
            r11.l = r6     // Catch: java.lang.Throwable -> L9f
            T.s0 r6 = new T.s0     // Catch: java.lang.Throwable -> L9f
            r6.<init>(r1, r4)     // Catch: java.lang.Throwable -> L9f
            q7.h r7 = r11.getContext()     // Catch: java.lang.Throwable -> L9f
            T.T r7 = T.C0735d.I(r7)     // Catch: java.lang.Throwable -> L9f
            T.q0 r8 = new T.q0     // Catch: java.lang.Throwable -> L9f
            r8.<init>(r1, r6, r7, r4)     // Catch: java.lang.Throwable -> L9f
            T.h r1 = r1.f8351a     // Catch: java.lang.Throwable -> L9f
            java.lang.Object r11 = S8.AbstractC0727y.F(r1, r8, r11)     // Catch: java.lang.Throwable -> L9f
            if (r11 != r0) goto L85
            goto L86
        L85:
            r11 = r3
        L86:
            if (r11 != r0) goto L89
            goto L8a
        L89:
            r11 = r3
        L8a:
            if (r11 != r0) goto L8d
            return r0
        L8d:
            r11 = r12
        L8e:
            if (r11 == 0) goto L93
            r11.g(r4)
        L93:
            A9.b r11 = r2.g()
            r11.V0(r5)
            return r3
        L9b:
            r10 = r12
            r12 = r11
            r11 = r10
            goto La1
        L9f:
            r11 = move-exception
            goto L9b
        La1:
            if (r11 == 0) goto La6
            r11.g(r4)
        La6:
            A9.b r11 = r2.g()
            r11.V0(r5)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.m1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
