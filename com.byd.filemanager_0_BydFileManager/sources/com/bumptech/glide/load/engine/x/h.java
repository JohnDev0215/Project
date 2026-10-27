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
    private final a<K, V> f3098a = new a<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<K, a<K, V>> f3099b = new HashMap();

    /* JADX INFO: compiled from: GroupedLinkedMap.java */
    private static class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final K f3100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<V> f3101b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a<K, V> f3102c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        a<K, V> f3103d;

        a() {
            this(null);
        }

        public void a(V v) {
            if (this.f3101b == null) {
                this.f3101b = new ArrayList();
            }
            this.f3101b.add(v);
        }

        public V b() {
            int iC = c();
            if (iC > 0) {
                return this.f3101b.remove(iC - 1);
            }
            return null;
        }

        public int c() {
            List<V> list = this.f3101b;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        a(K k) {
            this.f3103d = this;
            this.f3102c = this;
            this.f3100a = k;
        }
    }

    h() {
    }

    private void b(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f3098a;
        aVar.f3103d = aVar2;
        aVar.f3102c = aVar2.f3102c;
        g(aVar);
    }

    private void c(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f3098a;
        aVar.f3103d = aVar2.f3103d;
        aVar.f3102c = aVar2;
        g(aVar);
    }

    private static <K, V> void e(a<K, V> aVar) {
        a<K, V> aVar2 = aVar.f3103d;
        aVar2.f3102c = aVar.f3102c;
        aVar.f3102c.f3103d = aVar2;
    }

    private static <K, V> void g(a<K, V> aVar) {
        aVar.f3102c.f3103d = aVar;
        aVar.f3103d.f3102c = aVar;
    }

    public V a(K k) {
        a<K, V> aVar = this.f3099b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            this.f3099b.put(k, aVar);
        } else {
            k.a();
        }
        b(aVar);
        return aVar.b();
    }

    public void d(K k, V v) {
        a<K, V> aVar = this.f3099b.get(k);
        if (aVar == null) {
            aVar = new a<>(k);
            c(aVar);
            this.f3099b.put(k, aVar);
        } else {
            k.a();
        }
        aVar.a(v);
    }

    public V f() {
        for (a aVar = this.f3098a.f3103d; !aVar.equals(this.f3098a); aVar = aVar.f3103d) {
            V v = (V) aVar.b();
            if (v != null) {
                return v;
            }
            e(aVar);
            this.f3099b.remove(aVar.f3100a);
            ((m) aVar.f3100a).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z = false;
        for (a aVar = this.f3098a.f3102c; !aVar.equals(this.f3098a); aVar = aVar.f3102c) {
            z = true;
            sb.append('{');
            sb.append(aVar.f3100a);
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
