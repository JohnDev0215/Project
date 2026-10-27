package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.task.FollowAndDownloadPrimePlaylistTask;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.MathUtil;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.PlaylistShareProvider;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistContextMenuProvider extends ContextMenuProvider<PrimePlaylist> implements ContentAccessUtil.CatalogActionListener<PrimePlaylist> {
    private ContextMenuPageType contextMenuPageType;
    protected PrimePlaylistContextMenuProviderListener mProviderListener;
    private PlaylistModelConfiguration playlistModelConfiguration = new PlaylistModelConfiguration();

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.prime_playlist_context;
    }

    public static abstract class PrimePlaylistContextMenuProviderListener {
        public abstract PlaybackPageType getPlaybackPageType();

        public abstract StateProvider<PrimePlaylist> getPlaylistStateProvider();

        public abstract void startPlaylistPlayback(PrimePlaylist playlist);

        public abstract void updatePlaylistStatus(PrimePlaylist playlist);

        public boolean canContinueAction(FragmentActivity activity, PrimePlaylist primePlaylist, ContentAccessUtil.ContentAccessOperation operation) {
            return ContentAccessUtil.canContinueCatalogAction(activity, primePlaylist, operation);
        }
    }

    public PrimePlaylistContextMenuProvider(final FragmentActivity activity, final PrimePlaylistContextMenuProviderListener listener, final ContextMenuPageType contextMenuPageType) {
        this.mProviderListener = listener;
        this.contextMenuPageType = contextMenuPageType;
        init(activity);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(final PrimePlaylist playlist, final boolean canContinue) {
        if (canContinue) {
            playbackPlaylist(playlist);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimePlaylist) menuInfo.targetView.getTag(), sourceID);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimePlaylist) tag, sourceID);
    }

    public boolean onContextMenuItemSelected(final FragmentActivity activity, MenuItem item, final PrimePlaylist playlist, String sourceID) {
        setClickedItemHolder(playlist);
        if (playlist.getAsin() != null) {
            setClickedEntityIdAndType(playlist.getAsin(), EntityIdType.ASIN);
        } else {
            setClickedEntityIdAndType(Long.toString(playlist.getId().longValue()), EntityIdType.PLAYLIST_ID);
        }
        if (playlist.getBlockRef() == null) {
            setContentInfo(MetricsUtil.getContentInfoForLibraryContent(-1, MetricsUtil.getContentNameForSearchItem(playlist, isOfflineLibrary(sourceID))));
        }
        switch (item.getItemId()) {
            case R.id.MenuPlaylistAddToPlayQueue /* 2131427607 */:
                addToPlayQueue(activity, playlist, false);
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                return true;
            case R.id.MenuPlaylistAddToPresets /* 2131427608 */:
                CarModePresetsUtility.addToPresets(activity, playlist, getPageType());
                return true;
            case R.id.MenuPlaylistContextAddToLibrary /* 2131427611 */:
                if (canFollowPlaylist(activity, playlist)) {
                    addToLibrary(activity, playlist);
                }
                sendMetricEvent(ActionType.ADD_TO_LIBRARY_OVERFLOW);
                return true;
            case R.id.MenuPlaylistContextDeleteFromLibrary /* 2131427614 */:
                deletePlaylist(activity, playlist);
                sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
                return true;
            case R.id.MenuPlaylistContextDownload /* 2131427615 */:
                if (this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(playlist)) {
                    UpsellUtil.showBlockingUpsell(activity, playlist.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
                    return true;
                }
                PrimeContentUtil.checkOfflineAccess(playlist, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider.1
                    @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                    public void resultReceived(final boolean contentAvailable, final ContentUnavailableReason reasonUnavailable) {
                        if (PrimePlaylistContextMenuProvider.this.mProviderListener.canContinueAction(activity, playlist, ContentAccessUtil.ContentAccessOperation.DOWNLOAD)) {
                            PrimePlaylistContextMenuProvider.this.downloadPlaylist(activity, playlist);
                        }
                    }
                });
                sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
                return true;
            case R.id.MenuPlaylistContextShare /* 2131427620 */:
                new PlaylistShareProvider(activity, EndpointsProvider.get().getMusicDomain(), playlist.getAsin(), playlist.getTitle(), playlist.getArtworkUri()).startShare();
                return true;
            case R.id.MenuPlaylistPlay /* 2131427626 */:
                ContentAccessUtil.checkCanContinueCatalogAction(activity, playlist, ContentAccessUtil.ContentAccessOperation.STREAM, this);
                sendMetricEvent(ActionType.PLAY_NOW_OVERFLOW);
                return true;
            case R.id.MenuPlaylistPlayNext /* 2131427627 */:
                addToPlayQueue(activity, playlist, true);
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                return true;
            case R.id.MenuPlaylistRemoveFromPresets /* 2131427629 */:
                CarModePresetsUtility.removeFromPresets(activity, playlist, getPageType());
                return true;
            case R.id.MenuPlaylistShuffle /* 2131427632 */:
                GetPrimePlaylistTracksTask getPrimePlaylistTracksTaskCreatePlaybackTask = GetPrimePlaylistTracksTask.createPlaybackTask(activity, playlist.getAsin(), MathUtil.INSTANCE.generateRandomStartIndex(playlist.getTrackCount()), PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST, GetPrimePlaylistTracksTask.Task.PLAYBACK_FULLSCREEN);
                getPrimePlaylistTracksTaskCreatePlaybackTask.withShuffleState(true);
                getPrimePlaylistTracksTaskCreatePlaybackTask.execute(new Void[0]);
                sendMetricEvent(ActionType.SHUFFLE_ON_OVERFLOW);
                return true;
            case R.id.MenuPlaylistSimilarRecommend /* 2131427633 */:
                if (this.mProviderListener.canContinueAction(activity, playlist, ContentAccessUtil.ContentAccessOperation.BROWSE)) {
                    goToSimilarRecommendationsFragment(PaginatedDataType.PLAYLIST, playlist.getAsin());
                }
                sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
                return true;
            case R.id.MenuPlaylistStationFromAnything /* 2131427634 */:
                if (ContentAccessUtil.canContinueCatalogAction(activity, playlist, ContentAccessUtil.ContentAccessOperation.STREAM)) {
                    StationUtils.startSFA(activity, playlist.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLISTS_LIST, ControlSource.APP_UI);
                }
                StationUtils.sendStartSFAUiClickMetrics(playlist.getAsin(), EntityIdType.SFA_PLAYLIST_SEED);
                return true;
            default:
                return false;
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        PrimePlaylist primePlaylist = (PrimePlaylist) tag;
        StateProvider<PrimePlaylist> playlistStateProvider = this.mProviderListener.getPlaylistStateProvider();
        this.mProviderListener.updatePlaylistStatus(primePlaylist);
        setStyledHeaderTitle(primePlaylist.getTitle(), menu);
        menu.removeItem(R.id.MenuPlaylistSimilarRecommend);
        if (!this.playlistModelConfiguration.shouldShowContextMenuPlayButton(primePlaylist, this.contextMenuPageType)) {
            menu.removeItem(R.id.MenuPlaylistPlay);
        }
        updatePresetsContextMenu(activity, primePlaylist, menu, R.id.MenuPlaylistAddToPresets, R.id.MenuPlaylistRemoveFromPresets);
        if (!this.playlistModelConfiguration.shouldShowContextMenuShuffleButton(primePlaylist, null, this.contextMenuPageType)) {
            menu.removeItem(R.id.MenuPlaylistShuffle);
        }
        if (playlistStateProvider.isInLibrary(primePlaylist)) {
            menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
        } else {
            menu.removeItem(R.id.MenuPlaylistContextInLibrary);
        }
        if (!this.playlistModelConfiguration.shouldShowContextMenuDeleteFromLibrary(playlistStateProvider.isInLibrary(primePlaylist))) {
            menu.removeItem(R.id.MenuPlaylistContextDeleteFromLibrary);
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
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(primePlaylist);
        if (!SequencerHelper.canModifyQueue() || playCatalogContentUnavailableReason != null) {
            menu.removeItem(R.id.MenuPlaylistAddToPlayQueue);
            menu.removeItem(R.id.MenuPlaylistPlayNext);
        }
        menu.removeItem(R.id.MenuPlaylistCatalogStatus);
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            menu.removeItem(R.id.MenuPlaylistSimilarRecommend);
        }
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
            menu.removeItem(R.id.MenuPlaylistContextInLibrary);
        }
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuPlaylistContextDownload), this.mActivity);
    }

    private void playbackPlaylist(PrimePlaylist playlist) {
        if (playlist.getAsin() == null) {
            startPlayback(playlist);
        } else {
            this.mProviderListener.startPlaylistPlayback(playlist);
        }
    }

    private boolean canFollowPlaylist(final FragmentActivity activity, final PrimePlaylist playlist) {
        if (AmazonApplication.getCapabilities().canFreeTierUserFollowPlaylists()) {
            return true;
        }
        return this.mProviderListener.canContinueAction(activity, playlist, ContentAccessUtil.ContentAccessOperation.INTERACT);
    }

    private void addToLibrary(FragmentActivity activity, PrimePlaylist playlist) {
        new FollowAndDownloadPrimePlaylistTask(activity, playlist, false).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadPlaylist(FragmentActivity activity, PrimePlaylist playlist) {
        if (!this.mProviderListener.getPlaylistStateProvider().isInLibrary(playlist)) {
            new FollowAndDownloadPrimePlaylistTask(activity, playlist, true).execute(new Void[0]);
        } else {
            Uri contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getAsin());
            ContextMenuDownloadComponent.startDownload(activity, contentUri.toString(), contentUri, new NotificationInfo(playlist.getTitle(), playlist.getDescription(), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), playlist.getAsin()).toString()));
        }
        this.mProviderListener.updatePlaylistStatus(playlist);
    }

    public void startPlayback(PrimePlaylist playlist) {
        if (playlist == null) {
            return;
        }
        Uri contentUri = MediaProvider.PrimePlaylists.getContentUri("cirrus", playlist.getAsin());
        ContextMenuPlaybackComponent.startPlayback(this.mActivity, contentUri, 0, "order_num", null, this.mProviderListener.getPlaybackPageType(), Clock.now(), true, false, contentUri);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public <D extends SearchItem & CatalogContent> void addToPlayQueue(final D playlist, final boolean playNext) {
        PrimePlaylistContextMenuUtil.INSTANCE.addTracksToQueue(this.mActivity, playlist, playNext, this.mProviderListener.getPlaylistStateProvider());
    }

    private void deletePlaylist(FragmentActivity activity, PrimePlaylist playlist) {
        Uri contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getAsin());
        AbstractItem playlist2 = AmazonApplication.getLibraryItemFactory().getPlaylist(contentUri);
        if (playlist2 == null) {
            playlist2 = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(contentUri);
        }
        if (playlist2 instanceof Playlist) {
            new DeleteUserPlaylistAction(activity, playlist2.getContentUri(), ((Playlist) playlist2).getName()).perform();
        } else if (playlist2 instanceof CatalogPlaylist) {
            new PlaylistUnfollowAction(activity, playlist2.getAsin(), ((CatalogPlaylist) playlist2).getLuid(), CirrusMediaSource.matchCloud(playlist2.getContentUri())).unfollowPlaylist();
        }
    }
}
