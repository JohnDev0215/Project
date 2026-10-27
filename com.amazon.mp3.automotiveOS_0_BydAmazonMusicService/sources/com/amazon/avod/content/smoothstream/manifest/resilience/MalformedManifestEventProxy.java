package com.amazon.avod.content.smoothstream.manifest.resilience;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.event.RetriableManifestDownloadErrorEvent;
import com.amazon.avod.content.event.StaleManifestErrorEvent;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MalformedManifestEventProxy {
    private final ContentManagementEventBus mContentEventDispatcher;
    private long mLastSuccessfulManifestDownloadTimeNanos;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;

    public MalformedManifestEventProxy(@Nonnull ContentManagementEventBus contentManagementEventBus, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mContentEventDispatcher = (ContentManagementEventBus) Preconditions.checkNotNull(contentManagementEventBus, "contentEventDispatcher");
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
    }

    @Subscribe
    void handleRetriableContentEventError(@Nonnull RetriableContentEventError retriableContentEventError) {
        if (retriableContentEventError.getException().getErrorCode().equals(ContentException.ContentError.MALFORMED_MANIFEST) && this.mSmoothStreamingPlaybackConfig.isPlayerRestartForManifestDownloadFailureAllowed()) {
            DLog.warnf("Proxying MALFORMED_MANIFESTas RetriableManifestDownloadErrorEvent, retry %d", Integer.valueOf(retriableContentEventError.getRetryCount()));
            DLog.warnf("posting retriable manifest download failure and it may cause session restart");
            this.mContentEventDispatcher.postEvent(new RetriableManifestDownloadErrorEvent(retriableContentEventError.getContent(), retriableContentEventError.getSessionType(), retriableContentEventError.getException(), retriableContentEventError.getRetryCount(), retriableContentEventError.getElapsedNanos(), this.mLastSuccessfulManifestDownloadTimeNanos));
        }
    }

    @Subscribe
    void handleStaleManifestErrorEvent(@Nonnull StaleManifestErrorEvent staleManifestErrorEvent) {
        if (this.mSmoothStreamingPlaybackConfig.isPlayerRestartForManifestDownloadFailureAllowed()) {
            DLog.warnf("Proxying LIVE_STALE_MANIFEST as RetriableManifestDownloadErrorEvent");
            DLog.warnf("posting retriable manifest download failure and it may cause session restart");
            this.mContentEventDispatcher.postEvent(new RetriableManifestDownloadErrorEvent(staleManifestErrorEvent.getContent(), staleManifestErrorEvent.getSessionType(), staleManifestErrorEvent.getException(), staleManifestErrorEvent.getConsecutiveStaleManifestCount(), staleManifestErrorEvent.getElapsedTimeInNanos(), this.mLastSuccessfulManifestDownloadTimeNanos));
        }
    }

    public void setLastSuccessfulManifestDownloadTimeNanos(long j) {
        this.mLastSuccessfulManifestDownloadTimeNanos = j;
    }

    public void begin() {
        this.mContentEventDispatcher.registerEventBusHandler(this);
    }

    public void end() {
        this.mContentEventDispatcher.unregisterEventBusHandler(this);
    }
}
