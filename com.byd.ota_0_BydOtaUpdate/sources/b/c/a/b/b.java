package b.c.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c<K, V> f1145c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c<K, V> f1146d;
    public WeakHashMap<f<K, V>, Boolean> s = new WeakHashMap<>();
    public int x = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.x;
        }

        @Override // b.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.s;
        }
    }

    /* JADX INFO: renamed from: b.c.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class C0012b<K, V> extends e<K, V> {
        public C0012b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.s;
        }

        @Override // b.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.x;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final K f1147c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final V f1148d;
        public c<K, V> s;
        public c<K, V> x;

        public c(K k, V v) {
            this.f1147c = k;
            this.f1148d = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f1147c.equals(cVar.f1147c) && this.f1148d.equals(cVar.f1148d);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f1147c;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f1148d;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f1147c.hashCode() ^ this.f1148d.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f1147c + "=" + this.f1148d;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c<K, V> f1149c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1150d = true;

        public d() {
        }

        @Override // b.c.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f1149c;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.x;
                this.f1149c = cVar3;
                this.f1150d = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f1150d) {
                this.f1150d = false;
                this.f1149c = b.this.f1145c;
            } else {
                c<K, V> cVar = this.f1149c;
                this.f1149c = cVar != null ? cVar.s : null;
            }
            return this.f1149c;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f1150d) {
                return b.this.f1145c != null;
            }
            c<K, V> cVar = this.f1149c;
            return (cVar == null || cVar.s == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c<K, V> f1151c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f1152d;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f1151c = cVar2;
            this.f1152d = cVar;
        }

        @Override // b.c.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f1151c == cVar && cVar == this.f1152d) {
                this.f1152d = null;
                this.f1151c = null;
            }
            c<K, V> cVar2 = this.f1151c;
            if (cVar2 == cVar) {
                this.f1151c = b(cVar2);
            }
            if (this.f1152d == cVar) {
                this.f1152d = e();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f1152d;
            this.f1152d = e();
            return cVar;
        }

        public final c<K, V> e() {
            c<K, V> cVar = this.f1152d;
            c<K, V> cVar2 = this.f1151c;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1152d != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0012b c0012b = new C0012b(this.f1146d, this.f1145c);
        this.s.put(c0012b, Boolean.FALSE);
        return c0012b;
    }

    public Map.Entry<K, V> b() {
        return this.f1145c;
    }

    public c<K, V> c(K k) {
        c<K, V> cVar = this.f1145c;
        while (cVar != null && !cVar.f1147c.equals(k)) {
            cVar = cVar.s;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.s.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f1146d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (size() != bVar.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> it = iterator();
        Iterator<Map.Entry<K, V>> it2 = bVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry<K, V> next = it.next();
            Map.Entry<K, V> next2 = it2.next();
            if ((next == null && next2 != null) || (next != null && !next.equals(next2))) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    public c<K, V> f(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.x++;
        c<K, V> cVar2 = this.f1146d;
        if (cVar2 == null) {
            this.f1145c = cVar;
            this.f1146d = cVar;
            return cVar;
        }
        cVar2.s = cVar;
        cVar.x = cVar2;
        this.f1146d = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f1148d;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.x--;
        if (!this.s.isEmpty()) {
            Iterator<f<K, V>> it = this.s.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.x;
        if (cVar != null) {
            cVar.s = cVarC.s;
        } else {
            this.f1145c = cVarC.s;
        }
        c<K, V> cVar2 = cVarC.s;
        if (cVar2 != null) {
            cVar2.x = cVar;
        } else {
            this.f1146d = cVar;
        }
        cVarC.s = null;
        cVarC.x = null;
        return cVarC.f1148d;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f1145c, this.f1146d);
        this.s.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.x;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
