package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ViewGroup;
import defpackage.dx2;
import defpackage.l61;
import defpackage.nw;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: LayoutManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class h61 implements kb1, n61, vb {
    public boolean A;
    public boolean F;
    public final ew G;
    public final zp1<TabContentManager> I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final nw.a f40J;
    public fj K;
    public final nw2<xd3> N;
    public d P;
    public final float e;
    public final g61 f;
    public int g;
    public int h;
    public ts2 i;
    public final ViewGroup j;
    public y13 k;
    public b l;
    public a m;
    public c n;
    public y51 q;
    public y51 r;
    public boolean s;
    public EventFilter t;
    public boolean x;
    public final nt1 y;
    public final Context z;
    public final org.chromium.base.b<l61.a> o = new org.chromium.base.b<>();
    public final org.chromium.base.b<ie2> p = new org.chromium.base.b<>();
    public final SparseArray<m61> u = new SparseArray<>();
    public int v = -1;
    public int w = -1;
    public final RectF B = new RectF();
    public final RectF C = new RectF();
    public final RectF D = new RectF();
    public final PointF E = new PointF();
    public final aq1<y13> H = new aq1<>();
    public final ArrayList L = new ArrayList();
    public final HashMap M = new HashMap();
    public final aq1<Boolean> O = new aq1<>();

    /* JADX INFO: compiled from: LayoutManagerImpl.java */
    public class a extends j23 {
        public a(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void A0(TabImpl tabImpl) {
            h61.this.e(tabImpl.getId());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void M(int i, Tab tab) {
            h61.this.e(tab.getId());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            h61.this.e(tab.getId());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void l0(int i, Tab tab) {
            h61.this.e(tab.getId());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            h61.this.e(tab.getId());
        }
    }

    /* JADX INFO: compiled from: LayoutManagerImpl.java */
    public class b implements e23 {
        public b() {
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            h61.this.n0(tabModel.isIncognito());
        }
    }

    /* JADX INFO: compiled from: LayoutManagerImpl.java */
    public class c implements t13 {
        public c() {
        }

        @Override // defpackage.t13
        public final void B0(int i, int i2, Tab tab) {
            h61 h61Var = h61.this;
            if (i == 4) {
                h61Var.h0(i2, tab);
                return;
            }
            if (tab.getId() != i2) {
                tab.getId();
                tab.isIncognito();
                if (h61Var.q != null) {
                    SystemClock.uptimeMillis();
                }
            }
        }

        @Override // defpackage.t13
        public final void V0(Tab tab) {
            h61.u(h61.this, tab.getId(), tab.isIncognito(), false);
        }

        @Override // defpackage.t13
        public final void X0(boolean z) {
            h61.this.P(z);
        }

        @Override // defpackage.t13
        public final void d1(int i, Tab tab) {
            if (i == 3 || i == 6 || i == 1 || i == 7 || i == 11 || i == 15) {
                return;
            }
            h61 h61Var = h61.this;
            int iK = h61Var.k.k();
            tab.isIncognito();
            h61Var.l0(iK);
        }

        @Override // defpackage.t13
        public final void f1(Tab tab, int i, int i2, boolean z) {
            float f;
            float f2;
            int id = tab.getId();
            h61 h61Var = h61.this;
            if (i == 3) {
                h61Var.q.B(SystemClock.uptimeMillis());
                return;
            }
            boolean zIsIncognito = tab.isIncognito();
            boolean z2 = !(i == 5 || i == 14 || i == 17) || (!h61Var.k.r() && zIsIncognito);
            float width = LocalizationUtils.isLayoutRtl() ? h61Var.f.getWidth() * h61Var.e : 0.0f;
            if (i != 2) {
                float heightMinusBrowserControls = h61Var.f.getHeightMinusBrowserControls();
                float f3 = h61Var.e;
                f2 = (f3 * h61Var.h) - (heightMinusBrowserControls * f3);
                f = h61Var.g * f3;
            } else {
                f = width;
                f2 = 0.0f;
            }
            h61Var.k0(id, h61Var.k.k(), i, zIsIncognito, z2, f, f2);
        }

        @Override // defpackage.t13
        public final void h0(Tab tab) {
            h61.u(h61.this, tab.getId(), tab.isIncognito(), false);
        }

        @Override // defpackage.t13
        public final void i1(Tab tab) {
            tab.getId();
            tab.isIncognito();
            y51 y51Var = h61.this.q;
            if (y51Var != null) {
                SystemClock.uptimeMillis();
                y51Var.x();
            }
        }

        @Override // defpackage.t13
        public final void n0(Tab tab) {
            h61.u(h61.this, tab.getId(), tab.isIncognito(), true);
        }

        @Override // defpackage.t13
        public final void w0(List<Tab> list, boolean z) {
            if (z) {
                return;
            }
            for (Tab tab : list) {
                h61.u(h61.this, tab.getId(), tab.isIncognito(), false);
            }
        }
    }

    /* JADX INFO: compiled from: LayoutManagerImpl.java */
    public class d implements AutoCloseable {
        public boolean e;

        public d() {
            h61.this.P = this;
        }

        @Override // java.lang.AutoCloseable
        public final void close() {
            h61 h61Var = h61.this;
            h61Var.P = null;
            if (this.e) {
                h61Var.C();
            }
        }
    }

    public h61(g61 g61Var, ViewGroup viewGroup, zp1<TabContentManager> zp1Var, nw2<xd3> nw2Var) {
        this.f = g61Var;
        this.e = 1.0f / g61Var.getContext().getResources().getDisplayMetrics().density;
        this.I = zp1Var;
        this.N = nw2Var;
        this.z = g61Var.getContext();
        g61Var.getLayoutRenderHost();
        Class[] clsArr = {pt1.class, pd3.class, zu2.class, vf2.class, st2.class};
        for (int i = 0; i < 5; i++) {
            this.M.put(clsArr[i], Integer.valueOf(i));
        }
        this.j = viewGroup;
        this.G = new ew(new androidx.appcompat.widget.a(20, this));
        this.y = new nt1();
        this.f40J = new nw.a(new wv(27, this));
    }

    public static void u(h61 h61Var, int i, boolean z, boolean z2) {
        y13 y13Var = h61Var.k;
        Tab tabI = y13Var != null ? y13Var.i() : null;
        h61Var.j0(i, tabI != null ? tabI.getId() : -1, z, z2);
    }

    public final void C() {
        d dVar = this.P;
        if (dVar != null) {
            dVar.e = true;
            return;
        }
        Iterator<l61.a> it = this.o.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((l61.a) aVar.next()).h(this.q.m());
            }
        }
    }

    public y51 D(int i) {
        if (i == 0 || i == 1) {
            return this.i;
        }
        return null;
    }

    public final PointF E(MotionEvent motionEvent) {
        int iT = a8.t(motionEvent.getActionMasked());
        PointF pointF = this.E;
        if (iT == 0 || iT == 9) {
            RectF rectF = this.D;
            K(rectF);
            pointF.set(-rectF.left, -rectF.top);
            return pointF;
        }
        if (iT != 1 && iT != 3 && iT != 10) {
            return null;
        }
        pointF.set(0.0f, 0.0f);
        return pointF;
    }

    public final ResourceManager H() {
        g61 g61Var = this.f;
        if (g61Var.getLayoutRenderHost() == null) {
            return null;
        }
        return g61Var.getLayoutRenderHost().getResourceManager();
    }

    public dx2.a I() {
        return null;
    }

    public final void K(RectF rectF) {
        y51 y51Var = this.q;
        g61 g61Var = this.f;
        if (y51Var == null) {
            g61Var.h(rectF);
            return;
        }
        int iO = y51Var.o();
        if (iO == 0) {
            g61Var.h(rectF);
            return;
        }
        if (iO == 1) {
            g61Var.g(rectF);
            return;
        }
        if (iO != 3) {
            g61Var.e(rectF);
        } else if (this.A) {
            g61Var.g(rectF);
        } else {
            g61Var.h(rectF);
        }
    }

    public void M(y13 y13Var, gz2 gz2Var, e00 e00Var, df0 df0Var, xd3 xd3Var) {
        g61 g61Var = this.f;
        j61 layoutRenderHost = g61Var.getLayoutRenderHost();
        this.K = g61Var.getBrowserControlsManager();
        this.i = new ts2(this.z, this, layoutRenderHost, this.f, this.f40J, y13Var, this.I.get(), this.K, this.N);
        V(null, true);
        nt1 nt1Var = this.y;
        Iterator it = nt1Var.a.iterator();
        while (it.hasNext()) {
            ((it1) it.next()).getClass();
        }
        Iterator it2 = nt1Var.a.iterator();
        while (it2.hasNext()) {
            ((it1) it2.next()).l = this.j;
        }
        if (this.k == null) {
            X(y13Var);
        }
    }

    public void P(boolean z) {
        y51 y51Var = this.q;
        if (y51Var == null) {
            return;
        }
        y51Var.C(z);
    }

    public final boolean Q(MotionEvent motionEvent) {
        EventFilter eventFilter = this.t;
        if (eventFilter.b) {
            motionEvent.offsetLocation(eventFilter.c, eventFilter.d);
        }
        eventFilter.c(motionEvent);
        PointF pointFE = E(motionEvent);
        if (pointFE == null) {
            return true;
        }
        EventFilter eventFilter2 = this.t;
        float f = pointFE.x;
        float f2 = pointFE.y;
        eventFilter2.c = f;
        eventFilter2.d = f2;
        return true;
    }

    public final void S() {
        y51 y51Var = this.q;
        g61 g61Var = this.f;
        if (y51Var != null) {
            float f = y51Var.e;
            float f2 = y51Var.f;
            RectF rectF = this.C;
            float f3 = rectF.top;
            g61Var.h(rectF);
            RectF rectF2 = this.B;
            g61Var.e(rectF2);
            y51 y51Var2 = this.q;
            int topControlsHeightPixels = g61Var.getTopControlsHeightPixels();
            int bottomControlsHeightPixels = g61Var.getBottomControlsHeightPixels();
            int i = g61Var.getWidth() > g61Var.getHeight() ? 2 : 1;
            y51Var2.getClass();
            float fWidth = rectF.width();
            float f4 = y51Var2.r;
            float f5 = fWidth / f4;
            float fHeight = rectF.height() / f4;
            float f6 = topControlsHeightPixels / f4;
            float f7 = bottomControlsHeightPixels / f4;
            boolean z = (Float.compare(y51Var2.e, f5) == 0 && Float.compare(y51Var2.f, fHeight) == 0 && Float.compare(y51Var2.g, f6) == 0 && Float.compare(y51Var2.h, f7) == 0 && y51Var2.j == i) ? false : true;
            y51Var2.e = f5;
            y51Var2.f = fHeight;
            y51Var2.g = f6;
            y51Var2.h = f7;
            y51Var2.j = i;
            if (z) {
                y51Var2.s();
            }
            float fWidth2 = rectF.width();
            float f8 = this.e;
            float f9 = fWidth2 * f8;
            float fHeight2 = rectF.height() * f8;
            if (f9 != f || fHeight2 != f2 || f3 != rectF2.top) {
                int i2 = 0;
                while (true) {
                    ArrayList arrayList = this.L;
                    if (i2 >= arrayList.size()) {
                        break;
                    }
                    ((je2) arrayList.get(i2)).M0(f9, fHeight2, rectF2.top, g61Var.getWidth() > g61Var.getHeight() ? 2 : 1);
                    i2++;
                }
            }
        }
        int i3 = 0;
        while (true) {
            SparseArray<m61> sparseArray = this.u;
            if (i3 >= sparseArray.size()) {
                return;
            }
            m61 m61VarValueAt = sparseArray.valueAt(i3);
            int width = g61Var.getWidth();
            int height = g61Var.getHeight();
            m61VarValueAt.m(m61.t, width * m61.d);
            m61VarValueAt.m(m61.u, height * m61.d);
            i3++;
        }
    }

    public final void T(Runnable runnable) {
        if (this.x && runnable == null) {
            return;
        }
        this.f.f(runnable);
        this.x = true;
    }

    public final void V(y51 y51Var, boolean z) {
        if (y51Var == null) {
            y51Var = this.i;
        }
        this.r = y51Var;
        this.s = z;
    }

    public void X(y13 y13Var) {
        this.k = y13Var;
        this.H.r(y13Var);
        this.m = new a(this.k);
        y51 y51Var = this.r;
        if (y51Var != null) {
            g0(y51Var, true);
        }
        b bVar = new b();
        this.l = bVar;
        y13Var.m(bVar);
        this.n = v();
        this.k.u().a(this.n);
    }

    public boolean Y(y51 y51Var) {
        return false;
    }

    public boolean Z(y51 y51Var, int i) {
        return false;
    }

    public boolean c0(boolean z) {
        return false;
    }

    @Override // defpackage.l61
    public final int d() {
        y51 y51Var = this.q;
        if (y51Var != null) {
            return y51Var.m();
        }
        return 0;
    }

    public void destroy() {
        this.G.a.clear();
        this.p.clear();
        ts2 ts2Var = this.i;
        if (ts2Var != null) {
            ts2Var.d();
        }
        nt1 nt1Var = this.y;
        if (nt1Var != null) {
            HashSet hashSet = nt1Var.a;
            Iterator it = hashSet.iterator();
            if (it.hasNext()) {
                ((it1) it.next()).I(0, false);
                throw null;
            }
            hashSet.clear();
            nt1Var.c = null;
            nt1Var.d.clear();
        }
        a aVar = this.m;
        if (aVar != null) {
            aVar.destroy();
        }
        b bVar = this.l;
        if (bVar != null) {
            this.k.l(bVar);
        }
        if (this.n != null) {
            this.k.u().e(this.n);
        }
    }

    public void e(int i) {
        Tab tabS;
        m61 m61Var;
        y13 y13Var = this.k;
        if (y13Var == null || this.q == null || (tabS = y13Var.s(i)) == null || (m61Var = this.u.get(i)) == null) {
            return;
        }
        boolean z = false;
        boolean z2 = (tabS.c() == null || hd2.z1(tabS) || (tabS.isNativePage() || tabS.getUrl().i().equals("chrome-native")) || tabS.isHidden()) ? false : true;
        xd3 xd3Var = this.N.get();
        xd3Var.getClass();
        int iE = xd3.e(tabS);
        if ((tabS.isFrozen() || tabS.f()) && !jk1.h(tabS.getUrl(), tabS.isIncognito())) {
            z = true;
        }
        int iF = xd3Var.f(tabS);
        int iV0 = n3.v0(this.z, tabS, xd3Var.c(tabS.j(), tabS));
        float fG = xd3Var.g(tabS);
        m61Var.n(m61.M, iE);
        m61Var.n(m61.N, iF);
        m61Var.n(m61.O, iV0);
        m61Var.m(m61.P, fG);
        m61Var.l(m61.A, z);
        m61Var.l(m61.B, z2);
        m61Var.l(m61.L, true);
        this.f.d();
    }

    public final void e0(int i, boolean z) {
        T(null);
        org.chromium.base.b<l61.a> bVar = this.o;
        if (z) {
            Iterator<ie2> it = this.p.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((ie2) aVar.next()).e(i);
                }
            }
            Iterator<l61.a> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                } else {
                    ((l61.a) aVar2.next()).e(i);
                }
            }
        }
        y51 y51Var = this.q;
        Iterator<l61.a> it3 = bVar.iterator();
        while (true) {
            b.a aVar3 = (b.a) it3;
            if (!aVar3.hasNext()) {
                return;
            } else {
                ((l61.a) aVar3.next()).i(y51Var.m(), Z(y51Var, i), Y(y51Var));
            }
        }
    }

    @Override // defpackage.l61
    public final void f(l61.a aVar) {
        this.o.c(aVar);
    }

    public void g0(y51 y51Var, boolean z) {
        if (y51Var == null) {
            return;
        }
        V(null, true);
        y51 y51Var2 = this.q;
        g61 g61Var = this.f;
        if (y51Var2 != y51Var) {
            if (y51Var2 != null) {
                y51Var2.h();
                y51Var2.e();
            }
            Context context = g61Var.getContext();
            y51Var.i = context;
            float f = m61.c;
            float f2 = context.getResources().getDisplayMetrics().density;
            m61.c = f2;
            m61.d = 1.0f / f2;
            y51Var.a(this.j);
            this.q = y51Var;
        }
        fj browserControlsManager = g61Var.getBrowserControlsManager();
        if (browserControlsManager != null) {
            this.A = true ^ (browserControlsManager.p() == 1.0f);
            browserControlsManager.f.u(this.v);
            this.q.getClass();
        }
        S();
        d dVar = new d();
        try {
            this.q.H(SystemClock.uptimeMillis(), z);
            y51 y51Var3 = this.q;
            y51Var3.getClass();
            g61Var.b(y51Var3 instanceof ts2, this.q.b());
            T(null);
            Iterator<ie2> it = this.p.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((ie2) aVar.next()).p1(this.q);
                }
            }
            Iterator<l61.a> it2 = this.o.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    dVar.close();
                    return;
                }
                ((l61.a) aVar2.next()).g(y51Var.m(), c0(z));
            }
        } catch (Throwable th) {
            try {
                dVar.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.O;
    }

    @Override // defpackage.z51
    public final void h(je2 je2Var) {
        ArrayList arrayList = this.L;
        if (arrayList.contains(je2Var)) {
            throw new uv("Overlay already added!");
        }
        HashMap map = this.M;
        if (!map.containsKey(je2Var.getClass())) {
            throw new uv("Please add overlay to order list in constructor.");
        }
        int iIntValue = ((Integer) map.get(je2Var.getClass())).intValue();
        int i = 0;
        while (i < arrayList.size() && iIntValue >= ((Integer) map.get(((je2) arrayList.get(i)).getClass())).intValue()) {
            i++;
        }
        arrayList.add(i, je2Var);
        je2Var.getHandleBackPressChangedSupplier().p(new xr(9, this));
    }

    public void h0(int i, Tab tab) {
        tab.getId();
        tab.isIncognito();
        if (this.q != null) {
            SystemClock.uptimeMillis();
        }
    }

    @Override // defpackage.l61
    public final void j(l61.a aVar) {
        this.o.a(aVar);
    }

    public void j0(int i, int i2, boolean z, boolean z2) {
        y51 y51Var = this.q;
        if (y51Var != null) {
            SystemClock.uptimeMillis();
            y51Var.v(i2);
        }
    }

    public void k() {
        if (this.r == null) {
            return;
        }
        Iterator<l61.a> it = this.o.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                g0(this.r, this.s);
                return;
            }
            ((l61.a) aVar.next()).f(this.q.m());
        }
    }

    public void k0(int i, int i2, int i3, boolean z, boolean z2, float f, float f2) {
        z7.G(this.k.g(z), i);
        y51 y51Var = this.q;
        SystemClock.uptimeMillis();
        y51Var.y(i, i2, z, !z2, f, f2);
    }

    public void l0(int i) {
        y51 y51Var = this.q;
        if (y51Var != null) {
            y51Var.z(i);
        }
    }

    public void n0(boolean z) {
        y51 y51Var = this.q;
        if (y51Var != null) {
            y51Var.A();
        }
    }

    @Override // defpackage.vb
    public final int o() {
        for (je2 je2Var : this.L) {
            Boolean bool = je2Var.getHandleBackPressChangedSupplier().get();
            if (bool != null && bool.booleanValue()) {
                return je2Var.o();
            }
        }
        return 1;
    }

    @Override // defpackage.l61
    public final boolean q(int i) {
        y51 y51Var = this.q;
        return y51Var != null && y51Var.m() == i;
    }

    @Override // defpackage.l61
    public final boolean r() {
        if (q(2)) {
            return this.q.p == 2;
        }
        return false;
    }

    @Override // defpackage.z51
    public final nw s(PropertyModel propertyModel, ke2 ke2Var, j.a aVar) {
        return new nw(propertyModel, ke2Var, aVar, this.f40J);
    }

    @Override // defpackage.z51
    public void t(int i, boolean z) {
        y51 y51Var = this.q;
        if (y51Var != null) {
            if (!(y51Var.p == 2)) {
                V(D(i), z);
                y51Var.I(-1, z);
                return;
            }
        }
        g0(D(i), z);
    }

    public c v() {
        return new c();
    }

    public void f0(ArrayList arrayList) {
    }

    public void l(int i) {
    }
}
