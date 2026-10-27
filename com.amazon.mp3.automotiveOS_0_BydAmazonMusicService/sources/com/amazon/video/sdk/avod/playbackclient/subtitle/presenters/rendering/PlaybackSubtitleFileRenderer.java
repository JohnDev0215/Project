package com.amazon.video.sdk.avod.playbackclient.subtitle.presenters.rendering;

import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager;
import com.amazon.avod.playbackclient.subtitle.presenters.rendering.SubtitleFileRendererBase;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class PlaybackSubtitleFileRenderer extends SubtitleFileRendererBase {
    private final VideoPlayer mVideoPlayer;

    PlaybackSubtitleFileRenderer(@Nonnull VideoPlayer videoPlayer, @Nonnull SubtitleTextController subtitleTextController, @Nonnull SubtitleCollectionManager subtitleCollectionManager, @Nullable String str) {
        super(subtitleTextController, subtitleCollectionManager, str);
        this.mVideoPlayer = (VideoPlayer) Preconditions.checkNotNull(videoPlayer, "videoPlayer");
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.rendering.SubtitleFileRendererBase
    protected long getCurrentVideoPosition() {
        return this.mVideoPlayer.getCurrentPosition();
    }
}
