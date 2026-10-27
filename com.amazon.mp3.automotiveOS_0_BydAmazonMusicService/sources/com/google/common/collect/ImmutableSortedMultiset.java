package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.math.IntMath;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ImmutableSortedMultiset<E> extends ImmutableSortedMultisetFauxverideShim<E> implements SortedMultiset<E> {

    @CheckForNull
    transient ImmutableSortedMultiset<E> descendingMultiset;

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public abstract ImmutableSortedSet<E> elementSet();

    public abstract ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType);

    public abstract ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType);

    public static <E> ImmutableSortedMultiset<E> of() {
        return (ImmutableSortedMultiset<E>) RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
    }

    static <E> ImmutableSortedMultiset<E> emptyMultiset(Comparator<? super E> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return (ImmutableSortedMultiset<E>) RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
        }
        return new RegularImmutableSortedMultiset(comparator);
    }

    ImmutableSortedMultiset() {
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public final Comparator<? super E> comparator() {
        return elementSet().comparator();
    }

    @Override // com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> descendingMultiset() {
        ImmutableSortedMultiset<E> descendingImmutableSortedMultiset = this.descendingMultiset;
        if (descendingImmutableSortedMultiset == null) {
            if (isEmpty()) {
                descendingImmutableSortedMultiset = emptyMultiset(Ordering.from(comparator()).reverse());
            } else {
                descendingImmutableSortedMultiset = new DescendingImmutableSortedMultiset<>(this);
            }
            this.descendingMultiset = descendingImmutableSortedMultiset;
        }
        return descendingImmutableSortedMultiset;
    }

    @Override // com.google.common.collect.SortedMultiset
    @CheckForNull
    @Deprecated
    public final Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.SortedMultiset
    @CheckForNull
    @Deprecated
    public final Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        Preconditions.checkArgument(comparator().compare(e, e2) <= 0, "Expected lowerBound <= upperBound but %s > %s", e, e2);
        return tailMultiset((Object) e, boundType).headMultiset((Object) e2, boundType2);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static class Builder<E> extends ImmutableMultiset.Builder<E> {
        private final Comparator<? super E> comparator;
        private int[] counts;
        E[] elements;
        private boolean forceCopyElements;
        private int length;

        public Builder(Comparator<? super E> comparator) {
            super(true);
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
            this.elements = (E[]) new Object[4];
            this.counts = new int[4];
        }

        private void maintenance() {
            int i = this.length;
            E[] eArr = this.elements;
            if (i == eArr.length) {
                dedupAndCoalesce(true);
            } else if (this.forceCopyElements) {
                this.elements = (E[]) Arrays.copyOf(eArr, eArr.length);
            }
            this.forceCopyElements = false;
        }

        private void dedupAndCoalesce(boolean z) {
            int i = this.length;
            if (i == 0) {
                return;
            }
            Object[] objArr = (E[]) Arrays.copyOf(this.elements, i);
            Arrays.sort(objArr, this.comparator);
            int i2 = 1;
            for (int i3 = 1; i3 < objArr.length; i3++) {
                if (this.comparator.compare((Object) objArr[i2 - 1], (Object) objArr[i3]) < 0) {
                    objArr[i2] = objArr[i3];
                    i2++;
                }
            }
            Arrays.fill(objArr, i2, this.length, (Object) null);
            if (z) {
                int i4 = i2 * 4;
                int i5 = this.length;
                if (i4 > i5 * 3) {
                    objArr = (E[]) Arrays.copyOf(objArr, IntMath.saturatedAdd(i5, (i5 / 2) + 1));
                }
            }
            int[] iArr = new int[objArr.length];
            for (int i6 = 0; i6 < this.length; i6++) {
                int iBinarySearch = Arrays.binarySearch(objArr, 0, i2, this.elements[i6], this.comparator);
                int i7 = this.counts[i6];
                if (i7 >= 0) {
                    iArr[iBinarySearch] = iArr[iBinarySearch] + i7;
                } else {
                    iArr[iBinarySearch] = ~i7;
                }
            }
            this.elements = (E[]) objArr;
            this.counts = iArr;
            this.length = i2;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E e) {
            return addCopies((Object) e, 1);
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E... eArr) {
            for (E e : eArr) {
                add((Object) e);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder
        public Builder<E> addCopies(E e, int i) {
            Preconditions.checkNotNull(e);
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return this;
            }
            maintenance();
            E[] eArr = this.elements;
            int i2 = this.length;
            eArr[i2] = e;
            this.counts[i2] = i;
            this.length = i2 + 1;
            return this;
        }

        private void dedupAndCoalesceAndDeleteEmpty() {
            dedupAndCoalesce(false);
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = this.length;
                if (i < i3) {
                    int[] iArr = this.counts;
                    int i4 = iArr[i];
                    if (i4 > 0) {
                        E[] eArr = this.elements;
                        eArr[i2] = eArr[i];
                        iArr[i2] = i4;
                        i2++;
                    }
                    i++;
                } else {
                    Arrays.fill(this.elements, i2, i3, (Object) null);
                    Arrays.fill(this.counts, i2, this.length, 0);
                    this.length = i2;
                    return;
                }
            }
        }

        @Override // com.google.common.collect.ImmutableMultiset.Builder, com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSortedMultiset<E> build() {
            dedupAndCoalesceAndDeleteEmpty();
            int i = this.length;
            if (i == 0) {
                return ImmutableSortedMultiset.emptyMultiset(this.comparator);
            }
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) ImmutableSortedSet.construct(this.comparator, i, this.elements);
            long[] jArr = new long[this.length + 1];
            int i2 = 0;
            while (i2 < this.length) {
                int i3 = i2 + 1;
                jArr[i3] = jArr[i2] + ((long) this.counts[i2]);
                i2 = i3;
            }
            this.forceCopyElements = true;
            return new RegularImmutableSortedMultiset(regularImmutableSortedSet, jArr, 0, this.length);
        }
    }
}
