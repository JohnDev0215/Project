package t;

import java.util.HashMap;
import java.util.Map;
import t.b;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public HashMap<K, b.c<K, V>> f7939h = new HashMap<>();

    @Override // t.b
    public b.c<K, V> c(K k8) {
        return this.f7939h.get(k8);
    }

    public boolean contains(K k8) {
        return this.f7939h.containsKey(k8);
    }

    @Override // t.b
    public V g(K k8, V v7) {
        b.c<K, V> cVarC = c(k8);
        if (cVarC != null) {
            return cVarC.f7945e;
        }
        this.f7939h.put(k8, f(k8, v7));
        return null;
    }

    @Override // t.b
    public V h(K k8) {
        V v7 = (V) super.h(k8);
        this.f7939h.remove(k8);
        return v7;
    }

    public Map.Entry<K, V> i(K k8) {
        if (contains(k8)) {
            return this.f7939h.get(k8).f7947g;
        }
        return null;
    }
}
