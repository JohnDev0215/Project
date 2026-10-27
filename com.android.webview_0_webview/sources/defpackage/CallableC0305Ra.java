package defpackage;

import android.view.KeyEvent;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Ra, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0305Ra implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ KeyEvent b;
    public final /* synthetic */ WebViewChromium c;

    public CallableC0305Ra(WebViewChromium webViewChromium, int i, KeyEvent keyEvent) {
        this.c = webViewChromium;
        this.a = i;
        this.b = keyEvent;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.c.onKeyUp(this.a, this.b));
    }
}
