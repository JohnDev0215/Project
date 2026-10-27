package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Picture;
import android.os.Handler;
import android.os.Trace;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.JsDialogHelper;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import java.util.WeakHashMap;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: Ob, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0252Ob extends U8 {
    public WebChromeClient k;
    public WebView.FindListener l;
    public WebView.PictureListener m;
    public boolean n;
    public DownloadListener o;
    public Handler p;
    public WeakHashMap q;

    public C0252Ob(WebView webView, Context context, InterfaceC0555bc interfaceC0555bc) {
        super(webView, interfaceC0555bc, context);
        Trace.beginSection("WebViewContentsClientAdapter.constructor");
        try {
            this.p = new HandlerC0091Fb(this);
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    @Override // defpackage.AbstractC0511ao
    public void a(String str) {
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onLoadResource", null);
            this.h.onLoadResource(this.e, str);
            AbstractC0229Mo.a(6);
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onLoadResource");
        }
    }

    @Override // defpackage.AbstractC0511ao
    public void b(String str) {
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onPageFinished", null);
            this.h.onPageFinished(this.e, str);
            AbstractC0229Mo.a(5);
            if (this.m != null) {
                PostTask.a(AbstractC0538bE.a, new Runnable(this) { // from class: Cb
                    public final C0252Ob s;

                    {
                        this.s = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        C0252Ob c0252Ob = this.s;
                        WebView.PictureListener pictureListener = c0252Ob.m;
                        if (pictureListener != null) {
                            pictureListener.onNewPicture(c0252Ob.e, c0252Ob.n ? null : new Picture());
                        }
                    }
                }, 100L);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onPageFinished");
        }
    }

    @Override // defpackage.AbstractC0511ao
    public void c(String str) {
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onPageStarted", null);
            this.h.onPageStarted(this.e, str, this.e.getFavicon());
            AbstractC0229Mo.a(4);
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onPageStarted");
        }
    }

    @Override // defpackage.AbstractC0511ao
    public void d(int i) {
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onProgressChanged", null);
            if (this.k != null) {
                this.k.onProgressChanged(this.e, i);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onProgressChanged");
        }
    }

    @Override // defpackage.AbstractC0511ao
    public void e(String str) {
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onReceivedTitle", null);
            if (this.k != null) {
                this.k.onReceivedTitle(this.e, str);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedTitle");
        }
    }

    public final boolean h(JsPromptResult jsPromptResult, int i, String str, String str2, String str3) {
        Activity activityA = Is.a(this.g);
        if (activityA == null) {
            Ts.f("WebViewCallback", "Unable to create JsDialog without an Activity", new Object[0]);
            return false;
        }
        try {
            new JsDialogHelper(jsPromptResult, i, str, str2, str3).showDialog(activityA);
            return true;
        } catch (WindowManager.BadTokenException unused) {
            Ts.f("WebViewCallback", "Unable to create JsDialog. Has this WebView outlived the Activity it was created with?", new Object[0]);
            return false;
        }
    }
}
