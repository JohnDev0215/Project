package org.chromium.android_webview;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.Trace;
import defpackage.AbstractC0178Jq;
import defpackage.AbstractC0279Pk;
import defpackage.C0448Yr;
import defpackage.C1242nv;
import defpackage.C1569tt;
import defpackage.CD;
import defpackage.Es;
import defpackage.InterfaceC0321Rq;
import defpackage.InterfaceC0964iv;
import defpackage.InterfaceC1020jv;
import defpackage.Is;
import defpackage.Px;
import defpackage.RunnableC1012jn;
import defpackage.ServiceConnectionC1124ln;
import defpackage.Ts;
import defpackage.Z7;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.chromium.android_webview.safe_browsing.AwSafeBrowsingConfigHelper;
import org.chromium.base.PathUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.policy.CombinedPolicyProvider;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AwBrowserProcess {
    public static final InterfaceC1020jv a;
    public static String b;

    static {
        C1242nv c1242nv = C1242nv.h;
        a = ((InterfaceC0964iv) PostTask.e.get(c1242nv.d)).a(c1242nv);
    }

    public static void a(File[] fileArr, Map map, InterfaceC0321Rq interfaceC0321Rq) {
        int length = fileArr.length;
        ParcelFileDescriptor[] parcelFileDescriptorArr = new ParcelFileDescriptor[length];
        int i = 0;
        for (int i2 = 0; i2 < fileArr.length; i2++) {
            try {
                try {
                    parcelFileDescriptorArr[i2] = ParcelFileDescriptor.open(fileArr[i2], 268435456);
                } catch (FileNotFoundException unused) {
                    parcelFileDescriptorArr[i2] = null;
                }
            } finally {
                b(fileArr);
                while (i < length) {
                    try {
                        if (parcelFileDescriptorArr[i] != null) {
                            parcelFileDescriptorArr[i].close();
                        }
                    } catch (IOException unused2) {
                    }
                    i++;
                }
            }
        }
        try {
            interfaceC0321Rq.e(parcelFileDescriptorArr, c(fileArr, map));
        } catch (RemoteException unused3) {
        }
    }

    public static void b(File[] fileArr) {
        for (File file : fileArr) {
            if (!file.delete()) {
                StringBuilder sbF = Z7.f("Couldn't delete file ");
                sbF.append(file.getAbsolutePath());
                Ts.f("AwBrowserProcess", sbF.toString(), new Object[0]);
            }
        }
    }

    public static List c(File[] fileArr, Map map) {
        ArrayList arrayList = new ArrayList(fileArr.length);
        for (File file : fileArr) {
            String name = file.getName();
            int iIndexOf = name.indexOf(46);
            if (iIndexOf == -1) {
                iIndexOf = name.length();
            }
            String strSubstring = name.substring(0, iIndexOf);
            if (map == null) {
                arrayList.add(null);
            } else {
                arrayList.add((Map) map.get(strSubstring));
            }
        }
        return arrayList;
    }

    public static String d() {
        String str = b;
        return str == null ? "" : str;
    }

    public static void e(boolean z) {
        Trace.beginSection("AwBrowserProcess.handleMinidumpsAndSetMetricsConsent");
        try {
            boolean zH = Es.e().h("enable-crash-reporter-for-testing");
            if (zH) {
                a.a(new RunnableC1012jn(true));
            }
            AbstractC0178Jq.a();
            ThreadUtils.b();
            g(z, zH, Boolean.FALSE);
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

    public static final void f(boolean z) {
        Context context = Is.a;
        File file = new File(context.getCacheDir().getPath(), "WebView");
        if (file.isDirectory()) {
            Px px = new Px(file);
            Map mapI = px.i();
            File[] fileArrK = px.k(Px.c);
            if (fileArrK.length == 0) {
                return;
            }
            if (!z) {
                b(fileArrK);
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(d(), "org.chromium.android_webview.services.CrashReceiverService");
            if (context.bindService(intent, new ServiceConnectionC1124ln(fileArrK, mapI, context), 1)) {
                return;
            }
            Ts.f("AwBrowserProcess", "Could not bind to Minidump-copying Service " + intent, new Object[0]);
        }
    }

    public static final void g(boolean z, boolean z2, Boolean bool) {
        ThreadUtils.b();
        if (z) {
            Context context = Is.a;
            boolean zBooleanValue = bool.booleanValue();
            boolean z3 = false;
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo.metaData != null) {
                    z3 = applicationInfo.metaData.getBoolean("android.webkit.WebView.MetricsOptOut");
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Ts.a("AwMetricsServiceCli-", "App could not find itself by package name!", new Object[0]);
                z3 = true;
            }
            N.Mdl6A1eo(zBooleanValue, !z3);
        }
        if (z2) {
            return;
        }
        a.a(new RunnableC1012jn(bool.booleanValue()));
    }

    public static final void h(Context context) {
        boolean zH = Es.e().h("webview-sandboxed-renderer");
        if (zH) {
            ChildProcessLauncherHelperImpl.e(context, true);
        }
        CombinedPolicyProvider combinedPolicyProviderA = CombinedPolicyProvider.a();
        C0448Yr c0448Yr = new C0448Yr(context);
        combinedPolicyProviderA.c.add(c0448Yr);
        combinedPolicyProviderA.d.add(null);
        c0448Yr.b = combinedPolicyProviderA.c.size() - 1;
        c0448Yr.a = combinedPolicyProviderA;
        c0448Yr.c.registerReceiver(c0448Yr.d, new IntentFilter("android.intent.action.APPLICATION_RESTRICTIONS_CHANGED"), null, new Handler(ThreadUtils.d()));
        if (combinedPolicyProviderA.a != 0) {
            c0448Yr.b();
        }
        Trace.beginSection("AwBrowserProcess.maybeEnable");
        try {
            AwSafeBrowsingConfigHelper.b(context);
            Trace.endSection();
            Trace.beginSection("AwBrowserProcess.startBrowserProcessesSync");
            try {
                ((BrowserStartupControllerImpl) CD.a()).g(3, zH ? false : true);
                Trace.endSection();
            } finally {
                try {
                    Trace.endSection();
                } catch (Throwable th) {
                    AbstractC0279Pk.a.a(th, th);
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public static void i(String str) {
        C1569tt.l.m(3);
        if (str == null) {
            PathUtils.b("webview", "WebView");
        } else {
            String str2 = "webview_" + str;
            PathUtils.b(str2, str2);
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            C1569tt.l.i(Is.a);
            C1569tt c1569tt = C1569tt.l;
            synchronized (c1569tt.c) {
                c1569tt.a();
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static void triggerMinidumpUploading() {
        e(false);
    }
}
