package com.google.common.collect;

import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Map.Entry<K, V> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    public abstract Map.Entry<K, V> delegate();

    protected ForwardingMapEntry() {
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return delegate().getKey();
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return delegate().getValue();
    }

    public V setValue(V v) {
        return delegate().setValue(v);
    }

    @Override // java.util.Map.Entry
    public boolean equals(@CheckForNull Object obj) {
        return delegate().equals(obj);
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return delegate().hashCode();
    }
}
