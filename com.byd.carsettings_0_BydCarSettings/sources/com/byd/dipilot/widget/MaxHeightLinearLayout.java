package com.byd.dipilot.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.byd.dipilot.bpresenter.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class MaxHeightLinearLayout extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3648a;

    public MaxHeightLinearLayout(Context context) {
        super(context);
        this.f3648a = 0.0f;
    }

    public final void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MaxHeightLinearLayout);
        this.f3648a = typedArrayObtainStyledAttributes.getFloat(R$styleable.MaxHeightLinearLayout_highRatio, 0.8f);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f3648a > 0.0f) {
            ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(new Point());
            int i3 = (int) (r1.y * this.f3648a);
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            if (mode == Integer.MIN_VALUE) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), mode);
            } else if (mode == 0) {
                i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
            } else if (mode == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
            }
        }
        super.onMeasure(i, i2);
    }

    public MaxHeightLinearLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3648a = 0.0f;
        a(attributeSet);
    }

    public MaxHeightLinearLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3648a = 0.0f;
        a(attributeSet);
    }
}
