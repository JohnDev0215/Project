package io.reactivex.internal.observers;

import d.a.f;
import d.a.i;
import d.a.m.b;
import d.a.n.a;
import d.a.o.d;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class ToNotificationObserver<T> extends AtomicReference<b> implements i<T>, b {
    private static final long serialVersionUID = -7420197867343208289L;
    public final d<? super f<Object>> consumer;

    public ToNotificationObserver(d<? super f<Object>> dVar) {
        this.consumer = dVar;
    }

    @Override // d.a.m.b
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override // d.a.i
    public void onComplete() {
        try {
            this.consumer.accept(f.a());
        } catch (Throwable th) {
            a.a(th);
            d.a.r.a.o(th);
        }
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        try {
            this.consumer.accept(f.b(th));
        } catch (Throwable th2) {
            a.a(th2);
            d.a.r.a.o(new CompositeException(th, th2));
        }
    }

    @Override // d.a.i
    public void onNext(T t) {
        if (t == null) {
            get().dispose();
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        try {
            this.consumer.accept(f.c(t));
        } catch (Throwable th) {
            a.a(th);
            get().dispose();
            onError(th);
        }
    }

    @Override // d.a.i
    public void onSubscribe(b bVar) {
        DisposableHelper.setOnce(this, bVar);
    }
}
