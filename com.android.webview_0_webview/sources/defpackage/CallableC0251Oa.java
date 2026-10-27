package defpackage;

import android.view.KeyEvent;
import com.android.webview.chromium.WebViewChromium;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Oa, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0251Oa implements Callable {
    public final /* synthetic */ int a;
    public final /* synthetic */ int b;
    public final /* synthetic */ KeyEvent c;
    public final /* synthetic */ WebViewChromium d;

    public CallableC0251Oa(WebViewChromium webViewChromium, int i, int i2, KeyEvent keyEvent) {
        this.d = webViewChromium;
        this.a = i;
        this.b = i2;
        this.c = keyEvent;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.d.onKeyMultiple(this.a, this.b, this.c));
    }
}
