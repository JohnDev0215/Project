package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ViewGroup;
import defpackage.C0188h0;
import defpackage.dx2;
import defpackage.l61;
import java.util.ArrayList;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: LayoutManagerChrome.java */
/* JADX INFO: loaded from: classes.dex */
public class c61 extends h61 implements C0188h0.b {
    public org.chromium.chrome.browser.accessibility_tab_switcher.b Q;
    public yc3 R;
    public l43 S;
    public or2 T;
    public y51 U;
    public final b V;
    public boolean W;
    public boolean X;
    public a Y;
    public zp1<TabContentManager> Z;
    public boolean a0;

    /* JADX INFO: compiled from: LayoutManagerChrome.java */
    public class a implements l61.a {
        public final /* synthetic */ boolean e;
        public final /* synthetic */ n1 f;
        public final /* synthetic */ lr2 g;

        public a(boolean z, n1 n1Var, lr2 lr2Var) {
            this.e = z;
            this.f = n1Var;
            this.g = lr2Var;
        }

        @Override // l61.a
        public final void h(int i) {
            if (i == 2) {
                if (this.e) {
                    this.f.s().i();
                } else {
                    this.g.A().i();
                }
            }
        }
    }

    /* JADX INFO: compiled from: LayoutManagerChrome.java */
    public class b implements dx2.a {
        public int e;
        public final boolean f = true;

        public b() {
        }

        @Override // dx2.a
        public final void f(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            c61 c61Var = c61.this;
            yc3 yc3Var = c61Var.R;
            if (yc3Var != null) {
                if (yc3Var == ((h61) yc3Var.m).q) {
                    motionEvent.getRawX();
                    motionEvent.getRawX();
                    float f5 = c61Var.e;
                    float f6 = f * f5;
                    float f7 = f3 * f5;
                    yc3 yc3Var2 = c61Var.R;
                    SystemClock.uptimeMillis();
                    float f8 = yc3Var2.e * 0.5f;
                    yc3Var2.E = a8.o(yc3Var2.C + (a8.o(f7 * 0.033333335f, -f8, f8) + f6), 0.0f, yc3Var2.e) - yc3Var2.C;
                    yc3Var2.E();
                }
            }
        }

        @Override // dx2.a
        public final void h(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            int iIndex;
            c61 c61Var = c61.this;
            if (c61Var.R == null) {
                return;
            }
            motionEvent.getRawX();
            motionEvent.getRawY();
            float f5 = c61Var.e;
            float f6 = f3 * f5;
            float f7 = f4 * f5;
            float f8 = f * f5;
            if (this.e != 0) {
                yc3 yc3Var = c61Var.R;
                SystemClock.uptimeMillis();
                yc3Var.E = a8.o(yc3Var.C + f8, 0.0f, yc3Var.e) - yc3Var.C;
                yc3Var.E();
                return;
            }
            double degrees = (Math.toDegrees(Math.atan2(-f7, f6)) + 360.0d) % 360.0d;
            int i = (degrees >= 205.0d || degrees <= 155.0d) ? (degrees < 25.0d || degrees > 335.0d) ? 2 : (degrees >= 295.0d || degrees <= 245.0d) ? 0 : 4 : 1;
            this.e = i;
            if (i == 0) {
                return;
            }
            if (this.f && ((c61Var.S != null || c61Var.U != null) && i == 4)) {
                n3.X0("MobileToolbarSwipeOpenStackView");
                c61Var.t(2, true);
            } else if (i == 1 || i == 2) {
                c61Var.g0(c61Var.R, true);
            }
            yc3 yc3Var2 = c61Var.R;
            SystemClock.uptimeMillis();
            int i2 = this.e;
            y13 y13Var = yc3Var2.k;
            if (y13Var == null || yc3Var2.w != null || i2 == 4) {
                return;
            }
            boolean z = i2 == 2;
            TabModel tabModelZ = y13Var.z();
            if (tabModelZ == null || (iIndex = tabModelZ.index()) == -1) {
                return;
            }
            yc3Var2.R(i2, iIndex, LocalizationUtils.isLayoutRtl() ^ z ? iIndex - 1 : iIndex + 1);
        }

        @Override // dx2.a
        public final void k() {
            float f;
            m61 m61Var;
            m61 m61Var2;
            yc3 yc3Var = c61.this.R;
            if (yc3Var != null) {
                if (yc3Var == ((h61) yc3Var.m).q) {
                    SystemClock.uptimeMillis();
                    if (yc3Var.v == null || yc3Var.k == null) {
                        return;
                    }
                    float fMin = Math.min(yc3Var.G, yc3Var.e / 3.0f);
                    m61 m61Var3 = yc3Var.v;
                    yc3Var.w = m61Var3;
                    float f2 = yc3Var.E;
                    float f3 = 0.0f;
                    if (f2 <= fMin || (m61Var2 = yc3Var.t) == null) {
                        if (f2 < (-fMin) && (m61Var = yc3Var.u) != null) {
                            yc3Var.w = m61Var;
                            f3 = 0.0f - yc3Var.e;
                        }
                        f = f3;
                    } else {
                        yc3Var.w = m61Var2;
                        f = yc3Var.e + 0.0f;
                    }
                    if (yc3Var.w != m61Var3) {
                        n3.X0("MobileSideSwipeFinished");
                    }
                    yc3Var.I(yc3Var.w.q(), false);
                    float f4 = yc3Var.E;
                    long jAbs = (long) ((Math.abs(f4 - f) * 500.0f) / yc3Var.e);
                    yc3Var.w.q();
                    yc3Var.P(f4, f, jAbs);
                }
            }
        }

