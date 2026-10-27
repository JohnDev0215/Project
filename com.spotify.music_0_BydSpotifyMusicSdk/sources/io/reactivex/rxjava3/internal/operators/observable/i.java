package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class i extends io.reactivex.rxjava3.observers.a {
    public final ArrayBlockingQueue e = new ArrayBlockingQueue(1);
    public final AtomicInteger k = new AtomicInteger();

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        RxJavaPlugins.b(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        Notification notification = (Notification) obj;
        if (this.k.getAndSet(0) != 1 && notification.c()) {
            return;
        }
        while (true) {
            ArrayBlockingQueue arrayBlockingQueue = this.e;
            if (arrayBlockingQueue.offer(notification)) {
                return;
            }
            Notification notification2 = (Notification) arrayBlockingQueue.poll();
            if (notification2 != null && !notification2.c()) {
                notification = notification2;
            }
        }
    }
}
