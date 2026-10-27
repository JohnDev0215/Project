package f;

import f.b0.f.f;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Executor f5331a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), f.b0.c.x("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5332b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5333c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Runnable f5334d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<f.b0.f.c> f5335e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f.b0.f.d f5336f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5337g;

    /* JADX INFO: compiled from: ConnectionPool.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                long jA = j.this.a(System.nanoTime());
                if (jA == -1) {
                    return;
                }
                if (jA > 0) {
                    long j = jA / 1000000;
                    long j2 = jA - (1000000 * j);
                    synchronized (j.this) {
                        try {
                            j.this.wait(j, (int) j2);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            }
        }
    }

    public j() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    public long a(long j) {
        synchronized (this) {
            f.b0.f.c cVar = null;
            long j2 = Long.MIN_VALUE;
            int i2 = 0;
            int i3 = 0;
            for (f.b0.f.c cVar2 : this.f5335e) {
                if (e(cVar2, j) > 0) {
                    i3++;
                } else {
                    i2++;
                    long j3 = j - cVar2.o;
                    if (j3 > j2) {
                        cVar = cVar2;
                        j2 = j3;
                    }
                }
            }
            long j4 = this.f5333c;
            if (j2 < j4 && i2 <= this.f5332b) {
                if (i2 > 0) {
                    return j4 - j2;
                }
                if (i3 > 0) {
                    return j4;
                }
                this.f5337g = false;
                return -1L;
            }
            this.f5335e.remove(cVar);
            f.b0.c.d(cVar.q());
            return 0L;
        }
    }

    public boolean b(f.b0.f.c cVar) {
        if (cVar.k || this.f5332b == 0) {
            this.f5335e.remove(cVar);
            return true;
        }
        notifyAll();
        return false;
    }

    public Socket c(f.a aVar, f.b0.f.f fVar) {
        for (f.b0.f.c cVar : this.f5335e) {
            if (cVar.m(aVar) && cVar.o() && cVar != fVar.d()) {
                return fVar.m(cVar);
            }
        }
        return null;
    }

    public f.b0.f.c d(f.a aVar, f.b0.f.f fVar) {
        for (f.b0.f.c cVar : this.f5335e) {
            if (cVar.m(aVar)) {
                fVar.a(cVar);
                return cVar;
            }
        }
        return null;
    }

    public final int e(f.b0.f.c cVar, long j) {
        List<Reference<f.b0.f.f>> list = cVar.n;
        int i2 = 0;
        while (i2 < list.size()) {
            Reference<f.b0.f.f> reference = list.get(i2);
            if (reference.get() != null) {
                i2++;
            } else {
                f.b0.j.e.h().m("A connection to " + cVar.a().a().k() + " was leaked. Did you forget to close a response body?", ((f.a) reference).f5072a);
                list.remove(i2);
                cVar.k = true;
                if (list.isEmpty()) {
                    cVar.o = j - this.f5333c;
                    return 0;
                }
            }
        }
        return list.size();
    }

    public void f(f.b0.f.c cVar) {
        if (!this.f5337g) {
            this.f5337g = true;
            f5331a.execute(this.f5334d);
        }
        this.f5335e.add(cVar);
    }

    public j(int i2, long j, TimeUnit timeUnit) {
        this.f5334d = new a();
        this.f5335e = new ArrayDeque();
        this.f5336f = new f.b0.f.d();
        this.f5332b = i2;
        this.f5333c = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }
}
