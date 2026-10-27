package defpackage;

import android.webkit.ValueCallback;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class D9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ boolean t;
    public final /* synthetic */ ValueCallback u;
    public final /* synthetic */ WebViewChromium v;

    public D9(WebViewChromium webViewChromium, String str, boolean z, ValueCallback valueCallback) {
        this.v = webViewChromium;
        this.s = str;
        this.t = z;
        this.u = valueCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.v.saveWebArchive(this.s, this.t, this.u);
    }
}
