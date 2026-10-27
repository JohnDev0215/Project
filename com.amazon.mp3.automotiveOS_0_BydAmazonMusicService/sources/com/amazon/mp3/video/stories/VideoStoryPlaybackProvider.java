package com.amazon.mp3.video.stories;

import android.content.Context;
import com.amazon.mp3.video.stories.videostoryplayback.VideoPlayer;
import com.amazon.mp3.video.stories.videostoryplayback.VideoPlayerImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryPlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004R\u0016\u0010\f\u001a\u00020\u00068\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\b8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryPlaybackProvider;", "", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer$Callback;", "callback", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer;", "getPlayer", "Lcom/amazon/mp3/video/stories/VideoStoryAutoplayCoordinator;", "getVideoStoryAutoPlayCoordinator", "", "setPlayerCallback", "player", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer;", "autoplayCoordinator", "Lcom/amazon/mp3/video/stories/VideoStoryAutoplayCoordinator;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryPlaybackProvider {
    public static final VideoStoryPlaybackProvider INSTANCE = new VideoStoryPlaybackProvider();
    private static VideoStoryAutoplayCoordinator autoplayCoordinator;
    private static VideoPlayer player;

    private VideoStoryPlaybackProvider() {
    }

    public final VideoPlayer getPlayer(Context context, VideoPlayer.Callback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (player == null) {
            player = new VideoPlayerImpl(context);
        }
        VideoPlayer videoPlayer = player;
        if (videoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            videoPlayer = null;
        }
        videoPlayer.setCallback(callback);
        VideoPlayer videoPlayer2 = player;
        if (videoPlayer2 != null) {
            return videoPlayer2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("player");
        return null;
    }

    public final VideoStoryAutoplayCoordinator getVideoStoryAutoPlayCoordinator() {
        if (player == null) {
            autoplayCoordinator = new VideoStoryAutoplayCoordinator();
        }
        VideoStoryAutoplayCoordinator videoStoryAutoplayCoordinator = autoplayCoordinator;
        if (videoStoryAutoplayCoordinator != null) {
            return videoStoryAutoplayCoordinator;
        }
        Intrinsics.throwUninitializedPropertyAccessException("autoplayCoordinator");
        return null;
    }

    public final void setPlayerCallback(VideoPlayer.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        VideoPlayer videoPlayer = player;
        if (videoPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            videoPlayer = null;
        }
        videoPlayer.setCallback(callback);
    }
}
