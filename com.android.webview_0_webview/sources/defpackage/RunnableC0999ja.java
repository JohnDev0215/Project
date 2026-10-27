package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ja, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0999ja implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC0999ja(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.clearHistory();
    }
}
