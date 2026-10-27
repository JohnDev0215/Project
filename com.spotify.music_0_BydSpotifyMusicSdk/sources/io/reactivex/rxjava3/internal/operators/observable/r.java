package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.observers.BlockingObserver;
import io.reactivex.rxjava3.internal.observers.LambdaObserver;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static <T> void subscribe(ObservableSource<? extends T> observableSource, Observer<? super T> observer) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        BlockingObserver blockingObserver = new BlockingObserver(linkedBlockingQueue);
        observer.onSubscribe(blockingObserver);
        observableSource.subscribe(blockingObserver);
        while (!blockingObserver.isDisposed()) {
            Object objPoll = linkedBlockingQueue.poll();
            if (objPoll == null) {
                try {
                    objPoll = linkedBlockingQueue.take();
                } catch (InterruptedException e) {
                    blockingObserver.dispose();
                    observer.onError(e);
                    return;
                }
            }
            if (blockingObserver.isDisposed() || objPoll == BlockingObserver.c || NotificationLite.c(observer, objPoll)) {
                return;
            }
        }
    }

    public static <T> void subscribe(ObservableSource<? extends T> observableSource) {
        io.reactivex.rxjava3.internal.util.b bVar = new io.reactivex.rxjava3.internal.util.b(1);
        LambdaObserver lambdaObserver = new LambdaObserver(io.reactivex.rxjava3.internal.functions.k.d, bVar, bVar);
        observableSource.subscribe(lambdaObserver);
        if (bVar.getCount() != 0) {
            try {
                bVar.await();
            } catch (InterruptedException e) {
                lambdaObserver.dispose();
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e);
            }
        }
        Throwable th = bVar.c;
        if (th != null) {
            throw io.reactivex.rxjava3.internal.util.c.f(th);
        }
    }

    public static <T> void subscribe(ObservableSource<? extends T> observableSource, io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(gVar, "onNext is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        subscribe(observableSource, new LambdaObserver(gVar, gVar2, aVar));
    }
}
