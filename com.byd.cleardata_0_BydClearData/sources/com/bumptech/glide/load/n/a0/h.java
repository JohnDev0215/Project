package com.bumptech.glide.load.n.a0;

import com.bumptech.glide.load.n.a0.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class h<K extends m, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a<K, V> f2037a = new a<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<K, a<K, V>> f2038b = new HashMap();

    private static class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final K f2039a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<V> f2040b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a<K, V> f2041c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        a<K, V> f2042d;

        a() {
            this(null);
        }

        a(K k) {
            this.f2042d = this;
            this.f2041c = this;
            this.f2039a = k;
        }

        public void a(V v) {
            if (this.f2040b == null) {
                this.f2040b = new ArrayList();
            }
            this.f2040b.add(v);
        }

        public V b() {
            int iC = c();
            if (iC > 0) {
                return this.f2040b.remove(iC - 1);
            }
            return null;
        }

        public int c() {
            List<V> list = this.f2040b;
            if (list != null) {
                return list.size();
            }
            return 0;
        }
    }

    h() {
    }

    private void b(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f2037a;
        aVar.f2042d = aVar2;
        aVar.f2041c = aVar2.f2041c;
        g(aVar);
    }

    private void c(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f2037a;
        aVar.f2042d = aVar2.f2042d;
        aVar.f2041c = aVar2;
        g(aVar);
    }

    private static <K, V> void e(a<K, V> aVar) {
        a<K, V> aVar2 = aVar.f2042d;
        aVar2.f2041c = aVar.f2041c;
        aVar.f2041c.f2042d = aVar2;
    }

    private static <K, V> void g(a<K, V> aVar) {
        aVar.f2041c.f2042d = aVar;
        aVar.f2042d.f2041c = aVar;
    }

    public V a(K k) {
        a<K, V> aVar = this.f2038b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            this.f2038b.put(k, aVar);
        } else {
            k.a();
        }
        b(aVar);
        return aVar.b();
    }

    public void d(K k, V v) {
        a<K, V> aVar = this.f2038b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            c(aVar);
            this.f2038b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a(v);
    }

    public V f() {
        a aVar = this.f2037a;
        while (true) {
            aVar = aVar.f2042d;
            if (aVar.equals(this.f2037a)) {
                return null;
            }
            V v = (V) aVar.b();
            if (v != null) {
                return v;
            }
            e(aVar);
            this.f2038b.remove(aVar.f2039a);
            ((m) aVar.f2039a).a();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (a aVar = this.f2037a.f2041c; !aVar.equals(this.f2037a); aVar = aVar.f2041c) {
            z = true;
            sb.append('{');
            sb.append(aVar.f2039a);
            sb.append(':');
            sb.append(aVar.c());
            sb.append("}, ");
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
