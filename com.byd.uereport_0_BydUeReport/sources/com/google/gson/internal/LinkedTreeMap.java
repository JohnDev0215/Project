package com.google.gson.internal;

import java.io.ObjectStreamException;
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

/* JADX INFO: loaded from: classes2.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Comparator<Comparable> f4199d = new a();
    public Comparator<? super K> comparator;
    private LinkedTreeMap<K, V>.b entrySet;
    public final e<K, V> header;
    private LinkedTreeMap<K, V>.c keySet;
    public int modCount;
    public e<K, V> root;
    public int size;

    public static class a implements Comparator<Comparable> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    public class b extends AbstractSet<Map.Entry<K, V>> {

        public class a extends LinkedTreeMap<K, V>.d<Map.Entry<K, V>> {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Map.Entry<K, V> next() {
                return a();
            }
        }

        public b() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedTreeMap.this.findByEntry((Map.Entry) obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            e<K, V> eVarFindByEntry;
            if (!(obj instanceof Map.Entry) || (eVarFindByEntry = LinkedTreeMap.this.findByEntry((Map.Entry) obj)) == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(eVarFindByEntry, true);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    public final class c extends AbstractSet<K> {

        public class a extends LinkedTreeMap<K, V>.d<K> {
            public a() {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().f4213i;
            }
        }

        public c() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            LinkedTreeMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new a();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return LinkedTreeMap.this.removeInternalByKey(obj) != null;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return LinkedTreeMap.this.size;
        }
    }

    public abstract class d<T> implements Iterator<T> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e<K, V> f4204d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e<K, V> f4205e = null;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f4206f;

        public d() {
            this.f4204d = LinkedTreeMap.this.header.f4211g;
            this.f4206f = LinkedTreeMap.this.modCount;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.f4204d;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (eVar == linkedTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedTreeMap.modCount != this.f4206f) {
                throw new ConcurrentModificationException();
            }
            this.f4204d = eVar.f4211g;
            this.f4205e = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f4204d != LinkedTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f4205e;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(eVar, true);
            this.f4205e = null;
            this.f4206f = LinkedTreeMap.this.modCount;
        }
    }

    public LinkedTreeMap() {
        this(f4199d);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public final void b(e<K, V> eVar, boolean z7) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f4209e;
            e<K, V> eVar3 = eVar.f4210f;
            int i8 = eVar2 != null ? eVar2.f4215k : 0;
            int i9 = eVar3 != null ? eVar3.f4215k : 0;
            int i10 = i8 - i9;
            if (i10 == -2) {
                e<K, V> eVar4 = eVar3.f4209e;
                e<K, V> eVar5 = eVar3.f4210f;
                int i11 = (eVar4 != null ? eVar4.f4215k : 0) - (eVar5 != null ? eVar5.f4215k : 0);
                if (i11 == -1 || (i11 == 0 && !z7)) {
                    d(eVar);
                } else {
                    e(eVar3);
                    d(eVar);
                }
                if (z7) {
                    return;
                }
            } else if (i10 == 2) {
                e<K, V> eVar6 = eVar2.f4209e;
                e<K, V> eVar7 = eVar2.f4210f;
                int i12 = (eVar6 != null ? eVar6.f4215k : 0) - (eVar7 != null ? eVar7.f4215k : 0);
                if (i12 == 1 || (i12 == 0 && !z7)) {
                    e(eVar);
                } else {
                    d(eVar2);
                    e(eVar);
                }
                if (z7) {
                    return;
                }
            } else if (i10 == 0) {
                eVar.f4215k = i8 + 1;
                if (z7) {
                    return;
                }
            } else {
                eVar.f4215k = Math.max(i8, i9) + 1;
                if (!z7) {
                    return;
                }
            }
            eVar = eVar.f4208d;
        }
    }

    public final void c(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f4208d;
        eVar.f4208d = null;
        if (eVar2 != null) {
            eVar2.f4208d = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f4209e == eVar) {
            eVar3.f4209e = eVar2;
        } else {
            eVar3.f4210f = eVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        e<K, V> eVar = this.header;
        eVar.f4212h = eVar;
        eVar.f4211g = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public final void d(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4209e;
        e<K, V> eVar3 = eVar.f4210f;
        e<K, V> eVar4 = eVar3.f4209e;
        e<K, V> eVar5 = eVar3.f4210f;
        eVar.f4210f = eVar4;
        if (eVar4 != null) {
            eVar4.f4208d = eVar;
        }
        c(eVar, eVar3);
        eVar3.f4209e = eVar;
        eVar.f4208d = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.f4215k : 0, eVar4 != null ? eVar4.f4215k : 0) + 1;
        eVar.f4215k = iMax;
        eVar3.f4215k = Math.max(iMax, eVar5 != null ? eVar5.f4215k : 0) + 1;
    }

    public final void e(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4209e;
        e<K, V> eVar3 = eVar.f4210f;
        e<K, V> eVar4 = eVar2.f4209e;
        e<K, V> eVar5 = eVar2.f4210f;
        eVar.f4209e = eVar5;
        if (eVar5 != null) {
            eVar5.f4208d = eVar;
        }
        c(eVar, eVar2);
        eVar2.f4210f = eVar;
        eVar.f4208d = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.f4215k : 0, eVar5 != null ? eVar5.f4215k : 0) + 1;
        eVar.f4215k = iMax;
        eVar2.f4215k = Math.max(iMax, eVar4 != null ? eVar4.f4215k : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.b bVar = this.entrySet;
        if (bVar != null) {
            return bVar;
        }
        LinkedTreeMap<K, V>.b bVar2 = new b();
        this.entrySet = bVar2;
        return bVar2;
    }

    public e<K, V> find(K k8, boolean z7) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.comparator;
        e<K, V> eVar2 = this.root;
        if (eVar2 != null) {
            Comparable comparable = comparator == f4199d ? (Comparable) k8 : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f4213i) : comparator.compare(k8, eVar2.f4213i);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f4209e : eVar2.f4210f;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z7) {
            return null;
        }
        e<K, V> eVar4 = this.header;
        if (eVar2 != null) {
            eVar = new e<>(eVar2, k8, eVar4, eVar4.f4212h);
            if (iCompareTo < 0) {
                eVar2.f4209e = eVar;
            } else {
                eVar2.f4210f = eVar;
            }
            b(eVar2, true);
        } else {
            if (comparator == f4199d && !(k8 instanceof Comparable)) {
                throw new ClassCastException(k8.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k8, eVar4, eVar4.f4212h);
            this.root = eVar;
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    public e<K, V> findByEntry(Map.Entry<?, ?> entry) {
        e<K, V> eVarFindByObject = findByObject(entry.getKey());
        if (eVarFindByObject != null && a(eVarFindByObject.f4214j, entry.getValue())) {
            return eVarFindByObject;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public e<K, V> findByObject(Object obj) {
        if (obj == 0) {
            return null;
        }
        try {
            return find(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        e<K, V> eVarFindByObject = findByObject(obj);
        if (eVarFindByObject != null) {
            return eVarFindByObject.f4214j;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        LinkedTreeMap<K, V>.c cVar = this.keySet;
        if (cVar != null) {
            return cVar;
        }
        LinkedTreeMap<K, V>.c cVar2 = new c();
        this.keySet = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V put(K k8, V v7) {
        Objects.requireNonNull(k8, "key == null");
        e<K, V> eVarFind = find(k8, true);
        V v8 = eVarFind.f4214j;
        eVarFind.f4214j = v7;
        return v8;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarRemoveInternalByKey = removeInternalByKey(obj);
        if (eVarRemoveInternalByKey != null) {
            return eVarRemoveInternalByKey.f4214j;
        }
        return null;
    }

    public void removeInternal(e<K, V> eVar, boolean z7) {
        int i8;
        if (z7) {
            e<K, V> eVar2 = eVar.f4212h;
            eVar2.f4211g = eVar.f4211g;
            eVar.f4211g.f4212h = eVar2;
        }
        e<K, V> eVar3 = eVar.f4209e;
        e<K, V> eVar4 = eVar.f4210f;
        e<K, V> eVar5 = eVar.f4208d;
        int i9 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                c(eVar, eVar3);
                eVar.f4209e = null;
            } else if (eVar4 != null) {
                c(eVar, eVar4);
                eVar.f4210f = null;
            } else {
                c(eVar, null);
            }
            b(eVar5, false);
            this.size--;
            this.modCount++;
            return;
        }
        e<K, V> eVarB = eVar3.f4215k > eVar4.f4215k ? eVar3.b() : eVar4.a();
        removeInternal(eVarB, false);
        e<K, V> eVar6 = eVar.f4209e;
        if (eVar6 != null) {
            i8 = eVar6.f4215k;
            eVarB.f4209e = eVar6;
            eVar6.f4208d = eVarB;
            eVar.f4209e = null;
        } else {
            i8 = 0;
        }
        e<K, V> eVar7 = eVar.f4210f;
        if (eVar7 != null) {
            i9 = eVar7.f4215k;
            eVarB.f4210f = eVar7;
            eVar7.f4208d = eVarB;
            eVar.f4210f = null;
        }
        eVarB.f4215k = Math.max(i8, i9) + 1;
        c(eVar, eVarB);
    }

    public e<K, V> removeInternalByKey(Object obj) {
        e<K, V> eVarFindByObject = findByObject(obj);
        if (eVarFindByObject != null) {
            removeInternal(eVarFindByObject, true);
        }
        return eVarFindByObject;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.size = 0;
        this.modCount = 0;
        this.header = new e<>();
        this.comparator = comparator == null ? f4199d : comparator;
    }

    public static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e<K, V> f4208d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e<K, V> f4209e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public e<K, V> f4210f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public e<K, V> f4211g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public e<K, V> f4212h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final K f4213i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public V f4214j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f4215k;

        public e() {
            this.f4213i = null;
            this.f4212h = this;
            this.f4211g = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4209e; eVar2 != null; eVar2 = eVar2.f4209e) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4210f; eVar2 != null; eVar2 = eVar2.f4210f) {
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
            K k8 = this.f4213i;
            if (k8 == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k8.equals(entry.getKey())) {
                return false;
            }
            V v7 = this.f4214j;
            if (v7 == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!v7.equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.f4213i;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f4214j;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k8 = this.f4213i;
            int iHashCode = k8 == null ? 0 : k8.hashCode();
            V v7 = this.f4214j;
            return iHashCode ^ (v7 != null ? v7.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v7) {
            V v8 = this.f4214j;
            this.f4214j = v7;
            return v8;
        }

        public String toString() {
            return this.f4213i + "=" + this.f4214j;
        }

        public e(e<K, V> eVar, K k8, e<K, V> eVar2, e<K, V> eVar3) {
            this.f4208d = eVar;
            this.f4213i = k8;
            this.f4215k = 1;
            this.f4211g = eVar2;
            this.f4212h = eVar3;
            eVar3.f4211g = this;
            eVar2.f4212h = this;
        }
    }
}
