package defpackage;

import com.android.webview.chromium.WebViewChromium;
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: x9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1751x9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1751x9(WebViewChromium webViewChromium, String str) {
        this.t = webViewChromium;
        this.s = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwContents awContents = this.t.x;
        String str = this.s;
        if (awContents.t(1) || str == null) {
            return;
        }
        awContents.C(str, null);
    }
}
