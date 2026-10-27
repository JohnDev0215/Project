package e;

import e.d0.f.f;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Executor f3585a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), e.d0.c.x("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3586b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f3587c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Runnable f3588d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<e.d0.f.c> f3589e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e.d0.f.d f3590f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3591g;

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
            e.d0.f.c cVar = null;
            long j2 = Long.MIN_VALUE;
            int i2 = 0;
            int i3 = 0;
            for (e.d0.f.c cVar2 : this.f3589e) {
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
            long j4 = this.f3587c;
            if (j2 < j4 && i2 <= this.f3586b) {
                if (i2 > 0) {
                    return j4 - j2;
                }
                if (i3 > 0) {
                    return j4;
                }
                this.f3591g = false;
                return -1L;
            }
            this.f3589e.remove(cVar);
            e.d0.c.d(cVar.r());
            return 0L;
        }
    }

    public boolean b(e.d0.f.c cVar) {
        if (cVar.k || this.f3586b == 0) {
            this.f3589e.remove(cVar);
            return true;
        }
        notifyAll();
        return false;
    }

    @Nullable
    public Socket c(e.a aVar, e.d0.f.f fVar) {
        for (e.d0.f.c cVar : this.f3589e) {
            if (cVar.n(aVar, null) && cVar.p() && cVar != fVar.d()) {
                return fVar.m(cVar);
            }
        }
        return null;
    }

    @Nullable
    public e.d0.f.c d(e.a aVar, e.d0.f.f fVar, c0 c0Var) {
        for (e.d0.f.c cVar : this.f3589e) {
            if (cVar.n(aVar, c0Var)) {
                fVar.a(cVar);
                return cVar;
            }
        }
        return null;
    }

    public final int e(e.d0.f.c cVar, long j) {
        List<Reference<e.d0.f.f>> list = cVar.n;
        int i2 = 0;
        while (i2 < list.size()) {
            Reference<e.d0.f.f> reference = list.get(i2);
            if (reference.get() != null) {
                i2++;
            } else {
                e.d0.j.e.h().m("A connection to " + cVar.b().a().l() + " was leaked. Did you forget to close a response body?", ((f.a) reference).f3393a);
                list.remove(i2);
                cVar.k = true;
                if (list.isEmpty()) {
                    cVar.o = j - this.f3587c;
                    return 0;
                }
            }
        }
        return list.size();
    }

    public void f(e.d0.f.c cVar) {
        if (!this.f3591g) {
            this.f3591g = true;
            f3585a.execute(this.f3588d);
        }
        this.f3589e.add(cVar);
    }

    public j(int i2, long j, TimeUnit timeUnit) {
        this.f3588d = new a();
        this.f3589e = new ArrayDeque();
        this.f3590f = new e.d0.f.d();
        this.f3586b = i2;
        this.f3587c = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }
}
