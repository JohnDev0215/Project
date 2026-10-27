package W5;

import H7.H;
import T.C0761q;
import a6.C0928a;
import android.os.Bundle;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.l0;
import b2.C0991a;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import g1.AbstractC1274a;
import h6.C1382a;
import i6.C1415d;
import n3.C1945j;
import w.C2576g;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class h implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h f9822m = new h(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final h f9823n = new h(1);
    public static final h o = new h(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final h f9824p = new h(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final h f9825q = new h(4);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final h f9826r = new h(5);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final h f9827s = new h(6);
    public final /* synthetic */ int l;

    public /* synthetic */ h(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.l) {
            case 0:
                C1945j c1945j = (C1945j) obj2;
                C0761q c0761q = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f(c1945j, "backStackEntry");
                c0761q.R(1890788296);
                l0 l0VarA = c2.b.a(c0761q);
                if (l0VarA == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU = H.u(l0VarA, c0761q);
                c0761q.R(1729797275);
                f0 f0VarB0 = C7.a.b0(C1415d.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q);
                c0761q.p(false);
                c0761q.p(false);
                C1415d c1415d = (C1415d) f0VarB0;
                Bundle bundleC = c1945j.c();
                c1415d.f12250s = bundleC != null ? bundleC.getString("id") : null;
                c1415d.f12239i = c1415d.f12238h;
                c1415d.m(NavigationType.MAIN);
                w0.c.B(c1415d, c0761q, 0);
                return m7.q.f19267a;
            case 1:
                C1945j c1945j2 = (C1945j) obj2;
                C0761q c0761q2 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f(c1945j2, "backStackEntry");
                c0761q2.R(1890788296);
                l0 l0VarA2 = c2.b.a(c0761q2);
                if (l0VarA2 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU2 = H.u(l0VarA2, c0761q2);
                c0761q2.R(1729797275);
                f0 f0VarB02 = C7.a.b0(C0928a.class, l0VarA2, fVarU2, l0VarA2 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA2).e() : C0991a.f12202b, c0761q2);
                c0761q2.p(false);
                c0761q2.p(false);
                C0928a c0928a = (C0928a) f0VarB02;
                Bundle bundleC2 = c1945j2.c();
                c0928a.f12250s = bundleC2 != null ? bundleC2.getString("id") : null;
                c0928a.f12239i = c0928a.f12238h;
                c0928a.m(NavigationType.MAIN);
                AbstractC1274a.d(c0928a, c0761q2, 0);
                return m7.q.f19267a;
            case 2:
                C0761q c0761q3 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q3.R(1890788296);
                l0 l0VarA3 = c2.b.a(c0761q3);
                if (l0VarA3 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU3 = H.u(l0VarA3, c0761q3);
                c0761q3.R(1729797275);
                f0 f0VarB03 = C7.a.b0(f6.p.class, l0VarA3, fVarU3, l0VarA3 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA3).e() : C0991a.f12202b, c0761q3);
                c0761q3.p(false);
                c0761q3.p(false);
                f6.p pVar = (f6.p) f0VarB03;
                pVar.f12239i = pVar.f12238h;
                pVar.m(NavigationType.MAIN);
                AbstractC1274a.q(pVar, c0761q3, 0);
                return m7.q.f19267a;
            case 3:
                C0761q c0761q4 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q4.R(1890788296);
                l0 l0VarA4 = c2.b.a(c0761q4);
                if (l0VarA4 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU4 = H.u(l0VarA4, c0761q4);
                c0761q4.R(1729797275);
                f0 f0VarB04 = C7.a.b0(C1382a.class, l0VarA4, fVarU4, l0VarA4 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA4).e() : C0991a.f12202b, c0761q4);
                c0761q4.p(false);
                c0761q4.p(false);
                C1382a c1382a = (C1382a) f0VarB04;
                c1382a.f12239i = c1382a.f12238h;
                c1382a.m(NavigationType.MAIN);
                com.bumptech.glide.d.b(c1382a, c0761q4, 0);
                return m7.q.f19267a;
            case 4:
                C0761q c0761q5 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q5.R(1890788296);
                l0 l0VarA5 = c2.b.a(c0761q5);
                if (l0VarA5 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU5 = H.u(l0VarA5, c0761q5);
                c0761q5.R(1729797275);
                f0 f0VarB05 = C7.a.b0(h6.h.class, l0VarA5, fVarU5, l0VarA5 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA5).e() : C0991a.f12202b, c0761q5);
                c0761q5.p(false);
                c0761q5.p(false);
                h6.h hVar = (h6.h) f0VarB05;
                hVar.f12239i = hVar.f12238h;
                hVar.m(NavigationType.MAIN);
                P8.n.c(hVar, c0761q5, 0);
                return m7.q.f19267a;
            case 5:
                C0761q c0761q6 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q6.R(1890788296);
                l0 l0VarA6 = c2.b.a(c0761q6);
                if (l0VarA6 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU6 = H.u(l0VarA6, c0761q6);
                c0761q6.R(1729797275);
                f0 f0VarB06 = C7.a.b0(h6.b.class, l0VarA6, fVarU6, l0VarA6 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA6).e() : C0991a.f12202b, c0761q6);
                c0761q6.p(false);
                c0761q6.p(false);
                h6.b bVar = (h6.b) f0VarB06;
                bVar.f12239i = bVar.f12238h;
                bVar.m(NavigationType.MAIN);
                l9.l.h(bVar, c0761q6, 0);
                return m7.q.f19267a;
            default:
                C0761q c0761q7 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q7.R(1890788296);
                l0 l0VarA7 = c2.b.a(c0761q7);
                if (l0VarA7 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU7 = H.u(l0VarA7, c0761q7);
                c0761q7.R(1729797275);
                f0 f0VarB07 = C7.a.b0(h6.d.class, l0VarA7, fVarU7, l0VarA7 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA7).e() : C0991a.f12202b, c0761q7);
                c0761q7.p(false);
                c0761q7.p(false);
                h6.d dVar = (h6.d) f0VarB07;
                dVar.f12239i = dVar.f12238h;
                dVar.m(NavigationType.MAIN);
                l9.l.i(dVar, c0761q7, 0);
                return m7.q.f19267a;
        }
    }
}
