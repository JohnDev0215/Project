package b.a.a.g;

import b.a.a.f.e1;
import b.a.a.f.f1;
import b.a.a.f.g1;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e extends b.a.a.b.c<b.a.a.d.b> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public f1 f221b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g1 f222c;
    public e1 d;

    public int A() {
        return this.f222c.d();
    }

    public int B() {
        return this.f221b.i0();
    }

    public int C() {
        return this.f221b.t0();
    }

    public int D() {
        return this.f221b.u0();
    }

    public int E() {
        return this.f221b.H0();
    }

    public int F() {
        return this.f221b.h1();
    }

    public int G() {
        return this.f221b.m1();
    }

    public int H() {
        return this.f222c.h();
    }

    public int I() {
        return this.f222c.k();
    }

    public int J(int i) {
        return this.f221b.n1(i);
    }

    public int K() {
        return this.f221b.o1();
    }

    public int L() {
        return this.f221b.p1();
    }

    public int M(int i) {
        return this.f221b.q1(i);
    }

    public boolean N() {
        boolean zS1 = this.f221b.s1();
        BydLogHelper.logD("AutoBodywork isCarEV  = " + zS1);
        return zS1;
    }

    public int O(int i) {
        return this.f221b.J3(i);
    }

    public int P(int i) {
        return this.f221b.K3(i);
    }

    public int Q(int i) {
        return this.f221b.L3(i);
    }

    public int R(int i, int i2) {
        if (i == 2) {
            b.a.a.h.a.h(1038, i2);
        } else if (i == 1) {
            b.a.a.h.a.h(1006, i2);
        }
        return this.f221b.M3(i, i2);
    }

    public int S(int i) {
        e().U(i, true);
        return this.f221b.N3(i);
    }

    public int T(int i) {
        e().g0(1, i, true);
        if (i == 0) {
            return this.f221b.E4();
        }
        if (i == 1) {
            return this.f221b.C4();
        }
        return -1;
    }

    public int U(int i, int i2) {
        e().d(i, i2);
        return this.f221b.Q3(i, i2);
    }

    public int V(int i) {
        e().E(i);
        return this.f221b.R3(i);
    }

    public int W(int i, int i2) {
        return this.f221b.S3(i, i2);
    }

    public int X(int i) {
        return this.f221b.T3(i);
    }

    public int Y(int i) {
        e().n(1, i);
        return this.f221b.U3(i);
    }

    public int Z(int i) {
        e().c(i, true);
        return this.f221b.V3(i);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        super.a();
        EventBus.getDefault().unregister(this);
    }

    public int a0(int i) {
        return this.f221b.i4(i);
    }

    public int b0(int i) {
        e().u(i);
        b.a.a.h.a.g(1314);
        return this.f221b.j4(i);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.b bVar) {
        super.d(bVar);
        EventBus.getDefault().register(this);
        this.f221b = f1.l0();
        this.d = e1.a();
        this.f222c = g1.e();
    }

    public int g() {
        return this.f221b.n();
    }

    public int h() {
        return this.f221b.o();
    }

    public int i() {
        return this.f221b.p();
    }

    public int j(int i) {
        return this.f221b.q(i);
    }

    public int k() {
        return this.f221b.s();
    }

    public int l() {
        return this.f221b.t();
    }

    public int m() {
        return this.f221b.v();
    }

    public int n() {
        return this.f221b.w();
    }

    public int o() {
        BydLogHelper.logD("AcFrontControl attachView");
        return this.f221b.A();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        int i;
        int iC = aVar.c();
        if (iC == 1034) {
            int iA = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA = p(1);
            }
            e().d(1, iA);
        }
        if (iC == 1035) {
            int iA2 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA2 = p(2);
            }
            e().d(2, iA2);
            return;
        }
        if (iC == 1037) {
            e().z(aVar.a());
            return;
        }
        if (iC == 1038) {
            int iA3 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA3 = j(2) != 1 ? 0 : 1;
            }
            e().e(2, iA3, false);
            return;
        }
        if (iC == 1040) {
            int iA4 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA4 = w(0);
            }
            e().w(0, iA4);
            return;
        }
        if (iC == 1041) {
            int iA5 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA5 = l();
            }
            e().U(iA5, false);
            return;
        }
        switch (iC) {
            case 1003:
                int iA6 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA6 = h() != 0 ? 1 : 0;
                }
                e().l0(1, iA6);
                break;
            case 1004:
                int iA7 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA7 = g() != 1 ? 0 : 1;
                }
                e().g(iA7, false);
                break;
            case 1005:
                int iA8 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA8 = C() != 1 ? 0 : 1;
                }
                e().d0(iA8, false);
                break;
            case 1006:
                int iA9 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA9 = j(1) == 1 ? 1 : 0;
                }
                e().e(1, iA9, false);
                break;
            case 1007:
                int iA10 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA10 = i() == 0 ? 0 : 1;
                }
                e().k(iA10, false);
                break;
            case 1008:
                int iA11 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA11 = s() != 1 ? 0 : 1;
                }
                e().S(iA11);
                break;
            case 1009:
                int iA12 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA12 = u();
                }
                e().c(iA12, false);
                break;
            case 1017:
                e().m(aVar.a());
                break;
            case 1020:
                e().h(aVar.a());
                break;
            case 1022:
                int iA13 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA13 = o() == 1 ? 1 : 0;
                }
                e().g0(1, iA13, false);
                break;
            case 1032:
                e().i(aVar.a(), aVar.b());
                break;
            case 1106:
                e().g0(2, aVar.a(), false);
                break;
            case 1314:
                int iA14 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    iA14 = D() != 1 ? 2 : 1;
                }
                e().u(iA14);
                break;
            case 1600:
                e().j(aVar.a());
                break;
            case 1800:
                e().f(185, aVar.a());
                break;
            case 1801:
                e().f(166, aVar.a());
                break;
            case 1802:
                e().f(207, aVar.a());
                break;
            case 1803:
                e().f(111, aVar.a());
                break;
            case 1804:
                e().f(149, aVar.a());
                break;
            default:
                switch (iC) {
                    case 1011:
                        int iA15 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            iA15 = q() != 1 ? 0 : 1;
                        }
                        e().E(iA15);
                        break;
                    case 1012:
                        int iA16 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            iA16 = t();
                        }
                        e().n(1, iA16);
                        break;
                    case 1013:
                        e().l(aVar.a());
                        break;
                    default:
                        switch (iC) {
                            case 1025:
                                int iA17 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA17 = z(0) != 1 ? 2 : 1;
                                }
                                e().B(0, iA17, false);
                                break;
                            case 1026:
                                int iA18 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA18 = y(0) != 1 ? 2 : 1;
                                }
                                e().q(0, iA18, false);
                                break;
                            case 1027:
                                int iA19 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA19 = J(0) != 1 ? 2 : 1;
                                }
                                e().Y(0, iA19, false);
                                break;
                            default:
                                switch (iC) {
                                    case 1044:
                                        int iA20 = aVar.a();
                                        if (b.a.a.h.a.c(aVar)) {
                                            iA20 = r(0) != 1 ? 2 : 1;
                                        }
                                        e().o0(0, iA20, false);
                                        break;
                                    case 1045:
                                        int iA21 = aVar.a();
                                        if (b.a.a.h.a.c(aVar)) {
                                            iA21 = r(1) == 1 ? 1 : 2;
                                        }
                                        e().o0(1, iA21, false);
                                        break;
                                    case 1046:
                                        int iA22 = aVar.a();
                                        if (b.a.a.h.a.c(aVar)) {
                                            iA22 = r(2) != 1 ? 2 : 1;
                                        }
                                        e().o0(2, iA22, false);
                                        break;
                                    case 1047:
                                        int iA23 = aVar.a();
                                        if (b.a.a.h.a.c(aVar)) {
                                            iA23 = r(3) != 1 ? 2 : 1;
                                        }
                                        e().o0(3, iA23, false);
                                        break;
                                    default:
                                        switch (iC) {
                                            case 1049:
                                                int iA24 = aVar.a();
                                                if (b.a.a.h.a.c(aVar)) {
                                                    iA24 = z(1) == 1 ? 1 : 2;
                                                }
                                                e().B(1, iA24, false);
                                                break;
                                            case 1050:
                                                int iA25 = aVar.a();
                                                if (b.a.a.h.a.c(aVar)) {
                                                    iA25 = y(1) == 1 ? 1 : 2;
                                                }
                                                e().q(1, iA25, false);
                                                break;
                                            case 1051:
                                                int iA26 = aVar.a();
                                                if (b.a.a.h.a.c(aVar)) {
                                                    iA26 = J(1) == 1 ? 1 : 2;
                                                }
                                                e().Y(1, iA26, false);
                                                break;
                                            default:
                                                switch (iC) {
                                                    case 1053:
                                                        int iA27 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA27 = w(1);
                                                        }
                                                        e().w(1, iA27);
                                                        break;
                                                    case 1054:
                                                        int iA28 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA28 = w(2);
                                                        }
                                                        e().w(2, iA28);
                                                        break;
                                                    case 1055:
                                                        int iA29 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA29 = w(3);
                                                        }
                                                        e().w(3, iA29);
                                                        break;
                                                    case 1056:
                                                        int iA30 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA30 = w(4);
                                                        }
                                                        e().w(4, iA30);
                                                        break;
                                                    case 1057:
                                                        int iA31 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA31 = w(5);
                                                        }
                                                        e().w(5, iA31);
                                                        break;
                                                    case 1058:
                                                        int iA32 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA32 = w(6);
                                                        }
                                                        e().w(6, iA32);
                                                        break;
                                                    case 1059:
                                                        int iA33 = aVar.a();
                                                        if (b.a.a.h.a.c(aVar)) {
                                                            iA33 = w(7);
                                                        }
                                                        e().w(7, iA33);
                                                        break;
                                                    default:
                                                        switch (iC) {
                                                            case 1068:
                                                                e().b(((Boolean) aVar.d()).booleanValue());
                                                                break;
                                                            case 1069:
                                                                i = ((Boolean) aVar.d()).booleanValue() ? 1 : 2;
                                                                W(0, i);
                                                                e().e0(0, i, false);
                                                                break;
                                                            case 1070:
                                                                int i2 = ((Boolean) aVar.d()).booleanValue() ? 1 : 2;
                                                                W(1, i2);
                                                                e().e0(1, i2, false);
                                                                break;
                                                            case 1071:
                                                                i = ((Boolean) aVar.d()).booleanValue() ? 1 : 2;
                                                                W(2, i);
                                                                e().e0(2, i, false);
                                                                break;
                                                            case 1072:
                                                                i = ((Boolean) aVar.d()).booleanValue() ? 1 : 2;
                                                                W(3, i);
                                                                e().e0(3, i, false);
                                                                break;
                                                            case 1073:
                                                                e().a(((Boolean) aVar.d()).booleanValue());
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public int p(int i) {
        return this.f221b.B(i);
    }

    public int q() {
        return this.f221b.C();
    }

    public int r(int i) {
        return this.f221b.D(i);
    }

    public int s() {
        return this.f221b.F();
    }

    public int t() {
        return this.f221b.H();
    }

    public int u() {
        return this.f221b.J();
    }

    public int v() {
        return this.f222c.b();
    }

    public int w(int i) {
        return this.f221b.O(i);
    }

    public int x() {
        return this.f221b.Q();
    }

    public int y(int i) {
        return this.f221b.R(i);
    }

    public int z(int i) {
        return this.f221b.S(i);
    }
}
