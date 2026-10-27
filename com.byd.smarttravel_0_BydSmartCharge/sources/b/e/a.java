package b.e;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {
    public f<K, V> i;

    /* JADX INFO: renamed from: b.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    public class C0010a extends f<K, V> {
        public C0010a() {
        }

        @Override // b.e.f
        public void a() {
            a.this.clear();
        }

        @Override // b.e.f
        public Object b(int i, int i2) {
            return a.this.g[(i << 1) + i2];
        }

        @Override // b.e.f
        public Map<K, V> c() {
            return a.this;
        }

        @Override // b.e.f
        public int d() {
            return a.this.h;
        }

        @Override // b.e.f
        public int e(Object obj) {
            return a.this.f(obj);
        }

        @Override // b.e.f
        public int f(Object obj) {
            return a.this.h(obj);
        }

        @Override // b.e.f
        public void g(K k, V v) {
            a.this.put(k, v);
        }

        @Override // b.e.f
        public void h(int i) {
            a.this.k(i);
        }

        @Override // b.e.f
        public V i(int i, V v) {
            return a.this.l(i, v);
        }
    }

    public a() {
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return n().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return n().m();
    }

    public final f<K, V> n() {
        if (this.i == null) {
            this.i = new C0010a();
        }
        return this.i;
    }

    public boolean o(Collection<?> collection) {
        return f.p(this, collection);
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

    public a(int i) {
        super(i);
    }

    public a(g gVar) {
        super(gVar);
    }
}
