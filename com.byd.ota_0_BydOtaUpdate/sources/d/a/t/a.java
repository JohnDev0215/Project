package d.a.t;

import d.a.i;
import d.a.p.h.a;
import io.reactivex.internal.util.NotificationLite;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: BehaviorSubject.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends c<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object[] f3283c = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0079a[] f3284d = new C0079a[0];
    public static final C0079a[] s = new C0079a[0];
    public final ReadWriteLock E3;
    public final Lock F3;
    public final Lock G3;
    public boolean H3;
    public long I3;
    public final AtomicReference<Object> x;
    public final AtomicReference<C0079a<T>[]> y;

    /* JADX INFO: renamed from: d.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BehaviorSubject.java */
    public static final class C0079a<T> implements d.a.m.b, a.InterfaceC0077a<Object> {
        public boolean E3;
        public volatile boolean F3;
        public long G3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final i<? super T> f3285c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a<T> f3286d;
        public boolean s;
        public boolean x;
        public d.a.p.h.a<Object> y;

        public C0079a(i<? super T> iVar, a<T> aVar) {
            this.f3285c = iVar;
            this.f3286d = aVar;
        }

        public void a() {
            if (this.F3) {
                return;
            }
            synchronized (this) {
                if (this.F3) {
                    return;
                }
                if (this.s) {
                    return;
                }
                a<T> aVar = this.f3286d;
                Lock lock = aVar.F3;
                lock.lock();
                this.G3 = aVar.I3;
                Object obj = aVar.x.get();
                lock.unlock();
                this.x = obj != null;
                this.s = true;
                if (obj == null || test(obj)) {
                    return;
                }
                b();
            }
        }

        public void b() {
            d.a.p.h.a<Object> aVar;
            while (!this.F3) {
                synchronized (this) {
                    aVar = this.y;
                    if (aVar == null) {
                        this.x = false;
                        return;
                    }
                    this.y = null;
                }
                aVar.c(this);
            }
        }

        public void c(Object obj, long j) {
            if (this.F3) {
                return;
            }
            if (!this.E3) {
                synchronized (this) {
                    if (this.F3) {
                        return;
                    }
                    if (this.G3 == j) {
                        return;
                    }
                    if (this.x) {
                        d.a.p.h.a<Object> aVar = this.y;
                        if (aVar == null) {
                            aVar = new d.a.p.h.a<>(4);
                            this.y = aVar;
                        }
                        aVar.b(obj);
                        return;
                    }
                    this.s = true;
                    this.E3 = true;
                }
            }
            test(obj);
        }

        @Override // d.a.m.b
        public void dispose() {
            if (this.F3) {
                return;
            }
            this.F3 = true;
            this.f3286d.A(this);
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return this.F3;
        }

        @Override // d.a.p.h.a.InterfaceC0077a, d.a.o.g
        public boolean test(Object obj) {
            return this.F3 || NotificationLite.accept(obj, this.f3285c);
        }
    }

    public a() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.E3 = reentrantReadWriteLock;
        this.F3 = reentrantReadWriteLock.readLock();
        this.G3 = reentrantReadWriteLock.writeLock();
        this.y = new AtomicReference<>(f3284d);
        this.x = new AtomicReference<>();
    }

    public static <T> a<T> z() {
        return new a<>();
    }

    public void A(C0079a<T> c0079a) {
        C0079a<T>[] c0079aArr;
        C0079a<T>[] c0079aArr2;
        do {
            c0079aArr = this.y.get();
            if (c0079aArr == s || c0079aArr == f3284d) {
                return;
            }
            int length = c0079aArr.length;
            int i2 = -1;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (c0079aArr[i3] == c0079a) {
                    i2 = i3;
                    break;
                }
                i3++;
            }
            if (i2 < 0) {
                return;
            }
            if (length == 1) {
                c0079aArr2 = f3284d;
            } else {
                C0079a<T>[] c0079aArr3 = new C0079a[length - 1];
                System.arraycopy(c0079aArr, 0, c0079aArr3, 0, i2);
                System.arraycopy(c0079aArr, i2 + 1, c0079aArr3, i2, (length - i2) - 1);
                c0079aArr2 = c0079aArr3;
            }
        } while (!this.y.compareAndSet(c0079aArr, c0079aArr2));
    }

    public void B(Object obj) {
        this.G3.lock();
        try {
            this.I3++;
            this.x.lazySet(obj);
        } finally {
            this.G3.unlock();
        }
    }

    public C0079a<T>[] C(Object obj) {
        C0079a<T>[] andSet = this.y.get();
        C0079a<T>[] c0079aArr = s;
        if (andSet != c0079aArr && (andSet = this.y.getAndSet(c0079aArr)) != c0079aArr) {
            B(obj);
        }
        return andSet;
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.H3) {
            return;
        }
        this.H3 = true;
        Object objComplete = NotificationLite.complete();
        for (C0079a<T> c0079a : C(objComplete)) {
            c0079a.c(objComplete, this.I3);
        }
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.H3) {
            d.a.r.a.o(th);
            return;
        }
        this.H3 = true;
        Object objError = NotificationLite.error(th);
        for (C0079a<T> c0079a : C(objError)) {
            c0079a.c(objError, this.I3);
        }
    }

    @Override // d.a.i
    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        if (this.H3) {
            return;
        }
        Object next = NotificationLite.next(t);
        B(next);
        for (C0079a<T> c0079a : this.y.get()) {
            c0079a.c(next, this.I3);
        }
    }

    @Override // d.a.i
    public void onSubscribe(d.a.m.b bVar) {
        if (this.H3) {
            bVar.dispose();
        }
    }

    @Override // d.a.g
    public void t(i<? super T> iVar) {
        C0079a<T> c0079a = new C0079a<>(iVar, this);
        iVar.onSubscribe(c0079a);
        if (y(c0079a)) {
            if (c0079a.F3) {
                A(c0079a);
                return;
            } else {
                c0079a.a();
                return;
            }
        }
        Object obj = this.x.get();
        if (NotificationLite.isComplete(obj)) {
            iVar.onComplete();
        } else {
            iVar.onError(NotificationLite.getError(obj));
        }
    }

    public boolean y(C0079a<T> c0079a) {
        C0079a<T>[] c0079aArr;
        C0079a<T>[] c0079aArr2;
        do {
            c0079aArr = this.y.get();
            if (c0079aArr == s) {
                return false;
            }
            int length = c0079aArr.length;
            c0079aArr2 = new C0079a[length + 1];
            System.arraycopy(c0079aArr, 0, c0079aArr2, 0, length);
            c0079aArr2[length] = c0079a;
        } while (!this.y.compareAndSet(c0079aArr, c0079aArr2));
        return true;
    }
}
