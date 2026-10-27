package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R$id;
import com.google.android.material.R$styleable;
import o0.g;
import o0.t;

/* JADX INFO: loaded from: classes2.dex */
public class FlowLayout extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3932d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3933e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3934f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3935g;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public static int a(int i8, int i9, int i10) {
        return i9 != Integer.MIN_VALUE ? i9 != 1073741824 ? i10 : i8 : Math.min(i10, i8);
    }

    public int b(View view) {
        Object tag = view.getTag(R$id.row_index_key);
        if (tag instanceof Integer) {
            return ((Integer) tag).intValue();
        }
        return -1;
    }

    public boolean c() {
        return this.f3934f;
    }

    public final void d(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.FlowLayout, 0, 0);
        this.f3932d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlowLayout_lineSpacing, 0);
        this.f3933e = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlowLayout_itemSpacing, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int getItemSpacing() {
        return this.f3933e;
    }

    public int getLineSpacing() {
        return this.f3932d;
    }

    public int getRowCount() {
        return this.f3935g;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int iA;
        int iB;
        if (getChildCount() == 0) {
            this.f3935g = 0;
            return;
        }
        this.f3935g = 1;
        boolean z8 = t.A(this) == 1;
        int paddingRight = z8 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z8 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i12 = (i10 - i8) - paddingLeft;
        int measuredWidth = paddingRight;
        int i13 = paddingTop;
        for (int i14 = 0; i14 < getChildCount(); i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() == 8) {
                childAt.setTag(R$id.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    iB = g.b(marginLayoutParams);
                    iA = g.a(marginLayoutParams);
                } else {
                    iA = 0;
                    iB = 0;
                }
                int measuredWidth2 = measuredWidth + iB + childAt.getMeasuredWidth();
                if (!this.f3934f && measuredWidth2 > i12) {
                    i13 = this.f3932d + paddingTop;
                    this.f3935g++;
                    measuredWidth = paddingRight;
                }
                childAt.setTag(R$id.row_index_key, Integer.valueOf(this.f3935g - 1));
                int i15 = measuredWidth + iB;
                int measuredWidth3 = childAt.getMeasuredWidth() + i15;
                int measuredHeight = childAt.getMeasuredHeight() + i13;
                if (z8) {
                    childAt.layout(i12 - measuredWidth3, i13, (i12 - measuredWidth) - iB, measuredHeight);
                } else {
                    childAt.layout(i15, i13, measuredWidth3, measuredHeight);
                }
                measuredWidth += iB + iA + childAt.getMeasuredWidth() + this.f3933e;
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        int i10;
        int i11;
        int paddingLeft;
        int size = View.MeasureSpec.getSize(i8);
        int mode = View.MeasureSpec.getMode(i8);
        int size2 = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i9);
        int i12 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i12 - getPaddingRight();
        int i13 = paddingTop;
        int i14 = 0;
        for (int i15 = 0; i15 < getChildCount(); i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i8, i9);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i10 = marginLayoutParams.leftMargin + 0;
                    i11 = marginLayoutParams.rightMargin + 0;
                } else {
                    i10 = 0;
                    i11 = 0;
                }
                int i16 = paddingLeft2;
                if (paddingLeft2 + i10 + childAt.getMeasuredWidth() <= paddingRight || c()) {
                    paddingLeft = i16;
                } else {
                    paddingLeft = getPaddingLeft();
                    i13 = this.f3932d + paddingTop;
                }
                int measuredWidth = paddingLeft + i10 + childAt.getMeasuredWidth();
                int measuredHeight = i13 + childAt.getMeasuredHeight();
                if (measuredWidth > i14) {
                    i14 = measuredWidth;
                }
                paddingLeft2 = paddingLeft + i10 + i11 + childAt.getMeasuredWidth() + this.f3933e;
                if (i15 == getChildCount() - 1) {
                    i14 += i11;
                }
                paddingTop = measuredHeight;
            }
        }
        setMeasuredDimension(a(size, mode, i14 + getPaddingRight()), a(size2, mode2, paddingTop + getPaddingBottom()));
    }

    public void setItemSpacing(int i8) {
        this.f3933e = i8;
    }

    public void setLineSpacing(int i8) {
        this.f3932d = i8;
    }

    public void setSingleLine(boolean z7) {
        this.f3934f = z7;
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3934f = false;
        d(context, attributeSet);
    }
}
