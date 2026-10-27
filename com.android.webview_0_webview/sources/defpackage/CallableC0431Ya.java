package defpackage;

import android.view.KeyEvent;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Ya, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0431Ya implements Callable {
    public final /* synthetic */ KeyEvent a;
    public final /* synthetic */ WebViewChromium b;

    public CallableC0431Ya(WebViewChromium webViewChromium, KeyEvent keyEvent) {
        this.b = webViewChromium;
        this.a = keyEvent;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.b.dispatchKeyEvent(this.a));
    }
}
