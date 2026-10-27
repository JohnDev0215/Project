package io.reactivex.internal.schedulers;

import d.a.m.b;
import d.a.p.a.a;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: loaded from: classes.dex */
public final class ScheduledRunnable extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, b {
    public static final Object DISPOSED = new Object();
    public static final Object DONE = new Object();
    public static final int FUTURE_INDEX = 1;
    public static final int PARENT_INDEX = 0;
    private static final long serialVersionUID = -6120223772001106981L;
    public final Runnable actual;

    public ScheduledRunnable(Runnable runnable, a aVar) {
        super(2);
        this.actual = runnable;
        lazySet(0, aVar);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    @Override // d.a.m.b
    public void dispose() {
        Object obj;
        Object obj2;
        Object obj3;
        while (true) {
            Object obj4 = get(1);
            if (obj4 == DONE || obj4 == (obj3 = DISPOSED)) {
                break;
            } else if (compareAndSet(1, obj4, obj3)) {
                if (obj4 != null) {
                    ((Future) obj4).cancel(true);
                }
            }
        }
        do {
            obj = get(0);
            if (obj == DONE || obj == (obj2 = DISPOSED) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((a) obj).a(this);
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        Object obj = get(1);
        return obj == DISPOSED || obj == DONE;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        boolean zCompareAndSet;
        Object obj3;
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
        Object obj4 = get(0);
        if (obj4 != DISPOSED && obj4 != null && compareAndSet(0, obj4, DONE)) {
            ((a) obj4).a(this);
        }
        do {
            obj3 = get(1);
            if (obj3 == DISPOSED) {
                return;
            }
        } while (!compareAndSet(1, obj3, DONE));
    }

    public void setFuture(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == DONE) {
                return;
            }
            if (obj == DISPOSED) {
                future.cancel(true);
                return;
            }
        } while (!compareAndSet(1, obj, future));
    }
}
