package com.amazon.mp3.find.view;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.catalog.fragment.contextmenu.CommunityPlaylistContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimeAlbumContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimePlaylistContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.PrimeTrackContextMenuListener;
import com.amazon.mp3.catalog.fragment.contextmenu.VideoPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.CommunityPlaylistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuItemClickEvent;
import com.amazon.mp3.fragment.contextmenu.ContextMenuMetricHandler;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.ContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeArtistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.StationContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.UserPlaylistContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.VideoPlaylistContextMenuProvider;
import com.amazon.mp3.home.BrowseRecentlyPlayedContextMenuProvider;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchContextMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0085\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t*\u0001!\u0018\u0000 \u0086\u00012\u00020\u0001:\u0002\u0086\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u001c\u0010\n\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010¢\u0006\u0002\u0010\u0012J\b\u0010u\u001a\u00020vH\u0002J\u001c\u0010w\u001a\u00020\u000e2\b\u0010x\u001a\u0004\u0018\u00010y2\b\u0010z\u001a\u0004\u0018\u00010{H\u0016J\u001c\u0010|\u001a\u00020v2\b\u0010}\u001a\u0004\u0018\u00010~2\b\u0010z\u001a\u0004\u0018\u00010{H\u0016J#\u0010\u007f\u001a\u00020v2\u0007\u0010\u0080\u0001\u001a\u00020V2\u0007\u0010\u0081\u0001\u001a\u00020\f2\u0007\u0010\u0082\u0001\u001a\u00020&H\u0016J\t\u0010\u0083\u0001\u001a\u00020\u000eH\u0016J\u0012\u0010\u0084\u0001\u001a\u00020\u000e2\u0007\u0010\u0081\u0001\u001a\u00020\fH\u0016J\u0012\u0010\u0085\u0001\u001a\u00020v2\u0007\u0010\u0081\u0001\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b\u001d\u0010\u001eR\u0010\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\"R\u0014\u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020(8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u001a\u001a\u0004\b)\u0010*R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u001a\u001a\u0004\b.\u0010/R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u001a\u001a\u0004\b3\u00104R\u001b\u00106\u001a\u0002078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b:\u0010\u001a\u001a\u0004\b8\u00109R\u001b\u0010;\u001a\u00020<8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\u001a\u001a\u0004\b=\u0010>R\u001b\u0010@\u001a\u00020A8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u0010\u001a\u001a\u0004\bB\u0010CR\u001b\u0010E\u001a\u00020F8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bI\u0010\u001a\u001a\u0004\bG\u0010HR\u001b\u0010J\u001a\u00020K8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\u001a\u001a\u0004\bL\u0010MR\u001b\u0010O\u001a\u00020P8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bS\u0010\u001a\u001a\u0004\bQ\u0010RR\u0010\u0010T\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010U\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010W\u001a\u00020X8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b[\u0010\u001a\u001a\u0004\bY\u0010ZR\u001b\u0010\\\u001a\u00020]8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\u001a\u001a\u0004\b^\u0010_R\u001b\u0010a\u001a\u00020b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\be\u0010\u001a\u001a\u0004\bc\u0010dR\u001d\u0010f\u001a\u0004\u0018\u00010g8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bj\u0010\u001a\u001a\u0004\bh\u0010iR\u001b\u0010k\u001a\u00020l8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bo\u0010\u001a\u001a\u0004\bm\u0010nR\u001d\u0010p\u001a\u0004\u0018\u00010q8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bt\u0010\u001a\u001a\u0004\br\u0010s¨\u0006\u0087\u0001"}, d2 = {"Lcom/amazon/mp3/find/view/SearchContextMenuProvider;", "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "mFragment", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "mLibraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "mPageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "mOverflowMenuClickHandler", "Lkotlin/Function2;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuItemClickEvent;", "", "mPageTypeFunc", "Lkotlin/Function0;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;Lcom/amazon/mp3/library/provider/LibraryStateProvider;Lcom/amazon/mp3/playback/state/PageStateManager;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)V", "mCatalogItem", "", "mCommunityPlaylistMenuListener", "Lcom/amazon/mp3/catalog/fragment/contextmenu/CommunityPlaylistContextMenuListener;", "getMCommunityPlaylistMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/CommunityPlaylistContextMenuListener;", "mCommunityPlaylistMenuListener$delegate", "Lkotlin/Lazy;", "mCommunityPlaylistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider;", "getMCommunityPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/CommunityPlaylistContextMenuProvider;", "mCommunityPlaylistMenuProvider$delegate", "mContextMenuMetricHandler", "com/amazon/mp3/find/view/SearchContextMenuProvider$mContextMenuMetricHandler$1", "Lcom/amazon/mp3/find/view/SearchContextMenuProvider$mContextMenuMetricHandler$1;", "mContextMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "mInteractionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "mPrimeAlbumMenuListener", "Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeAlbumContextMenuListener;", "getMPrimeAlbumMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeAlbumContextMenuListener;", "mPrimeAlbumMenuListener$delegate", "mPrimeAlbumMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider;", "getMPrimeAlbumMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider;", "mPrimeAlbumMenuProvider$delegate", "mPrimeArtistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimeArtistContextMenuProvider;", "getMPrimeArtistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeArtistContextMenuProvider;", "mPrimeArtistMenuProvider$delegate", "mPrimeArtistPlaybackHandler", "Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "getMPrimeArtistPlaybackHandler", "()Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "mPrimeArtistPlaybackHandler$delegate", "mPrimePlaylistMenuListener", "Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimePlaylistContextMenuListener;", "getMPrimePlaylistMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimePlaylistContextMenuListener;", "mPrimePlaylistMenuListener$delegate", "mPrimePlaylistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuProvider;", "getMPrimePlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimePlaylistContextMenuProvider;", "mPrimePlaylistMenuProvider$delegate", "mPrimeTrackMenuListener", "Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeTrackContextMenuListener;", "getMPrimeTrackMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/PrimeTrackContextMenuListener;", "mPrimeTrackMenuListener$delegate", "mPrimeTrackMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/PrimeTrackContextMenuProvider;", "getMPrimeTrackMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/PrimeTrackContextMenuProvider;", "mPrimeTrackMenuProvider$delegate", "mRecentlyPlayedMenuProvider", "Lcom/amazon/mp3/home/BrowseRecentlyPlayedContextMenuProvider;", "getMRecentlyPlayedMenuProvider", "()Lcom/amazon/mp3/home/BrowseRecentlyPlayedContextMenuProvider;", "mRecentlyPlayedMenuProvider$delegate", "mSelectedMetadata", "mSelectedView", "Landroid/view/View;", "mStationMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/StationContextMenuProvider;", "getMStationMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/StationContextMenuProvider;", "mStationMenuProvider$delegate", "mUserPlaylistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/UserPlaylistContextMenuProvider;", "getMUserPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/UserPlaylistContextMenuProvider;", "mUserPlaylistMenuProvider$delegate", "mVideoMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "getMVideoMenuListener", "()Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "mVideoMenuListener$delegate", "mVideoMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "getMVideoMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "mVideoMenuProvider$delegate", "mVideoPlaylistMenuListener", "Lcom/amazon/mp3/catalog/fragment/contextmenu/VideoPlaylistContextMenuListener;", "getMVideoPlaylistMenuListener", "()Lcom/amazon/mp3/catalog/fragment/contextmenu/VideoPlaylistContextMenuListener;", "mVideoPlaylistMenuListener$delegate", "mVideoPlaylistMenuProvider", "Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider;", "getMVideoPlaylistMenuProvider", "()Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider;", "mVideoPlaylistMenuProvider$delegate", "clearSelectedData", "", "onContextMenuItemSelected", "menuItem", "Landroid/view/MenuItem;", "sourceId", "", "onCreateContextMenu", "menu", "Landroid/view/ContextMenu;", "openContextMenu", "view", "metadata", "interaction", "shouldBindContextMenuOnFreeTier", "shouldDisplayContextMenu", "updateStateProvider", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchContextMenuProvider implements BrushContextMenuProvider {
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(SearchContextMenuProvider.class.getName());
    private final FragmentActivity mActivity;
    private Object mCatalogItem;

    /* JADX INFO: renamed from: mCommunityPlaylistMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy mCommunityPlaylistMenuListener;

    /* JADX INFO: renamed from: mCommunityPlaylistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mCommunityPlaylistMenuProvider;
    private final SearchContextMenuProvider$mContextMenuMetricHandler$1 mContextMenuMetricHandler;
    private ContextMenuProvider<?> mContextMenuProvider;
    private final ContextMenuSupportingFragment mFragment;
    private InteractionType mInteractionType;
    private final LibraryStateProvider mLibraryStateProvider;
    private final Function2<ContentMetadata, ContextMenuItemClickEvent, Boolean> mOverflowMenuClickHandler;
    private final PageStateManager mPageStateManager;
    private final Function0<PageType> mPageTypeFunc;

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

    /* JADX INFO: renamed from: mVideoMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy mVideoMenuListener;

    /* JADX INFO: renamed from: mVideoMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mVideoMenuProvider;

    /* JADX INFO: renamed from: mVideoPlaylistMenuListener$delegate, reason: from kotlin metadata */
    private final Lazy mVideoPlaylistMenuListener;

    /* JADX INFO: renamed from: mVideoPlaylistMenuProvider$delegate, reason: from kotlin metadata */
    private final Lazy mVideoPlaylistMenuProvider;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v35, types: [com.amazon.mp3.find.view.SearchContextMenuProvider$mContextMenuMetricHandler$1] */
    public SearchContextMenuProvider(FragmentActivity mActivity, ContextMenuSupportingFragment mFragment, LibraryStateProvider mLibraryStateProvider, PageStateManager mPageStateManager, Function2<? super ContentMetadata, ? super ContextMenuItemClickEvent, Boolean> mOverflowMenuClickHandler, Function0<? extends PageType> mPageTypeFunc) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(mLibraryStateProvider, "mLibraryStateProvider");
        Intrinsics.checkNotNullParameter(mPageStateManager, "mPageStateManager");
        Intrinsics.checkNotNullParameter(mOverflowMenuClickHandler, "mOverflowMenuClickHandler");
        Intrinsics.checkNotNullParameter(mPageTypeFunc, "mPageTypeFunc");
        this.mActivity = mActivity;
        this.mFragment = mFragment;
        this.mLibraryStateProvider = mLibraryStateProvider;
        this.mPageStateManager = mPageStateManager;
        this.mOverflowMenuClickHandler = mOverflowMenuClickHandler;
        this.mPageTypeFunc = mPageTypeFunc;
        this.mPrimePlaylistMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimePlaylistContextMenuListener>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimePlaylistMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimePlaylistContextMenuListener invoke() {
                return new PrimePlaylistContextMenuListener(this.this$0.mActivity);
            }
        });
        this.mPrimePlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimePlaylistContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimePlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimePlaylistContextMenuProvider invoke() {
                return new PrimePlaylistContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimePlaylistMenuListener(), ContextMenuPageType.SEARCH);
            }
        });
        this.mVideoPlaylistMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlaylistContextMenuListener>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mVideoPlaylistMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPlaylistContextMenuListener invoke() {
                return new VideoPlaylistContextMenuListener(this.this$0.mActivity);
            }
        });
        this.mVideoPlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlaylistContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mVideoPlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPlaylistContextMenuProvider invoke() {
                return new VideoPlaylistContextMenuProvider(this.this$0.mActivity, this.this$0.getMVideoPlaylistMenuListener());
            }
        });
        this.mVideoMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<VideoContextMenuProvider.VideoContextMenuProviderListener>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mVideoMenuListener$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoContextMenuProvider.VideoContextMenuProviderListener invoke() {
                return new VideoContextMenuProvider.VideoContextMenuProviderListener();
            }
        });
        this.mVideoMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<VideoContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mVideoMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoContextMenuProvider invoke() {
                return new VideoContextMenuProvider(this.this$0.mActivity, this.this$0.getMVideoMenuListener());
            }
        });
        this.mPrimeTrackMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimeTrackContextMenuListener>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimeTrackMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeTrackContextMenuListener invoke() {
                return new PrimeTrackContextMenuListener(this.this$0.mActivity, this.this$0.mLibraryStateProvider);
            }
        });
        this.mPrimeTrackMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimeTrackContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimeTrackMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeTrackContextMenuProvider invoke() {
                return new PrimeTrackContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimeTrackMenuListener());
            }
        });
        this.mPrimeAlbumMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimeAlbumContextMenuListener>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimeAlbumMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeAlbumContextMenuListener invoke() {
                return new PrimeAlbumContextMenuListener(this.this$0.mActivity);
            }
        });
        this.mPrimeAlbumMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimeAlbumContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimeAlbumMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeAlbumContextMenuProvider invoke() {
                return new PrimeAlbumContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimeAlbumMenuListener());
            }
        });
        this.mPrimeArtistPlaybackHandler = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistPlaybackHandler>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimeArtistPlaybackHandler$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeArtistPlaybackHandler invoke() {
                return new PrimeArtistPlaybackHandler(this.this$0.mActivity);
            }
        });
        this.mPrimeArtistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mPrimeArtistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeArtistContextMenuProvider invoke() {
                return new PrimeArtistContextMenuProvider(this.this$0.mActivity, this.this$0.getMPrimeArtistPlaybackHandler());
            }
        });
        this.mStationMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<StationContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mStationMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StationContextMenuProvider invoke() {
                return new StationContextMenuProvider((BaseActivity) this.this$0.mActivity);
            }
        });
        this.mRecentlyPlayedMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<BrowseRecentlyPlayedContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mRecentlyPlayedMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BrowseRecentlyPlayedContextMenuProvider invoke() {
                return new BrowseRecentlyPlayedContextMenuProvider(this.this$0.mActivity);
            }
        });
        this.mUserPlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<UserPlaylistContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mUserPlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UserPlaylistContextMenuProvider invoke() {
                return new UserPlaylistContextMenuProvider(this.this$0.mActivity, this.this$0.mPageStateManager, this.this$0.getMPrimePlaylistMenuListener());
            }
        });
        this.mCommunityPlaylistMenuListener = LazyKt__LazyJVMKt.lazy(new Function0<CommunityPlaylistContextMenuListener>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mCommunityPlaylistMenuListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CommunityPlaylistContextMenuListener invoke() {
                return new CommunityPlaylistContextMenuListener(this.this$0.mActivity);
            }
        });
        this.mCommunityPlaylistMenuProvider = LazyKt__LazyJVMKt.lazy(new Function0<CommunityPlaylistContextMenuProvider>() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mCommunityPlaylistMenuProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CommunityPlaylistContextMenuProvider invoke() {
                return new CommunityPlaylistContextMenuProvider(this.this$0.mActivity, this.this$0.getMCommunityPlaylistMenuListener(), ContextMenuPageType.SEARCH);
            }
        });
        this.mContextMenuMetricHandler = new ContextMenuMetricHandler() { // from class: com.amazon.mp3.find.view.SearchContextMenuProvider$mContextMenuMetricHandler$1
            @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuMetricHandler
            public boolean handleSendMetricEvent(ContextMenuItemClickEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                return ((Boolean) this.this$0.mOverflowMenuClickHandler.mo4invoke(this.this$0.mSelectedMetadata, event)).booleanValue();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PrimePlaylistContextMenuListener getMPrimePlaylistMenuListener() {
        return (PrimePlaylistContextMenuListener) this.mPrimePlaylistMenuListener.getValue();
    }

    private final PrimePlaylistContextMenuProvider getMPrimePlaylistMenuProvider() {
        return (PrimePlaylistContextMenuProvider) this.mPrimePlaylistMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoPlaylistContextMenuListener getMVideoPlaylistMenuListener() {
        return (VideoPlaylistContextMenuListener) this.mVideoPlaylistMenuListener.getValue();
    }

    private final VideoPlaylistContextMenuProvider getMVideoPlaylistMenuProvider() {
        return (VideoPlaylistContextMenuProvider) this.mVideoPlaylistMenuProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoContextMenuProvider.VideoContextMenuProviderListener getMVideoMenuListener() {
        return (VideoContextMenuProvider.VideoContextMenuProviderListener) this.mVideoMenuListener.getValue();
    }

    private final VideoContextMenuProvider getMVideoMenuProvider() {
        return (VideoContextMenuProvider) this.mVideoMenuProvider.getValue();
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

    /* JADX INFO: Access modifiers changed from: private */
    public final CommunityPlaylistContextMenuListener getMCommunityPlaylistMenuListener() {
        return (CommunityPlaylistContextMenuListener) this.mCommunityPlaylistMenuListener.getValue();
    }

    private final CommunityPlaylistContextMenuProvider getMCommunityPlaylistMenuProvider() {
        return (CommunityPlaylistContextMenuProvider) this.mCommunityPlaylistMenuProvider.getValue();
    }

    @Override // com.amazon.music.views.library.providers.MetadataContextMenuProvider
    public boolean shouldDisplayContextMenu(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if ((metadata instanceof PlaylistMetadata) || (metadata instanceof TrackMetadata) || (metadata instanceof AlbumMetadata)) {
            return true;
        }
        if (!(metadata instanceof ArtistMetadata)) {
            if ((metadata instanceof StationMetadata) || (metadata instanceof UserPlaylistMetadata) || (metadata instanceof CommunityPlaylistMetadata)) {
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
        this.mFragment.openContextMenuForView(this, view);
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
            return;
        }
        if (metadata instanceof UserPlaylistMetadata) {
            PrimePlaylist primePlaylistConvertToPrimePlaylist = PrimeItemsTransformationUtil.convertToPrimePlaylist((UserPlaylistMetadata) metadata);
            primePlaylistConvertToPrimePlaylist.setSource("cirrus");
            getMPrimePlaylistMenuListener().updatePlaylistStatus(primePlaylistConvertToPrimePlaylist);
        } else if (metadata instanceof CommunityPlaylistMetadata) {
            PrimePlaylist communityPlaylist = PrimeItemsTransformationUtil.convertToPrimePlaylist((CommunityPlaylistMetadata) metadata);
            communityPlaylist.setSource("cirrus");
            CommunityPlaylistContextMenuListener mCommunityPlaylistMenuListener = getMCommunityPlaylistMenuListener();
            Intrinsics.checkNotNullExpressionValue(communityPlaylist, "communityPlaylist");
            CommunityPlaylistContextMenuProvider.CommunityPlaylistContextMenuProviderListener.updatePlaylistStatus$default(mCommunityPlaylistMenuListener, communityPlaylist, null, 2, null);
        }
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public void onCreateContextMenu(ContextMenu menu, String sourceId) {
        ContextMenuProvider<?> mPrimeTrackMenuProvider;
        PrimeTrack primeTrackConvertToPrimeTrack;
        ContextMenuProvider<?> mPrimePlaylistMenuProvider;
        ContentMetadata contentMetadata = this.mSelectedMetadata;
        if (contentMetadata == null) {
            return;
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            PlaylistMetadata playlistMetadata = (PlaylistMetadata) contentMetadata;
            if (Intrinsics.areEqual(CollectionsKt___CollectionsKt.getOrNull(playlistMetadata.getContentTypes(), 0), "VIDEO")) {
                this.mCatalogItem = PrimeItemsTransformationUtil.convertToMusicVideoPlaylist(playlistMetadata);
                mPrimePlaylistMenuProvider = getMVideoPlaylistMenuProvider();
            } else {
                this.mCatalogItem = PrimeItemsTransformationUtil.convertToPrimePlaylist(playlistMetadata);
                mPrimePlaylistMenuProvider = getMPrimePlaylistMenuProvider();
            }
            this.mContextMenuProvider = mPrimePlaylistMenuProvider;
        } else if (contentMetadata instanceof TrackMetadata) {
            TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
            if (Intrinsics.areEqual(trackMetadata.getAssetType(), "VIDEO")) {
                mPrimeTrackMenuProvider = getMVideoMenuProvider();
            } else {
                mPrimeTrackMenuProvider = getMPrimeTrackMenuProvider();
            }
            this.mContextMenuProvider = mPrimeTrackMenuProvider;
            if (trackMetadata.getContentEncodings() != null) {
                primeTrackConvertToPrimeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack(trackMetadata, trackMetadata.getContentEncodings());
            } else {
                primeTrackConvertToPrimeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack(trackMetadata);
            }
            this.mCatalogItem = primeTrackConvertToPrimeTrack;
        } else if (contentMetadata instanceof AlbumMetadata) {
            this.mContextMenuProvider = getMPrimeAlbumMenuProvider();
            this.mCatalogItem = PrimeItemsTransformationUtil.convertToPrimeAlbum((AlbumMetadata) contentMetadata);
        } else if (contentMetadata instanceof ArtistMetadata) {
            this.mContextMenuProvider = getMPrimeArtistMenuProvider();
            this.mCatalogItem = PrimeItemsTransformationUtil.convertToPrimeArtist((ArtistMetadata) contentMetadata);
        } else if (contentMetadata instanceof StationMetadata) {
            this.mContextMenuProvider = getMStationMenuProvider();
            this.mCatalogItem = PrimeItemsTransformationUtil.convertToStation((StationMetadata) contentMetadata);
        } else if (contentMetadata instanceof RecentsMetadata) {
            this.mContextMenuProvider = getMRecentlyPlayedMenuProvider();
            this.mCatalogItem = RecentlyPlayedManager.getInstance(this.mActivity).toRecentItem((RecentsMetadata) contentMetadata);
        } else if (contentMetadata instanceof UserPlaylistMetadata) {
            this.mContextMenuProvider = getMUserPlaylistMenuProvider();
            this.mCatalogItem = PrimeItemsTransformationUtil.convertToPrimePlaylist((UserPlaylistMetadata) contentMetadata);
        } else if (contentMetadata instanceof CommunityPlaylistMetadata) {
            this.mContextMenuProvider = getMCommunityPlaylistMenuProvider();
            this.mCatalogItem = PrimeItemsTransformationUtil.convertToPrimePlaylist((CommunityPlaylistMetadata) contentMetadata);
        } else {
            this.mContextMenuProvider = null;
        }
        ContextMenuProvider<?> contextMenuProvider = this.mContextMenuProvider;
        if (contextMenuProvider == null) {
            return;
        }
        contextMenuProvider.setBlockRef(contentMetadata.getViewId());
        contextMenuProvider.setMetricHandler(this.mContextMenuMetricHandler);
        contextMenuProvider.onCreateContextMenu(this.mActivity, menu, this.mCatalogItem, 0, sourceId, ActionType.SHOW_OVERFLOW, this.mPageTypeFunc.invoke(), this.mInteractionType, null, null);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuHandler
    public boolean onContextMenuItemSelected(MenuItem menuItem, String sourceId) {
        if (this.mContextMenuProvider == null) {
            logger.error(Intrinsics.stringPlus(TAG, "::Could not handle context menu selection; ContextMenuProvider is null"));
            return false;
        }
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = new AdapterView.AdapterContextMenuInfo(this.mSelectedView, 0, 0L);
        adapterContextMenuInfo.targetView.setTag(this.mCatalogItem);
        ContextMenuProvider<?> contextMenuProvider = this.mContextMenuProvider;
        if (contextMenuProvider != null) {
            contextMenuProvider.setPageType(this.mPageTypeFunc.invoke());
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
        ContextMenuProvider<?> contextMenuProvider = this.mContextMenuProvider;
        if (contextMenuProvider != null) {
            contextMenuProvider.setMetricHandler(null);
        }
        this.mContextMenuProvider = null;
        this.mCatalogItem = null;
    }
}
