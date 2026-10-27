package com.amazon.mp3.catalog.fragment.adapter;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.catalog.fragment.binder.PlayQueueItemBinder;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListenerFactory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.UserPlaylistDetailProfileLinkHandler;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.ArtistDetailHeaderBinder;
import com.amazon.music.views.library.binders.ArtworkFrameBinder;
import com.amazon.music.views.library.binders.ButtonNavigatorBinder;
import com.amazon.music.views.library.binders.CollectionDetailHeaderBinder;
import com.amazon.music.views.library.binders.DefaultPageHeaderBinder;
import com.amazon.music.views.library.binders.DescriptiveRowItemBinder;
import com.amazon.music.views.library.binders.DescriptiveTextBinder;
import com.amazon.music.views.library.binders.EmptyStateBinder;
import com.amazon.music.views.library.binders.ExposedRefinementListBinder;
import com.amazon.music.views.library.binders.FeatureBarkerBinder;
import com.amazon.music.views.library.binders.FeaturedToggleViewBinder;
import com.amazon.music.views.library.binders.HighlightableMessageBinder;
import com.amazon.music.views.library.binders.HorizontalTileBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.binders.MultiStateButtonBinder;
import com.amazon.music.views.library.binders.SectionBinder;
import com.amazon.music.views.library.binders.ShovelerBinder;
import com.amazon.music.views.library.binders.StoryPageHeaderBinder;
import com.amazon.music.views.library.binders.TextRowItemBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.binders.VisualRowItemBinder;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.deeplinks.DeeplinkClickListenerFactory;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ArtistClickListenerProvider;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.ButtonActionProvider;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.EmptyStateActionProvider;
import com.amazon.music.views.library.providers.FeaturedToggleListenerProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.MultiStateButtonClickListenerProvider;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SDCardMissingProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.views.BaseButton;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseViewsBinderProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001eB\t\b\u0002¢\u0006\u0004\bc\u0010dJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002J\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eJ-\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0016\u0010\u0017J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0003\u001a\u00020\u0002J-\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u001f\u0010 Ji\u0010-\u001a\u00020,2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%22\u0010+\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020)\u0012\u0006\b\u0001\u0012\u00020*0(0'\"\u0012\u0012\u0006\b\u0001\u0012\u00020)\u0012\u0006\b\u0001\u0012\u00020*0(¢\u0006\u0004\b-\u0010.J]\u00100\u001a\u00020/2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#22\u0010+\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020)\u0012\u0006\b\u0001\u0012\u00020*0(0'\"\u0012\u0012\u0006\b\u0001\u0012\u00020)\u0012\u0006\b\u0001\u0012\u00020*0(¢\u0006\u0004\b0\u00101J0\u00109\u001a\u0002082\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u00105\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000204022\n\b\u0002\u00107\u001a\u0004\u0018\u000106J0\u0010=\u001a\u00020<2\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u00105\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000204022\n\b\u0002\u0010;\u001a\u0004\u0018\u00010:J?\u0010C\u001a\u00020B2\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u00105\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000204022\b\u0010?\u001a\u0004\u0018\u00010>2\b\u0010A\u001a\u0004\u0018\u00010@¢\u0006\u0004\bC\u0010DJB\u0010L\u001a\u00020K2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010F\u001a\u00020E2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000204022\u0006\u0010H\u001a\u00020\u000e2\u0006\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020\u000eJ\u0016\u0010P\u001a\u00020O2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010N\u001a\u00020MJ\u0016\u0010R\u001a\u00020Q2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010N\u001a\u00020\fJ\u0018\u0010V\u001a\u00020U2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010T\u001a\u0004\u0018\u00010SJ\u0018\u0010Z\u001a\u00020Y2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010X\u001a\u0004\u0018\u00010WJ\u001e\u0010]\u001a\u00020\\2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010N\u001a\u00020[2\u0006\u0010\"\u001a\u00020!R\u0014\u0010^\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0017\u0010`\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b`\u0010_\u001a\u0004\b`\u0010aR\u0017\u0010b\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\bb\u0010_\u001a\u0004\bb\u0010a¨\u0006f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider;", "", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "configuration", "Lcom/amazon/music/views/library/binders/ImageBinder;", "getImageBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "getArtworkFrameBinder", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getClickListenerFactory", "Lcom/amazon/music/views/library/binders/TextRowItemBinder;", "getTextRowItemBinder", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "brushUriClickListenerProvider", "", "hideBackgroundImageShadow", "Lcom/amazon/music/views/library/binders/FeatureBarkerBinder;", "getFeatureBarkerBinder", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerFactory;", "clickListenerFactory", "showTrackDownloadButton", "Lcom/amazon/music/views/library/binders/VisualRowItemBinder;", "getVisualRowItemBinder", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerFactory;Ljava/lang/Boolean;)Lcom/amazon/music/views/library/binders/VisualRowItemBinder;", "Lcom/amazon/mp3/catalog/fragment/binder/PlayQueueItemBinder;", "getPlayQueueItemBinder", "Lcom/amazon/music/views/library/binders/VerticalTileBinder;", "getVerticalTileBinder", "Lcom/amazon/music/views/library/binders/HorizontalTileBinder;", "getHorizontalTileBinder", "Lcom/amazon/music/views/library/binders/DescriptiveRowItemBinder;", "getDescriptiveRowItemBinder", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;Lcom/amazon/music/views/library/click/ClickListenerFactory;Ljava/lang/Boolean;)Lcom/amazon/music/views/library/binders/DescriptiveRowItemBinder;", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/StateChangeProvider;", "stateChangeProvider", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/view/View;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "childrenBinders", "Lcom/amazon/music/views/library/binders/SectionBinder;", "getSectionBinder", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/providers/StateChangeProvider;[Lcom/amazon/music/views/library/binders/UniversalBinder;)Lcom/amazon/music/views/library/binders/SectionBinder;", "Lcom/amazon/music/views/library/binders/ShovelerBinder;", "getShovelerBinder", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/providers/SeeMoreProvider;[Lcom/amazon/music/views/library/binders/UniversalBinder;)Lcom/amazon/music/views/library/binders/ShovelerBinder;", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "onClickListenerMap", "Lcom/amazon/mp3/providers/UserPlaylistDetailProfileLinkHandler;", "playlistProfileLinkProvider", "Lcom/amazon/music/views/library/binders/CollectionDetailHeaderBinder;", "getCollectionDetailHeaderBinder", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/views/library/binders/DefaultPageHeaderBinder;", "getDefaultPageHeaderBinder", "Landroid/view/View$OnTouchListener;", "textContainerOnTouchListener", "", "paginationCount", "Lcom/amazon/music/views/library/binders/StoryPageHeaderBinder;", "getStoryPageHeaderBinder", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;Ljava/util/Map;Landroid/view/View$OnTouchListener;Ljava/lang/Integer;)Lcom/amazon/music/views/library/binders/StoryPageHeaderBinder;", "Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "artistClickListenerProvider", "libraryOnClickListenerMap", "isArtistFollowSupported", "isLibrary", "isAllOwnedContent", "Lcom/amazon/music/views/library/binders/ArtistDetailHeaderBinder;", "getArtistDetailHeaderBinder", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "clickListenerProvider", "Lcom/amazon/music/views/library/binders/ExposedRefinementListBinder;", "getExposedRefinementListBinder", "Lcom/amazon/music/views/library/binders/ButtonNavigatorBinder;", "getButtonNavigatorBinder", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "emptyStateActionProvider", "Lcom/amazon/music/views/library/binders/EmptyStateBinder;", "getEmptyStateBinder", "Lcom/amazon/music/views/library/providers/ButtonActionProvider;", "buttonActionProvider", "Lcom/amazon/music/views/library/binders/HighlightableMessageBinder;", "getHighlightableMessageBinder", "Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;", "Lcom/amazon/music/views/library/binders/FeaturedToggleViewBinder;", "getFeaturedToggleBinder", "isNightwingOnly", "Z", "isSonicRushEnabled", "()Z", "isSonicRushEnabledForPageHeaders", "<init>", "()V", "BinderListBuilder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BaseViewsBinderProvider {
    public static final BaseViewsBinderProvider INSTANCE = new BaseViewsBinderProvider();
    private static final boolean isNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
    private static final boolean isSonicRushEnabled = Feature.sonic_rush.isEnabled();
    private static final boolean isSonicRushEnabledForPageHeaders = Feature.SR_page_headers.isEnabled();

    private BaseViewsBinderProvider() {
    }

    private final ImageBinder getImageBinder(BaseViewsBinderConfiguration configuration) {
        return new ImageBinder(configuration.getContext(), configuration.getStyleSheet(), configuration.getImageLoader());
    }

    private final ArtworkFrameBinder getArtworkFrameBinder(BaseViewsBinderConfiguration configuration) {
        return new ArtworkFrameBinder(configuration.getContext(), configuration.getStyleSheet(), getImageBinder(configuration), configuration.getArtworkProvider(), configuration.getContentEnabilityProvider());
    }

    private final ClickListenerFactory getClickListenerFactory(BaseViewsBinderConfiguration configuration) {
        ContentMetadataOnClickListener metadataOnClickListener = configuration.getMetadataOnClickListener();
        if (metadataOnClickListener == null) {
            return null;
        }
        Context context = configuration.getContext();
        DeeplinksManager deeplinksManager = DeeplinksManager.get(configuration.getContext());
        Intrinsics.checkNotNullExpressionValue(deeplinksManager, "get(configuration.context)");
        return new DeeplinkClickListenerFactory(context, deeplinksManager, metadataOnClickListener);
    }

    public final TextRowItemBinder getTextRowItemBinder(BaseViewsBinderConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new TextRowItemBinder(configuration.getStyleSheet());
    }

    public final FeatureBarkerBinder getFeatureBarkerBinder(BaseViewsBinderConfiguration configuration, BrushUriClickListenerProvider brushUriClickListenerProvider, boolean hideBackgroundImageShadow) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
        return new FeatureBarkerBinder(configuration.getStyleSheet(), configuration.getImageLoader(), getArtworkFrameBinder(configuration), brushUriClickListenerProvider, true, hideBackgroundImageShadow);
    }

    public static /* synthetic */ VisualRowItemBinder getVisualRowItemBinder$default(BaseViewsBinderProvider baseViewsBinderProvider, BaseViewsBinderConfiguration baseViewsBinderConfiguration, CollectionTrackClickListenerFactory collectionTrackClickListenerFactory, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            collectionTrackClickListenerFactory = null;
        }
        if ((i & 4) != 0) {
            bool = Boolean.FALSE;
        }
        return baseViewsBinderProvider.getVisualRowItemBinder(baseViewsBinderConfiguration, collectionTrackClickListenerFactory, bool);
    }

    public final VisualRowItemBinder getVisualRowItemBinder(BaseViewsBinderConfiguration configuration, CollectionTrackClickListenerFactory clickListenerFactory, Boolean showTrackDownloadButton) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Context context = configuration.getContext();
        StyleSheet styleSheet = configuration.getStyleSheet();
        ArtworkFrameBinder artworkFrameBinder = getArtworkFrameBinder(configuration);
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        RowButtonOnClickProvider rowButtonOnClickProvider = configuration.getRowButtonOnClickProvider();
        ClickListenerFactory clickListenerFactory2 = clickListenerFactory == null ? getClickListenerFactory(configuration) : clickListenerFactory;
        SDCardMissingProvider missingSDCardProvider = configuration.getMissingSDCardProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        TrackDownloadButtonProvider trackDownloadButtonProvider = Intrinsics.areEqual(showTrackDownloadButton, Boolean.TRUE) ? configuration.getTrackDownloadButtonProvider() : null;
        ContentEligibilityProvider contentEligibilityProvider = configuration.getContentEligibilityProvider();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        return new VisualRowItemBinder(context, styleSheet, artworkFrameBinder, contextMenuProvider, clickListenerFactory2, contentEnabilityProvider, missingSDCardProvider, trackDownloadButtonProvider, userSubscription, contentEligibilityProvider, rowButtonOnClickProvider);
    }

    public final PlayQueueItemBinder getPlayQueueItemBinder(BaseViewsBinderConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new PlayQueueItemBinder(configuration.getStyleSheet(), getTextRowItemBinder(configuration), getVisualRowItemBinder$default(this, configuration, null, null, 6, null));
    }

    public final VerticalTileBinder getVerticalTileBinder(BaseViewsBinderConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        ClickListenerFactory clickListenerFactory = getClickListenerFactory(configuration);
        if (clickListenerFactory == null) {
            return null;
        }
        StyleSheet styleSheet = configuration.getStyleSheet();
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        BaseViewsBinderProvider baseViewsBinderProvider = INSTANCE;
        return new VerticalTileBinder(styleSheet, userSubscription, baseViewsBinderProvider.getArtworkFrameBinder(configuration), configuration.getContextMenuProvider(), clickListenerFactory, configuration.getContentEnabilityProvider(), baseViewsBinderProvider.getImageBinder(configuration));
    }

    public final HorizontalTileBinder getHorizontalTileBinder(BaseViewsBinderConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        ClickListenerFactory clickListenerFactory = getClickListenerFactory(configuration);
        if (clickListenerFactory == null) {
            return null;
        }
        StyleSheet styleSheet = configuration.getStyleSheet();
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        ArtworkFrameBinder artworkFrameBinder = INSTANCE.getArtworkFrameBinder(configuration);
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        RowButtonOnClickProvider rowButtonOnClickProvider = configuration.getRowButtonOnClickProvider();
        ContentEligibilityProvider contentEligibilityProvider = configuration.getContentEligibilityProvider();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        return new HorizontalTileBinder(styleSheet, userSubscription, contentEligibilityProvider, artworkFrameBinder, null, rowButtonOnClickProvider, contextMenuProvider, clickListenerFactory, contentEnabilityProvider, null, null, 1552, null);
    }

    public final DescriptiveRowItemBinder getDescriptiveRowItemBinder(BaseViewsBinderConfiguration configuration, ClickListenerFactory clickListenerFactory, Boolean showTrackDownloadButton) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Context context = configuration.getContext();
        StyleSheet styleSheet = configuration.getStyleSheet();
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        ClickListenerFactory clickListenerFactory2 = clickListenerFactory == null ? getClickListenerFactory(configuration) : clickListenerFactory;
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        ContentEligibilityProvider contentEligibilityProvider = configuration.getContentEligibilityProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        RowButtonOnClickProvider rowButtonOnClickProvider = configuration.getRowButtonOnClickProvider();
        SDCardMissingProvider missingSDCardProvider = configuration.getMissingSDCardProvider();
        TrackDownloadButtonProvider trackDownloadButtonProvider = Intrinsics.areEqual(showTrackDownloadButton, Boolean.TRUE) ? configuration.getTrackDownloadButtonProvider() : null;
        boolean z = isSonicRushEnabled;
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        return new DescriptiveRowItemBinder(context, styleSheet, contextMenuProvider, clickListenerFactory2, contentEnabilityProvider, missingSDCardProvider, trackDownloadButtonProvider, userSubscription, contentEligibilityProvider, rowButtonOnClickProvider, z);
    }

    public static /* synthetic */ SectionBinder getSectionBinder$default(BaseViewsBinderProvider baseViewsBinderProvider, BaseViewsBinderConfiguration baseViewsBinderConfiguration, ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, StateChangeProvider stateChangeProvider, UniversalBinder[] universalBinderArr, int i, Object obj) {
        return baseViewsBinderProvider.getSectionBinder(baseViewsBinderConfiguration, contentViewedListener, (i & 4) != 0 ? null : seeMoreProvider, (i & 8) != 0 ? null : stateChangeProvider, universalBinderArr);
    }

    public final SectionBinder getSectionBinder(BaseViewsBinderConfiguration configuration, ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, StateChangeProvider stateChangeProvider, UniversalBinder<? extends View, ? extends BaseViewModel>... childrenBinders) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(childrenBinders, "childrenBinders");
        return new SectionBinder(configuration.getContext(), configuration.getStyleSheet(), seeMoreProvider, stateChangeProvider, configuration.getAdapterProvider(), contentViewedListener, getArtworkFrameBinder(configuration), getClickListenerFactory(configuration), null, (UniversalBinder[]) Arrays.copyOf(childrenBinders, childrenBinders.length), 256, null);
    }

    public final ShovelerBinder getShovelerBinder(BaseViewsBinderConfiguration configuration, ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, UniversalBinder<? extends View, ? extends BaseViewModel>... childrenBinders) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(childrenBinders, "childrenBinders");
        return new ShovelerBinder(configuration.getContext(), configuration.getStyleSheet(), seeMoreProvider, configuration.getAdapterProvider(), contentViewedListener, getArtworkFrameBinder(configuration), null, null, null, (UniversalBinder[]) Arrays.copyOf(childrenBinders, childrenBinders.length), 448, null);
    }

    public final CollectionDetailHeaderBinder getCollectionDetailHeaderBinder(BaseViewsBinderConfiguration configuration, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> onClickListenerMap, UserPlaylistDetailProfileLinkHandler playlistProfileLinkProvider) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(onClickListenerMap, "onClickListenerMap");
        Context context = configuration.getContext();
        StyleSheet styleSheet = configuration.getStyleSheet();
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        if (contentEnabilityProvider == null) {
            contentEnabilityProvider = new CatalogEnabilityProvider();
        }
        return new CollectionDetailHeaderBinder(context, styleSheet, onClickListenerMap, contextMenuProvider, contentEnabilityProvider, configuration.getArtworkProvider(), configuration.getImageLoader(), playlistProfileLinkProvider, isSonicRushEnabledForPageHeaders, Feature.supports_download_text_button.isEnabled());
    }

    public final DefaultPageHeaderBinder getDefaultPageHeaderBinder(BaseViewsBinderConfiguration configuration, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> onClickListenerMap, PageType pageType) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(onClickListenerMap, "onClickListenerMap");
        Context context = configuration.getContext();
        StyleSheet styleSheet = configuration.getStyleSheet();
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        if (contentEnabilityProvider == null) {
            contentEnabilityProvider = new CatalogEnabilityProvider();
        }
        return new DefaultPageHeaderBinder(context, styleSheet, onClickListenerMap, contextMenuProvider, contentEnabilityProvider, isSonicRushEnabledForPageHeaders, pageType);
    }

    public final StoryPageHeaderBinder getStoryPageHeaderBinder(BaseViewsBinderConfiguration configuration, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> onClickListenerMap, View.OnTouchListener textContainerOnTouchListener, Integer paginationCount) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(onClickListenerMap, "onClickListenerMap");
        Context context = configuration.getContext();
        StyleSheet styleSheet = configuration.getStyleSheet();
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        if (contentEnabilityProvider == null) {
            contentEnabilityProvider = new CatalogEnabilityProvider();
        }
        return new StoryPageHeaderBinder(context, styleSheet, onClickListenerMap, contextMenuProvider, contentEnabilityProvider, textContainerOnTouchListener, paginationCount);
    }

    public final ArtistDetailHeaderBinder getArtistDetailHeaderBinder(BaseViewsBinderConfiguration configuration, ArtistClickListenerProvider artistClickListenerProvider, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> libraryOnClickListenerMap, boolean isArtistFollowSupported, boolean isLibrary, boolean isAllOwnedContent) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(artistClickListenerProvider, "artistClickListenerProvider");
        Intrinsics.checkNotNullParameter(libraryOnClickListenerMap, "libraryOnClickListenerMap");
        Context context = configuration.getContext();
        StyleSheet styleSheet = configuration.getStyleSheet();
        MetadataContextMenuProvider contextMenuProvider = configuration.getContextMenuProvider();
        ContentEnabilityProvider contentEnabilityProvider = configuration.getContentEnabilityProvider();
        if (contentEnabilityProvider == null) {
            contentEnabilityProvider = new CatalogEnabilityProvider();
        }
        return new ArtistDetailHeaderBinder(context, styleSheet, artistClickListenerProvider, libraryOnClickListenerMap, contextMenuProvider, contentEnabilityProvider, isNightwingOnly, isArtistFollowSupported, isLibrary, isAllOwnedContent, isSonicRushEnabledForPageHeaders);
    }

    public final ExposedRefinementListBinder getExposedRefinementListBinder(BaseViewsBinderConfiguration configuration, RefinementClickListenerProvider clickListenerProvider) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
        return new ExposedRefinementListBinder(configuration.getStyleSheet(), clickListenerProvider, configuration.getAdapterProvider(), null, 8, null);
    }

    public final ButtonNavigatorBinder getButtonNavigatorBinder(BaseViewsBinderConfiguration configuration, BrushUriClickListenerProvider clickListenerProvider) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
        return new ButtonNavigatorBinder(configuration.getStyleSheet(), clickListenerProvider);
    }

    public final EmptyStateBinder getEmptyStateBinder(BaseViewsBinderConfiguration configuration, EmptyStateActionProvider emptyStateActionProvider) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new EmptyStateBinder(configuration.getStyleSheet(), emptyStateActionProvider);
    }

    public final HighlightableMessageBinder getHighlightableMessageBinder(BaseViewsBinderConfiguration configuration, ButtonActionProvider buttonActionProvider) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new HighlightableMessageBinder(configuration.getStyleSheet(), buttonActionProvider);
    }

    public final FeaturedToggleViewBinder getFeaturedToggleBinder(BaseViewsBinderConfiguration configuration, FeaturedToggleListenerProvider clickListenerProvider, ContentViewedListener contentViewedListener) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        return new FeaturedToggleViewBinder(configuration.getStyleSheet(), clickListenerProvider, contentViewedListener);
    }

    /* JADX INFO: compiled from: BaseViewsBinderProvider.kt */
    @Metadata(d1 = {"\u0000\u009a\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002noB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030100J\u0016\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030100H\u0002J:\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u0002052\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000209072\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;J\u000e\u0010>\u001a\u00020\u00002\u0006\u0010?\u001a\u00020@J&\u0010A\u001a\u00020\u00002\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000209072\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DJ&\u0010E\u001a\u00020\u00002\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000209072\n\b\u0002\u0010F\u001a\u0004\u0018\u00010GJ#\u0010H\u001a\u00020\u00002\n\b\u0002\u0010I\u001a\u0004\u0018\u00010J2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010LJ\u0012\u0010M\u001a\u00020\u00002\n\b\u0002\u0010N\u001a\u0004\u0018\u00010OJ\u0018\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020@2\b\b\u0002\u0010R\u001a\u00020;J\u0016\u0010S\u001a\u00020\u00002\u0006\u0010?\u001a\u00020T2\u0006\u0010U\u001a\u00020VJ\u0006\u0010W\u001a\u00020\u0000J\u0012\u0010X\u001a\u00020\u00002\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010ZJ\u0006\u0010[\u001a\u00020\u0000J\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010?\u001a\u00020]J\u000e\u0010^\u001a\u00020\u00002\u0006\u0010?\u001a\u00020_J\u000e\u0010`\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"J\u000e\u0010a\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\u0006\u0010b\u001a\u00020\u0000J3\u0010c\u001a\u00020\u00002\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000209072\b\u0010d\u001a\u0004\u0018\u00010e2\b\u0010f\u001a\u0004\u0018\u00010g¢\u0006\u0002\u0010hJ\u0006\u0010i\u001a\u00020\u0000J\u0010\u0010j\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003J#\u0010k\u001a\u00020\u00002\n\b\u0002\u0010I\u001a\u0004\u0018\u00010l2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010;¢\u0006\u0002\u0010mR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006p"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder;", "", "configuration", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;)V", "artistDetailHeaderBinder", "Lcom/amazon/music/views/library/binders/ArtistDetailHeaderBinder;", "buttonNavigatorBinder", "Lcom/amazon/music/views/library/binders/ButtonNavigatorBinder;", "collectionDetailHeaderBinder", "Lcom/amazon/music/views/library/binders/CollectionDetailHeaderBinder;", "defaultDetailHeaderBinder", "Lcom/amazon/music/views/library/binders/DefaultPageHeaderBinder;", "descriptiveRowItemBinder", "Lcom/amazon/music/views/library/binders/DescriptiveRowItemBinder;", "emptyStateBinder", "Lcom/amazon/music/views/library/binders/EmptyStateBinder;", "featureBarkerBinder", "Lcom/amazon/music/views/library/binders/FeatureBarkerBinder;", "featuredToggleBinder", "Lcom/amazon/music/views/library/binders/FeaturedToggleViewBinder;", "headerRowItemBinder", "Lcom/amazon/music/views/library/binders/TextRowItemBinder;", "highlightableMessageBinder", "Lcom/amazon/music/views/library/binders/HighlightableMessageBinder;", "horizontalTileBinder", "Lcom/amazon/music/views/library/binders/HorizontalTileBinder;", "multiStateButtonBinder", "Lcom/amazon/music/views/library/binders/MultiStateButtonBinder;", "refinementsBinder", "Lcom/amazon/music/views/library/binders/ExposedRefinementListBinder;", "sectionBinder", "Lcom/amazon/music/views/library/binders/SectionBinder;", "sectionBuilder", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder$SectionBuilder;", "shovelerBinder", "Lcom/amazon/music/views/library/binders/ShovelerBinder;", "shovelerBuilder", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder$ShovelerBuilder;", "shuffledWithTextBinder", "Lcom/amazon/music/views/library/binders/DescriptiveTextBinder;", "storyDetailHeaderBinder", "Lcom/amazon/music/views/library/binders/StoryPageHeaderBinder;", "verticalTileBinder", "Lcom/amazon/music/views/library/binders/VerticalTileBinder;", "visualRowItemBinder", "Lcom/amazon/music/views/library/binders/VisualRowItemBinder;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getListOfChildrenBinders", "withArtistDetailHeader", "artistClickListenerProvider", "Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "libraryPageHeaderOnClickListenerMap", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "isArtistFollowSupported", "", "isLibrary", "isAllOwnedContent", "withButtonNavigator", "clickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "withCollectionHeader", "pageHeaderOnClickListenerMap", "playlistProfileLinkHandler", "Lcom/amazon/mp3/providers/UserPlaylistDetailProfileLinkHandler;", "withDefaultHeader", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "withDescriptiveRowItems", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "showTrackDownloadButton", "(Lcom/amazon/music/views/library/click/ClickListenerFactory;Ljava/lang/Boolean;)Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder;", "withEmptyView", "emptyStateActionProvider", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "withFeatureBarker", "brushUriClickListenerProvider", "hideBackgroundImageShadow", "withFeaturedToggle", "Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "withHeaderRowItems", "withHighlightableMessageView", "buttonActionProvider", "Lcom/amazon/music/views/library/providers/ButtonActionProvider;", "withHorizontalTiles", "withMultiStateButton", "Lcom/amazon/music/views/library/providers/MultiStateButtonClickListenerProvider;", "withRefinements", "Lcom/amazon/music/views/library/providers/RefinementClickListenerProvider;", "withSections", "withShovelers", "withShuffledWithText", "withStoryHeader", "textContainerOnTouchListener", "Landroid/view/View$OnTouchListener;", "paginationCount", "", "(Ljava/util/Map;Landroid/view/View$OnTouchListener;Ljava/lang/Integer;)Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder;", "withTextRowItems", "withVerticalTiles", "withVisualRowItems", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerFactory;", "(Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListenerFactory;Ljava/lang/Boolean;)Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder;", "SectionBuilder", "ShovelerBuilder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class BinderListBuilder {
        private ArtistDetailHeaderBinder artistDetailHeaderBinder;
        private ButtonNavigatorBinder buttonNavigatorBinder;
        private CollectionDetailHeaderBinder collectionDetailHeaderBinder;
        private final BaseViewsBinderConfiguration configuration;
        private DefaultPageHeaderBinder defaultDetailHeaderBinder;
        private DescriptiveRowItemBinder descriptiveRowItemBinder;
        private EmptyStateBinder emptyStateBinder;
        private FeatureBarkerBinder featureBarkerBinder;
        private FeaturedToggleViewBinder featuredToggleBinder;
        private TextRowItemBinder headerRowItemBinder;
        private HighlightableMessageBinder highlightableMessageBinder;
        private HorizontalTileBinder horizontalTileBinder;
        private MultiStateButtonBinder multiStateButtonBinder;
        private ExposedRefinementListBinder refinementsBinder;
        private SectionBinder sectionBinder;
        private SectionBuilder sectionBuilder;
        private ShovelerBinder shovelerBinder;
        private ShovelerBuilder shovelerBuilder;
        private DescriptiveTextBinder shuffledWithTextBinder;
        private StoryPageHeaderBinder storyDetailHeaderBinder;
        private VerticalTileBinder verticalTileBinder;
        private VisualRowItemBinder visualRowItemBinder;

        public BinderListBuilder(BaseViewsBinderConfiguration configuration) {
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            this.configuration = configuration;
        }

        public final BinderListBuilder withHeaderRowItems() {
            if (this.headerRowItemBinder == null) {
                this.headerRowItemBinder = BaseViewsBinderProvider.INSTANCE.getTextRowItemBinder(this.configuration);
            }
            return this;
        }

        public final BinderListBuilder withFeatureBarker(BrushUriClickListenerProvider brushUriClickListenerProvider, boolean hideBackgroundImageShadow) {
            Intrinsics.checkNotNullParameter(brushUriClickListenerProvider, "brushUriClickListenerProvider");
            if (this.featureBarkerBinder == null) {
                this.featureBarkerBinder = BaseViewsBinderProvider.INSTANCE.getFeatureBarkerBinder(this.configuration, brushUriClickListenerProvider, hideBackgroundImageShadow);
            }
            return this;
        }

        public static /* synthetic */ BinderListBuilder withVisualRowItems$default(BinderListBuilder binderListBuilder, CollectionTrackClickListenerFactory collectionTrackClickListenerFactory, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                collectionTrackClickListenerFactory = null;
            }
            if ((i & 2) != 0) {
                bool = Boolean.FALSE;
            }
            return binderListBuilder.withVisualRowItems(collectionTrackClickListenerFactory, bool);
        }

        public final BinderListBuilder withVisualRowItems(CollectionTrackClickListenerFactory clickListenerFactory, Boolean showTrackDownloadButton) {
            if (this.visualRowItemBinder == null) {
                this.visualRowItemBinder = BaseViewsBinderProvider.INSTANCE.getVisualRowItemBinder(this.configuration, clickListenerFactory, showTrackDownloadButton);
            }
            return this;
        }

        public final BinderListBuilder withTextRowItems() {
            if (this.headerRowItemBinder == null) {
                this.headerRowItemBinder = BaseViewsBinderProvider.INSTANCE.getTextRowItemBinder(this.configuration);
            }
            return this;
        }

        public static /* synthetic */ BinderListBuilder withDescriptiveRowItems$default(BinderListBuilder binderListBuilder, ClickListenerFactory clickListenerFactory, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                clickListenerFactory = null;
            }
            if ((i & 2) != 0) {
                bool = Boolean.FALSE;
            }
            return binderListBuilder.withDescriptiveRowItems(clickListenerFactory, bool);
        }

        public final BinderListBuilder withDescriptiveRowItems(ClickListenerFactory clickListenerFactory, Boolean showTrackDownloadButton) {
            if (this.descriptiveRowItemBinder == null) {
                this.descriptiveRowItemBinder = BaseViewsBinderProvider.INSTANCE.getDescriptiveRowItemBinder(this.configuration, clickListenerFactory, showTrackDownloadButton);
            }
            return this;
        }

        public static /* synthetic */ BinderListBuilder withVerticalTiles$default(BinderListBuilder binderListBuilder, BaseViewsBinderConfiguration baseViewsBinderConfiguration, int i, Object obj) {
            if ((i & 1) != 0) {
                baseViewsBinderConfiguration = binderListBuilder.configuration;
            }
            return binderListBuilder.withVerticalTiles(baseViewsBinderConfiguration);
        }

        public final BinderListBuilder withVerticalTiles(BaseViewsBinderConfiguration configuration) {
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            if (this.verticalTileBinder == null) {
                this.verticalTileBinder = BaseViewsBinderProvider.INSTANCE.getVerticalTileBinder(configuration);
            }
            return this;
        }

        public final BinderListBuilder withHorizontalTiles() {
            if (this.horizontalTileBinder == null) {
                this.horizontalTileBinder = BaseViewsBinderProvider.INSTANCE.getHorizontalTileBinder(this.configuration);
            }
            return this;
        }

        public final BinderListBuilder withArtistDetailHeader(ArtistClickListenerProvider artistClickListenerProvider, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> libraryPageHeaderOnClickListenerMap, boolean isArtistFollowSupported, boolean isLibrary, boolean isAllOwnedContent) {
            Intrinsics.checkNotNullParameter(artistClickListenerProvider, "artistClickListenerProvider");
            Intrinsics.checkNotNullParameter(libraryPageHeaderOnClickListenerMap, "libraryPageHeaderOnClickListenerMap");
            if (this.artistDetailHeaderBinder == null) {
                this.artistDetailHeaderBinder = BaseViewsBinderProvider.INSTANCE.getArtistDetailHeaderBinder(this.configuration, artistClickListenerProvider, libraryPageHeaderOnClickListenerMap, isArtistFollowSupported, isLibrary, isAllOwnedContent);
            }
            return this;
        }

        public static /* synthetic */ BinderListBuilder withCollectionHeader$default(BinderListBuilder binderListBuilder, Map map, UserPlaylistDetailProfileLinkHandler userPlaylistDetailProfileLinkHandler, int i, Object obj) {
            if ((i & 2) != 0) {
                userPlaylistDetailProfileLinkHandler = null;
            }
            return binderListBuilder.withCollectionHeader(map, userPlaylistDetailProfileLinkHandler);
        }

        public final BinderListBuilder withCollectionHeader(Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> pageHeaderOnClickListenerMap, UserPlaylistDetailProfileLinkHandler playlistProfileLinkHandler) {
            Intrinsics.checkNotNullParameter(pageHeaderOnClickListenerMap, "pageHeaderOnClickListenerMap");
            if (this.collectionDetailHeaderBinder == null) {
                this.collectionDetailHeaderBinder = BaseViewsBinderProvider.INSTANCE.getCollectionDetailHeaderBinder(this.configuration, pageHeaderOnClickListenerMap, playlistProfileLinkHandler);
            }
            return this;
        }

        public final BinderListBuilder withDefaultHeader(Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> pageHeaderOnClickListenerMap, PageType pageType) {
            Intrinsics.checkNotNullParameter(pageHeaderOnClickListenerMap, "pageHeaderOnClickListenerMap");
            if (this.defaultDetailHeaderBinder == null) {
                this.defaultDetailHeaderBinder = BaseViewsBinderProvider.INSTANCE.getDefaultPageHeaderBinder(this.configuration, pageHeaderOnClickListenerMap, pageType);
            }
            return this;
        }

        public final BinderListBuilder withStoryHeader(Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> pageHeaderOnClickListenerMap, View.OnTouchListener textContainerOnTouchListener, Integer paginationCount) {
            Intrinsics.checkNotNullParameter(pageHeaderOnClickListenerMap, "pageHeaderOnClickListenerMap");
            if (this.storyDetailHeaderBinder == null) {
                this.storyDetailHeaderBinder = BaseViewsBinderProvider.INSTANCE.getStoryPageHeaderBinder(this.configuration, pageHeaderOnClickListenerMap, textContainerOnTouchListener, paginationCount);
            }
            return this;
        }

        public final BinderListBuilder withRefinements(RefinementClickListenerProvider clickListenerProvider) {
            Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
            if (this.refinementsBinder == null) {
                this.refinementsBinder = BaseViewsBinderProvider.INSTANCE.getExposedRefinementListBinder(this.configuration, clickListenerProvider);
            }
            return this;
        }

        public final BinderListBuilder withButtonNavigator(BrushUriClickListenerProvider clickListenerProvider) {
            Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
            if (this.buttonNavigatorBinder == null) {
                this.buttonNavigatorBinder = BaseViewsBinderProvider.INSTANCE.getButtonNavigatorBinder(this.configuration, clickListenerProvider);
            }
            return this;
        }

        public final BinderListBuilder withFeaturedToggle(FeaturedToggleListenerProvider clickListenerProvider, ContentViewedListener contentViewedListener) {
            Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
            Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
            if (this.featuredToggleBinder == null) {
                this.featuredToggleBinder = BaseViewsBinderProvider.INSTANCE.getFeaturedToggleBinder(this.configuration, clickListenerProvider, contentViewedListener);
            }
            return this;
        }

        public final BinderListBuilder withShuffledWithText() {
            if (this.shuffledWithTextBinder == null) {
                this.shuffledWithTextBinder = new DescriptiveTextBinder(this.configuration.getStyleSheet());
            }
            return this;
        }

        public final BinderListBuilder withMultiStateButton(MultiStateButtonClickListenerProvider clickListenerProvider) {
            Intrinsics.checkNotNullParameter(clickListenerProvider, "clickListenerProvider");
            if (this.multiStateButtonBinder == null) {
                this.multiStateButtonBinder = new MultiStateButtonBinder(this.configuration.getStyleSheet(), clickListenerProvider);
            }
            return this;
        }

        public final BinderListBuilder withSections(SectionBuilder sectionBuilder) {
            Intrinsics.checkNotNullParameter(sectionBuilder, "sectionBuilder");
            this.sectionBuilder = sectionBuilder;
            Boolean withHeaderRowItems = sectionBuilder.getWithHeaderRowItems();
            Boolean bool = Boolean.TRUE;
            if (Intrinsics.areEqual(withHeaderRowItems, bool)) {
                withHeaderRowItems();
            }
            if (Intrinsics.areEqual(sectionBuilder.getWithVerticalTiles(), bool)) {
                withVerticalTiles$default(this, null, 1, null);
            }
            if (Intrinsics.areEqual(sectionBuilder.getWithHorizontalTiles(), bool)) {
                withHorizontalTiles();
            }
            if (Intrinsics.areEqual(sectionBuilder.getWithDescriptiveRowItems(), bool)) {
                withDescriptiveRowItems(sectionBuilder.getClickListenerFactory(), sectionBuilder.getShowTrackDownloadButton());
            }
            if (Intrinsics.areEqual(sectionBuilder.getWithVisualRowItems(), bool)) {
                withVisualRowItems$default(this, null, null, 3, null);
            }
            BaseViewsBinderProvider baseViewsBinderProvider = BaseViewsBinderProvider.INSTANCE;
            BaseViewsBinderConfiguration baseViewsBinderConfiguration = this.configuration;
            ContentViewedListener contentViewedListener = sectionBuilder.getContentViewedListener();
            SeeMoreProvider seeMoreProvider = sectionBuilder.getSeeMoreProvider();
            Object[] array = getListOfChildrenBinders().toArray(new UniversalBinder[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
            this.sectionBinder = BaseViewsBinderProvider.getSectionBinder$default(baseViewsBinderProvider, baseViewsBinderConfiguration, contentViewedListener, seeMoreProvider, null, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length), 8, null);
            return this;
        }

        public final BinderListBuilder withShovelers(ShovelerBuilder shovelerBuilder) {
            Intrinsics.checkNotNullParameter(shovelerBuilder, "shovelerBuilder");
            this.shovelerBuilder = shovelerBuilder;
            Boolean withHeaderRowItems = shovelerBuilder.getWithHeaderRowItems();
            Boolean bool = Boolean.TRUE;
            if (Intrinsics.areEqual(withHeaderRowItems, bool)) {
                withHeaderRowItems();
            }
            if (Intrinsics.areEqual(shovelerBuilder.getWithVerticalTiles(), bool)) {
                BaseViewsBinderConfiguration configuration = shovelerBuilder.getConfiguration();
                if (configuration == null) {
                    configuration = this.configuration;
                }
                withVerticalTiles(configuration);
            }
            if (Intrinsics.areEqual(shovelerBuilder.getWithHorizontalTiles(), bool)) {
                withHorizontalTiles();
            }
            if (Intrinsics.areEqual(shovelerBuilder.getWithDescriptiveRowItems(), bool)) {
                withDescriptiveRowItems$default(this, shovelerBuilder.getClickListenerFactory(), null, 2, null);
            }
            if (Intrinsics.areEqual(shovelerBuilder.getWithVisualRowItems(), bool)) {
                withVisualRowItems$default(this, null, null, 3, null);
            }
            new BaseButton.StyleBuilder().withStyle(new ButtonStyle(new StatefulStyle(-1, null, null, null, null, null, null, null, 254, null), null, null, null, 14, null));
            BaseViewsBinderProvider baseViewsBinderProvider = BaseViewsBinderProvider.INSTANCE;
            BaseViewsBinderConfiguration baseViewsBinderConfiguration = this.configuration;
            ContentViewedListener contentViewedListener = shovelerBuilder.getContentViewedListener();
            SeeMoreProvider seeMoreProvider = shovelerBuilder.getSeeMoreProvider();
            Object[] array = getListOfChildrenBinders().toArray(new UniversalBinder[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
            this.shovelerBinder = baseViewsBinderProvider.getShovelerBinder(baseViewsBinderConfiguration, contentViewedListener, seeMoreProvider, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length));
            return this;
        }

        public final BinderListBuilder withEmptyView(EmptyStateActionProvider emptyStateActionProvider) {
            if (this.emptyStateBinder == null) {
                this.emptyStateBinder = BaseViewsBinderProvider.INSTANCE.getEmptyStateBinder(this.configuration, emptyStateActionProvider);
            }
            return this;
        }

        public final BinderListBuilder withHighlightableMessageView(ButtonActionProvider buttonActionProvider) {
            if (this.highlightableMessageBinder == null) {
                this.highlightableMessageBinder = BaseViewsBinderProvider.INSTANCE.getHighlightableMessageBinder(this.configuration, buttonActionProvider);
            }
            return this;
        }

        /* JADX INFO: compiled from: BaseViewsBinderProvider.kt */
        @Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\u0004\b&\u0010'J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u0016R\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u0016R\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010\u0016R\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder$SectionBuilder;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "getSeeMoreProvider", "()Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "withHeaderRowItems", "Ljava/lang/Boolean;", "getWithHeaderRowItems", "()Ljava/lang/Boolean;", "withVerticalTiles", "getWithVerticalTiles", "withHorizontalTiles", "getWithHorizontalTiles", "withDescriptiveRowItems", "getWithDescriptiveRowItems", "showTrackDownloadButton", "getShowTrackDownloadButton", "withVisualRowItems", "getWithVisualRowItems", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getClickListenerFactory", "()Lcom/amazon/music/views/library/click/ClickListenerFactory;", "<init>", "(Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/views/library/click/ClickListenerFactory;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
        public static final /* data */ class SectionBuilder {
            private final ClickListenerFactory clickListenerFactory;
            private final ContentViewedListener contentViewedListener;
            private final SeeMoreProvider seeMoreProvider;
            private final Boolean showTrackDownloadButton;
            private final Boolean withDescriptiveRowItems;
            private final Boolean withHeaderRowItems;
            private final Boolean withHorizontalTiles;
            private final Boolean withVerticalTiles;
            private final Boolean withVisualRowItems;

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SectionBuilder)) {
                    return false;
                }
                SectionBuilder sectionBuilder = (SectionBuilder) other;
                return Intrinsics.areEqual(this.contentViewedListener, sectionBuilder.contentViewedListener) && Intrinsics.areEqual(this.seeMoreProvider, sectionBuilder.seeMoreProvider) && Intrinsics.areEqual(this.withHeaderRowItems, sectionBuilder.withHeaderRowItems) && Intrinsics.areEqual(this.withVerticalTiles, sectionBuilder.withVerticalTiles) && Intrinsics.areEqual(this.withHorizontalTiles, sectionBuilder.withHorizontalTiles) && Intrinsics.areEqual(this.withDescriptiveRowItems, sectionBuilder.withDescriptiveRowItems) && Intrinsics.areEqual(this.showTrackDownloadButton, sectionBuilder.showTrackDownloadButton) && Intrinsics.areEqual(this.withVisualRowItems, sectionBuilder.withVisualRowItems) && Intrinsics.areEqual(this.clickListenerFactory, sectionBuilder.clickListenerFactory);
            }

            public int hashCode() {
                int iHashCode = this.contentViewedListener.hashCode() * 31;
                SeeMoreProvider seeMoreProvider = this.seeMoreProvider;
                int iHashCode2 = (iHashCode + (seeMoreProvider == null ? 0 : seeMoreProvider.hashCode())) * 31;
                Boolean bool = this.withHeaderRowItems;
                int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
                Boolean bool2 = this.withVerticalTiles;
                int iHashCode4 = (iHashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
                Boolean bool3 = this.withHorizontalTiles;
                int iHashCode5 = (iHashCode4 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
                Boolean bool4 = this.withDescriptiveRowItems;
                int iHashCode6 = (iHashCode5 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
                Boolean bool5 = this.showTrackDownloadButton;
                int iHashCode7 = (iHashCode6 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
                Boolean bool6 = this.withVisualRowItems;
                int iHashCode8 = (iHashCode7 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
                ClickListenerFactory clickListenerFactory = this.clickListenerFactory;
                return iHashCode8 + (clickListenerFactory != null ? clickListenerFactory.hashCode() : 0);
            }

            public String toString() {
                return "SectionBuilder(contentViewedListener=" + this.contentViewedListener + ", seeMoreProvider=" + this.seeMoreProvider + ", withHeaderRowItems=" + this.withHeaderRowItems + ", withVerticalTiles=" + this.withVerticalTiles + ", withHorizontalTiles=" + this.withHorizontalTiles + ", withDescriptiveRowItems=" + this.withDescriptiveRowItems + ", showTrackDownloadButton=" + this.showTrackDownloadButton + ", withVisualRowItems=" + this.withVisualRowItems + ", clickListenerFactory=" + this.clickListenerFactory + ')';
            }

            public SectionBuilder(ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, ClickListenerFactory clickListenerFactory) {
                Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
                this.contentViewedListener = contentViewedListener;
                this.seeMoreProvider = seeMoreProvider;
                this.withHeaderRowItems = bool;
                this.withVerticalTiles = bool2;
                this.withHorizontalTiles = bool3;
                this.withDescriptiveRowItems = bool4;
                this.showTrackDownloadButton = bool5;
                this.withVisualRowItems = bool6;
                this.clickListenerFactory = clickListenerFactory;
            }

            public final ContentViewedListener getContentViewedListener() {
                return this.contentViewedListener;
            }

            public final SeeMoreProvider getSeeMoreProvider() {
                return this.seeMoreProvider;
            }

            public /* synthetic */ SectionBuilder(ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, ClickListenerFactory clickListenerFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(contentViewedListener, (i & 2) != 0 ? null : seeMoreProvider, (i & 4) != 0 ? Boolean.FALSE : bool, (i & 8) != 0 ? Boolean.FALSE : bool2, (i & 16) != 0 ? Boolean.FALSE : bool3, (i & 32) != 0 ? Boolean.FALSE : bool4, (i & 64) != 0 ? Boolean.FALSE : bool5, (i & 128) != 0 ? Boolean.FALSE : bool6, (i & 256) == 0 ? clickListenerFactory : null);
            }

            public final Boolean getWithHeaderRowItems() {
                return this.withHeaderRowItems;
            }

            public final Boolean getWithVerticalTiles() {
                return this.withVerticalTiles;
            }

            public final Boolean getWithHorizontalTiles() {
                return this.withHorizontalTiles;
            }

            public final Boolean getWithDescriptiveRowItems() {
                return this.withDescriptiveRowItems;
            }

            public final Boolean getShowTrackDownloadButton() {
                return this.showTrackDownloadButton;
            }

            public final Boolean getWithVisualRowItems() {
                return this.withVisualRowItems;
            }

            public final ClickListenerFactory getClickListenerFactory() {
                return this.clickListenerFactory;
            }
        }

        /* JADX INFO: compiled from: BaseViewsBinderProvider.kt */
        @Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010$¢\u0006\u0004\b)\u0010*J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u0016R\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u0016R\u0019\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(¨\u0006+"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderProvider$BinderListBuilder$ShovelerBuilder;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "getSeeMoreProvider", "()Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "withHeaderRowItems", "Ljava/lang/Boolean;", "getWithHeaderRowItems", "()Ljava/lang/Boolean;", "withVerticalTiles", "getWithVerticalTiles", "withHorizontalTiles", "getWithHorizontalTiles", "withDescriptiveRowItems", "getWithDescriptiveRowItems", "withVisualRowItems", "getWithVisualRowItems", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "getClickListenerFactory", "()Lcom/amazon/music/views/library/click/ClickListenerFactory;", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "configuration", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "getConfiguration", "()Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "<init>", "(Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
        public static final /* data */ class ShovelerBuilder {
            private final ClickListenerFactory clickListenerFactory;
            private final BaseViewsBinderConfiguration configuration;
            private final ContentViewedListener contentViewedListener;
            private final SeeMoreProvider seeMoreProvider;
            private final Boolean withDescriptiveRowItems;
            private final Boolean withHeaderRowItems;
            private final Boolean withHorizontalTiles;
            private final Boolean withVerticalTiles;
            private final Boolean withVisualRowItems;

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ShovelerBuilder)) {
                    return false;
                }
                ShovelerBuilder shovelerBuilder = (ShovelerBuilder) other;
                return Intrinsics.areEqual(this.contentViewedListener, shovelerBuilder.contentViewedListener) && Intrinsics.areEqual(this.seeMoreProvider, shovelerBuilder.seeMoreProvider) && Intrinsics.areEqual(this.withHeaderRowItems, shovelerBuilder.withHeaderRowItems) && Intrinsics.areEqual(this.withVerticalTiles, shovelerBuilder.withVerticalTiles) && Intrinsics.areEqual(this.withHorizontalTiles, shovelerBuilder.withHorizontalTiles) && Intrinsics.areEqual(this.withDescriptiveRowItems, shovelerBuilder.withDescriptiveRowItems) && Intrinsics.areEqual(this.withVisualRowItems, shovelerBuilder.withVisualRowItems) && Intrinsics.areEqual(this.clickListenerFactory, shovelerBuilder.clickListenerFactory) && Intrinsics.areEqual(this.configuration, shovelerBuilder.configuration);
            }

            public int hashCode() {
                int iHashCode = this.contentViewedListener.hashCode() * 31;
                SeeMoreProvider seeMoreProvider = this.seeMoreProvider;
                int iHashCode2 = (iHashCode + (seeMoreProvider == null ? 0 : seeMoreProvider.hashCode())) * 31;
                Boolean bool = this.withHeaderRowItems;
                int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
                Boolean bool2 = this.withVerticalTiles;
                int iHashCode4 = (iHashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
                Boolean bool3 = this.withHorizontalTiles;
                int iHashCode5 = (iHashCode4 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
                Boolean bool4 = this.withDescriptiveRowItems;
                int iHashCode6 = (iHashCode5 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
                Boolean bool5 = this.withVisualRowItems;
                int iHashCode7 = (iHashCode6 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
                ClickListenerFactory clickListenerFactory = this.clickListenerFactory;
                int iHashCode8 = (iHashCode7 + (clickListenerFactory == null ? 0 : clickListenerFactory.hashCode())) * 31;
                BaseViewsBinderConfiguration baseViewsBinderConfiguration = this.configuration;
                return iHashCode8 + (baseViewsBinderConfiguration != null ? baseViewsBinderConfiguration.hashCode() : 0);
            }

            public String toString() {
                return "ShovelerBuilder(contentViewedListener=" + this.contentViewedListener + ", seeMoreProvider=" + this.seeMoreProvider + ", withHeaderRowItems=" + this.withHeaderRowItems + ", withVerticalTiles=" + this.withVerticalTiles + ", withHorizontalTiles=" + this.withHorizontalTiles + ", withDescriptiveRowItems=" + this.withDescriptiveRowItems + ", withVisualRowItems=" + this.withVisualRowItems + ", clickListenerFactory=" + this.clickListenerFactory + ", configuration=" + this.configuration + ')';
            }

            public ShovelerBuilder(ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, ClickListenerFactory clickListenerFactory, BaseViewsBinderConfiguration baseViewsBinderConfiguration) {
                Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
                this.contentViewedListener = contentViewedListener;
                this.seeMoreProvider = seeMoreProvider;
                this.withHeaderRowItems = bool;
                this.withVerticalTiles = bool2;
                this.withHorizontalTiles = bool3;
                this.withDescriptiveRowItems = bool4;
                this.withVisualRowItems = bool5;
                this.clickListenerFactory = clickListenerFactory;
                this.configuration = baseViewsBinderConfiguration;
            }

            public final ContentViewedListener getContentViewedListener() {
                return this.contentViewedListener;
            }

            public final SeeMoreProvider getSeeMoreProvider() {
                return this.seeMoreProvider;
            }

            public /* synthetic */ ShovelerBuilder(ContentViewedListener contentViewedListener, SeeMoreProvider seeMoreProvider, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, ClickListenerFactory clickListenerFactory, BaseViewsBinderConfiguration baseViewsBinderConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(contentViewedListener, (i & 2) != 0 ? null : seeMoreProvider, (i & 4) != 0 ? Boolean.FALSE : bool, (i & 8) != 0 ? Boolean.FALSE : bool2, (i & 16) != 0 ? Boolean.FALSE : bool3, (i & 32) != 0 ? Boolean.FALSE : bool4, (i & 64) != 0 ? Boolean.FALSE : bool5, (i & 128) != 0 ? null : clickListenerFactory, (i & 256) == 0 ? baseViewsBinderConfiguration : null);
            }

            public final Boolean getWithHeaderRowItems() {
                return this.withHeaderRowItems;
            }

            public final Boolean getWithVerticalTiles() {
                return this.withVerticalTiles;
            }

            public final Boolean getWithHorizontalTiles() {
                return this.withHorizontalTiles;
            }

            public final Boolean getWithDescriptiveRowItems() {
                return this.withDescriptiveRowItems;
            }

            public final Boolean getWithVisualRowItems() {
                return this.withVisualRowItems;
            }

            public final ClickListenerFactory getClickListenerFactory() {
                return this.clickListenerFactory;
            }

            public final BaseViewsBinderConfiguration getConfiguration() {
                return this.configuration;
            }
        }

        public final List<UniversalBinder<?, ?>> build() {
            return CollectionsKt___CollectionsKt.plus((Collection) getListOfChildrenBinders(), (Iterable) CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new UniversalBinder[]{this.sectionBinder, this.shovelerBinder, this.collectionDetailHeaderBinder, this.refinementsBinder, this.buttonNavigatorBinder, this.defaultDetailHeaderBinder, this.storyDetailHeaderBinder, this.artistDetailHeaderBinder, this.featuredToggleBinder, this.multiStateButtonBinder, this.shuffledWithTextBinder}));
        }

        private final List<UniversalBinder<?, ?>> getListOfChildrenBinders() {
            return CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new UniversalBinder[]{this.headerRowItemBinder, this.verticalTileBinder, this.horizontalTileBinder, this.descriptiveRowItemBinder, this.visualRowItemBinder, this.emptyStateBinder, this.featureBarkerBinder, this.highlightableMessageBinder});
        }
    }
}
