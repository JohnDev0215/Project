package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
    private transient Map<K, V> delegate;

    @CheckForNull
    private transient Set<Map.Entry<K, V>> entrySet;
    transient AbstractBiMap<V, K> inverse;

    @CheckForNull
    private transient Set<K> keySet;

    @CheckForNull
    private transient Set<V> valueSet;

    K checkKey(K k) {
        return k;
    }

    V checkValue(V v) {
        return v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<K, V> delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public boolean containsValue(@CheckForNull Object obj) {
        return this.inverse.containsKey(obj);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map, com.google.common.collect.BiMap
    @CheckForNull
    public V put(K k, V v) {
        return putInBothMaps(k, v, false);
    }

    @CheckForNull
    private V putInBothMaps(K k, V v, boolean z) {
        checkKey(k);
        checkValue(v);
        boolean zContainsKey = containsKey(k);
        if (zContainsKey && Objects.equal(v, get(k))) {
            return v;
        }
        if (z) {
            inverse().remove(v);
        } else {
            Preconditions.checkArgument(!containsValue(v), "value already present: %s", v);
        }
        V vPut = this.delegate.put(k, v);
        updateInverseMap(k, zContainsKey, vPut, v);
        return vPut;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateInverseMap(K k, boolean z, @CheckForNull V v, V v2) {
        if (z) {
            removeFromInverseMap(NullnessCasts.uncheckedCastNullableTToT(v));
        }
        this.inverse.delegate.put(v2, k);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        if (containsKey(obj)) {
            return removeFromBothMaps(obj);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V removeFromBothMaps(@CheckForNull Object obj) {
        V v = (V) NullnessCasts.uncheckedCastNullableTToT(this.delegate.remove(obj));
        removeFromInverseMap(v);
        return v;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFromInverseMap(V v) {
        this.inverse.delegate.remove(v);
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public void clear() {
        this.delegate.clear();
        this.inverse.delegate.clear();
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        return this.inverse;
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet = new KeySet();
        this.keySet = keySet;
        return keySet;
    }

    private class KeySet extends ForwardingSet<K> {
        private KeySet() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<K> delegate() {
            return AbstractBiMap.this.delegate.keySet();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (!contains(obj)) {
                return false;
            }
            AbstractBiMap.this.removeFromBothMaps(obj);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            return standardRetainAll(collection);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return Maps.keyIterator(AbstractBiMap.this.entrySet().iterator());
        }
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set != null) {
            return set;
        }
        ValueSet valueSet = new ValueSet();
        this.valueSet = valueSet;
        return valueSet;
    }

    private class ValueSet extends ForwardingSet<V> {
        final Set<V> valuesDelegate;

        private ValueSet() {
            this.valuesDelegate = AbstractBiMap.this.inverse.keySet();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<V> delegate() {
            return this.valuesDelegate;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return Maps.valueIterator(AbstractBiMap.this.entrySet().iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet = new EntrySet();
        this.entrySet = entrySet;
        return entrySet;
    }

    class BiMapEntry extends ForwardingMapEntry<K, V> {
        private final Map.Entry<K, V> delegate;

        BiMapEntry(Map.Entry<K, V> entry) {
            this.delegate = entry;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingObject
        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            AbstractBiMap.this.checkValue(v);
            Preconditions.checkState(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
            if (Objects.equal(v, getValue())) {
                return v;
            }
            Preconditions.checkArgument(!AbstractBiMap.this.containsValue(v), "value already present: %s", v);
            V value = this.delegate.setValue(v);
            Preconditions.checkState(Objects.equal(v, AbstractBiMap.this.get(getKey())), "entry no longer in map");
            AbstractBiMap.this.updateInverseMap(getKey(), true, value, v);
            return value;
        }
    }

    Iterator<Map.Entry<K, V>> entrySetIterator() {
        final Iterator<Map.Entry<K, V>> it = this.delegate.entrySet().iterator();
        return new Iterator<Map.Entry<K, V>>() { // from class: com.google.common.collect.AbstractBiMap.1

            @CheckForNull
            Map.Entry<K, V> entry;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                Map.Entry<K, V> entry = (Map.Entry) it.next();
                this.entry = entry;
                return new BiMapEntry(entry);
            }

            @Override // java.util.Iterator
            public void remove() {
                Map.Entry<K, V> entry = this.entry;
                if (entry == null) {
                    throw new IllegalStateException("no calls to next() since the last call to remove()");
                }
                V value = entry.getValue();
                it.remove();
                AbstractBiMap.this.removeFromInverseMap(value);
                this.entry = null;
            }
        };
    }

    private class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
        final Set<Map.Entry<K, V>> esDelegate;

        private EntrySet() {
            this.esDelegate = AbstractBiMap.this.delegate.entrySet();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Set<Map.Entry<K, V>> delegate() {
            return this.esDelegate;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            AbstractBiMap.this.clear();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (!this.esDelegate.contains(obj) || !(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            ((AbstractBiMap) AbstractBiMap.this.inverse).delegate.remove(entry.getValue());
            this.esDelegate.remove(entry);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractBiMap.this.entrySetIterator();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return Maps.containsEntryImpl(delegate(), obj);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return standardContainsAll(collection);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return standardRemoveAll(collection);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            return standardRetainAll(collection);
        }
    }
}
