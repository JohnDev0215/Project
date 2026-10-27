package c.b.h.e;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, c.b.e.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f2289b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f2290c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f2291d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f2292e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f2293a;

    public g(Runnable runnable, c.b.h.a.a aVar) {
        super(3);
        this.f2293a = runnable;
        lazySet(0, aVar);
    }

    public void a(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == f2292e) {
                return;
            }
            if (obj == f2290c) {
                future.cancel(false);
                return;
            } else if (obj == f2291d) {
                future.cancel(true);
                return;
            }
        } while (!compareAndSet(1, obj, future));
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    @Override // c.b.e.b
    public void d() {
        Object obj;
        Object obj2;
        while (true) {
            Object obj3 = get(1);
            if (obj3 == f2292e || obj3 == f2290c || obj3 == f2291d) {
                break;
            }
            boolean z = get(2) != Thread.currentThread();
            if (compareAndSet(1, obj3, z ? f2291d : f2290c)) {
                if (obj3 != null) {
                    ((Future) obj3).cancel(z);
                }
            }
        }
        do {
            obj = get(0);
            if (obj == f2292e || obj == (obj2 = f2289b) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((c.b.h.a.a) obj).b(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        Object obj3;
        boolean zCompareAndSet;
        Object obj4;
        lazySet(2, Thread.currentThread());
        try {
            this.f2293a.run();
        } finally {
            try {
            } catch (Throwable th) {
                do {
                    if (obj == obj2) {
                        break;
                    } else if (obj == obj3) {
                        break;
                    }
                } while (!zCompareAndSet);
            }
        }
        lazySet(2, null);
        Object obj5 = get(0);
        if (obj5 != f2289b && compareAndSet(0, obj5, f2292e) && obj5 != null) {
            ((c.b.h.a.a) obj5).b(this);
        }
        do {
            obj4 = get(1);
            if (obj4 == f2290c || obj4 == f2291d) {
                return;
            }
        } while (!compareAndSet(1, obj4, f2292e));
    }
}
