package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes3.dex */
@ThreadSafe
public class LruCountingMemoryCache<K, V> implements CountingMemoryCache<K, V>, MemoryCache<K, V> {
    private final MemoryCache.CacheTrimStrategy mCacheTrimStrategy;

    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mCachedEntries;

    @Nullable
    private final CountingMemoryCache.EntryStateObserver<K> mEntryStateObserver;

    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mExclusiveEntries;

    @GuardedBy("this")
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    private final ValueDescriptor<V> mValueDescriptor;

    @GuardedBy("this")
    @VisibleForTesting
    final Map<Bitmap, Object> mOtherEntries = new WeakHashMap();

    @GuardedBy("this")
    private long mLastCacheParamsCheck = SystemClock.uptimeMillis();

    public LruCountingMemoryCache(ValueDescriptor<V> valueDescriptor, MemoryCache.CacheTrimStrategy cacheTrimStrategy, Supplier<MemoryCacheParams> memoryCacheParamsSupplier, @Nullable CountingMemoryCache.EntryStateObserver<K> entryStateObserver) {
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = memoryCacheParamsSupplier;
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(memoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
        this.mEntryStateObserver = entryStateObserver;
    }

    private ValueDescriptor<CountingMemoryCache.Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> evictableValueDescriptor) {
        return new ValueDescriptor<CountingMemoryCache.Entry<K, V>>() { // from class: com.facebook.imagepipeline.cache.LruCountingMemoryCache.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public int getSizeInBytes(CountingMemoryCache.Entry<K, V> entry) {
                return evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
            }
        };
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> cache(final K key, final CloseableReference<V> valueRef) {
        return cache(key, valueRef, this.mEntryStateObserver);
    }

