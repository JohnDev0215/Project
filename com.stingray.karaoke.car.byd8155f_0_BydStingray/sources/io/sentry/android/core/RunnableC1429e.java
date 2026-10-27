package io.sentry.android.core;

/* JADX INFO: renamed from: io.sentry.android.core.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1429e implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ ActivityLifecycleIntegration f16163m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ io.sentry.U f16164n;
    public final /* synthetic */ io.sentry.U o;

    public /* synthetic */ RunnableC1429e(ActivityLifecycleIntegration activityLifecycleIntegration, io.sentry.U u4, io.sentry.U u10, int i7) {
        this.l = i7;
        this.f16163m = activityLifecycleIntegration;
        this.f16164n = u4;
        this.o = u10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f16163m.m(this.f16164n, this.o);
                break;
            case 1:
                this.f16163m.m(this.f16164n, this.o);
                break;
            default:
                this.f16163m.getClass();
                ActivityLifecycleIntegration.g(this.f16164n, this.o);
                break;
        }
    }
}
