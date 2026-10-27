package b.c.a.b;

import b.c.a.b.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {
    public HashMap<K, b.c<K, V>> y = new HashMap<>();

    @Override // b.c.a.b.b
    public b.c<K, V> c(K k) {
        return this.y.get(k);
    }

    public boolean contains(K k) {
        return this.y.containsKey(k);
    }

    @Override // b.c.a.b.b
    public V g(K k, V v) {
        b.c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f1148d;
        }
        this.y.put(k, f(k, v));
        return null;
    }

    @Override // b.c.a.b.b
    public V h(K k) {
        V v = (V) super.h(k);
        this.y.remove(k);
        return v;
    }

    public Map.Entry<K, V> i(K k) {
        if (contains(k)) {
            return this.y.get(k).x;
        }
        return null;
    }
}
