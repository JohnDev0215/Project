package defpackage;

import android.view.ViewGroup;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: Ka, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0180Ka implements Runnable {
    public final /* synthetic */ ViewGroup.LayoutParams s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC0180Ka(WebViewChromium webViewChromium, ViewGroup.LayoutParams layoutParams) {
        this.t = webViewChromium;
        this.s = layoutParams;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.x.P();
    }
}
