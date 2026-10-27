package U5;

import H7.H;
import Q.c1;
import T.C0753m;
import T.C0761q;
import android.content.Context;
import android.os.Bundle;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.l0;
import b2.C0991a;
import c6.C1034a;
import com.stingray.karaoke.car.ui.navigation.NavigationRoutes;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import java.text.NumberFormat;
import java.util.Locale;
import k6.AbstractC1692D;
import n3.C1945j;
import w.C2576g;
import z7.InterfaceC2772a;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final class m implements InterfaceC2787p {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9156m;

    public /* synthetic */ m(Object obj, int i7) {
        this.l = i7;
        this.f9156m = obj;
    }

    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        switch (this.l) {
            case 0:
                g0.o oVar = (g0.o) obj;
                int iIntValue = ((Number) obj2).intValue();
                C0761q c0761q = (C0761q) obj3;
                int iIntValue2 = ((Number) obj4).intValue();
                A7.m.f(oVar, "modifier");
                if ((iIntValue2 & 6) == 0) {
                    i7 = (c0761q.f(oVar) ? 4 : 2) | iIntValue2;
                } else {
                    i7 = iIntValue2;
                }
                if ((iIntValue2 & 48) == 0) {
                    i7 |= c0761q.d(iIntValue) ? 32 : 16;
                }
                if ((i7 & 147) == 146 && c0761q.x()) {
                    c0761q.K();
                } else {
                    g0.o oVarM = androidx.compose.foundation.layout.a.m(oVar, m6.c.f19252a.F(c0761q), 0.0f, 2);
                    Locale locale = ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getConfiguration().getLocales().get(0);
                    A7.m.c(locale);
                    String str = NumberFormat.getNumberInstance(locale).format(Integer.valueOf(iIntValue));
                    A7.m.e(str, "format(...)");
                    c1.b(str, oVarM, l6.d.c(c0761q).f18696b, h6.f.g((FontSize) this.f9156m, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, com.bumptech.glide.d.O(((O5.a) c0761q.k(O5.b.f5155b)).f5144b, l6.d.c(c0761q).f18687T, 20.0f), c0761q, 0, 0, 65008);
                }
                return m7.q.f19267a;
            case 1:
                C1945j c1945j = (C1945j) obj2;
                C0761q c0761q2 = (C0761q) obj3;
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f(c1945j, "backStackEntry");
                c0761q2.Q(-1542080799);
                c0761q2.R(1890788296);
                l0 l0VarA = c2.b.a(c0761q2);
                if (l0VarA == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU = H.u(l0VarA, c0761q2);
                c0761q2.R(1729797275);
                f0 f0VarB0 = C7.a.b0(d6.j.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q2);
                c0761q2.p(false);
                c0761q2.p(false);
                d6.j jVar = (d6.j) f0VarB0;
                Bundle bundleC = c1945j.c();
                jVar.f12250s = bundleC != null ? bundleC.getString("id") : null;
                jVar.f12239i = jVar.f12238h;
                c0761q2.Q(737573066);
                f6.h hVar = (f6.h) this.f9156m;
                boolean zH = c0761q2.h(hVar);
                Object objG = c0761q2.G();
                if (zH || objG == C0753m.f8270a) {
                    objG = new A3.g(hVar, 6);
                    c0761q2.a0(objG);
                }
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG;
                c0761q2.p(false);
                c0761q2.Q(737577114);
                W5.e eVar = jVar.f13481t;
                int size = eVar.c.size();
                boolean z6 = size >= 2 && Q8.h.C0((CharSequence) eVar.c.get(size - 2), NavigationRoutes.SONG_DETAILS.getValue(), false) && c0761q2.k(AbstractC1692D.f18238a) != LayoutMode.WIDE_ULTRA;
                c0761q2.p(false);
                jVar.f12240j = z6 ? interfaceC2772a : null;
                jVar.m(NavigationType.MAIN);
                c0761q2.p(false);
                io.sentry.config.a.G(jVar, false, c0761q2, 0);
                return m7.q.f19267a;
            case 2:
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                com.bumptech.glide.d.h((f6.g) this.f9156m, (C0761q) obj3, 0);
                return m7.q.f19267a;
            case 3:
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                l9.d.f((C1034a) this.f9156m, (C0761q) obj3, 0);
                return m7.q.f19267a;
            default:
                ((Number) obj4).intValue();
                A7.m.f((C2576g) obj, "$this$composable");
                A7.m.f((C1945j) obj2, "it");
                l9.l.n((f6.o) this.f9156m, (C0761q) obj3, 0);
                return m7.q.f19267a;
        }
    }
}
