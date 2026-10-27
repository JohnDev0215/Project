package e6;

import A7.v;
import K5.F;
import K5.u;
import S8.AbstractC0727y;
import T.C0732b0;
import T.C0735d;
import T.C0738e0;
import T.S;
import V8.N;
import V8.f0;
import androidx.lifecycle.a0;
import j6.n;
import k6.C1691C;
import k6.C1705m;
import k6.r;
import m7.q;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends b6.i {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C0732b0 f14445A;
    public final N B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final N f14446C;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final F f14447t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final r f14448u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final k f14449v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final N f14450w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0738e0 f14451x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final N f14452y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final N f14453z;

    public g(F f10, r rVar, k kVar, C1705m c1705m, W5.e eVar, u uVar, L5.i iVar, n nVar, C1691C c1691c) {
        super(c1705m, eVar, uVar, iVar, nVar);
        this.f14447t = f10;
        this.f14448u = rVar;
        this.f14449v = kVar;
        this.f14450w = kVar.f14463g;
        this.f14451x = C0735d.O(n7.u.l, S.f8224q);
        this.f14452y = kVar.f14465i;
        this.f14453z = kVar.f14461e;
        this.f14445A = C0735d.M(0);
        this.B = c1691c.f18234h;
        this.f14446C = c1691c.f18237k;
        AbstractC0727y.w(a0.i(this), null, null, new C1230b(new v(), this, null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object o(e6.g r5, s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            r5.getClass()
            boolean r0 = r6 instanceof e6.e
            if (r0 == 0) goto L16
            r0 = r6
            e6.e r0 = (e6.e) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.o = r1
            goto L1b
        L16:
            e6.e r0 = new e6.e
            r0.<init>(r5, r6)
        L1b:
            java.lang.Object r6 = r0.f14441m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            e6.g r5 = r0.l
            h8.AbstractC1386c.u(r6)
            goto L51
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            h8.AbstractC1386c.u(r6)
            r0.l = r5
            r0.o = r3
            K5.F r6 = r5.f14447t
            r6.getClass()
            Z8.e r2 = S8.E.f8084a
            Z8.d r2 = Z8.d.f11010n
            K5.x r3 = new K5.x
            r4 = 0
            r3.<init>(r6, r4)
            java.lang.Object r6 = S8.AbstractC0727y.F(r2, r3, r0)
            if (r6 != r1) goto L51
            goto L5a
        L51:
            java.util.List r6 = (java.util.List) r6
            T.e0 r5 = r5.f14451x
            r5.setValue(r6)
            m7.q r1 = m7.q.f19267a
        L5a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: e6.g.o(e6.g, s7.c):java.lang.Object");
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        AbstractC0727y.w(this.f12236f, null, null, new c(this, null), 3);
        return q.f19267a;
    }

    @Override // b6.f
    public final void g() {
        i(true);
        N n10 = this.f14450w;
        if (((CharSequence) ((f0) n10.l).getValue()).length() > 0) {
            this.f14449v.b(0L, (String) ((f0) n10.l).getValue(), false);
        }
    }

    @Override // b6.f
    public final void j() {
        super.j();
        this.f14449v.a();
        this.f14445A.j(0);
    }
}
