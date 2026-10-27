package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: q9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1367q9 implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC1367q9(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.computeScroll();
    }
}
