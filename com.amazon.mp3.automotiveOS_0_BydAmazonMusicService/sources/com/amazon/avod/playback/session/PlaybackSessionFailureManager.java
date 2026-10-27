package com.amazon.avod.playback.session;

import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.media.playback.support.HardwareAccelerationState;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.service.cache.ForwardingPlayerResourcesCache;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.qos.metadata.QOSEventName;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSessionFailureManager {
    private final VideoCacheManager mCacheManager;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;
    private final ForwardingPlayerResourcesCache mPlayerResourcesCache;
    private final PlaybackSessionFailureManagerPolicy mPolicy;
    private final RendererSchemeController mRendererSchemeController;
    private final VideoPresentationEventReporter mVideoPresentationEventReporter;

    public PlaybackSessionFailureManager(@Nonnull RendererSchemeController rendererSchemeController, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull VideoCacheManager videoCacheManager) {
        this(rendererSchemeController, PlaybackSessionFailureManagerPolicy.getInstance(), videoPresentationEventReporter, videoCacheManager, ForwardingPlayerResourcesCache.getInstance(), PlaybackPmetMetricReporter.getInstance());
    }

    PlaybackSessionFailureManager(@Nonnull RendererSchemeController rendererSchemeController, @Nonnull PlaybackSessionFailureManagerPolicy playbackSessionFailureManagerPolicy, @Nonnull VideoPresentationEventReporter videoPresentationEventReporter, @Nonnull VideoCacheManager videoCacheManager, @Nonnull ForwardingPlayerResourcesCache forwardingPlayerResourcesCache, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mRendererSchemeController = (RendererSchemeController) Preconditions.checkNotNull(rendererSchemeController, "rendererSchemeController");
        this.mPolicy = (PlaybackSessionFailureManagerPolicy) Preconditions.checkNotNull(playbackSessionFailureManagerPolicy, "policy");
        this.mVideoPresentationEventReporter = (VideoPresentationEventReporter) Preconditions.checkNotNull(videoPresentationEventReporter, "videoPresentationEventReporter");
        this.mCacheManager = (VideoCacheManager) Preconditions.checkNotNull(videoCacheManager, "cacheManager");
        this.mPlayerResourcesCache = (ForwardingPlayerResourcesCache) Preconditions.checkNotNull(forwardingPlayerResourcesCache, "forwardingPlayerResourcesCache");
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
    }

    public void notifyMediaException(@Nonnull MediaException mediaException, @Nonnull RendererSchemeType rendererSchemeType) {
        Preconditions.checkNotNull(mediaException, "terminationCause");
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        if (this.mPolicy.shouldHandle(mediaException)) {
            this.mPolicy.reportException();
            if (this.mPolicy.shouldExecuteRecoveryAction()) {
                executeRecoveryAction(mediaException, rendererSchemeType);
            }
        }
    }

    public void executeRecoveryAction(@Nonnull MediaException mediaException, @Nonnull RendererSchemeType rendererSchemeType) {
        HardwareAccelerationState hardwareAccelerationState;
        Preconditions.checkNotNull(mediaException, "terminationCause");
        Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        if (this.mRendererSchemeController.changeRendererSchemeStatus(mediaException, rendererSchemeType, true)) {
            DLog.errorf("PlaybackSessionFailureManager clearing ALL whisper cache data and cached playback resources following %s renderer scheme failure!", rendererSchemeType);
            this.mCacheManager.clearCache();
            this.mPlayerResourcesCache.clear();
            String str = String.format(Locale.US, "PlaybackSessionFailureManager executing recovery action on error: %s, policy: %s", mediaException, this.mPolicy.getPolicyNote());
            DLog.warnf(str);
            this.mVideoPresentationEventReporter.getPlaybackReporter().reportMetric(QOSEventName.Information.toString(), "PlaybackStackFailureRecoveryTriggered", null, str, null);
            MediaErrorCode externalCode = mediaException.getErrorCode().getExternalCode();
            if (externalCode == StandardErrorCode.AUTO_EVAL_CANARY_FAILURE) {
                hardwareAccelerationState = HardwareAccelerationState.DISABLED_CANARY;
            } else if (externalCode == StandardErrorCode.AUTO_EVAL_POOR_PLAYER_PERFORMANCE) {
                hardwareAccelerationState = HardwareAccelerationState.DISABLED_PERFORMANCE;
            } else {
                hardwareAccelerationState = HardwareAccelerationState.DISABLED_FATAL;
            }
            this.mPlaybackPmetMetricReporter.reportPlaybackHardwareAccelerationToggled(hardwareAccelerationState, mediaException.getErrorCode());
        }
    }
}
