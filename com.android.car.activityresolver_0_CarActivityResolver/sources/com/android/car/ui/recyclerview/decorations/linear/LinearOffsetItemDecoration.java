package com.android.car.ui.recyclerview.decorations.linear;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LinearOffsetItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mOffsetDrawable;
    private int mOffsetPosition;
    private int mOffsetPx;
    private int mOrientation;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OffsetPosition {
        public static final int END = 1;
        public static final int START = 0;
    }

    public LinearOffsetItemDecoration(int offsetPx, int offsetPosition) {
        this.mOffsetPx = offsetPx;
        this.mOffsetPosition = offsetPosition;
    }

    public LinearOffsetItemDecoration(Drawable offsetDrawable) {
        this.mOffsetDrawable = offsetDrawable;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (this.mOffsetPosition == 0 && parent.getChildAdapterPosition(view) > 0) {
            return;
        }
        int itemCount = state.getItemCount();
        if (this.mOffsetPosition == 1 && parent.getChildAdapterPosition(view) != itemCount - 1) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        this.mOrientation = orientation;
        if (orientation == 0) {
            int i = this.mOffsetPx;
            if (i > 0) {
                if (this.mOffsetPosition == 0) {
                    outRect.left = i;
                    return;
                } else {
                    outRect.right = i;
                    return;
                }
            }
            Drawable drawable = this.mOffsetDrawable;
            if (drawable != null) {
                if (this.mOffsetPosition == 0) {
                    outRect.left = drawable.getIntrinsicWidth();
                    return;
                } else {
                    outRect.right = drawable.getIntrinsicWidth();
                    return;
                }
            }
            return;
        }
        if (orientation == 1) {
            int i2 = this.mOffsetPx;
            if (i2 > 0) {
                if (this.mOffsetPosition == 0) {
                    outRect.top = i2;
                    return;
                } else {
                    outRect.bottom = i2;
                    return;
                }
            }
            Drawable drawable2 = this.mOffsetDrawable;
            if (drawable2 != null) {
                if (this.mOffsetPosition == 0) {
                    outRect.top = drawable2.getIntrinsicHeight();
                } else {
                    outRect.bottom = drawable2.getIntrinsicHeight();
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (this.mOffsetDrawable == null) {
            return;
        }
        int i = this.mOrientation;
        if (i == 0) {
            drawOffsetHorizontal(c, parent);
        } else if (i == 1) {
            drawOffsetVertical(c, parent);
        }
    }

    private void drawOffsetHorizontal(Canvas canvas, RecyclerView parent) {
        int parentLeft;
        int parentTop = parent.getPaddingTop();
        int parentBottom = parent.getHeight() - parent.getPaddingBottom();
        if (this.mOffsetPosition == 0) {
            parentLeft = parent.getPaddingLeft();
        } else {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            View lastChild = layoutManager.getChildAt(layoutManager.getChildCount() - 1);
            RecyclerView.LayoutParams lastChildLayoutParams = (RecyclerView.LayoutParams) lastChild.getLayoutParams();
            parentLeft = lastChild.getRight() + lastChildLayoutParams.rightMargin;
        }
        int offsetDrawableRight = this.mOffsetDrawable.getIntrinsicWidth() + parentLeft;
        this.mOffsetDrawable.setBounds(parentLeft, parentTop, offsetDrawableRight, parentBottom);
        this.mOffsetDrawable.draw(canvas);
    }

    private void drawOffsetVertical(Canvas canvas, RecyclerView parent) {
        int parentTop;
        int parentLeft = parent.getPaddingLeft();
        int parentRight = parent.getWidth() - parent.getPaddingRight();
        if (this.mOffsetPosition == 0) {
            parentTop = parent.getPaddingTop();
        } else {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            View lastChild = layoutManager.getChildAt(layoutManager.getChildCount() - 1);
            RecyclerView.LayoutParams lastChildLayoutParams = (RecyclerView.LayoutParams) lastChild.getLayoutParams();
            parentTop = lastChild.getBottom() + lastChildLayoutParams.bottomMargin;
        }
        int offsetDrawableBottom = this.mOffsetDrawable.getIntrinsicHeight() + parentTop;
        this.mOffsetDrawable.setBounds(parentLeft, parentTop, parentRight, offsetDrawableBottom);
        this.mOffsetDrawable.draw(canvas);
    }
}
