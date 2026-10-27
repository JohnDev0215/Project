package com.amazon.video.sdk.player;

import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: AbstractPlaybackEventListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/player/AbstractPlaybackSessionBufferEventListener;", "Lcom/amazon/avod/playback/PlaybackSessionBufferEventListener;", "()V", "onBufferEnd", "", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/avod/playback/PlaybackBufferEventType;", "playbackEventContext", "Lcom/amazon/avod/playback/PlaybackEventContext;", "onBufferProgress", "percent", "", "onBufferStart", "bufferingAnalysis", "Lcom/amazon/avod/playback/BufferingAnalysis;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AbstractPlaybackSessionBufferEventListener implements PlaybackSessionBufferEventListener {
    public static final AbstractPlaybackSessionBufferEventListener INSTANCE = new AbstractPlaybackSessionBufferEventListener();

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType type, PlaybackEventContext playbackEventContext) {
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferProgress(float percent) {
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType type, PlaybackEventContext playbackEventContext, BufferingAnalysis bufferingAnalysis) {
    }

    private AbstractPlaybackSessionBufferEventListener() {
    }
}
