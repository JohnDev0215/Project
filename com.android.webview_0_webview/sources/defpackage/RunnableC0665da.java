package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: da, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0665da implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC0665da(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.onPause();
    }
}
