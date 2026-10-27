package com.amazon.video.sdk.avod.playbackclient.trickplay;

import androidx.annotation.Nullable;
import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackTrickplayFeature;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatch;
import com.amazon.video.sdk.avod.playbackclient.control.PlaybackProgressEventListener;
import com.amazon.video.sdk.avod.playbackclient.trickplay.TrickplayImageLoader;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class TrickplayController {
    private PlaybackEventDispatch mPlaybackEventDispatch;
    private final PlaybackProgressEventListener mProgressListener = new PlaybackProgressEventListener() { // from class: com.amazon.video.sdk.avod.playbackclient.trickplay.TrickplayController.1
        @Override // com.amazon.video.sdk.avod.playbackclient.control.PlaybackProgressEventListener
        public void onProgressChanged() {
            if (TrickplayController.this.mVideoPlayer == null) {
                return;
            }
            long thumbPositionForScrubbing = TrickplayController.this.mSeekThumbHandler.getThumbPositionForScrubbing();
            if (thumbPositionForScrubbing >= 0 && TrickplayController.this.mTrickplayImageLoader != null) {
                TrickplayController.this.mTrickplayImageLoader.loadImageAsync(thumbPositionForScrubbing);
            }
        }
    };
    private final PlaybackTrickplayFeature.TrickplaySeekThumbHandler mSeekThumbHandler;
    private TrickplayImageLoader mTrickplayImageLoader;
    private final TrickplayImageLoader.ImageLoaderListener mTrickplayImageLoaderListener;
    private TrickplayIndex mTrickplayIndex;
    private final VideoPlayer mVideoPlayer;

    public TrickplayController(@Nullable TrickplayImageLoader.ImageLoaderListener imageLoaderListener, @Nullable VideoPlayer videoPlayer, @Nullable PlaybackEventDispatch playbackEventDispatch, @Nullable PlaybackTrickplayFeature.TrickplaySeekThumbHandler trickplaySeekThumbHandler) {
        this.mTrickplayImageLoaderListener = imageLoaderListener;
        this.mVideoPlayer = videoPlayer;
        this.mPlaybackEventDispatch = playbackEventDispatch;
        this.mSeekThumbHandler = trickplaySeekThumbHandler;
    }

    public void enable() {
        this.mPlaybackEventDispatch.addPlaybackProgressEventListener(this.mProgressListener);
    }

    public void disable() {
        this.mPlaybackEventDispatch.removePlaybackProgressEventListener(this.mProgressListener);
    }

    public void registerForImageUpdates(@Nonnull TrickplayIndex trickplayIndex) {
        this.mTrickplayIndex = (TrickplayIndex) Preconditions.checkNotNull(trickplayIndex);
        this.mTrickplayImageLoader = new TrickplayImageLoader(trickplayIndex, this.mTrickplayImageLoaderListener);
        enable();
        PlaybackTrickplayFeature.TrickplaySeekThumbHandler trickplaySeekThumbHandler = this.mSeekThumbHandler;
        if (trickplaySeekThumbHandler != null) {
            trickplaySeekThumbHandler.initialize(this.mTrickplayIndex);
        }
        this.mProgressListener.onProgressChanged();
    }
}
