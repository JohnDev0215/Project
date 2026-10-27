package com.amazon.avod.playbackclient.presentation;

import com.amazon.avod.events.proxy.HandlerBasedListenerProxyFactory;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.perf.PlaybackMarkers;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playbackclient.PlaybackMetadataFuture;
import com.amazon.avod.playbackclient.perf.PlaybackExtras;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheOperation;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CachedVideoPresentation implements VideoPresentationEventListener {
    private boolean mHasRenderingSettings;
    private final boolean mIsPreinit;
    private boolean mIsReplayed;
    private final Object mMutex;
    private PlaybackDataSource mPlaybackDataSource;
    private final PlaybackMetadataFuture mPlaybackMetadataFuture;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final Stopwatch mPrepareTimer;
    private final VideoPresentation mPresentation;
    private final PresentationCache mPresentationCache;
    private PresentationState mPresentationState;
    private final VideoPresentationEventListener mQueuingEventListener;

    private enum PresentationState {
        PREPARING,
        PREPARED,
        ERRORED
    }

    public static CachedVideoPresentation from(@Nonnull VideoPresentation videoPresentation, @Nullable PresentationCache presentationCache, boolean z) {
        CachedVideoPresentation cachedVideoPresentation = new CachedVideoPresentation(videoPresentation, presentationCache, z);
        videoPresentation.setListener(cachedVideoPresentation);
        return cachedVideoPresentation;
    }

    CachedVideoPresentation(@Nonnull VideoPresentation videoPresentation, @Nullable PresentationCache presentationCache, boolean z) {
        this(videoPresentation, new HandlerBasedListenerProxyFactory(), presentationCache, PlaybackPmetMetricReporter.getInstance(), z);
    }

    CachedVideoPresentation(@Nonnull VideoPresentation videoPresentation, @Nonnull HandlerBasedListenerProxyFactory handlerBasedListenerProxyFactory, @Nullable PresentationCache presentationCache, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter, boolean z) {
        this.mMutex = new Object();
        PlaybackMetadataFuture playbackMetadataFuture = new PlaybackMetadataFuture();
        this.mPlaybackMetadataFuture = playbackMetadataFuture;
        this.mPresentationState = PresentationState.PREPARING;
        this.mPlaybackDataSource = null;
        this.mIsReplayed = false;
        this.mHasRenderingSettings = false;
        VideoPresentation videoPresentation2 = (VideoPresentation) Preconditions.checkNotNull(videoPresentation, "presentation");
        this.mPresentation = videoPresentation2;
        videoPresentation2.setListener(playbackMetadataFuture);
        videoPresentation2.getPlayer().addListener(playbackMetadataFuture);
        this.mPrepareTimer = Stopwatch.createStarted();
        this.mPresentationCache = presentationCache;
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mIsPreinit = z;
        this.mQueuingEventListener = (VideoPresentationEventListener) handlerBasedListenerProxyFactory.createUiProxy(VideoPresentationEventListener.class);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
        this.mQueuingEventListener.onCompletion(videoPresentation);
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
        synchronized (this.mMutex) {
            this.mPresentationState = PresentationState.ERRORED;
            this.mQueuingEventListener.onError(videoPresentation, mediaErrorCode);
            PresentationCache presentationCache = this.mPresentationCache;
            if (presentationCache != null) {
                presentationCache.onPresentationPreparedOrErrored();
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        synchronized (this.mMutex) {
            if (!this.mPrepareTimer.isRunning()) {
                DLog.logf("CachedVideoPresentation prepare timer is no longer running. Not executing onPrepared.");
                return;
            }
            long jElapsed = this.mPrepareTimer.stop().elapsed(TimeUnit.MILLISECONDS);
            DLog.logf("CachedVideoPresentation %s took %s ms to prepare", videoPresentation.getSpecification(), Long.valueOf(jElapsed));
            if (this.mIsPreinit) {
                this.mPlaybackPmetMetricReporter.reportCacheOperationTimerMetric(WhisperCacheMetrics$CacheOperation.PREINIT, jElapsed);
            }
            Profiler.trigger(PlaybackMarkers.PLAYBACK_ONPREPARED, PlaybackExtras.newPlaybackDataSourceExtra(playbackDataSource));
            this.mPlaybackDataSource = playbackDataSource;
            this.mPresentationState = PresentationState.PREPARED;
            tryStartVideo();
            tryInvokeOnPrepared();
            PresentationCache presentationCache = this.mPresentationCache;
            if (presentationCache != null) {
                presentationCache.onPresentationPreparedOrErrored();
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        synchronized (this.mMutex) {
            if (this.mPresentationState == PresentationState.PREPARED) {
                Preconditions.checkState(this.mPlaybackDataSource != null);
                this.mQueuingEventListener.onStarted(this.mPresentation, this.mPlaybackDataSource);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
        synchronized (this.mMutex) {
            if (this.mPresentationState == PresentationState.PREPARED) {
                this.mQueuingEventListener.onTerminated();
            }
        }
    }

    @Nonnull
    public VideoPresentation getPresentation() {
        return this.mPresentation;
    }

    public boolean isErrored() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mPresentationState == PresentationState.ERRORED;
        }
        return z;
    }

    private void tryInvokeOnPrepared() {
        synchronized (this.mMutex) {
            if (this.mPresentationState == PresentationState.PREPARED && this.mIsReplayed) {
                Preconditions.checkState(this.mPlaybackDataSource != null);
                this.mQueuingEventListener.onPrepared(this.mPresentation, this.mPlaybackDataSource);
            }
        }
    }

    private void tryStartVideo() {
        synchronized (this.mMutex) {
            if (this.mPresentationState == PresentationState.PREPARED && this.mHasRenderingSettings) {
                this.mPresentation.getPlayer().start();
            }
        }
    }
}
