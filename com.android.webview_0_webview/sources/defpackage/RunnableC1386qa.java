package defpackage;

import android.os.Message;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: qa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1386qa implements Runnable {
    public final /* synthetic */ Message s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1386qa(WebViewChromium webViewChromium, Message message) {
        this.t = webViewChromium;
        this.s = message;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.documentHasImages(this.s);
    }
}
