package M5;

import A3.v;
import A7.F;
import A7.m;
import C.k;
import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import H7.InterfaceC0321f;
import Q.M;
import Q5.G1;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.S;
import U5.i;
import X5.H;
import X5.I;
import X5.J;
import a.AbstractC0904a;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.l0;
import b2.C0991a;
import c6.C1034a;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import g0.l;
import g0.o;
import h2.AbstractC1347d;
import h6.n;
import n0.AbstractC1861K;
import n3.C1930E;
import w.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class g implements InterfaceC2786o {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f4210m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4211n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4212p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4213q;

    public g(k kVar, boolean z6, String str, InterfaceC2772a interfaceC2772a, L0.g gVar) {
        this.f4211n = kVar;
        this.f4210m = z6;
        this.o = str;
        this.f4212p = interfaceC2772a;
        this.f4213q = gVar;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                o oVar = (o) obj;
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                m.f(oVar, "$this$composed");
                c0761q.Q(-866185531);
                androidx.compose.material3.c cVarB = M.b(7);
                k kVar = (k) this.f4211n;
                if (kVar == null) {
                    c0761q.Q(113770490);
                    Object objG = c0761q.G();
                    if (objG == C0753m.f8270a) {
                        objG = new k();
                        c0761q.a0(objG);
                    }
                    kVar = (k) objG;
                    c0761q.p(false);
                }
                o oVarB = g0.a.b(oVar, new e(kVar, cVarB, this.f4210m, (String) this.o, (L0.g) this.f4213q, (InterfaceC2772a) this.f4212p));
                c0761q.p(false);
                return oVarB;
            default:
                C0761q c0761q2 = (C0761q) obj2;
                ((Number) obj3).intValue();
                m.f((q) obj, "$this$AnimatedVisibility");
                c0761q2.Q(650270579);
                SideMenuType sideMenuType = (SideMenuType) this.f4211n;
                boolean zF = c0761q2.f(sideMenuType);
                C1930E c1930e = (C1930E) this.o;
                boolean zH = zF | c0761q2.h(c1930e);
                Object objG2 = c0761q2.G();
                S s6 = C0753m.f8270a;
                if (zH || objG2 == s6) {
                    objG2 = new H(sideMenuType, c1930e, null);
                    c0761q2.a0(objG2);
                }
                c0761q2.p(false);
                C0735d.f(c0761q2, sideMenuType, (InterfaceC2785n) objG2);
                o oVarB2 = androidx.compose.foundation.layout.c.b(androidx.compose.foundation.a.b(l.f14684a, l6.d.c(c0761q2).f18704g, AbstractC1861K.f19381a), 1.0f);
                c0761q2.Q(650290216);
                float f10 = this.f4210m ? h6.f.f(R.dimen.split_view_percent, c0761q2) : 1.0f;
                c0761q2.p(false);
                o oVarD = androidx.compose.foundation.layout.c.d(oVarB2, f10);
                c0761q2.Q(650293480);
                Object objG3 = c0761q2.G();
                if (objG3 == s6) {
                    objG3 = new v(3);
                    c0761q2.a0(objG3);
                }
                c0761q2.p(false);
                o oVarO = com.bumptech.glide.c.O(oVarD, (InterfaceC2772a) objG3);
                K kE = AbstractC0133o.e(g0.b.l, false);
                int i7 = c0761q2.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q2.m();
                o oVarD2 = g0.a.d(c0761q2, oVarO);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i = C0160j.f1678b;
                c0761q2.U();
                if (c0761q2.f8303O) {
                    c0761q2.l(c0159i);
                } else {
                    c0761q2.d0();
                }
                C0735d.V(c0761q2, kE, C0160j.f1679e);
                C0735d.V(c0761q2, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q2.f8303O || !m.a(c0761q2.G(), Integer.valueOf(i7))) {
                    AbstractC1347d.l(i7, c0761q2, i7, c0158h);
                }
                C0735d.V(c0761q2, oVarD2, C0160j.c);
                int i10 = sideMenuType == null ? -1 : J.f10179a[sideMenuType.ordinal()];
                m7.q qVar = m7.q.f19267a;
                if (i10 != -1) {
                    InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f4213q;
                    if (i10 == 1) {
                        c0761q2.Q(-832394858);
                        c0761q2.Q(-996666335);
                        Object objG4 = c0761q2.G();
                        if (objG4 == s6) {
                            objG4 = new i(5);
                            c0761q2.a0(objG4);
                        }
                        c0761q2.p(false);
                        C0735d.d(qVar, (InterfaceC2782k) objG4, c0761q2);
                        c0761q2.Q(-996657200);
                        boolean zF2 = c0761q2.f(interfaceC2782k);
                        Object objG5 = c0761q2.G();
                        if (zF2 || objG5 == s6) {
                            objG5 = new G1(13, interfaceC2782k);
                            c0761q2.a0(objG5);
                        }
                        InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG5;
                        c0761q2.p(false);
                        c0761q2.Q(-996652494);
                        boolean zH2 = c0761q2.h(c1930e) | c0761q2.f(interfaceC2782k);
                        Object objG6 = c0761q2.G();
                        if (zH2 || objG6 == s6) {
                            objG6 = new I(c1930e, interfaceC2782k);
                            c0761q2.a0(objG6);
                        }
                        c0761q2.p(false);
                        AbstractC0904a.k((PlayerStatus) this.f4212p, interfaceC2772a, c1930e, (InterfaceC2772a) ((InterfaceC0321f) objG6), c0761q2, 0);
                        c0761q2.p(false);
                    } else {
                        if (i10 != 2) {
                            throw b.d(-996684113, c0761q2, false);
                        }
                        c0761q2.Q(-831334813);
                        c0761q2.Q(-996646137);
                        c0761q2.R(1890788296);
                        l0 l0VarA = c2.b.a(c0761q2);
                        if (l0VarA == null) {
                            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                        }
                        N6.f fVarU = H7.H.u(l0VarA, c0761q2);
                        c0761q2.R(1729797275);
                        f0 f0VarB0 = C7.a.b0(C1034a.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q2);
                        c0761q2.p(false);
                        c0761q2.p(false);
                        C1034a c1034a = (C1034a) f0VarB0;
                        c0761q2.Q(-731728798);
                        boolean zF3 = c0761q2.f(interfaceC2782k);
                        Object objG7 = c0761q2.G();
                        if (zF3 || objG7 == s6) {
                            objG7 = new G1(14, interfaceC2782k);
                            c0761q2.a0(objG7);
                        }
                        c0761q2.p(false);
                        c1034a.f12240j = (InterfaceC2772a) objG7;
                        c1034a.m(NavigationType.PLAYER);
                        c0761q2.p(false);
                        c0761q2.Q(-996637925);
                        boolean zF4 = c0761q2.f(interfaceC2782k);
                        Object objG8 = c0761q2.G();
                        if (zF4 || objG8 == s6) {
                            objG8 = new G1(15, interfaceC2782k);
                            c0761q2.a0(objG8);
                        }
                        c0761q2.p(false);
                        n.a(false, (InterfaceC2772a) objG8, c0761q2, 0, 1);
                        c0761q2.Q(-996633546);
                        boolean zH3 = c0761q2.h(c1034a);
                        Object objG9 = c0761q2.G();
                        if (zH3 || objG9 == s6) {
                            objG9 = new F(c1034a, 8);
                            c0761q2.a0(objG9);
                        }
                        c0761q2.p(false);
                        C0735d.d(qVar, (InterfaceC2782k) objG9, c0761q2);
                        l9.d.f(c1034a, c0761q2, 0);
                        c0761q2.p(false);
                    }
                } else {
                    c0761q2.Q(-996623088);
                    io.sentry.config.a.t(null, 0L, c0761q2, 0, 3);
                    c0761q2.p(false);
                }
                c0761q2.p(true);
                return qVar;
        }
    }

    public g(SideMenuType sideMenuType, C1930E c1930e, boolean z6, PlayerStatus playerStatus, InterfaceC2782k interfaceC2782k) {
        this.f4211n = sideMenuType;
        this.o = c1930e;
        this.f4210m = z6;
        this.f4212p = playerStatus;
        this.f4213q = interfaceC2782k;
    }
}
