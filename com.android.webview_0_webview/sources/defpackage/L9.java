package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class L9 implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ WebViewChromium t;

    public L9(WebViewChromium webViewChromium, int i) {
        this.t = webViewChromium;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.goBackOrForward(this.s);
    }
}
