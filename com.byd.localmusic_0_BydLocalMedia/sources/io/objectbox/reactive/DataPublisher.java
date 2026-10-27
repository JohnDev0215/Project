package io.objectbox.reactive;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface DataPublisher<T> {
    void publishSingle(DataObserver<T> dataObserver, @Nullable Object obj);

    void subscribe(DataObserver<T> dataObserver, @Nullable Object obj);

    void unsubscribe(DataObserver<T> dataObserver, @Nullable Object obj);
}
