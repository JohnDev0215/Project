package io.objectbox;

import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataPublisher;
import io.objectbox.reactive.DataPublisherUtils;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import org.greenrobot.essentials.collections.MultimapSet;

/* JADX INFO: loaded from: classes5.dex */
class ObjectClassPublisher implements DataPublisher<Class>, Runnable {
    final BoxStore boxStore;
    volatile boolean changePublisherRunning;
    final MultimapSet<Integer, DataObserver<Class>> observersByEntityTypeId = MultimapSet.create(MultimapSet.SetType.THREAD_SAFE);
    private final Deque<PublishRequest> changesQueue = new ArrayDeque();

    private static class PublishRequest {
        private final int[] entityTypeIds;

        @Nullable
        private final DataObserver<Class> observer;

        PublishRequest(@Nullable DataObserver<Class> dataObserver, int[] iArr) {
            this.observer = dataObserver;
            this.entityTypeIds = iArr;
        }
    }

    ObjectClassPublisher(BoxStore boxStore) {
        this.boxStore = boxStore;
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void subscribe(DataObserver<Class> dataObserver, @Nullable Object obj) {
        if (obj == null) {
            for (int i : this.boxStore.getAllEntityTypeIds()) {
                this.observersByEntityTypeId.putElement(Integer.valueOf(i), dataObserver);
            }
            return;
        }
        this.observersByEntityTypeId.putElement(Integer.valueOf(this.boxStore.getEntityTypeIdOrThrow((Class) obj)), dataObserver);
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void unsubscribe(DataObserver<Class> dataObserver, @Nullable Object obj) {
        if (obj != null) {
            unsubscribe(dataObserver, this.boxStore.getEntityTypeIdOrThrow((Class) obj));
            return;
        }
        for (int i : this.boxStore.getAllEntityTypeIds()) {
            unsubscribe(dataObserver, i);
        }
    }

    private void unsubscribe(DataObserver<Class> dataObserver, int i) {
        DataPublisherUtils.removeObserverFromCopyOnWriteSet((Set) this.observersByEntityTypeId.get((Object) Integer.valueOf(i)), dataObserver);
    }

    @Override // io.objectbox.reactive.DataPublisher
    public void publishSingle(DataObserver<Class> dataObserver, @Nullable Object obj) {
        queuePublishRequestAndScheduleRun(dataObserver, obj != null ? new int[]{this.boxStore.getEntityTypeIdOrThrow((Class) obj)} : this.boxStore.getAllEntityTypeIds());
    }

    void publish(int[] iArr) {
        queuePublishRequestAndScheduleRun(null, iArr);
    }

    private void queuePublishRequestAndScheduleRun(@Nullable DataObserver<Class> dataObserver, int[] iArr) {
        synchronized (this.changesQueue) {
            this.changesQueue.add(new PublishRequest(dataObserver, iArr));
            if (!this.changePublisherRunning) {
                this.changePublisherRunning = true;
                this.boxStore.internalScheduleThread(this);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        PublishRequest publishRequestPollFirst;
        Collection collectionSingletonList;
        while (true) {
            try {
            } finally {
            }
            synchronized (this.changesQueue) {
                publishRequestPollFirst = this.changesQueue.pollFirst();
                if (publishRequestPollFirst == null) {
                    this.changePublisherRunning = false;
                    return;
                }
                this.changePublisherRunning = false;
            }
            for (int i : publishRequestPollFirst.entityTypeIds) {
                if (publishRequestPollFirst.observer != null) {
                    collectionSingletonList = Collections.singletonList(publishRequestPollFirst.observer);
                } else {
                    collectionSingletonList = this.observersByEntityTypeId.get((Object) Integer.valueOf(i));
                }
                if (collectionSingletonList != null && !collectionSingletonList.isEmpty()) {
                    Class<?> entityClassOrThrow = this.boxStore.getEntityClassOrThrow(i);
                    try {
                        Iterator it = collectionSingletonList.iterator();
                        while (it.hasNext()) {
                            ((DataObserver) it.next()).onData(entityClassOrThrow);
                        }
                    } catch (RuntimeException unused) {
                        handleObserverException(entityClassOrThrow);
                    }
                }
            }
        }
    }

    private void handleObserverException(Class cls) {
        RuntimeException runtimeException = new RuntimeException("Observer failed while processing data for " + cls + ". Consider using an ErrorObserver");
        runtimeException.printStackTrace();
        throw runtimeException;
    }
}
