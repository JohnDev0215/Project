package org.chromium.android_webview;

import J.N;
import defpackage.C0284Pp;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwContentsStatics {
    public static C0284Pp a;
    public static String b;
    public static boolean c;

    public static String a() {
        if (b == null) {
            b = N.M0AyjO$E();
        }
        return b;
    }

    public static void clientCertificatesCleared(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public static void safeBrowsingWhitelistAssigned(Callback callback, boolean z) {
        if (callback == null) {
            return;
        }
        callback.a(Boolean.valueOf(z));
    }
}
