package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.DescriptiveListingElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.HorizontalItemAdapter;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.SectionWidget;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveListingBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00172\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0012\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/DescriptiveListingBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/DescriptiveListingElement;", "Lcom/amazon/music/views/library/views/SectionWidget;", "()V", "gridLayoutUtils", "Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "getGridLayoutUtils", "()Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "gridLayoutUtils$delegate", "Lkotlin/Lazy;", "bind", "", "element", "view", "bindChildren", "bindStyle", "createView", "parent", "Landroid/view/ViewGroup;", "displayingHeaderText", "", "reset", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveListingBinder extends ElementBinder<DescriptiveListingElement, SectionWidget> {
    private static final int tileLayoutType = 0;

    /* JADX INFO: renamed from: gridLayoutUtils$delegate, reason: from kotlin metadata */
    private final Lazy gridLayoutUtils = LazyKt__LazyJVMKt.lazy(new Function0<GridLayoutUtils>() { // from class: com.amazon.music.skyfire.ui.widgets.binders.DescriptiveListingBinder$gridLayoutUtils$2
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
    public void bind(DescriptiveListingElement element, SectionWidget view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        reset(view);
        GridLayoutUtils gridLayoutUtils = getGridLayoutUtils();
        RecyclerView recyclerView = view.getRecyclerView();
        Intrinsics.checkNotNull(recyclerView);
        GridLayoutUtils.setLayoutManager$default(gridLayoutUtils, recyclerView, 0, Integer.valueOf(element.items().size()), true, false, null, null, 96, null);
        bindStyle(view, element);
        bindChildren(view, element);
        String strPrimaryHeader = element.primaryHeader();
        if (strPrimaryHeader == null) {
            strPrimaryHeader = "";
        }
        view.setTitle(strPrimaryHeader);
        String strSecondaryHeader = element.secondaryHeader();
        view.setSubtitle(strSecondaryHeader != null ? strSecondaryHeader : "");
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    private final void reset(SectionWidget view) {
        view.setMoreButtonOnClickListener(null);
        view.setTitle("");
        view.setSubtitle("");
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        HorizontalItemAdapter horizontalItemAdapter = adapter instanceof HorizontalItemAdapter ? (HorizontalItemAdapter) adapter : null;
        if (horizontalItemAdapter != null) {
            horizontalItemAdapter.clear();
        }
        view.setInitialMargins();
    }

    private final void bindChildren(SectionWidget view, DescriptiveListingElement element) {
        HorizontalItemAdapter horizontalItemAdapter = new HorizontalItemAdapter(getBindingProvider());
        List<HorizontalItemElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        horizontalItemAdapter.addItems(listItems);
        horizontalItemAdapter.setOnEndOfList(element.onEndOfWidget());
        int size = element.items().size();
        if (getGridLayoutUtils().shouldDisplayPeek(Integer.valueOf(size), 0)) {
            horizontalItemAdapter.setTileWidth(getGridLayoutUtils().getShovelerItemWidth(size, 0));
        }
        RecyclerView recyclerView = view.getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.setAdapter(horizontalItemAdapter);
    }

    private final void bindStyle(SectionWidget view, DescriptiveListingElement element) {
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        SectionWidget.setTextMargins$default(view, null, null, null, Integer.valueOf(displayingHeaderText(element) ? spacerInPixels == null ? 0 : spacerInPixels.intValue() : 0), 7, null);
    }

    private final boolean displayingHeaderText(DescriptiveListingElement element) {
        String strPrimaryHeader = element.primaryHeader();
        if (strPrimaryHeader == null || strPrimaryHeader.length() == 0) {
            String strSecondaryHeader = element.secondaryHeader();
            if (strSecondaryHeader == null || strSecondaryHeader.length() == 0) {
                return false;
            }
        }
        return true;
    }
}
