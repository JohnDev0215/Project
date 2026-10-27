package com.google.gson.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: LinkedTreeMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class g<K, V> extends AbstractMap<K, V> implements Serializable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Comparator<Comparable> f4564b = new a();
    Comparator<? super K> comparator;
    private g<K, V>.b entrySet;
    final e<K, V> header;
    private g<K, V>.c keySet;
    int modCount;
    e<K, V> root;
    int size;

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    static class a implements Comparator<Comparable> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    class b extends AbstractSet<Map.Entry<K, V>> {

        /* JADX INFO: compiled from: LinkedTreeMap.java */
        class a extends g<K, V>.d<Map.Entry<K, V>> {
            a() {
                super();
            }

            @Override // java.util.Iterator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            g.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && g.this.d((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> eVarD;
            if (!(obj instanceof Map.Entry) || (eVarD = g.this.d((Map.Entry) obj)) == null) {
                return false;
            }
            g.this.h(eVarD, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return g.this.size;
        }
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    final class c extends AbstractSet<K> {

        /* JADX INFO: compiled from: LinkedTreeMap.java */
        class a extends g<K, V>.d<K> {
            a() {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().f4578g;
            }
        }

        c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            g.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return g.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return g.this.i(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return g.this.size;
        }
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    private abstract class d<T> implements Iterator<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e<K, V> f4569b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e<K, V> f4570c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f4571d;

        d() {
            this.f4569b = g.this.header.f4576e;
            this.f4571d = g.this.modCount;
        }

        final e<K, V> a() {
            e<K, V> eVar = this.f4569b;
            g gVar = g.this;
            if (eVar == gVar.header) {
                throw new NoSuchElementException();
            }
            if (gVar.modCount != this.f4571d) {
                throw new ConcurrentModificationException();
            }
            this.f4569b = eVar.f4576e;
            this.f4570c = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f4569b != g.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f4570c;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            g.this.h(eVar, true);
            this.f4570c = null;
            this.f4571d = g.this.modCount;
        }
    }

    public g() {
        this(f4564b);
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void g(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f4574c;
            e<K, V> eVar3 = eVar.f4575d;
            int i2 = eVar2 != null ? eVar2.f4580i : 0;
            int i3 = eVar3 != null ? eVar3.f4580i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e<K, V> eVar4 = eVar3.f4574c;
                e<K, V> eVar5 = eVar3.f4575d;
                int i5 = (eVar4 != null ? eVar4.f4580i : 0) - (eVar5 != null ? eVar5.f4580i : 0);
                if (i5 == -1 || (i5 == 0 && !z)) {
                    k(eVar);
                } else {
                    m(eVar3);
                    k(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                e<K, V> eVar6 = eVar2.f4574c;
                e<K, V> eVar7 = eVar2.f4575d;
                int i6 = (eVar6 != null ? eVar6.f4580i : 0) - (eVar7 != null ? eVar7.f4580i : 0);
                if (i6 == 1 || (i6 == 0 && !z)) {
                    m(eVar);
                } else {
                    k(eVar2);
                    m(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.f4580i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.f4580i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f4573b;
        }
    }

    private void j(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f4573b;
        eVar.f4573b = null;
        if (eVar2 != null) {
            eVar2.f4573b = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f4574c == eVar) {
            eVar3.f4574c = eVar2;
        } else {
            eVar3.f4575d = eVar2;
        }
    }

    private void k(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4574c;
        e<K, V> eVar3 = eVar.f4575d;
        e<K, V> eVar4 = eVar3.f4574c;
        e<K, V> eVar5 = eVar3.f4575d;
        eVar.f4575d = eVar4;
        if (eVar4 != null) {
            eVar4.f4573b = eVar;
        }
        j(eVar, eVar3);
        eVar3.f4574c = eVar;
        eVar.f4573b = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.f4580i : 0, eVar4 != null ? eVar4.f4580i : 0) + 1;
        eVar.f4580i = iMax;
        eVar3.f4580i = Math.max(iMax, eVar5 != null ? eVar5.f4580i : 0) + 1;
    }

    private void m(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4574c;
        e<K, V> eVar3 = eVar.f4575d;
        e<K, V> eVar4 = eVar2.f4574c;
        e<K, V> eVar5 = eVar2.f4575d;
        eVar.f4574c = eVar5;
        if (eVar5 != null) {
            eVar5.f4573b = eVar;
        }
        j(eVar, eVar2);
        eVar2.f4575d = eVar;
        eVar.f4573b = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.f4580i : 0, eVar5 != null ? eVar5.f4580i : 0) + 1;
        eVar.f4580i = iMax;
        eVar2.f4580i = Math.max(iMax, eVar4 != null ? eVar4.f4580i : 0) + 1;
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    e<K, V> b(K k, boolean z) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.comparator;
        e<K, V> eVar2 = this.root;
        if (eVar2 != null) {
            Comparable comparable = comparator == f4564b ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f4578g) : comparator.compare(k, eVar2.f4578g);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f4574c : eVar2.f4575d;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        e<K, V> eVar4 = this.header;
        if (eVar2 != null) {
            eVar = new e<>(eVar2, k, eVar4, eVar4.f4577f);
            if (iCompareTo < 0) {
                eVar2.f4574c = eVar;
            } else {
                eVar2.f4575d = eVar;
            }
            g(eVar2, true);
        } else {
            if (comparator == f4564b && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f4577f);
            this.root = eVar;
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        e<K, V> eVar = this.header;
        eVar.f4577f = eVar;
        eVar.f4576e = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return e(obj) != null;
    }

    e<K, V> d(Map.Entry<?, ?> entry) {
        e<K, V> eVarE = e(entry.getKey());
        if (eVarE != null && a(eVarE.f4579h, entry.getValue())) {
            return eVarE;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    e<K, V> e(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return b(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        g<K, V>.b bVar = this.entrySet;
        if (bVar != null) {
            return bVar;
        }
        g<K, V>.b bVar2 = new b();
        this.entrySet = bVar2;
        return bVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> eVarE = e(obj);
        if (eVarE != null) {
            return eVarE.f4579h;
        }
        return null;
    }

    void h(e<K, V> eVar, boolean z) {
        int i2;
        if (z) {
            e<K, V> eVar2 = eVar.f4577f;
            eVar2.f4576e = eVar.f4576e;
            eVar.f4576e.f4577f = eVar2;
        }
        e<K, V> eVar3 = eVar.f4574c;
        e<K, V> eVar4 = eVar.f4575d;
        e<K, V> eVar5 = eVar.f4573b;
        int i3 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                j(eVar, eVar3);
                eVar.f4574c = null;
            } else if (eVar4 != null) {
                j(eVar, eVar4);
                eVar.f4575d = null;
            } else {
                j(eVar, null);
            }
            g(eVar5, false);
            this.size--;
            this.modCount++;
            return;
        }
        e<K, V> eVarB = eVar3.f4580i > eVar4.f4580i ? eVar3.b() : eVar4.a();
        h(eVarB, false);
        e<K, V> eVar6 = eVar.f4574c;
        if (eVar6 != null) {
            i2 = eVar6.f4580i;
            eVarB.f4574c = eVar6;
            eVar6.f4573b = eVarB;
            eVar.f4574c = null;
        } else {
            i2 = 0;
        }
        e<K, V> eVar7 = eVar.f4575d;
        if (eVar7 != null) {
            i3 = eVar7.f4580i;
            eVarB.f4575d = eVar7;
            eVar7.f4573b = eVarB;
            eVar.f4575d = null;
        }
        eVarB.f4580i = Math.max(i2, i3) + 1;
        j(eVar, eVarB);
    }

    e<K, V> i(Object obj) {
        e<K, V> eVarE = e(obj);
        if (eVarE != null) {
            h(eVarE, true);
        }
        return eVarE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        g<K, V>.c cVar = this.keySet;
        if (cVar != null) {
            return cVar;
        }
        g<K, V>.c cVar2 = new c();
        this.keySet = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        Objects.requireNonNull(k, "key == null");
        e<K, V> eVarB = b(k, true);
        V v2 = eVarB.f4579h;
        eVarB.f4579h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarI = i(obj);
        if (eVarI != null) {
            return eVarI.f4579h;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    public g(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.header = new e<>();
        this.comparator = comparator == null ? f4564b : comparator;
    }

    /* JADX INFO: compiled from: LinkedTreeMap.java */
    static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e<K, V> f4573b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e<K, V> f4574c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        e<K, V> f4575d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        e<K, V> f4576e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        e<K, V> f4577f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final K f4578g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        V f4579h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f4580i;

        e() {
            this.f4578g = null;
            this.f4577f = this;
            this.f4576e = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4574c; eVar2 != null; eVar2 = eVar2.f4574c) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4575d; eVar2 != null; eVar2 = eVar2.f4575d) {
                eVar = eVar2;
            }
            return eVar;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            K k = this.f4578g;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.f4579h;
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
            return this.f4578g;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f4579h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f4578g;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.f4579h;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f4579h;
            this.f4579h = v;
            return v2;
        }

        public String toString() {
            return this.f4578g + "=" + this.f4579h;
        }

        e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f4573b = eVar;
            this.f4578g = k;
            this.f4580i = 1;
            this.f4576e = eVar2;
            this.f4577f = eVar3;
            eVar3.f4576e = this;
            eVar2.f4577f = this;
        }
    }
}
