package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
final class ObservableGroupBy$State<T, K> extends AtomicInteger implements Disposable, ObservableSource<T> {
    private static final long serialVersionUID = -3852313036005250360L;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final K key;
    final ObservableGroupBy$GroupByObserver<?, K, T> parent;
    final io.reactivex.rxjava3.operators.h queue;
    final AtomicBoolean cancelled = new AtomicBoolean();
    final AtomicReference<Observer<? super T>> actual = new AtomicReference<>();
    final AtomicInteger once = new AtomicInteger();

    /* JADX WARN: Multi-variable type inference failed */
    public ObservableGroupBy$State(int i, ObservableGroupBy$GroupByObserver observableGroupBy$GroupByObserver, Object obj, boolean z) {
        this.queue = new io.reactivex.rxjava3.operators.h(i);
        this.parent = observableGroupBy$GroupByObserver;
        this.key = obj;
        this.delayError = z;
    }

    public final void a() {
        if (getAndIncrement() != 0) {
            return;
        }
        io.reactivex.rxjava3.operators.h hVar = this.queue;
        boolean z = this.delayError;
        Observer<? super T> observer = this.actual.get();
        int iAddAndGet = 1;
        while (true) {
            if (observer != null) {
                while (true) {
                    boolean z2 = this.done;
                    Object objPoll = hVar.poll();
                    boolean z3 = objPoll == null;
                    if (this.cancelled.get()) {
                        this.queue.clear();
                        this.actual.lazySet(null);
                        if ((this.once.get() & 2) == 0) {
                            ObservableGroupBy$GroupByObserver<?, K, T> observableGroupBy$GroupByObserver = this.parent;
                            Object obj = this.key;
                            observableGroupBy$GroupByObserver.getClass();
                            if (obj == null) {
                                obj = ObservableGroupBy$GroupByObserver.c;
                            }
                            observableGroupBy$GroupByObserver.groups.remove(obj);
                            if (observableGroupBy$GroupByObserver.decrementAndGet() == 0) {
                                observableGroupBy$GroupByObserver.upstream.dispose();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (z2) {
                        if (!z) {
                            Throwable th = this.error;
                            if (th != null) {
                                this.queue.clear();
                                this.actual.lazySet(null);
                                observer.onError(th);
                                return;
                            } else if (z3) {
                                this.actual.lazySet(null);
                                observer.onComplete();
                                return;
                            }
                        } else if (z3) {
                            Throwable th2 = this.error;
                            this.actual.lazySet(null);
                            if (th2 != null) {
                                observer.onError(th2);
                                return;
                            } else {
                                observer.onComplete();
                                return;
                            }
                        }
                    }
                    if (z3) {
                        break;
                    } else {
                        observer.onNext(objPoll);
                    }
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
            if (observer == null) {
                observer = this.actual.get();
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
            this.actual.lazySet(null);
            if ((this.once.get() & 2) == 0) {
                ObservableGroupBy$GroupByObserver<?, K, T> observableGroupBy$GroupByObserver = this.parent;
                Object obj = this.key;
                observableGroupBy$GroupByObserver.getClass();
                if (obj == null) {
                    obj = ObservableGroupBy$GroupByObserver.c;
                }
                observableGroupBy$GroupByObserver.groups.remove(obj);
                if (observableGroupBy$GroupByObserver.decrementAndGet() == 0) {
                    observableGroupBy$GroupByObserver.upstream.dispose();
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled.get();
    }

    @Override // io.reactivex.rxjava3.core.ObservableSource
    public void subscribe(Observer<? super T> observer) {
        int i;
        do {
            i = this.once.get();
            if ((i & 1) != 0) {
                IllegalStateException illegalStateException = new IllegalStateException("Only one Observer allowed!");
                observer.onSubscribe(EmptyDisposable.c);
                observer.onError(illegalStateException);
                return;
            }
        } while (!this.once.compareAndSet(i, i | 1));
        observer.onSubscribe(this);
        this.actual.lazySet(observer);
        if (this.cancelled.get()) {
            this.actual.lazySet(null);
        } else {
            a();
        }
    }
}
