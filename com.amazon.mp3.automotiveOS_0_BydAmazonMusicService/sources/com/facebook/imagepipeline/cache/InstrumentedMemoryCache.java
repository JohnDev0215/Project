package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class InstrumentedMemoryCache<K, V> implements MemoryCache<K, V> {
    private final MemoryCache<K, V> mDelegate;
    private final MemoryCacheTracker mTracker;

    public InstrumentedMemoryCache(MemoryCache<K, V> delegate, MemoryCacheTracker tracker) {
        this.mDelegate = delegate;
        this.mTracker = tracker;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> get(K key) {
        CloseableReference<V> closeableReference = this.mDelegate.get(key);
        if (closeableReference == null) {
            this.mTracker.onCacheMiss(key);
        } else {
            this.mTracker.onCacheHit(key);
        }
        return closeableReference;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public void probe(K key) {
        this.mDelegate.probe(key);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    @Nullable
    public CloseableReference<V> cache(K key, CloseableReference<V> value) {
        this.mTracker.onCachePut(key);
        return this.mDelegate.cache(key, value);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        return this.mDelegate.removeAll(predicate);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public boolean contains(Predicate<K> predicate) {
        return this.mDelegate.contains(predicate);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int getCount() {
        return this.mDelegate.getCount();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int getSizeInBytes() {
        return this.mDelegate.getSizeInBytes();
    }
}
