package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ea, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0720ea implements Runnable {
    public final /* synthetic */ WebViewChromium s;

    public RunnableC0720ea(WebViewChromium webViewChromium) {
        this.s = webViewChromium;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.onResume();
    }
}
