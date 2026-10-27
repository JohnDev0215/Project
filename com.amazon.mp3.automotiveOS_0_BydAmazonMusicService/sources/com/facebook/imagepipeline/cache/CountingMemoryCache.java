package com.facebook.imagepipeline.cache;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public interface CountingMemoryCache<K, V> extends MemoryCache<K, V>, MemoryTrimmable {

    public interface EntryStateObserver<K> {
        void onExclusivityChanged(K key, boolean isExclusive);
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    /* synthetic */ int getCount();

    int getEvictionQueueCount();

    int getEvictionQueueSizeInBytes();

    int getInUseSizeInBytes();

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    /* synthetic */ int getSizeInBytes();

    @VisibleForTesting
    public static class Entry<K, V> {
        public final K key;

        @Nullable
        public final EntryStateObserver<K> observer;
        public final CloseableReference<V> valueRef;
        public int clientCount = 0;
        public boolean isOrphan = false;
        public int accessCount = 0;

        private Entry(K k, CloseableReference<V> closeableReference, @Nullable EntryStateObserver<K> entryStateObserver) {
            this.key = (K) Preconditions.checkNotNull(k);
            this.valueRef = (CloseableReference) Preconditions.checkNotNull(CloseableReference.cloneOrNull(closeableReference));
            this.observer = entryStateObserver;
        }

        @VisibleForTesting
        public static <K, V> Entry<K, V> of(final K key, final CloseableReference<V> valueRef, @Nullable final EntryStateObserver<K> observer) {
            return new Entry<>(key, valueRef, observer);
        }
    }
}
