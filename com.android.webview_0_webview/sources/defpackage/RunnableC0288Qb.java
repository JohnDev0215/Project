package defpackage;

import com.android.webview.chromium.WebViewDatabaseAdapter;

/* JADX INFO: renamed from: Qb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0288Qb implements Runnable {
    public final /* synthetic */ WebViewDatabaseAdapter s;

    public RunnableC0288Qb(WebViewDatabaseAdapter webViewDatabaseAdapter) {
        this.s = webViewDatabaseAdapter;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0464Zp c0464Zp = this.s.b;
        if (c0464Zp.e()) {
            c0464Zp.a.delete("httpauth", null, null);
        }
    }
}
