package com.amazon.avod.playbackclient.subtitle.internal;

import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface SubtitleCollectionManager {
    void clear();

    @Nonnull
    CompositeSubtitleElement getForcedNarratives(@Nullable String str, long j);

    @Nonnull
    CompositeSubtitleElement getSubtitles(@Nullable SubtitleLanguage subtitleLanguage, long j);

    void updateSubtitles(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, @Nonnull Optional<Long> optional2);
}
