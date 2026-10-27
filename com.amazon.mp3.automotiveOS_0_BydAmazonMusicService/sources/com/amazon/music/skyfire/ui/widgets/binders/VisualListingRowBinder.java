package com.amazon.music.skyfire.ui.widgets.binders;

import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.widgets.elements.VisualListingRowElement;
import com.amazon.music.skyfire.ui.widgets.views.VisualListingRowView;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.views.VerticalTileView;
import com.amazon.ui.foundations.ktx.ViewKt;
import com.amazon.ui.foundations.styles.GridStyle;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: VisualListingRowBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/VisualListingRowBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "Lcom/amazon/music/skyfire/ui/widgets/elements/VisualListingRowElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/VisualListingRowView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualListingRowBinder extends ElementBinder<VisualListingRowElement, VisualListingRowView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public VisualListingRowView createView(ViewGroup parent) {
        Integer columnSpacing;
        GridStyle gridStyle = getStyleSheet().getGridStyle(GridStyleKey.VERTICAL_ITEM_GRID, getContext());
        GridLayoutUtils gridLayoutUtils = new GridLayoutUtils(getContext(), getStyleSheet());
        int columnCount = gridStyle == null ? 0 : gridStyle.getColumnCount();
        int rowSpacing = gridStyle == null ? 0 : gridStyle.getRowSpacing();
        int iIntValue = (gridStyle == null || (columnSpacing = gridStyle.getColumnSpacing()) == null) ? 0 : columnSpacing.intValue();
        int shovelerItemWidth = gridLayoutUtils.getShovelerItemWidth(columnCount, 5);
        VisualListingRowView visualListingRowView = new VisualListingRowView(getContext());
        IntRange intRangeUntil = RangesKt___RangesKt.until(0, columnCount);
        ArrayList<VerticalTileView> arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            ((IntIterator) it).nextInt();
            VerticalTileView verticalTileView = new VerticalTileView(getContext(), getStyleSheet());
            ViewGroupKt.plusAssign(visualListingRowView, verticalTileView);
            arrayList.add(verticalTileView);
        }
        for (VerticalTileView verticalTileView2 : arrayList) {
            ViewGroup.LayoutParams layoutParams = verticalTileView2.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.width = shovelerItemWidth;
            int i = iIntValue / 2;
            layoutParams2.leftMargin = i;
            layoutParams2.rightMargin = i;
            int i2 = rowSpacing / 2;
            layoutParams2.topMargin = i2;
            layoutParams2.bottomMargin = i2;
            verticalTileView2.setLayoutParams(layoutParams2);
        }
        return visualListingRowView;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(VisualListingRowElement element, VisualListingRowView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        int i = 0;
        for (View view2 : ViewGroupKt.getChildren(view)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            View view3 = view2;
            VerticalItemElement verticalItemElement = (VerticalItemElement) CollectionsKt___CollectionsKt.getOrNull(element.getItems(), i);
            if (verticalItemElement != null) {
                getBindingProvider().bind(verticalItemElement, view3);
            }
            ViewKt.hideIf(view3, verticalItemElement == null);
            i = i2;
        }
    }
}
