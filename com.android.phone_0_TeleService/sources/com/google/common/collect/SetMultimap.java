package com.google.common.collect;

import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface SetMultimap<K, V> extends Multimap<K, V> {
    @Override // com.google.common.collect.Multimap
    Set<V> get(K k);
}
