package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class A9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ String u;
    public final /* synthetic */ String v;
    public final /* synthetic */ String w;
    public final /* synthetic */ WebViewChromium x;

    public A9(WebViewChromium webViewChromium, String str, String str2, String str3, String str4, String str5) {
        this.x = webViewChromium;
        this.s = str;
        this.t = str2;
        this.u = str3;
        this.v = str4;
        this.w = str5;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebViewChromium.f(45);
        this.x.x.B(this.s, this.t, this.u, this.v, this.w);
    }
}
