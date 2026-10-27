package com.google.gson.internal;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
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
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Comparator<Comparable> f3991b = new a();
    Comparator<? super K> comparator;
    private LinkedTreeMap<K, V>.b entrySet;
    final e<K, V> header;
    private LinkedTreeMap<K, V>.c keySet;
    int modCount;
    e<K, V> root;
    int size;

    class a implements Comparator<Comparable> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    }

    class b extends AbstractSet<Map.Entry<K, V>> {

        class a extends LinkedTreeMap<K, V>.d<Map.Entry<K, V>> {
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

    final class c extends AbstractSet<K> {

        class a extends LinkedTreeMap<K, V>.d<K> {
            a() {
                super();
            }

            @Override // java.util.Iterator
            public K next() {
                return a().f4005g;
            }
        }

        c() {
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

    private abstract class d<T> implements Iterator<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e<K, V> f3996b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e<K, V> f3997c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3998d;

        d() {
            this.f3996b = LinkedTreeMap.this.header.f4003e;
            this.f3998d = LinkedTreeMap.this.modCount;
        }

        final e<K, V> a() {
            e<K, V> eVar = this.f3996b;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (eVar == linkedTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedTreeMap.modCount != this.f3998d) {
                throw new ConcurrentModificationException();
            }
            this.f3996b = eVar.f4003e;
            this.f3997c = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f3996b != LinkedTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f3997c;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(eVar, true);
            this.f3997c = null;
            this.f3998d = LinkedTreeMap.this.modCount;
        }
    }

    public LinkedTreeMap() {
        this(f3991b);
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void b(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f4001c;
            e<K, V> eVar3 = eVar.f4002d;
            int i = eVar2 != null ? eVar2.i : 0;
            int i2 = eVar3 != null ? eVar3.i : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                e<K, V> eVar4 = eVar3.f4001c;
                e<K, V> eVar5 = eVar3.f4002d;
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
                e<K, V> eVar6 = eVar2.f4001c;
                e<K, V> eVar7 = eVar2.f4002d;
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
            eVar = eVar.f4000b;
        }
    }

    private void c(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f4000b;
        eVar.f4000b = null;
        if (eVar2 != null) {
            eVar2.f4000b = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f4001c == eVar) {
            eVar3.f4001c = eVar2;
        } else {
            eVar3.f4002d = eVar2;
        }
    }

    private void d(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4001c;
        e<K, V> eVar3 = eVar.f4002d;
        e<K, V> eVar4 = eVar3.f4001c;
        e<K, V> eVar5 = eVar3.f4002d;
        eVar.f4002d = eVar4;
        if (eVar4 != null) {
            eVar4.f4000b = eVar;
        }
        c(eVar, eVar3);
        eVar3.f4001c = eVar;
        eVar.f4000b = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.i : 0, eVar4 != null ? eVar4.i : 0) + 1;
        eVar.i = iMax;
        eVar3.i = Math.max(iMax, eVar5 != null ? eVar5.i : 0) + 1;
    }

    private void e(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4001c;
        e<K, V> eVar3 = eVar.f4002d;
        e<K, V> eVar4 = eVar2.f4001c;
        e<K, V> eVar5 = eVar2.f4002d;
        eVar.f4001c = eVar5;
        if (eVar5 != null) {
            eVar5.f4000b = eVar;
        }
        c(eVar, eVar2);
        eVar2.f4002d = eVar;
        eVar.f4000b = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.i : 0, eVar5 != null ? eVar5.i : 0) + 1;
        eVar.i = iMax;
        eVar2.i = Math.max(iMax, eVar4 != null ? eVar4.i : 0) + 1;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        e<K, V> eVar = this.header;
        eVar.f4004f = eVar;
        eVar.f4003e = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
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

    e<K, V> find(K k, boolean z) {
        int iCompareTo;
        e<K, V> eVar;
        Comparator<? super K> comparator = this.comparator;
        e<K, V> eVar2 = this.root;
        if (eVar2 != null) {
            Comparable comparable = comparator == f3991b ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f4005g) : comparator.compare(k, eVar2.f4005g);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f4001c : eVar2.f4002d;
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
            eVar = new e<>(eVar2, k, eVar4, eVar4.f4004f);
            if (iCompareTo < 0) {
                eVar2.f4001c = eVar;
            } else {
                eVar2.f4002d = eVar;
            }
            b(eVar2, true);
        } else {
            if (comparator == f3991b && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f4004f);
            this.root = eVar;
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    e<K, V> findByEntry(Map.Entry<?, ?> entry) {
        e<K, V> eVarFindByObject = findByObject(entry.getKey());
        if (eVarFindByObject != null && a(eVarFindByObject.f4006h, entry.getValue())) {
            return eVarFindByObject;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    e<K, V> findByObject(Object obj) {
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
            return eVarFindByObject.f4006h;
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
        V v2 = eVarFind.f4006h;
        eVarFind.f4006h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarRemoveInternalByKey = removeInternalByKey(obj);
        if (eVarRemoveInternalByKey != null) {
            return eVarRemoveInternalByKey.f4006h;
        }
        return null;
    }

    void removeInternal(e<K, V> eVar, boolean z) {
        int i;
        if (z) {
            e<K, V> eVar2 = eVar.f4004f;
            eVar2.f4003e = eVar.f4003e;
            eVar.f4003e.f4004f = eVar2;
        }
        e<K, V> eVar3 = eVar.f4001c;
        e<K, V> eVar4 = eVar.f4002d;
        e<K, V> eVar5 = eVar.f4000b;
        int i2 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                c(eVar, eVar3);
                eVar.f4001c = null;
            } else if (eVar4 != null) {
                c(eVar, eVar4);
                eVar.f4002d = null;
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
        e<K, V> eVar6 = eVar.f4001c;
        if (eVar6 != null) {
            i = eVar6.i;
            eVarB.f4001c = eVar6;
            eVar6.f4000b = eVarB;
            eVar.f4001c = null;
        } else {
            i = 0;
        }
        e<K, V> eVar7 = eVar.f4002d;
        if (eVar7 != null) {
            i2 = eVar7.i;
            eVarB.f4002d = eVar7;
            eVar7.f4000b = eVarB;
            eVar.f4002d = null;
        }
        eVarB.i = Math.max(i, i2) + 1;
        c(eVar, eVarB);
    }

    e<K, V> removeInternalByKey(Object obj) {
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
        this.comparator = comparator == null ? f3991b : comparator;
    }

    static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e<K, V> f4000b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e<K, V> f4001c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        e<K, V> f4002d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        e<K, V> f4003e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        e<K, V> f4004f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final K f4005g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        V f4006h;
        int i;

        e() {
            this.f4005g = null;
            this.f4004f = this;
            this.f4003e = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4001c; eVar2 != null; eVar2 = eVar2.f4001c) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4002d; eVar2 != null; eVar2 = eVar2.f4002d) {
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
            K k = this.f4005g;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.f4006h;
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
            return this.f4005g;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f4006h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f4005g;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.f4006h;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f4006h;
            this.f4006h = v;
            return v2;
        }

        public String toString() {
            return this.f4005g + "=" + this.f4006h;
        }

        e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f4000b = eVar;
            this.f4005g = k;
            this.i = 1;
            this.f4003e = eVar2;
            this.f4004f = eVar3;
            eVar3.f4003e = this;
            eVar2.f4004f = this;
        }
    }
}
