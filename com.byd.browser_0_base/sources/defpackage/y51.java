package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.util.AbstractList;
import org.chromium.chrome.browser.accessibility_tab_switcher.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: Layout.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class y51 {
    public Context i;
    public y13 k;
    public TabContentManager l;
    public final n61 m;
    public final j61 n;
    public m61[] o;
    public int p;
    public final float r;
    public final float s;
    public int q = -1;
    public float e = -1.0f;
    public float f = -1.0f;
    public float g = -1.0f;
    public float h = -1.0f;
    public int j = 0;

    public y51(Context context, n61 n61Var, j61 j61Var) {
        this.i = context;
        this.m = n61Var;
        this.n = j61Var;
        float f = context.getResources().getDisplayMetrics().density;
        this.r = f;
        this.s = 1.0f / f;
        this.p = 3;
    }

    public boolean D() {
        return true;
    }

    public final void E() {
        ((h61) this.m).T(null);
    }

    public void F(TabContentManager tabContentManager) {
        if (tabContentManager == null) {
            return;
        }
        this.l = tabContentManager;
    }

    public void G(y13 y13Var, TabContentManager tabContentManager) {
        this.k = y13Var;
        F(tabContentManager);
    }

    public void H(long j, boolean z) {
        this.p = 0;
        this.q = -1;
    }

    public void I(int i, boolean z) {
        ((h61) this.m).e0(i, z);
        this.p = 2;
        this.q = i;
    }

    public final void K(AbstractList abstractList) {
        TabContentManager tabContentManager = this.l;
        if (tabContentManager != null) {
            tabContentManager.g(-1, abstractList);
        }
    }

    public final float N(float f, float f2, float f3) {
        float fAbs = Math.abs(f2 - f3);
        float f4 = this.s;
        if (fAbs > f4) {
            return f3;
        }
        float fRound = Math.round(this.r * f3) * f4;
        return fRound < f3 ? Math.max(fRound, f2 - f) : Math.min(fRound, f2 + f);
    }

    public final boolean O(PropertyModel propertyModel) {
        float f = (16 * 1.0f) / 1000.0f;
        PropertyModel.o oVar = m61.j;
        float fG = propertyModel.g(oVar);
        PropertyModel.o oVar2 = m61.k;
        float fG2 = propertyModel.g(oVar2);
        float fN = N(f, fG, propertyModel.g(m61.h));
        float fN2 = N(f, fG2, propertyModel.g(m61.i));
        boolean z = (fN == fG && fN2 == fG2) ? false : true;
        propertyModel.m(oVar, fN);
        propertyModel.m(oVar2, fN2);
        return z;
    }

    public boolean b() {
        return true;
    }

    public final m61 c(int i, boolean z) {
        n61 n61Var = this.m;
        h61 h61Var = (h61) n61Var;
        SparseArray<m61> sparseArray = h61Var.u;
        m61 m61Var = sparseArray.get(i);
        g61 g61Var = h61Var.f;
        if (m61Var == null) {
            m61Var = new m61(i, g61Var.getWidth(), g61Var.getHeight(), z);
            sparseArray.put(i, m61Var);
        } else {
            m61Var.u(g61Var.getWidth(), g61Var.getHeight());
        }
        if (!m61Var.k(m61.L)) {
            n61Var.e(m61Var.q());
        }
        return m61Var;
    }

    public void f() {
        if (this.p != 2) {
            return;
        }
        this.p = 3;
        int i = this.q;
        if (i != -1) {
            TabModel tabModelJ = this.k.j(i);
            if (tabModelJ != null) {
                tabModelJ.q(z7.G(tabModelJ, this.q), 3, false);
            }
            this.q = -1;
        }
        this.m.k();
        j61 j61Var = this.n;
        if (j61Var != null && j61Var.getResourceManager() != null) {
            sk skVar = bq.a;
            if (!N.M09VlOh_("KeepAndroidTintedResources")) {
                ResourceManager resourceManager = j61Var.getResourceManager();
                long j = resourceManager.d;
                if (j != 0) {
                    N.MnAVuP1v(j, resourceManager);
                }
            }
        }
        if (n() != null) {
            SceneLayer sceneLayerN = n();
            long j2 = sceneLayerN.e;
            if (j2 == 0) {
                return;
            }
            N.MJ5Z0mi3(j2, sceneLayerN);
        }
    }

    public void g() {
        if (this.p != 0) {
            return;
        }
        this.p = 1;
        ((h61) this.m).C();
    }

    public boolean i() {
        return false;
    }

    public final ew j() {
        return ((h61) this.m).G;
    }

    public abstract EventFilter k();

    public m61 l(int i) {
        if (this.o == null) {
            return null;
        }
        int i2 = 0;
        while (true) {
            m61[] m61VarArr = this.o;
            if (i2 >= m61VarArr.length) {
                return null;
            }
            if (m61VarArr[i2].q() == i) {
                return this.o[i2];
            }
            i2++;
        }
    }

    public abstract int m();

    public abstract SceneLayer n();

    public int o() {
        return 1;
    }

    public boolean p() {
        return this instanceof b;
    }

    public boolean q() {
        m61[] m61VarArr = this.o;
        if (m61VarArr == null) {
            return false;
        }
        int length = m61VarArr.length;
        return false;
    }

    public boolean t() {
        return false;
    }

    public void A() {
    }

    public void J() {
    }

    public void L() {
    }

    public void d() {
    }

    public void e() {
    }

    public void h() {
    }

    public void s() {
    }

    public void u() {
    }

    public void x() {
    }

    public void B(long j) {
    }

    public void C(boolean z) {
    }

    public void a(ViewGroup viewGroup) {
    }

    public void v(int i) {
    }

    public void w(int i) {
    }

    public void z(int i) {
    }

    public void M(RectF rectF, TabContentManager tabContentManager, ResourceManager resourceManager, fj fjVar) {
    }

    public void y(int i, int i2, boolean z, boolean z2, float f, float f2) {
    }
}
