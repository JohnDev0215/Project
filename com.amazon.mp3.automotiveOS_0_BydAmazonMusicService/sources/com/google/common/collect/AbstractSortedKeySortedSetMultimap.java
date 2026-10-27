package com.google.common.collect;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractSortedKeySortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
    @Override // com.google.common.collect.AbstractSortedSetMultimap, com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public SortedMap<K, Collection<V>> asMap() {
        return (SortedMap) super.asMap();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public SortedSet<K> keySet() {
        return (SortedSet) super.keySet();
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap, com.google.common.collect.AbstractMultimap
    Set<K> createKeySet() {
        return createMaybeNavigableKeySet();
    }
}
