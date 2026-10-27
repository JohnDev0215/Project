package org.chromium.chrome.browser.password_manager;

import android.app.Activity;
import defpackage.aq1;
import defpackage.n3;
import defpackage.nx0;
import defpackage.qx1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordManagerLauncher {
    @CalledByNative
    public static boolean canManagePasswordsWhenPasskeysPresent() {
        qx1.a();
        return true;
    }

    @CalledByNative
    public static void showPasswordSettings(WebContents webContents, int i, boolean z) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            return;
        }
        nx0 nx0VarK = windowAndroidV.k();
        new aq1().r(windowAndroidV.n());
        qx1.b((Activity) nx0VarK.get(), i, new n3(), z);
    }
}
