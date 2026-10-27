package defpackage;

import android.webkit.ValueCallback;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ ValueCallback t;
    public final /* synthetic */ WebViewChromium u;

    public C9(WebViewChromium webViewChromium, String str, ValueCallback valueCallback) {
        this.u = webViewChromium;
        this.s = str;
        this.t = valueCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebViewChromium.f(19);
        this.u.x.k(this.s, AbstractC0532b8.a(this.t));
    }
}
