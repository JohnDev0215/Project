package com.amazon.ui.foundations.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.views.CustomDividerGridLayoutItemDecorator;
import com.amazon.ui.foundations.views.CustomGridLayoutItemDecorator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GridLayoutUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J`\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012H\u0007J>\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\fH\u0007J:\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007J\"\u0010\u001a\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0007J/\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u001eJ \u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010 \u001a\u00020\fH\u0002JH\u0010!\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0012H\u0002J\u0016\u0010#\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n¨\u0006$"}, d2 = {"Lcom/amazon/ui/foundations/utils/GridLayoutUtil;", "", "()V", "applyGridLayout", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "gridSize", "Lcom/amazon/ui/foundations/styles/GridSize;", "gridStyle", "Lcom/amazon/ui/foundations/styles/GridStyle;", "scrollHorizontally", "", "isCompact", "fullGridSpanList", "", "", "fullGridSpanPositionToRightOffsetMap", "", "applyGridLayoutWithDividers", "divider", "Landroid/graphics/drawable/Drawable;", "shouldShowLastDivider", "getItemWidth", "itemCount", "totalWidth", "getMaxItems", "isList", "getRightOffsetForCustomColumnFullGridSpanItem", "columnCount", "(Lcom/amazon/ui/foundations/styles/GridSize;Lcom/amazon/ui/foundations/styles/GridStyle;II)Ljava/lang/Integer;", "getTotalWidthForItemSpacing", "usePeek", "setViewWithFullWidthSpan", "fullGridSpanPositions", "shouldDisplayPeek", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GridLayoutUtil {
    public static final GridLayoutUtil INSTANCE = new GridLayoutUtil();

    private GridLayoutUtil() {
    }

    public static /* synthetic */ void applyGridLayout$default(RecyclerView recyclerView, GridSize gridSize, GridStyle gridStyle, boolean z, boolean z2, Set set, Map map, int i, Object obj) {
        if ((i & 16) != 0) {
            z2 = false;
        }
        applyGridLayout(recyclerView, gridSize, gridStyle, z, z2, (i & 32) != 0 ? null : set, (i & 64) != 0 ? null : map);
    }

    @JvmStatic
    @JvmOverloads
    public static final void applyGridLayout(RecyclerView recyclerView, GridSize gridSize, GridStyle gridStyle, boolean scrollHorizontally, boolean isCompact, Set<Integer> fullGridSpanList, Map<Integer, Integer> fullGridSpanPositionToRightOffsetMap) {
        Integer horizontalScrollRowCount;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        int iIntValue = (gridStyle == null || (horizontalScrollRowCount = gridStyle.getHorizontalScrollRowCount()) == null) ? 1 : horizontalScrollRowCount.intValue();
        if (!scrollHorizontally) {
            iIntValue = gridStyle == null ? 1 : gridStyle.getColumnCount();
        }
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), isCompact ? 1 : iIntValue, !scrollHorizontally ? 1 : 0, false));
        Integer columnSpacing = gridStyle == null ? null : gridStyle.getColumnSpacing();
        int columnSpace = columnSpacing == null ? gridSize == null ? 0 : gridSize.getColumnSpace() : columnSpacing.intValue();
        if (fullGridSpanList != null) {
            INSTANCE.setViewWithFullWidthSpan(recyclerView, gridStyle, gridSize, fullGridSpanList, fullGridSpanPositionToRightOffsetMap);
        } else {
            int i = columnSpace / 2;
            recyclerView.addItemDecoration(new CustomGridLayoutItemDecorator(i, gridStyle == null ? 0 : gridStyle.getRowSpacing(), i, 0));
        }
    }

    private final void setViewWithFullWidthSpan(RecyclerView recyclerView, final GridStyle gridStyle, GridSize gridSize, final Set<Integer> fullGridSpanPositions, final Map<Integer, Integer> fullGridSpanPositionToRightOffsetMap) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.GridLayoutManager");
        }
        ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.amazon.ui.foundations.utils.GridLayoutUtil.setViewWithFullWidthSpan.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                GridStyle gridStyle2;
                if (!fullGridSpanPositions.contains(Integer.valueOf(position)) || (gridStyle2 = gridStyle) == null) {
                    return 1;
                }
                return gridStyle2.getColumnCount();
            }
        });
        Integer columnSpacing = gridStyle == null ? null : gridStyle.getColumnSpacing();
        final int columnSpace = columnSpacing == null ? gridSize == null ? 0 : gridSize.getColumnSpace() : columnSpacing.intValue();
        final int rowSpacing = gridStyle != null ? gridStyle.getRowSpacing() : 0;
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.ui.foundations.utils.GridLayoutUtil$setViewWithFullWidthSpan$gridItemItemDecoration$1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                Intrinsics.checkNotNullParameter(outRect, "outRect");
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(state, "state");
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                Map<Integer, Integer> map = fullGridSpanPositionToRightOffsetMap;
                Integer num = map == null ? null : map.get(Integer.valueOf(childAdapterPosition));
                int iIntValue = num == null ? columnSpace / 2 : num.intValue();
                outRect.left = columnSpace / 2;
                outRect.right = iIntValue;
                outRect.bottom = 0;
                outRect.top = childAdapterPosition != 0 ? rowSpacing : 0;
            }
        });
    }

    public static /* synthetic */ void applyGridLayoutWithDividers$default(RecyclerView recyclerView, GridSize gridSize, GridStyle gridStyle, boolean z, Drawable drawable, boolean z2, int i, Object obj) {
        if ((i & 32) != 0) {
            z2 = false;
        }
        applyGridLayoutWithDividers(recyclerView, gridSize, gridStyle, z, drawable, z2);
    }

    @JvmStatic
    public static final void applyGridLayoutWithDividers(RecyclerView recyclerView, GridSize gridSize, GridStyle gridStyle, boolean scrollHorizontally, Drawable divider, boolean shouldShowLastDivider) {
        Integer horizontalScrollRowCount;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(divider, "divider");
        int iIntValue = (gridStyle == null || (horizontalScrollRowCount = gridStyle.getHorizontalScrollRowCount()) == null) ? 1 : horizontalScrollRowCount.intValue();
        if (!scrollHorizontally) {
            iIntValue = gridStyle == null ? 1 : gridStyle.getColumnCount();
        }
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), iIntValue, !scrollHorizontally ? 1 : 0, false));
        Integer columnSpacing = gridStyle == null ? null : gridStyle.getColumnSpacing();
        int columnSpace = (columnSpacing == null ? gridSize == null ? 0 : gridSize.getColumnSpace() : columnSpacing.intValue()) / 2;
        recyclerView.addItemDecoration(new CustomDividerGridLayoutItemDecorator(columnSpace, gridStyle == null ? 0 : gridStyle.getRowSpacing(), columnSpace, shouldShowLastDivider ? divider.getIntrinsicHeight() : 0, divider));
    }

    @JvmStatic
    public static final int getMaxItems(GridStyle gridStyle, boolean isList, boolean scrollHorizontally) {
        if (isList || scrollHorizontally || gridStyle == null) {
            return Integer.MAX_VALUE;
        }
        return gridStyle.getRowCount() * gridStyle.getColumnCount();
    }

    @JvmStatic
    @JvmOverloads
    public static final int getItemWidth(int itemCount, int totalWidth, GridSize gridSize, GridStyle gridStyle, boolean scrollHorizontally, boolean isCompact) {
        Integer peekSize;
        Intrinsics.checkNotNullParameter(gridSize, "gridSize");
        Intrinsics.checkNotNullParameter(gridStyle, "gridStyle");
        int columnCount = gridStyle.getColumnCount();
        boolean z = true;
        boolean z2 = scrollHorizontally && INSTANCE.shouldDisplayPeek(itemCount, gridStyle);
        GridLayoutUtil gridLayoutUtil = INSTANCE;
        if (!z2 && !isCompact) {
            z = false;
        }
        int totalWidthForItemSpacing = totalWidth - gridLayoutUtil.getTotalWidthForItemSpacing(gridSize, gridStyle, z);
        if ((z2 || isCompact) && (peekSize = gridStyle.getPeekSize()) != null) {
            totalWidthForItemSpacing -= peekSize.intValue();
        }
        return totalWidthForItemSpacing / columnCount;
    }

    public final boolean shouldDisplayPeek(int itemCount, GridStyle gridStyle) {
        Intrinsics.checkNotNullParameter(gridStyle, "gridStyle");
        Integer peekSize = gridStyle.getPeekSize();
        if (peekSize == null || peekSize.intValue() <= 0) {
            return false;
        }
        int columnCount = gridStyle.getColumnCount();
        Integer horizontalScrollRowCount = gridStyle.getHorizontalScrollRowCount();
        return itemCount > columnCount * (horizontalScrollRowCount == null ? gridStyle.getRowCount() : horizontalScrollRowCount.intValue());
    }

    @JvmStatic
    public static final Integer getRightOffsetForCustomColumnFullGridSpanItem(GridSize gridSize, GridStyle gridStyle, int totalWidth, int columnCount) {
        Intrinsics.checkNotNullParameter(gridSize, "gridSize");
        Intrinsics.checkNotNullParameter(gridStyle, "gridStyle");
        if (columnCount < 0 || columnCount >= gridStyle.getColumnCount()) {
            return null;
        }
        int itemWidth = getItemWidth(0, totalWidth, gridSize, gridStyle, false, false);
        Integer columnSpacing = gridStyle.getColumnSpacing();
        int columnSpace = columnSpacing == null ? gridSize.getColumnSpace() : columnSpacing.intValue();
        int i = columnSpace / 2;
        return Integer.valueOf((totalWidth - i) - ((columnCount * itemWidth) + (columnCount == 1 ? i : columnSpace * (columnCount - 1))));
    }

    private final int getTotalWidthForItemSpacing(GridSize gridSize, GridStyle gridStyle, boolean usePeek) {
        Integer columnSpacing = gridStyle == null ? null : gridStyle.getColumnSpacing();
        return (gridStyle.getColumnCount() + (usePeek ? 1 : 0)) * (columnSpacing == null ? gridSize == null ? 0 : gridSize.getColumnSpace() : columnSpacing.intValue());
    }
}
