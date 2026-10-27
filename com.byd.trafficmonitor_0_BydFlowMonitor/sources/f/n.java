package f;

import f.v;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f5363c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ExecutorService f5364d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5361a = 64;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5362b = 5;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Deque<v.a> f5365e = new ArrayDeque();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Deque<v.a> f5366f = new ArrayDeque();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Deque<v> f5367g = new ArrayDeque();

    public synchronized void a(v.a aVar) {
        if (this.f5366f.size() >= this.f5361a || i(aVar) >= this.f5362b) {
            this.f5365e.add(aVar);
        } else {
            this.f5366f.add(aVar);
            c().execute(aVar);
        }
    }

    public synchronized void b(v vVar) {
        this.f5367g.add(vVar);
    }

    public synchronized ExecutorService c() {
        if (this.f5364d == null) {
            this.f5364d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), f.b0.c.x("OkHttp Dispatcher", false));
        }
        return this.f5364d;
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
            runnable = this.f5363c;
        }
        if (iH != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }

    public void e(v.a aVar) {
        d(this.f5366f, aVar, true);
    }

    public void f(v vVar) {
        d(this.f5367g, vVar, false);
    }

    public final void g() {
        if (this.f5366f.size() < this.f5361a && !this.f5365e.isEmpty()) {
            Iterator<v.a> it = this.f5365e.iterator();
            while (it.hasNext()) {
                v.a next = it.next();
                if (i(next) < this.f5362b) {
                    it.remove();
                    this.f5366f.add(next);
                    c().execute(next);
                }
                if (this.f5366f.size() >= this.f5361a) {
                    return;
                }
            }
        }
    }

    public synchronized int h() {
        return this.f5366f.size() + this.f5367g.size();
    }

    public final int i(v.a aVar) {
        Iterator<v.a> it = this.f5366f.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next().l().equals(aVar.l())) {
                i2++;
            }
        }
        return i2;
    }

    public synchronized void j(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("max < 1: " + i2);
        }
        this.f5362b = i2;
        g();
    }
}
