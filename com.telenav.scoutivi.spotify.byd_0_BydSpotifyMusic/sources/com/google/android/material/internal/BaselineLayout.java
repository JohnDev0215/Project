package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class BaselineLayout extends ViewGroup {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4578e;

    public BaselineLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f4578e = -1;
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.f4578e;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((i9 - i7) - getPaddingRight()) - paddingLeft;
        int paddingTop = getPaddingTop();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i12 = ((paddingRight - measuredWidth) / 2) + paddingLeft;
                int baseline = (this.f4578e == -1 || childAt.getBaseline() == -1) ? paddingTop : (this.f4578e + paddingTop) - childAt.getBaseline();
                childAt.layout(i12, baseline, measuredWidth + i12, measuredHeight + baseline);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        int childCount = getChildCount();
        int iMax = -1;
        int iMax2 = -1;
        int iMax3 = 0;
        int iMax4 = 0;
        int iCombineMeasuredStates = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i7, i8);
                int baseline = childAt.getBaseline();
                if (baseline != -1) {
                    iMax = Math.max(iMax, baseline);
                    iMax2 = Math.max(iMax2, childAt.getMeasuredHeight() - baseline);
                }
                iMax4 = Math.max(iMax4, childAt.getMeasuredWidth());
                iMax3 = Math.max(iMax3, childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        if (iMax != -1) {
            iMax3 = Math.max(iMax3, Math.max(iMax2, getPaddingBottom()) + iMax);
            this.f4578e = iMax;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax4, getSuggestedMinimumWidth()), i7, iCombineMeasuredStates), View.resolveSizeAndState(Math.max(iMax3, getSuggestedMinimumHeight()), i8, iCombineMeasuredStates << 16));
    }
}
