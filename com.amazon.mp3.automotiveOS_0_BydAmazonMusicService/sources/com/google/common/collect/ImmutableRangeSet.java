package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collections;
import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class ImmutableRangeSet<C extends Comparable> extends AbstractRangeSet<C> implements Serializable {
    private final transient ImmutableList<Range<C>> ranges;
    private static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet<>(ImmutableList.of());
    private static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet<>(ImmutableList.of(Range.all()));

    @Override // com.google.common.collect.AbstractRangeSet
    public /* bridge */ /* synthetic */ boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    static <C extends Comparable> ImmutableRangeSet<C> all() {
        return ALL;
    }

    ImmutableRangeSet(ImmutableList<Range<C>> immutableList) {
        this.ranges = immutableList;
    }

    @Override // com.google.common.collect.RangeSet
    public ImmutableSet<Range<C>> asRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableSet.of();
        }
        return new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering());
    }

    public static <C extends Comparable<?>> Builder<C> builder() {
        return new Builder<>();
    }

    public static class Builder<C extends Comparable<?>> {
        private final List<Range<C>> ranges = Lists.newArrayList();

        public Builder<C> add(Range<C> range) {
            Preconditions.checkArgument(!range.isEmpty(), "range must not be empty, but was %s", range);
            this.ranges.add(range);
            return this;
        }

        public ImmutableRangeSet<C> build() {
            ImmutableList.Builder builder = new ImmutableList.Builder(this.ranges.size());
            Collections.sort(this.ranges, Range.rangeLexOrdering());
            PeekingIterator peekingIterator = Iterators.peekingIterator(this.ranges.iterator());
            while (peekingIterator.hasNext()) {
                Range rangeSpan = (Range) peekingIterator.next();
                while (peekingIterator.hasNext()) {
                    Range<C> range = (Range) peekingIterator.peek();
                    if (rangeSpan.isConnected(range)) {
                        Preconditions.checkArgument(rangeSpan.intersection(range).isEmpty(), "Overlapping ranges not permitted but found %s overlapping %s", rangeSpan, range);
                        rangeSpan = rangeSpan.span((Range) peekingIterator.next());
                    }
                }
                builder.add(rangeSpan);
            }
            ImmutableList immutableListBuild = builder.build();
            if (immutableListBuild.isEmpty()) {
                return ImmutableRangeSet.of();
            }
            if (immutableListBuild.size() == 1 && ((Range) Iterables.getOnlyElement(immutableListBuild)).equals(Range.all())) {
                return ImmutableRangeSet.all();
            }
            return new ImmutableRangeSet<>(immutableListBuild);
        }
    }
}
