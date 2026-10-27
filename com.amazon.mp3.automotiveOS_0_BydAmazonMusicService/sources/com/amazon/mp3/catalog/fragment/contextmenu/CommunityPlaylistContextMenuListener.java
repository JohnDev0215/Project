package com.amazon.mp3.catalog.fragment.contextmenu;

import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider;
import com.amazon.mp3.library.adapter.DefaultPlaylistStateProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.task.FollowAndDownloadPrimePlaylistTask;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.PlaybackUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommunityPlaylistContextMenuListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\"\u0010#J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J \u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016J \u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00032\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fH\u0016J \u0010\u0012\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0003H\u0016R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010!\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/contextmenu/CommunityPlaylistContextMenuListener;", "Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider$CommunityPlaylistContextMenuProviderListener;", "Lcom/amazon/mp3/library/provider/StateProvider;", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "getPlaylistStateProvider", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "task", "", "isShuffle", "", "startPlaylistPlayback", "Lcom/amazon/mp3/library/provider/StateProvider$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "updatePlaylistStatus", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "isDownload", "followAndDownloadPlaylist", "unFollowPlaylist", "Ljava/lang/ref/WeakReference;", "activityWeakReference", "Ljava/lang/ref/WeakReference;", "getActivityWeakReference", "()Ljava/lang/ref/WeakReference;", "Lcom/amazon/mp3/library/adapter/DefaultPlaylistStateProvider;", "defaultPlaylistStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultPlaylistStateProvider;", "getDefaultPlaylistStateProvider", "()Lcom/amazon/mp3/library/adapter/DefaultPlaylistStateProvider;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getPlaybackPageType", "()Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "<init>", "(Landroidx/fragment/app/FragmentActivity;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CommunityPlaylistContextMenuListener extends CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener {
    private final WeakReference<FragmentActivity> activityWeakReference;
    private final DefaultPlaylistStateProvider defaultPlaylistStateProvider;

    public CommunityPlaylistContextMenuListener(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activityWeakReference = new WeakReference<>(activity);
        this.defaultPlaylistStateProvider = new DefaultPlaylistStateProvider(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener
    public StateProvider<PrimePlaylist> getPlaylistStateProvider() {
        return this.defaultPlaylistStateProvider;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener
    public void startPlaylistPlayback(PrimePlaylist playlist, GetPrimePlaylistTracksTask.Task task, boolean isShuffle) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(task, "task");
        FragmentActivity fragmentActivity = this.activityWeakReference.get();
        if (fragmentActivity == null) {
            return;
        }
        PlaybackUtils.startCommunityPlaylistPlayback(fragmentActivity, playlist, getPlaybackPageType(), task, isShuffle);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener
    public void updatePlaylistStatus(PrimePlaylist playlist, StateProvider.Listener<PrimePlaylist> listener) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        this.defaultPlaylistStateProvider.requestState(playlist, listener);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener
    public void followAndDownloadPlaylist(FragmentActivity activity, PrimePlaylist playlist, boolean isDownload) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        new FollowAndDownloadPrimePlaylistTask(activity, playlist, isDownload).execute(new Void[0]);
        CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener.updatePlaylistStatus$default(this, playlist, null, 2, null);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener
    public void unFollowPlaylist(FragmentActivity activity, PrimePlaylist playlist) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        CatalogPlaylist catalogPlaylist = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getPlaylistId()));
        if (catalogPlaylist instanceof CatalogPlaylist) {
            PlaylistUnfollowAction.unfollowPlaylist$default(new PlaylistUnfollowAction(activity, catalogPlaylist.getAsin(), catalogPlaylist.getLuid(), CirrusMediaSource.matchCloud(catalogPlaylist.getContentUri())), null, 1, null);
        }
    }

    public PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.PRIME_BROWSE_SEARCH;
    }
}
