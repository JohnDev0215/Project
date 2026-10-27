package kotlinx.coroutines.scheduling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.coroutines.e1;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
final class f extends e1 implements j, Executor {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7769m = AtomicIntegerFieldUpdater.newUpdater(f.class, "inFlightTasks");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final d f7771i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f7772j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final String f7773k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int f7774l;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final ConcurrentLinkedQueue<Runnable> f7770h = new ConcurrentLinkedQueue<>();
    private volatile int inFlightTasks = 0;

    public f(d dVar, int i7, String str, int i8) {
        this.f7771i = dVar;
        this.f7772j = i7;
        this.f7773k = str;
        this.f7774l = i8;
    }

    private final void d0(Runnable runnable, boolean z6) {
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f7769m;
            if (atomicIntegerFieldUpdater.incrementAndGet(this) <= this.f7772j) {
                this.f7771i.e0(runnable, this, z6);
                return;
            }
            this.f7770h.add(runnable);
            if (atomicIntegerFieldUpdater.decrementAndGet(this) >= this.f7772j) {
                return;
            } else {
                runnable = this.f7770h.poll();
            }
        } while (runnable != null);
    }

    @Override // kotlinx.coroutines.scheduling.j
    public int P() {
        return this.f7774l;
    }

    @Override // kotlinx.coroutines.scheduling.j
    public void Z() {
        Runnable runnablePoll = this.f7770h.poll();
        if (runnablePoll != null) {
            this.f7771i.e0(runnablePoll, this, true);
            return;
        }
        f7769m.decrementAndGet(this);
        Runnable runnablePoll2 = this.f7770h.poll();
        if (runnablePoll2 != null) {
            d0(runnablePoll2, true);
        }
    }

    @Override // kotlinx.coroutines.e0
    public void a0(k5.g gVar, Runnable runnable) {
        d0(runnable, false);
    }

    @Override // kotlinx.coroutines.e0
    public void b0(k5.g gVar, Runnable runnable) {
        d0(runnable, true);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher".toString());
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        d0(runnable, false);
    }

    @Override // kotlinx.coroutines.e0
    public String toString() {
        String str = this.f7773k;
        if (str != null) {
            return str;
        }
        return super.toString() + "[dispatcher = " + this.f7771i + ']';
    }
}
