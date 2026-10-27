package okhttp3.internal.concurrent;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import p.cs2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class TaskRunner {
    public static final Companion h = new Companion(0);
    public static final TaskRunner i;
    public static final Logger j;
    public final Backend a;
    public boolean c;
    public long d;
    public int b = WebgateAuthorizer.COSMOS_TIMEOUT_MS;
    public final ArrayList e = new ArrayList();
    public final ArrayList f = new ArrayList();
    public final TaskRunner$runnable$1 g = new Runnable() { // from class: okhttp3.internal.concurrent.TaskRunner$runnable$1
        @Override // java.lang.Runnable
        public final void run() {
            Task taskC;
            long jC;
            while (true) {
                TaskRunner taskRunner = this.c;
                synchronized (taskRunner) {
                    taskC = taskRunner.c();
                }
                if (taskC == null) {
                    return;
                }
                TaskQueue taskQueue = taskC.c;
                v03.h(taskQueue);
                TaskRunner taskRunner2 = this.c;
                TaskRunner.h.getClass();
                boolean zIsLoggable = TaskRunner.j.isLoggable(Level.FINE);
                if (zIsLoggable) {
                    jC = taskQueue.a.a.c();
                    TaskLoggerKt.a(taskC, taskQueue, "starting");
                } else {
                    jC = -1;
                }
                try {
                    TaskRunner.a(taskRunner2, taskC);
                    if (zIsLoggable) {
                        TaskLoggerKt.a(taskC, taskQueue, "finished run in ".concat(TaskLoggerKt.b(taskQueue.a.a.c() - jC)));
                    }
                } finally {
                }
            }
        }
    };

    public interface Backend {
        void a(TaskRunner taskRunner);

        void b(TaskRunner taskRunner, long j);

        long c();

        void execute(Runnable runnable);
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public static final class RealBackend implements Backend {
        public final ThreadPoolExecutor a;

        public RealBackend(cs2 cs2Var) {
            this.a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), cs2Var);
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public final void a(TaskRunner taskRunner) {
            v03.j(taskRunner, "taskRunner");
            taskRunner.notify();
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public final void b(TaskRunner taskRunner, long j) throws InterruptedException {
            v03.j(taskRunner, "taskRunner");
            long j2 = j / 1000000;
            long j3 = j - (1000000 * j2);
            if (j2 > 0 || j > 0) {
                taskRunner.wait(j2, (int) j3);
            }
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public final long c() {
            return System.nanoTime();
        }

        @Override // okhttp3.internal.concurrent.TaskRunner.Backend
        public final void execute(Runnable runnable) {
            v03.j(runnable, "runnable");
            this.a.execute(runnable);
        }
    }

    static {
        String str = Util.g + " TaskRunner";
        v03.j(str, "name");
        i = new TaskRunner(new RealBackend(new cs2(str, true)));
        Logger logger = Logger.getLogger(TaskRunner.class.getName());
        v03.i(logger, "getLogger(TaskRunner::class.java.name)");
        j = logger;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [okhttp3.internal.concurrent.TaskRunner$runnable$1] */
    public TaskRunner(RealBackend realBackend) {
        this.a = realBackend;
    }

    public static final void a(TaskRunner taskRunner, Task task) {
        taskRunner.getClass();
        byte[] bArr = Util.a;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(task.a);
        try {
            long jA = task.a();
            synchronized (taskRunner) {
                taskRunner.b(task, jA);
            }
            threadCurrentThread.setName(name);
        } catch (Throwable th) {
            synchronized (taskRunner) {
                taskRunner.b(task, -1L);
                threadCurrentThread.setName(name);
                throw th;
            }
        }
    }

    public final void b(Task task, long j2) {
        byte[] bArr = Util.a;
        TaskQueue taskQueue = task.c;
        v03.h(taskQueue);
        if (taskQueue.d != task) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean z = taskQueue.f;
        taskQueue.f = false;
        taskQueue.d = null;
        this.e.remove(taskQueue);
        if (j2 != -1 && !z && !taskQueue.c) {
            taskQueue.e(task, j2, true);
        }
        if (!taskQueue.e.isEmpty()) {
            this.f.add(taskQueue);
        }
    }

    public final Task c() {
        long j2;
        boolean z;
        byte[] bArr = Util.a;
        while (true) {
            ArrayList arrayList = this.f;
            if (arrayList.isEmpty()) {
                return null;
            }
            Backend backend = this.a;
            long jC = backend.c();
            Iterator it = arrayList.iterator();
            long jMin = Long.MAX_VALUE;
            Task task = null;
            while (true) {
                if (!it.hasNext()) {
                    j2 = jC;
                    z = false;
                    break;
                }
                Task task2 = (Task) ((TaskQueue) it.next()).e.get(0);
                j2 = jC;
                long jMax = Math.max(0L, task2.d - jC);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (task != null) {
                        z = true;
                        break;
                    }
                    task = task2;
                }
                jC = j2;
            }
            if (task != null) {
                byte[] bArr2 = Util.a;
                task.d = -1L;
                TaskQueue taskQueue = task.c;
                v03.h(taskQueue);
                taskQueue.e.remove(task);
                arrayList.remove(taskQueue);
                taskQueue.d = task;
                this.e.add(taskQueue);
                if (z || (!this.c && (!arrayList.isEmpty()))) {
                    backend.execute(this.g);
                }
                return task;
            }
            if (this.c) {
                if (jMin >= this.d - j2) {
                    return null;
                }
                backend.a(this);
                return null;
            }
            this.c = true;
            this.d = j2 + jMin;
            try {
                try {
                    backend.b(this, jMin);
                } catch (InterruptedException unused) {
                    d();
                }
            } finally {
                this.c = false;
            }
        }
    }

    public final void d() {
        ArrayList arrayList = this.e;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                break;
            } else {
                ((TaskQueue) arrayList.get(size)).b();
            }
        }
        ArrayList arrayList2 = this.f;
        for (int size2 = arrayList2.size() - 1; -1 < size2; size2--) {
            TaskQueue taskQueue = (TaskQueue) arrayList2.get(size2);
            taskQueue.b();
            if (taskQueue.e.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
    }

    public final void e(TaskQueue taskQueue) {
        v03.j(taskQueue, "taskQueue");
        byte[] bArr = Util.a;
        if (taskQueue.d == null) {
            boolean z = !taskQueue.e.isEmpty();
            ArrayList arrayList = this.f;
            if (z) {
                v03.j(arrayList, "<this>");
                if (!arrayList.contains(taskQueue)) {
                    arrayList.add(taskQueue);
                }
            } else {
                arrayList.remove(taskQueue);
            }
        }
        boolean z2 = this.c;
        Backend backend = this.a;
        if (z2) {
            backend.a(this);
        } else {
            backend.execute(this.g);
        }
    }

    public final TaskQueue f() {
        int i2;
        synchronized (this) {
            i2 = this.b;
            this.b = i2 + 1;
        }
        return new TaskQueue(this, u4.e("Q", i2));
    }
}
