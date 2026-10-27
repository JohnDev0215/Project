package com.amazon.video.sdk.avod.playbackclient.control;

import com.amazon.avod.events.proxy.EventListenerProxy;
import com.amazon.avod.events.proxy.HandlerBasedListenerProxyFactory;
import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.avod.playback.PlaybackStateEventListener;
import com.amazon.video.sdk.avod.playbackclient.control.states.PlaybackControllerContext;
import com.amazon.video.sdk.avod.playbackclient.listeners.AudioTrackChangeListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackAdStateChangeListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackQualityChangedEventListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackSessionBufferEventListenerProxy;
import com.amazon.video.sdk.avod.playbackclient.listeners.PlaybackStateEventListenerProxy;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackEventDispatchFactory {
    private final HandlerBasedListenerProxyFactory mProxyFactory = new HandlerBasedListenerProxyFactory();

    @Nonnull
    public PlaybackEventDispatch createAndAttachDispatch(@Nonnull VideoPlayer videoPlayer, @Nonnull PlaybackControllerContext playbackControllerContext) {
        Preconditions.checkNotNull(videoPlayer, "videoPlayer");
        Preconditions.checkNotNull(playbackControllerContext, "context");
        PlaybackSessionBufferEventListenerProxy playbackSessionBufferEventListenerProxy = new PlaybackSessionBufferEventListenerProxy();
        PlaybackStateEventListenerProxy playbackStateEventListenerProxy = new PlaybackStateEventListenerProxy();
        PlaybackQualityChangedEventListenerProxy playbackQualityChangedEventListenerProxy = new PlaybackQualityChangedEventListenerProxy();
        PlaybackProgressEventListenerProxy playbackProgressEventListenerProxy = new PlaybackProgressEventListenerProxy();
        AudioTrackChangeListenerProxy audioTrackChangeListenerProxy = new AudioTrackChangeListenerProxy();
        PlaybackSpeedScrubResultListenerProxy playbackSpeedScrubResultListenerProxy = new PlaybackSpeedScrubResultListenerProxy();
        PlaybackAdStateChangeListenerProxy playbackAdStateChangeListenerProxy = new PlaybackAdStateChangeListenerProxy();
        final PlaybackSessionBufferEventListener playbackSessionBufferEventListener = (PlaybackSessionBufferEventListener) this.mProxyFactory.createUiProxy(PlaybackSessionBufferEventListener.class, playbackSessionBufferEventListenerProxy);
        final PlaybackStateEventListener playbackStateEventListener = (PlaybackStateEventListener) this.mProxyFactory.createUiProxy(PlaybackStateEventListener.class, playbackStateEventListenerProxy);
        final PlaybackQualityChangedEventListener playbackQualityChangedEventListener = (PlaybackQualityChangedEventListener) this.mProxyFactory.createUiProxy(PlaybackQualityChangedEventListener.class, playbackQualityChangedEventListenerProxy);
        final AudioTrackChangeListener audioTrackChangeListener = (AudioTrackChangeListener) this.mProxyFactory.createUiProxy(AudioTrackChangeListener.class, audioTrackChangeListenerProxy);
        final PlaybackAdStateChangeListener playbackAdStateChangeListener = (PlaybackAdStateChangeListener) this.mProxyFactory.createUiProxy(PlaybackAdStateChangeListener.class, playbackAdStateChangeListenerProxy);
        final Runnable runnable = new Runnable() { // from class: com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatchFactory.1
            @Override // java.lang.Runnable
            public void run() {
                EventListenerProxy.startDispatchingEvents(playbackSessionBufferEventListener, playbackStateEventListener, playbackQualityChangedEventListener, audioTrackChangeListener, playbackAdStateChangeListener);
            }
        };
        videoPlayer.addListener(playbackSessionBufferEventListener);
        videoPlayer.addListener(playbackStateEventListener);
        videoPlayer.addListener(playbackQualityChangedEventListener);
        videoPlayer.addListener(audioTrackChangeListener);
        videoPlayer.addListener(playbackAdStateChangeListener);
        playbackControllerContext.setPlaybackProgressEventListener(playbackProgressEventListenerProxy);
        playbackControllerContext.setPlaybackScrubEventListener(playbackSpeedScrubResultListenerProxy);
        return new PlaybackEventDispatch(playbackSessionBufferEventListenerProxy, playbackStateEventListenerProxy, playbackQualityChangedEventListenerProxy, playbackProgressEventListenerProxy, audioTrackChangeListenerProxy, playbackSpeedScrubResultListenerProxy, playbackAdStateChangeListenerProxy) { // from class: com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatchFactory.2
            private final AtomicBoolean mInitialized = new AtomicBoolean(false);

            @Override // com.amazon.video.sdk.avod.playbackclient.control.PlaybackEventDispatch
            public void initialize() {
                if (this.mInitialized.getAndSet(true)) {
                    return;
                }
                runnable.run();
            }
        };
    }
}
