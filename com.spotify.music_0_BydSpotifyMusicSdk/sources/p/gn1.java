package p;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class gn1 {
    public static final String a;

    static {
        String strD = vc1.d("NetworkStateTracker");
        v03.i(strD, "tagWithPrefix(\"NetworkStateTracker\")");
        a = strD;
    }

    public static final dn1 a(ConnectivityManager connectivityManager) {
        NetworkCapabilities networkCapabilitiesA;
        v03.j(connectivityManager, "<this>");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        try {
            networkCapabilitiesA = tm1.a(connectivityManager, um1.a(connectivityManager));
        } catch (SecurityException e) {
            vc1.c().b(a, "Unable to validate active network", e);
        }
        boolean zB = networkCapabilitiesA != null ? tm1.b(networkCapabilitiesA, 16) : false;
        return new dn1(z, zB, tx.a(connectivityManager), (activeNetworkInfo == null || activeNetworkInfo.isRoaming()) ? false : true);
    }
}
