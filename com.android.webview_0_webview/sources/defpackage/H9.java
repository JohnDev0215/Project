package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class H9 implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public H9(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.goBack();
    }
}
