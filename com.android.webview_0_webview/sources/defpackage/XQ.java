package defpackage;

import J.N;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AndroidRuntimeException;
import android.webkit.ValueCallback;
import android.webkit.WebViewFactory;
import java.io.File;
import java.util.Objects;
import org.chromium.base.PathUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class XQ extends AbstractBinderC0664dS {
    public static InterfaceC0831gS d;
    public final CQ a = new CQ();
    public boolean b;
    public boolean c;

    public static Context c(Context context) {
        try {
            return context.createPackageContext(WebViewFactory.getLoadedPackageInfo().packageName, 3);
        } catch (PackageManager.NameNotFoundException e) {
            throw new AndroidRuntimeException(e);
        }
    }

    public static void d(SharedPreferences sharedPreferences, PackageInfo packageInfo) {
        int i = sharedPreferences.getInt("org.chromium.weblayer.last_version_code_used", 0);
        int i2 = packageInfo.versionCode;
        if (i2 / 100000 < i / 100000) {
            File file = new File(PathUtils.getDataDirectory());
            Ts.d("WebLayer", "WebLayer package downgraded from " + i + " to " + i2 + "; deleting contents of " + file, new Object[0]);
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (!Ms.b(file2, Ms.a)) {
                        Ts.f("WebLayer", "Failed to delete " + file2, new Object[0]);
                    }
                }
            }
        }
        if (i != i2) {
            sharedPreferences.edit().putInt("org.chromium.weblayer.last_version_code_used", i2).apply();
        }
    }

    public static Context u(QR qr, QR qr2) {
        Context context = Is.a;
        if (context != null) {
            return context;
        }
        Context context2 = (Context) BinderC0998jS.d(qr, Context.class);
        Context contextC = (Context) BinderC0998jS.d(qr2, Context.class);
        if (contextC == null) {
            contextC = c(context2);
        }
        AbstractC1299ox.c = contextC;
        Context contextA = AbstractC1299ox.a(context2);
        Is.a = contextA;
        PathUtils.b("weblayer", "weblayer");
        return contextA;
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x01bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(defpackage.QR r18, defpackage.QR r19) {
        /*
            Method dump skipped, instruction units count: 655
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.XQ.f(QR, QR):void");
    }

    public void i(QR qr, QR qr2, QR qr3) {
        AbstractC1054kS.a();
        f(qr, qr2);
        ValueCallback valueCallback = (ValueCallback) BinderC0998jS.d(qr3, ValueCallback.class);
        ED edA = CD.a();
        VQ vq = new VQ(this, qr, valueCallback);
        BrowserStartupControllerImpl browserStartupControllerImpl = (BrowserStartupControllerImpl) edA;
        Objects.requireNonNull(browserStartupControllerImpl);
        Objects.requireNonNull(C1569tt.l);
        Cz.c.b(Cz.a(browserStartupControllerImpl.f, browserStartupControllerImpl.j, false));
        if (browserStartupControllerImpl.f) {
            PostTask.a(AbstractC0538bE.e, new Ky(browserStartupControllerImpl, vq), 0L);
            return;
        }
        browserStartupControllerImpl.a.add(vq);
        boolean z = browserStartupControllerImpl.i | (browserStartupControllerImpl.h == 1);
        browserStartupControllerImpl.i = z;
        if (!browserStartupControllerImpl.c) {
            browserStartupControllerImpl.c = true;
            BrowserStartupControllerImpl.m = false;
            browserStartupControllerImpl.e(false, new Iy(browserStartupControllerImpl, false));
        } else if (browserStartupControllerImpl.j && z) {
            browserStartupControllerImpl.h = 0;
            if (browserStartupControllerImpl.a() > 0) {
                browserStartupControllerImpl.b(1);
            }
        }
    }

    public final void v() {
        boolean z;
        BinderC1108lQ binderC1108lQD = BinderC1108lQ.d();
        binderC1108lQD.c = true;
        if (binderC1108lQD.a != null) {
            binderC1108lQD.i();
        }
        NetworkChangeNotifier.init();
        NetworkChangeNotifier.f.h(true, new ZK());
        Objects.requireNonNull(AbstractC1603uQ.a());
        ThreadUtils.b();
        Context context = Is.a;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            z = applicationInfo.metaData == null ? false : applicationInfo.metaData.getBoolean("android.WebLayer.MetricsAutoUpload");
        } catch (PackageManager.NameNotFoundException unused) {
            Ts.a("MetricsServiceClie-", "App could not find itself by package name!", new Object[0]);
            z = true;
        }
        N.Mar1tTQP(false, !z);
        N.MVSxAa8H(this.c);
    }
}
