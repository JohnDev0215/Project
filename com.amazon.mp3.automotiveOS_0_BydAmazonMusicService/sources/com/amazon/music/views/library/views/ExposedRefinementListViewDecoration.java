package com.amazon.music.views.library.views;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.models.enums.RefinementListType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GridSize;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExposedRefinementListViewDecoration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/views/ExposedRefinementListViewDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "refinementListType", "Lcom/amazon/music/views/library/models/enums/RefinementListType;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/models/enums/RefinementListType;)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExposedRefinementListViewDecoration extends RecyclerView.ItemDecoration {
    private final RefinementListType refinementListType;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: ExposedRefinementListViewDecoration.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RefinementListType.values().length];
            iArr[RefinementListType.FILTER.ordinal()] = 1;
            iArr[RefinementListType.NAVIGATION.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ExposedRefinementListViewDecoration(StyleSheet styleSheet, RefinementListType refinementListType) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(refinementListType, "refinementListType");
        this.styleSheet = styleSheet;
        this.refinementListType = refinementListType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Integer spacerInPixels;
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        super.getItemOffsets(outRect, view, parent, state);
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        GridSize gridSize = this.styleSheet.getGridSize();
        Integer num = null;
        Integer numValueOf = gridSize == null ? null : Integer.valueOf(gridSize.getColumnSpace() + gridSize.getMarginSpace());
        int i = WhenMappings.$EnumSwitchMapping$0[this.refinementListType.ordinal()];
        if (i == 1) {
            spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MINI);
        }
        if (childAdapterPosition == 0) {
            spacerInPixels = numValueOf;
        }
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter != null) {
            num = adapter.getItemCount() - 1 == childAdapterPosition ? numValueOf : 0;
        }
        if (parent.getLayoutDirection() == 0) {
            outRect.set(spacerInPixels == null ? 0 : spacerInPixels.intValue(), 0, num == null ? 0 : num.intValue(), 0);
        } else {
            outRect.set(num == null ? 0 : num.intValue(), 0, spacerInPixels == null ? 0 : spacerInPixels.intValue(), 0);
        }
    }
}
