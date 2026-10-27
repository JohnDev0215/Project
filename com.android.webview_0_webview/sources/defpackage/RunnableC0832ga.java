package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ga, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0832ga implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0832ga(WebViewChromium webViewChromium, boolean z) {
        this.t = webViewChromium;
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.clearCache(this.s);
    }
}
