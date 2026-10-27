package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class PaddedFrameLayout extends FrameLayout {
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;

    public PaddedFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PaddedFrameLayout);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.PaddedFrameLayout_maxChildWidth, -1);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.PaddedFrameLayout_maxChildHeight, -1);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.g = getPaddingLeft();
        this.h = getPaddingTop();
        this.i = getPaddingBottom();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size;
        int size2;
        int i3 = this.g;
        if (this.e != -1 && (size2 = (View.MeasureSpec.getSize(i) - (this.g * 2)) - this.e) > 0) {
            i3 += size2 / 2;
        }
        int i4 = this.h;
        int i5 = this.i;
        if (this.f != -1 && (size = (View.MeasureSpec.getSize(i2) - (this.h + this.i)) - this.f) > 0) {
            int i6 = size / 2;
            i4 += i6;
            i5 += i6;
        }
        setPadding(i3, i4, i3, i5);
        super.onMeasure(i, i2);
    }

    public void setMaxChildHeight(int i) {
        this.f = i;
    }

    public void setMaxChildWidth(int i) {
        this.e = i;
    }
}
