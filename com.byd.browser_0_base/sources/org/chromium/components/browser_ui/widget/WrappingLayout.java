package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class WrappingLayout extends ViewGroup {
    public final int e;
    public final int f;
    public final ArrayList<Integer> g;

    public WrappingLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        boolean z2 = true;
        boolean z3 = getLayoutDirection() == 1;
        int i5 = 0;
        int iMax = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int marginStart = marginLayoutParams.getMarginStart() + paddingStart;
                int i6 = marginLayoutParams.topMargin + paddingTop;
                boolean z4 = paddingStart == getPaddingStart() ? z2 : false;
                int marginEnd = marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart() + measuredWidth;
                int i7 = marginLayoutParams.topMargin + measuredHeight + marginLayoutParams.bottomMargin;
                if (z4 || paddingStart + marginEnd <= getMeasuredWidth()) {
                    iMax = Math.max(iMax, i7);
                } else {
                    marginStart = marginLayoutParams.getMarginStart() + getPaddingStart();
                    i6 += iMax + this.f;
                    paddingTop = i6 - marginLayoutParams.topMargin;
                    iMax = i7;
                }
                int measuredWidth2 = marginStart + measuredWidth;
                int marginEnd2 = marginLayoutParams.getMarginEnd() + measuredWidth2 + this.e;
                if (z3) {
                    measuredWidth2 = getMeasuredWidth() - marginStart;
                    marginStart = measuredWidth2 - measuredWidth;
                }
                childAt.layout(marginStart, i6, measuredWidth2, measuredHeight + i6);
                paddingStart = marginEnd2;
            }
            i5++;
            z2 = true;
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        ArrayList<Integer> arrayList;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 0) {
            return;
        }
        measureChildren(i, i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
            return;
        }
        int size = View.MeasureSpec.getSize(i) - (getPaddingRight() + getPaddingLeft());
        int i3 = 0;
        while (true) {
            int childCount = getChildCount();
            arrayList = this.g;
            if (i3 >= childCount) {
                break;
            }
            if (getChildAt(i3).getVisibility() != 8) {
                arrayList.add(Integer.valueOf(i3));
            }
            i3++;
        }
        int size2 = arrayList.size();
        int i4 = 0;
        int iMax = 0;
        int i5 = 0;
        int i6 = 0;
        int iMax2 = 0;
        while (i4 < size2) {
            View childAt = getChildAt(arrayList.get(i4).intValue());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int marginEnd = marginLayoutParams.getMarginEnd() + childAt.getMeasuredWidth() + marginLayoutParams.getMarginStart();
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            if (i6 + marginEnd <= size) {
                if (i6 != 0) {
                    i6 += this.e;
                }
                i6 += marginEnd;
                iMax2 = Math.max(iMax2, measuredHeight);
            } else {
                if (iMax2 != 0) {
                    i5 += iMax2 + this.f;
                }
                iMax2 = measuredHeight;
                i6 = marginEnd;
            }
            iMax = Math.max(iMax, i6);
            i4++;
            if (i4 == size2) {
                i5 += iMax2;
            }
        }
        int paddingRight = getPaddingRight() + getPaddingLeft() + iMax;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + i5;
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int mode3 = View.MeasureSpec.getMode(i);
        int size3 = View.MeasureSpec.getSize(i);
        if (mode3 != 1073741824) {
            if (mode3 == Integer.MIN_VALUE) {
                paddingRight = Math.min(paddingRight, size3);
            }
            size3 = Math.max(paddingRight, suggestedMinimumWidth);
        }
        int iResolveSizeAndState = View.resolveSizeAndState(size3, i, 0);
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int mode4 = View.MeasureSpec.getMode(i2);
        int size4 = View.MeasureSpec.getSize(i2);
        if (mode4 != 1073741824) {
            if (mode4 == Integer.MIN_VALUE) {
                paddingBottom = Math.min(paddingBottom, size4);
            }
            size4 = Math.max(paddingBottom, suggestedMinimumHeight);
        }
        setMeasuredDimension(iResolveSizeAndState, View.resolveSizeAndState(size4, i2, 0));
        arrayList.clear();
    }

    public WrappingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public WrappingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new ArrayList<>();
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.WrappingLayout, i, 0);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.WrappingLayout_horizontalSpacing, 0);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.WrappingLayout_verticalSpacing, 0);
    }
}
