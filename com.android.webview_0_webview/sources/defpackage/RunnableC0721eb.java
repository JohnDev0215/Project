package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: eb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0721eb implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC0721eb(WebViewChromium webViewChromium, int i, int i2) {
        this.u = webViewChromium;
        this.s = i;
        this.t = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.onMeasure(this.s, this.t);
    }
}
