package defpackage;

import android.graphics.Rect;
import android.view.View;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: fb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0777fb implements Callable {
    public final /* synthetic */ View a;
    public final /* synthetic */ Rect b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ WebViewChromium d;

    public CallableC0777fb(WebViewChromium webViewChromium, View view, Rect rect, boolean z) {
        this.d = webViewChromium;
        this.a = view;
        this.b = rect;
        this.c = z;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.d.requestChildRectangleOnScreen(this.a, this.b, this.c));
    }
}
