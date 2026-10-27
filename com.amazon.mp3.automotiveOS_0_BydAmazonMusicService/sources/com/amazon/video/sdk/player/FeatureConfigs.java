package com.amazon.video.sdk.player;

import com.amazon.mp3.environment.Environment;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureConfig;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureConfig;
import com.amazon.video.sdk.stream.StreamFeatureConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: Player.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/video/sdk/player/FeatureConfigs;", "", Environment.PLAYLIST_PATH, "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureConfig;", "getPlaylist", "()Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureConfig;", "stream", "Lcom/amazon/video/sdk/stream/StreamFeatureConfig;", "getStream", "()Lcom/amazon/video/sdk/stream/StreamFeatureConfig;", "videoPreviewAsset", "Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureConfig;", "getVideoPreviewAsset", "()Lcom/amazon/video/sdk/player/videopreviewasset/VideoPreviewAssetFeatureConfig;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface FeatureConfigs {
    PlaylistFeatureConfig getPlaylist();

    StreamFeatureConfig getStream();

    VideoPreviewAssetFeatureConfig getVideoPreviewAsset();
}
