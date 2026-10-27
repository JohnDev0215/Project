package e.a.n.g;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: ScheduledRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, e.a.k.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Object f4943b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f4944c = new Object();
    private static final long serialVersionUID = -6120223772001106981L;
    final Runnable actual;

    public h(Runnable runnable, e.a.n.a.a aVar) {
        super(3);
        this.actual = runnable;
        lazySet(0, aVar);
    }

    public void a(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == f4944c) {
                return;
            }
            if (obj == f4943b) {
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

    @Override // e.a.k.b
    public void d() {
        Object obj;
        Object obj2;
        Object obj3;
        while (true) {
            Object obj4 = get(1);
            if (obj4 == f4944c || obj4 == (obj3 = f4943b)) {
                break;
            } else if (compareAndSet(1, obj4, obj3)) {
                if (obj4 != null) {
                    ((Future) obj4).cancel(get(2) != Thread.currentThread());
                }
            }
        }
        do {
            obj = get(0);
            if (obj == f4944c || obj == (obj2 = f4943b) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((e.a.n.a.a) obj).a(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        boolean zCompareAndSet;
        Object obj3;
        lazySet(2, Thread.currentThread());
        try {
            this.actual.run();
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
        if (obj4 != f4943b && obj4 != null && compareAndSet(0, obj4, f4944c)) {
            ((e.a.n.a.a) obj4).a(this);
        }
        do {
            obj3 = get(1);
            if (obj3 == f4943b) {
                return;
            }
        } while (!compareAndSet(1, obj3, f4944c));
    }
}
