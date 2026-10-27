package p;

import android.net.ConnectivityManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class vm1 {
    public static final void a(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        v03.j(connectivityManager, "<this>");
        v03.j(networkCallback, "networkCallback");
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }
}
