package com.android.webview.chromium;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.webkit.DownloadListener;
import android.webkit.FindActionModeCallback;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebMessage;
import android.webkit.WebMessagePort;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewProvider;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import android.widget.TextView;
import defpackage.A9;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0532b8;
import defpackage.AbstractC0538bE;
import defpackage.AbstractC0566bn;
import defpackage.AbstractC1299ox;
import defpackage.AbstractC1402qq;
import defpackage.B9;
import defpackage.C0070Dq;
import defpackage.C0121Gn;
import defpackage.C0228Mn;
import defpackage.C0252Ob;
import defpackage.C0391Vo;
import defpackage.C0463Zo;
import defpackage.C0533b9;
import defpackage.C0778fc;
import defpackage.C1167mb;
import defpackage.C1222nb;
import defpackage.C1366q8;
import defpackage.C1893zn;
import defpackage.C9;
import defpackage.CallableC0000Aa;
import defpackage.CallableC0018Ba;
import defpackage.CallableC0054Da;
import defpackage.CallableC0072Ea;
import defpackage.CallableC0233Na;
import defpackage.CallableC0251Oa;
import defpackage.CallableC0269Pa;
import defpackage.CallableC0287Qa;
import defpackage.CallableC0305Ra;
import defpackage.CallableC0431Ya;
import defpackage.CallableC0449Za;
import defpackage.CallableC0497aa;
import defpackage.CallableC0498ab;
import defpackage.CallableC0554bb;
import defpackage.CallableC0610cb;
import defpackage.CallableC0666db;
import defpackage.CallableC0776fa;
import defpackage.CallableC0777fb;
import defpackage.CallableC0945ib;
import defpackage.CallableC1000jb;
import defpackage.CallableC1056kb;
import defpackage.CallableC1111la;
import defpackage.CallableC1202n9;
import defpackage.CallableC1257o9;
import defpackage.CallableC1312p9;
import defpackage.CallableC1421r9;
import defpackage.CallableC1586u9;
import defpackage.CallableC1641v9;
import defpackage.CallableC1715wa;
import defpackage.CallableC1770xa;
import defpackage.D9;
import defpackage.E9;
import defpackage.F9;
import defpackage.G9;
import defpackage.H9;
import defpackage.I9;
import defpackage.It;
import defpackage.J9;
import defpackage.JM;
import defpackage.K9;
import defpackage.L9;
import defpackage.Lt;
import defpackage.M9;
import defpackage.MM;
import defpackage.N9;
import defpackage.O9;
import defpackage.P9;
import defpackage.Q9;
import defpackage.R9;
import defpackage.RunnableC0036Ca;
import defpackage.RunnableC0090Fa;
import defpackage.RunnableC0108Ga;
import defpackage.RunnableC0126Ha;
import defpackage.RunnableC0144Ia;
import defpackage.RunnableC0162Ja;
import defpackage.RunnableC0180Ka;
import defpackage.RunnableC0198La;
import defpackage.RunnableC0215Ma;
import defpackage.RunnableC0323Sa;
import defpackage.RunnableC0341Ta;
import defpackage.RunnableC0359Ua;
import defpackage.RunnableC0377Va;
import defpackage.RunnableC0395Wa;
import defpackage.RunnableC0413Xa;
import defpackage.RunnableC0553ba;
import defpackage.RunnableC0609ca;
import defpackage.RunnableC0665da;
import defpackage.RunnableC0720ea;
import defpackage.RunnableC0721eb;
import defpackage.RunnableC0832ga;
import defpackage.RunnableC0833gb;
import defpackage.RunnableC0888ha;
import defpackage.RunnableC0889hb;
import defpackage.RunnableC0944ia;
import defpackage.RunnableC0999ja;
import defpackage.RunnableC1055ka;
import defpackage.RunnableC1112lb;
import defpackage.RunnableC1166ma;
import defpackage.RunnableC1221na;
import defpackage.RunnableC1276oa;
import defpackage.RunnableC1331pa;
import defpackage.RunnableC1367q9;
import defpackage.RunnableC1386qa;
import defpackage.RunnableC1440ra;
import defpackage.RunnableC1476s9;
import defpackage.RunnableC1495sa;
import defpackage.RunnableC1531t9;
import defpackage.RunnableC1550ta;
import defpackage.RunnableC1605ua;
import defpackage.RunnableC1660va;
import defpackage.RunnableC1696w9;
import defpackage.RunnableC1751x9;
import defpackage.RunnableC1806y9;
import defpackage.RunnableC1825ya;
import defpackage.RunnableC1861z9;
import defpackage.RunnableC1880za;
import defpackage.S9;
import defpackage.Su;
import defpackage.T8;
import defpackage.T9;
import defpackage.Ts;
import defpackage.U9;
import defpackage.V8;
import defpackage.V9;
import defpackage.VD;
import defpackage.W9;
import defpackage.Ww;
import defpackage.X8;
import defpackage.X9;
import defpackage.Y9;
import defpackage.Yw;
import defpackage.Z9;
import java.io.BufferedWriter;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import org.chromium.android_webview.AwAutofillClient;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsStatics;
import org.chromium.android_webview.AwPdfExporter;
import org.chromium.android_webview.AwSettings;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.components.autofill.AutofillProvider;
import org.chromium.content.browser.JavascriptInjectorImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.SmartClipProvider;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.support_lib_boundary.WebViewClientBoundaryInterface;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewChromium implements WebViewProvider, WebViewProvider.ScrollDelegate, WebViewProvider.ViewDelegate, SmartClipProvider {
    public static final String D = WebViewChromium.class.getSimpleName();
    public static boolean E;
    public WebViewChromiumFactoryProvider A;
    public final T8 B;
    public final boolean C;
    public WebView s;
    public WebView.PrivateAccess t;
    public C0252Ob u;
    public Context v;
    public ContentSettingsAdapter w;
    public AwContents x;
    public final WebView.HitTestResult y;
    public final int z;

    public WebViewChromium(WebViewChromiumFactoryProvider webViewChromiumFactoryProvider, WebView webView, WebView.PrivateAccess privateAccess, boolean z) {
        Trace.beginSection("WebViewChromium.constructor");
        try {
            WebViewChromiumFactoryProvider.a(webView.getContext());
            this.s = webView;
            this.t = privateAccess;
            this.y = new WebView.HitTestResult();
            Context contextA = AbstractC1299ox.a(this.s.getContext());
            this.v = contextA;
            this.z = contextA.getApplicationInfo().targetSdkVersion;
            this.A = webViewChromiumFactoryProvider;
            this.C = z;
            webViewChromiumFactoryProvider.d.h(this.s.getContext());
            this.B = new T8(this.A.a, this.A.b);
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

    public static void a(WebViewChromium webViewChromium) {
        Objects.requireNonNull(webViewChromium);
        Trace.beginSection("WebViewChromium.initForReal");
        try {
            AwContentsStatics.c = E || webViewChromium.z < 21;
            webViewChromium.x = new AwContents(webViewChromium.A.d(), webViewChromium.s, webViewChromium.v, new C1167mb(webViewChromium, null), new C1222nb(webViewChromium, null), webViewChromium.u, webViewChromium.w.a, new M9(webViewChromium));
            if (webViewChromium.z >= 19) {
                N.Mbm7UXr0();
            }
            if (webViewChromium.z < 21) {
                AwContents awContents = webViewChromium.x;
                if (!awContents.t(1)) {
                    JavascriptInjectorImpl javascriptInjectorImpl = (JavascriptInjectorImpl) awContents.o();
                    long j = javascriptInjectorImpl.u;
                    if (j != 0) {
                        N.M9VsdpoI(j, javascriptInjectorImpl, false);
                    }
                }
            }
            webViewChromium.x.u0.setLayerType(webViewChromium.s.getLayerType(), null);
            T8 t8 = webViewChromium.B;
            AwContents awContents2 = webViewChromium.x;
            if (t8.c != null) {
                throw new RuntimeException("Cannot create multiple AwContents for the same SharedWebViewChromium");
            }
            t8.c = awContents2;
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

    public static void d(WebView webView, WebView webView2) {
        ((WebViewChromium) webView.getWebViewProvider()).x.Y(webView2 == null ? null : ((WebViewChromium) webView2.getWebViewProvider()).x);
    }

    public static void f(int i) {
        It.d("WebView.ApiCall", i, 78);
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (b()) {
            this.A.a.a(new RunnableC1495sa(this, obj, str));
            return;
        }
        f(0);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return;
        }
        ((JavascriptInjectorImpl) awContents.o()).a(obj, str, awContents.x >= 17 ? JavascriptInterface.class : null);
    }

    public void autofill(SparseArray sparseArray) {
        this.A.h(false);
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            RunnableC1825ya runnableC1825ya = new RunnableC1825ya(this, sparseArray);
            C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
            Objects.requireNonNull(c0070Dq);
            c0070Dq.c(new FutureTask(runnableC1825ya, null));
        }
        f(1);
        AutofillProvider autofillProvider = this.x.z0;
        if (autofillProvider != null) {
            autofillProvider.b(sparseArray);
        }
    }

    public boolean b() {
        return this.B.b();
    }

    public final void c() {
        if (ThreadUtils.j()) {
            return;
        }
        PostTask.a(AbstractC0538bE.a, new X9(this, new IllegalStateException("Calling View methods on another thread than the UI thread.")), 0L);
        throw new IllegalStateException("Calling View methods on another thread than the UI thread.");
    }

    public boolean canGoBack() {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new G9(this)))).booleanValue();
        }
        f(2);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        return awContents.D.canGoBack();
    }

    public boolean canGoBackOrForward(int i) {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new K9(this, i)))).booleanValue();
        }
        f(3);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        return awContents.D.b(i);
    }

    public boolean canGoForward() {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new I9(this)))).booleanValue();
        }
        f(4);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        return awContents.D.canGoForward();
    }

    public boolean canZoomIn() {
        f(5);
        if (b()) {
            return false;
        }
        return this.x.f();
    }

    public boolean canZoomOut() {
        f(6);
        if (b()) {
            return false;
        }
        return this.x.g();
    }

    public Picture capturePicture() {
        this.A.h(true);
        if (!b()) {
            f(7);
            return this.x.h();
        }
        return (Picture) this.A.a.c(new FutureTask(new R9(this)));
    }

    public void clearCache(boolean z) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0832ga(this, z));
        } else {
            f(8);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            N.MhT$Vsgm(awContents.s, awContents, z);
        }
    }

    public void clearFormData() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0888ha(this));
        } else {
            f(9);
            AwAutofillClient awAutofillClient = this.x.s0;
            if (awAutofillClient != null) {
                awAutofillClient.hideAutofillPopup();
            }
        }
    }

    public void clearHistory() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0999ja(this));
        } else {
            f(10);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.D.clearHistory();
        }
    }

    public void clearMatches() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1331pa(this));
        } else {
            f(11);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            N.MFtcGkhm(awContents.s, awContents);
        }
    }

    public void clearSslPreferences() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1055ka(this));
        } else {
            f(12);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.D.clearSslPreferences();
        }
    }

    public void clearView() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new Q9(this));
        } else {
            f(13);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            N.MsURZ4Xi(awContents.s, awContents);
        }
    }

    public int computeHorizontalScrollOffset() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.computeHorizontalScrollOffset();
        }
        return ((Integer) this.A.a.c(new FutureTask(new CallableC1056kb(this)))).intValue();
    }

    public int computeHorizontalScrollRange() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.computeHorizontalScrollRange();
        }
        return ((Integer) this.A.a.c(new FutureTask(new CallableC1000jb(this)))).intValue();
    }

    public void computeScroll() {
        this.A.h(false);
        if (!b()) {
            this.x.u0.computeScroll();
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        RunnableC1367q9 runnableC1367q9 = new RunnableC1367q9(this);
        C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
        Objects.requireNonNull(c0070Dq);
        c0070Dq.c(new FutureTask(runnableC1367q9, null));
    }

    public int computeVerticalScrollExtent() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.computeVerticalScrollExtent();
        }
        return ((Integer) this.A.a.c(new FutureTask(new CallableC1312p9(this)))).intValue();
    }

    public int computeVerticalScrollOffset() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.computeVerticalScrollOffset();
        }
        return ((Integer) this.A.a.c(new FutureTask(new CallableC1257o9(this)))).intValue();
    }

    public int computeVerticalScrollRange() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.computeVerticalScrollRange();
        }
        return ((Integer) this.A.a.c(new FutureTask(new CallableC1202n9(this)))).intValue();
    }

    public WebBackForwardList copyBackForwardList() {
        this.A.h(true);
        if (b()) {
            return (WebBackForwardList) this.A.a.c(new FutureTask(new CallableC1111la(this)));
        }
        f(14);
        AwContents awContents = this.x;
        VD vdG = awContents.t(1) ? null : awContents.D.g();
        if (vdG == null) {
            vdG = new VD();
        }
        return new X8(vdG);
    }

    public PrintDocumentAdapter createPrintDocumentAdapter(String str) {
        AwPdfExporter awPdfExporter;
        f(15);
        c();
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            awPdfExporter = null;
        } else {
            if (awContents.t0 == null) {
                AwPdfExporter awPdfExporter2 = new AwPdfExporter(awContents.u);
                awContents.t0 = awPdfExporter2;
                N.MND9REwd(awContents.s, awContents, awPdfExporter2);
            }
            awPdfExporter = awContents.t0;
        }
        return new C0391Vo(awPdfExporter, str);
    }

    public WebMessagePort[] createWebMessageChannel() {
        f(16);
        return C0533b9.a(this.B.c());
    }

    public void destroy() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1476s9(this));
            return;
        }
        setWebChromeClient(null);
        setWebViewClient(null);
        C0252Ob c0252Ob = this.u;
        c0252Ob.m = null;
        c0252Ob.n = true;
        c0252Ob.l = null;
        c0252Ob.o = null;
        this.x.i();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.dispatchKeyEvent(keyEvent);
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0431Ya(this, keyEvent)))).booleanValue();
    }

    public void documentHasImages(Message message) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1386qa(this, message));
        } else {
            f(17);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            N.M74pgHWN(awContents.s, awContents, message);
        }
    }

    public void dumpViewHierarchyWithProperties(BufferedWriter bufferedWriter, int i) {
    }

    public void evaluateJavaScript(String str, ValueCallback valueCallback) {
        if (this.C && b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new C9(this, str, valueCallback));
        } else {
            f(19);
            c();
            this.x.k(str, AbstractC0532b8.a(valueCallback));
        }
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void extractSmartClipData(int i, int i2, int i3, int i4) {
        f(20);
        c();
        this.x.extractSmartClipData(i, i2, i3, i4);
    }

    public int findAll(String str) {
        findAllAsync(str);
        return 0;
    }

    public void findAllAsync(String str) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1221na(this, str));
        } else {
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            if (str == null) {
                throw new IllegalArgumentException("Search string shouldn't be null");
            }
            N.MkkpS1L9(awContents.s, awContents, str);
        }
    }

    public View findFocus(View view) {
        return view;
    }

    public View findHierarchyView(String str, int i) {
        return null;
    }

    public void findNext(boolean z) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1166ma(this, z));
        } else {
            f(21);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            N.M21lr0OA(awContents.s, awContents, z);
        }
    }

    public void flingScroll(int i, int i2) {
        if (b()) {
            this.A.a.a(new RunnableC1660va(this, i, i2));
        } else {
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.A.F().j(SystemClock.uptimeMillis(), -i, -i2, false, true);
        }
    }

    public void freeMemory() {
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.getAccessibilityNodeProvider();
        }
        return (AccessibilityNodeProvider) this.A.a.c(new FutureTask(new CallableC0018Ba(this)));
    }

    public SslCertificate getCertificate() {
        this.A.h(true);
        if (b()) {
            return (SslCertificate) this.A.a.c(new FutureTask(new CallableC0498ab(this)));
        }
        f(22);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return null;
        }
        return AbstractC1402qq.a(N.MJl6bbdC(awContents.s, awContents));
    }

    public int getContentHeight() {
        f(23);
        AwContents awContents = this.x;
        if (awContents == null || awContents.t(1)) {
            return 0;
        }
        return (int) Math.ceil(awContents.r0);
    }

    public int getContentWidth() {
        f(24);
        AwContents awContents = this.x;
        if (awContents == null || awContents.t(1)) {
            return 0;
        }
        return (int) Math.ceil(awContents.q0);
    }

    public Bitmap getFavicon() {
        this.A.h(true);
        if (b()) {
            return (Bitmap) this.A.a.c(new FutureTask(new CallableC0497aa(this)));
        }
        f(25);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return null;
        }
        return awContents.e0;
    }

    public Handler getHandler(Handler handler) {
        return handler;
    }

    public WebView.HitTestResult getHitTestResult() {
        C0228Mn c0228Mn;
        this.A.h(true);
        if (b()) {
            return (WebView.HitTestResult) this.A.a.c(new FutureTask(new T9(this)));
        }
        f(26);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            c0228Mn = null;
        } else {
            N.MHBuiDmd(awContents.s, awContents);
            c0228Mn = awContents.i0;
        }
        this.y.setType(c0228Mn.a);
        this.y.setExtra(c0228Mn.b);
        return this.y;
    }

    public String[] getHttpAuthUsernamePassword(String str, String str2) {
        this.A.h(true);
        if (!b()) {
            f(27);
            return ((WebViewDatabaseAdapter) this.A.getWebViewDatabase(this.v)).getHttpAuthUsernamePassword(str, str2);
        }
        return (String[]) this.A.a.c(new FutureTask(new CallableC1421r9(this, str, str2)));
    }

    public String getOriginalUrl() {
        this.A.h(true);
        if (!b()) {
            f(28);
            return this.x.p();
        }
        return (String) this.A.a.c(new FutureTask(new Y9(this)));
    }

    public int getProgress() {
        f(29);
        AwContents awContents = this.x;
        if (awContents == null) {
            return 100;
        }
        if (awContents.t(1)) {
            return 0;
        }
        if (awContents.A.c()) {
            return Math.round(awContents.A.s() * 100.0f);
        }
        return 100;
    }

    public boolean getRendererPriorityWaivedWhenNotVisible() {
        return this.x.d0;
    }

    public int getRendererRequestedPriority() {
        int i = this.x.c0;
        if (i != 0) {
            return i != 1 ? 2 : 1;
        }
        return 0;
    }

    public float getScale() {
        f(30);
        this.A.h(true);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return 1.0f;
        }
        return awContents.m() * awContents.n0;
    }

    public WebViewProvider.ScrollDelegate getScrollDelegate() {
        return this;
    }

    public WebSettings getSettings() {
        f(31);
        return this.w;
    }

    public TextClassifier getTextClassifier() {
        f(32);
        return this.x.q();
    }

    public String getTitle() {
        this.A.h(true);
        if (b()) {
            return (String) this.A.a.c(new FutureTask(new Z9(this)));
        }
        f(33);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return null;
        }
        return awContents.A.getTitle();
    }

    public String getTouchIconUrl() {
        return null;
    }

    public String getUrl() {
        GURL gurlR;
        this.A.h(true);
        if (b()) {
            return (String) this.A.a.c(new FutureTask(new W9(this)));
        }
        f(34);
        AwContents awContents = this.x;
        if (awContents.t(1) || (gurlR = awContents.A.r()) == null || gurlR.c().trim().isEmpty()) {
            gurlR = null;
        }
        if (gurlR == null) {
            return null;
        }
        return gurlR.c();
    }

    public WebViewProvider.ViewDelegate getViewDelegate() {
        return this;
    }

    public int getVisibleTitleHeight() {
        return 0;
    }

    public WebChromeClient getWebChromeClient() {
        f(35);
        return this.B.f;
    }

    public WebViewClient getWebViewClient() {
        f(36);
        return this.B.e;
    }

    public WebViewRenderProcess getWebViewRenderProcess() {
        return C1366q8.a(this.B.d());
    }

    public WebViewRenderProcessClient getWebViewRenderProcessClient() {
        V8 v8E = this.B.e();
        if (v8E == null || !(v8E instanceof C0778fc)) {
            return null;
        }
        return C1366q8.b(v8E);
    }

    public View getZoomControls() {
        boolean z;
        this.A.h(false);
        if (b()) {
            return null;
        }
        Log.w(D, "WebView doesn't support getZoomControls");
        AwSettings awSettings = this.x.S;
        synchronized (awSettings.f) {
            z = awSettings.i0;
        }
        if (z) {
            return new View(this.v);
        }
        return null;
    }

    public void goBack() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new H9(this));
        } else {
            f(37);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.D.goBack();
        }
    }

    public void goBackOrForward(int i) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new L9(this, i));
        } else {
            f(38);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.D.a(i);
        }
    }

    public void goForward() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new J9(this));
        } else {
            f(39);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.D.goForward();
        }
    }

    public void init(Map map, boolean z) {
        String str = D;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean z2 = !this.A.b.o;
        Trace.beginSection("WebViewChromium.init");
        if (z) {
            try {
                this.A.h(true);
                if (this.z >= 19) {
                    throw new IllegalArgumentException("Private browsing is not supported in WebView.");
                }
                Log.w(str, "Private browsing is not supported in WebView.");
                TextView textView = new TextView(this.v);
                textView.setText(this.v.getString(AbstractC0566bn.i2));
                this.s.addView(textView);
            } finally {
            }
        }
        if (this.z >= 18) {
            this.A.h(false);
            c();
        } else if (!this.A.b.o && Looper.myLooper() == Looper.getMainLooper()) {
            this.A.h(true);
        }
        boolean z3 = this.z < 16;
        boolean z4 = this.z < 19;
        boolean z5 = this.z <= 23;
        boolean z6 = this.z <= 23;
        boolean z7 = this.z <= 23;
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        WebView webView = this.s;
        Context context = this.v;
        Objects.requireNonNull(webViewChromiumFactoryProvider);
        Trace.beginSection("WebViewChromiumFactoryProvider.insideCreateWebViewContentsClientAdapter");
        try {
            C0252Ob c0252Ob = new C0252Ob(webView, context, webViewChromiumFactoryProvider.d);
            Trace.endSection();
            this.u = c0252Ob;
            Trace.beginSection("WebViewChromium.ContentSettingsAdapter");
            try {
                WebViewChromiumFactoryProvider webViewChromiumFactoryProvider2 = this.A;
                AwSettings awSettings = new AwSettings(this.v, z3, z4, z5, z6, z7);
                Objects.requireNonNull(webViewChromiumFactoryProvider2);
                this.w = new ContentSettingsAdapter(awSettings);
                Trace.endSection();
                if (this.z < 21) {
                    this.w.setMixedContentMode(0);
                    this.w.setAcceptThirdPartyCookies(true);
                    AwSettings awSettings2 = this.w.a;
                    synchronized (awSettings2.f) {
                        if (!awSettings2.F) {
                            awSettings2.F = true;
                            awSettings2.m0.b();
                        }
                    }
                }
                if (this.z >= 28) {
                    AwSettings awSettings3 = this.w.a;
                    synchronized (awSettings3.f) {
                        if (!awSettings3.O) {
                            awSettings3.O = true;
                            awSettings3.m0.b();
                        }
                    }
                    AwSettings awSettings4 = this.w.a;
                    synchronized (awSettings4.f) {
                        if (!awSettings4.P) {
                            awSettings4.P = true;
                            awSettings4.m0.b();
                        }
                    }
                }
                if (this.C) {
                    try {
                        Field declaredField = Class.forName("android.webkit.WebView").getDeclaredField("sEnforceThreadChecking");
                        declaredField.setAccessible(true);
                        declaredField.setBoolean(null, false);
                        declaredField.setAccessible(false);
                    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException unused) {
                        Log.w(str, "Failed to disable thread checking.");
                    }
                }
                this.B.d = this.u;
                this.A.a.a(new B9(this, z));
                Trace.endSection();
                if (this.A.b.o) {
                    if (z2) {
                        It.e("Android.WebView.Startup.CreationTime.Stage2.ProviderInit.Cold", SystemClock.elapsedRealtime() - jElapsedRealtime);
                    } else {
                        It.e("Android.WebView.Startup.CreationTime.Stage2.ProviderInit.Warm", SystemClock.elapsedRealtime() - jElapsedRealtime);
                    }
                }
            } finally {
            }
        } finally {
        }
    }

    public void insertVisualStateCallback(long j, WebView.VisualStateCallback visualStateCallback) {
        f(40);
        this.B.f(j, visualStateCallback == null ? null : new P9(this, visualStateCallback));
    }

    public void invokeZoomPicker() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new S9(this));
        } else {
            f(41);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.O.b();
        }
    }

    public boolean isPaused() {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new CallableC0776fa(this)))).booleanValue();
        }
        f(42);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        return awContents.V;
    }

    public boolean isPrivateBrowsingEnabled() {
        f(43);
        return false;
    }

    public void loadData(String str, String str2, String str3) {
        this.A.h(true);
        if (!b()) {
            f(44);
            this.x.A(str, str2, str3);
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1861z9(this, str, str2, str3));
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.A.h(true);
        if (!b()) {
            f(45);
            this.x.B(str, str2, str3, str4, str5);
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new A9(this, str, str2, str3, str4, str5));
        }
    }

    public void loadUrl(String str, Map map) {
        this.A.h(true);
        if (!b()) {
            this.x.C(str, map);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC1696w9(this, str, map));
    }

    public void notifyFindDialogDismissed() {
        if (!b()) {
            f(46);
            clearMatches();
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1276oa(this));
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (b()) {
            this.A.a.a(new RunnableC0198La(this, i, i2, intent));
            return;
        }
        AwContents awContents = this.x;
        if (awContents.t(0)) {
            return;
        }
        if (i == 100) {
            SelectionPopupControllerImpl.t(awContents.A).H(i2, intent);
        } else {
            Ts.a("AwContents", "Received activity result for an unknown request code %d", Integer.valueOf(i));
        }
    }

    public void onAttachedToWindow() {
        this.A.h(false);
        c();
        AwContents awContents = this.x;
        awContents.x0 = false;
        awContents.u0.onAttachedToWindow();
        awContents.z.a.t.a(awContents.R);
    }

    public boolean onCheckIsTextEditor() {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.onCheckIsTextEditor();
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0945ib(this)))).booleanValue();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!b()) {
            this.x.u0.onConfigurationChanged(configuration);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0215Ma(this, configuration));
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        this.A.h(false);
        if (b()) {
            return null;
        }
        return this.x.u0.onCreateInputConnection(editorInfo);
    }

    public void onDetachedFromWindow() {
        if (!b()) {
            this.x.F();
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0323Sa(this));
    }

    public boolean onDragEvent(DragEvent dragEvent) {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.onDragEvent(dragEvent);
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0233Na(this, dragEvent)))).booleanValue();
    }

    public void onDraw(Canvas canvas) {
        this.A.h(true);
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            RunnableC0162Ja runnableC0162Ja = new RunnableC0162Ja(this, canvas);
            C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
            Objects.requireNonNull(c0070Dq);
            c0070Dq.c(new FutureTask(runnableC0162Ja, null));
            return;
        }
        AwContents awContents = this.x;
        Objects.requireNonNull(awContents);
        try {
            TraceEvent.x("AwContents.onDraw", null);
            awContents.u0.onDraw(canvas);
        } finally {
            TraceEvent.A("AwContents.onDraw");
        }
    }

    public void onDrawVerticalScrollBar(Canvas canvas, Drawable drawable, int i, int i2, int i3, int i4) {
        this.t.super_onDrawVerticalScrollBar(canvas, drawable, i, i2, i3, i4);
    }

    public void onFinishTemporaryDetach() {
        this.x.x0 = false;
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0377Va(this, z, i, rect));
        } else {
            AwContents awContents = this.x;
            if (awContents.x0) {
                return;
            }
            awContents.u0.onFocusChanged(z, i, rect);
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        this.A.h(false);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new CallableC0610cb(this, motionEvent)))).booleanValue();
        }
        AwContents awContents = this.x;
        if (awContents.t(0)) {
            return false;
        }
        return awContents.u0.onGenericMotionEvent(motionEvent);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.onHoverEvent(motionEvent);
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0554bb(this, motionEvent)))).booleanValue();
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        this.A.h(false);
        if (!b()) {
            return false;
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0287Qa(this, i, keyEvent)))).booleanValue();
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        this.A.h(false);
        if (!b()) {
            return false;
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0251Oa(this, i, i2, keyEvent)))).booleanValue();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.onKeyUp(i, keyEvent);
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0305Ra(this, i, keyEvent)))).booleanValue();
    }

    public void onMeasure(int i, int i2) {
        this.A.h(false);
        if (!b()) {
            this.x.u0.onMeasure(i, i2);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        RunnableC0721eb runnableC0721eb = new RunnableC0721eb(this, i, i2);
        C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
        Objects.requireNonNull(c0070Dq);
        c0070Dq.c(new FutureTask(runnableC0721eb, null));
    }

    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (!b()) {
            this.x.u0.c(i, i2, z, z2);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0126Ha(this, i, i2, z, z2));
    }

    public void onPause() {
        if (!b()) {
            f(47);
            this.x.H();
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0665da(this));
        }
    }

    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        this.A.h(false);
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            RunnableC1880za runnableC1880za = new RunnableC1880za(this, viewStructure, i);
            C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
            Objects.requireNonNull(c0070Dq);
            c0070Dq.c(new FutureTask(runnableC1880za, null));
            return;
        }
        f(48);
        AutofillProvider autofillProvider = this.x.z0;
        if (autofillProvider != null) {
            autofillProvider.d(viewStructure, i);
        }
    }

    public void onProvideContentCaptureStructure(ViewStructure viewStructure, int i) {
        if (Yw.a()) {
            Log.i("ContentCapture", "onProvideContentCaptureStructure");
        }
        this.x.D0 = Ww.f(AbstractC1299ox.a(this.s.getContext()), this.s, viewStructure, this.x.A);
    }

    public void onProvideVirtualStructure(ViewStructure viewStructure) {
        this.A.h(false);
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            RunnableC0036Ca runnableC0036Ca = new RunnableC0036Ca(this, viewStructure);
            C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
            Objects.requireNonNull(c0070Dq);
            c0070Dq.c(new FutureTask(runnableC0036Ca, null));
            return;
        }
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return;
        }
        if (awContents.F.d) {
            ((WebContentsAccessibilityImpl) awContents.r()).t(viewStructure, true);
        } else {
            viewStructure.setChildCount(0);
        }
    }

    public void onResume() {
        if (!b()) {
            f(49);
            this.x.I();
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0720ea(this));
        }
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        if (!b()) {
            this.x.u0.a(i, i2, i3, i4);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0413Xa(this, i, i2, i3, i4));
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!b()) {
            this.x.u0.onSizeChanged(i, i2, i3, i4);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0395Wa(this, i, i2, i3, i4));
    }

    public void onStartTemporaryDetach() {
        this.x.x0 = true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.A.h(false);
        if (!b()) {
            return this.x.u0.onTouchEvent(motionEvent);
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0449Za(this, motionEvent)))).booleanValue();
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onVisibilityChanged(View view, int i) {
        if (this.x == null) {
            return;
        }
        if (!b()) {
            this.x.u0.onVisibilityChanged(view, i);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0341Ta(this, view, i));
    }

    public void onWindowFocusChanged(boolean z) {
        if (!b()) {
            this.x.u0.onWindowFocusChanged(z);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0359Ua(this, z));
    }

    public void onWindowVisibilityChanged(int i) {
        if (!b()) {
            this.x.u0.onWindowVisibilityChanged(i);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0144Ia(this, i));
    }

    public boolean overlayHorizontalScrollbar() {
        this.A.h(false);
        if (!b()) {
            f(50);
            return this.x.F0;
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0072Ea(this)))).booleanValue();
    }

    public boolean overlayVerticalScrollbar() {
        this.A.h(false);
        if (!b()) {
            f(51);
            return this.x.G0;
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0269Pa(this)))).booleanValue();
    }

    public boolean pageDown(boolean z) {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new O9(this, z)))).booleanValue();
        }
        f(52);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        C0463Zo c0463Zo = awContents.P;
        int iA = ((C0121Gn) c0463Zo.a).a();
        int iB = ((C0121Gn) c0463Zo.a).b();
        if (z) {
            return c0463Zo.a(iA, c0463Zo.d());
        }
        int i = c0463Zo.g;
        int i2 = i / 2;
        if (i > 48) {
            i2 = i - 24;
        }
        return c0463Zo.a(iA, iB + i2);
    }

    public boolean pageUp(boolean z) {
        boolean zA;
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new N9(this, z)))).booleanValue();
        }
        f(53);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        C0463Zo c0463Zo = awContents.P;
        int iA = ((C0121Gn) c0463Zo.a).a();
        int iB = ((C0121Gn) c0463Zo.a).b();
        if (z) {
            zA = c0463Zo.a(iA, 0);
        } else {
            int i = c0463Zo.g;
            int i2 = -i;
            int i3 = i2 / 2;
            if (i > 48) {
                i3 = i2 + 24;
            }
            zA = c0463Zo.a(iA, iB + i3);
        }
        return zA;
    }

    public void pauseTimers() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0553ba(this));
        } else {
            f(54);
            if (this.x.t(1)) {
                return;
            }
            N.M556jcRe(true);
        }
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        this.A.h(false);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new CallableC0054Da(this, i, bundle)))).booleanValue();
        }
        AwContents awContents = this.x;
        if (!awContents.t(1)) {
            Objects.requireNonNull((WebContentsAccessibilityImpl) awContents.r());
        }
        return this.t.super_performAccessibilityAction(i, bundle);
    }

    public boolean performLongClick() {
        if (this.s.getParent() != null) {
            return this.t.super_performLongClick();
        }
        return false;
    }

    public void postMessageToMainFrame(WebMessage webMessage, Uri uri) {
        f(55);
        this.B.g(webMessage.getData(), uri.toString(), C0533b9.b(webMessage.getPorts()));
    }

    public void postUrl(String str, byte[] bArr) {
        this.A.h(true);
        if (!b()) {
            f(56);
            this.x.K(str, bArr);
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1806y9(this, str, bArr));
        }
    }

    public void preDispatchDraw(Canvas canvas) {
    }

    public void reload() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new F9(this));
        } else {
            f(57);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.D.c(true);
        }
    }

    public void removeJavascriptInterface(String str) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1605ua(this, str));
            return;
        }
        f(58);
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return;
        }
        JavascriptInjectorImpl javascriptInjectorImpl = (JavascriptInjectorImpl) awContents.o();
        javascriptInjectorImpl.t.remove(str);
        long j = javascriptInjectorImpl.u;
        if (j != 0) {
            N.M5J62vXh(j, javascriptInjectorImpl, str);
        }
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        int i;
        int i2;
        int i3;
        this.A.h(false);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new CallableC0777fb(this, view, rect, z)))).booleanValue();
        }
        AwContents awContents = this.x;
        if (awContents.t(1)) {
            return false;
        }
        C0463Zo c0463Zo = awContents.P;
        int left = view.getLeft() - view.getScrollX();
        int top = view.getTop() - view.getScrollY();
        int iA = ((C0121Gn) c0463Zo.a).a();
        int iB = ((C0121Gn) c0463Zo.a).b();
        rect.offset(left, top);
        int i4 = c0463Zo.g;
        if (rect.bottom > iB + i4) {
            int i5 = i4 / 3;
            if (rect.width() > i5 * 2) {
                i = rect.top;
            } else {
                i2 = rect.top - (i5 + iB);
            }
        } else {
            i = rect.top;
            i2 = i < iB ? i - iB : 0;
        }
        int i6 = c0463Zo.f + iA;
        if (rect.right <= i6 || rect.left <= iA) {
            int i7 = rect.left;
            i3 = i7 < iA ? 0 - (iA - i7) : 0;
        } else {
            i3 = (rect.width() > c0463Zo.f ? rect.left - iA : rect.right - i6) + 0;
        }
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        if (!z) {
            return c0463Zo.a(iA + i3, iB + i2);
        }
        c0463Zo.e(i3, i2);
        return true;
    }

    public boolean requestFocus(int i, Rect rect) {
        this.A.h(false);
        if (!b()) {
            this.x.u0.b();
            return this.t.super_requestFocus(i, rect);
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0666db(this, i, rect)))).booleanValue();
    }

    public void requestFocusNodeHref(Message message) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new U9(this, message));
            return;
        }
        f(59);
        AwContents awContents = this.x;
        Objects.requireNonNull(awContents);
        if (message == null || awContents.t(1)) {
            return;
        }
        N.MHBuiDmd(awContents.s, awContents);
        Bundle data = message.getData();
        data.putString("url", awContents.i0.c);
        data.putString("title", awContents.i0.d);
        data.putString("src", awContents.i0.e);
        message.setData(data);
        message.sendToTarget();
    }

    public void requestImageRef(Message message) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new V9(this, message));
            return;
        }
        f(60);
        AwContents awContents = this.x;
        Objects.requireNonNull(awContents);
        if (message == null || awContents.t(1)) {
            return;
        }
        N.MHBuiDmd(awContents.s, awContents);
        Bundle data = message.getData();
        data.putString("url", awContents.i0.e);
        message.setData(data);
        message.sendToTarget();
    }

    public boolean restorePicture(Bundle bundle, File file) {
        return false;
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        byte[] byteArray;
        this.A.h(true);
        if (b()) {
            return (WebBackForwardList) this.A.a.c(new FutureTask(new CallableC1641v9(this, bundle)));
        }
        f(61);
        if (bundle == null) {
            return null;
        }
        AwContents awContents = this.x;
        boolean zM$ugXLRy = false;
        if (!awContents.t(1) && (byteArray = bundle.getByteArray("WEBVIEW_CHROMIUM_STATE")) != null && (zM$ugXLRy = N.M$ugXLRy(awContents.s, awContents, byteArray))) {
            awContents.E.e(awContents.A.getTitle());
        }
        if (zM$ugXLRy) {
            return copyBackForwardList();
        }
        return null;
    }

    public void resumeTimers() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0609ca(this));
        } else {
            f(62);
            if (this.x.t(1)) {
                return;
            }
            N.M556jcRe(false);
        }
    }

    public void savePassword(String str, String str2, String str3) {
    }

    public boolean savePicture(Bundle bundle, File file) {
        return false;
    }

    public WebBackForwardList saveState(Bundle bundle) {
        byte[] bArrMtUUIkJC;
        boolean z = true;
        this.A.h(true);
        if (b()) {
            return (WebBackForwardList) this.A.a.c(new FutureTask(new CallableC1586u9(this, bundle)));
        }
        f(63);
        if (bundle == null) {
            return null;
        }
        AwContents awContents = this.x;
        if (awContents.t(1) || (bArrMtUUIkJC = N.MtUUIkJC(awContents.s, awContents)) == null) {
            z = false;
        } else {
            bundle.putByteArray("WEBVIEW_CHROMIUM_STATE", bArrMtUUIkJC);
        }
        if (z) {
            return copyBackForwardList();
        }
        return null;
    }

    public void saveWebArchive(String str) {
        saveWebArchive(str, false, null);
    }

    public void setBackgroundColor(int i) {
        this.A.h(false);
        if (b()) {
            PostTask.a(AbstractC0538bE.a, new RunnableC0833gb(this, i), 0L);
            return;
        }
        AwContents awContents = this.x;
        awContents.h0 = i;
        if (awContents.t(1)) {
            return;
        }
        N.MLVoXr_n(awContents.s, awContents, i);
    }

    public void setCertificate(SslCertificate sslCertificate) {
    }

    public void setDownloadListener(DownloadListener downloadListener) {
        f(64);
        this.u.o = downloadListener;
    }

    public void setFindListener(WebView.FindListener findListener) {
        f(65);
        this.u.l = findListener;
    }

    public boolean setFrame(int i, int i2, int i3, int i4) {
        return this.t.super_setFrame(i, i2, i3, i4);
    }

    public void setHorizontalScrollbarOverlay(boolean z) {
        if (!b()) {
            f(66);
            this.x.F0 = z;
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC0944ia(this, z));
        }
    }

    public void setHttpAuthUsernamePassword(String str, String str2, String str3, String str4) {
        if (!b()) {
            f(67);
            ((WebViewDatabaseAdapter) this.A.getWebViewDatabase(this.v)).setHttpAuthUsernamePassword(str, str2, str3, str4);
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1112lb(this, str, str2, str3, str4));
        }
    }

    public void setInitialScale(int i) {
        f(68);
        final AwSettings awSettings = this.w.a;
        float f = i;
        synchronized (awSettings.f) {
            if (awSettings.K != f) {
                awSettings.K = f;
                awSettings.m0.a(new Runnable(awSettings) { // from class: gp
                    public final AwSettings s;

                    {
                        this.s = awSettings;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AwSettings awSettings2 = this.s;
                        long j = awSettings2.l0;
                        if (j != 0) {
                            N.ManPyeB5(j, awSettings2);
                        }
                    }
                });
            }
        }
    }

    public void setLayerType(int i, Paint paint) {
        if (this.x == null) {
            return;
        }
        if (b()) {
            PostTask.a(AbstractC0538bE.a, new RunnableC0889hb(this, i, paint), 0L);
        } else {
            this.x.u0.setLayerType(i, paint);
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.A.h(false);
        c();
        this.t.super_setLayoutParams(layoutParams);
        if (!b()) {
            this.x.P();
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        RunnableC0180Ka runnableC0180Ka = new RunnableC0180Ka(this, layoutParams);
        C0070Dq c0070Dq = webViewChromiumFactoryProvider.a;
        Objects.requireNonNull(c0070Dq);
        c0070Dq.c(new FutureTask(runnableC0180Ka, null));
    }

    public void setMapTrackballToArrowKeys(boolean z) {
    }

    public void setNetworkAvailable(boolean z) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1531t9(this, z));
        } else {
            f(69);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            NetworkChangeNotifier.g(false);
            N.MSO7DbfT(awContents.s, awContents, z);
        }
    }

    public void setOverScrollMode(int i) {
        if (this.x == null) {
            return;
        }
        if (!b()) {
            this.x.S(i);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0090Fa(this, i));
    }

    public void setPictureListener(WebView.PictureListener pictureListener) {
        if (b()) {
            this.A.a.a(new RunnableC1440ra(this, pictureListener));
            return;
        }
        f(70);
        boolean z = this.z >= 18;
        C0252Ob c0252Ob = this.u;
        c0252Ob.m = pictureListener;
        c0252Ob.n = z;
        final AwContents awContents = this.x;
        boolean z2 = pictureListener != null;
        if (awContents.t(1)) {
            return;
        }
        if (z) {
            awContents.k0 = null;
        } else if (z2 && awContents.k0 == null) {
            awContents.k0 = new Callable(awContents) { // from class: tn
                public final AwContents a;

                {
                    this.a = awContents;
                }

                @Override // java.util.concurrent.Callable
                public Object call() {
                    return this.a.h();
                }
            };
        }
        N.MjvLhcRF(awContents.s, awContents, z2);
    }

    public void setRendererPriorityPolicy(int i, boolean z) {
        int i2 = 1;
        if (i == 0) {
            i2 = 0;
        } else if (i != 1) {
            i2 = 2;
        }
        AwContents awContents = this.x;
        awContents.c0 = i2;
        awContents.d0 = z;
        awContents.Z();
    }

    public void setScrollBarStyle(int i) {
        if (!b()) {
            this.x.T(i);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
        webViewChromiumFactoryProvider.a.a(new RunnableC0108Ga(this, i));
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void setSmartClipResultHandler(Handler handler) {
        f(71);
        c();
        this.x.setSmartClipResultHandler(handler);
    }

    public void setTextClassifier(TextClassifier textClassifier) {
        f(72);
        this.x.V(textClassifier);
    }

    public void setVerticalScrollbarOverlay(boolean z) {
        if (!b()) {
            f(73);
            this.x.G0 = z;
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1550ta(this, z));
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        f(74);
        AwSettings awSettings = this.w.a;
        f(18);
        boolean z = false;
        if (webChromeClient != null) {
            boolean z2 = false;
            boolean z3 = false;
            for (Class<?> superclass = webChromeClient.getClass(); superclass != WebChromeClient.class && (!z2 || !z3); superclass = superclass.getSuperclass()) {
                if (!z2) {
                    try {
                        superclass.getDeclaredMethod("onShowCustomView", View.class, WebChromeClient.CustomViewCallback.class);
                        z2 = true;
                    } catch (NoSuchMethodException unused) {
                    }
                }
                if (!z3) {
                    try {
                        superclass.getDeclaredMethod("onHideCustomView", new Class[0]);
                        z3 = true;
                    } catch (NoSuchMethodException unused2) {
                    }
                }
            }
            if (z2 && z3) {
                z = true;
            }
        }
        synchronized (awSettings.f) {
            if (awSettings.h0 != z) {
                awSettings.h0 = z;
                awSettings.m0.b();
            }
        }
        this.B.f = webChromeClient;
        this.u.k = webChromeClient;
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        boolean zIsInstance;
        f(75);
        T8 t8 = this.B;
        if (webViewClient == null) {
            webViewClient = T8.g;
        }
        t8.e = webViewClient;
        C0252Ob c0252Ob = this.u;
        WebViewClient webViewClient2 = this.B.e;
        c0252Ob.h = webViewClient2;
        MM mm = c0252Ob.i;
        Objects.requireNonNull(mm);
        Trace.beginSection("SupportLibWebViewContentsClientAdapter.setWebViewClient");
        try {
            try {
                zIsInstance = Class.forName("androidx.webkit.WebViewClientCompat", false, webViewClient2.getClass().getClassLoader()).isInstance(webViewClient2);
            } catch (ClassNotFoundException unused) {
                zIsInstance = false;
            }
            WebViewClientBoundaryInterface webViewClientBoundaryInterface = !zIsInstance ? null : (WebViewClientBoundaryInterface) JM.a(WebViewClientBoundaryInterface.class, JM.c(webViewClient2));
            mm.a = webViewClientBoundaryInterface;
            mm.b = webViewClientBoundaryInterface == null ? MM.c : webViewClientBoundaryInterface.getSupportedFeatures();
            if (webViewClient2 != null) {
                Lt.a.b("Android.WebView.SupportLibrary.ClientIsCompat", mm.a != null);
            }
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

    public void setWebViewRenderProcessClient(Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        if (webViewRenderProcessClient == null) {
            this.B.i(null);
            return;
        }
        if (executor == null) {
            executor = new Executor() { // from class: m9
                @Override // java.util.concurrent.Executor
                public void execute(Runnable runnable) {
                    runnable.run();
                }
            };
        }
        C1366q8.c(this.B, executor, webViewRenderProcessClient);
    }

    public boolean shouldDelayChildPressedState() {
        this.A.h(false);
        if (!b()) {
            return true;
        }
        return ((Boolean) this.A.a.c(new FutureTask(new CallableC0000Aa(this)))).booleanValue();
    }

    public boolean showFindDialog(String str, boolean z) {
        f(76);
        this.A.h(false);
        if (b() || this.s.getParent() == null) {
            return false;
        }
        ActionMode.Callback findActionModeCallback = new FindActionModeCallback(this.v);
        this.s.startActionMode(findActionModeCallback);
        findActionModeCallback.setWebView(this.s);
        if (z) {
            findActionModeCallback.showSoftInput();
        }
        if (str == null) {
            return true;
        }
        findActionModeCallback.setText(str);
        findActionModeCallback.findAll();
        return true;
    }

    public void stopLoading() {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new E9(this));
        } else {
            f(77);
            AwContents awContents = this.x;
            if (awContents.t(1)) {
                return;
            }
            awContents.A.stop();
        }
    }

    public boolean zoomBy(float f) {
        this.A.h(true);
        c();
        this.x.c0(f);
        return true;
    }

    public boolean zoomIn() {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new CallableC1715wa(this)))).booleanValue();
        }
        AwContents awContents = this.x;
        if (!awContents.f()) {
            return false;
        }
        awContents.c0(1.25f);
        return true;
    }

    public boolean zoomOut() {
        this.A.h(true);
        if (b()) {
            return ((Boolean) this.A.a.c(new FutureTask(new CallableC1770xa(this)))).booleanValue();
        }
        AwContents awContents = this.x;
        if (!awContents.g()) {
            return false;
        }
        awContents.c0(0.8f);
        return true;
    }

    public void saveWebArchive(String str, boolean z, ValueCallback valueCallback) {
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new D9(this, str, z, valueCallback));
            return;
        }
        AwContents awContents = this.x;
        Callback callbackA = AbstractC0532b8.a(valueCallback);
        Objects.requireNonNull(awContents);
        if (z) {
            new C1893zn(awContents, str, callbackA).d(Su.f);
        } else {
            awContents.M(str, callbackA);
        }
    }

    public void loadUrl(String str) {
        this.A.h(true);
        if (b()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.A;
            webViewChromiumFactoryProvider.a.a(new RunnableC1751x9(this, str));
        } else {
            AwContents awContents = this.x;
            if (awContents.t(1) || str == null) {
                return;
            }
            awContents.C(str, null);
        }
    }
}
