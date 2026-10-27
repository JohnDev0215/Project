package org.chromium.content.browser;

import J.N;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import defpackage.an2;
import defpackage.ar2;
import defpackage.aw0;
import defpackage.gg3;
import defpackage.gn;
import defpackage.ht0;
import defpackage.i22;
import defpackage.ln;
import defpackage.lq2;
import defpackage.mq2;
import defpackage.o90;
import defpackage.q8;
import defpackage.qe3;
import defpackage.qn;
import defpackage.re;
import defpackage.rh3;
import defpackage.tn;
import defpackage.un;
import defpackage.uv;
import defpackage.xn;
import defpackage.xz;
import defpackage.z7;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.BuildInfo;
import org.chromium.base.SysUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.base.task.PostTask;
import org.chromium.content.app.SandboxedProcessService;

/* JADX INFO: loaded from: classes3.dex */
public final class ChildProcessLauncherHelperImpl {
    public static boolean n;
    public static lq2 o;
    public static lq2 p;
    public static gn q;
    public static xn r;
    public static gn.d t;
    public static re v;
    public static boolean w;
    public static ar2 x;
    public static int y;
    public static volatile Bundle z;
    public final xn a;
    public final re b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public boolean f;
    public final un g;
    public long h;
    public long i;
    public int j;
    public boolean k;
    public boolean l;
    public static final o90 m = new o90(13);
    public static final HashMap s = new HashMap();
    public static final int u = -1;

    @SuppressLint({"HandlerLeak"})
    public static final b A = new b();

    public class a extends un.c {
        public a() {
        }

        @Override // un.c
        public final qn a(gn gnVar, tn tnVar) {
            ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = ChildProcessLauncherHelperImpl.this;
            if (!childProcessLauncherHelperImpl.e) {
                return null;
            }
            lq2 lq2Var = childProcessLauncherHelperImpl.c ? ChildProcessLauncherHelperImpl.o : ChildProcessLauncherHelperImpl.p;
            if (lq2Var == null) {
                return null;
            }
            qn qnVar = lq2Var.b;
            if ((qnVar == null || lq2Var.d != null) || lq2Var.a != gnVar || lq2Var.d != null) {
                return null;
            }
            lq2Var.d = tnVar;
            if (lq2Var.c) {
                LauncherThread.a(new mq2(tnVar));
                lq2Var.b = null;
                lq2Var.c = false;
            }
            return qnVar;
        }
    }

    public class b extends Handler {

        public class a implements Runnable {
            public final /* synthetic */ qn e;

