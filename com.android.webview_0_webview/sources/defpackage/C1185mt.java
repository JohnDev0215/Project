package defpackage;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Process;
import android.os.UserManager;
import android.security.NetworkSecurityPolicy;
import android.view.ActionMode;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: renamed from: mt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1185mt {
    public static int a(ActionMode actionMode) {
        return actionMode.getType();
    }

    public static Network b(ConnectivityManager connectivityManager) {
        return connectivityManager.getActiveNetwork();
    }

    public static Network c(ConnectivityManager connectivityManager) {
        return connectivityManager.getBoundNetworkForProcess();
    }

    public static long d() {
        return ViewConfiguration.getDefaultActionModeHideDuration();
    }

    public static long e(Network network) {
        return network.getNetworkHandle();
    }

    public static NetworkInfo f(ConnectivityManager connectivityManager, Network network) {
        return connectivityManager.getNetworkInfo(network);
    }

    public static void g(ActionMode actionMode, long j) {
        actionMode.hide(j);
    }

    public static void h(ActionMode actionMode) {
        actionMode.invalidateContentRect();
    }

    public static boolean i() {
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
    }

    public static boolean j(Activity activity, String str) {
        return activity.getPackageManager().isPermissionRevokedByPolicy(str, activity.getPackageName());
    }

    public static boolean k() {
        return Process.is64Bit();
    }

    public static boolean l(UserManager userManager) {
        return userManager.isSystemUser();
    }

    public static void m(WebViewClient webViewClient, WebView webView, String str) {
        webViewClient.onPageCommitVisible(webView, str);
    }

    public static void n(ActionMode actionMode, boolean z) {
        actionMode.onWindowFocusChanged(z);
    }

    public static void o(Activity activity, String[] strArr, int i) {
        activity.requestPermissions(strArr, i);
    }

    public static boolean p(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }
}
