package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import defpackage.Base64;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, SortedIterable<E> {
    final transient Comparator<? super E> comparator;

    @CheckForNull
    transient ImmutableSortedSet<E> descendingSet;

    abstract ImmutableSortedSet<E> createDescendingSet();

    @Override // java.util.NavigableSet
    public abstract UnmodifiableIterator<E> descendingIterator();

    abstract ImmutableSortedSet<E> headSetImpl(E e, boolean z);

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public abstract UnmodifiableIterator<E> iterator();

    abstract ImmutableSortedSet<E> subSetImpl(E e, boolean z, E e2, boolean z2);

    abstract ImmutableSortedSet<E> tailSetImpl(E e, boolean z);

    static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        return new RegularImmutableSortedSet<>(ImmutableList.of(), comparator);
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;TE;[TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable, Comparable comparable2, Comparable comparable3, Comparable comparable4, Comparable comparable5, Comparable comparable6, Comparable... comparableArr) {
        int length = comparableArr.length + 6;
        Comparable[] comparableArr2 = new Comparable[length];
        comparableArr2[0] = comparable;
        comparableArr2[1] = comparable2;
        comparableArr2[2] = comparable3;
        comparableArr2[3] = comparable4;
        comparableArr2[4] = comparable5;
        comparableArr2[5] = comparable6;
        System.arraycopy(comparableArr, 0, comparableArr2, 6, comparableArr.length);
        return construct(Ordering.natural(), length, comparableArr2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator, int i, E... eArr) {
        if (i == 0) {
            return emptySet(comparator);
        }
        ObjectArrays.checkElementsNotNull(eArr, i);
        Arrays.sort(eArr, 0, i, comparator);
        int i2 = 1;
        for (int i3 = 1; i3 < i; i3++) {
            Base64 base64 = (Object) eArr[i3];
            if (comparator.compare(base64, (Object) eArr[i2 - 1]) != 0) {
                eArr[i2] = base64;
                i2++;
            }
        }
        Arrays.fill(eArr, i2, i, (Object) null);
        if (i2 < eArr.length / 2) {
            eArr = (E[]) Arrays.copyOf(eArr, i2);
        }
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(eArr, i2), comparator);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;

        public Builder(Comparator<? super E> comparator) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E e) {
            super.add((Object) e);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSortedSet<E> build() {
            ImmutableSortedSet<E> immutableSortedSetConstruct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
            this.size = immutableSortedSetConstruct.size();
            this.forceCopy = true;
            return immutableSortedSetConstruct;
        }
    }

    int unsafeCompare(Object obj, @CheckForNull Object obj2) {
        return unsafeCompare(this.comparator, obj, obj2);
    }

    static int unsafeCompare(Comparator<?> comparator, Object obj, @CheckForNull Object obj2) {
        return comparator.compare(obj, obj2);
    }

    ImmutableSortedSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> headSet(E e) {
        return headSet((Object) e, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> headSet(E e, boolean z) {
        return headSetImpl(Preconditions.checkNotNull(e), z);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> subSet(E e, E e2) {
        return subSet((Object) e, true, (Object) e2, false);
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(e2);
        Preconditions.checkArgument(this.comparator.compare(e, e2) <= 0);
        return subSetImpl(e, z, e2, z2);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> tailSet(E e) {
        return tailSet((Object) e, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> tailSet(E e, boolean z) {
        return tailSetImpl(Preconditions.checkNotNull(e), z);
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public E lower(E e) {
        return (E) Iterators.getNext(headSet((Object) e, false).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public E floor(E e) {
        return (E) Iterators.getNext(headSet((Object) e, true).descendingIterator(), null);
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public E ceiling(E e) {
        return (E) Iterables.getFirst(tailSet((Object) e, true), null);
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    public E higher(E e) {
        return (E) Iterables.getFirst(tailSet((Object) e, false), null);
    }

    @Override // java.util.SortedSet
    public E first() {
        return iterator().next();
    }

    @Override // java.util.SortedSet
    public E last() {
        return descendingIterator().next();
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    @Deprecated
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    @Deprecated
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.descendingSet;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> immutableSortedSetCreateDescendingSet = createDescendingSet();
        this.descendingSet = immutableSortedSetCreateDescendingSet;
        immutableSortedSetCreateDescendingSet.descendingSet = this;
        return immutableSortedSetCreateDescendingSet;
    }
}
