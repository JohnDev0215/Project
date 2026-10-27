package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ao3;
import defpackage.ca;
import defpackage.ca3;
import defpackage.cq2;
import defpackage.em1;
import defpackage.fj;
import defpackage.fm1;
import defpackage.gm1;
import defpackage.hm1;
import defpackage.hu0;
import defpackage.hw2;
import defpackage.i31;
import defpackage.ii2;
import defpackage.il0;
import defpackage.im1;
import defpackage.ji;
import defpackage.jk1;
import defpackage.jm1;
import defpackage.kj;
import defpackage.lk1;
import defpackage.lw2;
import defpackage.m2;
import defpackage.ml0;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.p21;
import defpackage.q2;
import defpackage.qb3;
import defpackage.sm1;
import defpackage.so3;
import defpackage.sr1;
import defpackage.tm1;
import defpackage.tz;
import defpackage.um1;
import defpackage.v42;
import defpackage.vk0;
import defpackage.vm1;
import defpackage.w93;
import defpackage.wp2;
import defpackage.xe3;
import defpackage.y13;
import defpackage.yp;
import defpackage.zk0;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: NewTabPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements jk1, p21, TemplateUrlService.c, kj.a, ml0, so3.b {
    public final long A;
    public long B;
    public boolean C;
    public boolean D;
    public final int E;
    public final nw2<qb3> F;
    public final y13 G;
    public final TemplateUrlService H;
    public org.chromium.chrome.browser.search_resumption.a I;
    public final Tab e;
    public final nw2<Tab> f;
    public final q2 g;
    public final String h;
    public final i31 i;
    public final Activity j;
    public final int k;
    public final b l;
    public final c m;
    public final boolean n;
    public final kj o;
    public final tz p;
    public final org.chromium.base.b<a> q;
    public final ji r;
    public final SettingsLauncher s;
    public org.chromium.chrome.browser.feed.c t;
    public NewTabPageLayout u;
    public fm1 v;
    public gm1 w;
    public boolean x;
    public sr1 y;
    public so3 z;

    /* JADX INFO: compiled from: NewTabPage.java */
    public interface a {
        void a(w93 w93Var, Tab tab);
    }

    /* JADX INFO: compiled from: NewTabPage.java */
    public class b extends lw2 implements sm1 {
        public final xe3 e;

        public b(hw2 hw2Var, Profile profile, lk1.b bVar) {
            super(hw2Var, profile, bVar);
            this.e = oj2.t(profile);
        }

        public final void b(String str, boolean z) {
            h hVar = h.this;
            if (hVar.D) {
                return;
            }
            hVar.t.getClass();
            so3 so3Var = hVar.z;
            if (so3Var != null && z) {
                so3Var.f(1);
                this.e.notifyEvent("ntp_voice_search_button_clicked");
            } else {
                sr1 sr1Var = hVar.y;
                if (sr1Var != null) {
                    sr1Var.s(str, str == null ? 2 : 3, true);
                }
            }
        }
    }

    /* JADX INFO: compiled from: NewTabPage.java */
    public class c extends ca3 {
        public c(Context context, Profile profile, hw2 hw2Var, wp2 wp2Var) {
            super(context, profile, hw2Var, wp2Var, 1);
        }

        @Override // defpackage.ca3, aa3.b
        public final void a(int i, w93 w93Var) {
            h hVar = h.this;
            if (hVar.D) {
                return;
            }
            super.a(i, w93Var);
            Iterator<a> it = hVar.q.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((a) aVar.next()).a(w93Var, hVar.e);
                }
            }
            if (i != 6) {
                oj2.Y((System.nanoTime() - hVar.B) / 1000000, "NewTabPage.MostVisitedTime");
            }
        }

        @Override // defpackage.ca3, aa3.b
        public final void b(List<w93> list) {
            h hVar = h.this;
            if (hVar.D) {
                return;
            }
            super.b(list);
            NewTabPageLayout newTabPageLayout = hVar.u;
            if (newTabPageLayout.q) {
                return;
            }
            newTabPageLayout.q = true;
            newTabPageLayout.h();
        }
    }

    /* JADX INFO: compiled from: NewTabPage.java */
    public interface d {
    }

    public h(Activity activity, fj fjVar, nw2 nw2Var, wp2 wp2Var, q2 q2Var, y13 y13Var, boolean z, um1 um1Var, boolean z2, lk1.b bVar, Tab tab, String str, ji jiVar, nw2 nw2Var2, WindowAndroid windowAndroid, i31 i31Var, nw2 nw2Var3, n3 n3Var) {
        Profile profile;
        WindowAndroid windowAndroid2;
        int i;
        long jNanoTime = System.nanoTime();
        this.A = jNanoTime;
        TraceEvent.q("NewTabPage", null);
        this.f = nw2Var;
        this.g = q2Var;
        this.e = tab;
        this.i = i31Var;
        this.F = nw2Var3;
        this.q = new org.chromium.base.b<>();
        this.o = fjVar;
        this.G = y13Var;
        this.r = jiVar;
        this.s = n3Var;
        Profile profileB = Profile.b(tab.c());
        hw2 hw2Var = new hw2(activity, profileB, bVar, y13Var, tab);
        b bVar2 = new b(hw2Var, profileB, bVar);
        this.l = bVar2;
        this.m = new c(activity, profileB, hw2Var, wp2Var);
        this.j = activity;
        this.h = activity.getResources().getString(R$string.new_tab_title);
        this.k = ii2.a(activity);
        this.n = z;
        TemplateUrlService templateUrlServiceQ = oj2.q(profileB);
        this.H = templateUrlServiceQ;
        templateUrlServiceQ.a(this);
        fm1 fm1Var = new fm1(this);
        this.v = fm1Var;
        tab.x(fm1Var);
        gm1 gm1Var = new gm1(this);
        this.w = gm1Var;
        q2Var.f(gm1Var);
        this.x = templateUrlServiceQ.b();
        Profile profileB2 = Profile.b(tab.c());
        this.u = (NewTabPageLayout) LayoutInflater.from(activity).inflate(R$layout.new_tab_page_layout, (ViewGroup) null);
        BookmarkModel.v(profileB2);
        hw2 hw2Var2 = bVar2.b;
        org.chromium.chrome.browser.feed.c cVar = new org.chromium.chrome.browser.feed.c(activity, wp2Var, windowAndroid, new cq2(bVar2, this.u), this.u, fjVar.l, z2, this, profileB2, false, jiVar, nw2Var2, null, vm1.a(str), v42.g(), nw2Var3, 1, jNanoTime, FeedSwipeRefreshLayout.j(activity, R$id.toolbar_container), false, null, new jm1(hw2Var2), hu0.a());
        this.t = cVar;
        um1Var.getClass();
        c.a aVar = cVar.q;
        aVar.getViewTreeObserver().addOnPreDrawListener(new tm1(jNanoTime, aVar));
        if (y13Var.t()) {
            profile = profileB;
            v(profile, ca.a(windowAndroid));
            windowAndroid2 = windowAndroid;
        } else {
            profile = profileB;
            windowAndroid2 = windowAndroid;
            y13Var.m(new hm1(this, profile, windowAndroid2));
        }
        this.t.q.addOnAttachStateChangeListener(new im1(this));
        fjVar.o(this);
        DownloadManagerService downloadManagerServiceG = DownloadManagerService.g();
        N.MQ35Y$D$(downloadManagerServiceG.k(), downloadManagerServiceG, ProfileKey.a());
        this.E = activity.getResources().getDimensionPixelSize(R$dimen.tab_strip_and_toolbar_height);
        oj2.O("NewTabPage.MobileIsUserOnline", NetworkChangeNotifier.a());
        if (oj2.k(profile).a("ntp_snippets.enable")) {
            boolean z3 = zk0.b;
            i = !N.MpwNAobK() ? 3 : !oj2.k(profile).a("ntp_snippets.list_visible") ? 1 : 0;
        } else {
            i = 2;
        }
        oj2.U(i, 4, "ContentSuggestions.Feed.DisplayStatusOnOpen");
        tz tzVar = new tz(hw2Var2, this.t.j, new m2(activity, 1));
        this.p = tzVar;
        windowAndroid2.addContextMenuCloseListener(tzVar);
        NewTabPageLayout newTabPageLayout = this.u;
        boolean z4 = this.x;
        org.chromium.chrome.browser.feed.c cVar2 = this.t;
        newTabPageLayout.c(bVar2, activity, z4, cVar2.j, cVar2.p, q2Var, um1Var, tab.isIncognito(), windowAndroid, wp2Var);
        TraceEvent.C("NewTabPage");
    }

    public static void s(h hVar) {
        hVar.getClass();
        hVar.B = System.nanoTime();
        n3.X0("MobileNTPShown");
        hVar.i.a(3);
        int i = 0;
        if (!SharedPreferencesManager.getInstance().readBoolean("content_suggestions_shown", false)) {
            n3.X0("Suggestions.FirstTimeSurfaceVisible");
            SharedPreferencesManager.getInstance().l("content_suggestions_shown", true);
        }
        n3.X0("Suggestions.SurfaceVisible");
        NewTabPageLayout newTabPageLayout = hVar.u;
        Objects.requireNonNull(newTabPageLayout);
        vk0.b(5, new em1(newTabPageLayout, i));
    }

    @Override // so3.b
    public final void E() {
        this.u.l();
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        TemplateUrlService templateUrlService = this.H;
        boolean zB = templateUrlService.b();
        this.x = zB;
        this.u.j(zB, templateUrlService.e());
        x();
    }

    @Override // defpackage.jk1
    public final void a() {
        this.t.c();
    }

    @Override // defpackage.ml0
    public final boolean b(MotionEvent motionEvent) {
        sr1 sr1Var;
        Tab tab = this.e;
        return (tab == null || !DeviceFormFactor.b(tab.D())) && (sr1Var = this.y) != null && sr1Var.O();
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        x();
    }

    @Override // defpackage.jk1
    public final int d(int i) {
        return u() ? this.k : i;
    }

    @Override // defpackage.jk1
    public final void destroy() {
        boolean z = this.C;
        Tab tab = this.e;
        if (z && !tab.isHidden()) {
            w();
        }
        this.l.a();
        this.m.c();
        this.H.i(this);
        tab.t(this.v);
        this.v = null;
        this.g.e(this.w);
        this.w = null;
        this.o.s(this);
        this.t.getClass();
        this.t.f();
        tab.D().removeContextMenuCloseListener(this.p);
        so3 so3Var = this.z;
        if (so3Var != null) {
            so3Var.f.c(this);
        }
        org.chromium.chrome.browser.search_resumption.a aVar = this.I;
        if (aVar != null) {
            aVar.a.b();
            aVar.b.a = null;
        }
        this.D = true;
    }

    @Override // defpackage.jk1
    public final float f(float f) {
        if (u()) {
            return 0.0f;
        }
        return f;
    }

    @Override // defpackage.jk1
    public final int g(int i) {
        if (!u()) {
            return i;
        }
        boolean z = this.u.getToolbarTransitionPercentage() == 1.0f;
        Activity activity = this.j;
        return z ? yp.c(activity, R$dimen.toolbar_text_box_elevation) : yp.b(activity, false);
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.h;
    }

    @Override // defpackage.jk1
    public final String getUrl() {
        return "chrome-native://newtab/";
    }

    @Override // defpackage.jk1
    public final View getView() {
        return this.t.q;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    @Override // defpackage.p21
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean i() {
        /*
            r5 = this;
            org.chromium.chrome.browser.ntp.NewTabPageLayout r0 = r5.u
            boolean r0 = r0.z
            r1 = 1
            if (r0 != 0) goto L36
            org.chromium.chrome.browser.feed.c r5 = r5.t
            org.chromium.chrome.browser.feed.e r5 = r5.j
            boolean r0 = r5.s
            r2 = 0
            if (r0 != 0) goto L31
            org.chromium.chrome.browser.feed.c r0 = r5.e
            org.chromium.chrome.browser.feed.c$a r3 = r0.q
            int r3 = r3.getWidth()
            int r4 = r5.u
            if (r3 != r4) goto L31
            org.chromium.chrome.browser.feed.c$a r0 = r0.q
            int r0 = r0.getHeight()
            int r3 = r5.v
            if (r0 != r3) goto L31
            int r0 = r5.f()
            int r5 = r5.w
            if (r0 == r5) goto L2f
            goto L31
        L2f:
            r5 = r2
            goto L32
        L31:
            r5 = r1
        L32:
            if (r5 == 0) goto L35
            goto L36
        L35:
            r1 = r2
        L36:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ntp.h.i():boolean");
    }

    @Override // defpackage.jk1
    public final String k() {
        return "newtab";
    }

    @Override // kj.a
    public final void l(int i) {
        x();
    }

    @Override // defpackage.p21
    public final void m(Canvas canvas) {
        NewTabPageLayout newTabPageLayout = this.u;
        newTabPageLayout.h.b.p(org.chromium.chrome.browser.logo.d.d, Boolean.TRUE);
        newTabPageLayout.z = false;
        org.chromium.chrome.browser.feed.c cVar = this.t;
        c.a aVar = cVar.q;
        ao3.d(aVar);
        aVar.draw(canvas);
        org.chromium.chrome.browser.feed.e eVar = cVar.j;
        org.chromium.chrome.browser.feed.c cVar2 = eVar.e;
        eVar.u = cVar2.q.getWidth();
        eVar.v = cVar2.q.getHeight();
        eVar.w = eVar.f();
        eVar.s = false;
    }

    @Override // defpackage.jk1
    public final void o() {
        this.t.getClass();
    }

    @Override // defpackage.jk1
    public final int r() {
        return this.k;
    }

    public void setSearchBoxScrollListener(d dVar) {
        this.u.setSearchBoxScrollListener(dVar);
    }

    public final int t(boolean z) {
        if (il0.b() && this.x) {
            return 0;
        }
        return z ? this.u.getResources().getDimensionPixelSize(R$dimen.ntp_logo_margin_top) : this.u.getResources().getDimensionPixelSize(R$dimen.ntp_logo_margin_bottom);
    }

    public final boolean u() {
        h hVar = h.this;
        if (hVar.D) {
            return false;
        }
        return (!hVar.n && hVar.x) && !hVar.u.x;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(org.chromium.chrome.browser.profiles.Profile r13, defpackage.ca r14) {
        /*
            r12 = this;
            boolean r0 = r12.n
            if (r0 == 0) goto L5
            return
        L5:
            org.chromium.chrome.browser.ntp.NewTabPageLayout r2 = r12.u
            y13 r0 = r12.G
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r0.z()
            int r7 = org.chromium.chrome.R$id.search_resumption_module_container_stub
            boolean r1 = org.chromium.chrome.browser.search_resumption.c.c(r13)
            r3 = 0
            if (r1 != 0) goto L18
            goto Laf
        L18:
            org.chromium.chrome.browser.tab.Tab r5 = r12.e
            int r1 = r5.getId()
            org.chromium.chrome.browser.tab.Tab r4 = defpackage.z7.z(r0, r1)
            if (r4 != 0) goto L2a
            r13 = 5
            org.chromium.chrome.browser.search_resumption.c.a(r13)
            goto Laf
        L2a:
            boolean r0 = r4.isNativePage()
            if (r0 != 0) goto L6a
            boolean r0 = r4.isIncognito()
            if (r0 != 0) goto L6a
            org.chromium.url.GURL r0 = r4.getUrl()
            boolean r0 = org.chromium.url.GURL.l(r0)
            if (r0 == 0) goto L41
            goto L6a
        L41:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r8 = java.lang.System.currentTimeMillis()
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData r1 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.m(r4)
            long r10 = r1.r
            long r8 = r8 - r10
            long r0 = r0.toSeconds(r8)
            sk r6 = defpackage.bq.a
            java.lang.String r6 = "SearchResumptionModuleAndroid"
            java.lang.String r8 = "tab_expiration_time"
            r9 = 3600(0xe10, float:5.045E-42)
            int r6 = J.N.M37SqSAy(r6, r8, r9)
            long r8 = (long) r6
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 >= 0) goto L65
            r0 = 1
            goto L6f
        L65:
            r0 = 7
            org.chromium.chrome.browser.search_resumption.c.a(r0)
            goto L6e
        L6a:
            r0 = 6
            org.chromium.chrome.browser.search_resumption.c.a(r0)
        L6e:
            r0 = 0
        L6f:
            if (r0 != 0) goto L72
            goto Laf
        L72:
            org.chromium.chrome.browser.search_resumption.a r0 = new org.chromium.chrome.browser.search_resumption.a
            boolean r1 = r5.i()
            if (r1 == 0) goto La7
            org.chromium.chrome.browser.search_resumption.e r1 = org.chromium.chrome.browser.search_resumption.e.a.a
            r1.getClass()
            org.chromium.chrome.browser.search_resumption.e r1 = org.chromium.chrome.browser.search_resumption.e.c(r5)
            if (r1 != 0) goto L87
            r1 = r3
            goto L89
        L87:
            org.chromium.chrome.browser.search_resumption.e$b r1 = r1.f
        L89:
            if (r1 == 0) goto La2
            org.chromium.url.GURL r6 = r1.a
            java.lang.String r6 = r6.j()
            org.chromium.url.GURL r8 = r4.getUrl()
            java.lang.String r8 = r8.j()
            boolean r6 = android.text.TextUtils.equals(r6, r8)
            if (r6 != 0) goto La0
            goto La2
        La0:
            r8 = r1
            goto La8
        La2:
            r1 = 8
            org.chromium.chrome.browser.search_resumption.c.a(r1)
        La7:
            r8 = r3
        La8:
            r1 = r0
            r3 = r14
            r6 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            r3 = r0
        Laf:
            r12.I = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ntp.h.v(org.chromium.chrome.browser.profiles.Profile, ca):void");
    }

    public final void w() {
        this.i.b(3);
        oj2.Y((System.nanoTime() - this.B) / 1000000, "NewTabPage.TimeSpent");
        n3.X0("Suggestions.SurfaceHidden");
        vk0.c(5);
    }

    public final void x() {
        View view = getView();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams == null) {
            return;
        }
        kj kjVar = this.o;
        int iL = (kjVar.l() - this.E) + (kjVar.f() - kjVar.l());
        int iG = kjVar.g() - kjVar.u();
        if (iL != marginLayoutParams.topMargin || iG != marginLayoutParams.bottomMargin) {
            marginLayoutParams.topMargin = iL;
            marginLayoutParams.bottomMargin = iG;
            view.setLayoutParams(marginLayoutParams);
        }
        this.u.setSearchProviderTopMargin(t(true));
        this.u.setSearchProviderBottomMargin(t(false));
    }

    @Override // defpackage.jk1
    public final void e(String str) {
    }
}
