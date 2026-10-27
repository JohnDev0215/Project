package defpackage;

import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
import defpackage.qj;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.function.Function;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: compiled from: ChromeBrowserInitializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class sp {
    public static sp h;
    public static BrowserStartupControllerImpl i;
    public final Locale a = Locale.getDefault();
    public ArrayList b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;

    /* JADX INFO: compiled from: ChromeBrowserInitializer.java */
    public class a extends n3 {
        public final /* synthetic */ boolean H;

        public a(boolean z) {
            this.H = z;
        }

        @Override // defpackage.n3, defpackage.nj
        public final boolean O() {
            return this.H;
        }
    }

    /* JADX INFO: compiled from: ChromeBrowserInitializer.java */
    public class b implements qj.a {
        public final /* synthetic */ nj a;
        public final /* synthetic */ xm b;

        public b(nj njVar, xm xmVar) {
            this.a = njVar;
            this.b = xmVar;
        }

        @Override // qj.a
        public final void a() {
            this.a.q(null);
        }

        @Override // qj.a
        public final void b() {
            this.b.b(false);
        }
    }

    public static sp a() {
        if (h == null) {
            h = new sp();
        }
        return h;
    }

    public final void b(boolean z, nj njVar) {
        if (!this.d) {
            throw new IllegalStateException("ChromeBrowserInitializer.handlePostNativeStartup called before ChromeBrowserInitializer.postInflationStartup has been run.");
        }
        xm xmVar = new xm();
        int i2 = 12;
        if (!njVar.f1() && !b62.b().b) {
            xmVar.a(rh3.a, new wz(i2));
        }
        int i3 = 11;
        if (!this.g) {
            xmVar.a(rh3.a, new zc0(i3, this));
        }
        d73 d73Var = rh3.a;
        xmVar.a(d73Var, new l91(this, 18, njVar));
        xmVar.a(d73Var, new ql0(10, njVar));
        xmVar.a(d73Var, new qm0(9, njVar));
        xmVar.a(d73Var, new zc0(i2, njVar));
        if (!this.e) {
            xmVar.a(d73Var, new l10(23, this));
        }
        if (!njVar.f1()) {
            xmVar.a(d73Var, new ql0(i3, this));
        }
        if (i == null) {
            i = qj.a();
        }
        BrowserStartupControllerImpl browserStartupControllerImpl = i;
        xmVar.a(d73Var, new xi2(aj2.a(browserStartupControllerImpl.f, browserStartupControllerImpl.j, njVar.f1()), 2));
        if (z) {
            boolean zO = njVar.O();
            boolean zF1 = njVar.f1();
            b bVar = new b(njVar, xmVar);
            try {
                TraceEvent.q("ChromeBrowserInitializer.startChromeBrowserProcessesAsync", null);
                if (i == null) {
                    i = qj.a();
                }
                i.j(zO, zF1, bVar);
                return;
            } finally {
                TraceEvent.C("ChromeBrowserInitializer.startChromeBrowserProcessesAsync");
            }
        }
        boolean zO2 = njVar.O();
        try {
            TraceEvent.q("ChromeBrowserInitializer.startChromeBrowserProcessesSync", null);
            Object obj = ThreadUtils.a;
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
            org.chromium.base.library_loader.a.l.a();
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            x61.a();
            if (i == null) {
                i = qj.a();
            }
            i.k(zO2);
            TraceEvent.C("ChromeBrowserInitializer.startChromeBrowserProcessesSync");
            xmVar.b(true);
        } catch (Throwable th) {
            TraceEvent.C("ChromeBrowserInitializer.startChromeBrowserProcessesSync");
            throw th;
        }
    }

    public final void c(nj njVar) {
        ThreadUtils.b();
        if (njVar.d()) {
            return;
        }
        b62.b().d();
        TraceEvent traceEventH0 = TraceEvent.H0("ChromeBrowserInitializer.preInflationStartup", null);
        try {
            e();
            njVar.w0();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            if (njVar.d()) {
                return;
            }
            if (SysUtils.isLowEndDevice()) {
                iv.e().a("disable-domain-reliability");
            }
            if (Process.is64Bit()) {
                String str = z7.n[0];
                if (str.equals("monochrome") || str.equals("monochrome.cr")) {
                    throw new uv("Starting in 64-bit mode requires the 64-bit native library. If the device is 64-bit only, see alternatives here: https://crbug.com/1303857#c7.");
                }
            }
            njVar.Q(new y70(this, 7, njVar));
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final void d(boolean z) {
        ThreadUtils.b();
        a aVar = new a(z);
        c(aVar);
        b(false, aVar);
    }

    public final void e() {
        e93 e93Var;
        String lowerCase;
        Object obj = ThreadUtils.a;
        if (this.c) {
            return;
        }
        new Thread(new o90(3)).start();
        int i2 = 1;
        if (!lr.a) {
            lr.a = true;
            iv ivVarE = iv.e();
            boolean zG = ivVarE.g("strict-mode");
            if (zG) {
                StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder(StrictMode.getThreadPolicy());
                StrictMode.VmPolicy.Builder builder2 = new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy());
                e93Var = new e93();
                builder.detectAll();
                builder2.detectActivityLeaks().detectLeakedClosableObjects().detectLeakedRegistrationObjects().detectLeakedSqlLiteObjects();
                builder2.detectContentUriWithoutPermission();
                builder2.detectCredentialProtectedWhileLocked().detectImplicitDirectBoot();
                builder2.detectFileUriExposure();
                if (zG) {
                    builder2.penaltyLog();
                    if ("death".equals(ivVarE.f("strict-mode"))) {
                        e93Var.b = new sb(i2);
                        builder2.penaltyDeath();
                    } else if ("testing".equals(ivVarE.f("strict-mode"))) {
                        e93Var.b = new sb(i2);
                    } else {
                        builder.penaltyLog();
                    }
                }
                String str = Build.MANUFACTURER;
                Locale locale = Locale.US;
                String lowerCase2 = str.toLowerCase(locale);
                String str2 = Build.MODEL;
                lowerCase = str2.toLowerCase(locale);
                lowerCase2.getClass();
                switch (lowerCase2) {
                    case "oneplus":
                        e93Var.a(3, "com.android.server.am.ActivityManagerService#checkProcessExist");
                        break;
                    case "xiaomi":
                        e93Var.a(2, "com.android.internal.policy.PhoneWindow#getDecorView");
                        e93Var.a(1, "miui.content.res.ThemeResourcesSystem#checkUpdate");
                        e93Var.a(2, "android.util.BoostFramework#<init>");
                        break;
                    case "vivo":
                        e93Var.a(2, "android.content.res.VivoResources#loadThemeValues");
                        break;
                    case "samsung":
                        e93Var.a(3, "android.util.GeneralUtil#isSupportedGloveModeInternal");
                        e93Var.a(2, "android.graphics.Typeface#SetAppTypeFace");
                        e93Var.a(2, "android.graphics.Typeface#setAppTypeFace");
                        e93Var.a(2, "android.app.ApplicationPackageManager#queryIntentActivities");
                        e93Var.a(2, "android.app.ActivityThread#parseCSCAppResource");
                        e93Var.a(2, "android.app.ActivityThread#performLaunchActivity");
                        e93Var.a(2, "android.widget.Toast#makeText");
                        e93Var.a(2, "android.widget.Toast#show");
                        e93Var.a(2, "com.samsung.android.knox.custom.ProKioskManager#getProKioskState");
                        if (lowerCase.equals("sm-g9350")) {
                            e93Var.a(1, "android.content.res.Resources#loadDrawable");
                        }
                        lowerCase.equals("sm-j700f");
                        break;
                }
                e93Var.a(2, "com.qualcomm.qti.Performance#<clinit>");
                e93Var.a(2, "com.android.messageformat.MessageFormat#formatNamedArgs");
                e93Var.a(16, "com.android.internal.widget.SwipeDismissLayout#init");
                e93Var.a(3, "java.lang.ThreadGroup#uncaughtException");
                e93Var.a(3, "android.widget.VideoView#openVideo");
                e93Var.a(3, "com.android.server.inputmethod.InputMethodManagerService#startInputOrWindowGainedFocus");
                e93Var.a(1, "com.android.server.clipboard.HostClipboardMonitor#setHostClipboard");
                ArrayList arrayList = e93Var.a;
                arrayList.add(new Function() { // from class: b93
                    public final /* synthetic */ int a = 3;
                    public final /* synthetic */ String b = "android.app.SharedPreferencesImpl";

                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        jo3 jo3Var = (jo3) obj2;
                        int i3 = jo3Var.a;
                        int i4 = this.a;
                        if ((i3 & i4) != 0) {
                            for (StackTraceElement stackTraceElement : jo3Var.b) {
                                if (stackTraceElement.getClassName().equals(this.b)) {
                                    return Integer.valueOf(i4);
                                }
                            }
                        }
                        return null;
                    }
                });
                e93Var.a(3, "android.app.ContextImpl#getSharedPreferences");
                arrayList.add(new Function() { // from class: c93
                    public final /* synthetic */ int a = 19;
                    public final /* synthetic */ String b = "re.dobv.android.xposed";

                    @Override // java.util.function.Function
                    public final Object apply(Object obj2) {
                        jo3 jo3Var = (jo3) obj2;
                        int i3 = jo3Var.a;
                        int i4 = this.a;
                        if ((i3 & i4) != 0) {
                            StackTraceElement[] stackTraceElementArr = jo3Var.b;
                            int length = stackTraceElementArr.length;
                            boolean z = false;
                            int i5 = 0;
                            while (true) {
                                if (i5 >= length) {
                                    break;
                                }
                                if (stackTraceElementArr[i5].getClassName().startsWith(this.b)) {
                                    z = true;
                                    break;
                                }
                                i5++;
                            }
                            if (z) {
                                return Integer.valueOf(i4);
                            }
                        }
                        return null;
                    }
                });
                if (str.toLowerCase(locale).equals("samsung")) {
                    e93Var.a(2, "android.net.ConnectivityManager#registerDefaultNetworkCallback");
                }
                String lowerCase3 = str2.toLowerCase(locale);
                if (!lowerCase3.contains("nexus") && !lowerCase3.contains("pixel")) {
                    arrayList.add(new ik2(i2, "org.chromium.content"));
                }
                ia2 ia2Var = new ia2(arrayList, e93Var.b);
                StrictMode.ThreadPolicy threadPolicyBuild = builder.build();
                try {
                    Field declaredField = StrictMode.class.getDeclaredField("violationsBeingTimed");
                    declaredField.setAccessible(true);
                    ThreadLocal threadLocal = (ThreadLocal) declaredField.get(null);
                    ((ArrayList) threadLocal.get()).clear();
                    threadLocal.set(new ha2(ia2Var));
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicyBuild).penaltyLog().build());
                    StrictMode.setVmPolicy(builder2.build());
                } catch (Exception e) {
                    throw new RuntimeException(null, e);
                }
            }
        }
        zs.c();
        PostTask.c(d73.g, new qe3(11));
        ku2 ku2VarT = ku2.t();
        try {
            if (!DeviceFormFactor.isTablet()) {
                iv.e().a("use-mobile-user-agent");
            }
            ku2VarT.close();
            ApplicationStatus.g(new up(this));
            this.c = true;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void f(Runnable runnable) {
        if (this.f) {
            runnable.run();
            return;
        }
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(runnable);
    }
}
