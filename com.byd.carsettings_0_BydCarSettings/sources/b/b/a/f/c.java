package b.b.a.f;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
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
public class c implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static c f504b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Priority f503a = Priority.NORMAL;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object f505c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f506d = new Handler(Looper.getMainLooper());
    public final Map<Runnable, Priority> g = new WeakHashMap();
    public final Map<Runnable, Future<?>> h = new WeakHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b.b.a.f.b f507e = new b.b.a.f.b(new PriorityBlockingQueue(11, new a()));

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ExecutorC0021c f508f = new ExecutorC0021c(this, null);

    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class a implements Comparator<Runnable> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Runnable runnable, Runnable runnable2) {
            return b(runnable).value() - b(runnable2).value();
        }

        @NonNull
        public final Priority b(Runnable runnable) {
            Priority priority = (Priority) c.this.g.get(runnable);
            return priority == null ? c.f503a : priority;
        }
    }

    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RunnableFuture f510a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Priority f511b;

        public b(RunnableFuture runnableFuture, Priority priority) {
            this.f510a = runnableFuture;
            this.f511b = priority;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.q(this.f510a, this.f511b);
        }
    }

    public static c p() {
        if (f504b == null) {
            synchronized (c.class) {
                if (f504b == null) {
                    f504b = new c();
                }
            }
        }
        return f504b;
    }

    @Override // b.b.a.f.e
    public void a(Runnable runnable, long j, Priority priority) {
        if (j <= 0) {
            q(runnable, priority);
            return;
        }
        RunnableFuture runnableFutureNewTaskFor = this.f507e.newTaskFor(runnable, null);
        this.h.put(runnable, runnableFutureNewTaskFor);
        this.f506d.postDelayed(new b(runnableFutureNewTaskFor, priority), j);
    }

    @Override // b.b.a.f.e
    public void b(Runnable runnable) {
        h(runnable, Priority.HIGH);
    }

    @Override // b.b.a.f.e
    public void c(Runnable runnable) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            d(runnable);
        }
    }

    @Override // b.b.a.f.e
    public void d(Runnable runnable) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        this.f506d.post(runnable);
    }

    @Override // b.b.a.f.e
    public void e(Runnable runnable, long j) {
        a(runnable, j, this.g.get(runnable));
    }

    @Override // b.b.a.f.e
    public void f(Runnable runnable) {
        Future<?> future = this.h.get(runnable);
        if (future != null) {
            future.cancel(false);
        }
    }

    @Override // b.b.a.f.e
    public void g(Runnable runnable, long j) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        this.f506d.postDelayed(runnable, j);
    }

    @Override // b.b.a.f.e
    public void h(Runnable runnable, Priority priority) {
        q(runnable, priority);
    }

    @Override // b.b.a.f.e
    public void i(Runnable runnable) {
        this.f506d.removeCallbacks(runnable);
    }

    @Override // b.b.a.f.e
    public void j(Runnable runnable) {
        h(runnable, this.g.get(runnable));
    }

    public final void q(Runnable runnable, Priority priority) {
        Future<?> futureSubmit;
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        if (priority == null) {
            priority = f503a;
        }
        this.g.put(runnable, priority);
        if (runnable instanceof Future) {
            this.f507e.execute(runnable);
            futureSubmit = (Future) runnable;
        } else {
            futureSubmit = this.f507e.submit(runnable);
        }
        this.h.put(runnable, futureSubmit);
    }

    /* JADX INFO: renamed from: b.b.a.f.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class ExecutorC0021c implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayDeque<RunnableFuture> f513a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RunnableFuture f514b;

        /* JADX INFO: renamed from: b.b.a.f.c$c$a */
        /* JADX INFO: compiled from: DispatcherImpl.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Runnable f516a;

            public a(Runnable runnable) {
                this.f516a = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.f516a.run();
                } finally {
                    ExecutorC0021c.this.a();
                }
            }
        }

        public ExecutorC0021c() {
            this.f513a = new ArrayDeque<>();
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        
            r2.f515c.f507e.execute(r2.f514b);
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
                java.util.ArrayDeque<java.util.concurrent.RunnableFuture> r0 = r2.f513a     // Catch: java.lang.Throwable -> L21
                java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> L21
                java.util.concurrent.RunnableFuture r0 = (java.util.concurrent.RunnableFuture) r0     // Catch: java.lang.Throwable -> L21
                r2.f514b = r0     // Catch: java.lang.Throwable -> L21
                if (r0 != 0) goto Le
                goto L1f
            Le:
                boolean r0 = r0.isCancelled()     // Catch: java.lang.Throwable -> L21
                if (r0 != 0) goto L1
                b.b.a.f.c r0 = b.b.a.f.c.this     // Catch: java.lang.Throwable -> L21
                b.b.a.f.b r0 = b.b.a.f.c.n(r0)     // Catch: java.lang.Throwable -> L21
                java.util.concurrent.RunnableFuture r1 = r2.f514b     // Catch: java.lang.Throwable -> L21
                r0.execute(r1)     // Catch: java.lang.Throwable -> L21
            L1f:
                monitor-exit(r2)
                return
            L21:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: b.b.a.f.c.ExecutorC0021c.a():void");
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(@NonNull Runnable runnable) {
            RunnableFuture runnableFutureNewTaskFor = c.this.f507e.newTaskFor(new a(runnable), null);
            c.this.h.put(runnable, runnableFutureNewTaskFor);
            this.f513a.offer(runnableFutureNewTaskFor);
            if (this.f514b == null) {
                a();
            }
        }

        public /* synthetic */ ExecutorC0021c(c cVar, a aVar) {
            this();
        }
    }
}
