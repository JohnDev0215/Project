package n;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    f<K, V> f8435m;

    /* JADX INFO: renamed from: n.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    class C0131a extends f<K, V> {
        C0131a() {
        }

        @Override // n.f
        protected void a() {
            a.this.clear();
        }

        @Override // n.f
        protected Object b(int i7, int i8) {
            return a.this.f8484g[(i7 << 1) + i8];
        }

        @Override // n.f
        protected Map<K, V> c() {
            return a.this;
        }

        @Override // n.f
        protected int d() {
            return a.this.f8485h;
        }

        @Override // n.f
        protected int e(Object obj) {
            return a.this.h(obj);
        }

        @Override // n.f
        protected int f(Object obj) {
            return a.this.j(obj);
        }

        @Override // n.f
        protected void g(K k7, V v6) {
            a.this.put(k7, v6);
        }

        @Override // n.f
        protected void h(int i7) {
            a.this.m(i7);
        }

        @Override // n.f
        protected V i(int i7, V v6) {
            return a.this.n(i7, v6);
        }
    }

    public a() {
    }

    private f<K, V> p() {
        if (this.f8435m == null) {
            this.f8435m = new C0131a();
        }
        return this.f8435m;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return p().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return p().m();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        e(this.f8485h + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean q(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return p().n();
    }

    public a(g gVar) {
        super(gVar);
    }
}
