package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import defpackage.aw0;
import defpackage.iw0;
import defpackage.qn;
import defpackage.un;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.a;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: ChildProcessConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class qn {
    public static int K;
    public static boolean L;
    public int A;
    public int B;
    public int C;
    public boolean D;
    public int E;
    public int F;
    public boolean G;
    public kn H;
    public String I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f93J;
    public final Object a = new Object();
    public final Handler b = new Handler();
    public final jd1 c = new jd1(1, this);
    public final ComponentName d;
    public final Bundle e;
    public final boolean f;
    public e g;
    public d h;
    public c i;
    public f j;
    public aw0 k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;
    public final Cdo q;
    public final nn r;
    public final String s;
    public final boolean t;
    public eo u;
    public eo v;
    public eo w;
    public eo x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: ChildProcessConnection.java */
    public class a extends iw0.a {
        public a() {
        }

        @Override // defpackage.iw0
        public final void f(String str) {
            qn qnVar;
            synchronized (qn.this.a) {
                qnVar = qn.this;
                qnVar.I = str;
            }
            qnVar.b.post(new pn(this));
        }

        @Override // defpackage.iw0
        public final void l() {
            qn qnVar;
            synchronized (qn.this.a) {
                qnVar = qn.this;
                qnVar.f93J = true;
            }
            qnVar.b.post(new pn(this));
        }

        @Override // defpackage.iw0
        public final void m(final int i, final int i2, final long j, final Bundle bundle) {
            qn.this.b.post(new Runnable() { // from class: on
                @Override // java.lang.Runnable
                public final void run() {
                    qn.a aVar = this.e;
                    int i3 = i;
                    int i4 = i2;
                    long j2 = j;
                    Bundle bundle2 = bundle;
                    qn qnVar = qn.this;
                    int i5 = qnVar.o;
                    if (i5 != 0) {
                        fa1.b("ChildProcessConn", "Pid was sent more than once: pid=%d", Integer.valueOf(i5));
                        return;
                    }
                    qnVar.o = i3;
                    qnVar.p = i4;
                    qn.f fVar = qnVar.j;
                    if (fVar != null) {
                        ((ChildProcessLauncherHelperImpl.a) un.this.b).getClass();
                        o90 o90Var = ChildProcessLauncherHelperImpl.m;
                        a aVar2 = a.l;
                        if (!(Build.VERSION.SDK_INT <= 30)) {
                            int i6 = qnVar.p;
                            if (i6 != 0) {
                                if (bundle2 == null) {
                                    oj2.U(0, 4, "ChromiumAndroidLinker.ChildProcessZygoteState");
                                } else {
                                    int i7 = ChildProcessLauncherHelperImpl.y;
                                    if (i7 == 0) {
                                        oj2.U(1, 4, "ChromiumAndroidLinker.ChildProcessZygoteState");
                                        ChildProcessLauncherHelperImpl.y = qnVar.p;
                                        ChildProcessLauncherHelperImpl.z = bundle2;
                                        a.l.g.f(bundle2);
                                        int i8 = qnVar.o;
                                        for (Map.Entry entry : ChildProcessLauncherHelperImpl.s.entrySet()) {
                                            o90 o90Var2 = ChildProcessLauncherHelperImpl.m;
                                            if (i8 != ((Integer) entry.getKey()).intValue()) {
                                                qn qnVar2 = ((ChildProcessLauncherHelperImpl) entry.getValue()).g.g;
                                                if (qnVar2.p == 0) {
                                                    Bundle bundle3 = ChildProcessLauncherHelperImpl.z;
                                                    aw0 aw0Var = qnVar2.k;
                                                    if (aw0Var != null) {
                                                        try {
                                                            aw0Var.b(bundle3);
                                                        } catch (RemoteException unused) {
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else if (i7 != i6) {
                                        oj2.U(2, 4, "ChromiumAndroidLinker.ChildProcessZygoteState");
                                    } else {
                                        oj2.U(3, 4, "ChromiumAndroidLinker.ChildProcessZygoteState");
                                    }
                                }
                            } else if (ChildProcessLauncherHelperImpl.y != 0 && ChildProcessLauncherHelperImpl.z != null) {
                                Bundle bundle4 = ChildProcessLauncherHelperImpl.z;
                                aw0 aw0Var2 = qnVar.k;
                                if (aw0Var2 != null) {
                                    try {
                                        aw0Var2.b(bundle4);
                                    } catch (RemoteException unused2) {
                                    }
                                }
                            }
                        }
                    }
                    qnVar.j = null;
                    int i9 = qn.K;
                    int i10 = qnVar.p;
                    if (i9 != i10) {
                        if (i10 != 0) {
                            qn.K = i10;
                            oj2.Y(j2, "Android.ChildProcessStartTimeV2.Zygote");
                        }
                    }
                    qn.c cVar = qnVar.i;
                    if (cVar != null) {
                        ((un.b) cVar).a();
                    }
                    qnVar.i = null;
                }
            });
        }
    }

    /* JADX INFO: compiled from: ChildProcessConnection.java */
    public static class b extends RuntimeException {
        public b(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ChildProcessConnection.java */
    public interface c {
    }

    /* JADX INFO: compiled from: ChildProcessConnection.java */
    public static class d {
        public final Bundle a;
        public final List<IBinder> b;

        public d(List list, Bundle bundle) {
            this.a = bundle;
            this.b = list;
        }
    }

    /* JADX INFO: compiled from: ChildProcessConnection.java */
    public interface e {
        void a(qn qnVar);

        void b(qn qnVar);

        void c();
    }

    /* JADX INFO: compiled from: ChildProcessConnection.java */
    public interface f {
    }

    public qn(Context context, ComponentName componentName, ComponentName componentName2, boolean z, boolean z2, Bundle bundle, String str) {
        this.d = componentName2;
        this.e = bundle;
        bundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", z);
        bundle.putString("org.chromium.base.process_launcher.extra.browser_package_name", BuildInfo.a.a.c);
        this.f = z;
        this.s = str;
        this.t = z2;
        this.q = new mn(this, context);
        this.r = new nn(this);
        if (L && componentName2 != null) {
            componentName = componentName2;
        }
        d(componentName);
    }

    public static String f() {
        ClassLoader classLoader = qn.class.getClassLoader();
        return classLoader.toString() + classLoader.hashCode();
    }

    public final void a() {
        if (!g()) {
            fa1.k("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(this.o));
            return;
        }
        if (this.y == 0) {
            this.u.a();
            m();
        }
        this.y++;
    }

    public final void b() {
        if (!g()) {
            fa1.k("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(this.o));
            return;
        }
        if (this.z == 0) {
            this.v.a();
            m();
        }
        this.z++;
    }

    public final boolean c(boolean z) {
        boolean zA;
        boolean z2 = L;
        ComponentName componentName = this.d;
        boolean z3 = z2 && componentName != null;
        if (z) {
            this.y++;
            zA = this.u.a();
        } else {
            this.z++;
            zA = this.v.a();
        }
        if (!zA) {
            if (z3 || componentName == null || !j()) {
                return false;
            }
            z3 = true;
        }
        if (!z3 && componentName != null) {
            this.b.postDelayed(new aw(4, this), 10000L);
        }
        this.x.a();
        m();
        return true;
    }

    public final void d(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        Bundle bundle = this.e;
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        int i = (this.t ? Integer.MIN_VALUE : 0) | 1;
        mn mnVar = (mn) this.q;
        nn nnVar = this.r;
        String str = this.s;
        this.v = mnVar.a(intent, i, nnVar, str);
        this.w = mnVar.a(intent, i | 256, nnVar, str);
        this.u = mnVar.a(intent, i | 64, nnVar, str);
        this.x = mnVar.a(intent, i | 32, nnVar, str);
    }

    public final void e() {
        try {
            TraceEvent.q("ChildProcessConnection.doConnectionSetup", null);
            a aVar = new a();
            try {
                aw0 aw0Var = this.k;
                d dVar = this.h;
                aw0Var.p(dVar.a, aVar, dVar.b);
            } catch (RemoteException e2) {
                Log.e("cr_ChildProcessConn", "Failed to setup connection.", e2);
            }
            this.h = null;
        } finally {
            TraceEvent.C("ChildProcessConnection.doConnectionSetup");
        }
    }

    public final boolean g() {
        return this.k != null;
    }

    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.Object, kn] */
    public final void h(IBinder iBinder) {
        aw0 c0031a;
        if (this.l) {
            return;
        }
        String str = null;
        try {
            TraceEvent.q("ChildProcessConnection.ChildServiceConnection.onServiceConnected", null);
            this.l = true;
            int i = aw0.a.e;
            if (iBinder == null) {
                c0031a = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.base.process_launcher.IChildProcessService");
                c0031a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof aw0)) ? new aw0.a.C0031a(iBinder) : (aw0) iInterfaceQueryLocalInterface;
            }
            this.k = c0031a;
        } catch (Throwable th) {
            TraceEvent.C("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
            throw th;
        }
        if (this.f) {
            try {
                if (!c0031a.r(f())) {
                    e eVar = this.g;
                    if (eVar != null) {
                        eVar.b(this);
                    }
                    l();
                    TraceEvent.C("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
                    return;
                }
            } catch (RemoteException e2) {
                Log.e("cr_ChildProcessConn", "Failed to bind service to connection.", e2);
                TraceEvent.C("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
                return;
            }
            TraceEvent.C("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
            throw th;
        }
        try {
            ApplicationInfo applicationInfoI = this.k.i();
            BuildInfo.a.a.getClass();
            ApplicationInfo applicationInfo = BuildInfo.n;
            if (!Objects.equals(applicationInfo.sourceDir, applicationInfoI.sourceDir)) {
                oj2.U(1, 3, "Android.ChildMismatch.AppInfoError2");
                str = "sourceDir mismatch; parent=" + applicationInfo.sourceDir + " child=" + applicationInfoI.sourceDir;
            } else if (!Arrays.equals(applicationInfo.sharedLibraryFiles, applicationInfoI.sharedLibraryFiles)) {
                oj2.U(2, 3, "Android.ChildMismatch.AppInfoError2");
                str = "sharedLibraryFiles mismatch; parent=" + Arrays.toString(applicationInfo.sharedLibraryFiles) + " child=" + Arrays.toString(applicationInfoI.sharedLibraryFiles);
            }
        } catch (RemoteException unused) {
            oj2.U(3, 3, "Android.ChildMismatch.AppInfoError2");
            str = "child didn't handle getAppInfo()";
        }
        if (str != null) {
            char[] cArr = zt1.a;
            BuildInfo buildInfo = BuildInfo.a.a;
            boolean z = zt1.c(0, buildInfo.c).getLongVersionCode() != buildInfo.d;
            oj2.O("Android.ChildMismatch.BrowserVersionChanged2", z);
            String str2 = str + "; browser version has changed: " + z;
            fa1.b("ChildProcessConn", "Child process code mismatch: %s", str2);
            boolean zMA9sJ3RR = N.MA9sJ3RR("CrashBrowserOnChildMismatchIfBrowserChanged");
            if (N.MA9sJ3RR("CrashBrowserOnAnyChildMismatch") || (z && zMA9sJ3RR)) {
                throw new b(str2);
            }
        } else {
            oj2.U(0, 3, "Android.ChildMismatch.AppInfoError2");
        }
        e eVar2 = this.g;
        if (eVar2 != null) {
            eVar2.c();
        }
        this.m = true;
        if (this.H == null) {
            ?? r11 = new qe1() { // from class: kn
                @Override // defpackage.qe1
                public final void a(int i2) {
                    qn qnVar = this.a;
                    qnVar.b.post(new ln(qnVar, i2));
                }
            };
            ThreadUtils.e(new wq0(9, r11));
            this.H = r11;
        }
        if (this.h != null) {
            e();
        }
        TraceEvent.C("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
    }

    public final void i() {
        if (this.n) {
            return;
        }
        this.n = true;
        Integer numValueOf = Integer.valueOf(this.o);
        StringBuilder sb = new StringBuilder("bindings:");
        sb.append(this.x.l ? "W" : " ");
        sb.append(this.v.l ? "V" : " ");
        sb.append(this.w.l ? "N" : " ");
        sb.append(this.u.l ? "S" : " ");
        fa1.l("ChildProcessConn", "onServiceDisconnected (crash or killed by oom): pid=%d %s", numValueOf, sb.toString());
        l();
        e eVar = this.g;
        if (eVar != null) {
            this.g = null;
            eVar.a(this);
        }
        c cVar = this.i;
        if (cVar != null) {
            ((un.b) cVar).a();
            this.i = null;
        }
    }

    public final boolean j() {
        ComponentName componentName = this.d;
        fa1.k("ChildProcessConn", "Fallback to %s", componentName);
        eo eoVar = this.u;
        boolean z = eoVar.l;
        boolean z2 = this.v.l;
        boolean z3 = this.w.l;
        boolean z4 = this.x.l;
        eoVar.j = null;
        eoVar.b();
        eo eoVar2 = this.v;
        eoVar2.j = null;
        eoVar2.b();
        eo eoVar3 = this.w;
        eoVar3.j = null;
        eoVar3.b();
        eo eoVar4 = this.x;
        eoVar4.j = null;
        eoVar4.b();
        d(componentName);
        if (z && !this.u.a()) {
            return false;
        }
        if (z2 && !this.v.a()) {
            return false;
        }
        if (!z3 || this.w.a()) {
            return !z4 || this.x.a();
        }
        return false;
    }

    public final void k(boolean z, en enVar) {
        try {
            TraceEvent.q("ChildProcessConnection.start", null);
            this.g = enVar;
            if (!c(z)) {
                Log.e("cr_ChildProcessConn", "Failed to establish the service connection.");
                e eVar = this.g;
                if (eVar != null) {
                    this.g = null;
                    eVar.a(this);
                }
            }
        } finally {
            TraceEvent.C("ChildProcessConnection.start");
        }
    }

    public final void l() {
        this.k = null;
        this.h = null;
        this.D = true;
        this.u.b();
        this.x.b();
        this.w.b();
        this.v.b();
        m();
        kn knVar = this.H;
        if (knVar != null) {
            ThreadUtils.e(new wv(9, knVar));
            this.H = null;
        }
    }

    public final void m() {
        int i = this.D ? 0 : this.u.l ? 4 : this.v.l ? 3 : this.w.l ? 2 : 1;
        synchronized (this.a) {
            this.E = i;
            if (!this.D) {
                this.F = i;
            }
        }
    }

    public final void n(int i, int i2) {
        if (this.B == i && this.C == i2) {
            return;
        }
        this.B = i;
        this.C = i2;
        if (g()) {
            eo eoVar = this.x;
            if (eoVar.l) {
                try {
                    eoVar.e.updateServiceGroup(eoVar, i, i2);
                    qe.b(eoVar.e, eoVar.f, eoVar, eoVar.g, eoVar.h, eoVar.i, eoVar.k);
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }
}
