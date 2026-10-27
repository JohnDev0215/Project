package com.amazon.video.sdk.player;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractPlaybackEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/player/AbstractVideoPresentationEventListener;", "Lcom/amazon/avod/media/playback/VideoPresentationEventListener;", "()V", "onCompletion", "", "presentation", "Lcom/amazon/avod/media/playback/VideoPresentation;", "onError", "errorCode", "Lcom/amazon/avod/media/error/MediaErrorCode;", "onPrepared", "dataSource", "Lcom/amazon/avod/media/playback/PlaybackDataSource;", "onStarted", "onTerminated", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractVideoPresentationEventListener implements VideoPresentationEventListener {
    public static final AbstractVideoPresentationEventListener INSTANCE = new AbstractVideoPresentationEventListener();

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation presentation) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation presentation, MediaErrorCode errorCode) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation presentation, PlaybackDataSource dataSource) {
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(VideoPresentation presentation, PlaybackDataSource dataSource) {
        Intrinsics.checkNotNullParameter(presentation, "presentation");
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
    }

    private AbstractVideoPresentationEventListener() {
    }
}
