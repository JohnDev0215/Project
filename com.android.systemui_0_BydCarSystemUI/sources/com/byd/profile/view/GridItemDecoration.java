package com.byd.profile.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: loaded from: classes2.dex */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private Builder mBuilder;
    private Paint mHorPaint;
    private Paint mVerPaint;

    GridItemDecoration(Builder builder) {
        init(builder);
    }

    private void init(Builder builder) {
        this.mBuilder = builder;
        Paint paint = new Paint(1);
        this.mVerPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mVerPaint.setColor(builder.verColor);
        Paint paint2 = new Paint(1);
        this.mHorPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.mHorPaint.setColor(builder.horColor);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            if (parent.getChildViewHolder(childAt).getItemViewType() != 1 || this.mBuilder.isShowHeadDivider) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                int left = childAt.getLeft() - layoutParams.leftMargin;
                int right = childAt.getRight() + layoutParams.rightMargin;
                c.drawRect(left, childAt.getBottom() + layoutParams.bottomMargin, right, this.mBuilder.dividerHorSize + r2, this.mHorPaint);
            }
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(childAt) % getSpanCount(parent) != 0) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                int top = childAt.getTop() - layoutParams.topMargin;
                int bottom = childAt.getBottom() + layoutParams.bottomMargin + this.mBuilder.dividerHorSize;
                c.drawRect(childAt.getRight() + layoutParams.rightMargin, top, this.mBuilder.dividerVerSize + r2, bottom, this.mVerPaint);
            }
        }
    }

    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return childCount <= spanCount || pos >= childCount - (childCount % spanCount);
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getOrientation() == 1 ? pos >= childCount - (childCount % spanCount) : (pos + 1) % spanCount == 0;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int spanCount = getSpanCount(parent);
        int itemCount = parent.getAdapter().getItemCount();
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (this.mBuilder.isExistHeadView) {
            viewLayoutPosition--;
        }
        if (this.mBuilder.isShowHeadDivider && viewLayoutPosition == -1) {
            outRect.set(0, 0, 0, this.mBuilder.dividerHorSize);
        }
        if (viewLayoutPosition < 0) {
            return;
        }
        int i = viewLayoutPosition % spanCount;
        outRect.set((this.mBuilder.dividerVerSize * i) / spanCount, 0, this.mBuilder.dividerVerSize - (((i + 1) * this.mBuilder.dividerVerSize) / spanCount), (!isLastRaw(parent, viewLayoutPosition, spanCount, itemCount) || this.mBuilder.isShowLastDivider) ? this.mBuilder.dividerHorSize : 0);
        marginOffsets(outRect, spanCount, viewLayoutPosition);
    }

    private void marginOffsets(Rect outRect, int spanCount, int itemPosition) {
        if (this.mBuilder.marginRight == 0 && this.mBuilder.marginLeft == 0) {
            return;
        }
        int i = (this.mBuilder.marginLeft + this.mBuilder.marginRight) / spanCount;
        int i2 = itemPosition % spanCount;
        outRect.left += this.mBuilder.marginLeft - (i2 * i);
        outRect.right += ((i2 + 1) * i) - this.mBuilder.marginLeft;
    }

    public static class Builder {
        private Context c;
        int dividerHorSize;
        int dividerVerSize;
        int horColor;
        int marginLeft;
        int marginRight;
        int verColor;
        boolean isShowLastDivider = false;
        boolean isExistHeadView = false;
        boolean isShowHeadDivider = false;

        public Builder(Context c) {
            this.c = c;
        }

        public Builder color(int color) {
            this.horColor = this.c.getResources().getColor(color);
            this.verColor = this.c.getResources().getColor(color);
            return this;
        }

        public Builder horColor(int horColor) {
            this.horColor = this.c.getResources().getColor(horColor);
            return this;
        }

        public Builder verColor(int verColor) {
            this.verColor = this.c.getResources().getColor(verColor);
            return this;
        }

        public Builder size(int size) {
            this.dividerHorSize = size;
            this.dividerVerSize = size;
            return this;
        }

        public Builder horSize(int horSize) {
            this.dividerHorSize = horSize;
            return this;
        }

        public Builder verSize(int verSize) {
            this.dividerVerSize = verSize;
            return this;
        }

        public Builder margin(int marginLeft, int marginRight) {
            this.marginLeft = marginLeft;
            this.marginRight = marginRight;
            return this;
        }

        public Builder showLastDivider(boolean isShow) {
            this.isShowLastDivider = isShow;
            return this;
        }

        public Builder showHeadDivider(boolean isShow) {
            this.isShowHeadDivider = isShow;
            return this;
        }

        public Builder isExistHead(boolean isExistHead) {
            this.isExistHeadView = isExistHead;
            return this;
        }

        public GridItemDecoration build() {
            return new GridItemDecoration(this);
        }
    }
}
