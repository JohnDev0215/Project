package com.amazon.music.views.library.binders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.uicontentview.ContentViewedListener;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$fraction;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HotspotTooltipModel;
import com.amazon.music.views.library.models.RemoveButtonModel;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.VerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.music.views.library.views.TransparentGradientImageView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: ShovelerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 @2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0004:\u0001@B\u0093\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00122\u0010\u0017\u001a\u001a\u0012\u0016\b\u0001\u0012\u0012\u0012\u0006\b\u0001\u0012\u00020\u001a\u0012\u0006\b\u0001\u0012\u00020\u001b0\u00190\u0018\"\u0012\u0012\u0006\b\u0001\u0012\u00020\u001a\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0019¢\u0006\u0002\u0010\u001cJ\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0002H\u0016J&\u0010+\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001b0-2\u0006\u0010.\u001a\u00020/H\u0002J\u0018\u00100\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0002H\u0002J\u0010\u00101\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0003H\u0002J\u0010\u00102\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u00103\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0002H\u0002J\u0010\u00104\u001a\u00020/2\u0006\u0010*\u001a\u00020\u0002H\u0002J\u0010\u00105\u001a\u00020/2\u0006\u0010*\u001a\u00020\u0002H\u0002J \u00106\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u00022\u0006\u00107\u001a\u000208H\u0016J\u0018\u00109\u001a\u00020(2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0002J\u0018\u0010>\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0002H\u0002J\u0018\u0010?\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0002H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/amazon/music/views/library/binders/ShovelerBinder;", "Lcom/amazon/music/views/library/binders/BaseViewContainerBinder;", "Lcom/amazon/music/views/library/models/ShovelerModel;", "Lcom/amazon/music/views/library/views/SectionWidget;", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "seeMoreProvider", "Lcom/amazon/music/views/library/providers/SeeMoreProvider;", "adapterProvider", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "contentViewedListener", "Lcom/amazon/music/uicontentview/ContentViewedListener;", "artworkFrameBinder", "Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;", "hotspotTooltipBinder", "Lcom/amazon/music/views/library/binders/HotspotTooltipBinder;", "clickListenerFactory", "Lcom/amazon/music/views/library/click/ClickListenerFactory;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "binders", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Landroid/view/View;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/providers/SeeMoreProvider;Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;Lcom/amazon/music/uicontentview/ContentViewedListener;Lcom/amazon/music/views/library/binders/ArtworkFrameBinder;Lcom/amazon/music/views/library/binders/HotspotTooltipBinder;Lcom/amazon/music/views/library/click/ClickListenerFactory;Lcom/amazon/music/imageloader/ImageLoader;[Lcom/amazon/music/views/library/binders/UniversalBinder;)V", "getContentViewedListener", "()Lcom/amazon/music/uicontentview/ContentViewedListener;", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "playbackContainerBinder", "Lcom/amazon/music/views/library/binders/PlaybackContainerBinder;", "bind", "", "view", "model", "bindModels", "models", "", "showChildrenSubtitle", "", "bindStyle", "cleanWidget", "createView", "displayArtwork", "displayingHeaderText", "displayingMoreButton", "handleStateChange", "payload", "", "reportContentView", "identifier", "", "numItemsAccessible", "", "showHotspot", "showView", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShovelerBinder extends BaseViewContainerBinder<ShovelerModel, SectionWidget> {
    private static final String FOLLOW_ARTISTS_WIDGET_TAG = "follow-artists";
    private final BaseViewModelAdapterProvider adapterProvider;
    private final ArtworkFrameBinder artworkFrameBinder;
    private ClickListenerFactory clickListenerFactory;
    private final ContentViewedListener contentViewedListener;
    private Context context;
    private final GridLayoutUtils gridLayoutUtils;
    private final HotspotTooltipBinder hotspotTooltipBinder;
    private final ImageLoader imageLoader;
    private final PlaybackContainerBinder playbackContainerBinder;
    private final SeeMoreProvider seeMoreProvider;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<ShovelerModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(ShovelerModel.class);
    }

    public /* synthetic */ ShovelerBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, BaseViewModelAdapterProvider baseViewModelAdapterProvider, ContentViewedListener contentViewedListener, ArtworkFrameBinder artworkFrameBinder, HotspotTooltipBinder hotspotTooltipBinder, ClickListenerFactory clickListenerFactory, ImageLoader imageLoader, UniversalBinder[] universalBinderArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, seeMoreProvider, baseViewModelAdapterProvider, contentViewedListener, (i & 32) != 0 ? null : artworkFrameBinder, (i & 64) != 0 ? null : hotspotTooltipBinder, (i & 128) != 0 ? null : clickListenerFactory, (i & 256) != 0 ? null : imageLoader, universalBinderArr);
    }

    protected ContentViewedListener getContentViewedListener() {
        return this.contentViewedListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShovelerBinder(Context context, StyleSheet styleSheet, SeeMoreProvider seeMoreProvider, BaseViewModelAdapterProvider adapterProvider, ContentViewedListener contentViewedListener, ArtworkFrameBinder artworkFrameBinder, HotspotTooltipBinder hotspotTooltipBinder, ClickListenerFactory clickListenerFactory, ImageLoader imageLoader, UniversalBinder<? extends View, ? extends BaseViewModel>... binders) {
        super(styleSheet, contentViewedListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(adapterProvider, "adapterProvider");
        Intrinsics.checkNotNullParameter(contentViewedListener, "contentViewedListener");
        Intrinsics.checkNotNullParameter(binders, "binders");
        this.context = context;
        this.seeMoreProvider = seeMoreProvider;
        this.adapterProvider = adapterProvider;
        this.contentViewedListener = contentViewedListener;
        this.artworkFrameBinder = artworkFrameBinder;
        this.hotspotTooltipBinder = hotspotTooltipBinder;
        this.clickListenerFactory = clickListenerFactory;
        this.imageLoader = imageLoader;
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
    public void bind(SectionWidget view, final ShovelerModel model) {
        ClickListenerFactory clickListenerFactory;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        cleanWidget(view);
        if (model.getModels() != null) {
            if (!model.getModels().isEmpty()) {
                List<BaseViewModel> models = model.getModels();
                boolean z = model.getMoreText() != null;
                bindModels(view, models, model.getShowChildrenSubtitle());
                if (model.getTileType() == 20) {
                    displayArtwork(view, model);
                    ContentMetadata containerMetadata = model.getContainerMetadata();
                    if (containerMetadata != null && (clickListenerFactory = this.clickListenerFactory) != null) {
                        view.setPlaybackContainerHeaderOnClickListener(clickListenerFactory.create(containerMetadata));
                    }
                    this.playbackContainerBinder.bindPlaybackContainerStyle(view, model);
                } else {
                    String title = model.getTitle();
                    if (title != null) {
                        view.setTitle(title);
                    }
                    view.highlightTitle(model.getStringsToHighlight());
                    String subtitle = model.getSubtitle();
                    if (subtitle != null) {
                        view.setSubtitle(subtitle);
                    }
                }
                if (z && !TextUtils.isEmpty(model.getMoreTarget()) && this.seeMoreProvider != null) {
                    String moreText = model.getMoreText();
                    Intrinsics.checkNotNull(moreText);
                    view.setMoreButtonText(moreText, model.getShouldDisplayEmptyTextButton());
                    view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.ShovelerBinder$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            ShovelerBinder.m2362bind$lambda4(this.f$0, model, view2);
                        }
                    });
                }
                String viewReferenceId = model.getViewReferenceId();
                if (viewReferenceId != null) {
                    reportContentView(viewReferenceId, models.size());
                }
                List<String> tags = model.getTags();
                if (tags != null && tags.contains(FOLLOW_ARTISTS_WIDGET_TAG)) {
                    hideView(view);
                    return;
                } else {
                    showHotspot(view, model);
                    showView(view, model);
                    return;
                }
            }
        }
        hideView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-4, reason: not valid java name */
    public static final void m2362bind$lambda4(ShovelerBinder this$0, ShovelerModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.seeMoreProvider.onSeeMoreClick(model.getMoreTarget(), model.getViewReferenceId(), model.getNextPageToken());
    }

    private final void showView(SectionWidget view, ShovelerModel model) {
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(this.gridLayoutUtils, recyclerView, model.getTileType(), Integer.valueOf(model.getModels().size()), false, false, null, null, 120, null);
        }
        bindStyle(view, model);
        if (this.gridLayoutUtils.shouldDisplayPeek(Integer.valueOf(model.getModels().size()), model.getTileType()) || model.getTileType() == 17) {
            int shovelerItemWidth = this.gridLayoutUtils.getShovelerItemWidth(model.getModels().size(), model.getTileType());
            UniversalAdapter adapter = view.getAdapter();
            if (!(adapter instanceof UniversalAdapter)) {
                adapter = null;
            }
            if (adapter != null) {
                adapter.setTileViewWidth(shovelerItemWidth);
            }
        }
        view.setVisibility(0);
    }

    @Override // com.amazon.music.views.library.binders.BaseViewContainerBinder
    public void handleStateChange(SectionWidget view, ShovelerModel model, Object payload) {
        List<String> tags;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (!Intrinsics.areEqual(payload, (Object) 7) || (tags = model.getTags()) == null || !tags.contains(FOLLOW_ARTISTS_WIDGET_TAG) || view.getVisibility() == 0) {
            return;
        }
        showView(view, model);
    }

    private final void bindStyle(SectionWidget view, ShovelerModel model) {
        if (this.gridLayoutUtils.shouldDisplayPeek(Integer.valueOf(model.getModels().size()), model.getTileType())) {
            SectionWidget.setMargins$default(view, 0, null, 0, null, null, 26, null);
            SectionWidget.setContentMargins$default(view, 0, null, 0, null, 10, null);
            GridSize gridSize = getStyleSheet().getGridSize(this.context);
            if (gridSize != null) {
                SectionWidget.setTextMargins$default(view, Integer.valueOf(gridSize.getMarginSpace() + gridSize.getColumnSpace()), null, null, null, 14, null);
                RecyclerView recyclerView = view.getRecyclerView();
                if (recyclerView != null) {
                    recyclerView.setClipToPadding(false);
                }
                RecyclerView recyclerView2 = view.getRecyclerView();
                if (recyclerView2 != null) {
                    recyclerView2.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
                }
            }
        }
        int tileType = model.getTileType();
        if (tileType == 9) {
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue : 0), 7, null);
            SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(displayingMoreButton(model) ? iIntValue : 0), 7, null);
            return;
        }
        if (tileType == 10 || tileType == 20) {
            Integer spacerInPixels2 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
            SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(model) ? iIntValue2 : 0), 7, null);
            SectionWidget.setMoreButtonMargins$default(view, null, null, null, Integer.valueOf(displayingMoreButton(model) ? iIntValue2 : 0), 7, null);
        }
    }

    private final boolean displayingHeaderText(ShovelerModel model) {
        return (TextUtils.isEmpty(model.getTitle()) && TextUtils.isEmpty(model.getSubtitle())) ? false : true;
    }

    private final boolean displayingMoreButton(ShovelerModel model) {
        return (TextUtils.isEmpty(model.getMoreText()) || TextUtils.isEmpty(model.getMoreTarget())) ? false : true;
    }

    private final void cleanWidget(SectionWidget view) {
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
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            recyclerView.setPadding(0, 0, 0, 0);
        }
        view.showBackground(false);
        view.showImage(false);
        SectionWidget.setPlaybackContainerBodyMargins$default(view, null, null, null, null, 15, null);
        SectionWidget.setPlaybackContainerHeaderMargins$default(view, null, null, null, null, 15, null);
        view.highlightTitle(null);
        this.playbackContainerBinder.reset(view);
    }

    private final void bindModels(SectionWidget view, List<? extends BaseViewModel> models, boolean showChildrenSubtitle) {
        for (BaseViewModel baseViewModel : models) {
            if ((baseViewModel instanceof VerticalTileModel) && !showChildrenSubtitle) {
                ((VerticalTileModel) baseViewModel).setSubTitle(null);
            }
            if (baseViewModel instanceof RemoveButtonModel) {
                ((RemoveButtonModel) baseViewModel).setClearableContainer(new WeakReference<>(view));
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

    private final void reportContentView(String identifier, int numItemsAccessible) {
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

    private final void displayArtwork(SectionWidget view, ShovelerModel model) {
        TransparentGradientImageView artworkFrameLayout = view.getArtworkFrameLayout();
        float fraction = artworkFrameLayout.getContext().getResources().getFraction(R$fraction.aspect_ratio_1_1, 1, 1);
        int dimension = (int) view.getContext().getResources().getDimension(R$dimen.artwork_dimen_featured);
        LayoutParamUtils.INSTANCE.setLayoutMargins(artworkFrameLayout, (int) (dimension * fraction), dimension, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        ArtworkFrameModel image = model.getImage();
        String imageUrl = image == null ? null : image.getImageUrl();
        ImageLoader.ImageLoaderCallback gradientImageCallback = this.playbackContainerBinder.getGradientImageCallback(view);
        ImageLoader imageLoader = this.imageLoader;
        if (imageLoader == null) {
            return;
        }
        imageLoader.loadImage(imageUrl, gradientImageCallback);
    }

    private final void showHotspot(SectionWidget view, ShovelerModel model) {
        HotspotTooltipBinder hotspotTooltipBinder;
        HotspotTooltipModel hotspotTooltipModel = model.getHotspotTooltipModel();
        if (hotspotTooltipModel != null && (hotspotTooltipBinder = this.hotspotTooltipBinder) != null) {
            hotspotTooltipBinder.bind(view, hotspotTooltipModel, model);
        }
        view.showHotspot(model.getHotspotTooltipModel() != null);
    }
}
