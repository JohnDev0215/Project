package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface Multimap<K, V> {
    Map<K, Collection<V>> asMap();

    void clear();

    boolean containsEntry(@CheckForNull Object obj, @CheckForNull Object obj2);

    boolean containsKey(@CheckForNull Object obj);

    Collection<Map.Entry<K, V>> entries();

    boolean equals(@CheckForNull Object obj);

    Collection<V> get(K k);

    int hashCode();

    boolean isEmpty();

    Set<K> keySet();

    boolean put(K k, V v);

    boolean remove(@CheckForNull Object obj, @CheckForNull Object obj2);

    Collection<V> removeAll(@CheckForNull Object obj);

    int size();

    Collection<V> values();
}
