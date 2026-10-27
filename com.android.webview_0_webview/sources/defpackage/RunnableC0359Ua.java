package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ua, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0359Ua implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0359Ua(WebViewChromium webViewChromium, boolean z) {
        this.t = webViewChromium;
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.onWindowFocusChanged(this.s);
    }
}
