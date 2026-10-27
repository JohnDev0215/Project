package B5;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class L implements Map {
    public final Map l;

    public L() {
        this.l = new LinkedHashMap();
    }

    public final boolean b(String str) {
        Object obj = this.l.get(str);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof String) {
            return Boolean.valueOf((String) obj).booleanValue();
        }
        return true;
    }

    public final String c(String str) {
        Object obj = this.l.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final void clear() {
        this.l.clear();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.l.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.l.containsValue(obj);
    }

    public final L d(Class cls, String str) {
        Object obj = this.l.get(str);
        if (obj == null) {
            return null;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return (L) obj;
        }
        if (!(obj instanceof Map)) {
            return null;
        }
        Map map = (Map) obj;
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(Map.class);
            declaredConstructor.setAccessible(true);
            return (L) declaredConstructor.newInstance(map);
        } catch (Exception e10) {
            throw new AssertionError("Could not create instance of " + cls.getCanonicalName() + ".\n" + e10);
        }
    }

    public final L e(String str) {
        Object obj = this.l.get(str);
        if (obj instanceof L) {
            return (L) obj;
        }
        if (obj instanceof Map) {
            return new L((Map) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.l.entrySet();
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return obj == this || this.l.equals(obj);
    }

    public final void f(Object obj, String str) {
        this.l.put(str, obj);
    }

    public void g(Object obj, String str) {
        this.l.put(str, obj);
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return this.l.get(obj);
    }

    public final HashMap h() {
        HashMap map = new HashMap();
        for (Map.Entry entry : this.l.entrySet()) {
            map.put((String) entry.getKey(), String.valueOf(entry.getValue()));
        }
        return map;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return this.l.hashCode();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        return this.l.keySet();
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        return this.l.put((String) obj, obj2);
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        this.l.putAll(map);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        return this.l.remove(obj);
    }

    @Override // java.util.Map
    public final int size() {
        return this.l.size();
    }

    public String toString() {
        return this.l.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.l.values();
    }

    public L(Map map) {
        if (map != null) {
            this.l = map;
            return;
        }
        throw new IllegalArgumentException("Map must not be null.");
    }
}
