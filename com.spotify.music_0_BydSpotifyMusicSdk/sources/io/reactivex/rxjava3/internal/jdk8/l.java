package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public final class l implements Disposable, BiConsumer {
    public final SingleObserver c;
    public final FlowableFromCompletionStage$BiConsumerAtomicReference e;

    public l(SingleObserver singleObserver, FlowableFromCompletionStage$BiConsumerAtomicReference flowableFromCompletionStage$BiConsumerAtomicReference) {
        this.c = singleObserver;
        this.e = flowableFromCompletionStage$BiConsumerAtomicReference;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        Throwable th = (Throwable) obj2;
        SingleObserver singleObserver = this.c;
        if (th != null) {
            singleObserver.onError(th);
        } else if (obj != null) {
            singleObserver.onSuccess(obj);
        } else {
            singleObserver.onError(new NullPointerException("The CompletionStage terminated with null."));
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e.set(null);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e.get() == null;
    }
}
