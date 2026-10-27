package com.byd.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;

/* JADX INFO: loaded from: classes2.dex */
public class PaintColorItemView extends ImageView {
    private int borderColor;
    private int borderSize;
    private int centerX;
    private int centerY;
    private int color;
    private Paint paint;
    private float radius;
    private String text;

    public PaintColorItemView(Context context) {
        this(context, null);
    }

    public PaintColorItemView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.borderSize = 1;
        this.borderColor = -1;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStrokeWidth(this.borderSize);
        this.paint.setTextSize(20.0f);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.color == 0) {
            super.onDraw(canvas);
            return;
        }
        this.paint.setStyle(Paint.Style.FILL);
        int i = this.color;
        if (i != -1 || this.text == null) {
            this.paint.setColor(i);
            canvas.drawCircle(this.centerX, this.centerY, this.radius, this.paint);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setColor(this.borderColor);
            this.paint.setStrokeWidth(this.borderSize);
            canvas.drawCircle(this.centerX, this.centerY, this.radius, this.paint);
            return;
        }
        Rect rect = new Rect();
        Paint paint = this.paint;
        String str = this.text;
        paint.getTextBounds(str, 0, str.length(), rect);
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawText(this.text, this.centerX - rect.centerX(), this.centerY - rect.centerY(), this.paint);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.centerX = getMeasuredWidth() / 2;
        this.centerY = getMeasuredHeight() / 2;
    }

    public PaintColorItemView setBorderColor(int i) {
        this.borderColor = i;
        return this;
    }

    public PaintColorItemView setBorderSize(int i) {
        this.borderSize = i;
        return this;
    }

    public PaintColorItemView setColor(int i) {
        this.color = i;
        this.paint.setColor(i);
        return this;
    }

    public PaintColorItemView setPaintSize(float f) {
        this.radius = f / 2.0f;
        return this;
    }

    public PaintColorItemView setText(String str) {
        this.text = str;
        return this;
    }
}
