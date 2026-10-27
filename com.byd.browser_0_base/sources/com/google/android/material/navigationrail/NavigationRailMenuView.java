package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

/* JADX INFO: loaded from: classes.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final FrameLayout.LayoutParams f33J;

    public NavigationRailMenuView(Context context) {
        super(context);
        this.I = -1;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.f33J = layoutParams;
        layoutParams.gravity = 49;
        setLayoutParams(layoutParams);
        setItemActiveIndicatorResizeable(true);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public final NavigationBarItemView e(Context context) {
        return new NavigationRailItemView(context);
    }

    public final int g(int i, int i2, int i3) {
        int iMax = i2 / Math.max(1, i3);
        int size = this.I;
        if (size == -1) {
            size = View.MeasureSpec.getSize(i);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, iMax), 0);
    }

    public int getItemMinimumHeight() {
        return this.I;
    }

    public int getMenuGravity() {
        return this.f33J.gravity;
    }

    public final int h(View view, int i, int i2, int i3) {
        int measuredHeight;
        g(i, i2, i3);
        int iG = view == null ? g(i, i2, i3) : View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        int childCount = getChildCount();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != view) {
                if (childAt.getVisibility() != 8) {
                    childAt.measure(i, iG);
                    measuredHeight = childAt.getMeasuredHeight();
                } else {
                    measuredHeight = 0;
                }
                i4 += measuredHeight;
            }
        }
        return i4;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i6;
                childAt.layout(0, i6, i5, measuredHeight);
                i6 = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iH;
        int measuredHeight;
        int size = View.MeasureSpec.getSize(i2);
        int size2 = getMenu().l().size();
        if (size2 <= 1 || !NavigationBarMenuView.f(getLabelVisibilityMode(), size2)) {
            iH = h(null, i, size, size2);
        } else {
            View childAt = getChildAt(getSelectedItemPosition());
            if (childAt != null) {
                int iG = g(i, size, size2);
                if (childAt.getVisibility() != 8) {
                    childAt.measure(i, iG);
                    measuredHeight = childAt.getMeasuredHeight();
                } else {
                    measuredHeight = 0;
                }
                size -= measuredHeight;
                size2--;
            } else {
                measuredHeight = 0;
            }
            iH = h(childAt, i, size, size2) + measuredHeight;
        }
        setMeasuredDimension(View.resolveSizeAndState(View.MeasureSpec.getSize(i), i, 0), View.resolveSizeAndState(iH, i2, 0));
    }

    public void setItemMinimumHeight(int i) {
        if (this.I != i) {
            this.I = i;
            requestLayout();
        }
    }

    public void setMenuGravity(int i) {
        FrameLayout.LayoutParams layoutParams = this.f33J;
        if (layoutParams.gravity != i) {
            layoutParams.gravity = i;
            setLayoutParams(layoutParams);
        }
    }
}
