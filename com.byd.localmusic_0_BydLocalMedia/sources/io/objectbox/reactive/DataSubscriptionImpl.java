package io.objectbox.reactive;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class DataSubscriptionImpl<T> implements DataSubscription {
    private volatile boolean canceled;
    private DataObserver<T> observer;
    private DataPublisher<T> publisher;
    private Object publisherParam;

    DataSubscriptionImpl(DataPublisher<T> dataPublisher, @Nullable Object obj, DataObserver<T> dataObserver) {
        this.publisher = dataPublisher;
        this.publisherParam = obj;
        this.observer = dataObserver;
    }

    @Override // io.objectbox.reactive.DataSubscription
    public synchronized void cancel() {
        this.canceled = true;
        if (this.publisher != null) {
            this.publisher.unsubscribe(this.observer, this.publisherParam);
            this.publisher = null;
            this.observer = null;
            this.publisherParam = null;
        }
    }

    @Override // io.objectbox.reactive.DataSubscription
    public boolean isCanceled() {
        return this.canceled;
    }
}
