package com.google.android.flexbox;

import android.R;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.a.b;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class FlexboxItemDecoration extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1125a = {R.attr.listDivider};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f1126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1127c;

    public final void a(Canvas canvas, RecyclerView recyclerView) {
        int top;
        int intrinsicHeight;
        int left;
        int right;
        int i;
        int iMin;
        int left2;
        if (d()) {
            FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
            int flexDirection = flexboxLayoutManager.getFlexDirection();
            int left3 = recyclerView.getLeft() - recyclerView.getPaddingLeft();
            int right2 = recyclerView.getRight() + recyclerView.getPaddingRight();
            int childCount = recyclerView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = recyclerView.getChildAt(i2);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                if (flexDirection == 3) {
                    intrinsicHeight = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                    top = this.f1126b.getIntrinsicHeight() + intrinsicHeight;
                } else {
                    top = childAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                    intrinsicHeight = top - this.f1126b.getIntrinsicHeight();
                }
                if (!flexboxLayoutManager.i()) {
                    left = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    right = childAt.getRight();
                    i = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                } else if (flexboxLayoutManager.J()) {
                    iMin = Math.min(childAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + this.f1126b.getIntrinsicWidth(), right2);
                    left2 = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    this.f1126b.setBounds(left2, intrinsicHeight, iMin, top);
                    this.f1126b.draw(canvas);
                } else {
                    left = Math.max((childAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin) - this.f1126b.getIntrinsicWidth(), left3);
                    right = childAt.getRight();
                    i = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                }
                int i3 = left;
                iMin = right + i;
                left2 = i3;
                this.f1126b.setBounds(left2, intrinsicHeight, iMin, top);
                this.f1126b.draw(canvas);
            }
        }
    }

    public final void b(Canvas canvas, RecyclerView recyclerView) {
        int left;
        int intrinsicWidth;
        int iMax;
        int bottom;
        int i;
        int i2;
        if (e()) {
            FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
            int top = recyclerView.getTop() - recyclerView.getPaddingTop();
            int bottom2 = recyclerView.getBottom() + recyclerView.getPaddingBottom();
            int childCount = recyclerView.getChildCount();
            int flexDirection = flexboxLayoutManager.getFlexDirection();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = recyclerView.getChildAt(i3);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
                if (flexboxLayoutManager.J()) {
                    intrinsicWidth = childAt.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
                    left = this.f1126b.getIntrinsicWidth() + intrinsicWidth;
                } else {
                    left = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    intrinsicWidth = left - this.f1126b.getIntrinsicWidth();
                }
                if (flexboxLayoutManager.i()) {
                    iMax = childAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                    bottom = childAt.getBottom();
                    i = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                } else if (flexDirection == 3) {
                    int iMin = Math.min(childAt.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + this.f1126b.getIntrinsicHeight(), bottom2);
                    iMax = childAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                    i2 = iMin;
                    this.f1126b.setBounds(intrinsicWidth, iMax, left, i2);
                    this.f1126b.draw(canvas);
                } else {
                    iMax = Math.max((childAt.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.f1126b.getIntrinsicHeight(), top);
                    bottom = childAt.getBottom();
                    i = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                }
                i2 = bottom + i;
                this.f1126b.setBounds(intrinsicWidth, iMax, left, i2);
                this.f1126b.draw(canvas);
            }
        }
    }

    public final boolean c(int i, List<b> list, FlexboxLayoutManager flexboxLayoutManager) {
        int iG = flexboxLayoutManager.G(i);
        if ((iG == -1 || iG >= flexboxLayoutManager.getFlexLinesInternal().size() || flexboxLayoutManager.getFlexLinesInternal().get(iG).o != i) && i != 0) {
            return list.size() != 0 && list.get(list.size() - 1).p == i - 1;
        }
        return true;
    }

    public final boolean d() {
        return (this.f1127c & 1) > 0;
    }

    public final boolean e() {
        return (this.f1127c & 2) > 0;
    }

    public final void f(Rect rect, int i, FlexboxLayoutManager flexboxLayoutManager, List<b> list) {
        if (list.size() == 0 || flexboxLayoutManager.G(i) == 0) {
            return;
        }
        if (flexboxLayoutManager.i()) {
            if (d()) {
                rect.top = this.f1126b.getIntrinsicHeight();
                rect.bottom = 0;
                return;
            } else {
                rect.top = 0;
                rect.bottom = 0;
                return;
            }
        }
        if (e()) {
            if (flexboxLayoutManager.J()) {
                rect.right = this.f1126b.getIntrinsicWidth();
                rect.left = 0;
            } else {
                rect.left = this.f1126b.getIntrinsicWidth();
                rect.right = 0;
            }
        }
    }

    public final void g(Rect rect, int i, FlexboxLayoutManager flexboxLayoutManager, List<b> list, int i2) {
        if (c(i, list, flexboxLayoutManager)) {
            return;
        }
        if (flexboxLayoutManager.i()) {
            if (!e()) {
                rect.left = 0;
                rect.right = 0;
                return;
            } else if (flexboxLayoutManager.J()) {
                rect.right = this.f1126b.getIntrinsicWidth();
                rect.left = 0;
                return;
            } else {
                rect.left = this.f1126b.getIntrinsicWidth();
                rect.right = 0;
                return;
            }
        }
        if (!d()) {
            rect.top = 0;
            rect.bottom = 0;
        } else if (i2 == 3) {
            rect.bottom = this.f1126b.getIntrinsicHeight();
            rect.top = 0;
        } else {
            rect.top = this.f1126b.getIntrinsicHeight();
            rect.bottom = 0;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == 0) {
            return;
        }
        if (!d() && !e()) {
            rect.set(0, 0, 0, 0);
            return;
        }
        FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
        List<b> listF = flexboxLayoutManager.F();
        g(rect, childAdapterPosition, flexboxLayoutManager, listF, flexboxLayoutManager.getFlexDirection());
        f(rect, childAdapterPosition, flexboxLayoutManager, listF);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        a(canvas, recyclerView);
        b(canvas, recyclerView);
    }
}
