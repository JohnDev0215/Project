package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ga, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0108Ga implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0108Ga(WebViewChromium webViewChromium, int i) {
        this.t = webViewChromium;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.setScrollBarStyle(this.s);
    }
}
