package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ma, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1166ma implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1166ma(WebViewChromium webViewChromium, boolean z) {
        this.t = webViewChromium;
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.findNext(this.s);
    }
}
