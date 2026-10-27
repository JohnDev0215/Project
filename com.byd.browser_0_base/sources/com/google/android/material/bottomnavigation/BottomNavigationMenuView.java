package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.MenuBuilder;
import com.google.android.material.R$dimen;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    public final int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f27J;
    public final int K;
    public final int L;
    public boolean M;
    public final int[] N;

    public BottomNavigationMenuView(Context context) {
        super(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.I = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_max_width);
        this.f27J = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_min_width);
        this.K = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_max_width);
        this.L = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_min_width);
        this.N = new int[5];
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public final NavigationBarItemView e(Context context) {
        return new BottomNavigationItemView(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int measuredWidth = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (om3.e.c(this) == 1) {
                    int i8 = i5 - measuredWidth;
                    childAt.layout(i8 - childAt.getMeasuredWidth(), 0, i8, i6);
                } else {
                    childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, i6);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        MenuBuilder menu = getMenu();
        int size = View.MeasureSpec.getSize(i);
        int size2 = menu.l().size();
        int childCount = getChildCount();
        int size3 = View.MeasureSpec.getSize(i2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        boolean zF = NavigationBarMenuView.f(getLabelVisibilityMode(), size2);
        int i3 = this.K;
        int[] iArr = this.N;
        if (zF && this.M) {
            View childAt = getChildAt(getSelectedItemPosition());
            int visibility = childAt.getVisibility();
            int iMax = this.L;
            if (visibility != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i4 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.f27J * i4), Math.min(iMax, i3));
            int i5 = size - iMin;
            int iMin2 = Math.min(i5 / (i4 != 0 ? i4 : 1), this.I);
            int i6 = i5 - (i4 * iMin2);
            int i7 = 0;
            while (i7 < childCount) {
                if (getChildAt(i7).getVisibility() != 8) {
                    int i8 = i7 == getSelectedItemPosition() ? iMin : iMin2;
                    iArr[i7] = i8;
                    if (i6 > 0) {
                        iArr[i7] = i8 + 1;
                        i6--;
                    }
                } else {
                    iArr[i7] = 0;
                }
                i7++;
            }
        } else {
            int iMin3 = Math.min(size / (size2 != 0 ? size2 : 1), i3);
            int i9 = size - (size2 * iMin3);
            for (int i10 = 0; i10 < childCount; i10++) {
                if (getChildAt(i10).getVisibility() != 8) {
                    iArr[i10] = iMin3;
                    if (i9 > 0) {
                        iArr[i10] = iMin3 + 1;
                        i9--;
                    }
                } else {
                    iArr[i10] = 0;
                }
            }
        }
        int measuredWidth = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt2 = getChildAt(i11);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(iArr[i11], TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(measuredWidth, View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), 0), View.resolveSizeAndState(size3, i2, 0));
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.M = z;
    }
}
