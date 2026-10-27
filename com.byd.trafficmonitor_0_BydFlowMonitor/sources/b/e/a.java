package b.e;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f<K, V> f1928i;

    /* JADX INFO: renamed from: b.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    public class C0024a extends f<K, V> {
        public C0024a() {
        }

        @Override // b.e.f
        public void a() {
            a.this.clear();
        }

        @Override // b.e.f
        public Object b(int i2, int i3) {
            return a.this.f1975g[(i2 << 1) + i3];
        }

        @Override // b.e.f
        public Map<K, V> c() {
            return a.this;
        }

        @Override // b.e.f
        public int d() {
            return a.this.f1976h;
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
        public void h(int i2) {
            a.this.k(i2);
        }

        @Override // b.e.f
        public V i(int i2, V v) {
            return a.this.l(i2, v);
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
        if (this.f1928i == null) {
            this.f1928i = new C0024a();
        }
        return this.f1928i;
    }

    public boolean o(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        c(this.f1976h + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return n().n();
    }

    public a(int i2) {
        super(i2);
    }

    public a(g gVar) {
        super(gVar);
    }
}
