package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: na, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1221na implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1221na(WebViewChromium webViewChromium, String str) {
        this.t = webViewChromium;
        this.s = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.findAllAsync(this.s);
    }
}
