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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Comparator<Comparable> f4933b = new a();
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
                return a().f4947g;
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
        public e<K, V> f4938b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e<K, V> f4939c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4940d;

        public d() {
            this.f4938b = LinkedTreeMap.this.header.f4945e;
            this.f4940d = LinkedTreeMap.this.modCount;
        }

        public final e<K, V> a() {
            e<K, V> eVar = this.f4938b;
            LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
            if (eVar == linkedTreeMap.header) {
                throw new NoSuchElementException();
            }
            if (linkedTreeMap.modCount != this.f4940d) {
                throw new ConcurrentModificationException();
            }
            this.f4938b = eVar.f4945e;
            this.f4939c = eVar;
            return eVar;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f4938b != LinkedTreeMap.this.header;
        }

        @Override // java.util.Iterator
        public final void remove() {
            e<K, V> eVar = this.f4939c;
            if (eVar == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(eVar, true);
            this.f4939c = null;
            this.f4940d = LinkedTreeMap.this.modCount;
        }
    }

    public LinkedTreeMap() {
        this(f4933b);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public final void b(e<K, V> eVar, boolean z) {
        while (eVar != null) {
            e<K, V> eVar2 = eVar.f4943c;
            e<K, V> eVar3 = eVar.f4944d;
            int i2 = eVar2 != null ? eVar2.f4949i : 0;
            int i3 = eVar3 != null ? eVar3.f4949i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e<K, V> eVar4 = eVar3.f4943c;
                e<K, V> eVar5 = eVar3.f4944d;
                int i5 = (eVar4 != null ? eVar4.f4949i : 0) - (eVar5 != null ? eVar5.f4949i : 0);
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
                e<K, V> eVar6 = eVar2.f4943c;
                e<K, V> eVar7 = eVar2.f4944d;
                int i6 = (eVar6 != null ? eVar6.f4949i : 0) - (eVar7 != null ? eVar7.f4949i : 0);
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
                eVar.f4949i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                eVar.f4949i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            eVar = eVar.f4942b;
        }
    }

    public final void c(e<K, V> eVar, e<K, V> eVar2) {
        e<K, V> eVar3 = eVar.f4942b;
        eVar.f4942b = null;
        if (eVar2 != null) {
            eVar2.f4942b = eVar3;
        }
        if (eVar3 == null) {
            this.root = eVar2;
        } else if (eVar3.f4943c == eVar) {
            eVar3.f4943c = eVar2;
        } else {
            eVar3.f4944d = eVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        e<K, V> eVar = this.header;
        eVar.f4946f = eVar;
        eVar.f4945e = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public final void d(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4943c;
        e<K, V> eVar3 = eVar.f4944d;
        e<K, V> eVar4 = eVar3.f4943c;
        e<K, V> eVar5 = eVar3.f4944d;
        eVar.f4944d = eVar4;
        if (eVar4 != null) {
            eVar4.f4942b = eVar;
        }
        c(eVar, eVar3);
        eVar3.f4943c = eVar;
        eVar.f4942b = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.f4949i : 0, eVar4 != null ? eVar4.f4949i : 0) + 1;
        eVar.f4949i = iMax;
        eVar3.f4949i = Math.max(iMax, eVar5 != null ? eVar5.f4949i : 0) + 1;
    }

    public final void e(e<K, V> eVar) {
        e<K, V> eVar2 = eVar.f4943c;
        e<K, V> eVar3 = eVar.f4944d;
        e<K, V> eVar4 = eVar2.f4943c;
        e<K, V> eVar5 = eVar2.f4944d;
        eVar.f4943c = eVar5;
        if (eVar5 != null) {
            eVar5.f4942b = eVar;
        }
        c(eVar, eVar2);
        eVar2.f4944d = eVar;
        eVar.f4942b = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.f4949i : 0, eVar5 != null ? eVar5.f4949i : 0) + 1;
        eVar.f4949i = iMax;
        eVar2.f4949i = Math.max(iMax, eVar4 != null ? eVar4.f4949i : 0) + 1;
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
            Comparable comparable = comparator == f4933b ? (Comparable) k : null;
            while (true) {
                iCompareTo = comparable != null ? comparable.compareTo(eVar2.f4947g) : comparator.compare(k, eVar2.f4947g);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e<K, V> eVar3 = iCompareTo < 0 ? eVar2.f4943c : eVar2.f4944d;
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
            eVar = new e<>(eVar2, k, eVar4, eVar4.f4946f);
            if (iCompareTo < 0) {
                eVar2.f4943c = eVar;
            } else {
                eVar2.f4944d = eVar;
            }
            b(eVar2, true);
        } else {
            if (comparator == f4933b && !(k instanceof Comparable)) {
                throw new ClassCastException(k.getClass().getName() + " is not Comparable");
            }
            eVar = new e<>(eVar2, k, eVar4, eVar4.f4946f);
            this.root = eVar;
        }
        this.size++;
        this.modCount++;
        return eVar;
    }

    public e<K, V> findByEntry(Map.Entry<?, ?> entry) {
        e<K, V> eVarFindByObject = findByObject(entry.getKey());
        if (eVarFindByObject != null && a(eVarFindByObject.f4948h, entry.getValue())) {
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
            return eVarFindByObject.f4948h;
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
        V v2 = eVarFind.f4948h;
        eVarFind.f4948h = v;
        return v2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        e<K, V> eVarRemoveInternalByKey = removeInternalByKey(obj);
        if (eVarRemoveInternalByKey != null) {
            return eVarRemoveInternalByKey.f4948h;
        }
        return null;
    }

    public void removeInternal(e<K, V> eVar, boolean z) {
        int i2;
        if (z) {
            e<K, V> eVar2 = eVar.f4946f;
            eVar2.f4945e = eVar.f4945e;
            eVar.f4945e.f4946f = eVar2;
        }
        e<K, V> eVar3 = eVar.f4943c;
        e<K, V> eVar4 = eVar.f4944d;
        e<K, V> eVar5 = eVar.f4942b;
        int i3 = 0;
        if (eVar3 == null || eVar4 == null) {
            if (eVar3 != null) {
                c(eVar, eVar3);
                eVar.f4943c = null;
            } else if (eVar4 != null) {
                c(eVar, eVar4);
                eVar.f4944d = null;
            } else {
                c(eVar, null);
            }
            b(eVar5, false);
            this.size--;
            this.modCount++;
            return;
        }
        e<K, V> eVarB = eVar3.f4949i > eVar4.f4949i ? eVar3.b() : eVar4.a();
        removeInternal(eVarB, false);
        e<K, V> eVar6 = eVar.f4943c;
        if (eVar6 != null) {
            i2 = eVar6.f4949i;
            eVarB.f4943c = eVar6;
            eVar6.f4942b = eVarB;
            eVar.f4943c = null;
        } else {
            i2 = 0;
        }
        e<K, V> eVar7 = eVar.f4944d;
        if (eVar7 != null) {
            i3 = eVar7.f4949i;
            eVarB.f4944d = eVar7;
            eVar7.f4942b = eVarB;
            eVar.f4944d = null;
        }
        eVarB.f4949i = Math.max(i2, i3) + 1;
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
        this.comparator = comparator == null ? f4933b : comparator;
    }

    public static final class e<K, V> implements Map.Entry<K, V> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e<K, V> f4942b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e<K, V> f4943c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e<K, V> f4944d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e<K, V> f4945e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public e<K, V> f4946f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final K f4947g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public V f4948h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f4949i;

        public e() {
            this.f4947g = null;
            this.f4946f = this;
            this.f4945e = this;
        }

        public e<K, V> a() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4943c; eVar2 != null; eVar2 = eVar2.f4943c) {
                eVar = eVar2;
            }
            return eVar;
        }

        public e<K, V> b() {
            e<K, V> eVar = this;
            for (e<K, V> eVar2 = this.f4944d; eVar2 != null; eVar2 = eVar2.f4944d) {
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
            K k = this.f4947g;
            if (k == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!k.equals(entry.getKey())) {
                return false;
            }
            V v = this.f4948h;
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
            return this.f4947g;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.f4948h;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.f4947g;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.f4948h;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = this.f4948h;
            this.f4948h = v;
            return v2;
        }

        public String toString() {
            return this.f4947g + "=" + this.f4948h;
        }

        public e(e<K, V> eVar, K k, e<K, V> eVar2, e<K, V> eVar3) {
            this.f4942b = eVar;
            this.f4947g = k;
            this.f4949i = 1;
            this.f4945e = eVar2;
            this.f4946f = eVar3;
            eVar3.f4945e = this;
            eVar2.f4946f = this;
        }
    }
}
