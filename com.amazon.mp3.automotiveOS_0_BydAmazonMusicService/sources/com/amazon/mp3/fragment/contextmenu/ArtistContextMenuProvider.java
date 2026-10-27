package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.adapter.ArtistListAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ArtistShareProvider;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistContextMenuProvider extends ContextMenuProvider<ArtistListAdapter.RowViewHolder> {
    private static final int CONFIRM_DELETE_ARTIST_ID = UniqueCodeUtil.nextUniqueCode();
    private boolean mLibraryArtist;
    private final PrimeArtistPlaybackHandler playbackHandler;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.library_artist_context;
    }

    public ArtistContextMenuProvider(FragmentActivity activity) {
        this(activity, false);
    }

    public ArtistContextMenuProvider(FragmentActivity activity, boolean isLibraryArtist) {
        super(activity);
        this.mLibraryArtist = isLibraryArtist;
        this.playbackHandler = new PrimeArtistPlaybackHandler(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public void onCreateContextMenu(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        super.onCreateContextMenu(activity, menu, tag, position, sourceID);
        if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
            menu.removeItem(R.id.menu_songs_add_to_library);
            menu.removeItem(R.id.menu_songs_in_library);
            menu.removeItem(R.id.MenuArtistContextDeleteFromCirrus);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        Artist artist;
        super.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
        Object tag = menuInfo.targetView.getTag();
        if (tag == null) {
            return false;
        }
        if (tag instanceof ArtistListAdapter.RowViewHolder) {
            ArtistListAdapter.RowViewHolder rowViewHolder = (ArtistListAdapter.RowViewHolder) menuInfo.targetView.getTag();
            if (rowViewHolder == null) {
                return false;
            }
            artist = (Artist) rowViewHolder.mLibraryItem;
        } else {
            if (tag instanceof Artist) {
                artist = (Artist) tag;
            }
            return false;
        }
        String string = MediaProvider.Artists.Tracks.getContentUri("cirrus", Long.parseLong(artist.getId())).toString();
        Uri contentUri = MediaProvider.Artists.Tracks.getContentUri(sourceID, menuInfo.id);
        String name = artist.getName();
        int itemId = item.getItemId();
        setClickedEntityIdAndType(artist.getAsin(), EntityIdType.ASIN);
        if (itemId == R.id.MenuArtistContextDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
            if (isFreeTierIneligible(itemId, artist)) {
                UpsellUtil.showBlockingUpsell(activity, artist.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.ARTIST);
                return true;
            }
            ContextMenuDownloadComponent.startDownload(activity, string, Uri.parse(string), new NotificationInfo(artist.getName(), null, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ARTIST.intValue(), artist.getId()).toString()));
            return true;
        }
        if (itemId == R.id.MenuArtistContextCancelDownload) {
            sendMetricEvent(ActionType.DOWNLOAD_CANCEL_ALL);
            ContextMenuDownloadComponent.cancelDownload(activity, string);
            return true;
        }
        if (itemId == R.id.MenuArtistContextDelete) {
            sendMetricEvent(ActionType.REMOVE_FROM_DEVICE_OVERFLOW);
            ContextMenuDownloadComponent.deleteDeviceContent(activity, CONFIRM_DELETE_ARTIST_ID, artist.getContentUri(), new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder(artist, R.string.dmusic_artist_confirm_delete_title, activity.getString(R.string.dmusic_artist_confirm_delete_msg, name), 0, 0, 0, 0, R.string.artist_removed_from_device_content_description));
            return true;
        }
        if (itemId == R.id.MenuArtistContextDeleteFromCirrus) {
            sendMetricEvent(ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
            ContextMenuDownloadComponent.deleteCloudContent(activity, CONFIRM_DELETE_ARTIST_ID, artist.getContentUri(), new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder(artist, R.string.dmusic_context_delete_cloud, activity.getResources().getQuantityString(R.plurals.dmusic_artist_confirm_delete_cloud_msg, (int) artist.getTrackCount(), Integer.valueOf((int) artist.getTrackCount())), R.string.dmusic_artist_toast_deleting_track_cloud, R.string.dmusic_button_yes, R.string.dmusic_button_no, R.string.artist_removed_content_description, R.string.artist_removed_from_device_content_description), false, null);
            return true;
        }
        if (itemId == R.id.MenuExploreArtist) {
            activity.startActivity(ShopLinkUtil.getArtistExploreIntent(activity, Long.valueOf(menuInfo.id).toString(), name, ItemWrapper$ItemType.ARTIST));
            MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
            return true;
        }
        if (itemId == R.id.MenuArtistAddToPlaylist) {
            sendMetricEvent(ActionType.ADD_ALL_TO_PLAYLIST_ATTEMPT_OVERFLOW);
            activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, contentUri, "album_id,disc_num,track_num", this.mPageType));
            return true;
        }
        if (itemId == R.id.MenuArtistSimilarRecommend) {
            sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
            goToSimilarRecommendationsFragment(PaginatedDataType.PLAYLIST, artist.getAsin());
            return true;
        }
        if (itemId == R.id.MenuArtistAddToPlayQueue) {
            sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
            if (isFreeTierIneligible(itemId, artist)) {
                UpsellUtil.showBlockingUpsell(activity, artist.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ARTIST);
                return true;
            }
            NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, false);
            return true;
        }
        if (itemId == R.id.MenuArtistPlayNext) {
            sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
            if (isFreeTierIneligible(itemId, artist)) {
                UpsellUtil.showBlockingUpsell(activity, artist.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ARTIST);
                return true;
            }
            NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, true);
            return true;
        }
        if (itemId == R.id.MenuArtistContextShare) {
            new ArtistShareProvider(activity, EndpointsProvider.get().getMusicDomain(), artist.getAsin(), artist.getName(), null).startShare();
            return true;
        }
        if (itemId == R.id.MenuArtistStationFromAnything) {
            StationUtils.startSFA(activity, artist.getAsin(), "ARTIST_SEED", PlaybackPageType.ARTISTS_LIST, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(artist.getAsin(), EntityIdType.SFA_ARTIST_SEED, getPageType());
            return true;
        }
        if (itemId == R.id.MenuArtistGoToEntityPlaylist) {
            goToEntityPlaylistFragment(artist.getAsin(), PlaylistSeedEntityType.ARTIST);
            return true;
        }
        if (itemId == R.id.MenuArtistPlay) {
            this.playbackHandler.startPlayback(new PrimeArtist(artist.getName(), artist.getAsin(), null, (int) artist.getTrackCount(), (int) artist.getAlbumCount(), null, ContentOwnershipStatus.fromValue(artist.getMinContentOwnershipStatusValue()), artist.getCatalogStatusTiers()), PrimeCollectionTask.Task.PLAYBACK_SHUFFLE);
            return true;
        }
        return false;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Artist artist;
        if (tag instanceof ArtistListAdapter.RowViewHolder) {
            artist = (Artist) ((ArtistListAdapter.RowViewHolder) tag).mLibraryItem;
        } else if (!(tag instanceof Artist)) {
            return;
        } else {
            artist = (Artist) tag;
        }
        setStyledHeaderTitle(artist.getName(), menu);
        menu.removeItem(R.id.MenuArtistSimilarRecommend);
        if (!artist.isRemote()) {
            menu.removeItem(R.id.MenuArtistContextDownload);
            menu.removeItem(R.id.MenuArtistContextCancelDownload);
            menu.removeItem(R.id.MenuArtistContextDeleteFromCirrus);
            menu.removeItem(R.id.MenuArtistSimilarRecommend);
            menu.removeItem(R.id.MenuArtistPlay);
            if (artist.getMinContentOwnershipStatusValue() < 200 && artist.getDownloadState() == 5) {
                menu.removeItem(R.id.MenuArtistContextDelete);
            }
        } else {
            int downloadState = artist.getDownloadState();
            if (downloadState == 0) {
                menu.removeItem(R.id.MenuArtistContextDownload);
                menu.removeItem(R.id.MenuArtistContextCancelDownload);
            } else if (downloadState == 1 || downloadState == 2 || downloadState == 3 || downloadState == 4) {
                menu.removeItem(R.id.MenuArtistContextDownload);
                menu.removeItem(R.id.MenuArtistContextDelete);
            } else if (downloadState == 5) {
                menu.removeItem(R.id.MenuArtistContextCancelDownload);
                menu.removeItem(R.id.MenuArtistContextDelete);
            } else {
                menu.removeItem(R.id.MenuArtistContextCancelDownload);
            }
            int downloadState2 = artist.getDownloadState();
            if (downloadState2 == 0 || downloadState2 == 1 || downloadState2 == 2) {
                menu.removeItem(R.id.MenuArtistContextDelete);
            }
        }
        if (TextUtils.isEmpty(artist.getAsin()) || ShopLinkUtil.isArtistBlacklisted(artist.getName()) || !AmazonApplication.getCapabilities().isSharingSupported()) {
            menu.removeItem(R.id.MenuArtistContextShare);
        }
        updateExploreArtistContextMenu(activity, menu, artist.getName());
        if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.MenuArtistAddToPlayQueue);
            menu.removeItem(R.id.MenuArtistPlayNext);
        }
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            menu.removeItem(R.id.MenuArtistSimilarRecommend);
        }
        int[] iArr = {R.id.MenuArtistContextDownload, R.id.MenuArtistPlayNext, R.id.MenuArtistAddToPlayQueue};
        for (int i = 0; i < 3; i++) {
            int i2 = iArr[i];
            if (isFreeTierIneligible(i2, artist)) {
                StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(i2), this.mActivity);
            }
        }
        if (TextUtils.isEmpty(artist.getAsin())) {
            menu.removeItem(R.id.MenuArtistStationFromAnything);
            menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
            menu.removeItem(R.id.MenuArtistAddToPlaylist);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuArtistStationFromAnything);
        } else {
            menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
        }
        if (Feature.hide_artist_context_menu_play_and_download.isEnabled()) {
            menu.removeItem(R.id.MenuArtistContextDownload);
            menu.removeItem(R.id.MenuArtistContextCancelDownload);
            menu.removeItem(R.id.MenuArtistPlay);
        }
    }

    private boolean isFreeTierIneligible(final int itemId, final Artist artist) {
        if (itemId != R.id.MenuArtistAddToPlayQueue) {
            if (itemId == R.id.MenuArtistContextDownload) {
                return (!UserSubscriptionUtil.isNightwingOnly() || artist.isAllOwned() || this.mLibraryArtist) ? false : true;
            }
            if (itemId != R.id.MenuArtistPlayNext) {
                return false;
            }
        }
        return UserSubscriptionUtil.isNightwingOnly() && !artist.isAllOwned();
    }
}
