package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.C1488n0;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.V0;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class SentryPerformanceProvider extends G {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final long f16103q = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Application f16104m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Y f16105n;
    public final P o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final B f16106p;

    public SentryPerformanceProvider() {
        P p10 = new P();
        this.o = p10;
        this.f16106p = new B(p10);
    }

    @Override // android.content.ContentProvider
    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        if (SentryPerformanceProvider.class.getName().equals(providerInfo.authority)) {
            throw new IllegalStateException("An applicationId is required to fulfill the manifest placeholder.");
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        io.sentry.android.core.performance.e.d(this);
        io.sentry.android.core.performance.e eVarB = io.sentry.android.core.performance.e.b();
        Context context = getContext();
        eVarB.o.d(f16103q);
        B b10 = this.f16106p;
        b10.getClass();
        if (context instanceof Application) {
            this.f16104m = (Application) context;
        }
        if (this.f16104m != null) {
            eVarB.f16278n.d(Process.getStartUptimeMillis());
            eVarB.f(this.f16104m);
            Y y10 = new Y(this, eVarB, new AtomicBoolean(false));
            this.f16105n = y10;
            this.f16104m.registerActivityLifecycleCallbacks(y10);
        }
        Context context2 = getContext();
        P p10 = this.o;
        if (context2 == null) {
            p10.k(EnumC1495p1.FATAL, "App. Context from ContentProvider is null", new Object[0]);
        } else {
            File file = new File(new File(context2.getCacheDir(), "sentry"), "app_start_profiling_config");
            if (file.exists() && file.canRead()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    try {
                        V0 v02 = (V0) new C1488n0(F1.empty()).a(bufferedReader, V0.class);
                        if (v02 == null) {
                            p10.k(EnumC1495p1.WARNING, "Unable to deserialize the SentryAppStartProfilingOptions. App start profiling will not start.", new Object[0]);
                        } else if (v02.f15973q) {
                            boolean z6 = v02.f15971n;
                            B8.t tVar = new B8.t(Boolean.valueOf(z6), v02.o, Boolean.valueOf(v02.l), v02.f15970m);
                            eVarB.f16283t = tVar;
                            if (((Boolean) tVar.f809p).booleanValue() && z6) {
                                p10.k(EnumC1495p1.DEBUG, "App start profiling started.", new Object[0]);
                                C1440p c1440p = new C1440p(context2, this.f16106p, new io.sentry.android.core.internal.util.l(context2, p10, b10), p10, v02.f15972p, v02.f15973q, v02.f15974r, new e7.f(13, (byte) 0));
                                eVarB.f16282s = c1440p;
                                c1440p.start();
                            } else {
                                p10.k(EnumC1495p1.DEBUG, "App start profiling was not sampled. It will not start.", new Object[0]);
                            }
                        } else {
                            p10.k(EnumC1495p1.INFO, "Profiling is not enabled. App start profiling will not start.", new Object[0]);
                        }
                        bufferedReader.close();
                    } finally {
                    }
                } catch (FileNotFoundException e10) {
                    p10.s(EnumC1495p1.ERROR, "App start profiling config file not found. ", e10);
                } catch (Throwable th) {
                    p10.s(EnumC1495p1.ERROR, "Error reading app start profiling config file. ", th);
                }
            }
        }
        io.sentry.android.core.performance.e.e(this);
        return true;
    }

    @Override // android.content.ContentProvider
    public final void shutdown() {
        synchronized (io.sentry.android.core.performance.e.b()) {
            try {
                C1440p c1440p = io.sentry.android.core.performance.e.b().f16282s;
                if (c1440p != null) {
                    c1440p.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
