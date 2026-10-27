package com.amazon.video.sdk.player;

import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.PlaybackContentEventListener;
import kotlin.Metadata;

/* JADX INFO: compiled from: AbstractPlaybackEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J'\u0010\u0005\u001a\u00020\u00042\u0010\u0010\u0006\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/player/AbstractPlaybackContentEventListener;", "Lcom/amazon/avod/playback/PlaybackContentEventListener;", "()V", "onContentDownloaded", "", "onContentMetadataAvailable", "availableVideoResolutions", "", "Lcom/amazon/avod/media/VideoResolution;", "videoFramerateFps", "", "([Lcom/amazon/avod/media/VideoResolution;D)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractPlaybackContentEventListener implements PlaybackContentEventListener {
    public static final AbstractPlaybackContentEventListener INSTANCE = new AbstractPlaybackContentEventListener();

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentDownloaded() {
    }

    @Override // com.amazon.avod.playback.PlaybackContentEventListener
    public void onContentMetadataAvailable(VideoResolution[] availableVideoResolutions, double videoFramerateFps) {
    }

    private AbstractPlaybackContentEventListener() {
    }
}
