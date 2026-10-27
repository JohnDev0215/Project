package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: wa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC1715wa implements Callable {
    public final /* synthetic */ WebViewChromium a;

    public CallableC1715wa(WebViewChromium webViewChromium) {
        this.a = webViewChromium;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.a.zoomIn());
    }
}
