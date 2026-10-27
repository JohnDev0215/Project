package b.a.a.g;

import android.os.Message;
import b.a.a.f.f1;
import b.a.a.f.g1;
import b.a.a.h.d;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class k extends b.a.a.b.c<b.a.a.d.h> implements b.a.a.b.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.a.a.h.d f233b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f1 f234c;
    public g1 d;
    public d.a e = new d.a() { // from class: b.a.a.g.b
        @Override // b.a.a.h.d.a
        public final boolean a(Message message) {
            return k.a0(message);
        }
    };

    public static /* synthetic */ boolean a0(Message message) {
        int i = message.what;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c0(int i) {
        this.f234c.A4(i);
    }

    public int A() {
        return this.f234c.B0();
    }

    public int B() {
        return this.f234c.C0();
    }

    public int C() {
        return this.f234c.D0();
    }

    public int D() {
        return this.f234c.E0();
    }

    public int E() {
        return this.f234c.F0();
    }

    public int F() {
        return this.f234c.G0();
    }

    public int G() {
        return this.f234c.H0();
    }

    public int H() {
        return this.f234c.R0();
    }

    public int I() {
        return this.f234c.S0();
    }

    public int J() {
        return this.f234c.T0();
    }

    public int K() {
        return this.f234c.U0();
    }

    public int L() {
        return this.f234c.V0();
    }

    public int M() {
        return this.f234c.W0();
    }

    public int N() {
        return this.f234c.X0();
    }

    public int O() {
        return this.f234c.Y0();
    }

    public int P() {
        return this.f234c.Z0();
    }

    public int Q(int i) {
        return this.f234c.a1(i);
    }

    public int R() {
        return this.f234c.d1();
    }

    public int S(int i) {
        return this.f234c.f1(i);
    }

    public int T(int i) {
        return this.f234c.e1(i);
    }

    public int U(int i) {
        return this.f234c.g1(i);
    }

    public int V() {
        return this.f234c.i1();
    }

    public int W() {
        return this.f234c.j1();
    }

    public int X() {
        return this.f234c.k1();
    }

    public int Y() {
        return this.f234c.l1();
    }

    public int Z() {
        return this.f234c.m1();
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    public void a() {
        this.f233b.c(this.e);
        EventBus.getDefault().unregister(this);
        super.a();
    }

    public int d0(int i, int i2) {
        e().s(i, i2, true);
        return this.f234c.C3(i, i2);
    }

    public int e0(int i, int i2) {
        e().K(i, i2, true);
        return this.f234c.E3(i, i2);
    }

    @Override // b.a.a.b.c, b.a.a.b.a
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(b.a.a.d.h hVar) {
        super.d(hVar);
        EventBus.getDefault().register(this);
        this.f234c = f1.l0();
        this.d = g1.e();
        b.a.a.h.d dVarB = b.a.a.h.d.b();
        this.f233b = dVarB;
        dVarB.a(this.e);
    }

    public int f0(int i, int i2) {
        e().x(i, i2, true);
        return this.f234c.F3(i, i2);
    }

    public int g(int i) {
        return this.f234c.b(i);
    }

    public int g0(int i, int i2) {
        e().I(i, i2, true);
        return this.f234c.G3(i, i2);
    }

    public int h(int i) {
        return this.f234c.c(i);
    }

    public int h0(int i, int i2) {
        e().J(i, i2, true);
        return this.f234c.H3(i, i2);
    }

    public int i(int i) {
        return this.f234c.d(i);
    }

    public int i0(int i, int i2) {
        e().e(i, i2, true);
        if (i == 2) {
            b.a.a.h.a.h(1039, i2);
        }
        return this.f234c.M3(i, i2);
    }

    public int j(int i) {
        return this.f234c.e(i);
    }

    public int j0(int i) {
        e().D(i, true);
        return this.f234c.s4(i);
    }

    public int k(int i) {
        return this.f234c.f(i);
    }

    public int k0(int i) {
        e().r(i, true);
        return this.f234c.t4(i);
    }

    public int l(int i) {
        return this.f234c.g(i);
    }

    public int l0(int i, int i2) {
        e().p0(i, i2, true);
        return this.f234c.u4(i, i2);
    }

    public int m(int i) {
        return this.f234c.q(i);
    }

    public int m0(int i, int i2) {
        e().P(i, i2, true);
        return this.f234c.v4(i, i2);
    }

    public int n() {
        return this.f234c.Q();
    }

    public int n0(int i, int i2) {
        e().H(i, i2, true);
        return this.f234c.w4(i, i2);
    }

    public int o() {
        return this.d.d();
    }

    public int o0(int i, int i2) {
        e().n0(i, i2, true);
        return this.f234c.x4(i, i2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(b.a.a.e.a aVar) {
        int iC = aVar.c();
        if (iC == 1006) {
            int iA = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA = m(1) == 1 ? 1 : 0;
            }
            e().e(1, iA, false);
        }
        if (iC == 1039) {
            int iA2 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                iA2 = m(2) != 1 ? 0 : 1;
            }
            e().e(2, iA2, false);
            return;
        }
        if (iC == 1400) {
            int iA3 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iY = Y();
                iA3 = iY != 0 ? iY : 1;
            }
            e().h0(iA3, false);
            return;
        }
        if (iC == 1407) {
            int iA4 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iP = P();
                iA4 = iP != 1 ? iP : 1;
            }
            e().D(iA4, false);
            return;
        }
        if (iC == 1600) {
            e().j(aVar.a());
            return;
        }
        if (iC == 1420) {
            int iA5 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iQ = Q(3);
                iA5 = iQ != 0 ? iQ : 1;
            }
            e().n0(3, iA5, false);
            return;
        }
        if (iC == 1421) {
            int iA6 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iQ2 = Q(4);
                iA6 = iQ2 != 0 ? iQ2 : 1;
            }
            e().n0(4, iA6, false);
            return;
        }
        if (iC == 1424) {
            int iA7 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iT = T(3);
                iA7 = iT != 1 ? iT : 1;
            }
            e().P(3, iA7, false);
            return;
        }
        if (iC == 1425) {
            int iA8 = aVar.a();
            if (b.a.a.h.a.c(aVar)) {
                int iT2 = T(4);
                iA8 = iT2 != 1 ? iT2 : 1;
            }
            e().P(4, iA8, false);
            return;
        }
        switch (iC) {
            case 1409:
                int iA9 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iR = R();
                    iA9 = iR != 1 ? iR : 1;
                }
                e().r(iA9, false);
                break;
            case 1410:
                int iA10 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iS = S(1);
                    iA10 = iS == 1 ? 1 : iS;
                }
                e().p0(1, iA10, false);
                break;
            case 1411:
                int iA11 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iR2 = t() != 1 ? R() : S(2);
                    iA11 = iR2 != 1 ? iR2 : 1;
                }
                e().p0(2, iA11, false);
                break;
            case 1412:
                int iA12 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iS2 = S(3);
                    iA12 = iS2 != 1 ? iS2 : 1;
                }
                e().p0(3, iA12, false);
                break;
            case 1413:
                int iA13 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iS3 = S(4);
                    iA13 = iS3 != 1 ? iS3 : 1;
                }
                e().p0(4, iA13, false);
                break;
            case 1414:
                int iA14 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iU = U(1);
                    iA14 = iU == 0 ? 1 : iU;
                }
                e().H(1, iA14, false);
                break;
            case 1415:
                int iA15 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iP2 = t() != 1 ? P() : U(2);
                    iA15 = iP2 != 0 ? iP2 : 1;
                }
                e().H(2, iA15, false);
                break;
            case 1416:
                int iA16 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iU2 = U(3);
                    iA16 = iU2 != 0 ? iU2 : 1;
                }
                e().H(3, iA16, false);
                break;
            case 1417:
                int iA17 = aVar.a();
                if (b.a.a.h.a.c(aVar)) {
                    int iU3 = U(4);
                    iA17 = iU3 != 0 ? iU3 : 1;
                }
                e().H(4, iA17, false);
                break;
            default:
                switch (iC) {
                    case 1700:
                        int iA18 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iW = W();
                            iA18 = iW != 0 ? iW : 2;
                        }
                        e().v(iA18, false);
                        break;
                    case 1701:
                        int iA19 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iL = l(1);
                            iA19 = iL != 1 ? iL : 2;
                        }
                        e().J(1, iA19, false);
                        break;
                    case 1702:
                        int iA20 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iL2 = l(2);
                            iA20 = iL2 == 1 ? 2 : iL2;
                        }
                        e().J(2, iA20, false);
                        break;
                    case 1703:
                        int iA21 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iL3 = l(3);
                            iA21 = iL3 != 1 ? iL3 : 2;
                        }
                        e().J(3, iA21, false);
                        break;
                    case 1704:
                        int iA22 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iL4 = l(4);
                            iA22 = iL4 != 1 ? iL4 : 2;
                        }
                        e().J(4, iA22, false);
                        break;
                    case 1705:
                        int iA23 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int i = i(1);
                            iA23 = i != 0 ? i : 2;
                        }
                        e().K(1, iA23, false);
                        break;
                    case 1706:
                        int iA24 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int i2 = i(2);
                            iA24 = i2 == 0 ? 2 : i2;
                        }
                        e().K(2, iA24, false);
                        break;
                    case 1707:
                        int iA25 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int i3 = i(3);
                            iA25 = i3 != 0 ? i3 : 2;
                        }
                        e().K(3, iA25, false);
                        break;
                    case 1708:
                        int iA26 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int i4 = i(4);
                            iA26 = i4 != 0 ? i4 : 2;
                        }
                        e().K(4, iA26, false);
                        break;
                    case 1709:
                        int iA27 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iK = k(1);
                            iA27 = iK == 0 ? 1 : iK;
                        }
                        e().I(1, iA27, false);
                        break;
                    case 1710:
                        int iA28 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iK2 = k(2);
                            iA28 = iK2 != 0 ? iK2 : 1;
                        }
                        e().I(2, iA28, false);
                        break;
                    case 1711:
                        int iA29 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iK3 = k(3);
                            iA29 = iK3 != 0 ? iK3 : 1;
                        }
                        e().I(3, iA29, false);
                        break;
                    case 1712:
                        int iA30 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iK4 = k(4);
                            iA30 = iK4 != 0 ? iK4 : 1;
                        }
                        e().I(4, iA30, false);
                        break;
                    case 1713:
                        int iA31 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iH = h(1);
                            iA31 = iH == 0 ? 1 : iH;
                        }
                        e().W(1, iA31, false);
                        break;
                    case 1714:
                        int iA32 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iH2 = h(2);
                            iA32 = iH2 != 0 ? iH2 : 1;
                        }
                        e().W(2, iA32, false);
                        break;
                    case 1715:
                        int iA33 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iH3 = h(3);
                            iA33 = iH3 != 0 ? iH3 : 1;
                        }
                        e().W(3, iA33, false);
                        break;
                    case 1716:
                        int iA34 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            int iH4 = h(4);
                            iA34 = iH4 != 0 ? iH4 : 1;
                        }
                        e().W(4, iA34, false);
                        break;
                    case 1717:
                        int iA35 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            iA35 = j(1);
                        }
                        e().x(1, iA35, false);
                        break;
                    case 1718:
                        int iA36 = aVar.a();
                        if (b.a.a.h.a.c(aVar)) {
                            iA36 = j(2);
                        }
                        e().x(2, iA36, false);
                        break;
                    default:
                        switch (iC) {
                            case 1720:
                                int iA37 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA37 = j(3);
                                }
                                e().x(3, iA37, false);
                                break;
                            case 1721:
                                int iA38 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA38 = j(4);
                                }
                                e().x(4, iA38, false);
                                break;
                            case 1722:
                                int iA39 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA39 = g(1);
                                }
                                e().s(1, iA39, false);
                                break;
                            case 1723:
                                int iA40 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA40 = g(2);
                                }
                                e().s(2, iA40, false);
                                break;
                            case 1724:
                                int iA41 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA41 = g(3);
                                }
                                e().s(3, iA41, false);
                                break;
                            case 1725:
                                int iA42 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA42 = g(4);
                                }
                                e().s(4, iA42, false);
                                break;
                            case 1726:
                                int iA43 = aVar.a();
                                if (b.a.a.h.a.c(aVar)) {
                                    iA43 = X();
                                }
                                e().b0(iA43, false);
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public int p() {
        return this.f234c.V();
    }

    public int p0(int i) {
        e().v(i, true);
        if (i == 1 && Y() != 2) {
            e().h0(2, false);
            b.a.a.h.a.g(1400);
        }
        return this.f234c.y4(i);
    }

    public int q() {
        return this.f234c.W();
    }

    public int q0(int i) {
        e().b0(i, true);
        return this.f234c.z4(i);
    }

    public int r() {
        return this.f234c.X();
    }

    public int r0(final int i, boolean z) {
        e().h0(i, true);
        if (i == 1 && W() == 1) {
            e().v(2, false);
            b.a.a.h.a.g(1700);
        }
        if (z) {
            b.a.a.h.i.e(new Runnable() { // from class: b.a.a.g.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f217a.c0(i);
                }
            }, 200L);
        } else {
            this.f234c.A4(i);
        }
        return 0;
    }

    public int s() {
        return this.f234c.Y();
    }

    public int t() {
        return this.d.f();
    }

    public int u() {
        return this.f234c.n0();
    }

    public int v() {
        return this.f234c.o0();
    }

    public int w() {
        return this.f234c.p0();
    }

    public int x() {
        return this.f234c.q0();
    }

    public int y() {
        return this.f234c.r0();
    }

    public int z() {
        return this.f234c.s0();
    }
}
