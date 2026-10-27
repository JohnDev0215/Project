package defpackage;

import android.content.res.Configuration;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ma, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0215Ma implements Runnable {
    public final /* synthetic */ Configuration s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0215Ma(WebViewChromium webViewChromium, Configuration configuration) {
        this.t = webViewChromium;
        this.s = configuration;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.onConfigurationChanged(this.s);
    }
}
