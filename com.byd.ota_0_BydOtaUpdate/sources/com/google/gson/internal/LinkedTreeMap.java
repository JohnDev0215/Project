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

/* JADX INFO: loaded from: classes.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Comparator<Comparable> f3151c = new a();
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
                return a().E3;
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

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e<K, V> f3154c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e<K, V> f3155d = null;
        public int s;

        public d() {
            this.f3154c = LinkedTreeMap.this.header.x;
            this.s = LinkedTreeMap.this.modCount;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.f3154c;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (eVar == linkedTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedTreeMap.modCount != this.s) {
                throw new ConcurrentModificationException();
            }
            this.f3154c = eVar.x;
            this.f3155d = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f3154c != LinkedTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f3155d;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(eVar, true);
            this.f3155d = null;
            this.s = LinkedTreeMap.this.modCount;
        }
    }

    public LinkedTreeMap() {
        this(f3151c);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public final void b(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f3157d;
            e<K, V> eVar3 = eVar.s;
            int i2 = eVar2 != null ? eVar2.G3 : 0;
            int i3 = eVar3 != null ? eVar3.G3 : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e<K, V> eVar4 = eVar3.f3157d;
                e<K, V> eVar5 = eVar3.s;
                int i5 = (eVar4 != null ? eVar4.G3 : 0) - (eVar5 != null ? eVar5.G3 : 0);
                if (i5 == -1 || (i5 == 0 && !z)) {
                    d(eVar);
                } else {
                    e(eVar3);
                    d(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                e<K, V> eVar6 = eVar2.f3157d;
                e<K, V> eVar7 = eVar2.s;
                int i6 = (eVar6 != null ? eVar6.G3 : 0) - (eVar7 != null ? eVar7.G3 : 0);
                if (i6 == 1 || (i6 == 0 && !z)) {
                    e(eVar);
                } else {
                    d(eVar2);
                    e(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.G3 = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.G3 = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f3156c;
        }
    }

    public final void c(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f3156c;
        eVar.f3156c = null;
        if (eVar2 != null) {
            eVar2.f3156c = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f3157d == eVar) {
            eVar3.f3157d = eVar2;
        } else {
            eVar3.s = eVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        e<K, V> eVar = this.header;
        eVar.y = eVar;
        eVar.x = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public final void d(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f3157d;
        e<K, V> eVar3 = eVar.s;
        e<K, V> eVar4 = eVar3.f3157d;
        e<K, V> eVar5 = eVar3.s;
        eVar.s = eVar4;
        if (eVar4 != null) {
            eVar4.f3156c = eVar;
        }
        c(eVar, eVar3);
        eVar3.f3157d = eVar;
        eVar.f3156c = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.G3 : 0, eVar4 != null ? eVar4.G3 : 0) + 1;
        eVar.G3 = iMax;
        eVar3.G3 = Math.max(iMax, eVar5 != null ? eVar5.G3 : 0) + 1;
    }

    public final void e(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f3157d;
        e<K, V> eVar3 = eVar.s;
        e<K, V> eVar4 = eVar2.f3157d;
        e<K, V> eVar5 = eVar2.s;
        eVar.f3157d = eVar5;
        if (eVar5 != null) {
            eVar5.f3156c = eVar;
        }
        c(eVar, eVar2);
        eVar2.s = eVar;
        eVar.f3156c = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.G3 : 0, eVar5 != null ? eVar5.G3 : 0) + 1;
        eVar.G3 = iMax;
        eVar2.G3 = Math.max(iMax, eVar4 != null ? eVar4.G3 : 0) + 1;
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

    public e<K, V> find(K k, boolean z) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.comparator;
        e<K, V> eVar2 = this.root;
        if (eVar2 != null) {
            Comparable comparable = comparator == f3151c ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.E3) : comparator.compare(k, eVar2.E3);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f3157d : eVar2.s;
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
            eVar = new e<>(eVar2, k, eVar4, eVar4.y);
            if (iCompareTo < 0) {
                eVar2.f3157d = eVar;
            } else {
                eVar2.s = eVar;
            }
            b(eVar2, true);
        } else {
            if (comparator == f3151c && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.y);
            this.root = eVar;
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    public e<K, V> findByEntry(Map.Entry<?, ?> entry) {
        e<K, V> eVarFindByObject = findByObject(entry.getKey());
        if (eVarFindByObject != null && a(eVarFindByObject.F3, entry.getValue())) {
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
            return eVarFindByObject.F3;
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
    public V put(K k, V v) {
        Objects.requireNonNull(k, "key == null");
        e<K, V> eVarFind = find(k, true);
        V v2 = eVarFind.F3;
        eVarFind.F3 = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarRemoveInternalByKey = removeInternalByKey(obj);
        if (eVarRemoveInternalByKey != null) {
            return eVarRemoveInternalByKey.F3;
        }
        return null;
    }

    public void removeInternal(e<K, V> eVar, boolean z) {
        int i2;
        if (z) {
            e<K, V> eVar2 = eVar.y;
            eVar2.x = eVar.x;
            eVar.x.y = eVar2;
        }
        e<K, V> eVar3 = eVar.f3157d;
        e<K, V> eVar4 = eVar.s;
        e<K, V> eVar5 = eVar.f3156c;
        int i3 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                c(eVar, eVar3);
                eVar.f3157d = null;
            } else if (eVar4 != null) {
                c(eVar, eVar4);
                eVar.s = null;
            } else {
                c(eVar, null);
            }
            b(eVar5, false);
            this.size--;
            this.modCount++;
            return;
        }
        e<K, V> eVarB = eVar3.G3 > eVar4.G3 ? eVar3.b() : eVar4.a();
        removeInternal(eVarB, false);
        e<K, V> eVar6 = eVar.f3157d;
        if (eVar6 != null) {
            i2 = eVar6.G3;
            eVarB.f3157d = eVar6;
            eVar6.f3156c = eVarB;
            eVar.f3157d = null;
        } else {
            i2 = 0;
        }
        e<K, V> eVar7 = eVar.s;
        if (eVar7 != null) {
            i3 = eVar7.G3;
            eVarB.s = eVar7;
            eVar7.f3156c = eVarB;
            eVar.s = null;
        }
        eVarB.G3 = Math.max(i2, i3) + 1;
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
        this.comparator = comparator == null ? f3151c : comparator;
    }

    public static final class e<K, V> implements Map.Entry<K, V> {
        public final K E3;
        public V F3;
        public int G3;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e<K, V> f3156c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e<K, V> f3157d;
        public e<K, V> s;
        public e<K, V> x;
        public e<K, V> y;

        public e() {
            this.E3 = null;
            this.y = this;
            this.x = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f3157d; eVar2 != null; eVar2 = eVar2.f3157d) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.s; eVar2 != null; eVar2 = eVar2.s) {
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
            K k = this.E3;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.F3;
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
            return this.E3;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.F3;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.E3;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.F3;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.F3;
            this.F3 = v;
            return v2;
        }

        public String toString() {
            return this.E3 + "=" + this.F3;
        }

        public e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f3156c = eVar;
            this.E3 = k;
            this.G3 = 1;
            this.x = eVar2;
            this.y = eVar3;
            eVar3.x = this;
            eVar2.y = this;
        }
    }
}
