package io.sentry.android.core;

import android.app.Application;
import io.sentry.F1;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16291m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16292n;

    public /* synthetic */ r(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f16291m = obj;
        this.f16292n = obj2;
    }

    private final void a() {
        SystemEventsBreadcrumbsIntegration systemEventsBreadcrumbsIntegration = (SystemEventsBreadcrumbsIntegration) this.f16291m;
        F1 f12 = (F1) this.f16292n;
        synchronized (systemEventsBreadcrumbsIntegration.f16110q) {
            try {
                if (!systemEventsBreadcrumbsIntegration.f16109p) {
                    systemEventsBreadcrumbsIntegration.b((SentryAndroidOptions) f12);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Y y10;
        switch (this.l) {
            case 0:
                AnrIntegration anrIntegration = (AnrIntegration) this.f16291m;
                SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.f16292n;
                synchronized (anrIntegration.f16034n) {
                    try {
                        if (!anrIntegration.f16033m) {
                            anrIntegration.b(sentryAndroidOptions);
                        }
                    } finally {
                    }
                    break;
                }
                return;
            case 1:
                PhoneStateBreadcrumbsIntegration phoneStateBreadcrumbsIntegration = (PhoneStateBreadcrumbsIntegration) this.f16291m;
                F1 f12 = (F1) this.f16292n;
                synchronized (phoneStateBreadcrumbsIntegration.f16090q) {
                    try {
                        if (!phoneStateBreadcrumbsIntegration.f16089p) {
                            phoneStateBreadcrumbsIntegration.b(f12);
                        }
                    } finally {
                    }
                    break;
                }
                return;
            case 2:
                Y y11 = (Y) this.f16291m;
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.f16292n;
                y11.getClass();
                if (atomicBoolean.compareAndSet(false, true)) {
                    SentryPerformanceProvider sentryPerformanceProvider = y11.o;
                    synchronized (sentryPerformanceProvider) {
                        io.sentry.android.core.performance.e eVarB = io.sentry.android.core.performance.e.b();
                        eVarB.o.e();
                        eVarB.f16278n.e();
                        Application application = sentryPerformanceProvider.f16104m;
                        if (application != null && (y10 = sentryPerformanceProvider.f16105n) != null) {
                            application.unregisterActivityLifecycleCallbacks(y10);
                        }
                        break;
                    }
                    return;
                }
                return;
            case 3:
                a();
                return;
            default:
                TempSensorBreadcrumbsIntegration tempSensorBreadcrumbsIntegration = (TempSensorBreadcrumbsIntegration) this.f16291m;
                F1 f13 = (F1) this.f16292n;
                synchronized (tempSensorBreadcrumbsIntegration.f16116q) {
                    try {
                        if (!tempSensorBreadcrumbsIntegration.f16115p) {
                            tempSensorBreadcrumbsIntegration.b(f13);
                        }
                    } finally {
                    }
                    break;
                }
                return;
        }
    }
}
