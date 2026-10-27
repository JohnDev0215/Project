package b.b.g.n.n;

import b.b.g.n.q.e;
import b.b.g.n.q.j;
import b.b.g.n.q.m;
import b.b.g.n.q.r;
import b.b.g.n.q.u;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.newenergy.EnergyManageFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: EnergyManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends g<EnergyManageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1221a = c.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1222b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1223c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1224d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1225e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1226f = false;
    public boolean g = false;
    public boolean h = false;
    public int i = -1;
    public int j = 0;
    public boolean k = false;
    public boolean l = false;

    public boolean A() {
        return this.f1224d && this.f1226f;
    }

    public boolean B() {
        return this.f1224d;
    }

    public boolean C() {
        return !this.f1224d && this.k;
    }

    public boolean D() {
        int i;
        boolean z = b.b.g.n.q.b.c().e(20) == 1;
        boolean z2 = b.b.g.n.q.b.c().e(222) == 1;
        if (!z && !z2) {
            return false;
        }
        boolean z3 = z && ((i = this.f1222b) == 3 || i == 2);
        if (!z || !z2) {
            if (z) {
                return z3;
            }
            return false;
        }
        int i2 = this.f1223c;
        if (i2 == 1) {
            return false;
        }
        if (i2 == 0 || i2 == 15) {
            return z3;
        }
        return true;
    }

    public boolean E() {
        int iT = t();
        return !this.f1224d && (iT == 1 || iT == 2);
    }

    public boolean F() {
        return this.l;
    }

    public int G(int i) {
        return e.j().F(i);
    }

    public int H(int i) {
        return e.j().G(i);
    }

    public int I(int i) {
        return e.j().H(i);
    }

    public int J(int i) {
        return e.j().I(i);
    }

    public int K(int i) {
        return m.p0().q2(i);
    }

    public int L(int i) {
        return e.j().J(i);
    }

    public int M(int i) {
        return e.j().K(i);
    }

    public int N(int i) {
        return e.j().L(i);
    }

    public int O(int i) {
        return e.j().M(i);
    }

    public int P(int i, int i2) {
        return m.p0().p3(i, i2);
    }

    public int Q(int i) {
        return b.b.g.n.q.c.K().B0(i);
    }

    public void R() {
        if (D()) {
            getV().r0(m(), false, false);
            getV().v0(s(), false);
        }
        if (y()) {
            getV().k0(e(), false);
            getV().z0(v());
        }
        if (z()) {
            getV().l0(f(), false);
        }
        if (A() && !b.b.g.q.d.m.m("EnergyManageFragment_label_epedal")) {
            getV().m0(g());
        }
        getV().w0(t());
        getV().x0(this.j);
        getV().o0(i(), false);
        if (!b.b.g.q.d.m.m("EnergyManageFragment_label_feedback_strength")) {
            getV().p0(j(), false);
        }
        if (!b.b.g.q.d.m.m("EnergyManageFragment_label_sport")) {
            getV().u0(q());
        }
        if (!b.b.g.q.d.m.m("EnergyManageFragment_label_ev")) {
            getV().t0(o());
        }
        if (b.b.g.q.d.m.m("EnergyManageFragment_label_electricity")) {
            return;
        }
        getV().s0(n());
    }

    @Override // b.b.m.e.g
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public void bindV(EnergyManageFragment energyManageFragment) {
        super.bindV(energyManageFragment);
        EventBus.getDefault().register(this);
    }

    public int d() {
        return this.i;
    }

    public int e() {
        return e.j().a();
    }

    public int f() {
        return e.j().b();
    }

    public int g() {
        return e.j().c();
    }

    public int h() {
        return j.a().b();
    }

    public int i() {
        return e.j().e();
    }

    public int j() {
        return m.p0().L();
    }

    public int l() {
        return e.j().g();
    }

    public int m() {
        return e.j().o();
    }

    public int n() {
        return e.j().p();
    }

    public int o() {
        return e.j().q();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetCommonMsg(b.b.g.m.a aVar) {
        if (getV() == null) {
            BydLogHelper.logE(this.f1221a, "[CommonMsg]----onGetCommonMsg getV() == null");
        } else if (aVar.b() == 1 && aVar.a() == 2 && getV().G0()) {
            getV().onResume();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (getV() == null) {
            BydLogHelper.logE(this.f1221a, "[CommonMsg]----onGetMessage getV() == null");
        }
        int iH = aVar.h();
        switch (iH) {
            case 140011:
                int iC = aVar.c();
                if (b.b.g.r.e.e(aVar)) {
                    iC = t() == 1 ? 1 : 2;
                }
                getV().w0(iC);
                break;
            case 140012:
                getV().x0(aVar.c());
                break;
            default:
                switch (iH) {
                    case 150000:
                        getV().q0(aVar.c());
                        break;
                    case 150029:
                        getV().y0(aVar.c());
                        break;
                    case 190000:
                        getV().A0(aVar.c());
                        break;
                    case 240012:
                        getV().z0(aVar.c());
                        break;
                    case 280000:
                        getV().n0(aVar.c());
                        break;
                    default:
                        switch (iH) {
                            case 150009:
                                int iC2 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC2 = g() == 1 ? 1 : 2;
                                }
                                getV().m0(iC2);
                                break;
                            case 150010:
                                int iC3 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC3 = i() != 2 ? 1 : 2;
                                }
                                getV().o0(iC3, true);
                                break;
                            case 150011:
                                int iC4 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC4 = m() != 2 ? 1 : 2;
                                }
                                getV().r0(iC4, true, b.b.g.r.e.c(150016));
                                break;
                            case 150012:
                                int iC5 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC5 = f() == 1 ? 1 : 2;
                                }
                                getV().l0(iC5, true);
                                break;
                            case 150013:
                                int iC6 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC6 = q() != 2 ? 1 : 2;
                                }
                                getV().u0(iC6);
                                break;
                            case 150014:
                                int iC7 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC7 = o() != 2 ? 1 : 2;
                                }
                                getV().t0(iC7);
                                break;
                            case 150015:
                                int iC8 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    int iJ = j();
                                    if (iJ == 3 || iJ == 2 || iJ == 4) {
                                        i = iJ;
                                    }
                                } else {
                                    i = iC8;
                                }
                                getV().p0(i, true);
                                break;
                            case 150016:
                                int iC9 = aVar.c();
                                if (b.b.g.r.e.e(aVar)) {
                                    iC9 = s();
                                }
                                getV().v0(iC9, true);
                                break;
                            default:
                                switch (iH) {
                                    case 150018:
                                        int iC10 = aVar.c();
                                        if (b.b.g.r.e.e(aVar)) {
                                            iC10 = e() != 2 ? 1 : 2;
                                        }
                                        getV().k0(iC10, true);
                                        break;
                                    case 150019:
                                        int iC11 = aVar.c();
                                        if (b.b.g.r.e.e(aVar)) {
                                            iC11 = n() != 2 ? 1 : 2;
                                        }
                                        getV().s0(iC11);
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public int q() {
        return e.j().r();
    }

    public int[] r() {
        int i;
        boolean z = b.b.g.n.q.b.c().e(222) == 1;
        int[] iArr = {25, 70};
        if (!z || (z && ((i = this.f1223c) == 0 || i == 15))) {
            int i2 = this.f1222b;
            if (i2 == 3) {
                iArr[0] = 15;
            } else if (i2 == 2) {
                iArr[0] = 25;
            } else {
                iArr[0] = -1;
            }
        } else {
            int i3 = this.f1223c;
            if (i3 == 1) {
                iArr[0] = -1;
            } else if (i3 == 2) {
                iArr[0] = 25;
            } else if (i3 == 3) {
                iArr[0] = 15;
            } else if (i3 == 4) {
                iArr[0] = 20;
            } else if (i3 == 5) {
                iArr[0] = 18;
            } else if (i3 == 6) {
                iArr[0] = 22;
            } else if (i3 == 7) {
                iArr[0] = 10;
            } else {
                iArr[0] = 25;
            }
        }
        return iArr;
    }

    public int s() {
        return m.p0().h1();
    }

    public int t() {
        return b.b.g.n.q.c.K().P();
    }

    public int u() {
        return m.p0().q1();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        EventBus.getDefault().unregister(this);
    }

    public int v() {
        return m.p0().z1();
    }

    public int w() {
        return r.b().o();
    }

    public void x() {
        this.f1223c = m.p0().r1();
        this.f1222b = u.I().r();
        this.i = u.I().x();
        this.f1224d = b.b.g.n.q.b.c().g();
        this.f1226f = u.I().C() == 0;
        this.g = u.I().y() == 2;
        this.h = u.I().z() == 0;
        this.j = b.b.g.n.q.c.K().Q();
        this.k = u.I().E() != 1;
        this.l = u.I().D() == 1;
        if (getV() != null) {
            getV().F0();
        }
    }

    public boolean y() {
        return this.g;
    }

    public boolean z() {
        return this.f1224d && this.f1225e && this.h && (b.b.g.n.q.b.c().e(151) == 1);
    }
}
