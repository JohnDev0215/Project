package org.greenrobot.essentials.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractMultimap<K, V, C extends Collection<V>> implements Map<K, C> {
    protected Map<K, C> map;

    protected abstract C createNewCollection();

    protected AbstractMultimap(Map<K, C> map) {
        this.map = map;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends C> map) {
        this.map.putAll(map);
    }

    @Override // java.util.Map
    public synchronized int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public synchronized boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public synchronized boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public synchronized boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public synchronized C get(Object obj) {
        return this.map.get(obj);
    }

    @Override // java.util.Map
    public synchronized C remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public synchronized void clear() {
        this.map.clear();
    }

    @Override // java.util.Map
    public synchronized Set<K> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public synchronized Collection<C> values() {
        return this.map.values();
    }

    @Override // java.util.Map
    public synchronized boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    public synchronized int hashCode() {
        return this.map.hashCode();
    }

    public synchronized int putElement(K k, V v) {
        C cCreateNewCollection;
        cCreateNewCollection = this.map.get(k);
        if (cCreateNewCollection == null) {
            cCreateNewCollection = createNewCollection();
            this.map.put(k, (C) cCreateNewCollection);
        }
        cCreateNewCollection.add(v);
        return cCreateNewCollection.size();
    }

    @Override // java.util.Map
    public synchronized C put(K k, C c) {
        return this.map.put(k, c);
    }

    @Override // java.util.Map
    public synchronized Set<Map.Entry<K, C>> entrySet() {
        return this.map.entrySet();
    }

    public synchronized boolean putElements(K k, Collection<V> collection) {
        C cCreateNewCollection;
        cCreateNewCollection = this.map.get(k);
        if (cCreateNewCollection == null) {
            cCreateNewCollection = createNewCollection();
            this.map.put(k, (C) cCreateNewCollection);
        }
        return cCreateNewCollection.addAll(collection);
    }

    public synchronized boolean removeElement(K k, V v) {
        C c = this.map.get(k);
        if (c == null) {
            return false;
        }
        boolean zRemove = c.remove(v);
        if (c.isEmpty()) {
            this.map.remove(k);
        }
        return zRemove;
    }

    public synchronized int countElements(K k) {
        C c = this.map.get(k);
        if (c == null) {
            return 0;
        }
        return c.size();
    }

    public synchronized int countElements() {
        int size;
        size = 0;
        Iterator<C> it = this.map.values().iterator();
        while (it.hasNext()) {
            size += it.next().size();
        }
        return size;
    }

    public synchronized boolean containsElement(K k, V v) {
        C c = this.map.get(k);
        if (c == null) {
            return false;
        }
        return c.contains(v);
    }

    public synchronized boolean containsElement(V v) {
        Iterator<C> it = this.map.values().iterator();
        while (it.hasNext()) {
            if (it.next().contains(v)) {
                return true;
            }
        }
        return false;
    }

    public synchronized C valuesElements() {
        C c;
        c = (C) createNewCollection();
        Iterator<C> it = this.map.values().iterator();
        while (it.hasNext()) {
            c.addAll(it.next());
        }
        return c;
    }
}
