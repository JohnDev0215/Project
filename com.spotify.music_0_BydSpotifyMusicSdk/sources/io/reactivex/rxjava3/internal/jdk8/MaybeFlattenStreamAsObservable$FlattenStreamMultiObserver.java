package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class MaybeFlattenStreamAsObservable$FlattenStreamMultiObserver<T, R> extends BasicIntQueueDisposable<R> implements MaybeObserver<T>, SingleObserver<T> {
    private static final long serialVersionUID = 7363336003027148283L;
    AutoCloseable close;
    volatile boolean disposed;
    final Observer<? super R> downstream;
    volatile Iterator<? extends R> iterator;
    final io.reactivex.rxjava3.functions.o mapper;
    boolean once;
    boolean outputFused;
    Disposable upstream;

    public MaybeFlattenStreamAsObservable$FlattenStreamMultiObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        this.downstream = observer;
        this.mapper = oVar;
    }

    public final void a() {
        if (getAndIncrement() != 0) {
            return;
        }
        Observer<? super R> observer = this.downstream;
        Iterator<? extends R> it = this.iterator;
        int iAddAndGet = 1;
        while (true) {
            if (this.disposed) {
                clear();
            } else if (this.outputFused) {
                observer.onNext(null);
                observer.onComplete();
            } else {
                try {
                    R next = it.next();
                    if (!this.disposed) {
                        observer.onNext(next);
                        if (!this.disposed) {
                            try {
                                boolean zHasNext = it.hasNext();
                                if (!this.disposed && !zHasNext) {
                                    observer.onComplete();
                                    this.disposed = true;
                                }
                            } catch (Throwable th) {
                                he1.R(th);
                                observer.onError(th);
                                this.disposed = true;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    observer.onError(th2);
                    this.disposed = true;
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.iterator = null;
        AutoCloseable autoCloseable = this.close;
        this.close = null;
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.disposed = true;
        this.upstream.dispose();
        if (this.outputFused) {
            return;
        }
        a();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        Iterator<? extends R> it = this.iterator;
        if (it == null) {
            return true;
        }
        if (!this.once || it.hasNext()) {
            return false;
        }
        clear();
        return true;
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        try {
            Object objApply = this.mapper.apply(obj);
            Objects.requireNonNull(objApply, "The mapper returned a null Stream");
            Stream stream = (Stream) objApply;
            Iterator<? extends R> it = stream.iterator();
            if (it.hasNext()) {
                this.iterator = it;
                this.close = stream;
                a();
            } else {
                this.downstream.onComplete();
                try {
                    stream.close();
                } catch (Throwable th) {
                    he1.R(th);
                    RxJavaPlugins.b(th);
                }
            }
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(th2);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        Iterator<? extends R> it = this.iterator;
        if (it == null) {
            return null;
        }
        if (!this.once) {
            this.once = true;
        } else if (!it.hasNext()) {
            clear();
            return null;
        }
        return it.next();
    }
}
