package defpackage;

import android.graphics.Paint;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: hb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0889hb implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ Paint t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC0889hb(WebViewChromium webViewChromium, int i, Paint paint) {
        this.u = webViewChromium;
        this.s = i;
        this.t = paint;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.setLayerType(this.s, this.t);
    }
}
