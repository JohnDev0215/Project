package com.amazon.mp3.catalog.fragment.contextmenu;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.fragment.contextmenu.VideoPlaylistContextMenuProvider;
import com.amazon.mp3.library.adapter.DefaultPlaylistStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaylistContextMenuListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/contextmenu/VideoPlaylistContextMenuListener;", "Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider$VideoPlaylistContextMenuProviderListener;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "defaultPlaylistStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultPlaylistStateProvider;", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getPlaylistStateProvider", "Lcom/amazon/mp3/library/provider/StateProvider;", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "startPlaylistPlayback", "", "videoPlaylist", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPlaylistContextMenuListener extends VideoPlaylistContextMenuProvider.VideoPlaylistContextMenuProviderListener {
    private final FragmentActivity activity;
    private final DefaultPlaylistStateProvider defaultPlaylistStateProvider;

    public VideoPlaylistContextMenuListener(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.defaultPlaylistStateProvider = new DefaultPlaylistStateProvider(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.VideoPlaylistContextMenuProvider.VideoPlaylistContextMenuProviderListener
    public void startPlaylistPlayback(PrimePlaylist videoPlaylist) {
        Intrinsics.checkNotNullParameter(videoPlaylist, "videoPlaylist");
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        if (NowPlayingUtil.isVideoPlaying() && controller.getPlayStatus() == PlayStatus.RENDERING) {
            controller.stop(ChangeReason.NEW_SOURCE);
        }
        GetPrimePlaylistTracksTask.createPlaybackTask(this.activity, videoPlaylist.getAsin(), getPlaybackPageType(), true).execute(new Void[0]);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.VideoPlaylistContextMenuProvider.VideoPlaylistContextMenuProviderListener
    public StateProvider<PrimePlaylist> getPlaylistStateProvider() {
        return this.defaultPlaylistStateProvider;
    }

    public PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST;
    }
}
