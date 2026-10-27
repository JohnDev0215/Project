package io.sentry.android.core;

/* JADX INFO: renamed from: io.sentry.android.core.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1449z implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AppLifecycleIntegration f16302m;

    public /* synthetic */ RunnableC1449z(AppLifecycleIntegration appLifecycleIntegration, int i7) {
        this.l = i7;
        this.f16302m = appLifecycleIntegration;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f16302m.g();
                break;
            default:
                this.f16302m.b();
                break;
        }
    }
}
