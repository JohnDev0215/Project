package com.google.android.material.navigationrail;

import android.content.Context;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import defpackage.el1;
import defpackage.r83;
import defpackage.yn3;

/* JADX INFO: loaded from: classes.dex */
public class NavigationRailView extends NavigationBarView {
    public final int k;
    public View l;
    public final Boolean m;
    public final Boolean n;

    public NavigationRailView(Context context) {
        this(context, null);
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public final NavigationBarMenuView a(Context context) {
        return new NavigationRailMenuView(context);
    }

    public View getHeaderView() {
        return this.l;
    }

    public int getItemMinimumHeight() {
        return ((NavigationRailMenuView) getMenuView()).getItemMinimumHeight();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 7;
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        NavigationRailMenuView navigationRailMenuView = getNavigationRailMenuView();
        View view = this.l;
        int i5 = 0;
        boolean z2 = (view == null || view.getVisibility() == 8) ? false : true;
        int i6 = this.k;
        if (z2) {
            int bottom = this.l.getBottom() + i6;
            int top = navigationRailMenuView.getTop();
            if (top < bottom) {
                i5 = bottom - top;
            }
        } else {
            if ((navigationRailMenuView.f33J.gravity & 112) == 48) {
                i5 = i6;
            }
        }
        if (i5 > 0) {
            navigationRailMenuView.layout(navigationRailMenuView.getLeft(), navigationRailMenuView.getTop() + i5, navigationRailMenuView.getRight(), navigationRailMenuView.getBottom() + i5);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(i) != 1073741824 && suggestedMinimumWidth > 0) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), getPaddingRight() + getPaddingLeft() + suggestedMinimumWidth), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i, i2);
        View view = this.l;
        if ((view == null || view.getVisibility() == 8) ? false : true) {
            measureChild(getNavigationRailMenuView(), i, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.l.getMeasuredHeight()) - this.k, Integer.MIN_VALUE));
        }
    }

    public void setItemMinimumHeight(int i) {
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(i);
    }

    public void setMenuGravity(int i) {
        getNavigationRailMenuView().setMenuGravity(i);
    }

    public NavigationRailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.navigationRailStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationRailView(Context context, AttributeSet attributeSet, int i) {
        int i2 = R$style.Widget_MaterialComponents_NavigationRailView;
        super(context, attributeSet, i, i2);
        this.m = null;
        this.n = null;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.mtrl_navigation_rail_margin);
        this.k = dimensionPixelSize;
        TintTypedArray tintTypedArrayE = r83.e(getContext(), attributeSet, R$styleable.NavigationRailView, i, i2, new int[0]);
        int i3 = tintTypedArrayE.i(R$styleable.NavigationRailView_headerLayout, 0);
        if (i3 != 0) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(i3, (ViewGroup) this, false);
            View view = this.l;
            if (view != null) {
                removeView(view);
                this.l = null;
            }
            this.l = viewInflate;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 49;
            layoutParams.topMargin = dimensionPixelSize;
            addView(viewInflate, 0, layoutParams);
        }
        setMenuGravity(tintTypedArrayE.h(R$styleable.NavigationRailView_menuGravity, 49));
        int i4 = R$styleable.NavigationRailView_itemMinHeight;
        if (tintTypedArrayE.l(i4)) {
            setItemMinimumHeight(tintTypedArrayE.d(i4, -1));
        }
        int i5 = R$styleable.NavigationRailView_paddingTopSystemWindowInsets;
        if (tintTypedArrayE.l(i5)) {
            this.m = Boolean.valueOf(tintTypedArrayE.a(i5, false));
        }
        int i6 = R$styleable.NavigationRailView_paddingBottomSystemWindowInsets;
        if (tintTypedArrayE.l(i6)) {
            this.n = Boolean.valueOf(tintTypedArrayE.a(i6, false));
        }
        tintTypedArrayE.n();
        yn3.a(this, new el1(this));
    }
}
