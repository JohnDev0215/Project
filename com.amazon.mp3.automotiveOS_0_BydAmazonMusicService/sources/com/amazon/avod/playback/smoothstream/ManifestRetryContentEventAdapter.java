package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentEventBufferFullnessChanged;
import com.amazon.avod.content.event.RetriableManifestDownloadErrorEvent;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestRetryContentEventAdapter {
    private long mBufferFullnessInNanos;
    private final SmoothStreamingPlaybackConfig mConfig;
    private final PlaybackEventTransport mPlaybackEventTransport;

    ManifestRetryContentEventAdapter(@Nonnull PlaybackEventTransport playbackEventTransport) {
        this(playbackEventTransport, SmoothStreamingPlaybackConfig.INSTANCE);
    }

    ManifestRetryContentEventAdapter(@Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mPlaybackEventTransport = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "config");
        this.mBufferFullnessInNanos = -1L;
    }

    @Subscribe
    public void handleContentEventBufferFullnessChanged(@Nonnull ContentEventBufferFullnessChanged contentEventBufferFullnessChanged) {
        this.mBufferFullnessInNanos = contentEventBufferFullnessChanged.getBufferFullnessInNanos();
    }

    @Subscribe
    public void handleRetriableManifestDownloadEventError(@Nonnull RetriableManifestDownloadErrorEvent retriableManifestDownloadErrorEvent) {
        long j = this.mBufferFullnessInNanos;
        if (j == -1) {
            DLog.warnf("Unable to handle RetriableManifestDownloadError; bufferFullness is not yet updated");
            return;
        }
        long elapsedNanos = retriableManifestDownloadErrorEvent.getElapsedNanos();
        long lastSuccessfulManifestDownloadTimeNanos = retriableManifestDownloadErrorEvent.getLastSuccessfulManifestDownloadTimeNanos() + this.mConfig.getManifestDownloadRetryMaxBackoffMillis().getTotalNanoSeconds();
        DLog.logf("timeSpentInRetryNanos %d, timePredictedForNewTryNanos %d, timeUntilBufferingNanos %d", Long.valueOf(elapsedNanos), Long.valueOf(lastSuccessfulManifestDownloadTimeNanos), Long.valueOf(j));
        if ((elapsedNanos + lastSuccessfulManifestDownloadTimeNanos) - j >= 0) {
            DLog.warnf("Restart player on RetriableManifestDownloadError");
            this.mBufferFullnessInNanos = -1L;
            postPlayerRestartEvent(retriableManifestDownloadErrorEvent);
            return;
        }
        DLog.warnf("Recorded RetriableManifestDownloadError; within front buffer fullness threshold; continue");
    }

    void postPlayerRestartEvent(@Nonnull RetriableManifestDownloadErrorEvent retriableManifestDownloadErrorEvent) {
        this.mPlaybackEventTransport.postEvent(PlaybackRestartEvent.newManifestDownloadFailedRestartPlayerEvent(retriableManifestDownloadErrorEvent.getException().getMessage()));
    }
}
