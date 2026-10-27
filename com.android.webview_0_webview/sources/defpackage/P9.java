package defpackage;

import android.webkit.WebView;
import com.android.webview.chromium.WebViewChromium;
import org.chromium.android_webview.AwContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class P9 extends AwContents.VisualStateCallback {
    public final /* synthetic */ WebView.VisualStateCallback a;

    public P9(WebViewChromium webViewChromium, WebView.VisualStateCallback visualStateCallback) {
        this.a = visualStateCallback;
    }

    @Override // org.chromium.android_webview.AwContents.VisualStateCallback
    public void a(long j) {
        this.a.onComplete(j);
    }
}
