package com.amazon.avod.playback.player.states;

import com.amazon.avod.drm.DecryptionContext;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.VideoRenderer;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.PlaybackSessionProtocol;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.event.playback.RetriablePlaybackErrorEvent;
import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.playback.player.actions.LaunchAction;
import com.amazon.avod.playback.player.actions.PlayAction;
import com.amazon.avod.pmet.LifecycleProfilerMetrics;
import com.amazon.avod.threading.Tickers;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class LaunchingState extends PlaybackEngineState {
    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExit() {
    }

    public LaunchingState(PlaybackStateDependencies playbackStateDependencies, PlaybackStateContext playbackStateContext) {
        super(playbackStateDependencies, playbackStateContext, Tickers.androidTicker());
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public PlaybackState getState() {
        return PlaybackState.Launching;
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onEnter(Action action) throws MediaException {
        Preconditions.checkArgument(action instanceof LaunchAction, "Can only accept LaunchAction in LaunchingState!");
        LaunchAction launchAction = (LaunchAction) action;
        MediaProfiler profiler = getProfiler();
        VideoRenderer renderer = getRenderer();
        profiler.start("VideoRenderer.configureOutputDisplay");
        renderer.configureOutputDisplay(launchAction.getDisplaySettings());
        if (this.mPlaybackStateContext.getSpecification() != null && this.mPlaybackStateContext.getSpecification().getVideoRegion() != null) {
            renderer.setVideoRegion(this.mPlaybackStateContext.getSpecification().getVideoRegion());
        }
        profiler.stop("VideoRenderer.configureOutputDisplay");
        PlaybackSessionProtocol playbackSessionProtocol = getPlaybackSessionProtocol();
        String encodedEncryptionHeader = playbackSessionProtocol.isEncryptedContent() ? playbackSessionProtocol.getEncodedEncryptionHeader() : null;
        String offlineKeyId = launchAction.getOfflineKeyId();
        DrmScheme drmScheme = launchAction.getDrmScheme();
        DecryptionContext decryptionContextNewDecryptionContext = this.mPlaybackStateDependencies.getDecryptionContextFactory().newDecryptionContext(this.mPlaybackStateContext.getSpecification(), playbackSessionProtocol.isDownload(), encodedEncryptionHeader, offlineKeyId, launchAction.isDash(), drmScheme, launchAction.getRendererSchemeType(), this.mPlaybackStateContext.getContentSession());
        this.mPlaybackStateContext.setDecryptionContext(decryptionContextNewDecryptionContext);
        profiler.start("DecryptionContext.initialize");
        decryptionContextNewDecryptionContext.initialize();
        profiler.stop("DecryptionContext.initialize");
        if (playbackSessionProtocol.isEncryptedContent()) {
            byte[] encryptionInitializationData = playbackSessionProtocol.getEncryptionInitializationData();
            LifecycleProfiler lifecycleProfiler = this.mPlaybackStateContext.getLifecycleProfiler();
            LifecycleProfilerMetrics lifecycleProfilerMetrics = LifecycleProfilerMetrics.CONFIGURE_DECRYPTION;
            lifecycleProfiler.start(lifecycleProfilerMetrics);
            profiler.start("VideoRenderer.configureDecryption");
            try {
                try {
                    renderer.configureDecryption(encryptionInitializationData, this.mPlaybackStateContext.getContentSession().getVideoDefaultKeyId(), this.mPlaybackStateContext.getContentSession().getAudioDefaultKeyId(), offlineKeyId, drmScheme);
                    this.mPlaybackStateContext.getLifecycleProfiler().end(lifecycleProfilerMetrics);
                } catch (PlaybackException e) {
                    postEvent(new RetriablePlaybackErrorEvent(TimeSpan.now(), e));
                    decryptionContextNewDecryptionContext.repairDecryptionInitializationFailure(e);
                    renderer.configureDecryption(encryptionInitializationData, this.mPlaybackStateContext.getContentSession().getVideoDefaultKeyId(), this.mPlaybackStateContext.getContentSession().getAudioDefaultKeyId(), offlineKeyId, drmScheme);
                    this.mPlaybackStateContext.getLifecycleProfiler().end(LifecycleProfilerMetrics.CONFIGURE_DECRYPTION);
                }
                profiler.stop("VideoRenderer.configureDecryption");
            } catch (Throwable th) {
                this.mPlaybackStateContext.getLifecycleProfiler().end(LifecycleProfilerMetrics.CONFIGURE_DECRYPTION);
                profiler.stop("VideoRenderer.configureDecryption");
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.playback.player.states.PlaybackEngineState
    public void onExecute() {
        enqueueAction(new PlayAction(getState()));
    }
}
