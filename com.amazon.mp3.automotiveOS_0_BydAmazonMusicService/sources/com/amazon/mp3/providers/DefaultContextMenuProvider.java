package com.amazon.mp3.providers;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimeAlbumContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimePlaylistContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimeTrackContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.VideoPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeArtistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.StationContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.VideoPlaylistContextMenuProvider;
import com.amazon.mp3.home.BrowseRecentlyPlayedContextMenuProvider;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingContextMenuProvider;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: DefaultContextMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \u0090\u00012\u00020\u00012\u00020\u0002:\u0002\u0090\u0001B7\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\u0006\u0010\"\u001a\u00020!\u0012\b\u0010%\u001a\u0004\u0018\u00010$\u0012\u0006\u0010(\u001a\u00020'¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u001c\u0010\u0013\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\u001c\u0010\u0016\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0018H\u0016R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010%\u001a\u0004\u0018\u00010$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001b\u0010/\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001b\u00104\u001a\u0002008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010,\u001a\u0004\b2\u00103R\u001d\u00109\u001a\u0004\u0018\u0001058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010,\u001a\u0004\b7\u00108R\u001d\u0010>\u001a\u0004\u0018\u00010:8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010,\u001a\u0004\b<\u0010=R\u001b\u0010C\u001a\u00020?8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010,\u001a\u0004\bA\u0010BR\u001d\u0010H\u001a\u0004\u0018\u00010D8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010,\u001a\u0004\bF\u0010GR\u001b\u0010M\u001a\u00020I8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010,\u001a\u0004\bK\u0010LR\u001b\u0010R\u001a\u00020N8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bO\u0010,\u001a\u0004\bP\u0010QR\u001b\u0010W\u001a\u00020S8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010,\u001a\u0004\bU\u0010VR\u001b\u0010\\\u001a\u00020X8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bY\u0010,\u001a\u0004\bZ\u0010[R\u001b\u0010a\u001a\u00020]8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b^\u0010,\u001a\u0004\b_\u0010`R\u001b\u0010f\u001a\u00020b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bc\u0010,\u001a\u0004\bd\u0010eR\u001b\u0010k\u001a\u00020g8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bh\u0010,\u001a\u0004\bi\u0010jR\u001b\u0010p\u001a\u00020l8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bm\u0010,\u001a\u0004\bn\u0010oR\u001b\u0010u\u001a\u00020q8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\br\u0010,\u001a\u0004\bs\u0010tR\u001d\u0010z\u001a\u0004\u0018\u00010v8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bw\u0010,\u001a\u0004\bx\u0010yR\u0018\u0010{\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b{\u0010|R\u0018\u0010}\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R\u0019\u0010\u007f\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R \u0010\u0082\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0081\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001c\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0084\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R*\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001¨\u0006\u0091\u0001"}, d2 = {"Lcom/amazon/mp3/providers/DefaultContextMenuProvider;", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "", "clearSelectedData", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "shouldDisplayContextMenu", "Landroid/view/View;", "view", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interaction", "openContextMenu", "updateStateProvider", "Landroid/view/ContextMenu;", "menu", "", "sourceId", "onCreateContextMenu", "Landroid/view/MenuItem;", "menuItem", "onContextMenuItemSelected", "shouldBindContextMenuOnFreeTier", "Landroidx/lifecycle/LifecycleOwner;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "onDestroy", "Landroidx/fragment/app/FragmentActivity;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "mFragment", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "mLibraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "mPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/mp3/playback/state/PageStateManager;", "mPageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimePlaylistContextMenuListener;", "mPrimePlaylistMenuListener$delegate", "Lkotlin/Lazy;", "getMPrimePlaylistMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimePlaylistContextMenuListener;", "mPrimePlaylistMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuProvider;", "mPrimePlaylistMenuProvider$delegate", "getMPrimePlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuProvider;", "mPrimePlaylistMenuProvider", "Lcom/amazon/mp3/catalog/fragment/contextmenu/VideoPlaylistContextMenuListener;", "videoPlaylistMenuListener$delegate", "getVideoPlaylistMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/VideoPlaylistContextMenuListener;", "videoPlaylistMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider;", "videoPlaylistMenuProvider$delegate", "getVideoPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider;", "videoPlaylistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "videoMenuListener$delegate", "getVideoMenuListener", "()Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "videoMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "videoMenuProvider$delegate", "getVideoMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "videoMenuProvider", "Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeTrackContextMenuListener;", "mPrimeTrackMenuListener$delegate", "getMPrimeTrackMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeTrackContextMenuListener;", "mPrimeTrackMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/PrimeTrackContextMenuProvider;", "mPrimeTrackMenuProvider$delegate", "getMPrimeTrackMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeTrackContextMenuProvider;", "mPrimeTrackMenuProvider", "Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeAlbumContextMenuListener;", "mPrimeAlbumMenuListener$delegate", "getMPrimeAlbumMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeAlbumContextMenuListener;", "mPrimeAlbumMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider;", "mPrimeAlbumMenuProvider$delegate", "getMPrimeAlbumMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider;", "mPrimeAlbumMenuProvider", "Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "mPrimeArtistPlaybackHandler$delegate", "getMPrimeArtistPlaybackHandler", "()Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "mPrimeArtistPlaybackHandler", "Lcom/amazon/mp3/fragment/contextmenu/PrimeArtistContextMenuProvider;", "mPrimeArtistMenuProvider$delegate", "getMPrimeArtistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeArtistContextMenuProvider;", "mPrimeArtistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/StationContextMenuProvider;", "mStationMenuProvider$delegate", "getMStationMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/StationContextMenuProvider;", "mStationMenuProvider", "Lcom/amazon/mp3/home/BrowseRecentlyPlayedContextMenuProvider;", "mRecentlyPlayedMenuProvider$delegate", "getMRecentlyPlayedMenuProvider", "()Lcom/amazon/mp3/home/BrowseRecentlyPlayedContextMenuProvider;", "mRecentlyPlayedMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/UserPlaylistContextMenuProvider;", "mUserPlaylistMenuProvider$delegate", "getMUserPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/UserPlaylistContextMenuProvider;", "mUserPlaylistMenuProvider", "Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueCastingContextMenuProvider;", "mCloudQueueCastingContextMenuProvider$delegate", "getMCloudQueueCastingContextMenuProvider", "()Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueCastingContextMenuProvider;", "mCloudQueueCastingContextMenuProvider", "mSelectedMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "mSelectedView", "Landroid/view/View;", "mInteractionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "mContextMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "", "mCatalogItem", "Ljava/lang/Object;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "mActionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getMActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "setMActionType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;)V", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;Lcom/amazon/mp3/library/provider/LibraryStateProvider;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/mp3/playback/state/PageStateManager;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class DefaultContextMenuProvider implements MetadataContextMenuHandler, DefaultLifecycleObserver {
    private static final String TAG;
    private ActionType mActionType;
    private FragmentActivity mActivity;
    private Object mCatalogItem;

    /* JADX INFO: renamed from: mCloudQueueCastingContextMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mCloudQueueCastingContextMenuProvider;
    private ContextMenuProvider<?> mContextMenuProvider;
    private ContextMenuSupportingFragment mFragment;
    private InteractionType mInteractionType;
    private final LibraryStateProvider mLibraryStateProvider;
    private final PageStateManager mPageStateManager;
    private final PageType mPageType;

    /* JADX INFO: renamed from: mPrimeAlbumMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy mPrimeAlbumMenuListener;

    /* JADX INFO: renamed from: mPrimeAlbumMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mPrimeAlbumMenuProvider;

    /* JADX INFO: renamed from: mPrimeArtistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mPrimeArtistMenuProvider;

    /* JADX INFO: renamed from: mPrimeArtistPlaybackHandler$delegate, reason: from kotlin metadata */
    private final Lazy mPrimeArtistPlaybackHandler;

    /* JADX INFO: renamed from: mPrimePlaylistMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy mPrimePlaylistMenuListener;

    /* JADX INFO: renamed from: mPrimePlaylistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mPrimePlaylistMenuProvider;

    /* JADX INFO: renamed from: mPrimeTrackMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy mPrimeTrackMenuListener;

    /* JADX INFO: renamed from: mPrimeTrackMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mPrimeTrackMenuProvider;

    /* JADX INFO: renamed from: mRecentlyPlayedMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mRecentlyPlayedMenuProvider;
    private ContentMetadata mSelectedMetadata;
    private View mSelectedView;

    /* JADX INFO: renamed from: mStationMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mStationMenuProvider;

    /* JADX INFO: renamed from: mUserPlaylistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mUserPlaylistMenuProvider;

    /* JADX INFO: renamed from: videoMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy videoMenuListener;

    /* JADX INFO: renamed from: videoMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy videoMenuProvider;

    /* JADX INFO: renamed from: videoPlaylistMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy videoPlaylistMenuListener;

    /* JADX INFO: renamed from: videoPlaylistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy videoPlaylistMenuProvider;

    public DefaultContextMenuProvider(FragmentActivity fragmentActivity, ContextMenuSupportingFragment contextMenuSupportingFragment, LibraryStateProvider mLibraryStateProvider, PageType pageType, PageStateManager mPageStateManager) {
        Intrinsics.checkNotNullParameter(mLibraryStateProvider, "mLibraryStateProvider");
        Intrinsics.checkNotNullParameter(mPageStateManager, "mPageStateManager");
        this.mActivity = fragmentActivity;
        this.mFragment = contextMenuSupportingFragment;
        this.mLibraryStateProvider = mLibraryStateProvider;
        this.mPageType = pageType;
        this.mPageStateManager = mPageStateManager;
        this.mPrimePlaylistMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimePlaylistContextMenuListener>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimePlaylistMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimePlaylistContextMenuListener invoke() {
                return new PrimePlaylistContextMenuListener(this.this$0.mActivity);
            }
        });
        this.mPrimePlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimePlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider invoke() {
                return new com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimePlaylistMenuListener(), ContextMenuPageType.LONG_PRESS);
            }
        });
        this.videoPlaylistMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlaylistContextMenuListener>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$videoPlaylistMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPlaylistContextMenuListener invoke() {
                FragmentActivity fragmentActivity2 = this.this$0.mActivity;
                if (fragmentActivity2 == null) {
                    return null;
                }
                return new VideoPlaylistContextMenuListener(fragmentActivity2);
            }
        });
        this.videoPlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlaylistContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$videoPlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPlaylistContextMenuProvider invoke() {
                VideoPlaylistContextMenuListener videoPlaylistMenuListener;
                FragmentActivity fragmentActivity2 = this.this$0.mActivity;
                if (fragmentActivity2 == null || (videoPlaylistMenuListener = this.this$0.getVideoPlaylistMenuListener()) == null) {
                    return null;
                }
                return new VideoPlaylistContextMenuProvider(fragmentActivity2, videoPlaylistMenuListener);
            }
        });
        this.videoMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<VideoContextMenuProvider.VideoContextMenuProviderListener>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$videoMenuListener$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoContextMenuProvider.VideoContextMenuProviderListener invoke() {
                return new VideoContextMenuProvider.VideoContextMenuProviderListener();
            }
        });
        this.videoMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<VideoContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$videoMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoContextMenuProvider invoke() {
                FragmentActivity fragmentActivity2 = this.this$0.mActivity;
                if (fragmentActivity2 == null) {
                    return null;
                }
                return new VideoContextMenuProvider(fragmentActivity2, this.this$0.getVideoMenuListener());
            }
        });
        this.mPrimeTrackMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimeTrackContextMenuListener>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimeTrackMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeTrackContextMenuListener invoke() {
                return new PrimeTrackContextMenuListener(this.this$0.mActivity, this.this$0.mLibraryStateProvider);
            }
        });
        this.mPrimeTrackMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimeTrackContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimeTrackMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeTrackContextMenuProvider invoke() {
                return new PrimeTrackContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimeTrackMenuListener());
            }
        });
        this.mPrimeAlbumMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimeAlbumContextMenuListener>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimeAlbumMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeAlbumContextMenuListener invoke() {
                return new PrimeAlbumContextMenuListener(this.this$0.mActivity);
            }
        });
        this.mPrimeAlbumMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimeAlbumContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimeAlbumMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeAlbumContextMenuProvider invoke() {
                return new PrimeAlbumContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimeAlbumMenuListener());
            }
        });
        this.mPrimeArtistPlaybackHandler = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistPlaybackHandler>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimeArtistPlaybackHandler$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeArtistPlaybackHandler invoke() {
                return new PrimeArtistPlaybackHandler(this.this$0.mActivity);
            }
        });
        this.mPrimeArtistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mPrimeArtistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeArtistContextMenuProvider invoke() {
                return new PrimeArtistContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimeArtistPlaybackHandler());
            }
        });
        this.mStationMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<StationContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mStationMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StationContextMenuProvider invoke() {
                FragmentActivity fragmentActivity2 = this.this$0.mActivity;
                if (fragmentActivity2 != null) {
                    return new StationContextMenuProvider((BaseActivity) fragmentActivity2);
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.BaseActivity");
            }
        });
        this.mRecentlyPlayedMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<BrowseRecentlyPlayedContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mRecentlyPlayedMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BrowseRecentlyPlayedContextMenuProvider invoke() {
                return new BrowseRecentlyPlayedContextMenuProvider(this.this$0.mActivity);
            }
        });
        this.mUserPlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<UserPlaylistContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mUserPlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UserPlaylistContextMenuProvider invoke() {
                return new UserPlaylistContextMenuProvider(this.this$0.mActivity, this.this$0.mPageStateManager, this.this$0.getMPrimePlaylistMenuListener());
            }
        });
        this.mCloudQueueCastingContextMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<CloudQueueCastingContextMenuProvider>() { // from class: com.amazon.mp3.providers.DefaultContextMenuProvider$mCloudQueueCastingContextMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CloudQueueCastingContextMenuProvider invoke() {
                FragmentActivity fragmentActivity2 = this.this$0.mActivity;
                if (fragmentActivity2 == null) {
                    return null;
                }
                return new CloudQueueCastingContextMenuProvider(fragmentActivity2);
            }
        });
        this.mActionType = ActionType.VIEW_CONTEXT_MENU;
    }

    static {
        String simpleName = DefaultContextMenuProvider.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "DefaultContextMenuProvider::class.java.simpleName");
        TAG = simpleName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PrimePlaylistContextMenuListener getMPrimePlaylistMenuListener() {
        return (PrimePlaylistContextMenuListener) this.mPrimePlaylistMenuListener.getValue();
    }

    private final com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider getMPrimePlaylistMenuProvider() {
        return (com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider) this.mPrimePlaylistMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoPlaylistContextMenuListener getVideoPlaylistMenuListener() {
        return (VideoPlaylistContextMenuListener) this.videoPlaylistMenuListener.getValue();
    }

    private final VideoPlaylistContextMenuProvider getVideoPlaylistMenuProvider() {
        return (VideoPlaylistContextMenuProvider) this.videoPlaylistMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoContextMenuProvider.VideoContextMenuProviderListener getVideoMenuListener() {
        return (VideoContextMenuProvider.VideoContextMenuProviderListener) this.videoMenuListener.getValue();
    }

    private final VideoContextMenuProvider getVideoMenuProvider() {
        return (VideoContextMenuProvider) this.videoMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PrimeTrackContextMenuListener getMPrimeTrackMenuListener() {
        return (PrimeTrackContextMenuListener) this.mPrimeTrackMenuListener.getValue();
    }

    private final PrimeTrackContextMenuProvider getMPrimeTrackMenuProvider() {
        return (PrimeTrackContextMenuProvider) this.mPrimeTrackMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PrimeAlbumContextMenuListener getMPrimeAlbumMenuListener() {
        return (PrimeAlbumContextMenuListener) this.mPrimeAlbumMenuListener.getValue();
    }

    private final PrimeAlbumContextMenuProvider getMPrimeAlbumMenuProvider() {
        return (PrimeAlbumContextMenuProvider) this.mPrimeAlbumMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PrimeArtistPlaybackHandler getMPrimeArtistPlaybackHandler() {
        return (PrimeArtistPlaybackHandler) this.mPrimeArtistPlaybackHandler.getValue();
    }

    private final PrimeArtistContextMenuProvider getMPrimeArtistMenuProvider() {
        return (PrimeArtistContextMenuProvider) this.mPrimeArtistMenuProvider.getValue();
    }

    private final StationContextMenuProvider getMStationMenuProvider() {
        return (StationContextMenuProvider) this.mStationMenuProvider.getValue();
    }

    private final BrowseRecentlyPlayedContextMenuProvider getMRecentlyPlayedMenuProvider() {
        return (BrowseRecentlyPlayedContextMenuProvider) this.mRecentlyPlayedMenuProvider.getValue();
    }

    private final UserPlaylistContextMenuProvider getMUserPlaylistMenuProvider() {
        return (UserPlaylistContextMenuProvider) this.mUserPlaylistMenuProvider.getValue();
    }

    private final CloudQueueCastingContextMenuProvider getMCloudQueueCastingContextMenuProvider() {
        return (CloudQueueCastingContextMenuProvider) this.mCloudQueueCastingContextMenuProvider.getValue();
    }

    protected final ActionType getMActionType() {
        return this.mActionType;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldDisplayContextMenu(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if ((metadata instanceof PlaylistMetadata) || (metadata instanceof TrackMetadata) || (metadata instanceof AlbumMetadata)) {
            return true;
        }
        if (!(metadata instanceof ArtistMetadata)) {
            if ((metadata instanceof StationMetadata) || (metadata instanceof UserPlaylistMetadata)) {
                return true;
            }
            if (metadata instanceof RecentsMetadata) {
                RecentsMetadata recentsMetadata = (RecentsMetadata) metadata;
                if (!StringsKt__StringsJVMKt.equals$default(recentsMetadata.getType(), RecentlyPlayedItem.Type.ALL_SONGS.toString(), false, 2, null) && !StringsKt__StringsJVMKt.equals$default(recentsMetadata.getType(), RecentlyPlayedItem.Type.ALEXA_RECENTS.toString(), false, 2, null) && !StringsKt__StringsJVMKt.equals$default(recentsMetadata.getType(), RecentlyPlayedItem.Type.CASTING_RECENTS.toString(), false, 2, null)) {
                    String type = recentsMetadata.getType();
                    RecentlyPlayedItem.Type type2 = RecentlyPlayedItem.Type.STATION;
                    if ((!StringsKt__StringsJVMKt.equals$default(type, type2.toString(), false, 2, null) || !UserSubscriptionUtil.isNightwingOnly()) && (!StringsKt__StringsJVMKt.equals$default(recentsMetadata.getType(), type2.toString(), false, 2, null) || !MediaProvider.Station.isStationFromAnything(metadata.getUri()))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public void openContextMenu(View view, ContentMetadata metadata, InteractionType interaction) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(interaction, "interaction");
        this.mSelectedMetadata = metadata;
        this.mSelectedView = view;
        this.mInteractionType = interaction;
        ContextMenuSupportingFragment contextMenuSupportingFragment = this.mFragment;
        if (contextMenuSupportingFragment == null) {
            return;
        }
        contextMenuSupportingFragment.openContextMenuForView(this, view);
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public void updateStateProvider(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof PlaylistMetadata) {
            getMPrimePlaylistMenuListener().updatePlaylistStatus(PrimeItemsTransformationUtil.convertToPrimePlaylist((PlaylistMetadata) metadata));
            return;
        }
        if (metadata instanceof TrackMetadata) {
            getMPrimeTrackMenuListener().updateTrackStatus(PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) metadata));
            return;
        }
        if (metadata instanceof AlbumMetadata) {
            getMPrimeAlbumMenuListener().updateAlbumStatus(PrimeItemsTransformationUtil.convertToPrimeAlbum((AlbumMetadata) metadata));
        } else if (metadata instanceof UserPlaylistMetadata) {
            PrimePlaylist primePlaylistConvertToPrimePlaylist = PrimeItemsTransformationUtil.convertToPrimePlaylist((UserPlaylistMetadata) metadata);
            primePlaylistConvertToPrimePlaylist.setSource("cirrus");
            getMPrimePlaylistMenuListener().updatePlaylistStatus(primePlaylistConvertToPrimePlaylist);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreateContextMenu(android.view.ContextMenu r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.providers.DefaultContextMenuProvider.onCreateContextMenu(android.view.ContextMenu, java.lang.String):void");
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(MenuItem menuItem, String sourceId) {
        if (this.mContextMenuProvider == null) {
            Log.error(TAG, "Could not handle context menu selection; ContextMenuProvider is null");
            return false;
        }
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = new AdapterView.AdapterContextMenuInfo(this.mSelectedView, 0, 0L);
        adapterContextMenuInfo.targetView.setTag(this.mCatalogItem);
        ContextMenuProvider<?> contextMenuProvider = this.mContextMenuProvider;
        if (contextMenuProvider != null) {
            return contextMenuProvider.onContextMenuItemSelected(this.mActivity, menuItem, adapterContextMenuInfo, sourceId);
        }
        clearSelectedData();
        return false;
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldBindContextMenuOnFreeTier() {
        return AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    private final void clearSelectedData() {
        this.mSelectedMetadata = null;
        this.mSelectedView = null;
        this.mInteractionType = null;
        this.mContextMenuProvider = null;
        this.mCatalogItem = null;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onDestroy(owner);
        this.mActivity = null;
        this.mFragment = null;
    }
}
