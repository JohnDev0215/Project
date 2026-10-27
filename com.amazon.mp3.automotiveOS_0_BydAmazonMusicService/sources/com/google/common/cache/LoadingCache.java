package com.google.common.cache;

import com.google.common.base.Function;

/* JADX INFO: loaded from: classes6.dex */
public interface LoadingCache<K, V> extends Cache<K, V>, Function<K, V> {
    V getUnchecked(K k);
}
