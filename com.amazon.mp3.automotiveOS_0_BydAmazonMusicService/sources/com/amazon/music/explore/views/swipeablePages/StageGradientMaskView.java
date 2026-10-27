package com.amazon.music.explore.views.swipeablePages;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.amazon.music.explore.R;

/* JADX INFO: loaded from: classes4.dex */
public class StageGradientMaskView extends View {
    private int[] colors;
    private int height;
    private Paint paint;
    private float[] positions;
    private Resources resources;

    public StageGradientMaskView(Context context) {
        super(context);
        init();
    }

    public StageGradientMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public StageGradientMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.resources = getResources();
        this.paint = new Paint(1);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.height = i2;
        setGradientProperties();
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.paint);
    }

    private void setGradientProperties() {
        Resources resources = this.resources;
        int i = R.color.primary;
        this.colors = new int[]{resources.getColor(i), this.resources.getColor(i), this.resources.getColor(R.color.white_15)};
        this.positions = new float[]{0.0f, 0.5f, 1.0f};
        createGradient();
    }

    private void createGradient() {
        this.paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, this.height, this.colors, this.positions, Shader.TileMode.CLAMP));
        this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }
}
