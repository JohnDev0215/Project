package defpackage;

import android.view.ViewStructure;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ca, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0036Ca implements Runnable {
    public final /* synthetic */ ViewStructure s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0036Ca(WebViewChromium webViewChromium, ViewStructure viewStructure) {
        this.t = webViewChromium;
        this.s = viewStructure;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.onProvideVirtualStructure(this.s);
    }
}
