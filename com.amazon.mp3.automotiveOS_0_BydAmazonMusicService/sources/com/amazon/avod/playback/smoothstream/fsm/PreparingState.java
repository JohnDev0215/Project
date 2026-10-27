package com.amazon.avod.playback.smoothstream.fsm;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerConfig;
import com.amazon.avod.fsm.Trigger;
import com.amazon.avod.media.ads.internal.VastReporter;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.event.playback.PlaybackLoadedEvent;
import com.amazon.avod.playback.session.PlaybackSessionContext;
import com.amazon.avod.playback.session.PlaybackSessionResources;
import com.amazon.avod.playback.smoothstream.TimelineUtils;
import com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState;
import com.amazon.avod.playback.smoothstream.fsm.Triggers;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class PreparingState extends PlayerLifecycleState {
    private final boolean mIsSSAIEnabled;
    private Boolean mIsStateActive;
    private final LifecycleProfiler mLifecycleProfiler;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;
    private final Object mMutex;
    private final PlaybackEnvelopeValidator mPlaybackEnvelopeValidator;
    private final boolean mShouldEnableAdManifestCapturer;
    private final TimelineUtils mTimelineUtils;
    private VastReporter mVastReporter;

    public PreparingState(PlayerLifecycleStateMachine playerLifecycleStateMachine, PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext) {
        this(playerLifecycleStateMachine, playbackSessionResources, playbackSessionContext, new TimelineUtils(), MediaSystemSharedDependencies.getInstance(), ManifestCapturerConfig.INSTANCE.shouldEnableAdManifestCapturer(), AdsConfig.getInstance().isSSAIEnabled());
    }

    public PreparingState(PlayerLifecycleStateMachine playerLifecycleStateMachine, PlaybackSessionResources playbackSessionResources, PlaybackSessionContext playbackSessionContext, TimelineUtils timelineUtils, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies, boolean z, boolean z2) {
        super(playerLifecycleStateMachine, PlayerLifecycleState.Type.PREPARING, playbackSessionResources, playbackSessionContext);
        this.mIsStateActive = Boolean.FALSE;
        this.mMutex = new Object();
        this.mTimelineUtils = timelineUtils;
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
        this.mLifecycleProfiler = playbackSessionResources.getLifecycleProfiler();
        this.mShouldEnableAdManifestCapturer = z;
        this.mIsSSAIEnabled = z2;
        this.mPlaybackEnvelopeValidator = mediaSystemSharedDependencies.getPlaybackEnvelopeValidator();
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x027f  */
    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void doEnter(@javax.annotation.Nonnull com.amazon.avod.fsm.Trigger<com.amazon.avod.playback.smoothstream.fsm.Triggers.Type> r36) throws com.amazon.avod.media.framework.error.MediaException {
        /*
            Method dump skipped, instruction units count: 1904
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.smoothstream.fsm.PreparingState.doEnter(com.amazon.avod.fsm.Trigger):void");
    }

    @Override // com.amazon.avod.playback.smoothstream.fsm.PlayerLifecycleState, com.amazon.avod.fsm.StateBase, com.amazon.avod.fsm.State
    public void exit(@Nonnull Trigger<Triggers.Type> trigger) {
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this);
        this.mPlaybackSessionResources.getPlaybackEventTransport().unregisterEventBusHandler(this.mVastReporter);
        this.mPlaybackSessionResources.getContentManagementEventBus().unregisterEventBusHandler(this.mVastReporter);
        synchronized (this.mMutex) {
            this.mIsStateActive = Boolean.FALSE;
        }
    }

    @Subscribe
    public void onPreparedEvent(PlaybackLoadedEvent playbackLoadedEvent) {
        synchronized (this.mMutex) {
            if (this.mIsStateActive.booleanValue()) {
                this.mPlaybackSessionContext.notifyPrimaryPlayerPrepared();
                ContentSession contentSession = this.mPlaybackSessionContext.getContentSession();
                Preconditions.checkState(contentSession != null);
                contentSession.setPlaybackRestrictedToBufferedContent(true);
            }
        }
    }
}
