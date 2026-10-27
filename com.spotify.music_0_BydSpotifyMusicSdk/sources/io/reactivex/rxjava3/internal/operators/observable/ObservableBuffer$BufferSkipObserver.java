package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class ObservableBuffer$BufferSkipObserver<T, U extends Collection<? super T>> extends AtomicBoolean implements Observer<T>, Disposable {
    private static final long serialVersionUID = -8223395059921494546L;
    final io.reactivex.rxjava3.functions.q bufferSupplier;
    final ArrayDeque<U> buffers = new ArrayDeque<>();
    final int count;
    final Observer<? super U> downstream;
    long index;
    final int skip;
    Disposable upstream;

    public ObservableBuffer$BufferSkipObserver(Observer observer, int i, int i2, io.reactivex.rxjava3.functions.q qVar) {
        this.downstream = observer;
        this.count = i;
        this.skip = i2;
        this.bufferSupplier = qVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.upstream.dispose();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        while (!this.buffers.isEmpty()) {
            this.downstream.onNext(this.buffers.poll());
        }
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.buffers.clear();
        this.downstream.onError(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        long j = this.index;
        this.index = 1 + j;
        if (j % ((long) this.skip) == 0) {
            try {
                Object obj2 = this.bufferSupplier.get();
                if (obj2 == null) {
                    throw io.reactivex.rxjava3.internal.util.c.b("The bufferSupplier returned a null Collection.");
                }
                Throwable th = io.reactivex.rxjava3.internal.util.c.a;
                this.buffers.offer((U) ((Collection) obj2));
            } catch (Throwable th2) {
                he1.R(th2);
                this.buffers.clear();
                this.upstream.dispose();
                this.downstream.onError(th2);
                return;
            }
        }
        Iterator<U> it = this.buffers.iterator();
        while (it.hasNext()) {
            U next = it.next();
            next.add(obj);
            if (this.count <= next.size()) {
                it.remove();
                this.downstream.onNext(next);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            this.downstream.onSubscribe(this);
        }
    }
}
