package com.android.car.ui.recyclerview.decorations.grid;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$dimen;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {
    public final Drawable a;
    public final Drawable b;
    public int c;

    public GridDividerItemDecoration(Drawable drawable, Drawable drawable2, int i) {
        this.a = drawable;
        this.b = drawable2;
        this.c = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void getItemOffsets(Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        rect.set(0, 0, this.a.getIntrinsicWidth(), this.a.getIntrinsicHeight());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public final void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        double dCeil = Math.ceil(((double) layoutManager.getChildCount()) / ((double) this.c));
        int i = 1;
        int i2 = 1;
        while (true) {
            double d = i2;
            if (d > dCeil) {
                break;
            }
            if (i2 != 1) {
                int i3 = d == dCeil ? (i2 - 1) * this.c : this.c * i2;
                View childAt = layoutManager.getChildAt((i2 - 1) * this.c);
                View childAt2 = layoutManager.getChildAt(i3 - 1);
                if (childAt == null || childAt2 == null) {
                    Log.e("GridDividerItem...", "drawVerticalDividers leftmostChild == null or rightmostChild == null");
                } else {
                    int left = childAt.getLeft() + ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_start_margin));
                    int top = childAt.getTop();
                    this.b.setBounds(left, top - this.b.getIntrinsicHeight(), childAt2.getRight() - ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_end_margin)), top);
                }
                this.b.draw(canvas);
            }
            i2++;
        }
        RecyclerView.LayoutManager layoutManager2 = recyclerView.getLayoutManager();
        Objects.requireNonNull(layoutManager2);
        int childCount = layoutManager2.getChildCount();
        int i4 = this.c;
        int i5 = childCount / i4;
        int i6 = childCount % i4;
        int iMin = Math.min(childCount, i4);
        while (i < iMin) {
            int i7 = i < i6 ? this.c * i5 : (i5 - 1) * this.c;
            View childAt3 = layoutManager2.getChildAt(i);
            View childAt4 = layoutManager2.getChildAt(i7 + i);
            if (childAt3 == null || childAt4 == null) {
                Log.e("GridDividerItem...", "drawHorizontalDividers firstRowChild == null or lastRowChild == null");
            } else {
                int top2 = childAt3.getTop() + ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_top_margin));
                int left2 = childAt3.getLeft();
                this.a.setBounds(left2 - this.a.getIntrinsicWidth(), top2, left2, childAt4.getBottom() - ((int) recyclerView.getContext().getResources().getDimension(R$dimen.car_ui_recyclerview_divider_bottom_margin)));
            }
            this.a.draw(canvas);
            i++;
        }
    }
}
