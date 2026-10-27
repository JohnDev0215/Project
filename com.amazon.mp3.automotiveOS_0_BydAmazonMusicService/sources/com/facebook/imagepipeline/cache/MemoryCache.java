package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public interface MemoryCache<K, V> extends MemoryTrimmable {

    public interface CacheTrimStrategy {
    }

    @Nullable
    CloseableReference<V> cache(K key, CloseableReference<V> value);

    boolean contains(Predicate<K> predicate);

    @Nullable
    CloseableReference<V> get(K key);

    int getCount();

    int getSizeInBytes();

    void probe(K key);

    int removeAll(Predicate<K> predicate);
}
