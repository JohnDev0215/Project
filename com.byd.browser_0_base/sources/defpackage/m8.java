package defpackage;

import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class m8<K, V> extends wm2<K, V> implements Map<K, V> {
    public m8<K, V>.a h;
    public m8<K, V>.c i;
    public m8<K, V>.e j;

    /* JADX INFO: compiled from: ArrayMap.java */
    public final class a extends AbstractSet<Map.Entry<K, V>> {
        public a() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return m8.this.g;
        }
    }

    /* JADX INFO: compiled from: ArrayMap.java */
    public final class b extends pz0<K> {
        public b() {
            super(m8.this.g);
        }

        @Override // defpackage.pz0
        public final K b(int i) {
            return m8.this.f(i);
        }

        @Override // defpackage.pz0
        public final void c(int i) {
            m8.this.g(i);
        }
    }

    /* JADX INFO: compiled from: ArrayMap.java */
    public final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        public int e;
        public int f = -1;
        public boolean g;

        public d() {
            this.e = m8.this.g - 1;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (!this.g) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            int i = this.f;
            m8 m8Var = m8.this;
            return i21.a(key, m8Var.f(i)) && i21.a(entry.getValue(), m8Var.i(this.f));
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            if (this.g) {
                return m8.this.f(this.f);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            if (this.g) {
                return m8.this.i(this.f);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f < this.e;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            if (!this.g) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            int i = this.f;
            m8 m8Var = m8.this;
            K kF = m8Var.f(i);
            V vI = m8Var.i(this.f);
            return (kF == null ? 0 : kF.hashCode()) ^ (vI != null ? vI.hashCode() : 0);
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.f++;
            this.g = true;
            return this;
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.g) {
                throw new IllegalStateException();
            }
            m8.this.g(this.f);
            this.f--;
            this.e--;
            this.g = false;
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            if (this.g) {
                return m8.this.h(this.f, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* JADX INFO: compiled from: ArrayMap.java */
    public final class f extends pz0<V> {
        public f() {
            super(m8.this.g);
        }

        @Override // defpackage.pz0
        public final V b(int i) {
            return m8.this.i(i);
        }

        @Override // defpackage.pz0
        public final void c(int i) {
            m8.this.g(i);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m8(m8 m8Var) {
        super(0);
        if (m8Var != null) {
            int i = m8Var.g;
            b(this.g + i);
            if (this.g != 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    put(m8Var.f(i2), m8Var.i(i2));
                }
            } else if (i > 0) {
                r8.p0(0, 0, i, m8Var.e, this.e);
                r8.q0(0, 0, i << 1, m8Var.f, this.f);
                this.g = i;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.wm2, java.util.Map
    public final boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.wm2, java.util.Map
    public final boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        m8<K, V>.a aVar = this.h;
        if (aVar != null) {
            return aVar;
        }
        m8<K, V>.a aVar2 = new a();
        this.h = aVar2;
        return aVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.wm2, java.util.Map
    public final V get(Object obj) {
        return (V) super.get(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean j(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!super.containsKey(it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean k(Collection<?> collection) {
        int i = this.g;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            super.remove(it.next());
        }
        return i != this.g;
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        m8<K, V>.c cVar = this.i;
        if (cVar != null) {
            return cVar;
        }
        m8<K, V>.c cVar2 = new c();
        this.i = cVar2;
        return cVar2;
    }

    public final boolean l(Collection<?> collection) {
        int i = this.g;
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (!collection.contains(f(i2))) {
                g(i2);
            }
        }
        return i != this.g;
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        b(map.size() + this.g);
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.wm2, java.util.Map
    public final V remove(Object obj) {
        return (V) super.remove(obj);
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        m8<K, V>.e eVar = this.j;
        if (eVar != null) {
            return eVar;
        }
        m8<K, V>.e eVar2 = new e();
        this.j = eVar2;
        return eVar2;
    }

    /* JADX INFO: compiled from: ArrayMap.java */
    public final class c implements Set<K> {
        public c() {
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            m8.this.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            return m8.this.containsKey(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            return m8.this.j(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Set) {
                Set set = (Set) obj;
                try {
                    if (size() == set.size()) {
                        if (containsAll(set)) {
                            return true;
                        }
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            m8 m8Var = m8.this;
            int iHashCode = 0;
            for (int i = m8Var.g - 1; i >= 0; i--) {
                K kF = m8Var.f(i);
                iHashCode += kF == null ? 0 : kF.hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return m8.this.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator<K> iterator() {
            return new b();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            m8 m8Var = m8.this;
            int iD = m8Var.d(obj);
            if (iD < 0) {
                return false;
            }
            m8Var.g(iD);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            return m8.this.k(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            return m8.this.l(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return m8.this.g;
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            m8 m8Var = m8.this;
            int i = m8Var.g;
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = m8Var.f(i2);
            }
            return objArr;
        }

        @Override // java.util.Set, java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            m8 m8Var = m8.this;
            int i = m8Var.g;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = m8Var.f(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    /* JADX INFO: compiled from: ArrayMap.java */
    public final class e implements Collection<V> {
        public e() {
        }

        @Override // java.util.Collection
        public final boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final void clear() {
            m8.this.clear();
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            return m8.this.a(obj) >= 0;
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return m8.this.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            return new f();
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            m8 m8Var = m8.this;
            int iA = m8Var.a(obj);
            if (iA < 0) {
                return false;
            }
            m8Var.g(iA);
            return true;
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            m8 m8Var = m8.this;
            int i = m8Var.g;
            int i2 = 0;
            boolean z = false;
            while (i2 < i) {
                if (collection.contains(m8Var.i(i2))) {
                    m8Var.g(i2);
                    i2--;
                    i--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            m8 m8Var = m8.this;
            int i = m8Var.g;
            int i2 = 0;
            boolean z = false;
            while (i2 < i) {
                if (!collection.contains(m8Var.i(i2))) {
                    m8Var.g(i2);
                    i2--;
                    i--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public final int size() {
            return m8.this.g;
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            m8 m8Var = m8.this;
            int i = m8Var.g;
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = m8Var.i(i2);
            }
            return objArr;
        }

        @Override // java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            m8 m8Var = m8.this;
            int i = m8Var.g;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = m8Var.i(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    public m8() {
    }
}
