package io.objectbox.reactive;

import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public class WeakDataObserver<T> implements DataObserver<T>, DelegatingObserver {
    private DataSubscription subscription;
    private final WeakReference<DataObserver<T>> weakDelegate;

    WeakDataObserver(DataObserver<T> dataObserver) {
        this.weakDelegate = new WeakReference<>(dataObserver);
    }

    @Override // io.objectbox.reactive.DataObserver
    public void onData(T t) {
        DataObserver<T> dataObserver = this.weakDelegate.get();
        if (dataObserver != null) {
            dataObserver.onData(t);
        } else {
            this.subscription.cancel();
        }
    }

    @Override // io.objectbox.reactive.DelegatingObserver
    public DataObserver<T> getObserverDelegate() {
        return this.weakDelegate.get();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WeakDataObserver)) {
            return false;
        }
        DataObserver<T> dataObserver = this.weakDelegate.get();
        if (dataObserver == null || dataObserver != ((WeakDataObserver) obj).weakDelegate.get()) {
            return super.equals(obj);
        }
        return true;
    }

    public int hashCode() {
        DataObserver<T> dataObserver = this.weakDelegate.get();
        if (dataObserver != null) {
            return dataObserver.hashCode();
        }
        return super.hashCode();
    }

    public void setSubscription(DataSubscription dataSubscription) {
        this.subscription = dataSubscription;
    }
}
