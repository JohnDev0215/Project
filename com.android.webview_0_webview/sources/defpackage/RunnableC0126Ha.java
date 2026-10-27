package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ha, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0126Ha implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ boolean u;
    public final /* synthetic */ boolean v;
    public final /* synthetic */ WebViewChromium w;

    public RunnableC0126Ha(WebViewChromium webViewChromium, int i, int i2, boolean z, boolean z2) {
        this.w = webViewChromium;
        this.s = i;
        this.t = i2;
        this.u = z;
        this.v = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.w.onOverScrolled(this.s, this.t, this.u, this.v);
    }
}