            public a(qn qnVar) {
                this.e = qnVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                qn qnVar = this.e;
                if (qnVar != null) {
                    aw0 aw0Var = qnVar.k;
                    qnVar.l();
                    if (aw0Var != null) {
                        try {
                            aw0Var.y();
                        } catch (RemoteException unused) {
                        }
                    }
                    synchronized (qnVar.a) {
                        qnVar.G = true;
                    }
                    qn.e eVar = qnVar.g;
                    if (eVar != null) {
                        qnVar.g = null;
                        eVar.a(qnVar);
                    }
                }
            }
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                ChildProcessLauncherHelperImpl.A.sendMessageDelayed(ChildProcessLauncherHelperImpl.b(), 100L);
            } else {
                if (i != 1) {
                    return;
                }
                LauncherThread.a(new a((qn) message.obj));
            }
        }
    }

    public ChildProcessLauncherHelperImpl(long j, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, boolean z2, boolean z3, boolean z4, ht0 ht0Var) {
        a aVar = new a();
        boolean z5 = true;
        this.j = 1;
        this.h = j;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.g = new un(LauncherThread.b, aVar, strArr, fileDescriptorInfoArr, a(xz.a, z2), ht0Var == null ? null : Arrays.asList(ht0Var));
        z7.E(strArr, "type");
        if (z2) {
            this.a = r;
            this.b = v;
        } else {
            this.a = null;
            this.b = null;
        }
        synchronized (ApplicationStatus.a) {
            if (ApplicationStatus.b == 0) {
                z5 = false;
            }
        }
        if (z5 && x == null) {
            PostTask.c(rh3.b, new qe3(23));
        }
    }

    public static gn a(Context context, boolean z2) {
        gn eVar;
        if (!z2) {
            if (t == null) {
                Handler handler = LauncherThread.b;
                String packageName = xz.a.getPackageName();
                try {
                    Bundle bundle = context.getPackageManager().getApplicationInfo(packageName, 128).metaData;
                    int i = bundle != null ? bundle.getInt("org.chromium.content.browser.NUM_PRIVILEGED_SERVICES", -1) : -1;
                    if (i < 0) {
                        throw new uv("Illegal meta data value for number of child services");
                    }
                    gn.a(context, packageName, "org.chromium.content.app.PrivilegedProcessService");
                    t = new gn.d(handler, null, packageName, "org.chromium.content.app.PrivilegedProcessService", true, i);
                } catch (PackageManager.NameNotFoundException unused) {
                    throw new uv("Could not get application info.");
                }
            }
            return t;
        }
        if (q == null) {
            String packageName2 = xz.a.getPackageName();
            gg3 gg3Var = new gg3(15);
            int i2 = u;
            if (i2 != -1) {
                eVar = new gn.d(new Handler(), gg3Var, packageName2, !TextUtils.isEmpty(null) ? null : SandboxedProcessService.class.getName(), false, i2);
            } else {
                Handler handler2 = LauncherThread.b;
                gn.a(context, packageName2, "org.chromium.content.app.SandboxedProcessService");
                BuildInfo buildInfo = BuildInfo.a.a;
                int i3 = Build.VERSION.SDK_INT;
                boolean z3 = true;
                boolean z4 = i3 == 29 && buildInfo.i.startsWith("OnePlus/");
                if (i3 != 29 || z4 || ((UserManager) xz.a.getSystemService("user")).isSystemUser()) {
                    if (!SysUtils.isLowEndDevice() && !z4) {
                        z3 = false;
                    }
                    eVar = new gn.e(handler2, gg3Var, packageName2, "org.chromium.content.app.SandboxedProcessService".concat(z3 ? "1" : "0"), z3 ? null : "org.chromium.content.app.SandboxedProcessService1");
                } else {
                    eVar = new gn.a(handler2, gg3Var, packageName2);
                }
            }
            q = eVar;
            r = new xn();
        }
        return q;
    }

    public static Message b() {
        ArrayList arrayList = r.g;
        qn qnVar = arrayList.isEmpty() ? null : ((xn.a) arrayList.get(arrayList.size() - 1)).a;
        int i = qnVar == null ? 0 : 1;
        Message message = new Message();
        message.what = i;
        message.obj = qnVar;
        return message;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.content.browser.ChildProcessLauncherHelperImpl createAndStart(long r14, java.lang.String[] r16, org.chromium.base.process_launcher.FileDescriptorInfo[] r17, boolean r18) {
        /*
            r0 = r16
            java.lang.String r1 = "type"
            java.lang.String r1 = defpackage.z7.E(r0, r1)
            boolean r2 = org.chromium.base.TraceEvent.f
            r3 = 1
            if (r2 == 0) goto L1b
            int r2 = r0.length
            int r2 = r2 + r3
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r2)
            java.lang.String[] r0 = (java.lang.String[]) r0
            int r2 = r0.length
            int r2 = r2 - r3
            java.lang.String r4 = "--trace-early-java-in-child"
            r0[r2] = r4
        L1b:
            r8 = r0
            java.lang.String r0 = "renderer"
            boolean r0 = r0.equals(r1)
            java.lang.String r2 = "gpu-process"
            r4 = 0
            if (r0 != 0) goto L45
            boolean r0 = r2.equals(r1)
            if (r0 == 0) goto L35
            java.lang.String r0 = "ReduceGpuPriorityOnBackground"
            boolean r0 = J.N.Mudil8Bg(r0)
            r11 = r0
            goto L47
        L35:
            java.lang.String r0 = "service-sandbox-type"
            java.lang.String r0 = defpackage.z7.E(r8, r0)
            java.lang.String r5 = "none"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L45
            r11 = r4
            goto L47
        L45:
            r11 = r4
            r4 = r3
        L47:
            boolean r0 = r2.equals(r1)
            if (r0 == 0) goto L53
            ht0 r0 = new ht0
            r0.<init>()
            goto L54
        L53:
            r0 = 0
        L54:
            r13 = r0
            org.chromium.content.browser.ChildProcessLauncherHelperImpl r0 = new org.chromium.content.browser.ChildProcessLauncherHelperImpl
            r5 = r0
            r6 = r14
            r9 = r17
            r10 = r4
            r12 = r18
            r5.<init>(r6, r8, r9, r10, r11, r12, r13)
            un r1 = r0.g
            r1.c(r3, r3)
            long r1 = java.lang.System.currentTimeMillis()
            r0.i = r1
            if (r4 == 0) goto L95
            boolean r1 = org.chromium.content.browser.ChildProcessLauncherHelperImpl.n
            if (r1 != 0) goto L95
            org.chromium.content.browser.ChildProcessLauncherHelperImpl.n = r3
            xn r1 = org.chromium.content.browser.ChildProcessLauncherHelperImpl.r
            if (r1 == 0) goto L95
            boolean r1 = J.N.MyYLH6Fg()
            if (r1 == 0) goto L95
            xn r1 = org.chromium.content.browser.ChildProcessLauncherHelperImpl.r
            r1.i = r3
            r1.c()
            boolean r2 = r1.j
            if (r2 == 0) goto L8a
            goto L95
        L8a:
            java.lang.Runnable r2 = r1.h
            android.os.Handler r4 = r1.e
            r5 = 1000(0x3e8, double:4.94E-321)
            r4.postDelayed(r2, r5)
            r1.j = r3
        L95:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ChildProcessLauncherHelperImpl.createAndStart(long, java.lang.String[], org.chromium.base.process_launcher.FileDescriptorInfo[], boolean):org.chromium.content.browser.ChildProcessLauncherHelperImpl");
    }

    @CalledByNative
    public static FileDescriptorInfo makeFdInfo(int i, int i2, boolean z2, long j, long j2) {
        ParcelFileDescriptor parcelFileDescriptorFromFd;
        if (z2) {
            parcelFileDescriptorFromFd = ParcelFileDescriptor.adoptFd(i2);
        } else {
            try {
                parcelFileDescriptorFromFd = ParcelFileDescriptor.fromFd(i2);
            } catch (IOException e) {
                Log.e("cr_ChildProcLH", "Invalid FD provided for process connection, aborting connection.", e);
                return null;
            }
        }
        return new FileDescriptorInfo(i, parcelFileDescriptorFromFd, j, j2);
    }

    @CalledByNative
    public static void stop(int i) {
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = (ChildProcessLauncherHelperImpl) s.get(Integer.valueOf(i));
        if (childProcessLauncherHelperImpl != null) {
            un unVar = childProcessLauncherHelperImpl.g;
            int i2 = unVar.g.o;
            qn qnVar = unVar.g;
            qnVar.l();
            qn.e eVar = qnVar.g;
            if (eVar != null) {
                qnVar.g = null;
                eVar.a(qnVar);
            }
        }
    }

    public final void c() {
        if (this.l) {
            return;
        }
        qn qnVar = this.g.g;
        if (qnVar.g() && qnVar.u.l) {
            if (qnVar.g()) {
                int i = qnVar.y - 1;
                qnVar.y = i;
                if (i == 0) {
                    qnVar.u.b();
                    qnVar.m();
                }
            }
            this.l = true;
        }
    }

    @CalledByNative
    public final void dumpProcessStack(int i) {
        aw0 aw0Var;
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = (ChildProcessLauncherHelperImpl) s.get(Integer.valueOf(i));
        if (childProcessLauncherHelperImpl == null || (aw0Var = childProcessLauncherHelperImpl.g.g.k) == null) {
            return;
        }
        try {
            aw0Var.o();
        } catch (RemoteException e) {
            Log.e("cr_ChildProcessConn", "Failed to dump process stack.", e);
        }
    }

    @CalledByNative
    public final int getEffectiveChildBindingState() {
        int i;
        qn qnVar = this.g.g;
        if (qnVar == null) {
            return 0;
        }
        synchronized (qnVar.a) {
            i = qnVar.E;
        }
        return i;
    }

    @CalledByNative
    public final void getTerminationInfoAndStop(long j) {
        String str;
        int i;
        boolean z2;
        boolean z3;
        qn qnVar = this.g.g;
        if (qnVar == null) {
            return;
        }
        synchronized (qnVar.a) {
            str = qnVar.I;
        }
        if (str != null && !this.f) {
            this.f = true;
            PostTask.c(rh3.b, new i22(8, str));
        }
        synchronized (qnVar.a) {
            i = qnVar.F;
        }
        synchronized (qnVar.a) {
            z2 = qnVar.G;
        }
        synchronized (qnVar.a) {
            z3 = qnVar.f93J;
        }
        N.MJcoZ9pW(j, i, z2, z3, str != null);
        LauncherThread.a(new an2(9, this));
    }

    @CalledByNative
    public final void setPriority(int i, boolean z2, boolean z3, boolean z4, long j, boolean z5, boolean z6, int i2) {
        re reVar;
        if (((ChildProcessLauncherHelperImpl) s.get(Integer.valueOf(i))) == null) {
            return;
        }
        qn qnVar = this.g.g;
        boolean zMudil8Bg = N.Mudil8Bg("BackgroundMediaRendererHasModerateBinding");
        int i3 = 0;
        int i4 = ((z2 && j == 0) || i2 == 2 || (z3 && !zMudil8Bg)) ? 2 : ((z2 && j > 0 && z5) || z6 || i2 == 1 || (z3 && zMudil8Bg) || z4) ? 1 : 0;
        int i5 = -1;
        if (z2 && !this.k && (reVar = this.b) != null) {
            q8 q8Var = reVar.e;
            if (!(!q8Var.add(qnVar))) {
                re.b(qnVar);
                int i6 = reVar.f;
                if (i6 != -1 && q8Var.g == i6 + 1) {
                    reVar.j++;
                    reVar.e(1);
                    reVar.c();
                }
            }
        }
        this.k = z2;
        if (this.j != i4 && i4 != 0) {
            if (i4 == 1) {
                qnVar.b();
            } else if (i4 == 2) {
                qnVar.a();
            }
        }
        xn xnVar = this.a;
        if (xnVar != null) {
            while (true) {
                ArrayList arrayList = xnVar.g;
                if (i3 >= arrayList.size()) {
                    break;
                }
                if (((xn.a) arrayList.get(i3)).a == qnVar) {
                    i5 = i3;
                    break;
                }
                i3++;
            }
            xn.a aVar = (xn.a) xnVar.g.get(i5);
            aVar.b = z2;
            aVar.c = j;
            aVar.d = z5;
            aVar.e = i2;
            xnVar.b(i5);
            re reVar2 = this.b;
            if (reVar2 != null) {
                reVar2.c();
            }
        }
        int i7 = this.j;
        if (i7 != i4 && i7 != 0) {
            ln lnVar = new ln(i7, qnVar);
            if (System.currentTimeMillis() - this.i < 1000) {
                LauncherThread.b.postDelayed(lnVar, 500L);
            } else {
                lnVar.run();
            }
        }
        this.j = i4;
    }
}
