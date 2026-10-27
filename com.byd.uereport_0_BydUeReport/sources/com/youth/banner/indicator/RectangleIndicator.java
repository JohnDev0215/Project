package com.youth.banner.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes2.dex */
public class RectangleIndicator extends BaseIndicator {
    public RectF rectF;

    public RectangleIndicator(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int indicatorSize = this.config.getIndicatorSize();
        if (indicatorSize <= 1) {
            return;
        }
        int i8 = 0;
        float indicatorSpace = 0.0f;
        while (i8 < indicatorSize) {
            this.mPaint.setColor(this.config.getCurrentPosition() == i8 ? this.config.getSelectedColor() : this.config.getNormalColor());
            this.rectF.set(indicatorSpace, 0.0f, (this.config.getCurrentPosition() == i8 ? this.config.getSelectedWidth() : this.config.getNormalWidth()) + indicatorSpace, this.config.getHeight());
            indicatorSpace += r4 + this.config.getIndicatorSpace();
            canvas.drawRoundRect(this.rectF, this.config.getRadius(), this.config.getRadius(), this.mPaint);
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
        int i10 = indicatorSize - 1;
        setMeasuredDimension((this.config.getIndicatorSpace() * i10) + (this.config.getNormalWidth() * i10) + this.config.getSelectedWidth(), this.config.getHeight());
    }

    public RectangleIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RectangleIndicator(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.rectF = new RectF();
    }
}
