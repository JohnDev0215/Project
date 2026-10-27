package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.InvertedRectangleVerticalItemElement;
import Touch.WidgetsInterface.v1_0.SeeMoreItemElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.AbstractVerticalItemAdapter;
import com.amazon.music.skyfire.ui.widgets.adapters.InvertedRectangleVerticalItemAdapter;
import com.amazon.music.skyfire.ui.widgets.adapters.VerticalItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualShovelerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J \u0010\u0010\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J0\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u0018\"\u0010\b\u0000\u0010\u001a*\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u00182\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J\u0018\u0010\u001f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/VisualShovelerBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/VisualShovelerElement;", "Lcom/amazon/music/views/library/views/SectionWidget;", "()V", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "getGridLayoutUtils", "()Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "bindChildren", "bindStyle", "tileLayoutType", "", "itemCount", "createView", "parent", "Landroid/view/ViewGroup;", "getAdapterAsPerElements", "Lcom/amazon/music/skyfire/ui/widgets/adapters/AbstractVerticalItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "T", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/VerticalItemElement;", "reset", "showView", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualShovelerBinder extends ElementBinder<VisualShovelerElement, SectionWidget> {

    /* JADX INFO: renamed from: gridLayoutUtils$delegate, reason: from kotlin metadata */
    private final Lazy gridLayoutUtils = LazyKt__LazyJVMKt.lazy(new Function0<GridLayoutUtils>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VisualShovelerBinder$gridLayoutUtils$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final GridLayoutUtils invoke() {
            return new GridLayoutUtils(this.this$0.getContext(), this.this$0.getStyleSheet());
        }
    });

    private final GridLayoutUtils getGridLayoutUtils() {
        return (GridLayoutUtils) this.gridLayoutUtils.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SectionWidget createView(ViewGroup parent) {
        SectionWidget sectionWidget = new SectionWidget(getContext(), getStyleSheet());
        sectionWidget.setContentView(new RecyclerView(getContext()));
        return sectionWidget;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(VisualShovelerElement element, SectionWidget view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        bindChildren(view, element);
        String strPrimaryHeader = element.primaryHeader();
        Intrinsics.checkNotNullExpressionValue(strPrimaryHeader, "element.primaryHeader()");
        view.setTitle(strPrimaryHeader);
        String strSecondaryHeader = element.secondaryHeader();
        if (strSecondaryHeader != null) {
            view.setSubtitle(strSecondaryHeader);
        }
        final SeeMoreItemElement seeMoreItemElementSeeMore = element.seeMore();
        if (seeMoreItemElementSeeMore != null) {
            SectionWidget.setMoreButtonText$default(view, seeMoreItemElementSeeMore.text(), false, 2, null);
            view.setMoreButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.binders.VisualShovelerBinder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    VisualShovelerBinder.m2251bind$lambda2$lambda1(this.f$0, seeMoreItemElementSeeMore, view2);
                }
            });
        }
        String strWidgetId = element.widgetId();
        if (strWidgetId != null) {
            view.setTag(strWidgetId);
        }
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
        showView(view, element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-2$lambda-1, reason: not valid java name */
    public static final void m2251bind$lambda2$lambda1(VisualShovelerBinder this$0, SeeMoreItemElement it, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        DispatcherContext dispatcherContext = this$0.getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        List<Method> listOnItemSelected = it.onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "it.onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
    }

    private final void showView(SectionWidget view, VisualShovelerElement element) {
        int i = element.isCompact() ? 17 : 5;
        int size = element.items().size();
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(getGridLayoutUtils(), recyclerView, i, Integer.valueOf(size), true, false, null, null, 96, null);
        }
        bindStyle(view, i, size);
    }

    private final void bindStyle(SectionWidget view, int tileLayoutType, int itemCount) {
        if (getGridLayoutUtils().shouldDisplayPeek(Integer.valueOf(itemCount), tileLayoutType)) {
            SectionWidget.setMargins$default(view, 0, null, 0, null, null, 26, null);
            SectionWidget.setContentMargins$default(view, 0, null, 0, null, 10, null);
            GridSize gridSize = getStyleSheet().getGridSize(getContext());
            if (gridSize == null) {
                return;
            }
            SectionWidget.setTextMargins$default(view, Integer.valueOf(gridSize.getMarginSpace() + gridSize.getColumnSpace()), null, null, null, 14, null);
            RecyclerView recyclerView = view.getRecyclerView();
            if (recyclerView != null) {
                recyclerView.setClipToPadding(false);
            }
            RecyclerView recyclerView2 = view.getRecyclerView();
            if (recyclerView2 == null) {
                return;
            }
            recyclerView2.setPadding(gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2), 0, 0, 0);
        }
    }

    private final void reset(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        VerticalItemAdapter verticalItemAdapter = adapter instanceof VerticalItemAdapter ? (VerticalItemAdapter) adapter : null;
        if (verticalItemAdapter != null) {
            verticalItemAdapter.clear();
        }
        view.setInitialMargins();
        RecyclerView recyclerView2 = view.getRecyclerView();
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setPadding(0, 0, 0, 0);
    }

    private final void bindChildren(SectionWidget view, VisualShovelerElement element) {
        List<VerticalItemElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        AbstractVerticalItemAdapter<? extends RecyclerView.ViewHolder> adapterAsPerElements = getAdapterAsPerElements(listItems);
        List<VerticalItemElement> listItems2 = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems2, "element.items()");
        adapterAsPerElements.addItems(listItems2);
        int i = element.isCompact() ? 17 : 5;
        int size = element.items().size();
        if (getGridLayoutUtils().shouldDisplayPeek(Integer.valueOf(size), i) || i == 17) {
            adapterAsPerElements.setTileWidth(getGridLayoutUtils().getShovelerItemWidth(size, i));
        }
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(adapterAsPerElements);
    }

    private final <T extends AbstractVerticalItemAdapter<? extends RecyclerView.ViewHolder>> AbstractVerticalItemAdapter<? extends RecyclerView.ViewHolder> getAdapterAsPerElements(List<VerticalItemElement> items) {
        if (items.size() == 0 || !(items.get(0) instanceof InvertedRectangleVerticalItemElement)) {
            return new VerticalItemAdapter(getOwnerId(), getMethodsDispatcher(), getBindingProvider());
        }
        return new InvertedRectangleVerticalItemAdapter(getOwnerId(), getMethodsDispatcher(), getBindingProvider());
    }
}
