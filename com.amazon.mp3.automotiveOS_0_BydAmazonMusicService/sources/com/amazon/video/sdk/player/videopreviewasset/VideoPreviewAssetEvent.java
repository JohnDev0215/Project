package com.amazon.video.sdk.player.videopreviewasset;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPreviewAssetFeature.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent;", "", "()V", "VideoPreviewChange", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent$VideoPreviewChange;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class VideoPreviewAssetEvent {
    public /* synthetic */ VideoPreviewAssetEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private VideoPreviewAssetEvent() {
    }

    /* JADX INFO: compiled from: VideoPreviewAssetFeature.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent$VideoPreviewChange;", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetEvent;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAsset;", "videoPreviewAsset", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAsset;", "getVideoPreviewAsset", "()Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAsset;", "<init>", "(Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAsset;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class VideoPreviewChange extends VideoPreviewAssetEvent {
        private final VideoPreviewAsset videoPreviewAsset;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof VideoPreviewChange) && Intrinsics.areEqual(this.videoPreviewAsset, ((VideoPreviewChange) other).videoPreviewAsset);
        }

        public int hashCode() {
            return this.videoPreviewAsset.hashCode();
        }

        public String toString() {
            return "VideoPreviewChange(videoPreviewAsset=" + this.videoPreviewAsset + ')';
        }

        public final VideoPreviewAsset getVideoPreviewAsset() {
            return this.videoPreviewAsset;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VideoPreviewChange(VideoPreviewAsset videoPreviewAsset) {
            super(null);
            Intrinsics.checkNotNullParameter(videoPreviewAsset, "videoPreviewAsset");
            this.videoPreviewAsset = videoPreviewAsset;
        }
    }
}
