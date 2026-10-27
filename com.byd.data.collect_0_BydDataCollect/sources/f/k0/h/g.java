package f.k0.h;

import f.i0;
import f.k0.h.k;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Proxy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: RealConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Executor f5198a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), f.k0.e.H("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f5199b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f5200c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Runnable f5201d = new Runnable() { // from class: f.k0.h.a
        @Override // java.lang.Runnable
        public final void run() {
            this.f5159b.e();
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Deque<f> f5202e = new ArrayDeque();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final h f5203f = new h();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    boolean f5204g;

    public g(int i2, long j, TimeUnit timeUnit) {
        this.f5199b = i2;
        this.f5200c = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void e() {
        while (true) {
            long jA = a(System.nanoTime());
            if (jA == -1) {
                return;
            }
            if (jA > 0) {
                long j = jA / 1000000;
                long j2 = jA - (1000000 * j);
                synchronized (this) {
                    try {
                        wait(j, (int) j2);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    private int f(f fVar, long j) {
        List<Reference<k>> list = fVar.p;
        int i2 = 0;
        while (i2 < list.size()) {
            Reference<k> reference = list.get(i2);
            if (reference.get() != null) {
                i2++;
            } else {
                f.k0.l.f.j().q("A connection to " + fVar.r().a().l() + " was leaked. Did you forget to close a response body?", ((k.b) reference).f5225a);
                list.remove(i2);
                fVar.k = true;
                if (list.isEmpty()) {
                    fVar.q = j - this.f5200c;
                    return 0;
                }
            }
        }
        return list.size();
    }

    long a(long j) {
        synchronized (this) {
            f fVar = null;
            long j2 = Long.MIN_VALUE;
            int i2 = 0;
            int i3 = 0;
            for (f fVar2 : this.f5202e) {
                if (f(fVar2, j) > 0) {
                    i3++;
                } else {
                    i2++;
                    long j3 = j - fVar2.q;
                    if (j3 > j2) {
                        fVar = fVar2;
                        j2 = j3;
                    }
                }
            }
            long j4 = this.f5200c;
            if (j2 < j4 && i2 <= this.f5199b) {
                if (i2 > 0) {
                    return j4 - j2;
                }
                if (i3 > 0) {
                    return j4;
                }
                this.f5204g = false;
                return -1L;
            }
            this.f5202e.remove(fVar);
            f.k0.e.g(fVar.t());
            return 0L;
        }
    }

    public void b(i0 i0Var, IOException iOException) {
        if (i0Var.b().type() != Proxy.Type.DIRECT) {
            f.e eVarA = i0Var.a();
            eVarA.i().connectFailed(eVarA.l().E(), i0Var.b().address(), iOException);
        }
        this.f5203f.b(i0Var);
    }

    boolean c(f fVar) {
        if (fVar.k || this.f5199b == 0) {
            this.f5202e.remove(fVar);
            return true;
        }
        notifyAll();
        return false;
    }

    void g(f fVar) {
        if (!this.f5204g) {
            this.f5204g = true;
            f5198a.execute(this.f5201d);
        }
        this.f5202e.add(fVar);
    }

    boolean h(f.e eVar, k kVar, @Nullable List<i0> list, boolean z) {
        for (f fVar : this.f5202e) {
            if (!z || fVar.o()) {
                if (fVar.m(eVar, list)) {
                    kVar.a(fVar);
                    return true;
                }
            }
        }
        return false;
    }
}
