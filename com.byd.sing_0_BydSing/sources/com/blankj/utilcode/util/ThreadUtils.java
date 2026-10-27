package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class ThreadUtils {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Handler f2569a = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<Integer, Map<Integer, ExecutorService>> f2570b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Map<e, ExecutorService> f2571c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f2572d = Runtime.getRuntime().availableProcessors();
    private static final Timer e = new Timer();
    private static Executor f;

    private static final class LinkedBlockingQueue4Util extends LinkedBlockingQueue<Runnable> {
        private int mCapacity;
        private volatile f mPool;

        LinkedBlockingQueue4Util() {
            this.mCapacity = Integer.MAX_VALUE;
        }

        @Override // java.util.concurrent.LinkedBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
        public boolean offer(Runnable runnable) {
            if (this.mCapacity > size() || this.mPool == null || this.mPool.getPoolSize() >= this.mPool.getMaximumPoolSize()) {
                return super.offer(runnable);
            }
            return false;
        }

        LinkedBlockingQueue4Util(boolean z) {
            this.mCapacity = Integer.MAX_VALUE;
            if (z) {
                this.mCapacity = 0;
            }
        }

        LinkedBlockingQueue4Util(int i) {
            this.mCapacity = Integer.MAX_VALUE;
            this.mCapacity = i;
        }
    }

    static final class UtilsThreadFactory extends AtomicLong implements ThreadFactory {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final AtomicInteger f2573b = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;

        class a extends Thread {
            a(Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    super.run();
                } catch (Throwable th) {
                    Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                }
            }
        }

        class b implements Thread.UncaughtExceptionHandler {
            b() {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                System.out.println(th);
            }
        }

        UtilsThreadFactory(String str, int i) {
            this(str, i, false);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            a aVar = new a(runnable, this.namePrefix + getAndIncrement());
            aVar.setDaemon(this.isDaemon);
            aVar.setUncaughtExceptionHandler(new b());
            aVar.setPriority(this.priority);
            return aVar;
        }

        UtilsThreadFactory(String str, int i, boolean z) {
            this.namePrefix = str + "-pool-" + f2573b.getAndIncrement() + "-thread-";
            this.priority = i;
            this.isDaemon = z;
        }
    }

    static class a extends TimerTask {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ExecutorService f2576b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ e f2577c;

        a(ExecutorService executorService, e eVar) {
            this.f2576b = executorService;
            this.f2577c = eVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.f2576b.execute(this.f2577c);
        }
    }

    static class b extends TimerTask {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ExecutorService f2578b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ e f2579c;

        b(ExecutorService executorService, e eVar) {
            this.f2578b = executorService;
            this.f2579c = eVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.f2578b.execute(this.f2579c);
        }
    }

    static class c implements Executor {
        c() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            ThreadUtils.q(runnable);
        }
    }

    public static abstract class d<T> extends e<T> {
        @Override // com.blankj.utilcode.util.ThreadUtils.e
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.e
        public void onFail(Throwable th) {
            Log.e("ThreadUtils", "onFail: ", th);
        }
    }

    public static abstract class e<T> implements Runnable {
        private static final int CANCELLED = 4;
        private static final int COMPLETING = 3;
        private static final int EXCEPTIONAL = 2;
        private static final int INTERRUPTED = 5;
        private static final int NEW = 0;
        private static final int RUNNING = 1;
        private static final int TIMEOUT = 6;
        private Executor deliver;
        private volatile boolean isSchedule;
        private f mTimeoutListener;
        private long mTimeoutMillis;
        private Timer mTimer;
        private volatile Thread runner;
        private final AtomicInteger state = new AtomicInteger(0);

        class a extends TimerTask {
            a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (e.this.isDone() || e.this.mTimeoutListener == null) {
                    return;
                }
                e.this.timeout();
                e.this.mTimeoutListener.a();
                e.this.onDone();
            }
        }

        class b implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2581b;

            b(Object obj) {
                this.f2581b = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                e.this.onSuccess(this.f2581b);
            }
        }

        class c implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2583b;

            c(Object obj) {
                this.f2583b = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                e.this.onSuccess(this.f2583b);
                e.this.onDone();
            }
        }

        class d implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Throwable f2585b;

            d(Throwable th) {
                this.f2585b = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                e.this.onFail(this.f2585b);
                e.this.onDone();
            }
        }

        /* JADX INFO: renamed from: com.blankj.utilcode.util.ThreadUtils$e$e, reason: collision with other inner class name */
        class RunnableC0081e implements Runnable {
            RunnableC0081e() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e.this.onCancel();
                e.this.onDone();
            }
        }

        public interface f {
            void a();
        }

        private Executor getDeliver() {
            Executor executor = this.deliver;
            return executor == null ? ThreadUtils.m() : executor;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSchedule(boolean z) {
            this.isSchedule = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void timeout() {
            synchronized (this.state) {
                if (this.state.get() > 1) {
                    return;
                }
                this.state.set(6);
                if (this.runner != null) {
                    this.runner.interrupt();
                }
            }
        }

        public void cancel() {
            cancel(true);
        }

        public abstract T doInBackground() throws Throwable;

        public boolean isCanceled() {
            return this.state.get() >= 4;
        }

        public boolean isDone() {
            return this.state.get() > 1;
        }

        public abstract void onCancel();

        protected void onDone() {
            ThreadUtils.f2571c.remove(this);
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
                this.mTimeoutListener = null;
            }
        }

        public abstract void onFail(Throwable th);

        public abstract void onSuccess(T t);

        @Override // java.lang.Runnable
        public void run() {
            if (this.isSchedule) {
                if (this.runner == null) {
                    if (!this.state.compareAndSet(0, 1)) {
                        return;
                    }
                    this.runner = Thread.currentThread();
                    if (this.mTimeoutListener != null) {
                        Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                    }
                } else if (this.state.get() != 1) {
                    return;
                }
            } else {
                if (!this.state.compareAndSet(0, 1)) {
                    return;
                }
                this.runner = Thread.currentThread();
                if (this.mTimeoutListener != null) {
                    Timer timer = new Timer();
                    this.mTimer = timer;
                    timer.schedule(new a(), this.mTimeoutMillis);
                }
            }
            try {
                T tDoInBackground = doInBackground();
                if (this.isSchedule) {
                    if (this.state.get() != 1) {
                        return;
                    }
                    getDeliver().execute(new b(tDoInBackground));
                } else if (this.state.compareAndSet(1, 3)) {
                    getDeliver().execute(new c(tDoInBackground));
                }
            } catch (InterruptedException unused) {
                this.state.compareAndSet(4, 5);
            } catch (Throwable th) {
                if (this.state.compareAndSet(1, 2)) {
                    getDeliver().execute(new d(th));
                }
            }
        }

        public e<T> setDeliver(Executor executor) {
            this.deliver = executor;
            return this;
        }

        public e<T> setTimeout(long j, f fVar) {
            this.mTimeoutMillis = j;
            this.mTimeoutListener = fVar;
            return this;
        }

        public void cancel(boolean z) {
            synchronized (this.state) {
                if (this.state.get() > 1) {
                    return;
                }
                this.state.set(4);
                if (z && this.runner != null) {
                    this.runner.interrupt();
                }
                getDeliver().execute(new RunnableC0081e());
            }
        }
    }

    static final class f extends ThreadPoolExecutor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final AtomicInteger f2588a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private LinkedBlockingQueue4Util f2589b;

        f(int i, int i2, long j, TimeUnit timeUnit, LinkedBlockingQueue4Util linkedBlockingQueue4Util, ThreadFactory threadFactory) {
            super(i, i2, j, timeUnit, linkedBlockingQueue4Util, threadFactory);
            this.f2588a = new AtomicInteger();
            linkedBlockingQueue4Util.mPool = this;
            this.f2589b = linkedBlockingQueue4Util;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ExecutorService b(int i, int i2) {
            if (i == -8) {
                return new f(ThreadUtils.f2572d + 1, (ThreadUtils.f2572d * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cpu", i2));
            }
            if (i == -4) {
                return new f((ThreadUtils.f2572d * 2) + 1, (ThreadUtils.f2572d * 2) + 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("io", i2));
            }
            if (i == -2) {
                return new f(0, 128, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue4Util(true), new UtilsThreadFactory("cached", i2));
            }
            if (i == -1) {
                return new f(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("single", i2));
            }
            return new f(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue4Util(), new UtilsThreadFactory("fixed(" + i + ")", i2));
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void afterExecute(Runnable runnable, Throwable th) {
            this.f2588a.decrementAndGet();
            super.afterExecute(runnable, th);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (isShutdown()) {
                return;
            }
            this.f2588a.incrementAndGet();
            try {
                super.execute(runnable);
            } catch (RejectedExecutionException unused) {
                Log.e("ThreadUtils", "This will not happen!");
                this.f2589b.offer(runnable);
            } catch (Throwable unused2) {
                this.f2588a.decrementAndGet();
            }
        }
    }

    public static void d(e eVar) {
        if (eVar == null) {
            return;
        }
        eVar.cancel();
    }

    private static <T> void e(ExecutorService executorService, e<T> eVar) {
        f(executorService, eVar, 0L, 0L, null);
    }

    private static <T> void f(ExecutorService executorService, e<T> eVar, long j, long j2, TimeUnit timeUnit) {
        Map<e, ExecutorService> map = f2571c;
        synchronized (map) {
            if (map.get(eVar) != null) {
                Log.e("ThreadUtils", "Task can only be executed once.");
                return;
            }
            map.put(eVar, executorService);
            if (j2 != 0) {
                eVar.setSchedule(true);
                e.scheduleAtFixedRate(new b(executorService, eVar), timeUnit.toMillis(j), timeUnit.toMillis(j2));
            } else if (j == 0) {
                executorService.execute(eVar);
            } else {
                e.schedule(new a(executorService, eVar), timeUnit.toMillis(j));
            }
        }
    }

    public static <T> void g(int i, e<T> eVar, long j, TimeUnit timeUnit) {
        j(n(i), eVar, j, timeUnit);
    }

    public static <T> void h(e<T> eVar) {
        e(n(-1), eVar);
    }

    public static <T> void i(e<T> eVar, long j, TimeUnit timeUnit) {
        j(n(-1), eVar, j, timeUnit);
    }

    private static <T> void j(ExecutorService executorService, e<T> eVar, long j, TimeUnit timeUnit) {
        f(executorService, eVar, j, 0L, timeUnit);
    }

    public static ExecutorService k() {
        return n(-2);
    }

    public static ExecutorService l() {
        return n(-8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Executor m() {
        if (f == null) {
            f = new c();
        }
        return f;
    }

    private static ExecutorService n(int i) {
        return o(i, 5);
    }

    private static ExecutorService o(int i, int i2) {
        ExecutorService executorServiceB;
        Map<Integer, Map<Integer, ExecutorService>> map = f2570b;
        synchronized (map) {
            Map<Integer, ExecutorService> map2 = map.get(Integer.valueOf(i));
            if (map2 == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                executorServiceB = f.b(i, i2);
                concurrentHashMap.put(Integer.valueOf(i2), executorServiceB);
                map.put(Integer.valueOf(i), concurrentHashMap);
            } else {
                executorServiceB = map2.get(Integer.valueOf(i2));
                if (executorServiceB == null) {
                    executorServiceB = f.b(i, i2);
                    map2.put(Integer.valueOf(i2), executorServiceB);
                }
            }
        }
        return executorServiceB;
    }

    public static ExecutorService p() {
        return n(-1);
    }

    public static void q(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f2569a.post(runnable);
        }
    }

    public static void r(Runnable runnable, long j) {
        f2569a.postDelayed(runnable, j);
    }
}
