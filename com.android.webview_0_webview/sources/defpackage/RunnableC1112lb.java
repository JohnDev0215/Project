package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: lb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1112lb implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ String u;
    public final /* synthetic */ String v;
    public final /* synthetic */ WebViewChromium w;

    public RunnableC1112lb(WebViewChromium webViewChromium, String str, String str2, String str3, String str4) {
        this.w = webViewChromium;
        this.s = str;
        this.t = str2;
        this.u = str3;
        this.v = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.w.setHttpAuthUsernamePassword(this.s, this.t, this.u, this.v);
    }
}
