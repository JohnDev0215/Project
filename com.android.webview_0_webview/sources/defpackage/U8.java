package defpackage;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class U8 extends AbstractC0511ao {
    public final WebView e;
    public final InterfaceC0555bc f;
    public final Context g;
    public WebViewClient h = T8.g;
    public final MM i;
    public V8 j;

    public U8(WebView webView, InterfaceC0555bc interfaceC0555bc, Context context) {
        if (webView == null) {
            throw new IllegalArgumentException("webView can't be null.");
        }
        if (interfaceC0555bc == null) {
            throw new IllegalArgumentException("delegate can't be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("context can't be null.");
        }
        this.e = webView;
        this.f = interfaceC0555bc;
        this.g = context;
        this.i = new MM();
    }
}
