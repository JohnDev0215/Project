package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.HotspotTooltipModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.music.views.library.providers.ContentEligibilityProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.ItemOrdinalProvider;
import com.amazon.music.views.library.providers.LanguageOfPerformancesProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.utils.AudioContentEncodingUtil;
import com.amazon.music.views.library.views.ArtworkFrameView;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.music.views.library.views.IconPresenter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: HorizontalTileBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001Bw\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010\u001aJ\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0003H\u0016J\u001e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&J\u0018\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0003H\u0002J\u0010\u0010(\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0002H\u0002J\u0010\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020-2\u0006\u0010$\u001a\u00020\u0003H\u0002J \u0010.\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00032\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0003H\u0002J\u0018\u00102\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0002H\u0002J\u0018\u00103\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0003H\u0002J\u0018\u00104\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0003H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/amazon/music/views/library/binders/HorizontalTileBinder;", "Lcom/amazon/music/views/library/binders/LibraryAddableBinder;", "Lcom/amazon/music/views/library/views/HorizontalTileView;", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "contentEligibilityProvider", "Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "hotspotTooltipBinder", "Lcom/amazon/music/views/library/binders/HotspotTooltipBinder;", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "contextMenuProvider", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "itemOrdinalProvider", "Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/subscription/UserSubscription;Lcom/amazon/music/views/library/providers/ContentEligibilityProvider;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/binders/HotspotTooltipBinder;Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;Lcom/amazon/music/views/library/providers/ItemOrdinalProvider;Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;)V", "isNightwingOnly", "", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "width", "", "bindContextMenu", "clearDownloadIcon", "createView", "context", "Landroid/content/Context;", "getOnClickListener", "Landroid/view/View$OnClickListener;", "handleStateChange", "payload", "", "setBadging", "setLanguageOfPerformanceBadging", "showDownloadButton", "showHotspot", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HorizontalTileBinder extends LibraryAddableBinder<HorizontalTileView, HorizontalTileModel> {
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentEligibilityProvider contentEligibilityProvider;
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final MetadataContextMenuProvider contextMenuProvider;
    private final HotspotTooltipBinder hotspotTooltipBinder;
    private final boolean isNightwingOnly;
    private final ItemOrdinalProvider itemOrdinalProvider;
    private final RowButtonOnClickProvider rowButtonOnClickProvider;
    private final StyleSheet styleSheet;
    private final TrackDownloadButtonProvider trackDownloadButtonProvider;
    private final UserSubscription userSubscription;

    /* JADX INFO: compiled from: HorizontalTileBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HorizontalTileType.values().length];
            iArr[HorizontalTileType.FEATURED.ordinal()] = 1;
            iArr[HorizontalTileType.REGULAR.ordinal()] = 2;
            iArr[HorizontalTileType.DETAILED.ordinal()] = 3;
            iArr[HorizontalTileType.COMPACT.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<HorizontalTileModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(HorizontalTileModel.class);
    }

    public /* synthetic */ HorizontalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ContentEligibilityProvider contentEligibilityProvider, ArtworkFrameBinder artworkFrameBinder, HotspotTooltipBinder hotspotTooltipBinder, RowButtonOnClickProvider rowButtonOnClickProvider, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ItemOrdinalProvider itemOrdinalProvider, TrackDownloadButtonProvider trackDownloadButtonProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(styleSheet, userSubscription, (i & 4) != 0 ? null : contentEligibilityProvider, artworkFrameBinder, (i & 16) != 0 ? null : hotspotTooltipBinder, rowButtonOnClickProvider, (i & 64) != 0 ? null : metadataContextMenuProvider, clickListenerFactory, (i & 256) != 0 ? null : contentEnabilityProvider, (i & 512) != 0 ? null : itemOrdinalProvider, (i & 1024) != 0 ? null : trackDownloadButtonProvider);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalTileBinder(StyleSheet styleSheet, UserSubscription userSubscription, ContentEligibilityProvider contentEligibilityProvider, ArtworkFrameBinder artworkFrameBinder, HotspotTooltipBinder hotspotTooltipBinder, RowButtonOnClickProvider rowButtonOnClickProvider, MetadataContextMenuProvider metadataContextMenuProvider, ClickListenerFactory clickListenerFactory, ContentEnabilityProvider contentEnabilityProvider, ItemOrdinalProvider itemOrdinalProvider, TrackDownloadButtonProvider trackDownloadButtonProvider) {
        super(userSubscription, contentEligibilityProvider, rowButtonOnClickProvider, trackDownloadButtonProvider);
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(userSubscription, "userSubscription");
        Intrinsics.checkNotNullParameter(artworkFrameBinder, "artworkFrameBinder");
        Intrinsics.checkNotNullParameter(clickListenerFactory, "clickListenerFactory");
        this.styleSheet = styleSheet;
        this.userSubscription = userSubscription;
        this.contentEligibilityProvider = contentEligibilityProvider;
        this.artworkFrameBinder = artworkFrameBinder;
        this.hotspotTooltipBinder = hotspotTooltipBinder;
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
        this.contextMenuProvider = metadataContextMenuProvider;
        this.clickListenerFactory = clickListenerFactory;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.itemOrdinalProvider = itemOrdinalProvider;
        this.trackDownloadButtonProvider = trackDownloadButtonProvider;
        this.isNightwingOnly = userSubscription.isNightwingOnly();
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public HorizontalTileView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HorizontalTileView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.LibraryAddableBinder
    public void handleStateChange(HorizontalTileView view, HorizontalTileModel model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 1)) {
            ArtworkFrameBinder artworkFrameBinder = this.artworkFrameBinder;
            ArtworkFrameView artworkFrameLayout = view.getArtworkFrameLayout();
            ArtworkFrameModel artworkFrameModel = model.getArtworkFrameModel();
            Intrinsics.checkNotNull(artworkFrameModel);
            artworkFrameBinder.handleStateChange(artworkFrameLayout, artworkFrameModel, payload);
            return;
        }
        if (Intrinsics.areEqual(payload, (Object) 12)) {
            if (shouldShowDownloadButton(model)) {
                showDownloadButton(view, model);
                return;
            } else {
                clearDownloadIcon(view);
                return;
            }
        }
        super.handleStateChange(view, model, payload);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(HorizontalTileView view, HorizontalTileModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        bind(view, model, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void bind(com.amazon.music.views.library.views.HorizontalTileView r22, com.amazon.music.views.library.models.HorizontalTileModel r23, int r24) {
        /*
            Method dump skipped, instruction units count: 747
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.binders.HorizontalTileBinder.bind(com.amazon.music.views.library.views.HorizontalTileView, com.amazon.music.views.library.models.HorizontalTileModel, int):void");
    }

    private final void showDownloadButton(HorizontalTileView view, final HorizontalTileModel model) {
        view.setLeftIcon(IconPresenter.IconType.DOWNLOAD_ICON, new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.HorizontalTileBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HorizontalTileBinder.m2323showDownloadButton$lambda11(this.f$0, model, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showDownloadButton$lambda-11, reason: not valid java name */
    public static final void m2323showDownloadButton$lambda11(HorizontalTileBinder this$0, HorizontalTileModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        TrackDownloadButtonProvider trackDownloadButtonProvider = this$0.trackDownloadButtonProvider;
        if (trackDownloadButtonProvider == null) {
            return;
        }
        trackDownloadButtonProvider.onDownload(model);
    }

    private final boolean setBadging(HorizontalTileView view, HorizontalTileModel model) {
        boolean languageOfPerformanceBadging = setLanguageOfPerformanceBadging(model, view);
        Integer brandingImageResource = model.getBrandingImageResource();
        if (brandingImageResource != null) {
            view.setBrandingResource(brandingImageResource.intValue());
            languageOfPerformanceBadging = true;
        }
        AudioContentEncodingUtil audioContentEncodingUtil = AudioContentEncodingUtil.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        String audioQualityText = audioContentEncodingUtil.getAudioQualityText(context, model.getContentEncoding());
        if (!TextUtils.isEmpty(audioQualityText)) {
            languageOfPerformanceBadging = true;
        }
        view.setAudioQualityText(audioQualityText);
        Context context2 = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "view.context");
        String immersiveBadgeText = audioContentEncodingUtil.getImmersiveBadgeText(context2, model.getContentEncoding(), this.userSubscription);
        if (!TextUtils.isEmpty(immersiveBadgeText)) {
            languageOfPerformanceBadging = true;
        }
        view.setImmersiveBadgeText(immersiveBadgeText);
        if (!(model.getMetadata() instanceof AlbumMetadata)) {
            return languageOfPerformanceBadging;
        }
        boolean zContainsBothAtmosAnd360 = audioContentEncodingUtil.containsBothAtmosAnd360(model.getContentEncoding(), this.userSubscription);
        boolean z = zContainsBothAtmosAnd360 ? true : languageOfPerformanceBadging;
        view.setExtraAtmosBadgeText(zContainsBothAtmosAnd360);
        return z;
    }

    private final boolean setLanguageOfPerformanceBadging(HorizontalTileModel model, HorizontalTileView view) {
        List<String> languageOfPerformances;
        ContentMetadata metadata = model.getMetadata();
        LanguageOfPerformancesProvider languageOfPerformancesProvider = metadata instanceof LanguageOfPerformancesProvider ? (LanguageOfPerformancesProvider) metadata : null;
        if (languageOfPerformancesProvider == null || (languageOfPerformances = languageOfPerformancesProvider.getLanguageOfPerformances()) == null || !(!languageOfPerformances.isEmpty())) {
            return false;
        }
        view.setLanguageOfPerformanceText(languageOfPerformances);
        return true;
    }

    private final void bindContextMenu(final HorizontalTileView view, HorizontalTileModel model) {
        Unit unit;
        final ContentMetadata metadata = model.getMetadata();
        if (this.contextMenuProvider == null) {
            unit = null;
        } else {
            if (metadata instanceof TrackMetadata) {
                ((TrackMetadata) metadata).setContentEncodings(model.getContentEncoding());
            }
            boolean z = model.getShouldShowOverflowMenu() && this.contextMenuProvider.shouldDisplayContextMenu(metadata);
            view.showOverflowMenu(z);
            if (z) {
                view.setOverflowOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.HorizontalTileBinder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        HorizontalTileBinder.m2321bindContextMenu$lambda16$lambda14(this.f$0, view, metadata, view2);
                    }
                });
                view.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.music.views.library.binders.HorizontalTileBinder$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view2) {
                        return HorizontalTileBinder.m2322bindContextMenu$lambda16$lambda15(this.f$0, view, metadata, view2);
                    }
                });
                this.contextMenuProvider.updateStateProvider(metadata);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            view.showOverflowMenu(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindContextMenu$lambda-16$lambda-14, reason: not valid java name */
    public static final void m2321bindContextMenu$lambda16$lambda14(HorizontalTileBinder this$0, HorizontalTileView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        this$0.contextMenuProvider.openContextMenu(view, metadata, InteractionType.TAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindContextMenu$lambda-16$lambda-15, reason: not valid java name */
    public static final boolean m2322bindContextMenu$lambda16$lambda15(HorizontalTileBinder this$0, HorizontalTileView view, ContentMetadata metadata, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        this$0.contextMenuProvider.openContextMenu(view, metadata, InteractionType.LONG_PRESS);
        return true;
    }

    private final View.OnClickListener getOnClickListener(HorizontalTileModel model) {
        List<ContentMetadata> metadataList = model.getMetadataList();
        if (metadataList != null) {
            return ClickListenerFactory.DefaultImpls.create$default(this.clickListenerFactory, metadataList, metadataList.indexOf(model.getMetadata()), null, 4, null);
        }
        return this.clickListenerFactory.create(model.getMetadata());
    }

    private final void showHotspot(HorizontalTileView view, HorizontalTileModel model) {
        HotspotTooltipBinder hotspotTooltipBinder;
        HotspotTooltipModel hotspotTooltipModel = model.getHotspotTooltipModel();
        if (hotspotTooltipModel != null && (hotspotTooltipBinder = this.hotspotTooltipBinder) != null) {
            hotspotTooltipBinder.bind(view, hotspotTooltipModel, model);
        }
        view.showHotspot(model.getHotspotTooltipModel() != null);
    }

    private final void clearDownloadIcon(HorizontalTileView view) {
        if (IconPresenter.IconType.DOWNLOAD_ICON == view.getLeftIconType()) {
            view.clearLeftIcon();
        }
    }
}
