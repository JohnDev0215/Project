package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SafeIterableMap.java */
/* JADX INFO: loaded from: classes.dex */
public class kd2<K, V> implements Iterable<Map.Entry<K, V>> {
    public c<K, V> e;
    public c<K, V> f;
    public final WeakHashMap<f<K, V>, Boolean> g = new WeakHashMap<>();
    public int h = 0;

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class a<K, V> extends e<K, V> {
        public a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // kd2.e
        public final c<K, V> c(c<K, V> cVar) {
            return cVar.h;
        }

        @Override // kd2.e
        public final c<K, V> d(c<K, V> cVar) {
            return cVar.g;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class b<K, V> extends e<K, V> {
        public b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        @Override // kd2.e
        public final c<K, V> c(c<K, V> cVar) {
            return cVar.g;
        }

        @Override // kd2.e
        public final c<K, V> d(c<K, V> cVar) {
            return cVar.h;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static class c<K, V> implements Map.Entry<K, V> {
        public final K e;
        public final V f;
        public c<K, V> g;
        public c<K, V> h;

        public c(K k, V v) {
            this.e = k;
            this.f = v;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.e.equals(cVar.e) && this.f.equals(cVar.f);
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            return this.e;
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return this.f;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            return this.f.hashCode() ^ this.e.hashCode();
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public final String toString() {
            return this.e + "=" + this.f;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public class d extends f<K, V> implements Iterator<Map.Entry<K, V>> {
        public c<K, V> e;
        public boolean f = true;

        public d() {
        }

        @Override // kd2.f
        public final void b(c<K, V> cVar) {
            c<K, V> cVar2 = this.e;
            if (cVar == cVar2) {
                c<K, V> cVar3 = cVar2.h;
                this.e = cVar3;
                this.f = cVar3 == null;
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.f) {
                return kd2.this.e != null;
            }
            c<K, V> cVar = this.e;
            return (cVar == null || cVar.g == null) ? false : true;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (this.f) {
                this.f = false;
                this.e = kd2.this.e;
            } else {
                c<K, V> cVar = this.e;
                this.e = cVar != null ? cVar.g : null;
            }
            return this.e;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class e<K, V> extends f<K, V> implements Iterator<Map.Entry<K, V>> {
        public c<K, V> e;
        public c<K, V> f;

        public e(c<K, V> cVar, c<K, V> cVar2) {
            this.e = cVar2;
            this.f = cVar;
        }

        @Override // kd2.f
        public final void b(c<K, V> cVar) {
            c<K, V> cVarD = null;
            if (this.e == cVar && cVar == this.f) {
                this.f = null;
                this.e = null;
            }
            c<K, V> cVar2 = this.e;
            if (cVar2 == cVar) {
                this.e = c(cVar2);
            }
            c<K, V> cVar3 = this.f;
            if (cVar3 == cVar) {
                c<K, V> cVar4 = this.e;
                if (cVar3 != cVar4 && cVar4 != null) {
                    cVarD = d(cVar3);
                }
                this.f = cVarD;
            }
        }

        public abstract c<K, V> c(c<K, V> cVar);

        public abstract c<K, V> d(c<K, V> cVar);

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f != null;
        }

        @Override // java.util.Iterator
        public final Object next() {
            c<K, V> cVar = this.f;
            c<K, V> cVar2 = this.e;
            this.f = (cVar == cVar2 || cVar2 == null) ? null : d(cVar);
            return cVar;
        }
    }

    /* JADX INFO: compiled from: SafeIterableMap.java */
    public static abstract class f<K, V> {
        public abstract void b(c<K, V> cVar);
    }

    public c<K, V> a(K k) {
        c<K, V> cVar = this.e;
        while (cVar != null && !cVar.e.equals(k)) {
            cVar = cVar.g;
        }
        return cVar;
    }

    public V b(K k) {
        c<K, V> cVarA = a(k);
        if (cVarA == null) {
            return null;
        }
        this.h--;
        WeakHashMap<f<K, V>, Boolean> weakHashMap = this.g;
        if (!weakHashMap.isEmpty()) {
            Iterator<f<K, V>> it = weakHashMap.keySet().iterator();
            while (it.hasNext()) {
                it.next().b(cVarA);
            }
        }
        c<K, V> cVar = cVarA.h;
        if (cVar != null) {
            cVar.g = cVarA.g;
        } else {
            this.e = cVarA.g;
        }
        c<K, V> cVar2 = cVarA.g;
        if (cVar2 != null) {
            cVar2.h = cVar;
        } else {
            this.f = cVar;
        }
        cVarA.g = null;
        cVarA.h = null;
        return cVarA.f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
    
        if (r1.hasNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        if (((kd2.e) r6).hasNext() != false) goto L28;
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
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L4
            return r0
        L4:
            boolean r1 = r6 instanceof defpackage.kd2
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            kd2 r6 = (defpackage.kd2) r6
            int r1 = r5.h
            int r3 = r6.h
            if (r1 == r3) goto L13
            return r2
        L13:
            java.util.Iterator r5 = r5.iterator()
            java.util.Iterator r6 = r6.iterator()
        L1b:
            r1 = r5
            kd2$e r1 = (kd2.e) r1
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L44
            r3 = r6
            kd2$e r3 = (kd2.e) r3
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
            kd2$e r6 = (kd2.e) r6
            boolean r5 = r6.hasNext()
            if (r5 != 0) goto L53
            goto L54
        L53:
            r0 = r2
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kd2.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
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
    public final Iterator<Map.Entry<K, V>> iterator() {
        a aVar = new a(this.e, this.f);
        this.g.put(aVar, Boolean.FALSE);
        return aVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (true) {
            e eVar = (e) it;
            if (!eVar.hasNext()) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(((Map.Entry) eVar.next()).toString());
            if (eVar.hasNext()) {
                sb.append(", ");
            }
        }
    }
}
