package org.chromium.chrome.browser.background_sync;

import android.util.Log;
import defpackage.oj2;
import defpackage.zi0;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public final class GooglePlayServicesChecker {
    @CalledByNative
    public static boolean shouldDisableBackgroundSync() {
        zi0.b.getClass();
        Log.i("cr_PlayServicesChecker", "Disabling Background Sync because Play Services is not up to date.");
        oj2.O("BackgroundSync.LaunchTask.PlayServicesAvailable", false);
        return true;
    }
}
