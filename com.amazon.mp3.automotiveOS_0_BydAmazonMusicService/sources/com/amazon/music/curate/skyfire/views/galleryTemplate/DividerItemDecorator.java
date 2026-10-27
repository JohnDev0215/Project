package com.amazon.music.curate.skyfire.views.galleryTemplate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class DividerItemDecorator extends DividerItemDecoration {
    private final Rect mBounds;
    private Drawable mDivider;

    public DividerItemDecorator(final Context context, final int orientation, final Drawable mDivider) {
        super(context, orientation);
        this.mBounds = new Rect();
        this.mDivider = mDivider;
    }

    @Override // androidx.recyclerview.widget.DividerItemDecoration, androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || this.mDivider == null) {
            return;
        }
        drawVertical(c, parent);
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int width;
        int paddingLeft;
        canvas.save();
        if (parent.getClipToPadding()) {
            paddingLeft = parent.getPaddingLeft();
            width = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(paddingLeft, parent.getPaddingTop(), width, parent.getHeight() - parent.getPaddingBottom());
        } else {
            width = parent.getWidth();
            paddingLeft = 0;
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(childAt, this.mBounds);
            int iRound = this.mBounds.bottom + Math.round(childAt.getTranslationY());
            this.mDivider.setBounds(paddingLeft, iRound - this.mDivider.getIntrinsicHeight(), width, iRound);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }
}
