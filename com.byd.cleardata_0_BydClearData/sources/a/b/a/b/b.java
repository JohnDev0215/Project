package a.b.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    c<K, V> f106b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c<K, V> f107c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private WeakHashMap<f<K, V>, Boolean> f108d = new WeakHashMap<>();
    private int e = 0;

    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.e;
        }

        @Override // a.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f111d;
        }
    }

    /* JADX INFO: renamed from: a.b.a.b.b$b, reason: collision with other inner class name */
    private static class C0004b<K, V> extends e<K, V> {
        C0004b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.b.a.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f111d;
        }

        @Override // a.b.a.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.e;
        }
    }

    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final K f109b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final V f110c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        c<K, V> f111d;
        c<K, V> e;

        c(K k, V v) {
            this.f109b = k;
            this.f110c = v;
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
            return this.f109b.equals(cVar.f109b) && this.f110c.equals(cVar.f110c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f109b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f110c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f109b.hashCode() ^ this.f110c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f109b + "=" + this.f110c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private c<K, V> f112b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f113c = true;

        d() {
        }

        @Override // a.b.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f112b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.e;
                this.f112b = cVar3;
                this.f113c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar;
            if (this.f113c) {
                this.f113c = false;
                cVar = b.this.f106b;
            } else {
                c<K, V> cVar2 = this.f112b;
                cVar = cVar2 != null ? cVar2.f111d : null;
            }
            this.f112b = cVar;
            return this.f112b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f113c) {
                return b.this.f106b != null;
            }
            c<K, V> cVar = this.f112b;
            return (cVar == null || cVar.f111d == null) ? false : true;
        }
    }

    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        c<K, V> f115b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f116c;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f115b = cVar2;
            this.f116c = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.f116c;
            c<K, V> cVar2 = this.f115b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // a.b.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f115b == cVar && cVar == this.f116c) {
                this.f116c = null;
                this.f115b = null;
            }
            c<K, V> cVar2 = this.f115b;
            if (cVar2 == cVar) {
                this.f115b = b(cVar2);
            }
            if (this.f116c == cVar) {
                this.f116c = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f116c;
            this.f116c = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f116c != null;
        }
    }

    interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0004b c0004b = new C0004b(this.f107c, this.f106b);
        this.f108d.put(c0004b, Boolean.FALSE);
        return c0004b;
    }

    public Map.Entry<K, V> b() {
        return this.f106b;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f106b;
        while (cVar != null && !cVar.f109b.equals(k)) {
            cVar = cVar.f111d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f108d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f107c;
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
        c<K, V> cVar2 = this.f107c;
        if (cVar2 == null) {
            this.f106b = cVar;
        } else {
            cVar2.f111d = cVar;
            cVar.e = cVar2;
        }
        this.f107c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f110c;
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
        if (!this.f108d.isEmpty()) {
            Iterator<f<K, V>> it = this.f108d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.e;
        c<K, V> cVar2 = cVarC.f111d;
        if (cVar != null) {
            cVar.f111d = cVar2;
        } else {
            this.f106b = cVar2;
        }
        c<K, V> cVar3 = cVarC.f111d;
        if (cVar3 != null) {
            cVar3.e = cVar;
        } else {
            this.f107c = cVar;
        }
        cVarC.f111d = null;
        cVarC.e = null;
        return cVarC.f110c;
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
        a aVar = new a(this.f106b, this.f107c);
        this.f108d.put(aVar, Boolean.FALSE);
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
