package com.android.car.ui.recyclerview.decorations.linear;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {
    private final Drawable mDivider;
    private int mOrientation;

    public LinearDividerItemDecoration(Drawable divider) {
        this.mDivider = divider;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int i = this.mOrientation;
        if (i == 0) {
            drawHorizontalDividers(canvas, parent);
        } else if (i == 1) {
            drawVerticalDividers(canvas, parent);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        this.mOrientation = orientation;
        if (orientation == 0) {
            outRect.left = this.mDivider.getIntrinsicWidth();
        } else if (orientation == 1) {
            outRect.top = this.mDivider.getIntrinsicHeight();
        }
    }

    private void drawHorizontalDividers(Canvas canvas, RecyclerView parent) {
        int parentTop = parent.getPaddingTop() + ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_top_margin));
        int parentBottom = (parent.getHeight() - parent.getPaddingBottom()) - ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_bottom_margin));
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = layoutManager.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int parentLeft = child.getRight() + params.rightMargin;
            int parentRight = this.mDivider.getIntrinsicWidth() + parentLeft;
            this.mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            this.mDivider.draw(canvas);
        }
    }

    private void drawVerticalDividers(Canvas canvas, RecyclerView parent) {
        int parentLeft = parent.getPaddingLeft() + ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_start_margin));
        int parentRight = (parent.getWidth() - parent.getPaddingRight()) - ((int) parent.getContext().getResources().getDimension(R.dimen.car_ui_recyclerview_divider_end_margin));
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = layoutManager.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int parentTop = child.getBottom() + params.bottomMargin;
            int parentBottom = this.mDivider.getIntrinsicHeight() + parentTop;
            this.mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
            this.mDivider.draw(canvas);
        }
    }
}
