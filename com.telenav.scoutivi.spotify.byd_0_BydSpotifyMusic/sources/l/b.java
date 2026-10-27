package l;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    c<K, V> f7867e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c<K, V> f7868g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f7869h = new WeakHashMap<>();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f7870i = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // l.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f7874i;
        }

        @Override // l.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f7873h;
        }
    }

    /* JADX INFO: renamed from: l.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static class C0112b<K, V> extends e<K, V> {
        C0112b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // l.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f7873h;
        }

        @Override // l.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f7874i;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final K f7871e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final V f7872g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        c<K, V> f7873h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        c<K, V> f7874i;

        c(K k7, V v6) {
            this.f7871e = k7;
            this.f7872g = v6;
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
            return this.f7871e.equals(cVar.f7871e) && this.f7872g.equals(cVar.f7872g);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f7871e;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f7872g;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f7871e.hashCode() ^ this.f7872g.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v6) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f7871e + "=" + this.f7872g;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private c<K, V> f7875e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f7876g = true;

        d() {
        }

        @Override // l.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f7875e;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f7874i;
                this.f7875e = cVar3;
                this.f7876g = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f7876g) {
                this.f7876g = false;
                this.f7875e = b.this.f7867e;
            } else {
                c<K, V> cVar = this.f7875e;
                this.f7875e = cVar != null ? cVar.f7873h : null;
            }
            return this.f7875e;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f7876g) {
                return b.this.f7867e != null;
            }
            c<K, V> cVar = this.f7875e;
            return (cVar == null || cVar.f7873h == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c<K, V> f7878e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        c<K, V> f7879g;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f7878e = cVar2;
            this.f7879g = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f7879g;
            c<K, V> cVar2 = this.f7878e;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // l.b.f
        public void a(c<K, V> cVar) {
            if (this.f7878e == cVar && cVar == this.f7879g) {
                this.f7879g = null;
                this.f7878e = null;
            }
            c<K, V> cVar2 = this.f7878e;
            if (cVar2 == cVar) {
                this.f7878e = b(cVar2);
            }
            if (this.f7879g == cVar) {
                this.f7879g = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f7879g;
            this.f7879g = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f7879g != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> c() {
        C0112b c0112b = new C0112b(this.f7868g, this.f7867e);
        this.f7869h.put(c0112b, Boolean.FALSE);
        return c0112b;
    }

    public Map.Entry<K, V> d() {
        return this.f7867e;
    }

    protected c<K, V> e(K k7) {
        c<K, V> cVar = this.f7867e;
        while (cVar != null && !cVar.f7871e.equals(k7)) {
            cVar = cVar.f7873h;
        }
        return cVar;
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

    public b<K, V>.d f() {
        b<K, V>.d dVar = new d();
        this.f7869h.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> g() {
        return this.f7868g;
    }

    protected c<K, V> h(K k7, V v6) {
        c<K, V> cVar = new c<>(k7, v6);
        this.f7870i++;
        c<K, V> cVar2 = this.f7868g;
        if (cVar2 == null) {
            this.f7867e = cVar;
            this.f7868g = cVar;
            return cVar;
        }
        cVar2.f7873h = cVar;
        cVar.f7874i = cVar2;
        this.f7868g = cVar;
        return cVar;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            iHashCode += it.next().hashCode();
        }
        return iHashCode;
    }

    public V i(K k7, V v6) {
        c<K, V> cVarE = e(k7);
        if (cVarE != null) {
            return cVarE.f7872g;
        }
        h(k7, v6);
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f7867e, this.f7868g);
        this.f7869h.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public V j(K k7) {
        c<K, V> cVarE = e(k7);
        if (cVarE == null) {
            return null;
        }
        this.f7870i--;
        if (!this.f7869h.isEmpty()) {
            Iterator<f<K, V>> it = this.f7869h.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarE);
            }
        }
        c<K, V> cVar = cVarE.f7874i;
        if (cVar != null) {
            cVar.f7873h = cVarE.f7873h;
        } else {
            this.f7867e = cVarE.f7873h;
        }
        c<K, V> cVar2 = cVarE.f7873h;
        if (cVar2 != null) {
            cVar2.f7874i = cVar;
        } else {
            this.f7868g = cVar;
        }
        cVarE.f7873h = null;
        cVarE.f7874i = null;
        return cVarE.f7872g;
    }

    public int size() {
        return this.f7870i;
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
