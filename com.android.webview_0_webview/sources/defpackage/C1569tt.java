package defpackage;

import J.N;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.SystemClock;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.JNIUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: renamed from: tt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1569tt {
    public static final boolean k = false;
    public static C1569tt l = new C1569tt();
    public volatile boolean a;
    public volatile int b;
    public final Object c = new Object();
    public final Object d = new Object();
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public long j;

    public static void l() {
    }

    public final void a() {
        if (this.h) {
            return;
        }
        AtomicReference atomicReference = Es.a;
        Es es = (Es) atomicReference.get();
        atomicReference.set(new Ds(es != null ? es.d() : null));
        this.h = true;
    }

    public void b() {
        if (f()) {
            return;
        }
        c();
        h();
    }

    public void c() {
        synchronized (this.c) {
            g(Is.a.getApplicationInfo(), false);
            e();
        }
    }

    public final boolean d() {
        return this.e && !this.f && Build.VERSION.SDK_INT >= 29;
    }

    public final void e() {
        if (this.a) {
            return;
        }
        if (this.i == 1) {
            C0683dt c0683dtX = C0683dt.x();
            try {
                boolean z = Hs.a.getBoolean("reached_code_profiler_enabled", false);
                c0683dtX.close();
                if (z) {
                    Es.e().a("enable-reached-code-profiler");
                }
            } catch (Throwable th) {
                try {
                    c0683dtX.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
                throw th;
            }
        }
        a();
        if (!N.M81WqFvs(this.i)) {
            Ts.a("LibraryLoader", "error calling LibraryLoaderJni.get().libraryLoaded", new Object[0]);
            throw new C1734wt(1);
        }
        if (!"83.0.4103.120".equals(N.M$HdV9JM())) {
            Ts.a("LibraryLoader", "Expected native library version number \"%s\", actual native library version number \"%s\"", "83.0.4103.120", N.M$HdV9JM());
            throw new C1734wt(3);
        }
        Ts.d("LibraryLoader", "Loaded native library version number \"%s\"", "83.0.4103.120");
        synchronized (Lt.b) {
            Lt.a.j(new Ht());
        }
        N.MFFzPOVw();
        this.a = true;
    }

    public boolean f() {
        return this.a && this.b == 2;
    }

    public void g(ApplicationInfo applicationInfo, boolean z) {
        String[] strArr = AbstractC1679vt.a;
        if (this.b >= 1) {
            return;
        }
        try {
            TraceEvent traceEventM = TraceEvent.M("LibraryLoader.loadMainDexAlreadyLocked");
            try {
                if (!this.g) {
                    this.e = false;
                    this.f = false;
                    this.g = true;
                }
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (!n() || z) {
                    TraceEvent traceEventM2 = TraceEvent.M("LibraryLoader.preloadAlreadyLocked");
                    if (traceEventM2 != null) {
                        traceEventM2.close();
                    }
                    for (String str : strArr) {
                        System.loadLibrary(str);
                    }
                } else {
                    j(applicationInfo, strArr[0]);
                }
                this.j = SystemClock.uptimeMillis() - jUptimeMillis;
                this.b = 1;
                if (traceEventM != null) {
                    traceEventM.close();
                }
            } finally {
            }
        } catch (UnsatisfiedLinkError e) {
            throw new C1734wt(2, e);
        }
    }

    public void h() {
        if (this.b == 2) {
            return;
        }
        synchronized (this.d) {
            if (this.b == 2) {
                return;
            }
            TraceEvent traceEventM = TraceEvent.M("LibraryLoader.loadNonMainDex");
            try {
                if (!JNIUtils.isSelectiveJniRegistrationEnabled()) {
                    N.MIOj213u();
                }
                this.b = 2;
                if (traceEventM != null) {
                    traceEventM.close();
                }
            } finally {
            }
        }
    }

    public void i(Context context) {
        synchronized (this.c) {
            if (this.b != 0 && context != Is.a) {
                throw new IllegalStateException("Attempt to load again from alternate context.");
            }
            g(context.getApplicationInfo(), false);
        }
        h();
    }

    public final void j(ApplicationInfo applicationInfo, String str) {
        Linker linkerB = Linker.b();
        Ts.d("LibraryLoader", "Loading %s", str);
        try {
            Objects.requireNonNull(linkerB);
            synchronized (Linker.e) {
                linkerB.c(str, true);
            }
        } catch (UnsatisfiedLinkError unused) {
            Ts.f("LibraryLoader", "Failed to load native library with shared RELRO, retrying without", new Object[0]);
            Objects.requireNonNull(linkerB);
            synchronized (Linker.e) {
                linkerB.c(str, false);
            }
        }
    }

    public void k() {
        if (this.e) {
            synchronized (this.c) {
                N.MiAWbgCC(this.j);
            }
        }
    }

    public void m(int i) {
        int i2 = this.i;
        if (i == i2) {
            return;
        }
        if (i2 != 0) {
            throw new IllegalStateException(String.format("Trying to change the LibraryProcessType from %d to %d", Integer.valueOf(this.i), Integer.valueOf(i)));
        }
        this.i = i;
    }

    public boolean n() {
        return this.e && !d();
    }
}
