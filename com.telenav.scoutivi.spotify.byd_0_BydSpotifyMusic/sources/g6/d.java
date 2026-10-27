package g6;

import g5.r;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: TaskQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f6571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a f6572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<a> f6573c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f6574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e f6575e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f6576f;

    public d(e taskRunner, String name) {
        l.e(taskRunner, "taskRunner");
        l.e(name, "name");
        this.f6575e = taskRunner;
        this.f6576f = name;
        this.f6573c = new ArrayList();
    }

    public static /* synthetic */ void j(d dVar, a aVar, long j7, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            j7 = 0;
        }
        dVar.i(aVar, j7);
    }

    public final void a() {
        if (!d6.b.f5967h || !Thread.holdsLock(this)) {
            synchronized (this.f6575e) {
                if (b()) {
                    this.f6575e.h(this);
                }
                r rVar = r.f6563a;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        l.d(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append(" MUST NOT hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public final boolean b() {
        a aVar = this.f6572b;
        if (aVar != null) {
            l.c(aVar);
            if (aVar.a()) {
                this.f6574d = true;
            }
        }
        boolean z6 = false;
        for (int size = this.f6573c.size() - 1; size >= 0; size--) {
            if (this.f6573c.get(size).a()) {
                a aVar2 = this.f6573c.get(size);
                if (e.f6579j.a().isLoggable(Level.FINE)) {
                    b.c(aVar2, this, "canceled");
                }
                this.f6573c.remove(size);
                z6 = true;
            }
        }
        return z6;
    }

    public final a c() {
        return this.f6572b;
    }

    public final boolean d() {
        return this.f6574d;
    }

    public final List<a> e() {
        return this.f6573c;
    }

    public final String f() {
        return this.f6576f;
    }

    public final boolean g() {
        return this.f6571a;
    }

    public final e h() {
        return this.f6575e;
    }

    public final void i(a task, long j7) {
        l.e(task, "task");
        synchronized (this.f6575e) {
            if (!this.f6571a) {
                if (k(task, j7, false)) {
                    this.f6575e.h(this);
                }
                r rVar = r.f6563a;
            } else if (task.a()) {
                if (e.f6579j.a().isLoggable(Level.FINE)) {
                    b.c(task, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                if (e.f6579j.a().isLoggable(Level.FINE)) {
                    b.c(task, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    public final boolean k(a task, long j7, boolean z6) {
        String str;
        l.e(task, "task");
        task.e(this);
        long jA = this.f6575e.g().a();
        long j8 = jA + j7;
        int iIndexOf = this.f6573c.indexOf(task);
        if (iIndexOf != -1) {
            if (task.c() <= j8) {
                if (e.f6579j.a().isLoggable(Level.FINE)) {
                    b.c(task, this, "already scheduled");
                }
                return false;
            }
            this.f6573c.remove(iIndexOf);
        }
        task.g(j8);
        if (e.f6579j.a().isLoggable(Level.FINE)) {
            if (z6) {
                str = "run again after " + b.b(j8 - jA);
            } else {
                str = "scheduled after " + b.b(j8 - jA);
            }
            b.c(task, this, str);
        }
        Iterator<a> it = this.f6573c.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (it.next().c() - jA > j7) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = this.f6573c.size();
        }
        this.f6573c.add(size, task);
        return size == 0;
    }

    public final void l(a aVar) {
        this.f6572b = aVar;
    }

    public final void m(boolean z6) {
        this.f6574d = z6;
    }

    public final void n() {
        if (!d6.b.f5967h || !Thread.holdsLock(this)) {
            synchronized (this.f6575e) {
                this.f6571a = true;
                if (b()) {
                    this.f6575e.h(this);
                }
                r rVar = r.f6563a;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread threadCurrentThread = Thread.currentThread();
        l.d(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append(" MUST NOT hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }

    public String toString() {
        return this.f6576f;
    }
}
