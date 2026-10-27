package d.a.p.g;

import d.a.j;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.schedulers.RxThreadFactory;
import io.reactivex.internal.schedulers.ScheduledRunnable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SingleScheduler.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final RxThreadFactory f3240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ScheduledExecutorService f3241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicReference<ScheduledExecutorService> f3242d;

    /* JADX INFO: compiled from: SingleScheduler.java */
    public static final class a extends j.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ScheduledExecutorService f3243c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final d.a.m.a f3244d = new d.a.m.a();
        public volatile boolean s;

        public a(ScheduledExecutorService scheduledExecutorService) {
            this.f3243c = scheduledExecutorService;
        }

        @Override // d.a.j.b
        public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.s) {
                return EmptyDisposable.INSTANCE;
            }
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(d.a.r.a.q(runnable), this.f3244d);
            this.f3244d.c(scheduledRunnable);
            try {
                scheduledRunnable.setFuture(j <= 0 ? this.f3243c.submit((Callable) scheduledRunnable) : this.f3243c.schedule((Callable) scheduledRunnable, j, timeUnit));
                return scheduledRunnable;
            } catch (RejectedExecutionException e2) {
                dispose();
                d.a.r.a.o(e2);
                return EmptyDisposable.INSTANCE;
            }
        }

        @Override // d.a.m.b
        public void dispose() {
            if (this.s) {
                return;
            }
            this.s = true;
            this.f3244d.dispose();
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.s;
        }
    }

    static {
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(0);
        f3241c = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.shutdown();
        f3240b = new RxThreadFactory("RxSingleScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.single-priority", 5).intValue())));
    }

    public f() {
        AtomicReference<ScheduledExecutorService> atomicReference = new AtomicReference<>();
        this.f3242d = atomicReference;
        atomicReference.lazySet(d());
    }

    public static ScheduledExecutorService d() {
        return e.a(f3240b);
    }

    @Override // d.a.j
    public j.b a() {
        return new a(this.f3242d.get());
    }

    @Override // d.a.j
    public d.a.m.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Runnable runnableQ = d.a.r.a.q(runnable);
        try {
            return d.a.m.c.a(j <= 0 ? this.f3242d.get().submit(runnableQ) : this.f3242d.get().schedule(runnableQ, j, timeUnit));
        } catch (RejectedExecutionException e2) {
            d.a.r.a.o(e2);
            return EmptyDisposable.INSTANCE;
        }
    }
}
