package defpackage;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewDelegate;

/* JADX INFO: renamed from: n8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1201n8 {
    public static boolean a(WebViewDelegate webViewDelegate) {
        return webViewDelegate.isMultiProcessEnabled();
    }

    public static boolean b(WebViewClient webViewClient, WebView webView, C0427Xo c0427Xo) {
        return webViewClient.onRenderProcessGone(webView, new C1146m8(c0427Xo));
    }
}
