package defpackage;

import android.os.Bundle;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: u9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC1586u9 implements Callable {
    public final /* synthetic */ Bundle a;
    public final /* synthetic */ WebViewChromium b;

    public CallableC1586u9(WebViewChromium webViewChromium, Bundle bundle) {
        this.b = webViewChromium;
        this.a = bundle;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.b.saveState(this.a);
    }
}
