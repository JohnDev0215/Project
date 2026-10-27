package t;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c<K, V> f7940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c<K, V> f7941e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WeakHashMap<f<K, V>, Boolean> f7942f = new WeakHashMap<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f7943g = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // t.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f7947g;
        }

        @Override // t.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f7946f;
        }
    }

    /* JADX INFO: renamed from: t.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class C0120b<K, V> extends e<K, V> {
        public C0120b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // t.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f7946f;
        }

        @Override // t.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f7947g;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final K f7944d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final V f7945e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public c<K, V> f7946f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public c<K, V> f7947g;

        public c(K k8, V v7) {
            this.f7944d = k8;
            this.f7945e = v7;
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
            return this.f7944d.equals(cVar.f7944d) && this.f7945e.equals(cVar.f7945e);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f7944d;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f7945e;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f7944d.hashCode() ^ this.f7945e.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v7) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f7944d + "=" + this.f7945e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f7948d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7949e = true;

        public d() {
        }

        @Override // t.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f7948d;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f7947g;
                this.f7948d = cVar3;
                this.f7949e = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f7949e) {
                this.f7949e = false;
                this.f7948d = b.this.f7940d;
            } else {
                c<K, V> cVar = this.f7948d;
                this.f7948d = cVar != null ? cVar.f7946f : null;
            }
            return this.f7948d;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f7949e) {
                return b.this.f7940d != null;
            }
            c<K, V> cVar = this.f7948d;
            return (cVar == null || cVar.f7946f == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f7951d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c<K, V> f7952e;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f7951d = cVar2;
            this.f7952e = cVar;
        }

        @Override // t.b.f
        public void a(c<K, V> cVar) {
            if (this.f7951d == cVar && cVar == this.f7952e) {
                this.f7952e = null;
                this.f7951d = null;
            }
            c<K, V> cVar2 = this.f7951d;
            if (cVar2 == cVar) {
                this.f7951d = b(cVar2);
            }
            if (this.f7952e == cVar) {
                this.f7952e = e();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f7952e;
            this.f7952e = e();
            return cVar;
        }

        public final c<K, V> e() {
            c<K, V> cVar = this.f7952e;
            c<K, V> cVar2 = this.f7951d;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f7952e != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0120b c0120b = new C0120b(this.f7941e, this.f7940d);
        this.f7942f.put(c0120b, Boolean.FALSE);
        return c0120b;
    }

    public Map.Entry<K, V> b() {
        return this.f7940d;
    }

    public c<K, V> c(K k8) {
        c<K, V> cVar = this.f7940d;
        while (cVar != null && !cVar.f7944d.equals(k8)) {
            cVar = cVar.f7946f;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f7942f.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f7941e;
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

    public c<K, V> f(K k8, V v7) {
        c<K, V> cVar = new c<>(k8, v7);
        this.f7943g++;
        c<K, V> cVar2 = this.f7941e;
        if (cVar2 == null) {
            this.f7940d = cVar;
            this.f7941e = cVar;
            return cVar;
        }
        cVar2.f7946f = cVar;
        cVar.f7947g = cVar2;
        this.f7941e = cVar;
        return cVar;
    }

    public V g(K k8, V v7) {
        c<K, V> cVarC = c(k8);
        if (cVarC != null) {
            return cVarC.f7945e;
        }
        f(k8, v7);
        return null;
    }

    public V h(K k8) {
        c<K, V> cVarC = c(k8);
        if (cVarC == null) {
            return null;
        }
        this.f7943g--;
        if (!this.f7942f.isEmpty()) {
            Iterator<f<K, V>> it = this.f7942f.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f7947g;
        if (cVar != null) {
            cVar.f7946f = cVarC.f7946f;
        } else {
            this.f7940d = cVarC.f7946f;
        }
        c<K, V> cVar2 = cVarC.f7946f;
        if (cVar2 != null) {
            cVar2.f7947g = cVar;
        } else {
            this.f7941e = cVar;
        }
        cVarC.f7946f = null;
        cVarC.f7947g = null;
        return cVarC.f7945e;
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
        a aVar = new a(this.f7940d, this.f7941e);
        this.f7942f.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f7943g;
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
