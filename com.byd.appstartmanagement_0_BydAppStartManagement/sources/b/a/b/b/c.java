package b.a.b.b;

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
public class c implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static c f168b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Priority f167a = Priority.NORMAL;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Object f169c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f170d = new Handler(Looper.getMainLooper());
    public final Map<Runnable, Priority> g = new WeakHashMap();
    public final Map<Runnable, Future<?>> h = new WeakHashMap();
    public final b.a.b.b.b e = new b.a.b.b.b(new PriorityBlockingQueue(11, new a()));
    public final b f = new b(this, null);

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
            return priority == null ? c.f167a : priority;
        }
    }

    public static c f() {
        if (f168b == null) {
            synchronized (c.class) {
                if (f168b == null) {
                    f168b = new c();
                }
            }
        }
        return f168b;
    }

    @Override // b.a.b.b.d
    public void a(Runnable runnable, long j) {
        Objects.requireNonNull(runnable, "Runnable cannot be null");
        this.f170d.postDelayed(runnable, j);
    }

    /* JADX INFO: compiled from: DispatcherImpl.java */
    public class b implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ArrayDeque<RunnableFuture> f172a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RunnableFuture f173b;

        /* JADX INFO: compiled from: DispatcherImpl.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Runnable f175b;

            public a(Runnable runnable) {
                this.f175b = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.f175b.run();
                } finally {
                    b.this.a();
                }
            }
        }

        public b() {
            this.f172a = new ArrayDeque<>();
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0014, code lost:
        
            r2.f174c.e.execute(r2.f173b);
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
                java.util.ArrayDeque<java.util.concurrent.RunnableFuture> r0 = r2.f172a     // Catch: java.lang.Throwable -> L21
                java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> L21
                java.util.concurrent.RunnableFuture r0 = (java.util.concurrent.RunnableFuture) r0     // Catch: java.lang.Throwable -> L21
                r2.f173b = r0     // Catch: java.lang.Throwable -> L21
                if (r0 != 0) goto Le
                goto L1f
            Le:
                boolean r0 = r0.isCancelled()     // Catch: java.lang.Throwable -> L21
                if (r0 != 0) goto L1
                b.a.b.b.c r0 = b.a.b.b.c.this     // Catch: java.lang.Throwable -> L21
                b.a.b.b.b r0 = b.a.b.b.c.d(r0)     // Catch: java.lang.Throwable -> L21
                java.util.concurrent.RunnableFuture r1 = r2.f173b     // Catch: java.lang.Throwable -> L21
                r0.execute(r1)     // Catch: java.lang.Throwable -> L21
            L1f:
                monitor-exit(r2)
                return
            L21:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: b.a.b.b.c.b.a():void");
        }

        @Override // java.util.concurrent.Executor
        public synchronized void execute(@NonNull Runnable runnable) {
            RunnableFuture runnableFutureNewTaskFor = c.this.e.newTaskFor(new a(runnable), null);
            c.this.h.put(runnable, runnableFutureNewTaskFor);
            this.f172a.offer(runnableFutureNewTaskFor);
            if (this.f173b == null) {
                a();
            }
        }

        public /* synthetic */ b(c cVar, a aVar) {
            this();
        }
    }
}
