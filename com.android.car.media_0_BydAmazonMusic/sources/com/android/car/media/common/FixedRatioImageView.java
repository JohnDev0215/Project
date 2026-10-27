package com.android.car.media.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class FixedRatioImageView extends ImageView {
    public float a;

    public FixedRatioImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getLayoutParams().height == -1) {
            int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
            setMeasuredDimension((int) (defaultSize * this.a), defaultSize);
        } else {
            if (getLayoutParams().width != -1) {
                throw new IllegalArgumentException("Either width or height should be set to MATCH_PARENT");
            }
            int defaultSize2 = View.getDefaultSize(getSuggestedMinimumWidth(), i);
            setMeasuredDimension(defaultSize2, (int) (defaultSize2 / this.a));
        }
    }

    public void setAspectRatio(float f) {
        this.a = f;
        requestLayout();
    }

    public FixedRatioImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FixedRatioImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context.obtainStyledAttributes(attributeSet, com.android.car.ui.R$styleable.FixedRatioImageView).getFloat(com.android.car.ui.R$styleable.FixedRatioImageView_aspect_ratio, 1.0f);
    }
}
