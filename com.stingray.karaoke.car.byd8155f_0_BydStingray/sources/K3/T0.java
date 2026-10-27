package k3;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T0 implements j1, k1 {
    public final /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f17859m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f17860n;

    public /* synthetic */ T0(int i7, int i10, Object obj) {
        this.l = obj;
        this.f17859m = i7;
        this.f17860n = i10;
    }

    @Override // k3.k1
    public void d(u1 u1Var, C1679y0 c1679y0, List list) {
        m1 m1Var = (m1) this.l;
        u1Var.q0(m1Var.r1(c1679y0, u1Var, this.f17859m), m1Var.r1(c1679y0, u1Var, this.f17860n), list);
    }

    @Override // k3.j1
    public void f(u1 u1Var, C1679y0 c1679y0) {
        m1 m1Var = (m1) this.l;
        u1Var.A0(m1Var.r1(c1679y0, u1Var, this.f17859m), m1Var.r1(c1679y0, u1Var, this.f17860n));
    }
}
