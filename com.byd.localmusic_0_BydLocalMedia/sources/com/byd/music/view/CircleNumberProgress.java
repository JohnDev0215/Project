package com.byd.music.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public class CircleNumberProgress extends View {
    private Context context;
    private boolean isMeasured;
    private Paint paintDone;
    private int paintDoneColor;
    private int paintProgressWidth;
    private int paintProgressWidthPx;
    private Paint paintText;
    private int paintTextColor;
    private int paintTextSize;
    private int paintTextSizePx;
    private Paint paintUndone;
    private int paintUndoneColor;
    private Path path;
    private int progress;
    private int radiusText;
    private RectF rectF;
    private RectF rectF2;

    public CircleNumberProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paintProgressWidth = 3;
        this.paintTextSize = 20;
        this.paintUndoneColor = -5592406;
        this.paintDoneColor = -9983260;
        this.paintTextColor = -65417;
        this.paintUndone = new Paint();
        this.paintDone = new Paint();
        this.paintText = new Paint();
        this.rectF = new RectF();
        this.rectF2 = new RectF();
        this.path = new Path();
        this.isMeasured = false;
        this.context = context;
        initData();
    }

    private void initData() {
        this.paintProgressWidthPx = this.paintProgressWidth;
        this.paintTextSizePx = this.paintTextSize;
        this.paintUndone.setColor(this.paintUndoneColor);
        this.paintUndone.setStrokeWidth(this.paintProgressWidthPx);
        this.paintUndone.setAntiAlias(true);
        this.paintUndone.setStyle(Paint.Style.STROKE);
        this.paintDone.setColor(this.paintDoneColor);
        this.paintDone.setStrokeWidth(this.paintProgressWidthPx);
        this.paintDone.setAntiAlias(true);
        this.paintDone.setStyle(Paint.Style.STROKE);
        this.paintText.setColor(this.paintTextColor);
        this.paintText.setTextSize(this.paintTextSizePx);
        this.paintText.setAntiAlias(true);
        this.paintText.setStyle(Paint.Style.STROKE);
        this.paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.isMeasured) {
            return;
        }
        getWidthAndHeight();
        this.isMeasured = true;
    }

    private void getWidthAndHeight() {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        int i = measuredHeight > measuredWidth ? measuredWidth / 2 : measuredHeight / 2;
        Rect rect = new Rect();
        this.paintText.getTextBounds("100%", 0, 4, rect);
        int iHeight = rect.height();
        int i2 = this.paintProgressWidthPx;
        int i3 = i - (i2 > iHeight ? i2 / 2 : iHeight / 2);
        this.rectF.left = r2 - i3;
        this.rectF.top = r3 - i3;
        this.rectF.right = r2 + i3;
        this.rectF.bottom = r3 + i3;
        this.radiusText = i3 - (iHeight / 2);
        this.rectF2.left = r2 - r0;
        this.rectF2.top = r3 - this.radiusText;
        this.rectF2.right = r2 + this.radiusText;
        this.rectF2.bottom = r3 + this.radiusText;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.rectF, 0.0f, 360.0f, false, this.paintUndone);
        canvas.drawArc(this.rectF, -90.0f, (this.progress / 100.0f) * 360.0f, false, this.paintDone);
        String str = this.progress + "%";
        int length = str.length();
        float[] fArr = new float[length];
        this.paintText.getTextWidths(str, 0, str.length(), fArr);
        float f = 0.0f;
        for (int i = 0; i < length; i++) {
            f += fArr[i];
        }
        float f2 = (float) ((((double) ((f * 360.0f) / 2.0f)) / 3.141592653589793d) / ((double) this.radiusText));
        this.path.addArc(this.rectF2, ((this.progress * 3.6f) - 90.0f) - (f2 / 2.0f), f2);
        canvas.drawTextOnPath(str, this.path, 0.0f, 0.0f, this.paintText);
        this.path.reset();
    }

    public void setProgress(int i) {
        this.progress = i;
        invalidate();
    }
}
