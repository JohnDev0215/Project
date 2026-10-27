package com.amazon.mp3.video.stories.videostoryplayback;

import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;

/* JADX INFO: compiled from: VideoPlayer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0016J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH&J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\bH&J\u001f\u0010\u0011\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0013H&¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer;", "", "", PageUriUtils.DO_PLAY_VALUE, "pause", "", "mute", "toggleMute", "", "queuePosition", "", "uri", "prepareQueueItem", "seekPos", "seekPosition", "T", "contentView", "setContentView", "(Ljava/lang/Object;)V", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer$Callback;", "callback", "setCallback", "Callback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface VideoPlayer {

    /* JADX INFO: compiled from: VideoPlayer.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0018\u0010\f\u001a\u00020\u00032\u000e\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/video/stories/videostoryplayback/VideoPlayer$Callback;", "", "onContentLoading", "", "onContentReady", "onPlaybackFinished", "onPlaybackPositionChanged", "queuePosition", "", "playbackPosition", "", "onPlaybackStarted", "onPlayerError", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Callback {
        void onContentLoading();

        void onContentReady();

        void onPlaybackFinished();

        void onPlaybackPositionChanged(int queuePosition, long playbackPosition);

        void onPlaybackStarted();

        void onPlayerError(Exception error);
    }

    void pause();

    void play();

    void prepareQueueItem(int queuePosition, String uri);

    void seekPosition(int seekPos);

    void setCallback(Callback callback);

    <T> void setContentView(T contentView);

    void toggleMute(boolean mute);
}
