package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: pa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1331pa implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC1331pa(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.clearMatches();
    }
}
