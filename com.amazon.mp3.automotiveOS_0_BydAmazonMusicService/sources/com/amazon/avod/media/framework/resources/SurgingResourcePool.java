package com.amazon.avod.media.framework.resources;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class SurgingResourcePool<T> implements ResourcePool<T> {
    private final List<T> mClaimedResources;
    private final List<T> mFreeResources;
    private final Object mPoolMutex = new Object();
    private final int mPoolSize;
    private final Provider<T> mResourceProvider;

    public SurgingResourcePool(int i, Provider<T> provider) {
        Preconditions.checkState(i >= 0, "initialCapacity cannot be negative");
        this.mResourceProvider = provider;
        this.mFreeResources = new LinkedList();
        this.mClaimedResources = new LinkedList();
        this.mPoolSize = i;
        DLog.devf("Creating surging resource pool with initial capacity of %d objects.", Integer.valueOf(i));
        for (int i2 = 0; i2 < this.mPoolSize; i2++) {
            this.mFreeResources.add(this.mResourceProvider.get());
        }
    }

    @Override // com.amazon.avod.media.framework.resources.ResourcePool
    public T requestResource() {
        T tRemove;
        synchronized (this.mPoolMutex) {
            if (this.mFreeResources.isEmpty()) {
                DLog.devf("All resources claimed (%s), adding another.", Integer.valueOf(this.mClaimedResources.size()));
                this.mFreeResources.add(this.mResourceProvider.get());
            }
            tRemove = this.mFreeResources.remove(0);
            this.mClaimedResources.add(tRemove);
        }
        return tRemove;
    }

    @Override // com.amazon.avod.media.framework.resources.ResourcePool
    public void releaseResource(T t) {
        synchronized (this.mPoolMutex) {
            if (!this.mClaimedResources.contains(t)) {
                throw new IllegalStateException("Tried to release an unclaimed resource.");
            }
            this.mClaimedResources.remove(t);
            int size = this.mFreeResources.size();
            int i = this.mPoolSize;
            if (size < i) {
                this.mFreeResources.add(t);
            } else {
                DLog.devf("Already have %d free resources, de-allocating one.", Integer.valueOf(i));
            }
        }
    }

    int getFreeCapacity() {
        int size;
        synchronized (this.mPoolMutex) {
            size = this.mFreeResources.size();
        }
        return size;
    }

    int getClaimedCapacity() {
        int size;
        synchronized (this.mPoolMutex) {
            size = this.mClaimedResources.size();
        }
        return size;
    }
}
