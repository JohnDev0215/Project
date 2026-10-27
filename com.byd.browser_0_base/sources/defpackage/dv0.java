package defpackage;

import J.N;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.history.BrowsingHistoryBridge;
import org.chromium.chrome.browser.history.HistoryActivity;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: HistoryContentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class dv0 implements SigninManager.b, PrefChangeRegistrar.a {
    public final ArrayList e;
    public final cv0 f;
    public ev0 g;
    public final Activity h;
    public final a i;
    public final boolean j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final nw2<Tab> n;
    public final org.chromium.chrome.browser.history.a o;
    public final RecyclerView p;
    public LargeIconBridge q;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<ev0> r;
    public boolean s;
    public final PrefChangeRegistrar t;
    public final BookmarkModel u;

    /* JADX INFO: compiled from: HistoryContentManager.java */
    public interface a {
        void e();

        void h(boolean z);

        void l();

        void q();

        void s();

        void t(ev0 ev0Var);

        void u();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public dv0(android.app.Activity r1, dv0.a r2, boolean r3, boolean r4, boolean r5, boolean r6, java.lang.String r7, org.chromium.components.browser_ui.widget.selectable_list.a r8, defpackage.nw2 r9, defpackage.aq1 r10, org.chromium.chrome.browser.history.BrowsingHistoryBridge r11) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dv0.<init>(android.app.Activity, dv0$a, boolean, boolean, boolean, boolean, java.lang.String, org.chromium.components.browser_ui.widget.selectable_list.a, nw2, aq1, org.chromium.chrome.browser.history.BrowsingHistoryBridge):void");
    }

    public final ArrayList a() {
        ArrayList arrayList = this.e;
        if (arrayList.isEmpty()) {
            BookmarkModel bookmarkModel = this.u;
            ArrayList arrayListS = bookmarkModel.s("", 0, -1);
            arrayListS.addAll(bookmarkModel.j(bookmarkModel.m()));
            Iterator it = arrayListS.iterator();
            while (it.hasNext()) {
                BookmarkItem bookmarkItemH = bookmarkModel.h((BookmarkId) it.next());
                if (bookmarkItemH != null && !bookmarkItemH.d) {
                    arrayList.add(bookmarkItemH);
                }
            }
        }
        return arrayList;
    }

    public final Intent b(GURL gurl, Boolean bool, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(gurl.j()));
        Activity activity = this.h;
        intent.putExtra("com.android.browser.application_id", activity.getApplicationContext().getPackageName());
        intent.addFlags(268435456);
        intent.putExtra("com.google.chrome.transition_type", 2);
        ComponentName componentName = ((activity instanceof HistoryActivity) || (activity instanceof BookmarkHistoryActivity)) ? (ComponentName) q11.n(activity.getIntent(), "org.chromium.chrome.browser.parent_component") : activity.getComponentName();
        if (componentName != null) {
            z7.g0(intent, componentName);
        } else {
            intent.setClass(activity, ChromeLauncherActivity.class);
        }
        q11.a(intent);
        if (bool != null) {
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", bool);
        }
        if (z) {
            intent.putExtra("create_new_tab", true);
        }
        return intent;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void c() {
        this.i.s();
        this.o.R();
    }

    public final void d() {
        cv0 cv0Var = this.f;
        if (cv0Var != null) {
            this.u.r(cv0Var);
        }
        org.chromium.chrome.browser.history.a aVar = this.o;
        aVar.q = true;
        BrowsingHistoryBridge browsingHistoryBridge = (BrowsingHistoryBridge) aVar.m;
        long j = browsingHistoryBridge.b;
        if (j != 0) {
            N.MZEuRD6z(j, browsingHistoryBridge);
            browsingHistoryBridge.b = 0L;
        }
        aVar.m = null;
        FaviconHelper.a aVar2 = aVar.l;
        aVar2.a = null;
        aVar2.b = null;
        aVar2.c = null;
        this.q.a();
        this.q = null;
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.D(profileD).n(this);
        this.t.b();
    }

    public final void e(GURL gurl, Boolean bool, boolean z) {
        if (this.j) {
            b.A(null, b(gurl, bool, z), null);
            return;
        }
        Tab tab = this.n.get();
        if (z) {
            new iz2(bool != null ? bool.booleanValue() : this.k).b(0, tab, new LoadUrlParams(gurl.j(), 2));
        } else {
            try {
                tab.b(new LoadUrlParams(gurl.j(), 2));
            } catch (Exception unused) {
            }
        }
    }

    @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
    public final void f() {
        this.i.s();
        this.o.R();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void j() {
        this.i.s();
        this.o.R();
    }
}
