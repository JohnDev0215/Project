package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.detailpages.actions.TrackListContainerBaseViewModelFetcher;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.AlbumListAdapter;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.moderation.ModerationEntity;
import com.amazon.mp3.moderation.ModerationHandler;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MathUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.AlbumShareProvider;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import rx.Observable;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumContextMenuProvider extends ContextMenuProvider<AlbumListAdapter.RowViewHolder> {
    private static final int CONFIRM_DELETE_ALBUM_ID = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = AlbumContextMenuProvider.class.getSimpleName();
    private AlbumModelConfiguration albumModelConfiguration;
    protected AlbumContextMenuProviderListener mProviderListener;
    private RemoveLibraryContentProvider.OnRemoveLibraryContentListener onRemoveLibraryContentListener;

    public static abstract class AlbumContextMenuProviderListener {
        public abstract ContextMenuPageType getContextMenuPageType();

        public abstract void removeDeleteAlbumMenuItem(ContextMenu menu);

        public abstract void removeDownloadAlbumMenuItem(ContextMenu menu);

        public abstract boolean shouldDisplayAddToPlaylist();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.library_album_context;
    }

    public AlbumContextMenuProvider(FragmentActivity activity, AlbumContextMenuProviderListener albumContextMenuProviderListener) {
        super(activity);
        this.onRemoveLibraryContentListener = null;
        this.albumModelConfiguration = new AlbumModelConfiguration();
        this.mProviderListener = albumContextMenuProviderListener;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(final FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        Album album;
        super.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
        DefaultTrackInformationFetcher defaultTrackInformationFetcher = new DefaultTrackInformationFetcher(new TrackListContainerBaseViewModelFetcher());
        PlaybackPageType playbackPageType = PlaybackPageType.ALBUM_DETAIL;
        final PlayCollectionAction playCollectionAction = new PlayCollectionAction(defaultTrackInformationFetcher, playbackPageType);
        Object tag = menuInfo.targetView.getTag();
        if (tag == null) {
            return false;
        }
        if (tag instanceof AlbumListAdapter.RowViewHolder) {
            AlbumListAdapter.RowViewHolder rowViewHolder = (AlbumListAdapter.RowViewHolder) menuInfo.targetView.getTag();
            if (rowViewHolder == null) {
                return false;
            }
            album = (Album) rowViewHolder.mLibraryItem;
        } else {
            if (tag instanceof Album) {
                album = (Album) tag;
            }
            return false;
        }
        Uri contentUri = MediaProvider.Albums.Tracks.getContentUri(sourceID, menuInfo.id);
        int itemId = item.getItemId();
        setClickedEntityIdAndType(album.getAsin(), EntityIdType.ASIN);
        long j = Long.parseLong(album.getId());
        String string = MediaProvider.Albums.Tracks.getContentUri("cirrus", j).toString();
        if (itemId == R.id.MenuAlbumContextDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
            if ((UserSubscriptionUtil.isNightwingOnly() && !album.isAllOwned()) || this.albumModelConfiguration.shouldShowContextMenuDownloadUpsellButton(album)) {
                UpsellUtil.showBlockingUpsell(activity, album.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.ALBUM);
                return true;
            }
            ContextMenuDownloadComponent.startDownload(activity, string, Uri.parse(string), new NotificationInfo(album.getTitle(), album.getArtistName(), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), j).toString()));
            return true;
        }
        if (itemId == R.id.MenuAlbumContextCancelDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_CANCEL_ALL);
            ContextMenuDownloadComponent.cancelDownload(activity, string);
            return true;
        }
        if (itemId == R.id.MenuAlbumContextDelete) {
            sendMetricEvent(ActionType.REMOVE_FROM_DEVICE_OVERFLOW);
            new RemoveLibraryContentProvider(activity, this.onRemoveLibraryContentListener).removeAlbumFromDevice(album);
            return true;
        }
        if (itemId == R.id.MenuAlbumContextDeleteFromCirrus) {
            sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
            new RemoveLibraryContentProvider(activity, this.onRemoveLibraryContentListener).removeAlbumFromCloud(album);
            return true;
        }
        if (itemId == R.id.MenuExploreArtist) {
            activity.startActivity(ShopLinkUtil.getArtistExploreIntent(activity, Long.valueOf(menuInfo.id).toString(), album.getArtistName(), ItemWrapper$ItemType.ALBUM));
            MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
            return true;
        }
        if (itemId == R.id.MenuAlbumAddToPlaylist) {
            sendMetricEvent(ActionType.ADD_ALL_TO_PLAYLIST_ATTEMPT_OVERFLOW);
            activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, contentUri, CirrusMediaSource.getAlbumTrackSortOrder(), null, false, album.getAsin(), this.mPageType));
            return true;
        }
        if (itemId == R.id.MenuAlbumAddToPlayQueue) {
            sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
            if (UserSubscriptionUtil.isNightwingOnly() && !album.isAllOwned()) {
                UpsellUtil.showBlockingUpsell(activity, album.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ALBUM);
                return true;
            }
            NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, TrackListAdapter.getDefaultSelection(), TrackListAdapter.getDefaultSelectionArgs(), false);
            return true;
        }
        if (itemId == R.id.MenuAlbumPlayNext) {
            sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
            if (UserSubscriptionUtil.isNightwingOnly() && !album.isAllOwned()) {
                UpsellUtil.showBlockingUpsell(activity, album.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ALBUM);
                return true;
            }
            NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, TrackListAdapter.getDefaultSelection(), TrackListAdapter.getDefaultSelectionArgs(), true);
            return true;
        }
        if (itemId == R.id.MenuAlbumGoToArtist) {
            sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW);
            Uri contentUri2 = MediaProvider.Artists.getContentUri(sourceID, album.getArtistId());
            FragmentActivity fragmentActivity = this.mActivity;
            fragmentActivity.startActivity(LibraryActivityFactory.getArtistDetailsIntent(fragmentActivity, album.getArtistName(), album.getArtistAsin(), contentUri2, sourceID));
            return true;
        }
        if (itemId == R.id.MenuAlbumSimilarRecommend) {
            sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
            goToSimilarRecommendationsFragment(PaginatedDataType.ALBUM, album.getAsin());
            return true;
        }
        if (itemId == R.id.MenuAlbumContextShare) {
            new AlbumShareProvider(activity, EndpointsProvider.get().getMusicDomain(), album.getAsin(), album.getTitle(), album.getArtist().getName(), new CTAPrimeCache(activity).getArtworkUrlForAsin(album.getAsin())).startShare();
            return true;
        }
        if (itemId == R.id.MenuAlbumStationFromAnything) {
            StationUtils.startSFA(activity, album.getAsin(), "ALBUM_SEED", PlaybackPageType.ALBUMS_LIST, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(album.getAsin(), EntityIdType.SFA_ALBUM_SEED, getPageType());
            return true;
        }
        if (itemId == R.id.MenuAlbumAddToPresets) {
            CarModePresetsUtility.addToPresets(activity, album, getPageType());
        } else if (itemId == R.id.MenuAlbumRemoveFromPresets) {
            CarModePresetsUtility.removeFromPresets(activity, album, getPageType());
        } else {
            if (itemId == R.id.MenuAlbumGoToEntityPlaylist) {
                goToEntityPlaylistFragment(album.getAsin(), PlaylistSeedEntityType.ALBUM);
                return true;
            }
            if (itemId == R.id.MenuAlbumShuffle) {
                final int iGenerateRandomStartIndex = MathUtil.INSTANCE.generateRandomStartIndex((int) album.getTrackCount());
                final Album album2 = album;
                StartPlaybackUtil.startCollectionPlayback(activity.getApplicationContext(), album.getTitle(), album.getContentUri(), null, iGenerateRandomStartIndex, true, true, null, playbackPageType, false, ControlSource.APP_UI, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.1
                    @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                    public void handlePlayback() {
                        playCollectionAction.beginPlayQueuePlayback(activity, iGenerateRandomStartIndex, true, album2.getContentUri(), Observable.just(null), true, null);
                    }
                });
                return true;
            }
            final Album album3 = album;
            if (itemId == R.id.MenuAlbumPlay) {
                StartPlaybackUtil.startCollectionPlayback(activity.getApplicationContext(), album3.getTitle(), album3.getContentUri(), Arrays.asList(album3.getTracks().toArray()), 0, true, true, null, playbackPageType, false, ControlSource.APP_UI, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.2
                    @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                    public void handlePlayback() {
                        playCollectionAction.beginPlayQueuePlayback(activity, 0, true, album3.getContentUri(), Observable.just(null), true, null);
                    }
                });
                return true;
            }
            if (itemId == R.id.MenuAlbumReport) {
                sendMetricEvent(ActionType.REPORT_OVERFLOW);
                new ModerationHandler(activity, album3, ModerationEntity.ALBUM).startModeration();
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(final Activity activity, final ContextMenu menu, Object tag, int position, final String sourceID) {
        Album album;
        if (tag instanceof AlbumListAdapter.RowViewHolder) {
            album = (Album) ((AlbumListAdapter.RowViewHolder) tag).mLibraryItem;
        } else if (!(tag instanceof Album)) {
            return;
        } else {
            album = (Album) tag;
        }
        final Album album2 = album;
        setStyledHeaderTitle(album2.getTitle(), menu);
        if (!album2.isAvailable()) {
            menu.removeItem(R.id.MenuAlbumAddToPlaylist);
            menu.removeItem(R.id.MenuAlbumContextDownload);
            menu.removeItem(R.id.MenuAlbumContextCancelDownload);
        }
        if (!this.albumModelConfiguration.shouldShowContextMenuShuffleButton(album2, Boolean.valueOf(album2.isAllOwned()), this.mProviderListener.getContextMenuPageType())) {
            menu.removeItem(R.id.MenuAlbumShuffle);
        }
        if (TextUtils.isEmpty(album2.getAsin())) {
            menu.removeItem(R.id.MenuAlbumSimilarRecommend);
            menu.removeItem(R.id.MenuAlbumContextShare);
            menu.removeItem(R.id.MenuAlbumStationFromAnything);
            menu.removeItem(R.id.MenuAlbumGoToEntityPlaylist);
            menu.removeItem(R.id.MenuAlbumReport);
        }
        if (!AmazonApplication.getCapabilities().isSharingSupported()) {
            menu.removeItem(R.id.MenuAlbumContextShare);
        }
        if (AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuAlbumGoToArtist);
        }
        if (album2.isRemote()) {
            int downloadState = album2.getDownloadState();
            if (downloadState == 0) {
                menu.removeItem(R.id.MenuAlbumContextDownload);
                menu.removeItem(R.id.MenuAlbumContextCancelDownload);
            } else if (downloadState == 1 || downloadState == 2 || downloadState == 3 || downloadState == 4) {
                menu.removeItem(R.id.MenuAlbumContextDownload);
                menu.removeItem(R.id.MenuAlbumContextDelete);
            } else if (downloadState == 5) {
                menu.removeItem(R.id.MenuAlbumContextCancelDownload);
                menu.removeItem(R.id.MenuAlbumContextDelete);
            } else {
                menu.removeItem(R.id.MenuAlbumContextCancelDownload);
            }
            int downloadState2 = album2.getDownloadState();
            if (downloadState2 == 0 || downloadState2 == 1 || downloadState2 == 2) {
                menu.removeItem(R.id.MenuAlbumContextDelete);
            }
            MenuItem menuItemFindItem = menu.findItem(R.id.MenuAlbumContextDeleteFromCirrus);
            if (menuItemFindItem != null) {
                menuItemFindItem.setTitle(R.string.dmusic_context_remove_from_library);
            }
        } else {
            menu.removeItem(R.id.MenuAlbumContextDownload);
            menu.removeItem(R.id.MenuAlbumContextCancelDownload);
            menu.removeItem(R.id.MenuAlbumContextDeleteFromCirrus);
            menu.removeItem(R.id.MenuAlbumSimilarRecommend);
            if (album2.getMaxContentOwnershipStatusValue() < 200 && album2.getDownloadState() == 5) {
                menu.removeItem(R.id.MenuAlbumContextDelete);
            }
            if (TextUtils.isEmpty(album2.getAsin())) {
                menu.removeItem(R.id.MenuAlbumAddToPlaylist);
            }
        }
        updateExploreArtistContextMenu(activity, menu, album2.getArtistName());
        if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.MenuAlbumAddToPlayQueue);
            menu.removeItem(R.id.MenuAlbumPlayNext);
        }
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            menu.removeItem(R.id.MenuAlbumSimilarRecommend);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuAlbumStationFromAnything);
        } else {
            menu.removeItem(R.id.MenuAlbumGoToEntityPlaylist);
        }
        if (!AmazonApplication.getCapabilities().isAlbumModerationSupported() || PlatformUtil.isFireOS()) {
            menu.removeItem(R.id.MenuAlbumReport);
        }
        if (!album2.isAllOwned()) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuAlbumContextDownload), this.mActivity);
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuAlbumPlayNext), this.mActivity);
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuAlbumAddToPlayQueue), this.mActivity);
        }
        updatePresetsContextMenu(activity, album2, menu, R.id.MenuAlbumAddToPresets, R.id.MenuAlbumRemoveFromPresets);
        this.mProviderListener.removeDeleteAlbumMenuItem(menu);
        if (this.albumModelConfiguration.shouldShowContextMenuDownloadUpsellButton(album2, Boolean.valueOf(album2.isInLibrary()))) {
            StringUtil.dimTextColor(menu.findItem(R.id.MenuAlbumContextDownload), this.mActivity);
        } else {
            menu.removeItem(R.id.MenuAlbumContextDownload);
        }
        menu.removeItem(R.id.MenuAlbumContextDownloading);
        menu.removeItem(R.id.MenuAlbumContextDownloaded);
        updatePresetsContextMenu(activity, album2, menu, R.id.MenuAlbumAddToPresets, R.id.MenuAlbumRemoveFromPresets);
        if (!this.albumModelConfiguration.shouldShowContextMenuDownloadUpsellButton(album2)) {
            this.mProviderListener.removeDownloadAlbumMenuItem(menu);
        }
        if (!this.albumModelConfiguration.shouldShowContextMenuPlayButton(album2, this.mProviderListener.getContextMenuPageType())) {
            menu.removeItem(R.id.MenuAlbumPlay);
        }
        Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Exception {
                AlbumContextMenuProvider.lambda$updateContextMenuItems$0(activity, album2, sourceID, singleEmitter);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                this.f$0.lambda$updateContextMenuItems$1(menu, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                AlbumContextMenuProvider.lambda$updateContextMenuItems$2((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$0(Activity activity, Album album, String str, SingleEmitter singleEmitter) throws Exception {
        try {
            singleEmitter.onSuccess(Boolean.valueOf(MediaProvider.Albums.Tracks.isAllAsinTracksAlbum(activity.getApplicationContext(), album.getContentUri(str))));
        } catch (Exception e) {
            singleEmitter.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateContextMenuItems$1(ContextMenu contextMenu, Boolean bool) throws Exception {
        if (this.mProviderListener.shouldDisplayAddToPlaylist() && bool.booleanValue()) {
            return;
        }
        contextMenu.removeItem(R.id.MenuAlbumAddToPlaylist);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$2(Throwable th) throws Exception {
        Log.error(TAG, "Error retrieving album data: " + th);
    }
}
