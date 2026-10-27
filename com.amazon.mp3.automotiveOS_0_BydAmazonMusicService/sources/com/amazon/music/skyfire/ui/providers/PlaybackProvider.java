package com.amazon.music.skyfire.ui.providers;

import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.SetRepeatStateMethod;
import ExternalActionInterface.v1_0.SetShuffleStateMethod;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.Video;
import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.skyfire.ui.callback.PlaybackViewCallback;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J \u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001e\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H&J&\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00172\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001e\u0010 \u001a\u00020\u00032\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u00192\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\"\u001a\u00020\u0003H&J\b\u0010#\u001a\u00020\u0003H&J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u001fH&J0\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u00152\b\u0010(\u001a\u0004\u0018\u00010\u00152\b\u0010)\u001a\u0004\u0018\u00010\u00152\b\u0010*\u001a\u0004\u0018\u00010\u0015H&J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020-H&J\b\u0010.\u001a\u00020\u0003H&J\b\u0010/\u001a\u00020\u0003H&J \u00100\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u00101\u001a\u00020\u0003H&¨\u00062"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "", "changeRepeatMode", "", "state", "LExternalActionInterface/v1_0/SetRepeatStateMethod$State;", "changeShuffleMode", "LExternalActionInterface/v1_0/SetShuffleStateMethod$State;", "nextTrack", "pause", PageUriUtils.DO_PLAY_VALUE, "playableContent", "LExternalActionInterface/v1_0/PlayableContent;", "context", "Landroid/content/Context;", "playArtistSpotlight", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "callback", "Lcom/amazon/music/skyfire/ui/callback/PlaybackViewCallback;", ImagesContract.URL, "", "playTracks", "Landroidx/fragment/app/FragmentActivity;", "tracks", "", "LExternalActionInterface/v1_0/Track;", "playVideoStories", "videos", "LExternalActionInterface/v1_0/Video;", "startingIndex", "", "prefetchVideoStoriesForPlayback", "playableContents", "previousTrack", "resume", "seekTo", "duration", "setNowPlayingScreenMetadata", "title", "primarySubtitle", "secondarySubtitle", "artworkUrl", "setPlaybackSpeed", "speed", "", "skipBackward", "skipForward", "startPlaybackTemplate", "stopPlaybackAndClearQueue", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface PlaybackProvider {

    /* JADX INFO: compiled from: PlaybackProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void playArtistSpotlight(PlaybackProvider playbackProvider, Activity activity, PlaybackViewCallback callback, String url) {
            Intrinsics.checkNotNullParameter(playbackProvider, "this");
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Intrinsics.checkNotNullParameter(url, "url");
        }

        public static void playVideoStories(PlaybackProvider playbackProvider, FragmentActivity activity, List<? extends Video> videos, int i) {
            Intrinsics.checkNotNullParameter(playbackProvider, "this");
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(videos, "videos");
        }

        public static void prefetchVideoStoriesForPlayback(PlaybackProvider playbackProvider, List<? extends PlayableContent> playableContents, String url) {
            Intrinsics.checkNotNullParameter(playbackProvider, "this");
            Intrinsics.checkNotNullParameter(playableContents, "playableContents");
            Intrinsics.checkNotNullParameter(url, "url");
        }

        public static void startPlaybackTemplate(PlaybackProvider playbackProvider, Activity activity, PlaybackViewCallback callback, PlayableContent playableContent) {
            Intrinsics.checkNotNullParameter(playbackProvider, "this");
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Intrinsics.checkNotNullParameter(playableContent, "playableContent");
        }
    }

    void changeRepeatMode(SetRepeatStateMethod.State state);

    void changeShuffleMode(SetShuffleStateMethod.State state);

    void nextTrack();

    void pause();

    void play(PlayableContent playableContent, Context context);

    void playArtistSpotlight(Activity activity, PlaybackViewCallback callback, String url);

    void playTracks(FragmentActivity activity, List<? extends Track> tracks);

    void playVideoStories(FragmentActivity activity, List<? extends Video> videos, int startingIndex);

    void prefetchVideoStoriesForPlayback(List<? extends PlayableContent> playableContents, String url);

    void previousTrack();

    void resume();

    void seekTo(int duration);

    void setNowPlayingScreenMetadata(String title, String primarySubtitle, String secondarySubtitle, String artworkUrl);

    void setPlaybackSpeed(float speed);

    void skipBackward();

    void skipForward();

    void startPlaybackTemplate(Activity activity, PlaybackViewCallback callback, PlayableContent playableContent);

    void stopPlaybackAndClearQueue();
}
