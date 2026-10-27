package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class GradientBackgroundView extends View {
    private int mEndColor;
    private Paint mPaint;
    private Rect mRect;
    private float mScaleX;
    private int mStartColor;

    public GradientBackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientBackgroundView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mRect = new Rect();
        this.mStartColor = context.getResources().getColor(R.color.gradient_background_start_color);
        this.mEndColor = context.getResources().getColor(R.color.gradient_background_end_color);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setShader();
        this.mScaleX = getWidth() / (getHeight() * 2.0f);
        this.mRect.right = getWidth();
        this.mRect.bottom = getHeight();
    }

    private void setShader() {
        this.mPaint.setShader(new RadialGradient(getWidth() / 2.0f, 0.0f, getHeight(), new int[]{this.mStartColor, this.mEndColor, 0}, new float[]{0.0f, 0.999f, 1.0f}, Shader.TileMode.CLAMP));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(this.mScaleX, 1.0f, getWidth() / 2.0f, 0.0f);
        canvas.drawRect(this.mRect, this.mPaint);
        canvas.restore();
    }
}
