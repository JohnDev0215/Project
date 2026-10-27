package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class Multisets {
    private Multisets() {
    }

    public static <E> Multiset.Entry<E> immutableEntry(E e, int i) {
        return new ImmutableEntry(e, i);
    }

    static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        private final int count;
        private final E element;

        ImmutableEntry(E e, int i) {
            this.element = e;
            this.count = i;
            CollectPreconditions.checkNonnegative(i, "count");
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final E getElement() {
            return this.element;
        }

        @Override // com.google.common.collect.Multisets.AbstractEntry, com.google.common.collect.Multiset.Entry
        public final int getCount() {
            return this.count;
        }
    }

    static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        @Override // com.google.common.collect.Multiset.Entry
        public abstract /* synthetic */ int getCount();

        AbstractEntry() {
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return getCount() == entry.getCount() && Objects.equal(getElement(), entry.getElement());
        }

        public int hashCode() {
            E element = getElement();
            return (element == null ? 0 : element.hashCode()) ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String strValueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return strValueOf;
            }
            StringBuilder sb = new StringBuilder(strValueOf.length() + 14);
            sb.append(strValueOf);
            sb.append(" x ");
            sb.append(count);
            return sb.toString();
        }
    }

    static boolean equalsImpl(Multiset<?> multiset, @CheckForNull Object obj) {
        if (obj == multiset) {
            return true;
        }
        if (obj instanceof Multiset) {
            Multiset multiset2 = (Multiset) obj;
            if (multiset.size() == multiset2.size() && multiset.entrySet().size() == multiset2.entrySet().size()) {
                for (Multiset.Entry entry : multiset2.entrySet()) {
                    if (multiset.count(entry.getElement()) != entry.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static <E> boolean addAllImpl(Multiset<E> multiset, Collection<? extends E> collection) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            return addAllImpl((Multiset) multiset, cast(collection));
        }
        if (collection.isEmpty()) {
            return false;
        }
        return Iterators.addAll(multiset, collection.iterator());
    }

    private static <E> boolean addAllImpl(Multiset<E> multiset, Multiset<? extends E> multiset2) {
        if (multiset2 instanceof AbstractMapBasedMultiset) {
            return addAllImpl((Multiset) multiset, (AbstractMapBasedMultiset) multiset2);
        }
        if (multiset2.isEmpty()) {
            return false;
        }
        for (Multiset.Entry<? extends E> entry : multiset2.entrySet()) {
            multiset.add(entry.getElement(), entry.getCount());
        }
        return true;
    }

    private static <E> boolean addAllImpl(Multiset<E> multiset, AbstractMapBasedMultiset<? extends E> abstractMapBasedMultiset) {
        if (abstractMapBasedMultiset.isEmpty()) {
            return false;
        }
        abstractMapBasedMultiset.addTo(multiset);
        return true;
    }

    static boolean removeAllImpl(Multiset<?> multiset, Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().removeAll(collection);
    }

    static boolean retainAllImpl(Multiset<?> multiset, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().retainAll(collection);
    }

    static <E> int setCountImpl(Multiset<E> multiset, E e, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        int iCount = multiset.count(e);
        int i2 = i - iCount;
        if (i2 > 0) {
            multiset.add(e, i2);
        } else if (i2 < 0) {
            multiset.remove(e, -i2);
        }
        return iCount;
    }

    static <E> boolean setCountImpl(Multiset<E> multiset, E e, int i, int i2) {
        CollectPreconditions.checkNonnegative(i, "oldCount");
        CollectPreconditions.checkNonnegative(i2, "newCount");
        if (multiset.count(e) != i) {
            return false;
        }
        multiset.setCount(e, i2);
        return true;
    }

    static <E> Iterator<E> elementIterator(Iterator<Multiset.Entry<E>> it) {
        return new TransformedIterator<Multiset.Entry<E>, E>(it) { // from class: com.google.common.collect.Multisets.5
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.TransformedIterator
            public E transform(Multiset.Entry<E> entry) {
                return entry.getElement();
            }
        };
    }

    static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        abstract Multiset<E> multiset();

        ElementSet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return multiset().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return multiset().containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return multiset().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            return multiset().remove(obj, Integer.MAX_VALUE) > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return multiset().entrySet().size();
        }
    }

    static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        abstract Multiset<E> multiset();

        EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return entry.getCount() > 0 && multiset().count(entry.getElement()) == entry.getCount();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                Object element = entry.getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return multiset().setCount(element, count, 0);
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            multiset().clear();
        }
    }

    static <E> Iterator<E> iteratorImpl(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
    }

    static final class MultisetIteratorImpl<E> implements Iterator<E> {
        private boolean canRemove;

        @CheckForNull
        private Multiset.Entry<E> currentEntry;
        private final Iterator<Multiset.Entry<E>> entryIterator;
        private int laterCount;
        private final Multiset<E> multiset;
        private int totalCount;

        MultisetIteratorImpl(Multiset<E> multiset, Iterator<Multiset.Entry<E>> it) {
            this.multiset = multiset;
            this.entryIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.laterCount == 0) {
                Multiset.Entry<E> next = this.entryIterator.next();
                this.currentEntry = next;
                int count = next.getCount();
                this.laterCount = count;
                this.totalCount = count;
            }
            this.laterCount--;
            this.canRemove = true;
            Multiset.Entry<E> entry = this.currentEntry;
            java.util.Objects.requireNonNull(entry);
            return entry.getElement();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            if (this.totalCount == 1) {
                this.entryIterator.remove();
            } else {
                Multiset<E> multiset = this.multiset;
                Multiset.Entry<E> entry = this.currentEntry;
                java.util.Objects.requireNonNull(entry);
                multiset.remove(entry.getElement());
            }
            this.totalCount--;
            this.canRemove = false;
        }
    }

    static <T> Multiset<T> cast(Iterable<T> iterable) {
        return (Multiset) iterable;
    }
}
