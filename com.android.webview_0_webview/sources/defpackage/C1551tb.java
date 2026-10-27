package defpackage;

import J.N;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import com.android.webview.chromium.DrawFunctor;
import com.android.webview.chromium.DrawGLFunctor;
import com.android.webview.chromium.GraphicsUtils;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import com.android.webview.chromium.WebViewDatabaseAdapter;
import java.util.Objects;
import org.chromium.android_webview.AwBrowserContext;
import org.chromium.android_webview.AwBrowserProcess;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwDataDirLock;
import org.chromium.android_webview.AwProxyController;
import org.chromium.android_webview.AwQuotaManagerBridge;
import org.chromium.android_webview.AwTracingController;
import org.chromium.base.BuildInfo;
import org.chromium.base.BundleUtils;
import org.chromium.base.JNIUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.ui.base.ResourceBundle;

/* JADX INFO: renamed from: tb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1551tb {
    public AwBrowserContext a;
    public AwTracingController b;
    public D8 c;
    public C0978j8 d;
    public C0644d8 e;
    public Z8 f;
    public C1091l9 g;
    public WebViewDatabaseAdapter h;
    public C0735ep i;
    public AwTracingController j;
    public C1731wq k;
    public Thread l;
    public AwProxyController m;
    public final Object n = new Object();
    public boolean o;
    public final WebViewChromiumFactoryProvider p;

    public C1551tb(WebViewChromiumFactoryProvider webViewChromiumFactoryProvider) {
        this.p = webViewChromiumFactoryProvider;
    }

    public final void a(Context context) {
        Trace.beginSection("WebViewChromiumAwInit.doNetworkInitializations");
        try {
            boolean z = true;
            if (context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0) {
                NetworkChangeNotifier.init();
                NetworkChangeNotifier.f.h(true, new C0283Po());
            }
            if (context.getApplicationInfo().targetSdkVersion < 26) {
                z = false;
            }
            N.Mu0fMVv8(z);
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

    public void b(boolean z) {
        if (this.o) {
            return;
        }
        Looper looperMyLooper = !z ? Looper.myLooper() : Looper.getMainLooper();
        StringBuilder sbF = Z7.f("Binding Chromium to ");
        sbF.append(Looper.getMainLooper().equals(looperMyLooper) ? "main" : "background");
        sbF.append(" looper ");
        sbF.append(looperMyLooper);
        sbF.toString();
        synchronized (ThreadUtils.a) {
            if (looperMyLooper == null) {
                ThreadUtils.c = null;
            } else {
                if (ThreadUtils.c != null && ThreadUtils.c.getLooper() != looperMyLooper) {
                    throw new RuntimeException("UI thread looper is already set to " + ThreadUtils.c.getLooper() + " (Main thread looper is " + Looper.getMainLooper() + "), cannot set to new looper " + looperMyLooper);
                }
                ThreadUtils.c = new Handler(looperMyLooper);
            }
        }
        if (ThreadUtils.j()) {
            i();
            return;
        }
        PostTask.a(AbstractC0538bE.a, new RunnableC1496sb(this), 0L);
        while (!this.o) {
            try {
                this.n.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public final void c() {
        Trace.beginSection("WebViewChromiumAwInit.finishVariationsInitLocked");
        try {
            if (this.k == null) {
                Log.e("WebViewChromiumAwInit", "finishVariationsInitLocked() called before startVariationsInit()");
                j();
            }
            this.k.a();
            this.k = null;
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

    public AwTracingController d() {
        synchronized (this.n) {
            if (this.j == null) {
                b(true);
            }
        }
        return this.j;
    }

    public AwBrowserContext e() {
        if (this.a == null) {
            if (AwBrowserContext.g == null) {
                AwBrowserContext.g = (AwBrowserContext) N.MCLx2xtg();
            }
            this.a = AwBrowserContext.g;
        }
        return this.a;
    }

    public C0735ep f() {
        synchronized (this.n) {
            if (this.i == null) {
                b(true);
            }
        }
        return this.i;
    }

    public D8 g() {
        synchronized (this.n) {
            if (this.c == null) {
                b(true);
            }
        }
        return this.c;
    }

    public final void h() {
        Trace.beginSection("WebViewChromiumAwInit.initPlatSupportLibrary");
        try {
            if (BuildInfo.a()) {
                N.Muf_UBRy(DrawFunctor.nativeGetFunctionTable());
            }
            DrawGLFunctor.nativeSetChromiumAwDrawGLFunction(AwContents.l());
            N.M1aUwTSB(GraphicsUtils.nativeGetDrawSWFunctionTable());
            N.MHELqQxx(GraphicsUtils.nativeGetDrawGLFunctionTable());
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

    public void i() {
        Trace.beginSection("WebViewChromiumAwInit.startChromiumLocked");
        try {
            boolean zJ = this.p.d.j();
            if (TraceEvent.u != zJ) {
                TraceEvent.u = zJ;
                if (zJ) {
                    N.MRN$Vid3();
                } else {
                    N.MOgCa3d$();
                }
            }
            this.n.notifyAll();
            if (this.o) {
                Trace.endSection();
                return;
            }
            final Context context = Is.a;
            BuildInfo.o = "";
            JNIUtils.b = C1551tb.class.getClassLoader();
            String[] strArr = new String[0];
            String[] strArr2 = AbstractC1237nq.a;
            ResourceBundle.a = strArr;
            ResourceBundle.b = strArr2;
            BundleUtils.a = Boolean.FALSE;
            Trace.beginSection("WebViewChromiumAwInit.LibraryLoader");
            try {
                C1569tt.l.b();
                Trace.endSection();
                N.M6H_IiaF(3, "/system/lib/");
                N.M6H_IiaF(3003, "/system/framework/webview/paks");
                h();
                a(context);
                try {
                    Trace.beginSection("WebViewChromiumAwInit.waitUntilSetUpResources");
                    try {
                        this.l.join();
                        Trace.endSection();
                        GD.a(AwBrowserProcess.d(), null, AwBrowserProcess.d(), null, true, 4, true, true);
                        c();
                        Trace.beginSection("AwBrowserProcess.start");
                        try {
                            final Context context2 = Is.a;
                            AwDataDirLock.b(context2);
                            ThreadUtils.h(new Runnable(context2) { // from class: in
                                public final Context s;

                                {
                                    this.s = context2;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    AwBrowserProcess.h(this.s);
                                }
                            });
                            Trace.endSection();
                            AwBrowserProcess.e(true);
                            this.c = new D8();
                            if (BuildInfo.b()) {
                                this.c.a(true);
                            }
                            this.p.d.i(new C1387qb(this));
                            this.o = true;
                            Lt.a.a("Android.WebView.TargetSdkVersion", context.getApplicationInfo().targetSdkVersion);
                            Trace.beginSection("WebViewChromiumAwInit.initThreadUnsafeSingletons");
                            try {
                                AwBrowserContext awBrowserContextE = e();
                                this.d = new C0978j8(this.p, awBrowserContextE.a());
                                WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.p;
                                AwBrowserContext awBrowserContext = this.a;
                                if (awBrowserContext.d == null) {
                                    awBrowserContext.d = new AwQuotaManagerBridge(N.MyGX0Tx3(awBrowserContext.e));
                                }
                                this.g = new C1091l9(webViewChromiumFactoryProvider, awBrowserContext.d);
                                if (this.b == null) {
                                    this.b = new AwTracingController();
                                }
                                this.j = this.b;
                                if (awBrowserContextE.c == null) {
                                    awBrowserContextE.c = new C0735ep(Is.a, awBrowserContextE);
                                }
                                this.i = awBrowserContextE.c;
                                this.m = new AwProxyController();
                                Trace.endSection();
                                this.p.a.b();
                                PostTask.a(C1242nv.h, new Runnable(context) { // from class: ob
                                    public final Context s;

                                    {
                                        this.s = context;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        Context context3 = this.s;
                                        boolean zH = Es.e().h("webview-verbose-logging");
                                        try {
                                            context3.getPackageManager().getPackageInfo("org.chromium.webview_log_verbosifier", 0);
                                            zH = true;
                                        } catch (PackageManager.NameNotFoundException unused) {
                                        }
                                        if (zH) {
                                            PostTask.a(AbstractC0538bE.b, new Runnable() { // from class: pb
                                                @Override // java.lang.Runnable
                                                public void run() {
                                                    N.MjT_gThK();
                                                    N.MHz6Fn06();
                                                }
                                            }, 0L);
                                        }
                                    }
                                }, 0L);
                                Trace.endSection();
                            } finally {
                            }
                        } finally {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                try {
                    Trace.endSection();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
        } finally {
            try {
                Trace.endSection();
            } catch (Throwable th3) {
                AbstractC0279Pk.a.a(th, th3);
            }
        }
    }

    public void j() {
        synchronized (this.n) {
            if (this.k == null) {
                C1731wq c1731wq = new C1731wq();
                this.k = c1731wq;
                Objects.requireNonNull(c1731wq);
                c1731wq.a = new RunnableC1566tq(c1731wq, null);
                new Thread(c1731wq.a).start();
            }
        }
    }

    public void k(boolean z) {
        synchronized (this.n) {
            b(z);
        }
    }
}
