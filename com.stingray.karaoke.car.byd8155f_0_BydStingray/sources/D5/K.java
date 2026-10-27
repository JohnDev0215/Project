package d5;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class K implements Map, Serializable {
    public transient M l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient M f13387m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient AbstractC1075D f13388n;

    public static K b(Map map) {
        if ((map instanceof K) && !(map instanceof SortedMap)) {
            K k10 = (K) map;
            k10.getClass();
            return k10;
        }
        Set<Map.Entry> setEntrySet = map.entrySet();
        boolean z6 = setEntrySet instanceof Collection;
        B5.u uVar = new B5.u(z6 ? setEntrySet.size() : 4);
        if (z6) {
            int size = setEntrySet.size() * 2;
            Object[] objArr = (Object[]) uVar.f736n;
            if (size > objArr.length) {
                uVar.f736n = Arrays.copyOf(objArr, AbstractC1074C.g(objArr.length, size));
            }
        }
        for (Map.Entry entry : setEntrySet) {
            uVar.s(entry.getKey(), entry.getValue());
        }
        return uVar.g();
    }

    public abstract f0 c();

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public abstract g0 d();

    public abstract AbstractC1075D e();

    @Override // java.util.Map
    public final Set entrySet() {
        M m10 = this.l;
        if (m10 != null) {
            return m10;
        }
        f0 f0VarC = c();
        this.l = f0VarC;
        return f0VarC;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return AbstractC1095q.i(obj, this);
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public AbstractC1075D values() {
        AbstractC1075D abstractC1075D = this.f13388n;
        if (abstractC1075D != null) {
            return abstractC1075D;
        }
        AbstractC1075D abstractC1075DE = e();
        this.f13388n = abstractC1075DE;
        return abstractC1075DE;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        M mC = this.l;
        if (mC == null) {
            mC = c();
            this.l = mC;
        }
        return AbstractC1095q.n(mC);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        M m10 = this.f13387m;
        if (m10 != null) {
            return m10;
        }
        g0 g0VarD = d();
        this.f13387m = g0VarD;
        return g0VarD;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        AbstractC1095q.e(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824L));
        sb.append('{');
        boolean z6 = true;
        for (Map.Entry entry : entrySet()) {
            if (!z6) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z6 = false;
        }
        sb.append('}');
        return sb.toString();
    }
}
