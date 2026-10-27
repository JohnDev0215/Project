package org.chromium.chrome.browser.datareduction;

import android.net.ConnectivityManager;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class DataSaverOSSetting {
    @CalledByNative
    public static boolean isDataSaverEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
        return connectivityManager.isActiveNetworkMetered() && connectivityManager.getRestrictBackgroundStatus() == 3;
    }
}
