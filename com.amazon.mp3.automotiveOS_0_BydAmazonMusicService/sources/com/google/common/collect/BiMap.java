package com.google.common.collect;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface BiMap<K, V> extends Map<K, V> {
    BiMap<V, K> inverse();

    @CheckForNull
    V put(K k, V v);

    @Override // java.util.Map
    Set<V> values();
}
