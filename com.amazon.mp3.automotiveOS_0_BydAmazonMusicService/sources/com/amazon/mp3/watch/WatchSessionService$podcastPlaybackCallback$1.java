package com.amazon.mp3.watch;

import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import kotlin.Metadata;

/* JADX INFO: compiled from: WatchSessionDelegate.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/mp3/watch/WatchSessionService$podcastPlaybackCallback$1", "Lcom/amazon/podcast/media/playback/Playback$StateCallback;", "Lcom/amazon/podcast/media/playback/Playback$SpeedCallback;", "onPlaybackSpeedChange", "", "p0", "Lcom/amazon/podcast/media/playback/PlaybackSpeed;", "onPlaybackStateChange", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class WatchSessionService$podcastPlaybackCallback$1 implements Playback.StateCallback, Playback.SpeedCallback {
    final /* synthetic */ WatchSessionService this$0;

    WatchSessionService$podcastPlaybackCallback$1(WatchSessionService watchSessionService) {
        this.this$0 = watchSessionService;
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int p0) {
        this.this$0.onPlayStateChanged();
    }

    @Override // com.amazon.podcast.media.playback.Playback.SpeedCallback
    public void onPlaybackSpeedChange(PlaybackSpeed p0) {
        this.this$0.onPlayStateChanged();
    }
}
