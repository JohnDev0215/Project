package b.d;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends g<K, V> implements Map<K, V> {
    public f<K, V> G3;

    /* JADX INFO: renamed from: b.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArrayMap.java */
    public class C0013a extends f<K, V> {
        public C0013a() {
        }

        @Override // b.d.f
        public void a() {
            a.this.clear();
        }

        @Override // b.d.f
        public Object b(int i2, int i3) {
            return a.this.E3[(i2 << 1) + i3];
        }

        @Override // b.d.f
        public Map<K, V> c() {
            return a.this;
        }

        @Override // b.d.f
        public int d() {
            return a.this.F3;
        }

        @Override // b.d.f
        public int e(Object obj) {
            return a.this.f(obj);
        }

        @Override // b.d.f
        public int f(Object obj) {
            return a.this.h(obj);
        }

        @Override // b.d.f
        public void g(K k, V v) {
            a.this.put(k, v);
        }

        @Override // b.d.f
        public void h(int i2) {
            a.this.j(i2);
        }

        @Override // b.d.f
        public V i(int i2, V v) {
            return a.this.k(i2, v);
        }
    }

    public a() {
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return m().l();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return m().m();
    }

    public final f<K, V> m() {
        if (this.G3 == null) {
            this.G3 = new C0013a();
        }
        return this.G3;
    }

    public boolean n(Collection<?> collection) {
        return f.p(this, collection);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        c(this.F3 + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return m().n();
    }

    public a(int i2) {
        super(i2);
    }
}
