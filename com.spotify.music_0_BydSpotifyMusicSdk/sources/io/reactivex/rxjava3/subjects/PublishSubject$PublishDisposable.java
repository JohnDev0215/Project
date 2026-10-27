package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class PublishSubject$PublishDisposable<T> extends AtomicBoolean implements Disposable {
    private static final long serialVersionUID = 3562861878281475070L;
    final Observer<? super T> downstream;
    final c parent;

    public PublishSubject$PublishDisposable(Observer observer, c cVar) {
        this.downstream = observer;
        this.parent = cVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (compareAndSet(false, true)) {
            this.parent.a(this);
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get();
    }
}
