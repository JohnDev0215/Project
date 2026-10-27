package com.amazon.music.views.library.views;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementOverflowItemDividerDecoration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/views/RefinementOverflowItemDividerDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "divider", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;)V", "onDrawOver", "", "c", "Landroid/graphics/Canvas;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementOverflowItemDividerDecoration extends RecyclerView.ItemDecoration {
    private final Drawable divider;

    public RefinementOverflowItemDividerDecoration(Drawable drawable) {
        this.divider = drawable;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        int paddingLeft = parent.getPaddingLeft();
        int right = parent.getRight() - parent.getPaddingRight();
        int childCount = parent.getChildCount() - 1;
        int i = 0;
        while (i < childCount) {
            int i2 = i + 1;
            View childAt = parent.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
            }
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) layoutParams)).bottomMargin;
            Drawable drawable = this.divider;
            int intrinsicHeight = (drawable == null ? 0 : drawable.getIntrinsicHeight()) + bottom;
            Drawable drawable2 = this.divider;
            if (drawable2 != null) {
                drawable2.setBounds(paddingLeft, bottom, right, intrinsicHeight);
            }
            Drawable drawable3 = this.divider;
            if (drawable3 != null) {
                drawable3.draw(c);
            }
            i = i2;
        }
    }
}
