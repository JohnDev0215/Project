package com.android.inputmethod.latin.utils;

import com.android.inputmethod.latin.SuggestedWords;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public final class SuggestionResults extends TreeSet<SuggestedWords.SuggestedWordInfo> {
    private static final SuggestedWordInfoComparator sSuggestedWordInfoComparator = new SuggestedWordInfoComparator();
    private final int mCapacity;
    public final boolean mFirstSuggestionExceedsConfidenceThreshold;
    public final boolean mIsBeginningOfSentence;
    public final ArrayList<SuggestedWords.SuggestedWordInfo> mRawSuggestions;

    public SuggestionResults(final int capacity, final boolean isBeginningOfSentence, final boolean firstSuggestionExceedsConfidenceThreshold) {
        this(sSuggestedWordInfoComparator, capacity, isBeginningOfSentence, firstSuggestionExceedsConfidenceThreshold);
    }

    private SuggestionResults(final Comparator<SuggestedWords.SuggestedWordInfo> comparator, final int capacity, final boolean isBeginningOfSentence, final boolean firstSuggestionExceedsConfidenceThreshold) {
        super(comparator);
        this.mCapacity = capacity;
        this.mRawSuggestions = null;
        this.mIsBeginningOfSentence = isBeginningOfSentence;
        this.mFirstSuggestionExceedsConfidenceThreshold = firstSuggestionExceedsConfidenceThreshold;
    }

    @Override // java.util.TreeSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(final SuggestedWords.SuggestedWordInfo e) {
        if (size() < this.mCapacity) {
            return super.add(e);
        }
        if (comparator().compare(e, last()) > 0) {
            return false;
        }
        super.add(e);
        pollLast();
        return true;
    }

    @Override // java.util.TreeSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(final Collection<? extends SuggestedWords.SuggestedWordInfo> e) {
        if (e == null) {
            return false;
        }
        return super.addAll(e);
    }

    static final class SuggestedWordInfoComparator implements Comparator<SuggestedWords.SuggestedWordInfo> {
        SuggestedWordInfoComparator() {
        }

        @Override // java.util.Comparator
        public int compare(final SuggestedWords.SuggestedWordInfo o1, final SuggestedWords.SuggestedWordInfo o2) {
            if (o1.mScore > o2.mScore) {
                return -1;
            }
            if (o1.mScore < o2.mScore) {
                return 1;
            }
            if (o1.mCodePointCount < o2.mCodePointCount) {
                return -1;
            }
            if (o1.mCodePointCount > o2.mCodePointCount) {
                return 1;
            }
            return o1.mWord.compareTo(o2.mWord);
        }
    }
}
