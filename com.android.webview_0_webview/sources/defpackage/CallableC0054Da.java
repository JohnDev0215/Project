package defpackage;

import android.os.Bundle;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Da, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0054Da implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Bundle b;
    public final /* synthetic */ WebViewChromium c;

    public CallableC0054Da(WebViewChromium webViewChromium, int i, Bundle bundle) {
        this.c = webViewChromium;
        this.a = i;
        this.b = bundle;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.c.performAccessibilityAction(this.a, this.b));
    }
}
