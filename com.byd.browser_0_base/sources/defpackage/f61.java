package defpackage;

import J.N;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.theme.ThemeColorProvider;

/* JADX INFO: compiled from: LayoutManagerChromeTablet.java */
/* JADX INFO: loaded from: classes.dex */
public final class f61 extends c61 {
    public final i31 b0;
    public final ef2 c0;
    public final Callable<ViewGroup> d0;
    public zu2 e0;
    public xd3 f0;
    public e61 g0;
    public LayerTitleCache h0;
    public final nw2<lr2> i0;
    public final nw2<n1> j0;

    public f61(CompositorViewHolder compositorViewHolder, ViewGroup viewGroup, rs1 rs1Var, rs1 rs1Var2, aq1 aq1Var, zr zrVar, j31 j31Var, ViewGroup viewGroup2, ef2 ef2Var, r2 r2Var, as asVar) {
        super(compositorViewHolder, viewGroup, rs1Var, rs1Var2, aq1Var, zrVar, j31Var, viewGroup2, ef2Var);
        this.i0 = rs1Var;
        this.j0 = rs1Var2;
        zu2 zu2Var = new zu2(compositorViewHolder.getContext(), this, this.f.getLayoutRenderHost(), new hs(1, this), r2Var);
        this.e0 = zu2Var;
        this.b0 = j31Var;
        this.c0 = ef2Var;
        this.d0 = asVar;
        h(zu2Var);
        j(this.e0.s);
        V(null, true);
    }

    @Override // defpackage.c61, defpackage.h61
    public final void M(y13 y13Var, gz2 gz2Var, e00 e00Var, df0 df0Var, xd3 xd3Var) {
        super.M(y13Var, gz2Var, e00Var, df0Var, xd3Var);
        if (g90.c().a) {
            LayerTitleCache layerTitleCache = new LayerTitleCache(this.f.getContext(), H());
            this.h0 = layerTitleCache;
            layerTitleCache.b = y13Var;
        }
        zu2 zu2Var = this.e0;
        if (zu2Var == null || zu2Var.f == y13Var) {
            return;
        }
        zu2Var.x = new bv2(zu2Var);
        y13Var.u().a(zu2Var.x);
        zu2Var.f = y13Var;
        LayerTitleCache layerTitleCache2 = zu2Var.A.get();
        y13 y13Var2 = zu2Var.f;
        if (y13Var2 != null && layerTitleCache2 != null) {
            ArrayList arrayListY = y13Var2.y();
            for (int i = 0; i < arrayListY.size(); i++) {
                TabModel tabModel = (TabModel) arrayListY.get(i);
                for (int i2 = 0; i2 < tabModel.getCount(); i2++) {
                    Tab tabAt = tabModel.getTabAt(i2);
                    if (tabAt != null) {
                        layerTitleCache2.a(tabAt, tabAt.getContext().getString(R$string.tab_loading_default_title));
                    }
                }
            }
        }
        if (zu2Var.f.t()) {
            zu2Var.s();
        } else {
            zu2Var.f.m(new cv2(zu2Var));
        }
        TabModel tabModelG = zu2Var.f.g(false);
        fz2 fz2VarP0 = gz2Var.p0(false);
        vu2 vu2Var = zu2Var.j;
        if (vu2Var.c != tabModelG) {
            vu2Var.c = tabModelG;
            vu2Var.d = fz2VarP0;
            vu2Var.u(vu2Var.T);
            vu2Var.c(false);
        }
        TabModel tabModelG2 = zu2Var.f.g(true);
        fz2 fz2VarP02 = gz2Var.p0(true);
        vu2 vu2Var2 = zu2Var.k;
        if (vu2Var2.c != tabModelG2) {
            vu2Var2.c = tabModelG2;
            vu2Var2.d = fz2VarP02;
            vu2Var2.u(vu2Var2.T);
            vu2Var2.c(false);
        }
        zu2Var.r(zu2Var.f.r());
        zu2Var.u = new dv2(zu2Var, y13Var);
        zu2Var.v = new ev2(zu2Var, y13Var);
        zu2Var.f.m(zu2Var.w);
    }

    @Override // defpackage.c61, defpackage.h61
    public final void destroy() {
        e61 e61Var;
        super.destroy();
        LayerTitleCache layerTitleCache = this.h0;
        if (layerTitleCache != null) {
            long j = layerTitleCache.e;
            if (j != 0) {
                N.M4YKMV3h(j);
                layerTitleCache.e = 0L;
            }
            this.h0 = null;
        }
        zu2 zu2Var = this.e0;
        if (zu2Var != null) {
            f(zu2Var.s);
            zu2 zu2Var2 = this.e0;
            zu2Var2.r.destroy();
            zu2Var2.r = null;
            zu2Var2.k.k.removeCallbacksAndMessages(null);
            zu2Var2.j.k.removeCallbacksAndMessages(null);
            zu2Var2.y.e(zu2Var2);
            y13 y13Var = zu2Var2.f;
            if (y13Var != null) {
                y13Var.u().e(zu2Var2.x);
                zu2Var2.f.l(zu2Var2.w);
                zu2Var2.u.a();
                zu2Var2.v.destroy();
            }
            this.e0 = null;
        }
        xd3 xd3Var = this.f0;
        if (xd3Var == null || (e61Var = this.g0) == null) {
            return;
        }
        xd3Var.h.c(e61Var);
        this.f0 = null;
        this.g0 = null;
    }

    @Override // defpackage.h61, defpackage.n61
    public final void e(int i) {
        LayerTitleCache layerTitleCache = this.h0;
        if (layerTitleCache != null) {
            layerTitleCache.b(i);
        }
        super.e(i);
    }

    @Override // defpackage.c61, defpackage.h61
    public final void k0(int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        g61 g61Var = this.f;
        if ((g61Var != null ? g61Var.getBrowserControlsManager() : null) != null) {
            (g61Var != null ? g61Var.getBrowserControlsManager() : null).f.x();
        }
        super.k0(i, i2, i3, z, z2, f, f2);
    }

    @Override // defpackage.h61, defpackage.n61
    public final void l(int i) {
        this.h0.b(i);
    }

    @Override // defpackage.h61
    public final void n0(boolean z) {
        super.n0(z);
        this.k.c();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [e61] */
    @Override // defpackage.h61, defpackage.z51
    public final void t(int i, boolean z) {
        nw2<n1> nw2Var = this.j0;
        nw2<lr2> nw2Var2 = this.i0;
        if (i == 2 && this.S == null && this.U == null && oj2.I(this.f.getContext())) {
            try {
                if (!nw2Var2.f() && !nw2Var.f()) {
                    final ViewGroup viewGroupCall = this.d0.call();
                    p0(nw2Var2.get(), nw2Var.get(), this.b0, this.c0, viewGroupCall);
                    this.g0 = new ThemeColorProvider.a() { // from class: e61
                        @Override // org.chromium.chrome.browser.theme.ThemeColorProvider.a
                        public final void d(int i2, boolean z2) {
                            viewGroupCall.setBackgroundColor(i2);
                        }
                    };
                    xd3 xd3Var = this.N.get();
                    this.f0 = xd3Var;
                    xd3Var.h.a(this.g0);
                }
            } catch (Exception e) {
                throw new uv("Failed to initialize start surface.", e);
            }
        }
        super.t(i, z);
    }
}
