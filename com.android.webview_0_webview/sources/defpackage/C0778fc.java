package defpackage;

import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import java.util.concurrent.Executor;
import org.chromium.android_webview.AwRenderProcess;

/* JADX INFO: renamed from: fc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0778fc extends V8 {
    public Executor a;
    public WebViewRenderProcessClient b;

    public C0778fc(Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        this.a = executor;
        this.b = webViewRenderProcessClient;
    }

    @Override // defpackage.V8
    public void b(final WebView webView, AwRenderProcess awRenderProcess) {
        final C0611cc c0611ccA = C0611cc.a(awRenderProcess);
        this.a.execute(new Runnable(this, webView, c0611ccA) { // from class: ec
            public final C0778fc s;
            public final WebView t;
            public final WebViewRenderProcess u;

            {
                this.s = this;
                this.t = webView;
                this.u = c0611ccA;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.e(this.t, this.u);
            }
        });
    }

    @Override // defpackage.V8
    public void c(final WebView webView, AwRenderProcess awRenderProcess) {
        final C0611cc c0611ccA = C0611cc.a(awRenderProcess);
        this.a.execute(new Runnable(this, webView, c0611ccA) { // from class: dc
            public final C0778fc s;
            public final WebView t;
            public final WebViewRenderProcess u;

            {
                this.s = this;
                this.t = webView;
                this.u = c0611ccA;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.f(this.t, this.u);
            }
        });
    }

    public WebViewRenderProcessClient d() {
        return this.b;
    }

    public final /* synthetic */ void e(WebView webView, WebViewRenderProcess webViewRenderProcess) {
        this.b.onRenderProcessResponsive(webView, webViewRenderProcess);
    }

    public final /* synthetic */ void f(WebView webView, WebViewRenderProcess webViewRenderProcess) {
        this.b.onRenderProcessUnresponsive(webView, webViewRenderProcess);
    }
}
