package b.b.a.b;

import b.b.a.b.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private HashMap<K, b.c<K, V>> f2234f = new HashMap<>();

    @Override // b.b.a.b.b
    protected b.c<K, V> c(K k) {
        return this.f2234f.get(k);
    }

    public boolean contains(K k) {
        return this.f2234f.containsKey(k);
    }

    @Override // b.b.a.b.b
    public V g(K k, V v) {
        b.c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f2240c;
        }
        this.f2234f.put(k, f(k, v));
        return null;
    }

    @Override // b.b.a.b.b
    public V h(K k) {
        V v = (V) super.h(k);
        this.f2234f.remove(k);
        return v;
    }

    public Map.Entry<K, V> i(K k) {
        if (contains(k)) {
            return this.f2234f.get(k).f2242e;
        }
        return null;
    }
}
