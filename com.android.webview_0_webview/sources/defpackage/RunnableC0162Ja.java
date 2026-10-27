package defpackage;

import android.graphics.Canvas;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ja, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0162Ja implements Runnable {
    public final /* synthetic */ Canvas s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0162Ja(WebViewChromium webViewChromium, Canvas canvas) {
        this.t = webViewChromium;
        this.s = canvas;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.onDraw(this.s);
    }
}
