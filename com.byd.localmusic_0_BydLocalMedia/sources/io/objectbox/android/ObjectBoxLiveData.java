package io.objectbox.android;

import androidx.lifecycle.LiveData;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ObjectBoxLiveData<T> extends LiveData<List<T>> {
    private final DataObserver<List<T>> listener = new DataObserver() { // from class: io.objectbox.android.-$$Lambda$ObjectBoxLiveData$waNzJZ3ULZ8hYnA1lgjolmOUiPM
        @Override // io.objectbox.reactive.DataObserver
        public final void onData(Object obj) {
            this.f$0.postValue((List) obj);
        }
    };
    private final Query<T> query;
    private DataSubscription subscription;

    public ObjectBoxLiveData(Query<T> query) {
        this.query = query;
    }

    @Override // androidx.lifecycle.LiveData
    protected void onActive() {
        if (this.subscription == null) {
            this.subscription = this.query.subscribe().observer(this.listener);
        }
    }

    @Override // androidx.lifecycle.LiveData
    protected void onInactive() {
        if (hasObservers()) {
            return;
        }
        this.subscription.cancel();
        this.subscription = null;
    }
}
