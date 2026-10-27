package org.chromium.content.browser;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import defpackage.AbstractC1590uD;
import defpackage.AbstractC1680vu;
import defpackage.BinderC1080kz;
import defpackage.C1406qu;
import defpackage.C1569tt;
import defpackage.C1735wu;
import defpackage.C1794xy;
import defpackage.Fy;
import defpackage.Fz;
import defpackage.Gu;
import defpackage.Is;
import defpackage.My;
import defpackage.Qy;
import defpackage.Ry;
import defpackage.ServiceConnectionC1186mu;
import defpackage.Ts;
import defpackage.Vy;
import defpackage.Xt;
import defpackage.Yy;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.library_loader.Linker;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChildProcessLauncherHelperImpl {
    public static boolean m;
    public static Fz n;
    public static Fz o;
    public static Xt p;
    public static Yy q;
    public static Xt s;
    public static boolean u;
    public static long v;
    public final Yy a;
    public final Fy b;
    public final boolean c;
    public final boolean d;
    public final C1735wu f;
    public long g;
    public long h;
    public boolean j;
    public int l;
    public static final Map r = new HashMap();
    public static int t = -1;
    public final AbstractC1680vu e = new Qy(this);
    public int i = 1;
    public final Object k = new Object();

    public ChildProcessLauncherHelperImpl(long j, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, boolean z, boolean z2, IBinder iBinder) {
        this.g = j;
        this.c = z;
        this.d = z2;
        this.f = new C1735wu(LauncherThread.c, this.e, strArr, fileDescriptorInfoArr, b(Is.a, z), iBinder == null ? null : Arrays.asList(iBinder));
        AbstractC1590uD.a(strArr, "type");
        if (z) {
            this.a = q;
            this.b = null;
            this.l = -1;
        } else {
            this.a = null;
            this.b = null;
            this.l = -2;
        }
    }

    public static ChildProcessLauncherHelperImpl a(int i) {
        return (ChildProcessLauncherHelperImpl) r.get(Integer.valueOf(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.Xt b(android.content.Context r12, boolean r13) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ChildProcessLauncherHelperImpl.b(android.content.Context, boolean):Xt");
    }

    public static final void c(int i, C1406qu c1406qu) {
        if (i != 0) {
            if (i == 1) {
                if (c1406qu.e()) {
                    int i2 = c1406qu.q - 1;
                    c1406qu.q = i2;
                    if (i2 == 0) {
                        ((ServiceConnectionC1186mu) c1406qu.n).b();
                        c1406qu.l();
                        return;
                    }
                    return;
                }
                return;
            }
            if (i == 2 && c1406qu.e()) {
                int i3 = c1406qu.p - 1;
                c1406qu.p = i3;
                if (i3 == 0) {
                    ((ServiceConnectionC1186mu) c1406qu.m).b();
                    c1406qu.l();
                }
            }
        }
    }

    public static ChildProcessLauncherHelperImpl createAndStart(long j, String[] strArr, FileDescriptorInfo[] fileDescriptorInfoArr, boolean z) {
        String strA = AbstractC1590uD.a(strArr, "type");
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = new ChildProcessLauncherHelperImpl(j, strArr, fileDescriptorInfoArr, "renderer".equals(strA) || !("gpu-process".equals(strA) || "network".equals(AbstractC1590uD.a(strArr, "service-sandbox-type"))), z, "gpu-process".equals(strA) ? new BinderC1080kz() : null);
        childProcessLauncherHelperImpl.f.d(true, true);
        childProcessLauncherHelperImpl.h = System.currentTimeMillis();
        if (!m) {
            m = true;
            if (q != null && N.Mudil8Bg("ServiceGroupImportance")) {
                Yy yy = q;
                yy.w = true;
                yy.h();
                if (!yy.x) {
                    yy.s.postDelayed(yy.v, 1000L);
                    yy.x = true;
                }
            }
        }
        return childProcessLauncherHelperImpl;
    }

    public static Bundle d(Bundle bundle) {
        long j;
        if (My.i) {
            bundle.putInt("org.chromium.content.common.child_service_params.library_process_type", My.d);
        }
        bundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", My.i && My.e);
        if (!u) {
            if (C1569tt.l.n()) {
                Linker linkerB = Linker.b();
                Objects.requireNonNull(linkerB);
                synchronized (Linker.e) {
                    linkerB.a();
                    linkerB.d();
                    j = linkerB.c;
                }
                v = j;
                if (j == 0) {
                    Ts.d("ChildProcLH", "Shared RELRO support disabled!", new Object[0]);
                }
            }
            u = true;
        }
        long j2 = v;
        C1794xy c1794xy = j2 == 0 ? null : new C1794xy(j2, true);
        if (c1794xy != null) {
            bundle.putLong("org.chromium.content.common.linker_params.base_load_address", c1794xy.a);
            bundle.putBoolean("org.chromium.content.common.linker_params.wait_for_shared_relro", c1794xy.b);
            bundle.putString("org.chromium.content.common.linker_params.test_runner_class_name", c1794xy.c);
            bundle.putInt("org.chromium.content.common.linker_params.linker_implementation", c1794xy.d);
        }
        return bundle;
    }

    private void dumpProcessStack(int i) {
        Gu gu;
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImplA = a(i);
        if (childProcessLauncherHelperImplA == null || (gu = childProcessLauncherHelperImplA.f.g.h) == null) {
            return;
        }
        try {
            gu.k();
        } catch (RemoteException e) {
            Ts.a("ChildProcessConn", "Failed to dump process stack.", e);
        }
    }

    public static void e(Context context, boolean z) {
        LauncherThread.c.post(new Ry(context, z));
    }

    private void getTerminationInfoAndStop(long j) {
        int i;
        int[] iArrCopyOf;
        int i2;
        boolean z;
        C1406qu c1406qu = this.f.g;
        if (c1406qu == null) {
            return;
        }
        synchronized (this.k) {
            i = this.l;
        }
        synchronized (C1406qu.A) {
            if (c1406qu.x != null) {
                iArrCopyOf = Arrays.copyOf(c1406qu.x, 4);
            } else {
                int[] iArr = C1406qu.B;
                iArrCopyOf = Arrays.copyOf(C1406qu.B, 4);
                if (c1406qu.u != 0) {
                    iArrCopyOf[c1406qu.u] = iArrCopyOf[r3] - 1;
                }
            }
        }
        synchronized (C1406qu.A) {
            i2 = c1406qu.v;
        }
        synchronized (C1406qu.A) {
            z = c1406qu.w;
        }
        N.MJcoZ9pW(j, i2, z, c1406qu.d(), iArrCopyOf[3], iArrCopyOf[2], iArrCopyOf[1], i);
        LauncherThread.c.post(new Runnable(this) { // from class: Oy
            public final ChildProcessLauncherHelperImpl s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                C1406qu c1406qu2 = this.s.f.g;
                int i3 = c1406qu2.l;
                c1406qu2.k();
                c1406qu2.f();
            }
        });
    }

    public static FileDescriptorInfo makeFdInfo(int i, int i2, boolean z, long j, long j2) {
        ParcelFileDescriptor parcelFileDescriptorFromFd;
        if (z) {
            parcelFileDescriptorFromFd = ParcelFileDescriptor.adoptFd(i2);
        } else {
            try {
                parcelFileDescriptorFromFd = ParcelFileDescriptor.fromFd(i2);
            } catch (IOException e) {
                Ts.a("ChildProcLH", "Invalid FD provided for process connection, aborting connection.", e);
                return null;
            }
        }
        return new FileDescriptorInfo(i, parcelFileDescriptorFromFd, j, j2);
    }

    private void setPriority(int i, boolean z, boolean z2, boolean z3, long j, boolean z4, boolean z5, int i2) {
        boolean z6;
        boolean z7;
        if (a(i) == null) {
            return;
        }
        final C1406qu c1406qu = this.f.g;
        if (My.i && My.f) {
            z6 = false;
            z7 = false;
        } else {
            z6 = z;
            z7 = z5;
        }
        boolean zMudil8Bg = N.Mudil8Bg("BackgroundMediaRendererHasModerateBinding");
        int i3 = ((z6 && j == 0) || i2 == 2 || (z2 && !zMudil8Bg)) ? 2 : ((z6 && j > 0 && z4) || z7 || i2 == 1 || (z2 && zMudil8Bg) || z3) ? 1 : 0;
        this.j = z6;
        if (this.i != i3 && i3 != 0) {
            if (i3 != 1) {
                if (i3 == 2) {
                    if (c1406qu.e()) {
                        if (c1406qu.p == 0) {
                            ((ServiceConnectionC1186mu) c1406qu.m).a();
                            c1406qu.l();
                        }
                        c1406qu.p++;
                    } else {
                        Ts.f("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(c1406qu.l));
                    }
                }
            } else if (c1406qu.e()) {
                if (c1406qu.q == 0) {
                    ((ServiceConnectionC1186mu) c1406qu.n).a();
                    c1406qu.l();
                }
                c1406qu.q++;
            } else {
                Ts.f("ChildProcessConn", "The connection is not bound for %d", Integer.valueOf(c1406qu.l));
            }
        }
        Yy yy = this.a;
        if (yy != null) {
            int iF = yy.f(c1406qu);
            Vy vy = (Vy) yy.u.get(iF);
            vy.b = z6;
            vy.c = j;
            vy.d = z4;
            vy.e = i2;
            yy.g(iF);
        }
        final int i4 = this.i;
        if (i4 != i3 && i4 != 0) {
            Runnable runnable = new Runnable(i4, c1406qu) { // from class: Py
                public final int s;
                public final C1406qu t;

                {
                    this.s = i4;
                    this.t = c1406qu;
                }

                @Override // java.lang.Runnable
                public void run() {
                    ChildProcessLauncherHelperImpl.c(this.s, this.t);
                }
            };
            if (System.currentTimeMillis() - this.h < 1000) {
                LauncherThread.c.postDelayed(runnable, 500L);
            } else {
                runnable.run();
            }
        }
        this.i = i3;
    }

    public static void stop(int i) {
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImplA = a(i);
        if (childProcessLauncherHelperImplA != null) {
            C1406qu c1406qu = childProcessLauncherHelperImplA.f.g;
            int i2 = c1406qu.l;
            c1406qu.k();
            c1406qu.f();
        }
    }
}
