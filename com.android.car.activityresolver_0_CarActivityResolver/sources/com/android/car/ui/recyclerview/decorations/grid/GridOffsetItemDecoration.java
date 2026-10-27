package com.android.car.ui.recyclerview.decorations.grid;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class GridOffsetItemDecoration extends RecyclerView.ItemDecoration {
    private int mNumColumns;
    private Drawable mOffsetDrawable;
    private final int mOffsetPosition;
    private int mOffsetPx;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OffsetPosition {
        public static final int END = 1;
        public static final int START = 0;
    }

    public GridOffsetItemDecoration(int offsetPx, int numColumns, int offsetPosition) {
        this.mOffsetPx = offsetPx;
        this.mNumColumns = numColumns;
        this.mOffsetPosition = offsetPosition;
    }

    public GridOffsetItemDecoration(Drawable offsetDrawable, int numColumns, int offsetPosition) {
        this.mOffsetDrawable = offsetDrawable;
        this.mNumColumns = numColumns;
        this.mOffsetPosition = offsetPosition;
    }

    public void setNumOfColumns(int numberOfColumns) {
        this.mNumColumns = numberOfColumns;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        boolean childIsInBottomRow;
        super.getItemOffsets(outRect, view, parent, state);
        if (this.mOffsetPosition == 0) {
            childIsInBottomRow = parent.getChildAdapterPosition(view) < this.mNumColumns;
            boolean childIsInTopRow = childIsInBottomRow;
            if (childIsInTopRow) {
                int i = this.mOffsetPx;
                if (i > 0) {
                    outRect.top = i;
                    return;
                }
                Drawable drawable = this.mOffsetDrawable;
                if (drawable != null) {
                    outRect.top = drawable.getIntrinsicHeight();
                    return;
                }
                return;
            }
            return;
        }
        int childCount = state.getItemCount();
        int lastRowChildCount = getLastRowChildCount(childCount);
        childIsInBottomRow = parent.getChildAdapterPosition(view) >= childCount - lastRowChildCount;
        if (childIsInBottomRow) {
            int i2 = this.mOffsetPx;
            if (i2 > 0) {
                outRect.bottom = i2;
                return;
            }
            Drawable drawable2 = this.mOffsetDrawable;
            if (drawable2 != null) {
                outRect.bottom = drawable2.getIntrinsicHeight();
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (this.mOffsetDrawable == null) {
            return;
        }
        int parentLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();
        if (this.mOffsetPosition == 0) {
            int parentTop = parent.getPaddingTop();
            int offsetDrawableBottom = this.mOffsetDrawable.getIntrinsicHeight() + parentTop;
            this.mOffsetDrawable.setBounds(parentLeft, parentTop, parentRight, offsetDrawableBottom);
            this.mOffsetDrawable.draw(c);
            return;
        }
        int childCount = state.getItemCount();
        int lastRowChildCount = getLastRowChildCount(childCount);
        int offsetDrawableTop = 0;
        int offsetDrawableBottom2 = 0;
        for (int i = childCount - lastRowChildCount; i < childCount; i++) {
            View child = parent.getChildAt(i);
            offsetDrawableTop = child.getBottom();
            offsetDrawableBottom2 = offsetDrawableTop + this.mOffsetDrawable.getIntrinsicHeight();
        }
        this.mOffsetDrawable.setBounds(parentLeft, offsetDrawableTop, parentRight, offsetDrawableBottom2);
        this.mOffsetDrawable.draw(c);
    }

    private int getLastRowChildCount(int itemCount) {
        int lastRowChildCount = itemCount % this.mNumColumns;
        if (lastRowChildCount == 0) {
            return this.mNumColumns;
        }
        return lastRowChildCount;
    }
}
