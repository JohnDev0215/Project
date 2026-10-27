package defpackage;

import J.N;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import java.util.concurrent.Callable;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwRenderProcess;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class T8 {
    public static final WebViewClient g = new WebViewClient();
    public final C0070Dq a;
    public final C1551tb b;
    public AwContents c;
    public U8 d;
    public WebViewClient e = g;
    public WebChromeClient f;

    public T8(C0070Dq c0070Dq, C1551tb c1551tb) {
        this.a = c0070Dq;
        this.b = c1551tb;
    }

    public void a(final String str, final String[] strArr, final InterfaceC0016Aq interfaceC0016Aq) {
        if (b()) {
            this.a.a(new Runnable(this, str, strArr, interfaceC0016Aq) { // from class: M8
                public final T8 s;
                public final String t;
                public final String[] u;
                public final InterfaceC0016Aq v;

                {
                    this.s = this;
                    this.t = str;
                    this.u = strArr;
                    this.v = interfaceC0016Aq;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.a(this.t, this.u, this.v);
                }
            });
        } else {
            this.c.e(str, strArr, interfaceC0016Aq);
        }
    }

    public boolean b() {
        boolean z = (this.a.b.a() && ThreadUtils.j()) ? false : true;
        if (z || this.c != null) {
            return z;
        }
        throw new IllegalStateException("AwContents must be created if we are not posting!");
    }

    public MessagePort[] c() {
        this.b.k(true);
        if (b()) {
            return (MessagePort[]) this.a.d(new P8(this));
        }
        if (this.c.t(1)) {
            return null;
        }
        return AppWebMessagePort.f();
    }

    public AwRenderProcess d() {
        this.b.k(true);
        if (b()) {
            return (AwRenderProcess) this.a.d(new Callable(this) { // from class: L8
                public final T8 a;

                {
                    this.a = this;
                }

                @Override // java.util.concurrent.Callable
                public Object call() {
                    return this.a.d();
                }
            });
        }
        AwContents awContents = this.c;
        if (awContents.t(1)) {
            return null;
        }
        return (AwRenderProcess) N.Mj2HEglc(awContents.s, awContents);
    }

    public V8 e() {
        this.b.k(true);
        return b() ? (V8) this.a.d(new S8(this)) : this.d.j;
    }

    public void f(long j, AwContents.VisualStateCallback visualStateCallback) {
        if (b()) {
            this.a.a(new O8(this, j, visualStateCallback));
            return;
        }
        AwContents awContents = this.c;
        if (awContents.t(0)) {
            throw new IllegalStateException("insertVisualStateCallback cannot be called after the WebView has been destroyed");
        }
        if (visualStateCallback == null) {
            throw new IllegalArgumentException("VisualStateCallback shouldn't be null");
        }
        N.ME4bwr8X(awContents.s, awContents, j, visualStateCallback);
    }

    public void g(String str, String str2, MessagePort[] messagePortArr) {
        RenderFrameHost renderFrameHostM;
        if (b()) {
            this.a.a(new Q8(this, str, str2, messagePortArr));
            return;
        }
        AwContents awContents = this.c;
        if (awContents.t(1) || (renderFrameHostM = awContents.A.M()) == null || !renderFrameHostM.b()) {
            return;
        }
        awContents.A.p(str, null, str2, messagePortArr);
    }

    public void h(final String str) {
        if (b()) {
            this.a.a(new Runnable(this, str) { // from class: N8
                public final T8 s;
                public final String t;

                {
                    this.s = this;
                    this.t = str;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.h(this.t);
                }
            });
        } else {
            AwContents awContents = this.c;
            N.MJJ_9Fzq(awContents.s, awContents, str);
        }
    }

    public void i(V8 v8) {
        if (b()) {
            this.a.a(new R8(this, v8));
        } else {
            this.d.j = v8;
        }
    }
}
