package defpackage;

import android.app.Activity;
import defpackage.lk1;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: SuggestionsNavigationDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public class hw2 extends ok1 {
    public hw2(Activity activity, Profile profile, lk1.b bVar, y13 y13Var, Tab tab) {
        super(activity, profile, bVar, y13Var, tab);
    }

    public void c(String str, int i, boolean z) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 2);
        if (!z) {
            b(i, loadUrlParams);
        } else {
            this.b.x(loadUrlParams, 14, this.c, false);
        }
    }
}
