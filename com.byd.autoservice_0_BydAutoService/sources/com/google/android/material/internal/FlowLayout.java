package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R$id;
import com.google.android.material.R$styleable;
import l0.g;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public class FlowLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2349b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2350c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2351d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2352e;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public static int a(int i3, int i4, int i5) {
        return i4 != Integer.MIN_VALUE ? i4 != 1073741824 ? i5 : i3 : Math.min(i5, i3);
    }

    public int b(View view) {
        Object tag = view.getTag(R$id.row_index_key);
        if (tag instanceof Integer) {
            return ((Integer) tag).intValue();
        }
        return -1;
    }

    public boolean c() {
        return this.f2351d;
    }

    public final void d(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.FlowLayout, 0, 0);
        this.f2349b = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlowLayout_lineSpacing, 0);
        this.f2350c = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.FlowLayout_itemSpacing, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int getItemSpacing() {
        return this.f2350c;
    }

    public int getLineSpacing() {
        return this.f2349b;
    }

    public int getRowCount() {
        return this.f2352e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        int iA;
        int iB;
        if (getChildCount() == 0) {
            this.f2352e = 0;
            return;
        }
        this.f2352e = 1;
        boolean z4 = t.z(this) == 1;
        int paddingRight = z4 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z4 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i7 = (i5 - i3) - paddingLeft;
        int measuredWidth = paddingRight;
        int i8 = paddingTop;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
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
                if (!this.f2351d && measuredWidth2 > i7) {
                    i8 = this.f2349b + paddingTop;
                    this.f2352e++;
                    measuredWidth = paddingRight;
                }
                childAt.setTag(R$id.row_index_key, Integer.valueOf(this.f2352e - 1));
                int i10 = measuredWidth + iB;
                int measuredWidth3 = childAt.getMeasuredWidth() + i10;
                int measuredHeight = childAt.getMeasuredHeight() + i8;
                if (z4) {
                    childAt.layout(i7 - measuredWidth3, i8, (i7 - measuredWidth) - iB, measuredHeight);
                } else {
                    childAt.layout(i10, i8, measuredWidth3, measuredHeight);
                }
                measuredWidth += iB + iA + childAt.getMeasuredWidth() + this.f2350c;
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        int i5;
        int i6;
        int paddingLeft;
        int size = View.MeasureSpec.getSize(i3);
        int mode = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i4);
        int mode2 = View.MeasureSpec.getMode(i4);
        int i7 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i7 - getPaddingRight();
        int i8 = paddingTop;
        int i9 = 0;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i3, i4);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i5 = marginLayoutParams.leftMargin + 0;
                    i6 = marginLayoutParams.rightMargin + 0;
                } else {
                    i5 = 0;
                    i6 = 0;
                }
                int i11 = paddingLeft2;
                if (paddingLeft2 + i5 + childAt.getMeasuredWidth() <= paddingRight || c()) {
                    paddingLeft = i11;
                } else {
                    paddingLeft = getPaddingLeft();
                    i8 = this.f2349b + paddingTop;
                }
                int measuredWidth = paddingLeft + i5 + childAt.getMeasuredWidth();
                int measuredHeight = i8 + childAt.getMeasuredHeight();
                if (measuredWidth > i9) {
                    i9 = measuredWidth;
                }
                paddingLeft2 = paddingLeft + i5 + i6 + childAt.getMeasuredWidth() + this.f2350c;
                if (i10 == getChildCount() - 1) {
                    i9 += i6;
                }
                paddingTop = measuredHeight;
            }
        }
        setMeasuredDimension(a(size, mode, i9 + getPaddingRight()), a(size2, mode2, paddingTop + getPaddingBottom()));
    }

    public void setItemSpacing(int i3) {
        this.f2350c = i3;
    }

    public void setLineSpacing(int i3) {
        this.f2349b = i3;
    }

    public void setSingleLine(boolean z3) {
        this.f2351d = z3;
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f2351d = false;
        d(context, attributeSet);
    }
}
