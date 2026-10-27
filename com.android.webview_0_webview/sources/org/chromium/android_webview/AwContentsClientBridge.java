package org.chromium.android_webview;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.webkit.JsPromptResult;
import defpackage.AbstractC0229Mo;
import defpackage.AbstractC0511ao;
import defpackage.C0199Lb;
import defpackage.C0252Ob;
import defpackage.C0266Op;
import defpackage.C0284Pp;
import defpackage.C0426Xn;
import defpackage.C0645d9;
import defpackage.C1179mn;
import defpackage.C1235no;
import defpackage.C1240nt;
import defpackage.C1454ro;
import defpackage.C1509so;
import defpackage.C1729wo;
import defpackage.C1839yo;
import defpackage.InterfaceC1345po;
import defpackage.Is;
import defpackage.Lt;
import defpackage.T8;
import defpackage.Ts;
import defpackage.U8;
import defpackage.Z7;
import java.util.HashMap;
import java.util.Objects;
import javax.security.auth.x500.X500Principal;
import org.chromium.android_webview.AwContentsClientBridge;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwContentsClientBridge {
    public AbstractC0511ao a;
    public Context b;
    public long c;
    public final C0284Pp d;

    public AwContentsClientBridge(Context context, AbstractC0511ao abstractC0511ao, C0284Pp c0284Pp) {
        this.b = context;
        this.a = abstractC0511ao;
        this.d = c0284Pp;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean allowCertificateError(int r2, byte[] r3, java.lang.String r4, final int r5) {
        /*
            r1 = this;
            android.net.http.SslCertificate r3 = defpackage.AbstractC1402qq.a(r3)
            if (r3 != 0) goto L8
            r2 = 0
            return r2
        L8:
            r0 = -217(0xffffffffffffff27, float:NaN)
            if (r2 == r0) goto L24
            switch(r2) {
                case -202: goto L24;
                case -201: goto L1d;
                case -200: goto L16;
                default: goto Lf;
            }
        Lf:
            android.net.http.SslError r2 = new android.net.http.SslError
            r0 = 5
            r2.<init>(r0, r3, r4)
            goto L2a
        L16:
            android.net.http.SslError r2 = new android.net.http.SslError
            r0 = 2
            r2.<init>(r0, r3, r4)
            goto L2a
        L1d:
            android.net.http.SslError r2 = new android.net.http.SslError
            r0 = 4
            r2.<init>(r0, r3, r4)
            goto L2a
        L24:
            android.net.http.SslError r2 = new android.net.http.SslError
            r0 = 3
            r2.<init>(r0, r3, r4)
        L2a:
            bo r3 = new bo
            r3.<init>(r1, r5)
            android.os.Handler r4 = new android.os.Handler
            r4.<init>()
            co r5 = new co
            r5.<init>(r1, r3, r2)
            r4.post(r5)
            int r2 = r2.getPrimaryError()
            Gt r3 = defpackage.Lt.a
            java.lang.String r4 = "Android.WebView.onReceivedSslError.ErrorCode"
            r3.a(r4, r2)
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AwContentsClientBridge.allowCertificateError(int, byte[], java.lang.String, int):boolean");
    }

    private void handleJsAlert(final String str, final String str2, final int i) {
        new Handler().post(new Runnable(this, i, str, str2) { // from class: do
            public final AwContentsClientBridge s;
            public final int t;
            public final String u;
            public final String v;

            {
                this.s = this;
                this.t = i;
                this.u = str;
                this.v = str2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AwContentsClientBridge awContentsClientBridge = this.s;
                int i2 = this.t;
                String str3 = this.u;
                String str4 = this.v;
                C0736eq c0736eq = new C0736eq(awContentsClientBridge, i2);
                C0252Ob c0252Ob = (C0252Ob) awContentsClientBridge.a;
                Objects.requireNonNull(c0252Ob);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.handleJsAlert", null);
                    if (c0252Ob.k != null) {
                        JsPromptResult jsPromptResult = new C0216Mb((InterfaceC0792fq) c0736eq).c;
                        if (!c0252Ob.k.onJsAlert(c0252Ob.e, str3, str4, jsPromptResult) && !c0252Ob.h(jsPromptResult, 1, null, str4, str3)) {
                            c0736eq.a();
                        }
                    } else {
                        c0736eq.a();
                    }
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.handleJsAlert");
                }
            }
        });
    }

    private void handleJsBeforeUnload(final String str, final String str2, final int i) {
        new Handler().post(new Runnable(this, i, str, str2) { // from class: go
            public final AwContentsClientBridge s;
            public final int t;
            public final String u;
            public final String v;

            {
                this.s = this;
                this.t = i;
                this.u = str;
                this.v = str2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AwContentsClientBridge awContentsClientBridge = this.s;
                int i2 = this.t;
                String str3 = this.u;
                String str4 = this.v;
                C0736eq c0736eq = new C0736eq(awContentsClientBridge, i2);
                C0252Ob c0252Ob = (C0252Ob) awContentsClientBridge.a;
                Objects.requireNonNull(c0252Ob);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.handleJsBeforeUnload", null);
                    if (c0252Ob.k != null) {
                        JsPromptResult jsPromptResult = new C0216Mb((InterfaceC0792fq) c0736eq).c;
                        if (!c0252Ob.k.onJsBeforeUnload(c0252Ob.e, str3, str4, jsPromptResult) && !c0252Ob.h(jsPromptResult, 4, null, str4, str3)) {
                            c0736eq.a();
                        }
                    } else {
                        c0736eq.a();
                    }
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.handleJsBeforeUnload");
                }
            }
        });
    }

    private void handleJsConfirm(final String str, final String str2, final int i) {
        new Handler().post(new Runnable(this, i, str, str2) { // from class: eo
            public final AwContentsClientBridge s;
            public final int t;
            public final String u;
            public final String v;

            {
                this.s = this;
                this.t = i;
                this.u = str;
                this.v = str2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AwContentsClientBridge awContentsClientBridge = this.s;
                int i2 = this.t;
                String str3 = this.u;
                String str4 = this.v;
                C0736eq c0736eq = new C0736eq(awContentsClientBridge, i2);
                C0252Ob c0252Ob = (C0252Ob) awContentsClientBridge.a;
                Objects.requireNonNull(c0252Ob);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.handleJsConfirm", null);
                    if (c0252Ob.k != null) {
                        JsPromptResult jsPromptResult = new C0216Mb((InterfaceC0792fq) c0736eq).c;
                        if (!c0252Ob.k.onJsConfirm(c0252Ob.e, str3, str4, jsPromptResult) && !c0252Ob.h(jsPromptResult, 2, null, str4, str3)) {
                            c0736eq.a();
                        }
                    } else {
                        c0736eq.a();
                    }
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.handleJsConfirm");
                }
            }
        });
    }

    private void handleJsPrompt(final String str, final String str2, final String str3, final int i) {
        new Handler().post(new Runnable(this, i, str, str2, str3) { // from class: fo
            public final AwContentsClientBridge s;
            public final int t;
            public final String u;
            public final String v;
            public final String w;

            {
                this.s = this;
                this.t = i;
                this.u = str;
                this.v = str2;
                this.w = str3;
            }

            @Override // java.lang.Runnable
            public void run() {
                AwContentsClientBridge awContentsClientBridge = this.s;
                int i2 = this.t;
                String str4 = this.u;
                String str5 = this.v;
                String str6 = this.w;
                C0736eq c0736eq = new C0736eq(awContentsClientBridge, i2);
                C0252Ob c0252Ob = (C0252Ob) awContentsClientBridge.a;
                Objects.requireNonNull(c0252Ob);
                try {
                    TraceEvent.x("WebViewContentsClientAdapter.handleJsPrompt", null);
                    if (c0252Ob.k != null) {
                        JsPromptResult jsPromptResult = new C0216Mb((InterfaceC0513aq) c0736eq).c;
                        if (!c0252Ob.k.onJsPrompt(c0252Ob.e, str4, str5, str6, jsPromptResult) && !c0252Ob.h(jsPromptResult, 3, str6, str5, str4)) {
                            c0736eq.a();
                        }
                    } else {
                        c0736eq.a();
                    }
                } finally {
                    TraceEvent.A("WebViewContentsClientAdapter.handleJsPrompt");
                }
            }
        });
    }

    private void newDownload(String str, String str2, String str3, String str4, long j) {
        C1839yo c1839yo = this.a.a;
        C1454ro c1454ro = new C1454ro(str, str2, str3, str4, j);
        Handler handler = c1839yo.d;
        handler.sendMessage(handler.obtainMessage(3, c1454ro));
        AbstractC0229Mo.a(3);
    }

    private void newLoginRequest(String str, String str2, String str3) {
        C1839yo c1839yo = this.a.a;
        C1509so c1509so = new C1509so(str, str2, str3);
        Handler handler = c1839yo.d;
        handler.sendMessage(handler.obtainMessage(4, c1509so));
        AbstractC0229Mo.a(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onReceivedError(java.lang.String r10, boolean r11, boolean r12, boolean r13, java.lang.String r14, java.lang.String[] r15, java.lang.String[] r16, int r17, java.lang.String r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AwContentsClientBridge.onReceivedError(java.lang.String, boolean, boolean, boolean, java.lang.String, java.lang.String[], java.lang.String[], int, java.lang.String, boolean, boolean):void");
    }

    private void onReceivedHttpError(String str, boolean z, boolean z2, String str2, String[] strArr, String[] strArr2, String str3, String str4, int i, String str5, String[] strArr3, String[] strArr4) {
        C0426Xn c0426Xn = new C0426Xn(str, z, z2, str2, strArr, strArr2);
        HashMap map = new HashMap(strArr3.length);
        for (int i2 = 0; i2 < strArr3.length; i2++) {
            if (!map.containsKey(strArr3[i2])) {
                map.put(strArr3[i2], strArr4[i2]);
            } else if (!strArr4[i2].isEmpty()) {
                String strC = (String) map.get(strArr3[i2]);
                if (!strC.isEmpty()) {
                    strC = Z7.c(strC, ", ");
                }
                String str6 = strArr3[i2];
                StringBuilder sbF = Z7.f(strC);
                sbF.append(strArr4[i2]);
                map.put(str6, sbF.toString());
            }
        }
        AwWebResourceResponse awWebResourceResponse = new AwWebResourceResponse(str3, str4, null, i, str5, map);
        C1839yo c1839yo = this.a.a;
        C1729wo c1729wo = new C1729wo(c0426Xn, awWebResourceResponse);
        Handler handler = c1839yo.d;
        handler.sendMessage(handler.obtainMessage(8, c1729wo));
        Lt.a.a("Android.WebView.onReceivedHttpError.StatusCode", i);
    }

    private void setNativeContentsClientBridge(long j) {
        this.c = j;
    }

    private boolean shouldOverrideUrlLoading(String str, boolean z, boolean z2, boolean z3) {
        AbstractC0511ao abstractC0511ao = this.a;
        Context context = this.b;
        InterfaceC1345po interfaceC1345po = abstractC0511ao.a.e;
        if (interfaceC1345po != null && ((C1179mn) interfaceC1345po).a.t(0)) {
            return false;
        }
        U8 u8 = (U8) abstractC0511ao;
        if (u8.h != T8.g) {
            C0426Xn c0426Xn = new C0426Xn(str, z3, z, "GET", null);
            c0426Xn.d = z2;
            try {
                TraceEvent.x("WebViewContentsClientAdapter.shouldOverrideUrlLoading", null);
                boolean zShouldOverrideUrlLoading = u8.i.a("SHOULD_OVERRIDE_WITH_REDIRECTS") ? u8.i.a.shouldOverrideUrlLoading(u8.e, new C0645d9(c0426Xn)) : Build.VERSION.SDK_INT >= 24 ? C1240nt.h(u8.h, u8.e, new C0645d9(c0426Xn)) : u8.h.shouldOverrideUrlLoading(u8.e, c0426Xn.a);
                AbstractC0229Mo.a(8);
                return zShouldOverrideUrlLoading;
            } finally {
                TraceEvent.A("WebViewContentsClientAdapter.shouldOverrideUrlLoading");
            }
        }
        if (!z && !z2) {
            Ts.f("AwContentsClient", "Denied starting an intent without a user gesture, URI %s", str);
        } else {
            if (str.startsWith("about:") || str.startsWith("chrome://") || AbstractC0511ao.d.matcher(str).matches()) {
                return false;
            }
            try {
                Intent uri = Intent.parseUri(str, 1);
                uri.addCategory("android.intent.category.BROWSABLE");
                uri.setComponent(null);
                Intent selector = uri.getSelector();
                if (selector != null) {
                    selector.addCategory("android.intent.category.BROWSABLE");
                    selector.setComponent(null);
                }
                uri.putExtra("com.android.browser.application_id", context.getPackageName());
                if (Is.a(context) == null) {
                    Ts.f("AwContentsClient", "Cannot call startActivity on non-activity context.", new Object[0]);
                    return false;
                }
                try {
                    context.startActivity(uri);
                } catch (ActivityNotFoundException unused) {
                    Ts.f("AwContentsClient", "No application can handle %s", str);
                    return false;
                } catch (SecurityException unused2) {
                    Ts.f("AwContentsClient", "SecurityException when starting intent for %s", str);
                    return false;
                }
            } catch (Exception e) {
                Ts.f("AwContentsClient", "Bad URI %s", str, e);
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b A[PHI: r3
  0x003b: PHI (r3v3 int) = (r3v2 int), (r3v5 int) binds: [B:3:0x0019, B:5:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSafeBrowsingHit(java.lang.String r11, boolean r12, boolean r13, java.lang.String r14, java.lang.String[] r15, java.lang.String[] r16, int r17, final int r18) {
        /*
            r10 = this;
            r0 = r10
            r1 = r17
            Xn r9 = new Xn
            r2 = r9
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r8 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8)
            ho r2 = new ho
            r3 = r18
            r2.<init>(r10, r3)
            r3 = 2
            r4 = 0
            if (r1 == r3) goto L3b
            r3 = 1
            r5 = 3
            if (r1 == r5) goto L3b
            r6 = 4
            if (r1 == r6) goto L39
            r5 = 22
            if (r1 == r5) goto L27
            goto L3c
        L27:
            android.content.Context r1 = defpackage.Is.a
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()
            int r1 = r1.targetSdkVersion
            r5 = 29
            if (r1 < r5) goto L34
            goto L35
        L34:
            r3 = 0
        L35:
            if (r3 == 0) goto L3c
            r4 = 4
            goto L3c
        L39:
            r4 = 3
            goto L3c
        L3b:
            r4 = r3
        L3c:
            ao r1 = r0.a
            yo r1 = r1.a
            xo r3 = new xo
            r3.<init>(r9, r4, r2)
            android.os.Handler r1 = r1.d
            r2 = 15
            android.os.Message r2 = r1.obtainMessage(r2, r3)
            r1.sendMessage(r2)
            r1 = 5
            java.lang.String r2 = "Android.WebView.onSafeBrowsingHit.ThreatType"
            defpackage.It.d(r2, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.android_webview.AwContentsClientBridge.onSafeBrowsingHit(java.lang.String, boolean, boolean, java.lang.String, java.lang.String[], java.lang.String[], int, int):void");
    }

    public void selectClientCertificate(int i, String[] strArr, byte[][] bArr, String str, int i2) {
        X500Principal[] x500PrincipalArr;
        C0266Op c0266Op = (C0266Op) this.d.a.get(C0284Pp.a(str, i2));
        if (this.d.b.contains(C0284Pp.a(str, i2))) {
            N.MZT_Y79r(this.c, this, i, null, null);
            return;
        }
        if (c0266Op != null) {
            N.MZT_Y79r(this.c, this, i, c0266Op.b, c0266Op.a);
            return;
        }
        if (bArr.length > 0) {
            X500Principal[] x500PrincipalArr2 = new X500Principal[bArr.length];
            for (int i3 = 0; i3 < bArr.length; i3++) {
                try {
                    x500PrincipalArr2[i3] = new X500Principal(bArr[i3]);
                } catch (IllegalArgumentException e) {
                    Log.w("AwContentsClientBridge", "Exception while decoding issuers list: " + e);
                    N.MZT_Y79r(this.c, this, i, null, null);
                    return;
                }
            }
            x500PrincipalArr = x500PrincipalArr2;
        } else {
            x500PrincipalArr = null;
        }
        C1235no c1235no = new C1235no(this, i, str, i2);
        C0252Ob c0252Ob = (C0252Ob) this.a;
        Objects.requireNonNull(c0252Ob);
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onReceivedClientCertRequest", null);
            c0252Ob.h.onReceivedClientCertRequest(c0252Ob.e, new C0199Lb(c1235no, strArr, x500PrincipalArr, str, i2));
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedClientCertRequest");
            AbstractC0229Mo.a(1);
        } catch (Throwable th) {
            TraceEvent.A("WebViewContentsClientAdapter.onReceivedClientCertRequest");
            throw th;
        }
    }
}
