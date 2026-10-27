package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.ContainerModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.PlaylistShareProvider;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommunityPlaylistContextMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 (2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002)(B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b&\u0010'J\u001a\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010\n\u001a\u00020\tH\u0014J0\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0014J(\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006*"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "primePlaylist", "", "sharePlaylist", "", "getContextMenuLayoutId", "Landroid/app/Activity;", "Landroid/view/ContextMenu;", "menu", ParserUtil.TAG_QUERY_PARAM_NAME, "position", "", "sourceID", "updateContextMenuItems", "Landroid/view/MenuItem;", "item", "Landroid/widget/AdapterView$AdapterContextMenuInfo;", "menuInfo", "", "onContextMenuItemSelected", "Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider$CommunityPlaylistContextMenuProviderListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider$CommunityPlaylistContextMenuProviderListener;", "getListener", "()Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider$CommunityPlaylistContextMenuProviderListener;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;", "contextMenuPageType", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;", "getContextMenuPageType", "()Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "playlistModelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider$CommunityPlaylistContextMenuProviderListener;Lcom/amazon/mp3/fragment/contextmenu/ContextMenuPageType;)V", "Companion", "CommunityPlaylistContextMenuProviderListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CommunityPlaylistContextMenuProvider extends ContextMenuProvider<Object> {
    private static final String TAG;
    private final ContextMenuPageType contextMenuPageType;
    private final CommunityPlaylistContextMenuProviderListener listener;
    private final PlaylistModelConfiguration playlistModelConfiguration;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.community_playlist_context;
    }

    public CommunityPlaylistContextMenuProvider(FragmentActivity activity, CommunityPlaylistContextMenuProviderListener listener, ContextMenuPageType contextMenuPageType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(contextMenuPageType, "contextMenuPageType");
        this.listener = listener;
        this.contextMenuPageType = contextMenuPageType;
        this.mActivity = activity;
        this.playlistModelConfiguration = new PlaylistModelConfiguration(null, 1, 0 == true ? 1 : 0);
        init(activity);
    }

    static {
        String simpleName = CommunityPlaylistContextMenuProvider.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "CommunityPlaylistContext…lass.java.getSimpleName()");
        TAG = simpleName;
    }

    /* JADX INFO: compiled from: CommunityPlaylistContextMenuProvider.kt */
    @Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\"\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H&J\"\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\nH&J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH&J \u0010\u0012\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0006H&J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider$CommunityPlaylistContextMenuProviderListener;", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "task", "", "isShuffle", "", "startPlaylistPlayback", "Lcom/amazon/mp3/library/provider/StateProvider$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "updatePlaylistStatus", "Lcom/amazon/mp3/library/provider/StateProvider;", "getPlaylistStateProvider", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "isDownload", "followAndDownloadPlaylist", "unFollowPlaylist", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static abstract class CommunityPlaylistContextMenuProviderListener {
        public abstract void followAndDownloadPlaylist(FragmentActivity activity, PrimePlaylist playlist, boolean isDownload);

        public abstract StateProvider<PrimePlaylist> getPlaylistStateProvider();

        public abstract void startPlaylistPlayback(PrimePlaylist playlist, GetPrimePlaylistTracksTask.Task task, boolean isShuffle);

        public abstract void unFollowPlaylist(FragmentActivity activity, PrimePlaylist playlist);

        public abstract void updatePlaylistStatus(PrimePlaylist playlist, StateProvider.Listener<PrimePlaylist> listener);

        public static /* synthetic */ void startPlaylistPlayback$default(CommunityPlaylistContextMenuProviderListener communityPlaylistContextMenuProviderListener, PrimePlaylist primePlaylist, GetPrimePlaylistTracksTask.Task task, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startPlaylistPlayback");
            }
            if ((i & 4) != 0) {
                z = false;
            }
            communityPlaylistContextMenuProviderListener.startPlaylistPlayback(primePlaylist, task, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void updatePlaylistStatus$default(CommunityPlaylistContextMenuProviderListener communityPlaylistContextMenuProviderListener, PrimePlaylist primePlaylist, StateProvider.Listener listener, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updatePlaylistStatus");
            }
            if ((i & 2) != 0) {
                listener = null;
            }
            communityPlaylistContextMenuProviderListener.updatePlaylistStatus(primePlaylist, listener);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(sourceID, "sourceID");
        PrimePlaylist primePlaylist = tag instanceof PrimePlaylist ? (PrimePlaylist) tag : null;
        if (primePlaylist == null) {
            return;
        }
        setStyledHeaderTitle(primePlaylist.getTitle(), menu);
        StateProvider<PrimePlaylist> playlistStateProvider = this.listener.getPlaylistStateProvider();
        CommunityPlaylistContextMenuProviderListener.updatePlaylistStatus$default(this.listener, primePlaylist, null, 2, null);
        if (!this.playlistModelConfiguration.shouldShowOnDemandCapability(primePlaylist)) {
            menu.removeItem(R.id.MenuPlaylistPlay);
            menu.removeItem(R.id.MenuPlaylistAddToPlayQueue);
            menu.removeItem(R.id.MenuPlaylistPlayNext);
            if (!ContainerModelConfiguration.shouldShowContextMenuShuffleButton$default(this.playlistModelConfiguration, primePlaylist, null, this.contextMenuPageType, 2, null)) {
                menu.removeItem(R.id.MenuPlaylistShuffle);
            }
        } else if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.MenuPlaylistAddToPlayQueue);
            menu.removeItem(R.id.MenuPlaylistPlayNext);
        }
        if (playlistStateProvider.isInLibrary(primePlaylist)) {
            menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
        } else {
            menu.removeItem(R.id.MenuPlaylistContextInLibrary);
        }
        if (!this.playlistModelConfiguration.shouldShowContextMenuDeleteFromLibrary(playlistStateProvider.isInLibrary(primePlaylist))) {
            menu.removeItem(R.id.MenuPlaylistContextDeleteFromLibrary);
        }
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
            menu.removeItem(R.id.MenuPlaylistContextInLibrary);
        }
        if (playlistStateProvider.isDownloading(primePlaylist)) {
            menu.removeItem(R.id.MenuPlaylistContextDownloaded);
            menu.removeItem(R.id.MenuPlaylistContextDownload);
        } else if (playlistStateProvider.isDownloaded(primePlaylist)) {
            menu.removeItem(R.id.MenuPlaylistContextDownload);
            menu.removeItem(R.id.MenuAlbumContextDownloading);
        } else {
            menu.removeItem(R.id.MenuPlaylistContextDownloaded);
            menu.removeItem(R.id.MenuAlbumContextDownloading);
        }
        if (this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(primePlaylist)) {
            StringUtil.dimTextColor(menu.findItem(R.id.MenuPlaylistContextDownload), this.mActivity);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(menuInfo, "menuInfo");
        Intrinsics.checkNotNullParameter(sourceID, "sourceID");
        Object tag = menuInfo.targetView.getTag();
        PrimePlaylist primePlaylist = tag instanceof PrimePlaylist ? (PrimePlaylist) tag : null;
        if (primePlaylist == null) {
            return false;
        }
        primePlaylist.setSource(sourceID);
        setClickedItemHolder(primePlaylist);
        setClickedEntityIdAndType(primePlaylist.getPlaylistId(), EntityIdType.CPL_PLAYLIST_ID);
        switch (item.getItemId()) {
            case R.id.MenuPlaylistAddToPlayQueue /* 2131427607 */:
                CommunityPlaylistContextMenuProviderListener.startPlaylistPlayback$default(this.listener, primePlaylist, GetPrimePlaylistTracksTask.Task.ADD_TO_PLAYQUEUE, false, 4, null);
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                break;
            case R.id.MenuPlaylistContextAddToLibrary /* 2131427611 */:
                this.listener.followAndDownloadPlaylist(activity, primePlaylist, false);
                sendMetricEvent(ActionType.ADD_TO_LIBRARY_OVERFLOW);
                break;
            case R.id.MenuPlaylistContextDeleteFromLibrary /* 2131427614 */:
                this.listener.unFollowPlaylist(activity, primePlaylist);
                sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
                break;
            case R.id.MenuPlaylistContextDownload /* 2131427615 */:
                if (this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(primePlaylist)) {
                    UpsellUtil.showBlockingUpsell(activity, primePlaylist.getPlaylistId(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
                } else {
                    this.listener.followAndDownloadPlaylist(activity, primePlaylist, true);
                }
                sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
                break;
            case R.id.MenuPlaylistPlay /* 2131427626 */:
                CommunityPlaylistContextMenuProviderListener.startPlaylistPlayback$default(this.listener, primePlaylist, GetPrimePlaylistTracksTask.Task.PLAYBACK_FULLSCREEN, false, 4, null);
                sendMetricEvent(ActionType.PLAY_NOW_OVERFLOW);
                break;
            case R.id.MenuPlaylistPlayNext /* 2131427627 */:
                CommunityPlaylistContextMenuProviderListener.startPlaylistPlayback$default(this.listener, primePlaylist, GetPrimePlaylistTracksTask.Task.PLAY_NEXT, false, 4, null);
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                break;
            case R.id.MenuPlaylistShuffle /* 2131427632 */:
                this.listener.startPlaylistPlayback(primePlaylist, GetPrimePlaylistTracksTask.Task.PLAYBACK_FULLSCREEN, true);
                sendMetricEvent(ActionType.SHUFFLE_ON_OVERFLOW);
                break;
            case R.id.MenuShare /* 2131427637 */:
                sharePlaylist(activity, primePlaylist);
                sendMetricEvent(ActionType.SHARE_OVERFLOW);
                break;
        }
        return false;
    }

    private final void sharePlaylist(FragmentActivity activity, PrimePlaylist primePlaylist) {
        Unit unit;
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        if (primePlaylist == null) {
            unit = null;
        } else {
            new PlaylistShareProvider(activity, musicDomain, primePlaylist.getPlaylistId(), primePlaylist.getTitle(), primePlaylist.getArtworkUri()).startShare();
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Log.error(TAG, "playlist was null");
        }
    }
}
