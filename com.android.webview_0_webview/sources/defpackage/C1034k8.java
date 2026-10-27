package defpackage;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.chromium.android_webview.AwWebResourceResponse;

/* JADX INFO: renamed from: k8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1034k8 {
    public static void a(WebViewClient webViewClient, WebView webView, C0426Xn c0426Xn, C0408Wn c0408Wn) {
        webViewClient.onReceivedError(webView, new C0645d9(c0426Xn), new C0589c9(c0408Wn));
    }

    public static void b(WebViewClient webViewClient, WebView webView, C0426Xn c0426Xn, AwWebResourceResponse awWebResourceResponse) {
        webViewClient.onReceivedHttpError(webView, new C0645d9(c0426Xn), new WebResourceResponse(true, awWebResourceResponse.getMimeType(), awWebResourceResponse.getCharset(), awWebResourceResponse.getStatusCode(), awWebResourceResponse.getReasonPhrase(), awWebResourceResponse.f, awWebResourceResponse.getData()));
    }
}
