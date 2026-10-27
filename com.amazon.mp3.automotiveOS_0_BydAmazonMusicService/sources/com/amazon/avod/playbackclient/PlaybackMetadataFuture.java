package com.amazon.avod.playbackclient;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.playback.PlaybackContentEventListener;
import com.amazon.avod.playback.PlaybackException;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.SettableFuture;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackMetadataFuture implements PlaybackContentEventListener, VideoPresentationEventListener {
    private final SettableFuture<PlaybackMetadata> mInnerFuture;

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
    }

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentDownloaded() {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
    }

    @Immutable
    public static class PlaybackMetadata {
        private final ImmutableList<VideoResolution> mAvailableVideoResolutions;
        private final double mVideoFramerateFps;

        public PlaybackMetadata(@Nonnull VideoResolution[] videoResolutionArr, double d) {
            Preconditions.checkNotNull(videoResolutionArr, "availableVideoResolutions");
            this.mAvailableVideoResolutions = ImmutableList.copyOf(videoResolutionArr);
            this.mVideoFramerateFps = d;
        }

        public double getVideoFramerateFps() {
            return this.mVideoFramerateFps;
        }
    }

    public PlaybackMetadataFuture() {
        this(SettableFuture.create());
    }

    PlaybackMetadataFuture(@Nonnull SettableFuture<PlaybackMetadata> settableFuture) {
        this.mInnerFuture = (SettableFuture) Preconditions.checkNotNull(settableFuture, "innerFuture");
    }

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentMetadataAvailable(VideoResolution[] videoResolutionArr, double d) {
        this.mInnerFuture.set(new PlaybackMetadata(videoResolutionArr, d));
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
        this.mInnerFuture.setException(new PlaybackException(mediaErrorCode.toReportableString()));
    }
}
