package io.reactivex.internal.operators.observable;

import d.a.e;
import d.a.h;
import d.a.i;
import d.a.m.f;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableScalarXMap {

    public static final class ScalarDisposable<T> extends AtomicInteger implements d.a.n.b.a<T>, Runnable {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final i<? super T> observer;
        final T value;

        public ScalarDisposable(i<? super T> iVar, T t) {
            this.observer = iVar;
            this.value = t;
        }

        @Override // d.a.n.b.e
        public void clear() {
            lazySet(3);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            set(3);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == 3;
        }

        @Override // d.a.n.b.e
        public boolean isEmpty() {
            return get() != 1;
        }

        @Override // d.a.n.b.e
        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Override // d.a.n.b.e
        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        @Override // d.a.n.b.b
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
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

    static final class a<T, R> extends e<R> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final T f4285b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final f<? super T, ? extends h<? extends R>> f4286c;

        a(T t, f<? super T, ? extends h<? extends R>> fVar) {
            this.f4285b = t;
            this.f4286c = fVar;
        }

        @Override // d.a.e
        public void o(i<? super R> iVar) {
            try {
                h hVar = (h) d.a.n.a.b.d(this.f4286c.apply(this.f4285b), "The mapper returned a null ObservableSource");
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
                    io.reactivex.exceptions.a.b(th);
                    EmptyDisposable.error(th, iVar);
                }
            } catch (Throwable th2) {
                EmptyDisposable.error(th2, iVar);
            }
        }
    }

    public static <T, U> e<U> a(T t, f<? super T, ? extends h<? extends U>> fVar) {
        return d.a.p.a.j(new a(t, fVar));
    }

    public static <T, R> boolean b(h<T> hVar, i<? super R> iVar, f<? super T, ? extends h<? extends R>> fVar) {
        if (!(hVar instanceof Callable)) {
            return false;
        }
        try {
            a.a.a aVar = (Object) ((Callable) hVar).call();
            if (aVar == null) {
                EmptyDisposable.complete(iVar);
                return true;
            }
            try {
                h hVar2 = (h) d.a.n.a.b.d(fVar.apply(aVar), "The mapper returned a null ObservableSource");
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
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, iVar);
                        return true;
                    }
                } else {
                    hVar2.a(iVar);
                }
                return true;
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptyDisposable.error(th2, iVar);
                return true;
            }
        } catch (Throwable th3) {
            io.reactivex.exceptions.a.b(th3);
            EmptyDisposable.error(th3, iVar);
            return true;
        }
    }
}
