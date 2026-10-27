package X5;

import T.C0761q;
import a.AbstractC0904a;
import a6.C0928a;
import android.os.Bundle;
import androidx.lifecycle.InterfaceC0977o;
import b2.C0991a;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import g1.AbstractC1274a;
import g6.C1306d;
import i6.C1415d;
import java.net.URLDecoder;
import n3.C1945j;
import w.C2576g;
import z7.InterfaceC2772a;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class M implements InterfaceC2787p {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f10185m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f10186n;

    public /* synthetic */ M(InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, int i7) {
        this.l = i7;
        this.f10185m = interfaceC2772a;
        this.f10186n = interfaceC2772a2;
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
                androidx.lifecycle.l0 l0VarA = c2.b.a(c0761q);
                if (l0VarA == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU = H7.H.u(l0VarA, c0761q);
                c0761q.R(1729797275);
                androidx.lifecycle.f0 f0VarB0 = C7.a.b0(C0928a.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q);
                c0761q.p(false);
                c0761q.p(false);
                C0928a c0928a = (C0928a) f0VarB0;
                Bundle bundleC = c1945j.c();
                c0928a.f12250s = URLDecoder.decode(bundleC != null ? bundleC.getString("id") : null, "utf-8");
                c0928a.f12239i = this.f10185m;
                c0928a.f12240j = this.f10186n;
                c0928a.m(NavigationType.PLAYER);
                AbstractC1274a.d(c0928a, c0761q, 0);
                return m7.q.f19267a;
            case 1:
                C1945j c1945j2 = (C1945j) obj2;
                C0761q c0761q2 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f(c1945j2, "backStackEntry");
                c0761q2.R(1890788296);
                androidx.lifecycle.l0 l0VarA2 = c2.b.a(c0761q2);
                if (l0VarA2 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU2 = H7.H.u(l0VarA2, c0761q2);
                c0761q2.R(1729797275);
                androidx.lifecycle.f0 f0VarB02 = C7.a.b0(d6.j.class, l0VarA2, fVarU2, l0VarA2 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA2).e() : C0991a.f12202b, c0761q2);
                c0761q2.p(false);
                c0761q2.p(false);
                d6.j jVar = (d6.j) f0VarB02;
                Bundle bundleC2 = c1945j2.c();
                jVar.f12250s = URLDecoder.decode(bundleC2 != null ? bundleC2.getString("id") : null, "utf-8");
                jVar.f12239i = this.f10185m;
                jVar.f12240j = this.f10186n;
                jVar.m(NavigationType.PLAYER);
                io.sentry.config.a.G(jVar, false, c0761q2, 0);
                return m7.q.f19267a;
            case 2:
                C0761q c0761q3 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q3.R(1890788296);
                androidx.lifecycle.l0 l0VarA3 = c2.b.a(c0761q3);
                if (l0VarA3 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU3 = H7.H.u(l0VarA3, c0761q3);
                c0761q3.R(1729797275);
                androidx.lifecycle.f0 f0VarB03 = C7.a.b0(C1306d.class, l0VarA3, fVarU3, l0VarA3 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA3).e() : C0991a.f12202b, c0761q3);
                c0761q3.p(false);
                c0761q3.p(false);
                C1306d c1306d = (C1306d) f0VarB03;
                c1306d.f12239i = this.f10185m;
                c1306d.f12240j = this.f10186n;
                c1306d.m(NavigationType.PLAYER);
                l9.d.r(c1306d, c0761q3, 0);
                return m7.q.f19267a;
            case 3:
                C0761q c0761q4 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                c0761q4.R(1890788296);
                androidx.lifecycle.l0 l0VarA4 = c2.b.a(c0761q4);
                if (l0VarA4 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU4 = H7.H.u(l0VarA4, c0761q4);
                c0761q4.R(1729797275);
                androidx.lifecycle.f0 f0VarB04 = C7.a.b0(e6.i.class, l0VarA4, fVarU4, l0VarA4 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA4).e() : C0991a.f12202b, c0761q4);
                c0761q4.p(false);
                c0761q4.p(false);
                e6.i iVar = (e6.i) f0VarB04;
                iVar.f12239i = this.f10185m;
                iVar.f12240j = this.f10186n;
                iVar.m(NavigationType.PLAYER);
                AbstractC0904a.c(iVar, true, c0761q4, 48, 0);
                return m7.q.f19267a;
            default:
                C1945j c1945j3 = (C1945j) obj2;
                C0761q c0761q5 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f(c1945j3, "backStackEntry");
                c0761q5.R(1890788296);
                androidx.lifecycle.l0 l0VarA5 = c2.b.a(c0761q5);
                if (l0VarA5 == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU5 = H7.H.u(l0VarA5, c0761q5);
                c0761q5.R(1729797275);
                androidx.lifecycle.f0 f0VarB05 = C7.a.b0(C1415d.class, l0VarA5, fVarU5, l0VarA5 instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA5).e() : C0991a.f12202b, c0761q5);
                c0761q5.p(false);
                c0761q5.p(false);
                C1415d c1415d = (C1415d) f0VarB05;
                Bundle bundleC3 = c1945j3.c();
                c1415d.f12250s = URLDecoder.decode(bundleC3 != null ? bundleC3.getString("id") : null, "utf-8");
                c1415d.f12239i = this.f10185m;
                c1415d.f12240j = this.f10186n;
                c1415d.m(NavigationType.PLAYER);
                w0.c.B(c1415d, c0761q5, 0);
                return m7.q.f19267a;
        }
    }
}
