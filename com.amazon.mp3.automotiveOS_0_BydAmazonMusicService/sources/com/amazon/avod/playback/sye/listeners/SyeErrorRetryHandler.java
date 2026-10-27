package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.playback.internal.PlaybackListenerProxy;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.playback.sye.SyeDomainHolder;
import com.amazon.avod.playback.sye.SyeDomainVendingMachine;
import com.amazon.avod.playback.sye.events.SyeFallbackMidStreamEvent;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SyeConfig;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.SyeSystem;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerError;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.playerListeners.IErrorListener;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeErrorRetryHandler implements IErrorListener {
    private final SyeConfig mConfig;
    private final ScheduledExecutorService mExecutor;
    private final int mMaxRetryTimeoutMillis;
    private final PlaybackEventTransport mPlaybackEventBus;
    private final PlaybackListenerProxy mPlaybackListenerProxy;
    private final PlaybackMediaEventReporters mReporters;
    private ScheduledFuture<?> mRetryTimeoutFuture;
    private final SyeDomainVendingMachine mSyeDomainVendingMachine;
    private ISyePlayer mSyePlayer;

    @Override // com.netinsight.sye.syeClient.playerListeners.IErrorListener
    public void onError(@Nonnull SyePlayerError syePlayerError, @Nonnull String str) {
    }

    public SyeErrorRetryHandler(@Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull PlaybackListenerProxy playbackListenerProxy, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine, @Nonnull PlaybackMediaEventReporters playbackMediaEventReporters, @Nonnull SyeConfig syeConfig) {
        this.mExecutor = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "executor");
        this.mPlaybackListenerProxy = (PlaybackListenerProxy) Preconditions.checkNotNull(playbackListenerProxy, "playbackListenerProxy");
        this.mPlaybackEventBus = (PlaybackEventTransport) Preconditions.checkNotNull(playbackEventTransport, "eventTransport");
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
        this.mReporters = (PlaybackMediaEventReporters) Preconditions.checkNotNull(playbackMediaEventReporters, "reporters");
        SyeConfig syeConfig2 = (SyeConfig) Preconditions.checkNotNull(syeConfig, "config");
        this.mConfig = syeConfig2;
        this.mMaxRetryTimeoutMillis = syeConfig2.getMaxRetryTimeoutMillis();
    }

    public void setSyePlayer(ISyePlayer iSyePlayer) {
        this.mSyePlayer = iSyePlayer;
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IErrorListener
    public void onErrorRetry(@Nonnull SyePlayerError syePlayerError, @Nonnull String str, int i) {
        Preconditions.checkArgument(this.mSyePlayer != null, "setSyePlayer must be called before start");
        DLog.warnf("SyeErrorRetryHandler#onErrorRetry %s : %s : retryMs %s", syePlayerError, str, Integer.valueOf(i));
        int i2 = this.mMaxRetryTimeoutMillis;
        if (i > i2) {
            DLog.warnf("SyeErrorRetryHandler#onErrorRetry, retryAfterMillis exceeds %s", Integer.valueOf(i2));
            playNextDomainOrFallback(syePlayerError, str);
            clearErrorHandleTimeout();
        } else {
            if (!this.mExecutor.isTerminated() && !this.mExecutor.isShutdown()) {
                this.mExecutor.schedule(new Runnable() { // from class: com.amazon.avod.playback.sye.listeners.SyeErrorRetryHandler$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onErrorRetry$0();
                    }
                }, i, TimeUnit.MILLISECONDS);
            } else {
                DLog.warnf("onErrorRetry executor is shutdown, player was exiting...");
            }
            setErrorHandleTimeout(syePlayerError, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onErrorRetry$0() {
        try {
            this.mSyePlayer.playResume();
        } catch (Exception e) {
            DLog.logf("failed to playResume() : %s", e.getMessage());
        }
    }

    private void setErrorHandleTimeout(@Nonnull final SyePlayerError syePlayerError, @Nonnull final String str) {
        if (this.mRetryTimeoutFuture != null) {
            return;
        }
        if (!this.mExecutor.isTerminated() && !this.mExecutor.isShutdown()) {
            this.mRetryTimeoutFuture = this.mExecutor.schedule(new Runnable() { // from class: com.amazon.avod.playback.sye.listeners.SyeErrorRetryHandler$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setErrorHandleTimeout$1(syePlayerError, str);
                }
            }, this.mMaxRetryTimeoutMillis, TimeUnit.MILLISECONDS);
        } else {
            DLog.warnf("SyeErrorRetryHandler#setErrorHandleTimeout executor is shutdown...");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setErrorHandleTimeout$1(SyePlayerError syePlayerError, String str) {
        if (this.mSyePlayer.getPlayerState() != SyePlayerState.Playing) {
            DLog.warnf("SyeErrorRetryHandler#setErrorHandleTimeout playerState is not Playing %s", this.mSyePlayer.getPlayerState());
            playNextDomainOrFallback(syePlayerError, str);
        }
        clearErrorHandleTimeout();
    }

    private void clearErrorHandleTimeout() {
        ScheduledFuture<?> scheduledFuture = this.mRetryTimeoutFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mRetryTimeoutFuture = null;
        }
    }

    private void playNextDomainOrFallback(@Nonnull SyePlayerError syePlayerError, @Nonnull String str) {
        String str2 = (String) Preconditions.checkNotNull(this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId(), "previousConsumptionId");
        SyeDomainHolder syeDomainHolderNextSyeDomain = this.mSyeDomainVendingMachine.nextSyeDomain();
        if (syeDomainHolderNextSyeDomain != null && this.mConfig.getSwitchFrontendUrlEnabledForRetryableError()) {
            DLog.logf("SyeErrorRetryHandler#playNextDomainOrFailover, switching to SyeDomain %s : %s, switching consumptionId from %s : to %s", syeDomainHolderNextSyeDomain.getSyeDomain().getBaseUrl(), syeDomainHolderNextSyeDomain.getSyeDomain().getName(), str2, syeDomainHolderNextSyeDomain.getConsumptionId());
            this.mReporters.getPlaybackReporter().updateConsumptionId(syeDomainHolderNextSyeDomain.getConsumptionId());
            this.mSyePlayer.updateSyeSystem(new SyeSystem(syeDomainHolderNextSyeDomain.getSyeDomain(), syeDomainHolderNextSyeDomain.getToken()));
            this.mSyePlayer.playResume();
            this.mReporters.getAloysiusFailoverReporter().handleLiveStreamingRestartEvent(LiveStreamingRestartEvent.newOriginSwitchEvent(this.mSyeDomainVendingMachine.currentSyeDomain().getConsumptionId(), null, System.currentTimeMillis(), false, null, null, str2));
            return;
        }
        DLog.warnf("SyeErrorRetryHandler#playNextDomainOrFailover, failover to DASH");
        this.mPlaybackEventBus.postEvent(new SyeFallbackMidStreamEvent(syePlayerError));
        this.mPlaybackListenerProxy.onError(SyeMediaErrorCodeExtra.FALLBACK_MIDSTREAM);
    }
}
