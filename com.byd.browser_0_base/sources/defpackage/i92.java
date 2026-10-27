package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.g50;
import defpackage.kz2;
import java.util.LinkedHashSet;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ReaderModeManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class i92 extends nh0 implements lk3 {
    public static final LinkedHashSet<Integer> u = new LinkedHashSet<>();
    public GURL e;
    public boolean f;
    public h92 g;
    public int h;
    public boolean i;
    public GURL j;
    public boolean k;
    public boolean l;
    public long m;
    public f92 n;
    public boolean o;
    public final Tab p;
    public final nw2<vg1> q;
    public boolean r;
    public boolean s;
    public PropertyModel t;

    public i92(TabImpl tabImpl, hs hsVar) {
        this.p = tabImpl;
        tabImpl.x(this);
        this.q = hsVar;
    }

    public static boolean A1() {
        if (!iv.e().g("enable-dom-distiller") || iv.e().g("disable-reader-mode-bottom-bar")) {
            return false;
        }
        return pb0.a() != 0;
    }

    public static void z1(GURL gurl) {
        LinkedHashSet<Integer> linkedHashSet = u;
        linkedHashSet.add(Integer.valueOf(gurl.e().hashCode()));
        while (linkedHashSet.size() > 100) {
            linkedHashSet.remove(Integer.valueOf(linkedHashSet.iterator().next().intValue()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void B1() {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.i92.B1():void");
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        if (tab == null) {
            return;
        }
        if (!this.k) {
            oj2.O("DomDistiller.ReaderShownForPageLoad", false);
        }
        if (this.l) {
            this.l = false;
            oj2.X(SystemClock.elapsedRealtime() - this.m, "DomDistiller.Time.ViewingReaderModePage");
        }
        kz2 kz2Var = (kz2) tab.B().b(kz2.class);
        kz2Var.e.c(this.n);
        h92 h92Var = this.g;
        if (h92Var != null) {
            h92Var.destroy();
        }
        this.h = 0;
        this.i = false;
        this.r = false;
        this.j = null;
        this.k = false;
        this.l = false;
        this.n = null;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        if (this.l) {
            this.l = false;
            oj2.X(SystemClock.elapsedRealtime() - this.m, "DomDistiller.Time.ViewingReaderModePage");
        }
    }

    @Override // defpackage.lk3
    public final void destroy() {
        h92 h92Var = this.g;
        if (h92Var != null) {
            h92Var.destroy();
        }
        this.o = true;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g0(int i, Tab tab, LoadUrlParams loadUrlParams) {
        WebContents webContentsC;
        Activity activityB = TabUtils.b(tab);
        int i2 = (activityB == null || activityB.getIntent().getExtras() == null) ? 0 : activityB.getIntent().getExtras().getInt("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE");
        if (tab == null || i2 != 3 || !a8.L(loadUrlParams.a) || (webContentsC = tab.c()) == null) {
            return;
        }
        g92 g92Var = new g92(activityB);
        Integer num = pb0.a;
        N.MEwGhN3r(g92Var, webContentsC);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [f92] */
    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, final Tab tab) {
        if (this.i) {
            return;
        }
        this.h = 1;
        this.j = tab.getUrl();
        if (this.n == null) {
            this.n = new kz2.a() { // from class: f92
                @Override // kz2.a
                public final void a(Tab tab2, boolean z, boolean z2, boolean z3) {
                    i92 i92Var = this.a;
                    i92Var.getClass();
                    if (tab2.getUrl().equals(i92Var.j)) {
                        if (!z || (z3 && pb0.b(tab))) {
                            i92Var.h = 1;
                        } else {
                            i92Var.h = 0;
                            i92Var.B1();
                        }
                        if (i92Var.f) {
                            return;
                        }
                        int i2 = i92Var.h;
                        if (i2 == 0 || z2) {
                            i92Var.f = true;
                            oj2.O("DomDistiller.PageDistillable", i2 == 0);
                        }
                    }
                }
            };
            kz2 kz2Var = (kz2) tab.B().b(kz2.class);
            kz2Var.e.a(this.n);
        }
        if (a8.M(tab.getUrl()) && !this.l) {
            this.l = true;
            this.m = SystemClock.elapsedRealtime();
        }
        if (this.g == null) {
            Tab tab2 = this.p;
            if (tab2.c() != null) {
                this.g = new h92(this, tab2.c());
            }
        }
        B1();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        if (!this.i || a8.M(tab.getUrl())) {
            this.h = 1;
            this.j = tab.getUrl();
            if (tab.c() != null) {
                this.g = new h92(this, this.p.c());
                if (a8.M(tab.getUrl())) {
                    this.h = 2;
                    this.e = tab.getUrl();
                }
            }
        }
    }

    public final void y1() {
        u.remove(Integer.valueOf(this.j.e().hashCode()));
        boolean zA = pb0.b.a();
        Tab tab = this.p;
        if (!zA || SysUtils.isLowEndDevice()) {
            WebContents webContentsC = tab.c();
            if (webContentsC == null) {
                return;
            }
            webContentsC.g();
            this.l = true;
            this.m = SystemClock.elapsedRealtime();
            fj fjVarT = hj.t(tab.D());
            qq0 qq0Var = fjVarT != null ? fjVarT.y : null;
            if (qq0Var != null) {
                qq0Var.b(tab);
            }
            if (hj.t(tab.D()) != null) {
                hj.t(tab.D()).f.x();
            }
            N.MAJeztUL(webContentsC);
            return;
        }
        Activity activityB = TabUtils.b(tab);
        WebContents webContentsC2 = tab.c();
        if (webContentsC2 == null) {
            return;
        }
        GURL gurlG = webContentsC2.g();
        this.l = true;
        this.m = SystemClock.elapsedRealtime();
        N.M2whIOZH(webContentsC2);
        String strJ = gurlG.j();
        String title = webContentsC2.getTitle();
        if (!TextUtils.isEmpty(strJ)) {
            strJ = N.MhGk9eKu("chrome-distiller", strJ, title);
        }
        g50.a aVar = new g50.a();
        aVar.d();
        aVar.b(z7.I(activityB) ? 2 : 1);
        g50 g50VarA = aVar.a();
        String name = CustomTabActivity.class.getName();
        Intent intent = g50VarA.a;
        intent.setClassName(activityB, name);
        intent.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 3);
        q11.a(intent);
        intent.putExtra("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", tab.getId());
        if (tab.isIncognito()) {
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
            intent.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_INCOGNITO_CCT_CALLER_ID", 3);
        }
        intent.setData(Uri.parse(strJ));
        Object obj = bz.a;
        activityB.startActivity(intent, null);
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
    }
}
