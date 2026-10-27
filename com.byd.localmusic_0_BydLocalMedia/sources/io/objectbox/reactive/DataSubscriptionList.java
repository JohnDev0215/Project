package io.objectbox.reactive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DataSubscriptionList implements DataSubscription {
    private boolean canceled;
    private final List<DataSubscription> subscriptions = new ArrayList();

    public synchronized void add(DataSubscription dataSubscription) {
        this.subscriptions.add(dataSubscription);
        this.canceled = false;
    }

    @Override // io.objectbox.reactive.DataSubscription
    public synchronized void cancel() {
        this.canceled = true;
        Iterator<DataSubscription> it = this.subscriptions.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
        this.subscriptions.clear();
    }

    @Override // io.objectbox.reactive.DataSubscription
    public synchronized boolean isCanceled() {
        return this.canceled;
    }

    public synchronized int getActiveSubscriptionCount() {
        return this.subscriptions.size();
    }
}
