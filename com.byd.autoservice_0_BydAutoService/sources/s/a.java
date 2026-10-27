package s;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f<K, V> f4840i;

    /* JADX INFO: renamed from: s.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    public class C0058a extends f<K, V> {
        public C0058a() {
        }

        @Override // s.f
        public void a() {
            a.this.clear();
        }

        @Override // s.f
        public Object b(int i3, int i4) {
            return a.this.f4889g[(i3 << 1) + i4];
        }

        @Override // s.f
        public Map<K, V> c() {
            return a.this;
        }

        @Override // s.f
        public int d() {
            return a.this.f4890h;
        }

        @Override // s.f
        public int e(Object obj) {
            return a.this.f(obj);
        }

        @Override // s.f
        public int f(Object obj) {
            return a.this.h(obj);
        }

        @Override // s.f
        public void g(K k3, V v3) {
            a.this.put(k3, v3);
        }

        @Override // s.f
        public void h(int i3) {
            a.this.k(i3);
        }

        @Override // s.f
        public V i(int i3, V v3) {
            return a.this.l(i3, v3);
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
        if (this.f4840i == null) {
            this.f4840i = new C0058a();
        }
        return this.f4840i;
    }

    public boolean o(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        c(this.f4890h + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return n().n();
    }

    public a(int i3) {
        super(i3);
    }

    public a(g gVar) {
        super(gVar);
    }
}
