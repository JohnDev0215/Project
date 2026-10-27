package com.amazon.avod.content.smoothstream.streamstate;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.AbstractMap;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CachedContentView {
    private final List<EntryTuple> mRanges = Lists.newLinkedList();

    public static final class EntryTuple extends AbstractMap.SimpleEntry<QualityLevel, Range<Integer>> {
        public EntryTuple(@Nullable QualityLevel qualityLevel, @Nonnull Range<Integer> range) {
            super(qualityLevel, range);
        }
    }

    public void initialize(Range<Integer> range, @Nullable QualityLevel qualityLevel) {
        this.mRanges.clear();
        this.mRanges.add(new EntryTuple(qualityLevel, range));
    }

    public void add(int i, @Nullable QualityLevel qualityLevel) {
        EntryTuple entryTuple;
        ListIterator<EntryTuple> listIterator = this.mRanges.listIterator();
        EntryTuple next = null;
        do {
            entryTuple = next;
            if (listIterator.hasNext()) {
                next = listIterator.next();
                if (next.getValue().contains(Integer.valueOf(i))) {
                    return;
                }
            } else {
                insertAfter(i, qualityLevel, next, listIterator);
                return;
            }
        } while (((Integer) next.getValue().lowerEndpoint()).intValue() <= i);
        insertBefore(i, qualityLevel, next, entryTuple, listIterator);
    }

    public void remove(int i) {
        ListIterator<EntryTuple> listIterator = this.mRanges.listIterator();
        while (listIterator.hasNext()) {
            EntryTuple next = listIterator.next();
            if (next.getValue().contains(Integer.valueOf(i))) {
                QualityLevel key = next.getKey();
                if (((Integer) next.getValue().lowerEndpoint()).equals(next.getValue().upperEndpoint())) {
                    listIterator.remove();
                    return;
                }
                if (((Integer) next.getValue().lowerEndpoint()).intValue() == i) {
                    next.setValue(Range.closed(Integer.valueOf(i + 1), next.getValue().upperEndpoint()));
                    return;
                } else {
                    if (((Integer) next.getValue().upperEndpoint()).intValue() == i) {
                        next.setValue(Range.closed(next.getValue().lowerEndpoint(), Integer.valueOf(i - 1)));
                        return;
                    }
                    int iIntValue = ((Integer) next.getValue().upperEndpoint()).intValue();
                    next.setValue(Range.closed(next.getValue().lowerEndpoint(), Integer.valueOf(i - 1)));
                    listIterator.add(new EntryTuple(key, Range.closed(Integer.valueOf(i + 1), Integer.valueOf(iIntValue))));
                    return;
                }
            }
        }
    }

    public boolean contains(int i) {
        return findContainingRange(i) != null;
    }

    public boolean isEmpty() {
        return this.mRanges.isEmpty();
    }

    public int getMax() {
        Preconditions.checkState(!isEmpty(), "Can't getMax() when isEmpty()!");
        EntryTuple next = null;
        ListIterator<EntryTuple> listIterator = this.mRanges.listIterator();
        while (listIterator.hasNext()) {
            next = listIterator.next();
        }
        return ((Integer) next.getValue().upperEndpoint()).intValue();
    }

    public int getMin() {
        Preconditions.checkState(!isEmpty(), "Can't getMin() when isEmpty()!");
        return ((Integer) this.mRanges.get(0).getValue().lowerEndpoint()).intValue();
    }

    @Nullable
    public EntryTuple findContainingRange(int i) {
        for (EntryTuple entryTuple : this.mRanges) {
            if (entryTuple.getValue().contains(Integer.valueOf(i))) {
                return entryTuple;
            }
            if (((Integer) entryTuple.getValue().lowerEndpoint()).intValue() > i) {
                return null;
            }
        }
        return null;
    }

    @Nullable
    public ImmutableList<EntryTuple> restrictToQuality(@Nonnull QualityLevel qualityLevel, int i) {
        Preconditions.checkNotNull(qualityLevel);
        Preconditions.checkArgument(i >= 0);
        ImmutableList.Builder builder = ImmutableList.builder();
        ListIterator<EntryTuple> listIterator = this.mRanges.listIterator();
        while (listIterator.hasNext()) {
            EntryTuple next = listIterator.next();
            if (next.getKey() != null && next.getKey().getBitrate() > qualityLevel.getBitrate()) {
                if (next.getValue().contains(Integer.valueOf(i))) {
                    int iIntValue = ((Integer) next.getValue().lowerEndpoint()).intValue();
                    int iIntValue2 = ((Integer) next.getValue().upperEndpoint()).intValue();
                    QualityLevel key = next.getKey();
                    if (iIntValue == i) {
                        listIterator.remove();
                    } else {
                        next.setValue(Range.closed(Integer.valueOf(iIntValue), Integer.valueOf(i - 1)));
                    }
                    builder.add(new EntryTuple(key, Range.closed(Integer.valueOf(i), Integer.valueOf(iIntValue2))));
                } else if (((Integer) next.getValue().lowerEndpoint()).intValue() > i) {
                    listIterator.remove();
                    builder.add(next);
                }
            }
        }
        return builder.build();
    }

    @Nullable
    public Range<Integer> getContainingRangeIgnoringQuality(int i) {
        ListIterator<EntryTuple> listIterator = this.mRanges.listIterator();
        while (listIterator.hasNext()) {
            EntryTuple next = listIterator.next();
            if (((Integer) next.getValue().lowerEndpoint()).intValue() > i) {
                return null;
            }
            if (next.getValue().contains(Integer.valueOf(i))) {
                int iIntValue = ((Integer) next.getValue().lowerEndpoint()).intValue();
                int iIntValue2 = ((Integer) next.getValue().upperEndpoint()).intValue();
                listIterator.previous();
                while (listIterator.hasPrevious()) {
                    EntryTuple entryTuplePrevious = listIterator.previous();
                    if (!entryTuplePrevious.getValue().contains(Integer.valueOf(iIntValue - 1))) {
                        break;
                    }
                    iIntValue = ((Integer) entryTuplePrevious.getValue().lowerEndpoint()).intValue();
                }
                while (listIterator.hasNext() && listIterator.next() != next) {
                }
                while (listIterator.hasNext()) {
                    EntryTuple next2 = listIterator.next();
                    if (!next2.getValue().contains(Integer.valueOf(iIntValue2 + 1))) {
                        break;
                    }
                    iIntValue2 = ((Integer) next2.getValue().upperEndpoint()).intValue();
                }
                return Range.closed(Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2));
            }
        }
        return null;
    }

    List<EntryTuple> getRanges() {
        return this.mRanges;
    }

    private void insertBefore(int i, @Nullable QualityLevel qualityLevel, @Nonnull EntryTuple entryTuple, @Nullable EntryTuple entryTuple2, @Nonnull ListIterator<EntryTuple> listIterator) {
        boolean z;
        if (((Integer) entryTuple.getValue().lowerEndpoint()).intValue() == i + 1 && isSameQualityLevel(qualityLevel, entryTuple.getKey())) {
            entryTuple.setValue(Range.closed(Integer.valueOf(i), entryTuple.getValue().upperEndpoint()));
        } else if (entryTuple2 != null && ((Integer) entryTuple2.getValue().upperEndpoint()).intValue() == i - 1 && isSameQualityLevel(qualityLevel, entryTuple2.getKey())) {
            entryTuple2.setValue(Range.closed(entryTuple2.getValue().lowerEndpoint(), Integer.valueOf(i)));
        } else {
            listIterator.previous();
            listIterator.add(new EntryTuple(qualityLevel, Range.closed(Integer.valueOf(i), Integer.valueOf(i))));
            z = false;
            if (z || entryTuple2 == null || ((Integer) entryTuple2.getValue().upperEndpoint()).intValue() + 1 < ((Integer) entryTuple.getValue().lowerEndpoint()).intValue() || !isSameQualityLevel(entryTuple.getKey(), entryTuple2.getKey())) {
                return;
            }
            entryTuple2.setValue(Range.closed(entryTuple2.getValue().lowerEndpoint(), entryTuple.getValue().upperEndpoint()));
            listIterator.remove();
            return;
        }
        z = true;
        if (z) {
        }
    }

    private void insertAfter(int i, @Nullable QualityLevel qualityLevel, @Nullable EntryTuple entryTuple, @Nonnull ListIterator<EntryTuple> listIterator) {
        if (entryTuple != null && ((Integer) entryTuple.getValue().upperEndpoint()).intValue() == i - 1 && isSameQualityLevel(qualityLevel, entryTuple.getKey())) {
            entryTuple.setValue(Range.closed(entryTuple.getValue().lowerEndpoint(), Integer.valueOf(i)));
        } else {
            listIterator.add(new EntryTuple(qualityLevel, Range.closed(Integer.valueOf(i), Integer.valueOf(i))));
        }
    }

    private boolean isSameQualityLevel(@Nullable QualityLevel qualityLevel, @Nullable QualityLevel qualityLevel2) {
        if (qualityLevel == qualityLevel2 || (qualityLevel == null && qualityLevel2 == null)) {
            return true;
        }
        return (qualityLevel == null || qualityLevel2 == null || qualityLevel.getBitrate() != qualityLevel2.getBitrate()) ? false : true;
    }
}
