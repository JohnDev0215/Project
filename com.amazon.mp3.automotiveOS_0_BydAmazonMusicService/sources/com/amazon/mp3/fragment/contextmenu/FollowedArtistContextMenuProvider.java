package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider;
import com.amazon.mp3.library.adapter.ArtistListAdapter;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.social.MusicRelationshipProvider;
import com.amazon.mp3.social.SocialFactory;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.ArtistShareProvider;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: FollowedArtistContextMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u00016B\u0019\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b4\u00105J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0002J\"\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u0007H\u0014J*\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016J6\u0010!\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0014Jj\u0010+\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010%\u001a\u0004\u0018\u00010$2\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010(\u001a\u0004\u0018\u00010\u00182\b\u0010*\u001a\u0004\u0018\u00010)H\u0016R\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010,\u001a\u0004\b-\u0010.R\u001c\u00100\u001a\n /*\u0004\u0018\u00010\u00180\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u00188\u0002X\u0082D¢\u0006\u0006\n\u0004\b2\u00101R\u0014\u00103\u001a\u00020\u00188\u0002X\u0082D¢\u0006\u0006\n\u0004\b3\u00101¨\u00067"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "Lcom/amazon/mp3/library/adapter/ArtistListAdapter$RowViewHolder;", "Lcom/amazon/mp3/library/item/Artist;", "artist", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "convertArtistToMetadata", "", "itemId", "metadata", "", "isFreeTier", "artistMetadata", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "unfollowArtist", "getContextMenuLayoutId", "Landroid/view/MenuItem;", "item", "Landroid/widget/AdapterView$AdapterContextMenuInfo;", "menuInfo", "", "sourceID", "onContextMenuItemSelected", "Landroid/app/Activity;", "Landroid/view/ContextMenu;", "menu", "", ParserUtil.TAG_QUERY_PARAM_NAME, "position", "updateContextMenuItems", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", ContextMappingConstants.ENTITY_ID, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "onCreateContextMenu", "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", "getListener", "()Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "SUCCESS_ARTIST_UNFOLLOW", "FAILED_ARTIST_UNFOLLOW", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;)V", "FollowedArtistContextMenuListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FollowedArtistContextMenuProvider extends ContextMenuProvider<ArtistListAdapter.RowViewHolder> {
    private final String FAILED_ARTIST_UNFOLLOW;
    private final String SUCCESS_ARTIST_UNFOLLOW;
    private final String TAG;
    private final FollowedArtistContextMenuListener listener;

    /* JADX INFO: compiled from: FollowedArtistContextMenuProvider.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", "", "onArtistUnfollow", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface FollowedArtistContextMenuListener {
        void onArtistUnfollow();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.library_followed_artist_context;
    }

    public FollowedArtistContextMenuProvider(FragmentActivity activity, FollowedArtistContextMenuListener followedArtistContextMenuListener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.listener = followedArtistContextMenuListener;
        this.TAG = FollowedArtistContextMenuProvider.class.getSimpleName();
        this.SUCCESS_ARTIST_UNFOLLOW = "successArtistUnfollow";
        this.FAILED_ARTIST_UNFOLLOW = "failedArtistUnfollow";
        init(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(final FragmentActivity activity, final MenuItem item, final AdapterView.AdapterContextMenuInfo menuInfo, final String sourceID) {
        ArtistMetadata artistMetadataConvertArtistToMetadata;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(menuInfo, "menuInfo");
        super.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
        Object tag = menuInfo.targetView.getTag();
        if (tag == null) {
            return false;
        }
        if (tag instanceof ArtistMetadata) {
            artistMetadataConvertArtistToMetadata = (ArtistMetadata) tag;
        } else {
            if (!(tag instanceof Artist)) {
                return false;
            }
            artistMetadataConvertArtistToMetadata = convertArtistToMetadata((Artist) tag);
        }
        if (!ConnectivityUtil.hasAnyInternetConnection(activity.getApplicationContext())) {
            NetworkErrorDialog.create(this.mActivity, new LibraryFollowedArtistFragment.FollowedArtistNetworkErrorHandler() { // from class: com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider$onContextMenuItemSelected$handler$1
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                    LibraryFollowedArtistFragment.FollowedArtistNetworkErrorHandler.DefaultImpls.onConnectionErrorDialogCancel(this);
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                    LibraryFollowedArtistFragment.FollowedArtistNetworkErrorHandler.DefaultImpls.onDialogDismiss(this);
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    this.this$0.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
                }
            }).show();
            return false;
        }
        int itemId = item.getItemId();
        setClickedEntityIdAndType(artistMetadataConvertArtistToMetadata.getAsin(), EntityIdType.ASIN);
        switch (itemId) {
            case R.id.MenuArtistAddToPlayQueue /* 2131427557 */:
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                if (isFreeTier(itemId, artistMetadataConvertArtistToMetadata)) {
                    UpsellUtil.showBlockingUpsell(activity, artistMetadataConvertArtistToMetadata.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ARTIST);
                } else {
                    NowPlayingQueueUtil.getInstance().addToPlayQueue(MediaProvider.Artists.Tracks.getContentUri(sourceID, MediaProvider.Artists.getArtistId(MediaProvider.Artists.getContentUri(sourceID, IdGenerator.generateArtistId(artistMetadataConvertArtistToMetadata.getName())))), false);
                }
                break;
            case R.id.MenuArtistContextShare /* 2131427563 */:
                sendMetricEvent(ActionType.SHARE);
                new ArtistShareProvider(activity, EndpointsProvider.get().getMusicDomain(), artistMetadataConvertArtistToMetadata.getAsin(), artistMetadataConvertArtistToMetadata.getName(), artistMetadataConvertArtistToMetadata.getImageUrl()).startShare();
                break;
            case R.id.MenuArtistGoToEntityPlaylist /* 2131427564 */:
                goToEntityPlaylistFragment(artistMetadataConvertArtistToMetadata.getAsin(), PlaylistSeedEntityType.ARTIST);
                break;
            case R.id.MenuArtistPlayNext /* 2131427566 */:
                sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
                if (isFreeTier(itemId, artistMetadataConvertArtistToMetadata)) {
                    UpsellUtil.showBlockingUpsell(activity, artistMetadataConvertArtistToMetadata.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ARTIST);
                } else {
                    NowPlayingQueueUtil.getInstance().addToPlayQueue(MediaProvider.Artists.Tracks.getContentUri(sourceID, MediaProvider.Artists.getArtistId(MediaProvider.Artists.getContentUri(sourceID, IdGenerator.generateArtistId(artistMetadataConvertArtistToMetadata.getName())))), true);
                }
                break;
            case R.id.MenuArtistStationFromAnything /* 2131427569 */:
                StationUtils.sendStartSFAUiClickMetrics(artistMetadataConvertArtistToMetadata.getAsin(), EntityIdType.SFA_ARTIST_SEED, getPageType());
                StationUtils.startSFA(activity, artistMetadataConvertArtistToMetadata.getAsin(), "ARTIST_SEED", PlaybackPageType.ARTISTS_LIST, ControlSource.APP_UI);
                break;
            case R.id.MenuArtistUnfollow /* 2131427570 */:
                sendMetricEvent(ActionType.UNFOLLOW_ARTIST);
                unfollowArtist(artistMetadataConvertArtistToMetadata, activity, this.listener);
                break;
        }
        return false;
    }

    private final ArtistMetadata convertArtistToMetadata(Artist artist) {
        String asin = artist.getAsin();
        String name = artist.getName();
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        String id = artist.getId();
        if (id == null) {
            id = "";
        }
        return new ArtistMetadata(null, contentPlaybackUtils.getArtistUri(id), null, null, asin, null, name, null, null, null, null, true, null, null, false, null, null, false, false, null, 1046445, null);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (tag instanceof ArtistMetadata) {
            ArtistMetadata artistMetadata = (ArtistMetadata) tag;
            setStyledHeaderTitle(artistMetadata.getName(), menu);
            if (TextUtils.isEmpty(artistMetadata.getAsin()) || ShopLinkUtil.isArtistBlacklisted(artistMetadata.getName()) || !AmazonApplication.getCapabilities().isSharingSupported()) {
                menu.removeItem(R.id.MenuArtistContextShare);
            }
            if (TextUtils.isEmpty(artistMetadata.getAsin())) {
                menu.removeItem(R.id.MenuArtistStationFromAnything);
                menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
            }
            if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
                menu.removeItem(R.id.MenuArtistStationFromAnything);
            } else {
                menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
            }
            if (!SequencerHelper.canModifyQueue()) {
                menu.removeItem(R.id.MenuArtistAddToPlayQueue);
                menu.removeItem(R.id.MenuArtistPlayNext);
            }
            menu.removeItem(R.id.MenuArtistContextDeleteFromCirrus);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public void onCreateContextMenu(Activity activity, ContextMenu menu, Object tag, int position, String sourceID, ActionType actionType, PageType pageType, InteractionType interactionType, String entityId, EntityIdType entityIdType) {
        super.onCreateContextMenu(activity, menu, tag, position, sourceID, actionType, pageType, interactionType, entityId, entityIdType);
    }

    private final boolean isFreeTier(int itemId, ArtistMetadata metadata) {
        return (itemId == R.id.MenuArtistAddToPlayQueue || itemId == R.id.MenuArtistPlayNext) && UserSubscriptionUtil.isNightwingOnly() && !metadata.getIsAllOwned();
    }

    private final synchronized void unfollowArtist(ArtistMetadata artistMetadata, FragmentActivity activity, final FollowedArtistContextMenuListener listener) {
        Observable<Void> observableSubscribeOn;
        Observable<Void> observableObserveOn;
        final Context baseContext = activity.getBaseContext();
        MusicRelationshipProvider musicRelationshipProviderCreateMusicRelationshipProvider = SocialFactory.createMusicRelationshipProvider(baseContext);
        String asin = artistMetadata.getAsin();
        if (asin != null && (observableSubscribeOn = musicRelationshipProviderCreateMusicRelationshipProvider.unFollowArtist(asin).subscribeOn(Schedulers.io())) != null && (observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
            observableObserveOn.subscribe(new Observer<Object>() { // from class: com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider$unfollowArtist$1$1
                @Override // rx.Observer
                public void onNext(Object p0) {
                }

                @Override // rx.Observer
                public void onCompleted() {
                    BauhausToastUtils.showTextToast(baseContext, R.string.dmusic_unfollow_artist_request_success_text, 1);
                    FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener followedArtistContextMenuListener = listener;
                    if (followedArtistContextMenuListener != null) {
                        followedArtistContextMenuListener.onArtistUnfollow();
                    }
                    MetricsLogger.sendEvent(FlexEvent.builder(this.SUCCESS_ARTIST_UNFOLLOW).build());
                }

                @Override // rx.Observer
                public void onError(Throwable throwable) {
                    Intrinsics.checkNotNullParameter(throwable, "throwable");
                    Log.error(this.TAG, Intrinsics.stringPlus("Cannot update artist follow status ", throwable.getLocalizedMessage()));
                    BauhausToastUtils.showTextToast(baseContext, R.string.dmusic_follow_artist_request_failed_text, 0);
                    MetricsLogger.sendEvent(FlexEvent.builder(this.FAILED_ARTIST_UNFOLLOW).withFlexStr1(throwable.getLocalizedMessage()).build());
                }
            });
        }
    }
}
