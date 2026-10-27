package p;

import android.net.ConnectivityManager;
import android.net.Network;

/* JADX INFO: loaded from: classes.dex */
public abstract class um1 {
    public static final Network a(ConnectivityManager connectivityManager) {
        v03.j(connectivityManager, "<this>");
        return connectivityManager.getActiveNetwork();
    }
}
