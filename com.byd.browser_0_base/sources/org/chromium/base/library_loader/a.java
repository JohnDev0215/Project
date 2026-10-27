package org.chromium.base.library_loader;

import J.N;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import defpackage.fa1;
import defpackage.fl;
import defpackage.iv;
import defpackage.ku2;
import defpackage.oj2;
import defpackage.p52;
import defpackage.qk1;
import defpackage.xz;
import defpackage.z7;
import defpackage.zh3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.chromium.base.JNIUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.library_loader.Linker;

/* JADX INFO: compiled from: LibraryLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final a l = new a();
    public volatile boolean a;
    public boolean b;
    public volatile int c;
    public int e;
    public Linker i;
    public boolean j;
    public boolean k;
    public boolean d = false;
    public final Object f = new Object();
    public final C0070a g = new C0070a();
    public final Object h = new Object();

    /* JADX INFO: renamed from: org.chromium.base.library_loader.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LibraryLoader.java */
    public class C0070a {
        public long a;
        public volatile boolean b;
        public volatile int c = 0;

        public C0070a() {
        }

        public final String a() {
            int i = this.c;
            return i != 0 ? i != 1 ? i != 2 ? "" : "Child" : "Zygote" : "Browser";
        }

        public final void b() {
            if (this.b) {
                return;
            }
            a aVar = a.this;
            if (aVar.d) {
                boolean z = Build.VERSION.SDK_INT <= 30;
                aVar.b().d(0L, z ? 2 : 0, z);
            }
            this.c = 0;
            this.b = true;
        }

        public final void c() {
            boolean z;
            long j;
            long j2;
            long j3;
            a aVar = a.this;
            if (!aVar.d) {
                this.b = true;
                return;
            }
            if (Build.VERSION.SDK_INT <= 30) {
                Linker linkerB = aVar.b();
                synchronized (a.this.h) {
                    j3 = this.a;
                }
                linkerB.d(j3, 1, false);
            } else {
                synchronized (aVar.h) {
                    z = aVar.j;
                }
                if (!z) {
                    if (this.c == 1) {
                        Linker linkerB2 = a.this.b();
                        synchronized (a.this.h) {
                            j2 = this.a;
                        }
                        linkerB2.d(j2, 1, false);
                    } else {
                        Linker linkerB3 = a.this.b();
                        synchronized (a.this.h) {
                            j = this.a;
                        }
                        linkerB3.d(j, 0, false);
                    }
                }
            }
            if (this.c != 1) {
                this.c = 2;
            }
            this.b = true;
        }

        public final void d(Bundle bundle) {
            a aVar = a.this;
            if (aVar.d) {
                Linker linkerB = aVar.b();
                synchronized (linkerB.a) {
                    Linker.LibInfo libInfo = linkerB.b;
                    if (libInfo != null) {
                        long j = libInfo.mLoadAddress;
                        if (j != 0) {
                            bundle.putLong("org.chromium.base.android.linker.base_load_address", j);
                        }
                    }
                }
            }
        }

        public final void e(Bundle bundle) {
            Bundle bundle2;
            a aVar = a.this;
            if (aVar.d) {
                Linker linkerB = aVar.b();
                synchronized (linkerB.a) {
                    if (linkerB.f == 2) {
                        Linker.LibInfo libInfo = linkerB.b;
                        libInfo.getClass();
                        bundle2 = new Bundle();
                        bundle2.putParcelable("libinfo", libInfo);
                    } else {
                        bundle2 = null;
                    }
                    bundle.putBundle("org.chromium.base.android.linker.shared_relros", bundle2);
                }
            }
        }

        public final void f(Bundle bundle) {
            a aVar = a.this;
            if (aVar.d) {
                Linker linkerB = aVar.b();
                linkerB.getClass();
                Bundle bundle2 = bundle.getBundle("org.chromium.base.android.linker.shared_relros");
                if (bundle2 == null) {
                    return;
                }
                bundle2.setClassLoader(Linker.class.getClassLoader());
                Linker.LibInfo libInfo = (Linker.LibInfo) bundle2.getParcelable("libinfo");
                if (libInfo == null) {
                    return;
                }
                synchronized (linkerB.a) {
                    Linker.LibInfo libInfo2 = linkerB.c;
                    if (libInfo2 == null || libInfo2.mRelroFd == -1) {
                        linkerB.c = libInfo;
                        if (linkerB.f == 3) {
                            linkerB.a(false);
                        }
                    }
                }
            }
        }
    }

    public final void a() {
        if (d()) {
            return;
        }
        synchronized (this.h) {
            e(xz.a.getApplicationInfo(), false);
            c();
        }
        f();
    }

    public final Linker b() {
        Linker linker;
        synchronized (this.h) {
            if (this.i == null) {
                this.i = new Linker();
            }
            linker = this.i;
        }
        return linker;
    }

    public final void c() {
        int andSet;
        HashMap map;
        int i;
        if (this.a) {
            return;
        }
        int i2 = 0;
        if (this.e == 1) {
            ku2 ku2VarT = ku2.t();
            try {
                SharedPreferences sharedPreferences = xz.a.a;
                if (sharedPreferences.getBoolean("reached_code_profiler_enabled", false)) {
                    ku2VarT.close();
                    i = TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE;
                } else {
                    int i3 = sharedPreferences.getInt("reached_code_sampling_interval", 0);
                    ku2VarT.close();
                    i = i3;
                }
                if (i > 0) {
                    iv.e().a("enable-reached-code-profiler");
                    iv.e().b("reached-code-sampling-interval-us", Integer.toString(i));
                }
                ku2VarT = ku2.t();
                try {
                    boolean z = sharedPreferences.getBoolean("background_thread_pool_enabled", false);
                    ku2VarT.close();
                    if (z) {
                        iv.e().a("enable-background-thread-pool");
                    }
                } finally {
                }
            } finally {
            }
        }
        ArrayList arrayList = null;
        if (!this.k) {
            AtomicReference<iv> atomicReference = iv.a;
            iv ivVar = atomicReference.get();
            atomicReference.set(new iv.b(ivVar != null ? ivVar.d() : null));
            this.k = true;
        }
        if (!N.M81WqFvs(this.e)) {
            Log.e("cr_LibraryLoader", "error calling LibraryLoaderJni.get().libraryLoaded");
            throw new p52(1);
        }
        Log.i("cr_LibraryLoader", "Successfully loaded native library");
        if (zh3.b) {
            qk1 qk1Var = new qk1();
            fl flVar = zh3.a;
            ReentrantReadWriteLock reentrantReadWriteLock = flVar.a;
            reentrantReadWriteLock.writeLock().lock();
            try {
                flVar.f = qk1Var;
                if (flVar.b.isEmpty()) {
                    andSet = 0;
                    map = null;
                } else {
                    map = flVar.b;
                    flVar.b = new HashMap();
                    andSet = flVar.c.getAndSet(0);
                }
                if (!flVar.d.isEmpty()) {
                    arrayList = flVar.d;
                    flVar.d = new ArrayList();
                    int i4 = flVar.e;
                    flVar.e = 0;
                    i2 = i4;
                }
                reentrantReadWriteLock.readLock().lock();
                if (map != null) {
                    try {
                        flVar.g(andSet, map);
                    } catch (Throwable th) {
                        reentrantReadWriteLock.readLock().unlock();
                        throw th;
                    }
                }
                if (arrayList != null) {
                    flVar.h(i2, arrayList);
                }
                reentrantReadWriteLock.readLock().unlock();
            } finally {
                reentrantReadWriteLock.writeLock().unlock();
            }
        }
        TraceEvent.g.set(true);
        N.MFFzPOVw();
        TraceEvent.a aVar = TraceEvent.j;
        if (aVar != null) {
            aVar.a();
        }
        this.a = true;
    }

    @Deprecated
    public final boolean d() {
        if (this.a) {
            return this.c == 2;
        }
        return false;
    }

    public final void e(ApplicationInfo applicationInfo, boolean z) {
        if (this.c >= 1) {
            return;
        }
        try {
            TraceEvent traceEventH0 = TraceEvent.H0("LibraryLoader.loadMainDexAlreadyLocked", null);
            try {
                long jUptimeMillis = SystemClock.uptimeMillis();
                long jCurrentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
                boolean z2 = this.d;
                String[] strArr = z7.n;
                if (!z2 || this.b) {
                    String str = applicationInfo.packageName;
                    TraceEvent traceEventH02 = TraceEvent.H0("LibraryLoader.preloadAlreadyLocked", null);
                    if (traceEventH02 != null) {
                        traceEventH02.close();
                    }
                    System.loadLibrary(strArr[0]);
                } else {
                    g(applicationInfo, strArr[0]);
                }
                long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
                this.c = 1;
                oj2.b0(jUptimeMillis2, "ChromiumAndroidLinker." + this.g.a() + "LoadTime2");
                C0070a c0070a = this.g;
                oj2.b0(SystemClock.currentThreadTimeMillis() - jCurrentThreadTimeMillis, "ChromiumAndroidLinker." + c0070a.a() + "ThreadLoadTime");
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } catch (UnsatisfiedLinkError e) {
            throw new p52(2, e);
        }
    }

    public final void f() {
        if (this.c == 2) {
            return;
        }
        synchronized (this.f) {
            if (this.c == 2) {
                return;
            }
            TraceEvent traceEventH0 = TraceEvent.H0("LibraryLoader.loadNonMainDex", null);
            try {
                if (!JNIUtils.isSelectiveJniRegistrationEnabled()) {
                    N.MIOj213u();
                }
                this.c = 2;
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
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
    }

    public final void g(ApplicationInfo applicationInfo, String str) {
        Linker linkerB = b();
        fa1.h("LibraryLoader", "Loading %s from within %s", str, applicationInfo.sourceDir);
        synchronized (linkerB.a) {
            int i = 1;
            try {
                linkerB.d(0L, 2, true);
                if (!linkerB.d) {
                    i = 2;
                }
                linkerB.b(i, str);
            } catch (UnsatisfiedLinkError unused) {
                Log.w("cr_Linker", "Failed to load native library with shared RELRO, retrying without");
                try {
                    linkerB.b.mLoadAddress = 0L;
                    linkerB.b(0, str);
                } catch (UnsatisfiedLinkError e) {
                    Log.w("cr_Linker", "Failed to load native library without RELRO sharing");
                    throw e;
                }
            }
        }
        C0070a c0070a = this.g;
        a aVar = a.this;
        if (aVar.d) {
            Linker linkerB2 = aVar.b();
            String strA = c0070a.a();
            synchronized (linkerB2.a) {
                Linker.a aVar2 = linkerB2.e;
                if (aVar2 != null) {
                    oj2.b0(aVar2.b, "ChromiumAndroidLinker.TimeToFindWebViewReservation." + (aVar2.a ? "Found" : "NotFound") + "." + strA);
                }
            }
        }
    }

    public final void h(int i) {
        int i2 = this.e;
        if (i == i2) {
            return;
        }
        if (i2 != 0) {
            throw new IllegalStateException(String.format("Trying to change the LibraryProcessType from %d to %d", Integer.valueOf(this.e), Integer.valueOf(i)));
        }
        this.e = i;
    }
}
