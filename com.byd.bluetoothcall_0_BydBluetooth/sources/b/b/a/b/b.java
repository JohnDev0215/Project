package b.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f2088b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f2089c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f2090d = new WeakHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2091e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2095e;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2094d;
        }
    }

    /* JADX INFO: renamed from: b.b.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0041b<K, V> extends e<K, V> {
        C0041b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2094d;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2095e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f2092b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f2093c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f2094d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c<K, V> f2095e;

        c(K k, V v) {
            this.f2092b = k;
            this.f2093c = v;
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
            return this.f2092b.equals(cVar.f2092b) && this.f2093c.equals(cVar.f2093c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f2092b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f2093c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f2092b.hashCode() ^ this.f2093c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f2092b + "=" + this.f2093c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f2096b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2097c = true;

        d() {
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f2096b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f2095e;
                this.f2096b = cVar3;
                this.f2097c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f2097c) {
                this.f2097c = false;
                this.f2096b = b.this.f2088b;
            } else {
                c<K, V> cVar = this.f2096b;
                this.f2096b = cVar != null ? cVar.f2094d : null;
            }
            return this.f2096b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f2097c) {
                return b.this.f2088b != null;
            }
            c<K, V> cVar = this.f2096b;
            return (cVar == null || cVar.f2094d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f2099b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f2100c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f2099b = cVar2;
            this.f2100c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f2100c;
            c<K, V> cVar2 = this.f2099b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f2099b == cVar && cVar == this.f2100c) {
                this.f2100c = null;
                this.f2099b = null;
            }
            c<K, V> cVar2 = this.f2099b;
            if (cVar2 == cVar) {
                this.f2099b = b(cVar2);
            }
            if (this.f2100c == cVar) {
                this.f2100c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f2100c;
            this.f2100c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f2100c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0041b c0041b = new C0041b(this.f2089c, this.f2088b);
        this.f2090d.put(c0041b, Boolean.FALSE);
        return c0041b;
    }

    public Map.Entry<K, V> b() {
        return this.f2088b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f2088b;
        while (cVar != null && !cVar.f2092b.equals(k)) {
            cVar = cVar.f2094d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f2090d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f2089c;
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
        this.f2091e++;
        c<K, V> cVar2 = this.f2089c;
        if (cVar2 == null) {
            this.f2088b = cVar;
            this.f2089c = cVar;
            return cVar;
        }
        cVar2.f2094d = cVar;
        cVar.f2095e = cVar2;
        this.f2089c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f2093c;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f2091e--;
        if (!this.f2090d.isEmpty()) {
            Iterator<f<K, V>> it = this.f2090d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f2095e;
        if (cVar != null) {
            cVar.f2094d = cVarC.f2094d;
        } else {
            this.f2088b = cVarC.f2094d;
        }
        c<K, V> cVar2 = cVarC.f2094d;
        if (cVar2 != null) {
            cVar2.f2095e = cVar;
        } else {
            this.f2089c = cVar;
        }
        cVarC.f2094d = null;
        cVarC.f2095e = null;
        return cVarC.f2093c;
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
        a aVar = new a(this.f2088b, this.f2089c);
        this.f2090d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f2091e;
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
