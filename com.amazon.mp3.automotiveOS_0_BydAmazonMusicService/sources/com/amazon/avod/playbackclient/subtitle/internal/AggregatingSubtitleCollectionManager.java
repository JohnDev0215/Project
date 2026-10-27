package com.amazon.avod.playbackclient.subtitle.internal;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AggregatingSubtitleCollectionManager extends SingleSubtitleCollectionManager {
    private static final SubtitleFragment UNSET_FRAGMENT = null;
    private static final String UNSET_LANGUAGE = null;
    private SubtitleFragment mLastRequestedFragment;
    private String mLastRequestedLanguage;
    private final SubtitleConfig mSubtitleConfig;
    private final Map<String, LinkedList<SubtitleFragment>> mSubtitleFragmentDataByLanguage;

    public AggregatingSubtitleCollectionManager() {
        this(new HashMap(), SubtitleConfig.getInstance());
    }

    public AggregatingSubtitleCollectionManager(Map<String, LinkedList<SubtitleFragment>> map, SubtitleConfig subtitleConfig) {
        this.mLastRequestedFragment = UNSET_FRAGMENT;
        this.mLastRequestedLanguage = UNSET_LANGUAGE;
        this.mSubtitleFragmentDataByLanguage = map;
        this.mSubtitleConfig = subtitleConfig;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SingleSubtitleCollectionManager, com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    @Nonnull
    public CompositeSubtitleElement getSubtitles(@Nullable SubtitleLanguage subtitleLanguage, long j) {
        SubtitleFragment subtitleFragment;
        if (subtitleLanguage == null) {
            return CompositeSubtitleElement.NO_CAPTION;
        }
        String languageCode = subtitleLanguage.getLanguageCode();
        if (!this.mSubtitleFragmentDataByLanguage.containsKey(languageCode)) {
            this.mLastRequestedFragment = UNSET_FRAGMENT;
            return CompositeSubtitleElement.NO_CAPTION;
        }
        String str = this.mLastRequestedLanguage;
        if (str != UNSET_LANGUAGE && str.equals(languageCode) && (subtitleFragment = this.mLastRequestedFragment) != UNSET_FRAGMENT && subtitleFragment.isFragmentAt(Long.valueOf(j))) {
            return getCompSubtitleElementFromCollection(this.mLastRequestedFragment.getSubtitleCollection(), j);
        }
        this.mLastRequestedLanguage = languageCode;
        Optional<SubtitleFragment> subtitleFragmentCollection = getSubtitleFragmentCollection(languageCode, j);
        if (!subtitleFragmentCollection.isPresent()) {
            this.mLastRequestedFragment = UNSET_FRAGMENT;
            return CompositeSubtitleElement.NO_CAPTION;
        }
        SubtitleFragment subtitleFragment2 = subtitleFragmentCollection.get();
        this.mLastRequestedFragment = subtitleFragment2;
        return getCompSubtitleElementFromCollection(subtitleFragment2.getSubtitleCollection(), j);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SingleSubtitleCollectionManager, com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    @Nonnull
    public CompositeSubtitleElement getForcedNarratives(@Nullable String str, long j) {
        return CompositeSubtitleElement.NO_CAPTION;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SingleSubtitleCollectionManager, com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    public void updateSubtitles(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, @Nonnull Optional<Long> optional2) {
        Preconditions.checkNotNull(subtitleLanguage, "language");
        Preconditions.checkNotNull(subtitleCollection, "subtitleCollection");
        Preconditions.checkNotNull(optional, "startTime");
        Preconditions.checkNotNull(optional2, "endTime");
        if (optional.isPresent() && optional2.isPresent()) {
            insert(subtitleLanguage.getLanguageCode(), new SubtitleFragment(subtitleCollection, optional.get().longValue(), optional2.get().longValue()));
        }
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SingleSubtitleCollectionManager, com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    public void clear() {
        super.clear();
        this.mSubtitleFragmentDataByLanguage.clear();
        this.mLastRequestedFragment = UNSET_FRAGMENT;
        this.mLastRequestedLanguage = UNSET_LANGUAGE;
    }

    @Nonnull
    private Optional<SubtitleFragment> getSubtitleFragmentCollection(@Nonnull String str, long j) {
        if (!this.mSubtitleFragmentDataByLanguage.containsKey(str)) {
            return Optional.absent();
        }
        LinkedList<SubtitleFragment> linkedList = this.mSubtitleFragmentDataByLanguage.get(str);
        if (linkedList == null || linkedList.isEmpty()) {
            return Optional.absent();
        }
        int iLinearSearch = linearSearch(linkedList, j);
        if (iLinearSearch < 0) {
            return Optional.absent();
        }
        for (int i = 0; i < iLinearSearch; i++) {
            linkedList.poll();
        }
        return Optional.fromNullable(linkedList.peek());
    }

    private void insert(@Nonnull String str, @Nonnull SubtitleFragment subtitleFragment) {
        Preconditions.checkNotNull(str, "languageCode");
        Preconditions.checkNotNull(subtitleFragment, "incomingSubtitleFragmentCollection");
        if (!this.mSubtitleFragmentDataByLanguage.containsKey(str)) {
            LinkedList<SubtitleFragment> linkedListNewLinkedList = Lists.newLinkedList();
            linkedListNewLinkedList.add(subtitleFragment);
            this.mSubtitleFragmentDataByLanguage.put(str, linkedListNewLinkedList);
            return;
        }
        LinkedList<SubtitleFragment> linkedList = this.mSubtitleFragmentDataByLanguage.get(str);
        if (linkedList == null) {
            return;
        }
        int size = linkedList.size();
        if (size == 0) {
            linkedList.add(subtitleFragment);
            return;
        }
        if (subtitleFragment.getStartTimeInclusive() <= linkedList.get(size - 1).getStartTimeInclusive()) {
            linkedList.clear();
            linkedList.add(subtitleFragment);
        } else {
            linkedList.add(subtitleFragment);
        }
    }

    private int linearSearch(LinkedList<SubtitleFragment> linkedList, long j) {
        Iterator<SubtitleFragment> it = linkedList.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().isFragmentAt(Long.valueOf(j))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Nonnull
    private CompositeSubtitleElement getCompSubtitleElementFromCollection(SubtitleCollection subtitleCollection, long j) {
        ImmutableList<SubtitleTextElement> immutableListSubtitleSearch = subtitleCollection.subtitleSearch(j);
        if (immutableListSubtitleSearch.isEmpty()) {
            return CompositeSubtitleElement.NO_CAPTION;
        }
        SubtitleTextElement next = immutableListSubtitleSearch.iterator().next();
        String strConcatenateSubtitles = concatenateSubtitles(immutableListSubtitleSearch);
        if (!this.mSubtitleConfig.shouldParseRegionStyle()) {
            return new CompositeSubtitleElement(strConcatenateSubtitles, null, null);
        }
        return new CompositeSubtitleElement(strConcatenateSubtitles, subtitleCollection.getRegionForElement(next), subtitleCollection.getStyleForElement(next), immutableListSubtitleSearch);
    }
}
