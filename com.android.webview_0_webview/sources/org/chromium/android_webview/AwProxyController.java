package org.chromium.android_webview;

import J.N;
import defpackage.It;
import defpackage.Lt;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwProxyController {
    public static void b(int i) {
        It.d("Android.WebView.SetProxyOverride.ProxySchemeFilterType", i, 3);
    }

    public static void c(int i) {
        It.d("Android.WebView.SetProxyOverride.ProxyUrlType", i, 3);
    }

    private void proxyOverrideChanged(Runnable runnable, Executor executor) {
        if (runnable == null) {
            return;
        }
        executor.execute(runnable);
    }

    public void a(Runnable runnable, Executor executor) {
        if (executor == null) {
            throw new IllegalArgumentException("Executor must not be null");
        }
        N.Mx5cEreM(this, runnable, executor);
        Lt.a.b("Android.WebView.ClearProxyOverride", true);
    }

    public void d(String[][] strArr, String[] strArr2, Runnable runnable, Executor executor) {
        boolean z = false;
        int length = strArr == null ? 0 : strArr.length;
        String[] strArr3 = new String[length];
        String[] strArr4 = new String[length];
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        for (int i = 0; i < length; i++) {
            if (strArr[i][0] == null) {
                strArr3[i] = "*";
            } else {
                strArr3[i] = strArr[i][0];
            }
            strArr4[i] = strArr[i][1];
            if (strArr4[i] == null) {
                throw new IllegalArgumentException("Proxy rule " + i + " has a null url");
            }
            if (strArr[i][0].equals("http")) {
                z2 = true;
            } else {
                if (!strArr[i][0].equals("https")) {
                    z2 = true;
                }
                z3 = true;
            }
            if (strArr4[i].startsWith("http://")) {
                z4 = true;
            } else if (strArr4[i].startsWith("https://")) {
                z5 = true;
            } else if (strArr4[i].startsWith("direct://")) {
                z6 = true;
            }
        }
        int length2 = strArr2 == null ? 0 : strArr2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (strArr2[i2] == null) {
                throw new IllegalArgumentException("Bypass rule " + i2 + " is null");
            }
        }
        if (executor == null) {
            throw new IllegalArgumentException("Executor must not be null");
        }
        String strM8RBhX3H = N.M8RBhX3H(this, strArr3, strArr4, strArr2, runnable, executor);
        if (!strM8RBhX3H.isEmpty()) {
            throw new IllegalArgumentException(strM8RBhX3H);
        }
        if (z2 && z3) {
            b(2);
        } else if (z2) {
            b(0);
        } else if (z3) {
            b(1);
        }
        if (z4) {
            c(0);
        }
        if (z5) {
            c(1);
        }
        if (z6) {
            c(2);
        }
        if (strArr2 != null && strArr2.length != 0) {
            z = true;
        }
        Lt.a.b("Android.WebView.SetProxyOverride.BypassRules", z);
    }
}
