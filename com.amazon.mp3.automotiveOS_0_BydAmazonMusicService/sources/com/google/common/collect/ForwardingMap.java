package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ForwardingMap<K, V> extends ForwardingObject implements Map<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Map<K, V> delegate();

    protected ForwardingMap() {
    }

    @Override // java.util.Map
    public int size() {
        return delegate().size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        return delegate().remove(obj);
    }

    public void clear() {
        delegate().clear();
    }

    @Override // java.util.Map
    public boolean containsKey(@CheckForNull Object obj) {
        return delegate().containsKey(obj);
    }

    public boolean containsValue(@CheckForNull Object obj) {
        return delegate().containsValue(obj);
    }

    @Override // java.util.Map
    @CheckForNull
    public V get(@CheckForNull Object obj) {
        return delegate().get(obj);
    }

    @CheckForNull
    public V put(K k, V v) {
        return delegate().put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    public Set<K> keySet() {
        return delegate().keySet();
    }

    public Collection<V> values() {
        return delegate().values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    @Override // java.util.Map
    public boolean equals(@CheckForNull Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return delegate().hashCode();
    }
}
