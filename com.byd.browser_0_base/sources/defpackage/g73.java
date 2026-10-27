package defpackage;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import org.chromium.base.library_loader.a;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.site_settings.CookieControlsServiceBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.p1;
import org.chromium.chrome.features.tasks.TasksView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TasksSurfaceCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g73 implements f73 {
    public final n1 a;
    public final TasksView b;
    public final m73 c;
    public final int d;
    public final wp2 e;
    public final nw2<df0> f;
    public final Activity g;
    public final nw2<Tab> h;
    public final si1 i;
    public qi1 j;
    public ca3 k;
    public boolean l;

    /* JADX WARN: Removed duplicated region for block: B:24:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public g73(android.app.Activity r19, defpackage.ef2 r20, org.chromium.ui.modelutil.PropertyModel r21, int r22, defpackage.nw2<org.chromium.chrome.browser.tab.Tab> r23, boolean r24, boolean r25, org.chromium.ui.base.WindowAndroid r26, defpackage.q2 r27, defpackage.y13 r28, defpackage.wp2 r29, defpackage.nw2<defpackage.df0> r30, org.chromium.chrome.browser.compositor.layouts.content.TabContentManager r31, defpackage.bi1 r32, defpackage.kj r33, defpackage.gz2 r34, defpackage.jf1 r35, defpackage.tj1 r36, android.view.ViewGroup r37, defpackage.qs1<defpackage.hy0> r38) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g73.<init>(android.app.Activity, ef2, org.chromium.ui.modelutil.PropertyModel, int, nw2, boolean, boolean, org.chromium.ui.base.WindowAndroid, q2, y13, wp2, nw2, org.chromium.chrome.browser.compositor.layouts.content.TabContentManager, bi1, kj, gz2, jf1, tj1, android.view.ViewGroup, qs1):void");
    }

    @Override // defpackage.f73
    public final void a() {
        si1 si1Var;
        if (!a.l.d() || this.l || (si1Var = this.i) == null) {
            return;
        }
        Profile profileD = Profile.d();
        ri1 ri1Var = new ri1(this.g, profileD, this.h);
        this.j = new qi1(this.b, ri1Var, profileD);
        ca3 ca3Var = new ca3(this.g, profileD, ri1Var, this.e, 2);
        this.k = ca3Var;
        si1Var.b(this.j, ca3Var, new C0173a());
        this.l = true;
    }

    @Override // defpackage.f73
    public void addHeaderOffsetChangeListener(AppBarLayout.f fVar) {
        this.b.addHeaderOffsetChangeListener(fVar);
    }

    public final n1.a b() {
        n1 n1Var = this.a;
        if (n1Var != null) {
            return n1Var.e();
        }
        return null;
    }

    public final nw2<Boolean> c() {
        int i = this.d;
        if (i == 1 || i == 0) {
            return this.a.k();
        }
        return null;
    }

    public final n1.c d() {
        n1 n1Var = this.a;
        if (n1Var != null) {
            return n1Var.s();
        }
        return null;
    }

    public final p1 e() {
        n1 n1Var = this.a;
        if (n1Var != null) {
            return n1Var.m();
        }
        return null;
    }

    public final void f() {
        org.chromium.chrome.browser.ntp.a aVar = this.c.f;
        if (aVar.g) {
            return;
        }
        aVar.e = new CookieControlsServiceBridge(aVar);
        aVar.g = true;
    }

    public final void g(Profile profile) {
        if (!r82.d()) {
            SharedPreferencesManager.getInstance().l("Chrome.QueryTiles.ShownOnStartSurface", false);
        } else {
            new q82((ViewGroup) this.b.findViewById(R$id.query_tiles_layout), profile, new x10(22, this));
            SharedPreferencesManager.getInstance().l("Chrome.QueryTiles.ShownOnStartSurface", true);
        }
    }

    public final void h(sr1 sr1Var) {
        n1 n1Var = this.a;
        if (n1Var != null) {
            n1Var.d();
        }
        m73 m73Var = this.c;
        m73Var.e = sr1Var;
        PropertyModel.r<View.OnClickListener> rVar = n73.p;
        h73 h73Var = new h73(m73Var);
        PropertyModel propertyModel = m73Var.g;
        propertyModel.p(rVar, h73Var);
        propertyModel.p(n73.q, new i73(m73Var));
        propertyModel.p(n73.v, new j73(m73Var));
        propertyModel.p(n73.r, new k73(m73Var));
        l73 l73Var = new l73(m73Var);
        m73Var.getClass();
        org.chromium.chrome.browser.ntp.a aVar = m73Var.f;
        aVar.f.a(l73Var);
        propertyModel.p(n73.l, aVar);
        propertyModel.p(n73.j, aVar);
    }

    public final void i() {
        qi1 qi1Var = this.j;
        if (qi1Var != null) {
            qi1Var.a();
            this.j = null;
        }
        ca3 ca3Var = this.k;
        if (ca3Var != null) {
            ca3Var.c();
            this.k = null;
        }
        si1 si1Var = this.i;
        if (si1Var != null) {
            si1Var.a();
            this.l = false;
        }
        this.a.s().p();
    }

    @Override // defpackage.f73
    public void removeHeaderOffsetChangeListener(AppBarLayout.f fVar) {
        this.b.removeHeaderOffsetChangeListener(fVar);
    }

    @Override // defpackage.f73
    public void setOnTabSelectingListener(n1.b bVar) {
        n1 n1Var = this.a;
        if (n1Var != null) {
            n1Var.setOnTabSelectingListener(bVar);
        }
    }
}
