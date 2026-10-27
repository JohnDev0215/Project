package com.google.common.cache;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* JADX INFO: loaded from: classes6.dex */
public abstract class CacheLoader<K, V> {
    public abstract V load(K k) throws Exception;

    protected CacheLoader() {
    }

    public ListenableFuture<V> reload(K k, V v) throws Exception {
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(v);
        return Futures.immediateFuture(load(k));
    }

    public static final class InvalidCacheLoadException extends RuntimeException {
        public InvalidCacheLoadException(String str) {
            super(str);
        }
    }
}
