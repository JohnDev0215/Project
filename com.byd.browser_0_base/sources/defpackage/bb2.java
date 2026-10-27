package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ReparentingTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class bb2 implements lk3 {
    public final Tab e;

    public bb2(Tab tab) {
        this.e = tab;
    }

    public static bb2 b(Tab tab) {
        bb2 bb2Var = (bb2) tab.B().b(bb2.class);
        return bb2Var == null ? (bb2) tab.B().d(bb2.class, new bb2(tab)) : bb2Var;
    }

    public final void a() {
        Tab tab = this.e;
        WebContents webContentsC = tab.c();
        if (webContentsC != null) {
            webContentsC.i0(null);
        }
        tab.E(null, null);
    }

    public final void c(Intent intent, l10 l10Var) {
        if (intent.getComponent() == null) {
            intent.setClass(xz.a, ChromeLauncherActivity.class);
        }
        intent.setAction("android.intent.action.VIEW");
        boolean zIsEmpty = TextUtils.isEmpty(intent.getDataString());
        Tab tab = this.e;
        if (zIsEmpty) {
            intent.setData(Uri.parse(tab.getUrl().j()));
        }
        if (tab.isIncognito()) {
            intent.putExtra("com.android.browser.application_id", xz.a.getPackageName());
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
        }
        q11.a(intent);
        intent.putExtra("com.android.chrome.tab_id", tab.getId());
        h9.a.c(tab.getId(), new d33(tab, l10Var));
        a();
    }
}
