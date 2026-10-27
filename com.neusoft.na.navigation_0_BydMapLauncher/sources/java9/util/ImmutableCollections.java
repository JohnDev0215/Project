package java9.util;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import java9.util.Iterators;

/* JADX INFO: loaded from: classes3.dex */
final class ImmutableCollections {
    static final int EXPAND_FACTOR = 2;
    static final int SALT;

    static {
        long jNanoTime = System.nanoTime();
        SALT = (int) (jNanoTime ^ (jNanoTime >>> 32));
    }

    private ImmutableCollections() {
    }

    static UnsupportedOperationException uoe() {
        return new UnsupportedOperationException();
    }

    static abstract class AbstractImmutableCollection<E> extends AbstractCollection<E> {
        AbstractImmutableCollection() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw ImmutableCollections.uoe();
        }
    }

    static <E> List<E> listCopy(Collection<? extends E> collection) {
        if ((collection instanceof AbstractImmutableList) && collection.getClass() != SubList.class) {
            return (List) collection;
        }
        return Lists.of(collection.toArray());
    }

    static abstract class AbstractImmutableList<E> extends AbstractImmutableCollection<E> implements List<E>, RandomAccess {
        AbstractImmutableList() {
        }

        @Override // java.util.List
        public void add(int i, E e) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            ImmutableCollections.rangeCheckForAdd(i, size());
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.List
        public E remove(int i) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.List
        public E set(int i, E e) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.List
        public List<E> subList(int i, int i2) {
            subListRangeCheck(i, i2, size());
            return SubList.fromList(this, i, i2);
        }

        static void subListRangeCheck(int i, int i2, int i3) {
            if (i < 0) {
                throw new IndexOutOfBoundsException("fromIndex = " + i);
            }
            if (i2 > i3) {
                throw new IndexOutOfBoundsException("toIndex = " + i2);
            }
            if (i > i2) {
                throw new IllegalArgumentException("fromIndex(" + i + ") > toIndex(" + i2 + ")");
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<E> iterator() {
            return new ListItr(this, size());
        }

        @Override // java.util.List
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.List
        public ListIterator<E> listIterator(int i) {
            int size = size();
            if (i < 0 || i > size) {
                throw outOfBounds(i);
            }
            return new ListItr(this, size, i);
        }

        @Override // java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof List)) {
                return false;
            }
            Iterator<E> it = ((List) obj).iterator();
            int size = size();
            for (int i = 0; i < size; i++) {
                if (!it.hasNext() || !get(i).equals(it.next())) {
                    return false;
                }
            }
            return !it.hasNext();
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            Objects.requireNonNull(obj);
            int size = size();
            for (int i = 0; i < size; i++) {
                if (obj.equals(get(i))) {
                    return i;
                }
            }
            return -1;
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            Objects.requireNonNull(obj);
            for (int size = size() - 1; size >= 0; size--) {
                if (obj.equals(get(size))) {
                    return size;
                }
            }
            return -1;
        }

        @Override // java.util.Collection, java.util.List
        public int hashCode() {
            int size = size();
            int iHashCode = 1;
            for (int i = 0; i < size; i++) {
                iHashCode = (iHashCode * 31) + get(i).hashCode();
            }
            return iHashCode;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return indexOf(obj) >= 0;
        }

        IndexOutOfBoundsException outOfBounds(int i) {
            return new IndexOutOfBoundsException("Index: " + i + " Size: " + size());
        }
    }

    static final class ListItr<E> implements ListIterator<E> {
        private int cursor;
        private final boolean isListIterator;
        private final List<E> list;
        private final int size;

        ListItr(List<E> list, int i) {
            this.list = list;
            this.size = i;
            this.cursor = 0;
            this.isListIterator = false;
        }

        ListItr(List<E> list, int i, int i2) {
            this.list = list;
            this.size = i;
            this.cursor = i2;
            this.isListIterator = true;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.cursor != this.size;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            try {
                int i = this.cursor;
                E e = this.list.get(i);
                this.cursor = i + 1;
                return e;
            } catch (IndexOutOfBoundsException unused) {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            if (this.isListIterator) {
                return this.cursor != 0;
            }
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (!this.isListIterator) {
                throw ImmutableCollections.uoe();
            }
            try {
                int i = this.cursor - 1;
                E e = this.list.get(i);
                this.cursor = i;
                return e;
            } catch (IndexOutOfBoundsException unused) {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            if (!this.isListIterator) {
                throw ImmutableCollections.uoe();
            }
            return this.cursor;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            if (!this.isListIterator) {
                throw ImmutableCollections.uoe();
            }
            return this.cursor - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw ImmutableCollections.uoe();
        }
    }

    static final class SubList<E> extends AbstractImmutableList<E> implements RandomAccess {
        private final int offset;
        private final List<E> root;
        private final int size;

        private SubList(List<E> list, int i, int i2) {
            this.root = list;
            this.offset = i;
            this.size = i2;
        }

        static <E> SubList<E> fromSubList(SubList<E> subList, int i, int i2) {
            return new SubList<>(((SubList) subList).root, ((SubList) subList).offset + i, i2 - i);
        }

        static <E> SubList<E> fromList(List<E> list, int i, int i2) {
            return new SubList<>(list, i, i2 - i);
        }

        @Override // java.util.List
        public E get(int i) {
            Objects.checkIndex(i, this.size);
            return this.root.get(this.offset + i);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }

        @Override // java9.util.ImmutableCollections.AbstractImmutableList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<E> iterator() {
            return new ListItr(this, size());
        }

        @Override // java9.util.ImmutableCollections.AbstractImmutableList, java.util.List
        public ListIterator<E> listIterator(int i) {
            rangeCheck(i);
            return new ListItr(this, size(), i);
        }

        @Override // java9.util.ImmutableCollections.AbstractImmutableList, java.util.List
        public List<E> subList(int i, int i2) {
            subListRangeCheck(i, i2, this.size);
            return fromSubList(this, i, i2);
        }

        private void rangeCheck(int i) {
            if (i < 0 || i > this.size) {
                throw outOfBounds(i);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            Object[] objArr = new Object[this.size];
            for (int i = 0; i < this.size; i++) {
                objArr[i] = get(i);
            }
            return objArr;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            int i;
            if (tArr.length < this.size) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.size));
            }
            int i2 = 0;
            while (true) {
                i = this.size;
                if (i2 >= i) {
                    break;
                }
                tArr[i2] = get(i2);
                i2++;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    static final class List12<E> extends AbstractImmutableList<E> implements Serializable {
        private final E e0;
        private final E e1;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return false;
        }

        List12(E e) {
            this.e0 = (E) Objects.requireNonNull(e);
            this.e1 = null;
        }

        List12(E e, E e2) {
            this.e0 = (E) Objects.requireNonNull(e);
            this.e1 = (E) Objects.requireNonNull(e2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.e1 != null ? 2 : 1;
        }

        @Override // java.util.List
        public E get(int i) {
            E e;
            if (i == 0) {
                return this.e0;
            }
            if (i != 1 || (e = this.e1) == null) {
                throw outOfBounds(i);
            }
            return e;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            throw new InvalidObjectException("not serial proxy");
        }

        private Object writeReplace() {
            if (this.e1 == null) {
                return new ColSer(1, this.e0);
            }
            return new ColSer(1, this.e0, this.e1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            E e = this.e1;
            return e == null ? new Object[]{this.e0} : new Object[]{this.e0, e};
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            int i = this.e1 == null ? 1 : 2;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            tArr[0] = this.e0;
            if (i == 2) {
                tArr[1] = this.e1;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    static final class ListN<E> extends AbstractImmutableList<E> implements Serializable {
        static final List<?> EMPTY_LIST = new ListN(new Object[0]);
        private final E[] elements;

        /* JADX WARN: Multi-variable type inference failed */
        ListN(E... eArr) {
            E[] eArr2 = (E[]) new Object[eArr.length];
            for (int i = 0; i < eArr.length; i++) {
                eArr2[i] = Objects.requireNonNull(eArr[i]);
            }
            this.elements = eArr2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.elements.length == 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.elements.length;
        }

        @Override // java.util.List
        public E get(int i) {
            return this.elements[i];
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            throw new InvalidObjectException("not serial proxy");
        }

        private Object writeReplace() {
            return new ColSer(1, this.elements);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            E[] eArr = this.elements;
            return Arrays.copyOf(eArr, eArr.length);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            E[] eArr = this.elements;
            int length = eArr.length;
            if (tArr.length < length) {
                return (T[]) Arrays.copyOf(eArr, length, tArr.getClass());
            }
            System.arraycopy(eArr, 0, tArr, 0, length);
            if (tArr.length > length) {
                tArr[length] = null;
            }
            return tArr;
        }
    }

    static abstract class AbstractImmutableSet<E> extends AbstractImmutableCollection<E> implements Set<E> {
        @Override // java.util.Collection, java.util.Set
        public abstract int hashCode();

        AbstractImmutableSet() {
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Set)) {
                return false;
            }
            Collection collection = (Collection) obj;
            if (collection.size() != size()) {
                return false;
            }
            for (E e : collection) {
                if (e == null || !contains(e)) {
                    return false;
                }
            }
            return true;
        }
    }

    static final class Set12<E> extends AbstractImmutableSet<E> implements Serializable {
        final E e0;
        final E e1;

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return false;
        }

        Set12(E e) {
            this.e0 = (E) Objects.requireNonNull(e);
            this.e1 = null;
        }

        Set12(E e, E e2) {
            if (e.equals(Objects.requireNonNull(e2))) {
                throw new IllegalArgumentException("duplicate element: " + e);
            }
            this.e0 = e;
            this.e1 = e2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.e1 == null ? 1 : 2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return obj.equals(this.e0) || obj.equals(this.e1);
        }

        @Override // java9.util.ImmutableCollections.AbstractImmutableSet, java.util.Collection, java.util.Set
        public int hashCode() {
            int iHashCode = this.e0.hashCode();
            E e = this.e1;
            return iHashCode + (e == null ? 0 : e.hashCode());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new Iterators.ImmutableIt<E>() { // from class: java9.util.ImmutableCollections.Set12.1
                private int idx;

                {
                    this.idx = Set12.this.size();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.idx > 0;
                }

                @Override // java.util.Iterator
                public E next() {
                    int i = this.idx;
                    if (i == 1) {
                        this.idx = 0;
                        return (ImmutableCollections.SALT >= 0 || Set12.this.e1 == null) ? Set12.this.e0 : Set12.this.e1;
                    }
                    if (i == 2) {
                        this.idx = 1;
                        return ImmutableCollections.SALT >= 0 ? Set12.this.e1 : Set12.this.e0;
                    }
                    throw new NoSuchElementException();
                }
            };
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            throw new InvalidObjectException("not serial proxy");
        }

        private Object writeReplace() {
            if (this.e1 == null) {
                return new ColSer(2, this.e0);
            }
            return new ColSer(2, this.e0, this.e1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return this.e1 == null ? new Object[]{this.e0} : ImmutableCollections.SALT >= 0 ? new Object[]{this.e1, this.e0} : new Object[]{this.e0, this.e1};
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            int i = this.e1 == null ? 1 : 2;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            if (i == 1) {
                tArr[0] = this.e0;
            } else if (ImmutableCollections.SALT >= 0) {
                tArr[0] = this.e1;
                tArr[1] = this.e0;
            } else {
                tArr[0] = this.e0;
                tArr[1] = this.e1;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    static final class SetN<E> extends AbstractImmutableSet<E> implements Serializable {
        static final Set<?> EMPTY_SET = new SetN(new Object[0]);
        final E[] elements;
        private final int size;

        SetN(E... eArr) {
            this.size = eArr.length;
            this.elements = (E[]) new Object[eArr.length * 2];
            for (E e : eArr) {
                int iProbe = probe(e);
                if (iProbe >= 0) {
                    throw new IllegalArgumentException("duplicate element: " + e);
                }
                this.elements[-(iProbe + 1)] = e;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.size == 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Objects.requireNonNull(obj);
            return this.size > 0 && probe(obj) >= 0;
        }

        private final class SetNIterator extends Iterators.ImmutableIt<E> implements Iterator<E> {
            private int idx;
            private int remaining;

            SetNIterator() {
                int size = SetN.this.size();
                this.remaining = size;
                if (size > 0) {
                    this.idx = ImmutableCollections.floorMod(ImmutableCollections.SALT, SetN.this.elements.length);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.remaining > 0;
            }

            private int nextIndex() {
                int length;
                int i = this.idx;
                if (ImmutableCollections.SALT >= 0) {
                    length = i + 1;
                    if (length >= SetN.this.elements.length) {
                        length = 0;
                    }
                } else {
                    length = i - 1;
                    if (length < 0) {
                        length = SetN.this.elements.length - 1;
                    }
                }
                this.idx = length;
                return length;
            }

            @Override // java.util.Iterator
            public E next() {
                E e;
                if (this.remaining > 0) {
                    do {
                        e = SetN.this.elements[nextIndex()];
                    } while (e == null);
                    this.remaining--;
                    return e;
                }
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new SetNIterator();
        }

        @Override // java9.util.ImmutableCollections.AbstractImmutableSet, java.util.Collection, java.util.Set
        public int hashCode() {
            int iHashCode = 0;
            for (E e : this.elements) {
                if (e != null) {
                    iHashCode += e.hashCode();
                }
            }
            return iHashCode;
        }

        private int probe(Object obj) {
            int iFloorMod = ImmutableCollections.floorMod(obj.hashCode(), this.elements.length);
            while (true) {
                E e = this.elements[iFloorMod];
                if (e == null) {
                    return (-iFloorMod) - 1;
                }
                if (obj.equals(e)) {
                    return iFloorMod;
                }
                iFloorMod++;
                if (iFloorMod == this.elements.length) {
                    iFloorMod = 0;
                }
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            throw new InvalidObjectException("not serial proxy");
        }

        private Object writeReplace() {
            Object[] objArr = new Object[this.size];
            int i = 0;
            for (E e : this.elements) {
                if (e != null) {
                    objArr[i] = e;
                    i++;
                }
            }
            return new ColSer(2, objArr);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            Object[] objArr = new Object[this.size];
            Iterator<E> it = iterator();
            for (int i = 0; i < this.size; i++) {
                objArr[i] = it.next();
            }
            return objArr;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            int i;
            if (tArr.length < this.size) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.size));
            }
            Iterator<E> it = iterator();
            int i2 = 0;
            while (true) {
                i = this.size;
                if (i2 >= i) {
                    break;
                }
                tArr[i2] = it.next();
                i2++;
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }
    }

    static abstract class AbstractImmutableMap<K, V> extends AbstractMap<K, V> implements Serializable {
        AbstractImmutableMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V put(K k, V v) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void putAll(Map<? extends K, ? extends V> map) {
            throw ImmutableCollections.uoe();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V remove(Object obj) {
            throw ImmutableCollections.uoe();
        }
    }

    static final class Map1<K, V> extends AbstractImmutableMap<K, V> {
        private final K k0;
        private final V v0;

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return 1;
        }

        Map1(K k, V v) {
            this.k0 = (K) Objects.requireNonNull(k);
            this.v0 = (V) Objects.requireNonNull(v);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return Sets.of(new KeyValueHolder(this.k0, this.v0));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (obj.equals(this.k0)) {
                return this.v0;
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return obj.equals(this.k0);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(Object obj) {
            return obj.equals(this.v0);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            throw new InvalidObjectException("not serial proxy");
        }

        private Object writeReplace() {
            return new ColSer(3, this.k0, this.v0);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return this.k0.hashCode() ^ this.v0.hashCode();
        }
    }

    static final class MapN<K, V> extends AbstractImmutableMap<K, V> {
        static final Map<?, ?> EMPTY_MAP = new MapN(new Object[0]);
        final int size;
        final Object[] table;

        MapN(Object... objArr) {
            if ((objArr.length & 1) != 0) {
                throw new InternalError("length is odd");
            }
            this.size = objArr.length >> 1;
            this.table = new Object[((objArr.length * 2) + 1) & (-2)];
            for (int i = 0; i < objArr.length; i += 2) {
                Object objRequireNonNull = Objects.requireNonNull(objArr[i]);
                Object objRequireNonNull2 = Objects.requireNonNull(objArr[i + 1]);
                int iProbe = probe(objRequireNonNull);
                if (iProbe >= 0) {
                    throw new IllegalArgumentException("duplicate key: " + objRequireNonNull);
                }
                int i2 = -(iProbe + 1);
                Object[] objArr2 = this.table;
                objArr2[i2] = objRequireNonNull;
                objArr2[i2 + 1] = objRequireNonNull2;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            Objects.requireNonNull(obj);
            return this.size > 0 && probe(obj) >= 0;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(Object obj) {
            Objects.requireNonNull(obj);
            int i = 1;
            while (true) {
                Object[] objArr = this.table;
                if (i >= objArr.length) {
                    return false;
                }
                Object obj2 = objArr[i];
                if (obj2 != null && obj.equals(obj2)) {
                    return true;
                }
                i += 2;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            int i = 0;
            int iHashCode = 0;
            while (true) {
                Object[] objArr = this.table;
                if (i >= objArr.length) {
                    return iHashCode;
                }
                Object obj = objArr[i];
                if (obj != null) {
                    iHashCode += obj.hashCode() ^ this.table[i + 1].hashCode();
                }
                i += 2;
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(Object obj) {
            if (this.size == 0) {
                Objects.requireNonNull(obj);
                return null;
            }
            int iProbe = probe(obj);
            if (iProbe >= 0) {
                return (V) this.table[iProbe + 1];
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.size == 0;
        }

        class MapNIterator extends Iterators.ImmutableIt<Map.Entry<K, V>> implements Iterator<Map.Entry<K, V>> {
            private int idx;
            private int remaining;

            MapNIterator() {
                int size = MapN.this.size();
                this.remaining = size;
                if (size > 0) {
                    this.idx = ImmutableCollections.floorMod(ImmutableCollections.SALT, MapN.this.table.length >> 1) << 1;
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.remaining > 0;
            }

            private int nextIndex() {
                int length;
                int i = this.idx;
                if (ImmutableCollections.SALT >= 0) {
                    length = i + 2;
                    if (length >= MapN.this.table.length) {
                        length = 0;
                    }
                } else {
                    length = i - 2;
                    if (length < 0) {
                        length = MapN.this.table.length - 2;
                    }
                }
                this.idx = length;
                return length;
            }

            @Override // java.util.Iterator
            public Map.Entry<K, V> next() {
                Object[] objArr;
                int iNextIndex;
                if (this.remaining > 0) {
                    do {
                        objArr = MapN.this.table;
                        iNextIndex = nextIndex();
                    } while (objArr[iNextIndex] == null);
                    this.remaining--;
                    return new KeyValueHolder(MapN.this.table[iNextIndex], MapN.this.table[iNextIndex + 1]);
                }
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<K, V>> entrySet() {
            return new AbstractSet<Map.Entry<K, V>>() { // from class: java9.util.ImmutableCollections.MapN.1
                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return MapN.this.size;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, V>> iterator() {
                    return new MapNIterator();
                }
            };
        }

        private int probe(Object obj) {
            int iFloorMod = ImmutableCollections.floorMod(obj.hashCode(), this.table.length >> 1) << 1;
            while (true) {
                Object obj2 = this.table[iFloorMod];
                if (obj2 == null) {
                    return (-iFloorMod) - 1;
                }
                if (obj.equals(obj2)) {
                    return iFloorMod;
                }
                iFloorMod += 2;
                if (iFloorMod == this.table.length) {
                    iFloorMod = 0;
                }
            }
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            throw new InvalidObjectException("not serial proxy");
        }

        private Object writeReplace() {
            Object[] objArr = new Object[this.size * 2];
            int length = this.table.length;
            int i = 0;
            for (int i2 = 0; i2 < length; i2 += 2) {
                Object[] objArr2 = this.table;
                Object obj = objArr2[i2];
                if (obj != null) {
                    int i3 = i + 1;
                    objArr[i] = obj;
                    i = i3 + 1;
                    objArr[i3] = objArr2[i2 + 1];
                }
            }
            return new ColSer(3, objArr);
        }
    }

    static void rangeCheckForAdd(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int floorMod(int i, int i2) {
        int i3 = i % i2;
        return ((i3 ^ i2) >= 0 || i3 == 0) ? i3 : i3 + i2;
    }
}
