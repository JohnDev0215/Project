package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PasswordCheckUkmRecorder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zw1 extends nh0 {
    public zw1(TabImpl tabImpl) {
        tabImpl.x(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        tab.t(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        Activity activity;
        if (tab.c() == null) {
            return;
        }
        WindowAndroid windowAndroidV = tab.c().V();
        Intent intent = (windowAndroidV == null || (activity = (Activity) windowAndroidV.k().get()) == null) ? null : activity.getIntent();
        if (intent == null || intent.getExtras() == null || !intent.hasExtra("org.chromium.chrome.browser.password_check.PASSWORD_CHANGE_TYPE")) {
            return;
        }
        N.MCnWTXic(tab.c(), "PasswordManager.PasswordChangeTriggered", "PasswordChangeType", intent.getExtras().getInt("org.chromium.chrome.browser.password_check.PASSWORD_CHANGE_TYPE"));
        intent.removeExtra("org.chromium.chrome.browser.password_check.PASSWORD_CHANGE_TYPE");
    }
}
