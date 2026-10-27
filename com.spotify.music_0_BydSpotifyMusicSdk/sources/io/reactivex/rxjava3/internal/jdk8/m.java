package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import java.util.concurrent.CompletionStage;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Single {
    public final CompletionStage c;

    public m(CompletionStage completionStage) {
        this.c = completionStage;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        FlowableFromCompletionStage$BiConsumerAtomicReference flowableFromCompletionStage$BiConsumerAtomicReference = new FlowableFromCompletionStage$BiConsumerAtomicReference();
        l lVar = new l(singleObserver, flowableFromCompletionStage$BiConsumerAtomicReference);
        flowableFromCompletionStage$BiConsumerAtomicReference.lazySet(lVar);
        singleObserver.onSubscribe(lVar);
        this.c.whenComplete(flowableFromCompletionStage$BiConsumerAtomicReference);
    }
}
