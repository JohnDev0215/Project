package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class K9 implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ WebViewChromium b;

    public K9(WebViewChromium webViewChromium, int i) {
        this.b = webViewChromium;
        this.a = i;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.b.canGoBackOrForward(this.a));
    }
}
