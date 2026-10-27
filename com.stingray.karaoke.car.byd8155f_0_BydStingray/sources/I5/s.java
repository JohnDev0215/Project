package I5;

import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.C0221p;
import F0.C0223q;
import H7.H;
import H7.InterfaceC0321f;
import Q5.C0607p1;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.S;
import X5.D0;
import a.AbstractC0904a;
import c6.C1034a;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.LayoutMode;
import h2.AbstractC1347d;
import k6.AbstractC1692D;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class s implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3017m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3018n;

    public /* synthetic */ s(int i7, Object obj, boolean z6) {
        this.l = i7;
        this.f3017m = obj;
        this.f3018n = z6;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        float fD0;
        S s6 = C0753m.f8270a;
        androidx.compose.foundation.layout.b bVar = androidx.compose.foundation.layout.b.f11474a;
        g0.l lVar = g0.l.f14684a;
        m7.q qVar = m7.q.f19267a;
        int i7 = 0;
        boolean z6 = this.f3018n;
        Object obj3 = this.f3017m;
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    int i10 = MainActivity.f12974j0;
                    MainActivity mainActivity = (MainActivity) obj3;
                    if (mainActivity.t().e() == LoadingInterface$LoadingState.Done) {
                        c0761q.Q(260763133);
                        g0.o oVarC = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
                        K kE = AbstractC0133o.e(g0.b.l, false);
                        int i11 = c0761q.f8304P;
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
                        if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                            AbstractC1347d.l(i11, c0761q, i11, c0158h);
                        }
                        C0735d.V(c0761q, oVarD, C0160j.c);
                        H.e(mainActivity.q(), (f6.g) mainActivity.f12981L.getValue(), (e6.h) mainActivity.f12982M.getValue(), (C1034a) mainActivity.f12983N.getValue(), (f6.o) mainActivity.f12984O.getValue(), c0761q, 0);
                        if (z6) {
                            c0761q.Q(-1691538005);
                            g0.o oVarA = bVar.a(lVar, g0.b.f14667s);
                            f6.h hVarQ = mainActivity.q();
                            f6.h hVarQ2 = mainActivity.q();
                            c0761q.Q(1192368728);
                            boolean zH = c0761q.h(hVarQ2);
                            Object objG = c0761q.G();
                            if (zH || objG == s6) {
                                objG = new C0223q(1, hVarQ2, f6.h.class, "changeTab", "changeTab(Lcom/stingray/karaoke/car/ui/navigation/StingrayKaraokeTabScreen;)V", 0, 2);
                                c0761q.a0(objG);
                            }
                            c0761q.p(false);
                            InterfaceC2782k interfaceC2782k = (InterfaceC2782k) ((InterfaceC0321f) objG);
                            StingrayKaraokeTabScreen stingrayKaraokeTabScreenA = mainActivity.q().l.a();
                            f6.h hVarQ3 = mainActivity.q();
                            c0761q.Q(1192374404);
                            boolean zH2 = c0761q.h(hVarQ3);
                            Object objG2 = c0761q.G();
                            if (zH2 || objG2 == s6) {
                                objG2 = new C0221p(0, hVarQ3, f6.h.class, "navigateToDevSettings", "navigateToDevSettings()V", 0, 3);
                                c0761q.a0(objG2);
                            }
                            c0761q.p(false);
                            AbstractC0904a.h(oVarA, hVarQ.f14622s, interfaceC2782k, stingrayKaraokeTabScreenA, (InterfaceC2772a) ((InterfaceC0321f) objG2), c0761q, 0);
                            c0761q.p(false);
                        } else {
                            c0761q.Q(-1690972472);
                            f6.h hVarQ4 = mainActivity.q();
                            f6.h hVarQ5 = mainActivity.q();
                            c0761q.Q(1192384088);
                            boolean zH3 = c0761q.h(hVarQ5);
                            Object objG3 = c0761q.G();
                            if (zH3 || objG3 == s6) {
                                objG3 = new C0223q(1, hVarQ5, f6.h.class, "changeTab", "changeTab(Lcom/stingray/karaoke/car/ui/navigation/StingrayKaraokeTabScreen;)V", 0, 3);
                                c0761q.a0(objG3);
                            }
                            c0761q.p(false);
                            InterfaceC2782k interfaceC2782k2 = (InterfaceC2782k) ((InterfaceC0321f) objG3);
                            StingrayKaraokeTabScreen stingrayKaraokeTabScreenA2 = mainActivity.q().l.a();
                            f6.h hVarQ6 = mainActivity.q();
                            c0761q.Q(1192389764);
                            boolean zH4 = c0761q.h(hVarQ6);
                            Object objG4 = c0761q.G();
                            if (zH4 || objG4 == s6) {
                                objG4 = new C0221p(0, hVarQ6, f6.h.class, "navigateToDevSettings", "navigateToDevSettings()V", 0, 4);
                                c0761q.a0(objG4);
                            }
                            c0761q.p(false);
                            AbstractC0904a.i(null, hVarQ4.f14622s, interfaceC2782k2, stingrayKaraokeTabScreenA2, false, (InterfaceC2772a) ((InterfaceC0321f) objG4), c0761q, 0);
                            c0761q.p(false);
                        }
                        c0761q.p(true);
                        c0761q.p(false);
                    } else {
                        c0761q.Q(260610272);
                        AbstractC2673a.n(null, mainActivity.t(), c0761q, 0);
                        c0761q.p(false);
                    }
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    int i12 = MainActivity.f12974j0;
                    MainActivity mainActivity2 = (MainActivity) obj3;
                    if (mainActivity2.t().e() == LoadingInterface$LoadingState.Done) {
                        g0.o oVarC2 = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
                        K kE2 = AbstractC0133o.e(g0.b.l, false);
                        int i13 = c0761q2.f8304P;
                        InterfaceC0744h0 interfaceC0744h0M2 = c0761q2.m();
                        g0.o oVarD2 = g0.a.d(c0761q2, oVarC2);
                        InterfaceC0161k.f1681b.getClass();
                        C0159i c0159i2 = C0160j.f1678b;
                        c0761q2.U();
                        if (c0761q2.f8303O) {
                            c0761q2.l(c0159i2);
                        } else {
                            c0761q2.d0();
                        }
                        C0735d.V(c0761q2, kE2, C0160j.f1679e);
                        C0735d.V(c0761q2, interfaceC0744h0M2, C0160j.d);
                        C0158h c0158h2 = C0160j.f1680f;
                        if (c0761q2.f8303O || !A7.m.a(c0761q2.G(), Integer.valueOf(i13))) {
                            AbstractC1347d.l(i13, c0761q2, i13, c0158h2);
                        }
                        C0735d.V(c0761q2, oVarD2, C0160j.c);
                        g0.o oVarA2 = bVar.a(lVar, g0.b.f14667s);
                        c0761q2.Q(1192412460);
                        float fZ = z6 ? m6.c.f19252a.Z(c0761q2) : 0;
                        c0761q2.p(false);
                        c0761q2.Q(1192416679);
                        c0761q2.Q(1192416742);
                        boolean z10 = z6 || c0761q2.k(AbstractC1692D.f18238a) == LayoutMode.WIDE_ULTRA;
                        c0761q2.p(false);
                        if (z10) {
                            fD0 = 0;
                        } else {
                            m6.b bVar2 = m6.c.f19252a;
                            bVar2.getClass();
                            c0761q2.Q(-1379251012);
                            fD0 = bVar2.D0(6, c0761q2) + (bVar2.C0(6, c0761q2) * 2);
                            c0761q2.p(false);
                        }
                        float f10 = fD0;
                        c0761q2.p(false);
                        g0.o oVarO = androidx.compose.foundation.layout.a.o(oVarA2, f10, 0.0f, 0.0f, fZ, 6);
                        D0 d0S = mainActivity2.s();
                        boolean zBooleanValue = ((Boolean) com.bumptech.glide.c.R(mainActivity2.r().f4655e0, c0761q2, 0).getValue()).booleanValue();
                        c0761q2.Q(1192430355);
                        boolean zH5 = c0761q2.h(mainActivity2);
                        Object objG5 = c0761q2.G();
                        if (zH5 || objG5 == s6) {
                            objG5 = new C0221p(0, mainActivity2, MainActivity.class, "onMiniPlayerClick", "onMiniPlayerClick()V", 0, 5);
                            c0761q2.a0(objG5);
                        }
                        c0761q2.p(false);
                        l9.d.n(oVarO, d0S, zBooleanValue, (InterfaceC2772a) ((InterfaceC0321f) objG5), ((Boolean) com.bumptech.glide.c.R(mainActivity2.q().f14624u, c0761q2, 0).getValue()).booleanValue(), c0761q2, 0);
                        c0761q2.p(true);
                    }
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    h6.d dVar = (h6.d) obj3;
                    AbstractC0904a.l(null, new C0607p1(dVar, "Dev Setting", false, null, 250), b0.g.b(-1796014024, new R5.c(i7, dVar, z6), c0761q3), c0761q3, 384, 1);
                }
                break;
        }
        return qVar;
    }
}
