package p;

import android.net.ConnectivityManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class tx {
    public static boolean a(ConnectivityManager connectivityManager) {
        return connectivityManager.isActiveNetworkMetered();
    }
}
