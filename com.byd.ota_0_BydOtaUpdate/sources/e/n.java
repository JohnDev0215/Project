package e;

import e.x;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public Runnable f3617c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public ExecutorService f3618d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3615a = 64;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3616b = 5;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<x.a> f3619e = new ArrayDeque();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Deque<x.a> f3620f = new ArrayDeque();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Deque<x> f3621g = new ArrayDeque();

    public synchronized void a(x.a aVar) {
        if (this.f3620f.size() >= this.f3615a || i(aVar) >= this.f3616b) {
            this.f3619e.add(aVar);
        } else {
            this.f3620f.add(aVar);
            c().execute(aVar);
        }
    }

    public synchronized void b(x xVar) {
        this.f3621g.add(xVar);
    }

    public synchronized ExecutorService c() {
        if (this.f3618d == null) {
            this.f3618d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), e.d0.c.x("OkHttp Dispatcher", false));
        }
        return this.f3618d;
    }

    public final <T> void d(Deque<T> deque, T t, boolean z) {
        int iH;
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            if (z) {
                g();
            }
            iH = h();
            runnable = this.f3617c;
        }
        if (iH != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }

    public void e(x.a aVar) {
        d(this.f3620f, aVar, true);
    }

    public void f(x xVar) {
        d(this.f3621g, xVar, false);
    }

    public final void g() {
        if (this.f3620f.size() < this.f3615a && !this.f3619e.isEmpty()) {
            Iterator<x.a> it = this.f3619e.iterator();
            while (it.hasNext()) {
                x.a next = it.next();
                if (i(next) < this.f3616b) {
                    it.remove();
                    this.f3620f.add(next);
                    c().execute(next);
                }
                if (this.f3620f.size() >= this.f3615a) {
                    return;
                }
            }
        }
    }

    public synchronized int h() {
        return this.f3620f.size() + this.f3621g.size();
    }

    public final int i(x.a aVar) {
        Iterator<x.a> it = this.f3620f.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().l().equals(aVar.l())) {
                i2++;
            }
        }
        return i2;
    }
}
