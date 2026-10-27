package defpackage;

import android.webkit.WebView;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ra, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1440ra implements Runnable {
    public final /* synthetic */ WebView.PictureListener s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1440ra(WebViewChromium webViewChromium, WebView.PictureListener pictureListener) {
        this.t = webViewChromium;
        this.s = pictureListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.setPictureListener(this.s);
    }
}
