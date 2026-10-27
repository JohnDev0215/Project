package com.amazon.music.media.playback.video;

import com.amazon.music.media.playback.PlaybackException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaybackException.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/media/playback/video/VideoPlaybackException;", "Lcom/amazon/music/media/playback/PlaybackException;", "config", "Lcom/amazon/music/media/playback/PlaybackException$Config;", "(Lcom/amazon/music/media/playback/PlaybackException$Config;)V", "DMMPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPlaybackException extends PlaybackException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPlaybackException(PlaybackException.Config config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
    }
}
