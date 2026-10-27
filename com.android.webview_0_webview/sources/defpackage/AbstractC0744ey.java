package defpackage;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: renamed from: ey, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0744ey {
    public static boolean a(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return !connectivityManager.isActiveNetworkMetered();
    }
}
