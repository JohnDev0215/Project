package com.amazon.video.sdk.player;

import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import kotlin.Metadata;

/* JADX INFO: compiled from: AbstractPlaybackEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/player/AbstractPlaybackAdStateChangeListener;", "Lcom/amazon/avod/playback/PlaybackAdStateChangeListener;", "()V", "onAdPlaybackCompleted", "", "onAdPlaybackLoaded", "onAdPlaybackPaused", "onAdPlaybackPlaying", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractPlaybackAdStateChangeListener implements PlaybackAdStateChangeListener {
    public static final AbstractPlaybackAdStateChangeListener INSTANCE = new AbstractPlaybackAdStateChangeListener();

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackCompleted() {
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackLoaded() {
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPaused() {
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPlaying() {
    }

    private AbstractPlaybackAdStateChangeListener() {
    }
}
