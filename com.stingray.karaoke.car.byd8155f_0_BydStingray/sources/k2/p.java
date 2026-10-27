package k2;

import d5.AbstractC1095q;
import d5.n0;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class p extends AbstractC1095q implements Map {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Map f17691m;

    public p(Map map) {
        super(2);
        this.f17691m = map;
    }

    @Override // java.util.Map
    public final void clear() {
        this.f17691m.clear();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return obj != null && this.f17691m.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        Iterator it = ((n0) entrySet()).iterator();
        it.getClass();
        if (obj == null) {
            while (it.hasNext()) {
                if (((Map.Entry) it.next()).getValue() == null) {
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (obj.equals(((Map.Entry) it.next()).getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return AbstractC1095q.k(this.f17691m.entrySet(), new o(0));
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return obj != null && AbstractC1095q.i(obj, this);
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        return (List) this.f17691m.get(obj);
    }

    @Override // d5.AbstractC1095q
    public final Object h() {
        return this.f17691m;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return AbstractC1095q.n(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        Map map = this.f17691m;
        if (map.isEmpty()) {
            return true;
        }
        return map.size() == 1 && map.containsKey(null);
    }

    @Override // java.util.Map
    public final Set keySet() {
        return AbstractC1095q.k(this.f17691m.keySet(), new o(1));
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        return this.f17691m.put(obj, obj2);
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        this.f17691m.putAll(map);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        return this.f17691m.remove(obj);
    }

    @Override // java.util.Map
    public final int size() {
        Map map = this.f17691m;
        return map.size() - (map.containsKey(null) ? 1 : 0);
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.f17691m.values();
    }
}
