package g6;

import g5.r;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: TaskRunner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final Logger f6578i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f6580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f6581b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f6582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<g6.d> f6583d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<g6.d> f6584e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Runnable f6585f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final a f6586g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final b f6579j = new b(null);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f6577h = new e(new c(d6.b.H(d6.b.f5968i + " TaskRunner", true)));

    /* JADX INFO: compiled from: TaskRunner.kt */
    public interface a {
        long a();

        void b(e eVar);

        void c(e eVar, long j7);

        void execute(Runnable runnable);
    }

    /* JADX INFO: compiled from: TaskRunner.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(g gVar) {
            this();
        }

        public final Logger a() {
            return e.f6578i;
        }
    }

    /* JADX INFO: compiled from: TaskRunner.kt */
    public static final class c implements a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ThreadPoolExecutor f6587a;

        public c(ThreadFactory threadFactory) {
            l.e(threadFactory, "threadFactory");
            this.f6587a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
        }

        @Override // g6.e.a
        public long a() {
            return System.nanoTime();
        }

        @Override // g6.e.a
        public void b(e taskRunner) {
            l.e(taskRunner, "taskRunner");
            taskRunner.notify();
        }

        @Override // g6.e.a
        public void c(e taskRunner, long j7) throws InterruptedException {
            l.e(taskRunner, "taskRunner");
            long j8 = j7 / 1000000;
            long j9 = j7 - (1000000 * j8);
            if (j8 > 0 || j7 > 0) {
                taskRunner.wait(j8, (int) j9);
            }
        }

        @Override // g6.e.a
        public void execute(Runnable runnable) {
            l.e(runnable, "runnable");
            this.f6587a.execute(runnable);
        }
    }

    /* JADX INFO: compiled from: TaskRunner.kt */
    public static final class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g6.a aVarD;
            while (true) {
                synchronized (e.this) {
                    aVarD = e.this.d();
                }
                if (aVarD == null) {
                    return;
                }
                g6.d dVarD = aVarD.d();
                l.c(dVarD);
                long jA = -1;
                boolean zIsLoggable = e.f6579j.a().isLoggable(Level.FINE);
                if (zIsLoggable) {
                    jA = dVarD.h().g().a();
                    g6.b.c(aVarD, dVarD, "starting");
                }
                try {
                    try {
                        e.this.j(aVarD);
                        r rVar = r.f6563a;
                        if (zIsLoggable) {
                            g6.b.c(aVarD, dVarD, "finished run in " + g6.b.b(dVarD.h().g().a() - jA));
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    if (zIsLoggable) {
                        g6.b.c(aVarD, dVarD, "failed a run in " + g6.b.b(dVarD.h().g().a() - jA));
                    }
                    throw th;
                }
            }
        }
    }

    static {
        Logger logger = Logger.getLogger(e.class.getName());
        l.d(logger, "Logger.getLogger(TaskRunner::class.java.name)");
        f6578i = logger;
    }

    public e(a backend) {
        l.e(backend, "backend");
        this.f6586g = backend;
        this.f6580a = 10000;
        this.f6583d = new ArrayList();
        this.f6584e = new ArrayList();
        this.f6585f = new d();
    }

    private final void c(g6.a aVar, long j7) {
        if (d6.b.f5967h && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        g6.d dVarD = aVar.d();
        l.c(dVarD);
        if (!(dVarD.c() == aVar)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean zD = dVarD.d();
        dVarD.m(false);
        dVarD.l(null);
        this.f6583d.remove(dVarD);
        if (j7 != -1 && !zD && !dVarD.g()) {
            dVarD.k(aVar, j7, true);
        }
        if (!dVarD.e().isEmpty()) {
            this.f6584e.add(dVarD);
        }
    }

    private final void e(g6.a aVar) {
        if (!d6.b.f5967h || Thread.holdsLock(this)) {
            aVar.g(-1L);
            g6.d dVarD = aVar.d();
            l.c(dVarD);
            dVarD.e().remove(aVar);
            this.f6584e.remove(dVarD);
            dVarD.l(aVar);
            this.f6583d.add(dVarD);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        l.d(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void j(g6.a aVar) {
        if (d6.b.f5967h && Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST NOT hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        Thread currentThread = Thread.currentThread();
        l.d(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(aVar.b());
        try {
            long jF = aVar.f();
            synchronized (this) {
                c(aVar, jF);
                r rVar = r.f6563a;
            }
            currentThread.setName(name);
        } catch (Throwable th) {
            synchronized (this) {
                c(aVar, -1L);
                r rVar2 = r.f6563a;
                currentThread.setName(name);
                throw th;
            }
        }
    }

    public final g6.a d() {
        boolean z6;
        if (d6.b.f5967h && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        while (!this.f6584e.isEmpty()) {
            long jA = this.f6586g.a();
            long jMin = Long.MAX_VALUE;
            Iterator<g6.d> it = this.f6584e.iterator();
            g6.a aVar = null;
            while (true) {
                if (!it.hasNext()) {
                    z6 = false;
                    break;
                }
                g6.a aVar2 = it.next().e().get(0);
                long jMax = Math.max(0L, aVar2.c() - jA);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (aVar != null) {
                        z6 = true;
                        break;
                    }
                    aVar = aVar2;
                }
            }
            if (aVar != null) {
                e(aVar);
                if (z6 || (!this.f6581b && (!this.f6584e.isEmpty()))) {
                    this.f6586g.execute(this.f6585f);
                }
                return aVar;
            }
            if (this.f6581b) {
                if (jMin < this.f6582c - jA) {
                    this.f6586g.b(this);
                }
                return null;
            }
            this.f6581b = true;
            this.f6582c = jA + jMin;
            try {
                try {
                    this.f6586g.c(this, jMin);
                } catch (InterruptedException unused) {
                    f();
                }
            } finally {
                this.f6581b = false;
            }
        }
        return null;
    }

    public final void f() {
        for (int size = this.f6583d.size() - 1; size >= 0; size--) {
            this.f6583d.get(size).b();
        }
        for (int size2 = this.f6584e.size() - 1; size2 >= 0; size2--) {
            g6.d dVar = this.f6584e.get(size2);
            dVar.b();
            if (dVar.e().isEmpty()) {
                this.f6584e.remove(size2);
            }
        }
    }

    public final a g() {
        return this.f6586g;
    }

    public final void h(g6.d taskQueue) {
        l.e(taskQueue, "taskQueue");
        if (d6.b.f5967h && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread threadCurrentThread = Thread.currentThread();
            l.d(threadCurrentThread, "Thread.currentThread()");
            sb.append(threadCurrentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        if (taskQueue.c() == null) {
            if (!taskQueue.e().isEmpty()) {
                d6.b.a(this.f6584e, taskQueue);
            } else {
                this.f6584e.remove(taskQueue);
            }
        }
        if (this.f6581b) {
            this.f6586g.b(this);
        } else {
            this.f6586g.execute(this.f6585f);
        }
    }

    public final g6.d i() {
        int i7;
        synchronized (this) {
            i7 = this.f6580a;
            this.f6580a = i7 + 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append('Q');
        sb.append(i7);
        return new g6.d(this, sb.toString());
    }
}
