package com.amazon.avod.playback.smoothstream.fsm;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerConfig;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface;
import com.amazon.avod.fsm.StateBase;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.playback.FatalPlaybackErrorEvent;
import com.amazon.avod.playback.event.playback.PlaybackErrorEvent;
import com.amazon.avod.playback.player.VideoPlaybackEngine;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.fsm.Triggers;
import com.amazon.avod.qos.ErrorReportingConfiguration;
import com.amazon.avod.qos.internal.metrics.QoSMetric;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
abstract class PlayerLifecycleState extends StateBase<Type, Triggers.Type> {
    private final ErrorReportingConfiguration mErrorConfig;
    private final int mErrorSeverityLevelToUploadManifest;
    private final boolean mIsUploadManifestsWhenBufferAndFatalEnabled;
    protected ManifestCapturerInterface mManifestCapturer;
    protected final PlaybackSessionContext mPlaybackSessionContext;
    protected final PlaybackSessionResources mPlaybackSessionResources;
    private final Type mType;

    public enum Type {
        IDLE,
        PREPARING,
        ACTIVE,
        TEARDOWN
    }

    protected abstract void doEnter(@Nonnull Trigger<Triggers.Type> trigger) throws MediaException;

    @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(Trigger<Triggers.Type> trigger) {
    }

    protected PlayerLifecycleState(PlayerLifecycleStateMachine playerLifecycleStateMachine, Type type, PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext) {
        super(playerLifecycleStateMachine);
        this.mType = type;
        this.mPlaybackSessionResources = playbackSessionResources;
        this.mPlaybackSessionContext = playbackSessionContext;
        ManifestCapturerConfig manifestCapturerConfig = ManifestCapturerConfig.INSTANCE;
        this.mIsUploadManifestsWhenBufferAndFatalEnabled = manifestCapturerConfig.isUploadManifestsWhenBufferEnabled();
        this.mErrorSeverityLevelToUploadManifest = manifestCapturerConfig.getErrorSeverityLevelToUploadManifest();
        this.mErrorConfig = new ErrorReportingConfiguration();
    }

    @Override // com.amazon.avod.fsm.State
    /* JADX INFO: renamed from: getType */
    public Type get_type() {
        return this.mType;
    }

    @Override // com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void enter(Trigger<Triggers.Type> trigger) {
        this.mPlaybackSessionResources.getProfiler().start(getClass().getSimpleName() + ".onEnter()");
        try {
            doEnter(trigger);
        } catch (MediaException e) {
            handleError(e);
        }
        this.mPlaybackSessionResources.getProfiler().stop(getClass().getSimpleName() + ".onEnter()");
    }

    protected void handleError(@Nonnull MediaException mediaException) {
        ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
        String consumptionId = null;
        if (contentSession != null) {
            try {
                consumptionId = contentSession.getConsumptionId();
            } catch (IllegalStateException unused) {
            }
        }
        String str = consumptionId;
        if (this.mPlaybackSessionResources.getRendererSchemeController().notifyAutoEvalCanaryPlaybackFailureEvent(mediaException)) {
            DLog.warnf("Restarting the player as auto eval canary has failed due to: %s", mediaException);
            VideoPlaybackEngine playbackEngine = this.mPlaybackSessionContext.getPlaybackEngine();
            this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(PlaybackRestartEvent.newAutoEvalCanaryFailedRestartEvent(playbackEngine != null ? TimeUnit.NANOSECONDS.toMillis(playbackEngine.getCurrentPositionInNanos()) : 0L, mediaException.getErrorCode().getExternalCode().getName()));
            this.mPlaybackSessionResources.getEventReporter().getPlaybackReporter().reportMetric(QoSMetric.Information.toString(), "PlayerAutoEvalCanaryFailed", TimeSpan.ZERO, String.format(Locale.US, "AutoEvalCanary failed: %s", mediaException), null);
            return;
        }
        if (mediaException instanceof ContentException) {
            ContentException contentException = (ContentException) mediaException;
            FatalPlaybackErrorEvent fatalPlaybackErrorEvent = new FatalPlaybackErrorEvent(TimeSpan.ZERO, mediaException, null, contentException.getStatusCode(), str, contentException.isPreCache());
            this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(fatalPlaybackErrorEvent);
            postManifestIfNecessary(fatalPlaybackErrorEvent);
        } else {
            FatalPlaybackErrorEvent fatalPlaybackErrorEvent2 = new FatalPlaybackErrorEvent(TimeSpan.ZERO, mediaException, str);
            this.mPlaybackSessionResources.getPlaybackEventTransport().postEvent(fatalPlaybackErrorEvent2);
            postManifestIfNecessary(fatalPlaybackErrorEvent2);
        }
        doTrigger(new Triggers.TearDown(false));
    }

    private void postManifestIfNecessary(@Nonnull PlaybackErrorEvent playbackErrorEvent) {
        Preconditions.checkNotNull(playbackErrorEvent, NotificationCompat.CATEGORY_EVENT);
        MediaException playbackException = playbackErrorEvent.getPlaybackException();
        int severityFor = this.mErrorConfig.getSeverityFor(playbackException, playbackErrorEvent.isRetriable(), playbackErrorEvent.getHttpErrorCode());
        if (this.mManifestCapturer == null || !this.mIsUploadManifestsWhenBufferAndFatalEnabled || severityFor > this.mErrorSeverityLevelToUploadManifest) {
            return;
        }
        DLog.logf("Attempt to post manifest due to exception: %s", playbackException);
        this.mManifestCapturer.uploadFromException(playbackException);
    }
}
