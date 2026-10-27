package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Fa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0090Fa implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0090Fa(WebViewChromium webViewChromium, int i) {
        this.t = webViewChromium;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.setOverScrollMode(this.s);
    }
}
