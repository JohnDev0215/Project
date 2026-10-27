package org.apache.shiro.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MapContext implements Map<String, Object>, Serializable {
    private static final long serialVersionUID = 5373399119017820322L;
    private final Map<String, Object> backingMap;

    public MapContext() {
        this.backingMap = new HashMap();
    }

    public MapContext(Map<String, Object> map) {
        this();
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        this.backingMap.putAll(map);
    }

    protected <E> E getTypedValue(String str, Class<E> cls) {
        E e = (E) this.backingMap.get(str);
        if (e == null) {
            return null;
        }
        if (cls.isAssignableFrom(e.getClass())) {
            return e;
        }
        throw new IllegalArgumentException("Invalid object found in SubjectContext Map under key [" + str + "].  Expected type was [" + cls.getName() + "], but the object under that key is of type [" + e.getClass().getName() + "].");
    }

    protected void nullSafePut(String str, Object obj) {
        if (obj != null) {
            put(str, obj);
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.backingMap.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.backingMap.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.backingMap.containsValue(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.backingMap.get(obj);
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        return this.backingMap.put(str, obj);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.backingMap.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.backingMap.putAll(map);
    }

    @Override // java.util.Map
    public void clear() {
        this.backingMap.clear();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.backingMap.keySet());
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return Collections.unmodifiableCollection(this.backingMap.values());
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return Collections.unmodifiableSet(this.backingMap.entrySet());
    }
}
