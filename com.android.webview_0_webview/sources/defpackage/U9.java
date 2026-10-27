package defpackage;

import android.os.Message;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U9 implements Runnable {
    public final /* synthetic */ Message s;
    public final /* synthetic */ WebViewChromium t;

    public U9(WebViewChromium webViewChromium, Message message) {
        this.t = webViewChromium;
        this.s = message;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.requestFocusNodeHref(this.s);
    }
}
