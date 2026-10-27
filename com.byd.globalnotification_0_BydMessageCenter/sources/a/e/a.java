package a.e;

import a.e.g.b;
import a.e.g.c;
import a.e.g.e;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class a<K, V> extends h<K, V> implements Map<K, V> {
    public g<K, V> h;

    /* JADX INFO: renamed from: a.e.a$a, reason: collision with other inner class name */
    public class C0006a extends g<K, V> {
        public C0006a() {
        }

        @Override // a.e.g
        public void a() {
            a.this.clear();
        }

        @Override // a.e.g
        public Object b(int i, int i2) {
            return a.this.f489b[(i << 1) + i2];
        }

        @Override // a.e.g
        public Map<K, V> c() {
            return a.this;
        }

        @Override // a.e.g
        public int d() {
            return a.this.f490c;
        }

        @Override // a.e.g
        public int e(Object obj) {
            return a.this.e(obj);
        }

        @Override // a.e.g
        public int f(Object obj) {
            return a.this.g(obj);
        }

        @Override // a.e.g
        public void g(K k, V v) {
            a.this.put(k, v);
        }

        @Override // a.e.g
        public void h(int i) {
            a.this.i(i);
        }

        @Override // a.e.g
        public V i(int i, V v) {
            int i2 = (i << 1) + 1;
            Object[] objArr = a.this.f489b;
            V v2 = (V) objArr[i2];
            objArr[i2] = v;
            return v2;
        }
    }

    public a() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(h hVar) {
        if (hVar != null) {
            int i = hVar.f490c;
            b(this.f490c + i);
            if (this.f490c != 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    put(hVar.h(i2), hVar.k(i2));
                }
            } else if (i > 0) {
                System.arraycopy(hVar.f488a, 0, this.f488a, 0, i);
                System.arraycopy(hVar.f489b, 0, this.f489b, 0, i << 1);
                this.f490c = i;
            }
        }
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        g<K, V> gVarL = l();
        if (gVarL.f470a == null) {
            gVarL.f470a = gVarL.new b();
        }
        return gVarL.f470a;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        g<K, V> gVarL = l();
        if (gVarL.f471b == null) {
            gVarL.f471b = gVarL.new c();
        }
        return gVarL.f471b;
    }

    public final g<K, V> l() {
        if (this.h == null) {
            this.h = new C0006a();
        }
        return this.h;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        b(map.size() + this.f490c);
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        g<K, V> gVarL = l();
        if (gVarL.f472c == null) {
            gVarL.f472c = gVarL.new e();
        }
        return gVarL.f472c;
    }
}
