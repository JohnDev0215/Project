package org.chromium.chrome.browser.password_manager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import defpackage.n3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.password_check.e;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckupLauncher {
    @CalledByNative
    public static void launchCheckupInAccountWithActivity(String str, Activity activity) {
        AppHooks.get().getClass();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setPackage(activity.getPackageName());
        activity.startActivity(intent);
    }

    @CalledByNative
    public static void launchCheckupInAccountWithWindowAndroid(String str, WindowAndroid windowAndroid) {
        if (windowAndroid.i.get() == null) {
            return;
        }
        launchCheckupInAccountWithActivity(str, (Activity) windowAndroid.k().get());
    }

    @CalledByNative
    public static void launchLocalCheckup(WindowAndroid windowAndroid, int i) {
        if (windowAndroid.i.get() == null) {
            return;
        }
        ((e) n3.i0(new n3())).b(windowAndroid.i.get(), i);
    }
}
