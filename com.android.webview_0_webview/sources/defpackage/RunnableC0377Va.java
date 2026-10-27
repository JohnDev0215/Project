package defpackage;

import android.graphics.Rect;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Va, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0377Va implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ int t;
    public final /* synthetic */ Rect u;
    public final /* synthetic */ WebViewChromium v;

    public RunnableC0377Va(WebViewChromium webViewChromium, boolean z, int i, Rect rect) {
        this.v = webViewChromium;
        this.s = z;
        this.t = i;
        this.u = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.v.onFocusChanged(this.s, this.t, this.u);
    }
}
