package l;

import java.util.HashMap;
import java.util.Map;
import l.b;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private HashMap<K, b.c<K, V>> f7866j = new HashMap<>();

    public boolean contains(K k7) {
        return this.f7866j.containsKey(k7);
    }

    @Override // l.b
    protected b.c<K, V> e(K k7) {
        return this.f7866j.get(k7);
    }

    @Override // l.b
    public V i(K k7, V v6) {
        b.c<K, V> cVarE = e(k7);
        if (cVarE != null) {
            return cVarE.f7872g;
        }
        this.f7866j.put(k7, h(k7, v6));
        return null;
    }

    @Override // l.b
    public V j(K k7) {
        V v6 = (V) super.j(k7);
        this.f7866j.remove(k7);
        return v6;
    }

    public Map.Entry<K, V> k(K k7) {
        if (contains(k7)) {
            return this.f7866j.get(k7).f7874i;
        }
        return null;
    }
}
