package r1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import r1.l;

/* JADX INFO: compiled from: GroupedLinkedMap.java */
/* JADX INFO: loaded from: classes.dex */
class g<K extends l, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a<K, V> f9333a = new a<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<K, a<K, V>> f9334b = new HashMap();

    /* JADX INFO: compiled from: GroupedLinkedMap.java */
    private static class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final K f9335a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private List<V> f9336b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a<K, V> f9337c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        a<K, V> f9338d;

        a() {
            this(null);
        }

        public void a(V v6) {
            if (this.f9336b == null) {
                this.f9336b = new ArrayList();
            }
            this.f9336b.add(v6);
        }

        public V b() {
            int iC = c();
            if (iC > 0) {
                return this.f9336b.remove(iC - 1);
            }
            return null;
        }

        public int c() {
            List<V> list = this.f9336b;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        a(K k7) {
            this.f9338d = this;
            this.f9337c = this;
            this.f9335a = k7;
        }
    }

    g() {
    }

    private void b(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f9333a;
        aVar.f9338d = aVar2;
        aVar.f9337c = aVar2.f9337c;
        g(aVar);
    }

    private void c(a<K, V> aVar) {
        e(aVar);
        a<K, V> aVar2 = this.f9333a;
        aVar.f9338d = aVar2.f9338d;
        aVar.f9337c = aVar2;
        g(aVar);
    }

    private static <K, V> void e(a<K, V> aVar) {
        a<K, V> aVar2 = aVar.f9338d;
        aVar2.f9337c = aVar.f9337c;
        aVar.f9337c.f9338d = aVar2;
    }

    private static <K, V> void g(a<K, V> aVar) {
        aVar.f9337c.f9338d = aVar;
        aVar.f9338d.f9337c = aVar;
    }

    public V a(K k7) {
        a<K, V> aVar = this.f9334b.get(k7);
        if (aVar == null) {
            aVar = new a<>(k7);
            this.f9334b.put(k7, aVar);
        } else {
            k7.a();
        }
        b(aVar);
        return aVar.b();
    }

    public void d(K k7, V v6) {
        a<K, V> aVar = this.f9334b.get(k7);
        if (aVar == null) {
            aVar = new a<>(k7);
            c(aVar);
            this.f9334b.put(k7, aVar);
        } else {
            k7.a();
        }
        aVar.a(v6);
    }

    public V f() {
        for (a aVar = this.f9333a.f9338d; !aVar.equals(this.f9333a); aVar = aVar.f9338d) {
            V v6 = (V) aVar.b();
            if (v6 != null) {
                return v6;
            }
            e(aVar);
            this.f9334b.remove(aVar.f9335a);
            ((l) aVar.f9335a).a();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        boolean z6 = false;
        for (a aVar = this.f9333a.f9337c; !aVar.equals(this.f9333a); aVar = aVar.f9337c) {
            z6 = true;
            sb.append('{');
            sb.append(aVar.f9335a);
            sb.append(':');
            sb.append(aVar.c());
            sb.append("}, ");
        }
        if (z6) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }
}
