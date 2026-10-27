package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class T9 implements Callable {
    public final /* synthetic */ WebViewChromium a;

    public T9(WebViewChromium webViewChromium) {
        this.a = webViewChromium;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.a.getHitTestResult();
    }
}
