package org.chromium.components.security_interstitials;

import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import defpackage.Is;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CaptivePortalHelper {
    public static String getCaptivePortalServerUrl() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) Is.a.getSystemService("connectivity");
            return (String) connectivityManager.getClass().getMethod("getCaptivePortalServerUrl", new Class[0]).invoke(connectivityManager, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return "http://connectivitycheck.gstatic.com/generate_204";
        }
    }

    public static void reportNetworkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Is.a.getSystemService("connectivity");
        if (Build.VERSION.SDK_INT >= 23) {
            for (Network network : connectivityManager.getAllNetworks()) {
                connectivityManager.reportNetworkConnectivity(network, true);
                connectivityManager.reportNetworkConnectivity(network, false);
            }
            return;
        }
        try {
            Class<?> cls = Class.forName("android.net.Network");
            Method method = connectivityManager.getClass().getMethod("reportNetworkConnectivity", Class.forName("android.net.Network"), Boolean.TYPE);
            for (Object obj : (Object[]) connectivityManager.getClass().getMethod("getAllNetworks", new Class[0]).invoke(connectivityManager, new Object[0])) {
                method.invoke(connectivityManager, cls.cast(obj), Boolean.TRUE);
                method.invoke(connectivityManager, cls.cast(obj), Boolean.FALSE);
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }
}
