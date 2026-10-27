package com.amazon.music.explore.videoqueueplayback;

import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement;
import Touch.WidgetsInterface.v1_0.VideoCarouselElement;
import android.content.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class VideoQueuePlayerManager {
    private final Callback callback;
    private final Context context;
    private VideoCarouselElement videoCarouselElement;
    private final VideoPlayer videoPlayer;
    private VideoQueueElement videoQueueElement;
    private final VideoQueuePlayback videoQueuePlayback;
    private final Logger logger = LoggerFactory.getLogger("VideoQueuePlayer");
    private int currentPosition = -1;
    private int currentPreviewPosition = -1;

    public interface Callback {
        void onContentLoading();

        void onContentReady();

        void onPlaybackFinished();

        void onPlaybackPositionChanged(int i, long j);

        void onPlaybackStarted();
    }

    public VideoQueuePlayerManager(Context context, Callback callback, VideoQueuePlayback videoQueuePlayback) {
        this.context = context;
        this.callback = callback;
        this.videoQueuePlayback = videoQueuePlayback;
        this.videoPlayer = new VideoPlayer(context, callback);
    }

    public void playQueuePosition(int i) {
        this.logger.info("playing queue at position {}", Integer.valueOf(i));
        this.currentPosition = i;
        VideoStoryContent videoStoryContent = (VideoStoryContent) this.videoQueueElement.videoItems().get(i).playableContent();
        this.videoPlayer.pause();
        this.videoPlayer.setTextureView(null);
        this.videoPlayer.prepareQueueItem(i, videoStoryContent.uri());
        this.videoPlayer.setTextureView(this.videoQueuePlayback.getTextureView(i));
        this.videoPlayer.play();
    }

    public void prepareQueue(VideoQueueElement videoQueueElement) {
        this.logger.info("preparing queue");
        this.videoQueueElement = videoQueueElement;
        VideoStoryContent videoStoryContent = (VideoStoryContent) videoQueueElement.videoItems().get(0).playableContent();
        if (this.currentPosition < 0) {
            this.currentPosition = 0;
            this.videoPlayer.prepareQueueItem(0, videoStoryContent.uri());
        }
    }

    public void playPreviewQueuePosition(int i) {
        String strIntroUri;
        this.currentPreviewPosition = i;
        this.videoPlayer.pause();
        this.videoPlayer.setTextureView(null);
        if (i == 0) {
            strIntroUri = this.videoCarouselElement.artworkIntro();
        } else if (i == 1) {
            strIntroUri = this.videoCarouselElement.artwork();
        } else {
            strIntroUri = ((VideoStoryContent) this.videoCarouselElement.items().get(i - 2).content()).introUri();
        }
        this.videoPlayer.prepareQueueItem(i, strIntroUri);
        this.videoPlayer.setTextureView(this.videoQueuePlayback.getTextureView(i));
        this.videoPlayer.play();
    }

    public void preparePreviewQueue(VideoCarouselElement videoCarouselElement) {
        this.videoCarouselElement = videoCarouselElement;
        if (this.currentPreviewPosition < 0) {
            this.currentPreviewPosition = 0;
            this.videoPlayer.prepareQueueItem(0, videoCarouselElement.artworkIntro());
        }
        this.videoPlayer.mute();
    }

    public int getDuration() {
        return this.videoPlayer.getDuration();
    }

    public void play() {
        this.videoPlayer.play();
    }

    public void pause() {
        this.videoPlayer.pause();
    }

    public void shutdown() {
        this.videoPlayer.shutdown();
    }
}
