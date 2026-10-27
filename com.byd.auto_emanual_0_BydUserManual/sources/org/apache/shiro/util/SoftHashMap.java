package org.apache.shiro.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class SoftHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_RETENTION_SIZE = 100;
    private final int RETENTION_SIZE;
    private final Map<K, SoftValue<V, K>> map;
    private final ReferenceQueue<? super V> queue;
    private final Queue<V> strongReferences;
    private final ReentrantLock strongReferencesLock;

    public SoftHashMap() {
        this(100);
    }

    public SoftHashMap(int i) {
        this.RETENTION_SIZE = Math.max(0, i);
        this.queue = new ReferenceQueue<>();
        this.strongReferencesLock = new ReentrantLock();
        this.map = new ConcurrentHashMap();
        this.strongReferences = new ConcurrentLinkedQueue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SoftHashMap(Map<K, V> map) {
        this(100);
        putAll(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SoftHashMap(Map<K, V> map, int i) {
        this(i);
        putAll(map);
    }

    @Override // java.util.Map
    public V get(Object obj) {
        processQueue();
        SoftValue<V, K> softValue = this.map.get(obj);
        if (softValue == null) {
            return null;
        }
        V v = softValue.get();
        if (v == null) {
            this.map.remove(obj);
            return v;
        }
        addToStrongReferences(v);
        return v;
    }

    private void addToStrongReferences(V v) {
        this.strongReferencesLock.lock();
        try {
            this.strongReferences.add(v);
            trimStrongReferencesIfNecessary();
        } finally {
            this.strongReferencesLock.unlock();
        }
    }

    private void trimStrongReferencesIfNecessary() {
        while (this.strongReferences.size() > this.RETENTION_SIZE) {
            this.strongReferences.poll();
        }
    }

    private void processQueue() {
        while (true) {
            SoftValue softValue = (SoftValue) this.queue.poll();
            if (softValue == null) {
                return;
            } else {
                this.map.remove(softValue.key);
            }
        }
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        processQueue();
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        processQueue();
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        processQueue();
        Collection<V> collectionValues = values();
        return collectionValues != null && collectionValues.contains(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null || map.isEmpty()) {
            processQueue();
            return;
        }
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        processQueue();
        return this.map.keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        processQueue();
        Set<K> setKeySet = this.map.keySet();
        if (setKeySet.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        ArrayList arrayList = new ArrayList(setKeySet.size());
        Iterator<K> it = setKeySet.iterator();
        while (it.hasNext()) {
            V v = get(it.next());
            if (v != null) {
                arrayList.add(v);
            }
        }
        return arrayList;
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        processQueue();
        SoftValue<V, K> softValuePut = this.map.put(k, new SoftValue<>(v, k, this.queue));
        addToStrongReferences(v);
        if (softValuePut != null) {
            return softValuePut.get();
        }
        return null;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        processQueue();
        SoftValue<V, K> softValueRemove = this.map.remove(obj);
        if (softValueRemove != null) {
            return softValueRemove.get();
        }
        return null;
    }

    @Override // java.util.Map
    public void clear() {
        this.strongReferencesLock.lock();
        try {
            this.strongReferences.clear();
            this.strongReferencesLock.unlock();
            processQueue();
            this.map.clear();
        } catch (Throwable th) {
            this.strongReferencesLock.unlock();
            throw th;
        }
    }

    @Override // java.util.Map
    public int size() {
        processQueue();
        return this.map.size();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        processQueue();
        Set<K> setKeySet = this.map.keySet();
        if (setKeySet.isEmpty()) {
            return Collections.EMPTY_SET;
        }
        HashMap map = new HashMap(setKeySet.size());
        for (K k : setKeySet) {
            V v = get(k);
            if (v != null) {
                map.put(k, v);
            }
        }
        return map.entrySet();
    }

    private static class SoftValue<V, K> extends SoftReference<V> {
        private final K key;

        private SoftValue(V v, K k, ReferenceQueue<? super V> referenceQueue) {
            super(v, referenceQueue);
            this.key = k;
        }
    }
}
