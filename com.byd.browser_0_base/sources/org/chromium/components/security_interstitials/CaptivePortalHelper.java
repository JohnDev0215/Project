package org.chromium.components.security_interstitials;

import android.net.ConnectivityManager;
import android.net.Network;
import defpackage.xz;
import java.lang.reflect.InvocationTargetException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class CaptivePortalHelper {
    @CalledByNative
    public static String getCaptivePortalServerUrl() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
            return (String) connectivityManager.getClass().getMethod("getCaptivePortalServerUrl", new Class[0]).invoke(connectivityManager, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return "http://connectivitycheck.gstatic.com/generate_204";
        }
    }

    @CalledByNative
    public static void reportNetworkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
        for (Network network : connectivityManager.getAllNetworks()) {
            connectivityManager.reportNetworkConnectivity(network, true);
            connectivityManager.reportNetworkConnectivity(network, false);
        }
    }
}
