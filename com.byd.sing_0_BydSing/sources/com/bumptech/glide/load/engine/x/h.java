package com.bumptech.glide.load.engine.x;

import com.bumptech.glide.load.engine.x.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GroupedLinkedMap.java */
/* JADX INFO: loaded from: classes.dex */
class h<K extends m, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a<K, V> f2851a = new a<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<K, a<K, V>> f2852b = new HashMap();

    /* JADX INFO: compiled from: GroupedLinkedMap.java */
    private static class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final K f2853a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<V> f2854b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a<K, V> f2855c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        a<K, V> f2856d;

        a() {
            this(null);
        }

        public void a(V v) {
            if (this.f2854b == null) {
                this.f2854b = new ArrayList();
            }
            this.f2854b.add(v);
        }

        public V b() {
            int iC = c();
            if (iC > 0) {
                return this.f2854b.remove(iC - 1);
            }
            return null;
        }

        public int c() {
            List<V> list = this.f2854b;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        a(K k) {
            this.f2856d = this;
            this.f2855c = this;
            this.f2853a = k;
        }
    }

    h() {
    }

    private void b(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f2851a;
        aVar.f2856d = aVar2;
        aVar.f2855c = aVar2.f2855c;
        g(aVar);
    }

    private void c(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f2851a;
        aVar.f2856d = aVar2.f2856d;
        aVar.f2855c = aVar2;
        g(aVar);
    }

    private static <K, V> void e(a<K, V> aVar) {
        a<K, V> aVar2 = aVar.f2856d;
        aVar2.f2855c = aVar.f2855c;
        aVar.f2855c.f2856d = aVar2;
    }

    private static <K, V> void g(a<K, V> aVar) {
        aVar.f2855c.f2856d = aVar;
        aVar.f2856d.f2855c = aVar;
    }

    public V a(K k) {
        a<K, V> aVar = this.f2852b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            this.f2852b.put(k, aVar);
        } else {
            k.a();
        }
        b(aVar);
        return aVar.b();
    }

    public void d(K k, V v) {
        a<K, V> aVar = this.f2852b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            c(aVar);
            this.f2852b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a(v);
    }

    public V f() {
        for (a aVar = this.f2851a.f2856d; !aVar.equals(this.f2851a); aVar = aVar.f2856d) {
            V v = (V) aVar.b();
            if (v != null) {
                return v;
            }
            e(aVar);
            this.f2852b.remove(aVar.f2853a);
            ((m) aVar.f2853a).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (a aVar = this.f2851a.f2855c; !aVar.equals(this.f2851a); aVar = aVar.f2855c) {
            z = true;
            sb.append('{');
            sb.append(aVar.f2853a);
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
