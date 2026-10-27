package defpackage;

import android.view.ViewStructure;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: za, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1880za implements Runnable {
    public final /* synthetic */ ViewStructure s;
    public final /* synthetic */ int t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC1880za(WebViewChromium webViewChromium, ViewStructure viewStructure, int i) {
        this.u = webViewChromium;
        this.s = viewStructure;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.onProvideAutofillVirtualStructure(this.s, this.t);
    }
}
