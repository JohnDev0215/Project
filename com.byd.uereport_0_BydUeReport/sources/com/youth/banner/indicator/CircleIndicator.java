package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes2.dex */
public class CircleIndicator extends BaseIndicator {
    private int mNormalRadius;
    private int mSelectedRadius;
    private int maxRadius;

    public CircleIndicator(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int indicatorSize = this.config.getIndicatorSize();
        if (indicatorSize <= 1) {
            return;
        }
        float indicatorSpace = 0.0f;
        int i8 = 0;
        while (i8 < indicatorSize) {
            this.mPaint.setColor(this.config.getCurrentPosition() == i8 ? this.config.getSelectedColor() : this.config.getNormalColor());
            int selectedWidth = this.config.getCurrentPosition() == i8 ? this.config.getSelectedWidth() : this.config.getNormalWidth();
            float f8 = this.config.getCurrentPosition() == i8 ? this.mSelectedRadius : this.mNormalRadius;
            canvas.drawCircle(indicatorSpace + f8, this.maxRadius, f8, this.mPaint);
            indicatorSpace += selectedWidth + this.config.getIndicatorSpace();
            i8++;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        int indicatorSize = this.config.getIndicatorSize();
        if (indicatorSize <= 1) {
            return;
        }
        this.mNormalRadius = this.config.getNormalWidth() / 2;
        int selectedWidth = this.config.getSelectedWidth() / 2;
        this.mSelectedRadius = selectedWidth;
        this.maxRadius = Math.max(selectedWidth, this.mNormalRadius);
        int i10 = indicatorSize - 1;
        setMeasuredDimension((this.config.getIndicatorSpace() * i10) + this.config.getSelectedWidth() + (this.config.getNormalWidth() * i10), Math.max(this.config.getNormalWidth(), this.config.getSelectedWidth()));
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.mNormalRadius = this.config.getNormalWidth() / 2;
        this.mSelectedRadius = this.config.getSelectedWidth() / 2;
    }
}
