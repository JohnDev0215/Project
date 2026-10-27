package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG;
    private boolean mAllowNewBuckets;

    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;

    @GuardedBy("this")
    @VisibleForTesting
    final Counter mFree;
    private boolean mIgnoreHardCap;

    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;

    @GuardedBy("this")
    @VisibleForTesting
    final Counter mUsed;

    protected abstract V alloc(int bucketedSize);

    @VisibleForTesting
    protected abstract void free(V value);

    protected abstract int getBucketedSize(int requestSize);

    protected abstract int getBucketedSizeForValue(V value);

    protected abstract int getSizeInBytes(int bucketedSize);

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.TAG = getClass();
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        PoolParams poolParams2 = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolParams = poolParams2;
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        if (poolParams2.fixBucketsReinitialization) {
            initBuckets();
        } else {
            legacyInitBuckets(new SparseIntArray(0));
        }
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker, boolean ignoreHardCap) {
        this(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        this.mIgnoreHardCap = ignoreHardCap;
    }

    protected void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    @Nullable
    protected synchronized V getValue(Bucket<V> bucket) {
        return bucket.get();
    }

    @Override // com.facebook.common.memory.Pool
    public V get(int size) throws Throwable {
        V value;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(size);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null && (value = getValue(bucket)) != null) {
                Preconditions.checkState(this.mInUseValues.add(value));
                int bucketedSizeForValue = getBucketedSizeForValue(value);
                int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
                this.mUsed.increment(sizeInBytes);
                this.mFree.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSizeForValue));
                }
                return value;
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (!canAllocate(sizeInBytes2)) {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
            }
            this.mUsed.increment(sizeInBytes2);
            if (bucket != null) {
                bucket.incrementInUseCount();
            }
            V vAlloc = null;
            try {
                vAlloc = alloc(bucketedSize);
            } catch (Throwable th) {
                synchronized (this) {
                    this.mUsed.decrement(sizeInBytes2);
                    Bucket<V> bucket2 = getBucket(bucketedSize);
                    if (bucket2 != null) {
                        bucket2.decrementInUseCount();
                    }
                    Throwables.propagateIfPossible(th);
                }
            }
            synchronized (this) {
                Preconditions.checkState(this.mInUseValues.add(vAlloc));
                trimToSoftCap();
                this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(vAlloc)), Integer.valueOf(bucketedSize));
                }
            }
            return vAlloc;
        }
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(V value) {
        Preconditions.checkNotNull(value);
        int bucketedSizeForValue = getBucketedSizeForValue(value);
        int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
        synchronized (this) {
            Bucket<V> bucketIfPresent = getBucketIfPresent(bucketedSizeForValue);
            if (!this.mInUseValues.remove(value)) {
                FLog.e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSizeForValue));
                free(value);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else if (bucketIfPresent == null || bucketIfPresent.isMaxLengthExceeded() || isMaxSizeSoftCapExceeded() || !isReusable(value)) {
                if (bucketIfPresent != null) {
                    bucketIfPresent.decrementInUseCount();
                }
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSizeForValue));
                }
                free(value);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                bucketIfPresent.release(value);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSizeForValue));
                }
            }
            logStats();
        }
    }

    protected boolean isReusable(V value) {
        Preconditions.checkNotNull(value);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        Preconditions.checkState(!isMaxSizeSoftCapExceeded() || this.mFree.mNumBytes == 0);
    }

    private synchronized void legacyInitBuckets(SparseIntArray inUseCounts) {
        Preconditions.checkNotNull(inUseCounts);
        this.mBuckets.clear();
        SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
        if (sparseIntArray != null) {
            for (int i = 0; i < sparseIntArray.size(); i++) {
                int iKeyAt = sparseIntArray.keyAt(i);
                this.mBuckets.put(iKeyAt, new Bucket<>(getSizeInBytes(iKeyAt), sparseIntArray.valueAt(i), inUseCounts.get(iKeyAt, 0), this.mPoolParams.fixBucketsReinitialization));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private synchronized void initBuckets() {
        SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
        if (sparseIntArray != null) {
            fillBuckets(sparseIntArray);
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private void fillBuckets(SparseIntArray bucketSizes) {
        this.mBuckets.clear();
        for (int i = 0; i < bucketSizes.size(); i++) {
            int iKeyAt = bucketSizes.keyAt(i);
            this.mBuckets.put(iKeyAt, new Bucket<>(getSizeInBytes(iKeyAt), bucketSizes.valueAt(i), 0, this.mPoolParams.fixBucketsReinitialization));
        }
    }

    @VisibleForTesting
    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @VisibleForTesting
    synchronized void trimToSize(int targetSize) {
        int i = this.mUsed.mNumBytes;
        int i2 = this.mFree.mNumBytes;
        int iMin = Math.min((i + i2) - targetSize, i2);
        if (iMin <= 0) {
            return;
        }
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(iMin));
        }
        logStats();
        for (int i3 = 0; i3 < this.mBuckets.size() && iMin > 0; i3++) {
            Bucket bucket = (Bucket) Preconditions.checkNotNull(this.mBuckets.valueAt(i3));
            while (iMin > 0) {
                Object objPop = bucket.pop();
                if (objPop == null) {
                    break;
                }
                free(objPop);
                int i4 = bucket.mItemSize;
                iMin -= i4;
                this.mFree.decrement(i4);
            }
        }
        logStats();
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
        }
    }

    @Nullable
    private synchronized Bucket<V> getBucketIfPresent(int bucketedSize) {
        return this.mBuckets.get(bucketedSize);
    }

    @Nullable
    @VisibleForTesting
    synchronized Bucket<V> getBucket(int bucketedSize) {
        Bucket<V> bucket = this.mBuckets.get(bucketedSize);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.TAG, "creating new bucket %s", Integer.valueOf(bucketedSize));
            }
            Bucket<V> bucketNewBucket = newBucket(bucketedSize);
            this.mBuckets.put(bucketedSize, bucketNewBucket);
            return bucketNewBucket;
        }
        return bucket;
    }

    Bucket<V> newBucket(int bucketedSize) {
        return new Bucket<>(getSizeInBytes(bucketedSize), Integer.MAX_VALUE, 0, this.mPoolParams.fixBucketsReinitialization);
    }

    @VisibleForTesting
    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    @VisibleForTesting
    synchronized boolean canAllocate(int sizeInBytes) {
        if (this.mIgnoreHardCap) {
            return true;
        }
        PoolParams poolParams = this.mPoolParams;
        int i = poolParams.maxSizeHardCap;
        int i2 = this.mUsed.mNumBytes;
        if (sizeInBytes > i - i2) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i3 = poolParams.maxSizeSoftCap;
        if (sizeInBytes > i3 - (i2 + this.mFree.mNumBytes)) {
            trimToSize(i3 - sizeInBytes);
        }
        if (sizeInBytes <= i - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            return true;
        }
        this.mPoolStatsTracker.onHardCapReached();
        return false;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    @NotThreadSafe
    @VisibleForTesting
    static class Counter {
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int numBytes) {
            this.mCount++;
            this.mNumBytes += numBytes;
        }

        public void decrement(int numBytes) {
            int i;
            int i2 = this.mNumBytes;
            if (i2 >= numBytes && (i = this.mCount) > 0) {
                this.mCount = i - 1;
                this.mNumBytes = i2 - numBytes;
            } else {
                FLog.wtf("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(numBytes), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
            }
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object size) {
            super("Invalid size: " + size.toString());
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int hardCap, int usedBytes, int freeBytes, int allocSize) {
            super("Pool hard cap violation? Hard cap = " + hardCap + " Used size = " + usedBytes + " Free size = " + freeBytes + " Request size = " + allocSize);
        }
    }
}
