package e6;

import K5.F;
import N5.q0;
import S8.AbstractC0727y;
import S8.E;
import S8.W;
import T.C0735d;
import T.C0738e0;
import T.S;
import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import X8.m;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import n7.u;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f14459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q0 f14460b;
    public final W5.e c;
    public final f0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final N f14461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f0 f14462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final N f14463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f0 f14464h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final N f14465i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final X8.c f14466j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f14467k;

    public k(F f10, q0 q0Var, W5.e eVar) {
        this.f14459a = f10;
        this.f14460b = q0Var;
        this.c = eVar;
        f0 f0VarC = AbstractC0828q.c(LoadingInterface$LoadingState.Done);
        this.d = f0VarC;
        this.f14461e = new N(f0VarC);
        f0 f0VarC2 = AbstractC0828q.c("");
        this.f14462f = f0VarC2;
        this.f14463g = new N(f0VarC2);
        f0 f0VarC3 = AbstractC0828q.c(u.l);
        this.f14464h = f0VarC3;
        this.f14465i = new N(f0VarC3);
        Z8.e eVar2 = E.f8084a;
        this.f14466j = AbstractC0727y.a(m.f10573a);
        this.f14467k = C0735d.O(null, S.f8224q);
    }

    public final void a() {
        f0 f0Var;
        Object value;
        f0 f0Var2;
        Object value2;
        f0 f0Var3;
        Object value3;
        do {
            f0Var = this.f14462f;
            value = f0Var.getValue();
        } while (!f0Var.k(value, ""));
        do {
            f0Var2 = this.f14464h;
            value2 = f0Var2.getValue();
        } while (!f0Var2.k(value2, u.l));
        W w10 = (W) this.f14467k.getValue();
        if (w10 != null) {
            w10.g(null);
        }
        do {
            f0Var3 = this.d;
            value3 = f0Var3.getValue();
        } while (!f0Var3.k(value3, LoadingInterface$LoadingState.Done));
    }

    public final void b(long j3, String str, boolean z6) {
        f0 f0Var;
        Object value;
        A7.m.f(str, "text");
        a();
        do {
            f0Var = this.f14462f;
            value = f0Var.getValue();
        } while (!f0Var.k(value, str));
        if (Q8.h.M0(str)) {
            return;
        }
        this.f14467k.setValue(AbstractC0727y.w(this.f14466j, null, null, new j(this, j3, str, z6, null), 3));
    }
}
