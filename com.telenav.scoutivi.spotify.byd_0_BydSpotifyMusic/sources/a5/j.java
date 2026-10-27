package a5;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: ScheduledRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, t4.b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final Object f97g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static final Object f98h = new Object();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final Object f99i = new Object();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static final Object f100j = new Object();
    private static final long serialVersionUID = -6120223772001106981L;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Runnable f101e;

    public j(Runnable runnable, w4.a aVar) {
        super(3);
        this.f101e = runnable;
        lazySet(0, aVar);
    }

    public void a(Future<?> future) {
        Object obj;
        do {
            obj = get(1);
            if (obj == f100j) {
                return;
            }
            if (obj == f98h) {
                future.cancel(false);
                return;
            } else if (obj == f99i) {
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

    @Override // t4.b
    public void dispose() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        while (true) {
            Object obj5 = get(1);
            if (obj5 == f100j || obj5 == (obj3 = f98h) || obj5 == (obj4 = f99i)) {
                break;
            }
            boolean z6 = get(2) != Thread.currentThread();
            if (z6) {
                obj3 = obj4;
            }
            if (compareAndSet(1, obj5, obj3)) {
                if (obj5 != null) {
                    ((Future) obj5).cancel(z6);
                }
            }
        }
        do {
            obj = get(0);
            if (obj == f100j || obj == (obj2 = f97g) || obj == null) {
                return;
            }
        } while (!compareAndSet(0, obj, obj2));
        ((w4.a) obj).b(this);
    }

    @Override // t4.b
    public boolean isDisposed() {
        Object obj = get(0);
        return obj == f97g || obj == f100j;
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
            this.f101e.run();
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
        if (obj5 != f97g && compareAndSet(0, obj5, f100j) && obj5 != null) {
            ((w4.a) obj5).b(this);
        }
        do {
            obj4 = get(1);
            if (obj4 == f98h || obj4 == f99i) {
                return;
            }
        } while (!compareAndSet(1, obj4, f100j));
    }
}
