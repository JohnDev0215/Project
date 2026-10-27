package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.app.creator.CreatorActivity;
import org.chromium.chrome.browser.feed.webfeed.WebFeedBridge$WebFeedPageInformation;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;
import org.chromium.chrome.browser.feed.webfeed.f;
import org.chromium.chrome.browser.feed.webfeed.i;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.components.favicon.LargeIconBridge;

/* JADX INFO: compiled from: TabbedAppMenuPropertiesDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q53 extends o7 {
    public final i.a t;
    public final bi1 u;
    public final wp2 v;

    public q53(Context context, z2 z2Var, uj1 uj1Var, a23 a23Var, g gVar, View view, rs1 rs1Var, rs1 rs1Var2, aq1 aq1Var, gx gxVar, bi1 bi1Var, wp2 wp2Var, rs1 rs1Var3) {
        super(context, z2Var, uj1Var, a23Var, gVar, view, rs1Var, rs1Var2, aq1Var, rs1Var3);
        this.t = gxVar;
        this.u = bi1Var;
        this.v = wp2Var;
    }

    @Override // defpackage.o7, defpackage.m7
    public final int a() {
        if (s()) {
            return R$layout.web_feed_main_menu_item;
        }
        return 0;
    }

    @Override // defpackage.o7, defpackage.m7
    public final void c() {
        s();
    }

    @Override // defpackage.o7, defpackage.m7
    public final void d(i7 i7Var, View view) {
        f.a aVar;
        if (view instanceof WebFeedMainMenuItem) {
            WebFeedMainMenuItem webFeedMainMenuItem = (WebFeedMainMenuItem) view;
            Tab tab = (Tab) this.d.f;
            Profile profileD = Profile.d();
            xq3 xq3Var = new xq3(new LargeIconBridge(profileD), dx0.c(profileD.g(), ys0.a));
            webFeedMainMenuItem.getClass();
            webFeedMainMenuItem.f = tab.v();
            webFeedMainMenuItem.g = tab;
            webFeedMainMenuItem.i = i7Var;
            webFeedMainMenuItem.q = xq3Var;
            webFeedMainMenuItem.r = new i(webFeedMainMenuItem.e, this.t, this.u, this.v);
            webFeedMainMenuItem.j = CreatorActivity.class;
            dr3 dr3Var = new dr3(webFeedMainMenuItem, 1);
            mk3 mk3VarB = webFeedMainMenuItem.g.B();
            byte[] bArr = null;
            if (mk3VarB != null && (aVar = (f.a) mk3VarB.b(f.a.class)) != null) {
                bArr = aVar.e;
            }
            webFeedMainMenuItem.p = bArr;
            if (bArr != null) {
                N.MHvYsLrH(bArr, dr3Var);
            } else {
                N.MSWj76M1(new WebFeedBridge$WebFeedPageInformation(webFeedMainMenuItem.g, webFeedMainMenuItem.f), 2, dr3Var);
            }
        }
    }

    @Override // defpackage.o7
    public final boolean n(Tab tab) {
        Profile profileB = Profile.b(tab.c());
        return profileB != null && N.MmSLoR8I(profileB);
    }

    public final boolean s() {
        Tab tab;
        if (!zk0.d() || (tab = (Tab) this.d.f) == null || tab.isIncognito() || d.e(tab)) {
            return false;
        }
        String strJ = tab.v().j();
        return strJ.startsWith("http://") || strJ.startsWith("https://");
    }

    @Override // defpackage.o7, defpackage.m7
    public final void e() {
    }
}
