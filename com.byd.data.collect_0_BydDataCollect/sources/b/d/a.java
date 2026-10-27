package b.d;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    f<K, V> f2248i;

    /* JADX INFO: renamed from: b.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    class C0036a extends f<K, V> {
        C0036a() {
        }

        @Override // b.d.f
        protected void a() {
            a.this.clear();
        }

        @Override // b.d.f
        protected Object b(int i2, int i3) {
            return a.this.f2295g[(i2 << 1) + i3];
        }

        @Override // b.d.f
        protected Map<K, V> c() {
            return a.this;
        }

        @Override // b.d.f
        protected int d() {
            return a.this.f2296h;
        }

        @Override // b.d.f
        protected int e(Object obj) {
            return a.this.f(obj);
        }

        @Override // b.d.f
        protected int f(Object obj) {
            return a.this.h(obj);
        }

        @Override // b.d.f
        protected void g(K k, V v) {
            a.this.put(k, v);
        }

        @Override // b.d.f
        protected void h(int i2) {
            a.this.k(i2);
        }

        @Override // b.d.f
        protected V i(int i2, V v) {
            return a.this.l(i2, v);
        }
    }

    public a() {
    }

    private f<K, V> n() {
        if (this.f2248i == null) {
            this.f2248i = new C0036a();
        }
        return this.f2248i;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return n().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return n().m();
    }

    public boolean o(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        c(this.f2296h + map.size());
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
