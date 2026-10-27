package com.amazon.avod.playbackresourcev2;

import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackResourcesV2Wrapper {
    private final Optional<Prsv2Error> mError;
    private Optional<PlaybackResourcesV2> mPlaybackResources;

    PlaybackResourcesV2Wrapper(@Nonnull PlaybackResourcesV2 playbackResourcesV2) {
        Preconditions.checkNotNull(playbackResourcesV2, "playbackResources");
        this.mPlaybackResources = Optional.of(playbackResourcesV2);
        this.mError = Optional.absent();
    }

    PlaybackResourcesV2Wrapper(@Nonnull Prsv2Error prsv2Error) {
        Preconditions.checkNotNull(prsv2Error, "error");
        this.mPlaybackResources = Optional.absent();
        this.mError = Optional.of(prsv2Error);
    }

    @Nonnull
    public Optional<PlaybackResourcesV2> getPlaybackResources() {
        return this.mPlaybackResources;
    }

    @Nonnull
    public Optional<Prsv2Error> getError() {
        return this.mError;
    }

    public PlaybackResourcesV2Wrapper update(@Nonnull PlaybackResourcesWrapper playbackResourcesWrapper) {
        Preconditions.checkNotNull(playbackResourcesWrapper, "playbackResourcesWrapperV1");
        if (this.mPlaybackResources.isPresent() && playbackResourcesWrapper.getPlaybackResources().isPresent()) {
            this.mPlaybackResources = Optional.of(this.mPlaybackResources.get().update(playbackResourcesWrapper.getPlaybackResources().get()));
        }
        return this;
    }
}
