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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Comparator<Comparable> f770b = new a();
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
                return a().g;
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

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e<K, V> f773b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e<K, V> f774c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f775d;

        public d() {
            this.f773b = LinkedTreeMap.this.header.e;
            this.f775d = LinkedTreeMap.this.modCount;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.f773b;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (eVar == linkedTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedTreeMap.modCount != this.f775d) {
                throw new ConcurrentModificationException();
            }
            this.f773b = eVar.e;
            this.f774c = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f773b != LinkedTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f774c;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(eVar, true);
            this.f774c = null;
            this.f775d = LinkedTreeMap.this.modCount;
        }
    }

    public LinkedTreeMap() {
        this(f770b);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public final void b(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f777c;
            e<K, V> eVar3 = eVar.f778d;
            int i = eVar2 != null ? eVar2.i : 0;
            int i2 = eVar3 != null ? eVar3.i : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                e<K, V> eVar4 = eVar3.f777c;
                e<K, V> eVar5 = eVar3.f778d;
                int i4 = (eVar4 != null ? eVar4.i : 0) - (eVar5 != null ? eVar5.i : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    d(eVar);
                } else {
                    e(eVar3);
                    d(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                e<K, V> eVar6 = eVar2.f777c;
                e<K, V> eVar7 = eVar2.f778d;
                int i5 = (eVar6 != null ? eVar6.i : 0) - (eVar7 != null ? eVar7.i : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    e(eVar);
                } else {
                    d(eVar2);
                    e(eVar);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                eVar.i = i + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.i = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f776b;
        }
    }

    public final void c(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f776b;
        eVar.f776b = null;
        if (eVar2 != null) {
            eVar2.f776b = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f777c == eVar) {
            eVar3.f777c = eVar2;
        } else {
            eVar3.f778d = eVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        e<K, V> eVar = this.header;
        eVar.f = eVar;
        eVar.e = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public final void d(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f777c;
        e<K, V> eVar3 = eVar.f778d;
        e<K, V> eVar4 = eVar3.f777c;
        e<K, V> eVar5 = eVar3.f778d;
        eVar.f778d = eVar4;
        if (eVar4 != null) {
            eVar4.f776b = eVar;
        }
        c(eVar, eVar3);
        eVar3.f777c = eVar;
        eVar.f776b = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.i : 0, eVar4 != null ? eVar4.i : 0) + 1;
        eVar.i = iMax;
        eVar3.i = Math.max(iMax, eVar5 != null ? eVar5.i : 0) + 1;
    }

    public final void e(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f777c;
        e<K, V> eVar3 = eVar.f778d;
        e<K, V> eVar4 = eVar2.f777c;
        e<K, V> eVar5 = eVar2.f778d;
        eVar.f777c = eVar5;
        if (eVar5 != null) {
            eVar5.f776b = eVar;
        }
        c(eVar, eVar2);
        eVar2.f778d = eVar;
        eVar.f776b = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.i : 0, eVar5 != null ? eVar5.i : 0) + 1;
        eVar.i = iMax;
        eVar2.i = Math.max(iMax, eVar4 != null ? eVar4.i : 0) + 1;
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
            Comparable comparable = comparator == f770b ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.g) : comparator.compare(k, eVar2.g);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f777c : eVar2.f778d;
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
            eVar = new e<>(eVar2, k, eVar4, eVar4.f);
            if (iCompareTo < 0) {
                eVar2.f777c = eVar;
            } else {
                eVar2.f778d = eVar;
            }
            b(eVar2, true);
        } else {
            if (comparator == f770b && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f);
            this.root = eVar;
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    public e<K, V> findByEntry(Map.Entry<?, ?> entry) {
        e<K, V> eVarFindByObject = findByObject(entry.getKey());
        if (eVarFindByObject != null && a(eVarFindByObject.h, entry.getValue())) {
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
            return eVarFindByObject.h;
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
        V v2 = eVarFind.h;
        eVarFind.h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarRemoveInternalByKey = removeInternalByKey(obj);
        if (eVarRemoveInternalByKey != null) {
            return eVarRemoveInternalByKey.h;
        }
        return null;
    }

    public void removeInternal(e<K, V> eVar, boolean z) {
        int i;
        if (z) {
            e<K, V> eVar2 = eVar.f;
            eVar2.e = eVar.e;
            eVar.e.f = eVar2;
        }
        e<K, V> eVar3 = eVar.f777c;
        e<K, V> eVar4 = eVar.f778d;
        e<K, V> eVar5 = eVar.f776b;
        int i2 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                c(eVar, eVar3);
                eVar.f777c = null;
            } else if (eVar4 != null) {
                c(eVar, eVar4);
                eVar.f778d = null;
            } else {
                c(eVar, null);
            }
            b(eVar5, false);
            this.size--;
            this.modCount++;
            return;
        }
        e<K, V> eVarB = eVar3.i > eVar4.i ? eVar3.b() : eVar4.a();
        removeInternal(eVarB, false);
        e<K, V> eVar6 = eVar.f777c;
        if (eVar6 != null) {
            i = eVar6.i;
            eVarB.f777c = eVar6;
            eVar6.f776b = eVarB;
            eVar.f777c = null;
        } else {
            i = 0;
        }
        e<K, V> eVar7 = eVar.f778d;
        if (eVar7 != null) {
            i2 = eVar7.i;
            eVarB.f778d = eVar7;
            eVar7.f776b = eVarB;
            eVar.f778d = null;
        }
        eVarB.i = Math.max(i, i2) + 1;
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
        this.comparator = comparator == null ? f770b : comparator;
    }

    public static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e<K, V> f776b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e<K, V> f777c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e<K, V> f778d;
        public e<K, V> e;
        public e<K, V> f;
        public final K g;
        public V h;
        public int i;

        public e() {
            this.g = null;
            this.f = this;
            this.e = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f777c; eVar2 != null; eVar2 = eVar2.f777c) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f778d; eVar2 != null; eVar2 = eVar2.f778d) {
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
            K k = this.g;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.h;
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
            return this.g;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.g;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.h;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.h;
            this.h = v;
            return v2;
        }

        public String toString() {
            return this.g + "=" + this.h;
        }

        public e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f776b = eVar;
            this.g = k;
            this.i = 1;
            this.e = eVar2;
            this.f = eVar3;
            eVar3.e = this;
            eVar2.f = this;
        }
    }
}