    @Nullable
    public CloseableReference<V> cache(final K key, final CloseableReference<V> valueRef, @Nullable final CountingMemoryCache.EntryStateObserver<K> observer) {
        CountingMemoryCache.Entry<K, V> entryRemove;
        CloseableReference<V> closeableReferenceNewClientReference;
        CloseableReference<V> closeableReferenceReferenceToClose;
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(valueRef);
        maybeUpdateCacheParams();
        synchronized (this) {
            entryRemove = this.mExclusiveEntries.remove(key);
            CountingMemoryCache.Entry<K, V> entryRemove2 = this.mCachedEntries.remove(key);
            closeableReferenceNewClientReference = null;
            if (entryRemove2 != null) {
                makeOrphan(entryRemove2);
                closeableReferenceReferenceToClose = referenceToClose(entryRemove2);
            } else {
                closeableReferenceReferenceToClose = null;
            }
            if (canCacheNewValue(valueRef.get())) {
                CountingMemoryCache.Entry<K, V> entryOf = CountingMemoryCache.Entry.of(key, valueRef, observer);
                this.mCachedEntries.put(key, entryOf);
                closeableReferenceNewClientReference = newClientReference(entryOf);
            }
        }
        CloseableReference.closeSafely(closeableReferenceReferenceToClose);
        maybeNotifyExclusiveEntryRemoval(entryRemove);
        maybeEvictEntries();
        return closeableReferenceNewClientReference;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized boolean canCacheNewValue(V r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.imagepipeline.cache.ValueDescriptor<V> r0 = r3.mValueDescriptor     // Catch: java.lang.Throwable -> L28
            int r4 = r0.getSizeInBytes(r4)     // Catch: java.lang.Throwable -> L28
            com.facebook.imagepipeline.cache.MemoryCacheParams r0 = r3.mMemoryCacheParams     // Catch: java.lang.Throwable -> L28
            int r0 = r0.maxCacheEntrySize     // Catch: java.lang.Throwable -> L28
            r1 = 1
            if (r4 > r0) goto L25
            int r0 = r3.getInUseCount()     // Catch: java.lang.Throwable -> L28
            com.facebook.imagepipeline.cache.MemoryCacheParams r2 = r3.mMemoryCacheParams     // Catch: java.lang.Throwable -> L28
            int r2 = r2.maxCacheEntries     // Catch: java.lang.Throwable -> L28
            int r2 = r2 - r1
            if (r0 > r2) goto L25
            int r0 = r3.getInUseSizeInBytes()     // Catch: java.lang.Throwable -> L28
            com.facebook.imagepipeline.cache.MemoryCacheParams r2 = r3.mMemoryCacheParams     // Catch: java.lang.Throwable -> L28
            int r2 = r2.maxCacheSize     // Catch: java.lang.Throwable -> L28
            int r2 = r2 - r4
            if (r0 > r2) goto L25
            goto L26
        L25:
            r1 = 0
        L26:
            monitor-exit(r3)
            return r1
        L28:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.canCacheNewValue(java.lang.Object):boolean");
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> get(final K key) {
        CountingMemoryCache.Entry<K, V> entryRemove;
        CloseableReference<V> closeableReferenceNewClientReference;
        Preconditions.checkNotNull(key);
        synchronized (this) {
            entryRemove = this.mExclusiveEntries.remove(key);
            CountingMemoryCache.Entry<K, V> entry = this.mCachedEntries.get(key);
            closeableReferenceNewClientReference = entry != null ? newClientReference(entry) : null;
        }
        maybeNotifyExclusiveEntryRemoval(entryRemove);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return closeableReferenceNewClientReference;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public void probe(final K key) {
        Preconditions.checkNotNull(key);
        synchronized (this) {
            CountingMemoryCache.Entry<K, V> entryRemove = this.mExclusiveEntries.remove(key);
            if (entryRemove != null) {
                this.mExclusiveEntries.put(key, entryRemove);
            }
        }
    }

    private synchronized CloseableReference<V> newClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        increaseClientCount(entry);
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() { // from class: com.facebook.imagepipeline.cache.LruCountingMemoryCache.2
            @Override // com.facebook.common.references.ResourceReleaser
            public void release(V unused) {
                LruCountingMemoryCache.this.releaseClientReference(entry);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        boolean zMaybeAddToExclusives;
        CloseableReference<V> closeableReferenceReferenceToClose;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            zMaybeAddToExclusives = maybeAddToExclusives(entry);
            closeableReferenceReferenceToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely(closeableReferenceReferenceToClose);
        if (!zMaybeAddToExclusives) {
            entry = null;
        }
        maybeNotifyExclusiveEntryInsertion(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    private synchronized boolean maybeAddToExclusives(CountingMemoryCache.Entry<K, V> entry) {
        if (entry.isOrphan || entry.clientCount != 0) {
            return false;
        }
        this.mExclusiveEntries.put(entry.key, entry);
        return true;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        ArrayList<CountingMemoryCache.Entry<K, V>> arrayListRemoveAll;
        ArrayList<CountingMemoryCache.Entry<K, V>> arrayListRemoveAll2;
        synchronized (this) {
            arrayListRemoveAll = this.mExclusiveEntries.removeAll(predicate);
            arrayListRemoveAll2 = this.mCachedEntries.removeAll(predicate);
            makeOrphans(arrayListRemoveAll2);
        }
        maybeClose(arrayListRemoveAll2);
        maybeNotifyExclusiveEntryRemoval(arrayListRemoveAll);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return arrayListRemoveAll2.size();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + this.mMemoryCacheParams.paramsCheckIntervalMs > SystemClock.uptimeMillis()) {
            return;
        }
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
    }

    public void maybeEvictEntries() {
        ArrayList<CountingMemoryCache.Entry<K, V>> arrayListTrimExclusivelyOwnedEntries;
        synchronized (this) {
            MemoryCacheParams memoryCacheParams = this.mMemoryCacheParams;
            int iMin = Math.min(memoryCacheParams.maxEvictionQueueEntries, memoryCacheParams.maxCacheEntries - getInUseCount());
            MemoryCacheParams memoryCacheParams2 = this.mMemoryCacheParams;
            arrayListTrimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(iMin, Math.min(memoryCacheParams2.maxEvictionQueueSize, memoryCacheParams2.maxCacheSize - getInUseSizeInBytes()));
            makeOrphans(arrayListTrimExclusivelyOwnedEntries);
        }
        maybeClose(arrayListTrimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(arrayListTrimExclusivelyOwnedEntries);
    }

    @Nullable
    private synchronized ArrayList<CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries(int i, int i2) {
        int iMax = Math.max(i, 0);
        int iMax2 = Math.max(i2, 0);
        if (this.mExclusiveEntries.getCount() <= iMax && this.mExclusiveEntries.getSizeInBytes() <= iMax2) {
            return null;
        }
        ArrayList<CountingMemoryCache.Entry<K, V>> arrayList = new ArrayList<>();
        while (true) {
            if (this.mExclusiveEntries.getCount() <= iMax && this.mExclusiveEntries.getSizeInBytes() <= iMax2) {
                return arrayList;
            }
            K firstKey = this.mExclusiveEntries.getFirstKey();
            if (firstKey == null) {
                throw new IllegalStateException(String.format("key is null, but exclusiveEntries count: %d, size: %d", Integer.valueOf(this.mExclusiveEntries.getCount()), Integer.valueOf(this.mExclusiveEntries.getSizeInBytes())));
            }
            this.mExclusiveEntries.remove(firstKey);
            arrayList.add(this.mCachedEntries.remove(firstKey));
        }
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it = oldEntries.iterator();
            while (it.hasNext()) {
                CloseableReference.closeSafely(referenceToClose(it.next()));
            }
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> entries) {
        if (entries != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it = entries.iterator();
            while (it.hasNext()) {
                maybeNotifyExclusiveEntryRemoval(it.next());
            }
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        CountingMemoryCache.EntryStateObserver<K> entryStateObserver;
        if (entry == null || (entryStateObserver = entry.observer) == null) {
            return;
        }
        entryStateObserver.onExclusivityChanged(entry.key, false);
    }

    private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        CountingMemoryCache.EntryStateObserver<K> entryStateObserver;
        if (entry == null || (entryStateObserver = entry.observer) == null) {
            return;
        }
        entryStateObserver.onExclusivityChanged(entry.key, true);
    }

    private synchronized void makeOrphans(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it = oldEntries.iterator();
            while (it.hasNext()) {
                makeOrphan(it.next());
            }
        }
    }

    private synchronized void makeOrphan(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.isOrphan = true;
    }

    private synchronized void increaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
    }

    private synchronized void decreaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    @Nullable
    private synchronized CloseableReference<V> referenceToClose(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        return (entry.isOrphan && entry.clientCount == 0) ? entry.valueRef : null;
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache, com.facebook.imagepipeline.cache.MemoryCache
    public synchronized int getCount() {
        return this.mCachedEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache, com.facebook.imagepipeline.cache.MemoryCache
    public synchronized int getSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes();
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueCount() {
        return this.mExclusiveEntries.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.CountingMemoryCache
    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mExclusiveEntries.getSizeInBytes();
    }
}
