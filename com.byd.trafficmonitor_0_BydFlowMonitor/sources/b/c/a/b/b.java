package b.c.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c<K, V> f1894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c<K, V> f1895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<f<K, V>, Boolean> f1896d = new WeakHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1897e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f1901e;
        }

        @Override // b.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f1900d;
        }
    }

    /* JADX INFO: renamed from: b.c.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class C0022b<K, V> extends e<K, V> {
        public C0022b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f1900d;
        }

        @Override // b.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f1901e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final K f1898b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final V f1899c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f1900d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c<K, V> f1901e;

        public c(K k, V v) {
            this.f1898b = k;
            this.f1899c = v;
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
            return this.f1898b.equals(cVar.f1898b) && this.f1899c.equals(cVar.f1899c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f1898b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f1899c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f1898b.hashCode() ^ this.f1899c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f1898b + "=" + this.f1899c;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f1902b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1903c = true;

        public d() {
        }

        @Override // b.c.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f1902b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f1901e;
                this.f1902b = cVar3;
                this.f1903c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f1903c) {
                this.f1903c = false;
                this.f1902b = b.this.f1894b;
            } else {
                c<K, V> cVar = this.f1902b;
                this.f1902b = cVar != null ? cVar.f1900d : null;
            }
            return this.f1902b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f1903c) {
                return b.this.f1894b != null;
            }
            c<K, V> cVar = this.f1902b;
            return (cVar == null || cVar.f1900d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f1905b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c<K, V> f1906c;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f1905b = cVar2;
            this.f1906c = cVar;
        }

        @Override // b.c.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f1905b == cVar && cVar == this.f1906c) {
                this.f1906c = null;
                this.f1905b = null;
            }
            c<K, V> cVar2 = this.f1905b;
            if (cVar2 == cVar) {
                this.f1905b = b(cVar2);
            }
            if (this.f1906c == cVar) {
                this.f1906c = e();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f1906c;
            this.f1906c = e();
            return cVar;
        }

        public final c<K, V> e() {
            c<K, V> cVar = this.f1906c;
            c<K, V> cVar2 = this.f1905b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f1906c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0022b c0022b = new C0022b(this.f1895c, this.f1894b);
        this.f1896d.put(c0022b, Boolean.FALSE);
        return c0022b;
    }

    public Map.Entry<K, V> b() {
        return this.f1894b;
    }

    public c<K, V> c(K k) {
        c<K, V> cVar = this.f1894b;
        while (cVar != null && !cVar.f1898b.equals(k)) {
            cVar = cVar.f1900d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f1896d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f1895c;
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
        this.f1897e++;
        c<K, V> cVar2 = this.f1895c;
        if (cVar2 == null) {
            this.f1894b = cVar;
            this.f1895c = cVar;
            return cVar;
        }
        cVar2.f1900d = cVar;
        cVar.f1901e = cVar2;
        this.f1895c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f1899c;
        }
        f(k, v);
        return null;
    }

    public V h(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f1897e--;
        if (!this.f1896d.isEmpty()) {
            Iterator<f<K, V>> it = this.f1896d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f1901e;
        if (cVar != null) {
            cVar.f1900d = cVarC.f1900d;
        } else {
            this.f1894b = cVarC.f1900d;
        }
        c<K, V> cVar2 = cVarC.f1900d;
        if (cVar2 != null) {
            cVar2.f1901e = cVar;
        } else {
            this.f1895c = cVar;
        }
        cVarC.f1900d = null;
        cVarC.f1901e = null;
        return cVarC.f1899c;
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
        a aVar = new a(this.f1894b, this.f1895c);
        this.f1896d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f1897e;
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
