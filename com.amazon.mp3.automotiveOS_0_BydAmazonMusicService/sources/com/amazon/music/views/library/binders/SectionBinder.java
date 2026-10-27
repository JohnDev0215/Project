package com.amazon.music.views.library.binders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.binders.SectionBinder;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.PillCellModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.StateModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.music.views.library.views.TransparentGradientImageView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: SectionBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\t\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0093\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u00122\u0010\u0016\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u0019\u0012\u0006\b\u0001\u0012\u00020\u001a0\u00180\u0017\"\u0012\u0012\u0006\b\u0001\u0012\u00020\u0019\u0012\u0006\b\u0001\u0012\u00020\u001a0\u0018¢\u0006\u0002\u0010\u001bJ\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0002H\u0016J\u001e\u0010,\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0.H\u0002J\u0018\u0010/\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0002H\u0002J\u0010\u00100\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0003H\u0002J\u0010\u00101\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u00102\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020\u0002H\u0002J\u0010\u00103\u001a\u0002042\u0006\u0010+\u001a\u00020\u0002H\u0002J\u0010\u00105\u001a\u0002042\u0006\u0010+\u001a\u00020\u0002H\u0002J\u000e\u00106\u001a\u0002072\u0006\u0010*\u001a\u00020\u0003J2\u00108\u001a\u0010\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0018\u0001092\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0.2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020:0<H\u0002J\u001e\u0010=\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010<2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0.H\u0002J\u0018\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020:H\u0002J&\u0010A\u001a\u00020)2\u0014\u0010B\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001d092\u0006\u0010@\u001a\u00020:H\u0002J\u001e\u0010C\u001a\u00020)2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0.2\u0006\u0010D\u001a\u000204H\u0002R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/amazon/music/views/library/binders/SectionBinder;", "Lcom/amazon/music/views/library/binders/BaseViewContainerBinder;", "Lcom/amazon/music/views/library/models/SectionGridViewModel;", "Lcom/amazon/music/views/library/views/SectionWidget;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "stateChangeProvider", "Lcom/amazon/music/views/library/providers/StateChangeProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "binders", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/view/View;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/providers/StateChangeProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/imageloader/ImageLoader;[Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "KEY_VIEW_REFERENCE_ID", "", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "playbackContainerBinder", "Lcom/amazon/music/views/library/binders/PlaybackContainerBinder;", "bind", "", "view", "model", "bindModels", "models", "", "bindStyle", "cleanSectionWidget", "createView", "displayArtwork", "displayingHeaderText", "", "displayingMoreButton", "getBitmapLoadedCallback", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "getFullGridSpanPositionToCustomColumnCountMap", "", "", "fullGridSpanPositions", "", "getFullGridSpanPositions", "reportContentViewWithBlockRef", "identifier", "numItemsAccessible", "reportContentViewWithContentInfo", ContextMappingConstants.CONTENT_INFO, "setChildrenStates", "isActive", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SectionBinder extends BaseViewContainerBinder<SectionGridViewModel, SectionWidget> {
    private final String KEY_VIEW_REFERENCE_ID;
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private GridLayoutUtils gridLayoutUtils;
    private final ImageLoader imageLoader;
    private final PlaybackContainerBinder playbackContainerBinder;
    private final SeeMoreProvider seeMoreProvider;
    private final StateChangeProvider stateChangeProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SectionGridViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(SectionGridViewModel.class);
    }

    public /* synthetic */ SectionBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, StateChangeProvider stateChangeProvider, BaseViewModelAdapterProvider baseViewModelAdapterProvider, ContentViewedListener contentViewedListener, ArtworkFrameBinder artworkFrameBinder, ClickListenerFactory clickListenerFactory, ImageLoader imageLoader, UniversalBinder[] universalBinderArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, seeMoreProvider, (i & 8) != 0 ? null : stateChangeProvider, baseViewModelAdapterProvider, contentViewedListener, (i & 64) != 0 ? null : artworkFrameBinder, (i & 128) != 0 ? null : clickListenerFactory, (i & 256) != 0 ? null : imageLoader, universalBinderArr);
    }

    protected ContentViewedListener getContentViewedListener() {
        return this.contentViewedListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SectionBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, StateChangeProvider stateChangeProvider, BaseViewModelAdapterProvider adapterProvider, ContentViewedListener contentViewedListener, ArtworkFrameBinder artworkFrameBinder, ClickListenerFactory clickListenerFactory, ImageLoader imageLoader, UniversalBinder<? extends View, ? extends BaseViewModel>... binders) {
        super(styleSheet, contentViewedListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(binders, "binders");
        this.context = context;
        this.seeMoreProvider = seeMoreProvider;
        this.stateChangeProvider = stateChangeProvider;
        this.adapterProvider = adapterProvider;
        this.contentViewedListener = contentViewedListener;
        this.artworkFrameBinder = artworkFrameBinder;
        this.clickListenerFactory = clickListenerFactory;
        this.imageLoader = imageLoader;
        this.KEY_VIEW_REFERENCE_ID = ContentUtils.KEY_VIEW_REFERENCE_ID;
        this.gridLayoutUtils = new GridLayoutUtils(context, styleSheet);
        this.playbackContainerBinder = new PlaybackContainerBinder();
        buildBinders((UniversalBinder<? extends View, ? extends BaseViewModel>[]) Arrays.copyOf(binders, binders.length));
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SectionWidget createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        SectionWidget sectionWidget = new SectionWidget(context, getStyleSheet());
        sectionWidget.setContentView(new RecyclerView(context));
        return sectionWidget;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(final SectionWidget view, final SectionGridViewModel model) {
        ClickListenerFactory clickListenerFactory;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        cleanSectionWidget(view);
        if (model.getModels() != null) {
            if (!model.getModels().isEmpty()) {
                if (model.getTileType() == 2) {
                    GridLayoutUtils gridLayoutUtils = this.gridLayoutUtils;
                    RecyclerView recyclerView = view.getRecyclerView();
                    Intrinsics.checkNotNull(recyclerView);
                    gridLayoutUtils.setSingleListLayoutManager(recyclerView, model.getModels().get(0));
                } else {
                    Set<Integer> fullGridSpanPositions = getFullGridSpanPositions(model.getModels());
                    GridLayoutUtils gridLayoutUtils2 = this.gridLayoutUtils;
                    RecyclerView recyclerView2 = view.getRecyclerView();
                    Intrinsics.checkNotNull(recyclerView2);
                    gridLayoutUtils2.setLayoutManager(recyclerView2, model.getTileType(), Integer.valueOf(model.getModels().size()), model.getShouldShowDividerLine(), false, fullGridSpanPositions, fullGridSpanPositions == null ? null : getFullGridSpanPositionToCustomColumnCountMap(model.getModels(), fullGridSpanPositions));
                }
                bindStyle(view, model);
                final List<BaseViewModel> models = model.getModels();
                boolean z = model.getMoreText() != null;
                Integer overrideDisplayCount = model.getOverrideDisplayCount();
                int maxItems = overrideDisplayCount == null ? this.gridLayoutUtils.getMaxItems(model.getTileType()) : overrideDisplayCount.intValue();
                final int iMin = Math.min(maxItems, models.size());
                bindModels(view, models.subList(0, iMin));
                String title = model.getTitle();
                if (title != null) {
                    view.setTitle(title);
                }
                String subtitle = model.getSubtitle();
                if (subtitle != null) {
                    view.setSubtitle(subtitle);
                }
                if (model.getButtonTargetType() == TargetType.RESET_STATE || (z && !TextUtils.isEmpty(model.getMoreTarget()) && model.getModels().size() > maxItems && this.seeMoreProvider != null)) {
                    String moreText = model.getMoreText();
                    Intrinsics.checkNotNull(moreText);
                    SectionWidget.setMoreButtonText$default(view, moreText, false, 2, null);
                    view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.SectionBinder$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            SectionBinder.m2360bind$lambda3(model, this, models, view, iMin, view2);
                        }
                    });
                }
                view.setVisibility(0);
                view.setEnabled(model.getIsEnabled());
                Map<String, String> contentInfo = model.getContentInfo();
                String viewReferenceId = model.getViewReferenceId();
                if (contentInfo != null) {
                    reportContentViewWithContentInfo(contentInfo, iMin);
                } else if (viewReferenceId != null) {
                    reportContentViewWithBlockRef(viewReferenceId, iMin);
                }
                if (model.getTileType() != 19 || this.artworkFrameBinder == null) {
                    return;
                }
                this.playbackContainerBinder.styleTopResultsPlaybackContainer(view, getStyleSheet().getColor(ColorKey.COLOR_ACCENT));
                displayArtwork(view, model);
                ContentMetadata containerMetadata = model.getContainerMetadata();
                if (containerMetadata == null || (clickListenerFactory = this.clickListenerFactory) == null) {
                    return;
                }
                view.setPlaybackContainerHeaderOnClickListener(clickListenerFactory.create(containerMetadata));
                return;
            }
        }
        hideView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-3, reason: not valid java name */
    public static final void m2360bind$lambda3(SectionGridViewModel model, SectionBinder this$0, List models, SectionWidget view, int i, View view2) {
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(models, "$models");
        Intrinsics.checkNotNullParameter(view, "$view");
        if (model.getButtonTargetType() == TargetType.RESET_STATE) {
            this$0.setChildrenStates(models, false);
            this$0.bindModels(view, models.subList(0, i));
            StateChangeProvider stateChangeProvider = this$0.stateChangeProvider;
            if (stateChangeProvider == null) {
                return;
            }
            stateChangeProvider.onResetState(model.getViewReferenceId());
            return;
        }
        SeeMoreProvider seeMoreProvider = this$0.seeMoreProvider;
        if (seeMoreProvider == null) {
            return;
        }
        seeMoreProvider.onSeeMoreClick(model.getMoreTarget(), model.getViewReferenceId(), model.getNextPageToken());
    }

    private final void setChildrenStates(List<? extends BaseViewModel> models, boolean isActive) {
        for (Object obj : models) {
            if (obj instanceof StateModel) {
                ((StateModel) obj).setActive(isActive);
            }
        }
    }

    private final void cleanSectionWidget(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            adapter.clear();
        }
        view.setInitialMargins();
        view.showImage(false);
        view.showBackground(false);
        this.playbackContainerBinder.reset(view);
    }

    private final void bindStyle(SectionWidget view, SectionGridViewModel model) {
        int tileType = model.getTileType();
        if (tileType == 0) {
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue : 0), 7, null);
            SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(displayingMoreButton(model) ? iIntValue : 0), 7, null);
            return;
        }
        if (tileType == 2) {
            if ((model.getModels().get(0) instanceof HorizontalTileModel) || (model.getModels().get(0) instanceof PillCellModel)) {
                SectionWidget.setContentMargins$default(view, 0, null, 0, null, 10, null);
                Integer spacerInPixels2 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
                SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue2 : 0), 7, null);
                SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(displayingMoreButton(model) ? iIntValue2 : 0), 7, null);
                if (model.getModels().get(0) instanceof PillCellModel) {
                    SectionWidget.setMargins$default(view, null, 0, null, Integer.valueOf(iIntValue2), null, 21, null);
                }
            }
            if (model.getModels().get(0) instanceof ButtonNavigatorModel) {
                Integer spacerInPixels3 = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
                int iIntValue3 = spacerInPixels3 != null ? spacerInPixels3.intValue() : 0;
                SectionWidget.setMargins$default(view, null, Integer.valueOf(iIntValue3), null, Integer.valueOf(iIntValue3), null, 21, null);
                return;
            }
            return;
        }
        if (tileType == 6) {
            Integer spacerInPixels4 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            int iIntValue4 = spacerInPixels4 == null ? 0 : spacerInPixels4.intValue();
            Integer spacerInPixels5 = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
            SectionWidget.setMargins$default(view, 0, Integer.valueOf(spacerInPixels5 == null ? 0 : spacerInPixels5.intValue()), 0, Integer.valueOf(iIntValue4), null, 16, null);
            if (!displayingHeaderText(model)) {
                iIntValue4 = 0;
            }
            SectionWidget.setContentMargins$default(view, 0, Integer.valueOf(iIntValue4), 0, null, 8, null);
            GridSize gridSize = getStyleSheet().getGridSize(this.context);
            if (gridSize == null) {
                return;
            }
            RecyclerView recyclerView = view.getRecyclerView();
            if (recyclerView != null) {
                recyclerView.setClipToPadding(false);
            }
            RecyclerView recyclerView2 = view.getRecyclerView();
            if (recyclerView2 == null) {
                return;
            }
            recyclerView2.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
            return;
        }
        if (tileType != 8) {
            switch (tileType) {
                case 13:
                    Integer spacerInPixels6 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
                    int iIntValue5 = spacerInPixels6 == null ? 0 : spacerInPixels6.intValue();
                    Integer spacerInPixels7 = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
                    int iIntValue6 = spacerInPixels7 == null ? 0 : spacerInPixels7.intValue();
                    SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue5 : 0), 7, null);
                    if (!displayingMoreButton(model)) {
                        iIntValue5 = 0;
                    }
                    SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(iIntValue5), 7, null);
                    if (!displayingHeaderText(model)) {
                        iIntValue6 = 0;
                    }
                    SectionWidget.setMargins$default(view, null, Integer.valueOf(iIntValue6), null, 0, null, 21, null);
                    break;
                case 14:
                    Integer spacerInPixels8 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
                    int iIntValue7 = spacerInPixels8 == null ? 0 : spacerInPixels8.intValue();
                    Integer spacerInPixels9 = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
                    int iIntValue8 = spacerInPixels9 == null ? 0 : spacerInPixels9.intValue();
                    SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue7 : 0), 7, null);
                    if (!displayingMoreButton(model)) {
                        iIntValue7 = 0;
                    }
                    SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(iIntValue7), 7, null);
                    SectionWidget.setMargins$default(view, null, Integer.valueOf(iIntValue8), null, 0, null, 21, null);
                    break;
                case 15:
                    Integer spacerInPixels10 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
                    int iIntValue9 = spacerInPixels10 == null ? 0 : spacerInPixels10.intValue();
                    SectionWidget.setMargins$default(view, 0, 0, 0, 0, null, 16, null);
                    SectionWidget.setContentMargins$default(view, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue9 : 0), null, null, 13, null);
                    break;
            }
            return;
        }
        Integer spacerInPixels11 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue10 = spacerInPixels11 == null ? 0 : spacerInPixels11.intValue();
        Integer spacerInPixels12 = getStyleSheet().getSpacerInPixels(SpacerKey.BASE);
        int iIntValue11 = spacerInPixels12 == null ? 0 : spacerInPixels12.intValue();
        if (!displayingHeaderText(model)) {
            iIntValue11 = 0;
        }
        SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(iIntValue11), 7, null);
        SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(displayingMoreButton(model) ? iIntValue10 : 0), 7, null);
    }

    private final boolean displayingHeaderText(SectionGridViewModel model) {
        return (TextUtils.isEmpty(model.getTitle()) && TextUtils.isEmpty(model.getSubtitle())) ? false : true;
    }

    private final boolean displayingMoreButton(SectionGridViewModel model) {
        return (TextUtils.isEmpty(model.getMoreText()) || TextUtils.isEmpty(model.getMoreTarget())) ? false : true;
    }

    private final void bindModels(SectionWidget view, List<? extends BaseViewModel> models) {
        for (BaseViewModel baseViewModel : models) {
            if (baseViewModel instanceof PillCellModel) {
                ((PillCellModel) baseViewModel).setClearableContainer(new WeakReference<>(view));
            }
        }
        UniversalAdapter adapter = view.getAdapter();
        if (adapter != null) {
            this.adapterProvider.updateItems(adapter, models);
            return;
        }
        List<UniversalBinder<? extends View, ? extends BaseViewModel>> binders = getBinders();
        if (binders == null) {
            return;
        }
        BaseViewModelAdapterProvider baseViewModelAdapterProvider = this.adapterProvider;
        Object[] array = binders.toArray(new UniversalBinder[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        UniversalBinder[] universalBinderArr = (UniversalBinder[]) array;
        view.setAdapter(baseViewModelAdapterProvider.getAdapter(models, (UniversalBinder[]) Arrays.copyOf(universalBinderArr, universalBinderArr.length)));
    }

    private final void reportContentViewWithContentInfo(Map<String, String> contentInfo, int numItemsAccessible) {
        String str = contentInfo.get(this.KEY_VIEW_REFERENCE_ID);
        Map<String, String> mutableMap = MapsKt__MapsKt.toMutableMap(contentInfo);
        mutableMap.put("firstViewableIndex", Integer.toString(0));
        mutableMap.put("lastViewableIndex", Integer.toString(numItemsAccessible - 1));
        mutableMap.put("numItemsAccessible", Integer.toString(numItemsAccessible));
        getContentViewedListener().onItemRendered(str, mutableMap);
    }

    private final void reportContentViewWithBlockRef(String identifier, int numItemsAccessible) {
        HashMap map = new HashMap();
        map.put("blockRef", identifier);
        String string = Integer.toString(0);
        Intrinsics.checkNotNullExpressionValue(string, "toString(0)");
        map.put("firstViewableIndex", string);
        String string2 = Integer.toString(numItemsAccessible - 1);
        Intrinsics.checkNotNullExpressionValue(string2, "toString(numItemsAccessible - 1)");
        map.put("lastViewableIndex", string2);
        String string3 = Integer.toString(numItemsAccessible);
        Intrinsics.checkNotNullExpressionValue(string3, "toString(numItemsAccessible)");
        map.put("numItemsAccessible", string3);
        getContentViewedListener().onItemRendered(identifier, map);
    }

    private final void displayArtwork(SectionWidget view, SectionGridViewModel model) {
        TransparentGradientImageView artworkFrameLayout = view.getArtworkFrameLayout();
        float fraction = artworkFrameLayout.getContext().getResources().getFraction(R$fraction.aspect_ratio_1_1, 1, 1);
        int dimension = (int) view.getContext().getResources().getDimension(R$dimen.artwork_dimen_compact);
        LayoutParamUtils.INSTANCE.setLayoutMargins(artworkFrameLayout, (int) (dimension * fraction), dimension, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        ArtworkFrameModel containerImage = model.getContainerImage();
        String imageUrl = containerImage == null ? null : containerImage.getImageUrl();
        ImageLoader.ImageLoaderCallback bitmapLoadedCallback = getBitmapLoadedCallback(view);
        ImageLoader imageLoader = this.imageLoader;
        if (imageLoader == null) {
            return;
        }
        imageLoader.loadImage(imageUrl, bitmapLoadedCallback);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.SectionBinder$getBitmapLoadedCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: SectionBinder.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/music/views/library/binders/SectionBinder$getBitmapLoadedCallback$1", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "onBitmapLoaded", "", "bitmap", "Landroid/graphics/Bitmap;", "onPrepareLoad", "placeHolderDrawable", "Landroid/graphics/drawable/Drawable;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements ImageLoader.ImageLoaderCallback {
        final /* synthetic */ TransparentGradientImageView $artworkFrameView;
        final /* synthetic */ SectionWidget $view;
        final /* synthetic */ SectionBinder this$0;

        @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }

        AnonymousClass1(SectionWidget sectionWidget, TransparentGradientImageView transparentGradientImageView, SectionBinder sectionBinder) {
            this.$view = sectionWidget;
            this.$artworkFrameView = transparentGradientImageView;
            this.this$0 = sectionBinder;
        }

        @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
        public void onBitmapLoaded(final Bitmap bitmap) {
            final SectionWidget sectionWidget = this.$view;
            final TransparentGradientImageView transparentGradientImageView = this.$artworkFrameView;
            final SectionBinder sectionBinder = this.this$0;
            sectionWidget.post(new Runnable() { // from class: com.amazon.music.views.library.binders.SectionBinder$getBitmapLoadedCallback$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SectionBinder.AnonymousClass1.m2361onBitmapLoaded$lambda4(transparentGradientImageView, bitmap, sectionWidget, sectionBinder);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onBitmapLoaded$lambda-4, reason: not valid java name */
        public static final void m2361onBitmapLoaded$lambda4(TransparentGradientImageView artworkFrameView, Bitmap bitmap, SectionWidget view, SectionBinder this$0) {
            Integer color;
            Intrinsics.checkNotNullParameter(artworkFrameView, "$artworkFrameView");
            Intrinsics.checkNotNullParameter(view, "$view");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            int height = artworkFrameView.getHeight();
            int width = artworkFrameView.getWidth();
            if (bitmap == null || (color = view.getStyleSheet().getColor(ColorKey.COLOR_PRIMARY)) == null) {
                return;
            }
            int iIntValue = color.intValue();
            int[] iArr = {iIntValue, iIntValue};
            float[] fArr = {0.0f, 1.0f};
            Integer color2 = this$0.getStyleSheet().getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_2);
            Bitmap colorOverlayBitmap = color2 == null ? null : BitmapEffectUtils.INSTANCE.getColorOverlayBitmap(bitmap, color2.intValue());
            TransparentGradientImageView.applyTransparentGradientToLeft$default(view.getArtworkFrameLayout(), colorOverlayBitmap == null ? bitmap : colorOverlayBitmap, fArr, iArr, width, height, false, 32, null);
            view.getArtworkFrameLayout().invalidate();
            Integer cornerSize = this$0.getStyleSheet().getCornerSize(CornerSizeKey.SMALL);
            if (cornerSize == null) {
                return;
            }
            view.getArtworkFrameLayout().setCornerRadius(cornerSize.intValue());
        }
    }

    public final ImageLoader.ImageLoaderCallback getBitmapLoadedCallback(SectionWidget view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return new AnonymousClass1(view, view.getArtworkFrameLayout(), this);
    }

    private final Set<Integer> getFullGridSpanPositions(List<? extends BaseViewModel> models) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        for (Object obj : models) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            if ((baseViewModel instanceof SectionGridViewModel) && ((SectionGridViewModel) baseViewModel).getTileType() == 19) {
                linkedHashSet.add(Integer.valueOf(i));
            }
            i = i2;
        }
        if (!linkedHashSet.isEmpty()) {
            return linkedHashSet;
        }
        return null;
    }

    private final Map<Integer, Integer> getFullGridSpanPositionToCustomColumnCountMap(List<? extends BaseViewModel> models, Set<Integer> fullGridSpanPositions) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Integer> it = fullGridSpanPositions.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (iIntValue < models.size()) {
                BaseViewModel baseViewModel = models.get(iIntValue);
                if ((baseViewModel instanceof SectionGridViewModel) && ((SectionGridViewModel) baseViewModel).getTileType() == 19) {
                    linkedHashMap.put(Integer.valueOf(iIntValue), 1);
                }
            }
        }
        if (!linkedHashMap.isEmpty()) {
            return linkedHashMap;
        }
        return null;
    }
}
