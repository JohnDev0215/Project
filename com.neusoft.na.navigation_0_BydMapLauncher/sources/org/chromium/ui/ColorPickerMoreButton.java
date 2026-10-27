package org.chromium.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

/* JADX INFO: loaded from: classes3.dex */
public class ColorPickerMoreButton extends Button {
    private Paint mBorderPaint;

    public ColorPickerMoreButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ColorPickerMoreButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        Paint paint = new Paint();
        this.mBorderPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setColor(-1);
        this.mBorderPaint.setStrokeWidth(1.0f);
        this.mBorderPaint.setAntiAlias(false);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.5f, 0.5f, getWidth() - 1.5f, getHeight() - 1.5f, this.mBorderPaint);
        super.onDraw(canvas);
    }
}
