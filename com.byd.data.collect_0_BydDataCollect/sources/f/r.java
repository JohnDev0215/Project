package f;

import f.d0;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private Runnable f5495c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    private ExecutorService f5496d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f5493a = 64;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5494b = 5;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Deque<d0.a> f5497e = new ArrayDeque();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Deque<d0.a> f5498f = new ArrayDeque();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Deque<d0> f5499g = new ArrayDeque();

    @Nullable
    private d0.a d(String str) {
        for (d0.a aVar : this.f5498f) {
            if (aVar.o().equals(str)) {
                return aVar;
            }
        }
        for (d0.a aVar2 : this.f5497e) {
            if (aVar2.o().equals(str)) {
                return aVar2;
            }
        }
        return null;
    }

    private <T> void e(Deque<T> deque, T t) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.f5495c;
        }
        if (h() || runnable == null) {
            return;
        }
        runnable.run();
    }

    private boolean h() {
        int i2;
        boolean z;
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<d0.a> it = this.f5497e.iterator();
            while (it.hasNext()) {
                d0.a next = it.next();
                if (this.f5498f.size() >= this.f5493a) {
                    break;
                }
                if (next.l().get() < this.f5494b) {
                    it.remove();
                    next.l().incrementAndGet();
                    arrayList.add(next);
                    this.f5498f.add(next);
                }
            }
            z = i() > 0;
        }
        int size = arrayList.size();
        for (i2 = 0; i2 < size; i2++) {
            ((d0.a) arrayList.get(i2)).m(c());
        }
        return z;
    }

    void a(d0.a aVar) {
        d0.a aVarD;
        synchronized (this) {
            this.f5497e.add(aVar);
            if (!aVar.n().f5040e && (aVarD = d(aVar.o())) != null) {
                aVar.p(aVarD);
            }
        }
        h();
    }

    synchronized void b(d0 d0Var) {
        this.f5499g.add(d0Var);
    }

    public synchronized ExecutorService c() {
        if (this.f5496d == null) {
            this.f5496d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), f.k0.e.H("OkHttp Dispatcher", false));
        }
        return this.f5496d;
    }

    void f(d0.a aVar) {
        aVar.l().decrementAndGet();
        e(this.f5498f, aVar);
    }

    void g(d0 d0Var) {
        e(this.f5499g, d0Var);
    }

    public synchronized int i() {
        return this.f5498f.size() + this.f5499g.size();
    }
}
