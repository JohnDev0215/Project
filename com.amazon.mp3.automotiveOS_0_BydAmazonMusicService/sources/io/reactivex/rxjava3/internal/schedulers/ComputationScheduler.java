package io.reactivex.rxjava3.internal.schedulers;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
public final class ComputationScheduler extends Scheduler {
    static final int MAX_THREADS = cap(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx3.computation-threads", 0).intValue());
    static final FixedSchedulerPool NONE;
    static final PoolWorker SHUTDOWN_WORKER;
    static final RxThreadFactory THREAD_FACTORY;
    final AtomicReference<FixedSchedulerPool> pool;
    final ThreadFactory threadFactory;

    static int cap(int cpuCount, int paramThreads) {
        return (paramThreads <= 0 || paramThreads > cpuCount) ? cpuCount : paramThreads;
    }

    static {
        PoolWorker poolWorker = new PoolWorker(new RxThreadFactory("RxComputationShutdown"));
        SHUTDOWN_WORKER = poolWorker;
        poolWorker.dispose();
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx3.computation-priority", 5).intValue())), true);
        THREAD_FACTORY = rxThreadFactory;
        FixedSchedulerPool fixedSchedulerPool = new FixedSchedulerPool(0, rxThreadFactory);
        NONE = fixedSchedulerPool;
        fixedSchedulerPool.shutdown();
    }

    static final class FixedSchedulerPool {
        final int cores;
        final PoolWorker[] eventLoops;
        long n;

        FixedSchedulerPool(int maxThreads, ThreadFactory threadFactory) {
            this.cores = maxThreads;
            this.eventLoops = new PoolWorker[maxThreads];
            for (int i = 0; i < maxThreads; i++) {
                this.eventLoops[i] = new PoolWorker(threadFactory);
            }
        }

        public PoolWorker getEventLoop() {
            int i = this.cores;
            if (i == 0) {
                return ComputationScheduler.SHUTDOWN_WORKER;
            }
            PoolWorker[] poolWorkerArr = this.eventLoops;
            long j = this.n;
            this.n = 1 + j;
            return poolWorkerArr[(int) (j % ((long) i))];
        }

        public void shutdown() {
            for (PoolWorker poolWorker : this.eventLoops) {
                poolWorker.dispose();
            }
        }
    }

    public ComputationScheduler() {
        this(THREAD_FACTORY);
    }

    public ComputationScheduler(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        this.pool = new AtomicReference<>(NONE);
        start();
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Scheduler.Worker createWorker() {
        return new EventLoopWorker(this.pool.get().getEventLoop());
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
        return this.pool.get().getEventLoop().scheduleDirect(run, delay, unit);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public Disposable schedulePeriodicallyDirect(Runnable run, long initialDelay, long period, TimeUnit unit) {
        return this.pool.get().getEventLoop().schedulePeriodicallyDirect(run, initialDelay, period, unit);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public void start() {
        FixedSchedulerPool fixedSchedulerPool = new FixedSchedulerPool(MAX_THREADS, this.threadFactory);
        if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.pool, NONE, fixedSchedulerPool)) {
            return;
        }
        fixedSchedulerPool.shutdown();
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public void shutdown() {
        AtomicReference<FixedSchedulerPool> atomicReference = this.pool;
        FixedSchedulerPool fixedSchedulerPool = NONE;
        FixedSchedulerPool andSet = atomicReference.getAndSet(fixedSchedulerPool);
        if (andSet != fixedSchedulerPool) {
            andSet.shutdown();
        }
    }

    static final class EventLoopWorker extends Scheduler.Worker {
        private final ListCompositeDisposable both;
        volatile boolean disposed;
        private final PoolWorker poolWorker;
        private final ListCompositeDisposable serial;
        private final CompositeDisposable timed;

        EventLoopWorker(PoolWorker poolWorker) {
            this.poolWorker = poolWorker;
            ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
            this.serial = listCompositeDisposable;
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            this.timed = compositeDisposable;
            ListCompositeDisposable listCompositeDisposable2 = new ListCompositeDisposable();
            this.both = listCompositeDisposable2;
            listCompositeDisposable2.add(listCompositeDisposable);
            listCompositeDisposable2.add(compositeDisposable);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.disposed) {
                return;
            }
            this.disposed = true;
            this.both.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable action) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            return this.poolWorker.scheduleActual(action, 0L, TimeUnit.MILLISECONDS, this.serial);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public Disposable schedule(Runnable action, long delayTime, TimeUnit unit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            return this.poolWorker.scheduleActual(action, delayTime, unit, this.timed);
        }
    }

    static final class PoolWorker extends NewThreadWorker {
        PoolWorker(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}
