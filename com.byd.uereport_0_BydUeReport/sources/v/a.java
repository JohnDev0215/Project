package v;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f<K, V> f8298k;

    /* JADX INFO: renamed from: v.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    public class C0132a extends f<K, V> {
        public C0132a() {
        }

        @Override // v.f
        public void a() {
            a.this.clear();
        }

        @Override // v.f
        public Object b(int i8, int i9) {
            return a.this.f8347i[(i8 << 1) + i9];
        }

        @Override // v.f
        public Map<K, V> c() {
            return a.this;
        }

        @Override // v.f
        public int d() {
            return a.this.f8348j;
        }

        @Override // v.f
        public int e(Object obj) {
            return a.this.f(obj);
        }

        @Override // v.f
        public int f(Object obj) {
            return a.this.h(obj);
        }

        @Override // v.f
        public void g(K k8, V v7) {
            a.this.put(k8, v7);
        }

        @Override // v.f
        public void h(int i8) {
            a.this.k(i8);
        }

        @Override // v.f
        public V i(int i8, V v7) {
            return a.this.l(i8, v7);
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
        if (this.f8298k == null) {
            this.f8298k = new C0132a();
        }
        return this.f8298k;
    }

    public boolean o(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        c(this.f8348j + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return n().n();
    }

    public a(int i8) {
        super(i8);
    }

    public a(g gVar) {
        super(gVar);
    }
}
