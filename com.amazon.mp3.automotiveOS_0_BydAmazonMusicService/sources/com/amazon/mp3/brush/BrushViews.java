package com.amazon.mp3.brush;

import android.content.Context;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.view.multiminiplayer.CloudQueueCastingListListenerProvider;
import com.amazon.mp3.providers.ArtistClickHandler;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.GenreClickHandler;
import com.amazon.mp3.providers.ViewsConfiguration;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository;
import com.amazon.mp3.video.stories.VideoStoryPlaybackProvider;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.explore.widgets.binders.ExploreFeaturedShovelerBinder;
import com.amazon.music.explore.widgets.binders.ExploreHorizontalItemBinder;
import com.amazon.music.explore.widgets.binders.ExploreLiveStreamBarkerBinder;
import com.amazon.music.explore.widgets.binders.ExploreSpotlightCarouselBinder;
import com.amazon.music.explore.widgets.binders.ExploreVisualShovelerBinder;
import com.amazon.music.explore.widgets.listeners.ExploreClickListenerFactory;
import com.amazon.music.find.binder.SearchResponseMessageBinder;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.binders.ArtistDetailHeaderBinder;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.BaseDetailHeaderBinder;
import com.amazon.music.views.library.binders.ButtonBinder;
import com.amazon.music.views.library.binders.ButtonNavigatorBinder;
import com.amazon.music.views.library.binders.CarModeButtonNavigatorBinder;
import com.amazon.music.views.library.binders.CarouselBinder;
import com.amazon.music.views.library.binders.CastingListItemHorizontalTileBinder;
import com.amazon.music.views.library.binders.CustomerProfileBinder;
import com.amazon.music.views.library.binders.ExposedRefinementListBinder;
import com.amazon.music.views.library.binders.FeatureBarkerBinder;
import com.amazon.music.views.library.binders.FeaturePlayBinder;
import com.amazon.music.views.library.binders.FeaturePlayV2Binder;
import com.amazon.music.views.library.binders.FeaturePlayV2PodcastBinder;
import com.amazon.music.views.library.binders.FollowTileBinder;
import com.amazon.music.views.library.binders.GenreDetailHeaderBinder;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.binders.GridListHeaderBinder;
import com.amazon.music.views.library.binders.HeaderBinder;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.HotspotTooltipBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.binders.LinkNavigatorBinder;
import com.amazon.music.views.library.binders.LivestreamBarkerBinder;
import com.amazon.music.views.library.binders.MessageViewBinder;
import com.amazon.music.views.library.binders.PillCellBinder;
import com.amazon.music.views.library.binders.PodcastFeaturePlayBinder;
import com.amazon.music.views.library.binders.PodcastShovelerBinder;
import com.amazon.music.views.library.binders.PodcastTileBinder;
import com.amazon.music.views.library.binders.SectionBinder;
import com.amazon.music.views.library.binders.ShovelerBinder;
import com.amazon.music.views.library.binders.SimpleHorizontalTileBinder;
import com.amazon.music.views.library.binders.SimpleShovelerBinder;
import com.amazon.music.views.library.binders.SimpleVerticalTileBinder;
import com.amazon.music.views.library.binders.ThumbnailCellBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.binders.VideoStoryShovelerBinder;
import com.amazon.music.views.library.binders.VideoStoryTileBinder;
import com.amazon.music.views.library.binders.VisualRowItemBinder;
import com.amazon.music.views.library.deeplinks.DeeplinkClickListenerFactory;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.CachedArtworkProvider;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.CustomerProfileClickListener;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.EntityTrackingIntentServiceProvider;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.providers.FollowProvider;
import com.amazon.music.views.library.providers.HotspotTooltipListenerProvider;
import com.amazon.music.views.library.providers.ItemOrdinalProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.PlaybackProvider;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.providers.ShareProfileProvider;
import com.amazon.music.views.library.providers.ShareProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.EndlessScrollListener;
import com.amazon.music.views.library.recyclerview.OnItemClickListener;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.podcast.views.browseHomeWidgets.PodcastEpisodeDescriptiveShovelerBinder;
import com.amazon.podcast.views.browseHomeWidgets.PodcastVisualShovelerCompactBinder;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemBinder;
import com.amazon.podcast.views.horizontalRowItemsListView.PodcastEpisodeHorizontalRowItemBinder;
import com.amazon.podcast.views.horizontalRowItemsListView.PodcastHorizontalRowItemBinder;
import com.amazon.podcast.views.verticalItemView.VerticalItemBinder;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushViews.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Î\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020-J,\u0010^\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020a0`2\u0006\u0010[\u001a\u00020\\2\u0006\u0010b\u001a\u00020-JJ\u0010^\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020a0`2\u0006\u0010[\u001a\u00020\\2\u0006\u0010b\u001a\u00020-2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010d2\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020\\\u0018\u00010fJ)\u0010g\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020d\u0012\u0006\b\u0001\u0012\u00020a0i0h2\u0006\u0010W\u001a\u00020X¢\u0006\u0002\u0010jJ\u0006\u0010k\u001a\u000207J\u0018\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u00108\u001a\u000209H\u0002J\u0014\u0010p\u001a\u00020V2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020a0`J\"\u0010r\u001a\u00020V2\u001a\u0010s\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010tj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`uJ\u0010\u0010v\u001a\u00020V2\u0006\u0010w\u001a\u00020dH\u0002J\u0014\u0010x\u001a\u00020V2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020a0`J\u000e\u0010y\u001a\u00020V2\u0006\u0010Y\u001a\u00020ZR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u00020-X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020-X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010FX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010HX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010K\u001a\u0004\u0018\u00010LX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020RX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u0004\u0018\u00010TX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006z"}, d2 = {"Lcom/amazon/mp3/brush/BrushViews;", "", "configuration", "Lcom/amazon/mp3/providers/ViewsConfiguration;", "(Lcom/amazon/mp3/providers/ViewsConfiguration;)V", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "artistClickHandler", "Lcom/amazon/mp3/providers/ArtistClickHandler;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "artworkProvider", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "brushEndlessScrollDataProvider", "Lcom/amazon/music/views/library/providers/EndlessScrollDataProvider;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "castingTileListenerProvider", "Lcom/amazon/mp3/playback/view/multiminiplayer/CloudQueueCastingListListenerProvider;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "context", "Landroid/content/Context;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "customerProfileClickListener", "Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "deeplinkClickListenerFactory", "Lcom/amazon/music/views/library/deeplinks/DeeplinkClickListenerFactory;", "deeplinksManager", "Lcom/amazon/music/deeplink/DeeplinksManager;", "entityTrackingIntentServiceProvider", "Lcom/amazon/music/views/library/providers/EntityTrackingIntentServiceProvider;", "featuredPlayMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "followProvider", "Lcom/amazon/music/views/library/providers/FollowProvider;", "genreClickHandler", "Lcom/amazon/mp3/providers/GenreClickHandler;", "headerAdapterProvider", "headerView", "Landroidx/recyclerview/widget/RecyclerView;", "getHeaderView", "()Landroidx/recyclerview/widget/RecyclerView;", "setHeaderView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "hotspotBinder", "Lcom/amazon/music/views/library/binders/HotspotTooltipBinder;", "hotspotTooltipListenerProvider", "Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "imageBinder", "Lcom/amazon/music/views/library/binders/ImageBinder;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "itemClickListener", "Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;", "itemOrdinalProvider", "Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "mainView", "playbackProvider", "Lcom/amazon/music/views/library/providers/PlaybackProvider;", "refinementClickListenerProvider", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "shareProfileProvider", "Lcom/amazon/music/views/library/providers/ShareProfileProvider;", "shareProvider", "Lcom/amazon/music/views/library/providers/ShareProvider;", "spellCorrectionListenerClickProvider", "Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;", "stateChangeProvider", "Lcom/amazon/music/views/library/providers/StateChangeProvider;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "createHeaderView", "", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "listModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "tileType", "", "headerRecyclerView", "createRootView", "baseViewModels", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "recyclerView", "recyclerViewContainer", "Landroid/view/ViewGroup;", "fullSpanList", "", "getBinders", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "(Lcom/amazon/music/subscription/UserSubscription;)[Lcom/amazon/music/views/library/binders/UniversalBinder;", "getImageBinder", "getPlaybackContainerBinder", "Lcom/amazon/music/views/library/binders/SectionBinder;", "horizontalTileBinder", "Lcom/amazon/music/views/library/binders/HorizontalTileBinder;", "removeItemsFromAdapter", "models", "setComparator", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "setMarginsForGrid", "viewGroup", "updateAdapter", "updateHeaderAdapter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushViews {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ArtistClickHandler artistClickHandler;
    private final ArtworkFrameBinder artworkFrameBinder;
    private final CachedArtworkProvider artworkProvider;
    private final EndlessScrollDataProvider brushEndlessScrollDataProvider;
    private final BrushUriClickListenerProvider brushUriClickListenerProvider;
    private final CloudQueueCastingListListenerProvider castingTileListenerProvider;
    private final ContentEligibilityProvider contentEligibilityProvider;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final ContentViewedListener contentViewedListener;
    private final Context context;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final CustomerProfileClickListener customerProfileClickListener;
    private final DeeplinkClickListenerFactory deeplinkClickListenerFactory;
    private final DeeplinksManager deeplinksManager;
    private final EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider;
    private final FeaturedPlayMusicProvider featuredPlayMusicProvider;
    private final FollowProvider followProvider;
    private final GenreClickHandler genreClickHandler;
    private final BaseViewModelAdapterProvider headerAdapterProvider;
    public RecyclerView headerView;
    private final HotspotTooltipBinder hotspotBinder;
    private final HotspotTooltipListenerProvider hotspotTooltipListenerProvider;
    private final ImageBinder imageBinder;
    private final ImageLoader imageLoader;
    private final OnItemClickListener itemClickListener;
    private final ItemOrdinalProvider itemOrdinalProvider;
    private final LifecycleOwner lifecycleOwner;
    private RecyclerView mainView;
    private final PlaybackProvider playbackProvider;
    private final RefinementClickListenerProvider refinementClickListenerProvider;
    private final RowButtonOnClickProvider rowButtonOnClickProvider;
    private final SeeMoreProvider seeMoreProvider;
    private final ShareProfileProvider shareProfileProvider;
    private final ShareProvider shareProvider;
    private final SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider;
    private final StateChangeProvider stateChangeProvider;
    private final StyleSheet styleSheet;
    private final TrackDownloadButtonProvider trackDownloadButtonProvider;

    public BrushViews(ViewsConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Context context = configuration.getContext();
        this.context = context;
        this.lifecycleOwner = configuration.getLifecycleOwner();
        StyleSheet styleSheet = configuration.getStyleSheet();
        this.styleSheet = styleSheet;
        ImageLoader imageLoader = configuration.getImageLoader();
        this.imageLoader = imageLoader;
        this.contentEligibilityProvider = configuration.getContentEligibilityProvider();
        this.rowButtonOnClickProvider = configuration.getRowButtonOnClickProvider();
        this.contextMenuProvider = configuration.getContextMenuProvider();
        DeeplinksManager deepLinksManager = configuration.getDeepLinksManager();
        this.deeplinksManager = deepLinksManager;
        this.deeplinkClickListenerFactory = new DeeplinkClickListenerFactory(context, deepLinksManager, configuration.getContentMetadataOnClickListener());
        this.contentEnabilityProvider = configuration.getContentEnabilityProvider();
        this.seeMoreProvider = configuration.getSeeMoreProvider();
        this.brushUriClickListenerProvider = configuration.getBrushUriClickListenerProvider();
        this.brushEndlessScrollDataProvider = configuration.getBrushEndlessScrollDataProvider();
        this.adapterProvider = configuration.getAdapterProvider();
        this.headerAdapterProvider = configuration.getHeaderAdapterProvider();
        CachedArtworkProvider artworkProvider = configuration.getArtworkProvider();
        this.artworkProvider = artworkProvider;
        this.shareProfileProvider = configuration.getShareProfileProvider();
        this.customerProfileClickListener = configuration.getCustomerProfileClickListener();
        this.contentViewedListener = configuration.getContentViewedListener();
        this.featuredPlayMusicProvider = configuration.getFeaturedPlayMusicProvider();
        this.genreClickHandler = configuration.getGenreClickHandler();
        this.artistClickHandler = configuration.getArtistClickHandler();
        this.playbackProvider = configuration.getPlaybackProvider();
        this.shareProvider = configuration.getShareProvider();
        this.followProvider = configuration.getFollowProvider();
        this.refinementClickListenerProvider = configuration.getRefinementClickListenerProvider();
        this.castingTileListenerProvider = new CloudQueueCastingListListenerProvider();
        this.trackDownloadButtonProvider = configuration.getTrackDownloadButtonProvider();
        this.spellCorrectionListenerClickProvider = configuration.getSpellCorrectionListenerClickProvider();
        this.stateChangeProvider = configuration.getStateChangeProvider();
        this.itemOrdinalProvider = configuration.getItemOrdinalProvider();
        this.entityTrackingIntentServiceProvider = configuration.getEntityTrackingIntentServiceProvider();
        this.itemClickListener = configuration.getItemClickListener();
        HotspotTooltipListenerProvider hotspotTooltipListenerProvider = configuration.getHotspotTooltipListenerProvider();
        this.hotspotTooltipListenerProvider = hotspotTooltipListenerProvider;
        ImageBinder imageBinder = new ImageBinder(context, styleSheet, imageLoader);
        this.imageBinder = imageBinder;
        this.artworkFrameBinder = new ArtworkFrameBinder(context, styleSheet, imageBinder, artworkProvider, new CatalogEnabilityProvider());
        this.hotspotBinder = new HotspotTooltipBinder(hotspotTooltipListenerProvider);
    }

    public final RecyclerView getHeaderView() {
        RecyclerView recyclerView = this.headerView;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("headerView");
        return null;
    }

    public final void setHeaderView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.headerView = recyclerView;
    }

    public final void createRootView(UserSubscription userSubscription, List<? extends BaseViewModel> baseViewModels, int tileType, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(baseViewModels, "baseViewModels");
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        createRootView$default(this, userSubscription, baseViewModels, tileType, recyclerView, null, null, 32, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void createRootView$default(BrushViews brushViews, UserSubscription userSubscription, List list, int i, RecyclerView recyclerView, ViewGroup viewGroup, Set set, int i2, Object obj) {
        brushViews.createRootView(userSubscription, list, i, recyclerView, (i2 & 16) != 0 ? null : viewGroup, (i2 & 32) != 0 ? null : set);
    }

    public final void createRootView(UserSubscription userSubscription, List<? extends BaseViewModel> baseViewModels, int tileType, RecyclerView recyclerView, ViewGroup recyclerViewContainer, Set<Integer> fullSpanList) {
        RecyclerView recyclerView2;
        RecyclerView recyclerView3 = recyclerView;
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(baseViewModels, "baseViewModels");
        Intrinsics.checkNotNullParameter(recyclerView3, "recyclerView");
        UniversalBinder<? extends ViewGroup, ? extends BaseViewModel>[] binders = getBinders(userSubscription);
        this.mainView = recyclerView3;
        RecyclerView recyclerView4 = null;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView3 = null;
        }
        recyclerView3.setItemViewCacheSize(20);
        RecyclerView recyclerView5 = this.mainView;
        if (recyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView5 = null;
        }
        recyclerView5.setDrawingCacheEnabled(true);
        GridLayoutUtils gridLayoutUtils = new GridLayoutUtils(this.context, this.styleSheet);
        RecyclerView recyclerView6 = this.mainView;
        if (recyclerView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView2 = null;
        } else {
            recyclerView2 = recyclerView6;
        }
        GridLayoutUtils.setLayoutManager$default(gridLayoutUtils, recyclerView2, tileType, null, false, false, fullSpanList, null, 92, null);
        RecyclerView recyclerView7 = this.mainView;
        if (recyclerView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView7 = null;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView7.getLayoutManager();
        if ((layoutManager instanceof GridLayoutManager) && recyclerViewContainer != null) {
            setMarginsForGrid(recyclerViewContainer);
        }
        if (layoutManager instanceof LinearLayoutManager) {
            RecyclerView recyclerView8 = this.mainView;
            if (recyclerView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainView");
                recyclerView8 = null;
            }
            recyclerView8.addOnScrollListener(new EndlessScrollListener(this) { // from class: com.amazon.mp3.brush.BrushViews.createRootView.1
                final /* synthetic */ BrushViews this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this.$layoutManager, 0, 2, null);
                    this.this$0 = this;
                }

                @Override // com.amazon.music.views.library.recyclerview.EndlessScrollListener
                public void onLoadMore(int page, int totalItemsCount) {
                    EndlessScrollDataProvider endlessScrollDataProvider = this.this$0.brushEndlessScrollDataProvider;
                    if (endlessScrollDataProvider == null) {
                        return;
                    }
                    endlessScrollDataProvider.loadMore(page, totalItemsCount);
                }
            });
        }
        RecyclerView recyclerView9 = this.mainView;
        if (recyclerView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
        } else {
            recyclerView4 = recyclerView9;
        }
        recyclerView4.setAdapter(this.adapterProvider.getAdapter(baseViewModels, (UniversalBinder[]) Arrays.copyOf(binders, binders.length)));
    }

    public final void createHeaderView(UserSubscription userSubscription, ExposedRefinementListModel listModel, int tileType, RecyclerView headerRecyclerView) {
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(listModel, "listModel");
        Intrinsics.checkNotNullParameter(headerRecyclerView, "headerRecyclerView");
        List mutableList = ArraysKt___ArraysKt.toMutableList(getBinders(userSubscription));
        CollectionsKt__MutableCollectionsKt.removeAll(mutableList, (Function1) new Function1<UniversalBinder<? extends ViewGroup, ? extends BaseViewModel>, Boolean>() { // from class: com.amazon.mp3.brush.BrushViews.createHeaderView.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(UniversalBinder<? extends ViewGroup, ? extends BaseViewModel> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(it instanceof ExposedRefinementListBinder);
            }
        });
        RefinementClickListenerProvider refinementClickListenerProvider = this.refinementClickListenerProvider;
        ExposedRefinementListBinder exposedRefinementListBinder = refinementClickListenerProvider == null ? null : new ExposedRefinementListBinder(this.styleSheet, refinementClickListenerProvider, this.headerAdapterProvider, this.contentViewedListener);
        if (exposedRefinementListBinder != null) {
            mutableList.add(exposedRefinementListBinder);
        }
        setHeaderView(headerRecyclerView);
        getHeaderView().setItemViewCacheSize(20);
        getHeaderView().setDrawingCacheEnabled(true);
        GridLayoutUtils.setLayoutManager$default(new GridLayoutUtils(this.context, this.styleSheet), getHeaderView(), tileType, null, false, false, null, null, 124, null);
        RecyclerView headerView = getHeaderView();
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.headerAdapterProvider;
        List<? extends BaseViewModel> listListOf = CollectionsKt__CollectionsJVMKt.listOf(listModel);
        Object[] array = mutableList.toArray(new UniversalBinder[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
        headerView.setAdapter(baseViewModelAdapterProvider.getAdapter(listListOf, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length)));
    }

    public final UniversalBinder<? extends ViewGroup, ? extends BaseViewModel>[] getBinders(UserSubscription userSubscription) {
        SearchResponseMessageBinder searchResponseMessageBinder;
        VerticalTileBinder verticalTileBinder;
        FollowTileBinder followTileBinder;
        UniversalBinder featurePlayBinder;
        UniversalBinder podcastFeaturePlayBinder;
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        HorizontalTileBinder horizontalTileBinder = new HorizontalTileBinder(this.styleSheet, userSubscription, this.contentEligibilityProvider, this.artworkFrameBinder, this.hotspotBinder, this.rowButtonOnClickProvider, this.contextMenuProvider, this.deeplinkClickListenerFactory, this.contentEnabilityProvider, this.itemOrdinalProvider, this.trackDownloadButtonProvider);
        SimpleHorizontalTileBinder simpleHorizontalTileBinder = new SimpleHorizontalTileBinder(this.styleSheet, userSubscription, this.contentEligibilityProvider, this.artworkFrameBinder, this.rowButtonOnClickProvider, this.contentEnabilityProvider, this.deeplinkClickListenerFactory, this.itemClickListener);
        CastingListItemHorizontalTileBinder castingListItemHorizontalTileBinder = new CastingListItemHorizontalTileBinder(this.styleSheet, this.artworkFrameBinder, this.deeplinkClickListenerFactory, this.contextMenuProvider, this.castingTileListenerProvider);
        VerticalTileBinder verticalTileBinder2 = new VerticalTileBinder(this.styleSheet, userSubscription, this.artworkFrameBinder, this.contextMenuProvider, this.deeplinkClickListenerFactory, this.contentEnabilityProvider, this.imageBinder);
        SimpleVerticalTileBinder simpleVerticalTileBinder = new SimpleVerticalTileBinder(this.styleSheet, userSubscription, this.artworkFrameBinder, this.deeplinkClickListenerFactory, this.contentEnabilityProvider);
        StyleSheet styleSheet = this.styleSheet;
        ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
        MetadataContextMenuProvider metadataContextMenuProvider = this.contextMenuProvider;
        DeeplinkClickListenerFactory deeplinkClickListenerFactory = this.deeplinkClickListenerFactory;
        ContentEnabilityProvider contentEnabilityProvider = this.contentEnabilityProvider;
        ImageBinder imageBinder = this.imageBinder;
        VideoStoryPlaybackProvider videoStoryPlaybackProvider = VideoStoryPlaybackProvider.INSTANCE;
        VideoStoryTileBinder videoStoryTileBinder = new VideoStoryTileBinder(styleSheet, userSubscription, artworkFrameBinder, metadataContextMenuProvider, deeplinkClickListenerFactory, contentEnabilityProvider, imageBinder, videoStoryPlaybackProvider.getVideoStoryAutoPlayCoordinator());
        FollowTileBinder followTileBinder2 = new FollowTileBinder(this.styleSheet, userSubscription, this.artworkFrameBinder, this.contextMenuProvider, this.deeplinkClickListenerFactory, this.contentEnabilityProvider, this.imageBinder, this.followProvider, this.artistClickHandler, AmazonApplication.getCapabilities().isArtistFollowSupported());
        VerticalTileBinder verticalTileBinder3 = new VerticalTileBinder(this.styleSheet, userSubscription, this.artworkFrameBinder, this.contextMenuProvider, new ExploreClickListenerFactory(), this.contentEnabilityProvider, this.imageBinder);
        ExploreHorizontalItemBinder exploreHorizontalItemBinder = new ExploreHorizontalItemBinder(this.styleSheet, userSubscription, this.artworkFrameBinder, this.rowButtonOnClickProvider, this.contextMenuProvider, new ExploreClickListenerFactory(), this.contentEnabilityProvider);
        LinkNavigatorBinder linkNavigatorBinder = new LinkNavigatorBinder(this.styleSheet, this.brushUriClickListenerProvider);
        ButtonNavigatorBinder buttonNavigatorBinder = new ButtonNavigatorBinder(this.styleSheet, this.brushUriClickListenerProvider);
        CarModeButtonNavigatorBinder carModeButtonNavigatorBinder = new CarModeButtonNavigatorBinder(this.styleSheet, this.seeMoreProvider);
        MessageViewBinder messageViewBinder = new MessageViewBinder(this.styleSheet);
        SearchResponseMessageBinder searchResponseMessageBinder2 = new SearchResponseMessageBinder(this.styleSheet, this.spellCorrectionListenerClickProvider);
        PillCellBinder pillCellBinder = new PillCellBinder(this.styleSheet, this.brushUriClickListenerProvider);
        ThumbnailCellBinder thumbnailCellBinder = new ThumbnailCellBinder(this.styleSheet, this.imageLoader, this.brushUriClickListenerProvider);
        FeatureBarkerBinder featureBarkerBinder = new FeatureBarkerBinder(this.styleSheet, this.imageLoader, this.artworkFrameBinder, this.brushUriClickListenerProvider, true, false, 32, null);
        boolean zIsEnabled = Feature.feature_play_v2_browse_home.isEnabled();
        if (zIsEnabled) {
            StyleSheet styleSheet2 = this.styleSheet;
            FeaturedPlayMusicProvider featuredPlayMusicProvider = this.featuredPlayMusicProvider;
            Intrinsics.checkNotNull(featuredPlayMusicProvider);
            verticalTileBinder = verticalTileBinder3;
            searchResponseMessageBinder = searchResponseMessageBinder2;
            featurePlayBinder = new FeaturePlayV2Binder(styleSheet2, featuredPlayMusicProvider, this.deeplinkClickListenerFactory, this.contentViewedListener, AmazonApplication.getCapabilities().isSonicRushEnabled());
            followTileBinder = followTileBinder2;
        } else {
            searchResponseMessageBinder = searchResponseMessageBinder2;
            verticalTileBinder = verticalTileBinder3;
            followTileBinder = followTileBinder2;
            featurePlayBinder = new FeaturePlayBinder(this.styleSheet, this.imageLoader, this.featuredPlayMusicProvider, this.deeplinkClickListenerFactory, this.contentViewedListener, AmazonApplication.getCapabilities().isSonicRushEnabled());
        }
        if (zIsEnabled) {
            StyleSheet styleSheet3 = this.styleSheet;
            FeaturedPlayMusicProvider featuredPlayMusicProvider2 = this.featuredPlayMusicProvider;
            Intrinsics.checkNotNull(featuredPlayMusicProvider2);
            podcastFeaturePlayBinder = new FeaturePlayV2PodcastBinder(styleSheet3, featuredPlayMusicProvider2, this.deeplinkClickListenerFactory, this.contentViewedListener, AmazonApplication.getCapabilities().isSonicRushEnabled());
        } else {
            StyleSheet styleSheet4 = this.styleSheet;
            FeaturedPlayMusicProvider featuredPlayMusicProvider3 = this.featuredPlayMusicProvider;
            Intrinsics.checkNotNull(featuredPlayMusicProvider3);
            podcastFeaturePlayBinder = new PodcastFeaturePlayBinder(styleSheet4, featuredPlayMusicProvider3, this.deeplinkClickListenerFactory, this.contentViewedListener, AmazonApplication.getCapabilities().isSonicRushEnabled());
        }
        UniversalBinder universalBinder = podcastFeaturePlayBinder;
        LivestreamBarkerBinder livestreamBarkerBinder = new LivestreamBarkerBinder(this.styleSheet, this.imageLoader, this.deeplinkClickListenerFactory, true);
        ExploreLiveStreamBarkerBinder exploreLiveStreamBarkerBinder = new ExploreLiveStreamBarkerBinder(this.styleSheet, this.imageLoader, new ExploreClickListenerFactory(), true);
        CarouselBinder carouselBinder = new CarouselBinder(this.styleSheet, new FeatureBarkerBinder(this.styleSheet, this.imageLoader, this.artworkFrameBinder, this.brushUriClickListenerProvider, false, false, 32, null), this.contentViewedListener);
        PodcastTileBinder podcastTileBinder = new PodcastTileBinder(this.styleSheet, this.lifecycleOwner, this.deeplinkClickListenerFactory);
        PodcastEpisodeDescriptiveShovelerBinder podcastEpisodeDescriptiveShovelerBinder = new PodcastEpisodeDescriptiveShovelerBinder(this.seeMoreProvider, this.contentViewedListener);
        EpisodeRowItemBinder episodeRowItemBinder = new EpisodeRowItemBinder();
        PodcastHorizontalRowItemBinder podcastHorizontalRowItemBinder = new PodcastHorizontalRowItemBinder(this.itemOrdinalProvider, this.entityTrackingIntentServiceProvider);
        PodcastEpisodeHorizontalRowItemBinder podcastEpisodeHorizontalRowItemBinder = new PodcastEpisodeHorizontalRowItemBinder(this.itemOrdinalProvider, this.entityTrackingIntentServiceProvider);
        PodcastVisualShovelerCompactBinder podcastVisualShovelerCompactBinder = new PodcastVisualShovelerCompactBinder(this.seeMoreProvider, this.contentViewedListener, this.lifecycleOwner);
        VerticalItemBinder verticalItemBinder = new VerticalItemBinder();
        VideoStoryShovelerBinder videoStoryShovelerBinder = new VideoStoryShovelerBinder(this.context, this.styleSheet, this.seeMoreProvider, this.adapterProvider, this.contentViewedListener, VideoStoryPlaybackDataRepository.INSTANCE.get(), videoStoryPlaybackProvider.getVideoStoryAutoPlayCoordinator(), videoStoryTileBinder);
        ShovelerBinder shovelerBinder = new ShovelerBinder(this.context, this.styleSheet, this.seeMoreProvider, this.adapterProvider, this.contentViewedListener, this.artworkFrameBinder, this.hotspotBinder, this.deeplinkClickListenerFactory, this.imageLoader, verticalTileBinder2, simpleVerticalTileBinder, horizontalTileBinder, simpleHorizontalTileBinder, castingListItemHorizontalTileBinder, messageViewBinder, pillCellBinder, thumbnailCellBinder, linkNavigatorBinder, buttonNavigatorBinder, carModeButtonNavigatorBinder, featureBarkerBinder, livestreamBarkerBinder, featurePlayBinder, universalBinder, followTileBinder, exploreHorizontalItemBinder);
        SimpleShovelerBinder simpleShovelerBinder = new SimpleShovelerBinder(this.context, this.styleSheet, this.seeMoreProvider, this.adapterProvider, this.contentViewedListener, verticalTileBinder2, simpleVerticalTileBinder, horizontalTileBinder, simpleHorizontalTileBinder, castingListItemHorizontalTileBinder, messageViewBinder, pillCellBinder, thumbnailCellBinder, linkNavigatorBinder, buttonNavigatorBinder, carModeButtonNavigatorBinder, featureBarkerBinder, livestreamBarkerBinder, featurePlayBinder, universalBinder, followTileBinder, exploreHorizontalItemBinder);
        Context context = this.context;
        StyleSheet styleSheet5 = this.styleSheet;
        SeeMoreProvider seeMoreProvider = this.seeMoreProvider;
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
        ContentViewedListener contentViewedListener = this.contentViewedListener;
        StateChangeProvider stateChangeProvider = this.stateChangeProvider;
        ArtworkFrameBinder artworkFrameBinder2 = this.artworkFrameBinder;
        DeeplinkClickListenerFactory deeplinkClickListenerFactory2 = this.deeplinkClickListenerFactory;
        ImageLoader imageLoader = this.imageLoader;
        SectionBinder sectionBinder = new SectionBinder(context, styleSheet5, seeMoreProvider, stateChangeProvider, baseViewModelAdapterProvider, contentViewedListener, artworkFrameBinder2, deeplinkClickListenerFactory2, imageLoader, verticalTileBinder2, simpleVerticalTileBinder, horizontalTileBinder, simpleHorizontalTileBinder, castingListItemHorizontalTileBinder, pillCellBinder, thumbnailCellBinder, messageViewBinder, linkNavigatorBinder, buttonNavigatorBinder, carModeButtonNavigatorBinder, featureBarkerBinder, featurePlayBinder, universalBinder, livestreamBarkerBinder, exploreLiveStreamBarkerBinder, shovelerBinder, simpleShovelerBinder, searchResponseMessageBinder, getPlaybackContainerBinder(horizontalTileBinder, imageLoader), podcastHorizontalRowItemBinder, podcastEpisodeHorizontalRowItemBinder);
        ExploreVisualShovelerBinder exploreVisualShovelerBinder = new ExploreVisualShovelerBinder(this.context, this.styleSheet, this.adapterProvider, verticalTileBinder);
        ExploreFeaturedShovelerBinder exploreFeaturedShovelerBinder = new ExploreFeaturedShovelerBinder(this.context, this.styleSheet, this.adapterProvider, horizontalTileBinder, exploreHorizontalItemBinder);
        ExploreSpotlightCarouselBinder exploreSpotlightCarouselBinder = new ExploreSpotlightCarouselBinder(this.context, this.styleSheet);
        boolean zIsEnabled2 = Feature.social_profiles_phase3_enabled.isEnabled();
        CustomerProfileBinder customerProfileBinder = new CustomerProfileBinder(this.styleSheet, this.imageBinder, this.shareProfileProvider, this.customerProfileClickListener, zIsEnabled2, zIsEnabled2);
        HeaderBinder headerBinder = new HeaderBinder(this.context, this.styleSheet, new ButtonBinder(this.context, this.styleSheet, this.brushUriClickListenerProvider, this.contextMenuProvider, this.playbackProvider, this.shareProvider, this.followProvider), this.lifecycleOwner);
        MetadataContextMenuProvider metadataContextMenuProvider2 = this.contextMenuProvider;
        ContentEnabilityProvider contentEnabilityProvider2 = this.contentEnabilityProvider;
        Intrinsics.checkNotNull(contentEnabilityProvider2);
        ArtistDetailHeaderBinder artistDetailHeaderBinder = new ArtistDetailHeaderBinder(this.context, this.styleSheet, this.artistClickHandler, null, metadataContextMenuProvider2, contentEnabilityProvider2, UserSubscriptionUtil.isNightwingOnly(), AmazonApplication.getCapabilities().isArtistFollowSupported(), false, false, false, 1800, null);
        Context context2 = this.context;
        StyleSheet styleSheet6 = this.styleSheet;
        GenreClickHandler genreClickHandler = this.genreClickHandler;
        ContentEnabilityProvider contentEnabilityProvider3 = this.contentEnabilityProvider;
        Intrinsics.checkNotNull(contentEnabilityProvider3);
        GenreDetailHeaderBinder genreDetailHeaderBinder = new GenreDetailHeaderBinder(context2, styleSheet6, genreClickHandler, contentEnabilityProvider3);
        BaseDetailHeaderBinder baseDetailHeaderBinder = new BaseDetailHeaderBinder(this.styleSheet);
        VisualRowItemBinder visualRowItemBinder = new VisualRowItemBinder(this.context, this.styleSheet, this.artworkFrameBinder, null, null, null, null, null, userSubscription, null, null, 1784, null);
        PodcastShovelerBinder podcastShovelerBinder = new PodcastShovelerBinder(this.context, this.styleSheet, this.seeMoreProvider, this.adapterProvider, this.contentViewedListener, podcastTileBinder);
        RefinementClickListenerProvider refinementClickListenerProvider = this.refinementClickListenerProvider;
        ExposedRefinementListBinder exposedRefinementListBinder = refinementClickListenerProvider == null ? null : new ExposedRefinementListBinder(this.styleSheet, refinementClickListenerProvider, this.adapterProvider, null, 8, null);
        List listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(verticalTileBinder2, simpleVerticalTileBinder, pillCellBinder, thumbnailCellBinder, horizontalTileBinder, simpleHorizontalTileBinder, linkNavigatorBinder, buttonNavigatorBinder, carModeButtonNavigatorBinder, castingListItemHorizontalTileBinder, sectionBinder, carouselBinder, messageViewBinder, searchResponseMessageBinder, featureBarkerBinder, featurePlayBinder, universalBinder, customerProfileBinder, shovelerBinder, simpleShovelerBinder, genreDetailHeaderBinder, baseDetailHeaderBinder, livestreamBarkerBinder, exploreVisualShovelerBinder, exploreFeaturedShovelerBinder, exploreLiveStreamBarkerBinder, exploreSpotlightCarouselBinder, artistDetailHeaderBinder, headerBinder, visualRowItemBinder, podcastShovelerBinder, videoStoryTileBinder, podcastEpisodeDescriptiveShovelerBinder, episodeRowItemBinder, podcastHorizontalRowItemBinder, podcastEpisodeHorizontalRowItemBinder, new GridListHeaderBinder(this.styleSheet), podcastVisualShovelerCompactBinder, verticalItemBinder, videoStoryShovelerBinder);
        if (exposedRefinementListBinder != null) {
            listMutableListOf.add(exposedRefinementListBinder);
        }
        Object[] array = listMutableListOf.toArray(new UniversalBinder[0]);
        if (array != null) {
            return (UniversalBinder[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public final void updateAdapter(List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        RecyclerView recyclerView = this.mainView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
        }
        BaseViewModelAdapterProvider.addItems$default(this.adapterProvider, (UniversalAdapter) adapter, models, null, 4, null);
    }

    public final void removeItemsFromAdapter(List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        RecyclerView recyclerView = this.mainView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
        }
        this.adapterProvider.removeItems((UniversalAdapter) adapter, models);
    }

    public final void updateHeaderAdapter(ExposedRefinementListModel listModel) {
        Intrinsics.checkNotNullParameter(listModel, "listModel");
        if (this.headerView != null) {
            RecyclerView.Adapter adapter = getHeaderView().getAdapter();
            if (adapter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
            }
            BaseViewModelAdapterProvider.addItems$default(this.headerAdapterProvider, (UniversalAdapter) adapter, CollectionsKt__CollectionsJVMKt.listOf(listModel), null, 4, null);
        }
    }

    public final void setComparator(Comparator<? super Object> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        RecyclerView recyclerView = this.mainView;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
        }
        ((UniversalAdapter) adapter).setComparator(comparator);
        RecyclerView recyclerView3 = this.mainView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainView");
        } else {
            recyclerView2 = recyclerView3;
        }
        RecyclerView.Adapter adapter2 = recyclerView2.getAdapter();
        if (adapter2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
        }
        ((UniversalAdapter) adapter2).sortOnAdd(true);
    }

    private final void setMarginsForGrid(ViewGroup viewGroup) {
        GridSize gridSize = this.styleSheet.getGridSize(this.context);
        if (gridSize == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(viewGroup, -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : Integer.valueOf(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2)), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2)), (240 & 128) != 0 ? null : null);
    }

    public final ImageBinder getImageBinder() {
        return this.imageBinder;
    }

    private final SectionBinder getPlaybackContainerBinder(HorizontalTileBinder horizontalTileBinder, ImageLoader imageLoader) {
        return new SectionBinder(this.context, this.styleSheet, this.seeMoreProvider, this.stateChangeProvider, this.adapterProvider, this.contentViewedListener, this.artworkFrameBinder, this.deeplinkClickListenerFactory, imageLoader, horizontalTileBinder);
    }
}
