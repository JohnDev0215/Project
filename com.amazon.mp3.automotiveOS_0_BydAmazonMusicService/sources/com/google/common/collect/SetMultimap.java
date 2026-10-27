package com.google.common.collect;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface SetMultimap<K, V> extends Multimap<K, V> {
    @Override // com.google.common.collect.Multimap
    Set<Map.Entry<K, V>> entries();

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    Set<V> get(K k);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    Set<V> removeAll(@CheckForNull Object obj);
}
