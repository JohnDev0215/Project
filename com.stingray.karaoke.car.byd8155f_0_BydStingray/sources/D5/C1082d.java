package d5;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: renamed from: d5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1082d extends AbstractMap {
    public transient C1080b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient C1092n f13413m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Map f13414n;
    public final /* synthetic */ Y o;

    public C1082d(Y y10, Map map) {
        this.o = y10;
        this.f13414n = map;
    }

    public final C1076E b(Map.Entry entry) {
        Object key = entry.getKey();
        Collection collection = (Collection) entry.getValue();
        Y y10 = this.o;
        List list = (List) collection;
        return new C1076E(key, list instanceof RandomAccess ? new C1086h(y10, key, list, null) : new C1090l(y10, key, list, null));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Y y10 = this.o;
        if (this.f13414n == y10.o) {
            y10.b();
            return;
        }
        C1081c c1081c = new C1081c(this);
        while (c1081c.hasNext()) {
            c1081c.next();
            c1081c.remove();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map map = this.f13414n;
        map.getClass();
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        C1080b c1080b = this.l;
        if (c1080b != null) {
            return c1080b;
        }
        C1080b c1080b2 = new C1080b(this);
        this.l = c1080b2;
        return c1080b2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return this == obj || this.f13414n.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Object obj2;
        Map map = this.f13414n;
        map.getClass();
        try {
            obj2 = map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection == null) {
            return null;
        }
        Y y10 = this.o;
        List list = (List) collection;
        return list instanceof RandomAccess ? new C1086h(y10, obj, list, null) : new C1090l(y10, obj, list, null);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.f13414n.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        Y y10 = this.o;
        C1083e c1085g = y10.l;
        if (c1085g == null) {
            Map map = y10.o;
            c1085g = map instanceof NavigableMap ? new C1085g(y10, (NavigableMap) map) : map instanceof SortedMap ? new C1088j(y10, (SortedMap) map) : new C1083e(y10, map);
            y10.l = c1085g;
        }
        return c1085g;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Collection collection = (Collection) this.f13414n.remove(obj);
        if (collection == null) {
            return null;
        }
        Y y10 = this.o;
        List list = (List) y10.f13402q.get();
        list.addAll(collection);
        y10.f13401p -= collection.size();
        collection.clear();
        return list;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f13414n.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.f13414n.toString();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        C1092n c1092n = this.f13413m;
        if (c1092n != null) {
            return c1092n;
        }
        C1092n c1092n2 = new C1092n(this);
        this.f13413m = c1092n2;
        return c1092n2;
    }
}
