package androidx.arch.core.b;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    c<K, V> f404c;
    private c<K, V> d;
    private WeakHashMap<f<K, V>, Boolean> e = new WeakHashMap<>();
    private int f = 0;

    /* JADX INFO: compiled from: caradapter-bug */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // androidx.arch.core.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.f;
        }

        @Override // androidx.arch.core.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.e;
        }
    }

    /* JADX INFO: renamed from: androidx.arch.core.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private static class C0007b<K, V> extends e<K, V> {
        C0007b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // androidx.arch.core.b.b.e
        c<K, V> b(c<K, V> cVar) {
            return cVar.e;
        }

        @Override // androidx.arch.core.b.b.e
        c<K, V> c(c<K, V> cVar) {
            return cVar.f;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @NonNull
        final K f405c;

        @NonNull
        final V d;
        c<K, V> e;
        c<K, V> f;

        c(@NonNull K k, @NonNull V v) {
            this.f405c = k;
            this.d = v;
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
            return this.f405c.equals(cVar.f405c) && this.d.equals(cVar.d);
        }

        @Override // java.util.Map.Entry
        @NonNull
        public K getKey() {
            return this.f405c;
        }

        @Override // java.util.Map.Entry
        @NonNull
        public V getValue() {
            return this.d;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.d.hashCode() ^ this.f405c.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f405c + "=" + this.d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: caradapter-bug */
    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private c<K, V> f406c;
        private boolean d = true;

        d() {
        }

        @Override // androidx.arch.core.b.b.f
        public void a(@NonNull c<K, V> cVar) {
            c<K, V> cVar2 = this.f406c;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f;
                this.f406c = cVar3;
                this.d = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            if (this.d) {
                this.d = false;
                this.f406c = b.this.f404c;
            } else {
                c<K, V> cVar = this.f406c;
                this.f406c = cVar != null ? cVar.e : null;
            }
            return this.f406c;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.d) {
                return b.this.f404c != null;
            }
            c<K, V> cVar = this.f406c;
            return (cVar == null || cVar.e == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c<K, V> f407c;
        c<K, V> d;

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.f407c = cVar2;
            this.d = cVar;
        }

        private c<K, V> e() {
            c<K, V> cVar = this.d;
            c<K, V> cVar2 = this.f407c;
            if (cVar == cVar2 || cVar2 == null) {
                return null;
            }
            return c(cVar);
        }

        @Override // androidx.arch.core.b.b.f
        public void a(@NonNull c<K, V> cVar) {
            if (this.f407c == cVar && cVar == this.d) {
                this.d = null;
                this.f407c = null;
            }
            c<K, V> cVar2 = this.f407c;
            if (cVar2 == cVar) {
                this.f407c = b(cVar2);
            }
            if (this.d == cVar) {
                this.d = e();
            }
        }

        abstract c<K, V> b(c<K, V> cVar);

        abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public Map.Entry<K, V> next() {
            c<K, V> cVar = this.d;
            this.d = e();
            return cVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.d != null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    interface f<K, V> {
        void a(@NonNull c<K, V> cVar);
    }

    public Iterator<Map.Entry<K, V>> a() {
        C0007b c0007b = new C0007b(this.d, this.f404c);
        this.e.put(c0007b, Boolean.FALSE);
        return c0007b;
    }

    public Map.Entry<K, V> b() {
        return this.f404c;
    }

    protected c<K, V> c(K k) {
        c<K, V> cVar = this.f404c;
        while (cVar != null && !cVar.f405c.equals(k)) {
            cVar = cVar.e;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.e.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public Map.Entry<K, V> e() {
        return this.d;
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

    protected c<K, V> f(@NonNull K k, @NonNull V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f++;
        c<K, V> cVar2 = this.d;
        if (cVar2 == null) {
            this.f404c = cVar;
            this.d = cVar;
            return cVar;
        }
        cVar2.e = cVar;
        cVar.f = cVar2;
        this.d = cVar;
        return cVar;
    }

    public V g(@NonNull K k, @NonNull V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.d;
        }
        f(k, v);
        return null;
    }

    public V h(@NonNull K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f--;
        if (!this.e.isEmpty()) {
            Iterator<f<K, V>> it = this.e.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f;
        if (cVar != null) {
            cVar.e = cVarC.e;
        } else {
            this.f404c = cVarC.e;
        }
        c<K, V> cVar2 = cVarC.e;
        if (cVar2 != null) {
            cVar2.f = cVarC.f;
        } else {
            this.d = cVarC.f;
        }
        cVarC.e = null;
        cVarC.f = null;
        return cVarC.d;
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
    @NonNull
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f404c, this.d);
        this.e.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public int size() {
        return this.f;
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
