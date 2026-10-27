package com.android.inputmethod.latin.setup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.latin.R;

/* JADX INFO: loaded from: classes2.dex */
public final class SetupStepIndicatorView extends View {
    private final Paint mIndicatorPaint;
    private final Path mIndicatorPath;
    private float mXRatio;

    public SetupStepIndicatorView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.mIndicatorPath = new Path();
        Paint paint = new Paint();
        this.mIndicatorPaint = paint;
        paint.setColor(getResources().getColor(R.color.setup_step_background));
        paint.setStyle(Paint.Style.FILL);
    }

    public void setIndicatorPosition(final int stepPos, final int totalStepNum) {
        float f = 1.0f / totalStepNum;
        float f2 = (stepPos * f) + (f / 2.0f);
        if (ViewCompat.getLayoutDirection(this) == 1) {
            f2 = 1.0f - f2;
        }
        this.mXRatio = f2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        int width = (int) (getWidth() * this.mXRatio);
        int height = getHeight();
        this.mIndicatorPath.rewind();
        this.mIndicatorPath.moveTo(width, 0.0f);
        float f = height;
        this.mIndicatorPath.lineTo(width + height, f);
        this.mIndicatorPath.lineTo(width - height, f);
        this.mIndicatorPath.close();
        canvas.drawPath(this.mIndicatorPath, this.mIndicatorPaint);
    }
}
