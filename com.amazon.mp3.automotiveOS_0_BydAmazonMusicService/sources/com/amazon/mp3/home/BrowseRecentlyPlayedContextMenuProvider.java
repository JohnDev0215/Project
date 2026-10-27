package com.amazon.mp3.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.DefaultRecentlyPlayedItemStateProvider;
import com.amazon.mp3.library.adapter.GenreListAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.fragment.AlbumDetailFragment;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.library.fragment.GenreDetailFragment;
import com.amazon.mp3.library.fragment.PlaylistDetailFragment;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.RecentlyPlayedItemStateProvider;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.sharing.RecentlyPlayedShareProviderHelper;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ShareProvider;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class BrowseRecentlyPlayedContextMenuProvider extends ContextMenuProvider {
    private static final String TAG = "BrowseRecentlyPlayedContextMenuProvider";
    private FragmentActivity mActivity;
    private DownloadListener mDownloadListener = new DownloadListener(new HashSet()) { // from class: com.amazon.mp3.home.BrowseRecentlyPlayedContextMenuProvider.1
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Group group, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Item item) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Group group) {
            BrowseRecentlyPlayedContextMenuProvider.this.mStateProvider.setDownloadState(BrowseRecentlyPlayedContextMenuProvider.this.convertToGroupCollectionUri(s), MusicDownloader.toAppDownloadState(downloadState));
        }
    };
    private final RecentlyPlayedItemStateProvider mStateProvider;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.recently_played_item_menu;
    }

    public BrowseRecentlyPlayedContextMenuProvider(final FragmentActivity activity) {
        this.mActivity = activity;
        MusicDownloader.getInstance(activity).registerDownloadListener(this.mDownloadListener);
        this.mStateProvider = DefaultRecentlyPlayedItemStateProvider.getInstance(activity.getApplicationContext());
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected boolean shouldDisplayMenuForItem(int position, Object tag) {
        RecentlyPlayedItem.Type itemType;
        RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) tag;
        if (recentlyPlayedItem == null || (itemType = recentlyPlayedItem.getItemType()) == RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS || itemType == RecentlyPlayedItem.Type.ALEXA_RECENTS || itemType == RecentlyPlayedItem.Type.CASTING_RECENTS) {
            return false;
        }
        try {
        } catch (IllegalArgumentException e) {
            Log.error(TAG, "shouldDisplayMenuForItem failed", e);
        }
        return !"vnd.android.cursor.item/vnd.amazonmp3.track".equals(DefaultUriMatcher.getType(recentlyPlayedItem.getContentUri()));
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(final Activity activity, final ContextMenu menu, final Object tag, final int position, final String sourceID) {
        RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) tag;
        if (recentlyPlayedItem == null) {
            return;
        }
        setStyledHeaderTitle(recentlyPlayedItem.getTitle(), menu);
        if (!shouldShowStationFromAnything(recentlyPlayedItem) || !StationUtils.isCustomerAllowedToPlaySFAStation()) {
            menu.findItem(R.id.MenuStationFromAnything).setVisible(false);
        }
        if (!shouldShowDownloadMenuItem(recentlyPlayedItem)) {
            menu.findItem(R.id.MenuDownload).setVisible(false);
        } else if (!shouldEnableDownloadMenuItem(recentlyPlayedItem)) {
            menu.findItem(R.id.MenuDownload).setEnabled(false);
        }
        if (!recentlyPlayedItem.shouldShowShareMenuItem() || shouldDisableSharingStation(recentlyPlayedItem)) {
            menu.findItem(R.id.MenuShare).setVisible(false);
        } else {
            recentlyPlayedItem.updateShareMenuItemTitle(menu);
        }
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.STATION) {
            menu.findItem(R.id.MenuViewDetails).setVisible(false);
        }
        if (UserSubscriptionUtil.isNightwingOnly() && recentlyPlayedItem.getContentUri() != null && MediaProvider.SmartPlaylists.getPlaylistId(recentlyPlayedItem.getContentUri()) != 0) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuDownload), activity);
        }
        if (shouldShowPresetsMenu(recentlyPlayedItem)) {
            updatePresetsContextMenu(activity, recentlyPlayedItem.convertToCatalogContent(), menu, R.id.MenuAddToPresets, R.id.MenuRemoveFromPresets);
        } else {
            menu.removeItem(R.id.MenuAddToPresets);
            menu.removeItem(R.id.MenuRemoveFromPresets);
        }
    }

    private boolean shouldShowPresetsMenu(final RecentlyPlayedItem recentlyPlayedItem) {
        return recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PLAYLIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PRIME_PLAYLIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.STATION;
    }

    private boolean shouldShowStationFromAnything(final RecentlyPlayedItem recentlyPlayedItem) {
        return recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ARTIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST || MediaProvider.PrimePlaylists.isPrimePlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.UdoPlaylists.isUdoPlaylist(recentlyPlayedItem.getContentUri());
    }

    private boolean shouldDisableSharingStation(RecentlyPlayedItem recentlyPlayedItem) {
        return recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.STATION && UserSubscriptionUtil.isNightwingOnly();
    }

    private boolean shouldShowDownloadMenuItem(final RecentlyPlayedItem item) {
        return (item.getItemType() == RecentlyPlayedItem.Type.ALL_SONGS || item.getItemType() == RecentlyPlayedItem.Type.STATION || item.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST || !item.getSource().equals("cirrus") || item.getContentUnavailableReason() != null) ? false : true;
    }

    private boolean shouldEnableDownloadMenuItem(final RecentlyPlayedItem recentlyPlayedItem) {
        return (this.mStateProvider.isDownloaded(recentlyPlayedItem) || this.mStateProvider.isDownloading(recentlyPlayedItem)) ? false : true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(final FragmentActivity activity, final MenuItem item, final AdapterView.AdapterContextMenuInfo menuInfo, final String sourceID) {
        String stringExtra;
        RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) menuInfo.targetView.getTag();
        boolean z = false;
        if (recentlyPlayedItem == null) {
            return false;
        }
        setClickedItemHolder(recentlyPlayedItem);
        setClickedEntityIdAndType(recentlyPlayedItem.getCollectionAsin(), EntityIdType.ASIN);
        switch (item.getItemId()) {
            case R.id.MenuAddToPresets /* 2131427533 */:
                CarModePresetsUtility.addToPresets(activity, recentlyPlayedItem.convertToCatalogContent(), getPageType());
                return false;
            case R.id.MenuDownload /* 2131427575 */:
                sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
                if (!ConnectivityUtil.hasAnyInternetConnection()) {
                    FragmentActivity fragmentActivity = this.mActivity;
                    fragmentActivity.startActivity(NetworkErrorDialogActivity.getStartIntent(fragmentActivity));
                } else {
                    Uri contentUri = recentlyPlayedItem.getContentUri();
                    if (UserSubscriptionUtil.isNightwingOnly() && contentUri != null && MediaProvider.SmartPlaylists.getPlaylistId(contentUri) != 0) {
                        UpsellUtil.showBlockingUpsell(activity, recentlyPlayedItem.getCollectionAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
                        return true;
                    }
                    NotificationInfo notificationInfo = new NotificationInfo(recentlyPlayedItem.getTitle(), null, getImageCacheUri(contentUri, recentlyPlayedItem.getItemType()));
                    if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM && recentlyPlayedItem.getCatalogStatus().isHawkfire() && !recentlyPlayedItem.getOwnershipStatus().isInLibrary()) {
                        z = true;
                    }
                    startDownload(recentlyPlayedItem, notificationInfo, z);
                }
                return true;
            case R.id.MenuRemoveFromPresets /* 2131427635 */:
                CarModePresetsUtility.removeFromPresets(activity, recentlyPlayedItem.convertToCatalogContent(), getPageType());
                return false;
            case R.id.MenuShare /* 2131427637 */:
                ShareProvider shareProvider = RecentlyPlayedShareProviderHelper.getShareProvider(recentlyPlayedItem, this.mActivity);
                if (shareProvider != null) {
                    shareProvider.startShare();
                }
                return true;
            case R.id.MenuStationFromAnything /* 2131427643 */:
                StationUtils.startStationFromAnythingForRecentlyPlayedItem(this.mActivity, recentlyPlayedItem, ControlSource.APP_UI);
                return false;
            case R.id.MenuViewDetails /* 2131427679 */:
                Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(this.mActivity, recentlyPlayedItem.getContentUri());
                String stringExtra2 = intentForContentUri.getStringExtra("com.amazon.mp3.fragment.extra");
                if (AlbumDetailFragment.class.getSimpleName().equals(stringExtra2)) {
                    sendMetricEvent(ActionType.VIEW_ALBUM_OVERFLOW);
                    intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", recentlyPlayedItem.getCollectionAsin());
                    intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
                } else if (GenreDetailFragment.class.getSimpleName().equals(stringExtra2)) {
                    sendMetricEvent(ActionType.EXPLORE_GENRE);
                    handleGenre(intentForContentUri, recentlyPlayedItem.getContentUri());
                } else if (PrimePlaylistDetailFragment.class.getSimpleName().equals(stringExtra2)) {
                    sendMetricEvent(ActionType.SELECT_CURATED_PLAYLIST);
                } else if (PlaylistDetailFragment.class.getSimpleName().equals(stringExtra2)) {
                    sendMetricEvent(ActionType.SELECT_PLAYLIST);
                } else if (BrushFragment.class.getSimpleName().equals(stringExtra2) && (stringExtra = intentForContentUri.getStringExtra("brush_uri_extra")) != null && stringExtra.contains("artist")) {
                    sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW);
                    intentForContentUri.putExtra("brush_asin_extra", recentlyPlayedItem.getCollectionAsin());
                }
                this.mActivity.startActivity(intentForContentUri);
                this.mActivity.overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.home.BrowseRecentlyPlayedContextMenuProvider$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.SMART_PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private String getImageCacheUri(final Uri uri, final RecentlyPlayedItem.Type type) {
        switch (AnonymousClass2.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[type.ordinal()]) {
            case 1:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ARTIST.intValue(), MediaProvider.Artists.getArtistId(uri)).toString();
            case 2:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), MediaProvider.Albums.getAlbumId(uri)).toString();
            case 3:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.GENRE.intValue(), MediaProvider.Genres.getGenreId(uri)).toString();
            case 4:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO.intValue(), MediaProvider.Playlists.getPlaylistId(uri)).toString();
            case 5:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_SMART.intValue(), MediaProvider.SmartPlaylists.getPlaylistId(uri)).toString();
            case 6:
                return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), MediaProvider.Playlists.getPlaylistId(uri)).toString();
            default:
                return null;
        }
    }

    private void handleGenre(final Intent intent, final Uri uri) {
        if (MediaProvider.Genres.isGenre(uri)) {
            Genre genre = AmazonApplication.getLibraryItemFactory().getGenre(uri);
            intent.putExtra("com.amazon.mp3.library.activity.EXTRA_GENRE_COUNT", GenreListAdapter.formatCountText(this.mActivity, (int) genre.getAlbumCount(), (int) genre.getTrackCount()));
            intent.putExtra("com.amazon.mp3.library.activity.EXTRA_GENRE_NAME", genre.getName());
        }
    }

    private void startDownload(final RecentlyPlayedItem recentlyPlayedItem, final NotificationInfo notificationInfo, final boolean downloadNonAddedPrimeTracks) {
        Uri contentUri = recentlyPlayedItem.getContentUri();
        if (!MediaProvider.Tracks.isTrackCollection(contentUri, new String[0])) {
            contentUri = contentUri.buildUpon().appendPath("tracks").build();
        }
        Uri uri = contentUri;
        String string = uri.toString();
        if (DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(this.mActivity)) {
            DownloadSettingDialogFragment.showSettingsDialog(this.mActivity, string, uri, notificationInfo, false);
            return;
        }
        BauhausToastUtils.showTextToast(this.mActivity, R.string.dmusic_download_queue_started, 1);
        this.mStateProvider.setDownloadState(recentlyPlayedItem.getContentUri(), 1);
        this.mDownloadListener.addRequestId(string);
        MusicDownloader.getInstance(this.mActivity).download(string, uri, notificationInfo, downloadNonAddedPrimeTracks, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri convertToGroupCollectionUri(final String uriString) {
        return MediaProvider.Tracks.getNonTrackCollectionUri(Uri.parse(uriString));
    }
}
