package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ReaderModeManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class g92 extends InterceptNavigationDelegate {
    public final /* synthetic */ Activity a;

    public g92(Activity activity) {
        this.a = activity;
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public final boolean shouldIgnoreNavigation(NavigationHandle navigationHandle, GURL gurl, boolean z, boolean z2) {
        if (a8.M(navigationHandle.e) || navigationHandle.p) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(gurl.j()));
        String name = ChromeLauncherActivity.class.getName();
        Activity activity = this.a;
        intent.setClassName(activity, name);
        intent.putExtra("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", q11.k("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", -1, activity.getIntent().getExtras()));
        activity.startActivity(intent);
        activity.finish();
        return true;
    }
}
