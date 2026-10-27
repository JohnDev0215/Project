package com.google.android.material.divider;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bz;
import defpackage.in3;
import defpackage.om3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uc1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDivider extends View {
    public static final int j = R$style.Widget_MaterialComponents_MaterialDivider;
    public final sc1 e;
    public int f;
    public int g;
    public int h;
    public int i;

    public MaterialDivider(Context context) {
        this(context, null);
    }

    public int getDividerColor() {
        return this.g;
    }

    public int getDividerInsetEnd() {
        return this.i;
    }

    public int getDividerInsetStart() {
        return this.h;
    }

    public int getDividerThickness() {
        return this.f;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width;
        int i;
        super.onDraw(canvas);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z = om3.e.c(this) == 1;
        int i2 = z ? this.i : this.h;
        if (z) {
            width = getWidth();
            i = this.h;
        } else {
            width = getWidth();
            i = this.i;
        }
        int i3 = width - i;
        sc1 sc1Var = this.e;
        sc1Var.setBounds(i2, 0, i3, getBottom() - getTop());
        sc1Var.draw(canvas);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int measuredHeight = getMeasuredHeight();
        if (mode == Integer.MIN_VALUE || mode == 0) {
            int i3 = this.f;
            if (i3 > 0 && measuredHeight != i3) {
                measuredHeight = i3;
            }
            setMeasuredDimension(getMeasuredWidth(), measuredHeight);
        }
    }

    public void setDividerColor(int i) {
        if (this.g != i) {
            this.g = i;
            this.e.n(ColorStateList.valueOf(i));
            invalidate();
        }
    }

    public void setDividerColorResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setDividerColor(context.getColor(i));
    }

    public void setDividerInsetEnd(int i) {
        this.i = i;
    }

    public void setDividerInsetEndResource(int i) {
        setDividerInsetEnd(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerInsetStart(int i) {
        this.h = i;
    }

    public void setDividerInsetStartResource(int i) {
        setDividerInsetStart(getContext().getResources().getDimensionPixelOffset(i));
    }

    public void setDividerThickness(int i) {
        if (this.f != i) {
            this.f = i;
            requestLayout();
        }
    }

    public void setDividerThicknessResource(int i) {
        setDividerThickness(getContext().getResources().getDimensionPixelSize(i));
    }

    public MaterialDivider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialDividerStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialDivider(Context context, AttributeSet attributeSet, int i) {
        int i2 = j;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.e = new sc1();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.MaterialDivider, i, i2, new int[0]);
        this.f = typedArrayD.getDimensionPixelSize(R$styleable.MaterialDivider_dividerThickness, getResources().getDimensionPixelSize(R$dimen.material_divider_thickness));
        this.h = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetStart, 0);
        this.i = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetEnd, 0);
        setDividerColor(rc1.a(context2, typedArrayD, R$styleable.MaterialDivider_dividerColor).getDefaultColor());
        typedArrayD.recycle();
    }
}
