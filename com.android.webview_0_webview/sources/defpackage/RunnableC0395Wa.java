package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Wa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0395Wa implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ int u;
    public final /* synthetic */ int v;
    public final /* synthetic */ WebViewChromium w;

    public RunnableC0395Wa(WebViewChromium webViewChromium, int i, int i2, int i3, int i4) {
        this.w = webViewChromium;
        this.s = i;
        this.t = i2;
        this.u = i3;
        this.v = i4;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.w.onSizeChanged(this.s, this.t, this.u, this.v);
    }
}
