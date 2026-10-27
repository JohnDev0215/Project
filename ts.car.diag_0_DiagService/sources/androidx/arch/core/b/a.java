package androidx.arch.core.b;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.arch.core.b.b;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class a<K, V> extends b<K, V> {
    private HashMap<K, b.c<K, V>> g = new HashMap<>();

    @Override // androidx.arch.core.b.b
    protected b.c<K, V> c(K k) {
        return this.g.get(k);
    }

    public boolean contains(K k) {
        return this.g.containsKey(k);
    }

    @Override // androidx.arch.core.b.b
    public V g(@NonNull K k, @NonNull V v) {
        b.c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.d;
        }
        this.g.put(k, f(k, v));
        return null;
    }

    @Override // androidx.arch.core.b.b
    public V h(@NonNull K k) {
        V v = (V) super.h(k);
        this.g.remove(k);
        return v;
    }

    public Map.Entry<K, V> i(K k) {
        if (contains(k)) {
            return this.g.get(k).f;
        }
        return null;
    }
}
