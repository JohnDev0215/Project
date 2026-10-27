package defpackage;

import defpackage.lh0;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: IncognitoTabModelImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fz0 implements ez0 {
    public final a e;
    public final b<t13> f = new b<>();
    public final b<hz0> g = new b<>();
    public TabModel h = lh0.a.a;
    public int i;
    public boolean j;

    /* JADX INFO: compiled from: IncognitoTabModelImpl.java */
    public interface a {
    }

    public fz0(gz0 gz0Var) {
        this.e = gz0Var;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean a(Tab tab) {
        this.i++;
        boolean zA = this.h.a(tab);
        this.i--;
        t();
        return zA;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void b() {
        this.i++;
        this.h.b();
        this.i--;
        t();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void c() {
        if (k().getCount() == 0) {
            return;
        }
        this.h.c();
        t();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void destroy() {
        this.h.destroy();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void e(boolean z) {
        this.i++;
        this.h.e(z);
        this.i--;
        t();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final Profile f() {
        return this.h.f();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final Tab g(int i, boolean z) {
        return this.h.g(i, z);
    }

    @Override // defpackage.w03
    public final int getCount() {
        return this.h.getCount();
    }

    @Override // defpackage.w03
    public final Tab getTabAt(int i) {
        return this.h.getTabAt(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean h(Tab tab, Tab tab2, boolean z, boolean z2) {
        this.i++;
        boolean zH = this.h.h(tab, tab2, z, z2);
        this.i--;
        t();
        return zH;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void i(Tab tab, int i, int i2, int i3) {
        this.i++;
        y();
        boolean z = getCount() == 0;
        this.h.i(tab, i, i2, i3);
        if (z) {
            Iterator<hz0> it = this.g.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((hz0) aVar.next()).f();
                }
            }
        }
        this.i--;
    }

    @Override // defpackage.w03
    public final int index() {
        return this.h.index();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean isActiveModel() {
        return this.j;
    }

    @Override // defpackage.w03
    public final boolean isIncognito() {
        return true;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void j(t13 t13Var) {
        this.f.a(t13Var);
        this.h.j(t13Var);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final w03 k() {
        return this.h.k();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void l(t13 t13Var) {
        this.f.c(t13Var);
        this.h.l(t13Var);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean m(Tab tab, boolean z, boolean z2) {
        this.i++;
        boolean zM = this.h.m(tab, z, z2);
        this.i--;
        t();
        return zM;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void n(int i) {
        this.h.n(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void o(Tab tab) {
        this.i++;
        this.h.o(tab);
        this.i--;
        t();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void p(List list) {
        this.i++;
        this.h.p(list);
        this.i--;
        t();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void q(int i, int i2, boolean z) {
        this.h.q(i, i2, z);
    }

    @Override // defpackage.w03
    public final int r(Tab tab) {
        return this.h.r(tab);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void s(int i, int i2) {
        this.h.s(i, i2);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void setActive(boolean z) {
        this.j = z;
        if (z) {
            y();
        }
        this.h.setActive(z);
        if (z) {
            return;
        }
        t();
    }

    public final void t() {
        Object obj = ThreadUtils.a;
        if (!(k().getCount() == 0) || (this.h instanceof lh0) || this.i != 0) {
            return;
        }
        Iterator<hz0> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.h.destroy();
                this.h = lh0.a.a;
                return;
            }
            ((hz0) aVar.next()).q();
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean u() {
        return this.h.u();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void v() {
        this.h.v();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean w(int i) {
        return this.h.w(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void x(int i) {
        this.h.x(i);
        t();
    }

    public final void y() {
        Object obj = ThreadUtils.a;
        if (!(this.h instanceof lh0)) {
            return;
        }
        gz0 gz0Var = (gz0) this.e;
        int i = gz0Var.i;
        this.h = new r13(i == 0 ? Profile.d().f(true) : i == 1 ? n3.h0(gz0Var.h.get()) : null, gz0Var.i, gz0Var.a, gz0Var.b, gz0Var.c, gz0Var.d, gz0Var.e, gz0Var.f, gz0Var.g, false);
        Iterator<t13> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                this.h.j((t13) aVar.next());
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void d() {
    }
}
