package defpackage;

import android.view.View;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ta, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0341Ta implements Runnable {
    public final /* synthetic */ View s;
    public final /* synthetic */ int t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC0341Ta(WebViewChromium webViewChromium, View view, int i) {
        this.u = webViewChromium;
        this.s = view;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.onVisibilityChanged(this.s, this.t);
    }
}
