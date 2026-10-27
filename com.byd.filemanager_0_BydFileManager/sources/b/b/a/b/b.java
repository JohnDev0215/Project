package b.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f1706b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f1707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f1708d = new WeakHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1709e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f1713e;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f1712d;
        }
    }

    /* JADX INFO: renamed from: b.b.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0028b<K, V> extends e<K, V> {
        C0028b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f1712d;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f1713e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f1710b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f1711c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f1712d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c<K, V> f1713e;

        c(K k, V v) {
            this.f1710b = k;
            this.f1711c = v;
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
            return this.f1710b.equals(cVar.f1710b) && this.f1711c.equals(cVar.f1711c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f1710b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f1711c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f1710b.hashCode() ^ this.f1711c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f1710b + "=" + this.f1711c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f1714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1715c = true;

        d() {
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f1714b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f1713e;
                this.f1714b = cVar3;
                this.f1715c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f1715c) {
                this.f1715c = false;
                this.f1714b = b.this.f1706b;
            } else {
                c<K, V> cVar = this.f1714b;
                this.f1714b = cVar != null ? cVar.f1712d : null;
            }
            return this.f1714b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f1715c) {
                return b.this.f1706b != null;
            }
            c<K, V> cVar = this.f1714b;
            return (cVar == null || cVar.f1712d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f1717b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f1718c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f1717b = cVar2;
            this.f1718c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f1718c;
            c<K, V> cVar2 = this.f1717b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f1717b == cVar && cVar == this.f1718c) {
                this.f1718c = null;
                this.f1717b = null;
            }
            c<K, V> cVar2 = this.f1717b;
            if (cVar2 == cVar) {
                this.f1717b = b(cVar2);
            }
            if (this.f1718c == cVar) {
                this.f1718c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f1718c;
            this.f1718c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1718c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0028b c0028b = new C0028b(this.f1707c, this.f1706b);
        this.f1708d.put(c0028b, Boolean.FALSE);
        return c0028b;
    }

    public Map.Entry<K, V> b() {
        return this.f1706b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f1706b;
        while (cVar != null && !cVar.f1710b.equals(k)) {
            cVar = cVar.f1712d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f1708d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f1707c;
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
        this.f1709e++;
        c<K, V> cVar2 = this.f1707c;
        if (cVar2 == null) {
            this.f1706b = cVar;
            this.f1707c = cVar;
            return cVar;
        }
        cVar2.f1712d = cVar;
        cVar.f1713e = cVar2;
        this.f1707c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f1711c;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f1709e--;
        if (!this.f1708d.isEmpty()) {
            Iterator<f<K, V>> it = this.f1708d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f1713e;
        if (cVar != null) {
            cVar.f1712d = cVarC.f1712d;
        } else {
            this.f1706b = cVarC.f1712d;
        }
        c<K, V> cVar2 = cVarC.f1712d;
        if (cVar2 != null) {
            cVar2.f1713e = cVar;
        } else {
            this.f1707c = cVar;
        }
        cVarC.f1712d = null;
        cVarC.f1713e = null;
        return cVarC.f1711c;
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
        a aVar = new a(this.f1706b, this.f1707c);
        this.f1708d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f1709e;
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
