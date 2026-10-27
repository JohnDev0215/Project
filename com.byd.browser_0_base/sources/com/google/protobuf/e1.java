package com.google.protobuf;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: SmallSortedMap.java */
/* JADX INFO: loaded from: classes.dex */
public class e1<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    public static final /* synthetic */ int k = 0;
    public final int e;
    public boolean h;
    public volatile e1<K, V>.d i;
    public List<e1<K, V>.b> f = Collections.emptyList();
    public Map<K, V> g = Collections.emptyMap();
    public Map<K, V> j = Collections.emptyMap();

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public static class a {
        public static final C0044a a = new C0044a();
        public static final b b = new b();

        /* JADX INFO: renamed from: com.google.protobuf.e1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: SmallSortedMap.java */
        public static class C0044a implements Iterator<Object> {
            @Override // java.util.Iterator
            public final boolean hasNext() {
                return false;
            }

            @Override // java.util.Iterator
            public final Object next() {
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public final void remove() {
                throw new UnsupportedOperationException();
            }
        }

        /* JADX INFO: compiled from: SmallSortedMap.java */
        public static class b implements Iterable<Object> {
            @Override // java.lang.Iterable
            public final Iterator<Object> iterator() {
                return a.a;
            }
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class b implements Map.Entry<K, V>, Comparable<e1<K, V>.b> {
        public final K e;
        public V f;

        public b() {
            throw null;
        }

        public b(K k, V v) {
            this.e = k;
            this.f = v;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            return this.e.compareTo(((b) obj).e);
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            K k = this.e;
            if (k == null ? key == null : k.equals(key)) {
                V v = this.f;
                Object value = entry.getValue();
                if (v == null ? value == null : v.equals(value)) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.e;
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            return this.f;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            K k = this.e;
            int iHashCode = k == null ? 0 : k.hashCode();
            V v = this.f;
            return iHashCode ^ (v != null ? v.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            int i = e1.k;
            e1.this.b();
            V v2 = this.f;
            this.f = v;
            return v2;
        }

        public final String toString() {
            return this.e + "=" + this.f;
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class c implements Iterator<Map.Entry<K, V>> {
        public int e = -1;
        public boolean f;
        public Iterator<Map.Entry<K, V>> g;

        public c() {
        }

        public final Iterator<Map.Entry<K, V>> b() {
            if (this.g == null) {
                this.g = e1.this.g.entrySet().iterator();
            }
            return this.g;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            int i = this.e + 1;
            e1 e1Var = e1.this;
            if (i >= e1Var.f.size()) {
                return !e1Var.g.isEmpty() && b().hasNext();
            }
            return true;
        }

        @Override // java.util.Iterator
        public final Object next() {
            this.f = true;
            int i = this.e + 1;
            this.e = i;
            e1 e1Var = e1.this;
            return i < e1Var.f.size() ? e1Var.f.get(this.e) : b().next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.f) {
                throw new IllegalStateException("remove() was called before next()");
            }
            this.f = false;
            int i = e1.k;
            e1 e1Var = e1.this;
            e1Var.b();
            if (this.e >= e1Var.f.size()) {
                b().remove();
                return;
            }
            int i2 = this.e;
            this.e = i2 - 1;
            e1Var.i(i2);
        }
    }

    /* JADX INFO: compiled from: SmallSortedMap.java */
    public class d extends AbstractSet<Map.Entry<K, V>> {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean add(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (contains(entry)) {
                return false;
            }
            e1.this.put((Comparable) entry.getKey(), entry.getValue());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            e1.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = e1.this.get(entry.getKey());
            Object value = entry.getValue();
            return obj2 == value || (obj2 != null && obj2.equals(value));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new c();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (!contains(entry)) {
                return false;
            }
            e1.this.remove(entry.getKey());
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return e1.this.size();
        }
    }

    public e1(int i) {
        this.e = i;
    }

    public final int a(K k2) {
        int size = this.f.size() - 1;
        if (size >= 0) {
            int iCompareTo = k2.compareTo(this.f.get(size).e);
            if (iCompareTo > 0) {
                return -(size + 2);
            }
            if (iCompareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int iCompareTo2 = k2.compareTo(this.f.get(i2).e);
            if (iCompareTo2 < 0) {
                size = i2 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i2;
                }
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    public final void b() {
        if (this.h) {
            throw new UnsupportedOperationException();
        }
    }

    public final Map.Entry<K, V> c(int i) {
        return this.f.get(i);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        b();
        if (!this.f.isEmpty()) {
            this.f.clear();
        }
        if (this.g.isEmpty()) {
            return;
        }
        this.g.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a(comparable) >= 0 || this.g.containsKey(comparable);
    }

    public final int d() {
        return this.f.size();
    }

    public final Iterable<Map.Entry<K, V>> e() {
        return this.g.isEmpty() ? a.b : this.g.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        if (this.i == null) {
            this.i = new d();
        }
        return this.i;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e1)) {
            return super.equals(obj);
        }
        e1 e1Var = (e1) obj;
        int size = size();
        if (size != e1Var.size()) {
            return false;
        }
        int iD = d();
        if (iD != e1Var.d()) {
            return entrySet().equals(e1Var.entrySet());
        }
        for (int i = 0; i < iD; i++) {
            if (!c(i).equals(e1Var.c(i))) {
                return false;
            }
        }
        if (iD != size) {
            return this.g.equals(e1Var.g);
        }
        return true;
    }

    public final SortedMap<K, V> f() {
        b();
        if (this.g.isEmpty() && !(this.g instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.g = treeMap;
            this.j = treeMap.descendingMap();
        }
        return (SortedMap) this.g;
    }

    public void g() {
        if (this.h) {
            return;
        }
        this.g = this.g.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.g);
        this.j = this.j.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.j);
        this.h = true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        return iA >= 0 ? this.f.get(iA).f : this.g.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final V put(K k2, V v) {
        b();
        int iA = a(k2);
        if (iA >= 0) {
            return this.f.get(iA).setValue(v);
        }
        b();
        boolean zIsEmpty = this.f.isEmpty();
        int i = this.e;
        if (zIsEmpty && !(this.f instanceof ArrayList)) {
            this.f = new ArrayList(i);
        }
        int i2 = -(iA + 1);
        if (i2 >= i) {
            return f().put(k2, v);
        }
        if (this.f.size() == i) {
            e1<K, V>.b bVarRemove = this.f.remove(i - 1);
            f().put(bVarRemove.e, bVarRemove.f);
        }
        this.f.add(i2, new b(k2, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int iD = d();
        int iHashCode = 0;
        for (int i = 0; i < iD; i++) {
            iHashCode += this.f.get(i).hashCode();
        }
        return this.g.size() > 0 ? iHashCode + this.g.hashCode() : iHashCode;
    }

    public final V i(int i) {
        b();
        V v = this.f.remove(i).f;
        if (!this.g.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = f().entrySet().iterator();
            List<e1<K, V>.b> list = this.f;
            Map.Entry<K, V> next = it.next();
            list.add(new b(next.getKey(), next.getValue()));
            it.remove();
        }
        return v;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        b();
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        if (iA >= 0) {
            return i(iA);
        }
        if (this.g.isEmpty()) {
            return null;
        }
        return this.g.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.g.size() + this.f.size();
    }
}
