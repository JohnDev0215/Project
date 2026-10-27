package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.audioquality.ContextMenuDownloadHelper;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.TrackModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.helper.RedownloadStateResolver;
import com.amazon.mp3.download.library.DownloadFileUtil;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.TrackListAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.local.DeleteUtil;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.service.sync.blacklist.database.BlacklistDatabase;
import com.amazon.mp3.library.service.sync.blacklist.entity.LocalUri;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.moderation.ModerationEntity;
import com.amazon.mp3.moderation.ModerationHandler;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.TrackShareProvider;
import java.util.Collections;
import java.util.HashSet;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class TrackContextMenuProvider extends ContextMenuProvider<TrackListAdapter.RowViewHolder> {
    private static final String TAG = "TrackContextMenuProvider";
    public boolean canAddToLibrary;
    protected int mHeaderCount;
    protected TrackContextMenuProviderListener mProviderListener;
    private PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private TrackModelConfiguration trackModelConfiguration = new TrackModelConfiguration();
    public boolean canRemoveFromDevice = true;
    protected boolean mIsNightwingOnlyUser = UserSubscriptionUtil.isNightwingOnly();

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.library_track_context;
    }

    public TrackContextMenuProvider() {
    }

    public interface TrackContextMenuProviderListener extends RemoveLibraryContentProvider.OnRemoveLibraryContentListener, OnAddLibraryContentListener {
        void addAndDownloadPrimeTrack(int position, Uri uri, AbstractItem libraryItem);

        void addPrimeTrack(String asin, String luid);

        boolean isNotAddedPrime(int position, AbstractItem libraryItem);

        void removeFromPlayQueueMenuItem(ContextMenu menu);

        void removeFromPlaylistMenuItem(ContextMenu menu);

        void removeTrackFromPlayQueue(int trackPos, boolean isShuffled);

        void removeTrackFromPlaylist(Uri trackUri);

        boolean shouldRemoveAddToPlaylist();

        boolean shouldRemoveAlbumDetailItem();

        boolean shouldRemoveTrackDownloadItem();

        boolean shouldRemoveVideoPlayItem(Boolean isVideoTrack);

        default ContextMenuPageType getContextMenuPageType() {
            return ContextMenuPageType.DETAIL;
        }
    }

    public TrackContextMenuProvider(FragmentActivity activity, int headerCount, TrackContextMenuProviderListener listener) {
        this.mProviderListener = listener;
        this.mHeaderCount = headerCount;
        init(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public void onCreateContextMenu(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        super.onCreateContextMenu(activity, menu, tag, position, sourceID);
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            menu.removeItem(R.id.MenuTrackContextAddToCirrus);
            menu.removeItem(R.id.menu_songs_add_to_library);
            menu.removeItem(R.id.menu_songs_in_library);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        updateContextMenuItems(activity, menu, tag, position, sourceID, true);
    }

    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID, boolean showMissingContentOptions) {
        MusicTrack musicTrack;
        int i;
        int i2;
        boolean zEquals = "nowplaying".equals(sourceID);
        if (tag == null) {
            return;
        }
        if (tag instanceof TrackListAdapter.RowViewHolder) {
            TrackListAdapter.RowViewHolder rowViewHolder = (TrackListAdapter.RowViewHolder) tag;
            if (rowViewHolder.mTrackUri == null) {
                return;
            } else {
                musicTrack = (MusicTrack) rowViewHolder.mLibraryItem;
            }
        } else if (!(tag instanceof MusicTrack)) {
            return;
        } else {
            musicTrack = (MusicTrack) tag;
        }
        if (!musicTrack.isAvailable()) {
            menu.removeItem(R.id.MenuTrackSimilarRecommend);
        }
        if (!this.canRemoveFromDevice) {
            menu.removeItem(R.id.MenuTrackContextDelete);
        }
        if (StringUtils.isBlank(musicTrack.getAsin())) {
            menu.removeItem(R.id.MenuTrackContextAddToPlaylist);
            menu.removeItem(R.id.MenuTrackStationFromAnything);
        }
        if (!StationUtils.isCustomerAllowedToPlaySFAStation()) {
            menu.removeItem(R.id.MenuTrackStationFromAnything);
        }
        String downloadQuality = new ContextMenuDownloadHelper().getDownloadQuality(musicTrack, this.mActivity);
        MenuItem menuItemFindItem = menu.findItem(R.id.MenuTrackContextDownload);
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity)) {
            menuItemFindItem.setTitle(this.mActivity.getResources().getString(R.string.dmusic_context_download_with_quality, downloadQuality));
        }
        boolean z = ContentAccessUtil.getCatalogContentUnavailableReason(musicTrack, ContentAccessUtil.ContentAccessOperation.DOWNLOAD) == null;
        modifyDownloadButton(menu, musicTrack, z);
        if (!(ContentAccessUtil.getCatalogContentUnavailableReason(musicTrack, ContentAccessUtil.ContentAccessOperation.INTERACT) == null)) {
            menu.removeItem(R.id.MenuTrackContextAddToCirrus);
        }
        setStyledHeaderTitle(musicTrack.getTitle(), menu);
        this.mProviderListener.removeFromPlaylistMenuItem(menu);
        this.mProviderListener.removeFromPlayQueueMenuItem(menu);
        if (TextUtils.isEmpty(musicTrack.getAsin())) {
            menu.removeItem(R.id.MenuTrackSimilarRecommend);
            menu.removeItem(R.id.MenuTrackContextShare);
            menu.removeItem(R.id.MenuTrackGoToEntityPlaylist);
            menu.removeItem(R.id.MenuTrackReport);
        }
        boolean zIsShareable = musicTrack.isShareable();
        if (!AmazonApplication.getCapabilities().isSharingSupported() || !zIsShareable) {
            menu.removeItem(R.id.MenuTrackContextShare);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuTrackStationFromAnything);
        } else {
            menu.removeItem(R.id.MenuTrackGoToEntityPlaylist);
        }
        if (!AmazonApplication.getCapabilities().isTrackModerationSupported() || PlatformUtil.isFireOS()) {
            menu.removeItem(R.id.MenuTrackReport);
        }
        boolean zIsContentDownloadable = DownloadFileUtil.isContentDownloadable(musicTrack.getExtension());
        if (musicTrack.isRemote()) {
            if (!zIsContentDownloadable) {
                menu.removeItem(R.id.MenuTrackContextDownload);
                menu.removeItem(R.id.MenuTrackContextCancelDownload);
                menu.removeItem(R.id.MenuTrackContextDelete);
            }
            if (!PlaylistUtil.isContentPlaylistAddable(musicTrack.getExtension())) {
                menu.removeItem(R.id.MenuTrackContextAddToPlaylist);
            }
            modifyDownloadButton(menu, musicTrack, z);
            int downloadState = musicTrack.getDownloadState();
            if (downloadState == 0) {
                if (new RedownloadStateResolver(this.mActivity).shouldRedownloadTrack(musicTrack.getAsin(), true, musicTrack.getContentEncodings()) && AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity)) {
                    menuItemFindItem.setTitle(this.mActivity.getResources().getString(R.string.dmusic_context_redownload_with_quality, downloadQuality));
                } else {
                    menu.removeItem(R.id.MenuTrackContextDownload);
                }
                menu.removeItem(R.id.MenuTrackContextCancelDownload);
            } else if (downloadState == 1 || downloadState == 2 || downloadState == 3 || downloadState == 4) {
                menu.removeItem(R.id.MenuTrackContextDownload);
            } else {
                menu.removeItem(R.id.MenuTrackContextCancelDownload);
            }
            if (this.canAddToLibrary && this.trackModelConfiguration.shouldShowContextMenuAddToMyMusicButton(this.mProviderListener.getContextMenuPageType())) {
                i2 = R.id.MenuTrackContextAddToCirrus;
            } else {
                i2 = R.id.MenuTrackContextAddToCirrus;
                menu.removeItem(R.id.MenuTrackContextAddToCirrus);
            }
            menu.removeItem(R.id.MenuTrackContextDelete);
            ContentOwnershipStatus ownershipStatus = musicTrack.getOwnershipStatus();
            if (zEquals) {
                menu.removeItem(i2);
                menu.removeItem(R.id.MenuTrackContextDeleteFromCirrus);
            } else if (ownershipStatus.isInLibrary() || !musicTrack.isAddableToLibrary().booleanValue()) {
                menu.removeItem(R.id.MenuTrackContextAddToCirrus);
                menu.removeItem(R.id.MenuTrackContextDeleteSideLoadedContentFromMyMusic);
            } else {
                menu.removeItem(R.id.MenuTrackContextDeleteFromCirrus);
            }
            menu.removeItem(R.id.MenuTrackContextDeleteSideLoadedContentFromMyMusic);
        } else {
            menu.removeItem(R.id.MenuTrackContextDownload);
            menu.removeItem(R.id.MenuTrackContextCancelDownload);
            menu.removeItem(R.id.MenuTrackContextDeleteFromCirrus);
            menu.removeItem(R.id.MenuTrackContextAddToCirrus);
            menu.removeItem(R.id.MenuTrackSimilarRecommend);
            if (!TextUtils.isEmpty(musicTrack.getAsin())) {
                menu.removeItem(R.id.MenuTrackContextDeleteSideLoadedContentFromMyMusic);
            }
            if (musicTrack.getOwnershipStatus().getValue() < 200 && TextUtils.isEmpty(musicTrack.getAsin())) {
                menu.removeItem(R.id.MenuTrackContextDelete);
            }
            if (PageType.DETAIL_USER_PLAYLIST.equals(getPageType()) && !musicTrack.isRemote()) {
                menu.removeItem(R.id.MenuTrackContextDelete);
            }
        }
        if (AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuTrackContextGoToArtist);
        }
        if (this.mProviderListener.shouldRemoveAlbumDetailItem()) {
            menu.removeItem(R.id.MenuTrackShowAlbumDetail);
        }
        if (this.mProviderListener.shouldRemoveAddToPlaylist()) {
            menu.removeItem(R.id.MenuTrackContextAddToPlaylist);
        }
        if (this.mProviderListener.shouldRemoveTrackDownloadItem()) {
            menu.removeItem(R.id.MenuTrackContextDownload);
        }
        PrimePlaylistTracksTable.AssetType assetType = musicTrack.getAssetType();
        PrimePlaylistTracksTable.AssetType assetType2 = PrimePlaylistTracksTable.AssetType.VIDEO;
        if (this.mProviderListener.shouldRemoveVideoPlayItem(Boolean.valueOf(assetType == assetType2))) {
            menu.removeItem(R.id.MenuVideoPlay);
        }
        if (musicTrack.isAllOwned() || !AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(this.mActivity)) {
            menu.removeItem(R.id.MenuTrackContextBuySong);
        }
        updateExploreArtistContextMenu(activity, menu, musicTrack.getArtistName());
        if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.MenuTrackAddToPlayQueue);
            menu.removeItem(R.id.MenuTrackPlayNext);
            menu.removeItem(R.id.MenuTrackRemoveFromPlayQueue);
        }
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(musicTrack);
        if (zEquals || this.mPlaybackController.getCurrentQueue().size() <= 0 || DeluxeContentUtil.isDeluxeContent(musicTrack.getExtension()) || playCatalogContentUnavailableReason != null) {
            menu.removeItem(R.id.MenuTrackAddToPlayQueue);
            menu.removeItem(R.id.MenuTrackPlayNext);
        }
        menu.removeItem(R.id.MenuTrackCatalogStatus);
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            menu.removeItem(R.id.MenuTrackSimilarRecommend);
        }
        ContentUnavailableReason catalogContentUnavailableReason = ContentAccessUtil.getCatalogContentUnavailableReason(musicTrack, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE);
        boolean z2 = musicTrack.getDownloadState() == 0 || musicTrack.getDownloadState() == 6 || musicTrack.getDownloadState() == 7;
        boolean z3 = catalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK || catalogContentUnavailableReason == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE || catalogContentUnavailableReason == ContentUnavailableReason.DOWNLOAD_CORRUPTED;
        if (showMissingContentOptions && z && z2 && zIsContentDownloadable && !zEquals && z3) {
            menu.clear();
            if ((musicTrack.getDownloadState() == 5 || musicTrack.getDownloadState() == 0 || musicTrack.getDownloadState() == 6 || musicTrack.getDownloadState() == 7) ? false : true) {
                i = 0;
                menu.add(0, R.id.MenuTrackContextCancelDownload, 0, R.string.dmusic_download_item_context_remove_item);
            } else {
                i = 0;
                if (musicTrack.isAllCatalog() || musicTrack.getAsin() != null) {
                    menu.add(0, R.id.MenuTrackContextDownload, 0, R.string.dmusic_context_download);
                }
            }
            if (musicTrack.isRemote()) {
                menu.add(i, R.id.MenuTrackContextDeleteFromCirrus, 1, R.string.dmusic_context_remove_from_library);
            } else {
                menu.add(i, R.id.MenuTrackContextDelete, 1, R.string.dmusic_context_delete_device);
            }
        }
        if (!AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuTrackContextAddToCirrus), this.mActivity);
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuTrackContextAddToCirrus), this.mActivity);
        }
        if (assetType2.equals(musicTrack.getAssetType())) {
            menu.removeItem(R.id.MenuTrackContextAddToPlaylist);
            menu.removeItem(R.id.MenuTrackShowAlbumDetail);
            menu.removeItem(R.id.MenuTrackExploreArtist);
            menu.removeItem(R.id.MenuTrackSimilarRecommend);
            menu.removeItem(R.id.MenuTrackContextShare);
            menu.removeItem(R.id.MenuTrackContextBuySong);
            menu.removeItem(R.id.MenuTrackContextDownload);
            menu.removeItem(R.id.MenuTrackContextCancelDownload);
            menu.removeItem(R.id.MenuTrackReport);
        } else {
            menu.removeItem(R.id.MenuVideoPlay);
        }
        if (SequencerHelper.canModifyQueue()) {
            return;
        }
        menu.removeItem(R.id.MenuTrackPlayNext);
        menu.removeItem(R.id.MenuTrackAddToPlayQueue);
    }

    private void modifyDownloadButton(ContextMenu menu, MusicTrack track, boolean hasDownloadAccess) {
        if (hasDownloadAccess) {
            return;
        }
        if (this.trackModelConfiguration.shouldShowContextMenuDownloadUpsellButton(track)) {
            StringUtil.dimTextColor(menu.findItem(R.id.MenuTrackContextDownload), this.mActivity);
        } else {
            menu.removeItem(R.id.MenuTrackContextDownload);
        }
        menu.removeItem(R.id.MenuTrackContextCancelDownload);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(final FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        final MusicTrack musicTrack;
        String trackLuidByLocalFileLocation;
        super.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
        Object tag = menuInfo.targetView.getTag();
        if (tag == null) {
            return false;
        }
        if (tag instanceof TrackListAdapter.RowViewHolder) {
            TrackListAdapter.RowViewHolder rowViewHolder = (TrackListAdapter.RowViewHolder) menuInfo.targetView.getTag();
            if (rowViewHolder == null) {
                return false;
            }
            musicTrack = (MusicTrack) rowViewHolder.mLibraryItem;
        } else {
            if (tag instanceof MusicTrack) {
                musicTrack = (MusicTrack) tag;
            }
            return false;
        }
        int i = menuInfo.position - this.mHeaderCount;
        int itemId = item.getItemId();
        String asin = !TextUtils.isEmpty(musicTrack.getAsin()) ? musicTrack.getAsin() : musicTrack.getLuid();
        setClickedEntityIdAndType(musicTrack.getAsin(), EntityIdType.ASIN);
        if (itemId == R.id.MenuTrackContextDownload) {
            if (this.trackModelConfiguration.shouldShowContextMenuDownloadUpsellButton(musicTrack)) {
                UpsellUtil.showBlockingUpsell(activity, musicTrack.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.TRACK);
                return true;
            }
            ActionType actionType = ActionType.DOWNLOAD_OVERFLOW;
            if (musicTrack.isAvailableOffline()) {
                actionType = ActionType.REDOWNLOAD_OVERFLOW;
            }
            sendMetricEvent(actionType);
            try {
                if (this.mProviderListener.isNotAddedPrime(i, musicTrack)) {
                    this.mProviderListener.addAndDownloadPrimeTrack(i, musicTrack.getContentUri(), musicTrack);
                } else {
                    NotificationInfo notificationInfo = new NotificationInfo(musicTrack.getTitle(), musicTrack.getArtistName(), ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), musicTrack.getAlbumId()).toString());
                    Uri contentUri = musicTrack.getContentUri();
                    if (!musicTrack.isRemote() && activity != null && (trackLuidByLocalFileLocation = TrackUtil.getTrackLuidByLocalFileLocation(CirrusDatabase.getWritableDatabase(activity), musicTrack.getLocalUri(), 0)) != null) {
                        contentUri = MediaProvider.Tracks.getContentUri("cirrus", trackLuidByLocalFileLocation);
                    }
                    ContextMenuDownloadComponent.startDownload(activity, asin, contentUri, notificationInfo, musicTrack.isAvailableOffline() || StringUtils.isNotEmpty(musicTrack.getLocalUri()));
                }
            } catch (IllegalStateException e) {
                Log.e(TAG, "Unable to download a track: " + e.getMessage(), e);
            }
            return true;
        }
        if (itemId == R.id.MenuTrackContextCancelDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_CANCEL_ONE);
            ContextMenuDownloadComponent.cancelDownload(activity, asin);
            return true;
        }
        if (itemId == R.id.MenuTrackContextDelete) {
            sendMetricEvent(ActionType.REMOVE_FROM_DEVICE_OVERFLOW);
            new RemoveLibraryContentProvider(activity, this.mProviderListener).removeTrackFromDevice(musicTrack);
            return true;
        }
        if (itemId == R.id.MenuTrackContextDeleteFromCirrus) {
            sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
            new RemoveLibraryContentProvider(activity, this.mProviderListener).removeTrackFromCloud(musicTrack);
            return true;
        }
        if (itemId == R.id.MenuTrackContextBuySong) {
            sendMetricEvent(ActionType.BUY_SONG_OVERFLOW);
            HTMLStoreActivityFactory.start(activity, HTMLStoreActivityFactory.bundleForAlbum(musicTrack.getAlbumAsin(), ""));
            MetricsLogger.clickedOnStoreLink(StoreLinkType.ALBUM);
            return true;
        }
        if (itemId == R.id.MenuTrackContextAddToPlaylist) {
            sendMetricEvent(ActionType.ADD_TO_PLAYLIST_ATTEMPT_OVERFLOW);
            Intent startIntent = AddToPlaylistPopupActivity.getStartIntent(activity, musicTrack.getContentUri(), this.mPageType);
            startIntent.putExtra("com.amazon.mp3.library.EXTRA_SAVE_SOURCE_ON_RESUME_ID", false);
            activity.startActivity(startIntent);
            return true;
        }
        if (itemId == R.id.MenuTrackContextDeleteFromPlaylist) {
            sendMetricEvent(ActionType.REMOVE_FROM_PLAYLIST_OVERFLOW);
            this.mProviderListener.removeTrackFromPlaylist(musicTrack.getContentUri());
            return true;
        }
        if (itemId == R.id.MenuTrackShowAlbumDetail) {
            sendMetricEvent(ActionType.VIEW_ALBUM_OVERFLOW);
            FragmentActivity fragmentActivity = this.mActivity;
            if (fragmentActivity instanceof NowPlayingFragmentActivity) {
                Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.Albums.getContentUri(sourceID, musicTrack.getAlbumId()));
                if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                    intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", musicTrack.getAlbumAsin());
                } else {
                    intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
                    intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", musicTrack.getAlbumAsin());
                }
                this.mActivity.startActivity(intentForContentUri);
            } else {
                changeScreenFragment(this.mActivity.getSupportFragmentManager(), getAlbumDetailFragment(fragmentActivity, musicTrack, sourceID));
            }
            return true;
        }
        if (itemId == R.id.MenuTrackExploreArtist) {
            sendMetricEvent(ActionType.SHOP_FOR_ARTIST_OVERFLOW);
            activity.startActivity(getArtistShopIntent(activity, musicTrack.getArtistName()));
            MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
            return true;
        }
        if (itemId == R.id.MenuTrackContextAddToCirrus) {
            sendMetricEvent(ActionType.ADD_TO_LIBRARY_OVERFLOW);
            if (this.mIsNightwingOnlyUser) {
                UpsellUtil.showBlockingUpsell(activity, musicTrack.getAsin(), UpsellReason.ADD_TO_LIBRARY, UpsellSourceEntity.TRACK);
                return true;
            }
            String asin2 = musicTrack.getAsin();
            this.mProviderListener.addPrimeTrack(asin2, musicTrack.getLuid());
            MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, asin2, SelectionSourceType.ALBUM, musicTrack.getAlbumAsin());
            return true;
        }
        if (itemId == R.id.MenuTrackContextGoToArtist) {
            sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW);
            Uri contentUri2 = MediaProvider.Artists.getContentUri(sourceID, musicTrack.getArtistId());
            FragmentActivity fragmentActivity2 = this.mActivity;
            fragmentActivity2.startActivity(LibraryActivityFactory.getArtistDetailsIntent(fragmentActivity2, musicTrack.getArtistName(), musicTrack.getArtistAsin(), contentUri2, sourceID));
            return true;
        }
        if (itemId == R.id.MenuTrackSimilarRecommend) {
            sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
            goToSimilarRecommendationsFragment(PaginatedDataType.TRACK, musicTrack.getAsin());
            return true;
        }
        if (itemId == R.id.MenuTrackAddToPlayQueue) {
            sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
            NowPlayingQueueUtil.getInstance().addToPlayQueue(musicTrack.getContentUri(), false);
            return true;
        }
        if (itemId == R.id.MenuTrackPlayNext) {
            sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
            NowPlayingQueueUtil.getInstance().addToPlayQueue(musicTrack.getContentUri(), true);
            return true;
        }
        if (itemId == R.id.MenuTrackRemoveFromPlayQueue) {
            sendMetricEvent(ActionType.REMOVE_FROM_PLAY_Q_OVERFLOW);
            this.mProviderListener.removeTrackFromPlayQueue((int) musicTrack.getTrackNum(), false);
            return true;
        }
        if (itemId == R.id.MenuTrackContextShare) {
            new TrackShareProvider(activity, EndpointsProvider.get().getMusicDomain(), musicTrack.getAlbumAsin(), musicTrack.getAsin(), musicTrack.getTitle(), musicTrack.getArtistName(), musicTrack.getAlbumArtUrl() != null ? musicTrack.getAlbumArtUrl() : new CTAPrimeCache(activity).getArtworkUrlForAsin(musicTrack.getAlbumAsin())).startShare();
            return true;
        }
        if (itemId == R.id.MenuTrackContextDeleteSideLoadedContentFromMyMusic) {
            sendMetricEvent(ActionType.REMOVE_FROM_MY_MUSIC_FOR_SIDE_LOADED_CONTENT);
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setCancelable(true);
            builder.setTitle(activity.getString(R.string.side_loaded_content_removal_confirm_dialog_title));
            builder.setMessage(activity.getString(R.string.side_loaded_content_removal_confirm_dialog_message));
            builder.setNegativeButton(activity.getString(R.string.side_loaded_content_removal_confirm_dialog_negative_button_text), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            builder.setPositiveButton(activity.getString(R.string.side_loaded_content_removal_confirm_dialog_positive_button_text), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    TrackContextMenuProvider.lambda$onContextMenuItemSelected$2(activity, musicTrack, dialogInterface, i2);
                }
            });
            builder.create().show();
            return true;
        }
        if (itemId == R.id.MenuTrackStationFromAnything) {
            StationUtils.startSFA(activity, musicTrack.getAsin(), "TRACK_SEED", PlaybackPageType.SONGS_LIST, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(musicTrack.getAsin(), EntityIdType.SFA_TRACK_SEED, getPageType());
            return true;
        }
        if (itemId == R.id.MenuTrackGoToEntityPlaylist) {
            goToEntityPlaylistFragment(musicTrack.getAsin(), PlaylistSeedEntityType.TRACK);
            return true;
        }
        if (itemId == R.id.MenuTrackReport) {
            sendMetricEvent(ActionType.REPORT_OVERFLOW);
            new ModerationHandler(activity, musicTrack, ModerationEntity.TRACK).startModeration();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onContextMenuItemSelected$2(final FragmentActivity fragmentActivity, final MusicTrack musicTrack, DialogInterface dialogInterface, int i) {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TrackContextMenuProvider.lambda$onContextMenuItemSelected$1(fragmentActivity, musicTrack);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onContextMenuItemSelected$1(FragmentActivity fragmentActivity, MusicTrack musicTrack) {
        BlacklistDatabase.getInstance(fragmentActivity).blacklistDao().insert(new LocalUri(musicTrack.getLocalUri()));
        DeleteUtil.deleteTracksByLuids("cirrus-local", new HashSet(Collections.singletonList(musicTrack.getLuid())));
        ContentResolver contentResolver = AmazonApplication.getContext().getContentResolver();
        contentResolver.notifyChange(Uri.parse("content://media"), null);
        contentResolver.notifyChange(Uri.parse("content://" + MediaProvider.AUTHORITY), null);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateExploreArtistContextMenu(Activity activity, Menu menu, String artist) {
        if (!AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(this.mActivity)) {
            menu.removeItem(R.id.MenuTrackExploreArtist);
            return;
        }
        String string = activity.getString(R.string.dmusic_context_shop_for, artist);
        MenuItem menuItemFindItem = menu.findItem(R.id.MenuTrackExploreArtist);
        if (menuItemFindItem != null) {
            menuItemFindItem.setTitle(string);
        }
    }

    private Fragment getAlbumDetailFragment(Activity activity, MusicTrack track, String sourceId) {
        Fragment albumDetailFragment;
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.Albums.getContentUri(sourceId, track.getAlbumId()));
        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
            intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", track.getAlbumAsin());
            albumDetailFragment = new AlbumDetailFragment();
        } else {
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", track.getAlbumAsin());
            albumDetailFragment = new com.amazon.mp3.library.fragment.AlbumDetailFragment();
        }
        albumDetailFragment.setArguments(intentForContentUri.getExtras());
        return albumDetailFragment;
    }

    private Intent getArtistShopIntent(Activity activity, String artistName) {
        return HTMLStoreActivityFactory.intentForSearch(activity, HTMLStoreActivityFactory.bundleForSearchString(artistName, 1, "field-author"));
    }
}
