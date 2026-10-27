package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: z9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1861z9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ String u;
    public final /* synthetic */ WebViewChromium v;

    public RunnableC1861z9(WebViewChromium webViewChromium, String str, String str2, String str3) {
        this.v = webViewChromium;
        this.s = str;
        this.t = str2;
        this.u = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebViewChromium.f(44);
        this.v.x.A(this.s, this.t, this.u);
    }
}