        @Override // dx2.a
        public final boolean q(int i) {
            c61 c61Var = c61.this;
            rq0 fullscreenManager = c61Var.f.getFullscreenManager();
            if (c61Var.q != c61Var.i || !g90.c().e) {
                return false;
            }
            if (fullscreenManager != null && fullscreenManager.g()) {
                return false;
            }
            if (i == 4) {
                return ((c61Var.S == null && c61Var.U == null) || qo.f().c()) ? false : true;
            }
            return i == 1 || i == 2;
        }

        @Override // dx2.a
        public final void v(int i) {
            this.e = 0;
        }
    }

    public c61(CompositorViewHolder compositorViewHolder, ViewGroup viewGroup, rs1 rs1Var, rs1 rs1Var2, aq1 aq1Var, nw2 nw2Var, j31 j31Var, ViewGroup viewGroup2, ef2 ef2Var) {
        super(compositorViewHolder, viewGroup, aq1Var, nw2Var);
        this.W = true;
        this.V = new b();
        this.Z = aq1Var;
        aq1Var.p(new b61(this, aq1Var));
        if (!zb2.e(compositorViewHolder.getContext())) {
            if (rs1Var.f()) {
                p0((lr2) rs1Var.get(), null, j31Var, ef2Var, viewGroup2);
            }
        } else if (rs1Var.f() || rs1Var2.f()) {
            p0((lr2) rs1Var.get(), (n1) rs1Var2.get(), j31Var, ef2Var, viewGroup2);
        }
    }

    @Override // defpackage.h61
    public y51 D(int i) {
        y51 y51Var;
        if (i == 4) {
            return this.R;
        }
        if (i != 2) {
            return i == 16 ? this.T : super.D(i);
        }
        boolean zA = g90.a(this.f.getContext());
        boolean z = zA && this.q == this.Q;
        y51 y51Var2 = this.q;
        l43 l43Var = this.S;
        if ((z || zA) && !((y51Var2 == l43Var && l43Var != null) || (y51Var2 == (y51Var = this.U) && y51Var != null))) {
            return this.Q;
        }
        y51 y51Var3 = this.U;
        return y51Var3 != null ? y51Var3 : l43Var;
    }

    @Override // defpackage.h61
    public final dx2.a I() {
        return this.V;
    }

    @Override // defpackage.h61
    public void M(y13 y13Var, gz2 gz2Var, e00 e00Var, df0 df0Var, xd3 xd3Var) {
        g61 g61Var = this.f;
        Context context = g61Var.getContext();
        j61 layoutRenderHost = g61Var.getLayoutRenderHost();
        fj browserControlsManager = g61Var.getBrowserControlsManager();
        this.Q = new org.chromium.chrome.browser.accessibility_tab_switcher.b(context, this, layoutRenderHost, browserControlsManager);
        this.R = new yc3(context, this, layoutRenderHost, browserControlsManager, this, xd3Var);
        super.M(y13Var, gz2Var, e00Var, df0Var, xd3Var);
        TabContentManager tabContentManager = this.Z.get();
        this.R.G(y13Var, tabContentManager);
        this.Q.G(y13Var, tabContentManager);
        l43 l43Var = this.S;
        if (l43Var != null) {
            l43Var.G(y13Var, tabContentManager);
            this.S.u();
        }
        y51 y51Var = this.U;
        if (y51Var != null) {
            y51Var.G(y13Var, tabContentManager);
            this.U.u();
        }
        or2 or2Var = this.T;
        if (or2Var != null) {
            or2Var.G(y13Var, tabContentManager);
            this.T.u();
        }
        this.a0 = true;
    }

    @Override // defpackage.h61
    public void P(boolean z) {
        if (this.q == this.i) {
            return;
        }
        super.P(z);
    }

    @Override // defpackage.h61
    public final void X(y13 y13Var) {
        super.X(y13Var);
        l43 l43Var = this.S;
        if (l43Var != null) {
            l43Var.G(y13Var, null);
        }
        y51 y51Var = this.U;
        if (y51Var != null) {
            y51Var.G(y13Var, null);
        }
        or2 or2Var = this.T;
        if (or2Var != null) {
            or2Var.G(y13Var, null);
        }
    }

    @Override // defpackage.h61
    public final boolean Y(y51 y51Var) {
        return this.W && (y51Var == this.S || y51Var == this.U) && this.X && !oj2.z(this.f.getContext());
    }

