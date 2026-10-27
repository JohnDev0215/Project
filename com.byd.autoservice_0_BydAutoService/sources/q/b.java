package q;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c<K, V> f4539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c<K, V> f4540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WeakHashMap<f<K, V>, Boolean> f4541d = new WeakHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4542e = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // q.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f4546e;
        }

        @Override // q.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f4545d;
        }
    }

    /* JADX INFO: renamed from: q.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class C0052b<K, V> extends e<K, V> {
        public C0052b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // q.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f4545d;
        }

        @Override // q.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f4546e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final K f4543b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final V f4544c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f4545d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c<K, V> f4546e;

        public c(K k3, V v3) {
            this.f4543b = k3;
            this.f4544c = v3;
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
            return this.f4543b.equals(cVar.f4543b) && this.f4544c.equals(cVar.f4544c);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f4543b;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f4544c;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f4543b.hashCode() ^ this.f4544c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v3) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f4543b + "=" + this.f4544c;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f4547b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4548c = true;

        public d() {
        }

        @Override // q.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f4547b;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f4546e;
                this.f4547b = cVar3;
                this.f4548c = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.f4548c) {
                this.f4548c = false;
                this.f4547b = b.this.f4539b;
            } else {
                c<K, V> cVar = this.f4547b;
                this.f4547b = cVar != null ? cVar.f4545d : null;
            }
            return this.f4547b;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f4548c) {
                return b.this.f4539b != null;
            }
            c<K, V> cVar = this.f4547b;
            return (cVar == null || cVar.f4545d == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f4550b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c<K, V> f4551c;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f4550b = cVar2;
            this.f4551c = cVar;
        }

        @Override // q.b.f
        public void a(c<K, V> cVar) {
            if (this.f4550b == cVar && cVar == this.f4551c) {
                this.f4551c = null;
                this.f4550b = null;
            }
            c<K, V> cVar2 = this.f4550b;
            if (cVar2 == cVar) {
                this.f4550b = b(cVar2);
            }
            if (this.f4551c == cVar) {
                this.f4551c = e();
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.f4551c;
            this.f4551c = e();
            return cVar;
        }

        public final c<K, V> e() {
            c<K, V> cVar = this.f4551c;
            c<K, V> cVar2 = this.f4550b;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f4551c != null;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0052b c0052b = new C0052b(this.f4540c, this.f4539b);
        this.f4541d.put(c0052b, Boolean.FALSE);
        return c0052b;
    }

    public Map.Entry<K, V> b() {
        return this.f4539b;
    }

    public c<K, V> c(K k3) {
        c<K, V> cVar = this.f4539b;
        while (cVar != null && !cVar.f4543b.equals(k3)) {
            cVar = cVar.f4545d;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f4541d.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.f4540c;
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

    public c<K, V> f(K k3, V v3) {
        c<K, V> cVar = new c<>(k3, v3);
        this.f4542e++;
        c<K, V> cVar2 = this.f4540c;
        if (cVar2 == null) {
            this.f4539b = cVar;
            this.f4540c = cVar;
            return cVar;
        }
        cVar2.f4545d = cVar;
        cVar.f4546e = cVar2;
        this.f4540c = cVar;
        return cVar;
    }

    public V g(K k3, V v3) {
        c<K, V> cVarC = c(k3);
        if (cVarC != null) {
            return cVarC.f4544c;
        }
        f(k3, v3);
        return null;
    }

    public V h(K k3) {
        c<K, V> cVarC = c(k3);
        if (cVarC == null) {
            return null;
        }
        this.f4542e--;
        if (!this.f4541d.isEmpty()) {
            Iterator<f<K, V>> it = this.f4541d.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f4546e;
        if (cVar != null) {
            cVar.f4545d = cVarC.f4545d;
        } else {
            this.f4539b = cVarC.f4545d;
        }
        c<K, V> cVar2 = cVarC.f4545d;
        if (cVar2 != null) {
            cVar2.f4546e = cVar;
        } else {
            this.f4540c = cVar;
        }
        cVarC.f4545d = null;
        cVarC.f4546e = null;
        return cVarC.f4544c;
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
        a aVar = new a(this.f4539b, this.f4540c);
        this.f4541d.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f4542e;
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
