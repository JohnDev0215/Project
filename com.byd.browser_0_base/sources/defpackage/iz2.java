package defpackage;

import android.app.Activity;
import android.content.Intent;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iz2 extends d9 {
    public final boolean a;

    public iz2(boolean z) {
        this.a = z;
    }

    @Override // defpackage.fz2
    public final Tab a(m33 m33Var, ui2 ui2Var, int i, boolean z, int i2) {
        boolean z2 = this.a;
        if (z != z2) {
            throw new IllegalStateException("Incognito state mismatch. isIncognito: " + z + ". TabDelegate: " + z2);
        }
        ry2 ry2Var = new ry2();
        ry2Var.b(3);
        ry2Var.g = 2;
        ry2Var.a = i;
        ry2Var.d = z;
        return ry2Var.a();
    }

    @Override // defpackage.fz2
    public final Tab b(int i, Tab tab, LoadUrlParams loadUrlParams) {
        h(new c9(loadUrlParams, null, null, null), i, tab == null ? -1 : tab.getId());
        return null;
    }

    @Override // defpackage.fz2
    public final Tab c(LoadUrlParams loadUrlParams, int i) {
        b(3, null, loadUrlParams);
        return null;
    }

    @Override // defpackage.fz2
    public final boolean d(Tab tab, WebContents webContents, int i, GURL gurl) {
        h(new c9(new LoadUrlParams(gurl.j(), 6), webContents, null, null), i, tab != null ? tab.getId() : -1);
        return true;
    }

    @Override // defpackage.fz2
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fz2
    public final Tab g(int i, String str) {
        b(i, null, new LoadUrlParams(str, 0));
        return null;
    }

    public final void h(c9 c9Var, int i, int i2) {
        b.A(null, i(c9Var, i2, i == 2), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.content.Intent i(defpackage.c9 r6, int r7, boolean r8) {
        /*
            r5 = this;
            t03 r0 = defpackage.t03.a()
            java.util.concurrent.atomic.AtomicInteger r1 = r0.a
            int r2 = r1.getAndIncrement()
            int r3 = r2 + 1
            int r4 = r1.get()
            int r3 = r3 - r4
            if (r3 >= 0) goto L14
            goto L22
        L14:
            r1.addAndGet(r3)
            int r1 = r1.get()
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r0 = r0.b
            java.lang.String r3 = "org.chromium.chrome.browser.tab.TabIdManager.NEXT_ID"
            r0.m(r1, r3)
        L22:
            g9 r0 = defpackage.h9.a
            r0.c(r2, r6)
            android.content.Intent r0 = new android.content.Intent
            org.chromium.content_public.browser.LoadUrlParams r1 = r6.a
            java.lang.String r3 = r1.a
            android.net.Uri r3 = android.net.Uri.parse(r3)
            java.lang.String r4 = "android.intent.action.VIEW"
            r0.<init>(r4, r3)
            android.content.ComponentName r3 = r6.d
            if (r3 != 0) goto L42
            android.content.Context r3 = defpackage.xz.a
            java.lang.Class<org.chromium.chrome.browser.document.ChromeLauncherActivity> r4 = org.chromium.chrome.browser.document.ChromeLauncherActivity.class
            r0.setClass(r3, r4)
            goto L45
        L42:
            defpackage.z7.g0(r0, r3)
        L45:
            java.util.Map<java.lang.String, java.lang.String> r1 = r1.f
            org.chromium.chrome.browser.b.y(r1, r0)
            java.lang.String r1 = "com.android.chrome.tab_id"
            r0.putExtra(r1, r2)
            java.lang.String r1 = "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB"
            boolean r5 = r5.a
            r0.putExtra(r1, r5)
            java.lang.String r1 = "com.android.chrome.parent_tab_id"
            r0.putExtra(r1, r7)
            if (r5 != 0) goto L5f
            if (r8 == 0) goto L6a
        L5f:
            android.content.Context r5 = defpackage.xz.a
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r1 = "com.android.browser.application_id"
            r0.putExtra(r1, r5)
        L6a:
            if (r8 == 0) goto L72
            java.lang.String r5 = "create_new_tab"
            r8 = 1
            r0.putExtra(r5, r8)
        L72:
            r5 = -1
            if (r7 != r5) goto L76
            goto L8e
        L76:
            n53 r5 = defpackage.oj2.r()
            o53 r5 = (defpackage.o53) r5
            org.chromium.chrome.browser.tab.Tab r5 = r5.e(r7)
            if (r5 != 0) goto L83
            goto L8e
        L83:
            android.content.Context r5 = r5.getContext()
            boolean r7 = r5 instanceof android.app.Activity
            if (r7 == 0) goto L8e
            android.app.Activity r5 = (android.app.Activity) r5
            goto L8f
        L8e:
            r5 = 0
        L8f:
            if (r5 == 0) goto La0
            android.content.Intent r7 = r5.getIntent()
            if (r7 == 0) goto La0
            java.lang.String r7 = "com.android.chrome.parent_intent"
            android.content.Intent r5 = r5.getIntent()
            r0.putExtra(r7, r5)
        La0:
            java.lang.Integer r5 = r6.c
            if (r5 == 0) goto Lad
            int r5 = r5.intValue()
            java.lang.String r6 = "org.chromium.chrome.browser.ServiceTabLauncher.LAUNCH_REQUEST_ID"
            r0.putExtra(r6, r5)
        Lad:
            r5 = 268435456(0x10000000, float:2.524355E-29)
            r0.setFlags(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iz2.i(c9, int, boolean):android.content.Intent");
    }

    public final void j(LoadUrlParams loadUrlParams, Activity activity, int i, Activity activity2) {
        Intent intentI = i(new c9(loadUrlParams, null, null, null), i, false);
        Class<? extends Activity> clsI = vj1.h.i(activity);
        if (clsI == null) {
            return;
        }
        vj1.s(intentI, activity, clsI);
        q11.a(intentI);
        kj1.s = 0;
        if (vj1.p()) {
            if (activity2 != null) {
                ((ChromeTabbedActivity) activity2).onNewIntent(intentI);
                return;
            } else {
                intentI.addFlags(268435456);
                intentI.addFlags(134217728);
            }
        }
        activity.startActivity(intentI, vj1.j(activity));
    }
}
