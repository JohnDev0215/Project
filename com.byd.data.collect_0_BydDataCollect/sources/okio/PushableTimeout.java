package okio;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class PushableTimeout extends Timeout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Timeout f5830e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f5831f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f5832g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f5833h;

    PushableTimeout() {
    }

    void k() {
        this.f5830e.h(this.f5833h, TimeUnit.NANOSECONDS);
        if (this.f5831f) {
            this.f5830e.d(this.f5832g);
        } else {
            this.f5830e.a();
        }
    }

    void l(Timeout timeout) {
        this.f5830e = timeout;
        boolean zE = timeout.e();
        this.f5831f = zE;
        this.f5832g = zE ? timeout.c() : -1L;
        long jI = timeout.i();
        this.f5833h = jI;
        timeout.h(Timeout.f(jI, i()), TimeUnit.NANOSECONDS);
        if (this.f5831f && e()) {
            timeout.d(Math.min(c(), this.f5832g));
        } else if (e()) {
            timeout.d(c());
        }
    }
}
