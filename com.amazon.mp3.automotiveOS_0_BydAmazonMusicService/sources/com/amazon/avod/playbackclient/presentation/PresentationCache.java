package com.amazon.avod.playbackclient.presentation;

import com.amazon.avod.media.playback.VideoOptions;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoSpecification;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PresentationCache {
    void destroyPresentationAsync(@Nonnull VideoSpecification videoSpecification, boolean z);

    @Nonnull
    CachedVideoPresentation getPresentation(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, @Nullable File file, @Nullable VideoPresentationEventListener videoPresentationEventListener) throws PrepareFailedException;

    default void onPresentationPreparedOrErrored() {
    }

    void preparePresentationAsync(@Nonnull VideoSpecification videoSpecification, @Nonnull VideoOptions videoOptions, @Nullable File file, @Nullable VideoPresentationEventListener videoPresentationEventListener);
}
