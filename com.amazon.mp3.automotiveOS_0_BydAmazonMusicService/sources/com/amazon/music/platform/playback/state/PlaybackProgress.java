package com.amazon.music.platform.playback.state;

import kotlin.Metadata;

/* JADX INFO: compiled from: PlaybackProgress.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0007\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/playback/state/PlaybackProgress;", "", "", "getProgressInMS", "()F", "progressInMS", "getBufferedPercentage", "bufferedPercentage", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface PlaybackProgress {
    float getBufferedPercentage();

    float getProgressInMS();
}
