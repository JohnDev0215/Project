package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: sa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1495sa implements Runnable {
    public final /* synthetic */ Object s;
    public final /* synthetic */ String t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC1495sa(WebViewChromium webViewChromium, Object obj, String str) {
        this.u = webViewChromium;
        this.s = obj;
        this.t = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.addJavascriptInterface(this.s, this.t);
    }
}
