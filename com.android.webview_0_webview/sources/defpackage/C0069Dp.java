package defpackage;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import java.util.Objects;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwSettings;
import org.chromium.android_webview.AwWebContentsDelegate;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: renamed from: Dp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0069Dp extends AwWebContentsDelegate {
    public final AwContents a;
    public final AbstractC0511ao b;
    public final AwSettings c;
    public final Context d;
    public View e;
    public FrameLayout f;

    public C0069Dp(AwContents awContents, AbstractC0511ao abstractC0511ao, AwSettings awSettings, Context context, View view) {
        this.a = awContents;
        this.b = abstractC0511ao;
        this.c = awSettings;
        this.d = context;
        this.e = view;
        view.setClickable(true);
    }

    public final boolean a(int i) {
        View viewFocusSearch = this.e.focusSearch(i);
        return (viewFocusSearch == null || viewFocusSearch == this.e || !viewFocusSearch.requestFocus()) ? false : true;
    }

    @Override // org.chromium.android_webview.AwWebContentsDelegate, org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void activateContents() {
        C0252Ob c0252Ob = (C0252Ob) this.b;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onRequestFocus", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.onRequestFocus(c0252Ob.e);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onRequestFocus");
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        int i3;
        ConsoleMessage.MessageLevel messageLevel;
        boolean zOnConsoleMessage = false;
        if (i == 0) {
            i3 = 0;
        } else if (i == 1) {
            i3 = 1;
        } else if (i == 2) {
            i3 = 2;
        } else if (i != 3) {
            Log.w("AwWebContentsDelegateAdapter", "Unknown message level, defaulting to DEBUG");
            i3 = 4;
        } else {
            i3 = 3;
        }
        C0252Ob c0252Ob = (C0252Ob) this.b;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onConsoleMessage", null);
            if (c0252Ob.k != null) {
                WebChromeClient webChromeClient = c0252Ob.k;
                if (i3 == 0) {
                    messageLevel = ConsoleMessage.MessageLevel.TIP;
                } else if (i3 == 1) {
                    messageLevel = ConsoleMessage.MessageLevel.LOG;
                } else if (i3 == 2) {
                    messageLevel = ConsoleMessage.MessageLevel.WARNING;
                } else if (i3 == 3) {
                    messageLevel = ConsoleMessage.MessageLevel.ERROR;
                } else {
                    if (i3 != 4) {
                        throw new IllegalArgumentException("Unsupported value: " + i3);
                    }
                    messageLevel = ConsoleMessage.MessageLevel.DEBUG;
                }
                zOnConsoleMessage = webChromeClient.onConsoleMessage(new ConsoleMessage(str, str2, i2, messageLevel));
            }
            return zOnConsoleMessage;
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onConsoleMessage");
        }
    }

    @Override // org.chromium.android_webview.AwWebContentsDelegate
    public boolean addNewContents(boolean z, boolean z2) {
        C0252Ob c0252Ob = (C0252Ob) this.b;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onCreateWindow", null);
            Handler handler = c0252Ob.p;
            WebView webView = c0252Ob.e;
            webView.getClass();
            return c0252Ob.k != null ? c0252Ob.k.onCreateWindow(c0252Ob.e, z, z2, handler.obtainMessage(100, new WebView.WebViewTransport(webView))) : false;
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onCreateWindow");
        }
    }

    @Override // org.chromium.android_webview.AwWebContentsDelegate, org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void closeContents() {
        C0252Ob c0252Ob = (C0252Ob) this.b;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onCloseWindow", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.onCloseWindow(c0252Ob.e);
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onCloseWindow");
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void enterFullscreenModeForTab(boolean z) {
        C0428Xp c0428Xp;
        if (this.a.v()) {
            return;
        }
        AwContents awContents = this.a;
        if (awContents.t(0)) {
            c0428Xp = null;
        } else {
            awContents.F();
            c0428Xp = new C0428Xp(awContents.w, awContents.u0, awContents);
            c0428Xp.setFocusable(true);
            c0428Xp.setFocusableInTouchMode(true);
            boolean zIsFocused = awContents.u.isFocused();
            if (zIsFocused) {
                c0428Xp.requestFocus();
            }
            C0211Ln c0211Ln = awContents.v0;
            C0463Zo c0463Zo = awContents.P;
            int i = c0463Zo.b;
            int i2 = c0463Zo.c;
            c0211Ln.d = c0428Xp;
            c0211Ln.e = zIsFocused;
            c0211Ln.f = i;
            c0211Ln.g = i2;
            awContents.u0 = new C0848gq(awContents, awContents.L, awContents.u);
            C0410Wp c0410Wp = c0428Xp.u;
            awContents.L = c0410Wp;
            ((Wz) awContents.B).h(c0410Wp);
            awContents.O(c0428Xp);
        }
        if (c0428Xp == null) {
            return;
        }
        final C1895zp c1895zp = new C1895zp(this);
        FrameLayout frameLayout = new FrameLayout(this.d);
        this.f = frameLayout;
        frameLayout.addView(c0428Xp);
        AbstractC0511ao abstractC0511ao = this.b;
        FrameLayout frameLayout2 = this.f;
        C0252Ob c0252Ob = (C0252Ob) abstractC0511ao;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onShowCustomView", null);
            if (c0252Ob.k != null) {
                c0252Ob.k.onShowCustomView(frameLayout2, new WebChromeClient.CustomViewCallback(c1895zp) { // from class: Eb
                    public final InterfaceC0444Yn a;

                    {
                        this.a = c1895zp;
                    }

                    @Override // android.webkit.WebChromeClient.CustomViewCallback
                    public void onCustomViewHidden() {
                        C0069Dp c0069Dp = ((C1895zp) this.a).a;
                        if (c0069Dp.f != null) {
                            AwContents awContents2 = c0069Dp.a;
                            if (awContents2.t(0)) {
                                return;
                            }
                            awContents2.A.t();
                        }
                    }
                });
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onShowCustomView");
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void exitFullscreenModeForTab() {
        if (this.f != null) {
            this.f = null;
            AwContents awContents = this.a;
            if (awContents.v() && !awContents.t(0)) {
                InterfaceC1840yp interfaceC1840yp = awContents.v0.c;
                interfaceC1840yp.onDetachedFromWindow();
                C0428Xp c0428Xp = awContents.v0.d;
                c0428Xp.s = new C0848gq(awContents, c0428Xp.u, c0428Xp);
                awContents.u0 = interfaceC1840yp;
                C0211Ln c0211Ln = awContents.v0;
                ViewGroup viewGroup = c0211Ln.a;
                InterfaceC0264On interfaceC0264On = c0211Ln.b;
                awContents.L = interfaceC0264On;
                ((Wz) awContents.B).h(interfaceC0264On);
                awContents.O(viewGroup);
                if (awContents.v0.e) {
                    awContents.u.requestFocus();
                }
                if (!awContents.t(0)) {
                    long j = awContents.s;
                    C0211Ln c0211Ln2 = awContents.v0;
                    N.MFKs48sP(j, awContents, c0211Ln2.f, c0211Ln2.g);
                }
                awContents.v0.d = null;
            }
            C0252Ob c0252Ob = (C0252Ob) this.b;
            Objects.requireNonNull(c0252Ob);
            try {
                TraceEvent.x("WebViewContentsClientAdapter.onHideCustomView", null);
                if (c0252Ob.k != null) {
                    c0252Ob.k.onHideCustomView();
                }
            } finally {
                TraceEvent.A("WebViewContentsClientAdapter.onHideCustomView");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0036 A[FALL_THROUGH] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleKeyboardEvent(android.view.KeyEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            if (r0 != 0) goto L23
            int r0 = r4.getKeyCode()
            switch(r0) {
                case 19: goto L18;
                case 20: goto L15;
                case 21: goto L12;
                case 22: goto Lf;
                default: goto Ld;
            }
        Ld:
            r0 = 0
            goto L1a
        Lf:
            r0 = 66
            goto L1a
        L12:
            r0 = 17
            goto L1a
        L15:
            r0 = 130(0x82, float:1.82E-43)
            goto L1a
        L18:
            r0 = 33
        L1a:
            if (r0 == 0) goto L23
            boolean r0 = r3.a(r0)
            if (r0 == 0) goto L23
            return
        L23:
            int r0 = r4.getKeyCode()
            r1 = 79
            if (r0 == r1) goto L36
            r1 = 222(0xde, float:3.11E-43)
            if (r0 == r1) goto L36
            switch(r0) {
                case 85: goto L36;
                case 86: goto L36;
                case 87: goto L36;
                case 88: goto L36;
                case 89: goto L36;
                case 90: goto L36;
                case 91: goto L36;
                default: goto L32;
            }
        L32:
            switch(r0) {
                case 126: goto L36;
                case 127: goto L36;
                case 128: goto L36;
                case 129: goto L36;
                case 130: goto L36;
                default: goto L35;
            }
        L35:
            goto L43
        L36:
            android.content.Context r0 = r3.d
            java.lang.String r1 = "audio"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.media.AudioManager r0 = (android.media.AudioManager) r0
            r0.dispatchMediaKeyEvent(r4)
        L43:
            ao r0 = r3.b
            Ob r0 = (defpackage.C0252Ob) r0
            java.util.Objects.requireNonNull(r0)
            java.lang.String r1 = "WebViewContentsClientAdapter.onUnhandledKeyEvent"
            r2 = 0
            org.chromium.base.TraceEvent.x(r1, r2)     // Catch: java.lang.Throwable -> L5b
            android.webkit.WebViewClient r2 = r0.h     // Catch: java.lang.Throwable -> L5b
            android.webkit.WebView r0 = r0.e     // Catch: java.lang.Throwable -> L5b
            r2.onUnhandledKeyEvent(r0, r4)     // Catch: java.lang.Throwable -> L5b
            org.chromium.base.TraceEvent.A(r1)
            return
        L5b:
            r4 = move-exception
            org.chromium.base.TraceEvent.A(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0069Dp.handleKeyboardEvent(android.view.KeyEvent):void");
    }

    @Override // org.chromium.android_webview.AwWebContentsDelegate
    public void loadingStateChanged() {
        AbstractC0511ao abstractC0511ao = this.b;
        AwContents awContents = this.a;
        abstractC0511ao.g(awContents.t(1) ? null : awContents.A.getTitle(), false);
    }

    @Override // org.chromium.android_webview.AwWebContentsDelegate, org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void navigationStateChanged(int i) {
        String strH;
        if ((i & 1) != 0) {
            AwContents awContents = this.a;
            if (awContents.g0) {
                if (awContents.t(0) ? false : awContents.A.I()) {
                    AwContents awContents2 = this.a;
                    String str = null;
                    if (!awContents2.t(0) && (strH = awContents2.A.h()) != null && !strH.trim().isEmpty()) {
                        str = strH;
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = "about:blank";
                    }
                    Handler handler = this.b.a.d;
                    handler.sendMessage(handler.obtainMessage(12, str));
                }
            }
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void onUpdateUrl(String str) {
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void openNewTab(String str, String str2, ResourceRequestBody resourceRequestBody, int i, boolean z) {
    }

    @Override // org.chromium.android_webview.AwWebContentsDelegate
    public void runFileChooser(int i, int i2, int i3, String str, String str2, String str3, boolean z) {
        C0462Zn c0462Zn = new C0462Zn(i3, str, str2, str3, z);
        AbstractC0511ao abstractC0511ao = this.b;
        C0033Bp c0033Bp = new C0033Bp(this, i, i2, i3);
        C0252Ob c0252Ob = (C0252Ob) abstractC0511ao;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.showFileChooser", null);
            if (c0252Ob.k == null) {
                c0033Bp.a(null);
            } else {
                if (!c0252Ob.k.onShowFileChooser(c0252Ob.e, new C0127Hb(c0252Ob, c0033Bp), new C0163Jb(c0462Zn))) {
                    if (c0252Ob.g.getApplicationInfo().targetSdkVersion >= 21) {
                        c0033Bp.a(null);
                    } else {
                        c0252Ob.k.openFileChooser(new C0145Ib(c0252Ob, c0033Bp), c0462Zn.b, c0462Zn.e ? "*" : "");
                    }
                }
            }
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.showFileChooser");
        }
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldBlockMediaRequest(String str) {
        AwSettings awSettings = this.c;
        if (awSettings != null) {
            return awSettings.g() && URLUtil.isNetworkUrl(str);
        }
        return true;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void showRepostFormWarningDialog() {
        HandlerC0015Ap handlerC0015Ap = new HandlerC0015Ap(this, ThreadUtils.d());
        Message messageObtainMessage = handlerC0015Ap.obtainMessage(1);
        Message messageObtainMessage2 = handlerC0015Ap.obtainMessage(2);
        C1839yo c1839yo = this.b.a;
        C1619uo c1619uo = new C1619uo(messageObtainMessage2, messageObtainMessage);
        Handler handler = c1839yo.d;
        handler.sendMessage(handler.obtainMessage(14, c1619uo));
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean takeFocus(boolean z) {
        if (a(z == (this.e.getLayoutDirection() == 1) ? 66 : 17)) {
            return true;
        }
        return a(z ? 1 : 2);
    }
}
