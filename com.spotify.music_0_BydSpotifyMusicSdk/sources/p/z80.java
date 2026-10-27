package p;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class z80 extends g2 implements ScheduledFuture {
    public static final /* synthetic */ int q = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ScheduledFuture f215p;

    public z80(y80 y80Var) {
        this.f215p = y80Var.a(new oh1(18, this));
    }

    @Override // p.g2
    public final void c() {
        ScheduledFuture scheduledFuture = this.f215p;
        Object obj = this.c;
        scheduledFuture.cancel((obj instanceof b2) && ((b2) obj).a);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Delayed delayed) {
        return this.f215p.compareTo(delayed);
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.f215p.getDelay(timeUnit);
    }
}
