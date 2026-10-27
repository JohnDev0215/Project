package defpackage;

import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Fb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandlerC0091Fb extends Handler {
    public final /* synthetic */ C0252Ob a;

    public HandlerC0091Fb(C0252Ob c0252Ob) {
        this.a = c0252Ob;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 100) {
            throw new IllegalStateException();
        }
        WebView webView = ((WebView.WebViewTransport) message.obj).getWebView();
        if (webView == this.a.e) {
            throw new IllegalArgumentException("Parent WebView cannot host its own popup window. Please use WebSettings.setSupportMultipleWindows(false)");
        }
        if (webView != null && webView.copyBackForwardList().getSize() != 0) {
            throw new IllegalArgumentException("New WebView for popup window must not have been  previously navigated.");
        }
        WebViewChromium.d(this.a.e, webView);
    }
}
