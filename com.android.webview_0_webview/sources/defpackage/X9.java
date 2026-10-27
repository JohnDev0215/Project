package defpackage;

import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class X9 implements Runnable {
    public final /* synthetic */ RuntimeException s;

    public X9(WebViewChromium webViewChromium, RuntimeException runtimeException) {
        this.s = runtimeException;
    }

    @Override // java.lang.Runnable
    public void run() {
        throw this.s;
    }
}
