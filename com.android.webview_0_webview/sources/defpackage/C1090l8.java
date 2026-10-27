package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.UserManager;
import android.webkit.ServiceWorkerController;
import android.webkit.WebView;
import android.webkit.WebViewDelegate;

/* JADX INFO: renamed from: l8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1090l8 {
    public static void a(WebViewDelegate webViewDelegate, Canvas canvas, long j, Runnable runnable) {
        webViewDelegate.callDrawGlFunction(canvas, j, runnable);
    }

    public static Context b(Context context) {
        return context.createCredentialProtectedStorageContext();
    }

    public static ServiceWorkerController c(C1551tb c1551tb) {
        return new C1695w8(c1551tb.f());
    }

    public static boolean d(Context context) {
        return context.isDeviceProtectedStorage();
    }

    public static boolean e(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }

    public static void f(WebView.PrivateAccess privateAccess, Intent intent, int i) {
        privateAccess.super_startActivityForResult(intent, i);
    }
}
