package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.internal.disposables.CancellableDisposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
public final class ObservableCreate<T> extends Observable<T> {
    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        CreateEmitter createEmitter = new CreateEmitter(observer);
        observer.onSubscribe(createEmitter);
        try {
            this.source.subscribe(createEmitter);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            createEmitter.onError(th);
        }
    }

    static final class CreateEmitter<T> extends AtomicReference<Disposable> implements ObservableEmitter<T>, Disposable {
        final Observer<? super T> observer;

        CreateEmitter(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onNext(T t) {
            if (t == null) {
                onError(ExceptionHelper.createNullPointerException("onNext called with a null value."));
            } else {
                if (isDisposed()) {
                    return;
                }
                this.observer.onNext(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.Emitter
        public void onError(Throwable t) {
            if (tryOnError(t)) {
                return;
            }
            RxJavaPlugins.onError(t);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public boolean tryOnError(Throwable t) {
            if (t == null) {
                t = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
            }
            if (isDisposed()) {
                return false;
            }
            try {
                this.observer.onError(t);
                dispose();
                return true;
            } catch (Throwable th) {
                dispose();
                throw th;
            }
        }

        @Override // io.reactivex.rxjava3.core.Emitter
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

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public void setDisposable(Disposable d) {
            DisposableHelper.set(this, d);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter
        public void setCancellable(Cancellable c) {
            setDisposable(new CancellableDisposable(c));
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.core.ObservableEmitter, io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", CreateEmitter.class.getSimpleName(), super.toString());
        }
    }
}
