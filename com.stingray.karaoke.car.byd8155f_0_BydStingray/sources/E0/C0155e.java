package E0;

import androidx.compose.ui.platform.AndroidComposeView;
import n0.C1870U;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: E0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0155e extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0155e f1652n = new C0155e(1, 0);
    public static final C0155e o = new C0155e(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0155e f1653p = new C0155e(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0155e f1654q = new C0155e(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0155e f1655r = new C0155e(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C0155e f1656s = new C0155e(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0155e f1657t = new C0155e(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0155e f1658u = new C0155e(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C0155e f1659v = new C0155e(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C0155e f1660w = new C0155e(1, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C0155e f1661x = new C0155e(1, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C0155e f1662y = new C0155e(1, 11);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final C0155e f1663z = new C0155e(1, 12);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1664m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0155e(int i7, int i10) {
        super(i7);
        this.f1664m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1664m) {
            case 0:
                ((C0153c) obj).J0();
                break;
            case 1:
                m0 m0Var = (m0) obj;
                if (m0Var.A()) {
                    m0Var.f1688m.t0(m0Var);
                }
                break;
            case 2:
                i0 i0Var = ((d0) obj).f1646O;
                if (i0Var != null) {
                    i0Var.invalidate();
                }
                break;
            case 3:
                d0 d0Var = (d0) obj;
                if (d0Var.A()) {
                    C0172w c0172w = d0Var.f1642K;
                    if (c0172w == null) {
                        d0Var.m1(true);
                    } else {
                        C0172w c0172w2 = d0.f1629R;
                        c0172w2.getClass();
                        c0172w2.f1707a = c0172w.f1707a;
                        c0172w2.f1708b = c0172w.f1708b;
                        c0172w2.c = c0172w.c;
                        c0172w2.d = c0172w.d;
                        c0172w2.f1709e = c0172w.f1709e;
                        c0172w2.f1710f = c0172w.f1710f;
                        c0172w2.f1711g = c0172w.f1711g;
                        c0172w2.f1712h = c0172w.f1712h;
                        c0172w2.f1713i = c0172w.f1713i;
                        d0Var.m1(true);
                        if (c0172w2.f1707a != c0172w.f1707a || c0172w2.f1708b != c0172w.f1708b || c0172w2.c != c0172w.c || c0172w2.d != c0172w.d || c0172w2.f1709e != c0172w.f1709e || c0172w2.f1710f != c0172w.f1710f || c0172w2.f1711g != c0172w.f1711g || c0172w2.f1712h != c0172w.f1712h || !C1870U.a(c0172w2.f1713i, c0172w.f1713i)) {
                            F f10 = d0Var.f1648w;
                            N nT = f10.t();
                            if (nT.f1565n > 0) {
                                if (nT.f1564m || nT.l) {
                                    f10.r0(false);
                                }
                                nT.f1568r.w0();
                            }
                            AndroidComposeView androidComposeView = f10.f1487t;
                            if (androidComposeView != null) {
                                ((V.d) androidComposeView.f11643S.f1590e.l).b(f10);
                                f10.f1477P = true;
                                androidComposeView.A(null);
                            }
                        }
                    }
                }
                break;
            case 4:
                g0 g0Var = (g0) obj;
                if (g0Var.A()) {
                    g0Var.l.L();
                }
                break;
            case 5:
                A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.node.OwnerScope");
                break;
            case 6:
                F f11 = (F) obj;
                if (f11.V()) {
                    f11.r0(false);
                }
                break;
            case 7:
                F f12 = (F) obj;
                if (f12.V()) {
                    f12.r0(false);
                }
                break;
            case 8:
                F f13 = (F) obj;
                if (f13.V()) {
                    f13.p0(false);
                }
                break;
            case 9:
                F f14 = (F) obj;
                if (f14.V()) {
                    f14.p0(false);
                }
                break;
            case 10:
                F f15 = (F) obj;
                if (f15.V()) {
                    F.q0(f15, false, 7);
                }
                break;
            case 11:
                F f16 = (F) obj;
                if (f16.V()) {
                    F.s0(f16, false, 7);
                }
                break;
            default:
                F f17 = (F) obj;
                if (f17.V()) {
                    f17.T();
                }
                break;
        }
        return m7.q.f19267a;
    }
}
