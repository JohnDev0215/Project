package com.android.car.ui.recyclerview.decorations.grid;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    private final Drawable mHorizontalDivider;
    private int mNumColumns;
    private final Drawable mVerticalDivider;

    public GridDividerItemDecoration(Drawable horizontalDivider, Drawable verticalDivider, int numColumns) {
        this.mHorizontalDivider = horizontalDivider;
        this.mVerticalDivider = verticalDivider;
        this.mNumColumns = numColumns;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        drawVerticalDividers(canvas, parent);
        drawHorizontalDividers(canvas, parent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, this.mHorizontalDivider.getIntrinsicWidth(), this.mHorizontalDivider.getIntrinsicHeight());
    }

    private void drawHorizontalDividers(Canvas canvas, RecyclerView parent) {
        int lastRowChildIndex;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        int childCount = layoutManager2.getChildCount();
        int i = this.mNumColumns;
        int rowCount = childCount / i;
        int lastRowChildCount = childCount % i;
        int lastColumn = Math.min(childCount, i);
        for (int i2 = 1; i2 < lastColumn; i2++) {
            if (i2 >= lastRowChildCount) {
                int lastRowChildIndex2 = rowCount - 1;
                lastRowChildIndex = lastRowChildIndex2 * this.mNumColumns;
            } else {
                lastRowChildIndex = this.mNumColumns * rowCount;
            }
            View firstRowChild = layoutManager2.getChildAt(i2);
            View lastRowChild = layoutManager2.getChildAt(lastRowChildIndex + i2);
            int dividerTop = firstRowChild.getTop() + ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_top_margin));
            int dividerRight = firstRowChild.getLeft();
            int dividerLeft = dividerRight - this.mHorizontalDivider.getIntrinsicWidth();
            int dividerBottom = lastRowChild.getBottom() - ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_bottom_margin));
            this.mHorizontalDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            this.mHorizontalDivider.draw(canvas);
        }
    }

    public void setNumOfColumns(int numberOfColumns) {
        this.mNumColumns = numberOfColumns;
    }

    private void drawVerticalDividers(Canvas canvas, RecyclerView parent) {
        int rightmostChildIndex;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        RecyclerView.LayoutManager layoutManager2 = layoutManager;
        double childCount = layoutManager2.getChildCount();
        double rowCount = Math.ceil(childCount / ((double) this.mNumColumns));
        for (int i = 1; i <= rowCount; i++) {
            if (i != 1) {
                if (i == rowCount) {
                    rightmostChildIndex = ((i - 1) * this.mNumColumns) - 1;
                } else {
                    int rightmostChildIndex2 = this.mNumColumns;
                    rightmostChildIndex = (rightmostChildIndex2 * i) - 1;
                }
                View leftmostChild = layoutManager2.getChildAt(this.mNumColumns * (i - 1));
                View rightmostChild = layoutManager2.getChildAt(rightmostChildIndex);
                int dividerLeft = leftmostChild.getLeft() + ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_start_margin));
                int dividerBottom = leftmostChild.getTop();
                int dividerTop = dividerBottom - this.mVerticalDivider.getIntrinsicHeight();
                int dividerRight = rightmostChild.getRight() - ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_end_margin));
                this.mVerticalDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                this.mVerticalDivider.draw(canvas);
            }
        }
    }
}
