package X5;

import S8.AbstractC0727y;
import V8.AbstractC0828q;
import k3.C1672v;

/* JADX INFO: renamed from: X5.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0886u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N5.Z f10384a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X8.c f10385b;
    public float c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final V8.f0 f10386e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final V8.f0 f10387f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public S8.k0 f10388g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final V8.f0 f10389h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final V8.N f10390i;

    public C0886u(N5.Z z6) {
        A7.m.f(z6, "playerManager");
        this.f10384a = z6;
        Z8.e eVar = S8.E.f8084a;
        X8.c cVarA = AbstractC0727y.a(X8.m.f10573a);
        this.f10385b = cVarA;
        this.f10386e = AbstractC0828q.c(Float.valueOf(this.c));
        this.f10387f = AbstractC0828q.c(Boolean.FALSE);
        V8.f0 f0VarC = AbstractC0828q.c(0L);
        this.f10389h = f0VarC;
        this.f10390i = new V8.N(f0VarC);
        AbstractC0727y.w(cVarA, null, null, new r(this, null), 3);
    }

    public final void a() {
        N5.Z z6 = this.f10384a;
        if (z6.l()) {
            return;
        }
        long jI = z6.i();
        V8.f0 f0Var = this.f10389h;
        long jLongValue = jI - ((Number) f0Var.getValue()).longValue();
        long jI2 = (jLongValue >= 0 || jLongValue <= -500) ? z6.i() : ((Number) f0Var.getValue()).longValue() + ((long) 15);
        if (this.d != jI2 && !((Boolean) this.f10387f.getValue()).booleanValue()) {
            this.d = jI2;
            Long lValueOf = Long.valueOf(jI2);
            f0Var.getClass();
            f0Var.m(null, lValueOf);
            z6.f4666n = jI2;
        }
        C1672v c1672v = z6.f4667p;
        if (c1672v == null) {
            A7.m.j("mediaController");
            throw null;
        }
        long jS0 = c1672v.s0();
        float fI = jS0 < 1 ? 0.0f : z6.i() / jS0;
        if (fI == this.c) {
            return;
        }
        this.c = fI;
        Float fValueOf = Float.valueOf(fI);
        V8.f0 f0Var2 = this.f10386e;
        f0Var2.getClass();
        f0Var2.m(null, fValueOf);
    }

    public final void b() {
        this.c = 0.0f;
        this.d = 0L;
        Float fValueOf = Float.valueOf(0.0f);
        V8.f0 f0Var = this.f10386e;
        f0Var.getClass();
        f0Var.m(null, fValueOf);
        V8.f0 f0Var2 = this.f10389h;
        f0Var2.getClass();
        f0Var2.m(null, 0L);
    }
}
