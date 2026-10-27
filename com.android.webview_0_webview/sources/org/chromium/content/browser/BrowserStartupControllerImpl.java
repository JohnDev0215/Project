package org.chromium.content.browser;

import J.N;
import android.os.StrictMode;
import defpackage.AbstractC0538bE;
import defpackage.C1569tt;
import defpackage.Cz;
import defpackage.DD;
import defpackage.ED;
import defpackage.Hy;
import defpackage.It;
import defpackage.Jy;
import defpackage.Ly;
import defpackage.RO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.BuildInfo;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.ui.base.ResourceBundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BrowserStartupControllerImpl implements ED {
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
    public final List a = new ArrayList();
    public final List b = new ArrayList();

    public BrowserStartupControllerImpl() {
        if (BuildInfo.b()) {
            PostTask.a(AbstractC0538bE.a, new Hy(this), 0L);
        }
    }

    public static void browserStartupComplete(int i) {
        BrowserStartupControllerImpl browserStartupControllerImpl = l;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.c(i);
        }
    }

    public static void serviceManagerStartupComplete() {
        BrowserStartupControllerImpl browserStartupControllerImpl = l;
        if (browserStartupControllerImpl != null) {
            browserStartupControllerImpl.j = true;
            if (!browserStartupControllerImpl.i) {
                if (browserStartupControllerImpl.h == 1) {
                    browserStartupControllerImpl.d(-1);
                }
                browserStartupControllerImpl.f();
            } else {
                browserStartupControllerImpl.h = 0;
                if (browserStartupControllerImpl.a() > 0) {
                    browserStartupControllerImpl.b(1);
                }
            }
        }
    }

    public static boolean shouldStartGpuProcessOnBrowserStartup() {
        return m;
    }

    public int a() {
        boolean z = this.h == 1;
        int iM1Y_XVCN = N.M1Y_XVCN(z);
        if (!z) {
            this.i = false;
        }
        this.e = true;
        return iM1Y_XVCN;
    }

    public final void b(int i) {
        PostTask.a(AbstractC0538bE.e, new Jy(this, i), 0L);
    }

    public final void c(int i) {
        this.f = true;
        this.g = i <= 0;
        for (DD dd : this.a) {
            if (this.g) {
                dd.a();
            } else {
                dd.b();
            }
        }
        this.a.clear();
        d(i);
        f();
    }

    public final void d(int i) {
        this.g = i <= 0;
        for (DD dd : this.b) {
            if (this.g) {
                dd.a();
            } else {
                dd.b();
            }
        }
        this.b.clear();
    }

    public void e(boolean z, Runnable runnable) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            C1569tt.l.b();
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            Ly ly = new Ly(this, z, runnable);
            RO.a().a = AbstractC0538bE.e;
            if (runnable == null) {
                Objects.requireNonNull(RO.a());
                ly.run();
                return;
            }
            RO roA = RO.a();
            Objects.requireNonNull(roA);
            ThreadUtils.b();
            if (!(ResourceBundle.a.length == 0)) {
                throw null;
            }
            PostTask.a(roA.a, ly, 0L);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th;
        }
    }

    public void f() {
        int[] iArr;
        Cz cz = Cz.c;
        cz.b = true;
        for (int i = 0; i < 4; i++) {
            if (cz.a[i] > 0) {
                int i2 = 0;
                while (true) {
                    iArr = cz.a;
                    if (i2 >= iArr[i]) {
                        break;
                    }
                    It.d("Servicification.Startup2", i, 4);
                    i2++;
                }
                iArr[i] = 0;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(int r4, boolean r5) {
        /*
            r3 = this;
            tt r4 = defpackage.C1569tt.l
            java.util.Objects.requireNonNull(r4)
            Cz r4 = defpackage.Cz.c
            boolean r0 = r3.f
            boolean r1 = r3.j
            r2 = 0
            int r0 = defpackage.Cz.a(r0, r1, r2)
            r4.b(r0)
            boolean r4 = r3.f
            if (r4 != 0) goto L5f
            boolean r4 = r3.c
            if (r4 == 0) goto L1f
            boolean r4 = r3.d
            if (r4 != 0) goto L2b
        L1f:
            java.lang.String r4 = "BrowserStartupController.prepareToStartBrowserProcess"
            android.os.Trace.beginSection(r4)
            r4 = 0
            r3.e(r5, r4)     // Catch: java.lang.Throwable -> L53
            android.os.Trace.endSection()
        L2b:
            boolean r4 = r3.e
            r5 = 1
            if (r4 != 0) goto L3c
            r3.h = r2
            int r4 = r3.a()
            if (r4 <= 0) goto L4c
            r3.b(r5)
            goto L4d
        L3c:
            int r4 = r3.h
            if (r4 != r5) goto L4c
            r3.h = r2
            int r4 = r3.a()
            if (r4 <= 0) goto L4c
            r3.b(r5)
            goto L4d
        L4c:
            r2 = 1
        L4d:
            if (r2 == 0) goto L5f
            J.N.M9iLjy6T()
            goto L5f
        L53:
            r4 = move-exception
            android.os.Trace.endSection()     // Catch: java.lang.Throwable -> L58
            goto L5e
        L58:
            r5 = move-exception
            Jk r0 = defpackage.AbstractC0279Pk.a
            r0.a(r4, r5)
        L5e:
            throw r4
        L5f:
            boolean r4 = r3.g
            if (r4 == 0) goto L64
            return
        L64:
            wt r4 = new wt
            r5 = 4
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.BrowserStartupControllerImpl.g(int, boolean):void");
    }
}
