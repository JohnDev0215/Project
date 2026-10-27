package p2;

import android.os.Handler;
import android.os.Looper;
import com.byd.base.thread.Priority;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;

/* JADX INFO: compiled from: DispatcherImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile c f7012b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Priority f7011a = Priority.NORMAL;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object f7013c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f7014d = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Map<Runnable, Priority> f7017g = new WeakHashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map<Runnable, Future<?>> f7018h = new WeakHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p2.b f7015e = new p2.b(new PriorityBlockingQueue(11, new a()));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ExecutorC0104c f7016f = new ExecutorC0104c(this, null);

    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class a implements Comparator<Runnable> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Runnable runnable, Runnable runnable2) {
            return b(runnable).value() - b(runnable2).value();
        }

        public final Priority b(Runnable runnable) {
            Priority priority = (Priority) c.this.f7017g.get(runnable);
            return priority == null ? c.f7011a : priority;
        }
    }

    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ RunnableFuture f7020d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Priority f7021e;

        public b(RunnableFuture runnableFuture, Priority priority) {
            this.f7020d = runnableFuture;
            this.f7021e = priority;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.n(this.f7020d, this.f7021e);
        }
    }

    public static c m() {
        if (f7012b == null) {
            synchronized (c.class) {
                if (f7012b == null) {
                    f7012b = new c();
                }
            }
        }
        return f7012b;
    }

    @Override // p2.d
    public void a(Runnable runnable, long j8, Priority priority) {
        if (j8 <= 0) {
            n(runnable, priority);
            return;
        }
        RunnableFuture runnableFutureNewTaskFor = this.f7015e.newTaskFor(runnable, null);
        this.f7018h.put(runnable, runnableFutureNewTaskFor);
        this.f7014d.postDelayed(new b(runnableFutureNewTaskFor, priority), j8);
    }

    @Override // p2.d
    public void b(Runnable runnable) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        this.f7014d.post(runnable);
    }

    @Override // p2.d
    public void c(Runnable runnable, long j8) {
        a(runnable, j8, this.f7017g.get(runnable));
    }

    @Override // p2.d
    public void d(Runnable runnable, long j8) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        this.f7014d.postDelayed(runnable, j8);
    }

    @Override // p2.d
    public void e(Runnable runnable, Priority priority) {
        n(runnable, priority);
    }

    @Override // p2.d
    public void f(Runnable runnable) {
        this.f7014d.removeCallbacks(runnable);
    }

    @Override // p2.d
    public void g(Runnable runnable) {
        e(runnable, this.f7017g.get(runnable));
    }

    public final void n(Runnable runnable, Priority priority) {
        Future<?> futureSubmit;
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        if (priority == null) {
            priority = f7011a;
        }
        this.f7017g.put(runnable, priority);
        if (runnable instanceof Future) {
            this.f7015e.execute(runnable);
            futureSubmit = (Future) runnable;
        } else {
            futureSubmit = this.f7015e.submit(runnable);
        }
        this.f7018h.put(runnable, futureSubmit);
    }

    /* JADX INFO: renamed from: p2.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class ExecutorC0104c implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayDeque<RunnableFuture> f7023a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RunnableFuture f7024b;

        /* JADX INFO: renamed from: p2.c$c$a */
        /* JADX INFO: compiled from: DispatcherImpl.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ Runnable f7026d;

            public a(Runnable runnable) {
                this.f7026d = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.f7026d.run();
                } finally {
                    ExecutorC0104c.this.a();
                }
            }
        }

        public ExecutorC0104c() {
            this.f7023a = new ArrayDeque<>();
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        
            r2.f7025c.f7015e.execute(r2.f7024b);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized void a() {
            /*
                r2 = this;
                monitor-enter(r2)
            L1:
                java.util.ArrayDeque<java.util.concurrent.RunnableFuture> r0 = r2.f7023a     // Catch: java.lang.Throwable -> L21
                java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> L21
                java.util.concurrent.RunnableFuture r0 = (java.util.concurrent.RunnableFuture) r0     // Catch: java.lang.Throwable -> L21
                r2.f7024b = r0     // Catch: java.lang.Throwable -> L21
                if (r0 != 0) goto Le
                goto L1f
            Le:
                boolean r0 = r0.isCancelled()     // Catch: java.lang.Throwable -> L21
                if (r0 != 0) goto L1
                p2.c r0 = p2.c.this     // Catch: java.lang.Throwable -> L21
                p2.b r0 = p2.c.k(r0)     // Catch: java.lang.Throwable -> L21
                java.util.concurrent.RunnableFuture r1 = r2.f7024b     // Catch: java.lang.Throwable -> L21
                r0.execute(r1)     // Catch: java.lang.Throwable -> L21
            L1f:
                monitor-exit(r2)
                return
            L21:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p2.c.ExecutorC0104c.a():void");
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(Runnable runnable) {
            RunnableFuture runnableFutureNewTaskFor = c.this.f7015e.newTaskFor(new a(runnable), null);
            c.this.f7018h.put(runnable, runnableFutureNewTaskFor);
            this.f7023a.offer(runnableFutureNewTaskFor);
            if (this.f7024b == null) {
                a();
            }
        }

        public /* synthetic */ ExecutorC0104c(c cVar, a aVar) {
            this();
        }
    }
}
