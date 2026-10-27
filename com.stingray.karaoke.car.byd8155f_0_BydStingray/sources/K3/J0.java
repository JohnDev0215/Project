package k3;

import h2.AbstractC1343A;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class J0 implements P0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Q0 f17819m;

    public /* synthetic */ J0(Q0 q02, int i7) {
        this.l = i7;
        this.f17819m = q02;
    }

    @Override // k3.P0
    public final void d(C1679y0 c1679y0) {
        switch (this.l) {
            case 0:
                this.f17819m.f17843f.f17811t.E0();
                break;
            case 1:
                I0 i02 = this.f17819m.f17843f;
                if (i02.f17811t.d1() != null) {
                    i02.r(c1679y0);
                    i02.f17798e.getClass();
                    l9.l.T(new C1(-6));
                    break;
                }
                break;
            case 2:
                this.f17819m.f17843f.f17811t.M0();
                break;
            case 3:
                this.f17819m.f17843f.f17811t.W();
                break;
            case 4:
                this.f17819m.f17843f.f17811t.H0();
                break;
            case 5:
                this.f17819m.f17843f.f17811t.c();
                break;
            case 6:
                this.f17819m.f17843f.f17811t.stop();
                break;
            case 7:
                I0 i03 = this.f17819m.f17843f;
                u1 u1Var = i03.f17811t;
                if (!AbstractC1343A.P(u1Var, i03.f17807p)) {
                    if (u1Var != null && u1Var.R0(1)) {
                        u1Var.b();
                        break;
                    }
                } else {
                    AbstractC1343A.A(u1Var);
                    break;
                }
                break;
            case 8:
                this.f17819m.f17843f.f17811t.C0();
                break;
            case 9:
                this.f17819m.f17843f.f17811t.G();
                break;
            case 10:
                this.f17819m.f17843f.f(c1679y0, true);
                break;
            default:
                u1 u1Var2 = this.f17819m.f17843f.f17811t;
                if (u1Var2 != null && u1Var2.R0(1)) {
                    u1Var2.b();
                    break;
                }
                break;
        }
    }

    public /* synthetic */ J0(Q0 q02, e2.X x8) {
        this.l = 1;
        this.f17819m = q02;
    }
}
