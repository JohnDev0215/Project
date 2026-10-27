package defpackage;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a4<K, V> extends g4<K, V> implements Map<K, V> {
    public f4<K, V> i;

    /* JADX INFO: compiled from: ArrayMap.java */
    public class a extends f4<K, V> {
        public a() {
        }

        @Override // defpackage.f4
        public void a() {
            a4.this.clear();
        }

        @Override // defpackage.f4
        public Object b(int i, int i2) {
            return a4.this.g[(i << 1) + i2];
        }

        @Override // defpackage.f4
        public Map<K, V> c() {
            return a4.this;
        }

        @Override // defpackage.f4
        public int d() {
            return a4.this.h;
        }

        @Override // defpackage.f4
        public int e(Object obj) {
            return a4.this.f(obj);
        }

        @Override // defpackage.f4
        public int f(Object obj) {
            return a4.this.h(obj);
        }

        @Override // defpackage.f4
        public void g(K k, V v) {
            a4.this.put(k, v);
        }

        @Override // defpackage.f4
        public void h(int i) {
            a4.this.k(i);
        }

        @Override // defpackage.f4
        public V i(int i, V v) {
            return a4.this.l(i, v);
        }
    }

    public a4() {
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return n().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return n().m();
    }

    public final f4<K, V> n() {
        if (this.i == null) {
            this.i = new a();
        }
        return this.i;
    }

    public boolean o(Collection<?> collection) {
        return f4.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        c(this.h + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return n().n();
    }

    public a4(int i) {
        super(i);
    }

    public a4(g4 g4Var) {
        super(g4Var);
    }
}