    @Override // defpackage.h61
    public final boolean Z(y51 y51Var, int i) {
        m61 m61VarL;
        return (this.W && (y51Var == this.S || y51Var == this.U) && (m61VarL = y51Var.l(i)) != null && m61VarL.k(m61.C)) ? false : true;
    }

    @Override // defpackage.h61
    public final boolean c0(boolean z) {
        return z && (!this.W || this.k.z().getCount() <= 0);
    }

    @Override // defpackage.h61
    public void destroy() {
        super.destroy();
        if (this.Z != null) {
            this.Z = null;
        }
        a aVar = this.Y;
        if (aVar != null) {
            f(aVar);
            this.Y = null;
        }
        l43 l43Var = this.S;
        if (l43Var != null) {
            l43Var.d();
            this.S = null;
        }
        y51 y51Var = this.U;
        if (y51Var != null) {
            y51Var.d();
            this.U = null;
        }
        if (this.T != null) {
            this.T = null;
        }
        org.chromium.chrome.browser.accessibility_tab_switcher.b bVar = this.Q;
        if (bVar != null) {
            bVar.d();
        }
    }

    @Override // defpackage.h61
    public final void f0(ArrayList arrayList) {
        int i = 0;
        while (true) {
            ArrayList arrayList2 = this.L;
            if (i >= arrayList2.size()) {
                return;
            }
            if (((je2) arrayList2.get(i)).q1()) {
                ((je2) arrayList2.get(i)).f0(arrayList);
            }
            i++;
        }
    }

    @Override // defpackage.h61
    public final void g0(y51 y51Var, boolean z) {
        this.X = false;
        super.g0(y51Var, z);
    }

    @Override // defpackage.h61
    public final void h0(int i, Tab tab) {
        if (i == -1) {
            super.h0(i, tab);
            return;
        }
        yc3 yc3Var = this.R;
        int id = tab.getId();
        yc3Var.K = true;
        yc3Var.L = id;
        yc3Var.M = i;
        t(4, false);
    }

    @Override // defpackage.h61, defpackage.n61
    public final void k() {
        Object obj = this.r;
        if (obj == null) {
            obj = this.i;
        }
        if (obj == this.i) {
            y13 y13Var = this.k;
            Tab tabI = y13Var != null ? y13Var.i() : null;
            int id = tabI != null ? tabI.getId() : -1;
            SparseArray<m61> sparseArray = this.u;
            m61 m61Var = sparseArray.get(id);
            sparseArray.clear();
            if (m61Var != null) {
                sparseArray.put(id, m61Var);
            }
        }
        super.k();
    }

    @Override // defpackage.h61
    public void k0(int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        Tab tabF = z7.F(this.k.g(z), i);
        this.X = tabF != null && tabF.isNativePage();
        super.k0(i, i2, i3, z, z2, f, f2);
    }

    public final void p0(lr2 lr2Var, n1 n1Var, i31 i31Var, ef2 ef2Var, ViewGroup viewGroup) {
        g61 g61Var = this.f;
        boolean zE = zb2.e(g61Var.getContext());
        Context context = g61Var.getContext();
        j61 layoutRenderHost = g61Var.getLayoutRenderHost();
        if (zE) {
            this.U = z7.r().d(context, this, layoutRenderHost, n1Var, i31Var, viewGroup, ef2Var);
            if (lr2Var != null) {
                this.T = new or2(context, this, layoutRenderHost, lr2Var);
            }
        } else {
            this.S = new l43(context, this, layoutRenderHost, lr2Var, i31Var, viewGroup, ef2Var);
        }
        if (oj2.I(g61Var.getContext())) {
            a aVar = new a(zE, n1Var, lr2Var);
            this.Y = aVar;
            j(aVar);
        }
        if (this.Z.f()) {
            l43 l43Var = this.S;
            if (l43Var != null) {
                l43Var.F(this.Z.get());
            }
            y51 y51Var = this.U;
            if (y51Var != null) {
                y51Var.F(this.Z.get());
            }
            or2 or2Var = this.T;
            if (or2Var != null) {
                or2Var.F(this.Z.get());
            }
        }
        y13 y13Var = this.k;
        if (y13Var != null) {
            l43 l43Var2 = this.S;
            if (l43Var2 != null) {
                l43Var2.G(y13Var, this.Z.get());
            }
            y51 y51Var2 = this.U;
            if (y51Var2 != null) {
                y51Var2.G(this.k, this.Z.get());
            }
            or2 or2Var2 = this.T;
            if (or2Var2 != null) {
                or2Var2.G(this.k, this.Z.get());
            }
        }
        if (this.a0) {
            l43 l43Var3 = this.S;
            if (l43Var3 != null) {
                l43Var3.u();
            }
            y51 y51Var3 = this.U;
            if (y51Var3 != null) {
                y51Var3.u();
            }
            or2 or2Var3 = this.T;
            if (or2Var3 != null) {
                or2Var3.u();
            }
        }
    }

    @Override // defpackage.C0188h0.b
    public final void y0(boolean z) {
        this.W = g90.b();
    }
}
