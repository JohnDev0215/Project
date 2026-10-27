package com.amazon.avod.playbackclient.subtitle.presenters;

import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface SubtitleRenderer {
    void addLanguage(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, Optional<Long> optional2);

    void clear();

    SubtitleLanguage getCurrentSubtitleLanguage();

    void render();

    void startRenderingSubtitles();

    void stopRenderingSubtitles();

    void updateCurrentSubtitleLanguage(@Nonnull SubtitleLanguage subtitleLanguage);

    void updateSpokenLanguage(@Nullable String str);
}
