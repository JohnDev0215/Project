package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl._Private_CurriedValueFactory;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes3.dex */
abstract class IonSequenceLite extends IonContainerLite implements IonSequence {
    protected static final IonValueLite[] EMPTY_VALUE_ARRAY = new IonValueLite[0];

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public abstract IonSequenceLite mo103clone();

    @Override // java.util.List
    public /* bridge */ /* synthetic */ IonValue get(int i) {
        return super.get(i);
    }

    IonSequenceLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonSequenceLite(IonSequenceLite ionSequenceLite, IonContext ionContext) {
        super(ionSequenceLite, ionContext, false);
    }

    protected int sequenceHashCode(int i, _Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (!isNullValue()) {
            Iterator<IonValue> it = iterator();
            while (it.hasNext()) {
                int iHashCode = (i * 8191) + ((IonValueLite) it.next()).hashCode(symbolTableProvider);
                i = iHashCode ^ ((iHashCode << 29) ^ (iHashCode >> 3));
            }
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(IonValue ionValue) throws ContainedValueException, NullPointerException {
        return super.add(ionValue);
    }

    public boolean addAll(Collection<? extends IonValue> collection) {
        checkForLock();
        collection.getClass();
        Iterator<? extends IonValue> it = collection.iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                if (add(it.next()) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    public boolean addAll(int i, Collection<? extends IonValue> collection) {
        checkForLock();
        collection.getClass();
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException();
        }
        boolean z = false;
        Iterator<? extends IonValue> it = collection.iterator();
        while (it.hasNext()) {
            add(i, it.next());
            z = true;
            i++;
        }
        if (z) {
            patch_elements_helper(i);
        }
        return z;
    }

    @Override // com.amazon.ion.IonSequence
    public ValueFactory add() {
        return new _Private_CurriedValueFactory(getSystem()) { // from class: com.amazon.ion.impl.lite.IonSequenceLite.1
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            protected void handle(IonValue ionValue) {
                IonSequenceLite.this.add(ionValue);
            }
        };
    }

    @Override // java.util.List
    public void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException {
        add(i, (IonValueLite) ionValue);
    }

    @Override // java.util.List
    public IonValue set(int i, IonValue ionValue) {
        checkForLock();
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("" + i);
        }
        validateNewChild(ionValue);
        ionValueLite._context = getContextForIndex(ionValue, i);
        IonValueLite ionValueLite2 = set_child(i, ionValueLite);
        ionValueLite._elementid(i);
        ionValueLite2.detachFromContainer();
        return ionValueLite2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.List
    public IonValue remove(int i) {
        checkForLock();
        if (i < 0 || i >= get_child_count()) {
            throw new IndexOutOfBoundsException("" + i);
        }
        IonValueLite ionValueLite = get_child(i);
        remove_child(i);
        patch_elements_helper(i);
        return ionValueLite;
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        checkForLock();
        int iLastIndexOf = lastIndexOf(obj);
        if (iLastIndexOf < 0) {
            return false;
        }
        remove_child(iLastIndexOf);
        patch_elements_helper(iLastIndexOf);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        checkForLock();
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            int iLastIndexOf = lastIndexOf(it.next());
            if (iLastIndexOf >= 0) {
                remove_child(iLastIndexOf);
                patch_elements_helper(iLastIndexOf);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        checkForLock();
        boolean z = false;
        if (get_child_count() < 1) {
            return false;
        }
        IdentityHashMap identityHashMap = new IdentityHashMap();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            IonValue ionValue = (IonValue) it.next();
            if (this == ionValue.getContainer()) {
                identityHashMap.put(ionValue, ionValue);
            }
        }
        int i = get_child_count();
        while (i > 0) {
            i--;
            IonValueLite ionValueLite = get_child(i);
            if (!identityHashMap.containsKey(ionValueLite)) {
                remove((IonValue) ionValueLite);
                patch_elements_helper(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        obj.getClass();
        if (obj instanceof IonValue) {
            return ((IonValue) obj).getContainer() == this;
        }
        throw new ClassCastException();
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        obj.getClass();
        _Private_IonValue _private_ionvalue = (_Private_IonValue) obj;
        if (this != _private_ionvalue.getContainer()) {
            return -1;
        }
        return _private_ionvalue.getElementId();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkSublistParameters(int i, int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("fromIndex is less than zero");
        }
        if (i3 < i2) {
            throw new IllegalArgumentException("toIndex may not be less than fromIndex");
        }
        if (i3 > i) {
            throw new IndexOutOfBoundsException("toIndex exceeds size");
        }
    }

    @Override // java.util.List
    public List<IonValue> subList(int i, int i2) {
        checkSublistParameters(size(), i, i2);
        return new SubListView(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public IonValue[] toArray() {
        if (get_child_count() < 1) {
            return EMPTY_VALUE_ARRAY;
        }
        IonValue[] ionValueArr = new IonValue[get_child_count()];
        System.arraycopy(this._children, 0, ionValueArr, 0, get_child_count());
        return ionValueArr;
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        int i = get_child_count();
        if (tArr.length < i) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
        }
        if (i > 0) {
            System.arraycopy(this._children, 0, tArr, 0, i);
        }
        if (i < tArr.length) {
            tArr[i] = null;
        }
        return tArr;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        IonType type = getType();
        if (isNullValue()) {
            ionWriter.writeNull(type);
            return;
        }
        ionWriter.stepIn(type);
        writeChildren(ionWriter, this, symbolTableProvider);
        ionWriter.stepOut();
    }

    private class SubListView extends AbstractList<IonValue> {
        private final int fromIndex;
        private int size;

        private SubListView(int i, int i2) {
            this.fromIndex = i;
            this.size = i2 - i;
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            checkForParentModification();
            return this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            checkForParentModification();
            return this.size == 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public IonValue get(int i) {
            checkForParentModification();
            rangeCheck(i);
            return IonSequenceLite.this.get(toParentIndex(i));
        }

        @Override // java.util.AbstractList, java.util.List
        public IonValue set(int i, IonValue ionValue) {
            checkForParentModification();
            rangeCheck(i);
            return IonSequenceLite.this.set(toParentIndex(i), ionValue);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            checkForParentModification();
            return super.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean containsAll(Collection<?> collection) {
            checkForParentModification();
            return super.containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            checkForParentModification();
            return super.toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] tArr) {
            checkForParentModification();
            return (T[]) super.toArray(tArr);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(IonValue ionValue) {
            checkForParentModification();
            int parentIndex = toParentIndex(this.size);
            if (parentIndex == IonSequenceLite.this.size()) {
                IonSequenceLite.this.add(ionValue);
            } else {
                IonSequenceLite.this.add(parentIndex, ionValue);
            }
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
            this.size++;
            return true;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, IonValue ionValue) {
            checkForParentModification();
            rangeCheck(i);
            IonSequenceLite.this.add(toParentIndex(i), ionValue);
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
            this.size++;
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection<? extends IonValue> collection) {
            checkForParentModification();
            return super.addAll(i, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends IonValue> collection) {
            checkForParentModification();
            return super.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection<?> collection) {
            checkForParentModification();
            if (this.size < 1) {
                return false;
            }
            IdentityHashMap identityHashMap = new IdentityHashMap();
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                identityHashMap.put(it.next(), null);
            }
            ArrayList arrayList = new ArrayList(this.size - collection.size());
            for (int i = 0; i < this.size; i++) {
                IonValue ionValue = get(i);
                if (!identityHashMap.containsKey(ionValue)) {
                    arrayList.add(ionValue);
                }
            }
            return removeAll(arrayList);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            checkForParentModification();
            int parentIndex = toParentIndex(0);
            for (int i = 0; i < this.size; i++) {
                IonSequenceLite.this.remove(parentIndex);
            }
            this.size = 0;
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
        }

        @Override // java.util.AbstractList, java.util.List
        public IonValue remove(int i) {
            checkForParentModification();
            rangeCheck(i);
            IonValue ionValueRemove = IonSequenceLite.this.remove(toParentIndex(i));
            ((AbstractList) this).modCount = IonSequenceLite.this.structuralModificationCount;
            this.size--;
            return ionValueRemove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object obj) {
            checkForParentModification();
            int iIndexOf = indexOf(obj);
            if (iIndexOf < 0) {
                return false;
            }
            remove(iIndexOf);
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection<?> collection) {
            checkForParentModification();
            Iterator<?> it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (remove(it.next())) {
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            checkForParentModification();
            return super.indexOf(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object obj) {
            checkForParentModification();
            return super.lastIndexOf(obj);
        }

        @Override // java.util.AbstractList
        protected void removeRange(int i, int i2) {
            checkForParentModification();
            super.removeRange(i, i2);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<IonValue> iterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<IonValue> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<IonValue> listIterator(int i) {
            checkForParentModification();
            return new ListIterator<IonValue>(i) { // from class: com.amazon.ion.impl.lite.IonSequenceLite.SubListView.1
                private int lastReturnedIndex;
                private int nextIndex;
                final /* synthetic */ int val$index;

                {
                    this.val$index = i;
                    this.lastReturnedIndex = i;
                    this.nextIndex = i;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public boolean hasNext() {
                    return this.nextIndex < SubListView.this.size();
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public IonValue next() {
                    int i2 = this.nextIndex;
                    this.nextIndex = i2 + 1;
                    this.lastReturnedIndex = i2;
                    return SubListView.this.get(i2);
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return this.nextIndex > 0;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.ListIterator
                public IonValue previous() {
                    int i2 = this.nextIndex - 1;
                    this.nextIndex = i2;
                    this.lastReturnedIndex = i2;
                    return SubListView.this.get(i2);
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return this.nextIndex;
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return this.nextIndex - 1;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                @Override // java.util.ListIterator
                public void set(IonValue ionValue) {
                    SubListView.this.set(this.lastReturnedIndex, ionValue);
                }

                @Override // java.util.ListIterator
                public void add(IonValue ionValue) {
                    SubListView.this.add(this.lastReturnedIndex, ionValue);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public List<IonValue> subList(int i, int i2) {
            IonSequenceLite.checkSublistParameters(size(), i, i2);
            checkForParentModification();
            return IonSequenceLite.this.new SubListView(toParentIndex(i), toParentIndex(i2));
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object obj) {
            checkForParentModification();
            return super.equals(obj);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            checkForParentModification();
            return super.hashCode();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            checkForParentModification();
            return super.toString();
        }

        private void rangeCheck(int i) {
            if (i < 0 || i >= this.size) {
                throw new IndexOutOfBoundsException(String.valueOf(i));
            }
        }

        private int toParentIndex(int i) {
            return i + this.fromIndex;
        }

        private void checkForParentModification() {
            if (((AbstractList) this).modCount != IonSequenceLite.this.structuralModificationCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
