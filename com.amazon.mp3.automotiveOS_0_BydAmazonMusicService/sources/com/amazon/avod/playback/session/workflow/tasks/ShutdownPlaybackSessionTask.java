package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheEviction;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.util.DLog;
import com.google.common.eventbus.EventBus;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ShutdownPlaybackSessionTask extends BasicTeardownTask {
    public ShutdownPlaybackSessionTask(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback, @Nonnull PlaybackSessionResources playbackSessionResources, @Nonnull PlaybackSessionContext playbackSessionContext, boolean z, @Nullable MediaException mediaException) {
        super(eventBus, exceptionCallback, playbackSessionResources, playbackSessionContext, z, mediaException);
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.BasicTeardownTask
    protected void executePreContentSessionTerminationTasks() throws MediaException {
        handleTerminationCause();
        this.mPlaybackSessionResources.getDataCollector().shutdown();
    }

    @Override // com.amazon.avod.playback.session.workflow.tasks.BasicTeardownTask
    protected void executePostContentSessionTerminationTasks() throws MediaException {
        PlaybackMediaEventReporters mediaEventReportersMain;
        this.mPlaybackSessionResources.getPlaybackEventTransport().shutdown();
        if (AloysiusConfig.getInstance().shouldUnifyMediaEventReporters()) {
            mediaEventReportersMain = this.mPlaybackSessionResources.getMediaEventReporters();
        } else {
            mediaEventReportersMain = this.mPlaybackSessionResources.getMediaEventReportersMain();
        }
        mediaEventReportersMain.terminate();
        if (this.mPlaybackSessionContext.getDrmScheme() != null && this.mPlaybackSessionContext.getRendererSchemeType() != null) {
            this.mPlaybackSessionResources.getDrmSystem().shutdownDrmSession(this.mPlaybackSessionContext.getDrmScheme(), this.mPlaybackSessionContext.getRendererSchemeType());
        }
        VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
        if (videoSpec != null && videoSpec.isLiveStream()) {
            this.mPlaybackSessionResources.getCacheManager().getLiveCache().notifyPlaybackTerminated();
        }
        this.mPlaybackSessionContext.getTerminationLatch().countDown();
    }

    private void handleTerminationCause() {
        MediaException mediaException = this.mTerminationCause;
        if (mediaException == null) {
            return;
        }
        DLog.logf("Termination cause: %s", mediaException);
        this.mPlaybackSessionResources.getPlaybackSessionFailureManager().notifyMediaException(this.mTerminationCause, this.mPlaybackSessionContext.getRendererSchemeType());
        if (this.mPlaybackSessionResources.getPlaybackConfig().getLicenseFailureErrorStrings().contains(this.mTerminationCause.getErrorCode().getExternalCode().getName())) {
            VideoCacheManager cacheManager = this.mPlaybackSessionResources.getCacheManager();
            VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
            if (videoSpec != null && cacheManager.queryFor(videoSpec) != null) {
                DLog.warnf("Triggering eviction of cached content due to missing or expired key in license");
                cacheManager.evict(videoSpec, WhisperCacheMetrics$CacheEviction.LICENSE_ERROR, "Triggering eviction of cached content due to missing or expired key in license");
            }
        }
        if (this.mTerminationCause.getErrorCode().getExternalCode() == LicenseError.AIV_DRM_FRAMEWORK_BUSY.getExternalCode()) {
            forceReleaseDrmSystemLock(String.format(Locale.US, "Force released the drm lock because %s caused the session to terminate.", this.mTerminationCause));
        }
    }

    private void forceReleaseDrmSystemLock(@Nonnull String str) {
        if (this.mPlaybackSessionResources.getPlaybackConfig().isDrmSystemBusyRecoveryEnabled()) {
            try {
                VideoSpecification videoSpec = this.mPlaybackSessionContext.getVideoSpec();
                ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
                if (videoSpec != null && contentSession != null && contentSession.isActive()) {
                    String encodedEncryptionHeader = contentSession.getEncodedEncryptionHeader();
                    DrmScheme drmScheme = this.mPlaybackSessionContext.getDrmScheme();
                    RendererSchemeType rendererSchemeType = this.mPlaybackSessionContext.getRendererSchemeType();
                    if (encodedEncryptionHeader == null) {
                        return;
                    }
                    this.mPlaybackSessionResources.getDrmSystem().shutdownPlayback(videoSpec.getTitleId(), encodedEncryptionHeader, this.mPlaybackSessionContext.getDownload() == null ? ConsumptionType.Streaming : ConsumptionType.Download, drmScheme, rendererSchemeType);
                    DLog.warnf(str);
                    this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter().reportMetric(QOSEventName.Information.toString(), "forceReleaseDrm", null, str, null);
                }
            } catch (ContentException e) {
                DLog.warnf("Failed to release the drm system lock because of %s.", e);
            }
        }
    }
}
