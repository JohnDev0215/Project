package org.chromium.content.browser;

import J.N;
import android.content.Context;
import android.os.StrictMode;
import android.os.Trace;
import defpackage.aj2;
import defpackage.es3;
import defpackage.iv;
import defpackage.ku2;
import defpackage.oj2;
import defpackage.qj;
import defpackage.rh3;
import defpackage.rj;
import defpackage.sj;
import defpackage.sp;
import defpackage.xz;
import java.util.ArrayList;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: loaded from: classes3.dex */
public class BrowserStartupControllerImpl implements qj {
    public static BrowserStartupControllerImpl l;
    public static boolean m;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean i;
    public boolean j;
    public TracingControllerAndroidImpl k;
    public int h = 0;
    public final ArrayList a = new ArrayList();
    public final ArrayList b = new ArrayList();

    public BrowserStartupControllerImpl() {
        if (!BuildInfo.b() || xz.e()) {
            return;
        }
        PostTask.c(rh3.a, new a());
    }

    @CalledByNative
    public static void browserStartupComplete(int i) {
        BrowserStartupControllerImpl browserStartupControllerImpl = l;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.e(i);
        }
    }

    public static void i() {
        aj2 aj2Var = aj2.c;
        aj2Var.b = true;
        for (int i = 0; i < 4; i++) {
            int[] iArr = aj2Var.a;
            if (iArr[i] > 0) {
                for (int i2 = 0; i2 < iArr[i]; i2++) {
                    oj2.U(i, 4, "Servicification.Startup2");
                }
                iArr[i] = 0;
            }
        }
    }

    @CalledByNative
    public static void minimalBrowserStartupComplete() {
        BrowserStartupControllerImpl browserStartupControllerImpl = l;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.j = true;
            if (!browserStartupControllerImpl.i) {
                if (browserStartupControllerImpl.h == 1) {
                    browserStartupControllerImpl.f(-1);
                }
                i();
            } else {
                browserStartupControllerImpl.h = 0;
                if (browserStartupControllerImpl.c() > 0) {
                    browserStartupControllerImpl.d();
                }
            }
        }
    }

    @CalledByNative
    public static boolean shouldStartGpuProcessOnBrowserStartup() {
        return m;
    }

    public final void b(qj.a aVar) {
        Object obj = ThreadUtils.a;
        if (this.f) {
            PostTask.c(rh3.a, new sj(this, aVar));
        } else {
            this.a.add(aVar);
        }
    }

    public final int c() {
        boolean z = this.h == 1;
        int iM1Y_XVCN = N.M1Y_XVCN(z);
        if (!z) {
            this.i = false;
        }
        this.e = true;
        return iM1Y_XVCN;
    }

    public final void d() {
        PostTask.c(rh3.a, new es3(26, this));
    }

    public final void e(int i) {
        this.f = true;
        this.g = i <= 0;
        ArrayList<qj.a> arrayList = this.a;
        for (qj.a aVar : arrayList) {
            if (this.g) {
                aVar.b();
            } else {
                aVar.a();
            }
        }
        arrayList.clear();
        f(i);
        i();
    }

    public final void f(int i) {
        this.g = i <= 0;
        ArrayList<qj.a> arrayList = this.b;
        for (qj.a aVar : arrayList) {
            if (this.g) {
                aVar.b();
            } else {
                aVar.a();
            }
        }
        arrayList.clear();
    }

    public final boolean g() {
        Object obj = ThreadUtils.a;
        return this.f && this.g;
    }

    public final void h(rj rjVar) {
        if (this.d) {
            return;
        }
        this.d = true;
        Trace.beginSection("prepareToStartBrowserProcess");
        try {
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                org.chromium.base.library_loader.a.l.a();
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                ku2 ku2VarT = ku2.t();
                try {
                    if (!DeviceFormFactor.isTablet()) {
                        iv.e().a("use-mobile-user-agent");
                    }
                    ku2VarT.close();
                    N.MwoPtAzD(false);
                    Trace.endSection();
                    if (rjVar != null) {
                        PostTask.c(rh3.d, rjVar);
                    }
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                Trace.endSection();
            } catch (Throwable unused2) {
            }
            throw th3;
        }
    }

    public final void j(boolean z, boolean z2, sp.b bVar) {
        org.chromium.base.library_loader.a.l.getClass();
        aj2 aj2Var = aj2.c;
        int iA = aj2.a(this.f, this.j, z2);
        if (iA >= 0) {
            if (aj2Var.b) {
                oj2.U(iA, 4, "Servicification.Startup2");
            } else {
                int[] iArr = aj2Var.a;
                iArr[iA] = iArr[iA] + 1;
            }
        }
        if (this.f || (z2 && this.j)) {
            PostTask.c(rh3.a, new sj(this, bVar));
            return;
        }
        if (z2) {
            this.b.add(bVar);
        } else {
            this.a.add(bVar);
        }
        boolean z3 = this.i | (this.h == 1 && !z2);
        this.i = z3;
        if (!this.c) {
            this.c = true;
            m = z | m;
            h(new rj(this, z2));
        } else if (this.j && z3) {
            this.h = 0;
            if (c() > 0) {
                d();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k(boolean r6) {
        /*
            r5 = this;
            org.chromium.base.library_loader.a r0 = org.chromium.base.library_loader.a.l
            r0.getClass()
            boolean r0 = org.chromium.content.browser.BrowserStartupControllerImpl.m
            r6 = r6 | r0
            org.chromium.content.browser.BrowserStartupControllerImpl.m = r6
            aj2 r6 = defpackage.aj2.c
            boolean r0 = r5.f
            boolean r1 = r5.j
            r2 = 0
            int r0 = defpackage.aj2.a(r0, r1, r2)
            r1 = 4
            r3 = 1
            if (r0 >= 0) goto L1a
            goto L2b
        L1a:
            boolean r4 = r6.b
            if (r4 == 0) goto L24
            java.lang.String r6 = "Servicification.Startup2"
            defpackage.oj2.U(r0, r1, r6)
            goto L2b
        L24:
            int[] r6 = r6.a
            r4 = r6[r0]
            int r4 = r4 + r3
            r6[r0] = r4
        L2b:
            boolean r6 = r5.f
            if (r6 != 0) goto L4d
            r6 = 0
            r5.h(r6)
            boolean r6 = r5.e
            if (r6 == 0) goto L3b
            int r6 = r5.h
            if (r6 != r3) goto L47
        L3b:
            r5.h = r2
            int r6 = r5.c()
            if (r6 <= 0) goto L47
            r5.d()
            goto L48
        L47:
            r2 = r3
        L48:
            if (r2 == 0) goto L4d
            J.N.M9iLjy6T()
        L4d:
            boolean r5 = r5.g
            if (r5 == 0) goto L52
            return
        L52:
            p52 r5 = new p52
            r5.<init>(r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.BrowserStartupControllerImpl.k(boolean):void");
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            BrowserStartupControllerImpl.this.b(new C0147a());
        }

        /* JADX INFO: renamed from: org.chromium.content.browser.BrowserStartupControllerImpl$a$a, reason: collision with other inner class name */
        public class C0147a implements qj.a {
            public C0147a() {
            }

            @Override // qj.a
            public final void b() {
                Context context = xz.a;
                a aVar = a.this;
                BrowserStartupControllerImpl.this.k = new TracingControllerAndroidImpl(context);
                TracingControllerAndroidImpl tracingControllerAndroidImpl = BrowserStartupControllerImpl.this.k;
                context.registerReceiver(tracingControllerAndroidImpl.b, tracingControllerAndroidImpl.c, null, null, 2);
            }

            @Override // qj.a
            public final void a() {
            }
        }
    }
}
