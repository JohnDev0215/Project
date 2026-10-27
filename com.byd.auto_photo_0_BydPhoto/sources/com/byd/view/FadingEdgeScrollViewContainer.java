package com.byd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class FadingEdgeScrollViewContainer extends FrameLayout {
    private int edgeHeight;
    private int edgeOffset;
    private int edgeStartColor;
    private final Paint paint;
    private boolean scrolling;
    private boolean showEdgeInTouch;
    private LinearGradient topShade;

    public FadingEdgeScrollViewContainer(Context context) {
        this(context, null);
    }

    public FadingEdgeScrollViewContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FadingEdgeScrollViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FadingEdgeScrollViewContainer);
        this.edgeHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 50);
        this.edgeStartColor = typedArrayObtainStyledAttributes.getColor(2, 1439485132);
        this.edgeOffset = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.showEdgeInTouch = typedArrayObtainStyledAttributes.getBoolean(3, false);
        typedArrayObtainStyledAttributes.recycle();
        initTopShade();
    }

    private void initTopShade() {
        this.topShade = new LinearGradient(0.0f, 0.0f, 0.0f, this.edgeHeight, this.edgeStartColor, 0, Shader.TileMode.CLAMP);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.scrolling || !this.showEdgeInTouch) {
            this.paint.setShader(this.topShade);
            canvas.drawRect(this.edgeOffset, 0.0f, getWidth() - this.edgeOffset, this.edgeHeight, this.paint);
            int height = getHeight();
            float f = height - this.edgeHeight;
            float f2 = height;
            this.paint.setShader(new LinearGradient(0.0f, f, 0.0f, f2, 0, this.edgeStartColor, Shader.TileMode.CLAMP));
            canvas.drawRect(this.edgeOffset, f, getWidth() - this.edgeOffset, f2, this.paint);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        this.scrolling = action == 2;
        if (action == 1 || action == 3) {
            invalidate();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getEdgeHeight() {
        return this.edgeHeight;
    }

    public int getEdgeOffset() {
        return this.edgeOffset;
    }

    public int getEdgeStartColor() {
        return this.edgeStartColor;
    }

    public void setEdgeHeight(int i) {
        if (this.edgeHeight == i) {
            return;
        }
        this.edgeHeight = i;
        initTopShade();
        invalidate();
    }

    public void setEdgeOffset(int i) {
        this.edgeOffset = i;
    }

    public void setEdgeStartColor(int i) {
        if (this.edgeStartColor == i) {
            return;
        }
        this.edgeStartColor = i;
        initTopShade();
        invalidate();
    }
}
