package defpackage;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: compiled from: MapCollections.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class xb1<K, V> {
    public xb1<K, V>.b a;
    public xb1<K, V>.c b;
    public xb1<K, V>.e c;

    /* JADX INFO: compiled from: MapCollections.java */
    public final class a<T> implements Iterator<T> {
        public final int e;
        public int f;
        public int g;
        public boolean h = false;

        public a(int i) {
            this.e = i;
            this.f = ((l8) xb1.this).d.g;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.g < this.f;
        }

        @Override // java.util.Iterator
        public final T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t = (T) xb1.this.a(this.g, this.e);
            this.g++;
            this.h = true;
            return t;
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (!this.h) {
                throw new IllegalStateException();
            }
            int i = this.g - 1;
            this.g = i;
            this.f--;
            this.h = false;
            xb1.this.b(i);
        }
    }

    /* JADX INFO: compiled from: MapCollections.java */
    public final class b implements Set<Map.Entry<K, V>> {
        public b() {
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            xb1 xb1Var = xb1.this;
            int i = ((l8) xb1Var).d.g;
            for (Map.Entry<K, V> entry : collection) {
                ((l8) xb1Var).d.put(entry.getKey(), entry.getValue());
            }
            return i != ((l8) xb1Var).d.g;
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            ((l8) xb1.this).d.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            xb1 xb1Var = xb1.this;
            int iE = ((l8) xb1Var).d.e(key);
            if (iE < 0) {
                return false;
            }
            Object objA = xb1Var.a(iE, 1);
            Object value = entry.getValue();
            return objA == value || (objA != null && objA.equals(value));
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            return xb1.c(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            xb1 xb1Var = xb1.this;
            int iHashCode = 0;
            for (int i = ((l8) xb1Var).d.g - 1; i >= 0; i--) {
                Object objA = xb1Var.a(i, 0);
                Object objA2 = xb1Var.a(i, 1);
                iHashCode += (objA == null ? 0 : objA.hashCode()) ^ (objA2 == null ? 0 : objA2.hashCode());
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return ((l8) xb1.this).d.g == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator<Map.Entry<K, V>> iterator() {
            return new d();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return ((l8) xb1.this).d.g;
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: compiled from: MapCollections.java */
    public final class d implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        public int e;
        public boolean g = false;
        public int f = -1;

        public d() {
            this.e = ((l8) xb1.this).d.g - 1;
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
            xb1 xb1Var = xb1.this;
            Object objA = xb1Var.a(i, 0);
            if (!(key == objA || (key != null && key.equals(objA)))) {
                return false;
            }
            Object value = entry.getValue();
            Object objA2 = xb1Var.a(this.f, 1);
            return value == objA2 || (value != null && value.equals(objA2));
        }

        @Override // java.util.Map.Entry
        public final K getKey() {
            if (!this.g) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (K) xb1.this.a(this.f, 0);
        }

        @Override // java.util.Map.Entry
        public final V getValue() {
            if (!this.g) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (V) xb1.this.a(this.f, 1);
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
            xb1 xb1Var = xb1.this;
            Object objA = xb1Var.a(i, 0);
            Object objA2 = xb1Var.a(this.f, 1);
            return (objA == null ? 0 : objA.hashCode()) ^ (objA2 != null ? objA2.hashCode() : 0);
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
            xb1.this.b(this.f);
            this.f--;
            this.e--;
            this.g = false;
        }

        @Override // java.util.Map.Entry
        public final V setValue(V v) {
            if (!this.g) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            int i = (this.f << 1) + 1;
            Object[] objArr = ((l8) xb1.this).d.f;
            V v2 = (V) objArr[i];
            objArr[i] = v;
            return v2;
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    public static <T> boolean c(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public abstract Object a(int i, int i2);

    public abstract void b(int i);

    public final Object[] d(int i, Object[] objArr) {
        int i2 = ((l8) this).d.g;
        if (objArr.length < i2) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i2);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = a(i3, i);
        }
        if (objArr.length > i2) {
            objArr[i2] = null;
        }
        return objArr;
    }

    /* JADX INFO: compiled from: MapCollections.java */
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
            ((l8) xb1.this).d.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            return ((l8) xb1.this).d.e(obj) >= 0;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            n8 n8Var = ((l8) xb1.this).d;
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                if (!n8Var.containsKey(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            return xb1.c(this, obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            xb1 xb1Var = xb1.this;
            int iHashCode = 0;
            for (int i = ((l8) xb1Var).d.g - 1; i >= 0; i--) {
                Object objA = xb1Var.a(i, 0);
                iHashCode += objA == null ? 0 : objA.hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return ((l8) xb1.this).d.g == 0;
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator<K> iterator() {
            return new a(0);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            xb1 xb1Var = xb1.this;
            int iE = ((l8) xb1Var).d.e(obj);
            if (iE < 0) {
                return false;
            }
            xb1Var.b(iE);
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            n8 n8Var = ((l8) xb1.this).d;
            int size = n8Var.size();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                n8Var.remove(it.next());
            }
            return size != n8Var.size();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            n8 n8Var = ((l8) xb1.this).d;
            int size = n8Var.size();
            Iterator<K> it = n8Var.keySet().iterator();
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                }
            }
            return size != n8Var.size();
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return ((l8) xb1.this).d.g;
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            xb1 xb1Var = xb1.this;
            xb1Var.getClass();
            int i = ((l8) xb1Var).d.g;
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = xb1Var.a(i2, 0);
            }
            return objArr;
        }

        @Override // java.util.Set, java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) xb1.this.d(0, tArr);
        }
    }

    /* JADX INFO: compiled from: MapCollections.java */
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
            ((l8) xb1.this).d.clear();
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            return ((l8) xb1.this).d.g(obj) >= 0;
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
            return ((l8) xb1.this).d.g == 0;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            return new a(1);
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            xb1 xb1Var = xb1.this;
            int iG = ((l8) xb1Var).d.g(obj);
            if (iG < 0) {
                return false;
            }
            xb1Var.b(iG);
            return true;
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection<?> collection) {
            xb1 xb1Var = xb1.this;
            int i = ((l8) xb1Var).d.g;
            int i2 = 0;
            boolean z = false;
            while (i2 < i) {
                if (collection.contains(xb1Var.a(i2, 1))) {
                    xb1Var.b(i2);
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
            xb1 xb1Var = xb1.this;
            int i = ((l8) xb1Var).d.g;
            int i2 = 0;
            boolean z = false;
            while (i2 < i) {
                if (!collection.contains(xb1Var.a(i2, 1))) {
                    xb1Var.b(i2);
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
            return ((l8) xb1.this).d.g;
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            xb1 xb1Var = xb1.this;
            xb1Var.getClass();
            int i = ((l8) xb1Var).d.g;
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = xb1Var.a(i2, 1);
            }
            return objArr;
        }

        @Override // java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) xb1.this.d(1, tArr);
        }
    }
}
