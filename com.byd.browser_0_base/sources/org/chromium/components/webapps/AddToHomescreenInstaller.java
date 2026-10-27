package org.chromium.components.webapps;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import defpackage.fa1;
import defpackage.xz;
import defpackage.zt1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class AddToHomescreenInstaller {
    @CalledByNative
    public static boolean installOrOpenNativeApp(WebContents webContents, AppData appData) {
        Context context = xz.a;
        appData.getClass();
        Intent launchIntentForPackage = zt1.e(null) ? context.getPackageManager().getLaunchIntentForPackage(null) : null;
        if (launchIntentForPackage == null) {
            return true;
        }
        WindowAndroid windowAndroidV = webContents.V();
        Context context2 = windowAndroidV == null ? null : (Context) windowAndroidV.k().get();
        if (context2 == null) {
            return true;
        }
        try {
            context2.startActivity(launchIntentForPackage);
            return true;
        } catch (ActivityNotFoundException e) {
            fa1.c("AddToHomescreen", "Failed to install or open app : %s!", null, e);
            return false;
        }
    }
}
