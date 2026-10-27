package c.a.m.g;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class h extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, c.a.j.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f1882b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f1883c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Runnable f1884d;

    public h(Runnable runnable, c.a.m.a.a aVar) {
        super(3);
        this.f1884d = runnable;
        lazySet(0, aVar);
    }

    public void a(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == f1883c) {
                return;
            }
            if (obj == f1882b) {
                future.cancel(get(2) != Thread.currentThread());
                return;
            }
        } while (!compareAndSet(1, obj, future));
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    @Override // c.a.j.b
    public void e() {
        Object obj;
        Object obj2;
        Object obj3;
        while (true) {
            Object obj4 = get(1);
            if (obj4 == f1883c || obj4 == (obj3 = f1882b)) {
                break;
            } else if (compareAndSet(1, obj4, obj3)) {
                if (obj4 != null) {
                    ((Future) obj4).cancel(get(2) != Thread.currentThread());
                }
            }
        }
        do {
            obj = get(0);
            if (obj == f1883c || obj == (obj2 = f1882b) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((c.a.m.a.a) obj).a(this);
    }

    @Override // c.a.j.b
    public boolean g() {
        Object obj = get(1);
        return obj == f1882b || obj == f1883c;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        boolean zCompareAndSet;
        Object obj3;
        lazySet(2, Thread.currentThread());
        try {
            this.f1884d.run();
        } finally {
            try {
            } catch (Throwable th) {
                do {
                    if (obj == obj2) {
                        break;
                    }
                } while (!zCompareAndSet);
            }
        }
        lazySet(2, null);
        Object obj4 = get(0);
        if (obj4 != f1882b && obj4 != null && compareAndSet(0, obj4, f1883c)) {
            ((c.a.m.a.a) obj4).a(this);
        }
        do {
            obj3 = get(1);
            if (obj3 == f1882b) {
                return;
            }
        } while (!compareAndSet(1, obj3, f1883c));
    }
}
