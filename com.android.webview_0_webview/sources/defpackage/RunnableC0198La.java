package defpackage;

import android.content.Intent;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: La, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0198La implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ Intent u;
    public final /* synthetic */ WebViewChromium v;

    public RunnableC0198La(WebViewChromium webViewChromium, int i, int i2, Intent intent) {
        this.v = webViewChromium;
        this.s = i;
        this.t = i2;
        this.u = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.v.onActivityResult(this.s, this.t, this.u);
    }
}
