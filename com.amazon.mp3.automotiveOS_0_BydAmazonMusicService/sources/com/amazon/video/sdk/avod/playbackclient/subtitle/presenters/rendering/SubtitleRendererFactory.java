package com.amazon.video.sdk.avod.playbackclient.subtitle.presenters.rendering;

import com.amazon.avod.media.playback.VideoPlayer;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playbackclient.subtitle.internal.AggregatingSubtitleCollectionManager;
import com.amazon.avod.playbackclient.subtitle.internal.SingleSubtitleCollectionManager;
import com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer;
import com.amazon.avod.playbackclient.subtitle.presenters.rendering.SubtitleStreamingRenderer;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.amazon.video.sdk.avod.playbackclient.utils.SubtitlesLanguageHelper;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SubtitleRendererFactory {
    private SubtitleRendererFactory() {
    }

    public static SubtitleRenderer createSubtitleRenderer(@Nonnull VideoPlayer videoPlayer, @Nonnull SubtitleTextController subtitleTextController, @Nonnull PlaybackExperienceController playbackExperienceController, @Nullable String str) {
        Preconditions.checkNotNull(videoPlayer, "videoPlayer");
        Preconditions.checkNotNull(subtitleTextController, "subtitleTextController");
        Preconditions.checkNotNull(playbackExperienceController, "playbackExperienceController");
        if (SubtitlesLanguageHelper.isStreamingSubtitlesSupported(playbackExperienceController)) {
            return new SubtitleStreamingRenderer(new PlaybackSubtitleFileRenderer(videoPlayer, subtitleTextController, new AggregatingSubtitleCollectionManager(), str), playbackExperienceController);
        }
        return new PlaybackSubtitleFileRenderer(videoPlayer, subtitleTextController, new SingleSubtitleCollectionManager(), str);
    }
}
