package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.PillNavigatorItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.views.SectionWidget;
import com.amazon.ui.foundations.styles.GridSize;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: PillNavigatorBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0012\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/PillNavigatorBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetElement;", "Lcom/amazon/music/views/library/views/SectionWidget;", "()V", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "getGridLayoutUtils", "()Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "bindChildren", "bindStyle", "createView", "parent", "Landroid/view/ViewGroup;", "reset", "showView", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PillNavigatorBinder extends ElementBinder<PillNavigatorWidgetElement, SectionWidget> {

    /* JADX INFO: renamed from: gridLayoutUtils$delegate, reason: from kotlin metadata */
    private final Lazy gridLayoutUtils = LazyKt__LazyJVMKt.lazy(new Function0<GridLayoutUtils>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.PillNavigatorBinder$gridLayoutUtils$2
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
    public void bind(PillNavigatorWidgetElement element, SectionWidget view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        bindChildren(view, element);
        String strHeader = element.header();
        if (strHeader == null) {
            strHeader = "";
        }
        view.setTitle(strHeader);
        view.setSubtitle("");
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
        showView(view, element);
    }

    private final void showView(SectionWidget view, PillNavigatorWidgetElement element) {
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            GridLayoutUtils.setLayoutManager$default(getGridLayoutUtils(), recyclerView, 6, Integer.valueOf(element.items().size()), false, false, null, null, 96, null);
        }
        bindStyle(view, element);
    }

    private final void bindStyle(SectionWidget view, PillNavigatorWidgetElement element) {
        String strHeader = element.header();
        SectionWidget.setMargins$default(view, null, strHeader == null || StringsKt__StringsJVMKt.isBlank(strHeader) ? 0 : null, null, null, null, 29, null);
        SectionWidget.setContentMargins$default(view, 0, null, 0, null, 10, null);
        GridSize gridSize = getStyleSheet().getGridSize(getContext());
        if (gridSize == null) {
            return;
        }
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView != null) {
            recyclerView.setClipToPadding(false);
        }
        int marginSpace = gridSize.getMarginSpace() + (gridSize.getColumnSpace() / 2);
        RecyclerView recyclerView2 = view.getRecyclerView();
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setPadding(marginSpace, 0, marginSpace, 0);
    }

    private final void reset(SectionWidget view) {
        view.setTitle("");
        view.setSubtitle("");
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        PillNavigatorItemAdapter pillNavigatorItemAdapter = adapter instanceof PillNavigatorItemAdapter ? (PillNavigatorItemAdapter) adapter : null;
        if (pillNavigatorItemAdapter != null) {
            pillNavigatorItemAdapter.clear();
        }
        view.setInitialMargins();
        RecyclerView recyclerView2 = view.getRecyclerView();
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setPadding(0, 0, 0, 0);
    }

    private final void bindChildren(SectionWidget view, PillNavigatorWidgetElement element) {
        PillNavigatorItemAdapter pillNavigatorItemAdapter = new PillNavigatorItemAdapter(getOwnerId(), getMethodsDispatcher(), getBindingProvider());
        List<PillNavigatorWidgetItemElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        pillNavigatorItemAdapter.addItems(listItems);
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(pillNavigatorItemAdapter);
    }
}
