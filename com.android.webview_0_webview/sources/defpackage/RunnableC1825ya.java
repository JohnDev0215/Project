package defpackage;

import android.util.SparseArray;
import com.android.webview.chromium.WebViewChromium;

/* JADX INFO: renamed from: ya, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1825ya implements Runnable {
    public final /* synthetic */ SparseArray s;
    public final /* synthetic */ WebViewChromium t;

    public RunnableC1825ya(WebViewChromium webViewChromium, SparseArray sparseArray) {
        this.t = webViewChromium;
        this.s = sparseArray;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.autofill(this.s);
    }
}
