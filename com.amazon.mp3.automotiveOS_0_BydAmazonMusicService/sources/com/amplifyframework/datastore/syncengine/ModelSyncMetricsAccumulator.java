package com.amplifyframework.datastore.syncengine;

import com.amplifyframework.datastore.events.ModelSyncedEvent;
import com.amplifyframework.datastore.storage.StorageItemChange;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes5.dex */
final class ModelSyncMetricsAccumulator {
    private final String modelClassName;
    private final Map<StorageItemChange.Type, AtomicInteger> syncMetrics;

    ModelSyncMetricsAccumulator(String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.syncMetrics = concurrentHashMap;
        concurrentHashMap.put(StorageItemChange.Type.CREATE, new AtomicInteger(0));
        concurrentHashMap.put(StorageItemChange.Type.UPDATE, new AtomicInteger(0));
        concurrentHashMap.put(StorageItemChange.Type.DELETE, new AtomicInteger(0));
        this.modelClassName = str;
    }

    public ModelSyncedEvent toModelSyncedEvent(SyncType syncType) {
        return new ModelSyncedEvent(this.modelClassName, SyncType.BASE.equals(syncType), this.syncMetrics.get(StorageItemChange.Type.CREATE).get(), this.syncMetrics.get(StorageItemChange.Type.UPDATE).get(), this.syncMetrics.get(StorageItemChange.Type.DELETE).get());
    }

    public void increment(StorageItemChange.Type type) {
        this.syncMetrics.get(type).incrementAndGet();
    }
}
