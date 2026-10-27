package com.amazon.video.sdk.player;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackStateEventListener;
import kotlin.Metadata;

/* JADX INFO: compiled from: AbstractPlaybackEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/video/sdk/player/AbstractPlaybackStateEventListener;", "Lcom/amazon/avod/playback/PlaybackStateEventListener;", "()V", "onPause", "", "playbackEventContext", "Lcom/amazon/avod/playback/PlaybackEventContext;", "onResume", "onSeekEnd", "onSeekStart", "targetSeekTime", "Lcom/amazon/avod/media/TimeSpan;", "onStart", "onStop", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractPlaybackStateEventListener implements PlaybackStateEventListener {
    public static final AbstractPlaybackStateEventListener INSTANCE = new AbstractPlaybackStateEventListener();

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onPause(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onResume(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekEnd(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekStart(TimeSpan targetSeekTime, PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStart(PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStop(PlaybackEventContext playbackEventContext) {
    }

    private AbstractPlaybackStateEventListener() {
    }
}
