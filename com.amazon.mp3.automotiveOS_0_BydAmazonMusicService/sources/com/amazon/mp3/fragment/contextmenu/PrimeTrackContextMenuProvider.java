package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.audioquality.ContextMenuDownloadHelper;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.TrackModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.download.helper.RedownloadStateResolver;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.cta.PrimeTrackTask;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.search.view.SearchFragmentUtils;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.IdGenerator;
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
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.playback.PlaybackExperience;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.share.TrackShareProvider;
import com.amazon.music.views.library.metadata.PlaylistMetadata;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeTrackContextMenuProvider extends ContextMenuProvider<PrimeTrack> {
    protected PrimeTrackContextMenuProviderListener mProviderListener;
    private final EligibilityUtil eligibilityUtil = new EligibilityUtil(null);
    private boolean mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    private TrackModelConfiguration trackModelConfiguration = new TrackModelConfiguration();

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.prime_track_context;
    }

    public static abstract class PrimeTrackContextMenuProviderListener {
        public abstract OnItemFinishedAddingListener getOnAddedListener();

        public abstract TrackStateProvider getTrackStateProvider();

        public abstract void setAdapterStatus(PrimeTrack track, boolean adding, boolean downloading);

        public abstract void updateTrackStatus(PrimeTrack track);

        public abstract void updateTrackStatusForPlaylist(PrimeTrack track);

        public boolean canContinueAction(FragmentActivity activity, PrimeTrack primeTrack, ContentAccessUtil.ContentAccessOperation operation) {
            return ContentAccessUtil.canContinueCatalogAction(activity, primeTrack, operation);
        }

        public ContextMenuPageType getContextMenupageType() {
            return ContextMenuPageType.LONG_PRESS;
        }
    }

    public PrimeTrackContextMenuProvider(FragmentActivity activity, PrimeTrackContextMenuProviderListener listener) {
        this.mProviderListener = listener;
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
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimeTrack) menuInfo.targetView.getTag(), sourceID);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimeTrack) tag, sourceID);
    }

    public boolean onContextMenuItemSelected(final FragmentActivity activity, MenuItem item, final PrimeTrack track, String sourceID) {
        setClickedItemHolder(track);
        setClickedEntityIdAndType(track.getAsin(), EntityIdType.ASIN);
        if (track.getBlockRef() == null) {
            setContentInfo(MetricsUtil.getContentInfoForLibraryContent(-1, MetricsUtil.getContentNameForSearchItem(track, isOfflineLibrary(sourceID))));
        }
        switch (item.getItemId()) {
            case R.id.menu_add_to_playlist /* 2131429111 */:
                if ((UserSubscriptionUtil.isNightwingOnly() && this.mFreeTierUserPlaylistEditingEnabled) || this.mProviderListener.canContinueAction(activity, track, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
                    addToPlaylist(activity, track);
                }
                sendMetricEvent(ActionType.ADD_TO_PLAYLIST_ATTEMPT_OVERFLOW);
                return true;
            case R.id.menu_container /* 2131429112 */:
            case R.id.menu_list /* 2131429116 */:
            case R.id.menu_songs_downloaded /* 2131429121 */:
            case R.id.menu_songs_downloading /* 2131429122 */:
            case R.id.menu_songs_in_library /* 2131429124 */:
            case R.id.menu_songs_purchased /* 2131429126 */:
            case R.id.menu_track_catalog_status /* 2131429128 */:
            case R.id.menu_track_shop_for_artist /* 2131429130 */:
            default:
                return false;
            case R.id.menu_go_to_album /* 2131429113 */:
                if (ContentAccessUtil.canContinueCatalogAction((Context) activity, track, ContentAccessUtil.ContentAccessOperation.BROWSE, false)) {
                    String albumArtist = track.getAlbumArtist();
                    if (albumArtist == null) {
                        albumArtist = track.getArtist();
                    }
                    launchAlbumDetail(activity, track.getAlbumAsin(), track.getAlbum(), albumArtist);
                }
                sendMetricEvent(ActionType.VIEW_ALBUM_OVERFLOW);
                return true;
            case R.id.menu_go_to_artist /* 2131429114 */:
                if (this.mProviderListener.canContinueAction(activity, track, ContentAccessUtil.ContentAccessOperation.BROWSE)) {
                    FragmentActivity fragmentActivity = this.mActivity;
                    if (fragmentActivity instanceof MusicHomeActivity) {
                        BrushFragmentNavigation.navigateToArtistDetails((MusicHomeActivity) fragmentActivity, track.getArtist(), track.getArtistAsin());
                    }
                }
                sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW);
                return true;
            case R.id.menu_go_to_playlist /* 2131429115 */:
                PlaylistMetadata onDemandPlaylist = getOnDemandPlaylist(track);
                if (onDemandPlaylist != null) {
                    SearchFragmentUtils.navigateToPlaylistDetail(activity, onDemandPlaylist, null);
                    sendMetricEvent(ActionType.VIEW_PLAYLIST_OVERFLOW);
                }
                return true;
            case R.id.menu_songs_add_to_library /* 2131429117 */:
                if (Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
                    UpsellUtil.showBlockingUpsell(activity, track.getAsin(), UpsellReason.ADD_TO_LIBRARY, UpsellSourceEntity.TRACK);
                } else if (this.mProviderListener.canContinueAction(activity, track, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
                    addToLibrary(activity, track, true, false, this.mProviderListener.getOnAddedListener());
                    MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, track.getAsin(), SelectionSourceType.SONGS, null);
                }
                sendMetricEvent(ActionType.ADD_TO_LIBRARY_OVERFLOW);
                return true;
            case R.id.menu_songs_add_to_playqueue /* 2131429118 */:
                addToPlayQueue(activity, track, false);
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                return true;
            case R.id.menu_songs_buy /* 2131429119 */:
                if (ConnectivityUtil.checkConnectivityAndShowDialog(activity)) {
                    HTMLStoreActivityFactory.start(activity, HTMLStoreActivityFactory.bundleForAlbum(track.getAlbumAsin(), ""));
                }
                sendMetricEvent(ActionType.BUY_SONG_OVERFLOW);
                return true;
            case R.id.menu_songs_download /* 2131429120 */:
                if (this.trackModelConfiguration.shouldShowContextMenuDownloadUpsellButton(track)) {
                    UpsellUtil.showBlockingUpsell(activity, track.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.TRACK);
                    return true;
                }
                PrimeContentUtil.checkOfflineAccess(track, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.1
                    @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                    public void resultReceived(final boolean contentAvailable, final ContentUnavailableReason reasonUnavailable) {
                        if (PrimeTrackContextMenuProvider.this.mProviderListener.canContinueAction(activity, track, ContentAccessUtil.ContentAccessOperation.DOWNLOAD)) {
                            PrimeTrackContextMenuProvider primeTrackContextMenuProvider = PrimeTrackContextMenuProvider.this;
                            primeTrackContextMenuProvider.addToLibrary(activity, track, true, true, primeTrackContextMenuProvider.mProviderListener.getOnAddedListener());
                        }
                    }
                });
                ActionType actionType = ActionType.DOWNLOAD_OVERFLOW;
                if (this.mProviderListener.getTrackStateProvider().isDownloaded(track)) {
                    actionType = ActionType.REDOWNLOAD_OVERFLOW;
                }
                sendMetricEvent(actionType);
                return true;
            case R.id.menu_songs_go_to_entity_playlist /* 2131429123 */:
                goToEntityPlaylistFragment(track.getAsin(), PlaylistSeedEntityType.TRACK);
                return true;
            case R.id.menu_songs_play_next /* 2131429125 */:
                addToPlayQueue(activity, track, true);
                sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
                return true;
            case R.id.menu_songs_station_from_anything /* 2131429127 */:
                if (this.mProviderListener.canContinueAction(activity, track, ContentAccessUtil.ContentAccessOperation.STREAM)) {
                    StationUtils.startSFA(activity, track.getAsin(), "TRACK_SEED", PlaybackPageType.SONGS_LIST, ControlSource.APP_UI);
                }
                StationUtils.sendStartSFAUiClickMetrics(track.getAsin(), EntityIdType.SFA_TRACK_SEED);
                return true;
            case R.id.menu_track_context_share /* 2131429129 */:
                new TrackShareProvider(activity, EndpointsProvider.get().getMusicDomain(), track.getAlbumAsin(), track.getAsin(), track.getTitle(), track.getArtist(), track.getArtworkUri() != null ? track.getArtworkUri() : new CTAPrimeCache(activity).getArtworkUrlForAsin(track.getAsin())).startShare();
                return true;
            case R.id.menu_track_similar_recommend /* 2131429131 */:
                goToSimilarRecommendationsFragment(PaginatedDataType.TRACK, track.getAsin());
                sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
                return true;
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        PrimeTrack primeTrack = (PrimeTrack) tag;
        TrackStateProvider trackStateProvider = this.mProviderListener.getTrackStateProvider();
        this.mProviderListener.updateTrackStatus(primeTrack);
        ContextMenuDownloadHelper contextMenuDownloadHelper = new ContextMenuDownloadHelper();
        PlaybackExperience playbackExperience = this.eligibilityUtil.getPlaybackExperience(primeTrack);
        setStyledHeaderTitle(primeTrack.getTitle(), menu);
        menu.removeItem(R.id.menu_track_shop_for_artist);
        if (TextUtils.isEmpty(primeTrack.getAsin()) || !StationUtils.isCustomerAllowedToPlaySFAStation() || playbackExperience == PlaybackExperience.STATION_FROM_ANYTHING) {
            menu.removeItem(R.id.menu_songs_station_from_anything);
        }
        if (TextUtils.isEmpty(primeTrack.getAsin())) {
            menu.removeItem(R.id.menu_track_similar_recommend);
            menu.removeItem(R.id.menu_songs_go_to_entity_playlist);
        }
        if (!AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            if (trackStateProvider.isInLibrary(primeTrack) || trackStateProvider.isAddingToLibrary(primeTrack)) {
                menu.removeItem(R.id.menu_songs_add_to_library);
            } else {
                menu.removeItem(R.id.menu_songs_in_library);
            }
        }
        MenuItem menuItemFindItem = menu.findItem(R.id.menu_songs_download);
        String downloadQuality = contextMenuDownloadHelper.getDownloadQuality(primeTrack, this.mActivity);
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity)) {
            menuItemFindItem.setTitle(this.mActivity.getResources().getString(R.string.dmusic_context_download_with_quality, downloadQuality));
        }
        if (trackStateProvider.isDownloading(primeTrack)) {
            menu.removeItem(R.id.menu_songs_downloaded);
            menu.removeItem(R.id.menu_songs_download);
        } else if (trackStateProvider.isDownloaded(primeTrack) && ContentAccessUtil.getCatalogContentUnavailableReason(primeTrack, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == null) {
            if (new RedownloadStateResolver(this.mActivity).shouldRedownloadTrack(primeTrack.getAsin(), true, primeTrack.getContentEncoding()) && AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity)) {
                menuItemFindItem.setTitle(this.mActivity.getResources().getString(R.string.dmusic_context_redownload_with_quality, downloadQuality));
                menu.removeItem(R.id.menu_songs_downloaded);
            } else {
                menu.removeItem(R.id.menu_songs_download);
            }
            menu.removeItem(R.id.menu_songs_downloading);
        } else {
            if (this.trackModelConfiguration.shouldShowContextMenuDownloadUpsellButton(primeTrack)) {
                StringUtil.dimTextColor(menu.findItem(R.id.menu_songs_download), this.mActivity);
            }
            menu.removeItem(R.id.menu_songs_downloaded);
            menu.removeItem(R.id.menu_songs_downloading);
        }
        if (AmazonApplication.getCapabilities().isStoreSupported() && !ChildUserUtil.INSTANCE.isChildUser(activity.getBaseContext())) {
            if (trackStateProvider.isPurchased(primeTrack)) {
                menu.removeItem(R.id.menu_songs_buy);
            } else {
                menu.removeItem(R.id.menu_songs_purchased);
            }
        } else {
            menu.removeItem(R.id.menu_songs_buy);
            menu.removeItem(R.id.menu_songs_purchased);
        }
        if (AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.menu_go_to_artist);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.menu_songs_station_from_anything);
        } else {
            menu.removeItem(R.id.menu_songs_go_to_entity_playlist);
        }
        if (ContentAccessUtil.getPlayCatalogContentUnavailableReason(primeTrack) != null || !SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.menu_songs_add_to_playqueue);
            menu.removeItem(R.id.menu_songs_play_next);
        }
        menu.removeItem(R.id.menu_track_catalog_status);
        if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
            menu.removeItem(R.id.menu_track_similar_recommend);
        }
        if (!this.trackModelConfiguration.shouldShowContextMenuAddToMyMusicButton(this.mProviderListener.getContextMenupageType())) {
            menu.removeItem(R.id.menu_songs_add_to_library);
        }
        if (!this.trackModelConfiguration.shouldShowContextMenuCustomersAlsoListenedToButton()) {
            menu.removeItem(R.id.menu_track_similar_recommend);
        }
        if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.menu_songs_play_next);
            menu.removeItem(R.id.menu_songs_add_to_playqueue);
        }
        if (!AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.menu_songs_add_to_library), this.mActivity);
        }
        if (!this.mFreeTierUserPlaylistEditingEnabled) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.menu_add_to_playlist), this.mActivity);
        }
        if (getOnDemandPlaylist(primeTrack) == null) {
            menu.removeItem(R.id.menu_go_to_playlist);
        }
    }

    private PlaylistMetadata getOnDemandPlaylist(PrimeTrack track) {
        PlaylistMetadata playlistMetadata;
        PlaymodeEligibility playmodeEligibility;
        EntityEligibilitiesProvider parentEligibilities = track.getParentEligibilities();
        if ((parentEligibilities instanceof PlaylistMetadata) && (playmodeEligibility = (playlistMetadata = (PlaylistMetadata) parentEligibilities).getPlaymodeEligibility()) != null && playmodeEligibility.isOnDemand()) {
            return playlistMetadata;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addToLibrary(Activity activity, PrimeTrack track, boolean showToast, boolean download, OnItemFinishedAddingListener listener) {
        this.mProviderListener.setAdapterStatus(track, true, download);
        this.mProviderListener.getTrackStateProvider().setAddingToLibrary(track, true);
        new AddAndDownloadTrackTask(activity, track, download, showToast, this.mProviderListener.getTrackStateProvider().isDownloaded(track), listener).execute(new Void[0]);
    }

    protected void launchAlbumDetail(Activity activity, String albumAsin, String albumTitle, String artistName) {
        Fragment albumDetailFragment;
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(albumAsin, albumTitle)));
        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
            intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", albumAsin);
            albumDetailFragment = new AlbumDetailFragment();
        } else {
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", albumAsin);
            intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
            albumDetailFragment = new com.amazon.mp3.library.fragment.AlbumDetailFragment();
        }
        albumDetailFragment.setArguments(intentForContentUri.getExtras());
        FragmentTransaction fragmentTransactionBeginTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out);
        fragmentTransactionBeginTransaction.replace(R.id.music_screen_container, albumDetailFragment);
        fragmentTransactionBeginTransaction.addToBackStack(AlbumDetailFragment.class.getSimpleName());
        fragmentTransactionBeginTransaction.commit();
    }

    public void addToPlaylist(Activity activity, PrimeTrack track) {
        if (!this.mProviderListener.getTrackStateProvider().isInLibrary(track)) {
            this.mProviderListener.setAdapterStatus(track, true, false);
            activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "cta", track.getAsin()), this.mPageType));
        } else if (this.mProviderListener.getTrackStateProvider().getContentUri(track) == null) {
            this.mProviderListener.updateTrackStatusForPlaylist(track);
        } else {
            activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, this.mProviderListener.getTrackStateProvider().getContentUri(track), this.mPageType));
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public <D extends SearchItem & CatalogContent> void addToPlayQueue(final D item, final boolean playNext) {
        PrimeTrack primeTrack = (PrimeTrack) item;
        if (!this.mProviderListener.getTrackStateProvider().isInLibrary(primeTrack)) {
            PrimeTrackTask.createPlaybackTask(this.mActivity, primeTrack.getAlbumAsin(), primeTrack.getAsin(), (PlaybackMetricInformation) null, (PlaybackPageType) null, playNext ? PrimeCollectionTask.Task.PLAY_NEXT : PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE, new QuerySongByAsin(this.mActivity), (PrimeCollectionTask.OnUriReadyCallback) null).execute();
        } else {
            NowPlayingQueueUtil.getInstance().addToPlayQueue(this.mProviderListener.getTrackStateProvider().getContentUri(primeTrack), playNext);
        }
    }
}
