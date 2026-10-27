package com.byd.automultipletheme.common.adapter;

import android.graphics.Rect;
import android.view.View;
import androidx.core.text.TextUtilsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.automultipletheme.R;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GridSpaceItemDecoration.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/byd/automultipletheme/common/adapter/GridSpaceItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "spanCount", "", "rowSpacing", "columnSpacing", "space", "(IIII)V", "mColumnSpacing", "mRowSpacing", "mSpace", "mSpanCount", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mColumnSpacing;
    private int mRowSpacing;
    private int mSpace;
    private int mSpanCount;

    public GridSpaceItemDecoration(int i, int i2, int i3, int i4) {
        this.mSpanCount = i;
        this.mRowSpacing = i2;
        this.mColumnSpacing = i3;
        this.mSpace = i4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(outRect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        int childAdapterPosition = parent.getChildAdapterPosition(view) % this.mSpanCount;
        if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            if (childAdapterPosition == 0) {
                int i = this.mSpace;
                if (i == 0) {
                    outRect.right = parent.getContext().getResources().getDimensionPixelSize(R.dimen.dp_40);
                } else {
                    outRect.right = i;
                }
            } else if (childAdapterPosition == 1) {
                outRect.right = this.mColumnSpacing / 2;
            }
        } else if (childAdapterPosition == 0) {
            int i2 = this.mSpace;
            if (i2 == 0) {
                outRect.left = parent.getContext().getResources().getDimensionPixelSize(R.dimen.dp_40);
            } else {
                outRect.left = i2;
            }
        } else if (childAdapterPosition == 1) {
            outRect.left = this.mColumnSpacing / 2;
        }
        outRect.bottom = this.mRowSpacing;
    }
}
