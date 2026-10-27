package com.android.webview.chromium;

import J.N;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.Settings;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.TokenBindingService;
import android.webkit.TracingController;
import android.webkit.ValueCallback;
import android.webkit.WebIconDatabase;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.webkit.WebViewDelegate;
import android.webkit.WebViewFactory;
import android.webkit.WebViewFactoryProvider;
import android.webkit.WebViewProvider;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import defpackage.A8;
import defpackage.AbstractC0106Fq;
import defpackage.AbstractC0214Lq;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0511ao;
import defpackage.AbstractC0532b8;
import defpackage.AbstractC0538bE;
import defpackage.AbstractC1299ox;
import defpackage.AbstractC1679vt;
import defpackage.B8;
import defpackage.C0001Ab;
import defpackage.C0070Dq;
import defpackage.C0088Eq;
import defpackage.C0104Fo;
import defpackage.C0142Hq;
import defpackage.C0396Wb;
import defpackage.C0446Yp;
import defpackage.C0450Zb;
import defpackage.C0464Zp;
import defpackage.C0644d8;
import defpackage.C0683dt;
import defpackage.C1090l8;
import defpackage.C1311p8;
import defpackage.C1551tb;
import defpackage.C1826yb;
import defpackage.C1881zb;
import defpackage.C8;
import defpackage.D8;
import defpackage.Es;
import defpackage.InterfaceC0052Cq;
import defpackage.InterfaceC0555bc;
import defpackage.Is;
import defpackage.It;
import defpackage.Lt;
import defpackage.RunnableC1441rb;
import defpackage.Ts;
import defpackage.Z8;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwBrowserProcess;
import org.chromium.android_webview.AwContentsStatics;
import org.chromium.android_webview.AwSettings;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.base.PathUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebViewChromiumFactoryProvider implements WebViewFactoryProvider {
    public static final Object i = new Object();
    public static WebViewChromiumFactoryProvider j;
    public static boolean k;
    public final C0070Dq a;
    public C1551tb b;
    public SharedPreferences c;
    public InterfaceC0555bc d;
    public boolean e;
    public WebViewFactoryProvider.Statics f;
    public C1881zb g;
    public C0001Ab h;

    public WebViewChromiumFactoryProvider() {
        int i2 = Build.VERSION.SDK_INT;
        this.a = new C0070Dq(new InterfaceC0052Cq(this) { // from class: ub
            public final WebViewChromiumFactoryProvider a;

            {
                this.a = this;
            }

            @Override // defpackage.InterfaceC0052Cq
            public boolean a() {
                return this.a.b.o;
            }
        });
        this.g = i2 >= 24 ? new C1881zb() : null;
        this.h = i2 >= 28 ? new C0001Ab() : null;
        f(new C0396Wb());
    }

    public static void a(Context context) {
        if (Build.VERSION.SDK_INT >= 24 && C1090l8.d(context)) {
            throw new IllegalArgumentException("WebView cannot be used with device protected storage");
        }
    }

    public static void b(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    b(file2);
                }
                if (!file2.delete()) {
                    Ts.f("WVCFactoryProvider", "Failed to delete " + file2, new Object[0]);
                }
            }
        }
    }

    public static WebViewChromiumFactoryProvider create(WebViewDelegate webViewDelegate) {
        return new WebViewChromiumFactoryProvider(webViewDelegate);
    }

    public static WebViewChromiumFactoryProvider e() {
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider;
        synchronized (i) {
            if (j == null) {
                throw new RuntimeException("WebViewChromiumFactoryProvider has not been set!");
            }
            webViewChromiumFactoryProvider = j;
        }
        return webViewChromiumFactoryProvider;
    }

    public static boolean preloadInZygote() {
        if (Build.VERSION.SDK_INT >= 26) {
        }
        for (String str : AbstractC1679vt.a) {
            System.loadLibrary(str);
        }
        return true;
    }

    public static void setWebLayerRunningInSameProcess() {
        synchronized (i) {
            k = true;
            if (j == null) {
                return;
            }
            WebViewChromiumFactoryProvider webViewChromiumFactoryProviderE = e();
            webViewChromiumFactoryProviderE.a.a(new Runnable() { // from class: xb
                @Override // java.lang.Runnable
                public void run() {
                    N.Mzi6ndOk(WebViewChromiumFactoryProvider.e().d().e);
                }
            });
        }
    }

    public final void c(PackageInfo packageInfo) {
        Trace.beginSection("WebViewChromiumFactoryProvider.deleteContentsOnPackageDowngrade");
        try {
            SharedPreferences sharedPreferences = Is.a.getSharedPreferences("WebViewChromiumPrefs", 0);
            this.c = sharedPreferences;
            int i2 = sharedPreferences.getInt("lastVersionCodeUsed", 0);
            int i3 = packageInfo.versionCode;
            if (!(i3 / 100000 >= i2 / 100000)) {
                String dataDirectory = PathUtils.getDataDirectory();
                Ts.d("WVCFactoryProvider", "WebView package downgraded from " + i2 + " to " + i3 + "; deleting contents of " + dataDirectory, new Object[0]);
                b(new File(dataDirectory));
            }
            if (i2 != i3) {
                this.c.edit().putInt("lastVersionCodeUsed", i3).apply();
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

    public WebViewProvider createWebView(WebView webView, WebView.PrivateAccess privateAccess) {
        return new WebViewChromium(this, webView, privateAccess, this.e);
    }

    public AwBrowserContext d() {
        return this.b.e();
    }

    public final void f(InterfaceC0555bc interfaceC0555bc) {
        int i2 = Build.VERSION.SDK_INT;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Trace.beginSection("WebViewChromiumFactoryProvider.initialize");
        try {
            Trace.beginSection("WebViewChromiumFactoryProvider.getLoadedPackageInfo");
            try {
                PackageInfo loadedPackageInfo = WebViewFactory.getLoadedPackageInfo();
                Trace.endSection();
                AwBrowserProcess.b = loadedPackageInfo.packageName;
                Trace.beginSection("WebViewChromiumFactoryProvider.createAwInit");
                try {
                    C1551tb c1551tb = new C1551tb(this);
                    Trace.endSection();
                    this.b = c1551tb;
                    this.d = interfaceC0555bc;
                    Context applicationContext = interfaceC0555bc.l().getApplicationContext();
                    try {
                        Trace.beginSection("WebViewChromiumFactoryProvider.checkStorage");
                    } catch (IllegalArgumentException e) {
                        if (!C1090l8.e(applicationContext)) {
                            throw e;
                        }
                        applicationContext = C1090l8.b(applicationContext);
                    }
                    try {
                        a(interfaceC0555bc.l());
                        Trace.endSection();
                        Context contextA = AbstractC1299ox.a(applicationContext);
                        Is.a = contextA;
                        String string = loadedPackageInfo.packageName;
                        if (loadedPackageInfo.applicationInfo.metaData != null) {
                            string = loadedPackageInfo.applicationInfo.metaData.getString("com.android.webview.WebViewDonorPackage", string);
                        }
                        int iC = interfaceC0555bc.c(contextA.getResources(), string);
                        C1551tb c1551tb2 = this.b;
                        Objects.requireNonNull(c1551tb2);
                        Trace.beginSection("WebViewChromiumAwInit.setUpResourcesOnBackgroundThread");
                        try {
                            Thread thread = new Thread(new RunnableC1441rb(c1551tb2, iC, contextA));
                            c1551tb2.l = thread;
                            thread.start();
                            Trace.endSection();
                            Trace.beginSection("WebViewChromiumFactoryProvider.initCommandLine");
                            try {
                                C0088Eq.a();
                                Trace.endSection();
                                if (i2 >= 26 ? interfaceC0555bc.isMultiProcessEnabled() : i2 >= 24 && Settings.Global.getInt(contextA.getContentResolver(), "webview_multiprocess", 0) == 1) {
                                    Es.e().a("webview-sandboxed-renderer");
                                }
                                boolean z = (contextA.getApplicationInfo().flags & 2) != 0;
                                boolean zB = BuildInfo.b();
                                if (z || zB) {
                                    Es.e().a("webview-log-js-console-messages");
                                }
                                String strD = AwBrowserProcess.d();
                                boolean zB2 = AbstractC0106Fq.b(strD);
                                Lt.a.b("Android.WebView.DevUi.DeveloperModeEnabled", zB2);
                                if (zB2) {
                                    long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                                    try {
                                        C0142Hq c0142Hq = new C0142Hq(AbstractC0214Lq.a);
                                        Map mapA = AbstractC0106Fq.a(strD);
                                        c0142Hq.a(mapA);
                                        Lt.a.e("Android.WebView.DevUi.ToggledFlagCount", ((HashMap) mapA).size(), 1, 100, 50);
                                        It.e("Android.WebView.DevUi.FlagLoadingBlockingTime", SystemClock.elapsedRealtime() - jElapsedRealtime2);
                                    } catch (Throwable th) {
                                        It.e("Android.WebView.DevUi.FlagLoadingBlockingTime", SystemClock.elapsedRealtime() - jElapsedRealtime2);
                                        throw th;
                                    }
                                }
                                Object obj = ThreadUtils.a;
                                synchronized (ThreadUtils.a) {
                                    ThreadUtils.b = true;
                                }
                                BuildInfo.m = loadedPackageInfo;
                                C0683dt c0683dtA = C0683dt.A();
                                try {
                                    Trace.beginSection("WebViewChromiumFactoryProvider.loadChromiumLibrary");
                                    String strG = null;
                                    if (i2 >= 28) {
                                        try {
                                            strG = interfaceC0555bc.g();
                                        } finally {
                                        }
                                    }
                                    AwBrowserProcess.i(strG);
                                    Trace.endSection();
                                    Trace.beginSection("WebViewChromiumFactoryProvider.loadGlueLayerPlatSupportLibrary");
                                    try {
                                        System.loadLibrary("webviewchromium_plat_support");
                                        Trace.endSection();
                                        c(loadedPackageInfo);
                                        c0683dtA.close();
                                        this.b.j();
                                        this.e = g(contextA);
                                        synchronized (i) {
                                            if (j != null) {
                                                throw new RuntimeException("WebViewChromiumFactoryProvider should only be set once!");
                                            }
                                            j = this;
                                        }
                                        if (k) {
                                            this.a.a(new Runnable(this) { // from class: wb
                                                public final WebViewChromiumFactoryProvider s;

                                                {
                                                    this.s = this;
                                                }

                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    N.Mzi6ndOk(this.s.d().e);
                                                }
                                            });
                                        }
                                        Trace.endSection();
                                        It.e("Android.WebView.Startup.CreationTime.Stage1.FactoryInit", SystemClock.elapsedRealtime() - jElapsedRealtime);
                                    } finally {
                                    }
                                } finally {
                                }
                            } finally {
                            }
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        Trace.endSection();
                        throw th;
                    } catch (Throwable th2) {
                        AbstractC0279Pk.a.a(th, th2);
                    }
                }
            } finally {
                try {
                    Trace.endSection();
                    throw th;
                } catch (Throwable th3) {
                    AbstractC0279Pk.a.a(th, th3);
                }
            }
        } finally {
        }
    }

    public final boolean g(Context context) {
        boolean z;
        PackageInfo packageInfo;
        String packageName = context.getPackageName();
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        int i2 = packageInfo != null ? packageInfo.versionCode : -1;
        int i3 = context.getApplicationInfo().targetSdkVersion;
        if (i2 == -1) {
            return false;
        }
        boolean z2 = true;
        if (!"com.lge.email".equals(packageName)) {
            z = false;
        } else {
            if (i3 > 24) {
                return false;
            }
            if (i2 > 67502100) {
                return false;
            }
            z = true;
        }
        if (packageName.startsWith("com.yahoo.mobile.client.android.mail")) {
            if (i3 > 23 || i2 > 1315850) {
                return false;
            }
            z = true;
        }
        if (!"com.htc.android.mail".equals(packageName)) {
            z2 = z;
        } else if (i3 > 23 || i2 >= 866001861) {
            return false;
        }
        if (z2) {
            Ts.f("WVCFactoryProvider", "Disabling thread check in WebView. APK name: " + packageName + ", versionCode: " + i2 + ", targetSdkVersion: " + i3, new Object[0]);
        }
        return z2;
    }

    public CookieManager getCookieManager() {
        C1551tb c1551tb = this.b;
        synchronized (c1551tb.n) {
            if (c1551tb.e == null) {
                c1551tb.e = new C0644d8(new C0104Fo());
            }
        }
        return c1551tb.e;
    }

    public GeolocationPermissions getGeolocationPermissions() {
        C1551tb c1551tb = this.b;
        synchronized (c1551tb.n) {
            if (c1551tb.d == null) {
                c1551tb.b(true);
            }
        }
        return c1551tb.d;
    }

    public ServiceWorkerController getServiceWorkerController() {
        synchronized (this.b.n) {
            if (this.g.a == null) {
                this.g.a = C1090l8.c(this.b);
            }
        }
        return this.g.a;
    }

    public WebViewFactoryProvider.Statics getStatics() {
        synchronized (this.b.n) {
            final D8 d8G = this.b.g();
            if (this.f == null) {
                this.f = new WebViewFactoryProvider.Statics() { // from class: com.android.webview.chromium.WebViewChromiumFactoryProvider.1
                    public void clearClientCertPreferences(final Runnable runnable) {
                        Objects.requireNonNull(d8G);
                        PostTask.b(AbstractC0538bE.a, new Runnable(runnable) { // from class: y8
                            public final Runnable s;

                            {
                                this.s = runnable;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                Runnable runnable2 = this.s;
                                ThreadUtils.b();
                                ThreadUtils.b();
                                if (AwContentsStatics.a == null) {
                                    AwContentsStatics.a = new C0284Pp();
                                }
                                C0284Pp c0284Pp = AwContentsStatics.a;
                                c0284Pp.a.clear();
                                c0284Pp.b.clear();
                                N.Ml71D$Ud(runnable2);
                            }
                        });
                    }

                    public void enableSlowWholeDocumentDraw() {
                        Objects.requireNonNull(d8G);
                        WebViewChromium.E = true;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:41:0x00bc  */
                    /* JADX WARN: Removed duplicated region for block: B:66:0x011b  */
                    /* JADX WARN: Removed duplicated region for block: B:98:0x0197  */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public java.lang.String findAddress(java.lang.String r20) {
                        /*
                            Method dump skipped, instruction units count: 469
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.webview.chromium.WebViewChromiumFactoryProvider.AnonymousClass1.findAddress(java.lang.String):java.lang.String");
                    }

                    public void freeMemoryForTests() {
                        Objects.requireNonNull(d8G);
                        if (ActivityManager.isRunningInTestHarness()) {
                            PostTask.a(AbstractC0538bE.a, new Runnable() { // from class: z8
                                @Override // java.lang.Runnable
                                public void run() {
                                    Ct.i.c(2);
                                }
                            }, 0L);
                        }
                    }

                    public String getDefaultUserAgent(Context context) {
                        Objects.requireNonNull(d8G);
                        return AwSettings.l();
                    }

                    public Uri getSafeBrowsingPrivacyPolicyUrl() {
                        Objects.requireNonNull(d8G);
                        return (Uri) PostTask.c(AbstractC0538bE.a, new C8());
                    }

                    public void initSafeBrowsing(Context context, ValueCallback valueCallback) {
                        D8 d8 = d8G;
                        Callback callbackA = AbstractC0532b8.a(valueCallback);
                        Objects.requireNonNull(d8);
                        PostTask.b(AbstractC0538bE.a, new A8(context, callbackA));
                    }

                    public boolean isMultiProcessEnabled() {
                        Objects.requireNonNull(d8G);
                        return N.M04mALrd();
                    }

                    public Uri[] parseFileChooserResult(int i2, Intent intent) {
                        Objects.requireNonNull(d8G);
                        return AbstractC0511ao.f(i2, intent);
                    }

                    public void setSafeBrowsingWhitelist(List list, ValueCallback valueCallback) {
                        D8 d8 = d8G;
                        Callback callbackA = AbstractC0532b8.a(valueCallback);
                        Objects.requireNonNull(d8);
                        PostTask.b(AbstractC0538bE.a, new B8(list, callbackA));
                    }

                    public void setWebContentsDebuggingEnabled(boolean z) {
                        D8 d8 = d8G;
                        Objects.requireNonNull(d8);
                        if (BuildInfo.b()) {
                            return;
                        }
                        d8.a(z);
                    }
                };
            }
        }
        return this.f;
    }

    public TokenBindingService getTokenBindingService() {
        return null;
    }

    public TracingController getTracingController() {
        synchronized (this.b.n) {
            this.b.b(true);
            if (this.h.a == null) {
                this.h.a = C1311p8.a(this, this.b);
            }
        }
        return this.h.a;
    }

    public WebIconDatabase getWebIconDatabase() {
        C1551tb c1551tb = this.b;
        synchronized (c1551tb.n) {
            c1551tb.b(true);
            if (c1551tb.f == null) {
                c1551tb.f = new Z8();
            }
        }
        return c1551tb.f;
    }

    public WebStorage getWebStorage() {
        C1551tb c1551tb = this.b;
        synchronized (c1551tb.n) {
            if (c1551tb.g == null) {
                c1551tb.b(true);
            }
        }
        return c1551tb.g;
    }

    public ClassLoader getWebViewClassLoader() {
        return new C1826yb(WebViewChromiumFactoryProvider.class.getClassLoader());
    }

    public WebViewDatabase getWebViewDatabase(Context context) {
        C1551tb c1551tb = this.b;
        synchronized (c1551tb.n) {
            c1551tb.b(true);
            if (c1551tb.h == null) {
                WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = c1551tb.p;
                C0464Zp c0464Zp = new C0464Zp();
                new C0446Yp(c0464Zp, context, "http_auth.db").start();
                c1551tb.h = new WebViewDatabaseAdapter(webViewChromiumFactoryProvider, c0464Zp);
            }
        }
        return c1551tb.h;
    }

    public void h(boolean z) {
        Trace.beginSection("WebViewChromiumFactoryProvider.startYourEngines");
        try {
            this.b.k(z);
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

    public WebViewChromiumFactoryProvider(WebViewDelegate webViewDelegate) {
        C1881zb c1881zb;
        int i2 = Build.VERSION.SDK_INT;
        this.a = new C0070Dq(new InterfaceC0052Cq(this) { // from class: vb
            public final WebViewChromiumFactoryProvider a;

            {
                this.a = this;
            }

            @Override // defpackage.InterfaceC0052Cq
            public boolean a() {
                return this.a.b.o;
            }
        });
        byte b = 0;
        if (i2 >= 24) {
            c1881zb = new C1881zb();
        } else {
            c1881zb = null;
        }
        this.g = c1881zb;
        this.h = i2 >= 28 ? new C0001Ab() : null;
        f(new C0450Zb(webViewDelegate));
    }
}
