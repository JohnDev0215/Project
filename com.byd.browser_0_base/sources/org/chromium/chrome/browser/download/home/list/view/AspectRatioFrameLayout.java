package org.chromium.chrome.browser.download.home.list.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.chromium.chrome.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class AspectRatioFrameLayout extends FrameLayout {
    public AspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getLayoutParams() instanceof LayoutParams) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.b) {
                    ((FrameLayout.LayoutParams) layoutParams).width = layoutParams.d;
                }
                if (layoutParams.c) {
                    ((FrameLayout.LayoutParams) layoutParams).height = layoutParams.e;
                }
                layoutParams.b = false;
                layoutParams.c = false;
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int size = (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getLayoutParams() instanceof LayoutParams) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int i4 = layoutParams.f;
                layoutParams.d = i4;
                int i5 = layoutParams.g;
                layoutParams.e = i5;
                float f = layoutParams.a;
                if (f > 0.0f) {
                    boolean z = layoutParams.b || i4 == 0;
                    boolean z2 = layoutParams.c || i5 == 0;
                    if (z) {
                        int i6 = ((FrameLayout.LayoutParams) layoutParams).height;
                        if (i6 == -1) {
                            i6 = size2;
                        }
                        ((FrameLayout.LayoutParams) layoutParams).width = Math.round(i6 * f);
                        layoutParams.b = true;
                    }
                    if (z2) {
                        int i7 = ((FrameLayout.LayoutParams) layoutParams).width;
                        if (i7 == -1) {
                            i7 = size;
                        }
                        ((FrameLayout.LayoutParams) layoutParams).height = Math.round(i7 / f);
                        layoutParams.c = true;
                    }
                }
            }
        }
        super.onMeasure(i, i2);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public AspectRatioFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public final float a;
        public boolean b;
        public boolean c;
        public int d;
        public int e;
        public int f;
        public int g;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AspectRatioFrameLayout_Layout);
            this.a = typedArrayObtainStyledAttributes.getFraction(R$styleable.AspectRatioFrameLayout_Layout_layout_aspectRatio, 1, 1, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // android.view.ViewGroup.LayoutParams
        public final void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            ((FrameLayout.LayoutParams) this).width = typedArray.getLayoutDimension(i, 0);
            int layoutDimension = typedArray.getLayoutDimension(i2, 0);
            ((FrameLayout.LayoutParams) this).height = layoutDimension;
            this.f = ((FrameLayout.LayoutParams) this).width;
            this.g = layoutDimension;
        }

        public LayoutParams() {
            super(-1, -1);
        }
    }
}
