package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackresource.PRSException;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesWrapper {
    private final Optional<PRSException> mError;
    private final Optional<PlaybackResources> mPlaybackResources;

    PlaybackResourcesWrapper(@Nonnull PRSException pRSException) {
        this.mPlaybackResources = Optional.absent();
        this.mError = Optional.of(pRSException);
    }

    PlaybackResourcesWrapper(@Nonnull PlaybackResources playbackResources) {
        this.mPlaybackResources = Optional.of(playbackResources);
        this.mError = Optional.absent();
    }

    @Nonnull
    public Optional<PlaybackResources> getPlaybackResources() {
        return this.mPlaybackResources;
    }

    @Nonnull
    public Optional<PRSException> getError() {
        return this.mError;
    }
}
