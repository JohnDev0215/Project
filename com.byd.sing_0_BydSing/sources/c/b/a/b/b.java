package c.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f2072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f2073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f2074d = new WeakHashMap<>();
    private int e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // c.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.e;
        }

        @Override // c.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f2077d;
        }
    }

    /* JADX INFO: renamed from: c.b.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0057b<K, V> extends e<K, V> {
        C0057b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // c.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f2077d;
        }

        @Override // c.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f2075b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f2076c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f2077d;
        c<K, V> e;

        c(K k, V v) {
            this.f2075b = k;
            this.f2076c = v;
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
            return this.f2075b.equals(cVar.f2075b) && this.f2076c.equals(cVar.f2076c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f2075b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f2076c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f2075b.hashCode() ^ this.f2076c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f2075b + "=" + this.f2076c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f2078b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2079c = true;

        d() {
        }

        @Override // c.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f2078b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.e;
                this.f2078b = cVar3;
                this.f2079c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f2079c) {
                this.f2079c = false;
                this.f2078b = b.this.f2072b;
            } else {
                c<K, V> cVar = this.f2078b;
                this.f2078b = cVar != null ? cVar.f2077d : null;
            }
            return this.f2078b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f2079c) {
                return b.this.f2072b != null;
            }
            c<K, V> cVar = this.f2078b;
            return (cVar == null || cVar.f2077d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f2081b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f2082c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f2081b = cVar2;
            this.f2082c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f2082c;
            c<K, V> cVar2 = this.f2081b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // c.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f2081b == cVar && cVar == this.f2082c) {
                this.f2082c = null;
                this.f2081b = null;
            }
            c<K, V> cVar2 = this.f2081b;
            if (cVar2 == cVar) {
                this.f2081b = b(cVar2);
            }
            if (this.f2082c == cVar) {
                this.f2082c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f2082c;
            this.f2082c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f2082c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0057b c0057b = new C0057b(this.f2073c, this.f2072b);
        this.f2074d.put(c0057b, Boolean.FALSE);
        return c0057b;
    }

    public Map.Entry<K, V> b() {
        return this.f2072b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f2072b;
        while (cVar != null && !cVar.f2075b.equals(k)) {
            cVar = cVar.f2077d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f2074d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f2073c;
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
        c<K, V> cVar2 = this.f2073c;
        if (cVar2 == null) {
            this.f2072b = cVar;
            this.f2073c = cVar;
            return cVar;
        }
        cVar2.f2077d = cVar;
        cVar.e = cVar2;
        this.f2073c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f2076c;
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
        if (!this.f2074d.isEmpty()) {
            Iterator<f<K, V>> it = this.f2074d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.e;
        if (cVar != null) {
            cVar.f2077d = cVarC.f2077d;
        } else {
            this.f2072b = cVarC.f2077d;
        }
        c<K, V> cVar2 = cVarC.f2077d;
        if (cVar2 != null) {
            cVar2.e = cVar;
        } else {
            this.f2073c = cVar;
        }
        cVarC.f2077d = null;
        cVarC.e = null;
        return cVarC.f2076c;
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
        a aVar = new a(this.f2072b, this.f2073c);
        this.f2074d.put(aVar, Boolean.FALSE);
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
