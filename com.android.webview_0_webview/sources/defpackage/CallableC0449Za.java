package defpackage;

import android.view.MotionEvent;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Za, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0449Za implements Callable {
    public final /* synthetic */ MotionEvent a;
    public final /* synthetic */ WebViewChromium b;

    public CallableC0449Za(WebViewChromium webViewChromium, MotionEvent motionEvent) {
        this.b = webViewChromium;
        this.a = motionEvent;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.b.onTouchEvent(this.a));
    }
}
