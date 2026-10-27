package com.amazon.mp3.providers;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
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
import com.amazon.music.views.library.recyclerview.OnItemClickListener;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewsConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bC\u0018\u00002\u00020\u0001BÝ\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u000100\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000102\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u000104\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u000106\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u000108\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010:\u0012\n\b\u0002\u0010;\u001a\u0004\u0018\u00010<\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010@\u0012\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B¢\u0006\u0002\u0010CR\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0013\u0010+\u001a\u0004\u0018\u00010,¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0013\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010KR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010]R\u0013\u0010=\u001a\u0004\u0018\u00010>¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R\u0013\u0010'\u001a\u0004\u0018\u00010(¢\u0006\b\n\u0000\u001a\u0004\b`\u0010aR\u0013\u00101\u001a\u0004\u0018\u000102¢\u0006\b\n\u0000\u001a\u0004\bb\u0010cR\u0013\u0010)\u001a\u0004\u0018\u00010*¢\u0006\b\n\u0000\u001a\u0004\bd\u0010eR\u0011\u0010\u0018\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\bf\u0010ER\u0013\u0010A\u001a\u0004\u0018\u00010B¢\u0006\b\n\u0000\u001a\u0004\bg\u0010hR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bi\u0010jR\u0013\u0010?\u001a\u0004\u0018\u00010@¢\u0006\b\n\u0000\u001a\u0004\bk\u0010lR\u0013\u00109\u001a\u0004\u0018\u00010:¢\u0006\b\n\u0000\u001a\u0004\bm\u0010nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bo\u0010pR\u0013\u0010-\u001a\u0004\u0018\u00010.¢\u0006\b\n\u0000\u001a\u0004\bq\u0010rR\u0013\u00107\u001a\u0004\u0018\u000108¢\u0006\b\n\u0000\u001a\u0004\bs\u0010tR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\bu\u0010vR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\b\n\u0000\u001a\u0004\bw\u0010xR\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\by\u0010zR\u0013\u0010/\u001a\u0004\u0018\u000100¢\u0006\b\n\u0000\u001a\u0004\b{\u0010|R\u0013\u00103\u001a\u0004\u0018\u000104¢\u0006\b\n\u0000\u001a\u0004\b}\u0010~R\u0014\u00105\u001a\u0004\u0018\u000106¢\u0006\t\n\u0000\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0000\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0015\u0010;\u001a\u0004\u0018\u00010<¢\u0006\n\n\u0000\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001¨\u0006\u0085\u0001"}, d2 = {"Lcom/amazon/mp3/providers/ViewsConfiguration;", "", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "deepLinksManager", "Lcom/amazon/music/deeplink/DeeplinksManager;", "contentMetadataOnClickListener", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "headerAdapterProvider", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "brushEndlessScrollDataProvider", "Lcom/amazon/music/views/library/providers/EndlessScrollDataProvider;", "artworkProvider", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "shareProfileProvider", "Lcom/amazon/music/views/library/providers/ShareProfileProvider;", "customerProfileClickListener", "Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "featuredPlayMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "genreClickHandler", "Lcom/amazon/mp3/providers/GenreClickHandler;", "artistClickHandler", "Lcom/amazon/mp3/providers/ArtistClickHandler;", "playbackProvider", "Lcom/amazon/music/views/library/providers/PlaybackProvider;", "shareProvider", "Lcom/amazon/music/views/library/providers/ShareProvider;", "followProvider", "Lcom/amazon/music/views/library/providers/FollowProvider;", "spellCorrectionListenerClickProvider", "Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;", "stateChangeProvider", "Lcom/amazon/music/views/library/providers/StateChangeProvider;", "refinementClickListenerProvider", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "itemOrdinalProvider", "Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "entityTrackingIntentServiceProvider", "Lcom/amazon/music/views/library/providers/EntityTrackingIntentServiceProvider;", "itemClickListener", "Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;", "hotspotTooltipListenerProvider", "Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/imageloader/ImageLoader;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/deeplink/DeeplinksManager;Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;Lcom/amazon/music/views/library/providers/EndlessScrollDataProvider;Lcom/amazon/music/views/library/providers/CachedArtworkProvider;Lcom/amazon/music/views/library/providers/ShareProfileProvider;Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;Lcom/amazon/mp3/providers/GenreClickHandler;Lcom/amazon/mp3/providers/ArtistClickHandler;Lcom/amazon/music/views/library/providers/PlaybackProvider;Lcom/amazon/music/views/library/providers/ShareProvider;Lcom/amazon/music/views/library/providers/FollowProvider;Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;Lcom/amazon/music/views/library/providers/StateChangeProvider;Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;Lcom/amazon/music/views/library/providers/EntityTrackingIntentServiceProvider;Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;)V", "getAdapterProvider", "()Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getArtistClickHandler", "()Lcom/amazon/mp3/providers/ArtistClickHandler;", "getArtworkProvider", "()Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "getBrushEndlessScrollDataProvider", "()Lcom/amazon/music/views/library/providers/EndlessScrollDataProvider;", "getBrushUriClickListenerProvider", "()Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "getContentEligibilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "getContentEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentMetadataOnClickListener", "()Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "getContext", "()Landroid/content/Context;", "getContextMenuProvider", "()Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "getCustomerProfileClickListener", "()Lcom/amazon/music/views/library/providers/CustomerProfileClickListener;", "getDeepLinksManager", "()Lcom/amazon/music/deeplink/DeeplinksManager;", "getEntityTrackingIntentServiceProvider", "()Lcom/amazon/music/views/library/providers/EntityTrackingIntentServiceProvider;", "getFeaturedPlayMusicProvider", "()Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "getFollowProvider", "()Lcom/amazon/music/views/library/providers/FollowProvider;", "getGenreClickHandler", "()Lcom/amazon/mp3/providers/GenreClickHandler;", "getHeaderAdapterProvider", "getHotspotTooltipListenerProvider", "()Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "getImageLoader", "()Lcom/amazon/music/imageloader/ImageLoader;", "getItemClickListener", "()Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;", "getItemOrdinalProvider", "()Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getPlaybackProvider", "()Lcom/amazon/music/views/library/providers/PlaybackProvider;", "getRefinementClickListenerProvider", "()Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "getRowButtonOnClickProvider", "()Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getSeeMoreProvider", "()Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "getShareProfileProvider", "()Lcom/amazon/music/views/library/providers/ShareProfileProvider;", "getShareProvider", "()Lcom/amazon/music/views/library/providers/ShareProvider;", "getSpellCorrectionListenerClickProvider", "()Lcom/amazon/music/find/providers/SpellCorrectionListenerClickProvider;", "getStateChangeProvider", "()Lcom/amazon/music/views/library/providers/StateChangeProvider;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "getTrackDownloadButtonProvider", "()Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ViewsConfiguration {
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ArtistClickHandler artistClickHandler;
    private final CachedArtworkProvider artworkProvider;
    private final EndlessScrollDataProvider brushEndlessScrollDataProvider;
    private final BrushUriClickListenerProvider brushUriClickListenerProvider;
    private final ContentEligibilityProvider contentEligibilityProvider;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final ContentMetadataOnClickListener contentMetadataOnClickListener;
    private final ContentViewedListener contentViewedListener;
    private final Context context;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final CustomerProfileClickListener customerProfileClickListener;
    private final DeeplinksManager deepLinksManager;
    private final EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider;
    private final FeaturedPlayMusicProvider featuredPlayMusicProvider;
    private final FollowProvider followProvider;
    private final GenreClickHandler genreClickHandler;
    private final BaseViewModelAdapterProvider headerAdapterProvider;
    private final HotspotTooltipListenerProvider hotspotTooltipListenerProvider;
    private final ImageLoader imageLoader;
    private final OnItemClickListener itemClickListener;
    private final ItemOrdinalProvider itemOrdinalProvider;
    private final LifecycleOwner lifecycleOwner;
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

    public ViewsConfiguration(Context context, LifecycleOwner lifecycleOwner, StyleSheet styleSheet, ImageLoader imageLoader, ContentEligibilityProvider contentEligibilityProvider, RowButtonOnClickProvider rowButtonOnClickProvider, MetadataContextMenuProvider metadataContextMenuProvider, DeeplinksManager deepLinksManager, ContentMetadataOnClickListener contentMetadataOnClickListener, ContentEnabilityProvider contentEnabilityProvider, BaseViewModelAdapterProvider adapterProvider, BaseViewModelAdapterProvider headerAdapterProvider, SeeMoreProvider seeMoreProvider, BrushUriClickListenerProvider brushUriClickListenerProvider, EndlessScrollDataProvider endlessScrollDataProvider, CachedArtworkProvider cachedArtworkProvider, ShareProfileProvider shareProfileProvider, CustomerProfileClickListener customerProfileClickListener, ContentViewedListener contentViewedListener, FeaturedPlayMusicProvider featuredPlayMusicProvider, GenreClickHandler genreClickHandler, ArtistClickHandler artistClickHandler, PlaybackProvider playbackProvider, ShareProvider shareProvider, FollowProvider followProvider, SpellCorrectionListenerClickProvider spellCorrectionListenerClickProvider, StateChangeProvider stateChangeProvider, RefinementClickListenerProvider refinementClickListenerProvider, ItemOrdinalProvider itemOrdinalProvider, TrackDownloadButtonProvider trackDownloadButtonProvider, EntityTrackingIntentServiceProvider entityTrackingIntentServiceProvider, OnItemClickListener onItemClickListener, HotspotTooltipListenerProvider hotspotTooltipListenerProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(imageLoader, "imageLoader");
        Intrinsics.checkNotNullParameter(deepLinksManager, "deepLinksManager");
        Intrinsics.checkNotNullParameter(contentMetadataOnClickListener, "contentMetadataOnClickListener");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(headerAdapterProvider, "headerAdapterProvider");
        Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
        Intrinsics.checkNotNullParameter(shareProfileProvider, "shareProfileProvider");
        Intrinsics.checkNotNullParameter(customerProfileClickListener, "customerProfileClickListener");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.styleSheet = styleSheet;
        this.imageLoader = imageLoader;
        this.contentEligibilityProvider = contentEligibilityProvider;
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.deepLinksManager = deepLinksManager;
        this.contentMetadataOnClickListener = contentMetadataOnClickListener;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.adapterProvider = adapterProvider;
        this.headerAdapterProvider = headerAdapterProvider;
        this.seeMoreProvider = seeMoreProvider;
        this.brushUriClickListenerProvider = brushUriClickListenerProvider;
        this.brushEndlessScrollDataProvider = endlessScrollDataProvider;
        this.artworkProvider = cachedArtworkProvider;
        this.shareProfileProvider = shareProfileProvider;
        this.customerProfileClickListener = customerProfileClickListener;
        this.contentViewedListener = contentViewedListener;
        this.featuredPlayMusicProvider = featuredPlayMusicProvider;
        this.genreClickHandler = genreClickHandler;
        this.artistClickHandler = artistClickHandler;
        this.playbackProvider = playbackProvider;
        this.shareProvider = shareProvider;
        this.followProvider = followProvider;
        this.spellCorrectionListenerClickProvider = spellCorrectionListenerClickProvider;
        this.stateChangeProvider = stateChangeProvider;
        this.refinementClickListenerProvider = refinementClickListenerProvider;
        this.itemOrdinalProvider = itemOrdinalProvider;
        this.trackDownloadButtonProvider = trackDownloadButtonProvider;
        this.entityTrackingIntentServiceProvider = entityTrackingIntentServiceProvider;
        this.itemClickListener = onItemClickListener;
        this.hotspotTooltipListenerProvider = hotspotTooltipListenerProvider;
    }

    public final Context getContext() {
        return this.context;
    }

    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ImageLoader getImageLoader() {
        return this.imageLoader;
    }

    public final ContentEligibilityProvider getContentEligibilityProvider() {
        return this.contentEligibilityProvider;
    }

    public final RowButtonOnClickProvider getRowButtonOnClickProvider() {
        return this.rowButtonOnClickProvider;
    }

    public final MetadataContextMenuProvider getContextMenuProvider() {
        return this.contextMenuProvider;
    }

    public final DeeplinksManager getDeepLinksManager() {
        return this.deepLinksManager;
    }

    public final ContentMetadataOnClickListener getContentMetadataOnClickListener() {
        return this.contentMetadataOnClickListener;
    }

    public final ContentEnabilityProvider getContentEnabilityProvider() {
        return this.contentEnabilityProvider;
    }

    public final BaseViewModelAdapterProvider getAdapterProvider() {
        return this.adapterProvider;
    }

    public final BaseViewModelAdapterProvider getHeaderAdapterProvider() {
        return this.headerAdapterProvider;
    }

    public final SeeMoreProvider getSeeMoreProvider() {
        return this.seeMoreProvider;
    }

    public final BrushUriClickListenerProvider getBrushUriClickListenerProvider() {
        return this.brushUriClickListenerProvider;
    }

    public final EndlessScrollDataProvider getBrushEndlessScrollDataProvider() {
        return this.brushEndlessScrollDataProvider;
    }

    public final CachedArtworkProvider getArtworkProvider() {
        return this.artworkProvider;
    }

    public final ShareProfileProvider getShareProfileProvider() {
        return this.shareProfileProvider;
    }

    public final CustomerProfileClickListener getCustomerProfileClickListener() {
        return this.customerProfileClickListener;
    }

    public final ContentViewedListener getContentViewedListener() {
        return this.contentViewedListener;
    }

    public final FeaturedPlayMusicProvider getFeaturedPlayMusicProvider() {
        return this.featuredPlayMusicProvider;
    }

    public final GenreClickHandler getGenreClickHandler() {
        return this.genreClickHandler;
    }

    public final ArtistClickHandler getArtistClickHandler() {
        return this.artistClickHandler;
    }

    public final PlaybackProvider getPlaybackProvider() {
        return this.playbackProvider;
    }

    public final ShareProvider getShareProvider() {
        return this.shareProvider;
    }

    public final FollowProvider getFollowProvider() {
        return this.followProvider;
    }

    public final SpellCorrectionListenerClickProvider getSpellCorrectionListenerClickProvider() {
        return this.spellCorrectionListenerClickProvider;
    }

    public final StateChangeProvider getStateChangeProvider() {
        return this.stateChangeProvider;
    }

    public final RefinementClickListenerProvider getRefinementClickListenerProvider() {
        return this.refinementClickListenerProvider;
    }

    public final ItemOrdinalProvider getItemOrdinalProvider() {
        return this.itemOrdinalProvider;
    }

    public final TrackDownloadButtonProvider getTrackDownloadButtonProvider() {
        return this.trackDownloadButtonProvider;
    }

    public final EntityTrackingIntentServiceProvider getEntityTrackingIntentServiceProvider() {
        return this.entityTrackingIntentServiceProvider;
    }

    public final OnItemClickListener getItemClickListener() {
        return this.itemClickListener;
    }

    public final HotspotTooltipListenerProvider getHotspotTooltipListenerProvider() {
        return this.hotspotTooltipListenerProvider;
    }
}
