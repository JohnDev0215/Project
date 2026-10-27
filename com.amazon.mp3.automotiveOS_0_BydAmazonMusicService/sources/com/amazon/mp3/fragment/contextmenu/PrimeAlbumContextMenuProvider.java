package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.fragment.AddAndDownloadAlbumTask;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.MathUtil;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.AlbumShareProvider;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeAlbumContextMenuProvider extends ContextMenuProvider<PrimeAlbum> implements ContentAccessUtil.CatalogActionListener<PrimeAlbum> {
    private AlbumModelConfiguration albumModelConfiguration = new AlbumModelConfiguration();
    protected PrimeAlbumContextMenuProviderListener mProviderListener;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.prime_album_context;
    }

    public static abstract class PrimeAlbumContextMenuProviderListener {
        public abstract void addAlbumForPlaylist(PrimeAlbum album);

        public abstract StateProvider<PrimeAlbum> getAlbumStateProvider();

        public abstract ContextMenuPageType getContextMenuPageType();

        public abstract void removeDownloadAlbumMenuItem(ContextMenu menu);

        public abstract void startAlbumPlayback(PrimeAlbum album);

        public abstract void updateAlbumStatus(PrimeAlbum album);

        public abstract void updateAlbumStatusForPlaylist(PrimeAlbum album);

        public boolean canContinueAction(FragmentActivity activity, PrimeAlbum primeAlbum, ContentAccessUtil.ContentAccessOperation operation) {
            return ContentAccessUtil.canContinueCatalogAction(activity, primeAlbum, operation);
        }
    }

    public PrimeAlbumContextMenuProvider(FragmentActivity activity, PrimeAlbumContextMenuProviderListener listener) {
        this.mProviderListener = listener;
        init(activity);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(final PrimeAlbum album, final boolean canContinue) {
        if (canContinue) {
            this.mProviderListener.startAlbumPlayback(album);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimeAlbum) menuInfo.targetView.getTag(), sourceID);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimeAlbum) tag, sourceID);
    }

    public boolean onContextMenuItemSelected(final FragmentActivity activity, MenuItem item, final PrimeAlbum album, String sourceID) {
        setClickedItemHolder(album);
        String asin = album.getAsin();
        EntityIdType entityIdType = EntityIdType.ASIN;
        setClickedEntityIdAndType(asin, entityIdType);
        if (album.getAsin() != null) {
            setClickedEntityIdAndType(album.getAsin(), entityIdType);
        } else {
            setClickedEntityIdAndType(Long.toString(album.getId().longValue()), EntityIdType.CD_OBJECT_ID);
        }
        if (album.getBlockRef() == null) {
            setContentInfo(MetricsUtil.getContentInfoForLibraryContent(-1, MetricsUtil.getContentNameForSearchItem(album, isOfflineLibrary(sourceID))));
        }
        switch (item.getItemId()) {
            case R.id.MenuAlbumAddToPlayQueue /* 2131427534 */:
                addToPlayQueue(activity, album, false);
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                return true;
            case R.id.MenuAlbumAddToPlaylist /* 2131427535 */:
                if (this.mProviderListener.canContinueAction(activity, album, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
                    addToPlaylist(activity, album);
                }
                sendMetricEvent(ActionType.ADD_ALL_TO_PLAYLIST_ATTEMPT_OVERFLOW);
                return true;
            case R.id.MenuAlbumAddToPresets /* 2131427536 */:
                CarModePresetsUtility.addToPresets(activity, album, getPageType());
                return true;
            case R.id.MenuAlbumContextAddToLibrary /* 2131427537 */:
                if (Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
                    UpsellUtil.showBlockingUpsell(activity, album.getAsin(), UpsellReason.ADD_TO_LIBRARY, UpsellSourceEntity.ALBUM);
                } else if (this.mProviderListener.canContinueAction(activity, album, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
                    addToLibrary(activity, album);
                    String asin2 = album.getAsin();
                    MetricsLogger.clickedAddPrimeContent(LinkType.ALBUM, asin2, SelectionSourceType.ALBUM, asin2);
                }
                sendMetricEvent(ActionType.ADD_TO_LIBRARY_OVERFLOW);
                return true;
            case R.id.MenuAlbumContextDeleteFromLibrary /* 2131427541 */:
                sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
                new RemoveLibraryContentProvider(activity, null).removeAlbumFromCloud(AmazonApplication.getLibraryItemFactory().getAlbum(album.getContentUri()));
                return true;
            case R.id.MenuAlbumContextDownload /* 2131427542 */:
                if (this.albumModelConfiguration.shouldShowContextMenuDownloadUpsellButton(album)) {
                    UpsellUtil.showBlockingUpsell(activity, album.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.ALBUM);
                    return true;
                }
                PrimeContentUtil.checkOfflineAccess(album, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.1
                    @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                    public void resultReceived(final boolean contentAvailable, final ContentUnavailableReason reasonUnavailable) {
                        if (PrimeAlbumContextMenuProvider.this.mProviderListener.canContinueAction(activity, album, ContentAccessUtil.ContentAccessOperation.DOWNLOAD)) {
                            PrimeAlbumContextMenuProvider.this.downloadAlbum(activity, album);
                        }
                    }
                });
                sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
                return true;
            case R.id.MenuAlbumContextShare /* 2131427546 */:
                new AlbumShareProvider(activity, EndpointsProvider.get().getMusicDomain(), album.getAsin(), album.getTitle(), album.getArtist(), album.getArtworkUri() != null ? album.getArtworkUri() : new CTAPrimeCache(activity).getArtworkUrlForAsin(album.getAsin())).startShare();
                return true;
            case R.id.MenuAlbumGoToArtist /* 2131427547 */:
                if (this.mProviderListener.canContinueAction(activity, album, ContentAccessUtil.ContentAccessOperation.BROWSE)) {
                    FragmentActivity fragmentActivity = this.mActivity;
                    if (fragmentActivity instanceof MusicHomeActivity) {
                        BrushFragmentNavigation.navigateToArtistDetails((MusicHomeActivity) fragmentActivity, album.getArtist(), album.getArtistAsin());
                    }
                }
                sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW);
                return true;
            case R.id.MenuAlbumGoToEntityPlaylist /* 2131427548 */:
                goToEntityPlaylistFragment(album.getAsin(), PlaylistSeedEntityType.ALBUM);
                return true;
            case R.id.MenuAlbumPlayNext /* 2131427550 */:
                addToPlayQueue(activity, album, true);
                sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
                return true;
            case R.id.MenuAlbumRemoveFromPresets /* 2131427551 */:
                CarModePresetsUtility.removeFromPresets(activity, album, getPageType());
                return true;
            case R.id.MenuAlbumShuffle /* 2131427554 */:
                Album album2 = AmazonApplication.getLibraryItemFactory().getAlbum(album.getContentUri());
                int iGenerateRandomStartIndex = MathUtil.INSTANCE.generateRandomStartIndex(album2 != null ? (int) album2.getTrackCount() : 0);
                PrimeCollectionTask primeCollectionTaskCreatePlaybackTask = PrimeCollectionTask.createPlaybackTask(activity.getApplicationContext(), album.getAsin(), PlaybackPageType.PRIME_BROWSE_ALBUMS_LIST, new QueryAlbumByAsin(activity.getApplicationContext()), PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN);
                primeCollectionTaskCreatePlaybackTask.withStartIndex(iGenerateRandomStartIndex);
                primeCollectionTaskCreatePlaybackTask.execute();
                sendMetricEvent(ActionType.SHUFFLE_ON_OVERFLOW);
                return true;
            case R.id.MenuAlbumSimilarRecommend /* 2131427555 */:
                goToSimilarRecommendationsFragment(PaginatedDataType.ALBUM, album.getAsin());
                sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
                return true;
            case R.id.MenuAlbumStationFromAnything /* 2131427556 */:
                if (UserSubscriptionUtil.isNightwingOnly() || this.mProviderListener.canContinueAction(activity, album, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
                    StationUtils.startSFA(activity, album.getAsin(), "ALBUM_SEED", PlaybackPageType.ALBUMS_LIST, ControlSource.APP_UI);
                }
                StationUtils.sendStartSFAUiClickMetrics(album.getAsin(), EntityIdType.SFA_ALBUM_SEED);
                return true;
            case R.id.MenuExploreArtist /* 2131427578 */:
                if (ConnectivityUtil.checkConnectivityAndShowDialog(activity)) {
                    activity.startActivity(ShopLinkUtil.getArtistExploreIntent(activity, album.getArtistAsin(), album.getArtist(), ItemWrapper$ItemType.ALBUM));
                }
                sendMetricEvent(ActionType.SHOP_FOR_ARTIST_OVERFLOW);
                return true;
            case R.id.MenuPlayAlbumContext /* 2131427606 */:
                ContentAccessUtil.checkCanContinueCatalogAction(activity, album, ContentAccessUtil.ContentAccessOperation.STREAM, this);
                sendMetricEvent(ActionType.PLAY_NOW_OVERFLOW);
                return true;
            default:
                return false;
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        PrimeAlbum primeAlbum = (PrimeAlbum) tag;
        StateProvider<PrimeAlbum> albumStateProvider = this.mProviderListener.getAlbumStateProvider();
        this.mProviderListener.updateAlbumStatus(primeAlbum);
        setStyledHeaderTitle(primeAlbum.getTitle(), menu);
        menu.removeItem(R.id.MenuAlbumAddToPlaylist);
        if (ChildUserUtil.INSTANCE.isChildUser(activity.getBaseContext())) {
            menu.removeItem(R.id.MenuExploreArtist);
        }
        if (!this.albumModelConfiguration.shouldShowContextMenuShuffleButton(primeAlbum, null, this.mProviderListener.getContextMenuPageType())) {
            menu.removeItem(R.id.MenuAlbumShuffle);
        }
        if (TextUtils.isEmpty(primeAlbum.getAsin())) {
            menu.removeItem(R.id.MenuAlbumStationFromAnything);
            menu.removeItem(R.id.MenuAlbumGoToEntityPlaylist);
            menu.removeItem(R.id.MenuAlbumSimilarRecommend);
        }
        if (albumStateProvider.isInLibrary(primeAlbum)) {
            menu.removeItem(R.id.MenuAlbumContextAddToLibrary);
        } else {
            menu.removeItem(R.id.MenuAlbumContextInLibrary);
        }
        if (!this.albumModelConfiguration.shouldShowContextMenuDeleteFromLibrary(albumStateProvider.isInLibrary(primeAlbum))) {
            menu.removeItem(R.id.MenuAlbumContextDeleteFromLibrary);
        }
        if (AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuAlbumGoToArtist);
        }
        if (albumStateProvider.isDownloading(primeAlbum)) {
            menu.removeItem(R.id.MenuAlbumContextDownloaded);
            menu.removeItem(R.id.MenuAlbumContextDownload);
        } else if (albumStateProvider.isDownloaded(primeAlbum)) {
            menu.removeItem(R.id.MenuAlbumContextDownload);
            menu.removeItem(R.id.MenuAlbumContextDownloading);
        } else {
            menu.removeItem(R.id.MenuAlbumContextDownloaded);
            menu.removeItem(R.id.MenuAlbumContextDownloading);
        }
        if (this.albumModelConfiguration.shouldShowContextMenuDownloadUpsellButton(primeAlbum)) {
            StringUtil.dimTextColor(menu.findItem(R.id.MenuAlbumContextDownload), this.mActivity);
        }
        updateExploreArtistContextMenu(activity, menu, primeAlbum.getArtist());
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(primeAlbum);
        if (!SequencerHelper.canModifyQueue() || playCatalogContentUnavailableReason != null) {
            menu.removeItem(R.id.MenuAlbumAddToPlayQueue);
            menu.removeItem(R.id.MenuAlbumPlayNext);
        }
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            menu.removeItem(R.id.MenuAlbumSimilarRecommend);
        }
        if (!this.albumModelConfiguration.shouldShowContextMenuPlayButton(primeAlbum, this.mProviderListener.getContextMenuPageType())) {
            menu.removeItem(R.id.MenuPlayAlbumContext);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuAlbumStationFromAnything);
        } else {
            menu.removeItem(R.id.MenuAlbumGoToEntityPlaylist);
        }
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            menu.removeItem(R.id.MenuAlbumContextAddToLibrary);
            menu.removeItem(R.id.MenuAlbumContextInLibrary);
        }
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuAlbumContextAddToLibrary), this.mActivity);
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuAlbumContextDownload), this.mActivity);
        StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuAlbumAddToPlaylist), this.mActivity);
        updatePresetsContextMenu(activity, primeAlbum, menu, R.id.MenuAlbumAddToPresets, R.id.MenuAlbumRemoveFromPresets);
        this.mProviderListener.removeDownloadAlbumMenuItem(menu);
    }

    private void addToLibrary(Activity activity, PrimeAlbum album) {
        addToLibraryAndDownload(activity, album, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadAlbum(Activity activity, PrimeAlbum album) {
        addToLibraryAndDownload(activity, album, true);
    }

    public void addToPlaylist(Context context, PrimeAlbum album) {
        if (!this.mProviderListener.getAlbumStateProvider().isInLibrary(album)) {
            this.mProviderListener.addAlbumForPlaylist(album);
        } else if (this.mProviderListener.getAlbumStateProvider().getAllTracksUri(album) == null) {
            this.mProviderListener.updateAlbumStatusForPlaylist(album);
        } else {
            context.startActivity(AddToPlaylistPopupActivity.getStartIntent(context, this.mProviderListener.getAlbumStateProvider().getAllTracksUri(album), CirrusMediaSource.getAlbumSortOrder(), this.mPageType));
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public <D extends SearchItem & CatalogContent> void addToPlayQueue(final D album, final boolean playNext) {
        PrimeAlbum primeAlbum = (PrimeAlbum) album;
        if (!this.mProviderListener.getAlbumStateProvider().isInLibrary(primeAlbum)) {
            PrimeCollectionTask.createPlaybackTask(this.mActivity, album.getAsin(), (String) null, (PlaybackPageType) null, playNext ? PrimeCollectionTask.Task.PLAY_NEXT : PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE, new QueryAlbumByAsin(this.mActivity), (PrimeCollectionTask.OnUriReadyCallback) null).execute();
        } else {
            NowPlayingQueueUtil.getInstance().addToPlayQueue(this.mProviderListener.getAlbumStateProvider().getAllTracksUri(primeAlbum), playNext);
        }
    }

    private void addToLibraryAndDownload(Activity activity, final PrimeAlbum album, boolean download) {
        new AddAndDownloadAlbumTask(activity, album, download) { // from class: com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(final Integer jobStatus) {
                if (jobStatus.intValue() == 1) {
                    PrimeAlbumContextMenuProvider.this.mProviderListener.updateAlbumStatus(album);
                }
            }
        }.execute(new Void[0]);
    }
}
