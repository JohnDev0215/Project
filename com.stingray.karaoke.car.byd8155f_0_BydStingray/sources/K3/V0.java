package k3;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class V0 implements j1, k1 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ m1 f17866m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f17867n;

    public /* synthetic */ V0(m1 m1Var, int i7, int i10) {
        this.l = i10;
        this.f17866m = m1Var;
        this.f17867n = i7;
    }

    @Override // k3.k1
    public void d(u1 u1Var, C1679y0 c1679y0, List list) {
        switch (this.l) {
            case 1:
                u1Var.z(this.f17866m.r1(c1679y0, u1Var, this.f17867n), list);
                break;
            case 2:
                m1 m1Var = this.f17866m;
                m1Var.getClass();
                int size = list.size();
                int i7 = this.f17867n;
                if (size != 1) {
                    u1Var.q0(m1Var.r1(c1679y0, u1Var, i7), m1Var.r1(c1679y0, u1Var, i7 + 1), list);
                } else {
                    u1Var.p(m1Var.r1(c1679y0, u1Var, i7), (e2.F) list.get(0));
                }
                break;
            default:
                u1Var.z(this.f17866m.r1(c1679y0, u1Var, this.f17867n), list);
                break;
        }
    }

    @Override // k3.j1
    public void f(u1 u1Var, C1679y0 c1679y0) {
        switch (this.l) {
            case 0:
                u1Var.t(this.f17866m.r1(c1679y0, u1Var, this.f17867n));
                break;
            default:
                u1Var.v0(this.f17866m.r1(c1679y0, u1Var, this.f17867n));
                break;
        }
    }
}
