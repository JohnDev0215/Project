package defpackage;

import android.view.DragEvent;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Na, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0233Na implements Callable {
    public final /* synthetic */ DragEvent a;
    public final /* synthetic */ WebViewChromium b;

    public CallableC0233Na(WebViewChromium webViewChromium, DragEvent dragEvent) {
        this.b = webViewChromium;
        this.a = dragEvent;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.b.onDragEvent(this.a));
    }
}
