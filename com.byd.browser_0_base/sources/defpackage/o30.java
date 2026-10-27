package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: CustomTabDelegateFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class o30 implements jz2 {
    public final Activity a;
    public final boolean b;
    public final boolean c;
    public final int d;
    public final String e;
    public final int f;
    public final boolean g;
    public final lj h;
    public final zi0 i;
    public final il3 j;
    public final jp k;
    public final kj l;
    public final rq0 m;
    public final gz2 n;
    public final nw2<y13> o;
    public final nw2<CompositorViewHolder> p;
    public final nw2<bi1> q;
    public final p61<wp2> r;
    public final nw2<ck2> s;
    public b t;
    public cj0 u;
    public final p61<EphemeralTabCoordinator> v;

    /* JADX INFO: compiled from: CustomTabDelegateFactory.java */
    public static class a extends cj0 {
        public final String d;
        public final zi0 e;
        public final il3 f;

        public a(TabImpl tabImpl, zi0 zi0Var, il3 il3Var) {
            super(tabImpl);
            this.d = jy2.d(tabImpl).f;
            this.e = zi0Var;
            this.f = il3Var;
        }

        @Override // defpackage.cj0, defpackage.aj0
        public final boolean a(GURL gurl) {
            il3 il3Var = this.f;
            return il3Var != null && il3Var.b(gurl.j());
        }

        @Override // defpackage.cj0, defpackage.aj0
        public final void b(Intent intent) {
            intent.setPackage(this.d);
        }

        @Override // defpackage.cj0, defpackage.aj0
        public final boolean c(GURL gurl) {
            return bk3.e(gurl);
        }

        @Override // defpackage.cj0, defpackage.aj0
        public final void d() {
            if (TextUtils.isEmpty(this.d)) {
                return;
            }
            this.e.b();
        }
    }

    /* JADX INFO: compiled from: CustomTabDelegateFactory.java */
    public static class b extends f3 {
        public final Activity l;
        public final int m;
        public final String n;
        public final int o;
        public final boolean p;

        public b(Tab tab, Activity activity, int i, String str, int i2, boolean z, jp jpVar, kj kjVar, rq0 rq0Var, gz2 gz2Var, nw2 nw2Var, nw2 nw2Var2, nw2 nw2Var3) {
            super(tab, activity, jpVar, true, kjVar, rq0Var, gz2Var, nw2Var, nw2Var2, nw2Var3);
            this.l = activity;
            this.m = i;
            this.n = str;
            this.o = i2;
            this.p = z;
        }

        @Override // defpackage.f3, org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
        public final int a() {
            return this.o;
        }

        @Override // defpackage.f3
        public final void b() {
            Activity activity = this.l;
            ((ActivityManager) activity.getSystemService("activity")).moveTaskToFront(activity.getTaskId(), 0);
        }

        @Override // defpackage.k53
        public final boolean canShowAppBanners() {
            return this.m == 1;
        }

        @Override // defpackage.k53
        public final String getManifestScope() {
            return this.n;
        }

        @Override // defpackage.k53
        public final boolean isInstalledWebappDelegateGeolocation() {
            Activity activity = this.l;
            if (!(activity instanceof CustomTabActivity)) {
                return false;
            }
            xf3 xf3Var = ((CustomTabActivity) activity).l1;
            if (xf3Var == null ? false : xf3Var.a.l) {
                return org.chromium.chrome.browser.browserservices.permissiondelegation.a.c(xf3Var == null ? null : xf3Var.d.e) != null;
            }
            return false;
        }

        @Override // defpackage.k53
        public final boolean shouldEnableEmbeddedMediaExperience() {
            return this.p;
        }

        @Override // defpackage.f3, defpackage.k53
        public final boolean shouldResumeRequestsForCreatedWindow() {
            return true;
        }
    }

    public o30() {
        throw null;
    }

    public o30(Activity activity, boolean z, boolean z2, String str, int i, boolean z3, g30 g30Var, zi0 zi0Var, vj1 vj1Var, il3 il3Var, p61 p61Var, jp jpVar, kj kjVar, rq0 rq0Var, gz2 gz2Var, nw2 nw2Var, nw2 nw2Var2, nw2 nw2Var3, p61 p61Var2, nw2 nw2Var4, int i2) {
        this.a = activity;
        this.b = z;
        this.c = z2;
        this.e = str;
        this.f = i;
        this.g = z3;
        this.h = g30Var;
        this.i = zi0Var;
        this.j = il3Var;
        this.v = p61Var;
        this.k = jpVar;
        this.l = kjVar;
        this.m = rq0Var;
        this.n = gz2Var;
        this.o = nw2Var;
        this.p = nw2Var2;
        this.q = nw2Var3;
        this.r = p61Var2;
        this.s = nw2Var4;
        this.d = i2;
    }

    @Override // defpackage.jz2
    public final jk1 a(String str, jk1 jk1Var, Tab tab) {
        if (!"chrome-native://newtab/".equals(str) || !tab.m()) {
            return null;
        }
        this.a.finish();
        return null;
    }

    @Override // defpackage.jz2
    public final ContextMenuPopulatorFactory b(Tab tab) {
        nw2 se1Var;
        int i = 3;
        int i2 = this.d;
        int i3 = i2 == 3 || i2 == 4 ? 2 : 1;
        y13 y13Var = this.o.get();
        tab.isIncognito();
        if (true ^ SysUtils.isLowEndDevice()) {
            p61<EphemeralTabCoordinator> p61Var = this.v;
            Objects.requireNonNull(p61Var);
            se1Var = new p11(i, p61Var);
        } else {
            se1Var = new se1(i);
        }
        return new f31(new az2(tab, y13Var, se1Var, new qe3(5), new yq2(6, this)), this.s, i3, zi0.b);
    }

    @Override // defpackage.jz2
    public final lj c(TabImpl tabImpl) {
        n30 n30Var = new n30(this, tabImpl);
        lj ljVar = this.h;
        return ljVar == null ? n30Var : new bw(n30Var, ljVar);
    }

    @Override // defpackage.jz2
    public final hj0 d(TabImpl tabImpl) {
        if (this.c) {
            this.u = new cj0(tabImpl);
        } else {
            this.u = new a(tabImpl, this.i, this.j);
        }
        return new hj0(this.u);
    }

    @Override // defpackage.jz2
    public final k53 e(Tab tab) {
        b bVar = new b(tab, this.a, this.d, this.e, this.f, this.g, this.k, this.l, this.m, this.n, this.o, this.p, this.q);
        this.t = bVar;
        return bVar;
    }
}
