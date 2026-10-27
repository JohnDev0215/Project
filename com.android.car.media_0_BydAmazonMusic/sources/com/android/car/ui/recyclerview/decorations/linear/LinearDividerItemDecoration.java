package com.android.car.ui.recyclerview.decorations.linear;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$dimen;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {
    public final Drawable a;
    public int b;

    public LinearDividerItemDecoration(Drawable drawable) {
        this.a = drawable;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        this.b = orientation;
        if (orientation == 0) {
            rect.left = this.a.getIntrinsicWidth();
        } else if (orientation == 1) {
            rect.top = this.a.getIntrinsicHeight();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int i = this.b;
        int i2 = 0;
        if (i == 0) {
            int paddingTop = recyclerView.getPaddingTop() + ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_top_margin));
            int height = (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_bottom_margin));
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                Log.e("LinearDivider...", "drawHorizontalDividers layoutManager == null");
                return;
            }
            int childCount = layoutManager.getChildCount();
            while (i2 < childCount - 1) {
                View childAt = layoutManager.getChildAt(i2);
                if (childAt != null) {
                    int right = childAt.getRight() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).rightMargin;
                    this.a.setBounds(right, paddingTop, this.a.getIntrinsicWidth() + right, height);
                } else {
                    Log.e("LinearDivider...", "drawHorizontalDividers child == null");
                }
                this.a.draw(canvas);
                i2++;
            }
            return;
        }
        if (i == 1) {
            int paddingLeft = recyclerView.getPaddingLeft() + ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_start_margin));
            int width = (recyclerView.getWidth() - recyclerView.getPaddingRight()) - ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_end_margin));
            RecyclerView.LayoutManager layoutManager2 = recyclerView.getLayoutManager();
            if (layoutManager2 == null) {
                Log.e("LinearDivider...", "drawVerticalDividers layoutManager == null");
                return;
            }
            int childCount2 = layoutManager2.getChildCount();
            while (i2 < childCount2 - 1) {
                View childAt2 = layoutManager2.getChildAt(i2);
                if (childAt2 != null) {
                    int bottom = childAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt2.getLayoutParams())).bottomMargin;
                    this.a.setBounds(paddingLeft, bottom, width, this.a.getIntrinsicHeight() + bottom);
                } else {
                    Log.e("LinearDivider...", "drawVerticalDividers child == null");
                }
                this.a.draw(canvas);
                i2++;
            }
        }
    }
}
