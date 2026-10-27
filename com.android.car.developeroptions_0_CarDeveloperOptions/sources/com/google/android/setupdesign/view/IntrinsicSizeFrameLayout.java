package com.google.android.setupdesign.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.setupdesign.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class IntrinsicSizeFrameLayout extends FrameLayout {
    private int intrinsicHeight;
    private int intrinsicWidth;

    public IntrinsicSizeFrameLayout(Context context) {
        super(context);
        this.intrinsicHeight = 0;
        this.intrinsicWidth = 0;
        init(context, null, 0);
    }

    public IntrinsicSizeFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.intrinsicHeight = 0;
        this.intrinsicWidth = 0;
        init(context, attributeSet, 0);
    }

    @TargetApi(11)
    public IntrinsicSizeFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.intrinsicHeight = 0;
        this.intrinsicWidth = 0;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudIntrinsicSizeFrameLayout, i, 0);
        this.intrinsicHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SudIntrinsicSizeFrameLayout_android_height, 0);
        this.intrinsicWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SudIntrinsicSizeFrameLayout_android_width, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(getIntrinsicMeasureSpec(i, this.intrinsicWidth), getIntrinsicMeasureSpec(i2, this.intrinsicHeight));
    }

    private int getIntrinsicMeasureSpec(int i, int i2) {
        if (i2 <= 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(this.intrinsicHeight, 1073741824);
        }
        return mode == Integer.MIN_VALUE ? View.MeasureSpec.makeMeasureSpec(Math.min(size, this.intrinsicHeight), 1073741824) : i;
    }
}
