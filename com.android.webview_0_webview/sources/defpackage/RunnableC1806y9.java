package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: y9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1806y9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ byte[] t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC1806y9(WebViewChromium webViewChromium, String str, byte[] bArr) {
        this.u = webViewChromium;
        this.s = str;
        this.t = bArr;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebViewChromium.f(56);
        this.u.x.K(this.s, this.t);
    }
}
