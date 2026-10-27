package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: r9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC1421r9 implements Callable {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ WebViewChromium c;

    public CallableC1421r9(WebViewChromium webViewChromium, String str, String str2) {
        this.c = webViewChromium;
        this.a = str;
        this.b = str2;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.c.getHttpAuthUsernamePassword(this.a, this.b);
    }
}
