package b.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f2225b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f2226c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f2227d = new WeakHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2228e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2232e;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2231d;
        }
    }

    /* JADX INFO: renamed from: b.b.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0031b<K, V> extends e<K, V> {
        C0031b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2231d;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2232e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f2229b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f2230c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f2231d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c<K, V> f2232e;

        c(K k, V v) {
            this.f2229b = k;
            this.f2230c = v;
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
            return this.f2229b.equals(cVar.f2229b) && this.f2230c.equals(cVar.f2230c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f2229b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f2230c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f2229b.hashCode() ^ this.f2230c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f2229b + "=" + this.f2230c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f2233b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2234c = true;

        d() {
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f2233b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f2232e;
                this.f2233b = cVar3;
                this.f2234c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f2234c) {
                this.f2234c = false;
                this.f2233b = b.this.f2225b;
            } else {
                c<K, V> cVar = this.f2233b;
                this.f2233b = cVar != null ? cVar.f2231d : null;
            }
            return this.f2233b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f2234c) {
                return b.this.f2225b != null;
            }
            c<K, V> cVar = this.f2233b;
            return (cVar == null || cVar.f2231d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f2236b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f2237c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f2236b = cVar2;
            this.f2237c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f2237c;
            c<K, V> cVar2 = this.f2236b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f2236b == cVar && cVar == this.f2237c) {
                this.f2237c = null;
                this.f2236b = null;
            }
            c<K, V> cVar2 = this.f2236b;
            if (cVar2 == cVar) {
                this.f2236b = b(cVar2);
            }
            if (this.f2237c == cVar) {
                this.f2237c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f2237c;
            this.f2237c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f2237c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0031b c0031b = new C0031b(this.f2226c, this.f2225b);
        this.f2227d.put(c0031b, Boolean.FALSE);
        return c0031b;
    }

    public Map.Entry<K, V> b() {
        return this.f2225b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f2225b;
        while (cVar != null && !cVar.f2229b.equals(k)) {
            cVar = cVar.f2231d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f2227d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f2226c;
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

    protected c<K, V> f(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f2228e++;
        c<K, V> cVar2 = this.f2226c;
        if (cVar2 == null) {
            this.f2225b = cVar;
            this.f2226c = cVar;
            return cVar;
        }
        cVar2.f2231d = cVar;
        cVar.f2232e = cVar2;
        this.f2226c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f2230c;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f2228e--;
        if (!this.f2227d.isEmpty()) {
            Iterator<f<K, V>> it = this.f2227d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f2232e;
        if (cVar != null) {
            cVar.f2231d = cVarC.f2231d;
        } else {
            this.f2225b = cVarC.f2231d;
        }
        c<K, V> cVar2 = cVarC.f2231d;
        if (cVar2 != null) {
            cVar2.f2232e = cVar;
        } else {
            this.f2226c = cVar;
        }
        cVarC.f2231d = null;
        cVarC.f2232e = null;
        return cVarC.f2230c;
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
        a aVar = new a(this.f2225b, this.f2226c);
        this.f2227d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f2228e;
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
