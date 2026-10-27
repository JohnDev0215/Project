package b.c.a.b;

import b.c.a.b.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public HashMap<K, b.c<K, V>> f1893f = new HashMap<>();

    @Override // b.c.a.b.b
    public b.c<K, V> c(K k) {
        return this.f1893f.get(k);
    }

    public boolean contains(K k) {
        return this.f1893f.containsKey(k);
    }

    @Override // b.c.a.b.b
    public V g(K k, V v) {
        b.c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f1899c;
        }
        this.f1893f.put(k, f(k, v));
        return null;
    }

    @Override // b.c.a.b.b
    public V h(K k) {
        V v = (V) super.h(k);
        this.f1893f.remove(k);
        return v;
    }

    public Map.Entry<K, V> i(K k) {
        if (contains(k)) {
            return this.f1893f.get(k).f1901e;
        }
        return null;
    }
}
