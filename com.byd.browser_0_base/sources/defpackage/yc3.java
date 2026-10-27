package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.compositor.layouts.eventfilter.BlackHoleEventFilter;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: ToolbarSwipeLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class yc3 extends y51 {
    public final aq1<Tab> A;
    public final boolean B;
    public float C;
    public float D;
    public float E;
    public final float F;
    public final float G;
    public final BlackHoleEventFilter H;
    public zc3 I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final kj f102J;
    public boolean K;
    public int L;
    public int M;
    public m61 t;
    public m61 u;
    public m61 v;
    public m61 w;
    public final pd3 x;
    public final pd3 y;
    public final aq1<Tab> z;

    public yc3(Context context, h61 h61Var, j61 j61Var, fj fjVar, h61 h61Var2, xd3 xd3Var) {
        super(context, h61Var, j61Var);
        this.H = new BlackHoleEventFilter(context);
        this.f102J = fjVar;
        Resources resources = context.getResources();
        float f = 1.0f / resources.getDisplayMetrics().density;
        this.G = resources.getDimension(R$dimen.toolbar_swipe_commit_distance) * f;
        this.F = resources.getDimension(R$dimen.toolbar_swipe_space_between_tabs) * f;
        int i = 1;
        boolean z = !DeviceFormFactor.a(context);
        this.B = z;
        aq1<Tab> aq1Var = new aq1<>();
        this.z = aq1Var;
        aq1<Tab> aq1Var2 = new aq1<>();
        this.A = aq1Var2;
        if (z) {
            pd3 pd3Var = new pd3(this.i, h61Var2, new ps(2), aq1Var, fjVar, new yq2(1, this), xd3Var, 4, true);
            this.x = pd3Var;
            pd3Var.d(true);
            h61Var2.h(pd3Var);
            pd3 pd3Var2 = new pd3(this.i, h61Var2, new xo(2), aq1Var2, fjVar, new yo(i, this), xd3Var, 4, true);
            this.y = pd3Var2;
            pd3Var2.d(true);
            h61Var2.h(pd3Var2);
        }
    }

    @Override // defpackage.y51
    public final void F(TabContentManager tabContentManager) {
        super.F(tabContentManager);
        this.I = new zc3(this.i, tabContentManager);
    }

    @Override // defpackage.y51
    public final void H(long j, boolean z) {
        int iK;
        super.H(j, z);
        this.o = null;
        this.v = null;
        this.t = null;
        this.u = null;
        this.w = null;
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        y13 y13Var = this.k;
        if (y13Var == null) {
            return;
        }
        Tab tabI = y13Var.i();
        if (tabI != null && tabI.isNativePage()) {
            this.l.a(tabI);
        }
        TabModel tabModelZ = this.k.z();
        if (tabModelZ == null || (iK = this.k.k()) == -1) {
            return;
        }
        m61 m61VarC = c(iK, tabModelZ.isIncognito());
        this.v = m61VarC;
        Q(m61VarC, false);
        if (this.K) {
            int i = this.L;
            int iG = z7.G(this.k.z(), this.M);
            int iG2 = z7.G(this.k.z(), i);
            R(iG < iG2 ? 1 : 2, iG, iG2);
            this.w = iG < iG2 ? this.u : this.t;
            float f = iG < iG2 ? -this.e : this.e;
            I(i, false);
            P(0.0f, f, 350L);
            Tab tabS = this.k.s(this.M);
            if (bk3.l(tabS.getUrl()) && !tabS.g() && !tabS.i()) {
                this.k.g(tabS.isIncognito()).h(tabS, tabI, false, false);
            }
            this.K = false;
            this.L = -1;
            this.M = -1;
        }
    }

    @Override // defpackage.y51
    public final void L() {
        float fMax;
        float fMin;
        m61 m61Var = this.v;
        if (m61Var == null) {
            return;
        }
        if (this.t == null && this.u == null) {
            this.u = m61Var;
        }
        float f = this.D;
        float f2 = this.E;
        float fO = a8.o(f, f2 - 30.0f, 30.0f + f2);
        float f3 = C0173a.f(f2, fO, 0.8f, fO);
        this.D = f3;
        boolean z = true;
        boolean z2 = Math.abs(f3 - this.E) >= 0.1f;
        if ((this.t != null) ^ (this.u != null)) {
            float f4 = this.D / this.e;
            fMin = Math.signum(f4) * h21.a.getInterpolation(Math.abs(f4)) * (this.e / 5.0f);
            fMax = fMin;
        } else {
            float fO2 = a8.o((this.D / this.e) + (this.C == 0.0f ? 0.0f : 1.0f), 0.0f, 1.0f);
            float f5 = this.e;
            float f6 = this.F;
            float f7 = (((f5 + f6) - 0.0f) * fO2) + 0.0f;
            float fMin2 = (f7 - f6) - Math.min(f5, this.t.s());
            float f8 = this.e / 2.0f;
            m61 m61Var2 = this.u;
            PropertyModel.o oVar = m61.n;
            float fG = m61Var2.g(oVar);
            float fS = m61Var2.s();
            PropertyModel.o oVar2 = m61.g;
            fMax = Math.max(f8 - (Math.min(fG, m61Var2.g(oVar2) * fS) / 2.0f), f7);
            m61 m61Var3 = this.t;
            fMin = Math.min(f8 - (Math.min(m61Var3.g(oVar), m61Var3.g(oVar2) * m61Var3.s()) / 2.0f), fMin2);
        }
        float f9 = this.i.getResources().getDisplayMetrics().density;
        m61 m61Var4 = this.t;
        kj kjVar = this.f102J;
        pd3 pd3Var = this.x;
        if (m61Var4 != null) {
            if (pd3Var != null) {
                pd3Var.d(true);
                boolean z3 = this.t != this.v;
                td3 td3Var = pd3Var.g;
                td3Var.getClass();
                td3Var.i.l(ud3.a, z3);
                td3Var.i.m(ud3.i, fMin * f9);
            }
            this.t.m(m61.h, fMin);
            this.t.m(m61.i, kjVar.f() / f9);
            z2 = O(this.t) || z2;
        } else if (pd3Var != null) {
            pd3Var.d(false);
        }
        m61 m61Var5 = this.u;
        pd3 pd3Var2 = this.y;
        if (m61Var5 != null) {
            if (pd3Var2 != null) {
                pd3Var2.d(true);
                boolean z4 = this.u != this.v;
                td3 td3Var2 = pd3Var2.g;
                td3Var2.getClass();
                td3Var2.i.l(ud3.a, z4);
                td3Var2.i.m(ud3.i, fMax * f9);
            }
            this.u.m(m61.h, fMax);
            this.u.m(m61.i, kjVar.f() / f9);
            if (!O(this.u) && !z2) {
                z = false;
            }
            z2 = z;
        } else if (pd3Var2 != null) {
            pd3Var2.d(false);
        }
        if (z2) {
            E();
        }
    }

    @Override // defpackage.y51
    public final void M(RectF rectF, TabContentManager tabContentManager, ResourceManager resourceManager, fj fjVar) {
        if (this.I != null) {
            y13 y13Var = this.k;
            int iA = (y13Var == null || !y13Var.r()) ? ii2.a(this.i) : this.i.getColor(R$color.default_bg_color_dark);
            this.I.e(this.t, true, iA);
            this.I.e(this.u, false, iA);
        }
    }

    public final void P(float f, float f2, long j) {
        if (j <= 0) {
            return;
        }
        kw kwVarE = kw.e(j(), f, f2, j, null);
        kwVarE.g.add(new jt1(1, this));
        kwVarE.start();
    }

    public final void Q(m61 m61Var, boolean z) {
        if (m61Var.k(m61.A)) {
            m61Var.m(m61.q, 0.0f);
        }
        float fR = m61Var.r();
        m61Var.m(m61.n, m61Var.s());
        m61Var.m(m61.o, fR);
        m61Var.m(m61.g, 1.0f);
        m61Var.m(m61.s, 1.0f);
        m61Var.v();
        m61Var.m(m61.i, 0.0f);
        m61Var.l(m61.C, this.B);
        m61Var.l(m61.D, z);
    }

    public final void R(int i, int i2, int i3) {
        int id;
        int id2;
        m61 m61Var;
        boolean z = i == 2;
        int i4 = z ? i3 : i2;
        if (z) {
            i3 = i2;
        }
        aq1<Tab> aq1Var = this.z;
        aq1Var.r(null);
        aq1<Tab> aq1Var2 = this.A;
        aq1Var2.r(null);
        TabModel tabModelZ = this.k.z();
        if (i4 < 0 || i4 >= tabModelZ.getCount()) {
            id = -1;
        } else {
            id = tabModelZ.getTabAt(i4).getId();
            m61 m61VarC = c(id, tabModelZ.isIncognito());
            this.t = m61VarC;
            Q(m61VarC, i4 != i2);
            aq1Var.r(tabModelZ.getTabAt(i4));
        }
        if (i3 < 0 || i3 >= tabModelZ.getCount()) {
            id2 = -1;
        } else {
            id2 = tabModelZ.getTabAt(i3).getId();
            m61 m61VarC2 = c(id2, tabModelZ.isIncognito());
            this.u = m61VarC2;
            Q(m61VarC2, i3 != i2);
            aq1Var2.r(tabModelZ.getTabAt(i3));
        }
        int i5 = z ? id2 : id;
        if (!z) {
            id = id2;
        }
        ArrayList arrayList = new ArrayList();
        if (id != -1) {
            arrayList.add(Integer.valueOf(id));
        }
        if (i5 != -1) {
            arrayList.add(Integer.valueOf(i5));
        }
        K(arrayList);
        this.w = null;
        this.C = z ? 0.0f : this.e;
        this.D = 0.0f;
        this.E = 0.0f;
        m61 m61Var2 = this.t;
        if (m61Var2 != null && (m61Var = this.u) != null) {
            this.o = new m61[]{m61Var2, m61Var};
        } else if (m61Var2 != null) {
            this.o = new m61[]{m61Var2};
        } else {
            m61 m61Var3 = this.u;
            if (m61Var3 != null) {
                this.o = new m61[]{m61Var3};
            } else {
                this.o = null;
            }
        }
        E();
    }

    @Override // defpackage.y51
    public final boolean i() {
        return this.B;
    }

    @Override // defpackage.y51
    public final EventFilter k() {
        return this.H;
    }

    @Override // defpackage.y51
    public final int m() {
        return 4;
    }

    @Override // defpackage.y51
    public final SceneLayer n() {
        return this.I;
    }

    @Override // defpackage.y51
    public final int o() {
        return !this.B ? 1 : 0;
    }
}
