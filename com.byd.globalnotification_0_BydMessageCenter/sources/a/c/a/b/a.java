package a.c.a.b;

import a.c.a.b.b;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends b<K, V> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashMap<K, b.c<K, V>> f424e = new HashMap<>();

    @Override // a.c.a.b.b
    public b.c<K, V> c(K k) {
        return this.f424e.get(k);
    }

    public boolean contains(K k) {
        return this.f424e.containsKey(k);
    }

    @Override // a.c.a.b.b
    public V f(K k, V v) {
        b.c<K, V> cVar = this.f424e.get(k);
        if (cVar != null) {
            return cVar.f430b;
        }
        this.f424e.put(k, e(k, v));
        return null;
    }

    @Override // a.c.a.b.b
    public V g(K k) {
        V v = (V) super.g(k);
        this.f424e.remove(k);
        return v;
    }
}
