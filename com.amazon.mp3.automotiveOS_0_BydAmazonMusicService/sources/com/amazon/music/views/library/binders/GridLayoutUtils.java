package com.amazon.music.views.library.binders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.WindowManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$integer;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.recyclerview.CustomGridLayoutItemDecorator;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.utils.GridLayoutUtil;
import com.amazon.ui.foundations.utils.ScreenUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GridLayoutUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u00002\u00020\u0001:\u0001?B\u0017\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b=\u0010>J\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002JT\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000bH\u0002Ji\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u001f\u0010\u001d\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010(R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010*R\u0016\u0010,\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010-R\u0016\u0010/\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010-R\u0016\u00100\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010-R\u0016\u00101\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010-R\u0016\u00102\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010-R\u0016\u00103\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u0010-R\u0016\u00104\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u0010-R\u0016\u00105\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u0010-R\u0016\u00106\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u0010-R\u0016\u00107\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u0010-R\u0016\u00108\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u0010-R\u0014\u00109\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010*R\u0014\u0010:\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010*R\u0014\u0010;\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010*R\u0014\u0010<\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010*¨\u0006@"}, d2 = {"Lcom/amazon/music/views/library/binders/GridLayoutUtils;", "", "Lcom/amazon/ui/foundations/styles/GridStyle;", "gridStyle", "", "tileLayoutType", "getParentWidth", "Lcom/amazon/ui/foundations/styles/GridSize;", "gridSize", "", "fullGridSpanPositions", "", "fullGridSpanPositionToCustomColumnCountMap", "getFullGridSpanPositionToRightOffsetMap", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "itemCount", "", "shouldShowDivider", "useCustomLinearLayoutManager", "fullGridSpanList", "", "setLayoutManager", "(Landroidx/recyclerview/widget/RecyclerView;ILjava/lang/Integer;ZZLjava/util/Set;Ljava/util/Map;)V", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "childModel", "setSingleListLayoutManager", "getMaxItems", "getShovelerItemWidth", "shouldDisplayPeek", "(Ljava/lang/Integer;I)Z", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/view/WindowManager;", "windowManager", "Landroid/view/WindowManager;", "Lcom/amazon/ui/foundations/styles/GridSize;", "columnSpacing", "I", "smallOffset", "verticalGridStyle", "Lcom/amazon/ui/foundations/styles/GridStyle;", "vertical16X9GridStyle", "horizontalGridStyle", "featuredGridStyle", "listItemGridStyle", "buttonGridStyle", "thumbnailGridStyle", "compactVerticalGridStyle", "descriptiveListingGridStyle", "horizontalItemSectionNestedGrid", "carModeGridStyle", "largeVerticalGridStyle", "verticalRowSpacing", "horizontalRowSpacing", "compactArtworkSpacing", "featureBarkerColumnCount", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "CustomLinearLayoutManager", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class GridLayoutUtils {
    private final GridStyle buttonGridStyle;
    private final GridStyle carModeGridStyle;
    private final int columnSpacing;
    private final int compactArtworkSpacing;
    private final GridStyle compactVerticalGridStyle;
    private final Context context;
    private final GridStyle descriptiveListingGridStyle;
    private final int featureBarkerColumnCount;
    private final GridStyle featuredGridStyle;
    private final GridSize gridSize;
    private final GridStyle horizontalGridStyle;
    private final GridStyle horizontalItemSectionNestedGrid;
    private final int horizontalRowSpacing;
    private final GridStyle largeVerticalGridStyle;
    private final GridStyle listItemGridStyle;
    private final int smallOffset;
    private final StyleSheet styleSheet;
    private final GridStyle thumbnailGridStyle;
    private final GridStyle vertical16X9GridStyle;
    private final GridStyle verticalGridStyle;
    private final int verticalRowSpacing;
    private final WindowManager windowManager;

    @JvmOverloads
    @SuppressLint({"SwitchIntDef"})
    public final void setLayoutManager(RecyclerView recyclerView, int i, Integer num) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        setLayoutManager$default(this, recyclerView, i, num, false, false, null, null, 120, null);
    }

    public GridLayoutUtils(Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.context = context;
        this.styleSheet = styleSheet;
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.windowManager = (WindowManager) systemService;
        GridSize gridSize = styleSheet.getGridSize(context);
        this.gridSize = gridSize;
        Integer numValueOf = gridSize == null ? null : Integer.valueOf(gridSize.getColumnSpace());
        this.columnSpacing = numValueOf == null ? context.getResources().getDimensionPixelOffset(R$dimen.gutter) : numValueOf.intValue();
        this.smallOffset = context.getResources().getDimensionPixelOffset(R$dimen.spacer_12);
        this.verticalGridStyle = styleSheet.getGridStyle(GridStyleKey.VERTICAL_ITEM_GRID, context);
        this.vertical16X9GridStyle = styleSheet.getGridStyle(GridStyleKey.VERTICAL_16X9_ITEM_GRID, context);
        this.horizontalGridStyle = styleSheet.getGridStyle(GridStyleKey.HORIZONTAL_ITEM_GRID, context);
        this.featuredGridStyle = styleSheet.getGridStyle(GridStyleKey.FEATURED_ITEM_GRID, context);
        this.listItemGridStyle = styleSheet.getGridStyle(GridStyleKey.LIST_ITEM_GRID, context);
        this.buttonGridStyle = styleSheet.getGridStyle(GridStyleKey.BUTTON_NAVIGATOR_GRID, context);
        this.thumbnailGridStyle = styleSheet.getGridStyle(GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, context);
        this.compactVerticalGridStyle = styleSheet.getGridStyle(GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, context);
        this.descriptiveListingGridStyle = styleSheet.getGridStyle(GridStyleKey.DESCRIPTIVE_LISTING_GRID, context);
        this.horizontalItemSectionNestedGrid = styleSheet.getGridStyle(GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, context);
        this.carModeGridStyle = styleSheet.getGridStyle(GridStyleKey.CAR_MODE_GRID, context);
        this.largeVerticalGridStyle = styleSheet.getGridStyle(GridStyleKey.LARGE_VERTICAL_ITEM_GRID, context);
        this.verticalRowSpacing = context.getResources().getDimensionPixelOffset(R$dimen.row_spacing);
        this.horizontalRowSpacing = context.getResources().getDimensionPixelOffset(R$dimen.horizontal_tile_row_spacing);
        this.compactArtworkSpacing = (int) context.getResources().getDimension(R$dimen.artwork_dimen_compact);
        this.featureBarkerColumnCount = context.getResources().getInteger(R$integer.feature_barker_grid_column);
    }

    /* JADX INFO: compiled from: GridLayoutUtils.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/binders/GridLayoutUtils$CustomLinearLayoutManager;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "supportsPredictiveItemAnimations", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class CustomLinearLayoutManager extends LinearLayoutManager {
        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CustomLinearLayoutManager(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void setLayoutManager$default(GridLayoutUtils gridLayoutUtils, RecyclerView recyclerView, int i, Integer num, boolean z, boolean z2, Set set, Map map, int i2, Object obj) {
        gridLayoutUtils.setLayoutManager(recyclerView, i, (i2 & 4) != 0 ? null : num, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? false : z2, (i2 & 32) != 0 ? null : set, (i2 & 64) != 0 ? null : map);
    }

    @JvmOverloads
    @SuppressLint({"SwitchIntDef"})
    public final void setLayoutManager(RecyclerView recyclerView, int tileLayoutType, Integer itemCount, boolean shouldShowDivider, boolean useCustomLinearLayoutManager, Set<Integer> fullGridSpanList, Map<Integer, Integer> fullGridSpanPositionToCustomColumnCountMap) {
        RecyclerView.LayoutManager linearLayoutManager;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        while (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecorationAt(0);
        }
        Drawable drawable = this.context.getDrawable(R$drawable.grid_divider);
        Intrinsics.checkNotNull(drawable);
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2);
        gradientDrawable.setColor(color == null ? 0 : color.intValue());
        switch (tileLayoutType) {
            case 0:
                Map<Integer, Integer> fullGridSpanPositionToRightOffsetMap = null;
                if (fullGridSpanList != null && fullGridSpanPositionToCustomColumnCountMap != null) {
                    fullGridSpanPositionToRightOffsetMap = getFullGridSpanPositionToRightOffsetMap(this.gridSize, this.horizontalGridStyle, tileLayoutType, fullGridSpanList, fullGridSpanPositionToCustomColumnCountMap);
                }
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.horizontalGridStyle, false, false, fullGridSpanList, fullGridSpanPositionToRightOffsetMap, 16, null);
                break;
            case 1:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.verticalGridStyle, false, false, fullGridSpanList, null, 80, null);
                break;
            case 2:
            default:
                if (useCustomLinearLayoutManager) {
                    Context context = recyclerView.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "recyclerView.context");
                    linearLayoutManager = new CustomLinearLayoutManager(context);
                } else {
                    linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
                }
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case 3:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.buttonGridStyle, false, false, null, null, 112, null);
                break;
            case 4:
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), this.featureBarkerColumnCount));
                int i = this.columnSpacing;
                recyclerView.addItemDecoration(new CustomGridLayoutItemDecorator(i / 2, this.horizontalRowSpacing, i / 2, 0));
                break;
            case 5:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.verticalGridStyle, shouldDisplayPeek(itemCount, tileLayoutType), false, null, null, 112, null);
                break;
            case 6:
                Integer spacerInDp = this.styleSheet.getSpacerInDp(SpacerKey.SMALL);
                int iIntValue = spacerInDp == null ? this.columnSpacing / 2 : spacerInDp.intValue();
                recyclerView.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
                recyclerView.addItemDecoration(new CustomGridLayoutItemDecorator(iIntValue, this.smallOffset, iIntValue, 0));
                break;
            case 7:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.thumbnailGridStyle, false, false, null, null, 112, null);
                break;
            case 8:
                GridLayoutUtil.applyGridLayoutWithDividers$default(recyclerView, this.gridSize, this.listItemGridStyle, false, gradientDrawable, false, 32, null);
                break;
            case 9:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.featuredGridStyle, shouldDisplayPeek(itemCount, tileLayoutType), false, null, null, 112, null);
                break;
            case 10:
            case 20:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.horizontalGridStyle, shouldDisplayPeek(itemCount, tileLayoutType), false, null, null, 112, null);
                break;
            case 11:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.vertical16X9GridStyle, shouldDisplayPeek(itemCount, tileLayoutType), false, null, null, 112, null);
                break;
            case 12:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.vertical16X9GridStyle, false, false, fullGridSpanList, null, 80, null);
                break;
            case 13:
                if (shouldShowDivider) {
                    GridLayoutUtil.applyGridLayoutWithDividers(recyclerView, this.gridSize, this.horizontalGridStyle, false, gradientDrawable, true);
                } else {
                    GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.horizontalGridStyle, false, false, fullGridSpanList, null, 80, null);
                }
                break;
            case 14:
                GridLayoutUtil.applyGridLayoutWithDividers(recyclerView, this.gridSize, this.horizontalGridStyle, false, gradientDrawable, true);
                break;
            case 15:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.thumbnailGridStyle, false, false, null, null, 112, null);
                break;
            case 16:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.thumbnailGridStyle, shouldDisplayPeek(itemCount, tileLayoutType), false, null, null, 112, null);
                break;
            case 17:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.compactVerticalGridStyle, true, true, null, null, 96, null);
                break;
            case 18:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.descriptiveListingGridStyle, false, false, null, null, 112, null);
                break;
            case 19:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.horizontalItemSectionNestedGrid, false, false, null, null, 112, null);
                break;
            case 21:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.carModeGridStyle, false, false, fullGridSpanList, null, 80, null);
                break;
            case 22:
                GridLayoutUtil.applyGridLayout$default(recyclerView, this.gridSize, this.largeVerticalGridStyle, shouldDisplayPeek(itemCount, tileLayoutType), false, null, null, 112, null);
                break;
        }
    }

    public final void setSingleListLayoutManager(RecyclerView recyclerView, BaseViewModel childModel) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(childModel, "childModel");
        if (childModel instanceof HorizontalTileModel) {
            GridSize gridSize = this.gridSize;
            GridStyle gridStyle = this.horizontalGridStyle;
            GridLayoutUtil.applyGridLayout$default(recyclerView, gridSize, gridStyle == null ? null : GridStyle.copy$default(gridStyle, null, 0, null, 0, 1, null, null, 111, null), false, false, null, null, 112, null);
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }

    public final int getMaxItems(int tileLayoutType) {
        if (tileLayoutType == 0) {
            GridStyle gridStyle = this.styleSheet.getGridStyle(GridStyleKey.HORIZONTAL_ITEM_GRID, this.context);
            Intrinsics.checkNotNull(gridStyle);
            return GridLayoutUtil.getMaxItems(gridStyle, false, false);
        }
        if (tileLayoutType == 1) {
            GridStyle gridStyle2 = this.styleSheet.getGridStyle(GridStyleKey.VERTICAL_ITEM_GRID, this.context);
            Intrinsics.checkNotNull(gridStyle2);
            return GridLayoutUtil.getMaxItems(gridStyle2, false, false);
        }
        if (tileLayoutType != 15) {
            return Integer.MAX_VALUE;
        }
        GridStyle gridStyle3 = this.styleSheet.getGridStyle(GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, this.context);
        Intrinsics.checkNotNull(gridStyle3);
        return GridLayoutUtil.getMaxItems(gridStyle3, false, false);
    }

    public final int getShovelerItemWidth(int itemCount, int tileLayoutType) {
        GridStyle gridStyle;
        GridSize gridSize = this.gridSize;
        if (gridSize != null) {
            if (tileLayoutType == 5) {
                gridStyle = this.verticalGridStyle;
            } else if (tileLayoutType == 20) {
                gridStyle = this.horizontalGridStyle;
            } else if (tileLayoutType == 22) {
                gridStyle = this.largeVerticalGridStyle;
            } else if (tileLayoutType == 16) {
                gridStyle = this.thumbnailGridStyle;
            } else if (tileLayoutType != 17) {
                switch (tileLayoutType) {
                    case 9:
                        gridStyle = this.featuredGridStyle;
                        break;
                    case 10:
                        gridStyle = this.horizontalGridStyle;
                        break;
                    case 11:
                        gridStyle = this.vertical16X9GridStyle;
                        break;
                    default:
                        gridStyle = null;
                        break;
                }
            } else {
                gridStyle = this.compactVerticalGridStyle;
            }
            GridStyle gridStyle2 = gridStyle;
            if (gridStyle2 != null) {
                return GridLayoutUtil.getItemWidth(itemCount, getParentWidth(gridStyle2, tileLayoutType) - (20 == tileLayoutType ? this.compactArtworkSpacing : 0), gridSize, gridStyle2, true, tileLayoutType == 17);
            }
        }
        return 0;
    }

    public final boolean shouldDisplayPeek(Integer itemCount, int tileLayoutType) {
        GridStyle gridStyle;
        if (tileLayoutType == 5) {
            gridStyle = this.verticalGridStyle;
        } else if (tileLayoutType == 20) {
            gridStyle = this.horizontalGridStyle;
        } else if (tileLayoutType == 22) {
            gridStyle = this.largeVerticalGridStyle;
        } else if (tileLayoutType == 16) {
            gridStyle = this.thumbnailGridStyle;
        } else if (tileLayoutType != 17) {
            switch (tileLayoutType) {
                case 9:
                    gridStyle = this.featuredGridStyle;
                    break;
                case 10:
                    gridStyle = this.horizontalGridStyle;
                    break;
                case 11:
                    gridStyle = this.vertical16X9GridStyle;
                    break;
                default:
                    gridStyle = null;
                    break;
            }
        } else {
            gridStyle = this.compactVerticalGridStyle;
        }
        if (gridStyle == null) {
            return false;
        }
        if (itemCount != null) {
            return GridLayoutUtil.INSTANCE.shouldDisplayPeek(itemCount.intValue(), gridStyle);
        }
        return true;
    }

    private final int getParentWidth(GridStyle gridStyle, int tileLayoutType) {
        int marginSpace;
        int screenWidthPixels = ScreenUtil.INSTANCE.getScreenWidthPixels(this.windowManager);
        if (tileLayoutType == 5 || tileLayoutType == 20 || tileLayoutType == 9 || tileLayoutType == 10 || tileLayoutType == 16 || tileLayoutType == 17) {
            marginSpace = gridStyle.getGridSize().getMarginSpace();
        } else {
            marginSpace = gridStyle.getGridSize().getMarginSpace() * 2;
        }
        return screenWidthPixels - marginSpace;
    }

    private final Map<Integer, Integer> getFullGridSpanPositionToRightOffsetMap(GridSize gridSize, GridStyle gridStyle, int tileLayoutType, Set<Integer> fullGridSpanPositions, Map<Integer, Integer> fullGridSpanPositionToCustomColumnCountMap) {
        if (gridSize == null || gridStyle == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int parentWidth = getParentWidth(gridStyle, tileLayoutType);
        Iterator<Integer> it = fullGridSpanPositions.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            Integer num = fullGridSpanPositionToCustomColumnCountMap.get(Integer.valueOf(iIntValue));
            Integer rightOffsetForCustomColumnFullGridSpanItem = num == null ? null : GridLayoutUtil.getRightOffsetForCustomColumnFullGridSpanItem(gridSize, gridStyle, parentWidth, num.intValue());
            if (rightOffsetForCustomColumnFullGridSpanItem != null) {
                rightOffsetForCustomColumnFullGridSpanItem.intValue();
            }
        }
        if (!linkedHashMap.isEmpty()) {
            return linkedHashMap;
        }
        return null;
    }
}
