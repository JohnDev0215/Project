package java9.util;

import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class KeyValueHolder<K, V> implements Map.Entry<K, V> {
    final K key;
    final V value;

    KeyValueHolder(K k, V v) {
        this.key = (K) Objects.requireNonNull(k);
        this.value = (V) Objects.requireNonNull(v);
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        throw new UnsupportedOperationException("not supported");
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return this.key.hashCode() ^ this.value.hashCode();
    }

    public String toString() {
        return this.key + "=" + this.value;
    }
}
