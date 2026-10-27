package defpackage;

import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import java.util.concurrent.Executor;
import org.chromium.android_webview.AwRenderProcess;

/* JADX INFO: renamed from: q8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1366q8 {
    public static WebViewRenderProcess a(AwRenderProcess awRenderProcess) {
        return C0611cc.a(awRenderProcess);
    }

    public static WebViewRenderProcessClient b(V8 v8) {
        return ((C0778fc) v8).d();
    }

    public static void c(T8 t8, Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        t8.i(new C0778fc(executor, webViewRenderProcessClient));
    }
}
