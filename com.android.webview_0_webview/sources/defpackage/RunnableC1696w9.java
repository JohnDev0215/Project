package defpackage;

import com.android.webview.chromium.WebViewChromium;
import java.util.Map;

/* JADX INFO: renamed from: w9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1696w9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ Map t;
    public final /* synthetic */ WebViewChromium u;

    public RunnableC1696w9(WebViewChromium webViewChromium, String str, Map map) {
        this.u = webViewChromium;
        this.s = str;
        this.t = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.x.C(this.s, this.t);
    }
}
