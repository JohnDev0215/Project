package com.amazon.avod.media.playback.reporting.aloysius;

import com.amazon.avod.event.PlaybackEventTransport;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackStateTracker;
import com.amazon.avod.media.service.AVODServiceConfig;
import com.amazon.avod.playback.session.ConsumptionIdProvider;
import com.amazon.avod.playback.sye.events.BitstreamEvent;
import com.amazon.avod.playback.sye.events.SyePlaybackStartEvent;
import com.amazon.avod.util.DLog;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyeAloysiusPlaybackReporter extends AloysiusPlaybackReporter {
    public SyeAloysiusPlaybackReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull PlaybackEventTransport playbackEventTransport, @Nonnull String str) {
        super(mediaEventQueue, null, playbackEventTransport, null, new ConsumptionIdProvider(str), true, AloysiusPlaybackStateTracker.getInstance(), AdsConfig.getInstance(), AVODServiceConfig.getInstance());
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter
    public synchronized void handleSyePlaybackStartEvent(SyePlaybackStartEvent syePlaybackStartEvent) {
        this.mAloysiusPlaybackStateTracker.trigger(AloysiusPlaybackStateTracker.ActionType.MainStart);
        this.mPlaybackStarted = true;
        MediaEventQueue mediaEventQueue = this.mMediaEventQueue;
        AloysiusPlaybackReporter.PlaybackMediaEvent.Action action = AloysiusPlaybackReporter.PlaybackMediaEvent.Action.Context;
        AloysiusPlaybackReporter.PlaybackMediaEvent.ContentContext contentContext = AloysiusPlaybackReporter.PlaybackMediaEvent.ContentContext.Feature;
        mediaEventQueue.add(createMediaEvent(action, contentContext, syePlaybackStartEvent.getEventTimeStamp()));
        DLog.devf("PlaybackEvent : sending switch event during switch");
        this.mMediaEventQueue.add(createMediaEvent(AloysiusPlaybackReporter.PlaybackMediaEvent.Action.Start, contentContext, syePlaybackStartEvent.getEventTimeStamp()));
        DLog.devf("PlaybackEvent : ResumeEvent sending main player start event");
    }

    @Subscribe
    public synchronized void handleBitstreamEvent(BitstreamEvent bitstreamEvent) {
        this.mMediaEventQueue.add(bitstreamEvent);
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusPlaybackReporter, com.amazon.avod.media.events.MediaEventProducer
    public synchronized void produce() {
        this.mMediaEventQueue.add(createMediaEvent(AloysiusPlaybackReporter.PlaybackMediaEvent.Action.Update, AloysiusPlaybackReporter.PlaybackMediaEvent.ContentContext.Feature, null));
        DLog.devf("PlaybackEvent : sending Update event");
    }
}
