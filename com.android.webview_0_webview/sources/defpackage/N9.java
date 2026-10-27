package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class N9 implements Callable {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ WebViewChromium b;

    public N9(WebViewChromium webViewChromium, boolean z) {
        this.b = webViewChromium;
        this.a = z;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.b.pageUp(this.a));
    }
}
