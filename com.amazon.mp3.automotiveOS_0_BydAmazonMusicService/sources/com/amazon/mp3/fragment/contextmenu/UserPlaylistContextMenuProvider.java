package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.UserPlaylistModelConfiguration;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.playlist.api.PlaylistFactory;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.sharing.ShareProviderActionHandler;
import com.amazon.mp3.task.FollowAndDownloadPrimePlaylistTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.share.UserPlaylistShareProvider;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import java.util.List;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class UserPlaylistContextMenuProvider extends ContextMenuProvider {
    private static String TAG = "UserPlaylistContextMenuProvider";
    private FragmentActivity mActivity;
    private PageStateManager mPageStateManager;
    private PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener mProviderListener;
    private UserPlaylistModelConfiguration playlistModelConfiguration = new UserPlaylistModelConfiguration(null);
    private GetOnDemandPlaylistsService onDemandPlaylistsService = GetOnDemandPlaylistsService.INSTANCE.get();

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.user_playlist_item_menu;
    }

    public UserPlaylistContextMenuProvider(final FragmentActivity activity, final PageStateManager pageStateManager, final PrimePlaylistContextMenuProvider.PrimePlaylistContextMenuProviderListener listener) {
        this.mActivity = activity;
        this.mPageStateManager = pageStateManager;
        this.mProviderListener = listener;
        init(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(final Activity activity, final ContextMenu menu, final Object tag, final int position, final String sourceID) {
        final PrimePlaylist primePlaylist = (PrimePlaylist) tag;
        if (primePlaylist == null) {
            return;
        }
        Boolean boolValueOf = Boolean.valueOf(this.onDemandPlaylistsService.isOnDemandUserPlaylist(primePlaylist.getPlaylistId()));
        primePlaylist.setSource(sourceID);
        StateProvider<PrimePlaylist> playlistStateProvider = this.mProviderListener.getPlaylistStateProvider();
        this.mProviderListener.updatePlaylistStatus(primePlaylist);
        Uri playlistUri = getPlaylistUri(activity, sourceID, primePlaylist);
        if (playlistStateProvider.isDownloading(primePlaylist) || playlistStateProvider.isDownloaded(primePlaylist)) {
            menu.removeItem(R.id.MenuDownload);
        } else if (this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(primePlaylist)) {
            StringUtil.dimTextColor(menu.findItem(R.id.MenuDownload), this.mActivity);
        }
        if (!AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
            menu.removeItem(R.id.MenuPlaylistMakePublic);
            menu.removeItem(R.id.MenuPlaylistMakePrivate);
        }
        setStyledHeaderTitle(primePlaylist.getTitle(), menu);
        if (primePlaylist.getOwnership()) {
            menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
            menu.removeItem(R.id.MenuPlaylistContextInLibrary);
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$library$provider$MediaProvider$Playlists$VisibilityState[PlaylistUtil.getPlaylistVisibilityStateFromUri(activity, playlistUri).ordinal()];
            if (i == 1) {
                menu.removeItem(R.id.MenuPlaylistMakePublic);
            } else if (i == 2) {
                menu.removeItem(R.id.MenuPlaylistMakePrivate);
            } else {
                menu.removeItem(R.id.MenuPlaylistMakePublic);
                menu.removeItem(R.id.MenuPlaylistMakePrivate);
            }
        } else {
            menu.removeItem(R.id.MenuPlaylistMakePrivate);
            menu.removeItem(R.id.MenuPlaylistMakePublic);
            if (playlistStateProvider.isInLibrary(primePlaylist)) {
                menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
            } else {
                menu.removeItem(R.id.MenuPlaylistContextInLibrary);
            }
        }
        if (!SequencerHelper.canModifyQueue()) {
            menu.removeItem(R.id.MenuPlaylistAddToPlayQueue);
            menu.removeItem(R.id.MenuPlaylistPlayNext);
        }
        if (boolValueOf.booleanValue()) {
            menu.removeItem(R.id.MenuShare);
            menu.removeItem(R.id.MenuDownload);
            menu.removeItem(R.id.MenuPlaylistContextAddToLibrary);
            menu.removeItem(R.id.MenuUserPlaylistStationFromAnything);
        }
        if (primePlaylist.getTrackCount() <= 0 || !StationUtils.isCustomerAllowedToPlaySFAStation()) {
            menu.removeItem(R.id.MenuUserPlaylistStationFromAnything);
        } else {
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    UserPlaylistContextMenuProvider.lambda$updateContextMenuItems$0(activity, primePlaylist, (Subscriber) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    UserPlaylistContextMenuProvider.lambda$updateContextMenuItems$1(menu, (Boolean) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    UserPlaylistContextMenuProvider.lambda$updateContextMenuItems$2((Throwable) obj);
                }
            });
        }
        if (UserSubscriptionUtil.isNightwingOnly()) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuPlaylistContextAddToLibrary), this.mActivity);
            MenuItem menuItemFindItem = menu.findItem(R.id.MenuPlaylistContextAddToLibrary);
            if (menuItemFindItem != null) {
                menuItemFindItem.setEnabled(false);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$0(Activity activity, PrimePlaylist primePlaylist, Subscriber subscriber) {
        subscriber.onNext(Boolean.valueOf(StationUtils.containsValidAsinsInUserPlaylist(activity, primePlaylist.getPlaylistId(), primePlaylist.getSource())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$1(ContextMenu contextMenu, Boolean bool) {
        if (bool.booleanValue()) {
            return;
        }
        contextMenu.removeItem(R.id.MenuPlaylistStationFromAnything);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateContextMenuItems$2(Throwable th) {
        Log.error(TAG, "Error determining if the playlist has a list of asins for SFA support");
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(final FragmentActivity activity, final MenuItem item, final AdapterView.AdapterContextMenuInfo menuInfo, final String sourceID) {
        final PrimePlaylist primePlaylist = (PrimePlaylist) menuInfo.targetView.getTag();
        if (primePlaylist == null) {
            return false;
        }
        primePlaylist.setSource(sourceID);
        setClickedItemHolder(primePlaylist);
        setClickedEntityIdAndType(primePlaylist.getPlaylistId(), EntityIdType.PLAYLIST_ID);
        Set<BaseViewContentModel> storedModelsForId = this.mPageStateManager.getStoredModelsForId(primePlaylist.getPlaylistId());
        BaseViewContentModel next = (storedModelsForId == null || storedModelsForId.isEmpty()) ? null : storedModelsForId.iterator().next();
        boolean z = !primePlaylist.getOwnership();
        final Uri playlistUri = getPlaylistUri(activity, sourceID, primePlaylist);
        switch (item.getItemId()) {
            case R.id.MenuDownload /* 2131427575 */:
                if (this.playlistModelConfiguration.shouldShowContextMenuDownloadUpsellButton(primePlaylist)) {
                    UpsellUtil.showBlockingUpsell(activity, primePlaylist.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
                } else {
                    PrimeContentUtil.checkOfflineAccess(primePlaylist, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider$$ExternalSyntheticLambda0
                        @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                        public final void resultReceived(boolean z2, ContentUnavailableReason contentUnavailableReason) {
                            this.f$0.lambda$onContextMenuItemSelected$3(activity, primePlaylist, playlistUri, z2, contentUnavailableReason);
                        }
                    });
                }
                sendMetricEvent(ActionType.DOWNLOAD_OVERFLOW);
                break;
            case R.id.MenuPlaylistAddToPlayQueue /* 2131427607 */:
                addToPlayQueue(playlistUri, false);
                break;
            case R.id.MenuPlaylistContextAddToLibrary /* 2131427611 */:
                addToLibrary(activity, primePlaylist, false);
                if (z) {
                    this.mProviderListener.updatePlaylistStatus(primePlaylist);
                }
                break;
            case R.id.MenuPlaylistMakePrivate /* 2131427624 */:
                PlaylistUtil.setPlaylistVisibility(activity, playlistUri, MediaProvider.Playlists.VisibilityState.PRIVATE);
                if (next != null && (next instanceof VerticalTileModel)) {
                    ((VerticalTileModel) next).updatePlaylistVisibility(activity.getResources().getString(R.string.public_playlist_visibility_state_private));
                }
                break;
            case R.id.MenuPlaylistMakePublic /* 2131427625 */:
                PlaylistUtil.setPlaylistVisibility(activity, playlistUri, MediaProvider.Playlists.VisibilityState.PUBLIC);
                if (next != null && (next instanceof VerticalTileModel)) {
                    ((VerticalTileModel) next).updatePlaylistVisibility(activity.getResources().getString(R.string.public_playlist_visibility_state_public));
                }
                break;
            case R.id.MenuPlaylistPlayNext /* 2131427627 */:
                addToPlayQueue(playlistUri, true);
                break;
            case R.id.MenuShare /* 2131427637 */:
                sharePlaylist(activity, playlistUri, primePlaylist, z);
                break;
            case R.id.MenuUserPlaylistStationFromAnything /* 2131427670 */:
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UserPlaylistContextMenuProvider.lambda$onContextMenuItemSelected$4(activity, primePlaylist);
                    }
                });
                break;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onContextMenuItemSelected$3(FragmentActivity fragmentActivity, PrimePlaylist primePlaylist, Uri uri, boolean z, ContentUnavailableReason contentUnavailableReason) {
        if (this.mProviderListener.canContinueAction(fragmentActivity, primePlaylist, ContentAccessUtil.ContentAccessOperation.DOWNLOAD)) {
            downloadPlaylist(fragmentActivity, uri, primePlaylist);
            this.mProviderListener.updatePlaylistStatus(primePlaylist);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onContextMenuItemSelected$4(FragmentActivity fragmentActivity, PrimePlaylist primePlaylist) {
        List<String> asinListSeedFromPlaylistId = StationUtils.getAsinListSeedFromPlaylistId(fragmentActivity, primePlaylist.getPlaylistId(), primePlaylist.getSource());
        StationUtils.startSFA(fragmentActivity, StationUtils.convertAsinsToTracks(asinListSeedFromPlaylistId), "TRACK_LIST_SEED", PlaybackPageType.PLAYLISTS_LIST);
        StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", asinListSeedFromPlaylistId), EntityIdType.SFA_TRACK_LIST_SEED);
    }

    private void sharePlaylist(final FragmentActivity activity, final Uri playlistUri, PrimePlaylist primePlaylist, boolean isSharedUserPlaylist) {
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        if (primePlaylist == null) {
            Log.error(TAG, "playlist was null");
            return;
        }
        if (isSharedUserPlaylist) {
            new PlaylistShareProvider(activity, musicDomain, primePlaylist.getPlaylistId(), primePlaylist.getTitle(), primePlaylist.getArtworkUri()).startShare();
            return;
        }
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(playlistUri);
        if (playlist == null) {
            Log.error(TAG, "playlist from itemFactory was Null, playlist title from muse: " + primePlaylist.getTitle());
            return;
        }
        new UserPlaylistShareProvider(activity, musicDomain, playlist.getLuid(), playlist.getName(), playlist.getVersion(), PlaylistFactory.createPlaylistLibrary(activity), ShareProviderActionHandler.newInstance(), null).startShare();
    }

    private void downloadPlaylist(final FragmentActivity activity, final Uri uri, final PrimePlaylist playlist) {
        if (playlist.getOwnership()) {
            Long lValueOf = Long.valueOf(MediaProvider.Playlists.getPlaylistId(uri));
            Uri uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO.intValue(), lValueOf.longValue());
            NotificationInfo notificationInfo = new NotificationInfo(playlist.getTitle(), null, uriGenerateUri == null ? null : uriGenerateUri.toString());
            Uri contentUri = MediaProvider.UdoPlaylists.Tracks.getContentUri(MediaProvider.getSource(uri), lValueOf.longValue());
            ContextMenuDownloadComponent.startDownload(activity, contentUri.toString(), contentUri, notificationInfo);
            return;
        }
        addToLibrary(activity, playlist, true);
    }

    private void addToPlayQueue(final Uri uri, final boolean playNext) {
        NowPlayingQueueUtil.getInstance().addToPlayQueue(MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(uri), playNext);
    }

    private Uri getPlaylistUri(Activity activity, String sourceId, PrimePlaylist playlist) {
        if (playlist.getOwnership()) {
            return MediaProvider.UdoPlaylists.getContentUriFromLuid(activity, sourceId, playlist.getPlaylistId());
        }
        return MediaProvider.SharedUserPlaylists.getContentUri(sourceId, playlist.getPlaylistId());
    }

    private void addToLibrary(FragmentActivity activity, PrimePlaylist playlist, boolean download) {
        new FollowAndDownloadPrimePlaylistTask(activity, playlist, download).execute(new Void[0]);
    }
}
