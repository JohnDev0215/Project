package com.amazon.ui.foundations.views;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomDividerGridLayoutItemDecorator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/ui/foundations/views/CustomDividerGridLayoutItemDecorator;", "Lcom/amazon/ui/foundations/views/CustomGridLayoutItemDecorator;", "leftSpacing", "", "topSpacing", "rightSpacing", "bottomSpacing", "divider", "Landroid/graphics/drawable/Drawable;", "(IIIILandroid/graphics/drawable/Drawable;)V", "onDrawOver", "", "c", "Landroid/graphics/Canvas;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomDividerGridLayoutItemDecorator extends CustomGridLayoutItemDecorator {
    private final int bottomSpacing;
    private final Drawable divider;
    private final int leftSpacing;
    private final int rightSpacing;
    private final int topSpacing;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomDividerGridLayoutItemDecorator(int i, int i2, int i3, int i4, Drawable divider) {
        super(i, i2, i3, i4);
        Intrinsics.checkNotNullParameter(divider, "divider");
        this.leftSpacing = i;
        this.topSpacing = i2;
        this.rightSpacing = i3;
        this.bottomSpacing = i4;
        this.divider = divider;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(c, "c");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        int i = 0;
        while (i < childCount) {
            int i2 = i + 1;
            View childAt = parent.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
            }
            int left = childAt.getLeft() - childAt.getPaddingLeft();
            int width = (childAt.getWidth() + left) - childAt.getPaddingRight();
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) layoutParams)).bottomMargin;
            this.divider.setBounds(left, bottom, width, this.divider.getIntrinsicHeight() + bottom);
            this.divider.draw(c);
            i = i2;
        }
    }
}
