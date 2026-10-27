package com.amazon.podcast.views;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.R$dimen;

/* JADX INFO: loaded from: classes5.dex */
public final class DividerItemDecorator extends RecyclerView.ItemDecoration {
    private final Drawable divider;

    public DividerItemDecorator(final Drawable divider) {
        this.divider = divider;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(final Canvas canvas, final RecyclerView parent, final RecyclerView.State state) {
        int dimensionPixelSize = parent.getResources().getDimensionPixelSize(R$dimen.margin) + parent.getResources().getDimensionPixelSize(R$dimen.gutter);
        int width = parent.getWidth() - dimensionPixelSize;
        int childCount = parent.getChildCount();
        for (int i = 0; i <= childCount - 2; i++) {
            View childAt = parent.getChildAt(i);
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).bottomMargin;
            this.divider.setBounds(dimensionPixelSize, bottom, width, this.divider.getIntrinsicHeight() + bottom);
            this.divider.draw(canvas);
        }
    }
}
