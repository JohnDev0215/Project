package com.amazon.video.sdk.player.videopreviewasset;

import androidx.core.app.NotificationCompat;
import com.amazon.video.sdk.Feature;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: VideoPreviewAssetFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J.\u0010\f\u001a\u00020\u0004\"\b\b\u0000\u0010\u0007*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH&J.\u0010\r\u001a\u00020\u0004\"\b\b\u0000\u0010\u0007*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\nH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeature;", "", "", "currentVideoPosition", "", "requestVideoPreviewAsset", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent;", "E", "Ljava/lang/Class;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEventListener;", "callback", DebugKt.DEBUG_PROPERTY_VALUE_ON, DebugKt.DEBUG_PROPERTY_VALUE_OFF, "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public interface VideoPreviewAssetFeature extends Feature {
    <E extends VideoPreviewAssetEvent> void off(Class<E> event, VideoPreviewAssetEventListener<E> callback);

    <E extends VideoPreviewAssetEvent> void on(Class<E> event, VideoPreviewAssetEventListener<E> callback);

    void requestVideoPreviewAsset(long currentVideoPosition);
}
