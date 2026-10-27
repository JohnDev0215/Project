package io.objectbox.query;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataPublisher;
import io.objectbox.reactive.DataPublisherUtils;
import io.objectbox.reactive.DataSubscription;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class QueryPublisher<T> implements DataPublisher<List<T>>, Runnable {
    private final Box<T> box;
    private DataObserver<Class<T>> objectClassObserver;
    private DataSubscription objectClassSubscription;
    private final Query<T> query;
    private final Set<DataObserver<List<T>>> observers = new CopyOnWriteArraySet();
    private final Deque<DataObserver<List<T>>> publishQueue = new ArrayDeque();
    private volatile boolean publisherRunning = false;
    private final SubscribedObservers<T> SUBSCRIBED_OBSERVERS = new SubscribedObservers<>();

    private static class SubscribedObservers<T> implements DataObserver<List<T>> {
        @Override // io.objectbox.reactive.DataObserver
        public void onData(List<T> list) {
        }

        private SubscribedObservers() {
        }
    }

    QueryPublisher(Query<T> query, Box<T> box) {
        this.query = query;
        this.box = box;
    }

    @Override // io.objectbox.reactive.DataPublisher
    public synchronized void subscribe(DataObserver<List<T>> dataObserver, @Nullable Object obj) {
        BoxStore store = this.box.getStore();
        if (this.objectClassObserver == null) {
            this.objectClassObserver = new DataObserver() { // from class: io.objectbox.query.-$$Lambda$QueryPublisher$WjVAgedSvNdouQc6Z0btKuAXgD4
                @Override // io.objectbox.reactive.DataObserver
                public final void onData(Object obj2) {
                    this.f$0.lambda$subscribe$0$QueryPublisher((Class) obj2);
                }
            };
        }
        if (this.observers.isEmpty()) {
            if (this.objectClassSubscription != null) {
                throw new IllegalStateException("Existing subscription found");
            }
            this.objectClassSubscription = store.subscribe(this.box.getEntityClass()).weak().onlyChanges().observer(this.objectClassObserver);
        }
        this.observers.add(dataObserver);
    }

    public /* synthetic */ void lambda$subscribe$0$QueryPublisher(Class cls) {
        publish();
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void publishSingle(DataObserver<List<T>> dataObserver, @Nullable Object obj) {
        queueObserverAndScheduleRun(dataObserver);
    }

    void publish() {
        queueObserverAndScheduleRun(this.SUBSCRIBED_OBSERVERS);
    }

    private void queueObserverAndScheduleRun(DataObserver<List<T>> dataObserver) {
        synchronized (this.publishQueue) {
            this.publishQueue.add(dataObserver);
            if (!this.publisherRunning) {
                this.publisherRunning = true;
                this.box.getStore().internalScheduleThread(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ArrayList arrayList;
        boolean z;
        while (true) {
            try {
                arrayList = new ArrayList();
            } finally {
            }
            synchronized (this.publishQueue) {
                z = false;
                while (true) {
                    DataObserver<List<T>> dataObserverPoll = this.publishQueue.poll();
                    if (dataObserverPoll == null) {
                        break;
                    } else if (this.SUBSCRIBED_OBSERVERS.equals(dataObserverPoll)) {
                        z = true;
                    } else {
                        arrayList.add(dataObserverPoll);
                    }
                    this.publisherRunning = false;
                }
                if (!z && arrayList.isEmpty()) {
                    this.publisherRunning = false;
                    return;
                }
                this.publisherRunning = false;
            }
            List<T> listFind = this.query.find();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((DataObserver) it.next()).onData(listFind);
            }
            if (z) {
                Iterator<DataObserver<List<T>>> it2 = this.observers.iterator();
                while (it2.hasNext()) {
                    it2.next().onData(listFind);
                }
            }
        }
    }

    @Override // io.objectbox.reactive.DataPublisher
    public synchronized void unsubscribe(DataObserver<List<T>> dataObserver, @Nullable Object obj) {
        DataPublisherUtils.removeObserverFromCopyOnWriteSet(this.observers, dataObserver);
        if (this.observers.isEmpty()) {
            this.objectClassSubscription.cancel();
            this.objectClassSubscription = null;
        }
    }
}
