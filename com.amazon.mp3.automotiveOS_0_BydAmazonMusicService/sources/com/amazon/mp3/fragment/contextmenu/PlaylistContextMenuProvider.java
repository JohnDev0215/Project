package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.detailpages.actions.TrackListContainerBaseViewModelFetcher;
import com.amazon.mp3.detailpages.playlist.playlistSongSearch.PlaylistSongSearchHandler;
import com.amazon.mp3.dialog.RenamePlaylistDialog;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.adapter.PlaylistsListAdapter;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.moderation.ModerationEntity;
import com.amazon.mp3.moderation.ModerationHandler;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.operation.CatalogPlaylistShareMetadata;
import com.amazon.mp3.playlist.operation.PlaylistPrivacyMetadata;
import com.amazon.mp3.playlist.operation.PlaylistShareMetadata;
import com.amazon.mp3.playlist.operation.UserPlaylistShareMetadata;
import com.amazon.mp3.playlist.support.PlaylistPrivacyOperationSupportManager;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MathUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistContextMenuProvider extends ContextMenuProvider<PlaylistsListAdapter.RowViewHolder> {
    private boolean mAllowsDownload;
    private boolean mAllowsEdit;
    protected int mHeaderCount;
    protected PlaylistContextMenuProviderListener mProviderListener;
    private final String TAG = getClass().getSimpleName();
    private PlaylistModelConfiguration playlistModelConfiguration = new PlaylistModelConfiguration();

    public interface PlaylistContextMenuProviderListener extends RenamePlaylistDialog.OnPlaylistRenamedListener {
        boolean canStartNewTask();

        void deleteContent(int position, AbstractItem item);

        ContextMenuPageType getContextMenuPageType();

        void removeDeletePlaylistMenuItem(ContextMenu menu);

        void removeDownloadPlaylistMenuItem(ContextMenu menu);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.library_playlist_context;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected boolean shouldDisplayMenuForItem(int position, Object tag) {
        return position != 0;
    }

    public PlaylistContextMenuProvider(BaseActivity activity, int headerCount, PlaylistContextMenuProviderListener listener, boolean allowsDownload) {
        this.mProviderListener = listener;
        this.mHeaderCount = headerCount;
        this.mAllowsEdit = !UserSubscriptionUtil.isNightwingOnly() || AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
        this.mAllowsDownload = allowsDownload;
        init(activity);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(final FragmentActivity fragmentActivity, MenuItem menuItem, AdapterView.AdapterContextMenuInfo adapterContextMenuInfo, String str) {
        int trackCount;
        String thumbnailArtUrl;
        String str2;
        boolean zIsCatalog;
        String luid;
        String string;
        boolean zEquals;
        String str3;
        String str4;
        Long lValueOf;
        AbstractItem abstractItem;
        PlaylistShareMetadata userPlaylistShareMetadata;
        super.onContextMenuItemSelected(fragmentActivity, menuItem, adapterContextMenuInfo, str);
        DefaultTrackInformationFetcher defaultTrackInformationFetcher = new DefaultTrackInformationFetcher(new TrackListContainerBaseViewModelFetcher());
        PlaybackPageType playbackPageType = PlaybackPageType.PLAYLIST_DETAIL;
        final PlayCollectionAction playCollectionAction = new PlayCollectionAction(defaultTrackInformationFetcher, playbackPageType);
        int i = adapterContextMenuInfo.position - this.mHeaderCount;
        int itemId = menuItem.getItemId();
        Object tag = adapterContextMenuInfo.targetView.getTag();
        if (tag instanceof PlaylistsListAdapter.RowViewHolder) {
            PlaylistsListAdapter.RowViewHolder rowViewHolder = (PlaylistsListAdapter.RowViewHolder) adapterContextMenuInfo.targetView.getTag();
            Playlist playlist = (Playlist) rowViewHolder.mLibraryItem;
            if (playlist == null) {
                return false;
            }
            int trackCount2 = (int) playlist.getTrackCount();
            String str5 = rowViewHolder.mAsin;
            luid = playlist.getLuid();
            String version = playlist.getVersion();
            lValueOf = Long.valueOf(rowViewHolder.mPlaylistId);
            zEquals = rowViewHolder.mIsSmartPlaylist;
            boolean z = rowViewHolder.mIsCatalogPlaylist;
            String string2 = rowViewHolder.mName.getText().toString();
            str2 = version;
            string = rowViewHolder.mDownloadId;
            abstractItem = playlist;
            str4 = string2;
            trackCount = trackCount2;
            thumbnailArtUrl = null;
            zIsCatalog = z;
            str3 = str5;
        } else if (tag instanceof Playlist) {
            Playlist playlist2 = (Playlist) tag;
            String asin = playlist2.getAsin();
            String luid2 = playlist2.getLuid();
            String version2 = playlist2.getVersion();
            lValueOf = Long.valueOf(Long.parseLong(playlist2.getId()));
            zEquals = playlist2.getType().equals("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist");
            zIsCatalog = playlist2.isCatalog();
            String name = playlist2.getName();
            str2 = version2;
            str3 = asin;
            thumbnailArtUrl = null;
            string = playlist2.getContentUri().toString();
            trackCount = (int) playlist2.getTrackCount();
            str4 = name;
            luid = luid2;
            abstractItem = playlist2;
        } else {
            if (!(tag instanceof CatalogPlaylist)) {
                return false;
            }
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) tag;
            String asin2 = catalogPlaylist.getAsin();
            String luid3 = catalogPlaylist.getLuid();
            String version3 = catalogPlaylist.getVersion();
            String title = catalogPlaylist.getTitle();
            String string3 = catalogPlaylist.getContentUri().toString();
            trackCount = catalogPlaylist.getTrackCount();
            thumbnailArtUrl = catalogPlaylist.getThumbnailArtUrl();
            str2 = version3;
            zIsCatalog = true;
            luid = luid3;
            string = string3;
            zEquals = false;
            str3 = asin2;
            str4 = title;
            lValueOf = null;
            abstractItem = catalogPlaylist;
        }
        setClickedEntityIdAndType(str3, EntityIdType.ASIN);
        final Uri contentUri = abstractItem.getContentUri();
        switch (itemId) {
            case R.id.MenuPlaylistAddToPlayQueue /* 2131427607 */:
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                if (UserSubscriptionUtil.isNightwingOnly() && i != 2 && !abstractItem.isAllOwned()) {
                    UpsellUtil.showBlockingUpsell(fragmentActivity, str3, UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.PLAYLIST);
                    return true;
                }
                addToPlayQueue(zEquals, zIsCatalog, lValueOf, str3, str, false);
                return true;
            case R.id.MenuPlaylistAddToPresets /* 2131427608 */:
                CarModePresetsUtility.addToPresets(fragmentActivity, abstractItem, getPageType());
                return false;
            case R.id.MenuPlaylistCatalogStatus /* 2131427609 */:
            case R.id.MenuPlaylistContextAddToLibrary /* 2131427611 */:
            case R.id.MenuPlaylistContextDeleteFromLibrary /* 2131427614 */:
            case R.id.MenuPlaylistContextDownloaded /* 2131427616 */:
            case R.id.MenuPlaylistContextInLibrary /* 2131427618 */:
            case R.id.MenuPlaylistDownload /* 2131427622 */:
            case R.id.MenuPlaylistEdit /* 2131427623 */:
            case R.id.MenuPlaylistShare /* 2131427631 */:
            default:
                Log.warning(this.TAG, "onContextMenuSelected %s is not handled. Please add a case", Integer.valueOf(itemId));
                return false;
            case R.id.MenuPlaylistContextAddSongsToPlaylist /* 2131427610 */:
                sendMetricEvent(ActionType.ADD_TO_PLAYLIST_ATTEMPT_OVERFLOW);
                if (AmazonApplication.getCapabilities().isPlaylistSongSearchEnabled() && ConnectivityUtil.hasAnyInternetConnection(fragmentActivity.getApplicationContext()) && luid != null && str4 != null) {
                    new PlaylistSongSearchHandler(fragmentActivity, luid, str4).startPlaylistSongSearchActivty();
                } else {
                    addSongsToPlaylist(fragmentActivity, adapterContextMenuInfo.id, true, str);
                }
                return true;
            case R.id.MenuPlaylistContextDelete /* 2131427612 */:
            case R.id.MenuPlaylistContextDeleteDevice /* 2131427613 */:
            case R.id.MenuPlaylistDelete /* 2131427621 */:
                sendMetricEvent(ActionType.DELETE_PLAYLIST);
                this.mProviderListener.deleteContent(i, abstractItem);
                return true;
            case R.id.MenuPlaylistContextDownload /* 2131427615 */:
                sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
                if ((UserSubscriptionUtil.isNightwingOnly() && i != 2 && (!abstractItem.isAllOwned() || isPartiallyOwned(abstractItem) || !this.mAllowsDownload)) || this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(abstractItem, zIsCatalog)) {
                    UpsellUtil.showBlockingUpsell(fragmentActivity, str3, UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
                    return true;
                }
                Uri imageUri = getImageUri(abstractItem.getContentUri(), lValueOf, str3);
                ContextMenuDownloadComponent.startDownload(fragmentActivity, string, ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(Uri.parse(string)), new NotificationInfo(str4, null, imageUri == null ? null : imageUri.toString()));
                return false;
            case R.id.MenuPlaylistContextEdit /* 2131427617 */:
                sendMetricEvent(ActionType.EDIT_PLAYLIST);
                if (UserSubscriptionUtil.isNightwingOnly() && !this.mAllowsEdit) {
                    UpsellUtil.showBlockingUpsell(fragmentActivity, str3, UpsellReason.OTHER, UpsellSourceEntity.PLAYLIST);
                    return true;
                }
                addSongsToPlaylist(fragmentActivity, adapterContextMenuInfo.id, false, str);
                return true;
            case R.id.MenuPlaylistContextRename /* 2131427619 */:
                new RenamePlaylistDialog(fragmentActivity, (Playlist) abstractItem, str4, this.mProviderListener).show();
                return false;
            case R.id.MenuPlaylistContextShare /* 2131427620 */:
                if (zIsCatalog) {
                    userPlaylistShareMetadata = new CatalogPlaylistShareMetadata(str4, str3, thumbnailArtUrl);
                } else {
                    Uri contentUri2 = MediaProvider.UdoPlaylists.getContentUri(str, lValueOf.longValue());
                    userPlaylistShareMetadata = new UserPlaylistShareMetadata(str4, str3, thumbnailArtUrl, trackCount, luid, str2, PlaylistUtil.getPlaylistVisibilityStateFromUri(fragmentActivity, contentUri2), contentUri2, lValueOf);
                }
                new PlaylistPrivacyOperationSupportManager(fragmentActivity).sharePlaylist(userPlaylistShareMetadata);
                return true;
            case R.id.MenuPlaylistMakePrivate /* 2131427624 */:
                setUserPlaylistVisibility(fragmentActivity, new PlaylistPrivacyMetadata(str4, trackCount, MediaProvider.UdoPlaylists.getContentUri(str, lValueOf.longValue()), lValueOf), MediaProvider.Playlists.VisibilityState.PRIVATE);
                return true;
            case R.id.MenuPlaylistMakePublic /* 2131427625 */:
                setUserPlaylistVisibility(fragmentActivity, new PlaylistPrivacyMetadata(str4, trackCount, MediaProvider.UdoPlaylists.getContentUri(str, lValueOf.longValue()), lValueOf), MediaProvider.Playlists.VisibilityState.PUBLIC);
                return true;
            case R.id.MenuPlaylistPlay /* 2131427626 */:
                sendMetricEvent(ActionType.PLAY_NOW_OVERFLOW);
                StartPlaybackUtil.startCollectionPlayback(fragmentActivity.getApplicationContext(), str4, contentUri, null, 0, false, true, null, playbackPageType, false, ControlSource.APP_UI, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.1
                    @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                    public void handlePlayback() {
                        playCollectionAction.beginPlayQueuePlayback(fragmentActivity, 0, true, contentUri, Observable.just(null), true, null);
                    }
                });
                return true;
            case R.id.MenuPlaylistPlayNext /* 2131427627 */:
                sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
                if (UserSubscriptionUtil.isNightwingOnly() && i != 2 && !abstractItem.isAllOwned()) {
                    UpsellUtil.showBlockingUpsell(fragmentActivity, str3, UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.PLAYLIST);
                    return true;
                }
                addToPlayQueue(zEquals, zIsCatalog, lValueOf, str3, str, true);
                return true;
            case R.id.MenuPlaylistRemoveDownload /* 2131427628 */:
                sendMetricEvent(ActionType.REMOVE_FROM_DEVICE_OVERFLOW);
                this.mProviderListener.deleteContent(i, abstractItem);
                return true;
            case R.id.MenuPlaylistRemoveFromPresets /* 2131427629 */:
                CarModePresetsUtility.removeFromPresets(fragmentActivity, abstractItem, getPageType());
                return false;
            case R.id.MenuPlaylistReport /* 2131427630 */:
                sendMetricEvent(ActionType.REPORT_OVERFLOW);
                new ModerationHandler(fragmentActivity, abstractItem, ModerationEntity.PLAYLIST).startModeration();
                return false;
            case R.id.MenuPlaylistShuffle /* 2131427632 */:
                sendMetricEvent(ActionType.SHUFFLE_ON_OVERFLOW);
                final int iGenerateRandomStartIndex = MathUtil.INSTANCE.generateRandomStartIndex(trackCount);
                StartPlaybackUtil.startCollectionPlayback(fragmentActivity.getApplicationContext(), str4, contentUri, null, iGenerateRandomStartIndex, true, true, null, playbackPageType, false, ControlSource.APP_UI, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.2
                    @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
                    public void handlePlayback() {
                        playCollectionAction.beginPlayQueuePlayback(fragmentActivity, iGenerateRandomStartIndex, true, contentUri, Observable.just(null), true, null);
                    }
                });
                return true;
            case R.id.MenuPlaylistSimilarRecommend /* 2131427633 */:
                sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
                goToSimilarRecommendationsFragment(PaginatedDataType.PLAYLIST, str3);
                return false;
            case R.id.MenuPlaylistStationFromAnything /* 2131427634 */:
                final Uri contentUri3 = abstractItem.getContentUri();
                if (MediaProvider.UdoPlaylists.isUdoPlaylist(contentUri3) || isFollowedPlaylist(contentUri3, str3, luid)) {
                    final AbstractItem abstractItem2 = abstractItem;
                    final String str6 = luid;
                    ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onContextMenuItemSelected$0(abstractItem2, contentUri3, fragmentActivity, str6);
                        }
                    });
                    return true;
                }
                if (!MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri3)) {
                    return true;
                }
                StationUtils.startSFA(fragmentActivity, abstractItem.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLISTS_LIST, ControlSource.APP_UI);
                StationUtils.sendStartSFAUiClickMetrics(abstractItem.getAsin(), EntityIdType.SFA_PLAYLIST_SEED, getPageType());
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onContextMenuItemSelected$0(AbstractItem abstractItem, Uri uri, FragmentActivity fragmentActivity, String str) {
        List<String> asinListSeedFromFollowedPlaylistId;
        List<Track> listConvertAsinsToTracks;
        if (abstractItem instanceof CatalogPlaylist) {
            listConvertAsinsToTracks = StationUtils.getTrackListFromSharedPlaylistWithLimitation((CatalogPlaylist) abstractItem);
            asinListSeedFromFollowedPlaylistId = StationUtils.convertTracksToAsins(listConvertAsinsToTracks);
        } else if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
            asinListSeedFromFollowedPlaylistId = StationUtils.getAsinListSeedFromUserPlaylistLuid(fragmentActivity, str, abstractItem.getSource());
            listConvertAsinsToTracks = StationUtils.convertAsinsToTracks(asinListSeedFromFollowedPlaylistId);
        } else {
            asinListSeedFromFollowedPlaylistId = StationUtils.getAsinListSeedFromFollowedPlaylistId(fragmentActivity, abstractItem.getId());
            listConvertAsinsToTracks = StationUtils.convertAsinsToTracks(asinListSeedFromFollowedPlaylistId);
        }
        StationUtils.startSFA(fragmentActivity, listConvertAsinsToTracks, "TRACK_LIST_SEED", PlaybackPageType.PLAYLISTS_LIST);
        StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", asinListSeedFromFollowedPlaylistId), EntityIdType.SFA_TRACK_LIST_SEED, getPageType());
    }

    private Uri getImageUri(Uri itemUri, Long playlistId, String playlistAsin) {
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(itemUri)) {
            return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO.intValue(), playlistId.longValue());
        }
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(itemUri)) {
            return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_SMART.intValue(), playlistId.longValue());
        }
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(itemUri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(itemUri)) {
            return ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), playlistAsin);
        }
        return null;
    }

    private boolean isFollowedPlaylist(Uri itemUri, String asin, String luid) {
        return MediaProvider.PrimePlaylists.isPrimePlaylist(itemUri) && luid.contains(asin);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(final Activity activity, final ContextMenu contextMenu, Object obj, int i, String str) {
        Long lValueOf;
        String name;
        String str2;
        final String luid;
        boolean z;
        int trackCount;
        boolean z2;
        boolean zIsCatalog;
        List list;
        final AbstractItem abstractItem = null;
        if (obj instanceof PlaylistsListAdapter.RowViewHolder) {
            PlaylistsListAdapter.RowViewHolder rowViewHolder = (PlaylistsListAdapter.RowViewHolder) obj;
            String str3 = rowViewHolder.mPlaylistType;
            Playlist playlist = (Playlist) rowViewHolder.mLibraryItem;
            if (playlist == null) {
                return;
            }
            Long lValueOf2 = Long.valueOf(rowViewHolder.mPlaylistId);
            String string = rowViewHolder.mName.getText().toString();
            boolean z3 = rowViewHolder.mIsSmartPlaylist;
            zIsCatalog = rowViewHolder.mIsCatalogPlaylist;
            z = rowViewHolder.mIsEntityPlaylist;
            luid = playlist.getLuid();
            trackCount = (int) playlist.getTrackCount();
            name = string;
            z2 = z3;
            str2 = str3;
            abstractItem = playlist;
            lValueOf = lValueOf2;
        } else if (obj instanceof Playlist) {
            Playlist playlist2 = (Playlist) obj;
            String type = playlist2.getType();
            lValueOf = Long.valueOf(Long.parseLong(playlist2.getId()));
            String luid2 = playlist2.getLuid();
            name = playlist2.getName();
            zIsCatalog = playlist2.isCatalog();
            boolean zEquals = type.equals("vnd.android.cursor.item/vnd.amazonmp3.smartplaylist");
            int trackCount2 = (int) playlist2.getTrackCount();
            if (!PlaylistUtil.canAddTracksToPlaylist(playlist2, activity)) {
                contextMenu.removeItem(R.id.MenuPlaylistContextAddSongsToPlaylist);
            }
            luid = luid2;
            trackCount = trackCount2;
            z2 = zEquals;
            str2 = type;
            z = false;
            abstractItem = playlist2;
        } else if (obj instanceof CatalogPlaylist) {
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) obj;
            String luid3 = catalogPlaylist.getLuid();
            String title = catalogPlaylist.getTitle();
            boolean zIsPrime = catalogPlaylist.isPrime();
            boolean zIsEntityPlaylist = catalogPlaylist.isEntityPlaylist();
            catalogPlaylist.setCatalogStatusForCatalogOnlyContent(zIsPrime, !zIsPrime);
            int trackCount3 = catalogPlaylist.getTrackCount();
            str2 = "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist";
            luid = luid3;
            lValueOf = null;
            zIsCatalog = true;
            abstractItem = catalogPlaylist;
            z = zIsEntityPlaylist;
            z2 = false;
            name = title;
            trackCount = trackCount3;
        } else {
            lValueOf = null;
            name = null;
            str2 = null;
            luid = null;
            z = false;
            trackCount = 0;
            z2 = false;
            zIsCatalog = false;
        }
        if (abstractItem == null) {
            contextMenu.clear();
            return;
        }
        contextMenu.removeItem(R.id.MenuPlaylistSimilarRecommend);
        if (!AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
            contextMenu.removeItem(R.id.MenuPlaylistMakePublic);
            contextMenu.removeItem(R.id.MenuPlaylistMakePrivate);
        }
        if (!AmazonApplication.getCapabilities().isEntityModerationSupported() || !Feature.dragonfly_fireos_enabled.isEnabled()) {
            contextMenu.removeItem(R.id.MenuPlaylistReport);
        }
        if (isHeader(str2) || (lValueOf != null && lValueOf.longValue() == -1)) {
            contextMenu.clear();
            return;
        }
        if (!TextUtils.isEmpty(name)) {
            setStyledHeaderTitle(name.toString(), contextMenu);
        }
        if (trackCount <= 0 || !StationUtils.isCustomerAllowedToPlaySFAStation() || !ConnectivityUtil.isAvailable(activity.getApplicationContext())) {
            contextMenu.removeItem(R.id.MenuPlaylistStationFromAnything);
        } else if (str2.equals("vnd.android.cursor.item/vnd.amazonmp3.udoplaylist")) {
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    PlaylistContextMenuProvider.lambda$updateContextMenuItems$1(activity, luid, abstractItem, (Subscriber) obj2);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    PlaylistContextMenuProvider.lambda$updateContextMenuItems$2(contextMenu, (Boolean) obj2);
                }
            }, new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    this.f$0.lambda$updateContextMenuItems$3((Throwable) obj2);
                }
            });
        }
        if (z2) {
            contextMenu.removeItem(R.id.MenuPlaylistContextDelete);
            contextMenu.removeItem(R.id.MenuPlaylistContextEdit);
            contextMenu.removeItem(R.id.MenuPlaylistContextRename);
            contextMenu.removeItem(R.id.MenuPlaylistContextAddSongsToPlaylist);
            contextMenu.removeItem(R.id.MenuPlaylistContextShare);
            contextMenu.removeItem(R.id.MenuPlaylistMakePublic);
            contextMenu.removeItem(R.id.MenuPlaylistMakePrivate);
            contextMenu.removeItem(R.id.MenuPlaylistStationFromAnything);
            contextMenu.removeItem(R.id.MenuPlaylistDelete);
            contextMenu.removeItem(R.id.MenuPlaylistContextDeleteDevice);
            contextMenu.removeItem(R.id.MenuPlaylistRemoveDownload);
            contextMenu.removeItem(R.id.MenuPlaylistReport);
            if (!PlaylistUtil.canDownloadPlaylist(MediaProvider.SmartPlaylists.getContentUri(str, lValueOf.longValue()))) {
                contextMenu.removeItem(R.id.MenuPlaylistDownload);
                contextMenu.removeItem(R.id.MenuPlaylistContextDownload);
            }
        } else if (zIsCatalog) {
            contextMenu.removeItem(R.id.MenuPlaylistContextAddSongsToPlaylist);
            contextMenu.removeItem(R.id.MenuPlaylistContextEdit);
            contextMenu.removeItem(R.id.MenuPlaylistContextRename);
            contextMenu.removeItem(R.id.MenuPlaylistMakePublic);
            contextMenu.removeItem(R.id.MenuPlaylistMakePrivate);
            contextMenu.removeItem(R.id.MenuPlaylistDelete);
            contextMenu.removeItem(R.id.MenuPlaylistContextDeleteDevice);
            String profileId = ((CatalogPlaylist) abstractItem).getProfileId();
            if (!((profileId == null || profileId.isEmpty()) ? false : true)) {
                contextMenu.removeItem(R.id.MenuPlaylistReport);
            }
        } else {
            Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(str, lValueOf.longValue());
            int i2 = AnonymousClass3.$SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[PlaylistUtil.getPlaylistVisibilityStateFromUri(activity.getApplicationContext(), contentUri).ordinal()];
            if (i2 == 1) {
                contextMenu.removeItem(R.id.MenuPlaylistMakePublic);
            } else if (i2 == 2) {
                contextMenu.removeItem(R.id.MenuPlaylistMakePrivate);
            } else {
                contextMenu.removeItem(R.id.MenuPlaylistMakePublic);
                contextMenu.removeItem(R.id.MenuPlaylistMakePrivate);
            }
            if (!PlaylistUtil.canRemovePlaylist(activity.getApplicationContext(), contentUri)) {
                contextMenu.removeItem(R.id.MenuPlaylistContextDelete);
            }
            contextMenu.removeItem(R.id.MenuPlaylistContextRename);
            contextMenu.removeItem(R.id.MenuPlaylistReport);
        }
        if (!this.playlistModelConfiguration.shouldShowContextMenuShuffleButton(abstractItem, Boolean.valueOf(abstractItem.isAllOwned()), this.mProviderListener.getContextMenuPageType())) {
            contextMenu.removeItem(R.id.MenuPlaylistShuffle);
        }
        if (abstractItem.isRemote()) {
            contextMenu.removeItem(R.id.MenuPlaylistRemoveDownload);
        } else {
            contextMenu.removeItem(R.id.MenuPlaylistContextDelete);
        }
        if (!zIsCatalog) {
            contextMenu.removeItem(R.id.MenuPlaylistSimilarRecommend);
            contextMenu.removeItem(R.id.MenuAlbumContextShare);
        }
        if ((abstractItem.isAvailableOffline() || !abstractItem.isAvailable()) && !this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(abstractItem, zIsCatalog)) {
            contextMenu.removeItem(R.id.MenuPlaylistContextDownload);
        } else {
            contextMenu.removeItem(R.id.MenuPlaylistRemoveDownload);
        }
        boolean z4 = abstractItem instanceof CatalogPlaylist;
        if (z4 && !((CatalogPlaylist) abstractItem).isFollowed()) {
            contextMenu.removeItem(R.id.MenuPlaylistContextDelete);
        }
        if (!SequencerHelper.canModifyQueue()) {
            contextMenu.removeItem(R.id.MenuPlaylistAddToPlayQueue);
            contextMenu.removeItem(R.id.MenuPlaylistPlayNext);
        }
        boolean z5 = !zIsCatalog && "cirrus-local".equals(str);
        if (!AmazonApplication.getCapabilities().isSharingSupported() || z5) {
            contextMenu.removeItem(R.id.MenuPlaylistContextShare);
        }
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            contextMenu.removeItem(R.id.MenuPlaylistSimilarRecommend);
        }
        if (z5) {
            contextMenu.removeItem(R.id.MenuPlaylistMakePublic);
            contextMenu.removeItem(R.id.MenuPlaylistMakePrivate);
        }
        if (z) {
            contextMenu.removeItem(R.id.MenuPlaylistContextShare);
            contextMenu.removeItem(R.id.MenuPlaylistAddToPresets);
        }
        if (!abstractItem.isAllOwned()) {
            if (!isPartiallyOwned(abstractItem)) {
                StringUtil.tryResetMenuTitleColorForNightwing(contextMenu.findItem(R.id.MenuPlaylistContextDownload), this.mActivity);
            }
            StringUtil.tryResetMenuTitleColorForNightwing(contextMenu.findItem(R.id.MenuPlaylistPlayNext), this.mActivity);
            StringUtil.tryResetMenuTitleColorForNightwing(contextMenu.findItem(R.id.MenuPlaylistAddToPlayQueue), this.mActivity);
        }
        Uri contentUri2 = abstractItem.getContentUri();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            boolean zIsUdoPlaylist = MediaProvider.UdoPlaylists.isUdoPlaylist(contentUri2);
            if (zIsUdoPlaylist && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled() && (!abstractItem.isRemote() || abstractItem.getDownloadState() == 0 || zIsCatalog)) {
                contextMenu.removeItem(R.id.MenuPlaylistContextDownload);
            } else if (!zIsUdoPlaylist && !abstractItem.isAllOwned()) {
                StringUtil.dimTextColor(contextMenu.findItem(R.id.MenuPlaylistContextDownload), this.mActivity);
            }
        }
        if (z4 && (list = (List) ((CatalogPlaylist) abstractItem).getTracks()) != null && list.get(0) != null && ((CatalogPlaylistTrack) list.get(0)).getAssetType() == PrimePlaylistTracksTable.AssetType.VIDEO) {
            contextMenu.removeItem(R.id.MenuPlaylistContextDelete);
            contextMenu.removeItem(R.id.MenuPlaylistStationFromAnything);
            contextMenu.removeItem(R.id.MenuPlaylistContextDownload);
            contextMenu.removeItem(R.id.MenuPlaylistPlayNext);
            contextMenu.removeItem(R.id.MenuPlaylistAddToPlayQueue);
        }
        updatePresetsContextMenu(activity, abstractItem, contextMenu, R.id.MenuPlaylistAddToPresets, R.id.MenuPlaylistRemoveFromPresets);
        this.mProviderListener.removeDeletePlaylistMenuItem(contextMenu);
        if (!this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(abstractItem, zIsCatalog)) {
            this.mProviderListener.removeDownloadPlaylistMenuItem(contextMenu);
        }
        if (!this.playlistModelConfiguration.shouldShowContextMenuPlayButton(abstractItem, this.mProviderListener.getContextMenuPageType())) {
            contextMenu.removeItem(R.id.MenuPlaylistPlay);
        }
        if (this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(abstractItem, zIsCatalog)) {
            StringUtil.dimTextColor(contextMenu.findItem(R.id.MenuPlaylistContextDownload), this.mActivity);
        }
        if (!this.mAllowsDownload && !abstractItem.isAllOwned()) {
            contextMenu.removeItem(R.id.MenuPlaylistContextDownload);
        }
        if (this.mAllowsEdit) {
            return;
        }
        contextMenu.removeItem(R.id.MenuPlaylistContextEdit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$1(Activity activity, String str, AbstractItem abstractItem, Subscriber subscriber) {
        subscriber.onNext(Boolean.valueOf(StationUtils.containsValidAsinsInUserPlaylist(activity, str, abstractItem.getSource())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$2(ContextMenu contextMenu, Boolean bool) {
        if (bool.booleanValue()) {
            return;
        }
        contextMenu.removeItem(R.id.MenuPlaylistStationFromAnything);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateContextMenuItems$3(Throwable th) {
        Log.error(this.TAG, "Error determining if the playlist has a list of asins for SFA support");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState;

        static {
            int[] iArr = new int[MediaProvider.Playlists.VisibilityState.values().length];
            $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState = iArr;
            try {
                iArr[MediaProvider.Playlists.VisibilityState.PUBLIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[MediaProvider.Playlists.VisibilityState.PRIVATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[MediaProvider.Playlists.VisibilityState.NO_STATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void addSongsToPlaylist(Activity activity, long id, boolean skipEditPlaylist, String sourceID) {
        if (this.mProviderListener.canStartNewTask()) {
            PlaylistUtil.startEditPlaylist(activity, PlaylistUtil.withAppendedScratchArgument(MediaProvider.UdoPlaylists.getContentUri(sourceID, id)), skipEditPlaylist);
        }
    }

    private boolean isHeader(String playlistType) {
        return "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist.header".equals(playlistType) || "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist.header".equals(playlistType) || "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist.header".equals(playlistType);
    }

    private void addToPlayQueue(boolean isSmartPlaylist, boolean isCatalogPlaylist, Long playlistId, String playlistAsin, String sourceID, boolean playNext) {
        Uri contentUri;
        if (isCatalogPlaylist) {
            contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri(sourceID, playlistAsin);
        } else if (isSmartPlaylist) {
            contentUri = MediaProvider.SmartPlaylists.Tracks.getContentUri(sourceID, playlistId.longValue());
        } else {
            contentUri = MediaProvider.UdoPlaylistTracks.getContentUri(sourceID, playlistId.longValue());
        }
        NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, playNext);
    }

    private void setUserPlaylistVisibility(final FragmentActivity activity, final PlaylistPrivacyMetadata playlistPrivacyMetadata, final MediaProvider.Playlists.VisibilityState visibility) {
        new PlaylistPrivacyOperationSupportManager(activity).updatePlaylistVisibilityWithConfirmationDialogIfNeeded(playlistPrivacyMetadata, visibility);
    }

    private boolean isPartiallyOwned(AbstractItem abstractItem) {
        return (abstractItem.isNotOwned() || abstractItem.isAllOwned()) ? false : true;
    }
}
