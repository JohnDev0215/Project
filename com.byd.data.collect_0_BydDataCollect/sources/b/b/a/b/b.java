package b.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f2235b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f2236c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f2237d = new WeakHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2238e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2242e;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2241d;
        }
    }

    /* JADX INFO: renamed from: b.b.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0035b<K, V> extends e<K, V> {
        C0035b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2241d;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2242e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f2239b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f2240c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f2241d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c<K, V> f2242e;

        c(K k, V v) {
            this.f2239b = k;
            this.f2240c = v;
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
            return this.f2239b.equals(cVar.f2239b) && this.f2240c.equals(cVar.f2240c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f2239b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f2240c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f2239b.hashCode() ^ this.f2240c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f2239b + "=" + this.f2240c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f2243b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2244c = true;

        d() {
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f2243b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f2242e;
                this.f2243b = cVar3;
                this.f2244c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f2244c) {
                this.f2244c = false;
                this.f2243b = b.this.f2235b;
            } else {
                c<K, V> cVar = this.f2243b;
                this.f2243b = cVar != null ? cVar.f2241d : null;
            }
            return this.f2243b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f2244c) {
                return b.this.f2235b != null;
            }
            c<K, V> cVar = this.f2243b;
            return (cVar == null || cVar.f2241d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f2246b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f2247c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f2246b = cVar2;
            this.f2247c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f2247c;
            c<K, V> cVar2 = this.f2246b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f2246b == cVar && cVar == this.f2247c) {
                this.f2247c = null;
                this.f2246b = null;
            }
            c<K, V> cVar2 = this.f2246b;
            if (cVar2 == cVar) {
                this.f2246b = b(cVar2);
            }
            if (this.f2247c == cVar) {
                this.f2247c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f2247c;
            this.f2247c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f2247c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0035b c0035b = new C0035b(this.f2236c, this.f2235b);
        this.f2237d.put(c0035b, Boolean.FALSE);
        return c0035b;
    }

    public Map.Entry<K, V> b() {
        return this.f2235b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f2235b;
        while (cVar != null && !cVar.f2239b.equals(k)) {
            cVar = cVar.f2241d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f2237d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f2236c;
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
        this.f2238e++;
        c<K, V> cVar2 = this.f2236c;
        if (cVar2 == null) {
            this.f2235b = cVar;
            this.f2236c = cVar;
            return cVar;
        }
        cVar2.f2241d = cVar;
        cVar.f2242e = cVar2;
        this.f2236c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f2240c;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f2238e--;
        if (!this.f2237d.isEmpty()) {
            Iterator<f<K, V>> it = this.f2237d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f2242e;
        if (cVar != null) {
            cVar.f2241d = cVarC.f2241d;
        } else {
            this.f2235b = cVarC.f2241d;
        }
        c<K, V> cVar2 = cVarC.f2241d;
        if (cVar2 != null) {
            cVar2.f2242e = cVar;
        } else {
            this.f2236c = cVar;
        }
        cVarC.f2241d = null;
        cVarC.f2242e = null;
        return cVarC.f2240c;
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
        a aVar = new a(this.f2235b, this.f2236c);
        this.f2237d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f2238e;
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
