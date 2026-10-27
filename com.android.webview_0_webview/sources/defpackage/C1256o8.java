package defpackage;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: o8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1256o8 {
    public static void a(WebViewClient webViewClient, WebView webView, C0426Xn c0426Xn, int i, Callback callback) {
        webViewClient.onSafeBrowsingHit(webView, new C0645d9(c0426Xn), i, new C1585u8(callback));
    }
}
