package com.facebook.imagepipeline.memory;

/* JADX INFO: loaded from: classes5.dex */
public interface PoolStatsTracker {
    void onAlloc(int size);

    void onFree(int sizeInBytes);

    void onHardCapReached();

    void onSoftCapReached();

    void onValueRelease(int sizeInBytes);

    void onValueReuse(int bucketedSize);

    void setBasePool(BasePool basePool);
}
