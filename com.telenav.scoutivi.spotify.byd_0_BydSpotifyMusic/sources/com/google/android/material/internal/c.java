package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import i0.u;

/* JADX INFO: compiled from: FlowLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends ViewGroup {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4626e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4627g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4628h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4629i;

    public c(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private static int a(int i7, int i8, int i9) {
        return i8 != Integer.MIN_VALUE ? i8 != 1073741824 ? i9 : i7 : Math.min(i9, i7);
    }

    private void d(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, o2.l.FlowLayout, 0, 0);
        this.f4626e = typedArrayObtainStyledAttributes.getDimensionPixelSize(o2.l.FlowLayout_lineSpacing, 0);
        this.f4627g = typedArrayObtainStyledAttributes.getDimensionPixelSize(o2.l.FlowLayout_itemSpacing, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int b(View view) {
        Object tag = view.getTag(o2.f.row_index_key);
        if (tag instanceof Integer) {
            return ((Integer) tag).intValue();
        }
        return -1;
    }

    public boolean c() {
        return this.f4628h;
    }

    protected int getItemSpacing() {
        return this.f4627g;
    }

    protected int getLineSpacing() {
        return this.f4626e;
    }

    protected int getRowCount() {
        return this.f4629i;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int iA;
        int iB;
        if (getChildCount() == 0) {
            this.f4629i = 0;
            return;
        }
        this.f4629i = 1;
        boolean z7 = u.y(this) == 1;
        int paddingRight = z7 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z7 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int i11 = (i9 - i7) - paddingLeft;
        int measuredWidth = paddingRight;
        int i12 = paddingTop;
        for (int i13 = 0; i13 < getChildCount(); i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() == 8) {
                childAt.setTag(o2.f.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    iB = i0.h.b(marginLayoutParams);
                    iA = i0.h.a(marginLayoutParams);
                } else {
                    iA = 0;
                    iB = 0;
                }
                int measuredWidth2 = measuredWidth + iB + childAt.getMeasuredWidth();
                if (!this.f4628h && measuredWidth2 > i11) {
                    i12 = this.f4626e + paddingTop;
                    this.f4629i++;
                    measuredWidth = paddingRight;
                }
                childAt.setTag(o2.f.row_index_key, Integer.valueOf(this.f4629i - 1));
                int i14 = measuredWidth + iB;
                int measuredWidth3 = childAt.getMeasuredWidth() + i14;
                int measuredHeight = childAt.getMeasuredHeight() + i12;
                if (z7) {
                    childAt.layout(i11 - measuredWidth3, i12, (i11 - measuredWidth) - iB, measuredHeight);
                } else {
                    childAt.layout(i14, i12, measuredWidth3, measuredHeight);
                }
                measuredWidth += iB + iA + childAt.getMeasuredWidth() + this.f4627g;
                paddingTop = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        int paddingLeft;
        int size = View.MeasureSpec.getSize(i7);
        int mode = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i8);
        int i11 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft2 = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = i11 - getPaddingRight();
        int i12 = paddingTop;
        int i13 = 0;
        for (int i14 = 0; i14 < getChildCount(); i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, i7, i8);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i9 = marginLayoutParams.leftMargin + 0;
                    i10 = marginLayoutParams.rightMargin + 0;
                } else {
                    i9 = 0;
                    i10 = 0;
                }
                int i15 = paddingLeft2;
                if (paddingLeft2 + i9 + childAt.getMeasuredWidth() <= paddingRight || c()) {
                    paddingLeft = i15;
                } else {
                    paddingLeft = getPaddingLeft();
                    i12 = this.f4626e + paddingTop;
                }
                int measuredWidth = paddingLeft + i9 + childAt.getMeasuredWidth();
                int measuredHeight = i12 + childAt.getMeasuredHeight();
                if (measuredWidth > i13) {
                    i13 = measuredWidth;
                }
                paddingLeft2 = paddingLeft + i9 + i10 + childAt.getMeasuredWidth() + this.f4627g;
                if (i14 == getChildCount() - 1) {
                    i13 += i10;
                }
                paddingTop = measuredHeight;
            }
        }
        setMeasuredDimension(a(size, mode, i13 + getPaddingRight()), a(size2, mode2, paddingTop + getPaddingBottom()));
    }

    protected void setItemSpacing(int i7) {
        this.f4627g = i7;
    }

    protected void setLineSpacing(int i7) {
        this.f4626e = i7;
    }

    public void setSingleLine(boolean z6) {
        this.f4628h = z6;
    }

    public c(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f4628h = false;
        d(context, attributeSet);
    }
}
