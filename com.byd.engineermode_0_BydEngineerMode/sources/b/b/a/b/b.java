package b.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f1585b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f1586c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f1587d = new WeakHashMap<>();
    private int e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.e;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f1590d;
        }
    }

    /* JADX INFO: renamed from: b.b.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0032b<K, V> extends e<K, V> {
        C0032b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f1590d;
        }

        @Override // b.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f1588b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f1589c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f1590d;
        c<K, V> e;

        c(K k, V v) {
            this.f1588b = k;
            this.f1589c = v;
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
            return this.f1588b.equals(cVar.f1588b) && this.f1589c.equals(cVar.f1589c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f1588b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f1589c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f1588b.hashCode() ^ this.f1589c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f1588b + "=" + this.f1589c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f1591b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1592c = true;

        d() {
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f1591b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.e;
                this.f1591b = cVar3;
                this.f1592c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f1592c) {
                this.f1592c = false;
                this.f1591b = b.this.f1585b;
            } else {
                c<K, V> cVar = this.f1591b;
                this.f1591b = cVar != null ? cVar.f1590d : null;
            }
            return this.f1591b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f1592c) {
                return b.this.f1585b != null;
            }
            c<K, V> cVar = this.f1591b;
            return (cVar == null || cVar.f1590d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f1594b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f1595c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f1594b = cVar2;
            this.f1595c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f1595c;
            c<K, V> cVar2 = this.f1594b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // b.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f1594b == cVar && cVar == this.f1595c) {
                this.f1595c = null;
                this.f1594b = null;
            }
            c<K, V> cVar2 = this.f1594b;
            if (cVar2 == cVar) {
                this.f1594b = b(cVar2);
            }
            if (this.f1595c == cVar) {
                this.f1595c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f1595c;
            this.f1595c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1595c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0032b c0032b = new C0032b(this.f1586c, this.f1585b);
        this.f1587d.put(c0032b, Boolean.FALSE);
        return c0032b;
    }

    public Map.Entry<K, V> b() {
        return this.f1585b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f1585b;
        while (cVar != null && !cVar.f1588b.equals(k)) {
            cVar = cVar.f1590d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f1587d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f1586c;
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
        this.e++;
        c<K, V> cVar2 = this.f1586c;
        if (cVar2 == null) {
            this.f1585b = cVar;
            this.f1586c = cVar;
            return cVar;
        }
        cVar2.f1590d = cVar;
        cVar.e = cVar2;
        this.f1586c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f1589c;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.e--;
        if (!this.f1587d.isEmpty()) {
            Iterator<f<K, V>> it = this.f1587d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.e;
        if (cVar != null) {
            cVar.f1590d = cVarC.f1590d;
        } else {
            this.f1585b = cVarC.f1590d;
        }
        c<K, V> cVar2 = cVarC.f1590d;
        if (cVar2 != null) {
            cVar2.e = cVar;
        } else {
            this.f1586c = cVar;
        }
        cVarC.f1590d = null;
        cVarC.e = null;
        return cVarC.f1589c;
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
        a aVar = new a(this.f1585b, this.f1586c);
        this.f1587d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.e;
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
