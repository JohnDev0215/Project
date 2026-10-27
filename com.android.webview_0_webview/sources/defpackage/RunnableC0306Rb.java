package defpackage;

import com.android.webview.chromium.WebViewDatabaseAdapter;

/* JADX INFO: renamed from: Rb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0306Rb implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ String u;
    public final /* synthetic */ String v;
    public final /* synthetic */ WebViewDatabaseAdapter w;

    public RunnableC0306Rb(WebViewDatabaseAdapter webViewDatabaseAdapter, String str, String str2, String str3, String str4) {
        this.w = webViewDatabaseAdapter;
        this.s = str;
        this.t = str2;
        this.u = str3;
        this.v = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.w.b.d(this.s, this.t, this.u, this.v);
    }
}
