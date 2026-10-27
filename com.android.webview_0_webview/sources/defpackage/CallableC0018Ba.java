package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Ba, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0018Ba implements Callable {
    public final /* synthetic */ WebViewChromium a;

    public CallableC0018Ba(WebViewChromium webViewChromium) {
        this.a = webViewChromium;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.a.getAccessibilityNodeProvider();
    }
}
