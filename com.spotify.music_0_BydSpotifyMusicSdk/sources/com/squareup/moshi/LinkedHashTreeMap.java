package com.squareup.moshi;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> c = new a();
    final Comparator<? super K> comparator;
    private LinkedHashTreeMap<K, V>.d entrySet;
    final g<K, V> header;
    private LinkedHashTreeMap<K, V>.e keySet;
    int modCount;
    int size;
    g<K, V>[] table;
    int threshold;

    public class a implements Comparator<Comparable> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public static final class b<K, V> {
        private g<K, V> a;
        private int b;
        private int c;
        private int d;

        public void a(g<K, V> gVar) {
            gVar.k = null;
            gVar.c = null;
            gVar.e = null;
            gVar.q = 1;
            int i = this.b;
            if (i > 0) {
                int i2 = this.d;
                if ((i2 & 1) == 0) {
                    this.d = i2 + 1;
                    this.b = i - 1;
                    this.c++;
                }
            }
            gVar.c = this.a;
            this.a = gVar;
            int i3 = this.d;
            int i4 = i3 + 1;
            this.d = i4;
            int i5 = this.b;
            if (i5 > 0 && (i4 & 1) == 0) {
                this.d = i3 + 2;
                this.b = i5 - 1;
                this.c++;
            }
            int i6 = 4;
            while (true) {
                int i7 = i6 - 1;
                if ((this.d & i7) != i7) {
                    return;
                }
                int i8 = this.c;
                if (i8 == 0) {
                    g<K, V> gVar2 = this.a;
                    g<K, V> gVar3 = gVar2.c;
                    g<K, V> gVar4 = gVar3.c;
                    gVar3.c = gVar4.c;
                    this.a = gVar3;
                    gVar3.e = gVar4;
                    gVar3.k = gVar2;
                    gVar3.q = gVar2.q + 1;
                    gVar4.c = gVar3;
                    gVar2.c = gVar3;
                } else if (i8 == 1) {
                    g<K, V> gVar5 = this.a;
                    g<K, V> gVar6 = gVar5.c;
                    this.a = gVar6;
                    gVar6.k = gVar5;
                    gVar6.q = gVar5.q + 1;
                    gVar5.c = gVar6;
                    this.c = 0;
                } else if (i8 == 2) {
                    this.c = 0;
                }
                i6 *= 2;
            }
        }

        public void b(int i) {
            this.b = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.d = 0;
            this.c = 0;
            this.a = null;
        }

        public g<K, V> c() {
            g<K, V> gVar = this.a;
            if (gVar.c == null) {
                return gVar;
            }
            throw new IllegalStateException();
        }
    }

    public static class c<K, V> {
        private g<K, V> a;

        public g<K, V> a() {
            g<K, V> gVar = this.a;
            if (gVar == null) {
                return null;
            }
            g<K, V> gVar2 = gVar.c;
            gVar.c = null;
            g<K, V> gVar3 = gVar.k;
            while (true) {
                g<K, V> gVar4 = gVar2;
                gVar2 = gVar3;
                if (gVar2 == null) {
                    this.a = gVar4;
                    return gVar;
                }
                gVar2.c = gVar4;
                gVar3 = gVar2.e;
            }
        }

        public void b(g<K, V> gVar) {
            g<K, V> gVar2 = null;
            while (gVar != null) {
                gVar.c = gVar2;
                gVar2 = gVar;
                gVar = gVar.e;
            }
            this.a = gVar2;
        }
    }

    public final class d extends AbstractSet<Map.Entry<K, V>> {

        public class a extends LinkedHashTreeMap<K, V>.f<Map.Entry<K, V>> {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        public d() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.e((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            g<K, V> gVarE;
            if (!(obj instanceof Map.Entry) || (gVarE = LinkedHashTreeMap.this.e((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedHashTreeMap.this.h(gVarE, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    public final class e extends AbstractSet<K> {

        public class a extends LinkedHashTreeMap<K, V>.f<K> {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().n;
            }
        }

        public e() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.i(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedHashTreeMap.this.size;
        }
    }

    public abstract class f<T> implements Iterator<T> {
        g<K, V> c;
        g<K, V> e = null;
        int k;

        public f() {
            this.c = LinkedHashTreeMap.this.header.l;
            this.k = LinkedHashTreeMap.this.modCount;
        }

        public final g<K, V> a() {
            g<K, V> gVar = this.c;
            LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
            if (gVar == linkedHashTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedHashTreeMap.modCount != this.k) {
                throw new ConcurrentModificationException();
            }
            this.c = gVar.l;
            this.e = gVar;
            return gVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.c != LinkedHashTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            g<K, V> gVar = this.e;
            if (gVar == null) {
                throw new IllegalStateException();
            }
            LinkedHashTreeMap.this.h(gVar, true);
            this.e = null;
            this.k = LinkedHashTreeMap.this.modCount;
        }
    }

    public static final class g<K, V> implements Map.Entry<K, V> {
        g<K, V> c;
        g<K, V> e;
        g<K, V> k;
        g<K, V> l;
        g<K, V> m;
        final K n;
        final int o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        V f66p;
        int q;

        public g() {
            this.n = null;
            this.o = -1;
            this.m = this;
            this.l = this;
        }

        public g<K, V> a() {
            g<K, V> gVar = this;
            for (g<K, V> gVar2 = this.e; gVar2 != null; gVar2 = gVar2.e) {
                gVar = gVar2;
            }
            return gVar;
        }

        public g<K, V> b() {
            g<K, V> gVar = this;
            for (g<K, V> gVar2 = this.k; gVar2 != null; gVar2 = gVar2.k) {
                gVar = gVar2;
            }
            return gVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.n;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.f66p;
            if (v == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.n;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f66p;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.n;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.f66p;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f66p;
            this.f66p = v;
            return v2;
        }

        public String toString() {
            return this.n + "=" + this.f66p;
        }

        public g(g<K, V> gVar, K k, int i, g<K, V> gVar2, g<K, V> gVar3) {
            this.c = gVar;
            this.n = k;
            this.o = i;
            this.q = 1;
            this.l = gVar2;
            this.m = gVar3;
            gVar3.l = this;
            gVar2.m = this;
        }
    }

    public LinkedHashTreeMap() {
        this(null);
    }

    private void a() {
        g<K, V>[] gVarArrB = b(this.table);
        this.table = gVarArrB;
        this.threshold = (gVarArrB.length / 4) + (gVarArrB.length / 2);
    }

    public static <K, V> g<K, V>[] b(g<K, V>[] gVarArr) {
        int length = gVarArr.length;
        g<K, V>[] gVarArr2 = new g[length * 2];
        c cVar = new c();
        b bVar = new b();
        b bVar2 = new b();
        for (int i = 0; i < length; i++) {
            g<K, V> gVar = gVarArr[i];
            if (gVar != null) {
                cVar.b(gVar);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    g<K, V> gVarA = cVar.a();
                    if (gVarA == null) {
                        break;
                    }
                    if ((gVarA.o & length) == 0) {
                        i2++;
                    } else {
                        i3++;
                    }
                }
                bVar.b(i2);
                bVar2.b(i3);
                cVar.b(gVar);
                while (true) {
                    g<K, V> gVarA2 = cVar.a();
                    if (gVarA2 == null) {
                        break;
                    }
                    if ((gVarA2.o & length) == 0) {
                        bVar.a(gVarA2);
                    } else {
                        bVar2.a(gVarA2);
                    }
                }
                gVarArr2[i] = i2 > 0 ? bVar.c() : null;
                gVarArr2[i + length] = i3 > 0 ? bVar2.c() : null;
            }
        }
        return gVarArr2;
    }

    private boolean c(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void g(g<K, V> gVar, boolean z) {
        while (gVar != null) {
            g<K, V> gVar2 = gVar.e;
            g<K, V> gVar3 = gVar.k;
            int i = gVar2 != null ? gVar2.q : 0;
            int i2 = gVar3 != null ? gVar3.q : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                g<K, V> gVar4 = gVar3.e;
                g<K, V> gVar5 = gVar3.k;
                int i4 = (gVar4 != null ? gVar4.q : 0) - (gVar5 != null ? gVar5.q : 0);
                if (i4 != -1 && (i4 != 0 || z)) {
                    l(gVar3);
                }
                k(gVar);
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                g<K, V> gVar6 = gVar2.e;
                g<K, V> gVar7 = gVar2.k;
                int i5 = (gVar6 != null ? gVar6.q : 0) - (gVar7 != null ? gVar7.q : 0);
                if (i5 != 1 && (i5 != 0 || z)) {
                    k(gVar2);
                }
                l(gVar);
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                gVar.q = i + 1;
                if (z) {
                    return;
                }
            } else {
                gVar.q = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            gVar = gVar.c;
        }
    }

    private void j(g<K, V> gVar, g<K, V> gVar2) {
        g<K, V> gVar3 = gVar.c;
        gVar.c = null;
        if (gVar2 != null) {
            gVar2.c = gVar3;
        }
        if (gVar3 == null) {
            int i = gVar.o;
            this.table[i & (r0.length - 1)] = gVar2;
        } else if (gVar3.e == gVar) {
            gVar3.e = gVar2;
        } else {
            gVar3.k = gVar2;
        }
    }

    private void k(g<K, V> gVar) {
        g<K, V> gVar2 = gVar.e;
        g<K, V> gVar3 = gVar.k;
        g<K, V> gVar4 = gVar3.e;
        g<K, V> gVar5 = gVar3.k;
        gVar.k = gVar4;
        if (gVar4 != null) {
            gVar4.c = gVar;
        }
        j(gVar, gVar3);
        gVar3.e = gVar;
        gVar.c = gVar3;
        int iMax = Math.max(gVar2 != null ? gVar2.q : 0, gVar4 != null ? gVar4.q : 0) + 1;
        gVar.q = iMax;
        gVar3.q = Math.max(iMax, gVar5 != null ? gVar5.q : 0) + 1;
    }

    private void l(g<K, V> gVar) {
        g<K, V> gVar2 = gVar.e;
        g<K, V> gVar3 = gVar.k;
        g<K, V> gVar4 = gVar2.e;
        g<K, V> gVar5 = gVar2.k;
        gVar.e = gVar5;
        if (gVar5 != null) {
            gVar5.c = gVar;
        }
        j(gVar, gVar2);
        gVar2.k = gVar;
        gVar.c = gVar2;
        int iMax = Math.max(gVar3 != null ? gVar3.q : 0, gVar5 != null ? gVar5.q : 0) + 1;
        gVar.q = iMax;
        gVar2.q = Math.max(iMax, gVar4 != null ? gVar4.q : 0) + 1;
    }

    private static int m(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.table, (Object) null);
        this.size = 0;
        this.modCount++;
        g<K, V> gVar = this.header;
        g<K, V> gVar2 = gVar.l;
        while (gVar2 != gVar) {
            g<K, V> gVar3 = gVar2.l;
            gVar2.m = null;
            gVar2.l = null;
            gVar2 = gVar3;
        }
        gVar.m = gVar;
        gVar.l = gVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return f(obj) != null;
    }

    public g<K, V> d(K k, boolean z) {
        int iCompareTo;
        g<K, V> gVar;
        Comparator<? super K> comparator = this.comparator;
        g<K, V>[] gVarArr = this.table;
        int iM = m(k.hashCode());
        int length = (gVarArr.length - 1) & iM;
        g<K, V> gVar2 = gVarArr[length];
        if (gVar2 != null) {
            Comparable comparable = comparator == c ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(gVar2.n) : comparator.compare(k, gVar2.n);
                if (iCompareTo == 0) {
                    return gVar2;
                }
                g<K, V> gVar3 = iCompareTo < 0 ? gVar2.e : gVar2.k;
                if (gVar3 == null) {
                    break;
                }
                gVar2 = gVar3;
            }
        } else {
            iCompareTo = 0;
        }
        g<K, V> gVar4 = gVar2;
        int i = iCompareTo;
        if (!z) {
            return null;
        }
        g<K, V> gVar5 = this.header;
        if (gVar4 != null) {
            gVar = new g<>(gVar4, k, iM, gVar5, gVar5.m);
            if (i < 0) {
                gVar4.e = gVar;
            } else {
                gVar4.k = gVar;
            }
            g(gVar4, true);
        } else {
            if (comparator == c && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName().concat(" is not Comparable"));
            }
            gVar = new g<>(gVar4, k, iM, gVar5, gVar5.m);
            gVarArr[length] = gVar;
        }
        int i2 = this.size;
        this.size = i2 + 1;
        if (i2 > this.threshold) {
            a();
        }
        this.modCount++;
        return gVar;
    }

    public g<K, V> e(Map.Entry<?, ?> entry) {
        g<K, V> gVarF = f(entry.getKey());
        if (gVarF == null || !c(gVarF.f66p, entry.getValue())) {
            return null;
        }
        return gVarF;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.d dVar = this.entrySet;
        if (dVar != null) {
            return dVar;
        }
        LinkedHashTreeMap<K, V>.d dVar2 = new d();
        this.entrySet = dVar2;
        return dVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g<K, V> f(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return d(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        g<K, V> gVarF = f(obj);
        if (gVarF != null) {
            return gVarF.f66p;
        }
        return null;
    }

    public void h(g<K, V> gVar, boolean z) {
        int i;
        if (z) {
            g<K, V> gVar2 = gVar.m;
            gVar2.l = gVar.l;
            gVar.l.m = gVar2;
            gVar.m = null;
            gVar.l = null;
        }
        g<K, V> gVar3 = gVar.e;
        g<K, V> gVar4 = gVar.k;
        g<K, V> gVar5 = gVar.c;
        int i2 = 0;
        if (gVar3 == null || gVar4 == null) {
            if (gVar3 != null) {
                j(gVar, gVar3);
                gVar.e = null;
            } else if (gVar4 != null) {
                j(gVar, gVar4);
                gVar.k = null;
            } else {
                j(gVar, null);
            }
            g(gVar5, false);
            this.size--;
            this.modCount++;
            return;
        }
        g<K, V> gVarB = gVar3.q > gVar4.q ? gVar3.b() : gVar4.a();
        h(gVarB, false);
        g<K, V> gVar6 = gVar.e;
        if (gVar6 != null) {
            i = gVar6.q;
            gVarB.e = gVar6;
            gVar6.c = gVarB;
            gVar.e = null;
        } else {
            i = 0;
        }
        g<K, V> gVar7 = gVar.k;
        if (gVar7 != null) {
            i2 = gVar7.q;
            gVarB.k = gVar7;
            gVar7.c = gVarB;
            gVar.k = null;
        }
        gVarB.q = Math.max(i, i2) + 1;
        j(gVar, gVarB);
    }

    public g<K, V> i(Object obj) {
        g<K, V> gVarF = f(obj);
        if (gVarF != null) {
            h(gVarF, true);
        }
        return gVarF;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.e eVar = this.keySet;
        if (eVar != null) {
            return eVar;
        }
        LinkedHashTreeMap<K, V>.e eVar2 = new e();
        this.keySet = eVar2;
        return eVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        g<K, V> gVarD = d(k, true);
        V v2 = gVarD.f66p;
        gVarD.f66p = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        g<K, V> gVarI = i(obj);
        if (gVarI != null) {
            return gVarI.f66p;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.comparator = comparator == null ? c : comparator;
        this.header = new g<>();
        g<K, V>[] gVarArr = new g[16];
        this.table = gVarArr;
        this.threshold = (gVarArr.length / 4) + (gVarArr.length / 2);
    }
}
