package io.reactivex.internal.operators.observable;

import e.a.e;
import e.a.f;
import e.a.g;
import e.a.i;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableCreate<T> extends e<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final g<T> f4996b;

    static final class CreateEmitter<T> extends AtomicReference<io.reactivex.disposables.b> implements f<T>, io.reactivex.disposables.b {
        private static final long serialVersionUID = -3434801548987643227L;
        final i<? super T> observer;

        CreateEmitter(i<? super T> iVar) {
            this.observer = iVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // e.a.f
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // e.a.b
        public void onComplete() {
            if (isDisposed()) {
                return;
            }
            try {
                this.observer.onComplete();
            } finally {
                dispose();
            }
        }

        @Override // e.a.b
        public void onError(Throwable th) {
            if (tryOnError(th)) {
                return;
            }
            e.a.o.a.k(th);
        }

        @Override // e.a.b
        public void onNext(T t) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                if (isDisposed()) {
                    return;
                }
                this.observer.onNext(t);
            }
        }

        public f<T> serialize() {
            return new SerializedEmitter(this);
        }

        @Override // e.a.f
        public void setCancellable(e.a.m.d dVar) {
            setDisposable(new CancellableDisposable(dVar));
        }

        @Override // e.a.f
        public void setDisposable(io.reactivex.disposables.b bVar) {
            DisposableHelper.set(this, bVar);
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", CreateEmitter.class.getSimpleName(), super.toString());
        }

        public boolean tryOnError(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isDisposed()) {
                return false;
            }
            try {
                this.observer.onError(th);
                dispose();
                return true;
            } catch (Throwable th2) {
                dispose();
                throw th2;
            }
        }
    }

    static final class SerializedEmitter<T> extends AtomicInteger implements f<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final f<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final io.reactivex.internal.queue.a<T> queue = new io.reactivex.internal.queue.a<>(16);

        SerializedEmitter(f<T> fVar) {
            this.emitter = fVar;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            f<T> fVar = this.emitter;
            io.reactivex.internal.queue.a<T> aVar = this.queue;
            AtomicThrowable atomicThrowable = this.error;
            int iAddAndGet = 1;
            while (!fVar.isDisposed()) {
                if (atomicThrowable.get() != null) {
                    aVar.clear();
                    fVar.onError(atomicThrowable.terminate());
                    return;
                }
                boolean z = this.done;
                T tPoll = aVar.poll();
                boolean z2 = tPoll == null;
                if (z && z2) {
                    fVar.onComplete();
                    return;
                } else if (z2) {
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                } else {
                    fVar.onNext(tPoll);
                }
            }
            aVar.clear();
        }

        @Override // e.a.f
        public boolean isDisposed() {
            return this.emitter.isDisposed();
        }

        @Override // e.a.b
        public void onComplete() {
            if (this.emitter.isDisposed() || this.done) {
                return;
            }
            this.done = true;
            drain();
        }

        @Override // e.a.b
        public void onError(Throwable th) {
            if (tryOnError(th)) {
                return;
            }
            e.a.o.a.k(th);
        }

        @Override // e.a.b
        public void onNext(T t) {
            if (this.emitter.isDisposed() || this.done) {
                return;
            }
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            if (get() == 0 && compareAndSet(0, 1)) {
                this.emitter.onNext(t);
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                io.reactivex.internal.queue.a<T> aVar = this.queue;
                synchronized (aVar) {
                    aVar.offer(t);
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        public f<T> serialize() {
            return this;
        }

        @Override // e.a.f
        public void setCancellable(e.a.m.d dVar) {
            this.emitter.setCancellable(dVar);
        }

        @Override // e.a.f
        public void setDisposable(io.reactivex.disposables.b bVar) {
            this.emitter.setDisposable(bVar);
        }

        @Override // java.util.concurrent.atomic.AtomicInteger
        public String toString() {
            return this.emitter.toString();
        }

        public boolean tryOnError(Throwable th) {
            if (!this.emitter.isDisposed() && !this.done) {
                if (th == null) {
                    th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
                }
                if (this.error.addThrowable(th)) {
                    this.done = true;
                    drain();
                    return true;
                }
            }
            return false;
        }
    }

    public ObservableCreate(g<T> gVar) {
        this.f4996b = gVar;
    }

    @Override // e.a.e
    protected void m(i<? super T> iVar) {
        CreateEmitter createEmitter = new CreateEmitter(iVar);
        iVar.onSubscribe(createEmitter);
        try {
            this.f4996b.subscribe(createEmitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            createEmitter.onError(th);
        }
    }
}
