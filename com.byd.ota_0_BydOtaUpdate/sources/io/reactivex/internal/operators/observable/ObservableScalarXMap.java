package io.reactivex.internal.operators.observable;

import a.b.e.a.a.d;
import d.a.g;
import d.a.h;
import d.a.i;
import d.a.o.e;
import d.a.p.b.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableScalarXMap {

    public static final class ScalarDisposable<T> extends AtomicInteger implements d.a.p.c.a<T>, Runnable {
        public static final int FUSED = 1;
        public static final int ON_COMPLETE = 3;
        public static final int ON_NEXT = 2;
        public static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        public final i<? super T> observer;
        public final T value;

        public ScalarDisposable(i<? super T> iVar, T t) {
            this.observer = iVar;
            this.value = t;
        }

        @Override // d.a.p.c.e
        public void clear() {
            lazySet(3);
        }

        @Override // d.a.m.b
        public void dispose() {
            set(3);
        }

        @Override // d.a.m.b
        public boolean isDisposed() {
            return get() == 3;
        }

        @Override // d.a.p.c.e
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // d.a.p.c.e
        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // d.a.p.c.e
        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        @Override // d.a.p.c.b
        public int requestFusion(int i2) {
            if ((i2 & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext(this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }
    }

    public static final class a<T, R> extends g<R> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final T f5908c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final e<? super T, ? extends h<? extends R>> f5909d;

        public a(T t, e<? super T, ? extends h<? extends R>> eVar) {
            this.f5908c = t;
            this.f5909d = eVar;
        }

        @Override // d.a.g
        public void t(i<? super R> iVar) {
            try {
                h hVar = (h) b.d(this.f5909d.apply(this.f5908c), "The mapper returned a null ObservableSource");
                if (!(hVar instanceof Callable)) {
                    hVar.a(iVar);
                    return;
                }
                try {
                    Object objCall = ((Callable) hVar).call();
                    if (objCall == null) {
                        EmptyDisposable.complete(iVar);
                        return;
                    }
                    ScalarDisposable scalarDisposable = new ScalarDisposable(iVar, objCall);
                    iVar.onSubscribe(scalarDisposable);
                    scalarDisposable.run();
                } catch (Throwable th) {
                    d.a.n.a.a(th);
                    EmptyDisposable.error(th, iVar);
                }
            } catch (Throwable th2) {
                EmptyDisposable.error(th2, iVar);
            }
        }
    }

    public static <T, U> g<U> a(T t, e<? super T, ? extends h<? extends U>> eVar) {
        return d.a.r.a.l(new a(t, eVar));
    }

    public static <T, R> boolean b(h<T> hVar, i<? super R> iVar, e<? super T, ? extends h<? extends R>> eVar) {
        if (!(hVar instanceof Callable)) {
            return false;
        }
        try {
            d dVar = (Object) ((Callable) hVar).call();
            if (dVar == null) {
                EmptyDisposable.complete(iVar);
                return true;
            }
            try {
                h hVar2 = (h) b.d(eVar.apply(dVar), "The mapper returned a null ObservableSource");
                if (hVar2 instanceof Callable) {
                    try {
                        Object objCall = ((Callable) hVar2).call();
                        if (objCall == null) {
                            EmptyDisposable.complete(iVar);
                            return true;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(iVar, objCall);
                        iVar.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        d.a.n.a.a(th);
                        EmptyDisposable.error(th, iVar);
                        return true;
                    }
                } else {
                    hVar2.a(iVar);
                }
                return true;
            } catch (Throwable th2) {
                d.a.n.a.a(th2);
                EmptyDisposable.error(th2, iVar);
                return true;
            }
        } catch (Throwable th3) {
            d.a.n.a.a(th3);
            EmptyDisposable.error(th3, iVar);
            return true;
        }
    }
}
