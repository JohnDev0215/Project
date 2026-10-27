package I5;

import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0752l0;
import T.C0753m;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import T.S;
import T.X;
import android.content.res.Configuration;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.MainLayerLevel;
import h2.AbstractC1347d;
import k6.AbstractC1692D;
import k6.C1691C;
import k6.C1701i;
import n3.C1930E;
import n3.O;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class v implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MainActivity f3023m;

    public /* synthetic */ v(MainActivity mainActivity, int i7) {
        this.l = i7;
        this.f3023m = mainActivity;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        m7.q qVar = m7.q.f19267a;
        MainActivity mainActivity = this.f3023m;
        int i7 = 2;
        int i10 = 1;
        int i11 = 0;
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    int i12 = MainActivity.f12974j0;
                    X xR = com.bumptech.glide.c.R(mainActivity.s().f10122R, c0761q, 0);
                    g0.o oVarC = androidx.compose.foundation.layout.c.c(g0.l.f14684a, 1.0f);
                    K kE = AbstractC0133o.e(g0.b.l, false);
                    int i13 = c0761q.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                    g0.o oVarD = g0.a.d(c0761q, oVarC);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q.U();
                    if (c0761q.f8303O) {
                        c0761q.l(c0159i);
                    } else {
                        c0761q.d0();
                    }
                    C0735d.V(c0761q, kE, C0160j.f1679e);
                    C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                        AbstractC1347d.l(i13, c0761q, i13, c0158h);
                    }
                    C0735d.V(c0761q, oVarD, C0160j.c);
                    C0735d.a(AbstractC1692D.f18239b.a(Boolean.TRUE), b0.g.b(-433783156, new u(mainActivity, i11, xR), c0761q), c0761q, 56);
                    c0761q.p(true);
                }
                return qVar;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    boolean zBooleanValue = ((Boolean) c0761q2.k(AbstractC1692D.c)).booleanValue();
                    int i14 = MainActivity.f12974j0;
                    X xR2 = com.bumptech.glide.c.R(mainActivity.q().f14621r, c0761q2, 0);
                    mainActivity.o().f4038j = mainActivity.n();
                    mainActivity.o().f4039k = mainActivity.r();
                    L5.i iVarO = mainActivity.o();
                    c0761q2.Q(-1118880074);
                    boolean zH = c0761q2.h(mainActivity);
                    Object objG = c0761q2.G();
                    S s6 = C0753m.f8270a;
                    if (zH || objG == s6) {
                        objG = new q(mainActivity, i11);
                        c0761q2.a0(objG);
                    }
                    InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG;
                    c0761q2.p(false);
                    A7.m.f(interfaceC2772a, "<set-?>");
                    iVarO.f4040m = interfaceC2772a;
                    L5.i iVarO2 = mainActivity.o();
                    c0761q2.Q(-1118869560);
                    boolean zH2 = c0761q2.h(mainActivity);
                    Object objG2 = c0761q2.G();
                    if (zH2 || objG2 == s6) {
                        objG2 = new q(mainActivity, i10);
                        c0761q2.a0(objG2);
                    }
                    InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG2;
                    c0761q2.p(false);
                    A7.m.f(interfaceC2772a2, "<set-?>");
                    iVarO2.l = interfaceC2772a2;
                    LoadingInterface$LoadingState loadingInterface$LoadingStateE = mainActivity.t().e();
                    c0761q2.Q(-1118858779);
                    boolean zH3 = c0761q2.h(mainActivity);
                    Object objG3 = c0761q2.G();
                    if (zH3 || objG3 == s6) {
                        objG3 = new r(mainActivity, null);
                        c0761q2.a0(objG3);
                    }
                    c0761q2.p(false);
                    C0735d.f(c0761q2, loadingInterface$LoadingStateE, (InterfaceC2785n) objG3);
                    io.sentry.hints.i iVar = k6.o.f18284a;
                    io.sentry.hints.i.m(MainLayerLevel.BACKGROUND, f.f2950a);
                    io.sentry.hints.i.m(MainLayerLevel.MAIN, b0.g.b(1592057924, new s(i11, mainActivity, zBooleanValue), c0761q2));
                    io.sentry.hints.i.m(MainLayerLevel.MINI_PLAYER, b0.g.b(-642986875, new s(i10, mainActivity, zBooleanValue), c0761q2));
                    io.sentry.hints.i.m(MainLayerLevel.PLAYER, b0.g.b(1416935622, new v(mainActivity, i11), c0761q2));
                    com.bumptech.glide.c.R(mainActivity.p().f18249f, c0761q2, 0);
                    com.bumptech.glide.c.R(mainActivity.p().f18256n, c0761q2, 0);
                    com.bumptech.glide.c.R(mainActivity.p().o, c0761q2, 0);
                    com.bumptech.glide.c.R(mainActivity.p().f18257p, c0761q2, 0);
                    com.bumptech.glide.c.R(mainActivity.p().f18258q, c0761q2, 0);
                    c0761q2.Q(-1118644746);
                    io.sentry.hints.i.m(MainLayerLevel.DEV_SETTINGS_DISPLAY_INFO, null);
                    io.sentry.hints.i.m(MainLayerLevel.DEV_SETTINGS_LOG_BOX, null);
                    c0761q2.p(false);
                    C1691C c1691c = mainActivity.f13003h0;
                    if (c1691c == null) {
                        A7.m.j("uxRestrictionManager");
                        throw null;
                    }
                    X xR3 = com.bumptech.glide.c.R(c1691c.d, c0761q2, 0);
                    C1691C c1691c2 = mainActivity.f13003h0;
                    if (c1691c2 == null) {
                        A7.m.j("uxRestrictionManager");
                        throw null;
                    }
                    X xR4 = com.bumptech.glide.c.R(c1691c2.f18236j, c0761q2, 0);
                    Boolean bool = (Boolean) xR2.getValue();
                    bool.booleanValue();
                    c0761q2.Q(-1118595779);
                    boolean zF = c0761q2.f(xR2) | c0761q2.f(xR3) | c0761q2.h(mainActivity);
                    Object objG4 = c0761q2.G();
                    if (zF || objG4 == s6) {
                        objG4 = new x(xR2, xR3, mainActivity, null);
                        c0761q2.a0(objG4);
                    }
                    c0761q2.p(false);
                    C0735d.f(c0761q2, bool, (InterfaceC2785n) objG4);
                    Boolean bool2 = (Boolean) xR4.getValue();
                    bool2.booleanValue();
                    c0761q2.Q(-1118505965);
                    boolean zF2 = c0761q2.f(xR4) | c0761q2.h(mainActivity);
                    Object objG5 = c0761q2.G();
                    if (zF2 || objG5 == s6) {
                        objG5 = new z(xR4, mainActivity, null);
                        c0761q2.a0(objG5);
                    }
                    c0761q2.p(false);
                    C0735d.f(c0761q2, bool2, (InterfaceC2785n) objG5);
                    Boolean bool3 = (Boolean) com.bumptech.glide.c.R(mainActivity.p().l, c0761q2, 0).getValue();
                    c0761q2.Q(-1118489188);
                    boolean zO = bool3 == null ? h6.n.o(c0761q2) : bool3.booleanValue();
                    c0761q2.p(false);
                    l6.d.b(zO, true, f.d, c0761q2, 432, 0);
                }
                return qVar;
            case 2:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    C0735d.b(new C0752l0[]{AbstractC1692D.f18238a.a(h6.i.m(c0761q3)), AbstractC1692D.c.a(Boolean.valueOf(!AbstractC1692D.c(h6.i.m(c0761q3))))}, b0.g.b(643667792, new v(mainActivity, i10), c0761q3), c0761q3, 56);
                }
                return qVar;
            default:
                C0761q c0761q4 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    W5.e eVarN = mainActivity.n();
                    NavigationType navigationType = NavigationType.MAIN;
                    C1930E c1930eQ = h6.n.q(new O[0], c0761q4);
                    A7.m.f(navigationType, "navigationType");
                    eVarN.d.put(navigationType, c1930eQ);
                    W5.e eVarN2 = mainActivity.n();
                    NavigationType navigationType2 = NavigationType.TAB;
                    C1930E c1930eQ2 = h6.n.q(new O[0], c0761q4);
                    A7.m.f(navigationType2, "navigationType");
                    eVarN2.d.put(navigationType2, c1930eQ2);
                    W5.e eVarN3 = mainActivity.n();
                    NavigationType navigationType3 = NavigationType.PLAYER;
                    C1930E c1930eQ3 = h6.n.q(new O[0], c0761q4);
                    A7.m.f(navigationType3, "navigationType");
                    eVarN3.d.put(navigationType3, c1930eQ3);
                    C0773y c0773y = AbstractC1692D.d;
                    C0773y c0773y2 = AndroidCompositionLocals_androidKt.f11687a;
                    C0752l0 c0752l0A = c0773y.a(new k6.u(((Configuration) c0761q4.k(c0773y2)).screenWidthDp, ((Configuration) c0761q4.k(c0773y2)).screenHeightDp));
                    C0752l0 c0752l0A2 = AbstractC1692D.f18240e.a(new C1701i(0.0f, 7));
                    C0773y c0773y3 = k6.s.f18295a;
                    if (mainActivity.f12999d0 == null) {
                        A7.m.j("parentalControlsManager");
                        throw null;
                    }
                    C0735d.b(new C0752l0[]{c0752l0A, c0752l0A2, c0773y3.a(Boolean.valueOf(!((Boolean) com.bumptech.glide.c.R(r8.f18294e, c0761q4, 0).getValue()).booleanValue()))}, b0.g.b(-2117234032, new v(mainActivity, i7), c0761q4), c0761q4, 56);
                }
                return qVar;
        }
    }
}
