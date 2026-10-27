package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
final class ObservableFromCompletionStage$CompletionStageHandler<T> extends DeferredScalarDisposable<T> implements BiConsumer<T, Throwable> {
    private static final long serialVersionUID = 4665335664328839859L;
    final ObservableFromCompletionStage$BiConsumerAtomicReference<T> whenReference;

    public ObservableFromCompletionStage$CompletionStageHandler(Observer observer, ObservableFromCompletionStage$BiConsumerAtomicReference observableFromCompletionStage$BiConsumerAtomicReference) {
        super(observer);
        this.whenReference = observableFromCompletionStage$BiConsumerAtomicReference;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Throwable th) {
        Throwable th2 = th;
        if (th2 != null) {
            this.downstream.onError(th2);
        } else if (obj != null) {
            c(obj);
        } else {
            this.downstream.onError(new NullPointerException("The CompletionStage terminated with null."));
        }
    }

    @Override // io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable, io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        super.dispose();
        this.whenReference.set(null);
    }
}
