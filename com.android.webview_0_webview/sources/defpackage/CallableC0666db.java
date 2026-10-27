package defpackage;

import android.graphics.Rect;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: db, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0666db implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Rect b;
    public final /* synthetic */ WebViewChromium c;

    public CallableC0666db(WebViewChromium webViewChromium, int i, Rect rect) {
        this.c = webViewChromium;
        this.a = i;
        this.b = rect;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.c.requestFocus(this.a, this.b));
    }
}
