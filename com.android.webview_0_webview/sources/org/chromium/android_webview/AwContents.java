package org.chromium.android_webview;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.android.webview.chromium.WebViewChromium;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import defpackage.AbstractC0175Jn;
import defpackage.AbstractC0229Mo;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0511ao;
import defpackage.AbstractC0538bE;
import defpackage.AbstractC0827gO;
import defpackage.AbstractC1301oz;
import defpackage.Aw;
import defpackage.C0067Dn;
import defpackage.C0069Dp;
import defpackage.C0103Fn;
import defpackage.C0121Gn;
import defpackage.C0123Gp;
import defpackage.C0139Hn;
import defpackage.C0157In;
import defpackage.C0177Jp;
import defpackage.C0181Kb;
import defpackage.C0211Ln;
import defpackage.C0212Lo;
import defpackage.C0228Mn;
import defpackage.C0234Nb;
import defpackage.C0246Nn;
import defpackage.C0248Np;
import defpackage.C0252Ob;
import defpackage.C0265Oo;
import defpackage.C0282Pn;
import defpackage.C0284Pp;
import defpackage.C0336Sn;
import defpackage.C0338Sp;
import defpackage.C0358Tr;
import defpackage.C0372Un;
import defpackage.C0394Vr;
import defpackage.C0427Xo;
import defpackage.C0428Xp;
import defpackage.C0463Zo;
import defpackage.C0904hq;
import defpackage.C0984jE;
import defpackage.C1179mn;
import defpackage.C1201n8;
import defpackage.C1206nD;
import defpackage.C1347pq;
import defpackage.C1453rn;
import defpackage.C1785xp;
import defpackage.C1839yo;
import defpackage.C1896zq;
import defpackage.FN;
import defpackage.InterfaceC0016Aq;
import defpackage.InterfaceC0264On;
import defpackage.InterfaceC0286Pr;
import defpackage.InterfaceC0300Qn;
import defpackage.InterfaceC0482aE;
import defpackage.InterfaceC0650dE;
import defpackage.InterfaceC0771fO;
import defpackage.InterfaceC0817gE;
import defpackage.InterfaceC0873hE;
import defpackage.InterfaceC1840yp;
import defpackage.Is;
import defpackage.It;
import defpackage.M9;
import defpackage.QD;
import defpackage.RD;
import defpackage.Ts;
import defpackage.U8;
import defpackage.V8;
import defpackage.VD;
import defpackage.Vw;
import defpackage.Ws;
import defpackage.Ys;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.permission.AwPermissionRequest;
import org.chromium.base.Callback;
import org.chromium.base.LocaleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.components.autofill.AutofillProvider;
import org.chromium.content.browser.JavascriptInjectorImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.selection.SmartSelectionClient;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.JavaScriptCallback;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.SmartClipProvider;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwContents implements SmartClipProvider {
    public static WeakHashMap L0;
    public WebContents A;
    public Paint A0;
    public InterfaceC0650dE B;
    public InterfaceC0873hE B0;
    public C0336Sn C;
    public QD C0;
    public NavigationController D;
    public Vw D0;
    public final AbstractC0511ao E;
    public C0248Np E0;
    public C0123Gp F;
    public final AwContentsClientBridge G;
    public boolean G0;
    public final C0069Dp H;
    public final AwContentsBackgroundThreadClient I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final AwContentsIoThreadClient f26J;
    public final C0246Nn K;
    public InterfaceC0264On L;
    public final InterfaceC0300Qn M;
    public final C0265Oo N;
    public final C0177Jp O;
    public final C0463Zo P;
    public C0904hq Q;
    public final InterfaceC0771fO R;
    public final AwSettings S;
    public final C1347pq T;
    public boolean V;
    public boolean W;
    public boolean X;
    public boolean Y;
    public boolean Z;
    public boolean a0;
    public Runnable b0;
    public int c0;
    public boolean d0;
    public Bitmap e0;
    public boolean f0;
    public boolean g0;
    public final C0338Sp j0;
    public Callable k0;
    public boolean l0;
    public boolean m0;
    public float q0;
    public float r0;
    public long s;
    public AwAutofillClient s0;
    public AwBrowserContext t;
    public AwPdfExporter t0;
    public ViewGroup u;
    public InterfaceC1840yp u0;
    public InterfaceC0286Pr v;
    public final C0211Ln v0;
    public final Context w;
    public boolean w0;
    public final int x;
    public boolean x0;
    public C1785xp y;
    public boolean y0;
    public C0372Un z;
    public AutofillProvider z0;
    public static final String H0 = N.MhBmyxCD();
    public static final Pattern I0 = Pattern.compile("^file:/*android_(asset|res).*");
    public static final Pattern J0 = Pattern.compile("^[^#]*(#[A-Za-z][A-Za-z0-9\\-_:.]*)$");
    public static String K0 = "";
    public static final Rect M0 = new Rect();
    public final Ys U = new Ys();
    public int h0 = -1;
    public final C0228Mn i0 = new C0228Mn();
    public float n0 = 1.0f;
    public float o0 = 1.0f;
    public float p0 = 1.0f;
    public boolean F0 = true;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public abstract class VisualStateCallback {
        public abstract void a(long j);
    }

    public AwContents(AwBrowserContext awBrowserContext, ViewGroup viewGroup, Context context, InterfaceC0264On interfaceC0264On, InterfaceC0300Qn interfaceC0300Qn, AbstractC0511ao abstractC0511ao, AwSettings awSettings, AbstractC0175Jn abstractC0175Jn) {
        Trace.beginSection("AwContents.constructor");
        try {
            this.c0 = 2;
            this.S = awSettings;
            a0();
            this.t = awBrowserContext;
            this.u = viewGroup;
            viewGroup.setWillNotDraw(false);
            this.w = context;
            WebViewChromium webViewChromium = ((M9) abstractC0175Jn).a;
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = webViewChromium.A;
            WebView webView = webViewChromium.s;
            Objects.requireNonNull(webViewChromiumFactoryProvider);
            this.z0 = Build.VERSION.SDK_INT < 26 ? null : new Aw(context, webView);
            this.x = this.w.getApplicationInfo().targetSdkVersion;
            this.L = interfaceC0264On;
            this.M = interfaceC0300Qn;
            this.E = abstractC0511ao;
            abstractC0511ao.a.e = new C1179mn(this);
            C0139Hn c0139Hn = new C0139Hn(this, null);
            this.u0 = c0139Hn;
            this.v0 = new C0211Ln(this.u, this.L, c0139Hn, null);
            C0265Oo c0265Oo = new C0265Oo();
            this.N = c0265Oo;
            c0265Oo.k = new C0103Fn(this, null);
            this.H = new C0069Dp(this, abstractC0511ao, awSettings, this.w, this.u);
            Context context2 = this.w;
            ThreadUtils.b();
            if (AwContentsStatics.a == null) {
                AwContentsStatics.a = new C0284Pp();
            }
            this.G = new AwContentsClientBridge(context2, abstractC0511ao, AwContentsStatics.a);
            this.O = new C0177Jp(this);
            this.I = new C0157In(this, null);
            this.f26J = new C0282Pn(this, null);
            this.K = new C0246Nn(this, null);
            this.R = new C0067Dn(this, null);
            this.b0 = new Runnable(this) { // from class: qn
                public final AwContents s;

                {
                    this.s = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.b0();
                }
            };
            C1453rn c1453rn = new C1453rn(this);
            AwSettings awSettings2 = this.S;
            synchronized (awSettings2.f) {
                awSettings2.d = c1453rn;
            }
            C0338Sp c0338Sp = new C0338Sp(this.E);
            this.j0 = c0338Sp;
            AwSettings awSettings3 = this.S;
            String str = c0338Sp.a;
            synchronized (awSettings3.f) {
                if ((awSettings3.f27J != null && !awSettings3.f27J.equals(str)) || (awSettings3.f27J == null && str != null)) {
                    awSettings3.f27J = str;
                    awSettings3.m0.b();
                }
            }
            this.P = new C0463Zo(new C0121Gn(this, null));
            this.T = new C1347pq(this.u);
            S(this.u.getOverScrollMode());
            T(this.L.a());
            Q(N.MFiR_zHY(this.t.e));
            E();
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

    public static void L(int i) {
        It.d("Android.WebView.LoadDataWithBaseUrl.HistoryUrl", i, 3);
    }

    public static void U() {
        N.Mbm7UXr0();
    }

    public static void b(AwContents awContents, InterfaceC0286Pr interfaceC0286Pr) {
        InterfaceC0286Pr interfaceC0286Pr2 = awContents.v;
        if (interfaceC0286Pr2 == interfaceC0286Pr) {
            return;
        }
        awContents.v = interfaceC0286Pr;
        N.MzAVKSgA(awContents.s, awContents, interfaceC0286Pr != null ? interfaceC0286Pr.b() : 0L);
        if (interfaceC0286Pr2 != null) {
            interfaceC0286Pr2.destroy();
        }
    }

    private void didOverscroll(int i, int i2, float f, float f2) {
        this.P.e(i, i2);
        C0904hq c0904hq = this.Q;
        if (c0904hq == null) {
            return;
        }
        c0904hq.f += i;
        c0904hq.g += i2;
        int scrollX = this.u.getScrollX();
        int scrollY = this.u.getScrollY();
        int i3 = i + scrollX;
        int i4 = i2 + scrollY;
        C0463Zo c0463Zo = this.P;
        int i5 = c0463Zo.d;
        int i6 = c0463Zo.e;
        C0904hq c0904hq2 = this.Q;
        float fHypot = (float) Math.hypot(f, f2);
        if (!c0904hq2.h) {
            if (i6 > 0 || c0904hq2.a.getOverScrollMode() == 0) {
                if (i4 < 0 && scrollY >= 0) {
                    c0904hq2.b.onAbsorb((int) fHypot);
                    if (!c0904hq2.c.isFinished()) {
                        c0904hq2.c.onRelease();
                    }
                } else if (i4 > i6 && scrollY <= i6) {
                    c0904hq2.c.onAbsorb((int) fHypot);
                    if (!c0904hq2.b.isFinished()) {
                        c0904hq2.b.onRelease();
                    }
                }
            }
            if (i5 > 0) {
                if (i3 < 0 && scrollX >= 0) {
                    c0904hq2.d.onAbsorb((int) fHypot);
                    if (!c0904hq2.e.isFinished()) {
                        c0904hq2.e.onRelease();
                    }
                } else if (i3 > i5 && scrollX <= i5) {
                    c0904hq2.e.onAbsorb((int) fHypot);
                    if (!c0904hq2.d.isFinished()) {
                        c0904hq2.d.onRelease();
                    }
                }
            }
        }
        C0904hq c0904hq3 = this.Q;
        if ((c0904hq3.b.isFinished() && c0904hq3.c.isFinished() && c0904hq3.d.isFinished() && c0904hq3.e.isFinished()) ? false : true) {
            postInvalidateOnAnimation();
        }
    }

    public static void generateMHTMLCallback(String str, long j, Callback callback) {
        if (callback == null) {
            return;
        }
        if (j < 0) {
            str = null;
        }
        callback.a(str);
    }

    private int getErrorUiType() {
        int[] iArr = new int[2];
        this.u.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        this.u.getRootView().getLocationOnScreen(iArr);
        int i3 = i - iArr[0];
        int i4 = i2 - iArr[1];
        if (i3 < 0 || i4 < 0 || this.u.getWidth() + i3 > this.u.getRootView().getWidth() || this.u.getHeight() + i4 > this.u.getRootView().getHeight()) {
            return 2;
        }
        return this.u.getWidth() == this.u.getRootView().getWidth() && ((((double) this.u.getHeight()) / ((double) this.u.getRootView().getHeight())) > 0.7d ? 1 : ((((double) this.u.getHeight()) / ((double) this.u.getRootView().getHeight())) == 0.7d ? 0 : -1)) >= 0 ? 0 : 1;
    }

    private int[] getLocationOnScreen() {
        int[] iArr = new int[2];
        this.u.getLocationOnScreen(iArr);
        return iArr;
    }

    public static long l() {
        return N.Mq4IKpgt();
    }

    private long onCreateTouchHandle() {
        return new PopupTouchHandleDrawable(this.U, this.A, this.u).y;
    }

    public static void onDocumentHasImagesResponse(boolean z, Message message) {
        message.arg1 = z ? 1 : 0;
        message.sendToTarget();
    }

    public static void onEvaluateJavaScriptResultForTesting(String str, JavaScriptCallback javaScriptCallback) {
        javaScriptCallback.a(str);
    }

    private void onGeolocationPermissionsHidePrompt() {
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onGeolocationPermissionsHidePrompt", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.onGeolocationPermissionsHidePrompt();
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onGeolocationPermissionsHidePrompt");
        }
    }

    private void onGeolocationPermissionsShowPrompt(String str) {
        boolean z;
        boolean z2;
        if (t(0)) {
            return;
        }
        C0212Lo c0212LoA = this.t.a();
        AwSettings awSettings = this.S;
        synchronized (awSettings.f) {
            z = awSettings.f0;
        }
        if (!z) {
            N.M0cKB_Do(this.s, this, false, str);
            return;
        }
        if (c0212LoA.a.contains(c0212LoA.d(str))) {
            N.M0cKB_Do(this.s, this, c0212LoA.a.getBoolean(c0212LoA.d(str), false), str);
            return;
        }
        AbstractC0511ao abstractC0511ao = this.E;
        final C0394Vr c0394Vr = new C0394Vr(str, this);
        C0252Ob c0252Ob = (C0252Ob) abstractC0511ao;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onGeolocationPermissionsShowPrompt", null);
            if (c0252Ob.k == null) {
                c0394Vr.a(str, false, false);
            } else {
                try {
                    z2 = !c0252Ob.k.getClass().getMethod("onGeolocationPermissionsShowPrompt", String.class, GeolocationPermissions.Callback.class).getDeclaringClass().equals(WebChromeClient.class);
                } catch (NoSuchMethodException | SecurityException unused) {
                    z2 = false;
                }
                if (z2) {
                    c0252Ob.k.onGeolocationPermissionsShowPrompt(str, new GeolocationPermissions.Callback(c0394Vr) { // from class: Db
                        public final InterfaceC0194Ko a;

                        {
                            this.a = c0394Vr;
                        }

                        @Override // android.webkit.GeolocationPermissions.Callback
                        public void invoke(String str2, boolean z3, boolean z4) {
                            ((C0394Vr) this.a).a(str2, z3, z4);
                        }
                    });
                } else {
                    c0394Vr.a(str, false, false);
                }
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onGeolocationPermissionsShowPrompt");
        }
    }

    private void onPermissionRequest(AwPermissionRequest awPermissionRequest) {
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onPermissionRequest", null);
            if (c0252Ob.k != null) {
                if (c0252Ob.q == null) {
                    c0252Ob.q = new WeakHashMap();
                }
                C0234Nb c0234Nb = new C0234Nb(awPermissionRequest);
                c0252Ob.q.put(awPermissionRequest, new WeakReference(c0234Nb));
                c0252Ob.k.onPermissionRequest(c0234Nb);
            } else {
                awPermissionRequest.a();
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onPermissionRequest");
        }
    }

    private void onPermissionRequestCanceled(AwPermissionRequest awPermissionRequest) {
        WeakReference weakReference;
        C0234Nb c0234Nb;
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onPermissionRequestCanceled", null);
            if (c0252Ob.k != null && c0252Ob.q != null && (weakReference = (WeakReference) c0252Ob.q.get(awPermissionRequest)) != null && (c0234Nb = (C0234Nb) weakReference.get()) != null) {
                c0252Ob.k.onPermissionRequestCanceled(c0234Nb);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onPermissionRequestCanceled");
        }
    }

    private void onReceivedHttpAuthRequest(AwHttpAuthHandler awHttpAuthHandler, String str, String str2) {
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onReceivedHttpAuthRequest", null);
            c0252Ob.h.onReceivedHttpAuthRequest(c0252Ob.e, new C0181Kb(awHttpAuthHandler), str, str2);
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedHttpAuthRequest");
            AbstractC0229Mo.a(2);
        } catch (Throwable th) {
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedHttpAuthRequest");
            throw th;
        }
    }

    private void onReceivedIcon(Bitmap bitmap) {
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onReceivedIcon", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.onReceivedIcon(c0252Ob.e, bitmap);
            }
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedIcon");
            this.e0 = bitmap;
        } catch (Throwable th) {
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedIcon");
            throw th;
        }
    }

    private void onReceivedTouchIconUrl(String str, boolean z) {
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onReceivedTouchIconUrl", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.onReceivedTouchIconUrl(c0252Ob.e, str, z);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedTouchIconUrl");
        }
    }

    private void onRendererResponsive(AwRenderProcess awRenderProcess) {
        U8 u8;
        V8 v8;
        if (t(0) || (v8 = (u8 = (U8) this.E).j) == null) {
            return;
        }
        v8.b(u8.e, awRenderProcess);
    }

    private void onRendererUnresponsive(AwRenderProcess awRenderProcess) {
        U8 u8;
        V8 v8;
        if (t(0) || (v8 = (u8 = (U8) this.E).j) == null) {
            return;
        }
        v8.c(u8.e, awRenderProcess);
    }

    private void onWebLayoutContentsSizeChanged(int i, int i2) {
        C0265Oo c0265Oo = this.N;
        c0265Oo.a(i, i2, c0265Oo.e);
    }

    private void onWebLayoutPageScaleFactorChanged(float f) {
        C0265Oo c0265Oo = this.N;
        c0265Oo.a(c0265Oo.d, c0265Oo.c, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postInvalidateOnAnimation() {
        if (((Boolean) FN.j.get()).booleanValue()) {
            this.u.invalidate();
        } else {
            this.u.postInvalidateOnAnimation();
        }
    }

    public static boolean s(String str) {
        return "base64".equals(str);
    }

    private void scrollContainerViewTo(int i, int i2) {
        C0463Zo c0463Zo = this.P;
        c0463Zo.b = i;
        c0463Zo.c = i2;
        int iA = ((C0121Gn) c0463Zo.a).a();
        int iB = ((C0121Gn) c0463Zo.a).b();
        int i3 = c0463Zo.d;
        int i4 = c0463Zo.e;
        ((C0121Gn) c0463Zo.a).c(i - iA, i2 - iB, iA, iB, i3, i4, c0463Zo.h);
    }

    private void setAwAutofillClient(AwAutofillClient awAutofillClient) {
        this.s0 = awAutofillClient;
        awAutofillClient.c = this.w;
    }

    public static boolean u(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                return true;
            default:
                return false;
        }
    }

    private void updateHitTestData(int i, String str, String str2, String str3, String str4) {
        C0228Mn c0228Mn = this.i0;
        c0228Mn.a = i;
        c0228Mn.b = str;
        c0228Mn.c = str2;
        c0228Mn.d = str3;
        c0228Mn.e = str4;
    }

    private void updateScrollState(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        this.q0 = f;
        this.r0 = f2;
        C0463Zo c0463Zo = this.P;
        c0463Zo.d = i;
        c0463Zo.e = i2;
        if (this.n0 == f3 && this.o0 == f4 && this.p0 == f5) {
            return;
        }
        this.o0 = f4;
        this.p0 = f5;
        float f6 = this.n0;
        if (f6 != f3) {
            this.n0 = f3;
            float fM = m();
            C1839yo c1839yo = this.E.a;
            float f7 = f6 * fM;
            float f8 = this.n0 * fM;
            Handler handler = c1839yo.d;
            handler.sendMessage(handler.obtainMessage(7, Float.floatToIntBits(f7), Float.floatToIntBits(f8)));
        }
    }

    private boolean useLegacyGeolocationPermissionAPI() {
        return true;
    }

    public static final void x(final Callback callback, final String str) {
        PostTask.a(AbstractC0538bE.a, new Runnable(callback, str) { // from class: on
            public final Callback s;
            public final String t;

            {
                this.s = callback;
                this.t = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.a(this.t);
            }
        }, 0L);
    }

    public void A(String str, String str2, String str3) {
        if (t(1)) {
            return;
        }
        if (str != null && str.contains("#")) {
            if (!(Is.a.getApplicationInfo().targetSdkVersion >= 29) && !s(str3)) {
                Matcher matcher = J0.matcher(str);
                str = str.replace("#", "%23") + (matcher.matches() ? matcher.group(1) : "");
            }
        }
        String str4 = TextUtils.isEmpty(str) ? "" : str;
        if (TextUtils.isEmpty(str2)) {
            str2 = "text/html";
        }
        D(RD.b(str4, str2, s(str3), null));
    }

    public void B(String str, String str2, String str3, String str4, String str5) {
        final RD rdC;
        boolean z = true;
        if (t(1)) {
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        String str6 = str2;
        if (TextUtils.isEmpty(str3)) {
            str3 = "text/html";
        }
        String str7 = str3;
        String str8 = TextUtils.isEmpty(str) ? "about:blank" : str;
        String str9 = TextUtils.isEmpty(str5) ? "about:blank" : str5;
        if (str9.equals("about:blank")) {
            L(0);
        } else if (str9.equals(str8)) {
            L(1);
        } else {
            L(2);
        }
        It.d("Android.WebView.LoadDataWithBaseUrl.BaseUrl", N(str8), 14);
        if (str8.startsWith("data:")) {
            boolean zS = s(str4);
            if (zS) {
                str4 = null;
            }
            rdC = RD.c(str6, str7, zS, str8, str9, str4);
        } else {
            try {
                rdC = RD.c(Base64.encodeToString(str6.getBytes("utf-8"), 0), str7, true, str8, str9, "utf-8");
            } catch (UnsupportedEncodingException e) {
                Object[] objArr = {str6, e};
                Throwable thC = Ts.c(objArr);
                String strB = Ts.b("Unable to load data string %s", thC, objArr);
                if (thC != null) {
                    Log.wtf(Ts.e("AwContents"), strB, thC);
                    return;
                } else {
                    Log.wtf(Ts.e("AwContents"), strB);
                    return;
                }
            }
        }
        String packageName = this.w.getPackageName();
        if (!"com.android.email".equals(packageName) && !"com.samsung.android.email.composer".equals(packageName)) {
            z = false;
        }
        if (z && "email://".equals(rdC.h)) {
            PostTask.a(AbstractC0538bE.a, new Runnable(this, rdC) { // from class: vn
                public final AwContents s;
                public final RD t;

                {
                    this.s = this;
                    this.t = rdC;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.D(this.t);
                }
            }, 200L);
        } else {
            D(rdC);
        }
    }

    public void C(String str, Map map) {
        if (t(1) || str == null) {
            return;
        }
        if (this.x < 19 && str.startsWith("javascript:")) {
            k(str.substring(11), null);
            return;
        }
        RD rd = new RD(str, 1);
        if (map != null) {
            rd.e = new HashMap(map);
        }
        D(rd);
    }

    public void D(RD rd) {
        if (rd.h == null) {
            It.d("Android.WebView.LoadUrl.UrlScheme", N(rd.a), 14);
        }
        int i = rd.b;
        if (i == 2) {
            if (!((rd.h == null && i == 2) ? true : N.MWH2gOYe(rd.h))) {
                rd.k = true;
                N.Mt543z$z(this.s, this);
            }
        }
        String str = rd.a;
        if (str != null && str.equals(this.A.h()) && rd.c == 1) {
            rd.c = 8;
        }
        rd.c |= 134217728;
        rd.f = 2;
        Map map = rd.e;
        if (map != null) {
            Iterator it = map.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str2 = (String) it.next();
                if ("referer".equals(str2.toLowerCase(Locale.US))) {
                    rd.d = new C0984jE((String) map.remove(str2), 1);
                    rd.e = map;
                    break;
                }
            }
        }
        N.Me4$sHFE(this.s, this, rd.a, rd.d("\r\n", true));
        rd.e = new HashMap();
        this.D.e(rd);
        if (this.f0) {
            return;
        }
        this.f0 = true;
        final Callback callback = new Callback(this) { // from class: un
            public final AwContents a;

            {
                this.a = this;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                AwContents awContents = this.a;
                String[] strArr = (String[]) obj;
                Objects.requireNonNull(awContents);
                if (strArr != null) {
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        if (strArr[i2] == null) {
                            strArr[i2] = "";
                        }
                    }
                }
                PostTask.b(AbstractC0538bE.a, new Runnable(awContents, strArr) { // from class: pn
                    public final AwContents s;
                    public final String[] t;

                    {
                        this.s = awContents;
                        this.t = strArr;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AwContents awContents2 = this.s;
                        String[] strArr2 = this.t;
                        if (awContents2.t(0)) {
                            return;
                        }
                        N.MGEWt61o(awContents2.s, awContents2, strArr2);
                    }
                });
            }
        };
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.getVisitedHistory", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.getVisitedHistory(new ValueCallback(callback) { // from class: Bb
                    public final Callback a;

                    {
                        this.a = callback;
                    }

                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(Object obj) {
                        this.a.a((String[]) obj);
                    }
                });
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.getVisitedHistory");
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void E() {
        InterfaceC1840yp interfaceC1840yp = this.v0.c;
        ViewGroup viewGroup = this.u;
        interfaceC1840yp.onVisibilityChanged(viewGroup, viewGroup.getVisibility());
        interfaceC1840yp.onWindowVisibilityChanged(this.u.getWindowVisibility());
        boolean zIsAttachedToWindow = this.u.isAttachedToWindow();
        if (zIsAttachedToWindow && !this.Y) {
            interfaceC1840yp.onAttachedToWindow();
        } else if (!zIsAttachedToWindow && this.Y) {
            interfaceC1840yp.onDetachedFromWindow();
        }
        ViewGroup viewGroup2 = this.u;
        if (!(viewGroup2 instanceof C0428Xp)) {
            interfaceC1840yp.onSizeChanged(viewGroup2.getWidth(), this.u.getHeight(), 0, 0);
        }
        interfaceC1840yp.onWindowFocusChanged(this.u.hasWindowFocus());
        interfaceC1840yp.onFocusChanged(this.u.hasFocus(), 0, null);
        this.u.requestLayout();
        AutofillProvider autofillProvider = this.z0;
        if (autofillProvider != null) {
            autofillProvider.c(this.u);
        }
    }

    public void F() {
        AbstractC0827gO abstractC0827gO = this.z.a.t;
        abstractC0827gO.a.remove(this.R);
        this.u0.onDetachedFromWindow();
    }

    public void G(boolean z, int i, Rect rect) {
        if (this.x0) {
            return;
        }
        this.u0.onFocusChanged(z, i, null);
    }

    public void H() {
        if (this.V || t(0)) {
            return;
        }
        this.V = true;
        N.M11ykFVM(this.s, this, true);
        b0();
    }

    public void I() {
        if (!this.V || t(0)) {
            return;
        }
        this.V = false;
        N.M11ykFVM(this.s, this, false);
        b0();
    }

    public final void J() {
        if (this.a0) {
            return;
        }
        this.a0 = true;
        PostTask.a(AbstractC0538bE.a, this.b0, 0L);
    }

    public void K(String str, byte[] bArr) {
        if (t(1)) {
            return;
        }
        RD rd = new RD(str, 0);
        rd.b = 1;
        rd.c = 1;
        rd.g = ResourceRequestBody.createFromEncodedNativeForm(N.MugoAW_d(bArr));
        HashMap map = new HashMap();
        map.put("Content-Type", "application/x-www-form-urlencoded");
        rd.e = map;
        D(rd);
    }

    public final void M(String str, final Callback callback) {
        if (str != null && !t(1)) {
            N.MfwgZRb_(this.s, this, str, callback);
        } else {
            if (callback == null) {
                return;
            }
            PostTask.b(AbstractC0538bE.a, new Runnable(callback) { // from class: nn
                public final Callback s;

                {
                    this.s = callback;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.a(null);
                }
            });
        }
    }

    public final int N(String str) {
        if (str == null || str.equals("about:blank")) {
            return 0;
        }
        if (str.startsWith("http:")) {
            return 2;
        }
        if (str.startsWith("https:")) {
            return 3;
        }
        if (I0.matcher(str).matches()) {
            return 13;
        }
        if (str.startsWith("file:")) {
            return 4;
        }
        if (str.startsWith("ftp:")) {
            return 5;
        }
        if (str.startsWith("data:")) {
            return 6;
        }
        if (str.startsWith("javascript:")) {
            return 7;
        }
        if (str.startsWith("about:")) {
            return 8;
        }
        if (str.startsWith("chrome:")) {
            return 9;
        }
        if (str.startsWith("blob:")) {
            return 10;
        }
        if (str.startsWith("content:")) {
            return 11;
        }
        return str.startsWith("intent:") ? 12 : 1;
    }

    public final void O(ViewGroup viewGroup) {
        this.u = viewGroup;
        viewGroup.setWillNotDraw(false);
        this.y.b(this.u);
        AwPdfExporter awPdfExporter = this.t0;
        if (awPdfExporter != null) {
            awPdfExporter.mContainerView = this.u;
        }
        C0069Dp c0069Dp = this.H;
        ViewGroup viewGroup2 = this.u;
        c0069Dp.e = viewGroup2;
        viewGroup2.setClickable(true);
        Iterator it = this.U.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                E();
                return;
            }
            PopupTouchHandleDrawable popupTouchHandleDrawable = (PopupTouchHandleDrawable) ws.next();
            popupTouchHandleDrawable.w.c.clear();
            popupTouchHandleDrawable.w = new C1896zq(viewGroup);
            if (popupTouchHandleDrawable.s.isShowing()) {
                popupTouchHandleDrawable.w.a(popupTouchHandleDrawable.t);
            }
            popupTouchHandleDrawable.v = viewGroup;
        }
    }

    public void P() {
        this.N.b();
    }

    public final void Q(long j) {
        if (Build.VERSION.SDK_INT < 26) {
            R(j);
        } else {
            TextClassifier textClassifierQ = this.A != null ? q() : null;
            R(j);
            if (textClassifierQ != null) {
                V(textClassifierQ);
            }
        }
        Vw vw = this.D0;
        if (vw != null) {
            vw.e(this.A);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void R(long r19) {
        /*
            Method dump skipped, instruction units count: 445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AwContents.R(long):void");
    }

    public void S(int i) {
        if (i != 2) {
            this.Q = new C0904hq(this.w, this.u);
        } else {
            this.Q = null;
        }
    }

    public void T(int i) {
        if (i == 0 || i == 33554432) {
            this.G0 = true;
            this.F0 = true;
        } else {
            this.G0 = false;
            this.F0 = false;
        }
    }

    public void V(TextClassifier textClassifier) {
        InterfaceC0482aE interfaceC0482aE = SelectionPopupControllerImpl.t(this.A).S;
        if (interfaceC0482aE != null) {
            C1206nD c1206nD = ((SmartSelectionClient) interfaceC0482aE).b;
            c1206nD.d = textClassifier;
            Context context = (Context) c1206nD.b.S().get();
            if (context == null) {
                return;
            }
            ((TextClassificationManager) context.getSystemService("textclassification")).setTextClassifier(textClassifier);
        }
    }

    public final void W(boolean z) {
        this.W = z;
        if (!t(0)) {
            N.MJqblGS2(this.s, this, this.W);
        }
        J();
    }

    public final void X(boolean z) {
        this.w0 |= Build.VERSION.SDK_INT <= 21 && z && !this.X;
        this.X = z;
        if (!t(0)) {
            N.MkfSbdDV(this.s, this, this.X);
        }
        J();
    }

    public void Y(AwContents awContents) {
        if (t(1)) {
            return;
        }
        long jMOttaOmZ = N.MOttaOmZ(this.s, this);
        if (jMOttaOmZ == 0) {
            Ts.f("AwContents", "Popup WebView bind failed: no pending content.", new Object[0]);
            if (awContents != null) {
                awContents.i();
                return;
            }
            return;
        }
        if (awContents == null) {
            N.MQGusfGb(jMOttaOmZ);
            return;
        }
        if (awContents.t(1)) {
            return;
        }
        boolean z = awContents.Y;
        boolean z2 = awContents.W;
        boolean z3 = awContents.X;
        boolean z4 = awContents.V;
        boolean z5 = awContents.l0;
        boolean z6 = awContents.m0;
        if (z5) {
            awContents.G(false, 0, null);
        }
        if (z6) {
            awContents.u0.onWindowFocusChanged(false);
        }
        if (z2) {
            awContents.W(false);
        }
        if (z3) {
            awContents.X(false);
        }
        if (z) {
            awContents.F();
        }
        if (!z4) {
            awContents.H();
        }
        HashMap map = new HashMap();
        if (awContents.A != null) {
            map.putAll(((JavascriptInjectorImpl) awContents.o()).t);
        }
        WebMessageListenerInfo[] webMessageListenerInfoArr = (WebMessageListenerInfo[]) N.MM3s1PPD(awContents.s, awContents, WebMessageListenerInfo.class);
        awContents.Q(jMOttaOmZ);
        N.MlAm1rvf(awContents.s, awContents);
        if (!z4) {
            awContents.I();
        }
        if (z) {
            awContents.x0 = false;
            awContents.u0.onAttachedToWindow();
            awContents.z.a.t.a(awContents.R);
            awContents.postInvalidateOnAnimation();
        }
        awContents.u0.onSizeChanged(awContents.u.getWidth(), awContents.u.getHeight(), 0, 0);
        if (z3) {
            awContents.X(true);
        }
        if (z2) {
            awContents.W(true);
        }
        if (z6) {
            awContents.u0.onWindowFocusChanged(z6);
        }
        if (z5) {
            awContents.G(true, 0, null);
        }
        awContents.g0 = true;
        for (Map.Entry entry : map.entrySet()) {
            ((JavascriptInjectorImpl) awContents.o()).a(((Pair) entry.getValue()).first, (String) entry.getKey(), (Class) ((Pair) entry.getValue()).second);
        }
        if (webMessageListenerInfoArr != null) {
            for (WebMessageListenerInfo webMessageListenerInfo : webMessageListenerInfoArr) {
                awContents.e(webMessageListenerInfo.a, webMessageListenerInfo.b, webMessageListenerInfo.c.a);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Z() {
        /*
            r5 = this;
            boolean r0 = r5.d0
            r1 = 0
            r2 = 1
            r3 = 2
            if (r0 == 0) goto Lc
            boolean r0 = r5.Z
            if (r0 != 0) goto Lc
            goto L19
        Lc:
            int r0 = r5.c0
            r4 = -1
            if (r0 == r4) goto L18
            if (r0 == 0) goto L19
            if (r0 == r2) goto L16
            goto L18
        L16:
            r1 = 1
            goto L19
        L18:
            r1 = 2
        L19:
            org.chromium.content_public.browser.WebContents r0 = r5.A
            r0.G(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AwContents.Z():void");
    }

    public void a0() {
        String defaultLocaleListString = LocaleUtils.getDefaultLocaleListString();
        if (K0.equals(defaultLocaleListString)) {
            return;
        }
        K0 = defaultLocaleListString;
        N.Mer1B1Jd(LocaleUtils.getDefaultLocaleString(), K0);
        final AwSettings awSettings = this.S;
        synchronized (awSettings.f) {
            awSettings.m0.a(new Runnable(awSettings) { // from class: np
                public final AwSettings s;

                {
                    this.s = awSettings;
                }

                @Override // java.lang.Runnable
                public void run() {
                    AwSettings awSettings2 = this.s;
                    long j = awSettings2.l0;
                    if (j != 0) {
                        N.MbLkcpTV(j, awSettings2);
                    }
                }
            });
        }
    }

    public final void b0() {
        this.a0 = false;
        if (t(0)) {
            return;
        }
        boolean zM5$3H1S$ = N.M5$3H1S$(this.s, this);
        if (zM5$3H1S$ && !this.Z) {
            this.A.N();
        } else if (!zM5$3H1S$ && this.Z) {
            this.A.w();
        }
        this.Z = zM5$3H1S$;
        Z();
    }

    public void c0(float f) {
        if (t(1)) {
            return;
        }
        if (f < 0.01f || f > 100.0f) {
            throw new IllegalStateException("zoom delta value outside [0.01, 100] range.");
        }
        N.MuRWIe$h(this.s, this, f);
    }

    public boolean canShowInterstitial() {
        return this.Y && this.W;
    }

    public void e(String str, String[] strArr, InterfaceC0016Aq interfaceC0016Aq) {
        if (interfaceC0016Aq == null) {
            throw new NullPointerException("listener shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("jsObjectName shouldn't be null or empty string");
        }
        for (int i = 0; i < strArr.length; i++) {
            if (TextUtils.isEmpty(strArr[i])) {
                throw new IllegalArgumentException("allowedOriginRules[" + i + "] is null or empty");
            }
        }
        String strMxPw2eDG = N.MxPw2eDG(this.s, this, new WebMessageListenerHolder(interfaceC0016Aq), str, strArr);
        if (!TextUtils.isEmpty(strMxPw2eDG)) {
            throw new IllegalArgumentException(strMxPw2eDG);
        }
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void extractSmartClipData(int i, int i2, int i3, int i4) {
        if (t(1)) {
            return;
        }
        this.A.K(i, i2, i3, i4);
    }

    public boolean f() {
        return !t(1) && this.p0 - this.n0 > 0.007f;
    }

    public boolean g() {
        return !t(1) && this.n0 - this.o0 > 0.007f;
    }

    public Picture h() {
        if (t(1)) {
            return null;
        }
        long j = this.s;
        C0463Zo c0463Zo = this.P;
        return new C0358Tr(N.MpPu_UgV(j, this, c0463Zo.f + c0463Zo.d, c0463Zo.d()));
    }

    public void i() {
        if (t(0)) {
            return;
        }
        Vw vw = this.D0;
        if (vw != null) {
            vw.e(null);
            this.D0 = null;
        }
        this.E.a.d.removeCallbacksAndMessages(null);
        if (this.Y) {
            Ts.f("AwContents", "WebView.destroy() called while WebView is still attached to window.", new Object[0]);
            F();
        }
        this.y0 = true;
        PostTask.a(AbstractC0538bE.a, new Runnable(this) { // from class: sn
            public final AwContents s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.j();
            }
        }, 0L);
    }

    public void invokeVisualStateCallback(final VisualStateCallback visualStateCallback, final long j) {
        if (t(0)) {
            return;
        }
        PostTask.a(AbstractC0538bE.a, new Runnable(visualStateCallback, j) { // from class: xn
            public final AwContents.VisualStateCallback s;
            public final long t;

            {
                this.s = visualStateCallback;
                this.t = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.a(this.t);
            }
        }, 0L);
    }

    public final void j() {
        if (this.E0 != null) {
            this.F.destroy();
            this.F = null;
            this.s = 0L;
            this.A = null;
            this.B0 = null;
            this.D = null;
            this.E0.b(2);
            this.E0 = null;
        }
    }

    public void k(String str, final Callback callback) {
        if (t(1)) {
            return;
        }
        this.A.g(str, callback != null ? new JavaScriptCallback(callback) { // from class: wn
            public final Callback a;

            {
                this.a = callback;
            }

            @Override // org.chromium.content_public.browser.JavaScriptCallback
            public void a(String str2) {
                AwContents.x(this.a, str2);
            }
        } : null);
    }

    public final float m() {
        return this.z.a.t.d;
    }

    public int n() {
        if (!t(0)) {
            if (this.E.b != 0) {
                return this.E.b;
            }
        }
        return this.h0;
    }

    public final QD o() {
        if (this.C0 == null) {
            this.C0 = (QD) ((WebContentsImpl) this.A).S(JavascriptInjectorImpl.class, AbstractC1301oz.a);
        }
        return this.C0;
    }

    public void onFindResultReceived(int i, int i2, boolean z) {
        C0252Ob c0252Ob = (C0252Ob) this.E;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onFindResultReceived", null);
            if (c0252Ob.l != null) {
                c0252Ob.l.onFindResultReceived(i, i2, z);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onFindResultReceived");
        }
    }

    public void onNewPicture() {
        C1839yo c1839yo = this.E.a;
        Callable callable = this.k0;
        if (c1839yo.b) {
            return;
        }
        c1839yo.b = true;
        long jMax = Math.max(c1839yo.a + 500, SystemClock.uptimeMillis());
        Handler handler = c1839yo.d;
        handler.sendMessageAtTime(handler.obtainMessage(6, callable), jMax);
    }

    public boolean onRenderProcessGone(int i, boolean z) {
        boolean zB = false;
        if (t(0)) {
            return true;
        }
        AbstractC0511ao abstractC0511ao = this.E;
        C0427Xo c0427Xo = new C0427Xo(z, N.MmkSmonh(this.s, this));
        C0252Ob c0252Ob = (C0252Ob) abstractC0511ao;
        Objects.requireNonNull(c0252Ob);
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                TraceEvent.x("WebViewContentsClientAdapter.onRenderProcessGone", null);
                zB = C1201n8.b(c0252Ob.h, c0252Ob.e, c0427Xo);
            } finally {
                TraceEvent.A("WebViewContentsClientAdapter.onRenderProcessGone");
            }
        }
        return zB;
    }

    public String p() {
        VD vdG;
        int i;
        if (!t(1) && (i = (vdG = this.D.g()).b) >= 0 && i < vdG.a()) {
            return ((NavigationEntry) vdG.a.get(i)).b;
        }
        return null;
    }

    public TextClassifier q() {
        InterfaceC0482aE interfaceC0482aE = SelectionPopupControllerImpl.t(this.A).S;
        if (interfaceC0482aE == null) {
            return null;
        }
        return ((SmartSelectionClient) interfaceC0482aE).b.a();
    }

    public final InterfaceC0817gE r() {
        return WebContentsAccessibilityImpl.i(this.A);
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void setSmartClipResultHandler(Handler handler) {
        if (t(1)) {
            return;
        }
        this.A.setSmartClipResultHandler(handler);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t(int r5) {
        /*
            r4 = this;
            boolean r0 = r4.y0
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L18
            if (r5 != r2) goto L18
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            r5[r1] = r0
            java.lang.String r0 = "AwContents"
            java.lang.String r3 = "Application attempted to call on a destroyed WebView"
            defpackage.Ts.f(r0, r3, r5)
        L18:
            Np r5 = r4.E0
            if (r5 == 0) goto L27
            java.lang.Runnable r5 = r5.a
            if (r5 != 0) goto L22
            r5 = 1
            goto L23
        L22:
            r5 = 0
        L23:
            if (r5 == 0) goto L27
            r5 = 1
            goto L28
        L27:
            r5 = 0
        L28:
            Sn r0 = r4.C
            if (r0 == 0) goto L3b
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            if (r0 != 0) goto L36
            r0 = 1
            goto L37
        L36:
            r0 = 0
        L37:
            if (r0 == 0) goto L3b
            r0 = 1
            goto L3c
        L3b:
            r0 = 0
        L3c:
            boolean r3 = r4.y0
            if (r3 != 0) goto L44
            if (r5 != 0) goto L44
            if (r0 == 0) goto L45
        L44:
            r1 = 1
        L45:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AwContents.t(int):boolean");
    }

    public boolean v() {
        return this.v0.d != null;
    }
}
