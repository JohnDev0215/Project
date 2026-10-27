package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
final class ObservableObserveOn$ObserveOnObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T>, Runnable {
    private static final long serialVersionUID = 6576896619930983584L;
    final int bufferSize;
    final boolean delayError;
    volatile boolean disposed;
    volatile boolean done;
    final Observer<? super T> downstream;
    Throwable error;
    boolean outputFused;
    io.reactivex.rxjava3.operators.g queue;
    int sourceMode;
    Disposable upstream;
    final Scheduler.Worker worker;

    public ObservableObserveOn$ObserveOnObserver(Observer observer, Scheduler.Worker worker, boolean z, int i) {
        this.downstream = observer;
        this.worker = worker;
        this.delayError = z;
        this.bufferSize = i;
    }

    public final boolean a(boolean z, boolean z2, Observer observer) {
        if (this.disposed) {
            this.queue.clear();
            return true;
        }
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (this.delayError) {
            if (!z2) {
                return false;
            }
            this.disposed = true;
            if (th != null) {
                observer.onError(th);
            } else {
                observer.onComplete();
            }
            this.worker.dispose();
            return true;
        }
        if (th != null) {
            this.disposed = true;
            this.queue.clear();
            observer.onError(th);
            this.worker.dispose();
            return true;
        }
        if (!z2) {
            return false;
        }
        this.disposed = true;
        observer.onComplete();
        this.worker.dispose();
        return true;
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
        this.queue.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        this.upstream.dispose();
        this.worker.dispose();
        if (this.outputFused || getAndIncrement() != 0) {
            return;
        }
        this.queue.clear();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        if (getAndIncrement() == 0) {
            this.worker.a(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.b(th);
            return;
        }
        this.error = th;
        this.done = true;
        if (getAndIncrement() == 0) {
            this.worker.a(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.done) {
            return;
        }
        if (this.sourceMode != 2) {
            this.queue.offer(obj);
        }
        if (getAndIncrement() == 0) {
            this.worker.a(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(7);
                if (iB == 1) {
                    this.sourceMode = iB;
                    this.queue = bVar;
                    this.done = true;
                    this.downstream.onSubscribe(this);
                    if (getAndIncrement() == 0) {
                        this.worker.a(this);
                        return;
                    }
                    return;
                }
                if (iB == 2) {
                    this.sourceMode = iB;
                    this.queue = bVar;
                    this.downstream.onSubscribe(this);
                    return;
                }
            }
            this.queue = new io.reactivex.rxjava3.operators.h(this.bufferSize);
            this.downstream.onSubscribe(this);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        return this.queue.poll();
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0075, code lost:
    
        r3 = addAndGet(-r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007a, code lost:
    
        if (r3 != 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r7 = this;
            boolean r0 = r7.outputFused
            r1 = 1
            if (r0 == 0) goto L4f
            r0 = r1
        L6:
            boolean r2 = r7.disposed
            if (r2 == 0) goto Lc
            goto L97
        Lc:
            boolean r2 = r7.done
            java.lang.Throwable r3 = r7.error
            boolean r4 = r7.delayError
            if (r4 != 0) goto L28
            if (r2 == 0) goto L28
            if (r3 == 0) goto L28
            r7.disposed = r1
            io.reactivex.rxjava3.core.Observer<? super T> r0 = r7.downstream
            java.lang.Throwable r1 = r7.error
            r0.onError(r1)
            io.reactivex.rxjava3.core.Scheduler$Worker r0 = r7.worker
            r0.dispose()
            goto L97
        L28:
            io.reactivex.rxjava3.core.Observer<? super T> r3 = r7.downstream
            r4 = 0
            r3.onNext(r4)
            if (r2 == 0) goto L47
            r7.disposed = r1
            java.lang.Throwable r0 = r7.error
            if (r0 == 0) goto L3c
            io.reactivex.rxjava3.core.Observer<? super T> r1 = r7.downstream
            r1.onError(r0)
            goto L41
        L3c:
            io.reactivex.rxjava3.core.Observer<? super T> r0 = r7.downstream
            r0.onComplete()
        L41:
            io.reactivex.rxjava3.core.Scheduler$Worker r0 = r7.worker
            r0.dispose()
            goto L97
        L47:
            int r0 = -r0
            int r0 = r7.addAndGet(r0)
            if (r0 != 0) goto L6
            goto L97
        L4f:
            io.reactivex.rxjava3.operators.g r0 = r7.queue
            io.reactivex.rxjava3.core.Observer<? super T> r2 = r7.downstream
            r3 = r1
        L54:
            boolean r4 = r7.done
            boolean r5 = r0.isEmpty()
            boolean r4 = r7.a(r4, r5, r2)
            if (r4 == 0) goto L61
            goto L97
        L61:
            boolean r4 = r7.done
            java.lang.Object r5 = r0.poll()     // Catch: java.lang.Throwable -> L81
            if (r5 != 0) goto L6b
            r6 = r1
            goto L6c
        L6b:
            r6 = 0
        L6c:
            boolean r4 = r7.a(r4, r6, r2)
            if (r4 == 0) goto L73
            goto L97
        L73:
            if (r6 == 0) goto L7d
            int r3 = -r3
            int r3 = r7.addAndGet(r3)
            if (r3 != 0) goto L54
            goto L97
        L7d:
            r2.onNext(r5)
            goto L61
        L81:
            r3 = move-exception
            p.he1.R(r3)
            r7.disposed = r1
            io.reactivex.rxjava3.disposables.Disposable r1 = r7.upstream
            r1.dispose()
            r0.clear()
            r2.onError(r3)
            io.reactivex.rxjava3.core.Scheduler$Worker r0 = r7.worker
            r0.dispose()
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableObserveOn$ObserveOnObserver.run():void");
    }
}
