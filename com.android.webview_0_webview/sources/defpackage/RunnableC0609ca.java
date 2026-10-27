package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ca, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0609ca implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC0609ca(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.resumeTimers();
    }
}
