package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.HorizontalItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedShovelerBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J(\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J\u0018\u0010\u001a\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/FeaturedShovelerBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/FeaturedHorizontalShovelerElement;", "Lcom/amazon/music/views/library/views/SectionWidget;", "()V", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "getGridLayoutUtils", "()Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "bindChildren", "bindStyle", "tileLayoutType", "", "itemCount", "createView", "parent", "Landroid/view/ViewGroup;", "displayingHeaderText", "", "reset", "showView", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedShovelerBinder extends ElementBinder<FeaturedHorizontalShovelerElement, SectionWidget> {
    private static final int tileLayoutType = 9;

    /* JADX INFO: renamed from: gridLayoutUtils$delegate, reason: from kotlin metadata */
    private final Lazy gridLayoutUtils = LazyKt__LazyJVMKt.lazy(new Function0<GridLayoutUtils>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.FeaturedShovelerBinder$gridLayoutUtils$2
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
    public void bind(FeaturedHorizontalShovelerElement element, SectionWidget view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        bindChildren(view, element);
        String strPrimaryHeader = element.primaryHeader();
        Intrinsics.checkNotNullExpressionValue(strPrimaryHeader, "element.primaryHeader()");
        view.setTitle(strPrimaryHeader);
        String strSecondaryHeader = element.secondaryHeader();
        if (strSecondaryHeader == null) {
            strSecondaryHeader = "";
        }
        view.setSubtitle(strSecondaryHeader);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
        showView(view, element);
    }

    private final void showView(SectionWidget view, FeaturedHorizontalShovelerElement element) {
        int size = element.items().size();
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(getGridLayoutUtils(), recyclerView, 9, Integer.valueOf(size), true, false, null, null, 96, null);
        }
        bindStyle(element, view, 9, size);
    }

    private final void bindStyle(FeaturedHorizontalShovelerElement element, SectionWidget view, int tileLayoutType2, int itemCount) {
        if (getGridLayoutUtils().shouldDisplayPeek(Integer.valueOf(itemCount), tileLayoutType2)) {
            SectionWidget.setMargins$default(view, 0, null, 0, null, null, 26, null);
            SectionWidget.setContentMargins$default(view, 0, null, 0, null, 10, null);
            GridSize gridSize = getStyleSheet().getGridSize(getContext());
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
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(element) ? spacerInPixels == null ? 0 : spacerInPixels.intValue() : 0), 7, null);
    }

    private final boolean displayingHeaderText(FeaturedHorizontalShovelerElement element) {
        return (TextUtils.isEmpty(element.primaryHeader()) && TextUtils.isEmpty(element.secondaryHeader())) ? false : true;
    }

    private final void reset(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        SectionWidget.setMoreButtonText$default(view, "", false, 2, null);
        view.setMoreButtonOnClickListener(null);
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        HorizontalItemAdapter horizontalItemAdapter = adapter instanceof HorizontalItemAdapter ? (HorizontalItemAdapter) adapter : null;
        if (horizontalItemAdapter != null) {
            horizontalItemAdapter.clear();
        }
        view.setInitialMargins();
        RecyclerView recyclerView2 = view.getRecyclerView();
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setPadding(0, 0, 0, 0);
    }

    private final void bindChildren(SectionWidget view, FeaturedHorizontalShovelerElement element) {
        HorizontalItemAdapter horizontalItemAdapter = new HorizontalItemAdapter(getBindingProvider());
        List<HorizontalItemElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        horizontalItemAdapter.addItems(listItems);
        int size = element.items().size();
        if (getGridLayoutUtils().shouldDisplayPeek(Integer.valueOf(size), 9)) {
            horizontalItemAdapter.setTileWidth(getGridLayoutUtils().getShovelerItemWidth(size, 9));
        }
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(horizontalItemAdapter);
    }
}
