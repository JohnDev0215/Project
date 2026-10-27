package com.amazon.avod.media.ads.internal.state;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdClip;
import com.amazon.avod.media.ads.AdError;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdClip;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachine;
import com.amazon.avod.media.ads.internal.AdPlaybackStateMachineContext;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimerTask;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdBreakBufferEventListener implements PlaybackSessionBufferEventListener {
    private final AdsConfig mAdsConfig;
    private final AdPlaybackStateMachineContext mContext;
    private final AdPlaybackStateMachine mStateMachine;
    private AdEnabledPlaybackTimer mTimer;
    private final Object mMutex = new Object();
    private boolean mIsActive = false;

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferProgress(float f) {
    }

    public AdBreakBufferEventListener(AdPlaybackStateMachineContext adPlaybackStateMachineContext, AdsConfig adsConfig, AdPlaybackStateMachine adPlaybackStateMachine) {
        this.mContext = adPlaybackStateMachineContext;
        this.mAdsConfig = adsConfig;
        this.mStateMachine = adPlaybackStateMachine;
    }

    private TimeSpan calculateTimeout() {
        AdManagerBasedAdClip currentAdClip = this.mContext.getCurrentAdClip();
        AdBreak currentAdBreak = this.mContext.getCurrentAdBreak();
        AdBreakBufferContext adBreakBufferContext = this.mContext.getAdBreakBufferContext();
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(currentAdBreak.getDuration().getTotalSeconds() * this.mAdsConfig.getAdBreakBufferMillisFactor());
        TimeSpan timeSpanAdd = TimeSpan.ZERO;
        Iterator<AdClip> it = currentAdBreak.getClips().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AdClip next = it.next();
            if (next != currentAdClip) {
                timeSpanAdd = TimeSpan.add(timeSpanAdd, next.getDuration());
            } else {
                timeSpanAdd = TimeSpan.add(timeSpanAdd, TimeSpan.fromMilliseconds(currentAdClip.getPlayer().getCurrentPosition()));
                break;
            }
        }
        return TimeSpan.min(TimeSpan.difference(timeSpanFromSeconds, TimeSpan.add(adBreakBufferContext.getBufferStopwatchTime(), timeSpanAdd)), this.mAdsConfig.getAdClipBufferingTimeout());
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        if (this.mIsActive) {
            AdBreakBufferContext adBreakBufferContext = this.mContext.getAdBreakBufferContext();
            if (playbackBufferEventType != PlaybackBufferEventType.INITIAL_LOADING) {
                adBreakBufferContext.incrementCurrentBreakBuffersCount();
            }
            if (adBreakBufferContext.getCurrentBreakBuffersCount() > this.mAdsConfig.getAdBreakNumBuffersThreshold()) {
                this.mStateMachine.doTrigger(new ClipErrorTrigger(this.mContext.getCurrentAdClip(), AdError.EXCESSIVE_BUFFERING, String.format(Locale.US, "Ad buffer count of %d exceeds allowed %d", Integer.valueOf(adBreakBufferContext.getCurrentBreakBuffersCount()), Integer.valueOf(this.mAdsConfig.getAdBreakNumBuffersThreshold()))));
                return;
            }
            synchronized (this.mMutex) {
                adBreakBufferContext.startBufferStopwatch();
                AdEnabledPlaybackTimer adEnabledPlaybackTimer = this.mTimer;
                if (adEnabledPlaybackTimer != null) {
                    adEnabledPlaybackTimer.stop();
                }
                AdEnabledPlaybackTimer adEnabledPlaybackTimer2 = new AdEnabledPlaybackTimer(calculateTimeout());
                this.mTimer = adEnabledPlaybackTimer2;
                adEnabledPlaybackTimer2.start(new TimerTask() { // from class: com.amazon.avod.media.ads.internal.state.AdBreakBufferEventListener.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        synchronized (AdBreakBufferEventListener.this.mMutex) {
                            if (AdBreakBufferEventListener.this.mIsActive) {
                                AdBreakBufferEventListener.this.mStateMachine.doTrigger(new ClipErrorTrigger(AdBreakBufferEventListener.this.mContext.getCurrentAdClip(), AdError.EXCESSIVE_BUFFERING, String.format(Locale.US, "Ad buffering exceeded allowed factor of %s", Float.valueOf(AdBreakBufferEventListener.this.mAdsConfig.getAdBreakBufferMillisFactor()))));
                            }
                        }
                    }
                });
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        if (this.mIsActive) {
            stopTimers();
        }
    }

    public void onStartingClip() {
        this.mIsActive = true;
    }

    public void onEndingClip() {
        synchronized (this.mMutex) {
            this.mIsActive = false;
            stopTimers();
        }
    }

    private void stopTimers() {
        synchronized (this.mMutex) {
            this.mContext.getAdBreakBufferContext().stopBufferStopwatch();
            AdEnabledPlaybackTimer adEnabledPlaybackTimer = this.mTimer;
            if (adEnabledPlaybackTimer != null) {
                adEnabledPlaybackTimer.stop();
                this.mTimer = null;
            }
        }
    }
}
