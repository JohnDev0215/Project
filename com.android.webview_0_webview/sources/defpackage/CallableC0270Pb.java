package defpackage;

import com.android.webview.chromium.WebViewDatabaseAdapter;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Pb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0270Pb implements Callable {
    public final /* synthetic */ WebViewDatabaseAdapter a;

    public CallableC0270Pb(WebViewDatabaseAdapter webViewDatabaseAdapter) {
        this.a = webViewDatabaseAdapter;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return Boolean.valueOf(this.a.b.b());
    }
}
