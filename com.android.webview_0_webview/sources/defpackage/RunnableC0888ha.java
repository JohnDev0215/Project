package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ha, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0888ha implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC0888ha(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.clearFormData();
    }
}
