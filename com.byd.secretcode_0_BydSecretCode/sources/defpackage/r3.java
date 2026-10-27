package defpackage;

import defpackage.s3;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: FastSafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class r3<K, V> extends s3<K, V> {
    public HashMap<K, s3.c<K, V>> f = new HashMap<>();

    @Override // defpackage.s3
    public s3.c<K, V> c(K k) {
        return this.f.get(k);
    }

    public boolean contains(K k) {
        return this.f.containsKey(k);
    }

    @Override // defpackage.s3
    public V g(K k, V v) {
        s3.c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.c;
        }
        this.f.put(k, f(k, v));
        return null;
    }

    @Override // defpackage.s3
    public V h(K k) {
        V v = (V) super.h(k);
        this.f.remove(k);
        return v;
    }

    public Map.Entry<K, V> i(K k) {
        if (contains(k)) {
            return this.f.get(k).e;
        }
        return null;
    }
}
