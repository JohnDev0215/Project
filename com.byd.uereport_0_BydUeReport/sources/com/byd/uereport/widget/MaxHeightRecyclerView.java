package com.byd.uereport.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.uereport.R;

/* JADX INFO: loaded from: classes2.dex */
public class MaxHeightRecyclerView extends RecyclerView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3428d;

    public MaxHeightRecyclerView(Context context) {
        super(context);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaxHeightRecyclerView);
        this.f3428d = typedArrayObtainStyledAttributes.getLayoutDimension(0, this.f3428d);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i8, int i9) {
        int i10 = this.f3428d;
        if (i10 > 0) {
            i9 = View.MeasureSpec.makeMeasureSpec(i10, Integer.MIN_VALUE);
        }
        super.onMeasure(i8, i9);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        a(context, attributeSet);
    }
}
