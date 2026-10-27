package com.amazon.video.sdk.player;

import androidx.core.app.NotificationCompat;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.video.sdk.Feature;
import com.amazon.video.sdk.player.error.PlaybackError;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeature;
import com.amazon.video.sdk.stream.StreamFeature;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: Player.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\b\u001a\u00020\u0006H&J\b\u0010\t\u001a\u00020\u0006H&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH&J.\u0010\u0013\u001a\u00020\u0006\"\b\b\u0000\u0010\u000e*\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H&J.\u0010\u0014\u001a\u00020\u0006\"\b\b\u0000\u0010\u000e*\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H&J\b\u0010\u0015\u001a\u00020\u0006H&R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u001a8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010!\u001a\u00020\u001e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010%\u001a\u00020\"8&X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010)\u001a\u00020&8&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u001c\u0010/\u001a\u00020*8&@&X¦\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0014\u00101\u001a\u0002008&X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u001c\u0010\u0005\u001a\u00020\u00048&@&X¦\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u00067"}, d2 = {"Lcom/amazon/video/sdk/player/Player;", "Lcom/amazon/video/sdk/Feature;", "Lcom/amazon/video/sdk/player/ContentConfig;", "content", "Lcom/amazon/video/sdk/player/RenderingConfig;", "renderingConfig", "", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "pause", PageUriUtils.DO_PLAY_VALUE, "", "position", "seek", "Lcom/amazon/video/sdk/player/PlayerEvent;", "E", "Ljava/lang/Class;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/video/sdk/player/EventListener;", "callback", DebugKt.DEBUG_PROPERTY_VALUE_ON, DebugKt.DEBUG_PROPERTY_VALUE_OFF, "destroy", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "getContentError", "()Lcom/amazon/video/sdk/player/error/PlaybackError;", "contentError", "Lcom/amazon/video/sdk/player/ContentState;", "getContentState", "()Lcom/amazon/video/sdk/player/ContentState;", "contentState", "Lcom/amazon/video/sdk/player/PlaybackState;", "getPlaybackState", "()Lcom/amazon/video/sdk/player/PlaybackState;", TetheredMessageKey.playbackState, "Lcom/amazon/video/sdk/stream/StreamFeature;", "getStreamFeature", "()Lcom/amazon/video/sdk/stream/StreamFeature;", "streamFeature", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "getVideoPreviewAssetFeature", "()Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "videoPreviewAssetFeature", "", "getPlaybackRate", "()F", "setPlaybackRate", "(F)V", "playbackRate", "", "isVideoTrackRecreationSupported", "()Z", "getRenderingConfig", "()Lcom/amazon/video/sdk/player/RenderingConfig;", "setRenderingConfig", "(Lcom/amazon/video/sdk/player/RenderingConfig;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface Player extends Feature {
    void destroy();

    PlaybackError getContentError();

    ContentState getContentState();

    float getPlaybackRate();

    PlaybackState getPlaybackState();

    RenderingConfig getRenderingConfig();

    StreamFeature getStreamFeature();

    VideoPreviewAssetFeature getVideoPreviewAssetFeature();

    boolean isVideoTrackRecreationSupported();

    <E extends PlayerEvent> void off(Class<E> event, EventListener<E> callback);

    <E extends PlayerEvent> void on(Class<E> event, EventListener<E> callback);

    void pause();

    void play();

    void seek(long position);

    void setPlaybackRate(float f);

    void setRenderingConfig(RenderingConfig renderingConfig);

    void start(ContentConfig content, RenderingConfig renderingConfig);
}
