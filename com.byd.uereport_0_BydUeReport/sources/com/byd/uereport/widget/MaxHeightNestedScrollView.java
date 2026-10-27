package com.byd.uereport.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class MaxHeightNestedScrollView extends NestedScrollView {
    public int F;

    public MaxHeightNestedScrollView(Context context) {
        super(context);
        this.F = -1;
    }

    public final void R(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaxHeightNestedScrollView);
        this.F = typedArrayObtainStyledAttributes.getLayoutDimension(0, this.F);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        int i10 = this.F;
        if (i10 > 0) {
            i9 = View.MeasureSpec.makeMeasureSpec(i10, Integer.MIN_VALUE);
        }
        super.onMeasure(i8, i9);
    }

    public MaxHeightNestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = -1;
        R(context, attributeSet);
    }

    public MaxHeightNestedScrollView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.F = -1;
        R(context, attributeSet);
    }
}
