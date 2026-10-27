package com.amazon.podcast.views.contextMenu;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class DividerItemDecorator extends RecyclerView.ItemDecoration {
    private Drawable divider;

    public DividerItemDecorator(final Drawable divider) {
        this.divider = divider;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(final Canvas canvas, final RecyclerView parent, final RecyclerView.State state) {
        int paddingLeft = parent.getPaddingLeft();
        int width = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i <= childCount - 2; i++) {
            View childAt = parent.getChildAt(i);
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).bottomMargin;
            this.divider.setBounds(paddingLeft, bottom, width, this.divider.getIntrinsicHeight() + bottom);
            this.divider.draw(canvas);
        }
    }
}
