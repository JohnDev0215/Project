package com.amazon.avod.playbackclient.subtitle.internal;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SingleSubtitleCollectionManager implements SubtitleCollectionManager {
    private final StringBuilder mSubtitleBuilder = new StringBuilder();
    private final Map<SubtitleLanguage, SubtitleCollection> mSubtitleDataByLanguage = Maps.newHashMap();
    private final Map<SubtitleLanguage, SubtitleCollection> mNarrativeDataByLanguage = Maps.newHashMap();

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    @Nonnull
    public CompositeSubtitleElement getSubtitles(@Nullable SubtitleLanguage subtitleLanguage, long j) {
        return getCaptions(this.mSubtitleDataByLanguage, subtitleLanguage, j, false);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    @Nonnull
    @Deprecated
    public CompositeSubtitleElement getForcedNarratives(@Nullable String str, long j) {
        SubtitleLanguage next;
        Iterator<SubtitleLanguage> it = this.mNarrativeDataByLanguage.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.getLanguageCode().equals(str)) {
                break;
            }
        }
        SubtitleLanguage subtitleLanguage = next;
        if (subtitleLanguage == null) {
            return CompositeSubtitleElement.NO_CAPTION;
        }
        return getCaptions(this.mNarrativeDataByLanguage, subtitleLanguage, j, true);
    }

    @Nonnull
    private CompositeSubtitleElement getCaptions(@Nonnull Map<SubtitleLanguage, SubtitleCollection> map, @Nullable SubtitleLanguage subtitleLanguage, long j, boolean z) {
        if (!map.containsKey(subtitleLanguage)) {
            return CompositeSubtitleElement.NO_CAPTION;
        }
        SubtitleCollection subtitleCollection = map.get(subtitleLanguage);
        if (subtitleCollection == null) {
            return CompositeSubtitleElement.NO_CAPTION;
        }
        ImmutableList<SubtitleTextElement> immutableListSubtitleSearch = subtitleCollection.subtitleSearch(j);
        if (immutableListSubtitleSearch.isEmpty()) {
            return CompositeSubtitleElement.NO_CAPTION;
        }
        SubtitleTextElement next = immutableListSubtitleSearch.iterator().next();
        return new CompositeSubtitleElement(concatenateSubtitles(immutableListSubtitleSearch), subtitleCollection.getRegionForElement(next), subtitleCollection.getStyleForElement(next), immutableListSubtitleSearch, z);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    public void updateSubtitles(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, @Nonnull Optional<Long> optional2) {
        Preconditions.checkNotNull(subtitleLanguage, "language");
        Preconditions.checkNotNull(subtitleCollection, "subtitleCollection");
        Preconditions.checkNotNull(optional, "startTime");
        Preconditions.checkNotNull(optional2, "endTime");
        if (subtitleCollection.isForced()) {
            this.mNarrativeDataByLanguage.put(subtitleLanguage, subtitleCollection);
        } else {
            this.mSubtitleDataByLanguage.put(subtitleLanguage, subtitleCollection);
        }
    }

    @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager
    public void clear() {
        this.mSubtitleDataByLanguage.clear();
        this.mNarrativeDataByLanguage.clear();
        this.mSubtitleBuilder.setLength(0);
    }

    @Nonnull
    protected String concatenateSubtitles(ImmutableList<SubtitleTextElement> immutableList) {
        if (immutableList.isEmpty()) {
            return "";
        }
        this.mSubtitleBuilder.setLength(0);
        UnmodifiableIterator<SubtitleTextElement> it = immutableList.iterator();
        while (it.hasNext()) {
            this.mSubtitleBuilder.append(it.next().getSubtitleText());
            this.mSubtitleBuilder.append(System.getProperty("line.separator"));
        }
        return this.mSubtitleBuilder.toString().trim();
    }
}
