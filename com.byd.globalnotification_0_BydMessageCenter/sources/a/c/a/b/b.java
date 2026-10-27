package a.c.a.b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class b<K, V> implements Iterable<Map.Entry<K, V>> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c<K, V> f425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c<K, V> f426b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakHashMap<f<K, V>, Boolean> f427c = new WeakHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f428d = 0;

    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f432d;
        }

        @Override // a.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f431c;
        }
    }

    /* JADX INFO: renamed from: a.c.a.b.b$b, reason: collision with other inner class name */
    public static class C0005b<K, V> extends e<K, V> {
        public C0005b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // a.c.a.b.b.e
        public c<K, V> b(c<K, V> cVar) {
            return cVar.f431c;
        }

        @Override // a.c.a.b.b.e
        public c<K, V> c(c<K, V> cVar) {
            return cVar.f432d;
        }
    }

    public static class c<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final K f429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final V f430b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c<K, V> f431c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c<K, V> f432d;

        public c(K k, V v) {
            this.f429a = k;
            this.f430b = v;
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
            return this.f429a.equals(cVar.f429a) && this.f430b.equals(cVar.f430b);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f429a;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f430b;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return this.f430b.hashCode() ^ this.f429a.hashCode();
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            return this.f429a + "=" + this.f430b;
        }
    }

    public class d implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c<K, V> f433a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f434b = true;

        public d() {
        }

        @Override // a.c.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVar2 = this.f433a;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.f432d;
                this.f433a = cVar3;
                this.f434b = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f434b) {
                return b.this.f425a != null;
            }
            c<K, V> cVar = this.f433a;
            return (cVar == null || cVar.f431c == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Object next() {
            c<K, V> cVar;
            if (this.f434b) {
                this.f434b = false;
                cVar = b.this.f425a;
            } else {
                c<K, V> cVar2 = this.f433a;
                cVar = cVar2 != null ? cVar2.f431c : null;
            }
            this.f433a = cVar;
            return cVar;
        }
    }

    public static abstract class e<K, V> implements Iterator<Map.Entry<K, V>>, f<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c<K, V> f436a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c<K, V> f437b;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.f436a = cVar2;
            this.f437b = cVar;
        }

        @Override // a.c.a.b.b.f
        public void a(c<K, V> cVar) {
            c<K, V> cVarC = null;
            if (this.f436a == cVar && cVar == this.f437b) {
                this.f437b = null;
                this.f436a = null;
            }
            c<K, V> cVar2 = this.f436a;
            if (cVar2 == cVar) {
                this.f436a = b(cVar2);
            }
            c<K, V> cVar3 = this.f437b;
            if (cVar3 == cVar) {
                c<K, V> cVar4 = this.f436a;
                if (cVar3 != cVar4 && cVar4 != null) {
                    cVarC = c(cVar3);
                }
                this.f437b = cVarC;
            }
        }

        public abstract c<K, V> b(c<K, V> cVar);

        public abstract c<K, V> c(c<K, V> cVar);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f437b != null;
        }

        @Override // java.util.Iterator
        public Object next() {
            c<K, V> cVar = this.f437b;
            c<K, V> cVar2 = this.f436a;
            this.f437b = (cVar == cVar2 || cVar2 == null) ? null : c(cVar);
            return cVar;
        }
    }

    public interface f<K, V> {
        void a(c<K, V> cVar);
    }

    public c<K, V> c(K k) {
        c<K, V> cVar = this.f425a;
        while (cVar != null && !cVar.f429a.equals(k)) {
            cVar = cVar.f431c;
        }
        return cVar;
    }

    public b<K, V>.d d() {
        b<K, V>.d dVar = new d();
        this.f427c.put(dVar, Boolean.FALSE);
        return dVar;
    }

    public c<K, V> e(K k, V v) {
        c<K, V> cVar = new c<>(k, v);
        this.f428d++;
        c<K, V> cVar2 = this.f426b;
        if (cVar2 == null) {
            this.f425a = cVar;
        } else {
            cVar2.f431c = cVar;
            cVar.f432d = cVar2;
        }
        this.f426b = cVar;
        return cVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r1.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((a.c.a.b.b.e) r6).hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L4
            return r0
        L4:
            boolean r1 = r6 instanceof a.c.a.b.b
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            a.c.a.b.b r6 = (a.c.a.b.b) r6
            int r1 = r5.f428d
            int r3 = r6.f428d
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r5 = r5.iterator()
            java.util.Iterator r6 = r6.iterator()
        L1b:
            r1 = r5
            a.c.a.b.b$e r1 = (a.c.a.b.b.e) r1
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L44
            r3 = r6
            a.c.a.b.b$e r3 = (a.c.a.b.b.e) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L44
            java.lang.Object r1 = r1.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r3 = r3.next()
            if (r1 != 0) goto L3b
            if (r3 != 0) goto L43
        L3b:
            if (r1 == 0) goto L1b
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L1b
        L43:
            return r2
        L44:
            boolean r5 = r1.hasNext()
            if (r5 != 0) goto L53
            a.c.a.b.b$e r6 = (a.c.a.b.b.e) r6
            boolean r5 = r6.hasNext()
            if (r5 != 0) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.c.a.b.b.equals(java.lang.Object):boolean");
    }

    public V f(K k, V v) {
        c<K, V> cVarC = c(k);
        if (cVarC != null) {
            return cVarC.f430b;
        }
        e(k, v);
        return null;
    }

    public V g(K k) {
        c<K, V> cVarC = c(k);
        if (cVarC == null) {
            return null;
        }
        this.f428d--;
        if (!this.f427c.isEmpty()) {
            Iterator<f<K, V>> it = this.f427c.keySet().iterator();
            while (it.hasNext()) {
                it.next().a(cVarC);
            }
        }
        c<K, V> cVar = cVarC.f432d;
        if (cVar != null) {
            cVar.f431c = cVarC.f431c;
        } else {
            this.f425a = cVarC.f431c;
        }
        c<K, V> cVar2 = cVarC.f431c;
        if (cVar2 != null) {
            cVar2.f432d = cVarC.f432d;
        } else {
            this.f426b = cVarC.f432d;
        }
        cVarC.f431c = null;
        cVarC.f432d = null;
        return cVarC.f430b;
    }

    public int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int iHashCode = 0;
        while (true) {
            e eVar = (e) it;
            if (!eVar.hasNext()) {
                return iHashCode;
            }
            iHashCode += ((Map.Entry) eVar.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.f425a, this.f426b);
        this.f427c.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (true) {
            e eVar = (e) it;
            if (!eVar.hasNext()) {
                sbD.append("]");
                return sbD.toString();
            }
            sbD.append(((Map.Entry) eVar.next()).toString());
            if (eVar.hasNext()) {
                sbD.append(", ");
            }
        }
    }
}
