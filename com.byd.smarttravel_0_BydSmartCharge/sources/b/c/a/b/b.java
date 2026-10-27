package b.c.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c<K, V> f856b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c<K, V> f857c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<f<K, V>, Boolean> f858d = new WeakHashMap<>();
    public int e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.e;
        }

        @Override // b.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f861d;
        }
    }

    /* JADX INFO: renamed from: b.c.a.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class C0008b<K, V> extends e<K, V> {
        public C0008b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // b.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f861d;
        }

        @Override // b.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final K f859b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final V f860c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f861d;
        public c<K, V> e;

        public c(K k, V v) {
            this.f859b = k;
            this.f860c = v;
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
            return this.f859b.equals(cVar.f859b) && this.f860c.equals(cVar.f860c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f859b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f860c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f859b.hashCode() ^ this.f860c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f859b + "=" + this.f860c;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f862b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f863c = true;

        public d() {
        }

        @Override // b.c.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f862b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.e;
                this.f862b = cVar3;
                this.f863c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f863c) {
                this.f863c = false;
                this.f862b = b.this.f856b;
            } else {
                c<K, V> cVar = this.f862b;
                this.f862b = cVar != null ? cVar.f861d : null;
            }
            return this.f862b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f863c) {
                return b.this.f856b != null;
            }
            c<K, V> cVar = this.f862b;
            return (cVar == null || cVar.f861d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f865b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c<K, V> f866c;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f865b = cVar2;
            this.f866c = cVar;
        }

        @Override // b.c.a.b.b.f
        public void a(c<K, V> cVar) {
            if (this.f865b == cVar && cVar == this.f866c) {
                this.f866c = null;
                this.f865b = null;
            }
            c<K, V> cVar2 = this.f865b;
            if (cVar2 == cVar) {
                this.f865b = b(cVar2);
            }
            if (this.f866c == cVar) {
                this.f866c = e();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f866c;
            this.f866c = e();
            return cVar;
        }

        public final c<K, V> e() {
            c<K, V> cVar = this.f866c;
            c<K, V> cVar2 = this.f865b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f866c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0008b c0008b = new C0008b(this.f857c, this.f856b);
        this.f858d.put(c0008b, Boolean.FALSE);
        return c0008b;
    }

    public Map.Entry<K, V> b() {
        return this.f856b;
    }

    public c<K, V> c(K k) {
        c<K, V> cVar = this.f856b;
        while (cVar != null && !cVar.f859b.equals(k)) {
            cVar = cVar.f861d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f858d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f857c;
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
        this.e++;
        c<K, V> cVar2 = this.f857c;
        if (cVar2 == null) {
            this.f856b = cVar;
            this.f857c = cVar;
            return cVar;
        }
        cVar2.f861d = cVar;
        cVar.e = cVar2;
        this.f857c = cVar;
        return cVar;
    }

    public V g(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f860c;
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
        if (!this.f858d.isEmpty()) {
            Iterator<f<K, V>> it = this.f858d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.e;
        if (cVar != null) {
            cVar.f861d = cVarC.f861d;
        } else {
            this.f856b = cVarC.f861d;
        }
        c<K, V> cVar2 = cVarC.f861d;
        if (cVar2 != null) {
            cVar2.e = cVar;
        } else {
            this.f857c = cVar;
        }
        cVarC.f861d = null;
        cVarC.e = null;
        return cVarC.f860c;
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
        a aVar = new a(this.f856b, this.f857c);
        this.f858d.put(aVar, Boolean.FALSE);
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
