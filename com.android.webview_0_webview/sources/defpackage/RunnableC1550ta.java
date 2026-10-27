package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ta, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1550ta implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1550ta(WebViewChromium webViewChromium, boolean z) {
        this.t = webViewChromium;
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.setVerticalScrollbarOverlay(this.s);
    }
}
