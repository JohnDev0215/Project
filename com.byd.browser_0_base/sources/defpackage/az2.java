package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.Clipboard;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabContextMenuItemDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class az2 implements pz {
    public final TabImpl a;
    public final y13 b;
    public final nw2<EphemeralTabCoordinator> c;
    public final Runnable d;
    public final nw2<wp2> e;

    public az2(Tab tab, y13 y13Var, nw2<EphemeralTabCoordinator> nw2Var, Runnable runnable, nw2<wp2> nw2Var2) {
        this.a = (TabImpl) tab;
        this.b = y13Var;
        this.c = nw2Var;
        this.d = runnable;
        this.e = nw2Var2;
    }

    public final boolean a() {
        return this.a.isIncognito();
    }

    public final void b(GURL gurl, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(gurl.j()));
        intent.setFlags(268435456);
        intent.setClass(xz.a, ChromeLauncherActivity.class);
        if (z) {
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
            intent.putExtra("com.android.browser.application_id", xz.a.getPackageName());
            q11.a(intent);
            intent.putExtra("org.chromium.chrome.browser.tab_launch_type", 1);
        }
        Context context = this.a.getContext();
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }

    public final void c(GURL gurl, on2 on2Var, boolean z) {
        n3.X0("MobileNewTabOpened");
        n3.X0("LinkOpenedInNewTab");
        LoadUrlParams loadUrlParams = new LoadUrlParams(gurl.j(), 0);
        loadUrlParams.e = on2Var;
        this.b.x(loadUrlParams, z ? 4 : 5, this.a, a());
    }

    public final void d(GURL gurl, on2 on2Var) {
        TabImpl tabImpl = this.a;
        iz2 iz2Var = new iz2(tabImpl.isIncognito());
        LoadUrlParams loadUrlParams = new LoadUrlParams(gurl.j(), 0);
        loadUrlParams.e = on2Var;
        Activity activityB = TabUtils.b(tabImpl);
        iz2Var.j(loadUrlParams, activityB, CriticalPersistedTabData.m(tabImpl).p, vj1.f(activityB));
    }

    public final void e(int i, String str) {
        Clipboard.getInstance().setText(str);
        if (i == 0) {
            this.d.run();
        }
    }
}
